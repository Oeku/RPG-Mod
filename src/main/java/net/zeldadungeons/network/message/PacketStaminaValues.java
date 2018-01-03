package net.zeldadungeons.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.zeldadungeons.client.gui.overlay.GuiOverlay;

public class PacketStaminaValues implements IMessage {

    public static HandlerPacketStaminaValues handler = new HandlerPacketStaminaValues();

    private double maxStamina;
    private double currentStamina;
    private double share;
    private int level;

    public PacketStaminaValues(double currentStamina, double maxStamina, int level, double share) {
	this.currentStamina = currentStamina;
	this.maxStamina = maxStamina;
	this.share = share;
	this.level = level;
    }

    public PacketStaminaValues() {
    }

    @Override
    public void fromBytes(ByteBuf buf) {
	this.currentStamina = buf.readDouble();
	this.maxStamina = buf.readDouble();
	this.share = buf.readDouble();
	this.level = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
	buf.writeDouble(currentStamina);
	buf.writeDouble(maxStamina);
	buf.writeDouble(share);
	buf.writeInt(level);
    }

    static class HandlerPacketStaminaValues implements IMessageHandler<PacketStaminaValues, IMessage> {

	@Override
	public IMessage onMessage(PacketStaminaValues message, MessageContext ctx) {
	    if (ctx.side == Side.CLIENT) {
		 GuiOverlay.renderCurrentStamina = message.currentStamina;
		 GuiOverlay.renderCurrentStamina = message.currentStamina;
		/*Minecraft.getMinecraft().addScheduledTask(new Runnable() {
		    public void run() {
			processMessage(message);
		    }
		});*/
	    }
	    return null;
	}

	void processMessage(PacketStaminaValues message) {
	    GuiOverlay.renderCurrentStamina = message.currentStamina;
	    GuiOverlay.renderCurrentStamina = message.currentStamina;
	}
    }
}
