package carpet.utils;

import java.nio.charset.StandardCharsets;

import carpet.CarpetSettings;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.network.packet.c2s.play.CustomPayloadC2SPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;

public class VoxelMapUtil {
    private static final String CHANNEL = "world_id";

    public static void sendWorldInfoPacket(ServerPlayerEntity player){
        String name = CarpetSettings.voxelMapWorldName;
        if (!name.equals("")){
            name += '/' + player.dimension.toString();
            byte[] bytes = name.getBytes(StandardCharsets.UTF_8);

            ByteBuf bbuf = Unpooled.buffer();
            PacketByteBuf buf = new PacketByteBuf(bbuf);
            buf.writeByte(0);
            buf.writeByte(bytes.length);
            buf.writeBytes(bytes);
            player.networkHandler.sendPacket(new CustomPayloadC2SPacket(new Identifier(CHANNEL), buf));
            bbuf.release();
        }
    }
}