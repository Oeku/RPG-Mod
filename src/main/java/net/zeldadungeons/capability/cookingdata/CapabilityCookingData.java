package net.zeldadungeons.capability.cookingdata;

import net.minecraft.item.Item;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.zeldadungeons.ZeldaDungeons;
import net.zeldadungeons.capability.playerlevels.IPlayerLevels;
import net.zeldadungeons.capability.playerlevels.PlayerLevels;
import net.zeldadungeons.skill.SkillCombat;
import net.zeldadungeons.skill.SkillHealth;
import net.zeldadungeons.skill.SkillStamina;

public class CapabilityCookingData {
    @CapabilityInject(ICookingData.class)
    public static final Capability<ICookingData> COOKING = null;

    public static final EnumFacing DEFAULT_FACING = null;

    public static final ResourceLocation ID = new ResourceLocation(ZeldaDungeons.MODID, "cooking_data");

    public static void register() {
	CapabilityManager.INSTANCE.register(ICookingData.class, new Capability.IStorage<ICookingData>() {

	    @Override
	    public NBTBase writeNBT(Capability<ICookingData> capability, ICookingData instance, EnumFacing side) {
		NBTTagCompound compound = new NBTTagCompound();
		compound.setString("item", Item.REGISTRY.getNameForObject(instance.getItem()).toString());
		return compound;
	    }

	    @Override
	    public void readNBT(Capability<ICookingData> capability, ICookingData instance, EnumFacing side, NBTBase nbt) {
		NBTTagCompound compound = ((NBTTagCompound) nbt);
		Item item = Item.REGISTRY.getObject(new ResourceLocation(compound.getString("item")));
		instance.setItem(item);
	    }
	}, () -> new CookingData());
    }
}
