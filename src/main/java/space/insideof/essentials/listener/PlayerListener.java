package space.insideof.essentials.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import space.insideof.essentials.User;

public final class PlayerListener implements Listener {
    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        User.getUser(event.getPlayer(), true);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        User.unloadUser(event.getPlayer());
    }
}
