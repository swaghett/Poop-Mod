package com.tiviacz.poop.init;

import java.util.ArrayList;
import java.util.List;

import com.tiviacz.poop.Poop;
import com.tiviacz.poop.init.base.ArmorBase;
import com.tiviacz.poop.init.base.FoodBase;
import com.tiviacz.poop.init.base.ItemBase;
import com.tiviacz.poop.objects.tools.PoopAxe;
import com.tiviacz.poop.objects.tools.PoopBow;
import com.tiviacz.poop.objects.tools.PoopHoe;
import com.tiviacz.poop.objects.tools.PoopKatana;
import com.tiviacz.poop.objects.tools.PoopPickaxe;
import com.tiviacz.poop.objects.tools.PoopShovel;
import com.tiviacz.poop.objects.tools.PoopSword;
import com.tiviacz.poop.objects.tools.PoopWarAxe;
import com.tiviacz.poop.objects.tools.PoopWarHammer;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ModItems 
{	
		public static final List<Item> ITEMS = new ArrayList<Item>();
				
		//Materials
		public static final ArmorMaterial POOP_ARMOR = EnumHelper.addArmorMaterial("poop_armor", Poop.MODID +":poop", 15, new int[] {3, 5, 6, 3}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0F);
		public static final ToolMaterial POOP_TOOL = EnumHelper.addToolMaterial("tool_poop", 3, 350, 7.0F, 2.5F, 10);
		public static final ToolMaterial POOP_WAR_AXE_MATERIAL = EnumHelper.addToolMaterial("poop_war_axe_material", 0, 350, 7.0F, 2.5F, 10);
		public static final ToolMaterial POOP_KATANA_MATERIAL = EnumHelper.addToolMaterial("poop_katana_material", 0, 350, 7.0F, 2.5F, 10);
		public static final ToolMaterial POOP_WAR_HAMMER_MATERIAL = EnumHelper.addToolMaterial("poop_war_hammer_material", 0, 350, 7.0F, 16.0F, 10);
		
		//Items
		public static final Item POOP_INGOT = new ItemBase("poop_ingot");
		public static final Item POOP_RAW_INGOT = new ItemBase("poop_raw_ingot");
		public static final Item TOILET_PAPER = new ItemBase("toilet_paper");
		public static final Item RAW_EMPTY_ROLL = new ItemBase("raw_empty_roll");
						
		//Food
		public static final Item CHOCOLATE_BAR = new FoodBase("chocolate_bar", 4, false);
		
		//Tools
		public static final Item POOP_SWORD = new PoopSword("poop_sword", POOP_TOOL);
		public static final Item POOP_KATANA = new PoopKatana("poop_katana", POOP_KATANA_MATERIAL);
		public static final Item POOP_WAR_AXE = new PoopWarAxe("poop_war_axe", POOP_WAR_AXE_MATERIAL);
		public static final Item POOP_WAR_HAMMER = new PoopWarHammer("poop_war_hammer", POOP_WAR_HAMMER_MATERIAL);
		public static final Item POOP_PICKAXE = new PoopPickaxe("poop_pickaxe", POOP_TOOL);
		public static final Item POOP_AXE = new PoopAxe("poop_axe", POOP_TOOL);
		public static final Item POOP_SHOVEL = new PoopShovel("poop_shovel", POOP_TOOL);
		public static final Item POOP_HOE = new PoopHoe("poop_hoe", POOP_TOOL);
		public static final Item POOP_BOW = new PoopBow("poop_bow");
	
		//Armor
		public static final Item POOP_HELMET = new ArmorBase("poop_helmet", POOP_ARMOR, 1, EntityEquipmentSlot.HEAD);
		public static final Item POOP_CHESTPLATE = new ArmorBase("poop_chestplate", POOP_ARMOR, 1, EntityEquipmentSlot.CHEST);
		public static final Item POOP_LEGGINGS = new ArmorBase("poop_leggings", POOP_ARMOR, 2, EntityEquipmentSlot.LEGS);
		public static final Item POOP_BOOTS = new ArmorBase("poop_boots", POOP_ARMOR, 1, EntityEquipmentSlot.FEET);
}