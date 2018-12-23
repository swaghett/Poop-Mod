package com.tiviacz.poop.util.handlers;

import java.util.List;

import com.tiviacz.poop.Poop;
import com.tiviacz.poop.init.ModBlocks;
import com.tiviacz.poop.init.ModItems;
import com.tiviacz.poop.objects.blocks.BlockToilet;
import com.tiviacz.poop.objects.entities.SeatToilet;
import com.tiviacz.poop.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class EventsHandler 
{
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event)
	{
		for(Item item : ModItems.ITEMS)
		{
			if(item instanceof IHasModel)
			{
				Poop.proxy.registerItemRenderer(item, 0,"inventory");
			}
		}
		for(Block block : ModBlocks.BLOCKS)
		{
			if(block instanceof IHasModel)
			{
				Poop.proxy.registerItemRenderer(Item.getItemFromBlock(block), 0, "inventory");
			}
		}
	}
	
	@SubscribeEvent
    public static void onInteractWithBlock(PlayerInteractEvent.RightClickBlock event) 
    {
        EntityPlayer player = event.getEntityPlayer();
        
        if(player.getRidingEntity() != null)
        {
        	return;
        }

        World world = event.getWorld();
        BlockPos pos = event.getPos();

        Vec3d vec = new Vec3d(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        double maxDist = 2;
        
        if((vec.x - player.posX) * (vec.x - player.posX) + (vec.y - player.posY) * (vec.y - player.posY) + (vec.z - player.posZ) * (vec.z - player.posZ) > maxDist * maxDist)
        {
        	return;
        }

        IBlockState state = world.getBlockState(pos);

        ItemStack stack1 = player.getHeldItemMainhand();
        ItemStack stack2 = player.getHeldItemOffhand();
        
        if(!stack1.isEmpty() || !stack2.isEmpty())
        {
        	return;
        }
        
        if(player.isSneaking())
        {
        	return;
        }

        if(state.getBlock() instanceof BlockToilet) 
        {
            List<SeatToilet> seats = world.getEntitiesWithinAABB(SeatToilet.class, new AxisAlignedBB(pos, pos.add(1, 1, 1)));

            if(seats.isEmpty()) 
            {
            	SeatToilet seat = new SeatToilet(world, pos);
                world.spawnEntity(seat);
                event.getEntityPlayer().startRiding(seat);
            }
        }
    }
	
/*	@SubscribeEvent
	public static void onHostileUpdate(LivingUpdateEvent event)
	{
		World world = event.getEntity().world;
		EntityLivingBase entity = event.getEntityLiving();
		
		float x = entity.getPosition().getX();
		float y = entity.getPosition().getY();
		float z = entity.getPosition().getZ();
		
		BlockPos pos = new BlockPos(x, y, z);
		
		if(entity instanceof EntityLivingBase)
		{
			if(entity instanceof EntityPlayer)
			{
				return;
			}
			
			if(entity instanceof EntityMob)
			{
				return;
			}
			
			if(entity instanceof EntityAnimal)
			{
				if(world.isAirBlock(pos) && world.getBlockState(pos.down()).isSideSolid(world, pos, EnumFacing.UP));
				{
					if(world.rand.nextInt(ConfigHandler.poopChance) == 1)
					{
						world.setBlockState(pos, ModBlocks.POOP.getDefaultState(), 3);
						world.playSound(x + 0.5D, y, z + 0.5D, SoundEvents.BLOCK_GRAVEL_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);  
						System.out.println("############ POOP PLACED ############" + "xPos =" + pos.getX() + "zPos =" + pos.getZ());
					}
				}
			}
		}
	} */
}