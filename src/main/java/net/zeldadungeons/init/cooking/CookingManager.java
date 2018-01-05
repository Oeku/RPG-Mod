package net.zeldadungeons.init.cooking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.zeldadungeons.ZeldaDungeons;
import net.zeldadungeons.init.Mealizer;
import net.zeldadungeons.init.items.ItemModFood;

public class CookingManager {
    public static ItemModFood processCooking(List<ItemStack> items, List<CookingEffectAmpl> effects, int dominantEffects) {
	CookingMeal finalMeal;
	boolean perfect = false;
	List<CookingMeal> validList = new ArrayList<CookingMeal>();
	List<CookingMeal> perfectList = new ArrayList<CookingMeal>();
	List<CookingMeal> list;
	for (CookingMeal meal : Mealizer.REGISTRY) {
	    if (meal == Mealizer.DISGUSTING) continue;
	    EMealValid valid = meal.areItemsValid(items);
	    if (valid == EMealValid.VALID) {
		validList.add(meal);
	    }
	    else if (valid == EMealValid.PERFECT) {
		perfectList.add(meal);
	    }
	}
	if (perfectList.size() != 0) {
	    list = perfectList;
	    perfect = true;
	}
	else list = validList;
	if (list.size() == 0) {
	    finalMeal = Mealizer.DISGUSTING;
	}
	else {
	    finalMeal = list.get(ZeldaDungeons.RANDOM.nextInt(list.size() - 1));
	}
	return new ItemModFood(finalMeal, effects, dominantEffects);
    }

    public static List<CookingEffectAmpl> combineEffects(List<CookingEffectAmpl> effects) {
	List<CookingEffectAmpl> list = new ArrayList<CookingEffectAmpl>();
	ECookingEffect[] values = ECookingEffect.values();
	for (int i = 0; i < values.length; i++) {
	    int currentAmplifier = 0;
	    for (CookingEffectAmpl c : effects) {
		if (c.getEffect() == values[i]) {
		    currentAmplifier += c.getAmpl();
		}
	    }
	    CookingEffectAmpl cea = new CookingEffectAmpl(values[i], currentAmplifier);
	    list.add(cea);
	}
	Collections.sort(list);
	Collections.reverse(list);
	return list;
    }

}