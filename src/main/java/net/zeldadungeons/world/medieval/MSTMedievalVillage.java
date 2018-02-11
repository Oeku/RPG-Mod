package net.zeldadungeons.world.medieval;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.zeldadungeons.init.Generizer;
import net.zeldadungeons.init.Structurizer;
import net.zeldadungeons.util.Log;
import net.zeldadungeons.world.ICustomCG;
import net.zeldadungeons.world.structure.ModStructure;
import net.zeldadungeons.world.structure.ModStructureTypeMulti;
import net.zeldadungeons.world.structure.StructurePiece;

public class MSTMedievalVillage extends ModStructureTypeMulti {

    public MSTMedievalVillage() {
	this.minPieces = 5;
	this.maxPieces = 50;
	this.setSizes(new int[] { 200, 0, 200 }, new int[] { 30, 0, 30 });
	this.validPieces = new ArrayList<StructurePiece>();
	// Add the building Pieces here =)
	this.validPieces.add(new StructurePiece(10, Structurizer.mvh1));
	this.totalWeight = 0;
	for (StructurePiece p : validPieces) {
	    this.totalWeight += p.getPieceWeight();
	}
    }

    @Override
    public ModStructure createNewStructures(Chunk chunk, int chunkX, int chunkZ, IChunkGenerator cg, Random rand) {
	ICustomCG customCG = ((ICustomCG) cg);
	// How many random positions in a chunk will be tried to create a new
	// Structure from
	int tries = 1;
	// 1/probability-1 is how probable it is for a structure to spawn.
	int possibility = 50;
	for (int i = 0; i < tries; i++) {
	    int randomX = rand.nextInt(16);
	    int randomZ = rand.nextInt(16);
	    BlockPos pos = new BlockPos(randomX + (chunkX << 4), 0, randomZ + (chunkZ << 4));
	    pos = customCG.getWorld().getHeight(pos);
	    Biome biome = customCG.getWorld().getBiomeProvider().getBiome(pos);
	    int i4 = chunk.getHeight(pos);
	    if (biome == Generizer.MEDIEVAL_HILLS && chunk.getBlockState(pos.getX(), pos.getY(), pos.getZ()).getMaterial() != Material.WATER) {
		if (rand.nextInt(possibility) == 1) {
		    Log.getLogger().info("Created new Medieval Village at" + pos.getX() + " " + pos.getY() + " " + pos.getZ());
		    return createRandomNewStructure(rand, pos, cg);
		}
	    }
	}
	return null;
    }

    /**
     * Returns a randomly sized structure of this type
     * 
     * @param rand
     * @param pos The position to start the structure
     * @param cg Custom Chunk Generator instance
     * @return
     */
    public ModStructure createRandomNewStructure(Random rand, BlockPos pos, IChunkGenerator cg) {
	int sizeX = rand.nextInt(this.maxSize[0] - this.minSize[0]) + this.minSize[0];
	int sizeZ = rand.nextInt(this.maxSize[2] - this.minSize[2]) + this.minSize[2];
	ModStructure structure = new ModStructure(cg, this);
	structure.setSize(sizeX, 50, sizeZ);
	structure.setPositions(pos);
	structure.generate();
	return structure;
    }

    public void generate(ModStructure structure) {
	int height = structure.getBoundingBox().minY;
	if (height < 10) height = 10;
	else if (height > 246) height = 246;
	IChunkGenerator provider = structure.getProvider();
	if (provider instanceof ICustomCG) {
	    // load all chunks that the structure is in
	    for (ChunkPos pos : structure.getAffectedChunks()) {
		Chunk currentChunk = ((ICustomCG) provider).getChunkAt(pos.x, pos.z);
		raiseTerrainInChunk(currentChunk, height, height - 10, height + 10, 0);
		((ICustomCG) provider).chunkCache.put(pos.asLong(pos.x, pos.z), currentChunk);
	    }
	    this.generateStructureMap(structure, ((ICustomCG) provider).getWorld().rand);
	}
    }

    /**
     * Generate the actual structure.
     * 
     * @return A List of Structure Pieces that can be added to a world.
     */
    private List<StructurePiece> generateStructureMap(ModStructure structure, Random rand) {
	List<StructurePiece> components = new ArrayList<StructurePiece>();
	this.addBuildings(structure, components, rand);
	return components;
    }

    /**
     * Add building StructurePieces to the component list
     * 
     * @param structure
     * @param list
     * @param rand
     * @return
     */
    private List<StructurePiece> addBuildings(ModStructure structure, List<StructurePiece> list, Random rand) {
	boolean keepGenerating = true;
	while (keepGenerating) {
	    StructurePiece pieceToSpawn = this.getRandomWeighedPiece(rand, this.validPieces);
	    // TODO Add Random PlacementData and create new StructureComponent
	    // of pieceToSpawn
	    keepGenerating = false;
	}
	return list;
    }

    /**
     * Raise the terrain in a Chunk and flatten it.
     * 
     * @param height The height the terrain should flattened to.
     * @param flags
     * @param start The height where the terrain raising should begin
     * @param end The height where the terrain raising should stop
     * @return
     */
    public Chunk raiseTerrainInChunk(Chunk chunk, int height, int start, int end, int flags) {
	// chunk position as block position
	int x = chunk.getPos().x << 4;
	int z = chunk.getPos().z << 4;
	int placed = 0;
	chunk.setBlockState(new BlockPos(x, 160, z), Blocks.WOOL.getDefaultState());
	Log.getLogger().info(height + " " + start + " " + " " + end);
	BlockPos posrator = new BlockPos(x, start, end);
	for (int i = 0; i < end - start; i++) {
	    for (int j = 0; j < 16; j++) {
		for (int k = 0; k < 16; k++) {
		    if (posrator.getY() > height) {
			chunk.setBlockState(posrator, AIR);
			placed++;
		    }
		    else if (posrator.getY() == height) {
			chunk.setBlockState(posrator, GRASS);
			placed++;
		    }
		    else chunk.setBlockState(posrator, DIRT);
		    posrator.north();
		    placed++;
		}
		posrator.east();

	    }
	    posrator.up();
	}
	Log.getLogger().info("Placed " + placed);
	return chunk;
    }
}
