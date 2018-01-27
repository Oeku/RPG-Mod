package net.zeldadungeons.world.medieval;

import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;
import net.zeldadungeons.init.Generizer;
import net.zeldadungeons.world.BiomeProviderCustom;

public class WPMedieval extends WorldProvider {

    @Override
    public IChunkGenerator createChunkGenerator() {
	return new CGMedieval(this.world, this.getSeed(), false, "");
    }

    @Override
    public DimensionType getDimensionType() {
	return DimensionType.byName("Medieval");
    }

    @Override
    public String getSaveFolder() {
	return "DIM-Medieval";
    }

    @Override
    public float getCloudHeight() {
	return 170F;
    }

    @Override
    public boolean isNether() {
	return false;
    }

    @Override
    public BiomeProvider getBiomeProvider() {
	return this.biomeProvider;
    }

    public boolean canDropChunk(int x, int z) {
	return !this.world.isSpawnChunk(x, z) || !this.world.provider.getDimensionType().shouldLoadSpawn();
    }

    @Override
    protected void init() {
	this.biomeProvider = new BPMedieval(this.world);
    }

    @Override
    public boolean hasSkyLight() {
	return true;
    }
}
