package org.voidane.eliteblock.effects.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.voidane.eliteblockeffects.Main;

public class commandEBEarrowdestroy {
	
	Main main;
	
	public commandEBEarrowdestroy(Main main) {
		this.main = main;
	}
	
	public void ebearrowdamage(Player player) {
		player.sendMessage("True or False");
	}
	
	public void ebearrowdestroyTrue(Player player) {
		File file = new File(main.getDataFolder(), "Projectile Options.yml");
		FileConfiguration configuration;
		configuration = YamlConfiguration.loadConfiguration(file);
		
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c[&fEliteBlockEffects&c] "+
				"&aArrow breaking is enabled"));
		configuration.set("Arrows Break Blocks", true);
		try {
			configuration.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void ebearrowdestroyFalse(Player player) {
		File file = new File(main.getDataFolder(), "Projectile Options.yml");
		FileConfiguration configuration;
		configuration = YamlConfiguration.loadConfiguration(file);
		
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c[&fEliteBlockEffects&c] "+
				"&cArrow breaking is disabled"));
		configuration.set("Arrows Break Blocks", false);
		try {
			configuration.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
