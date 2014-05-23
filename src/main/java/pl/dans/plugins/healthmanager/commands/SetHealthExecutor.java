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

        Double health;
        try {
            health = Double.parseDouble(args[0]);
        } catch (NumberFormatException ex) {
            sender.sendMessage(ChatColor.RED + args[0] + " is not a number!");
            return true;
        }

        String playerName = args[1];

        int percent = (int) ((health/20.0)*100);
        if (playerName.equals("*")) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getMaxHealth() >= health) {
                    player.setHealth(health);
                } else {
                    sender.sendMessage(ChatColor.RED + "Couldn't set health for " + player.getName() + " - his maximum health is too low");
                }

            }
            Bukkit.broadcastMessage(ChatColor.GOLD + "Health of all players was set to " + percent + "%");
            return true;
        }

        Player player = Bukkit.getPlayer(playerName);
        if (player == null || !player.isOnline()) {
            sender.sendMessage(ChatColor.RED + "Player is not online.");
        }

        if (player.getMaxHealth() >= health) {
            player.setHealth(health);
            player.sendMessage(ChatColor.GOLD + "Your health was set to " + percent + "%");
            if (player.getName().equalsIgnoreCase(sender.getName())) {
                sender.sendMessage(ChatColor.GOLD + playerName + "'s health was set to " + percent + "%");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Couldn't set health for " + playerName + " - his maximum health is too low");
            return true;
        }

        return true;
    }
}
