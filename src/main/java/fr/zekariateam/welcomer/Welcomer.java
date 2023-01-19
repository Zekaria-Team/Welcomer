package fr.zekariateam.welcomer;

import fr.zekariateam.welcomer.managers.MCommands;
import fr.zekariateam.welcomer.managers.MFiles;
import fr.zekariateam.welcomer.managers.MListeners;
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
        INITIALIZATION
         */
        mListeners.InitListeners();
        mCommands.InitCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
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
}
