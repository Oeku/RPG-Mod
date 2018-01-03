package net.zeldadungeons.client;

public class PlayerData {

    private static int currentHealth;
    private static int maxHealth;
    private static int healthLevel;
    private static int healthXP;

    private static int currentStamina;
    private static int maxStamina;
    private static int staminaLevel;
    private static int staminaXP;
    
    public static int getCurrentHealth() {
        return currentHealth;
    }
    public static void setCurrentHealth(int currentHealth) {
        PlayerData.currentHealth = currentHealth;
    }
    public static int getMaxHealth() {
        return maxHealth;
    }
    public static void setMaxHealth(int maxHealth) {
        PlayerData.maxHealth = maxHealth;
    }
    public static int getHealthLevel() {
        return healthLevel;
    }
    public static void setHealthLevel(int healthLevel) {
        PlayerData.healthLevel = healthLevel;
    }
    public static int getHealthXP() {
        return healthXP;
    }
    public static void setHealthXP(int healthXP) {
        PlayerData.healthXP = healthXP;
    }
    public static int getCurrentStamina() {
        return currentStamina;
    }
    public static void setCurrentStamina(int currentStamina) {
        PlayerData.currentStamina = currentStamina;
    }
    public static int getMaxStamina() {
        return maxStamina;
    }
    public static void setMaxStamina(int maxStamina) {
        PlayerData.maxStamina = maxStamina;
    }
    public static int getStaminaLevel() {
        return staminaLevel;
    }
    public static void setStaminaLevel(int staminaLevel) {
        PlayerData.staminaLevel = staminaLevel;
    }
    public static int getStaminaXP() {
        return staminaXP;
    }
    public static void setStaminaXP(int staminaXP) {
        PlayerData.staminaXP = staminaXP;
    }
    
}
