package it.mr_replete.scoreboardapi.util;

import com.google.common.collect.Lists;
import it.mr_replete.scoreboardapi.NiceBoard;
import it.mr_replete.scoreboardapi.ScoreboardAPI;
import it.mr_replete.scoreboardapi.settings.Settings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreboardUtil {

    /**
     *
     * With this method you can register a NiceBoard! Have fun
     *
     * @param displaySlot
     * @param title
     * @return
     */
    public static NiceBoard registerScoreboard(DisplaySlot displaySlot, String title) {

        // init scoreboard (bukkit)
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

        // checking for error
        if (title.length() > 32){
           return null;
        }

        // register objective
        Objective objective = board.registerNewObjective("Objective", "dummy");

        // adding stuff to scoreboard
         objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', title));
        objective.setDisplaySlot(displaySlot);

        NiceBoard scoreboard = new NiceBoard(board);

        return scoreboard;

    }

    /**
     *
     * This method create or get Team by the "api_+index"
     *
     * @param scoreboard
     * @param index
     * @return
     */
    public static Team getTeam(NiceBoard scoreboard, int index){
        if (scoreboard.getScoreboard().getTeam("api_"+index) != null){
            return scoreboard.getScoreboard().getTeam("api_"+index);
        } else{
            return scoreboard.getScoreboard().registerNewTeam("api_"+index);
        }
    }

    /**
     *
     * Remove a line of NiceBoard by an index(Score)
     *
     * @param scoreboard
     * @param index
     */
    public static void removeScore(NiceBoard scoreboard, int index){
        String key = key(index-Settings.STARTING_SCORE);
        scoreboard.getScoreboard().resetScores(key);
    }

    /**
     *
     * Set the score of NiceBoard to an index(Score)
     *
     * @param scoreboard
     * @param text
     * @param index
     */
    public static void setScore(NiceBoard scoreboard, String text, int index){
        Component cont = new Component(null, null);
        if (text.length() > 16){
            cont.setA(text.substring(0, 16));
            cont.setB(ChatColor.getLastColors(text.substring(0,16)) + text.substring(16, text.length()));
        } else{
            cont.setA(text);
            cont.setB("");
        }
        Team currentTeam = getTeam(scoreboard,index);
        String key = key(index-Settings.STARTING_SCORE);
        currentTeam.addEntry(key);
        scoreboard.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(key).setScore(index);
        scoreboard.getScoreboard().getTeam(currentTeam.getName()).setPrefix(cont.getA());
        scoreboard.getScoreboard().getTeam(currentTeam.getName()).setSuffix(cont.getB());
    }


    /**
     *
     * @param index
     * @return to a custom code of ChatColor
     */
    public static String key(int index){
        return ChatColor.values()[index].toString() + ChatColor.RESET;
    }

    /**
     *
     * This method run a repeating scheduler due update scoreboard
     *
     * @param player
     */
    public static void registerPlayer(Player player){
        if (Settings.DEFAULT_ACTIVE) {
            final NiceBoard niceBoard = ScoreboardUtil.registerScoreboard(DisplaySlot.SIDEBAR, Settings.TITLE);
            BukkitRunnable runnable = new BukkitRunnable() {
                @Override
                public void run() {
                    if (Bukkit.getPlayer(player.getName()) != null) {
                        for (int j = Settings.SCOREBOARDS_VALUES.size() - 1; j >= 0; j--) {
                            String text = PlaceHolders.replace(Lists.reverse(Settings.SCOREBOARDS_VALUES).get(j), player);
                            niceBoard.setScore(text, j + Settings.STARTING_SCORE);
                            niceBoard.addPlayer(player);
                        }
                    } else{
                        cancel();
                    }
                }
            };
            runnable.runTaskTimer(ScoreboardAPI.getInstance(), 0L, 20L);

        }
    }

    private static final class Component {

        private String a;
        private String b;

        public Component(String a, String b) {
            this.a = a;
            this.b = b;
        }

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }
    }

}
