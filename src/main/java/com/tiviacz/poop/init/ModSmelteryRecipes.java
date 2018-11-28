package com.tiviacz.poop.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModSmelteryRecipes 
{
	 public static void init()
	 {
		GameRegistry.addSmelting(ModItems.POOP_RAW_INGOT, new ItemStack(ModItems.POOP_INGOT), 1.0F);
		GameRegistry.addSmelting(ModBlocks.POOP_BLOCK, new ItemStack(ModBlocks.POOP_HARDENED), 1.0F);
		GameRegistry.addSmelting(ModItems.RAW_EMPTY_ROLL, new ItemStack(ModBlocks.EMPTY_ROLL), 1.0F);
	 }
}
