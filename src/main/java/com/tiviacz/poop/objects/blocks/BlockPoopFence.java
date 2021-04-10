package com.tiviacz.poop.objects.blocks;

import com.tiviacz.poop.Poop;
import com.tiviacz.poop.init.ModBlocks;
import com.tiviacz.poop.init.ModItems;
import com.tiviacz.poop.util.IHasModel;

import net.minecraft.block.BlockFence;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockPoopFence extends BlockFence implements IHasModel
{
	public BlockPoopFence(String name, Material material, MapColor mapColorIn)
	{
		super(material, mapColorIn);
		
		setTranslationKey(name);
		setRegistryName(name);
		setCreativeTab(Poop.POOPTAB);	
		setSoundType(SoundType.STONE);
		setHardness(1.5F);
		setResistance(30.0F);
		setHarvestLevel("pickaxe", 0);
			
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
}