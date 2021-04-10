package com.tiviacz.poop.objects.tools;

import com.tiviacz.poop.Poop;
import com.tiviacz.poop.init.ModItems;
import com.tiviacz.poop.util.IHasModel;

import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.Item.ToolMaterial;

public class PoopPickaxe extends ItemPickaxe implements IHasModel 
{
	public PoopPickaxe(String name, ToolMaterial material) 
	{
		super(material);
		
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(Poop.POOPTAB);
		
		ModItems.ITEMS.add(this);
	}
}