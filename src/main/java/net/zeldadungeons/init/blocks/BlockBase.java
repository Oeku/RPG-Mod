package net.zeldadungeons.init.blocks;

import net.zeldadungeons.ZeldaDungeons;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class BlockBase extends Block {
	public BlockBase(final Material material, final MapColor mapColor, final String blockName) {
		super(material, mapColor);
		setBlockName(this, blockName);
	}

	public BlockBase(final Material materialIn, final String blockName) {
		this(materialIn, materialIn.getMaterialMapColor(), blockName);
	}

	public static void setBlockName(final Block block, final String blockName) {
		block.setRegistryName(ZeldaDungeons.MODID, blockName);
		block.setUnlocalizedName(block.getRegistryName().toString());
	}
}

