package net.zeldadungeons.event;

import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraftforge.event.terraingen.InitNoiseGensEvent;
import net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextOverworld;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.zeldadungeons.init.Generizer;

public class WorldHandler {
    public static final WorldHandler INSTANCE = new WorldHandler();

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
	
    }
}
