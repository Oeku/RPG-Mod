package net.zeldadungeons.event;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiome;
import net.minecraftforge.event.terraingen.WorldTypeEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldHandler {
    public static final WorldHandler INSTANCE = new WorldHandler();

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
	
    }
    
    @SubscribeEvent
    public void onBiome(WorldTypeEvent.InitBiomeGens event){
	GenLayer[] gen = event.getNewBiomeGens();
    }
    
   
}
