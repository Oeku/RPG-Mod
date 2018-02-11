package net.zeldadungeons.world.structure;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.zeldadungeons.util.Log;

public class ModStructure {
    /**
     * true if this structure's blocks are already integrated into the world.
     **/
    private boolean isFinished;
    private boolean isGenerating;
    private int xSize;
    private int ySize;
    private int zSize;
    private StructureBoundingBox boundingBox;
    private World world;
    private IChunkGenerator provider;
    /** What kind of structure this is **/
    private ModStructureType strutureType;
    /**
     * All Components that this structure contains. Positions, Rotations and
     * additional Data are stored in here
     **/
    private List<StructureComponent> structurePieces;
    private List<ChunkPos> affectedChunks;

    public ModStructure(IChunkGenerator provider, ModStructureType type) {
	this.provider = provider;
	this.structurePieces = new ArrayList<StructureComponent>();
	this.affectedChunks = new ArrayList<ChunkPos>();
	this.strutureType = type;
	this.boundingBox = new StructureBoundingBox();
	Log.getLogger().info("initializing structure");
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
	this.boundingBox.minX = pos.getX();
	this.boundingBox.minY = pos.getY();
	this.boundingBox.minZ = pos.getZ();
	pos = pos.add(this.xSize, this.ySize, this.zSize);
	this.boundingBox.maxX = pos.getX();
	this.boundingBox.maxY = pos.getY();
	this.boundingBox.maxZ = pos.getZ();

	int xStart = this.boundingBox.minX >> 4;
	int zStart = this.boundingBox.minZ >> 4;

	int xDiff = (this.boundingBox.maxX >> 4) - xStart;
	int zDiff = (this.boundingBox.maxZ >> 4) - zStart;

	for (int i = 0; i <= xDiff; i++) {
	    for (int j = 0; j <= zDiff; j++) {
		this.affectedChunks.add(new ChunkPos(i + xStart, j + zStart));
	    }
	}
    }

    public StructureBoundingBox getBoundingBox() {
	return this.boundingBox;
    }

    public List<ChunkPos> getAffectedChunks() {
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
	if(isGenerating)return false;
	isGenerating = true;
	this.strutureType.generate(this);
	isGenerating = false;
	return this.isFinished;
    }

    public IChunkGenerator getProvider() {
	return this.provider;
    }
}
