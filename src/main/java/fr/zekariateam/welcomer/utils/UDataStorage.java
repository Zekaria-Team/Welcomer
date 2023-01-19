package fr.zekariateam.welcomer.utils;

import dev.dejvokep.boostedyaml.YamlDocument;
import fr.zekariateam.welcomer.Welcomer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class UDataStorage {

    private final Welcomer main = Welcomer.getInstance();

    public HashMap<Player, List<Player>> welcomers = new HashMap<>();

    public String PREFIX;

    /*
    CONFIG
     */
    public Integer FIRST_JOIN_TIMER;

    /*
    MESSAGES
     */
    public String ERRORS_NOT_A_PLAYER;
    public String ERRORS_NOT_ENOUGH_ARGUMENTS;
    public String ERRORS_PLAYER_NOT_FOUND;
    public String ERRORS_CANT_WELCOME;
    public String ERRORS_CANT_WELCOME_YOURSELF;

    public String FIRST_JOIN_ANNOUNCEMENT;
    public String FIRST_JOIN_HOVER;
    public String FIRST_JOIN_WELCOME;

    public void LoadConfig() {
        YamlDocument config = main.getmFiles().config;

        FIRST_JOIN_TIMER = config.getInt("first-join.time-to-welcome")*20;
    }

    public void LoadMessages() {
        YamlDocument messages = main.getmFiles().messages;

        PREFIX = messages.getString("prefix");

        ERRORS_NOT_A_PLAYER = PREFIX + messages.getString("errors.not-a-player").replace("&", "§");
        ERRORS_NOT_ENOUGH_ARGUMENTS = PREFIX + messages.getString("errors.not-enough-arguments").replace("&", "§");
        ERRORS_PLAYER_NOT_FOUND = PREFIX + messages.getString("errors.player-not-found").replace("&", "§");
        ERRORS_CANT_WELCOME = PREFIX + messages.getString("errors.cant-welcome").replace("&", "§");
        ERRORS_CANT_WELCOME_YOURSELF = PREFIX + messages.getString("errors.cant-welcome-yourself").replace("&", "§");

        FIRST_JOIN_ANNOUNCEMENT = messages.getString("first-join.announcement").replace("&", "§");
        FIRST_JOIN_HOVER = messages.getString("first-join.hover").replace("&", "§");
        FIRST_JOIN_WELCOME = messages.getString("first-join.welcome").replace("&", "§");
    }
}
