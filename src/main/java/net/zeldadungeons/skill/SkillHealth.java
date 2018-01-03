package net.zeldadungeons.skill;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.zeldadungeons.capability.playerlevels.CapabilityPlayerLevels;
import net.zeldadungeons.event.EventManager;
import net.zeldadungeons.network.NetworkHandler;
import net.zeldadungeons.network.message.PacketHealthValues;

public class SkillHealth extends Skill {
    private int maxHealth;
    private int currentHealth;

    /**
     * @param level the starting level of this skill
     * @param totalExp the total exp of this skill
     */
    public SkillHealth(int level, int totalExp) {
	this.maxLevel = 1000;
	this.setLevel(level);
	this.totalExp = totalExp;
	this.expToNext = this.calculateExp(level + 1);
	this.calculateMaxHealth(level);
	this.currentHealth = this.maxHealth;
    }

    /**
     * sets the level, called from levelup() to increase the level. also updates
     * the maxhealth that goes along with the new level.
     */
    @Override
    public void setLevel(int level) {
	super.setLevel(level);
	this.sendUpdatePackets(player);
	calculateMaxHealth(level);
    }

    /**
     * Calculates the maximum health for the given level.
     * 
     * @param level
     */
    public void calculateMaxHealth(int level) {
	int j = 10 * level;
	for (int i = level; i > 0; i--) {
	    j += i * 1;
	}
	this.setMaxHealth(j);
    }

    /**
     * Called to damage the player.
     * 
     * @param i
     */
    public void setCurrentHealth(int i) {
	i = EventManager.onSetCurrentSkill(this, i, this.player);
	this.currentHealth = i;
	if (i <= 0) {
	    if (player != null)
		player.setHealth(0F); 
	    this.currentHealth = 0;
	    this.sendUpdatePackets(player);
	}
    }

    /**
     * Set the maxHealth.
     * 
     * @param i
     */
    public void setMaxHealth(int i) {
	this.maxHealth = i;
	this.sendUpdatePackets(player);
    }

    public int getMaxHealth() {
	return this.maxHealth;
    }

    public int getCurrentHealth() {
	return this.currentHealth;
    }

    public void setPlayer(EntityPlayer player) {
	this.player = player;
	this.sendUpdatePackets(player);
    }

    @Override
    public void sendUpdatePackets(EntityPlayer player) {
	if (player != null && !player.world.isRemote && player instanceof EntityPlayerMP)
	    NetworkHandler.getInstance().sendTo(new PacketHealthValues(this.currentHealth, this.maxHealth, this.level, this.totalExp), (EntityPlayerMP) player);
    }
    
    public void damagePlayer(boolean withArmor, int amount){
	SkillCombat combatSkill = this.player.getCapability(CapabilityPlayerLevels.PLAYER_LEVELS_CAPABILITY, CapabilityPlayerLevels.DEFAULT_FACING).getCombatSkill();
    }

    public boolean fullHealth() {
	if(this.getCurrentHealth() == this.getMaxHealth())return true;
	else return false;
    }
    
    public boolean willDamageKill(int i){
	if(i >= this.currentHealth)return true;
	else return false;
    }
}
