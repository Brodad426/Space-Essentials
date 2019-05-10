package space.insideof.essentials;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        switch(command.getName()){
            case("clearinv"): {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.getInventory().clear();
                }
            }
            case("gamemode"): {
                if(sender instanceof Player) {
                    Player player = (Player) sender;
                    if (player.getGameMode() == GameMode.SURVIVAL) {
                        player.setGameMode(GameMode.CREATIVE);
                        player.sendMessage(ChatColor.AQUA + ChatColor.BOLD.toString() + "Your gamemode has been changed to Creative.");
                    } else if (player.getGameMode() == GameMode.CREATIVE) {
                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage(ChatColor.AQUA + ChatColor.BOLD.toString() + "Your gamemode has been changed to Survival.");
                    }
                }
            }
            case("kill"): {
                if (args.length == 0) {
                    Player player = (Player) sender;
                    player.setHealth(0);
                } else if (args.length == 1 && sender.hasPermission("kill")) {
                    Player player = getServer().getPlayer(args[0]);
                    player.setHealth(0);
                }
            }
            case("god"): {
                if(sender instanceof Player && sender.hasPermission("god")){
                    Player player = (Player) sender;
                    player.setInvulnerable(!player.isInvulnerable());
                }
            }
            case("tpa"): {
                if(sender instanceof Player && sender.hasPermission("tpa")){
                    Player player = (Player) sender;
                }
            }
            case("i"): {
                if(args.length == 0){
                    Player player = (Player) sender;
                    player.sendMessage("I need an item!");
                }
                else if(args.length == 1){
                    Player player = (Player) sender;
                    player.sendMessage("I need an amount!");
                }
                else if(sender instanceof Player && sender.hasPermission("give")){
                    Player player = (Player) sender;

                }
            }
        }

        return true;
    }
    @Override
    public void onEnable() {
        getLogger().info("Essentials running!");
    }
}
