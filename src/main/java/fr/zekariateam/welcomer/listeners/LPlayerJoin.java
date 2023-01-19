package fr.zekariateam.welcomer.listeners;

import fr.zekariateam.welcomer.Welcomer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LPlayerJoin implements Listener {

    private final Welcomer main = Welcomer.getInstance();

    public void OnPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
    }

}
