package fr.zekariateam.welcomer.managers;

import fr.zekariateam.welcomer.Welcomer;
import fr.zekariateam.welcomer.commands.CWelcome;

public class MCommands {

    private final Welcomer main = Welcomer.getInstance();

    public void InitCommands() {
        // /welcome [player]
        main.getCommand("welcome").setExecutor(new CWelcome());
    }

}
