package com.saracalihan.mineglide.block;

import com.saracalihan.mineglide.Mineglide;
import com.saracalihan.mineglide.utility.ItemUtility;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final String ARIF_BLOCK_ID = "arif_block";

    public static final Block ARIF_BLOCK = registerBlock(ARIF_BLOCK_ID,
            new Block(AbstractBlock.Settings.create().strength(4f).sounds(BlockSoundGroup.AMETHYST_BLOCK).registryKey(
                    RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Mineglide.MOD_ID, ARIF_BLOCK_ID)))));

    public static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Mineglide.MOD_ID, name), block);
    }

    public static void registerBlockItem(String name, Block block) {
        Registry.register(
                Registries.ITEM,
                Identifier.of(Mineglide.MOD_ID, name),
                new BlockItem(block, ItemUtility.createItemSetting(RegistryKeys.ITEM, ARIF_BLOCK_ID)));
    }

    public static void registerModBlocks() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ARIF_BLOCK);
        });
    }
}
