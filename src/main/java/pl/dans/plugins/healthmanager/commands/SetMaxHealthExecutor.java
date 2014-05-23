package pl.dans.plugins.healthmanager.commands;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetMaxHealthExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (command.getName().compareTo("setMaxHealth") == 0) {

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
        return false;
    }
}
