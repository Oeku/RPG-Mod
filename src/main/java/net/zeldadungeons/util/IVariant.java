package net.zeldadungeons.util;

import net.minecraft.util.IStringSerializable;

/**
 * An interface representing a block or item variant.
 *
 */
public interface IVariant extends IStringSerializable {

	/**
	 * Get the metadata value of this variant.
	 *
	 * @return The metadata value
	 */
	int getMeta();
}

