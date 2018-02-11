package net.zeldadungeons.world.structure;

import java.util.List;
import java.util.Random;

import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;

/**
 * 
 * @author ArmamentHaki
 */
public class ModStructureTypeMulti extends ModStructureType {
    protected int maxPieces;
    protected int minPieces;
    protected List<StructurePiece> validPieces;
    protected int totalWeight;

    public ModStructureTypeMulti() {
	this.init();
    }

    public void generate(ModStructure structure) {

    }

    @Override
    public ModStructure createNewStructures(Chunk chunk, int chunkX, int chunkZ, IChunkGenerator cg, Random rand) {
	return null;
    }

    /** 
     * Returns a random StructurePiece out of the given list of pieces.
     * Higher PieceWeights = Higher probability.
     * @param rand Random Object
     * @param pieces Input List
     * @return A Weighed StructurePiece!
     */
    public StructurePiece getRandomWeighedPiece(Random rand, List<StructurePiece> pieces) {
	int randomInt = rand.nextInt(this.totalWeight);
	int i = 0;
	for (StructurePiece p : pieces) {
	    p.getPieceWeight();
	    if (randomInt >= i && randomInt <= i + p.getPieceWeight()) {
		return p;
	    }
	    else i += p.getPieceWeight();
	}
	return null;
    }
}
