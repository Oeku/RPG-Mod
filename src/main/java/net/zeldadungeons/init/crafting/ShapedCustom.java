package net.zeldadungeons.init.crafting;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.NonNullList;

public class ShapedCustom extends ShapedRecipes{

    public ShapedCustom(String name, String group, int width, int height, NonNullList<Ingredient> ingredients, ItemStack result) {
	super(group, width, height, ingredients, result);
	this.setRegistryName(name);
    }
    
}
