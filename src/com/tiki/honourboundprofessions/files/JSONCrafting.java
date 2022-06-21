package com.tiki.honourboundprofessions.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Material;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class JSONCrafting {
	
	public static final Map<Material, Integer> craftingMap = new HashMap<Material, Integer>();
	public static final Map<Material, Integer> smeltingMap = new HashMap<Material, Integer>();

	private static final Path pluginFolder = Paths.get("");
	public static final String craftingPath = pluginFolder.toAbsolutePath().toString() + "/plugins/Honourbound-Professions/crafting.json";
	public static final String smeltingPath = pluginFolder.toAbsolutePath().toString() + "/plugins/Honourbound-Professions/smelting.json";
	
	public static Map<Material, Integer> readCraftingJson() {
		Map<Material, Integer> map = new HashMap<Material, Integer>();
		File file = new File(craftingPath);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			JsonReader r = new JsonReader(new BufferedReader(new FileReader(craftingPath)));
			if(r.peek() == null) {
				return map;
			}
			
			r.beginArray();
			while(r.hasNext()) {
				r.beginObject();
				String material = r.nextName();
				int levels = r.nextInt();
				map.put(Material.getMaterial(material), levels);
				r.endObject();
			}
			r.endArray();
			r.close();
		}catch (IOException e) {
			System.out.println("[WARN]: There has been an issue reading the crafting JSON. This issue is not severe, but the crafting list will NOT work until fixed. This can also be caused by an empty list, in which case ignore this message.");
			return map;
		}
		return map;
	}
	
	public static Map<Material, Integer> readSmeltingJson() {
		Map<Material, Integer> map = new HashMap<Material, Integer>();
		File file = new File(smeltingPath);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			JsonReader r = new JsonReader(new BufferedReader(new FileReader(smeltingPath)));
			
			r.beginArray();
			while(r.hasNext()) {
				r.beginObject();
				String material = r.nextName();
				int levels = r.nextInt();
				map.put(Material.getMaterial(material), levels);
				r.endObject();
			}
			r.endArray();
			r.close();
		}catch (IOException e) {
			System.out.println("[WARN]: There has been an issue reading the smelting JSON. This issue is not severe, but the smelting list will NOT work until fixed. This can also be caused by an empty list, in which case ignore this message.");
			return map;
		}
		return map;
	}
	
	public static void writeJson(Map<Material, Integer> recipes, String filePath) {
		JsonObject json = new JsonObject();
		for(Entry<Material, Integer> e : recipes.entrySet()) {
			json.addProperty(e.getKey().toString(), e.getValue());
		}
		try {
			JsonWriter w = new JsonWriter(new BufferedWriter(new FileWriter(filePath)));
			w.setIndent("    ");
			w.beginArray();
			json.entrySet().forEach((e) -> {
				try {
					w.beginObject();
					
					w.name(e.getKey()).value(e.getValue().getAsNumber());
					
					w.endObject();
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});
			w.endArray();
			w.close();
		}catch (IOException e2) {
			e2.printStackTrace();
		}
	}
}
