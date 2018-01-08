package net.zeldadungeons.init.cooking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.zeldadungeons.ZeldaDungeons;
import net.zeldadungeons.capability.cookingdata.CapabilityCookingData;
import net.zeldadungeons.capability.cookingdata.CookingDataContainer;
import net.zeldadungeons.init.Mealizer;

public class CookingManager {

    public static void updateItemCookingCap(ItemStack stack, CookingMeal meal, List<CookingEffectAmpl> effects, int dominantEffects) {
	int heal = 0;
	int saturation = 0;
	int stamina = 0;
	List<String> params = Lists.newArrayList();
	for (CookingEffectAmpl eff : effects) {
	    params.add(eff.getEffect().getDisplay());
	    // values above 10 boost, values below ten backboost.
	    // negative high values stay as they are.
	    float f = (float) eff.getAmpl();
	    if (f > 0F) f *= 0.1F;
	    switch (eff.getEffect()) {
	    case CHILLY:
		break;
	    case DISGUSTING:
		break;
	    case FIRE:
		break;
	    case HEART:
		heal = (int) (f *= meal.getHealAmount());
		break;
	    case NEUTRAL:
		break;
	    case SATURATING:
		saturation = (int) (f *= meal.getHealAmount() * 2);
		break;
	    case SPICY:
		break;
	    case STAMINA:
		stamina = (int) (f *= meal.getStamina());
		break;
	    default:
		break;
	    }
	    --dominantEffects;
	    if (dominantEffects == 0) break;
	}
	CookingDataContainer c = stack.getCapability(CapabilityCookingData.COOKING, CapabilityCookingData.DEFAULT_FACING).getContainer();
	c.setHealAmount(heal);
	c.setSatAmount(saturation);
	c.setStamAmount(stamina);
	c.setParameters(params);
	c.setName(meal.getDisplayName());
    }

    public static CookingMeal processCooking(List<ItemStack> items, ItemStack stack) {
	CookingMeal finalMeal;
	boolean perfect = false;
	List<CookingMeal> validList = new ArrayList<CookingMeal>();
	List<CookingMeal> perfectList = new ArrayList<CookingMeal>();
	List<CookingMeal> list;
	for (CookingMeal meal : Mealizer.REGISTRY) {
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
	    int j = 0;
	    if (list.size() >= 2) j = ZeldaDungeons.RANDOM.nextInt(list.size() - 1);
	    finalMeal = list.get(j);
	}
	stack.getCapability(CapabilityCookingData.COOKING, CapabilityCookingData.DEFAULT_FACING).getContainer().setPerfect(perfect);
	return finalMeal;
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