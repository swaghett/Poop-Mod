package com.tiviacz.poop.objects.blocks;

import java.util.Random;

import com.tiviacz.poop.init.ModBlocks;
import com.tiviacz.poop.init.ModItems;
import com.tiviacz.poop.init.base.BlockModelBase;
import com.tiviacz.poop.tileentity.TileEntityPaperRoll;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPaperRoll extends BlockModelBase implements ITileEntityProvider
{
	public static final AxisAlignedBB PAPER_ROLL_AABB = new AxisAlignedBB(0.3125D, 0D, 0.3125D, 0.6875D, 0.375D, 0.6875D);

	public BlockPaperRoll(String name, Material material)
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
		return PAPER_ROLL_AABB;
    }

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) 
	{
		return new TileEntityPaperRoll();
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
		TileEntityPaperRoll te = (TileEntityPaperRoll)worldIn.getTileEntity(pos);
		te.paperCount = 8;
    }
	
	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Items.AIR;
    }
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
		TileEntityPaperRoll te = (TileEntityPaperRoll)worldIn.getTileEntity(pos);
		int x = te.paperCount;
		
		if(!worldIn.isRemote)
		{
			if(x != 8 && x != 0)
			{
				worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.TOILET_PAPER, x)));
				worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModBlocks.EMPTY_ROLL)));
			}
			if(x == 8)
			{
				worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModBlocks.PAPER_ROLL)));
			}
		}
    }
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		if(!worldIn.isRemote)
		{
			TileEntityPaperRoll te = (TileEntityPaperRoll)worldIn.getTileEntity(pos);
			ItemStack helditem = playerIn.getHeldItem(hand);
			
			if(helditem.getItem() == ModItems.TOILET_PAPER)
			{
				if(!te.isFull())
				{
					te.addPaper();
					helditem.shrink(1);
				}
			}
			
			if(helditem.getItem() != ModItems.TOILET_PAPER && te.paperCount >= 0)
			{
				te.removePaper();
				playerIn.addItemStackToInventory(new ItemStack(ModItems.TOILET_PAPER));
			}
		}
		return true;
    }
}