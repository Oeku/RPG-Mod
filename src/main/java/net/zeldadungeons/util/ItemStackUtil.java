package net.zeldadungeons.util;

import java.util.Iterator;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public class ItemStackUtil {

    /**
     * The same as in TileEntityCookingPot but usable without instance
     * @param list
     * @param item
     * @return
     */
    public static ItemStack getStackContains(List<ItemStack>list, Item item){
	Iterator it = list.iterator();
	while(it.hasNext()){
	    ItemStack stack = (ItemStack) it.next();
	    if((stack.getItem() == item))return stack;
	}
	return null;
    }
}
