package net.zeldadungeons.init.items.tools;

import net.minecraft.item.ItemPickaxe;

public class ItemModPickaxe extends ItemPickaxe {

    public ItemModPickaxe(String name, ToolMaterial material) {
	super(material);
	this.setRegistryName(name);
	this.setUnlocalizedName(name);
	
    }

}
