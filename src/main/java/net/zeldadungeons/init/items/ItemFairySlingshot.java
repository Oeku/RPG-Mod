package net.zeldadungeons.init.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.zeldadungeons.init.entity.projectile.EntitySlingshotPellet;

public class ItemFairySlingshot extends Item{
	public ItemFairySlingshot()
	{
		this.setRegistryName(new ResourceLocation("zeldadungeons:fairy_slingshot"));
		this.setMaxDamage(-1);
	}
	
    private ItemStack findAmmo(EntityPlayer player)
    {
        if (this.isArrow(player.getHeldItem(EnumHand.OFF_HAND)))
        {
            return player.getHeldItem(EnumHand.OFF_HAND);
        }
        else if (this.isArrow(player.getHeldItem(EnumHand.MAIN_HAND)))
        {
            return player.getHeldItem(EnumHand.MAIN_HAND);
        }
        else
        {
            for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
            {
                ItemStack itemstack = player.inventory.getStackInSlot(i);

                if (this.isArrow(itemstack))
                {
                    return itemstack;
                }
            }

            return ItemStack.EMPTY;
        }
    }

    protected boolean isArrow(ItemStack stack)
    {
        return stack.getItem() instanceof ItemArrow;
    }
    
    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
    {
    	 if (entityLiving instanceof EntityPlayer)
         {
             EntityPlayer entityplayer = (EntityPlayer)entityLiving;
             ItemStack itemstack = this.findAmmo(entityplayer);
         }
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
    	ItemStack item = playerIn.getHeldItem(handIn); 
    	if(!worldIn.isRemote)worldIn.spawnEntity(new EntitySlingshotPellet(worldIn, playerIn));
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);

    }
}
