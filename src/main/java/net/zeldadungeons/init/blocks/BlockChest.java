package net.zeldadungeons.init.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;

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
}
