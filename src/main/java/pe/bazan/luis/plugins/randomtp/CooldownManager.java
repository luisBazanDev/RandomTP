package pe.bazan.luis.plugins.randomtp;

import java.awt.*;

public class CooldownManager {
  private CustomYML db;
  private long cooldown = 0;
  public CooldownManager(RandomTP plugin) {
    this.db = plugin.getDb();
    this.cooldown = plugin.getCustomConfig().getConfigField("cooldown", 7200) * 1000;
  }

  public long getCooldown(String user) {
    Long timeStamp = db.getConfigField("cooldown."+user);
    if(timeStamp == null) {
      db.setField("cooldown."+user, System.currentTimeMillis());
      return 0;
    }
    long compare = System.currentTimeMillis() - this.cooldown;
    if(compare < timeStamp)
      return (timeStamp - compare) / 1000;
    else
      db.setField("cooldown."+user, System.currentTimeMillis());
    return 0;
  }
}
