package net.zeldadungeons.init.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityPlayerPulling extends Entity {

	public EntityPlayer parent;
	
	public EntityPlayerPulling(World worldIn) {
		super(worldIn);
	}
	
	public EntityPlayerPulling(World worldIn, EntityPlayer entityPlayer) {
		super(worldIn);
		this.parent = entityPlayer;
		this.setPosition(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ);
		this.setSize(1F, 3F);
	}
	
	@Override
	public void onUpdate() {
		
		super.onUpdate();
		if(parent != null)this.setPosition(parent.posX, parent.posY, parent.posZ);
		this.setDead();
	}
	
	
	@Override
	protected void entityInit() {
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		
	}

}
