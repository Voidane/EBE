package org.voidane.eliteblockeffects.events;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.voidane.eliteblockeffects.Main;
import net.minecraft.server.v1_8_R1.EnumParticle;
import net.minecraft.server.v1_8_R1.PacketPlayOutWorldParticles;

public class Placement implements Listener {

	Main main;
	
	public Placement(Main main) {
		this.main = main;
		Bukkit.getServer().getPluginManager().registerEvents(this, main);
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void blockPlacedInAcceptance(BlockPlaceEvent e) {
		
		Player player = e.getPlayer();
		
		for (int i = 1 ; i < main.getConfig().getInt("Amount Placement Effects")+1 ; i++ ) {
			if (e.getBlock().getType() == Material.getMaterial(
					main.getConfig().getString("Block Effects."+i+".Material Name")) && e.getBlock().getData() == (byte) 
					main.getConfig().getInt("Block Effects."+i+".Material Data")) {
				
				if (player.hasPermission(main.getConfig().getString("Block Effects."+i+".Permission"))) {
					if (main.getConfig().getInt("Block Effects."+i+".Type") == 1) {
						if (main.getConfig().getString("Block Effects."+i+".Effect").length() > 1) {
						PacketPlayOutWorldParticles particles = new PacketPlayOutWorldParticles(EnumParticle.valueOf
								(main.getConfig().getString("Block Effects."+i+".Effect")), true, 
								(float)e.getBlock().getLocation().getX(),
								(float)e.getBlock().getLocation().getY()+1, 
										(float)e.getBlock().getLocation().getZ(),
										0, 0, 0, 0, 0, 0);
						PacketPlayOutWorldParticles particles2 = new PacketPlayOutWorldParticles(EnumParticle.valueOf
								(main.getConfig().getString("Block Effects."+i+".Effect")), true, 
								(float)e.getBlock().getLocation().getX()+1,
								(float)e.getBlock().getLocation().getY()+1, 
										(float)e.getBlock().getLocation().getZ()+1,
										0, 0, 0, 0, 0, 0);
						PacketPlayOutWorldParticles particles3 = new PacketPlayOutWorldParticles(EnumParticle.valueOf
								(main.getConfig().getString("Block Effects."+i+".Effect")), true, 
								(float)e.getBlock().getLocation().getX(),
								(float)e.getBlock().getLocation().getY()+1, 
										(float)e.getBlock().getLocation().getZ()+1,
										0, 0, 0, 0, 0, 0);
						PacketPlayOutWorldParticles particles4 = new PacketPlayOutWorldParticles(EnumParticle.valueOf
								(main.getConfig().getString("Block Effects."+i+".Effect")), true, 
								(float)e.getBlock().getLocation().getX()+1,
								(float)e.getBlock().getLocation().getY()+1, 
										(float)e.getBlock().getLocation().getZ(),
										0, 0, 0, 0, 0, 0);
				new BukkitRunnable() {
					@Override
					public void run() {
							((CraftPlayer) player).getHandle().playerConnection.sendPacket(particles);
							((CraftPlayer) player).getHandle().playerConnection.sendPacket(particles2);
							((CraftPlayer) player).getHandle().playerConnection.sendPacket(particles3);
							((CraftPlayer) player).getHandle().playerConnection.sendPacket(particles4);
							}
				
						}.runTaskTimerAsynchronously(main, 0, Integer.MAX_VALUE);	// 68 years 8 days before next tick //
						}
					}
				}
			}
		}
	}
}/*
	private int numX;
	private int numY;
	private int numZ;
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void blockPlaceEffectTypePlus(BlockPlaceEvent e) {
		Player player = e.getPlayer();
		int start=0;
		for ( start = 1 ; start < main.getConfig().getInt("Amount Placement Effects")+1 ; start++ ) {
			player.sendMessage(e.getBlockPlaced().getType()
					+":"+e.getBlockPlaced().getData()+" == "+Material.valueOf(main.getConfig().getString("Block Effects."+start+".Material Name"))+
					":"+main.getConfig().getInt("Block Effects."+start+".Material Data"));
			
			if (e.getBlockPlaced().getType() == Material.getMaterial(
					main.getConfig().getString("Block Effects."+start+".Material Name")) && e.getBlockPlaced().getData() == (byte) 
					main.getConfig().getInt("Block Effects."+start+".Material Data")) {

				
				if (player.hasPermission(main.getConfig().getString("Block Effects."+start+".Permission"))) {
					
					if (main.getConfig().getInt("Block Effects."+start+".Type") == 2) {
					
						for ( int a = 0 ; a < 8 ; a++ ) {
							if (a == 0) {
								numX = 0; numY = 0; numZ = 0;
							}
							if (a == 1) {
								numX = 0; numY = 1; numZ = 0;
							}
							if (a == 2) {
								numX = 0; numY = -1; numZ = 0;
							}
							if (a == 3) {
								numX = 0; numY = 0; numZ = 1;
							}
							if (a == 4) {
								numX = 0; numY = 0; numZ = -1;
							}
							if (a == 5) {
								numX = 1; numY = 0; numZ = 0;
							}
							if (a == 6) {
								numX = -1; numY = 0; numZ = 0;
							}
							if (main.getConfig().getString("Block Effects."+start+".Effect").length() > 1) {
						PacketPlayOutWorldParticles particles = new PacketPlayOutWorldParticles(EnumParticle.valueOf
								(main.getConfig().getString("Block Effects."+start+".Effect")), true, 
								(float)e.getBlockPlaced().getLocation().getX()+numX,
								(float)e.getBlockPlaced().getLocation().getY()+numY, 
										(float)e.getBlockPlaced().getLocation().getZ()+numZ,
										0, 0, 0, 0, 0, 0);
							
				new BukkitRunnable() {
					@Override
					public void run() {

						
						
						
							((CraftPlayer) player).getHandle().playerConnection.sendPacket(particles);
							}
								}.runTaskTimerAsynchronously(main, 0, Integer.MAX_VALUE);	// 68 years 8 days before next tick //
							}
						}
					}
			}
		}
	}
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void blockPlaceEffectTypeBox(BlockPlaceEvent e) {
		Player player = e.getPlayer();
		
		for (int i = 0 ; i < main.getConfig().getInt("Amount Placement Effects")+1 ; i++ ) {
			if (e.getBlockPlaced().getType() == Material.getMaterial(
					main.getConfig().getString("Block Effects."+i+".Material Name")) && e.getBlockPlaced().getData() == (byte) 
					main.getConfig().getInt("Block Effects."+i+".Material Data")) {
				player.sendMessage("success materials match");
				
				if (player.hasPermission(main.getConfig().getString("Block Effects."+i+".Permission"))) {
					player.sendMessage("Permission passes");
					if (main.getConfig().getInt("Block Effects."+i+".Type") == 3) {
						player.sendMessage("sending locational info");
							for ( int a = 0 ; a < 27 ; a++ ) {
							
							if (a == 0) {
								numX = 0; numY = 0; numZ = 0;
							}
							if (a == 1) {
								numX = 0; numY = 1; numZ = 0;
							}
							if (a == 2) {
								numX = 1; numY = 1; numZ = 0;
							}
							if (a == 3) {
								numX = -1; numY = 1; numZ = 0;
							}
							if (a == 4) {
								numX = 0; numY = 1; numZ = 1;
							}
							if (a == 5) {
								numX = 0; numY = 1; numZ = -1;
							}
							if (a == 6) {
								numX = 1; numY = 1; numZ = 1;
							}
							if (a == 7) {
								numX = -1; numY = 1; numZ = -1;
							}
							if (a == 8) {
								numX = -1; numY = 1; numZ = 1;
							}
							if (a == 9) {
								numX = 1; numY = 1; numZ = -1;
							}
							if (a == 10) {
								numX = 0; numY = 0; numZ = 0;
							}
							if (a == 11) {
								numX = 1; numY = 0; numZ = 0;
							}
							if (a == 12) {
								numX = -1; numY = 0; numZ = 0;
							}
							if (a == 13) {
								numX = 0; numY = 0; numZ = 1;
							}
							if (a == 14) {
								numX = 0; numY = 0; numZ = -1;
							}
							if (a == 15) {
								numX = 1; numY = 0; numZ = 1;
							}
							if (a == 16) {
								numX = -1; numY = 0; numZ = -1;
							}
							if (a == 17) {
								numX = -1; numY = 0; numZ = 1;
							}
							if (a == 18) {
								numX = 1; numY = 0; numZ = -1;
							}
                            if (a == 19) {
								numX = 0; numY = -1; numZ = 0;
							}
							if (a == 20) {
								numX = 1; numY = -1; numZ = 0;
							}
							if (a == 21) {
								numX = -1; numY = -1; numZ = 0;
							}
							if (a == 22) {
								numX = 0; numY = -1; numZ = 1;
							}
							if (a == 23) {
								numX = 0; numY = -1; numZ = -1;
							}
							if (a == 24) {
								numX = 1; numY = -1; numZ = 1;
							}
							if (a == 25) {
								numX = -1; numY = -1; numZ = -1;
							}
							if (a == 26) {
								numX = -1; numY = -1; numZ = 1;
							}
							if (a == 27) {
								numX = 1; numY = -1; numZ = -1;
							}
							if (main.getConfig().getString("Block Effects."+i+".Effect").length() > 1) {
								player.sendMessage("passes length "+a);
						PacketPlayOutWorldParticles particles = new PacketPlayOutWorldParticles(EnumParticle.valueOf(main
								.getConfig().getString("Block Effects."+i+".Effect")), true, 
								(float)e.getBlockPlaced().getLocation().getX()+numX,
								(float)e.getBlockPlaced().getLocation().getY()+numY, 
										(float)e.getBlockPlaced().getLocation().getZ()+numZ,
										0, 0, 0, 0, 0, 0);
				new BukkitRunnable() {
					@Override
					public void run() {
						player.sendMessage("sends particles ");
							((CraftPlayer) player).getHandle().playerConnection.sendPacket(particles);
						}
								}.runTaskTimerAsynchronously(main, 0, Integer.MAX_VALUE);	// 68 years 8 days before next tick //
							}
						}
					}
				}
			}
		}
	}
}*/
