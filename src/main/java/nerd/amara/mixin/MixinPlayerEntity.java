package nerd.amara.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import nerd.amara.TierModifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.Font;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity implements TierModifier {
    @Shadow public abstract void remove(Entity.RemovalReason reason);

    private String suffix = null;
    @Override
    public String getSuffix(){
        return suffix;
    }
    @Override
    public void setSuffix(String value){
        this.suffix=value;
    }
    @ModifyReturnValue(method = "getDisplayName", at = @At("RETURN"))
    public Text lol(Text original) {
        PlayerEntity self = (PlayerEntity) (Object) this;
        //String username = self.getName().getString();
        String sufix = this.suffix;
        if (suffix!=null) {
            return original.copy().append(Text.literal(" " + sufix).styled(s -> s.withColor(Formatting.WHITE).withFont(Identifier.of("frtl","lol"))));
        }
        return original;
    }
}
