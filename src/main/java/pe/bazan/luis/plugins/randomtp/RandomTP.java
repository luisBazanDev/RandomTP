package pe.bazan.luis.plugins.randomtp;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class RandomTP extends JavaPlugin {
  private static Economy econ = null;

  @Override
  public void onEnable() {
    // Plugin startup logic
    if (!setupEconomy() ) {
      getLogger().info("Disabled due to no Vault dependency found!");
      getServer().getPluginManager().disablePlugin(this);
      return;
    }
    new RTPCommand(this);
  }

  @Override
  public void onDisable() {
    // Plugin shutdown logic
  }

  private boolean setupEconomy() {
    if (getServer().getPluginManager().getPlugin("Vault") == null) {
      return false;
    }
    RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
    if (rsp == null) {
      return false;
    }
    econ = rsp.getProvider();
    return econ != null;
  }

  public static Economy getEcon() {
    return econ;
  }
}
