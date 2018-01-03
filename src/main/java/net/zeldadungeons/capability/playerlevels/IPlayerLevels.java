package net.zeldadungeons.capability.playerlevels;

import net.minecraft.entity.player.EntityPlayer;
import net.zeldadungeons.skill.SkillCombat;
import net.zeldadungeons.skill.SkillHealth;
import net.zeldadungeons.skill.SkillStamina;

public interface IPlayerLevels {
    
    SkillHealth getHealthSkill();

    SkillStamina getStaminaSkill();

    EntityPlayer getPlayer();

    void setHealthSkill(SkillHealth skill);

    void setStaminaSkill(SkillStamina skill);

    void setPlayer(EntityPlayer player);

    SkillCombat getCombatSkill();

    void setCombatSkill(SkillCombat skill);

    void sendUpdates();

}
