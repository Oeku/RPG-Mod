package net.zeldadungeons.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.zeldadungeons.capability.playerlevels.CapabilityPlayerLevels;
import net.zeldadungeons.capability.playerlevels.IPlayerLevels;
import net.zeldadungeons.capability.playerlevels.PlayerLevels;
import net.zeldadungeons.skill.Skill;

public class EventManager {
    
    public static int onSetCurrentSkill(Skill skill, int value, EntityPlayer player){
	if(player == null)return value;
	IPlayerLevels data = player.getCapability(CapabilityPlayerLevels.PLAYER_LEVELS_CAPABILITY, CapabilityPlayerLevels.DEFAULT_FACING);
	
	//SappizeArmor Effect
	if(data.getCombatSkill().getBonus() == "sappize"){
	    if(data.getHealthSkill().fullHealth() && value <= 0){
		return 1;
	    }
	}
	return value;
	    
    }
}
