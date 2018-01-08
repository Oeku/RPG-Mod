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

    public static Biome MEDIEVAL_HILLS;

    public static void registerWorldGenerators() {
	GameRegistry.registerWorldGenerator(new WorldGenOres(), 1000);
	GameRegistry.registerWorldGenerator(new WorldGenAmber(), 1000);
    }

    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event) {
	registerWorldType();
	MEDIEVAL_HILLS = new BioMedievalHills();
	registerBiome(MEDIEVAL_HILLS, "medieval_hills", event.getRegistry(), 100, BiomeManager.BiomeType.COOL, BiomeDictionary.Type.HOT);
	registerBiome(new BioMedievalHills(), "medieval_hills1", event.getRegistry(), 100, BiomeManager.BiomeType.COOL, BiomeDictionary.Type.DRY);
	registerBiome(new BioMedievalHills(), "medieval_hills2", event.getRegistry(), 100, BiomeManager.BiomeType.WARM, BiomeDictionary.Type.HILLS);
	registerBiome(new BioMedievalHills(), "medieval_hills3", event.getRegistry(), 100, BiomeManager.BiomeType.DESERT, BiomeDictionary.Type.HILLS);
	registerBiome(new BioMedievalHills(), "medieval_hills4", event.getRegistry(), 100, BiomeManager.BiomeType.ICY, BiomeDictionary.Type.PLAINS);
	registerBiome(new BioMedievalHills(), "medieval_hills5", event.getRegistry(), 100, BiomeManager.BiomeType.COOL, BiomeDictionary.Type.HILLS);
	registerBiome(new BioMedievalHills(), "medieval_hills6", event.getRegistry(), 100, BiomeManager.BiomeType.COOL, BiomeDictionary.Type.SPARSE);


	// remove biomes
	BiomeManager.removeBiome(BiomeType.WARM, new BiomeEntry(Biomes.FOREST, 10));
	BiomeManager.removeBiome(BiomeType.WARM, new BiomeEntry(Biomes.ROOFED_FOREST, 10));
	BiomeManager.removeBiome(BiomeType.WARM, new BiomeEntry(Biomes.EXTREME_HILLS, 10));
	BiomeManager.removeBiome(BiomeType.WARM, new BiomeEntry(Biomes.PLAINS, 10));
	BiomeManager.removeBiome(BiomeType.WARM, new BiomeEntry(Biomes.BIRCH_FOREST, 10));
	BiomeManager.removeBiome(BiomeType.WARM, new BiomeEntry(Biomes.SWAMPLAND, 10));

	BiomeManager.removeBiome(BiomeType.COOL, new BiomeEntry(Biomes.FOREST, 10));
	BiomeManager.removeBiome(BiomeType.COOL, new BiomeEntry(Biomes.EXTREME_HILLS, 10));
	BiomeManager.removeBiome(BiomeType.COOL, new BiomeEntry(Biomes.TAIGA, 10));
	BiomeManager.removeBiome(BiomeType.COOL, new BiomeEntry(Biomes.PLAINS, 10));

	BiomeManager.removeBiome(BiomeType.COOL, new BiomeEntry(Biomes.ICE_PLAINS, 30));
	BiomeManager.removeBiome(BiomeType.COOL, new BiomeEntry(Biomes.COLD_TAIGA, 10));

    }

    public static void registerBiome(Biome biome, String biomeName, IForgeRegistry reg, int weight, BiomeManager.BiomeType type, BiomeDictionary.Type... types) {
	reg.register(biome.setRegistryName(ZeldaDungeons.MODID, biomeName));
	BiomeManager.addBiome(type, new BiomeManager.BiomeEntry(biome, weight));
	BiomeDictionary.addTypes(biome, types);

    }

    public static void registerWorldType() {
    }
}
