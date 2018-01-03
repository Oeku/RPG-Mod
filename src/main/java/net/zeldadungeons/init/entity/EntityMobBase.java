package net.zeldadungeons.init.entity;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.zeldadungeons.capability.entitylevels.EntityLevelsContainer;

public class EntityMobBase extends EntityMob {
    protected EntityLevelsContainer container;
    
    
    public EntityMobBase(World worldIn) {
	super(worldIn);
    }
    
    public EntityMobBase(World worldIn, EntityLevelsContainer c){
	this(worldIn);
	this.container = c;
	this.container.entity = this;
    }

    public void setEntityProperties(int level, int maxHealth, int damage) {
	this.container.setLevel(level);
	this.container.setDamage(damage);
	this.container.setMaxHealth(maxHealth);
	this.container.setCurrentHealth(maxHealth);
    }
    
    public EntityLevelsContainer getContainer(){
	return this.container;
    }
    
    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        int[] data = {this.container.getLevel(), this.container.getCurrentHealth(), this.container.getMaxHealth(), this.container.getDamage()};
        compound.setIntArray("zeldadungeons_entity", data);
    }
    
    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        int[] data = compound.getIntArray("zeldadungeons_entity");
        this.container.setLevel(data[0]);
        this.container.setMaxHealth(data[2]);
        this.container.setCurrentHealth(data[1]);
        this.container.setDamage(data[3]);
        
    }
    
}
