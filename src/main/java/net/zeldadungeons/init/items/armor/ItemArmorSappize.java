package net.zeldadungeons.init.items.armor;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.zeldadungeons.ZeldaDungeons;
import net.zeldadungeons.init.Itemizer;

public class ItemArmorSappize extends ItemCustomArmor {

    public ItemArmorSappize(int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, int prot, String name) {
	super(Itemizer.ArmorMaterials.CUSTOM1, renderIndexIn, equipmentSlotIn, prot, name, "sappize");
    }
    
    @Override
    public void addToolTip(List<String> tooltip, ItemStack stack) {
	tooltip.add(this.getItemStackDisplayName(stack));
	tooltip.add("Set Bonus:");
	tooltip.add("Roughness - Immunity to Instant Kills");
	tooltip.add("Grants "+this.pieceProtection+" protection if worn.");
    }
}
