package pe.bazan.luis.plugins.randomtp;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;
import java.util.SplittableRandom;

public class MessagesFormat {
  private static RandomTP plugin;

  public static void setPlugin(RandomTP plugin) {
    MessagesFormat.plugin = plugin;
  }

  public static String getPrefix() {
    return plugin.getMessages().getConfigField("prefix");
  }

  public static void sendSenderWithPrefix(CommandSender sender, String field) {
    String msg = plugin.getMessages().getConfigField(field);
    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', getPrefix()+msg));
  }

  public static void sendSender(CommandSender sender, String field) {
    String msg = plugin.getMessages().getConfigField(field);
    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
  }

  public static void sendSender(CommandSender sender, String field, String[] variables) {
    String msg = plugin.getMessages().getConfigField(field);
    for (String variable : variables) {
      msg = msg.replaceFirst("%s", variable);
    }
    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
  }

  public static void sendSenderWithPrefix(CommandSender sender, String field, String[] variables) {
    String msg = plugin.getMessages().getConfigField(field);
    for (String variable : variables) {
      msg = msg.replaceFirst("%s", variable);
    }
    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', getPrefix()+msg));
  }

  public static void sendMultiLineSender(CommandSender sender, String field) {
    List<String> lines = plugin.getMessages().getConfigField(field);
    for(String line : lines) {
      sender.sendMessage(ChatColor.translateAlternateColorCodes('&', line));
    }
  }

  public static void sendMultiLineSender(CommandSender sender, String field, String[] variables) {
    List<String> lines = plugin.getMessages().getConfigField(field);
    List<String> vars = Arrays.stream(variables).toList();
    lines.forEach((line)->{
      vars.forEach((var) -> {
        if(!line.contains("%s")) return;
        line.replaceFirst("%s", var);
        vars.remove(0);
      });
      sender.sendMessage(ChatColor.translateAlternateColorCodes('&', line));
    });
  }
}
