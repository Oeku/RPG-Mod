package net.zeldadungeons.init.entity.ai;

import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.Vec3d;
import net.zeldadungeons.capability.playerlevels.CapabilityPlayerLevels;

public class EntityAvoidLevel<T extends EntityPlayer> extends EntityAIAvoidEntity {

    private int neededLevel;

    public EntityAvoidLevel(EntityCreature entityIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn, int neededLevel) {
	super(entityIn, EntityPlayer.class, avoidDistanceIn, farSpeedIn, nearSpeedIn);
	this.neededLevel = neededLevel;
    }

    @Override
    public boolean shouldExecute() {
	if (this.closestLivingEntity != null) if (this.closestLivingEntity.getCapability(CapabilityPlayerLevels.PLAYER_LEVELS_CAPABILITY, CapabilityPlayerLevels.DEFAULT_FACING).getCombatSkill().getLevel() > this.neededLevel) {
	    return super.shouldExecute();
	}
	return false;
    }

}
