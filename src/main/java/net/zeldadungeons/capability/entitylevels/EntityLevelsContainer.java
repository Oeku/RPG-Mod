package net.zeldadungeons.capability.entitylevels;

import net.minecraft.entity.EntityLivingBase;
import net.zeldadungeons.init.entity.ICustomEntity;

public class EntityLevelsContainer {

    public EntityLivingBase entity;

    private int level;
    private int damage;
    private int maxHealth;

    private int currentHealth;

    public EntityLevelsContainer() {
    }

    public EntityLevelsContainer(EntityLivingBase entity, int damage, int level, int health) {
	this();
	this.damage = damage;
	this.level = level;
	this.maxHealth = health;
    }

    /** getters and setters **/

    public int getLevel() {
	return level;
    }

    public void setLevel(int level) {
	this.level = level;
    }

    public int getDamage() {
	return damage;
    }

    public void setDamage(int damage) {
	this.damage = damage;
    }

    public int getMaxHealth() {
	return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
	this.maxHealth = maxHealth;
    }

    public int getCurrentHealth() {
	return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
	this.currentHealth = currentHealth;
	if (this.currentHealth <= 0 && this.entity instanceof ICustomEntity) {
	    this.entity.setHealth(0.0F);
	}
    }

    // Calculates the maximum health value for a normal mob.
    public int calculateMaxHealth(int level) {
	int j = 10 * level;
	for (int i = level; i > 0; i--) {
	    j += i * 1;
	}
	return j;
    }

    // Always returns 1/5 of calculateMaxHealth for the same parameter
    public int calculateDamage(int level) {
	int j = 10 * level;
	for (int i = level; i > 0; i--) {
	    j += i * 1;
	}
	double d = j;
	d = d * 0.2;
	j = (int) d;
	return j;

    }

}
