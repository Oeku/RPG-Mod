package net.zeldadungeons.init.items;

import net.zeldadungeons.ZeldaDungeons;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.zeldadungeons.init.blocks.BlockPressureSwitch;

public class ItemBlockEditor extends Item{
	public ItemBlockEditor()
	{
		this.setRegistryName(new ResourceLocation("zeldadungeons:block_editor"));
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!player.isCreative())return EnumActionResult.PASS;
		if(worldIn.getBlockState(pos).getBlock() instanceof BlockPressureSwitch)
		{
			player.openGui(ZeldaDungeons.instance, 1, worldIn, pos.getX(), pos.getY(), pos.getZ());
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.PASS;
	}
}
