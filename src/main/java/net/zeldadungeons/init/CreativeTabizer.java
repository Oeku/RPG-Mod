package net.zeldadungeons.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CreativeTabizer
{
	public static final CreativeTabs zeldadungeons_items = new CreativeTabs(CreativeTabs.CREATIVE_TAB_ARRAY.length, "zeldadungeons_items")
    {
        @Override
	@SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem()
        {
            return new ItemStack(Itemizer.SMALL_KEY);
        }
    };
}
