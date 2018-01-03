package net.zeldadungeons.event;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.zeldadungeons.capability.entitylevels.EntityLevelsContainer;
import net.zeldadungeons.capability.playerlevels.CapabilityPlayerLevels;
import net.zeldadungeons.capability.playerlevels.IPlayerLevels;
import net.zeldadungeons.init.Itemizer;
import net.zeldadungeons.init.entity.EntityMobBase;
import net.zeldadungeons.init.entity.ICustomEntity;
import net.zeldadungeons.skill.SkillCombat;
import net.zeldadungeons.skill.SkillHealth;
import net.zeldadungeons.skill.SkillStamina;
import net.zeldadungeons.util.Log;

@EventBusSubscriber
public class TickEventHandler {
    public static final TickEventHandler INSTANCE = new TickEventHandler();
    private int ticks = 0;

    @SubscribeEvent
    public void onWorldTick(WorldTickEvent e) {
	if (ticks > 1000000)
	    ticks = 1;
	ticks++;
	World world = e.world;
	if (!world.isRemote) {
	    List<Entity> list = world.loadedEntityList;
	    for (Entity entity : list) {
		if (entity instanceof EntityPlayer) {
		    this.executePlayerTick((EntityPlayer) entity, ticks);
		}
	    }
	}
    }

    public void executePlayerTick(EntityPlayer e, int ticks) {
	e.getCapability(CapabilityPlayerLevels.PLAYER_LEVELS_CAPABILITY, CapabilityPlayerLevels.DEFAULT_FACING).getStaminaSkill().sendUpdatePackets(e);;
	SkillStamina stamina = e.getCapability(CapabilityPlayerLevels.PLAYER_LEVELS_CAPABILITY, CapabilityPlayerLevels.DEFAULT_FACING).getStaminaSkill();
	if (!e.capabilities.isCreativeMode) {
	    stamina.regenStamina(ticks);
	    stamina.removeStamina(ticks);
	}
	ArmorHandler.testPlayerBonus(e);
	ArmorHandler.setPlayerProtection(e);

    }

    @SubscribeEvent
    public void onAttack(LivingHurtEvent event) {
	int j = 0;
	float f = event.getAmount();
	Entity exactsource = event.getSource().getImmediateSource();
	// eventually drops a health shard when an entity is hit.
	if (!(event.getEntityLiving() instanceof EntityPlayer)) {
	    EntityLivingBase entity = event.getEntityLiving();
	    Random random = new Random();
	    if (random.nextInt(3) == 2 && event.getAmount() > 1F) {
		EntityItem entityitem = new EntityItem(entity.world, entity.posX, entity.posY, entity.posZ, new ItemStack(Itemizer.HEALTH_SHARD, 1));
		entity.getEntityWorld().spawnEntity(entityitem);
		entityitem.setPickupDelay(20);
	    }
	}

	// tests whether there is a living attacker. if so, it updates the
	// amount of this damage event to the damage of this attacker
	if (exactsource != null && exactsource instanceof EntityMobBase) {
	    EntityLevelsContainer c = ((EntityMobBase) event.getSource().getImmediateSource()).getContainer();
	    if (c.getDamage() != 0)
		j = c.getDamage();
	}
	int i = (int) event.getAmount();
	if (j > 0)
	    i = j;
	// replaces players health with players skill
	if (event.getEntityLiving() instanceof EntityPlayer) {
	    IPlayerLevels pL = event.getEntityLiving().getCapability(CapabilityPlayerLevels.PLAYER_LEVELS_CAPABILITY, CapabilityPlayerLevels.DEFAULT_FACING);
	    SkillHealth hS = pL.getHealthSkill();
	    SkillCombat cS = pL.getCombatSkill();
	    Log.getLogger().info(cS.getProtection());
	    i -= cS.getProtection();
	    if (i < 0)
		i = 0;
	    hS.setCurrentHealth(hS.getCurrentHealth() - i);
	} else if (event.getEntityLiving() instanceof EntityMobBase) {
	    EntityLevelsContainer c1 = ((EntityMobBase) event.getEntityLiving()).getContainer();
	    c1.setCurrentHealth(c1.getCurrentHealth() - i);
	}
	if (event.getEntity() instanceof EntityMobBase || event.getEntity() instanceof EntityPlayer) {
	    f = 0;
	}
	event.setAmount(f);

    }

    @SubscribeEvent
    public void onPickup(EntityItemPickupEvent e) {
	if (e.getItem().getItem().getItem() == Itemizer.HEALTH_SHARD) {
	    SkillHealth skill = e.getEntityPlayer().getCapability(CapabilityPlayerLevels.PLAYER_LEVELS_CAPABILITY, CapabilityPlayerLevels.DEFAULT_FACING).getHealthSkill();
	    skill.gainExp(e.getItem().getItem().getCount());
	    e.getItem().getItem().setCount(0);
	}
    }
}