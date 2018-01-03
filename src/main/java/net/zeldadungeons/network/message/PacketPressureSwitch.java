package net.zeldadungeons.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.zeldadungeons.init.blocks.BlockPressureSwitch;
import net.zeldadungeons.util.Log;

public class PacketPressureSwitch implements IMessage {

	public static HandlerPacketPressureSwitch handler = new HandlerPacketPressureSwitch();

	private int ticks;
	private boolean heavyObject;
	private int x;
	private int y;
	private int z;

	public PacketPressureSwitch(int ticks, boolean heavyObject, int x, int y, int z) {
		this.ticks = ticks;
		this.heavyObject = heavyObject;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public PacketPressureSwitch() {
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.heavyObject = buf.readBoolean();
		this.ticks = buf.readInt();
		z = buf.readInt();
		y = buf.readInt();
		x = buf.readInt();

	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(100);
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeByte(ticks);
		buf.writeBoolean(heavyObject);
	}

	static class HandlerPacketPressureSwitch implements IMessageHandler<PacketPressureSwitch, IMessage> {

		@Override
		public IMessage onMessage(PacketPressureSwitch message, MessageContext ctx) {
			if (ctx.side == Side.SERVER) {
				EntityPlayer player = ctx.getServerHandler().player;
				World world = player.world;
				Block block = world.getBlockState(new BlockPos(message.x, message.y, message.z)).getBlock();
				if (!world.isRemote)
				{
					Log.getLogger().info(message.heavyObject);
					Log.getLogger().info(message.ticks);
					if(block  instanceof BlockPressureSwitch)
					{
						
						BlockPressureSwitch pSwitch = (BlockPressureSwitch) block;
						pSwitch.setHeavy(message.heavyObject);
						pSwitch.setTicks(message.ticks);

					}
				}
			}
			return null;
		}
	}
}
