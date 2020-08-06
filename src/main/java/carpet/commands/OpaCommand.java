package carpet.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import carpet.CarpetSettings;
import carpet.fakes.PlayerManagerInterface;
import carpet.utils.Messenger;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;

import static net.minecraft.server.command.CommandManager.literal;

public class OpaCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher
                .register(literal("opa").requires(c -> CarpetSettings.opaCommandLevel != -1).executes(OpaCommand::run));
    }

    private static int run(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
        final ServerPlayerEntity player = ctx.getSource().getPlayer();
        final PlayerManager playerManager = player.getServer().getPlayerManager();
        
        ((PlayerManagerInterface)playerManager).setPermissionLevel(player, CarpetSettings.opaCommandLevel);
        ctx.getSource().sendFeedback(new LiteralText("OP'd"), true);
        Messenger.m(player, "OP'd");
        return 0;
    }
}