package net.zeldadungeons.world;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.zeldadungeons.init.Blockizer;
import net.zeldadungeons.init.blocks.BlockAmber;

public class WorldGenAmber implements IWorldGenerator {
	
	private BlockAmber block = (BlockAmber) Blockizer.AMBER_BLOCK;
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		final BlockPos chunkPos = new BlockPos(chunkX*16, 0, chunkZ*16);
		switch(world.provider.getDimensionType()){
		case OVERWORLD:
			//Log.getLogger().info("Beginning generation of Amber for chunk "+chunkX+" "+ chunkZ);
			for(int i = 0; i < 40; i++)
			{
				int rx = random.nextInt(16);
				int rz = random.nextInt(16);
				int ry = random.nextInt(60)+5;
				BlockPos pos = new BlockPos(chunkPos.getX()+rx, chunkPos.getY()+ry, chunkPos.getZ()+rz);
				if(world.isAirBlock(pos) && this.block.canBlockStay(world, pos, block.getDefaultState()))
				{
	                world.setBlockState(pos, block.getDefaultState(), 2);
				}
			}
		case NETHER:
		case THE_END:
			//Log.getLogger().info("Ending generation of Amber for this chunk!");
		}
	}
	
}
