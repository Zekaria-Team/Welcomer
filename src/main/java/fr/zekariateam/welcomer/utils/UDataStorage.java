package fr.zekariateam.welcomer.utils;

import fr.zekariateam.welcomer.Welcomer;
import org.bukkit.configuration.file.FileConfiguration;

public class UDataStorage {

    private static UDataStorage instance;
    private final Welcomer main = Welcomer.getInstance();

    public void LoadConfig() {
        FileConfiguration config = main.getmFiles().configFile;
    }

    public void LoadMessages() {
        FileConfiguration messages = main.getmFiles().messagesFile;
    }

    public void InitDataStorage() {
        instance = this;
    }

    public static UDataStorage getInstance() {
        return instance;
    }
}
