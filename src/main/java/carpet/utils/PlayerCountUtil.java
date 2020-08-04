package carpet.utils;

import java.util.List;

import carpet.CarpetSettings;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerCountUtil {
    public static int getPlayerCount(List<?> players){
        if (CarpetSettings.fakesNoPlayerCount){
            int c = 0;
            for (Object p: players){
                if (p.getClass().equals(ServerPlayerEntity.class)){
                    c++;
                }
            }
            return c;
        }
        else {
            return players.size();
        }
    }
}