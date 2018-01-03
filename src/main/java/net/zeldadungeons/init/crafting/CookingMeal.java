package net.zeldadungeons.init.crafting;

import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class CookingMeal extends IForgeRegistryEntry.Impl<CookingMeal> {
    /** A List of Ingredients that the given cooking recipe needs to have
     * in order for this Meal to be a valid result.
     */
    private Item[] ingredients;
    /** The amount of Items for each ingredient that are allowed. **/
    private int[][] value;
    private String displayName;
    private int saturation;
    private int health;
    private int stamina;
    
    public CookingMeal(String name, String displayName){
	this.setRegistryName(new ResourceLocation("cooking_meal:"+name));
	this.displayName = name;
    }
    
    public CookingMeal setIngredients(Item... items){
	this.ingredients = items;
	return this;
    }
    
    public CookingMeal setValues(int[] min, int[] max){
	this.value = new int[][]{min, max};
	return this;
    }
    
    public String getDisplayName(){
	return this.displayName;
    }
    
    public boolean isApplicableFor(List<Item> itemsIn){
	for(int i = 0; i < ingredients.length; i++){
	    int occurences = 0;
	    for(Item item : itemsIn){
		if(ingredients[i] == item){
		    occurences++;
		}
	    }
	}
	return false;
    }
  
}
