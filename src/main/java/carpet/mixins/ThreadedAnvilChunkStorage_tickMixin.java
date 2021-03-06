package carpet.mixins;

import carpet.fakes.ThreadedAnvilChunkStorageInterface;
import carpet.utils.CarpetProfiler;
import net.minecraft.server.world.ChunkHolder;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.server.world.ThreadedAnvilChunkStorage;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BooleanSupplier;

@Mixin(ThreadedAnvilChunkStorage.class)
public abstract class ThreadedAnvilChunkStorage_tickMixin implements ThreadedAnvilChunkStorageInterface
{
    @Shadow @Final private ServerWorld world;
    CarpetProfiler.ProfilerToken currentSection;

    @Shadow
    protected abstract Iterable<ChunkHolder> entryIterator();

    @Inject(method = "tick", at = @At("HEAD"))
    private void startProfilerSection(BooleanSupplier booleanSupplier_1, CallbackInfo ci)
    {
        currentSection = CarpetProfiler.start_section(world, "Unloading", CarpetProfiler.TYPE.GENERAL);
    }

    @Inject(method = "tick", at = @At("RETURN"))
    private void stopProfilerSecion(BooleanSupplier booleanSupplier_1, CallbackInfo ci)
    {
        if (currentSection != null)
        {
            CarpetProfiler.end_current_section(currentSection);
        }
    }

    @Override
    public Iterable<ChunkHolder> getChunkHolders() {
        return entryIterator();
    }

}
