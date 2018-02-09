package net.zeldadungeons.world.structure;

import java.util.List;
import java.util.Random;

import net.minecraft.world.gen.IChunkGenerator;

public class ModStructureTypeMulti extends ModStructureType{
    protected int maxPieces;
    protected int minPieces;
    protected List<StructurePiece> validPieces;
    
    public ModStructureTypeMulti(){
	super();
    }

    public void generate(ModStructure structure) {
	
    }
    
    @Override
    public ModStructure createNewStructures(int chunkX, int chunkZ, IChunkGenerator cg, Random rand) {
	cg.getChunkAt(x, z)
	int tries = 4;
	for()
    }
    
}
