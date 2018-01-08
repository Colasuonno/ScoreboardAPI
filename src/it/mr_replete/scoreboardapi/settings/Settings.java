package it.mr_replete.scoreboardapi.settings;

import it.mr_replete.scoreboardapi.ScoreboardAPI;
import it.mr_replete.scoreboardapi.util.ConfUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class Settings {

    public static List<String> SCOREBOARDS_VALUES;
    public static boolean DEFAULT_ACTIVE;
    public static int STARTING_SCORE;
    public static String TITLE;

    public static void setUP(){

        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "[ScoreboardAPI] Loading components...");

        FileConfiguration configuration = ScoreboardAPI.getInstance().getConfig();

        SCOREBOARDS_VALUES = ConfUtil.translateList(configuration.getStringList("scoreboard"));
        DEFAULT_ACTIVE = configuration.getBoolean("options.active");
        STARTING_SCORE = configuration.getInt("options.starting_score");
        TITLE = ChatColor.translateAlternateColorCodes('&', configuration.getString("options.title"));

        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[ScoreboardAPI] Components Loaded! OK");

    }

}
