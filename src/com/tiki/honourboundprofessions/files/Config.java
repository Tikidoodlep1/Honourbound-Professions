package com.tiki.honourboundprofessions.files;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import com.tiki.honourboundprofessions.HBP;

public class Config {

	private static FileConfiguration config;
	
	public static void initConfig() {
		config = HBP.getPlugin().getConfig();
		
		ConfigurationSection smithing = config.createSection("Smithing Levels");
		smithing.addDefault("Level1", "Level 1/9");
		smithing.addDefault("Level2", "Level 2/9");
		smithing.addDefault("Level3", "Level 3/9");
		smithing.addDefault("Level4", "Level 4/9");
		smithing.addDefault("Level5", "Level 5/9");
		smithing.addDefault("Level6", "Level 6/9");
		smithing.addDefault("Level7", "Level 7/9");
		smithing.addDefault("Level8", "Level 8/9");
		smithing.addDefault("Level9", "Level 9/9");
		
		ConfigurationSection fletching = config.createSection("Fletching Levels");
		fletching.addDefault("Level1", "Level 1/9");
		fletching.addDefault("Level2", "Level 2/9");
		fletching.addDefault("Level3", "Level 3/9");
		fletching.addDefault("Level4", "Level 4/9");
		fletching.addDefault("Level5", "Level 5/9");
		fletching.addDefault("Level6", "Level 6/9");
		fletching.addDefault("Level7", "Level 7/9");
		fletching.addDefault("Level8", "Level 8/9");
		fletching.addDefault("Level9", "Level 9/9");
		
		ConfigurationSection alchemy = config.createSection("Alchemy Levels");
		alchemy.addDefault("Level1", "Level 1/9");
		alchemy.addDefault("Level2", "Level 2/9");
		alchemy.addDefault("Level3", "Level 3/9");
		alchemy.addDefault("Level4", "Level 4/9");
		alchemy.addDefault("Level5", "Level 5/9");
		alchemy.addDefault("Level6", "Level 6/9");
		alchemy.addDefault("Level7", "Level 7/9");
		alchemy.addDefault("Level8", "Level 8/9");
		alchemy.addDefault("Level9", "Level 9/9");
		
		ConfigurationSection farming = config.createSection("Farming Levels");
		farming.addDefault("Level1", "Level 1/9");
		farming.addDefault("Level2", "Level 2/9");
		farming.addDefault("Level3", "Level 3/9");
		farming.addDefault("Level4", "Level 4/9");
		farming.addDefault("Level5", "Level 5/9");
		farming.addDefault("Level6", "Level 6/9");
		farming.addDefault("Level7", "Level 7/9");
		farming.addDefault("Level8", "Level 8/9");
		farming.addDefault("Level9", "Level 9/9");
		
		ConfigurationSection enchanting = config.createSection("Enchanting Levels");
		enchanting.addDefault("Level1", "Level 1/9");
		enchanting.addDefault("Level2", "Level 2/9");
		enchanting.addDefault("Level3", "Level 3/9");
		enchanting.addDefault("Level4", "Level 4/9");
		enchanting.addDefault("Level5", "Level 5/9");
		enchanting.addDefault("Level6", "Level 6/9");
		enchanting.addDefault("Level7", "Level 7/9");
		enchanting.addDefault("Level8", "Level 8/9");
		enchanting.addDefault("Level9", "Level 9/9");
		
		config.options().copyDefaults(true);
		HBP.getPlugin().saveConfig();
	}
}
