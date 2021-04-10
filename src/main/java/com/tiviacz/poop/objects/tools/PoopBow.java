package com.tiviacz.poop.objects.tools;

import javax.annotation.Nullable;

import com.tiviacz.poop.Poop;
import com.tiviacz.poop.init.ModItems;
import com.tiviacz.poop.util.IHasModel;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PoopBow extends ItemBow implements IHasModel
{
	 public PoopBow(String name) 
	 {
		 setMaxDamage(700);
		 this.setUnlocalizedName(name);
		 this.setRegistryName(name);
		 this.setCreativeTab(Poop.POOPTAB);
		 this.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter()
	        {
	            @SideOnly(Side.CLIENT)
	            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
	            {
	                if (entityIn == null)
	                {
	                    return 0.0F;
	                }
	                else
	                {
	                    return entityIn.getActiveItemStack().getItem() != ModItems.POOP_BOW ? 0.0F : (float)(stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 20.0F;
	                }
	            }
	        });
	        		
		 ModItems.ITEMS.add(this);
	}
}