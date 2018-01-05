package net.zeldadungeons.init.cooking;

import java.util.ArrayList;
import java.util.List;

public class IngredientData {

    private List<CookingEffectAmpl> effects;

    public IngredientData() {
    }

    public IngredientData(ECookingEffect[] ef, int[] am) {
	effects = new ArrayList<CookingEffectAmpl>();
	for (int i = 0; ef.length > i; i++) {
	    this.effects.add(new CookingEffectAmpl(ef[i], am[i]));
	}
    }
    
    public List<CookingEffectAmpl> getEffectList() {
	return this.effects;
    }
}

