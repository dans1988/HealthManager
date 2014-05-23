package pl.dans.plugins.healthmanager.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHealthExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        boolean setMax;
        if (command.getName().equalsIgnoreCase("setHealth")) {
            setMax = false;
        } else if(command.getName().equalsIgnoreCase("setMaxHealth")) {
            setMax = true;
        } else {
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

        Player[] players;

        if (playerName.equals("*")) {
            players = Bukkit.getOnlinePlayers();
        } else {
            Player player = Bukkit.getPlayer(playerName);
            if (player == null) {
                sender.sendMessage(ChatColor.RED + "Player is not online.");
                return true;
            }
            players = new Player[]{player};
        }

        if(setMax) {
            setMaxHealthForAll(hpCount, players);
            sender.sendMessage(ChatColor.GOLD + "Set max health to " + (hpCount / 2.0D) + " hearts");
        } else {
            setHealthForAll(hpCount, players);
            sender.sendMessage(ChatColor.GOLD + "Set health to " + (hpCount / 2.0D) + " hearts");
        }

        return true;
    }

    /**
     * Sets the max health for all the provided players
     * @param maxHealth the health value to set to
     * @param players the list of player to set for
     */
    private void setMaxHealthForAll(double maxHealth, Player... players) {
        for(Player player : players) {
            player.setMaxHealth(maxHealth);
            player.sendMessage(ChatColor.GOLD + "Your max health was set to " + (maxHealth / 2.0D) + " hearts");
        }
    }

    /**
     * Sets the health for all the provided players
     * @param health the health value to set to
     * @param players the list of player to set for
     */
    private void setHealthForAll(double health, Player... players) {
        for(Player player : players) {
            double actualHealth = Math.min(health, player.getMaxHealth());
            player.setHealth(actualHealth);
            player.sendMessage(ChatColor.GOLD + "Your health was set to " + (actualHealth / 2.0D) + " hearts");
        }
    }
}
