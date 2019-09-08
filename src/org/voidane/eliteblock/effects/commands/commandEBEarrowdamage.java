package org.voidane.eliteblock.effects.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.voidane.eliteblockeffects.Main;

public class commandEBEarrowdamage {

	Main main;
	
	public commandEBEarrowdamage(Main main) {
		this.main = main;
	}
	
	public void ebearrowdamageFalse(Player player) {
		File file = new File(main.getDataFolder(), "Projectile Options.yml");
		FileConfiguration configuration;
		configuration = YamlConfiguration.loadConfiguration(file);
		
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c[&fEliteBlockEffects&c] "+
	"&cArrow Cracks is disabled"));
		configuration.set("Arrows Crack Blocks", false);
		try {
			configuration.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void ebearrowdamageTrue(Player player) {
		File file = new File(main.getDataFolder(), "Projectile Options.yml");
		FileConfiguration configuration;
		configuration = YamlConfiguration.loadConfiguration(file);
		
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c[&fEliteBlockEffects&c] "+
	"&aArrow cracks is enabled"));
		configuration.set("Arrows Crack Blocks", true);	
		
		try {
			configuration.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
