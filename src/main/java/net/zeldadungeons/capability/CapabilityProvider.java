package net.zeldadungeons.capability;

import javax.annotation.Nullable;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.zeldadungeons.util.Log;

public class CapabilityProvider<HANDLER> implements ICapabilityProvider {
	
	protected final Capability<HANDLER> capability;
	protected final EnumFacing facing;
	protected final HANDLER instance;
	
	public CapabilityProvider(final Capability<HANDLER> capability, @Nullable final EnumFacing facing, @Nullable final HANDLER instance)
	{
		this.capability = capability;
		this.facing = facing;
		this.instance = instance;
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == getCapability();
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
	    if(capability.getName().equals("net.zeldadungeons.capability.playerlevels.IPlayerLevels") || capability.getName().equals("net.zeldadungeons.capability.playerlevels.ICookingData")){
		return getCapability().cast(getInstance());
	    }
	    else return null;
	}
	
	public final Capability<HANDLER> getCapability(){
		return capability;
	}
	
	@Nullable
	public EnumFacing getFacing(){
		return facing;
	}
	
	@Nullable
	public final HANDLER getInstance(){
		return instance;
	}
}
