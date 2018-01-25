package net.zeldadungeons.world.medieval;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.zeldadungeons.init.Generizer;

public class GLMedievalBiomes extends GenLayer {
    private static final Biome[] biomes = new Biome[] { Generizer.MEDIEVAL_HILLS, Generizer.MEDIEVAL_MOUNTAINS, Generizer.MEDIEVAL_FLOWER_FIELD };

    public GLMedievalBiomes(long l, GenLayer parent) {
	super(l);
	this.parent = parent;
    }

    public GLMedievalBiomes(long l) {
	super(l);
    }

    @Override
    public int[] getInts(int x, int z, int width, int depth) {
	int[] dest = IntCache.getIntCache(width * depth);
	for (int k = 0; k < depth; ++k) {
	    for (int i = 0; i < width; ++i) {
		initChunkSeed(x + i, z + k);
		dest[i + k * width] = Biome.getIdForBiome(biomes[nextInt(biomes.length)]);
	    }
	}

	return dest;
    }
}