package com.tiviacz.poop.objects.tools;

import java.util.Set;

import com.google.common.collect.Sets;
import com.tiviacz.poop.Poop;
import com.tiviacz.poop.init.ModItems;
import com.tiviacz.poop.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.util.EnumHelper;

public class PoopWarAxe extends PoopAxe
{
	public PoopWarAxe(String name, ToolMaterial material) 
	{
		super(name, material);
		
		this.attackSpeed = -3.0F;
		this.attackDamage = 9.0F;
	}
}