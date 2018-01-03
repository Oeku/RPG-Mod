package net.zeldadungeons.capability.playerlevels;

import net.zeldadungeons.ZeldaDungeons;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.zeldadungeons.skill.SkillCombat;
import net.zeldadungeons.skill.SkillHealth;
import net.zeldadungeons.skill.SkillStamina;

public class CapabilityPlayerLevels {
    @CapabilityInject(IPlayerLevels.class)
    public static final Capability<IPlayerLevels> PLAYER_LEVELS_CAPABILITY = null;

    public static final EnumFacing DEFAULT_FACING = null;

    public static final ResourceLocation ID = new ResourceLocation(ZeldaDungeons.MODID, "player_levels");

    public static void register() {
	CapabilityManager.INSTANCE.register(IPlayerLevels.class, new Capability.IStorage<IPlayerLevels>() {

	    @Override
	    public NBTBase writeNBT(Capability<IPlayerLevels> capability, IPlayerLevels instance, EnumFacing side) {
		SkillHealth hS = instance.getHealthSkill();
		SkillStamina sS = instance.getStaminaSkill();
		SkillCombat cS = instance.getCombatSkill();
		int[] health = { hS.getLevel(), hS.getTotalExp(), hS.getCurrentHealth(), hS.getMaxHealth() };
		int[] stamina = { sS.getLevel(), sS.getTotalExp(), sS.getCurrentStamina(), sS.getMaxStamina() };
		int[] combat = { cS.getLevel(), cS.getTotalExp(), cS.getDamage() };
		NBTTagCompound compound = new NBTTagCompound();
		compound.setIntArray("health", health);
		compound.setIntArray("stamina", stamina);
		compound.setIntArray("combat", combat);
		return compound;
	    }

	    @Override
	    public void readNBT(Capability<IPlayerLevels> capability, IPlayerLevels instance, EnumFacing side, NBTBase nbt) {
		NBTTagCompound compound = ((NBTTagCompound) nbt);
		int[] health = compound.getIntArray("health");
		int[] stamina = compound.getIntArray("stamina");
		int[] combat = compound.getIntArray("combat");

		SkillHealth hS = new SkillHealth(health[0], health[1]);
		hS.setMaxHealth(health[3]);
		hS.setCurrentHealth(health[2]);
		instance.setHealthSkill(hS);
		SkillStamina sS = new SkillStamina(stamina[0], stamina[1]);
		sS.setMaxStamina(stamina[3]);
		sS.setCurrentStamina(stamina[2]);
		instance.setStaminaSkill(sS);
		SkillCombat cS = new SkillCombat(combat[0], combat[1]);
		cS.setDamage(combat[2]);

	    }
	}, () -> new PlayerLevels());
    }
}
