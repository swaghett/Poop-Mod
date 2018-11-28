package com.tiviacz.poop.objects.blocks;

import com.tiviacz.poop.init.base.BlockBase;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockPoopBase extends BlockBase
{
	float hardness;
	float resistance;
	int harvestLevel;
	SoundType sound;
	String harvestTool;
	
	public BlockPoopBase(String name, Material material, float hardness, float resistance, String harvestTool, int harvestLevel, SoundType sound) 
	{
		super(name, material);
		
		this.hardness = hardness;
		this.resistance = resistance;
		this.harvestTool = harvestTool;
		this.harvestLevel = harvestLevel;
		this.sound = sound;
		
		setSoundType(sound);
		setHardness(hardness);
		setResistance(resistance);
		setHarvestLevel(harvestTool, harvestLevel);
		
	}
}