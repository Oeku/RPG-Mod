package net.zeldadungeons;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.zeldadungeons.client.gui.overlay.GuiOverlay;
import net.zeldadungeons.client.render.RenderHandler;
import net.zeldadungeons.util.Log;

public class CombinedClientProxy implements IProxy {

    public static final Minecraft MINECRAFT = Minecraft.getMinecraft();
    
    @Override
    public void preInit() {
	Log.getLogger().info("proxy");
	OBJLoader.INSTANCE.addDomain(ZeldaDungeons.MODID);
    	MinecraftForge.EVENT_BUS.register(GuiOverlay.INSTANCE);
    }

    @Override
    public void init() {
	RenderHandler.register();
    }

    @Override
    public void postInit() {
	
    }
    
}
