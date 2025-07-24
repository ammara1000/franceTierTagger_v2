package nerd.amara;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import nerd.amara.tiers.PlayerInfo;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;
import static net.minecraft.text.StringVisitable.styled;

public class CommandManager {
    public static void registerCommands(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(literal("francetiers")
                .then(argument("name", StringArgumentType.word())
                        .suggests(playerNameSuggester())
                        .executes(context -> {
                            String name = StringArgumentType.getString(context, "name");
                            PlayerInfo info = Http.getJson("https://tierlistmc.fr/search_player.php?pseudo="+name, PlayerInfo.class);
                            if (info != null) {
                                Text text=Text.literal(ShowedTier.showed_message(info)).styled(s -> s.withColor(Formatting.WHITE).withFont(Identifier.of("frtl","lol")));
                                MinecraftClient.getInstance().player.sendMessage(text,false);
                            }



                            return 1;
                        }))
        );
    }
    private static SuggestionProvider<FabricClientCommandSource> playerNameSuggester() {
        return (context, builder) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.getNetworkHandler() == null) {
                return builder.buildFuture(); // Pas connecté
            }

            Collection<PlayerListEntry> players = client.getNetworkHandler().getPlayerList();
            List<String> names = players.stream()
                    .map(entry -> entry.getProfile().getName())
                    .collect(Collectors.toList());

            for (String name : names) {
                builder.suggest(name);
            }

            return builder.buildFuture();
        };
    }
}
