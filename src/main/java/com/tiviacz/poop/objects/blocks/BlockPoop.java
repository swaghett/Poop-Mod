package com.tiviacz.poop.objects.blocks;

import com.tiviacz.poop.init.base.BlockModelBase;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockPoop extends BlockModelBase
{
	public static final AxisAlignedBB POOP_AABB = new AxisAlignedBB(0.3125D, 0D, 0.3125D, 0.6875D, 0.1875D, 0.6875D);
	
	public BlockPoop(String name, Material material) 
	{	
		super(name, material);
		
		setSoundType(SoundType.GROUND);
		setHardness(0F);
		setResistance(0F);
		setHarvestLevel("hand", 0);
	}
    
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) 
    {
		return POOP_AABB;
	}
}