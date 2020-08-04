package carpet.mixins;

import java.util.List;
import java.util.function.Predicate;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import carpet.CarpetSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameMode;

@Mixin(ServerWorld.class)
public abstract class ServerWorld_bedrockStatsMixin {
    @Shadow @Final
    private MinecraftServer server;

    @Shadow
    public abstract List<ServerPlayerEntity> getPlayers();

    @Inject(method = "onBlockChanged", at = @At("HEAD"))
    private void onBlockChanged(BlockPos pos, BlockState oldBlock, BlockState newBlock, CallbackInfo ci){
        int r = CarpetSettings.bedrockBreakingDetectRadius;
        if (r != -1 && oldBlock.getBlock() == Blocks.BEDROCK && newBlock.getBlock() != Blocks.BEDROCK){
            r *= r;

            ServerPlayerEntity selected = null;
            double selectedDistance = 0;
            for (ServerPlayerEntity player: getPlayers()){
                double r2 = pos.getSquaredDistance(player.x, player.y, player.z, true);
                if (r2 <= r && player.interactionManager.getGameMode() == GameMode.SURVIVAL){
                    if (selected == null || r2 < selectedDistance){
                        selectedDistance = r2;
                        selected = player;
                    }
                }
            }
            if (selected != null){
                selected.increaseStat(Stats.MINED.getOrCreateStat(Blocks.BEDROCK), 1);
            }
        }
    }
}