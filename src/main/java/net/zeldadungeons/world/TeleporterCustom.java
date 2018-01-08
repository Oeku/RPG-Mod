package net.zeldadungeons.world;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterCustom extends Teleporter {

    public TeleporterCustom(WorldServer worldIn) {
	super(worldIn);
    }

    @Override
    public void placeInPortal(Entity entityIn, float rotationYaw) {
	entityIn.setPosition(0, 100.5D, 0);
	entityIn.getEntityWorld().setBlockState(entityIn.getPosition().down(), Blocks.BRICK_BLOCK.getDefaultState());
    }
}
