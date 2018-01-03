package net.zeldadungeons.skill;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.zeldadungeons.util.MathUtil;

public class Skill {
    @Nullable
    protected EntityPlayer player;
    protected int level;
    protected int maxLevel;

    protected int totalExp;
    protected int expToNext;

    public Skill() {
    }

    /**
     * Called to increase the level of this skill, paying attention to maxLevel.
     */
    public void levelup() {
	if (this.level < maxLevel) setLevel(level + 1);
	this.expToNext = calculateExp(level);
    }

    /**
     * Called when a player gains Exp in this skill.
     * 
     * @param exp
     */
    public void gainExp(int exp) {
	this.setTotalExp(this.totalExp + exp);
	if (this.totalExp >= expToNext) this.levelup();
    }

    /**
     * Set the level of this Skill and the exp needed to get to the next one.
     * 
     * @param level
     */
    public void setLevel(int level) {
	this.level = level;
	this.expToNext = calculateExp(level++);
    }

    /**
     * Calculate needed Exp for a certain level.
     * 
     * @param level the level
     */
    public int calculateExp(int level) {
	int i = 0;
	for (int g = level; g > 0; g--) {
	    i += MathUtil.fakultaet1(g) * 10;
	}
	return i;
    }

    
    public void sL(int i) {
	this.level = i;
    }

    /**
     * Sets the totalExp that the player has ever gained.
     * 
     * @param i
     */
    public void setTotalExp(int i) {
	this.totalExp = i;
    }

    public boolean canLevelup() {
	if (totalExp >= expToNext) return true;
	else return false;
    }

    public int getTotalExp() {
	return this.totalExp;
    }

    public int getExpToNext() {
	return this.expToNext;
    }

    public int getLevel() {
	return this.level;
    }

    public void sendUpdatePackets(EntityPlayer player) {

    }
}
