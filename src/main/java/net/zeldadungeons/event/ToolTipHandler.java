package net.zeldadungeons.event;

import java.util.List;

import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.zeldadungeons.capability.cookingdata.CapabilityCookingData;
import net.zeldadungeons.capability.cookingdata.CookingDataContainer;
import net.zeldadungeons.init.items.ItemModFood;
import net.zeldadungeons.init.items.armor.ItemCustomArmor;

public class ToolTipHandler {
    public static final ToolTipHandler INSTANCE = new ToolTipHandler();

    @SubscribeEvent
    public void onToolTip(ItemTooltipEvent e) {
	List<String> tooltip = e.getToolTip();
	if (e.getItemStack().getItem() instanceof ItemCustomArmor) {
	    tooltip.clear();
	    ((ItemCustomArmor) e.getItemStack().getItem()).addToolTip(e.getToolTip(), e.getItemStack());
	}
	else if(e.getItemStack().getItem() instanceof ItemModFood){
	    
	    CookingDataContainer cooktainer = e.getItemStack().getCapability(CapabilityCookingData.COOKING, CapabilityCookingData.DEFAULT_FACING).getContainer();
	    tooltip.clear();
	    tooltip.add(cooktainer.getName());
	    tooltip.addAll(cooktainer.getParameters());
	    if(cooktainer.isPerfect())tooltip.add("$5PERFECT");
	}
    }
}
