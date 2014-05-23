package pl.dans.plugins.healthmanager.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetHealthExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (command.getName().compareTo("getHealth") == 0) {

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
        return false;
    }
}
