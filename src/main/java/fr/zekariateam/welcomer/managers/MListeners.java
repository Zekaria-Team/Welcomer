package fr.zekariateam.welcomer.managers;

import fr.zekariateam.welcomer.Welcomer;
import fr.zekariateam.welcomer.listeners.LPlayerJoin;
import org.bukkit.plugin.PluginManager;

public class MListeners {

    private final Welcomer main = Welcomer.getInstance();

    public void InitListeners() {
        PluginManager pm = main.getServer().getPluginManager();

        pm.registerEvents(new LPlayerJoin(), main);

    }

}
