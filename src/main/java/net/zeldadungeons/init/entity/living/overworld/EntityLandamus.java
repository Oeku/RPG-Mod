package net.zeldadungeons.init.entity.living.overworld;

import javax.annotation.Nullable;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.zeldadungeons.init.entity.ICustomEntity;

public class EntityLandamus extends EntityMob implements ICustomEntity {

    public EntityLandamus(World worldIn) {
	super(worldIn);

    }

    @Override
    protected void initEntityAI() {
	this.tasks.addTask(0, new EntityAISwimming(this));
	this.tasks.addTask(2, new EntityAIAttackMelee(this, 2.0D, false));
	this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
	this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
	this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
	this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    @Override
    protected void applyEntityAttributes() {
	super.applyEntityAttributes();
	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    }
    
    @Override
    public boolean getCanSpawnHere() {
	boolean b = false;
	if(this.posY > 60D)b= true;
	return b;
    }
    
    @Override
    protected boolean isValidLightLevel() {
        return true;
    }

    @Override
    public int getMaxFallHeight() {
	return 200;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
	return SoundEvents.ENTITY_CREEPER_HURT;
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
	return LootTableList.ENTITIES_CREEPER;
    }

    @Override
    protected SoundEvent getDeathSound() {
	return SoundEvents.ENTITY_CREEPER_DEATH;
    }

    @Override
    protected SoundEvent getAmbientSound() {
	return SoundEvents.ENTITY_ENDERDRAGON_HURT;
    }

    protected SoundEvent getStepSound() {
	return SoundEvents.ENTITY_PIG_STEP;
    }
}
