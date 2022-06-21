package com.tiki.honourboundprofessions.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.tiki.honourboundprofessions.helpers.PersistentDataTracker;

import net.md_5.bungee.api.ChatColor;

public class ClassLevels implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length != 4) {
				p.sendMessage(String.format("%s%sIncorrect number of arguments", ChatColor.RED, ChatColor.UNDERLINE));
			}else {
				boolean give = true;
				if(args[0].equalsIgnoreCase("take")) {
					give = false;
				}else if(args[0].equalsIgnoreCase("give")) {
					give = true;
				}else {
					p.sendMessage(String.format("%s%sFirst argument not recognized", ChatColor.RED, ChatColor.UNDERLINE));
					return false;
				}
				String profession = args[1];
				if(!(profession.equalsIgnoreCase("smith") || profession.equalsIgnoreCase("fletcher") || profession.equalsIgnoreCase("alchemist")
						|| profession.equalsIgnoreCase("farmer") || profession.equalsIgnoreCase("enchanter"))) {
					p.sendMessage(String.format("%s%sProfession not recognized, please ensure it's spelled correctly", ChatColor.RED, ChatColor.UNDERLINE));
					return false;
				}else {
					profession = profession.toLowerCase();
				}
				String playerName = args[2];
				int levels = 0;
				try {
					levels = Integer.parseInt(args[3]);
				}catch(NumberFormatException e) {
					p.sendMessage(String.format("%s%sThe third argument must be a number only using the characters [0-9]", ChatColor.RED, ChatColor.UNDERLINE));
					return false;
				}
				
				Player target = Bukkit.getServer().getPlayerExact(playerName);
				
				if(target == null) {
					p.sendMessage(String.format("%s%sGiven player is not online or does not exist", ChatColor.RED, ChatColor.UNDERLINE));
					return false;
				}
				
				switch(profession) {
				case "smith":
					int fullLevels = PersistentDataTracker.modifyLevels(p, PersistentDataTracker.smithkey, levels, give);
					p.sendMessage(ChatColor.GOLD + "You have successfully modified " + target.getPlayerListName() + "'s smithing level to " + fullLevels);
					target.sendMessage(ChatColor.GOLD + "You have been set to level " + fullLevels + " in smithing");
					break;
				case "fletcher":
					int fullLevelf = PersistentDataTracker.modifyLevels(p, PersistentDataTracker.fletcherkey, levels, give);
					p.sendMessage(ChatColor.LIGHT_PURPLE + "You have successfully modified " + target.getPlayerListName() + "'s fletching level to " + fullLevelf);
					target.sendMessage(ChatColor.LIGHT_PURPLE + "You have been set to level " + fullLevelf + " in fletching");
					break;
				case "alchemist":
					int fullLevela = PersistentDataTracker.modifyLevels(p, PersistentDataTracker.alchemistkey, levels, give);
					p.sendMessage(ChatColor.DARK_AQUA + "You have successfully modified " + target.getPlayerListName() + "'s alchemy level to " + fullLevela);
					target.sendMessage(ChatColor.DARK_AQUA + "You have been set to level " + fullLevela + " in alchemy");
					break;
				case "farmer":
					int fullLevelfa = PersistentDataTracker.modifyLevels(p, PersistentDataTracker.farmerkey, levels, give);
					p.sendMessage(ChatColor.GREEN + "You have successfully modified " + target.getPlayerListName() + "'s farming level to " + fullLevelfa);
					target.sendMessage(ChatColor.GREEN + "You have been set to level " + fullLevelfa + " in farming");
					break;
				case "enchanter":
					int fullLevele = PersistentDataTracker.modifyLevels(p, PersistentDataTracker.enchanterkey, levels, give);
					p.sendMessage(ChatColor.DARK_RED + "You have successfully modified " + target.getPlayerListName() + "'s enchanting level to " + fullLevele);
					target.sendMessage(ChatColor.DARK_RED + "You have been set to level " + fullLevele + " in enchanting");
					break;
				}
			}
		}else if(sender instanceof ConsoleCommandSender) {
			if(args.length != 4) {
				System.out.println("Incorrect number of arguments");
			}else {
				boolean give = true;
				if(args[0].equalsIgnoreCase("take")) {
					give = false;
				}else if(args[0].equalsIgnoreCase("give")) {
					give = true;
				}else {
					System.out.println("First argument not recognized");
					return false;
				}
				String profession = args[1];
				if(!(profession.equalsIgnoreCase("smith") || profession.equalsIgnoreCase("fletcher") || profession.equalsIgnoreCase("alchemist")
						|| profession.equalsIgnoreCase("farmer") || profession.equalsIgnoreCase("enchanter"))) {
					System.out.println("Profession not recognized, please ensure it's spelled correctly");
					return false;
				}else {
					profession = profession.toLowerCase();
				}
				String playerName = args[2];
				int levels = 0;
				try {
					levels = Integer.parseInt(args[3]);
				}catch(NumberFormatException e) {
					System.out.println("The third argument must be a number only using the characters [0-9]");
					return false;
				}
				
				Player target = Bukkit.getServer().getPlayerExact(playerName);
				
				if(target == null) {
					System.out.println("Given player is not online or does not exist");
					return false;
				}
				
				switch(profession) {
				case "smith":
					PersistentDataTracker.modifyLevels(target, PersistentDataTracker.smithkey, levels, give);
					int fullLevels = PersistentDataTracker.getLevels(target, PersistentDataTracker.smithkey) + levels;
					fullLevels = fullLevels>10?10:fullLevels;
					System.out.println("You have successfully modified " + target.getPlayerListName() + "'s smithing level to " + fullLevels);
					target.sendMessage(ChatColor.GOLD + "You have been set to level " + fullLevels + " in smithing");
					break;
				case "fletcher":
					PersistentDataTracker.modifyLevels(target, PersistentDataTracker.fletcherkey, levels, give);
					int fullLevelf = PersistentDataTracker.getLevels(target, PersistentDataTracker.fletcherkey) + levels;
					fullLevelf = fullLevelf>10?10:fullLevelf;
					System.out.println("You have successfully modified " + target.getPlayerListName() + "'s fletching level to " + fullLevelf);
					target.sendMessage(ChatColor.LIGHT_PURPLE + "You have been set to level " + fullLevelf + levels + " in fletching");
					break;
				case "alchemist":
					PersistentDataTracker.modifyLevels(target, PersistentDataTracker.alchemistkey, levels, give);
					int fullLevela = PersistentDataTracker.getLevels(target, PersistentDataTracker.alchemistkey) + levels;
					fullLevela = fullLevela>10?10:fullLevela;
					System.out.println("You have successfully modified " + target.getPlayerListName() + "'s alchemy level to " + fullLevela);
					target.sendMessage(ChatColor.DARK_AQUA + "You have been set to level " + fullLevela + levels + " in alchemy");
					break;
				case "farmer":
					PersistentDataTracker.modifyLevels(target, PersistentDataTracker.farmerkey, levels, give);
					int fullLevelfa = PersistentDataTracker.getLevels(target, PersistentDataTracker.farmerkey) + levels;
					fullLevelfa = fullLevelfa>10?10:fullLevelfa;
					System.out.println("You have successfully modified " + target.getPlayerListName() + "'s farming level to " + fullLevelfa);
					target.sendMessage(ChatColor.GREEN + "You have been set to level " + fullLevelfa + levels + " in farming");
					break;
				case "enchanter":
					PersistentDataTracker.modifyLevels(target, PersistentDataTracker.enchanterkey, levels, give);
					int fullLevele = PersistentDataTracker.getLevels(target, PersistentDataTracker.enchanterkey) + levels;
					fullLevele = fullLevele>10?10:fullLevele;
					System.out.println("You have successfully modified " + target.getPlayerListName() + "'s enchanting level to " + fullLevele);
					target.sendMessage(ChatColor.DARK_RED + "You have been set to level " + fullLevele + levels + " in enchanting");
					break;
				}
			}
		}
		return true;
	}
}
