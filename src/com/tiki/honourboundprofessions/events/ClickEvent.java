package com.tiki.honourboundprofessions.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import net.md_5.bungee.api.ChatColor;

import com.tiki.honourboundprofessions.commands.GUICommand;
import com.tiki.honourboundprofessions.helpers.PersistentDataTracker;

public class ClickEvent implements Listener {
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player p = (Player)e.getWhoClicked();
		if(e.getClickedInventory() == GUICommand.GUISMITH || e.getClickedInventory() == GUICommand.GUIFLETCHER || e.getClickedInventory() == GUICommand.GUIALCHEMIST
				 || e.getClickedInventory() == GUICommand.GUIFARMER || e.getClickedInventory() == GUICommand.GUIENCHANTER) {
						
			int smithLevels = PersistentDataTracker.getLevels(p, PersistentDataTracker.smithkey);
			int fletcherLevels = PersistentDataTracker.getLevels(p, PersistentDataTracker.fletcherkey);
			int alchemistLevels = PersistentDataTracker.getLevels(p, PersistentDataTracker.alchemistkey);
			int farmerLevels = PersistentDataTracker.getLevels(p, PersistentDataTracker.farmerkey);
			int enchanterLevels = PersistentDataTracker.getLevels(p, PersistentDataTracker.enchanterkey);
			int levelups = PersistentDataTracker.getPlayerLevelups(p);
			int maxedStats = 0;
			
			if(smithLevels >= 10) {
				maxedStats++;
			}
			if(fletcherLevels >= 10) {
				maxedStats++;
			}
			if(alchemistLevels >= 10) {
				maxedStats++;
			}
			if(farmerLevels >= 10) {
				maxedStats++;
			}
			if(enchanterLevels >= 10) {
				maxedStats++;
			}
			
			switch(e.getCurrentItem().getType()) {
			case ANVIL:
				if(levelups > 0) {
					if(maxedStats >= 2 && smithLevels == 9) {
						p.sendMessage(String.format("%s%sYou already have 2 stats mastered!", ChatColor.RED, ChatColor.UNDERLINE));
						break;
					}
					p.closeInventory();
					++smithLevels;
					p.sendMessage(ChatColor.GOLD + "You Reached Level " + smithLevels + " Smithing!");
					PersistentDataTracker.modifyLevels(p, PersistentDataTracker.smithkey, 1, true);
					PersistentDataTracker.modifyPlayerLevelups(p, 1, false);
					GUICommand.openProfessionsMenu(p);
				}else {
					p.sendMessage(String.format("%s%sYou don't have enough levelups!", ChatColor.RED, ChatColor.UNDERLINE));
				}
				break;
			case FEATHER:
				if(levelups > 0) {
					if(maxedStats >= 2 && fletcherLevels == 9) {
						p.sendMessage(String.format("%s%sYou already have 2 stats mastered!", ChatColor.RED, ChatColor.UNDERLINE));
						break;
					}
					p.closeInventory();
					++fletcherLevels;
					p.sendMessage(ChatColor.LIGHT_PURPLE + "You Reached Level " + fletcherLevels + " Fletching!");
					PersistentDataTracker.modifyLevels(p, PersistentDataTracker.fletcherkey, 1, true);
					PersistentDataTracker.modifyPlayerLevelups(p, 1, false);
					GUICommand.openProfessionsMenu(p);
				}else {
					p.sendMessage(String.format("%s%sYou don't have enough levelups!", ChatColor.RED, ChatColor.UNDERLINE));
				}
				break;
			case LINGERING_POTION:
				if(levelups > 0) {
					if(maxedStats >= 2 && alchemistLevels == 9) {
						p.sendMessage(String.format("%s%sYou already have 2 stats mastered!", ChatColor.RED, ChatColor.UNDERLINE));
						break;
					}
					p.closeInventory();
					++alchemistLevels;
					p.sendMessage(ChatColor.DARK_AQUA + "You Reached Level " + alchemistLevels + " Alchemy!");
					PersistentDataTracker.modifyLevels(p, PersistentDataTracker.alchemistkey, 1, true);
					PersistentDataTracker.modifyPlayerLevelups(p, 1, false);
					GUICommand.openProfessionsMenu(p);
				}else {
					p.sendMessage(String.format("%s%sYou don't have enough levelups!", ChatColor.RED, ChatColor.UNDERLINE));
				}
				break;
			case WHEAT:
				if(levelups > 0) {
					if(maxedStats >= 2 && farmerLevels == 9) {
						p.sendMessage(String.format("%s%sYou already have 2 stats mastered!", ChatColor.RED, ChatColor.UNDERLINE));
						break;
					}
					p.closeInventory();
					++farmerLevels;
					p.sendMessage(ChatColor.GREEN + "You Reached Level " + farmerLevels + " Farming!");
					PersistentDataTracker.modifyLevels(p, PersistentDataTracker.farmerkey, 1, true);
					PersistentDataTracker.modifyPlayerLevelups(p, 1, false);
					GUICommand.openProfessionsMenu(p);
				}else {
					p.sendMessage(String.format("%s%sYou don't have enough levelups!", ChatColor.RED, ChatColor.UNDERLINE));
				}
				break;
			case BOOK:
				if(levelups > 0) {
					if(maxedStats >= 2 && enchanterLevels == 9) {
						p.sendMessage(String.format("%s%sYou already have 2 stats mastered!", ChatColor.RED, ChatColor.UNDERLINE));
						break;
					}
					p.closeInventory();
					++enchanterLevels;
					p.sendMessage(ChatColor.GOLD + "You Reached Level " + enchanterLevels + " Enchanting!");
					PersistentDataTracker.modifyLevels(p, PersistentDataTracker.enchanterkey, 1, true);
					PersistentDataTracker.modifyPlayerLevelups(p, 1, false);
					GUICommand.openProfessionsMenu(p);
				}else {
					p.sendMessage(String.format("%s%sYou don't have enough levelups!", ChatColor.RED, ChatColor.UNDERLINE));
				}
				break;
			case ARROW:
				p.closeInventory();
				GUICommand.openProfessionsMenu(p);
				break;
			default:
				break;
			}
			e.setCancelled(true);
		}else if(e.getClickedInventory() == GUICommand.GUI) {
			int smithLevels = PersistentDataTracker.getLevels(p, PersistentDataTracker.smithkey);
			int fletcherLevels = PersistentDataTracker.getLevels(p, PersistentDataTracker.fletcherkey);
			int alchemistLevels = PersistentDataTracker.getLevels(p, PersistentDataTracker.alchemistkey);
			int farmerLevels = PersistentDataTracker.getLevels(p, PersistentDataTracker.farmerkey);
			int enchanterLevels = PersistentDataTracker.getLevels(p, PersistentDataTracker.enchanterkey);
			
			switch(e.getCurrentItem().getType()) {
				case ANVIL:
					if(smithLevels < 10) {
						p.closeInventory();
						GUICommand.openSmithGui(p);
					}
					break;
				case FEATHER:
					if(fletcherLevels < 10) {
						p.closeInventory();
						GUICommand.openFletcherGui(p);
					}
					break;
				case LINGERING_POTION:
					if(alchemistLevels < 10) {
						p.closeInventory();
						GUICommand.openAlchemistGui(p);
					}
					break;
				case WHEAT:
					if(farmerLevels < 10) {
						p.closeInventory();
						GUICommand.openFarmerGui(p);
					}
					break;
				case BOOK:
					if(enchanterLevels < 10) {
						p.closeInventory();
						GUICommand.openEnchanterGui(p);
					}
					break;
				default:
					break;
			}
			e.setCancelled(true);
		}
	}
}
