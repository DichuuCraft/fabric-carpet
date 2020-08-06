package carpet.fakes;

import net.minecraft.server.network.ServerPlayerEntity;

public interface PlayerManagerInterface {
	void setPermissionLevel(ServerPlayerEntity player, int level);
}