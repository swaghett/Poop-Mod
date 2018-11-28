package com.tiviacz.poop.objects.blocks;

import com.tiviacz.poop.init.base.BlockModelBase;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockPoopTrophy extends BlockModelBase
{
	public static final AxisAlignedBB POOP_TROPHY_AABB = new AxisAlignedBB(0.25D, 0D, 0.25D, 0.75D, 0.5625D, 0.75D);
		
	public BlockPoopTrophy(String name, Material material)
	{
		super(name, material);
		
		setSoundType(SoundType.METAL);
		setHardness(0.0F);
		setResistance(0.0F);
		setHarvestLevel("hand", 0);
		setLightLevel(1.0F);
	}
		
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return POOP_TROPHY_AABB;	
	}
}