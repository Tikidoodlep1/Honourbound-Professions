package com.tiki.honourboundprofessions.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.tiki.honourboundprofessions.files.JSONCrafting;

import net.md_5.bungee.api.ChatColor;

public class GainPointsOnCrafting implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		boolean craft;
		Material material;
		int levels;
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			
			if(args.length != 3) {
				p.sendMessage(String.format("%s%sIncorrect number of arguments", ChatColor.RED, ChatColor.UNDERLINE));
				return false;
			}
			if(args[0].equalsIgnoreCase("craft")) {
				craft = true;
			}else if(args[0].equalsIgnoreCase("smelt")) {
				craft = false;
			}else {
				p.sendMessage(String.format("%s%sFirst argument not recognized", ChatColor.RED, ChatColor.UNDERLINE));
				return false;
			}
			if(Material.getMaterial(args[1]) == null) {
				p.sendMessage(String.format("%s%sMaterial not recognized", ChatColor.RED, ChatColor.UNDERLINE));
				return false;
			}else {
				material = Material.getMaterial(args[1]);
			}
			try {
				levels = Integer.parseInt(args[2]);
			}catch (NumberFormatException e) {
				p.sendMessage(String.format("%s%sThe third argument must be a number only using the characters [0-9]", ChatColor.RED, ChatColor.UNDERLINE));
				return false;
			}
			
			if(craft) {
				JSONCrafting.craftingMap.put(material, levels);
				JSONCrafting.writeJson(JSONCrafting.craftingMap, JSONCrafting.craftingPath);
				p.sendMessage(String.format("%s%sSuccessfully registered " + material + " to the crafting list", ChatColor.GREEN, ChatColor.UNDERLINE));
			}else {
				JSONCrafting.smeltingMap.put(material, levels);
				JSONCrafting.writeJson(JSONCrafting.smeltingMap, JSONCrafting.smeltingPath);
				p.sendMessage(String.format("%s%sSuccessfully registered " + material + " to the smelting list", ChatColor.GREEN, ChatColor.UNDERLINE));
			}
			
		}else if(sender instanceof ConsoleCommandSender) {
			if(args.length != 3) {
				System.out.println("Incorrect number of arguments");
				return false;
			}
			if(args[0].equalsIgnoreCase("craft")) {
				craft = true;
			}else if(args[0].equalsIgnoreCase("smelt")) {
				craft = false;
			}else {
				System.out.println("First argument not recognized");
				return false;
			}
			if(Material.getMaterial(args[1]) == null) {
				System.out.println("Material not recognized");
				return false;
			}else {
				material = Material.getMaterial(args[1]);
			}
			try {
				levels = Integer.parseInt(args[2]);
			}catch (NumberFormatException e) {
				System.out.println("The third argument must be a number only using the characters [0-9]");
				return false;
			}
			
			if(craft) {
				JSONCrafting.craftingMap.put(material, levels);
				JSONCrafting.writeJson(JSONCrafting.craftingMap, JSONCrafting.craftingPath);
				System.out.println("Successfully registered " + material + " to the crafting list");
			}else {
				JSONCrafting.smeltingMap.put(material, levels);
				JSONCrafting.writeJson(JSONCrafting.smeltingMap, JSONCrafting.smeltingPath);
				System.out.println("Successfully registered " + material + " to the smelting list");
			}
		}
		return true;
	}
}
