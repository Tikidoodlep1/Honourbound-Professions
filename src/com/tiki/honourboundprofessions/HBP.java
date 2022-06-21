package com.tiki.honourboundprofessions;

import org.bukkit.plugin.java.JavaPlugin;

import com.tiki.honourboundprofessions.commands.ClassLevels;
import com.tiki.honourboundprofessions.commands.GUICommand;
import com.tiki.honourboundprofessions.commands.GainPointsOnCrafting;
import com.tiki.honourboundprofessions.commands.Levelups;
import com.tiki.honourboundprofessions.events.ClickEvent;
import com.tiki.honourboundprofessions.events.CraftEvent;
import com.tiki.honourboundprofessions.files.Config;
import com.tiki.honourboundprofessions.files.JSONCrafting;

public class HBP extends JavaPlugin {
	
	private static HBP plugin;

	@Override
	public void onEnable() {
		plugin = this;
		if(this.getConfig().getConfigurationSection("Smithing Levels").getString("Level1") == null) {
			Config.initConfig();
		}
		
		JSONCrafting.craftingMap.putAll(JSONCrafting.readCraftingJson());
		JSONCrafting.smeltingMap.putAll(JSONCrafting.readSmeltingJson());
		
		getCommand("professions").setExecutor(new GUICommand());
		getCommand("professions-levelups").setExecutor(new Levelups());
		getCommand("professions-levels").setExecutor(new ClassLevels());
		getCommand("professions-setlevelups").setExecutor(new GainPointsOnCrafting());
		getServer().getPluginManager().registerEvents(new ClickEvent(), this);
		getServer().getPluginManager().registerEvents(new CraftEvent(), this);
	}
	
	@Override
	public void onDisable() {
		
	}

	public static HBP getPlugin() {
		return plugin;
	}
}
