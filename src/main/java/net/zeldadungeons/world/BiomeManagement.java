package net.zeldadungeons.world;

import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.world.WorldEvent;
import net.zeldadungeons.init.Generizer;

public class BiomeManagement {
    
    public static void addMedievalBiomes(WorldEvent.Load event){
	BiomeManager.addBiome(BiomeType.getType("MEDIEVAL"), new BiomeEntry(Generizer.MEDIEVAL_HILLS1, 15));
	BiomeManager.addBiome(BiomeType.getType("MEDIEVAL"), new BiomeEntry(Generizer.MEDIEVAL_HILLS2, 15));
	BiomeManager.addBiome(BiomeType.getType("MEDIEVAL"), new BiomeEntry(Generizer.MEDIEVAL_HILLS3, 15));
	BiomeManager.addBiome(BiomeType.getType("MEDIEVAL"), new BiomeEntry(Generizer.MEDIEVAL_HILLS4, 15));
    }
    
    public static void removeTypeBiomes(String type){
	for(BiomeEntry entry : BiomeManager.getBiomes(BiomeManager.BiomeType.getType(type))){
	    BiomeManager.removeBiome(BiomeType.getType(type), entry);
	}
    }
    
    public static void addOverworldBiomes(WorldEvent.Load event){
	removeAllBiomes();
	for(BiomeManager.BiomeType type : BiomeManager.BiomeType.values()){
	    if(!(type == type.getType("MEDIEVAL")))){
		for(BiomeManager.getBiomes(type)
	    }
	}
    }
    
    public static void removeAllBiomes(){
	for(BiomeManager.BiomeType type : BiomeManager.BiomeType.values()){
	    for(BiomeEntry entry : BiomeManager.getBiomes(type))
	    BiomeManager.removeBiome(type, entry);
	}
    }
}
