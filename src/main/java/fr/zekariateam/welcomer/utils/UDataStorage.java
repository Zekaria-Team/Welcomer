package fr.zekariateam.welcomer.utils;

import dev.dejvokep.boostedyaml.YamlDocument;
import fr.zekariateam.welcomer.Welcomer;
import org.bukkit.Bukkit;
import org.bukkit.World;
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
    public Boolean OPTIONS_CLICKABLE_MESSAGE;
    public Boolean OPTIONS_HOVER_MESSAGE;
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
    public String ERRORS_NO_PERMISSION;
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
    //Others
    public String RELOAD_SUCCESS;
    public String RELOAD_ERROR;
    public List<String> HELP_PLAYER;
    public List<String> HELP_ADMIN;


    public void LoadConfig() {
        YamlDocument config = main.getmFiles().config;

        FIRST_JOIN_TIMER = config.getInt("first-join.time-to-welcome")*20;
        OPTIONS_CLICKABLE_MESSAGE = config.getBoolean("first-join.options.clickable-message");
        OPTIONS_HOVER_MESSAGE = config.getBoolean("first-join.options.hover-message");

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
        ERRORS_NO_PERMISSION = PREFIX + messages.getString("errors.no-permission").replace("&", "§");

        FIRST_JOIN_ANNOUNCEMENT = messages.getString("first-join.announcement").replace("&", "§");
        FIRST_JOIN_HOVER = messages.getString("first-join.hover").replace("&", "§");
        FIRST_JOIN_WELCOME = messages.getString("first-join.welcome").replace("&", "§");

        SPAWN_SET = PREFIX + messages.getString("spawn.set").replace("&", "§");
        SPAWN_ENABLE_MESSAGE = PREFIX + messages.getString("spawn.enable").replace("&", "§");
        SPAWN_DISABLE_MESSAGE = PREFIX + messages.getString("spawn.disable").replace("&", "§");
        SPAWN_ALREADY_ENABLE = PREFIX + messages.getString("spawn.already-enable").replace("&", "§");
        SPAWN_ALREADY_DISABLE = PREFIX + messages.getString("spawn.already-disable").replace("&", "§");

        RELOAD_SUCCESS = PREFIX + messages.getString("others.reload.success").replace("&", "§");
        RELOAD_ERROR = PREFIX + messages.getString("others.reload.error").replace("&", "§");
        
        HELP_PLAYER = messages.getStringList("others.help.player");
        for (int i = 0; i < HELP_PLAYER.size(); i++) {
            String str = HELP_PLAYER.get(i);
            str = str.replace("&", "§");
            HELP_PLAYER.set(i, str);
        }
        
        HELP_ADMIN = messages.getStringList("others.help.admin");
        for (int i = 0; i < HELP_ADMIN.size(); i++) {
            String str = HELP_ADMIN.get(i);
            str = str.replace("&", "§");
            HELP_ADMIN.set(i, str);
        }
    }
}
