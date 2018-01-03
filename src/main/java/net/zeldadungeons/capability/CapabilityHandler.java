package net.zeldadungeons.capability;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.zeldadungeons.ZeldaDungeons;
import net.zeldadungeons.capability.entitylevels.CapabilityEntityLevels;
import net.zeldadungeons.capability.entitylevels.EntityLevels;
import net.zeldadungeons.capability.entitylevels.EntityLevelsContainer;
import net.zeldadungeons.capability.playerlevels.CapabilityPlayerLevels;
import net.zeldadungeons.capability.playerlevels.IPlayerLevels;
import net.zeldadungeons.capability.playerlevels.PlayerLevels;
import net.zeldadungeons.init.entity.ICustomEntity;
import net.zeldadungeons.init.entity.living.overworld.EntityGorok;
import net.zeldadungeons.init.entity.living.overworld.EntityLandamus;
import net.zeldadungeons.init.entity.living.overworld.EntityProoper;
import net.zeldadungeons.init.entity.tile.TileEntityCookingPot;
import net.zeldadungeons.skill.SkillCombat;
import net.zeldadungeons.skill.SkillHealth;
import net.zeldadungeons.skill.SkillStamina;

@Mod.EventBusSubscriber
public class CapabilityHandler {

    public static final CapabilityHandler INSTANCE = new CapabilityHandler();

    @SubscribeEvent
    public void attachBlockCapability(AttachCapabilitiesEvent<TileEntityCookingPot> event){
	
    }
    
    @SubscribeEvent
    public void attachEntityCapability(AttachCapabilitiesEvent<Entity> event) {
	if (event.getObject().world.isRemote)
	    return;
	if (event.getObject() instanceof ICustomEntity) {
	    final EntityLevels entitylevels = new EntityLevels((EntityLivingBase) event.getObject(), new EntityLevelsContainer((EntityLivingBase) event.getObject(), 1, 1, 1));
	    event.addCapability(CapabilityEntityLevels.ID, new CapabilityProviderSerializable<>(CapabilityEntityLevels.ENTITY_LEVELS_CAPABILITY, CapabilityEntityLevels.DEFAULT_FACING, entitylevels));
	    this.setEntityProperties(event.getObject(), entitylevels);
	} else if (event.getObject() instanceof EntityPlayer) {
	    final PlayerLevels playerlevels = new PlayerLevels((EntityPlayer) event.getObject());
	    event.addCapability(CapabilityPlayerLevels.ID, new CapabilityProviderSerializable<>(CapabilityPlayerLevels.PLAYER_LEVELS_CAPABILITY, CapabilityPlayerLevels.DEFAULT_FACING, playerlevels));
	}
    }

    private void setEntityProperties(Entity object, EntityLevels entitylevels) {
	Random r = ZeldaDungeons.RANDOM;
	int level = 0;
	float baseHealth = 1.0F;
	float baseDamage = 1.0F;
	int health = 0;
	int damage = 0;
	EntityLevelsContainer c = entitylevels.getContainer();
	if (object instanceof EntityLandamus) {
	    level = r.nextInt(10) + 1;
	    health = (c.calculateMaxHealth(level));
	    damage = (c.calculateDamage(level));
	} else if (object instanceof EntityGorok) {
	    level = r.nextInt(5) + 1;
	    health = (int) (1F * c.calculateMaxHealth(level));
	    damage = (int) (0.5F * c.calculateDamage(level));

	} else if (object instanceof EntityProoper) {
	    level = r.nextInt(5) + 1;
	    health = (int) (0.7F * c.calculateMaxHealth(level));
	    damage = (int) (0.3F * c.calculateDamage(level));
	}
	c.setLevel(level);
	c.setMaxHealth(health);
	c.setDamage(damage);
	c.setCurrentHealth(health);
    }

    public void registerCapabilities() {
	CapabilityPlayerLevels.register();
	CapabilityEntityLevels.register();
    }

    @SubscribeEvent
    public void playerClone(PlayerEvent.Clone event) {
	final IPlayerLevels instance = event.getOriginal().getCapability(CapabilityPlayerLevels.PLAYER_LEVELS_CAPABILITY, CapabilityPlayerLevels.DEFAULT_FACING);
	IPlayerLevels newInstance = event.getEntityPlayer().getCapability(CapabilityPlayerLevels.PLAYER_LEVELS_CAPABILITY, CapabilityPlayerLevels.DEFAULT_FACING);
	SkillHealth hS = instance.getHealthSkill();
	SkillStamina sS = instance.getStaminaSkill();
	SkillCombat cS = instance.getCombatSkill();
	int[] health = { hS.getLevel(), hS.getTotalExp(), hS.getCurrentHealth(), hS.getMaxHealth() };
	int[] stamina = { sS.getLevel(), sS.getTotalExp(), sS.getCurrentStamina(), sS.getMaxStamina() };
	int[] combat = { cS.getLevel(), cS.getTotalExp(), cS.getDamage() };
	if (instance != null && newInstance != null) {
	    SkillHealth hS1 = new SkillHealth(health[0], health[1]);
	    hS.setMaxHealth(health[3]);
	    hS.setCurrentHealth(health[3]);
	    newInstance.setHealthSkill(hS1);
	    SkillStamina sS1 = new SkillStamina(stamina[0], stamina[1]);
	    sS.setMaxStamina(stamina[3]);
	    sS.setCurrentStamina(stamina[3]);
	    newInstance.setStaminaSkill(sS);
	    SkillCombat cS1 = new SkillCombat(combat[0], combat[1]);
	    cS.setDamage(combat[2]);
	    newInstance.setCombatSkill(cS1);
	}
    }
}
