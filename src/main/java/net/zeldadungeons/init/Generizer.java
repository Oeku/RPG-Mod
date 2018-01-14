package net.zeldadungeons.init;

import net.minecraft.init.Biomes;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.zeldadungeons.ZeldaDungeons;
import net.zeldadungeons.world.WorldGenAmber;
import net.zeldadungeons.world.WorldGenOres;
import net.zeldadungeons.world.biome.medieval.BioMedievalHills;

@Mod.EventBusSubscriber(modid = ZeldaDungeons.MODID)
public class Generizer {
    public static WorldType WORLD_TYPE;

    public static Biome MEDIEVAL_HILLS1;
    public static Biome MEDIEVAL_HILLS2;
    public static Biome MEDIEVAL_HILLS3;
    public static Biome MEDIEVAL_HILLS4;


    public static void registerWorldGenerators() {
	GameRegistry.registerWorldGenerator(new WorldGenOres(), 1000);
	GameRegistry.registerWorldGenerator(new WorldGenAmber(), 1000);
    }

    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event) {
	registerWorldType();
	BiomeType.getType("medieval_hills");
	MEDIEVAL_HILLS1 = new BioMedievalHills(new Biome.BiomeProperties("Deep Medieval Hills").setBaseHeight(0.05F).setHeightVariation(0.01F).setTemperature(1F).setRainfall(0.2F));
	MEDIEVAL_HILLS2 = new BioMedievalHills(new Biome.BiomeProperties("Medieval Hills").setBaseHeight(0.5F).setHeightVariation(0.05F).setTemperature(0.8F).setRainfall(0.2F));
	MEDIEVAL_HILLS3 = new BioMedievalHills(new Biome.BiomeProperties("Upper Medieval Hills").setBaseHeight(0.95F).setHeightVariation(0.05F).setTemperature(0.6F).setRainfall(0.6F));
	MEDIEVAL_HILLS4 = new BioMedievalHills(new Biome.BiomeProperties("High Medieval Hills").setBaseHeight(1.5F).setHeightVariation(0.01F).setTemperature(0.4F).setRainfall(0.2F));
	
	registerBiome(MEDIEVAL_HILLS1, "medieval_hills1", event.getRegistry(), 100, BiomeManager.BiomeType.getType("MEDIEVAL"), BiomeDictionary.Type.getType("MEDIEVAL"));
	registerBiome(MEDIEVAL_HILLS2, "medieval_hills2", event.getRegistry(), 100, BiomeManager.BiomeType.getType("MEDIEVAL"), BiomeDictionary.Type.getType("MEDIEVAL"));
	registerBiome(MEDIEVAL_HILLS3, "medieval_hills3", event.getRegistry(), 100, BiomeManager.BiomeType.getType("MEDIEVAL"), BiomeDictionary.Type.getType("MEDIEVAL"));
	registerBiome(MEDIEVAL_HILLS4, "medieval_hills4", event.getRegistry(), 100, BiomeManager.BiomeType.getType("MEDIEVAL"), BiomeDictionary.Type.getType("MEDIEVAL"));
    }

    public static void registerBiome(Biome biome, String biomeName, IForgeRegistry reg, int weight, BiomeManager.BiomeType type, BiomeDictionary.Type... types) {
	reg.register(biome.setRegistryName(ZeldaDungeons.MODID, biomeName));
	BiomeDictionary.addTypes(biome, types);

    }

    public static void registerWorldType() {
    }
}
