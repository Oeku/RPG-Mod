package net.zeldadungeons.world.biome.medieval;

import java.util.Random;

import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter.Yellow;

import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.zeldadungeons.init.Blockizer;
import net.zeldadungeons.init.blocks.BlockFlower;
import net.zeldadungeons.util.Log;

public class BMedievalFlowerField extends BMedievalHills {

    public BMedievalFlowerField(BiomeProperties prop) {
	super(prop);
    }

    @Override
    public void decorate(World worldIn, Random rand, BlockPos pos) {
	super.decorate(worldIn, rand, pos);
	for(int i = 0; i < 4; i++){
	    int xOff = rand.nextInt(16);
	    int zOff = rand.nextInt(16);
	    this.TREE_FEATURE.generate(worldIn, rand, new BlockPos(xOff+pos.getX()+8, worldIn.getHeight(xOff+pos.getX(), zOff+pos.getZ()), zOff+pos.getZ()+8));
	}
	for (int i = 0; i < 50; i++) {
	    int xOff = rand.nextInt(16);
	    int zOff = rand.nextInt(16);
	    tryFlowerAt(worldIn, rand, new BlockPos(xOff+pos.getX(), worldIn.getHeight(xOff+pos.getX(), zOff+pos.getZ())-1, zOff+pos.getZ()), 3);
	}
	for (int i = 0; i < 20; i++) {
	    int xOff = rand.nextInt(16);
	    int zOff = rand.nextInt(16);
	    tryFlowerAt(worldIn, rand, new BlockPos(xOff+pos.getX(), worldIn.getHeight(xOff+pos.getX(), zOff+pos.getZ())-1, zOff+pos.getZ()), 0);
	}
    }

    public void tryFlowerAt(World worldIn, Random rand, BlockPos pos, int i2) {
	if (worldIn.getBlockState(pos) == GRASS && (worldIn.getBlockState(pos.up()) == AIR || worldIn.getBlockState(pos.up()) == TALLGRASS) && pos.getY() < 105) {
	    int i = rand.nextInt(3)+i2;
	    IBlockState flowerState;
	    if(i > 2 )flowerState = TALLGRASS;
	    else if (i == 0) flowerState = RED_COLUMBINE;
	    else if (i == 1) flowerState = CYAN_COLUMBINE;
	    else flowerState = PINK_COLUMBINE;
	    worldIn.setBlockState(pos.up(), flowerState);
	    Log.getLogger().info("generated flower at"+ pos.getX()+" "+ pos.getZ());
	}
	pos.down();
    }
}
