package net.zeldadungeons.init.entity.living.overworld;

import javax.annotation.Nullable;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.zeldadungeons.init.entity.ICustomEntity;

public class EntityProoper extends EntityMob implements ICustomEntity {

    public EntityProoper(World worldIn) {
	super(worldIn);

    }

    @Override
    protected void initEntityAI() {
	this.tasks.addTask(0, new EntityAISwimming(this));
	this.tasks.addTask(2, new EntityAIAttackMelee(this, 2.0D, false));
	this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
	this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
	this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityCreeper.class, true));
	
    }
    
    @Override
    public boolean getCanSpawnHere() {
	boolean b = false;
	BlockPos pos = this.getPosition();
	if(pos.getY()<40)b = true; 
	if(!this.world.isAirBlock(pos.up(1)) && !this.world.isAirBlock(pos.up(2)))b = false;
	if(super.getCanSpawnHere() && b)return true;
	else return false;
    }

    @Override
    protected void applyEntityAttributes() {
	super.applyEntityAttributes();
	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.175D);
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
