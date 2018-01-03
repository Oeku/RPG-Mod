package net.zeldadungeons.event;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.zeldadungeons.capability.playerlevels.CapabilityPlayerLevels;
import net.zeldadungeons.init.items.armor.ItemCustomArmor;
import net.zeldadungeons.skill.SkillCombat;
import net.zeldadungeons.util.Log;

public class ArmorHandler {

    public static void testPlayerBonus(EntityPlayer player) {
	Iterator it = player.getArmorInventoryList().iterator();
	boolean b = true;
	int i = 0;
	String armortype = "";
	while (it.hasNext()) {
	    ItemStack armor = (ItemStack) it.next();
	    if (armor.getItem() instanceof ItemCustomArmor) {
		if (b)
		    armortype = ((ItemCustomArmor) armor.getItem()).getArmortypeName();
		b = false;

		if (armortype == ((ItemCustomArmor) armor.getItem()).getArmortypeName())
		    i++;
	    } else
		break;
	}
	if (i != 4)
	    armortype = "";
	player.getCapability(CapabilityPlayerLevels.PLAYER_LEVELS_CAPABILITY, CapabilityPlayerLevels.DEFAULT_FACING).getCombatSkill().setBonus(armortype);
    }

    public static void setPlayerProtection(EntityPlayer player) {
	Iterator it = player.getArmorInventoryList().iterator();
	int i = 0;
	while (it.hasNext()) {
	    ItemStack s = (ItemStack) it.next();
	    if (s.getItem() instanceof ItemCustomArmor) {
		i += ((ItemCustomArmor) s.getItem()).getProtection();
	    } else if (s.getItem() instanceof ItemArmor) {
		i += ((ItemArmor) s.getItem()).getArmorMaterial().getDamageReductionAmount(((ItemArmor) s.getItem()).armorType);
	    }
	}
	player.getCapability(CapabilityPlayerLevels.PLAYER_LEVELS_CAPABILITY, CapabilityPlayerLevels.DEFAULT_FACING).getCombatSkill().setProtection(i);
    }

}
