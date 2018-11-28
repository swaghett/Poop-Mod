package com.tiviacz.poop.tileentity;

import com.tiviacz.poop.init.ModBlocks;
import com.tiviacz.poop.objects.blocks.BlockHolder;
import com.tiviacz.poop.objects.blocks.BlockHolderPaperRoll;
import com.tiviacz.poop.objects.blocks.BlockPaperRoll;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class TileEntityPaperRoll extends TileEntity
{
	public int paperCount = 1;
	public int maxPaperCount = 8;
	
	public boolean addPaper()
	{
		if(paperCount < maxPaperCount)
		{
			paperCount++;
			this.markDirty();
			return true;
		}
		return false;
	}
	
	public boolean removePaper()
	{
		if(paperCount > 0)
		{
			IBlockState state = world.getBlockState(pos);
			
			paperCount--;
			this.markDirty();
			if(paperCount == 0 && state.getBlock() == ModBlocks.PAPER_ROLL)
			{
				world.setBlockState(pos, ModBlocks.EMPTY_ROLL.getDefaultState());
				this.markDirty();
			}
			
			if(paperCount == 0 && state.getBlock() == ModBlocks.HOLDER_PAPER_ROLL)
			{
				if(state.getValue(BlockHolderPaperRoll.FACING) == EnumFacing.NORTH)
				{
					world.setBlockState(pos, ModBlocks.HOLDER_EMPTY_ROLL.getDefaultState().withProperty(BlockHolderPaperRoll.FACING, EnumFacing.NORTH));
				}
				
				if(state.getValue(BlockHolderPaperRoll.FACING) == EnumFacing.SOUTH)
				{
					world.setBlockState(pos, ModBlocks.HOLDER_EMPTY_ROLL.getDefaultState().withProperty(BlockHolderPaperRoll.FACING, EnumFacing.SOUTH));
				}
				
				if(state.getValue(BlockHolderPaperRoll.FACING) == EnumFacing.EAST)
				{
					world.setBlockState(pos, ModBlocks.HOLDER_EMPTY_ROLL.getDefaultState().withProperty(BlockHolderPaperRoll.FACING, EnumFacing.EAST));
				}
				
				if(state.getValue(BlockHolderPaperRoll.FACING) == EnumFacing.WEST)
				{
					world.setBlockState(pos, ModBlocks.HOLDER_EMPTY_ROLL.getDefaultState().withProperty(BlockHolderPaperRoll.FACING, EnumFacing.WEST));
				}
				this.markDirty();
			}
			return true;
		}
		return false;
	}
	
	public boolean isFull()
	{
		if(paperCount == maxPaperCount)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setInteger("PaperCount", this.paperCount);
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.paperCount = compound.getInteger("PaperCount");
	}
}
