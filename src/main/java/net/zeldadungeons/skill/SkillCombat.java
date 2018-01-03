package net.zeldadungeons.skill;

import net.minecraft.entity.player.EntityPlayer;

public class SkillCombat extends Skill {
    
    private int rawDamage;
    private int armorProtection;
    private String bonus = "";
    
    public SkillCombat(int level, int totalExp) {
	this.maxLevel = 1000;
	this.setLevel(level);
	this.totalExp = totalExp;
	this.calculateExp(level + 1);
	this.calculateDamage(level);
    }
    
    public void setPlayer(EntityPlayer player) {
   	this.player = player;
       }
    
    public void setDamage(int i){
	this.rawDamage = i;
    }
    
    public int getDamage(){
	return this.rawDamage;
    }
    
    public int getProtection(){
	return this.armorProtection;
    }
    
    public void setProtection(int i){
	this.armorProtection = i;
    }
    
    public void calculateDamage(int level) {
	int j = 10 * level;
	for (int i = level; i > 0; i--) {
	    j += i * 1;
	}
	this.setDamage(j);
    }
    
    public void setBonus(String armortype){
	this.bonus = armortype;
    }

    public String getBonus() {
	return this.bonus;
    }
    
    
}
