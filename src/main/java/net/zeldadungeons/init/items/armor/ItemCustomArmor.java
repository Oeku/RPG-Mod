package net.zeldadungeons.init.items.armor;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemCustomArmor extends ItemArmor{

    protected int pieceProtection;
    protected String armorTypeName;
    private String textureName;
    
    /**
     * Creates a custom armor piece. 
     * @param materialIn
     * @param renderIndexIn
     * @param equipmentSlotIn
     * @param prot The protection this piece provides. Boots: 0.15 MAX, Legs: 0.3: MAX, Chest: 0.4 MAX, Helmet 0.15 MAX
     */
    public ItemCustomArmor(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, int prot, String name, String armorTypeName) {
	super(materialIn, renderIndexIn, equipmentSlotIn);
	this.pieceProtection = prot;
	this.armorTypeName = armorTypeName;
	this.setRegistryName(name);
	this.setUnlocalizedName(name);
	int i = 1;
	if(equipmentSlotIn == EntityEquipmentSlot.LEGS)i = 2;
	this.textureName = "zeldadungeons:textures/armor/"+armorTypeName+i+ ".png";
    }
    
    public int getProtection(){
	return this.pieceProtection;
    }
    
    public String getArmortypeName() {
	return this.armorTypeName;
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return this.textureName;
    }
    
    public void addToolTip(List<String> tooltip, ItemStack itemStack){
	
    }
}
