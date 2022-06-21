package com.tiki.honourboundprofessions.commands;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.tiki.honourboundprofessions.helpers.PersistentDataTracker;

import net.md_5.bungee.api.ChatColor;

public class Levelups implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length != 3) {
				p.sendMessage(String.format("%s%sIncorrect number of arguments", ChatColor.RED, ChatColor.UNDERLINE));
			}else {
				System.out.println(Arrays.toString(args));
				System.out.println(args[0].equalsIgnoreCase("give"));
				boolean give = true;
				if(args[0].equalsIgnoreCase("take")) {
					give = false;
				}else if(args[0].equalsIgnoreCase("give")) {
					give = true;
				}else {
					p.sendMessage(String.format("%s%sFirst argument not recognized", ChatColor.RED, ChatColor.UNDERLINE));
					return false;
				}
				String playerName = args[1];
				int levelups = 0;
				try {
					levelups = Integer.parseInt(args[2]);
				}catch(NumberFormatException e) {
					p.sendMessage(String.format("%s%sThe third argument must be a number only using the characters [0-9]", ChatColor.RED, ChatColor.UNDERLINE));
					return false;
				}
				
				Player target = Bukkit.getServer().getPlayerExact(playerName);
				
				if(target == null) {
					p.sendMessage(String.format("%s%sGiven player is not online or does not exist", ChatColor.RED, ChatColor.UNDERLINE));
					return false;
				}
				p.sendMessage(ChatColor.GREEN + "You have successfully modified " + target.getPlayerListName() + "'s available levelups by " + levelups);
				target.sendMessage(ChatColor.GREEN + "Your available levelups have been modified by " + levelups);
				PersistentDataTracker.modifyPlayerLevelups(target, levelups, give);
			}
		}else if(sender instanceof ConsoleCommandSender) {
			if(args.length != 3) {
				System.out.println("Incorrect number of arguments");
			}else {
				System.out.println(Arrays.toString(args));
				System.out.println(args[0].equalsIgnoreCase("give"));
				boolean give = true;
				if(args[0].equalsIgnoreCase("take")) {
					give = false;
				}else if(args[0].equalsIgnoreCase("give")) {
					give = true;
				}else {
					System.out.println("First argument not recognized");
					return false;
				}
				String playerName = args[1];
				int levelups = 0;
				try {
					levelups = Integer.parseInt(args[2]);
				}catch(NumberFormatException e) {
					System.out.println("The third argument must be a number only using the characters [0-9]");
					return false;
				}
				
				Player target = Bukkit.getServer().getPlayerExact(playerName);
				
				if(target == null) {
					System.out.println("Given player is not online or does not exist");
					return false;
				}
				
				PersistentDataTracker.modifyPlayerLevelups(target, levelups, give);
				
			}
		}
		return true;
	}
}
