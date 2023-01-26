package fr.zekariateam.welcomer.managers;

import dev.dejvokep.boostedyaml.YamlDocument;
import dev.dejvokep.boostedyaml.dvs.versioning.BasicVersioning;
import dev.dejvokep.boostedyaml.settings.dumper.DumperSettings;
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings;
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings;
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings;
import fr.zekariateam.welcomer.Welcomer;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class MFiles {

    private final Welcomer main = Welcomer.getInstance();

    /*
    FILES
     */
    public YamlDocument config;
    public YamlDocument messages;

    public void InitFiles() {
        try {
            config = YamlDocument.create(new File(main.getDataFolder(), "config.yml"), main.getResource("config.yml"),
                    GeneralSettings.DEFAULT, LoaderSettings.builder().setAutoUpdate(true).build(), DumperSettings.DEFAULT,
                    UpdaterSettings.builder().setVersioning(new BasicVersioning("config-version")).build());
            messages = YamlDocument.create(new File(main.getDataFolder(), "messages.yml"), main.getResource("messages.yml"),
                    GeneralSettings.DEFAULT, LoaderSettings.builder().setAutoUpdate(true).build(), DumperSettings.DEFAULT,
                    UpdaterSettings.builder().setVersioning(new BasicVersioning("messages-version")).build());
        } catch (IOException exception) {
            main.Log(Level.SEVERE, exception.getMessage());
        }
    }

}
