package nerd.amara;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.minecraft.entity.player.PlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Francetiers_tagger implements ClientModInitializer {
	public static final String MOD_ID = "francetiers_tagger";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitializeClient() {
		ClientEntityEvents.ENTITY_LOAD.register(((entity, clientWorld) ->{
			if (entity instanceof PlayerEntity){
				((TierModifier)entity).setSuffix(" (lol)");
			}
		}));

		LOGGER.info("france tiers tagger initialized");
	}
}