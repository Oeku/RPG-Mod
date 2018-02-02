package net.zeldadungeons.init;

import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructureIO;
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
import net.zeldadungeons.world.biome.medieval.BMedievalFlowerField;
import net.zeldadungeons.world.biome.medieval.BMedievalHills;
import net.zeldadungeons.world.structure.FortressGenerator;
import net.zeldadungeons.world.structure.FortressTemplate;
import net.zeldadungeons.world.structure.MGMedievalVillage;

@Mod.EventBusSubscriber(modid = ZeldaDungeons.MODID)
public class Generizer {
    public static WorldType WORLD_TYPE;

    public static Biome MEDIEVAL_HILLS;
    public static Biome MEDIEVAL_FLOWER_FIELD;
    public static Biome MEDIEVAL_MARSH;
    public static Biome MEDIEVAL_MOUNTAINS;

    public static void registerStructures(){
	MapGenStructureIO.registerStructure(MGMedievalVillage.Start.class, "Medieval Village");
    }
    

    public static void registerWorldGenerators() {
	GameRegistry.registerWorldGenerator(new WorldGenOres(), 1000);
	GameRegistry.registerWorldGenerator(new WorldGenAmber(), 1000);
	GameRegistry.registerWorldGenerator(new FortressGenerator(37, 30, 40, "Fortress1",
		new FortressTemplate(0, 0, 0, new ResourceLocation(ZeldaDungeons.MODID, "fortress1_1")), 
		new FortressTemplate(31, 0, 11, new ResourceLocation(ZeldaDungeons.MODID, "fortress1_2")),
		new FortressTemplate(0, 0, 31, new ResourceLocation(ZeldaDungeons.MODID, "fortress1_3"))), 1);
	registerStructures();
    }

    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event) {
	registerWorldType();
	BiomeType.getType("medieval_hills");
	MEDIEVAL_HILLS = new BMedievalHills(new Biome.BiomeProperties("Medieval Hills").setBaseHeight(1.5F).setHeightVariation(0.011F).setTemperature(0.8F).setRainfall(0.2F));
	MEDIEVAL_MOUNTAINS = new BMedievalHills(new Biome.BiomeProperties("Medieval Mountains").setBaseHeight(4F).setHeightVariation(0.04F).setTemperature(0.4F));
	MEDIEVAL_FLOWER_FIELD = new BMedievalFlowerField(new Biome.BiomeProperties("Medieval Flower Field").setBaseHeight(1.5F).setHeightVariation(0.011F).setTemperature(0.8F).setRainfall(0.5F));
	registerBiome(MEDIEVAL_HILLS, "medieval_hills1", event.getRegistry(), 100, BiomeManager.BiomeType.getType("MEDIEVAL"), BiomeDictionary.Type.getType("MEDIEVAL"));
	registerBiome(MEDIEVAL_MOUNTAINS, "medieval_mountains", event.getRegistry(), 100, BiomeManager.BiomeType.getType("MEDIEVAL"), BiomeDictionary.Type.getType("MEDIEVAL"));
	registerBiome(MEDIEVAL_FLOWER_FIELD, "medieval_flower_field", event.getRegistry(), 100, BiomeManager.BiomeType.getType("MEDIEVAL"), BiomeDictionary.Type.getType("MEDIEVAL"));

    }

    public static void registerBiome(Biome biome, String biomeName, IForgeRegistry reg, int weight, BiomeManager.BiomeType type, BiomeDictionary.Type... types) {
	reg.register(biome.setRegistryName(ZeldaDungeons.MODID, biomeName));
	BiomeDictionary.addTypes(biome, types);

    }

    public static void registerWorldType() {
    }
}
