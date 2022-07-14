package pe.bazan.luis.plugins.randomtp.commands;

import dev.jorel.commandapi.CommandAPICommand;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import pe.bazan.luis.plugins.randomtp.MessagesFormat;
import pe.bazan.luis.plugins.randomtp.RandomTP;

import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class RTP {
  private RandomTP plugin;
  private CommandAPICommand rtp_help = new CommandAPICommand("help")
          .executesPlayer((sender, args) -> {
            MessagesFormat.sendMultiLineSender(sender, "help.user");
          });
  private CommandAPICommand rtp_self = new CommandAPICommand("rtp")
          .withPermission("rtp")
          .withSubcommand(rtp_help)
          .executesPlayer((sender, args) -> {
            long cooldown = plugin.getCooldownManager().getCooldown(sender.getName().toLowerCase());
            if(cooldown > 0 && !sender.hasPermission("rtp.bypass.cooldown")) {
              MessagesFormat.sendSenderWithPrefix(sender, "use.cooldown", new String[]{String.valueOf(Math.floorDiv(cooldown, 60)), String.valueOf(cooldown % 60)});
              return;
            }
            if(!plugin.withdrawAmount(sender, plugin.getCustomConfig().getConfigField("price"))) return;
            MessagesFormat.sendSenderWithPrefix(sender, "use.success");
            sender.teleport(getRandomPoint(plugin));
          });
  private CommandAPICommand rtp_other = new CommandAPICommand("rtp")
          .withPermission("rtp")
          .executesPlayer((sender, args) -> {

          });
  public RTP(RandomTP randomTP) {
    this.plugin = randomTP;
    rtp_self.register();
  }

  public static int getRandomCord(RandomTP randomTP) {
    final int min = randomTP.getCustomConfig().getConfigField("limits.min");
    final int max = randomTP.getCustomConfig().getConfigField("limits.max");
    final int cord = (int) Math.floor(Math.random() * (max - min) + min);
    if(Math.random() < 0.5) return cord * -1;
    else return cord;
  }

  public static Location getRandomPoint(RandomTP randomTP) {
    final List<String> dontBlocks = randomTP.getCustomConfig().getConfigField("dont-blocks");
    final World world = Bukkit.getWorld((String) randomTP.getCustomConfig().getConfigField("world_name"));
    Location loc = null;
    Integer x;
    Integer z;
    Block block;
    while (loc == null) {
      x = getRandomCord(randomTP);
      z = getRandomCord(randomTP);
      block = world.getHighestBlockAt(x, z);
      boolean isValid = true;
      for(String dontBlock : dontBlocks) {
        if(block.getType().toString().equalsIgnoreCase(dontBlock)) isValid = false;
      }
      if(isValid) loc = new Location(world, x+.5, block.getY() + 1, z+.5);
    }
    return loc;
  }
}
