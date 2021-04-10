package com.tiviacz.poop.objects.blocks;

import java.util.Random;

import com.tiviacz.poop.init.ModBlocks;
import com.tiviacz.poop.init.ModItems;
import com.tiviacz.poop.init.base.BlockBase;
import com.tiviacz.poop.tileentity.TileEntityPaperRoll;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockHolderEmptyRoll extends Block
{
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	
	public static final AxisAlignedBB HOLDER_NORTH_AABB = new AxisAlignedBB(0.1875D, 0.53125D, 0.5625D, 0.8125D, 0.90625D, 1D);
    public static final AxisAlignedBB HOLDER_SOUTH_AABB = new AxisAlignedBB(0.1875D, 0.53125D, 0D, 0.8125D, 0.90625D, 0.4375D);
    public static final AxisAlignedBB HOLDER_WEST_AABB = new AxisAlignedBB(0.5625D, 0.53125D, 0.1875D, 1D, 0.90625D, 0.8125D);
    public static final AxisAlignedBB HOLDER_EAST_AABB = new AxisAlignedBB(0D, 0.53125D, 0.1875D, 0.4375, 0.90625D, 0.8125D);
	
	public BlockHolderEmptyRoll(String name, Material material) 
	{
		super(material);
		
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		
		setSoundType(SoundType.METAL);
		setHardness(5F);
		setResistance(30F);
		setHarvestLevel("pickaxe", 2);
		
		ModBlocks.BLOCKS.add(this);
	}
	
	@Override
    public boolean isOpaqueCube(IBlockState state)
    {
    	return false;
    }
    
    @Override
    public boolean isFullCube(IBlockState state)
    {
    	return false;
    }
    
    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }
    
    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(ModBlocks.HOLDER);
    }
    
    @Override
    public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state)
    {
		if(!worldIn.isRemote)
		{
			worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModBlocks.EMPTY_ROLL)));
    		worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModBlocks.HOLDER)));
		}
    }
    
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(!worldIn.isRemote)
        {
        	ItemStack helditem = playerIn.getHeldItem(hand);
        	
        	if(helditem.getItem() != ModItems.TOILET_PAPER)
        	{
        		worldIn.setBlockState(pos, ModBlocks.HOLDER.getDefaultState().withProperty(FACING, state.getValue(FACING)));
        		playerIn.addItemStackToInventory(new ItemStack(ModBlocks.EMPTY_ROLL));
        	}
        	if(helditem.getItem() == ModItems.TOILET_PAPER)
        	{
        		worldIn.setBlockState(pos, ModBlocks.HOLDER_PAPER_ROLL.getDefaultState().withProperty(FACING, state.getValue(FACING)));
        		helditem.shrink(1);
        	}
        }
		return true;
    }
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        switch (state.getValue(FACING))
        {
            case EAST:
                return HOLDER_EAST_AABB;
            case WEST:
                return HOLDER_WEST_AABB;
            case SOUTH:
                return HOLDER_SOUTH_AABB;
            case NORTH:
                return HOLDER_NORTH_AABB;
		default:
			return HOLDER_NORTH_AABB;
        }
    }
	
	@Override
	public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side)
    {
        if(this.canAttachTo(worldIn, pos.west(), side))
        {
            return true;
        }
        else if(this.canAttachTo(worldIn, pos.east(), side))
        {
            return true;
        }
        else if(this.canAttachTo(worldIn, pos.north(), side))
        {
            return true;
        }
        else
        {
            return this.canAttachTo(worldIn, pos.south(), side);
        }
    }

    private boolean canAttachTo(World worldIn, BlockPos pos, EnumFacing facing)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        boolean flag = isExceptBlockForAttachWithPiston(iblockstate.getBlock());
        return !flag && iblockstate.getBlockFaceShape(worldIn, pos, facing) == BlockFaceShape.SOLID && !iblockstate.canProvidePower();
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        if(facing.getAxis().isHorizontal() && this.canAttachTo(worldIn, pos.offset(facing.getOpposite()), facing))
        {
            return this.getDefaultState().withProperty(FACING, facing);
        }
        else
        {
            for(EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
            {
                if(this.canAttachTo(worldIn, pos.offset(enumfacing.getOpposite()), enumfacing))
                {
                    return this.getDefaultState().withProperty(FACING, enumfacing);
                }
            }

            return this.getDefaultState();
        }
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);

        if(!this.canAttachTo(worldIn, pos.offset(enumfacing.getOpposite()), enumfacing))
        {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockToAir(pos);
        }

        super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
    }
	
	@Override
	public IBlockState getStateFromMeta(int meta) 
	{
		EnumFacing facing = EnumFacing.getFront(meta);

		if(facing.getAxis() == EnumFacing.Axis.Y) 
		{
		    facing = EnumFacing.NORTH;
		}
		return getDefaultState().withProperty(FACING, facing);
	}

	@Override
	public int getMetaFromState(IBlockState state) 
	{
        return state.getValue(FACING).getIndex();
	}
		    
	@Override
	protected BlockStateContainer createBlockState() 
	{
		return new BlockStateContainer(this, new IProperty[]{FACING});
	}
}