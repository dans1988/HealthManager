package pl.dans.plugins.healthmanager;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class HealthManager extends JavaPlugin implements Listener {
    
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
         if (command.getName().compareTo("setHealth") == 0) {

             if (args.length != 2) {
                 return false;
             }
             
            Server server = getServer();
            String playerName = args[1];
            Double health = Double.parseDouble(args[0]);
            int percent = (int) ((health/20.0)*100);
            if (playerName.compareTo("*") == 0) {
                for (Player player : server.getOnlinePlayers()) {
                    if (player.getMaxHealth() >= health) {
                        player.setHealth(health);
                    } else {
                        sender.sendMessage(ChatColor.RED + "Couldn't set health for " + player.getName() + " - his maximum health is too low");
                    }
                    
                }
                getServer().broadcastMessage(ChatColor.GOLD + "Health of all players was set to " + percent + "%");
            } else {
                Player player = server.getPlayer(playerName);
                if (player == null || !player.isOnline()) {
                    sender.sendMessage(ChatColor.RED + "Player is not online.");
                }
                
                if (player.getMaxHealth() >= health) {
                    player.setHealth(health);
                    player.sendMessage(ChatColor.GOLD + "Your health was set to " + percent + "%");
                    if (player.getName().compareTo(sender.getName()) != 0) {
                        sender.sendMessage(ChatColor.GOLD + playerName + "'s health was set to " + percent + "%");
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Couldn't set health for " + playerName + " - his maximum health is too low");
                    return true;
                }
            }
            return true;
            
        } else if (command.getName().compareTo("setMaxHealth") == 0) {
            
            if (args.length != 2) {
                 return false;
             }

            Server server = getServer();
            String playerName = args[1];
            Double health = Double.parseDouble(args[0]);
            
            int percent = (int) ((health/20.0)*100); 
            if (playerName.compareTo("*") == 0) {
                for (Player player : server.getOnlinePlayers()) {
                    player.setMaxHealth(health);
                }
                
                getServer().broadcastMessage(ChatColor.GOLD + "Maximum health of all players was set to " + percent + "%");
                
            } else {
                
                Player player = server.getPlayer(playerName);
                if (player == null || !player.isOnline()) {
                    sender.sendMessage(ChatColor.RED + "Player is not online.");
                    return true;
                }
                
                player.setMaxHealth(health);
                player.sendMessage(ChatColor.GOLD + "Your maximum health was set to " + percent + "%");
                
                if (player.getName().compareTo(sender.getName()) != 0) {
                    sender.sendMessage(ChatColor.GOLD + playerName + "'s maximum health was set to " + percent + "%");
                }
            }
            
            return true;
        }
        else if (command.getName().compareTo("getHealth") == 0) {
            
            if (args.length != 1) {
                 return false;
             }
            
            String playerName = args[0];

            Player player = getServer().getPlayer(playerName);
            
            if (player != null) {
                Double health = player.getHealth();
                int percent = (int) ((health/20.0)*100);
                sender.sendMessage(ChatColor.AQUA + playerName + "'s health is at " + percent + "%");
            } else {
                sender.sendMessage(ChatColor.RED + "Player is not online.");
            }
            
            return true;
        }
        else {
            return false;
        }
        
    }
}
