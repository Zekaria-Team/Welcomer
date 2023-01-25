package fr.zekariateam.welcomer.managers;

import fr.zekariateam.welcomer.Welcomer;
import fr.zekariateam.welcomer.commands.CTabWelcomer;
import fr.zekariateam.welcomer.commands.CWelcome;
import fr.zekariateam.welcomer.commands.CWelcomer;

import java.util.Objects;

public class MCommands {

    private final Welcomer main = Welcomer.getInstance();

    public void InitCommands() {
        // /welcome [player]
        Objects.requireNonNull(main.getCommand("welcome")).setExecutor(new CWelcome());
        // /welcomer [enable | disable | set]
        Objects.requireNonNull(main.getCommand("welcomer")).setExecutor(new CWelcomer());
        Objects.requireNonNull(main.getCommand("welcomer")).setTabCompleter(new CTabWelcomer());
    }

}
