package net.zeldadungeons.init;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Recipizer {
    
    public static void registerSmeltings(){
	GameRegistry.addSmelting(Item.getItemFromBlock(Blockizer.SAPPHIRE_ORE), new ItemStack(Itemizer.SAPPHIRE), 10F);
	GameRegistry.addSmelting(Item.getItemFromBlock(Blockizer.TOPAZ_ORE), new ItemStack(Itemizer.TOPAZ), 5F);
	GameRegistry.addSmelting(Item.getItemFromBlock(Blockizer.LUMINOUS_ORE), new ItemStack(Itemizer.LUMINOUS_STONE, 4), 20F);
	GameRegistry.addSmelting(Item.getItemFromBlock(Blockizer.SALT_ORE), new ItemStack(Itemizer.SALT), 5F);
    }
}
