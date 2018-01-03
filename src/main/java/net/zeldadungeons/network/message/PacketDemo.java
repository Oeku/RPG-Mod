package net.zeldadungeons.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.zeldadungeons.util.ByteBufUtil;

public class PacketDemo implements IMessage {
	public static HandlerPacketCards handler = new HandlerPacketCards();
	ByteBufUtil bbu;
	private int intention;

	public PacketDemo(int i) {
		
	}

	public PacketDemo() {
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		
	}

	static class HandlerPacketCards implements IMessageHandler<PacketDemo, IMessage> {

		@Override
		public IMessage onMessage(PacketDemo message, MessageContext ctx) {
			if (ctx.side == Side.CLIENT) {
				Minecraft mc = Minecraft.getMinecraft();
				return message;

			}
			return null;
		}
	}
}
