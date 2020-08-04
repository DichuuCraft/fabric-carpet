package carpet.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import carpet.utils.PlayerCountUtil;
import net.minecraft.server.MinecraftServer;

@Mixin(MinecraftServer.class)
public class MinecraftServer_playerCountMixin {
    @Redirect(method = "tick", at = @At(
        value = "INVOKE",
        target = "Lnet/minecraft/server/MinecraftServer;getCurrentPlayerCount()I"
    ), expect = 3)
    private int getCurrentPlayerCount(MinecraftServer cela){
        return PlayerCountUtil.getPlayerCount(cela.getPlayerManager().getPlayerList());
    }
}