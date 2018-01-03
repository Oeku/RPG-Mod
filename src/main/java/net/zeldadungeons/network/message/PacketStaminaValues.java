package net.zeldadungeons.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.zeldadungeons.capability.playerlevels.CapabilityPlayerLevels;
import net.zeldadungeons.capability.playerlevels.IPlayerLevels;
import net.zeldadungeons.client.PlayerData;
import net.zeldadungeons.skill.SkillHealth;

public class PacketStaminaValues implements IMessage {

    public static HandlerPacketStaminaValues handler = new HandlerPacketStaminaValues();

    private int maxStamina;
    private int currentStamina;
    private int level;
    private int totalExp;

    public PacketStaminaValues(int currentHealth, int maxHealth, int level, int totalExp) {
	this.currentStamina = currentHealth;
	this.maxStamina = maxHealth;
	this.level = level;
	this.totalExp = totalExp;
    }

    public PacketStaminaValues() {
    }

    @Override
    public void fromBytes(ByteBuf buf) {
	this.currentStamina = buf.readInt();
	this.maxStamina = buf.readInt();
	this.level = buf.readInt();
	this.totalExp = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
	buf.writeInt(currentStamina);
	buf.writeInt(maxStamina);
	buf.writeInt(level);
	buf.writeInt(totalExp);
    }

    static class HandlerPacketStaminaValues implements IMessageHandler<PacketStaminaValues, IMessage> {

	@Override
	public IMessage onMessage(PacketStaminaValues message, MessageContext ctx) {
	    if (ctx.side == Side.CLIENT) {
		PlayerData.setStaminaLevel(message.level);
		PlayerData.setStaminaXP(message.totalExp);
		PlayerData.setCurrentStamina(message.currentStamina);
		PlayerData.setMaxStamina(message.maxStamina);
	    }
	    return null;
	}
    }
}
