package net.zeldadungeons;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.zeldadungeons.client.gui.overlay.GuiOverlay;
import net.zeldadungeons.client.render.RenderHandler;

public class CombinedClientProxy implements IProxy {

    public static final Minecraft MINECRAFT = Minecraft.getMinecraft();
    
    @Override
    public void preInit() {
	RenderHandler.register();
	OBJLoader.INSTANCE.addDomain(ZeldaDungeons.MODID);
    	MinecraftForge.EVENT_BUS.register(GuiOverlay.INSTANCE);
    }

    @Override
    public void init() {
    }

    @Override
    public void postInit() {
	
    }
    
}
