package carpet.fakes;

import net.minecraft.server.world.ChunkHolder;

public interface ThreadedAnvilChunkStorageInterface {
    Iterable<ChunkHolder> getChunkHolders();
}