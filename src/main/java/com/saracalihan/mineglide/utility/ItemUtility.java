package com.saracalihan.mineglide.utility;

import com.saracalihan.mineglide.Mineglide;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ItemUtility {
    public static Item.Settings createItemSetting(RegistryKey registry, String name) {
        return new Item.Settings().registryKey(
                RegistryKey.of(registry, Identifier.of(Mineglide.MOD_ID, name)));
    }
}
