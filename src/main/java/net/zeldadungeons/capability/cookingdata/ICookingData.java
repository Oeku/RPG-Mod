package net.zeldadungeons.capability.cookingdata;

import net.minecraft.item.Item;
import net.zeldadungeons.init.cooking.ECookingEffect;
import net.zeldadungeons.init.cooking.IngredientData;

public interface ICookingData {
    
    CookingDataContainer getContainer();
    void setContainer(CookingDataContainer c);
}
