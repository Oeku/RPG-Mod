package net.zeldadungeons.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.zeldadungeons.capability.playerlevels.CapabilityPlayerLevels;
import net.zeldadungeons.init.entity.living.overworld.EntityGorok;
import net.zeldadungeons.init.entity.living.overworld.EntityLandamus;
import net.zeldadungeons.init.entity.living.overworld.EntityProoper;
import net.zeldadungeons.util.Log;

public class SpawnEventHandler {
    public static final SpawnEventHandler INSTANCE = new SpawnEventHandler();
    
    public void onEntitySpawn(LivingSpawnEvent.CheckSpawn e){
	if(e.getEntityLiving() instanceof EntityGorok){
	    if(e.getY() > 40)e.setResult(Result.ALLOW);
	}
    }
    
    @SubscribeEvent
    public void entityJoin(EntityJoinWorldEvent e){
	BlockPos pos = e.getEntity().getPosition();
	if(e.getEntity() instanceof EntityPlayer && e.getEntity().hasCapability(CapabilityPlayerLevels.PLAYER_LEVELS_CAPABILITY, CapabilityPlayerLevels.DEFAULT_FACING)){
	    e.getEntity().getCapability(CapabilityPlayerLevels.PLAYER_LEVELS_CAPABILITY, CapabilityPlayerLevels.DEFAULT_FACING).setPlayer((EntityPlayer) e.getEntity());
	    if(e.getEntity() instanceof EntityPlayerMP){
		e.getEntity().getCapability(CapabilityPlayerLevels.PLAYER_LEVELS_CAPABILITY, CapabilityPlayerLevels.DEFAULT_FACING).sendUpdates();
	    }
	}
	if(e.getEntity() instanceof EntityGorok){
	    Log.getLogger().info("gorok "+pos.getX()+" "+ pos.getY()+" "+ pos.getZ());
	}
	if(e.getEntity() instanceof EntityLandamus){
	    Log.getLogger().info("landamus "+pos.getX()+" "+ pos.getY()+" "+ pos.getZ());
	}
	if(e.getEntity() instanceof EntityProoper){
	    Log.getLogger().info("prooper "+pos.getX()+" "+ pos.getY()+" "+ pos.getZ());
	}
    }
}
