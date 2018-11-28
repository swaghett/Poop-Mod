package com.tiviacz.poop;

import com.tiviacz.poop.init.ModBlocks;
import com.tiviacz.poop.init.ModSmelteryRecipes;
import com.tiviacz.poop.proxy.CommonProxy;
import com.tiviacz.poop.util.handlers.EventsHandler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Poop.MODID, name = Poop.NAME, version = Poop.VERSION)
public class Poop
{
	public static final String MODID = "poop";
	public static final String NAME = "Poop Mod";
	public static final String VERSION = "1.3.0";
	
	public static CreativeTabs POOPTAB = (new CreativeTabs("pooptab")
	{
		@Override
		public ItemStack getTabIconItem() 
		{
			return new ItemStack(ModBlocks.POOP);
		}
	});
	
	@Instance
	public static Poop instance;
	
	@SidedProxy(clientSide = "com.tiviacz.poop.proxy.ClientProxy", serverSide = "com.tiviacz.poop.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event){EventsHandler.PreInitRegistries();}
	
	@EventHandler
	public static void init(FMLInitializationEvent event){EventsHandler.initRegistries();}
	
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event){EventsHandler.PostInitRegistries();}
}