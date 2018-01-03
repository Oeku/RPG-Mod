package net.zeldadungeons.init.items.tools;

import net.minecraft.item.ItemAxe;

public class ItemModAxe extends ItemAxe{

    public ItemModAxe(String name, ToolMaterial material, float dmg, float speed ) {
	super(material, dmg, speed);
	this.setRegistryName(name);
	this.setUnlocalizedName(name);
    }

}
