package com.saracalihan.mineglide;

import com.saracalihan.mineglide.block.ModBlocks;
import com.saracalihan.mineglide.item.ModItemGroups;
import com.saracalihan.mineglide.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mineglide implements ModInitializer {
	public static final String MOD_ID = "mineglide";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info(MOD_ID + " initializing...");
		ModItems.registerModItems();
		ModItemGroups.registerModItemGroups();
		ModBlocks.registerModBlocks();
		LOGGER.info(MOD_ID + " initialized.");
	}
}