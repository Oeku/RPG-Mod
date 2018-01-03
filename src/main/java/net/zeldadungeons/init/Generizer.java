package net.zeldadungeons.init;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.zeldadungeons.world.WorldGenAmber;
import net.zeldadungeons.world.WorldGenOres;

public class Generizer {
	public static void registerWorldGenerators(){
		GameRegistry.registerWorldGenerator(new WorldGenOres(), 1000);
		GameRegistry.registerWorldGenerator(new WorldGenAmber(), 1000);
	}
}
