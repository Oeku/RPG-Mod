package net.zeldadungeons.capability.playerlevels;

import net.minecraft.entity.player.EntityPlayer;
import net.zeldadungeons.skill.SkillCombat;
import net.zeldadungeons.skill.SkillHealth;
import net.zeldadungeons.skill.SkillStamina;

public class PlayerLevels implements IPlayerLevels {
    private EntityPlayer player;
    private SkillHealth healthSkill = new SkillHealth(1, 0);
    private SkillStamina staminaSkill = new SkillStamina(1, 0);
    private SkillCombat combatSkill = new SkillCombat(1, 0);

    public PlayerLevels(EntityPlayer entity) {
	setPlayer(entity);
    }

    @Override
    public SkillHealth getHealthSkill() {
	return this.healthSkill;
    }

    @Override
    public void setHealthSkill(SkillHealth skill) {
	this.healthSkill = skill;
    }

    @Override
    public SkillStamina getStaminaSkill() {
	return this.staminaSkill;
    }

    @Override
    public void setStaminaSkill(SkillStamina skill) {
	this.staminaSkill = skill;
    }

    @Override
    public SkillCombat getCombatSkill() {
	return this.combatSkill;
    }

    @Override
    public void setCombatSkill(SkillCombat skill) {
	this.combatSkill = skill;
    }

    @Override
    public EntityPlayer getPlayer() {
	return this.player;
    }

    @Override
    public void setPlayer(EntityPlayer player) {
	this.player = player;
	this.healthSkill.setPlayer(player);
	this.staminaSkill.setPlayer(player);
	this.combatSkill.setPlayer(player);
    }

    @Override
    public void sendUpdates() {
	this.getHealthSkill().sendUpdatePackets(player);
	this.getCombatSkill().sendUpdatePackets(player);
	this.getStaminaSkill().sendUpdatePackets(player);

    }
}
