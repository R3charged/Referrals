package io.github.R3charged;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class RefListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        if(!ReferalMap.get().containsKey(e.getPlayer().getUniqueId())) {
            e.getPlayer().kickPlayer("You have not been referred by a player.");
        }
    }

}