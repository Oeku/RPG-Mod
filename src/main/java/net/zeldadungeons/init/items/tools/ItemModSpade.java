package net.zeldadungeons.init.items.tools;

import net.minecraft.item.ItemSpade;
import net.zeldadungeons.ZeldaDungeons;

public class ItemModSpade extends ItemSpade{
    public ItemModSpade(String name, ToolMaterial material) {
	super(material);
	this.setRegistryName(ZeldaDungeons.MODID, name);
	this.setUnlocalizedName(name);
    }
}
