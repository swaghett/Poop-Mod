package com.tiviacz.poop.proxy;

import com.tiviacz.poop.Poop;
import com.tiviacz.poop.init.ModSmelteryRecipes;
import com.tiviacz.poop.tileentity.TileEntityPaperRoll;
import com.tiviacz.poop.util.handlers.SoundsHandler;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy 
{
	public void preInitRegistries(FMLPreInitializationEvent event)
	{
		
	}
	
	public void initRegistries(FMLInitializationEvent event)
	{
		SoundsHandler.registerSounds();
		ModSmelteryRecipes.init();
		GameRegistry.registerTileEntity(TileEntityPaperRoll.class, new ResourceLocation(Poop.MODID + ":TileEntityPaperRoll"));
	}
	
	public void postInitRegistries(FMLPostInitializationEvent event)
	{
		
	}
	
	public void registerItemRenderer(Item item, int meta, String id) 
	{
	
	}
}