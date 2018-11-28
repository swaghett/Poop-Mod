package com.tiviacz.poop.util.handlers;

import com.tiviacz.poop.Poop;

import net.minecraftforge.common.config.Config;

@Config(modid=Poop.MODID)
public class ConfigHandler 
{
	@Config.Comment("Chance to mob make poop *DO NOT SET LOWER NUMBERS THAN 10000* *MORE - BIGGER CHANCE* *0 - NO CHANCE*")
	@Config.RequiresMcRestart
	public static int poopChance = 12500;
}