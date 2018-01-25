package net.zeldadungeons.world.biome.medieval;

import java.util.Random;

import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.NoiseGeneratorSimplex;
import net.zeldadungeons.init.Blockizer;
import net.zeldadungeons.init.blocks.BlockFlower;

public class BMedievalHills extends Biome {
    public static final NoiseGeneratorSimplex TEMPNOISE = new NoiseGeneratorSimplex();

    protected static final IBlockState MEDSTONE = Blockizer.MEDIEVAL_STONE.getDefaultState();
    protected static final IBlockState COBBLE = Blocks.COBBLESTONE.getDefaultState();
    protected static final IBlockState SAND = Blocks.SAND.getDefaultState();
    protected static final IBlockState SNOW = Blocks.SNOW.getDefaultState();
    protected static final IBlockState GRASS = Blocks.GRASS.getDefaultState();

    protected static final IBlockState RED_COLUMBINE = Blockizer.FLOWER.getDefaultState().withProperty(BlockFlower.TYPE, BlockFlower.EnumType.RED_COLUMBINE);
    protected static final IBlockState CYAN_COLUMBINE = Blockizer.FLOWER.getDefaultState().withProperty(BlockFlower.TYPE, BlockFlower.EnumType.CYAN_COLUMBINE);
    protected static final IBlockState PINK_COLUMBINE = Blockizer.FLOWER.getDefaultState().withProperty(BlockFlower.TYPE, BlockFlower.EnumType.PINK_COLUMBINE);
    protected static final IBlockState TALLGRASS = Blocks.TALLGRASS.getDefaultState().withProperty(BlockTallGrass.TYPE, BlockTallGrass.EnumType.GRASS);

    public BMedievalHills(Biome.BiomeProperties prop) {
	super(prop);
    }

    @Override
    public void decorate(World worldIn, Random rand, BlockPos pos) {

    }

    @Override
    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
	this.genBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
    }

    public boolean isNorth(int x, int z, Random rand) {
	return z < (-5000 + rand.nextInt(100) * TEMPNOISE.getValue(x*0.001D, 5000));
    }

    public boolean isSouth(int x, int z, Random rand) {
	return z > (5000 + rand.nextInt(100) * TEMPNOISE.getValue(x*0.001D, 5000));
    }

    public boolean canSnow(int x, int z, Random rand) {
	return z < (3000 + rand.nextInt(4));
    }

    public void genBiomeTerrain(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
	boolean flag = isNorth(x, z, rand);
	boolean flag1 = isSouth(x, z, rand);
	boolean flag2 = canSnow(x, z, rand);
	int i = worldIn.getSeaLevel();
	IBlockState iblockstate = this.topBlock;
	IBlockState iblockstate1 = this.fillerBlock;
	if (flag) {
	    iblockstate = SNOW;
	    iblockstate1 = SNOW;
	}
	else if (flag1) {
	    iblockstate = SAND;
	    iblockstate1 = SANDSTONE;
	}
	int j = -1;
	int k = (int) (noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
	int l = x & 15;
	int i1 = z & 15;
	BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

	for (int j1 = 255; j1 >= 0; --j1) {
	    if (j1 <= rand.nextInt(5)) {
		chunkPrimerIn.setBlockState(i1, j1, l, BEDROCK);
	    }
	    else {
		IBlockState iblockstate2 = chunkPrimerIn.getBlockState(i1, j1, l);

		if (iblockstate2.getMaterial() == Material.AIR) {
		    j = -1;
		}
		else if (iblockstate2.getBlock() == Blockizer.MEDIEVAL_STONE) {
		    if (j == -1) {
			if (k <= 0) {
			    iblockstate = AIR;
			    iblockstate1 = MEDSTONE;
			    if (j1 > 140 && flag2) iblockstate1 = SNOW;
			}
			else if (j1 >= i - 4 && j1 <= i + 1) {
			    //iblockstate = this.topBlock;
			    //iblockstate1 = this.fillerBlock;
			}
			if (iblockstate != null && iblockstate != WATER && j1 <= i && j1 >= i - 5 && !flag && !flag1) {
			    iblockstate = SAND;
			    iblockstate1 = SAND;
			}
			if (iblockstate1 != null && j1 > 121 + rand.nextInt(3) && iblockstate != AIR) {
			    if (j1 > 137 + rand.nextInt(3) && flag2) {
				iblockstate = SNOW;
				iblockstate1 = SNOW;
			    }
			    else {
				int rand1 = rand.nextInt(3);
				if (rand1 == 0) {
				    iblockstate = STONE;
				    iblockstate1 = STONE;
				}
				else if (rand1 == 1) {
				    iblockstate = MEDSTONE;
				    iblockstate1 = MEDSTONE;
				}
				else if (rand1 == 2) {
				    iblockstate = COBBLE;
				    iblockstate1 = COBBLE;
				}
			    }
			}
			if (j1 < i && (iblockstate == null || iblockstate.getMaterial() == Material.AIR)) {
			    if (flag) {
				iblockstate = ICE;
			    }
			    else {
				iblockstate = WATER;
			    }
			}

			j = k;

			if (j1 >= i - 1) {
			    chunkPrimerIn.setBlockState(i1, j1, l, iblockstate);
			}
			else if (j1 < i - 7 - k) {
			    iblockstate = AIR;
			    iblockstate1 = MEDSTONE;
			    chunkPrimerIn.setBlockState(i1, j1, l, GRAVEL);
			}
			else {
			    chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);
			}
		    }
		    else if (j > 0) {
			--j;
			chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);

			if (j == 0 && iblockstate1.getBlock() == Blocks.SAND && k > 1) {
			    j = rand.nextInt(4) + Math.max(0, j1 - 63);
			    iblockstate1 = iblockstate1.getValue(BlockSand.VARIANT) == BlockSand.EnumType.RED_SAND ? RED_SANDSTONE : SANDSTONE;
			}
		    }
		}
	    }
	}
    }
}
