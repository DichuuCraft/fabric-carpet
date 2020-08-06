package carpet.mixins;

import com.mojang.authlib.GameProfile;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import carpet.fakes.PlayerManagerInterface;
import net.minecraft.server.OperatorEntry;
import net.minecraft.server.OperatorList;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;

@Mixin(PlayerManager.class)
public abstract class PlayerManager_commandOpaMixin implements PlayerManagerInterface {
    @Shadow @Final
    private OperatorList ops;

    @Shadow
    public abstract void sendCommandTree(ServerPlayerEntity player);
    
    @Override
    public void setPermissionLevel(ServerPlayerEntity player, int level) {
        final GameProfile profile = player.getGameProfile();
        OperatorEntry entry = ops.get(profile);
        if (entry == null || entry.getPermissionLevel() < level){
            entry = new OperatorEntry(profile, level, false);
        }
        ops.add(entry);
        sendCommandTree(player);
    }
    
}