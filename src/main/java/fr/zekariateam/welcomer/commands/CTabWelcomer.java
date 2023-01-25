package fr.zekariateam.welcomer.commands;

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

        if (sender.hasPermission("welcomer.config")) {

            if (args.length == 1) {
                list.add("spawn");
            } else if (args.length == 2) {
                list.add("set");
                list.add("enable");
                list.add("disable");
            }

        }

        return list;
    }
}
