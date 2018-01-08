package net.zeldadungeons.init.blocks;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraftforge.common.DimensionManager;
import net.zeldadungeons.init.Dimensionizer;
import net.zeldadungeons.init.Generizer;
import net.zeldadungeons.util.Log;
import net.zeldadungeons.world.TeleporterCustom;

public class BlockChest extends BlockBase{

	public static final IProperty<Boolean> OPEN = PropertyBool.create("open");
	
	public BlockChest(final Material material) {
		super(material, "chest");
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, OPEN);
	}
	
	@Override
	public int getMetaFromState(final IBlockState state) {
		int i = 0;
		if(state.getValue(OPEN) == true)i=1;
		return i;
	}

	@SuppressWarnings("deprecation")
	@Override
	public IBlockState getStateFromMeta(final int meta) {
		boolean b = false;
		if(meta == 1)b = true;
		return getDefaultState().withProperty(OPEN, b);
	}

	
	@Override
	public int damageDropped(final IBlockState state) {
		return getMetaFromState(state);
	}
	
	  @Override
	    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(worldIn.isRemote)return false;
		worldIn.getMinecraftServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP) playerIn, Dimensionizer.medievalID, new TeleporterCustom(worldIn.getMinecraftServer().getWorld(Dimensionizer.medievalID)));
		return true;
	    }
}
