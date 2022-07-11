package pe.bazan.luis.plugins.randomtp;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class RandomTP extends JavaPlugin {
  private static Economy econ = null;
  private CustomYML messages = null;
  private CustomYML config = null;
  private CustomYML db = null;

  @Override
  public void onEnable() {
    reloadConfigs();
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

  public void reloadConfigs() {
    messages = new CustomYML("messages", this);
    config = new CustomYML("config", this);
    db = new CustomYML("db", this);
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

  public CustomYML getCustomConfig() {
    return config;
  }

  public CustomYML getMessages() {
    return messages;
  }

  public CustomYML getDb() {
    return db;
  }
}
