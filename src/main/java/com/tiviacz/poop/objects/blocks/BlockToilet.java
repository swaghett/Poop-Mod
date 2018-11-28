package com.tiviacz.poop.objects.blocks;

import java.util.List;

import com.tiviacz.poop.init.ModBlocks;
import com.tiviacz.poop.init.ModItems;
import com.tiviacz.poop.init.base.BlockBase;
import com.tiviacz.poop.objects.entities.SeatToilet;
import com.tiviacz.poop.util.handlers.SoundsHandler;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLever;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.Sound;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockToilet extends BlockBase
{
	public static final PropertyBool OPEN = PropertyBool.create("state");
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final AxisAlignedBB TOILET_AABB = new AxisAlignedBB(0.1875D, 0, 0.1875D, 0.8125D, 0.625D, 0.8125D);
	
	public BlockToilet(String name, Material material)
	{
		super(name, material);
		
		setSoundType(SoundType.METAL);
		setHardness(5.0F);
		setResistance(30.0F);
		setHarvestLevel("pickaxe", 2);
		this.setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(OPEN, false)); 
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
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return TOILET_AABB;
	}
	
	@Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, getFacing(meta)).withProperty(OPEN, (meta & 4) != 0);
    }

	@Override
    public int getMetaFromState(IBlockState state)
    {
    	int i = 0;
        i = i | getMetaForFacing(state.getValue(FACING));
        
        if(state.getValue(OPEN))
        {
        	i |= 4;
        }
        
        return i;
    }
		    
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[]{FACING, OPEN});
	}
	
	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) 
	{
		return getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()).withProperty(OPEN, false);
	}
	
	private void playSound(EntityPlayer playerIn, World worldIn, BlockPos pos, boolean open)
    {
        if(open)
        {
        	worldIn.playSound(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, SoundEvents.BLOCK_IRON_TRAPDOOR_OPEN, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
        }
        else
        {
        	worldIn.playSound(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, SoundEvents.BLOCK_IRON_TRAPDOOR_CLOSE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
        }
    }
		    
	@Override		   
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(!worldIn.isRemote) 
		{
			ItemStack helditem = playerIn.getHeldItem(hand);
			float x = pos.getX();
		    float y = pos.getY();
		    float z = pos.getZ();
		    
		    if(helditem.isEmpty() && playerIn.isSneaking())
		    {
		    	state = state.cycleProperty(OPEN);
	            worldIn.setBlockState(pos, state, 2);
				playSound(playerIn, worldIn, pos, state.getValue(OPEN));
		    }
		    
		    if(state.getValue(OPEN) == false)
		    {
		    	if(helditem.getItem() == ModItems.TOILET_PAPER)
			    {
			    	InventoryHelper.spawnItemStack(worldIn, x, y, z, new ItemStack(ModBlocks.POOP));
			    	helditem.shrink(1);	                          
			    	worldIn.playSound(x + 0.5D, y, z + 0.5D, SoundsHandler.TOILET_FLUSHSOUND, SoundCategory.BLOCKS, 1.0F, 1.0F, false);   
			    }
			    
			    if(helditem.getItem() == Item.getItemFromBlock(ModBlocks.PAPER_ROLL))
			    {
			    	InventoryHelper.spawnItemStack(worldIn, x, y, z, new ItemStack(ModBlocks.POOP, 9));            
			    	helditem.shrink(1);
			    	worldIn.playSound(x + 0.5D, y, z + 0.5D, SoundsHandler.TOILET_FLUSHSOUND, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
			    }
		    }
		}
		return true;
	}
	
	private static EnumFacing getFacing(int meta)
    {
        switch(meta & 3)
        {
            case 0:
                return EnumFacing.NORTH;
            case 1:
                return EnumFacing.SOUTH;
            case 2:
                return EnumFacing.WEST;
            case 3:
            default:
                return EnumFacing.EAST;
        }
    }

    private static int getMetaForFacing(EnumFacing facing)
    {
        switch(facing)
        {
            case NORTH:
                return 0;
            case SOUTH:
                return 1;
            case WEST:
                return 2;
            case EAST:
            default:
                return 3;
        }
    }
}