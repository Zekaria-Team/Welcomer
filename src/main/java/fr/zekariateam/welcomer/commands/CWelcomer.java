package fr.zekariateam.welcomer.commands;

import dev.dejvokep.boostedyaml.YamlDocument;
import fr.zekariateam.welcomer.Welcomer;
import fr.zekariateam.welcomer.utils.UDataStorage;
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

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("welcomer.admin")) {
                    try {
                        plugin.getmFiles().config.reload();
                        plugin.getmFiles().messages.reload();
                        plugin.getuDataStorage().LoadConfig();
                        plugin.getuDataStorage().LoadMessages();
                        sender.sendMessage(data.RELOAD_SUCCESS);
                    } catch (IOException exception) {
                        plugin.Log(Level.SEVERE, exception.getMessage());
                        sender.sendMessage(data.RELOAD_ERROR);
                    }
                } else {
                    sender.sendMessage(data.ERRORS_NO_PERMISSION);
                }
                return true;
            }

            if (args[0].equalsIgnoreCase("help")) {

                for (String string : data.HELP_PLAYER) {
                    sender.sendMessage(string);
                }

                if (sender.hasPermission("welcomer.admin")) {
                    for (String string : data.HELP_ADMIN) {
                        sender.sendMessage(string);
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
                            sender.sendMessage(data.SPAWN_ENABLE_MESSAGE);
                        } catch (IOException exception) {
                            plugin.Log(Level.SEVERE, exception.getMessage());
                        }

                    } else {
                        sender.sendMessage(data.SPAWN_ALREADY_ENABLE);
                    }
                    return true;
                } else if (args[1].equalsIgnoreCase("disable")) {

                    if (data.SPAWN_ENABLE) {

                        try {
                            plugin.getmFiles().config.set("spawn.enable", false);
                            plugin.getmFiles().config.save();
                            plugin.getmFiles().config.reload();
                            plugin.getuDataStorage().LoadConfig();
                            sender.sendMessage(data.SPAWN_DISABLE_MESSAGE);
                        } catch (IOException exception) {
                            plugin.Log(Level.SEVERE, exception.getMessage());
                        }

                    } else {
                        sender.sendMessage(data.SPAWN_ALREADY_DISABLE);
                    }
                    return true;
                } else if (args[1].equalsIgnoreCase("set")) {

                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        SetSpawn(player);
                    } else {
                        sender.sendMessage(data.ERRORS_NOT_A_PLAYER);
                    }
                    return true;
                }
            } else {
                sender.sendMessage(data.ERRORS_NO_PERMISSION);
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

            player.sendMessage(data.SPAWN_SET);

        } catch (IOException exception) {
            plugin.Log(Level.SEVERE, exception.getMessage());
        }

    }
}
