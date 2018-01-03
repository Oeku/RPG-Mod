package net.zeldadungeons;

import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.zeldadungeons.client.gui.overlay.GuiOverlay;
import net.zeldadungeons.client.render.RenderHandler;
import net.zeldadungeons.init.Blockizer;

public class ClientProxy extends CommonProxy 
{
	@Override
	public void registerRenders()
	{

		Blockizer.renderBlocks();	
		}
	
	@Override
	public void registerKeys()
	{
		
	}
	
	
	public void registerClientEvents()
	{
	}
	
	@Override
	public void registerEntityRenders()
	{
		RenderHandler.register();
	}
	
	@Override
	public void registerObjInstance()
	{
		OBJLoader.INSTANCE.addDomain(ZeldaDungeons.MODID);
	}
	
	@Override
	public void registerBusses(){
    	MinecraftForge.EVENT_BUS.register(GuiOverlay.INSTANCE);
	}
	
}
