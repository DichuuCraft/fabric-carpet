package carpet.mixins;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import carpet.utils.PlayerCountUtil;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;

@Mixin(PlayerManager.class)
public class PlayerManager_playerCountMixin {
    @Redirect(method = "checkCanJoin", at = @At(
        value = "INVOKE",
        target = "Ljava/util/List;size()I"
    ))
    private int getPlayerCount(List<ServerPlayerEntity> cela){
        return PlayerCountUtil.getPlayerCount(cela);
    }

}