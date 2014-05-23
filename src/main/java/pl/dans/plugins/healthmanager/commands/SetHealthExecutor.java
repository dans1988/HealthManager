package pl.dans.plugins.healthmanager.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHealthExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("setHealth")) {
            return false;
        }

        if (args.length != 2) {
            return false;
        }

        Double healthInput;
        try {
            healthInput = Double.parseDouble(args[0]);
        } catch (NumberFormatException ex) {
            sender.sendMessage(ChatColor.RED + args[0] + " is not a number!");
            return true;
        }

        int hpCount = (int) Math.round(healthInput * 2D);

        if (hpCount <= 0) {
            sender.sendMessage(ChatColor.RED + "You must provide a number greater than zero");
            return true;
        }

        String playerName = args[1];

        if (playerName.equals("*")) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.setHealth(Math.min(hpCount, player.getMaxHealth()));
            }
            Bukkit.broadcastMessage(ChatColor.GOLD + "Health of all players was set to " + args[0] + " hearts");
            return true;
        }

        Player player = Bukkit.getPlayer(playerName);
        if (player == null) {
            sender.sendMessage(ChatColor.RED + "Player is not online.");
            return true;
        }

        player.setHealth(Math.min(hpCount, player.getMaxHealth()));
        player.sendMessage(ChatColor.GOLD + "Your health was set to " + args[0] + " hearts");

        if(!player.getName().equalsIgnoreCase(sender.getName())) {
            sender.sendMessage(ChatColor.GOLD + "Health of " + player.getName() + " set to " + args[0] + " hearts");
        }

        return true;
    }
}
