package com.tiviacz.poop.init.base;

import com.tiviacz.poop.Poop;
import com.tiviacz.poop.init.ModItems;
import com.tiviacz.poop.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class ItemBase extends Item implements IHasModel 
{
	public ItemBase(String name)
	{
		setTranslationKey(name);
		setRegistryName(name);
		setCreativeTab(Poop.POOPTAB);
		
		ModItems.ITEMS.add(this);
	}
}