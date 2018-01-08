package net.zeldadungeons.world.medieval;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;

public class WPMedieval extends WorldProvider{

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new CGMedieval(this.world, this.getSeed(), false, "");
    }

    @Override
    public DimensionType getDimensionType() {
	return DimensionType.OVERWORLD;
    }
    
    @Override
    public String getSaveFolder() {
        return "DIM-Medieval";
    }
    
    @Override
    public float getCloudHeight() {
        return 128F;
    }
    
    @Override
    public boolean isNether() {
        return false;
    }
    
    @Override
    public BiomeProvider getBiomeProvider() {
        return WorldType.DEFAULT.getBiomeProvider(this.world);
    }
}
