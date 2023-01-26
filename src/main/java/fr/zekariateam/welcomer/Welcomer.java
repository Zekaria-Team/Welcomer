package fr.zekariateam.welcomer;

import fr.zekariateam.welcomer.managers.MCommands;
import fr.zekariateam.welcomer.managers.MFiles;
import fr.zekariateam.welcomer.managers.MListeners;
import fr.zekariateam.welcomer.utils.UDataStorage;
import fr.zekariateam.welcomer.utils.UUtils;
import fr.zekariateam.welcomer.utils.UpdateChecker;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class Welcomer extends JavaPlugin {

    private static Welcomer instance;

    /*
    MANAGERS CLASSES
     */
    private MFiles mFiles;
    private MListeners mListeners;
    private MCommands mCommands;

    /*
    UTILS CLASSES
     */
    private UUtils uUtils;
    private UDataStorage uDataStorage;

    @Override
    public void onEnable() {
        //Instance
        instance = this;

        /*
        MANAGERS CLASSES
         */
        mFiles = new MFiles();
        mFiles.InitFiles();
        mListeners = new MListeners();
        mCommands = new MCommands();

        /*
        UTILS ClASSES
         */
        uUtils = new UUtils();
        uDataStorage = new UDataStorage();
        uDataStorage.LoadConfig();
        uDataStorage.LoadMessages();

        /*
        INITIALIZATION
         */
        mListeners.InitListeners();
        mCommands.InitCommands();

        new UpdateChecker(this, 107615).getVersion(version -> {
            if (this.getDescription().getVersion().equals(version)) {
                getLogger().info("There is not a new update available.");
            } else {
                getLogger().info("There is a new update available.");
            }
        });

        Log(Level.INFO, "Welcomer has been loaded!");

    }

    @Override
    public void onDisable() {
        Log(Level.INFO, "Welcomer has been unloaded!");
    }

    public void Log(Level level, String message) {
        getLogger().log(level, message);
    }

    public static Welcomer getInstance() {
        return instance;
    }

    public MFiles getmFiles() {
        return mFiles;
    }

    public MListeners getmListeners() {
        return mListeners;
    }

    public MCommands getmCommands() {
        return mCommands;
    }

    public UUtils getuUtils() {
        return uUtils;
    }

    public UDataStorage getuDataStorage() {
        return uDataStorage;
    }
}
