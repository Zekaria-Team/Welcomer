package fr.zekariateam.welcomer.commands;

import fr.zekariateam.welcomer.Welcomer;
import fr.zekariateam.welcomer.utils.UDataStorage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CWelcome implements CommandExecutor {

    private final Welcomer plugin = Welcomer.getInstance();
    private final UDataStorage data = plugin.getuDataStorage();

    @Override
    public boolean onCommand(CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender.hasPermission("welcomer.welcome")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(data.ERRORS_NOT_A_PLAYER);
                return true;
            }

            Player player = (Player) sender;

            if (args.length < 1) {
                player.sendMessage(data.ERRORS_NOT_ENOUGH_ARGUMENTS);
                return false;
            }

            if (args[0].equalsIgnoreCase(player.getName())) {
                player.sendMessage(data.ERRORS_CANT_WELCOME_YOURSELF);
                return true;
            }

            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                player.sendMessage(data.ERRORS_PLAYER_NOT_FOUND);
                return true;
            }

            if (!data.welcomers.containsKey(target) || data.welcomers.get(target).contains(player)) {
                player.sendMessage(data.ERRORS_CANT_WELCOME);
            } else {
                player.chat(data.FIRST_JOIN_WELCOME.replace("%target%", target.getName()));
                data.welcomers.get(target).add(player);
            }
            return true;
        } else {
            sender.sendMessage(data.ERRORS_NO_PERMISSION);
        }

        return false;
    }
}
