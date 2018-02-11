package net.zeldadungeons.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.zeldadungeons.capability.playerlevels.CapabilityPlayerLevels;
import net.zeldadungeons.init.Dimensionizer;
import net.zeldadungeons.init.entity.living.overworld.EntityGorok;
import net.zeldadungeons.init.entity.living.overworld.EntityLandamus;
import net.zeldadungeons.init.entity.living.overworld.EntityProoper;
import net.zeldadungeons.util.Log;
import net.zeldadungeons.world.TeleporterCustom;

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
	if(e.getEntity() instanceof EntityGorok){
	}
	if(e.getEntity() instanceof EntityLandamus){
	}
	if(e.getEntity() instanceof EntityProoper){
	}
    }
    
    @SubscribeEvent
    public void onPlayerLogIn(PlayerLoggedInEvent e){
	e.player.getCapability(CapabilityPlayerLevels.PLAYER_LEVELS_CAPABILITY, CapabilityPlayerLevels.DEFAULT_FACING).setPlayer(e.player);
	if(e.player.dimension != Dimensionizer.medievalID)e.player.world.getMinecraftServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP) e.player, Dimensionizer.medievalID, new TeleporterCustom(e.player.world.getMinecraftServer().getWorld(Dimensionizer.medievalID)));

    }
}
