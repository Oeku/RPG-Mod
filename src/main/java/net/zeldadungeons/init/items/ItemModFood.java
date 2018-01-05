package net.zeldadungeons.init.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.zeldadungeons.capability.playerlevels.CapabilityPlayerLevels;
import net.zeldadungeons.capability.playerlevels.IPlayerLevels;
import net.zeldadungeons.init.cooking.CookingEffectAmpl;
import net.zeldadungeons.init.cooking.CookingMeal;

public class ItemModFood extends ItemFood {

    private int saturation;
    private int healAmount;
    private int staminaAmount;
    private List<CookingEffectAmpl> effectList;
    
    CookingMeal meal;
    
    public ItemModFood(CookingMeal meal, List<CookingEffectAmpl> dominantEffects, int amount) {
	super(1, 0.6F, false);
	this.maxStackSize = 1;
	this.meal = meal;
	for(CookingEffectAmpl ef : dominantEffects){
	    amount--;
	    if(amount == 0)break;
	}
	this.effectList = dominantEffects;
	
    }
    
    public void addEffect(CookingEffectAmpl effect){
	switch(effect.getEffect()){
	case HEART:
	    this.healAmount = effect.getAmpl()*this.meal.getHealAmount();
	case STAMINA:
	    this.staminaAmount = effect.getAmpl()*this.meal.getStamina();
	case SATURATING:
	    this.saturation = effect.getAmpl()*this.meal.getSaturation();
	
	default:
	    break;
		
	}
    }
    
    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
	if(worldIn.isRemote)return;
	IPlayerLevels cap = player.getCapability(CapabilityPlayerLevels.PLAYER_LEVELS_CAPABILITY, CapabilityPlayerLevels.DEFAULT_FACING);
	cap.getHealthSkill().heal(this.healAmount);
	cap.getStaminaSkill().addStamina(this.staminaAmount);
    }
    
    
}
