package net.zeldadungeons.init.entity.tile;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.NetworkSystem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.zeldadungeons.init.Itemizer;
import net.zeldadungeons.init.blocks.BlockCookingPot;
import net.zeldadungeons.init.items.IMeal;
import net.zeldadungeons.network.NetworkHandler;
import net.zeldadungeons.util.Log;
import net.zeldadungeons.util.PacketUtil;

public class TileEntityCookingPot extends TileEntity implements ITickable {
    private static final AxisAlignedBB RADIUS = new AxisAlignedBB(0.0D, 0.5D, 0.0D, 1.0D, 0.85D, 1.0D);
    /**
     * A List of Items that this Cooking Pot holds. Used to evaluate the cooking
     * result
     **/
    private NonNullList<ItemStack> ingredientList;
    /** How many ingredients this cooking pot can hold. Default: 10 **/
    private int capacity;
    /** if cookingTime should lose **/
    private boolean cooking;
    /**
     * how long this pot will burn, increased by use of fireable items like coal
     **/
    public int fireTime;
    /** how long this pot has been cooking **/
    private int cookingTime;
    /** how long this pot needs to cook before getting a meal **/
    private int cookingTimeSave;

    public TileEntityCookingPot(int capacity, int cookingTime) {
	this.capacity = capacity;
	this.cookingTime = cookingTime;
	this.cookingTimeSave = cookingTime;
	this.ingredientList = NonNullList.<ItemStack>create();

    }

    @Override
    public void update() {
	if(world.isRemote)return;
	// reduce the time that this pot is burning.
	if (fireTime > 0) --fireTime;
	if (this.isPotFull() && this.fireTime >= this.cookingTimeSave) {
	    this.cooking = true;
	}
	else if(this.fireTime <= 0 || this.cookingTime <= 1){
	    this.cooking = false;
	    this.cookingTime = this.cookingTimeSave;
	}
	if(cookingTime == 1){
	    this.cook();
	}
	// spawn particles
	this.spawnParticles();
	if (!this.cooking) this.addPotentialIngredients();
	this.updateBlockState();
    }

    public void addPotentialIngredients() {
	// all entities in range of this pot
	List<EntityItem> ingredients = this.world.getEntitiesWithinAABB(EntityItem.class, RADIUS.offset(pos));
	for (EntityItem e : ingredients) {
	    // entities need before they can be added to the pot
	    if (!e.cannotPickup()) {
		ItemStack stack = e.getItem();
		int numberDecreased = 0;
		for (int i = 1; i <= stack.getCount(); i++) {
		    if (this.isPotFull()) break;
		    this.ingredientList.add(new ItemStack(e.getItem().getItem()));
		    Log.getLogger().info("added " + stack.getItem().getRegistryName());
		    numberDecreased++;
		}
		stack.shrink(numberDecreased);
		if (stack.isEmpty()) {
		    e.setDead();
		}
		if (this.isPotFull()) break;
	    }
	}
    }

    public ItemStack evaluateCookingResult() {
	return new ItemStack(Itemizer.TOPAZ, 1);
    }

    public void cook() {
	if (this.cookingTime <= 0) {
	    EntityItem entity = new EntityItem(this.world, this.pos.getX(), this.pos.getY(), this.pos.getZ(), evaluateCookingResult());
	    this.world.spawnEntity(entity);
	    entity.motionX = Math.random();
	    entity.motionY = Math.random();
	    entity.motionZ = Math.random();
	}
    }

    public boolean isPotFull() {
	return this.ingredientList.size() >= capacity;
    }

    public void setCooking(boolean b) {
	this.cooking = b;
    }

    public void logThings() {
	Log.getLogger().info(this.fireTime);
	Log.getLogger().info(this.cooking);
	Log.getLogger().info(this.cookingTime);
	Log.getLogger().info(this.cookingTimeSave);
	for (ItemStack i : ingredientList) {
	    Log.getLogger().info(i.getItem().getRegistryName().getResourcePath());
	}
	Log.getLogger().info(world.getBlockState(pos).getBlock().getRegistryName());
	Log.getLogger().info(world.getBlockState(pos).getValue(BlockCookingPot.COOKING));
    }

    public void addFireTime(int i) {
	this.fireTime += i;
    }

    public void spawnParticles() {
	double d1 = Math.random() * 0.3D;
	double d0 = Math.random() * 0.2D + 0.4D;
	double d2 = Math.random() * 0.2D + 0.4D;
	if (fireTime > 0 && this.world instanceof WorldServer) {
	    PacketUtil.spawnParticle((WorldServer)world, EnumParticleTypes.FLAME, this.pos.getX()+d0, this.pos.getY() + d1, this.pos.getZ() + d2, 1, 0D, 0);
	    if (this.cooking) {
		d0 = Math.random() * 0.6D + 0.2D;
		d1 = Math.random() * 0.1D + 0.55D;
		d2 = Math.random() * 0.6D + 0.2D;
		PacketUtil.spawnParticle((WorldServer)world, EnumParticleTypes.WATER_BUBBLE, this.pos.getX()+d0, this.pos.getY() + d1, this.pos.getZ() + d2, 1, 0D, 0);
	    }
	}
    }
    
    public void updateBlockState(){
	this.world.setBlockState(pos, world.getBlockState(pos).withProperty(BlockCookingPot.COOKING, this.cooking));
    }
    
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
	int[] data = {this.capacity, this.cookingTime, this.cookingTimeSave};
	compound.setBoolean("cooking", this.cooking);
	compound.setIntArray("data", data);
	ItemStackHelper.saveAllItems(compound, this.ingredientList);
        return compound;
    }
    
    @Override
    public void readFromNBT(NBTTagCompound compound) {
	this.cooking = compound.getBoolean("cooking");
	int[] data = compound.getIntArray("data");
	this.capacity = data[0];
	this.cookingTime = data[1];
	this.cookingTimeSave = data[3];
    }
}
