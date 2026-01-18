package com.saracalihan.mineglide.item;

import com.saracalihan.mineglide.Mineglide;
import com.saracalihan.mineglide.utility.ItemUtility;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item PARAGLIDER = registerItem(ParagliderItem.ITEM_ID,
            new ParagliderItem(ItemUtility.createItemSetting(RegistryKeys.ITEM, ParagliderItem.ITEM_ID)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Mineglide.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Mineglide.LOGGER.info("Registering mod items for " + Mineglide.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(PARAGLIDER);
        });
        Mineglide.LOGGER.info("Mod items registered for " + Mineglide.MOD_ID);

    }
}
