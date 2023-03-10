package fr.zekariateam.welcomer.commands;

import dev.dejvokep.boostedyaml.YamlDocument;
import fr.zekariateam.welcomer.Welcomer;
import fr.zekariateam.welcomer.utils.UDataStorage;
import fr.zekariateam.welcomer.utils.UUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.logging.Level;

public class CWelcomer implements CommandExecutor {

    private final Welcomer plugin = Welcomer.getInstance();
    private final UDataStorage data = plugin.getuDataStorage();
    private final UUtils utils = plugin.getuUtils();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("welcomer.admin")) {
                    try {
                        data.REWARDS.clear();
                        plugin.getmFiles().config.reload();
                        plugin.getmFiles().messages.reload();
                        plugin.getuDataStorage().LoadConfig();
                        plugin.getuDataStorage().LoadMessages();
                        utils.sendMessage(data.RELOAD_SUCCESS, sender);
                    } catch (IOException exception) {
                        plugin.Log(Level.SEVERE, exception.getMessage());
                        utils.sendMessage(data.RELOAD_ERROR, sender);
                    }
                } else {
                    utils.sendMessage(data.ERRORS_NO_PERMISSION, sender);
                }
                return true;
            }

            if (args[0].equalsIgnoreCase("help")) {

                for (String string : data.HELP_PLAYER) {
                    utils.sendMessage(string, sender);
                }

                if (sender.hasPermission("welcomer.admin")) {
                    for (String string : data.HELP_ADMIN) {
                        utils.sendMessage(string, sender);
                    }
                }
                return true;
            }

        }

        if (args.length >= 2 && args[0].equalsIgnoreCase("spawn")) {

            if (sender.hasPermission("welcomer.admin")) {
                if (args[1].equalsIgnoreCase("enable")) {
                    if (!data.SPAWN_ENABLE) {

                        try {
                            plugin.getmFiles().config.set("spawn.enable", true);
                            plugin.getmFiles().config.save();
                            plugin.getmFiles().config.reload();
                            plugin.getuDataStorage().LoadConfig();
                            utils.sendMessage(data.SPAWN_ENABLE_MESSAGE, sender);
                        } catch (IOException exception) {
                            plugin.Log(Level.SEVERE, exception.getMessage());
                        }

                    } else {
                        utils.sendMessage(data.SPAWN_ALREADY_ENABLE, sender);
                    }
                    return true;
                } else if (args[1].equalsIgnoreCase("disable")) {

                    if (data.SPAWN_ENABLE) {

                        try {
                            plugin.getmFiles().config.set("spawn.enable", false);
                            plugin.getmFiles().config.save();
                            plugin.getmFiles().config.reload();
                            plugin.getuDataStorage().LoadConfig();
                            utils.sendMessage(data.SPAWN_DISABLE_MESSAGE, sender);
                        } catch (IOException exception) {
                            plugin.Log(Level.SEVERE, exception.getMessage());
                        }

                    } else {
                        utils.sendMessage(data.SPAWN_ALREADY_DISABLE, sender);
                    }
                    return true;
                } else if (args[1].equalsIgnoreCase("set")) {

                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        SetSpawn(player);
                    } else {
                        utils.sendMessage(data.ERRORS_NOT_A_PLAYER, sender);
                    }
                    return true;
                }
            } else {
                utils.sendMessage(data.ERRORS_NO_PERMISSION, sender);
            }

            return true;

        }

        return false;
    }

    private void SetSpawn(Player player) {
        YamlDocument config = plugin.getmFiles().config;

        try {
            config.set("spawn.location.world", player.getLocation().getWorld().getName());
            config.set("spawn.location.x", player.getLocation().getX());
            config.set("spawn.location.y", player.getLocation().getZ());
            config.set("spawn.location.z", player.getLocation().getZ());
            config.set("spawn.location.yaw", player.getLocation().getYaw());
            config.set("spawn.location.pitch", player.getLocation().getPitch());

            config.save();
            config.reload();
            data.LoadConfig();

            utils.sendMessage(data.SPAWN_SET, player);

        } catch (IOException exception) {
            plugin.Log(Level.SEVERE, exception.getMessage());
        }

    }
}
