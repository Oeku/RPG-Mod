package net.zeldadungeons.util;

import net.minecraft.entity.player.EntityPlayer;
import net.zeldadungeons.capability.playerlevels.CapabilityPlayerLevels;
import net.zeldadungeons.capability.playerlevels.IPlayerLevels;

public class CapabilityUtil {
	
	public static final CapabilityUtil INSTANCE = new CapabilityUtil();
	
	public IPlayerLevels getPlayerLevelCap(EntityPlayer player){
		return player.getCapability(CapabilityPlayerLevels.PLAYER_LEVELS_CAPABILITY, CapabilityPlayerLevels.DEFAULT_FACING);
	}
}
