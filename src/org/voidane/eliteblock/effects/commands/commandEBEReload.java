package org.voidane.eliteblock.effects.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.voidane.eliteblockeffects.Main;

public class commandEBEReload {
	
	Main main;

	public commandEBEReload(Main main, Player player) {
		this.main = main;
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c[&fEliteBlockEffects&c] &aConfig Reloaded"));
		main.reloadConfig();
	}
}

