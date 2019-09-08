package org.voidane.eliteblockeffects;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.voidane.eliteblock.effects.commands.commandEBEReload;
import org.voidane.eliteblock.effects.commands.commandEBEarrowdamage;
import org.voidane.eliteblock.effects.commands.commandEBEarrowdestroy;
import org.voidane.eliteblock.effects.commands.commandEliteBlockEffects;
import org.voidane.eliteblockeffects.events.ArrowToBlockInteract;
import org.voidane.eliteblockeffects.events.Destroyed;
import org.voidane.eliteblockeffects.events.DestroyedEffects;
import org.voidane.eliteblockeffects.events.Placement;
import org.voidane.eliteblockeffects.events.PlacementSounds;

import gnu.trove.Version;

public class Main extends JavaPlugin{
	
	public String version = "1.01.1";
	
	@Override
	public void onEnable() {
		Bukkit.getServer().getConsoleSender().sendMessage("[EliteBlockEffects] Attempting to enable config");
		saveDefaultConfig();
		Bukkit.getServer().getConsoleSender().sendMessage("[EliteBlockEffects] Config successfully enabled");
		Bukkit.getServer().getConsoleSender().sendMessage("[EliteBlockEffects] Registering Placement Effects.....Compiling Config");
		new Placement(this);
		Bukkit.getServer().getConsoleSender().sendMessage("[EliteBlockEffects] Successfully compiled Placement Effects");
		Bukkit.getServer().getConsoleSender().sendMessage("[EliteBlockEffects] Registering Placement Sound.....Compiling Config");
		new PlacementSounds(this);
		Bukkit.getServer().getConsoleSender().sendMessage("[EliteBlockEffects] Successfully compiled Placement Sounds");
		Bukkit.getServer().getConsoleSender().sendMessage("[EliteBlockEffects] Registering Destroyed Sound.....Compiling Config");
		new Destroyed(this);
		Bukkit.getServer().getConsoleSender().sendMessage("[EliteBlockEffects] Successfully compiled Destroyed Sounds ");
		Bukkit.getServer().getConsoleSender().sendMessage("[EliteBlockEffects] Registering Destroyed Effects.....Compiling Config");
		new DestroyedEffects(this);
		Bukkit.getServer().getConsoleSender().sendMessage("[EliteBlockEffects] Successfully compiled Destroyed Effects");
		new Config(this);
		new ArrowToBlockInteract(this);
		this.getCommand("eliteblockeffects");
		new Config(this).copyOldConfigToNew();
	}
	
	@Override
	public void onDisable() {
		Bukkit.getServer().getConsoleSender().sendMessage("[EliteBlockEffects] DISABLED");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		
		Player player = (Player) sender;
		
		if (player.hasPermission("eliteblockeffects")) {
		if (cmd.getName().equalsIgnoreCase("eliteblockeffects")) {
			if (args.length == 0) {
			new commandEliteBlockEffects(this, player);
			return true;
			} else if (args.length > 0) {
			if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
				if (player.hasPermission("eliteblockeffects.reload")) {
				new commandEBEReload(this, player);
				return true;
				}
			} else if (args[0].equalsIgnoreCase("help")){
				if (player.hasPermission("eliteblockeffects.help")) {
					player.sendMessage(translateChatColor("&c[&fEliteBlockEffects&c] &bViewing page 1 of 1"));
					player.sendMessage(translateChatColor("&6/ebe help &f: &adisplay commands used by ebe"));
					player.sendMessage(translateChatColor("&6/ebe reload &f: &areload ebe's config files"));
					player.sendMessage(translateChatColor("&6/ebe create &f: &acreate a new config instance"));
					player.sendMessage(translateChatColor("&6/ebe arrow &f: &aChange arrow options"));
					return true;
				}
				} else if (args[0].equalsIgnoreCase("create")) {
					if (args.length == 1) {
						if (player.hasPermission("eliteblockeffects.create")) {
						player.sendMessage(translateChatColor("&c[&fEliteBlockEffects&c] &b/ebe create (number)"));
						int total = 1+getConfig().getInt("Amount Placement Effects");
						player.sendMessage(translateChatColor("&fINFO: &cNext creation is &c"
								+total));
						player.sendMessage(translateChatColor
								("&4If not correctly done the config can be broken (Use the right number above)"));
						player.sendMessage(translateChatColor(
								"&dSorry , this command will be released in a later update when ready!"));
						return true;
						}
					} else if (args.length > 1) {
						
					}
				} else if (args[0].equalsIgnoreCase("arrow") && player.hasPermission("eliteblockeffects.arrow")) {
					if (args.length == 1) {
						player.sendMessage(translateChatColor("&c[&fEliteBlockEffects&c]"));
						player.sendMessage(translateChatColor("&6/ebe arrow crack &f: Change if a arrow can crack a block"));
						player.sendMessage(translateChatColor("&6/ebe arrow break &f: Change if a arrow can break a block"));
						return true;
					}
					if (args[1].equalsIgnoreCase("crack")) {
						if (args.length == 2) {
							player.sendMessage(translateChatColor("&c[&fEliteBlockEffects&c] &bTrue or False"));
							return true;
						}
						if (args[2].equalsIgnoreCase("true") || args[2].equalsIgnoreCase("false")) {
							if (args[2].equalsIgnoreCase("true")) {
								new commandEBEarrowdamage(this).ebearrowdamageTrue(player);
								return true;
							} else if (args[2].equalsIgnoreCase("false")) {
								new commandEBEarrowdamage(this).ebearrowdamageFalse(player);
								return true;
							}
						} else {
							player.sendMessage(translateChatColor("&c[&fEliteBlockEffects&c] &cNot a valid value True or False"));
							return true;
						}
					} else if (args[1].equalsIgnoreCase("break")) {
						if (args.length == 2) {
							player.sendMessage(translateChatColor("&c[&fEliteBlockEffects&c] &bTrue or False"));
							return true;
						}
						if (args[2].equalsIgnoreCase("true") || args[2].equalsIgnoreCase("false")) {
							if (args[2].equalsIgnoreCase("true")) {
								new commandEBEarrowdestroy(this).ebearrowdestroyTrue(player);
								return true;
							} else if (args[2].equalsIgnoreCase("false")) {
								new commandEBEarrowdestroy(this).ebearrowdestroyFalse(player);
								return true;
							}
						} else {
							player.sendMessage(translateChatColor("&c[&fEliteBlockEffects&c] &cNot a valid value True or False"));
							return true;
						}
					}
				}
			}
		}
	}
		player.sendMessage(translateChatColor("&c[&fEliteBlockEffects&c] &bYou do not have permission to perform this action"));
		return false;
	}
	public String translateChatColor(String chat) {
		chat = ChatColor.translateAlternateColorCodes('&', chat);
		return chat;
	}
}
