package com.tiviacz.poop.init.base;

import com.tiviacz.poop.Poop;
import com.tiviacz.poop.init.ModBlocks;
import com.tiviacz.poop.init.ModItems;
import com.tiviacz.poop.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IHasModel
{
	public BlockBase(String name, Material material)
	{
		super(material);
		setTranslationKey(name);
		setRegistryName(name);
		setCreativeTab(Poop.POOPTAB);
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));	
	}
}