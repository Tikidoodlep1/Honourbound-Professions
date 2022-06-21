package com.tiki.honourboundprofessions.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.FurnaceExtractEvent;
import com.tiki.honourboundprofessions.files.JSONCrafting;

public class CraftEvent implements Listener {

	@EventHandler
	public void onCraft(CraftItemEvent e) {
		int count = e.getRecipe().getResult().getAmount();
		Material result = e.getRecipe().getResult().getType();
		if(JSONCrafting.craftingMap.get(result) != null) {
			int levels = JSONCrafting.craftingMap.get(result);
			Player p = (Player)e.getWhoClicked();
			p.performCommand("professions-levelups give " + p.getPlayerListName() + " " + (count*levels));
		}
	}
	
	@EventHandler
	public void onSmelt(FurnaceExtractEvent e) {
		int count = e.getItemAmount();
		Material result = e.getItemType();
		if(JSONCrafting.smeltingMap.get(result) != null) {
			int levels = JSONCrafting.smeltingMap.get(result);
			Player p = (Player)e.getPlayer();
			p.performCommand("professions-levelups give " + p.getPlayerListName() + " " + (count*levels));
		}
	}
}
