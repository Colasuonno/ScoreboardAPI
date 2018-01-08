package it.mr_replete.scoreboardapi.bukkitevents;


import it.mr_replete.scoreboardapi.ScoreboardAPI;
import it.mr_replete.scoreboardapi.util.ScoreboardUtil;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;



public class Join implements Listener {

    public Join() {
        Bukkit.getPluginManager().registerEvents(this, ScoreboardAPI.getInstance());
    }

    @EventHandler
    public void onjoin(PlayerJoinEvent e) {
        ScoreboardUtil.registerPlayer(e.getPlayer());
    }


}
