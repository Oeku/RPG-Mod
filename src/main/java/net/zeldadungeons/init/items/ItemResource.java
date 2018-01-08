package net.zeldadungeons.init.items;

import net.minecraft.item.Item;

public class ItemResource extends Item{

	public ItemResource(String name)
	{
		super();
		this.setName(name);
		
	}
	
	public void setName(String name)
	{
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
	}
	
	
	
	
}
