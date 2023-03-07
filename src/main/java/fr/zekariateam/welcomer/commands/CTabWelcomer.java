package fr.zekariateam.welcomer.commands;

import fr.zekariateam.welcomer.Welcomer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CTabWelcomer implements TabCompleter {

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> list = new ArrayList<>();
        boolean isAdmin = sender.hasPermission("welcomer.admin");

        if (args.length == 1) {
            list.add("help");
            if (isAdmin) {
                list.add("spawn");
                list.add("reload");
            }
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("spawn")) {
            if (isAdmin) {
                list.add("set");
                list.add("enable");
                list.add("disable");
            }
        }

        return list;
    }
}
