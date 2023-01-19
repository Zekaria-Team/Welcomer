package fr.zekariateam.welcomer.managers;

import fr.zekariateam.welcomer.Welcomer;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class MFiles {

    private final Welcomer main = Welcomer.getInstance();

    /*
    FILES
     */
    private File config;
    public FileConfiguration configFile;
    private File messages;
    public FileConfiguration messagesFile;

    public void InitFiles() {
        CreateFile("config.yml", config, configFile);
        CreateFile("messages.yml", messages, messagesFile);
    }

    public void CreateFile(String name, File file, FileConfiguration fileConfiguration) {
        file = new File(main.getDataFolder(), name);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            main.saveResource(name, false);
        }

        fileConfiguration = new YamlConfiguration();
        try {
            fileConfiguration.load(file);
        } catch (IOException | InvalidConfigurationException exception) {
            main.Log(Level.SEVERE, exception.getMessage());
        }
    }

}
