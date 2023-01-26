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

    private final Welcomer main = Welcomer.getInstance();
    private final UDataStorage data = main.getuDataStorage();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length >= 2 && args[0].equalsIgnoreCase("spawn")) {

            if (args[1].equalsIgnoreCase("enable")) {
                if (!data.SPAWN_ENABLE) {

                    try {
                        main.getmFiles().config.set("spawn.enable", true);
                        main.getmFiles().config.save();
                        main.getmFiles().config.reload();
                        main.getuDataStorage().LoadConfig();
                        sender.sendMessage(data.PREFIX + data.SPAWN_ENABLE_MESSAGE);
                    } catch (IOException exception) {
                        main.Log(Level.SEVERE, exception.getMessage());
                    }

                } else {
                    sender.sendMessage(data.PREFIX + data.SPAWN_ALREADY_ENABLE);
                }
                return true;
            } else if (args[1].equalsIgnoreCase("disable")) {

                if (data.SPAWN_ENABLE) {

                    try {
                        main.getmFiles().config.set("spawn.enable", false);
                        main.getmFiles().config.save();
                        main.getmFiles().config.reload();
                        main.getuDataStorage().LoadConfig();
                        sender.sendMessage(data.PREFIX + data.SPAWN_DISABLE_MESSAGE);
                    } catch (IOException exception) {
                        main.Log(Level.SEVERE, exception.getMessage());
                    }

                } else {
                    sender.sendMessage(data.PREFIX + data.SPAWN_ALREADY_DISABLE);
                }
                return true;
            } else if (args[1].equalsIgnoreCase("set")) {

                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    SetSpawn(player);
                } else {
                    sender.sendMessage(data.PREFIX + data.ERRORS_NOT_A_PLAYER);
                }
                return true;
            }

        }

        return false;
    }

    private void SetSpawn(Player player) {
        YamlDocument config = main.getmFiles().config;

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

            player.sendMessage(data.PREFIX + data.SPAWN_SET);

        } catch (IOException exception) {
            main.Log(Level.SEVERE, exception.getMessage());
        }

    }
}
