package pl.dans.plugins.healthmanager;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import pl.dans.plugins.healthmanager.commands.GetHealthExecutor;
import pl.dans.plugins.healthmanager.commands.SetHealthExecutor;
import pl.dans.plugins.healthmanager.commands.SetMaxHealthExecutor;

public class HealthManager extends JavaPlugin implements Listener {
    
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);

        getCommand("setHealth").setExecutor(new SetHealthExecutor());
        getCommand("getHealth").setExecutor(new GetHealthExecutor());
        getCommand("setMaxHealth").setExecutor(new SetMaxHealthExecutor());
    }
}
