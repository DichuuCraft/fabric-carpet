package carpet.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import carpet.utils.VoxelMapUtil;
import net.minecraft.network.packet.c2s.play.CustomPayloadC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandler_voxelMapMixin {
    @Shadow
    public ServerPlayerEntity player;
    @Inject(method = "onCustomPayload", at = @At("HEAD"))
    private void onCustomPayload(CustomPayloadC2SPacket packet, CallbackInfo ci){
        CustomPayloadC2SPacketAccessor pa = (CustomPayloadC2SPacketAccessor)packet;
        if (pa.getChannel().equals(VoxelMapUtil.CHANNEL)){
            VoxelMapUtil.sendWorldInfoPacket(player);
        }
    }
}