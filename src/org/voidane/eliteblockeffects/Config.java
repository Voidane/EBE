package org.voidane.eliteblockeffects;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {

	Main main;
	
	public Config(Main main) {
		this.main = main;
		loadFileConfigurations();
		loadProjectileEnableConfig();
	}
	
	
	private void loadFileConfigurations() {
		
		
		File storeMetaDataFile = new File(main.getDataFolder(),"CrackedBlocks.yml");
		FileConfiguration storeMetaConfiguration;
		storeMetaConfiguration = YamlConfiguration.loadConfiguration(storeMetaDataFile);
		storeMetaConfiguration.set("Hit Amount.Total", 0);
		if (!storeMetaDataFile.exists()) {
			
		try {
			storeMetaConfiguration.save(storeMetaDataFile);
			} catch (IOException e) {
			e.printStackTrace();
			}
			storeMetaConfiguration = YamlConfiguration.loadConfiguration(storeMetaDataFile);
		}
	}
	
	private void loadProjectileEnableConfig() {
		File file = new File(main.getDataFolder(), "Projectile Options.yml");
		FileConfiguration configuration;
		configuration = YamlConfiguration.loadConfiguration(file);
		
		if (!file.exists()) {
			configuration.set("Arrows Break Blocks", true);
			configuration.set("Arrows Crack Blocks", true);
		}
		try {
			configuration.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void copyOldConfigToNew() {
		
		File file = new File(main.getDataFolder(), "config.yml");
		FileConfiguration configuration;
		configuration = YamlConfiguration.loadConfiguration(file);
		
		if (main.getConfig().getString("Version") == null) {
			
			new ConfigInfoSender(main).getContentsOfOldConfig();
			
		}
		
		if (main.getConfig().getString("Version") != main.version ) {
			
			new ConfigInfoSender(main).getContentsOfOldConfig();
			
		}
		
	}
	
}
