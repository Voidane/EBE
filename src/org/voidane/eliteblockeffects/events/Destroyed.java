package org.voidane.eliteblockeffects.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.voidane.eliteblockeffects.Main;

public class Destroyed implements Listener {

	Main main;
	
	public Destroyed(Main main) {
		this.main = main;
		Bukkit.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void breakEffectParticles(BlockBreakEvent e) {
		Player player = e.getPlayer();
		for (int i = 0 ; i < main.getConfig().getInt("Amount Placement Effects")+1 ; i++ ) {
			
			if (e.getBlock().getType() == Material.getMaterial(
					main.getConfig().getString("Block Effects."+i+".Material Name")) && e.getBlock().getData() == (byte) 
					main.getConfig().getInt("Block Effects."+i+".Material Data")) {
			
				
				if (player.hasPermission(main.getConfig().getString("Block Effects."+i+".Permission Sound"))) {
			
					if (main.getConfig().getString("Block Effects."+i+".Sound Break").length() > 1) {
					e.getPlayer().playSound(e.getBlock().getLocation(), Sound.valueOf(main.getConfig()
							.getString("Block Effects."+i+".Sound Break")), 1.0F, 1.0F);
					}
				}
			}
		}
	}
}
