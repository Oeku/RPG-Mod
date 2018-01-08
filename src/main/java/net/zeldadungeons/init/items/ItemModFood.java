package net.zeldadungeons.init.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.zeldadungeons.capability.cookingdata.CapabilityCookingData;
import net.zeldadungeons.capability.cookingdata.CookingDataContainer;
import net.zeldadungeons.capability.playerlevels.CapabilityPlayerLevels;
import net.zeldadungeons.capability.playerlevels.IPlayerLevels;
import net.zeldadungeons.init.cooking.CookingEffectAmpl;
import net.zeldadungeons.init.cooking.CookingMeal;

public class ItemModFood extends ItemFood {

    
    public ItemModFood() {
	super(1, 0.6F, false);
	this.maxStackSize = 1;
	this.setRegistryName("meal");
    }
    
    
    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
	if(worldIn.isRemote)return;
	CookingDataContainer cook = stack.getCapability(CapabilityCookingData.COOKING, CapabilityCookingData.DEFAULT_FACING).getContainer();
	IPlayerLevels cap = player.getCapability(CapabilityPlayerLevels.PLAYER_LEVELS_CAPABILITY, CapabilityPlayerLevels.DEFAULT_FACING);
	cap.getHealthSkill().heal(cook.getHealAmount());
	cap.getStaminaSkill().addStamina(cook.getStamAmount());
	player.getFoodStats().addStats(cook.getSatAmount(), 1F);
    }
}
