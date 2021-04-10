package com.tiviacz.poop.objects.tools;

import com.tiviacz.poop.Poop;
import com.tiviacz.poop.init.ModItems;
import com.tiviacz.poop.util.IHasModel;

import net.minecraft.item.ItemHoe;

public class PoopHoe extends ItemHoe implements IHasModel
{
	public PoopHoe(String name, ToolMaterial material) 
	{
		super(material);
		
		setTranslationKey(name);
		setRegistryName(name);
		setCreativeTab(Poop.POOPTAB);
		
		ModItems.ITEMS.add(this);
	}
}