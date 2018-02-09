package net.zeldadungeons.world.structure;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkProviderServer;
import net.zeldadungeons.world.ICustomCG;

public class ModStructure {
    /**
     * true if this structure's blocks are already integrated into the world.
     **/
    private boolean isFinished;
    private boolean watchY;
    private int xSize;
    private int ySize;
    private int zSize;
    private BlockPos[] boundingBoxPositions;
    private World world;
    private ChunkProviderServer provider;
    /** What kind of structure this is **/
    private ModStructureType strutureType;
    private List<StructurePiece> structurePieces;
    private List<ChunkPos> affectedChunks;

    public ModStructure(ChunkProviderServer provider) {
	this.provider = provider;
	this.world = provider.world;
	this.boundingBoxPositions = new BlockPos[8];
	this.structurePieces = new ArrayList<StructurePiece>();
	this.affectedChunks = new ArrayList<ChunkPos>();
    }

    /**
     * Sets the size of this structure.
     * 
     * @param xSize
     * @param ySize
     * @param zSize
     */
    public void setSize(int xSize, int ySize, int zSize) {
	this.xSize = xSize;
	this.ySize = ySize;
	this.zSize = zSize;
    }

    /**
     * Uses a starting position and xSize/ySize/zSize to determine the
     * {@link boundingBoxPositions} of this structure. Call this after setSize.
     * Also calculates all the chunks that this structure uses.
     * 
     * @param pos The Starting Position
     */
    public void setPositions(BlockPos pos) {
	/**
	 * 0 = left bottom corner near 1 = left bottom corner far 2 = right
	 * bottom corner near 3 = right bottom corner far 4 = left top corner
	 * near 5 = left top corner far 6 = right top corner near 7 = right top
	 * corner far
	 */
	this.boundingBoxPositions[0] = pos;
	this.boundingBoxPositions[1] = new BlockPos(pos).add(xSize, 0, 0);
	this.boundingBoxPositions[2] = new BlockPos(pos).add(0, 0, zSize);
	this.boundingBoxPositions[3] = new BlockPos(pos).add(xSize, 0, zSize);
	this.boundingBoxPositions[4] = new BlockPos(pos).add(0, ySize, 0);
	this.boundingBoxPositions[5] = new BlockPos(pos).add(0, ySize, zSize);
	this.boundingBoxPositions[6] = new BlockPos(pos).add(xSize, ySize, 0);
	this.boundingBoxPositions[7] = new BlockPos(pos).add(xSize, ySize, zSize);
	
	int xDiff = boundingBoxPositions[0].getX() >> 4 - boundingBoxPositions[1].getX() >> 4;
	int zDiff = boundingBoxPositions[0].getZ() >> 4 - boundingBoxPositions[2].getZ() >> 4;
	for(int i = 0; i < xDiff; i++){
	    for(int j = 0; j < zDiff; j++){
		this.affectedChunks.add(new ChunkPos(i, j));
	    }
	}

    }

    public BlockPos[] getPositions() {
	return this.boundingBoxPositions;
    }
    
    public List<ChunkPos> getAffectedChunks(){
	return this.affectedChunks;
    }

    /**
     * Generate this structure. Use this bounding box for position. If the
     * structure is finished building, it will be removed from the
     * ChunkGenerators' list.
     * 
     * @return Whether this structure is finished.
     */
    public boolean generate() {
	this.strutureType.generate(this);
	return this.isFinished;
    }

    public ChunkProviderServer getProvider() {
	return this.provider;
    }
}
