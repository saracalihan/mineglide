package com.saracalihan.mineglide.item;

import com.saracalihan.mineglide.Mineglide;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup PARAGLIDE_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Mineglide.MOD_ID, "paraglide_items"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModItems.PARAGLIDER))
                    .displayName(Text.translatable("itemgroup.mineglide.paraglide_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.PARAGLIDER);
                    })
                    .build());

    public static void registerModItemGroups() {
        Mineglide.LOGGER.info("Registering mod groups for " + Mineglide.MOD_ID);
        Mineglide.LOGGER.info("Mod groups registered for " + Mineglide.MOD_ID);

    }
}
