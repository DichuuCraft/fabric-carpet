package carpet.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import carpet.CarpetSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.FacingBlock;
import net.minecraft.block.ObserverBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.IWorld;

@Mixin(ObserverBlock.class)
public class ObserverBlock_disabledMixin extends FacingBlock {
    protected ObserverBlock_disabledMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "getStateForNeighborUpdate", cancellable = true, at = @At("HEAD"))
    private void getStateForNeighborUpdate(BlockState state, Direction facing, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos, CallbackInfoReturnable<BlockState> ci){
        if (CarpetSettings.observerDisabled && world instanceof ServerWorld){
            if (!((ServerWorld)world).isInsideTick()){
                ci.setReturnValue(super.getStateForNeighborUpdate(state, facing, neighborState, world, pos, neighborPos));
            }
        }
    }
}