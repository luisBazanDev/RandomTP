package pe.bazan.luis.plugins.randomtp;

import org.bukkit.command.CommandSender;

public class MessagesFormat {
  private static RandomTP plugin;

  public static void setPlugin(RandomTP plugin) {
    MessagesFormat.plugin = plugin;
  }

  public static String getPrefix() {
    return plugin.getMessages().getConfigField("prefix");
  }

  public static void sendSender(CommandSender sender, String field, String[] variables) {

  }
}
