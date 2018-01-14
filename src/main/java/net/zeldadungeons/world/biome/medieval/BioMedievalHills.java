package net.zeldadungeons.world.biome.medieval;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.zeldadungeons.util.Log;

public class BioMedievalHills extends Biome{

    public BioMedievalHills(Biome.BiomeProperties prop) {
	super(prop);
    }

    @Override
    public void decorate(World worldIn, Random rand, BlockPos pos) {
    }
}
