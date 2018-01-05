package net.zeldadungeons.skill;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.zeldadungeons.network.NetworkHandler;
import net.zeldadungeons.network.message.PacketStaminaValues;

public class SkillStamina extends Skill {

    private int currentStamina;
    private int maxStamina;
    private int regen = 1000;
    private int costSprinting = 1;
    private int costSpeed = 5;
    private int costSpeed3 = 15;
    private EnumMode modeIn = EnumMode.NORMAL;

    /**
     * @param level the starting level of this skill
     * @param totalExp the total exp of this skill
     */
    public SkillStamina(int level, int totalExp) {
	this.maxLevel = 1000;
	level = 100;
	this.setLevel(level);
	this.totalExp = totalExp;
	this.calculateExp(level + 1);
	this.calculateMaxStamina(level);
	this.currentStamina = this.maxStamina;
    }

    /**
     * sets the level, called from levelup() to increase the level. also updates
     * the maxStamina that goes along with the new level.
     */
    @Override
    public void setLevel(int level) {
	super.setLevel(level);
	this.regen = maxLevel/level;
	calculateMaxStamina(level);
    }
    

    /**
     * Calculates the maximum stamina for the given level.
     * 
     * @param level
     */
    public void calculateMaxStamina(int level) {
	int j = 10 * level;
	for (int i = level; i > 0; i--) {
	    j += i * 1;
	}
	this.setMaxStamina(j);
    }

    /**
     * Called when the player is exercising.
     * 
     * @param i
     */
    public void setCurrentStamina(int i) {
	if (i <= this.maxStamina)
	    this.currentStamina = i;
	else this.currentStamina = maxStamina;
	if (i <= 0) {
	    this.currentStamina = 0;
	    if (player.isSprinting()) {
		player.addPotionEffect(new PotionEffect(Potion.REGISTRY.getObjectById(2), 1, 255));
		player.setJumping(false);
	    }
	}
	if (this.currentStamina > this.maxStamina / 2)
	    if(player != null)player.removeActivePotionEffect(Potion.REGISTRY.getObjectById(2));
    }

    /**
     * Set the maxHealth.
     * 
     * @param i
     */
    public void setMaxStamina(int i) {
	this.maxStamina = i;
	this.sendUpdatePackets(player);
    }

    public int getMaxStamina() {
	return this.maxStamina;
    }

    public int getCurrentStamina() {
	return this.currentStamina;
    }

    public void setPlayer(EntityPlayer player) {
	this.player = player;
	this.sendUpdatePackets(player);
    }
    
    public void regenStamina(int ticks){
	this.updateMode();
	if(this.modeIn == EnumMode.STANDING){
	    this.addStamina(1);
	}
	if(ticks % regen == 0){
	    this.addStamina(1);
	}
    }
    
    public void removeStamina(int ticks){
	this.updateMode();
	int i = 0;
	if(this.modeIn == EnumMode.SPRINTING){
	    i=1;
	}
	if(this.modeIn == EnumMode.SPEED){
	    i=5;
	}
	if(this.modeIn == EnumMode.SPEED2){
	    i=15;
	}
	if(this.modeIn == EnumMode.FLIGHT){
	    i= 100;
	}
	
	this.addStamina(-i);
    }
    
    public void updateMode(){
	if(player.motionX == 0 && player.motionY <= 0 && player.motionZ == 0){
	    this.modeIn = EnumMode.STANDING;
	}
	else if(!player.isSprinting()){
	   this.modeIn = EnumMode.NORMAL; 
	}
	if(player.isSprinting()){
	    this.modeIn = EnumMode.SPRINTING;
	}
	if(player.capabilities.isFlying)
	    this.modeIn = EnumMode.FLIGHT;
	if(player.isElytraFlying()){
	    this.modeIn = EnumMode.FLIGHT;
	}
    }
    
    public void setMode(EnumMode mode){
	this.modeIn = mode;
    }

    @Override
    public void sendUpdatePackets(EntityPlayer player) {
	if (player != null && player.isServerWorld() && player instanceof EntityPlayerMP) {
	    NetworkHandler.getInstance().sendTo(new PacketStaminaValues(this.currentStamina, this.maxStamina, this.level, this.totalExp), (EntityPlayerMP) player);
	}
    }
    
    public void addStamina(int i){
	this.setCurrentStamina(this.getCurrentStamina()+i);
    }
    
    enum EnumMode{
	STANDING, NORMAL, SPRINTING,  SPEED, SPEED2, CLIMB, FLIGHT,
    }
}
