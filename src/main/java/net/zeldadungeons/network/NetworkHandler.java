package net.zeldadungeons.network;

import net.zeldadungeons.ZeldaDungeons;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.zeldadungeons.network.message.PacketDemo;
import net.zeldadungeons.network.message.PacketHealthValues;
import net.zeldadungeons.network.message.PacketPressureSwitch;
import net.zeldadungeons.network.message.PacketStaminaValues;

public class NetworkHandler 
{
	private static SimpleNetworkWrapper INSTANCE; 
	
	public static void init()
	{
		setInstance(NetworkRegistry.INSTANCE.newSimpleChannel(ZeldaDungeons.MODID));
		getInstance().registerMessage(PacketDemo.handler, PacketDemo.class, 0, Side.SERVER);
		getInstance().registerMessage(PacketPressureSwitch.handler, PacketPressureSwitch.class, 0, Side.SERVER);
		getInstance().registerMessage(PacketHealthValues.handler, PacketHealthValues.class, 0, Side.CLIENT);
		getInstance().registerMessage(PacketStaminaValues.handler, PacketStaminaValues.class, 0, Side.CLIENT);
	}

	public static SimpleNetworkWrapper getInstance() {
		return INSTANCE;
	}

	public static void setInstance(SimpleNetworkWrapper iNSTANCE) {
		INSTANCE = iNSTANCE;
	}
}
