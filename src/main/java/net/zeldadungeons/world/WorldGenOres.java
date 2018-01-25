package net.zeldadungeons.world;

import java.util.Random;

import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.zeldadungeons.init.Blockizer;
import net.zeldadungeons.init.Dimensionizer;

public class WorldGenOres implements IWorldGenerator{

	private final CustomGenMinable oreGenSapphire;
	private final CustomGenMinable oreGenSalt;
	private final CustomGenMinable oreGenLuminous;
	private final CustomGenMinable oreGenTopaz;
	private final CustomGenMinable oreGenSilver;
	private final CustomGenMinable oreGenCopper;
	private final CustomGenMinable oreGenTin;
	private final CustomGenMinable oreGenGold;
	
	public WorldGenOres()
	{
		this.oreGenSapphire = new CustomGenMinable(Blockizer.SAPPHIRE_ORE.getDefaultState(), 4, BlockMatcher.forBlock(Blocks.STONE));
		this.oreGenSalt = new CustomGenMinable(Blockizer.SALT_ORE.getDefaultState(), 10, BlockMatcher.forBlock(Blocks.STONE));
		this.oreGenLuminous = new CustomGenMinable(Blockizer.LUMINOUS_ORE.getDefaultState(), 8, BlockMatcher.forBlock(Blocks.STONE));
		this.oreGenTopaz = new CustomGenMinable(Blockizer.TOPAZ_ORE.getDefaultState(), 6, BlockMatcher.forBlock(Blocks.STONE));
		this.oreGenCopper = new CustomGenMinable(Blockizer.COPPER_ORE.getDefaultState(), 12, BlockMatcher.forBlock(Blockizer.MEDIEVAL_STONE));
		this.oreGenSilver = new CustomGenMinable(Blockizer.SILVER_ORE.getDefaultState(), 5, BlockMatcher.forBlock(Blockizer.MEDIEVAL_STONE));
		this.oreGenTin = new CustomGenMinable(Blockizer.TIN_ORE.getDefaultState(), 2, BlockMatcher.forBlock(Blockizer.MEDIEVAL_STONE));
		this.oreGenGold = new CustomGenMinable(Blockizer.GOLD_ORE.getDefaultState(), 10, BlockMatcher.forBlock(Blockizer.MEDIEVAL_STONE));
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		final BlockPos chunkPos = new BlockPos(chunkX*16, 0, chunkZ*16);
		switch(world.provider.getDimensionType()){
		case OVERWORLD:
				genOre(world, random, 2, oreGenSapphire, 0, 16, chunkPos);
				genOre(world, random, 10, oreGenSalt, 0, 160, chunkPos);
				genOre(world, random, 6, oreGenLuminous, 30, 60, chunkPos);
				genOre(world, random, 4, oreGenTopaz, 5, 40, chunkPos);
				//oreGenLuminous.generate(world, random, chunkPos.add(random.nextInt(16), random.nextInt(70)+20, random.nextInt(16)));
		case NETHER:
		case THE_END:		   
		}
		if(world.provider.getDimension() == Dimensionizer.medievalID){
		    genOre(world, random, 3, oreGenCopper, 50, 180, chunkPos);
		    genOre(world, random, 15, oreGenTin, 50, 180, chunkPos);
		    genOre(world, random, 4, oreGenSilver, 0, 50, chunkPos);
		    genOre(world, random, 4, oreGenGold, 0, 40, chunkPos);
		}
	}
	
	protected void genOre(World worldIn, Random random, int blockCount, WorldGenerator generator, int minHeight, int maxHeight, BlockPos chunkPos)
	{
		   if (maxHeight < minHeight)
	        {
	            int i = minHeight;
	            minHeight = maxHeight;
	            maxHeight = i;
	        }
	        else if (maxHeight == minHeight)
	        {
	            if (minHeight < 255)
	            {
	                ++maxHeight;
	            }
	            else
	            {
	                --minHeight;
	            }
	        }

	        for (int j = 0; j < blockCount; ++j)
	        {
	            BlockPos blockpos = chunkPos.add(random.nextInt(16), random.nextInt(maxHeight - minHeight) + minHeight, random.nextInt(16));
	            generator.generate(worldIn, random, blockpos);
	        }
	}
	
	protected void genOre2(World worldIn, Random random, int blockCount, WorldGenerator generator, int centerHeight, int spread, BlockPos chunkPos)
	{
        for (int i = 0; i < blockCount; ++i)
        {
            BlockPos blockpos = chunkPos.add(random.nextInt(16), random.nextInt(spread) + random.nextInt(spread) + centerHeight - spread, random.nextInt(16));
            generator.generate(worldIn, random, blockpos);
        }
	}
	
}
