package net.zeldadungeons.capability.entitylevels;

import net.zeldadungeons.*;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityEntityLevels {
	
	@CapabilityInject(IEntityLevels.class)
	public static final Capability<IEntityLevels> ENTITY_LEVELS_CAPABILITY = null;

	public static final EnumFacing DEFAULT_FACING = null;

	public static final ResourceLocation ID = new ResourceLocation(ZeldaDungeons.MODID, "entity_levels");
	
	public static void register(){
		CapabilityManager.INSTANCE.register(IEntityLevels.class, new Capability.IStorage<IEntityLevels>() {

			@Override
			public NBTBase writeNBT(Capability<IEntityLevels> capability, IEntityLevels instance, EnumFacing side) {
				NBTTagCompound compound = new NBTTagCompound();
				return compound;
			}

			@Override
			public void readNBT(Capability<IEntityLevels> capability, IEntityLevels instance, EnumFacing side,
					NBTBase nbt) {
				NBTTagCompound compound = ((NBTTagCompound)nbt);
			}
		}, () -> new EntityLevels(null));
	}
}
