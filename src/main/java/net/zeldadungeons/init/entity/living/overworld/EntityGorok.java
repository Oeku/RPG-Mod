package net.zeldadungeons.init.entity.living.overworld;

import javax.annotation.Nullable;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.zeldadungeons.init.entity.ICustomEntity;

public class EntityGorok extends EntityMob implements ICustomEntity {

    public EntityGorok(World worldIn) {
	super(worldIn);

    }

    @Override
    protected void initEntityAI() {
	this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
	this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
	this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
	this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    @Override
    protected void applyEntityAttributes() {
	super.applyEntityAttributes();
	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.15D);
	//this.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(2.0D);
    }

    @Override
    public boolean getCanSpawnHere() {
	boolean b = false;
	BlockPos pos = this.getPosition();
	if(pos.getY()<40)b = true; 
	if(this.world.getBlockState(pos.down()).getBlock() == Blocks.STONE && this.posY > 90D)b= true;
	if(super.getCanSpawnHere() && b)return true;
	else return false;
    }
    
    @Override
    public boolean isValidLightLevel(){
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
