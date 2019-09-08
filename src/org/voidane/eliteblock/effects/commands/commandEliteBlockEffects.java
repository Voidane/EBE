package org.voidane.eliteblock.effects.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.voidane.eliteblockeffects.Main;

public class commandEliteBlockEffects {

	Main main;
	
	public commandEliteBlockEffects(Main main , CommandSender sender) {
		this.main = main;
		Player player = (Player) sender;
		player.sendMessage
		(ChatColor.translateAlternateColorCodes('&', "&c[&fEliteBlockEffects&c] &6use /ebe help for more information"));
	}
	
	
	
}
