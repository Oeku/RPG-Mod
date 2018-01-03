package net.zeldadungeons.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.Event;

public class LevelEvent extends Event{
	
	private World world;
	private EntityPlayer player;
	
	public LevelEvent(World world, EntityPlayer player){
		this.world = world;
		this.player = player;
	}
	
	public EntityPlayer getPlayer(){
		return this.player;
	}
	
	public World getWorld(){
		return this.world;
	}
	
	public class Health extends LevelEvent{
		public Health(World world, EntityPlayer player) {
			super(world, player);
		}
	}
}
