package fr.zekariateam.welcomer.commands;

import fr.zekariateam.welcomer.Welcomer;
import fr.zekariateam.welcomer.objects.Reward;
import fr.zekariateam.welcomer.utils.UDataStorage;
import fr.zekariateam.welcomer.utils.UUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.logging.Level;

public class CWelcome implements CommandExecutor {

    private final Welcomer plugin = Welcomer.getInstance();
    private final UDataStorage data = plugin.getuDataStorage();
    private final UUtils utils = plugin.getuUtils();

    @Override
    public boolean onCommand(CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender.hasPermission("welcomer.welcome")) {
            if (!(sender instanceof Player)) {
                utils.sendMessage(data.ERRORS_NOT_A_PLAYER, sender);
                return true;
            }

            Player player = (Player) sender;

            if (args.length < 1) {
                utils.sendMessage(data.ERRORS_NOT_ENOUGH_ARGUMENTS, player);
                return false;
            }

            if (args[0].equalsIgnoreCase(player.getName())) {
                utils.sendMessage(data.ERRORS_CANT_WELCOME_YOURSELF, player);
                return true;
            }

            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                utils.sendMessage(data.ERRORS_PLAYER_NOT_FOUND, player);
                return true;
            }

            if (!data.welcomers.containsKey(target) || data.welcomers.get(target).contains(player)) {
                utils.sendMessage(data.ERRORS_CANT_WELCOME, player);
            } else {
                player.chat(data.FIRST_JOIN_WELCOME.replace("%target%", target.getName()));
                data.welcomers.get(target).add(player);

                //Reward
                Random random = new Random();
                for (Reward reward : data.REWARDS) {
                    int randomINT = random.nextInt(100);

                    plugin.Log(Level.INFO, "Int Random: " + randomINT);

                    if (randomINT <= reward.getChance()) {
                        for (String string : reward.getCommands()) {
                            string = string.replace("%welcomer%", player.getName());
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), string);
                        }

                        for (String string : reward.getMessages()) {
                            player.sendMessage(string);
                        }

                    }
                }

            }
            return true;
        } else {
            utils.sendMessage(data.ERRORS_NO_PERMISSION, sender);
        }

        return false;
    }
}
