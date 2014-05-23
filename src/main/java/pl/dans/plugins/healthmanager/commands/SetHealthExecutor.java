package pl.dans.plugins.healthmanager.commands;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHealthExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
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

        }
        return false;
    }
}
