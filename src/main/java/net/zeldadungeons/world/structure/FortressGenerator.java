package net.zeldadungeons.world.structure;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.zeldadungeons.util.Log;

public class FortressGenerator implements IWorldGenerator {

    private int xSize;
    private int ySize;
    private int zSize;
    private String name;
    FortressTemplate[] pieces;

    private static final IBlockState GRASS = Blocks.GRASS.getDefaultState();

    /**
     * Creates a new Fortress Generator.
     * 
     * @param xS Width of the Structure
     * @param yS Height of the Structure
     * @param zS Depth of the Structure
     * @param name
     * @param pieces All the pieces that belong to the structure that needs to
     * be generated.
     */
    public FortressGenerator(int xS, int yS, int zS, String name, FortressTemplate... pieces) {
	this.xSize = xS;
	this.ySize = yS;
	this.zSize = zS;
	this.name = name;
	this.pieces = pieces;
    }

    /**
     * Generates a Fortress at a certain chunk.
     * 
     */
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {

	BlockPos pos = findPosForGeneration(chunkX, chunkZ, world, random);
	if (pos == null) {
	    Log.logString("Couldn't find a position to spawn fortress in Chunk " + chunkX + " " + chunkZ);
	    return;
	}
	else if (world.isRemote) return;
	PlacementSettings settings = new PlacementSettings().setMirror(Mirror.NONE).setRotation(Rotation.NONE).setIgnoreEntities(false);
	WorldServer server = (WorldServer) world;
	TemplateManager tm = server.getStructureTemplateManager();
	for (int i = 0; i < pieces.length; i++) {
	    FortressTemplate ft = pieces[i];
	    Template template = tm.get(server.getMinecraftServer(), ft.getPath());
	    template.addBlocksToWorld(world, pos.add(ft.getOffX() - xSize / 2, ft.getOffY(), ft.getOffZ() - zSize / 2), settings);
	    Log.getLogger().info("generated fortress piece");
	}
    }

    /**
     * Searches the chunk for a position to generate the fortress at. Evaluates
     * random positions to be checked if they are viable.
     * 
     * @param chunkX
     * @param chunkZ
     * @param world
     * @param rand
     * @return
     */
    public BlockPos findPosForGeneration(int chunkX, int chunkZ, World world, Random rand) {
	for (int i = 0; i < 1; i++) {
	    int x = chunkX * 16 + rand.nextInt(16);
	    int z = chunkZ * 16 + rand.nextInt(16);
	    int y = world.getHeight(x, z);
	    if (isValidPosition(x, y, z, world)) return new BlockPos(x, y, z);
	    // if(world.isChunkGeneratedAt(x+this.xSize/2, z+this.zSize/2))
	}
	return null;

    }

    /**
     * Checks if the given position can generate a Fortress, the goal is to
     * avoid weird structure spawning(like e.g. blocks spawning in mid-air)
     * 
     * @param x
     * @param y
     * @param z
     * @param world
     * @return
     */
    private boolean isValidPosition(int x, int y, int z, World world) {
	if (world.getHeight() - this.ySize - y < 0) return false;
	boolean[] flags = { checkEdge(x + xSize / 2, y, z + zSize / 2, 6, world), checkEdge(x, y, z + zSize / 2, 6, world), checkEdge(x - xSize / 2, y, z + zSize / 2, 6, world), checkEdge(x - xSize / 2, y, z, 6, world),
		checkEdge(x - xSize / 2, y, z - zSize / 2, 6, world), checkEdge(x, y, z - zSize / 2, 6, world), checkEdge(x + xSize / 2, y, z - zSize / 2, 6, world), checkEdge(x + xSize / 2, y, z, 6, world), };
	for (int i = 0; i < flags.length; i++) {
	    if (flags[i] == false) return false;
	}
	Log.getLogger().info("Position " + x + " " + y + " " + z + " is viable for spawning fortress " + this.name + "!");
	return true;

    }

    /**
     * Checks an edge of the structure if it has viable spawning positions.
     * 
     * @param x
     * @param y
     * @param z
     * @param allowedOffset How much higher the Ground can be compared to the
     * middle of the Structure.
     * @param world
     * @return
     */
    private boolean checkEdge(int x, int y, int z, int allowedOffset, World world) {
	if (world.getHeight(x, z) < y || world.getHeight(x, z) > y + allowedOffset) return false;
	if (world.getBlockState(new BlockPos(x, y + allowedOffset, z)).getMaterial() != Material.AIR || world.getBlockState(new BlockPos(x, y, z)).getMaterial() == Material.AIR) return false;
	Log.getLogger().info("Edge is viable at " + x + " " + y + " " + z);
	return true;
    }
}
