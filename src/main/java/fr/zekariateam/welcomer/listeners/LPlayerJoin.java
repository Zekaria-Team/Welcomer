package fr.zekariateam.welcomer.listeners;

import fr.zekariateam.welcomer.Welcomer;
import fr.zekariateam.welcomer.utils.UDataStorage;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;

public class LPlayerJoin implements Listener {

    private final Welcomer main = Welcomer.getInstance();

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        TextComponent announcement = new TextComponent(main.getuDataStorage().FIRST_JOIN_ANNOUNCEMENT.replace("%player%", player.getName()));
        announcement.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(main.getuDataStorage().FIRST_JOIN_HOVER)));
        announcement.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/welcome "+player.getName()));

        if (!player.hasPlayedBefore()) {
            List<Player> onlines = new ArrayList<>();
            for (Player players : Bukkit.getOnlinePlayers()) {
                players.spigot().sendMessage(announcement);
            }
            main.getuDataStorage().welcomers.put(player, onlines);
            Bukkit.getScheduler().runTaskLater(main, () -> main.getuDataStorage().welcomers.remove(player), main.getuDataStorage().FIRST_JOIN_TIMER);
        }

    }

}
