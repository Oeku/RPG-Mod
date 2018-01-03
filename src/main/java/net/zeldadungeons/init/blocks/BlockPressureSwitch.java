package net.zeldadungeons.init.blocks;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.zeldadungeons.util.IVariant;

@EventBusSubscriber
public class BlockPressureSwitch extends BlockBase {
	private boolean needHeavy;
	private int ticksActivated;

	public static final IProperty<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);
	protected static final AxisAlignedBB PRESSURE_AABB = new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.5D, 0.9D);
	protected static final AxisAlignedBB PRESSED_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.1D,
			0.9375D);
	protected static final AxisAlignedBB UNPRESSED_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.4D,
			0.9375D);

	public BlockPressureSwitch(final Material materialIn) {
		super(materialIn, "pressure_switch");
		this.needHeavy = false;
		this.ticksActivated = 0;

	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, VARIANT);
	}

	@SuppressWarnings("deprecation")
	@Override
	public IBlockState getStateFromMeta(final int meta) {
		return getDefaultState().withProperty(VARIANT, EnumType.byMetadata(meta));
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public int getMetaFromState(final IBlockState state) {
		return state.getValue(VARIANT).getMeta();
	}

	@Override
	public int damageDropped(final IBlockState state) {
		return getMetaFromState(state);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isRemote) {
			AxisAlignedBB aabb = PRESSURE_AABB.offset(pos);
			List<? extends Entity> list;
			list = worldIn.getEntitiesWithinAABBExcludingEntity((Entity) null, aabb);
			boolean b = false;
			for (Entity entity : list) {
				if (!this.needHeavy) {
					if (entity instanceof EntityPlayer)
						b = true;
				} else {

				}
			}
			if (b)worldIn.setBlockState(pos, worldIn.getBlockState(pos).getBlock().getDefaultState().withProperty(VARIANT,EnumType.VARIANT_B));
			else worldIn.setBlockState(pos, getDefaultState().withProperty(VARIANT, EnumType.VARIANT_A));
		}
		worldIn.scheduleBlockUpdate(pos, state.getBlock(), 2, 1);
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.scheduleBlockUpdate(pos, state.getBlock(), 1, 1);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		if (state.getValue(VARIANT).getMeta() == 0)
			return UNPRESSED_AABB;
		else
			return PRESSED_AABB;
	}

	@Override
	public boolean onBlockActivated(final World worldIn, final BlockPos pos, final IBlockState state,
			final EntityPlayer playerIn, final EnumHand hand, final EnumFacing facing, final float hitX,
			final float hitY, final float hitZ) {
		return worldIn.setBlockState(pos, state.cycleProperty(VARIANT));
	}

	@Override
	public void getSubBlocks(final CreativeTabs tab, final NonNullList<ItemStack> list) {
		for (final EnumType enumType : EnumType.values()) {
			list.add(new ItemStack(this, 1, enumType.getMeta()));
		}
	}

	/**
	 * Get the unlocalised name suffix for the specified {@link ItemStack}.
	 *
	 * @param stack
	 *            The ItemStack
	 * @return The unlocalised name suffix
	 */
	public String getName(final ItemStack stack) {
		final int metadata = stack.getMetadata();

		return EnumType.byMetadata(metadata).getName();
	}

	public void setHeavy(boolean heavy) {
		this.needHeavy = heavy;
	}

	public void setTicks(int ticks) {
		this.ticksActivated = ticks;
	}

	public enum EnumType implements IVariant {
		VARIANT_A(0, "a"), VARIANT_B(1, "b");

		private static final EnumType[] META_LOOKUP = Stream.of(values())
				.sorted(Comparator.comparing(EnumType::getMeta)).toArray(EnumType[]::new);

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
