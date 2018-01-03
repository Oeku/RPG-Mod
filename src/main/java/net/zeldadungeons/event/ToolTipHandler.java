package net.zeldadungeons.event;

import java.util.List;

import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.zeldadungeons.init.items.armor.ItemCustomArmor;

public class ToolTipHandler {
    public static final ToolTipHandler INSTANCE = new ToolTipHandler();

    @SubscribeEvent
    public void onToolTip(ItemTooltipEvent e) {
	if (e.getItemStack().getItem() instanceof ItemCustomArmor) {
	    List<String> tooltip = e.getToolTip();
	    tooltip.clear();
	    ((ItemCustomArmor) e.getItemStack().getItem()).addToolTip(e.getToolTip(), e.getItemStack());

	}
    }
}
