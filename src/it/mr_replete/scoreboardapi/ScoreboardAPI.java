package it.mr_replete.scoreboardapi;

import it.mr_replete.scoreboardapi.settings.Settings;
import it.mr_replete.scoreboardapi.bukkitevents.Join;
import it.mr_replete.scoreboardapi.util.ScoreboardUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;


public class ScoreboardAPI extends JavaPlugin {

    public static ScoreboardAPI instance;

    @Override
    public void onEnable() {
        instance = this;

        // register bukkit events

        new Join();

        // init config
        saveDefaultConfig();

        // setting up
        Settings.setUP();

        // register online players
        for (Player op : Bukkit.getOnlinePlayers()){
            ScoreboardUtil.registerPlayer(op);
        }

    }

    @Override
    public void onDisable() {
        for (Player op : Bukkit.getOnlinePlayers()){
            op.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
        }
    }

    public static ScoreboardAPI getInstance() {
        return instance;
    }
}
