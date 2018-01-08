package it.mr_replete.scoreboardapi.util;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class ConfUtil {

    /**
     *
     * Translate an List<String> repleacing & to ChatColor code
     *
     * @param list
     * @return
     */
    public static List<String> translateList(List<String> list){
        List<String> newlist = new ArrayList<String>();
        for (String s : list){
            newlist.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        return newlist;
    }

}
