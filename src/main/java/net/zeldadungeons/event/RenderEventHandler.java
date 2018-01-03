package net.zeldadungeons.event;

import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.zeldadungeons.client.gui.overlay.GuiOverlay;

@EventBusSubscriber
public class RenderEventHandler {

    public static final RenderEventHandler INSTANCE = new RenderEventHandler();

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRenderPlayer(RenderPlayerEvent.Pre e) {

    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRenderPlayer(RenderPlayerEvent.Post e) {

    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRenderOverlay(RenderGameOverlayEvent.Post e) {
	GuiIngameForge.renderHealth = false;
	GuiIngameForge.renderArmor = false;
	if (e.getType() == RenderGameOverlayEvent.ElementType.EXPERIENCE) {
	    GuiOverlay.INSTANCE.onRenderGameOverlay(e.getResolution());
	}
    }
}
