package com.tiviacz.poop.objects.entities;

import java.util.List;

import com.tiviacz.poop.objects.blocks.BlockToilet;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SeatToilet extends Entity
{
	public SeatToilet(World worldIn) 
	{
		super(worldIn);

		setSize(0F, 0F);
	}
	
	public SeatToilet(World worldIn, BlockPos pos)
	{
		this(worldIn);
		
		setPosition(pos.getX() + 0.5, pos.getY() + 0.3, pos.getZ() + 0.5);
	}
	
	@Override
    public void onUpdate() 
    {
        super.onUpdate();

        BlockPos pos = getPosition();
        
        if(!(getEntityWorld().getBlockState(pos).getBlock() instanceof BlockToilet)) 
        {
            setDead();
            return;
        }

        List<Entity> passengers = getPassengers();
        
        if(passengers.isEmpty())
        {
        	setDead();
        }
        
        for(Entity e : passengers)
        {
        	if(e.isSneaking())
        	{
        		setDead();
        	}
        }
    }

	@Override
	protected void entityInit() {}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {}
}
