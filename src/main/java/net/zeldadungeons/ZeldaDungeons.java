package net.zeldadungeons;

import java.util.Random;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.zeldadungeons.capability.CapabilityHandler;
import net.zeldadungeons.event.RenderEventHandler;
import net.zeldadungeons.event.SpawnEventHandler;
import net.zeldadungeons.event.TickEventHandler;
import net.zeldadungeons.event.ToolTipHandler;
import net.zeldadungeons.init.Generizer;
import net.zeldadungeons.init.Itemizer;
import net.zeldadungeons.init.Recipizer;
import net.zeldadungeons.network.GuiHandler;
import net.zeldadungeons.network.NetworkHandler;
import net.zeldadungeons.util.Log;

@Mod(modid = ZeldaDungeons.MODID, name = ZeldaDungeons.NAME, version = ZeldaDungeons.VERSION)
public class ZeldaDungeons
{
    public static final String MODID = "zeldadungeons";
    public static final String VERSION = "alpha 1.0";
    public static final String NAME = "zeldadungeons";
    public static final String SERVER_PROXY_CLASS = "net.zeldadungeons.DedicatedServerProxy";
    public static final String CLIENT_PROXY_CLASS = "net.zeldadungeons.CombinedClientProxy";
    public static final RegistryBuilder regBuilder = new RegistryBuilder();
    
    @Mod.Instance(MODID)
    public static ZeldaDungeons instance = new ZeldaDungeons();
    
    @SidedProxy(serverSide = SERVER_PROXY_CLASS, clientSide = CLIENT_PROXY_CLASS)
    public static IProxy proxy;
    public static final EventBus EVENT_BUS = new EventBus(); 
    
    public static final Random RANDOM = new Random();
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	/**Testing Area**/
	/**End**/
    	Log.logString("PreInitialization - Arpg");
    	CapabilityHandler.INSTANCE.registerCapabilities();
    	NetworkHandler.init();
    	proxy.init();
    	this.registerBusses();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	Log.logString("Loading - Arpg");
    	NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    	Generizer.registerWorldGenerators();
    	Recipizer.registerSmeltings();
    	proxy.init();    	
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
	proxy.postInit();
    	Log.logString("PostInitialization - Arpg");
    }
    
    public void registerBusses()
    {
    	MinecraftForge.EVENT_BUS.register(TickEventHandler.INSTANCE);
    	MinecraftForge.EVENT_BUS.register(RenderEventHandler.INSTANCE);
    	MinecraftForge.EVENT_BUS.register(CapabilityHandler.INSTANCE);
    	MinecraftForge.EVENT_BUS.register(SpawnEventHandler.INSTANCE);
    }

}
