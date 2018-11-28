package com.tiviacz.poop.init;

import java.util.ArrayList;
import java.util.List;

import com.tiviacz.poop.objects.blocks.BlockEmptyRoll;
import com.tiviacz.poop.objects.blocks.BlockHolder;
import com.tiviacz.poop.objects.blocks.BlockHolderEmptyRoll;
import com.tiviacz.poop.objects.blocks.BlockHolderPaperRoll;
import com.tiviacz.poop.objects.blocks.BlockPaperRoll;
import com.tiviacz.poop.objects.blocks.BlockPoop;
import com.tiviacz.poop.objects.blocks.BlockPoopBase;
import com.tiviacz.poop.objects.blocks.BlockPoopFence;
import com.tiviacz.poop.objects.blocks.BlockPoopPillar;
import com.tiviacz.poop.objects.blocks.BlockPoopStairs;
import com.tiviacz.poop.objects.blocks.BlockPoopTrophy;
import com.tiviacz.poop.objects.blocks.BlockToilet;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModBlocks
{
		public static final List<Block> BLOCKS = new ArrayList<Block>();
		
		//Models
		
		public static final Block POOP = new BlockPoop("poop", Material.GROUND);
		public static final Block EMPTY_ROLL = new BlockEmptyRoll("empty_roll", Material.CLOTH);
		public static final Block PAPER_ROLL = new BlockPaperRoll("paper_roll", Material.CLOTH);
		public static final Block HOLDER = new BlockHolder("holder", Material.IRON);
		public static final Block HOLDER_EMPTY_ROLL = new BlockHolderEmptyRoll("holder_empty_roll", Material.IRON);
		public static final Block HOLDER_PAPER_ROLL = new BlockHolderPaperRoll("holder_paper_roll", Material.IRON);
		public static final Block POOP_TROPHY = new BlockPoopTrophy("poop_trophy", Material.IRON);
		public static final Block TOILET = new BlockToilet("toilet", Material.ROCK);
		
		//Blocks
		
		public static final Block POOP_BLOCK = new BlockPoopBase("poop_block", Material.GROUND, 0, 0, "hand", 0, SoundType.GROUND);
		public static final Block POOP_HARDENED = new BlockPoopBase("poop_hardened", Material.ROCK, 1.5F, 30F, "pickaxe", 0, SoundType.STONE);
		public static final Block POOP_BRICKS = new BlockPoopBase("poop_bricks", Material.ROCK, 1.5F, 30F, "pickaxe", 0, SoundType.STONE);
		public static final Block POOP_LANTERN = new BlockPoopBase("poop_lantern", Material.ROCK, 1.5F, 30F, "pickaxe", 0, SoundType.STONE).setLightLevel(1F);
		public static final Block POOP_PILLAR = new BlockPoopPillar("poop_pillar", Material.ROCK);
		public static final Block POOP_FENCE = new BlockPoopFence("poop_fence", Material.ROCK, MapColor.BROWN);
		public static final Block POOP_STAIRS = new BlockPoopStairs("poop_stairs", Material.ROCK, POOP_BRICKS.getDefaultState());
}