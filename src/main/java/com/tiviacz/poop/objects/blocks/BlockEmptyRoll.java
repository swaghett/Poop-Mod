package com.tiviacz.poop.objects.blocks;

import com.tiviacz.poop.init.ModBlocks;
import com.tiviacz.poop.init.ModItems;
import com.tiviacz.poop.init.base.BlockModelBase;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockEmptyRoll extends BlockModelBase
{
	public static final AxisAlignedBB EMPTY_ROLL_AABB = new AxisAlignedBB(0.375D, 0D, 0.375D, 0.625D, 0.375D, 0.625D);
	
	public BlockEmptyRoll(String name, Material material) 
	{	
		super(name, material);
		
		setSoundType(SoundType.CLOTH);
		setHardness(0F);
		setResistance(0F);
		setHarvestLevel("hand", 0);
	}
    
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
		return EMPTY_ROLL_AABB;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		if(!worldIn.isRemote)
		{
			ItemStack helditem = playerIn.getHeldItem(hand);
			
			if(helditem.getItem() == ModItems.TOILET_PAPER)
			{
				worldIn.setBlockState(pos, ModBlocks.PAPER_ROLL.getDefaultState());
				helditem.shrink(1);
			}
		}
		return true;
    }
}