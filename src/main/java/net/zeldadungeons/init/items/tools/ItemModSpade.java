package net.zeldadungeons.init.items.tools;

import net.minecraft.item.ItemSpade;

public class ItemModSpade extends ItemSpade{
    public ItemModSpade(String name, ToolMaterial material) {
	super(material);
	this.setRegistryName(name);
	this.setUnlocalizedName(name);
    }
}
