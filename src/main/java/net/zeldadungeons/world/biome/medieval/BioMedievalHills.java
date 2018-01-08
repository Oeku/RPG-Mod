package net.zeldadungeons.world.biome.medieval;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.zeldadungeons.util.Log;

public class BioMedievalHills extends Biome{

    public BioMedievalHills() {
	super(new Biome.BiomeProperties("Medieval Hills").setBaseHeight(0.1F).setHeightVariation(0F).setTemperature(0.8F).setRainfall(0.2F));
    }

    @Override
    public void decorate(World worldIn, Random rand, BlockPos pos) {
	Log.getLogger().info(pos.getX()+" "+ pos.getY()+ " "+ pos.getZ());
    }
}
