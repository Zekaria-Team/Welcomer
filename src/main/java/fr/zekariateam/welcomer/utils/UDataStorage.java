package fr.zekariateam.welcomer.utils;

import dev.dejvokep.boostedyaml.YamlDocument;
import fr.zekariateam.welcomer.Welcomer;
import org.bukkit.Bukkit;
import org.bukkit.World;
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
    //SPAWN
    public Boolean SPAWN_ENABLE;
    public World SPAWN_WORLD;
    public Float SPAWN_X;
    public Float SPAWN_Y;
    public Float SPAWN_Z;
    public Float SPAWN_YAW;
    public Float SPAWN_PITCH;

    /*
    MESSAGES
     */
    //ERRORS
    public String ERRORS_NOT_A_PLAYER;
    public String ERRORS_NOT_ENOUGH_ARGUMENTS;
    public String ERRORS_PLAYER_NOT_FOUND;
    public String ERRORS_CANT_WELCOME;
    public String ERRORS_CANT_WELCOME_YOURSELF;
    //FIRST JOIN
    public String FIRST_JOIN_ANNOUNCEMENT;
    public String FIRST_JOIN_HOVER;
    public String FIRST_JOIN_WELCOME;
    //SPAWN
    public String SPAWN_SET;
    public String SPAWN_ENABLE_MESSAGE;
    public String SPAWN_DISABLE_MESSAGE;
    public String SPAWN_ALREADY_ENABLE;
    public String SPAWN_ALREADY_DISABLE;


    public void LoadConfig() {
        YamlDocument config = main.getmFiles().config;

        FIRST_JOIN_TIMER = config.getInt("first-join.time-to-welcome")*20;
        SPAWN_ENABLE = config.getBoolean("spawn.enable");
        if (SPAWN_ENABLE) {
            SPAWN_WORLD = Bukkit.getWorld(config.getString("spawn.location.world"));
            SPAWN_X = config.getFloat("spawn.location.x");
            SPAWN_Y = config.getFloat("spawn.location.y");
            SPAWN_Z = config.getFloat("spawn.location.z");
            SPAWN_YAW = config.getFloat("spawn.location.yaw");
            SPAWN_PITCH = config.getFloat("spawn.location.pitch");
        }
    }

    public void LoadMessages() {
        YamlDocument messages = main.getmFiles().messages;

        PREFIX = messages.getString("prefix").replace("&", "§");

        ERRORS_NOT_A_PLAYER = PREFIX + messages.getString("errors.not-a-player").replace("&", "§");
        ERRORS_NOT_ENOUGH_ARGUMENTS = PREFIX + messages.getString("errors.not-enough-arguments").replace("&", "§");
        ERRORS_PLAYER_NOT_FOUND = PREFIX + messages.getString("errors.player-not-found").replace("&", "§");
        ERRORS_CANT_WELCOME = PREFIX + messages.getString("errors.cant-welcome").replace("&", "§");
        ERRORS_CANT_WELCOME_YOURSELF = PREFIX + messages.getString("errors.cant-welcome-yourself").replace("&", "§");

        FIRST_JOIN_ANNOUNCEMENT = messages.getString("first-join.announcement").replace("&", "§");
        FIRST_JOIN_HOVER = messages.getString("first-join.hover").replace("&", "§");
        FIRST_JOIN_WELCOME = messages.getString("first-join.welcome").replace("&", "§");

        SPAWN_SET = messages.getString("spawn.set").replace("&", "§");
        SPAWN_ENABLE_MESSAGE = messages.getString("spawn.enable").replace("&", "§");
        SPAWN_DISABLE_MESSAGE = messages.getString("spawn.disable").replace("&", "§");
        SPAWN_ALREADY_ENABLE = messages.getString("spawn.already-enable").replace("&", "§");
        SPAWN_ALREADY_DISABLE = messages.getString("spawn.already-disable").replace("&", "§");
    }
}
