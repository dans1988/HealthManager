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
                        sender.sendMessage(new StringBuilder().append(ChatColor.RED).append("Couldn't set health for ").append(player.getName()).append(" - his maximum health is too low").toString());
                    }
                    
                }
                getServer().broadcastMessage(new StringBuilder().append(ChatColor.GOLD).append("Health of all players was set to ").append(percent).append("%").toString());
            } else {
                Player player = server.getPlayer(playerName);
                if (player == null || !player.isOnline()) {
                    sender.sendMessage(new StringBuilder().append(ChatColor.RED).append("Player is not online.").toString());
                }
                
                if (player.getMaxHealth() >= health) {
                    player.setHealth(health);
                    player.sendMessage(new StringBuilder().append(ChatColor.GOLD).append("Your health was set to ").append(percent).append("%").toString());
                    if (player.getName().compareTo(sender.getName()) != 0) {
                        sender.sendMessage(new StringBuilder().append(ChatColor.GOLD).append(playerName).append("'s health was set to ").append(percent).append("%").toString());
                    }
                } else {
                    sender.sendMessage(new StringBuilder().append(ChatColor.RED).append("Couldn't set health for ").append(playerName).append(" - his maximum health is too low").toString());
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
                
                getServer().broadcastMessage(new StringBuilder().append(ChatColor.GOLD).append("Maximum health of all players was set to ").append(percent).append("%").toString());
                
            } else {
                
                Player player = server.getPlayer(playerName);
                if (player == null || !player.isOnline()) {
                    sender.sendMessage(new StringBuilder().append(ChatColor.RED).append("Player is not online.").toString());
                    return true;
                }
                
                player.setMaxHealth(health);
                player.sendMessage(new StringBuilder().append(ChatColor.GOLD).append("Your maximum health was set to ").append(percent).append("%").toString());
                
                if (player.getName().compareTo(sender.getName()) != 0) {
                    sender.sendMessage(new StringBuilder().append(ChatColor.GOLD).append(playerName).append("'s maximum health was set to ").append(percent).append("%").toString());
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
                sender.sendMessage(new StringBuilder().append(ChatColor.AQUA).append(playerName).append("'s health is at ").append(percent).append("%").toString());
            } else {
                sender.sendMessage(new StringBuilder().append(ChatColor.RED).append("Player is not online.").toString());
            }
            
            return true;
        }
        else {
            return false;
        }
        
    }
}
