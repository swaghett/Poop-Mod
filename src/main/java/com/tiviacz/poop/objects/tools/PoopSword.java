package com.tiviacz.poop.objects.tools;

import com.tiviacz.poop.Poop;
import com.tiviacz.poop.init.ModItems;
import com.tiviacz.poop.util.IHasModel;

import net.minecraft.item.ItemSword;

public class PoopSword extends ItemSword implements IHasModel
{
	public PoopSword(String name, ToolMaterial material) 
	{
		super(material);
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Poop.POOPTAB);
		
		ModItems.ITEMS.add(this);
	}
		
	@Override
	public void registerModels()
	{
		Poop.proxy.registerItemRenderer(this, 0,"inventory");
	}
}
