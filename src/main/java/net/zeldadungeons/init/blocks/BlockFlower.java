package net.zeldadungeons.init.blocks;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.zeldadungeons.init.Blockizer;
import net.zeldadungeons.init.blocks.BlockPressureSwitch.EnumType;
import net.zeldadungeons.util.IVariant;

public class BlockFlower extends BlockFoliage {
    public static final IProperty<EnumType> TYPE = PropertyEnum.create("type", EnumType.class);

    public BlockFlower() {
	super(Material.PLANTS);
	this.setRegistryName("flower");
	this.setUnlocalizedName("flower");
	this.setDefaultState(getBlockState().getBaseState().withProperty(TYPE, EnumType.RED_COLUMBINE));
    }

    @Override
    public int getMetaFromState(final IBlockState state) {
	return state.getValue(TYPE).getMeta();
    }

    @SuppressWarnings("deprecation")
    @Override
    public IBlockState getStateFromMeta(final int meta) {
	return getDefaultState().withProperty(TYPE, EnumType.byMetadata(meta));
    }

    @Override
    protected BlockStateContainer createBlockState() {
	return new BlockStateContainer(this, TYPE);
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
	return getStateFromMeta(meta);
    }
    
    @Override
    public int damageDropped(final IBlockState state) {
	return getMetaFromState(state);
    }

    public enum EnumType implements IVariant {
	RED_COLUMBINE(0, "red_columbine"), CYAN_COLUMBINE(1, "cyan_columbine"), PINK_COLUMBINE(2, "pink_columbine");

	private static final EnumType[] META_LOOKUP = Stream.of(values()).sorted(Comparator.comparing(EnumType::getMeta)).toArray(EnumType[]::new);

	private final int meta;
	private final String name;

	EnumType(final int meta, final String name) {
	    this.meta = meta;
	    this.name = name;
	}

	@Override
	public int getMeta() {
	    return meta;
	}

	@Override
	public String getName() {
	    return name;
	}

	public static EnumType byMetadata(int meta) {
	    if (meta < 0 || meta >= META_LOOKUP.length) {
		meta = 0;
	    }

	    return META_LOOKUP[meta];
	}
    }

}