package com.tiki.honourboundprofessions.commands;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.tiki.honourboundprofessions.HBP;
import com.tiki.honourboundprofessions.helpers.PersistentDataTracker;

import net.md_5.bungee.api.ChatColor;

public class GUICommand implements CommandExecutor {
	
	public static final Inventory GUI = Bukkit.createInventory(null, 18, String.format("%s%sProfessions", ChatColor.DARK_GREEN, ChatColor.BOLD));
	public static final Inventory GUISMITH = Bukkit.createInventory(null, 18, String.format("%s%sSmithing", ChatColor.GOLD, ChatColor.BOLD));
	public static final Inventory GUIFLETCHER = Bukkit.createInventory(null, 18, String.format("%s%sFletching", ChatColor.LIGHT_PURPLE, ChatColor.BOLD));
	public static final Inventory GUIALCHEMIST = Bukkit.createInventory(null, 18, String.format("%s%sAlchemy", ChatColor.DARK_AQUA, ChatColor.BOLD));
	public static final Inventory GUIFARMER = Bukkit.createInventory(null, 18, String.format("%s%sFarming", ChatColor.GREEN, ChatColor.BOLD));
	public static final Inventory GUIENCHANTER = Bukkit.createInventory(null, 18, String.format("%s%sEnchanting", ChatColor.DARK_RED, ChatColor.BOLD));
	
	private static final ItemStack smith = new ItemStack(Material.ANVIL);
	private static final ItemStack fletcher = new ItemStack(Material.FEATHER);
	private static final ItemStack alchemist = new ItemStack(Material.LINGERING_POTION);
	private static final ItemStack farmer = new ItemStack(Material.WHEAT);
	private static final ItemStack enchanter = new ItemStack(Material.BOOK);
	private static final ItemStack points = new ItemStack(Material.EXPERIENCE_BOTTLE);
	private static final ItemStack empty = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
	private static final ItemStack back = new ItemStack(Material.ARROW);
	
	
	
