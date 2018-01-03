package net.zeldadungeons.util;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;

/**
 * A useful interface which makes things easier for me =)
 * 
 * @author diesieben07
 * 
 */
public interface MeshDefinitionFix extends ItemMeshDefinition {
	ModelResourceLocation getLocation(final ItemStack stack);

	static ItemMeshDefinition create(final MeshDefinitionFix lambda) {
		return lambda;
	}
	
	@Override
	default ModelResourceLocation getModelLocation(final ItemStack stack){
		return getLocation(stack);
	}
}


