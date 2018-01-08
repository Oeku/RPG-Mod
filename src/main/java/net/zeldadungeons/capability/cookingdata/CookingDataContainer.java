package net.zeldadungeons.capability.cookingdata;

import java.util.List;

public class CookingDataContainer {
    private int healAmount;
    private int satAmount;
    private int stamAmount;

    private String name;
    private List<String> effectParameters; 
    private boolean perfect;
    
    public CookingDataContainer(){
    }
    
    public CookingDataContainer(int i, int j, int k) {
	this();
	this.healAmount = i;
	this.satAmount = j;
	this.stamAmount = k;
    }

    public int getHealAmount() {
	return healAmount;
    }

    public void setHealAmount(int healAmount) {
	this.healAmount = healAmount;
    }

    public int getSatAmount() {
	return satAmount;
    }

    public void setSatAmount(int satAmount) {
	this.satAmount = satAmount;
    }

    public int getStamAmount() {
	return stamAmount;
    }

    public void setStamAmount(int stamAmount) {
	this.stamAmount = stamAmount;
    }
    
    public void setParameters(List<String> par1){
	this.effectParameters = par1;
    }
    
    public List<String> getParameters(){
	return this.effectParameters;
    }

    public void setName(String name) {
	this.name = name;
    }
    
    public String getName(){
	return this.name;
    }
    
    public boolean isPerfect(){
	return this.perfect;
    }
    
    public void setPerfect(boolean per){
	this.perfect = per;
    }
}
