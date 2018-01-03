package net.zeldadungeons.init.entity;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;
import net.zeldadungeons.capability.entitylevels.EntityLevelsContainer;

public class EntityMobBase extends EntityMob {
    protected EntityLevelsContainer container;
    
    
    public EntityMobBase(World worldIn) {
	super(worldIn);
	this.container.entity = this;
    }
    
}
