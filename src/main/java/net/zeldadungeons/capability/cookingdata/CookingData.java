package net.zeldadungeons.capability.cookingdata;

import net.minecraft.item.Item;
import net.zeldadungeons.init.cooking.ECookingEffect;
import net.zeldadungeons.init.cooking.IngredientData;

public class CookingData implements ICookingData {

    private IngredientData ingredientData;
    private Item item;

    public CookingData(Item item) {
	this.setData(ingredientDataFrom(item));
	this.item = item;
    }

    public CookingData() {
    }

    @Override
    public void setData(IngredientData data) {
	this.ingredientData = data;
    }

    @Override
    public IngredientData getData() {
	return this.ingredientData;
    }

    @Override
    public Item getItem() {
	return this.item;
    }

    @Override
    public void setItem(Item item) {
	this.item = item;
	this.ingredientData = ingredientDataFrom(item);
    }

    public static IngredientData ingredientDataFrom(Item item) {
	IngredientData data = new IngredientData(new ECookingEffect[] { ECookingEffect.NEUTRAL }, new int[] { 1 });
	return data;
    }

}
