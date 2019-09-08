package org.voidane.eliteblockeffects;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigInfoSender {

	Main main;
	
	public ConfigInfoSender(Main main) {
		this.main = main;
	}
	
	
	public void getContentsOfOldConfig() {
		
		int storeAmountPlacementEffectsOFC = main.getConfig().getInt("Amount Placement Effects");
		int storeAmountPlacementEffects = main.getConfig().getInt("Amount Placement Effects");
		storeAmountPlacementEffects++;
		
		String[] materialName = new String[storeAmountPlacementEffects];
		int[] materialData = new int[storeAmountPlacementEffects];
		String[] effect = new String[storeAmountPlacementEffects];
		String[] effectDestroy = new String[storeAmountPlacementEffects];
		int[] type = new int[storeAmountPlacementEffects];
		int[] typeDestroy = new int[storeAmountPlacementEffects];
		String[] soundPlace = new String[storeAmountPlacementEffects];
		String[] soundBreak = new String[storeAmountPlacementEffects];
		String[] permission = new String[storeAmountPlacementEffects];
		String[] permissionSound = new String[storeAmountPlacementEffects];
		
		for ( int i = 1 ; i < storeAmountPlacementEffects ; i++ ) {
			
			materialName[i] = main.getConfig().getString("Block Effects."+i+".Material Name");
			materialData[i] = main.getConfig().getInt("Block Effects."+i+".Material Data");
			effect[i] = main.getConfig().getString("Block Effects."+i+".Effect");
			effectDestroy[i] = main.getConfig().getString("Block Effects."+i+".Effect Destroy");
			type[i] = main.getConfig().getInt("Block Effects."+i+".Type");
			typeDestroy[i] = main.getConfig().getInt("Block Effects."+i+".Type Destroy");
			soundPlace[i] = main.getConfig().getString("Block Effects."+i+".Sound Place");
			soundBreak[i] = main.getConfig().getString("Block Effects."+i+".Sound Break");
			permission[i] = main.getConfig().getString("Block Effects."+i+".Permission");
			permissionSound[i] = main.getConfig().getString("Block Effects."+i+".Permission Sound");
			
		}
		
		File file = new File(main.getDataFolder(), "config.yml");
		FileConfiguration configuration;
		configuration = YamlConfiguration.loadConfiguration(file);
		
		try {
			FileUtils.forceDelete(file);
		} catch (IOException e) {
			Bukkit.getServer().getConsoleSender().sendMessage(main.translateChatColor
					("[EliteBlockEffects] &cNew config file failed to update"));
			e.printStackTrace();
		}
		
		main.saveDefaultConfig();
		
		for ( int i = 1 ; i < storeAmountPlacementEffects ; i++ ) {
			
			main.getConfig().set("Block Effects."+i+".Material Name", materialName[i]);
			main.getConfig().set("Block Effects."+i+".Material Data", materialData[i]);
			main.getConfig().set("Block Effects."+i+".Effect", effect[i]);
			main.getConfig().set("Block Effects."+i+".Effect Destroy", effectDestroy[i]);
			main.getConfig().set("Block Effects."+i+".Type", type[i]);
			main.getConfig().set("Block Effects."+i+".Type Destroy", typeDestroy[i]);
			main.getConfig().set("Block Effects."+i+".Sound Place", soundPlace[i]);
			main.getConfig().set("Block Effects."+i+".Sound Break", soundBreak[i]);
			main.getConfig().set("Block Effects."+i+".Permission", permission[i]);
			main.getConfig().set("Block Effects."+i+".Permission Sound", permissionSound[i]);
			
			main.getConfig().set("Amount Placement Effects", storeAmountPlacementEffectsOFC);
			
		}
		
		
	}
	
}
