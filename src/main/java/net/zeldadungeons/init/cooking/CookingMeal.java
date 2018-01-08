package net.zeldadungeons.init.cooking;

import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.zeldadungeons.ZeldaDungeons;
import net.zeldadungeons.util.ItemStackUtil;

public class CookingMeal extends IForgeRegistryEntry.Impl<CookingMeal> {
    /**
     * A List of Ingredients that the given cooking recipe needs to have in
     * order for this Meal to be a valid result.
     */
    private Item[] ingredients;
    /** The amount of Items for each ingredient that are allowed. **/
    private int[][] value;
    private String displayName;
    /**Base Values to modify**/
    private int saturation;
    private int health;
    private int stamina;
    
    public CookingMeal(String name, String displayName) {
	this.setRegistryName(new ResourceLocation(ZeldaDungeons.MODID, "meal." + name));
	this.displayName = name;
    }

    public CookingMeal setIngredients(Item... items) {
	this.ingredients = items;
	return this;
    }

    /**
     * Sets the values which indicate the count
     * @param min
     * @param perfect
     * @param max
     * @return
     */
    public CookingMeal setValues(int[] min, int[]perfect, int[] max) {
	this.value = new int[][] { min, perfect, max};
	return this;
    }
    
    public CookingMeal setBase(int saturation, int health, int stamina){
	this.saturation = saturation;
	this.health = health;
	this.stamina = stamina;
	return this;
    }
    
    public int getHealAmount(){
	return this.health;
    }
    
    public int getSaturation(){
	return this.saturation;
    }
    
    public int getStamina(){
	return this.stamina;
    }

    public String getDisplayName() {
	return this.displayName;
    }
    /**
     * Checks if this is a valid Meal for the given Recipe
     * @param itemsIn a list of items, usually a cooking_pot's contents
     * @return VALID if the input can create this recipe, PERFECT if the input has perfect 
     * conditions for this recipe and INVALID if the input can not cook
     * this recipe.
     */
    public EMealValid areItemsValid(List<ItemStack> itemsIn) {
	int perfect = 0;
	for (int i = 0; i < ingredients.length; i++) {
	    int occurences = 0;
	    ItemStack stack = ItemStackUtil.getStackContains(itemsIn, ingredients[i]);
	    if(stack != null)occurences = stack.getCount();
	    if (occurences >= value[0][i] && occurences <= value[2][i]) {
		if(occurences==value[1][i])perfect++;
		continue;
	    }
	    return EMealValid.INVALID;
	}
	if(perfect == this.ingredients.length)return EMealValid.PERFECT;
	else return EMealValid.VALID;
    }
}
