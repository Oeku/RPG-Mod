package net.zeldadungeons.world.medieval;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;
import net.zeldadungeons.world.structure.ModStructure;
import net.zeldadungeons.world.structure.ModStructureTypeMulti;

public class MSTMedievalVillage extends ModStructureTypeMulti{
    public MSTMedievalVillage(){
	super();
    }
    
    @Override
    public void init(){
	this.minPieces = 5;
	this.maxPieces = 50;
	this.setSizes(new int[]{200, 0, 200}, new int[]{30, 0, 30});
    }
    
    @Override
    public void generate(ModStructure structure){
	this.preGenerate(structure.getPositions());
    }
    
    public void preGenerate(BlockPos[] positions){
    }
    
    public void raiseTerrainToHeight(int height, Chunk chunkIn){
	
    }
    
}
