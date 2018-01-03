package net.zeldadungeons.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.zeldadungeons.client.gui.overlay.GuiOverlay;

public class PacketHealthValues implements IMessage {

    public static HandlerPacketHealthValues handler = new HandlerPacketHealthValues();

    private double maxHealth;
    private double currentHealth;
    private double share;
    private int level;

    public PacketHealthValues(double currentHealth, double maxHealth, int level, double share) {
	this.currentHealth = currentHealth;
	this.maxHealth = maxHealth;
	this.share = share;
	this.level = level;
    }

    public PacketHealthValues() {
    }

    @Override
    public void fromBytes(ByteBuf buf) {
	this.currentHealth = buf.readDouble();
	this.maxHealth = buf.readDouble();
	this.share = buf.readDouble();
	this.level = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
	buf.writeDouble(currentHealth);
	buf.writeDouble(maxHealth);
	buf.writeDouble(share);
	buf.writeInt(level);
    }

    static class HandlerPacketHealthValues implements IMessageHandler<PacketHealthValues, IMessage> {

	@Override
	public IMessage onMessage(PacketHealthValues message, MessageContext ctx) {
	    if (ctx.side == Side.CLIENT) {
		GuiOverlay.renderCurrentHealth = message.currentHealth;
		GuiOverlay.renderMaxHealth = message.maxHealth;
		/*Minecraft.getMinecraft().addScheduledTask(new Runnable() {
		    public void run() {
			processMessage(message);
		    }
		});*/
	    }
	    return null;
	}

	void processMessage(PacketHealthValues message) {
	    GuiOverlay.renderCurrentHealth = message.currentHealth;
	    GuiOverlay.renderMaxHealth = message.maxHealth;
	}
    }
}
