# Random TP
RandomTP is a plugin created in order to simplify and improve the alternatives that already exist. This is a simple project and I'll try to keep it up to date regarding minecraft versions etc. Any contribution is welcome!

## Licence <a href="./LICENCE.md">MIT</a>

## Translations
- 🇪🇸 spanish - <a href="./translations/spanish.yml">Here</a>
## Commands
- /rtp - Teleport you to a random point.
- /rtp help - Display help message.
## Permissions
- `rtp` - For use /rtp command
- `rtp.bypass.cooldown` - To avoid cooldown
## Default Config
```yml
# Limit in blocks  
limits:
  min: 300
  max: 2000

# World rtp  
world_name: "world"

# Cooldown for new use of the /rtp in seconds  
# set for 0 disabled or permission rtp.bypass.cooldown  
cooldown: 60

# Price for the command use (require VAULT)  
# set for 0 disabled  
price: 100.0

# Use points from the database? (Development)  
custom-points: false

# Blocks of the https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Material.html  
dont-blocks:
  - WATER
  - LAVA
  - GRASS
```