package carpet.utils;

import java.nio.charset.StandardCharsets;

import carpet.CarpetSettings;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;

public class VoxelMapUtil {
    public static final Identifier CHANNEL = new Identifier("worldinfo:world_id");

    public static void sendWorldInfoPacket(ServerPlayerEntity player){
        String name = CarpetSettings.voxelMapWorldName;
        if (!name.equals("")){
            byte[] bytes = name.getBytes(StandardCharsets.UTF_8);

            ByteBuf bbuf = Unpooled.buffer();
            PacketByteBuf buf = new PacketByteBuf(bbuf);
            buf.writeByte(0);
            buf.writeByte(bytes.length);
            buf.writeBytes(bytes);
            player.networkHandler.sendPacket(new CustomPayloadS2CPacket(CHANNEL, buf), future -> {
                bbuf.release();
            });
        }
    }
}