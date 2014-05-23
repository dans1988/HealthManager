package pl.dans.plugins.healthmanager.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetHealthExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("getHealth")) {
            return false;
        }

        if (args.length != 1) {
            return false;
        }

        Player player = Bukkit.getPlayer(args[0]);

        if (player == null) {
            sender.sendMessage(ChatColor.RED + "Player is not online");
            return true;
        }

        int percent = (int) (player.getHealth()/player.getMaxHealth()*100D);

        double hearts = player.getHealth() * 2;
        double maxHearts = player.getMaxHealth() * 2;

        sender.sendMessage(ChatColor.AQUA + args[0] + "'s health is at " + percent + "% " + hearts + "/" + maxHearts);

        return true;
    }
}
