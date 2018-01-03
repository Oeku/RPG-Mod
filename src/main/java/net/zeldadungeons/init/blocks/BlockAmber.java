package net.zeldadungeons.init.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.zeldadungeons.capability.playerlevels.CapabilityPlayerLevels;
import net.zeldadungeons.capability.playerlevels.IPlayerLevels;
import net.zeldadungeons.init.Itemizer;

public class BlockAmber extends BlockBase{

	protected static final AxisAlignedBB BOUNDING = new AxisAlignedBB(0.45D, 0.0D, 0.4D, 0.55D, 0.15D, 0.6D);
	
	public BlockAmber() {
		super(Material.ROCK, MapColor.BROWN, "amber_block");
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		if(worldIn.isRemote)return false;
        worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
        Block.spawnAsEntity(worldIn, pos, new ItemStack(Itemizer.AMBER));
        IPlayerLevels cap = playerIn.getCapability(CapabilityPlayerLevels.PLAYER_LEVELS_CAPABILITY, CapabilityPlayerLevels.DEFAULT_FACING);
        
        
        return true;
    }
	
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
	{
		if(worldIn.getBlockState(pos.add(0, -1, 0)).getBlock() == Blocks.STONE)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BlockAmber.BOUNDING;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
}
