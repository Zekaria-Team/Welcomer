package fr.zekariateam.welcomer.commands;

import fr.zekariateam.welcomer.Welcomer;
import fr.zekariateam.welcomer.utils.UDataStorage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CWelcome implements CommandExecutor {

    private final Welcomer main = Welcomer.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(main.getuDataStorage().ERRORS_NOT_A_PLAYER);
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            player.sendMessage(main.getuDataStorage().ERRORS_NOT_ENOUGH_ARGUMENTS);
            return false;
        }

        if (args[0].equalsIgnoreCase(player.getName())) {
            player.sendMessage(main.getuDataStorage().ERRORS_CANT_WELCOME_YOURSELF);
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(main.getuDataStorage().ERRORS_PLAYER_NOT_FOUND);
            return true;
        }

        if (!main.getuDataStorage().welcomers.containsKey(target) || main.getuDataStorage().welcomers.get(target).contains(player)) {
            player.sendMessage(main.getuDataStorage().ERRORS_CANT_WELCOME);
            System.out.println(main.getuDataStorage().welcomers);
        } else {
            player.chat(main.getuDataStorage().FIRST_JOIN_WELCOME.replace("%target%", target.getName()));
            main.getuDataStorage().welcomers.get(target).add(player);
        }
        return true;

    }
}
