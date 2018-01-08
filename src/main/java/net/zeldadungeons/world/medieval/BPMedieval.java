package net.zeldadungeons.world.medieval;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiome;
import net.zeldadungeons.world.BiomeProviderCustom;

public class BPMedieval extends BiomeProviderCustom{

    private GenLayer genBiomes;

    public BPMedieval(long seed, WorldType type, String options){
	super(seed, type, options);
	
    }
    
    public BPMedieval(World worldIn) {
	this(worldIn.getSeed(), worldIn.getWorldInfo().getTerrainType(), worldIn.getWorldInfo().getGeneratorOptions());
    }
    
    @Override
    public Biome[] getBiomesForGeneration(Biome[] biomes, int x, int z, int width, int height) {
	return biomes;
    }
    
    @Override
    public Biome[] getBiomes(Biome[] oldBiomeList, int x, int z, int width, int depth) {
        return oldBiomeList;
    }
    
}
