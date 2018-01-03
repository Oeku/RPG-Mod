package net.zeldadungeons.network;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.zeldadungeons.client.gui.GuiPressureSwitch;
import net.zeldadungeons.init.blocks.BlockPressureSwitch;

public class GuiHandler implements IGuiHandler
{
	public static final int GUI_PRESSURE_SWITCH = 1;
	
	@Nullable
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	@Nullable
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID){
		case GUI_PRESSURE_SWITCH:
			Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
			if(block instanceof BlockPressureSwitch)
			{
				return new GuiPressureSwitch((BlockPressureSwitch)block, x, y, z);
			}
		}
		return null;
	}
	
}
