package pe.bazan.luis.plugins.randomtp;

import dev.jorel.commandapi.CommandAPICommand;

public class RTPCommand {
  private RandomTP plugin;
  CommandAPICommand rtp = new CommandAPICommand("rtp")
          .withPermission("rtp.command")
          .executesPlayer((sender, args) -> {

          });

  public RTPCommand(RandomTP plugin) {
    this.plugin = plugin;
  }
}
