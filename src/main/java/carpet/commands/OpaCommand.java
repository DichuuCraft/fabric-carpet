package carpet.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;

import carpet.CarpetSettings;
import net.minecraft.server.command.ServerCommandSource;

import static net.minecraft.server.command.CommandManager.literal;

public class OpaCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher){
        dispatcher.register(literal("opa").requires(c -> CarpetSettings.opaCommandLevel != -1).executes(OpaCommand::run));
    }

    private static int run(CommandContext<ServerCommandSource> src){
        
        return 0;
    }
}