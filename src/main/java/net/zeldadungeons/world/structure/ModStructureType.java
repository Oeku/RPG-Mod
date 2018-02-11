package net.zeldadungeons.world.structure;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;
/** Only usable for custom dimensions
 * @author ArmamentHaki
 */
public class ModStructureType {
    public static final IBlockState DIRT = Blocks.WOOL.getDefaultState();
    public static final IBlockState AIR = Blocks.GLASS.getDefaultState();
    public static final IBlockState GRASS = Blocks.WOOL.getDefaultState();

    
    protected int maxSize[];
    protected int minSize[];
    
    /**
     * Called to generate parts of this structure. It uses a single structure as Reference. Only generates if
     * the structure isn't finished.
     * @param structure The ModStructure instance. Contains useful information such as where to generate,
     * how many pieces this structure contains, or which chunks to load.
     */
    public void generate(ModStructure structure){
	
    }
    
    public ModStructureType(){
	this.init();
    }
    
    /** Set the data for this Structure Type */
    public void init(){
	this.setSizes(new int[]{0, 0, 0}, new int[]{0, 0, 0});
    }
    
    public void setSizes(int[] maxSizes, int[] minSizes){
	this.maxSize = maxSizes;
	this.minSize = minSizes;
    } 
    
    /**
     * Can this structure generate at the given Position?
     * @param The Position to be checked.
     * @return True or False! ;-;
     */
    public static boolean canGenerateAt(BlockPos pos){
	return true;
    }
    
    /** Creates a new structure at a given chunk position, if it is suitable.
     * 
     * @param chunkX
     * @param chunkZ
     * @return The ModStructure, if a structure was created in this chunk. Else null !!! Add != null check in CG !!!.
     */
    public ModStructure createNewStructures(Chunk chunk, int chunkX, int chunkZ, IChunkGenerator cg, Random rand) {
	return null;
    }
}
