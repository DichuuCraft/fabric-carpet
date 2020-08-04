package carpet.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import carpet.utils.VoxelMapUtil;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.dimension.DimensionType;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntity_voxelMapMixin {
    @Inject(method = "changeDimension", at = @At("RETURN"))
    private void onChangeDimension(DimensionType newDimension, CallbackInfoReturnable<Void> ci){
        VoxelMapUtil.sendWorldInfoPacket((ServerPlayerEntity)(Object)this);
    }
}