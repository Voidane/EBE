package org.voidane.eliteblockeffects.events;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.util.BlockIterator;
import org.voidane.eliteblockeffects.Main;
import net.minecraft.server.v1_8_R1.BlockPosition;
import net.minecraft.server.v1_8_R1.PacketPlayOutBlockBreakAnimation;


public class ArrowToBlockInteract implements Listener {
	
	Main main;
	
	public ArrowToBlockInteract(Main main) {
		this.main = main;
		Bukkit.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void arrowToBlockInteract(ProjectileHitEvent e) {

		if (e.getEntity() instanceof Arrow) {
			
		    Arrow arrow = (Arrow) e.getEntity();
            Entity shooter = (Entity) arrow.getShooter();
 
            if(shooter instanceof Player) {
               Player p = (Player)shooter;
		


	    World world = arrow.getWorld();
	    BlockIterator bi = new BlockIterator(world, arrow.getLocation().toVector(), arrow.getVelocity().normalize(), 0, 4);
	    org.bukkit.block.Block hit = null;

	    while(bi.hasNext())
	    {
	        hit = bi.next();
	        if(hit.getType()!=Material.AIR)
	        { 
	            break;
	        }
	    }
	    File filePO = new File(main.getDataFolder(), "Projectile Options.yml");
		FileConfiguration configurationPO;
		configurationPO = YamlConfiguration.loadConfiguration(filePO);
		boolean load = configurationPO.getBoolean("Arrows Break Blocks");
	    if (!configurationPO.getBoolean("Arrows Crack Blocks")) {
	    	storeHitBlockData(hit.getLocation(), hit, world, p, load);
	    } else {
			if (p.hasPermission("eliteblockeffects.arroweffect.crack")) {
	    	PacketPlayOutBlockBreakAnimation packet = new PacketPlayOutBlockBreakAnimation(new Random().nextInt(2000), 
	    		new BlockPosition(hit.getLocation().getBlockX(), 
	    				hit.getLocation().getBlockY(), hit.getLocation().getBlockZ()), 5);
        	
        	((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
			}
	    }
	    storeHitBlockData(hit.getLocation(), hit, world, p, load);
            }
		}
	}
	
	private void storeHitBlockData(Location location, org.bukkit.block.Block hit, World world,Player player, boolean load) {
    	File file = new File(main.getDataFolder(), "CrackedBlocks.yml");
    		FileConfiguration configFileConfiguration;
    			configFileConfiguration = YamlConfiguration.loadConfiguration(file);
    	    	if (player.hasPermission("eliteblockeffects.arroweffect.crack")) {
    	int total = configFileConfiguration.getInt("Hit Amount.Total");
    	total++;
    	configFileConfiguration.set("Hit Amount.Total", total);
    	total = configFileConfiguration.getInt("Hit Amount.Total");
    	}

    	if (!configFileConfiguration.getBoolean("Block Hit."+location+".HIT")) {
    	configFileConfiguration.set("Block Hit."+location+".HIT", true);
    	} else {
    		if (load == true && player.hasPermission("eliteblockeffects.arroweffect.break")) { {
    			world.getBlockAt(location).setType(Material.AIR);
    			}
    		} else {
    		}
    	}
    	try {
			configFileConfiguration.save(file);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	
	}
}
	 

	  

















/*
	     * PacketPlayOutBlockBreakAnimation packet = new PacketPlayOutBlockBreakAnimation(new Random().nextInt(2000), 
		    		new BlockPosition(hit.getLocation().getBlockX(), 
		    				hit.getLocation().getBlockY(), hit.getLocation().getBlockZ()), 5);
	        	p.sendMessage(hit.getLocation().getBlockX()+"");
	        	
	        	((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
	        	
	        	
	        	
	        	
	    
			PacketPlayOutBlockBreakAnimation packet = new PacketPlayOutBlockBreakAnimation(new Random().nextInt(2000),
					new BlockPosition(e.getEntity().getLocation().getBlockX(), e.getEntity().getLocation().getBlockY()-1,
							e.getEntity().getLocation().getBlockZ()), 5);
			
			PacketPlayOutBlockBreakAnimation packet1 = new PacketPlayOutBlockBreakAnimation(new Random().nextInt(2000),
					new BlockPosition(e.getEntity().getLocation().getBlockX()+1, e.getEntity().getLocation().getBlockY(),
							e.getEntity().getLocation().getBlockZ()), 5);
			PacketPlayOutBlockBreakAnimation packet2 = new PacketPlayOutBlockBreakAnimation(new Random().nextInt(2000),
					new BlockPosition(e.getEntity().getLocation().getBlockX()-1, e.getEntity().getLocation().getBlockY(),
							e.getEntity().getLocation().getBlockZ()), 5);
			PacketPlayOutBlockBreakAnimation packet3 = new PacketPlayOutBlockBreakAnimation(new Random().nextInt(2000),
					new BlockPosition(e.getEntity().getLocation().getBlockX(), e.getEntity().getLocation().getBlockY(),
							e.getEntity().getLocation().getBlockZ()+1), 5);
			PacketPlayOutBlockBreakAnimation packet4 = new PacketPlayOutBlockBreakAnimation(new Random().nextInt(2000),
					new BlockPosition(e.getEntity().getLocation().getBlockX(), e.getEntity().getLocation().getBlockY(),
							e.getEntity().getLocation().getBlockZ()-1), 5);
			PacketPlayOutBlockBreakAnimation packet5 = new PacketPlayOutBlockBreakAnimation(new Random().nextInt(2000),
					new BlockPosition(e.getEntity().getLocation().getBlockX(), e.getEntity().getLocation().getBlockY()+1,
							e.getEntity().getLocation().getBlockZ()), 5);

			((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
			((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet1);
			((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet2);
			((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet3);
			((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet4);
			((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet5);
			*/
