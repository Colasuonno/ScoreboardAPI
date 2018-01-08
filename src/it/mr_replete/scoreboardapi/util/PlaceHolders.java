package it.mr_replete.scoreboardapi.util;

import com.google.common.collect.Lists;
import it.mr_replete.scoreboardapi.reflection.Reflection;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class PlaceHolders {


    private static List<String> placeholders = Lists.newArrayList("{PLAYER}",
            "{PING}",
            "{MAXPLAYERS}",
            "{CURRENTPLAYERS}");

    /**
     *
     * This is an ugly method ik
     *
     * @param input
     * @param player
     * @return
     */
    public static String replace(String input, Player player){
        return input.replace("{PING}", String.valueOf(Reflection.getPlayerPing(player))).replace("{PLAYER}", player.getName()).replace("{MAXPLAYERS}",String.valueOf(Bukkit.getServer().getMaxPlayers())).replace("{CURRENTPLAYERS}", String.valueOf(Bukkit.getServer().getOnlinePlayers().size()));
    }

}
