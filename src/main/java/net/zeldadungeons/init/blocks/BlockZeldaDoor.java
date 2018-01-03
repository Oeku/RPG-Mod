package net.zeldadungeons.init.blocks;

import java.util.Comparator;
import java.util.stream.Stream;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.zeldadungeons.util.IVariant;

public class BlockZeldaDoor extends BlockBase{
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyBool OPEN = PropertyBool.create("open");
    public static final PropertyEnum LOCK = PropertyEnum.create("lock", EnumType.class);
    		
    public BlockZeldaDoor(final Material materialIn) {
		super(materialIn, "zelda_door");
	}
    
    public enum EnumType implements IVariant {
		UNLOCKED(0, "a"),
		LOCKED(1, "b");
		

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
