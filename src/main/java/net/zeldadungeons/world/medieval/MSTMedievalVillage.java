package net.zeldadungeons.world.medieval;

import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.gen.ChunkProviderServer;
import net.zeldadungeons.world.ICustomCG;
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
    
    public void generate(ModStructure structure){
	ChunkProviderServer provider = structure.getProvider();
	if(provider instanceof ICustomCG){
	    //load all chunks that the structure is in
	    for(ChunkPos pos : structure.getAffectedChunks()){
		((ICustomCG) provider).chunkCache.put(pos.asLong(pos.x, pos.z), ((ICustomCG) provider).getChunkAt(pos.x, pos.z));
	    }
	}
    }
}
