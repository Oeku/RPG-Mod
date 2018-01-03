package net.zeldadungeons.util;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketParticles;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.WorldServer;

public class PacketUtil {
    public static void spawnParticle(WorldServer world, EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, int number, double speed, int... parameters)
    {
	world.spawnParticle(particleType, xCoord, yCoord, zCoord, number, 0D, 0D, 0D, speed, parameters);
    }
}
