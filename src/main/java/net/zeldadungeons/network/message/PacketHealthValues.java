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

public class PacketHealthValues implements IMessage {

    public static HandlerPacketHealthValues handler = new HandlerPacketHealthValues();

    private int maxHealth;
    private int currentHealth;
    private int level;
    private int totalExp;

    public PacketHealthValues(int currentHealth, int maxHealth, int level, int totalExp) {
	this.currentHealth = currentHealth;
	this.maxHealth = maxHealth;
	this.level = level;
	this.totalExp = totalExp;
    }

    public PacketHealthValues() {
    }

    @Override
    public void fromBytes(ByteBuf buf) {
	this.currentHealth = buf.readInt();
	this.maxHealth = buf.readInt();
	this.level = buf.readInt();
	this.totalExp = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
	buf.writeInt(currentHealth);
	buf.writeInt(maxHealth);
	buf.writeInt(level);
	buf.writeInt(totalExp);
    }

    static class HandlerPacketHealthValues implements IMessageHandler<PacketHealthValues, IMessage> {

	@Override
	public IMessage onMessage(PacketHealthValues message, MessageContext ctx) {
	    if (ctx.side == Side.CLIENT) {
		PlayerData.setHealthLevel(message.level);
		PlayerData.setHealthXP(message.totalExp);
		PlayerData.setCurrentHealth(message.currentHealth);
		PlayerData.setMaxHealth(message.maxHealth);
	    }
	    return null;
	}
    }
}
