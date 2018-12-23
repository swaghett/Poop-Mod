package com.tiviacz.poop.objects.tools;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import com.google.common.collect.Multimap;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class PoopWarHammer extends PoopKatana
{
	public PoopWarHammer(String name, ToolMaterial material) 
	{
		super(name, material);
	}
		
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) 
	{
		final Multimap<String, AttributeModifier> modifiers = super.getAttributeModifiers(slot, stack);
	 
		if(slot == EntityEquipmentSlot.MAINHAND) 
	    {
			replaceModifier(modifiers, SharedMonsterAttributes.ATTACK_DAMAGE, ATTACK_DAMAGE_MODIFIER, 1);
			replaceModifier(modifiers, SharedMonsterAttributes.ATTACK_SPEED, ATTACK_SPEED_MODIFIER, 1.457);  // 1+0.04 Here = 1.5-0.1 In Game
	    }
	    return modifiers;
	}
}