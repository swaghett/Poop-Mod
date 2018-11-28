package com.tiviacz.poop.util.handlers;

import com.tiviacz.poop.Poop;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SoundsHandler
{
	public static SoundEvent TOILET_FLUSHSOUND;
	
	public static void registerSounds()
	{
		TOILET_FLUSHSOUND = registerSound("block.toilet.flushsound");
	}
	
	private static SoundEvent registerSound(String name)
	{
		ResourceLocation location = new ResourceLocation(Poop.MODID, name);
		SoundEvent event = new SoundEvent(location);
		event.setRegistryName(name);
		ForgeRegistries.SOUND_EVENTS.register(event);
		return event;
	}
}