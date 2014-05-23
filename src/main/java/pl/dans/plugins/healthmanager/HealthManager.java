package pl.dans.plugins.healthmanager;

import org.bukkit.plugin.java.JavaPlugin;
import pl.dans.plugins.healthmanager.commands.GetHealthExecutor;
import pl.dans.plugins.healthmanager.commands.SetHealthExecutor;

public class HealthManager extends JavaPlugin {
    
    @Override
    public void onEnable() {
        SetHealthExecutor setHealth = new SetHealthExecutor();
        getCommand("setHealth").setExecutor(setHealth);
        getCommand("setMaxHealth").setExecutor(setHealth);
        getCommand("getHealth").setExecutor(new GetHealthExecutor());
    }
}
