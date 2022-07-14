package pe.bazan.luis.plugins.randomtp;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import pe.bazan.luis.plugins.randomtp.commands.RTP;

import java.sql.Array;

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
    MessagesFormat.setPlugin(this);
    new RTP(this);
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

  public boolean withdrawAmount(CommandSender player, double amount) {
    double balance = econ.getBalance((OfflinePlayer) player);
    if(balance < amount) {
      MessagesFormat.sendSenderWithPrefix(player, "money.error", new String[]{String.valueOf(amount)});
      return false;
    }
    econ.withdrawPlayer((OfflinePlayer) player, amount);
    MessagesFormat.sendSenderWithPrefix(player, "money.success", new String[]{String.valueOf(amount)});
    return true;
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
