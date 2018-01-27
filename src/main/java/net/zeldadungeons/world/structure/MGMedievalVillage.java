package net.zeldadungeons.world.structure;

import java.util.Random;

import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGeneratorEnd;
import net.minecraft.world.gen.structure.MapGenEndCity;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureEndCityPieces;
import net.minecraft.world.gen.structure.StructureStart;
import net.zeldadungeons.util.Log;
import net.zeldadungeons.world.biome.medieval.BMedievalHills;
import net.zeldadungeons.world.medieval.CGMedieval;

public class MGMedievalVillage extends MapGenStructure{

    private CGMedieval provider;
    
    public MGMedievalVillage(CGMedieval provider){
	this.provider = provider;
    }
    
    
    @Override
    public String getStructureName() {
	return "Medieval Village";
    }

    @Override
    public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored) {
	return new BlockPos(0, 0, 0);
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
	if(this.world.getBiome(new BlockPos(chunkX*16, 0, chunkZ*16)) instanceof BMedievalHills){
	}
	return true;
    }

    @Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ) {
	return new MGMedievalVillage.Start(this.world, this.provider, this.rand, chunkX, chunkZ);
    }
    
    public class Start extends StructureStart{
	private boolean isSizeable;

	public Start()
        {
        }

        public Start(World worldIn, CGMedieval chunkProvider, Random random, int chunkX, int chunkZ)
        {
            super(chunkX, chunkZ);
            this.create(worldIn, chunkProvider, random, chunkX, chunkZ);
        }
        
        private void create(World worldIn, CGMedieval chunkProvider, Random rnd, int chunkX, int chunkZ)
        {
            Random random = new Random((long)(chunkX + chunkZ * 10387313));
            Rotation rotation = Rotation.values()[random.nextInt(Rotation.values().length)];
            Log.getLogger().info("started structure at chunk "+chunkX+" "+chunkZ);
        }

    }
}
