package fr.zekariateam.welcomer.listeners;

import fr.zekariateam.welcomer.Welcomer;
import fr.zekariateam.welcomer.utils.UDataStorage;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;

public class LPlayerJoin implements Listener {

    private final Welcomer main = Welcomer.getInstance();
    private final UDataStorage data = main.getuDataStorage();

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPlayedBefore()) {

            /*
            Spawn
             */
            if (data.SPAWN_ENABLE) {
                Location spawn = new Location(data.SPAWN_WORLD, data.SPAWN_X, data.SPAWN_Y, data.SPAWN_Z, data.SPAWN_YAW, data.SPAWN_PITCH);
                player.teleport(spawn);
            }

            /*
            Welcome
             */
            List<TextComponent> messages = new ArrayList<>();
            for (String str : data.FIRST_JOIN_ANNOUNCEMENT) {
                String string = str.replace("%player%", player.getName()).replace("%joinedNumber%", Bukkit.getOfflinePlayers().length+"");
                if (string.contains("<center>")) {
                    string = string.replace("<center>", "");
                    string = main.getuUtils().sendCenteredMessage(string);
                }

                TextComponent textComponent = new TextComponent(string);

                if (main.getuDataStorage().OPTIONS_HOVER_MESSAGE) {
                    textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(data.FIRST_JOIN_HOVER)));
                }

                if (main.getuDataStorage().OPTIONS_CLICKABLE_MESSAGE) {
                    textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/welcome "+player.getName()));
                }
                messages.add(textComponent);
            }

            List<Player> onlines = new ArrayList<>();
            for (Player players : Bukkit.getOnlinePlayers()) {
                for (TextComponent textComponent : messages) {
                    players.spigot().sendMessage(textComponent);
                }
            }
            main.getuDataStorage().welcomers.put(player, onlines);
            Bukkit.getScheduler().runTaskLater(main, () -> data.welcomers.remove(player), data.FIRST_JOIN_TIMER);
        }

    }

}
