package net.zeldadungeons.capability.entitylevels;

import net.minecraft.entity.EntityLivingBase;

public class EntityLevels implements IEntityLevels{

	private EntityLivingBase entity;
	private EntityLevelsContainer container;
	
	public EntityLevels(EntityLivingBase entity){
		this.entity = entity;
	}
	
	public EntityLevels(EntityLivingBase entity, EntityLevelsContainer container){
		this(entity);
		setContainer(container);
		container.entity = entity;
	}
	
	@Override
	public EntityLevelsContainer getContainer()
	{
		return this.container;
	}
	
	@Override
	public void setContainer(EntityLevelsContainer c){
		this.container = c;
	}
}