	public static void openSmithGui(Player player) {
		int levelups = PersistentDataTracker.getPlayerLevelups(player);
		FileConfiguration config = HBP.getPlugin().getConfig();
		
		ItemStack[] filler = {new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE)};
		ItemStack[] leveled = {new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE)};
		ItemMeta fillerMeta[] = new ItemMeta[9];
		ItemMeta leveledMeta[] = new ItemMeta[9];
		
		for(int i = 0; i < 9; i++) {
			fillerMeta[i] = filler[i].getItemMeta();
			fillerMeta[i].setDisplayName(ChatColor.ITALIC + config.getConfigurationSection("Smithing Levels").getString("Level" + (i+1)));
			filler[i].setItemMeta(fillerMeta[i]);
			
			leveledMeta[i] = leveled[i].getItemMeta();
			leveledMeta[i].setDisplayName(ChatColor.ITALIC + config.getConfigurationSection("Smithing Levels").getString("Level" + (i+1)));
			leveled[i].setItemMeta(leveledMeta[i]);
		}
		
		ItemMeta backMeta = back.getItemMeta();
		ItemMeta emptyMeta = empty.getItemMeta();
		ItemMeta pointMeta = points.getItemMeta();
		ItemMeta smithMeta = smith.getItemMeta();
		
		backMeta.setDisplayName(String.format("%sBack to Professions Menu", ChatColor.GRAY));
		emptyMeta.setDisplayName(" ");
		pointMeta.setDisplayName(String.format("%s%s%sYou have %d points to allocate", ChatColor.YELLOW, ChatColor.BOLD, ChatColor.UNDERLINE, levelups)); 
		smithMeta.setDisplayName(String.format("%s%sSmith", ChatColor.GOLD, ChatColor.BOLD));
		
		ArrayList<String> smithArr = new ArrayList<String>();
		
		smithArr.add("Use an experience point to level up in Smithing?");
		
		smithMeta.setLore(smithArr);
		
		back.setItemMeta(backMeta);
		empty.setItemMeta(emptyMeta);
		points.setItemMeta(pointMeta);
		smith.setItemMeta(smithMeta);
		
		
		ItemStack[] inventory = {
				back, empty, empty, smith, empty, points, empty, empty, empty,
				filler[0], filler[1], filler[2], filler[3], filler[4], filler[5], filler[6], filler[7], filler[8]
		};
		
		for(int i = 9; i < PersistentDataTracker.getLevels(player, PersistentDataTracker.smithkey)+9; i++) {
			inventory[i] = leveled[i-9];
		}
		
		GUISMITH.setContents(inventory);
		
		player.openInventory(GUISMITH);
	}
	
	public static void openFletcherGui(Player player) {
		int levelups = PersistentDataTracker.getPlayerLevelups(player);
		FileConfiguration config = HBP.getPlugin().getConfig();
		
		ItemStack[] filler = {new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE)};
		ItemStack[] leveled = {new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE)};
		ItemMeta fillerMeta[] = new ItemMeta[9];
		ItemMeta leveledMeta[] = new ItemMeta[9];
		
		for(int i = 0; i < 9; i++) {
			fillerMeta[i] = filler[i].getItemMeta();
			fillerMeta[i].setDisplayName(ChatColor.ITALIC + config.getConfigurationSection("Fletching Levels").getString("Level" + (i+1)));
			filler[i].setItemMeta(fillerMeta[i]);
			
			leveledMeta[i] = leveled[i].getItemMeta();
			leveledMeta[i].setDisplayName(ChatColor.ITALIC + config.getConfigurationSection("Fletching Levels").getString("Level" + (i+1)));
			leveled[i].setItemMeta(leveledMeta[i]);
		}
		
		ItemMeta backMeta = back.getItemMeta();
		ItemMeta emptyMeta = empty.getItemMeta();
		ItemMeta pointMeta = points.getItemMeta();
		ItemMeta fletcherMeta = fletcher.getItemMeta();
		
		backMeta.setDisplayName(String.format("%sBack to Professions Menu", ChatColor.GRAY));
		emptyMeta.setDisplayName(" ");
		pointMeta.setDisplayName(String.format("%s%s%sYou have %d points to allocate", ChatColor.YELLOW, ChatColor.BOLD, ChatColor.UNDERLINE, levelups)); 
		fletcherMeta.setDisplayName(String.format("%s%sFletcher", ChatColor.LIGHT_PURPLE, ChatColor.BOLD));
		
		ArrayList<String> smithArr = new ArrayList<String>();
		
		smithArr.add("Use an experience point to level up in Fletching?");
		
		fletcherMeta.setLore(smithArr);
		
		back.setItemMeta(backMeta);
		empty.setItemMeta(emptyMeta);
		points.setItemMeta(pointMeta);
		fletcher.setItemMeta(fletcherMeta);
		
		
		ItemStack[] inventory = {
				back, empty, empty, fletcher, empty, points, empty, empty, empty,
				filler[0], filler[1], filler[2], filler[3], filler[4], filler[5], filler[6], filler[7], filler[8]
		};
		
		for(int i = 9; i < PersistentDataTracker.getLevels(player, PersistentDataTracker.fletcherkey)+9; i++) {
			inventory[i] = leveled[i-9];
		}
		
		GUIFLETCHER.setContents(inventory);
		
		player.openInventory(GUIFLETCHER);
	}

	public static void openAlchemistGui(Player player) {
		int levelups = PersistentDataTracker.getPlayerLevelups(player);
		FileConfiguration config = HBP.getPlugin().getConfig();
		
		ItemStack[] filler = {new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE)};
		ItemStack[] leveled = {new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE)};
		ItemMeta fillerMeta[] = new ItemMeta[9];
		ItemMeta leveledMeta[] = new ItemMeta[9];
		
		for(int i = 0; i < 9; i++) {
			fillerMeta[i] = filler[i].getItemMeta();
			fillerMeta[i].setDisplayName(ChatColor.ITALIC + config.getConfigurationSection("Alchemy Levels").getString("Level" + (i+1)));
			filler[i].setItemMeta(fillerMeta[i]);
			
			leveledMeta[i] = leveled[i].getItemMeta();
			leveledMeta[i].setDisplayName(ChatColor.ITALIC + config.getConfigurationSection("Alchemy Levels").getString("Level" + (i+1)));
			leveled[i].setItemMeta(leveledMeta[i]);
		}
		
		ItemMeta backMeta = back.getItemMeta();
		ItemMeta emptyMeta = empty.getItemMeta();
		ItemMeta pointMeta = points.getItemMeta();
		ItemMeta alchemistMeta = alchemist.getItemMeta();
		
		backMeta.setDisplayName(String.format("%sBack to Professions Menu", ChatColor.GRAY));
		emptyMeta.setDisplayName(" ");
		pointMeta.setDisplayName(String.format("%s%s%sYou have %d points to allocate", ChatColor.YELLOW, ChatColor.BOLD, ChatColor.UNDERLINE, levelups)); 
		alchemistMeta.setDisplayName(String.format("%s%sAlchemist", ChatColor.DARK_AQUA, ChatColor.BOLD));
		
		ArrayList<String> alchemistArr = new ArrayList<String>();
		
		alchemistArr.add("Use an experience point to level up in Alchemy?");
		
		alchemistMeta.setLore(alchemistArr);
		
		back.setItemMeta(backMeta);
		empty.setItemMeta(emptyMeta);
		points.setItemMeta(pointMeta);
		alchemist.setItemMeta(alchemistMeta);
		
		
		ItemStack[] inventory = {
				back, empty, empty, alchemist, empty, points, empty, empty, empty,
				filler[0], filler[1], filler[2], filler[3], filler[4], filler[5], filler[6], filler[7], filler[8]
		};
		
		for(int i = 9; i < PersistentDataTracker.getLevels(player, PersistentDataTracker.alchemistkey)+9; i++) {
			inventory[i] = leveled[i-9];
		}
		
		GUIALCHEMIST.setContents(inventory);
		
		player.openInventory(GUIALCHEMIST);
	}
	
	public static void openFarmerGui(Player player) {
		int levelups = PersistentDataTracker.getPlayerLevelups(player);
		FileConfiguration config = HBP.getPlugin().getConfig();
		
		ItemStack[] filler = {new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE)};
		ItemStack[] leveled = {new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE)};
		ItemMeta fillerMeta[] = new ItemMeta[9];
		ItemMeta leveledMeta[] = new ItemMeta[9];
		
		for(int i = 0; i < 9; i++) {
			fillerMeta[i] = filler[i].getItemMeta();
			fillerMeta[i].setDisplayName(ChatColor.ITALIC + config.getConfigurationSection("Farming Levels").getString("Level" + (i+1)));
			filler[i].setItemMeta(fillerMeta[i]);
			
			leveledMeta[i] = leveled[i].getItemMeta();
			leveledMeta[i].setDisplayName(ChatColor.ITALIC + config.getConfigurationSection("Farming Levels").getString("Level" + (i+1)));
			leveled[i].setItemMeta(leveledMeta[i]);
		}
		
		ItemMeta backMeta = back.getItemMeta();
		ItemMeta emptyMeta = empty.getItemMeta();
		ItemMeta pointMeta = points.getItemMeta();
		ItemMeta farmerMeta = farmer.getItemMeta();
		
		backMeta.setDisplayName(String.format("%sBack to Professions Menu", ChatColor.GRAY));
		emptyMeta.setDisplayName(" ");
		pointMeta.setDisplayName(String.format("%s%s%sYou have %d points to allocate", ChatColor.YELLOW, ChatColor.BOLD, ChatColor.UNDERLINE, levelups)); 
		farmerMeta.setDisplayName(String.format("%s%sFarmer", ChatColor.GREEN, ChatColor.BOLD));
		
		ArrayList<String> farmerArr = new ArrayList<String>();
		
		farmerArr.add("Use an experience point to level up in Farming?");
		
		farmerMeta.setLore(farmerArr);
		
		back.setItemMeta(backMeta);
		empty.setItemMeta(emptyMeta);
		points.setItemMeta(pointMeta);
		farmer.setItemMeta(farmerMeta);
		
		
		ItemStack[] inventory = {
				back, empty, empty, farmer, empty, points, empty, empty, empty,
				filler[0], filler[1], filler[2], filler[3], filler[4], filler[5], filler[6], filler[7], filler[8]
		};
		
		for(int i = 9; i < PersistentDataTracker.getLevels(player, PersistentDataTracker.farmerkey)+9; i++) {
			inventory[i] = leveled[i-9];
		}
		
		GUIFARMER.setContents(inventory);
		
		player.openInventory(GUIFARMER);
	}

	public static void openEnchanterGui(Player player) {
		int levelups = PersistentDataTracker.getPlayerLevelups(player);
		FileConfiguration config = HBP.getPlugin().getConfig();
		
		ItemStack[] filler = {new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_STAINED_GLASS_PANE)};
		ItemStack[] leveled = {new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.LIME_STAINED_GLASS_PANE)};
		ItemMeta fillerMeta[] = new ItemMeta[9];
		ItemMeta leveledMeta[] = new ItemMeta[9];
		
		for(int i = 0; i < 9; i++) {
			fillerMeta[i] = filler[i].getItemMeta();
			fillerMeta[i].setDisplayName(ChatColor.ITALIC + config.getConfigurationSection("Enchanting Levels").getString("Level" + i));
			filler[i].setItemMeta(fillerMeta[i]);
			
			leveledMeta[i] = leveled[i].getItemMeta();
			leveledMeta[i].setDisplayName(ChatColor.ITALIC + config.getConfigurationSection("Enchanting Levels").getString("Level" + i));
			leveled[i].setItemMeta(leveledMeta[i]);
		}
		
		ItemMeta backMeta = back.getItemMeta();
		ItemMeta emptyMeta = empty.getItemMeta();
		ItemMeta pointMeta = points.getItemMeta();
		ItemMeta enchanterMeta = enchanter.getItemMeta();
		
		backMeta.setDisplayName(String.format("%sBack to Professions Menu", ChatColor.GRAY));
		emptyMeta.setDisplayName(" ");
		pointMeta.setDisplayName(String.format("%s%s%sYou have %d points to allocate", ChatColor.YELLOW, ChatColor.BOLD, ChatColor.UNDERLINE, levelups)); 
		enchanterMeta.setDisplayName(String.format("%s%sEnchanter", ChatColor.GOLD, ChatColor.BOLD));
		
		ArrayList<String> enchanterArr = new ArrayList<String>();
		
		enchanterArr.add("Use an experience point to level up in Enchanting?");
		
		enchanterMeta.setLore(enchanterArr);
		
		back.setItemMeta(backMeta);
		empty.setItemMeta(emptyMeta);
		points.setItemMeta(pointMeta);
		enchanter.setItemMeta(enchanterMeta);
		
		
		ItemStack[] inventory = {
				back, empty, empty, enchanter, empty, points, empty, empty, empty,
				filler[0], filler[1], filler[2], filler[3], filler[4], filler[5], filler[6], filler[7], filler[8]
		};
		
		for(int i = 9; i < PersistentDataTracker.getLevels(player, PersistentDataTracker.enchanterkey)+9; i++) {
			inventory[i] = leveled[i-9];
		}
		
		GUIENCHANTER.setContents(inventory);
		
		player.openInventory(GUIENCHANTER);
	}
	
	public static void openProfessionsMenu(Player player) {
		int levelups = PersistentDataTracker.getPlayerLevelups(player);
		int smithLevel = PersistentDataTracker.getLevels(player, PersistentDataTracker.smithkey);
		int fletcherLevel = PersistentDataTracker.getLevels(player, PersistentDataTracker.fletcherkey);
		int alchemistLevel = PersistentDataTracker.getLevels(player, PersistentDataTracker.alchemistkey);
		int farmerLevel = PersistentDataTracker.getLevels(player, PersistentDataTracker.farmerkey);
		int enchanterLevel = PersistentDataTracker.getLevels(player, PersistentDataTracker.enchanterkey);
		
		ItemStack mainSmith = new ItemStack(Material.ANVIL);
		ItemStack mainFletcher = new ItemStack(Material.FEATHER);
		ItemStack mainAlchemist = new ItemStack(Material.LINGERING_POTION);
		ItemStack mainFarmer = new ItemStack(Material.WHEAT);
		ItemStack mainEnchanter = new ItemStack(Material.BOOK);
		
		ItemMeta backMeta = back.getItemMeta();
		ItemMeta emptyMeta = empty.getItemMeta();
		ItemMeta pointMeta = points.getItemMeta();
		ItemMeta smithMeta = mainSmith.getItemMeta();
		ItemMeta fletcherMeta = mainFletcher.getItemMeta();
		ItemMeta alchemistMeta = mainAlchemist.getItemMeta();
		ItemMeta farmerMeta = mainFarmer.getItemMeta();
		ItemMeta enchanterMeta = mainEnchanter.getItemMeta();
		
		backMeta.setDisplayName(String.format("%sBack to Professions Menu", ChatColor.GRAY));
		emptyMeta.setDisplayName(" ");
		pointMeta.setDisplayName(String.format("%s%s%sYou have %d points to allocate", ChatColor.YELLOW, ChatColor.BOLD, ChatColor.UNDERLINE, levelups)); 
		smithMeta.setDisplayName(String.format("%s%sSmith", ChatColor.GOLD, ChatColor.BOLD));
		fletcherMeta.setDisplayName(String.format("%s%sFletcher", ChatColor.LIGHT_PURPLE, ChatColor.BOLD));
		alchemistMeta.setDisplayName(String.format("%s%sAlchemist", ChatColor.DARK_AQUA, ChatColor.BOLD));
		farmerMeta.setDisplayName(String.format("%s%sFarmer", ChatColor.GREEN, ChatColor.BOLD));
		enchanterMeta.setDisplayName(String.format("%s%sEnchanter", ChatColor.DARK_RED, ChatColor.BOLD));
		
		ArrayList<String> smithArr = new ArrayList<String>();
		ArrayList<String> fletcherArr = new ArrayList<String>();
		ArrayList<String> alchemistArr = new ArrayList<String>();
		ArrayList<String> farmerArr = new ArrayList<String>();
		ArrayList<String> enchanterArr = new ArrayList<String>();
		
		if(smithLevel >= 10) {
			smithMeta.addEnchant(Enchantment.MENDING, 1, true);
			smithMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			smithArr.add("You're a Master Smith!");
		}else {
			smithArr.add("Open the Smithing Menu");
		}
		if(fletcherLevel >= 10) {
			fletcherMeta.addEnchant(Enchantment.MENDING, 1, true);
			fletcherMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			fletcherArr.add("You're a Master Fletcher!");
		}else {
			fletcherArr.add("Open the Fletching Menu");
		}
		if(alchemistLevel >= 10) {
			alchemistMeta.addEnchant(Enchantment.MENDING, 1, true);
			alchemistMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			alchemistMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
			alchemistArr.add("You're a Master Alchemist!");
		}else {
			alchemistArr.add("Open the Alchemy Menu");
		}
		if(farmerLevel >= 10) {
			farmerMeta.addEnchant(Enchantment.MENDING, 1, true);
			farmerMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			farmerArr.add("You're a Master Farmer!");
		}else {
			farmerArr.add("Open the Farming Menu");
		}
		if(enchanterLevel >= 10) {
			enchanterMeta.addEnchant(Enchantment.MENDING, 1, true);
			enchanterMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			enchanterArr.add("You're a Master Enchanter!");
		}else {
			enchanterArr.add("Open the Enchanting Menu");
		}
		
		smithMeta.setLore(smithArr);
		fletcherMeta.setLore(fletcherArr);
		alchemistMeta.setLore(alchemistArr);
		farmerMeta.setLore(farmerArr);
		enchanterMeta.setLore(enchanterArr);
		
		back.setItemMeta(backMeta);
		empty.setItemMeta(emptyMeta);
		points.setItemMeta(pointMeta);
		mainSmith.setItemMeta(smithMeta);
		mainFletcher.setItemMeta(fletcherMeta);
		mainAlchemist.setItemMeta(alchemistMeta);
		mainFarmer.setItemMeta(farmerMeta);
		mainEnchanter.setItemMeta(enchanterMeta);
		
		ItemStack[] inventory = {
				empty, empty, empty, empty, points, empty, empty, empty, empty,
				mainSmith, empty, mainFletcher, empty, mainAlchemist, empty, mainFarmer, empty, mainEnchanter,
		};
		
		GUI.setContents(inventory);
		
		player.openInventory(GUI);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			GUICommand.openProfessionsMenu(p);
		}
		return true;
	}
}
