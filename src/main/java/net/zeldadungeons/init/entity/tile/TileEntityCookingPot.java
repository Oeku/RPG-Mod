package net.zeldadungeons.init.entity.tile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.WorldServer;
import net.zeldadungeons.capability.cookingdata.CapabilityCookingData;
import net.zeldadungeons.init.Itemizer;
import net.zeldadungeons.init.blocks.BlockCookingPot;
import net.zeldadungeons.init.cooking.CookingEffectAmpl;
import net.zeldadungeons.init.cooking.CookingManager;
import net.zeldadungeons.init.cooking.ICookingIngredient;
import net.zeldadungeons.init.cooking.IngredientData;
import net.zeldadungeons.init.items.ItemModFood;
import net.zeldadungeons.util.Log;
import net.zeldadungeons.util.PacketUtil;

public class TileEntityCookingPot extends TileEntity implements ITickable {
    private static final AxisAlignedBB RADIUS = new AxisAlignedBB(0.0D, 0.5D, 0.0D, 1.0D, 0.85D, 1.0D);
    /**
     * A List of Items that this Cooking Pot holds. Used to evaluate the cooking
     * result
     **/
    private NonNullList<ItemStack> ingredientList;
    private List<CookingEffectAmpl> effectlist;
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

    /**
     * Called to update this Pot.
     */
    @Override
    public void update() {
	if (world.isRemote) return;
	reduceFireTime();
	startCooking();
	if (cooking){
	    handleCooking();
	    terminateCooking();
	}
	else if(!this.isPotFull())this.addPotentialIngredients();
	this.updateBlockState();
	this.spawnParticles();
    }

    public void reduceFireTime() {
	if (fireTime > 0) --fireTime;
    }

    /**
     * Called to initiate the cooking process.
     */
    public void startCooking() {
	if (!this.cooking && this.isPotFull() && this.fireTime >= cookingTime) {
	    this.cooking = true;
	}
    }

    /**
     * handles everything while this pot is cooking.
     */
    public void handleCooking() {
	--this.cookingTime;
	if (this.cookingTime == 10) {
	    this.effectlist = new ArrayList<CookingEffectAmpl>();
	    this.addEffects();
	}
	else if(this.cookingTime == 5){
	    //returns a combined, sorted List of the current effectlist.
	    this.effectlist = CookingManager.combineEffects(this.effectlist);
	}
	else if(this.cookingTime == 1){
	    ItemModFood stack = CookingManager.processCooking(this.ingredientList,  this.effectlist, 3);
	    EntityItem entity = new EntityItem(this.world, this.pos.getX(), this.pos.getY(), this.pos.getZ(), new ItemStack(stack));
	    this.world.spawnEntity(entity);
	    entity.motionX = Math.random();
	    entity.motionY = Math.random();
	    entity.motionZ = Math.random();
	    Log.getLogger().info(stack);
	}
    }
    
    /**
     * Checks if the cooking process needs to be stopped.
     * If so, resets the process.
     */
    public void terminateCooking(){
	if(this.fireTime == 0){
	    this.cooking = false;
	    this.cookingTime = 1000;
	}
	else if(this.cookingTime == 0){
	    this.cooking = false;
	    this.cookingTime = 1000;
	}
    }

    /**
     * Adds EffectList of all ItemStacks, to make evaluation of Item more performant.
     */
    public void addEffects() {
	for (ItemStack it : this.ingredientList) {
	    if (it.getItem() instanceof ICookingIngredient) {
		IngredientData d = (IngredientData) ((ICookingIngredient) it.getItem()).getIngredient().getEffectList();
		this.effectlist.addAll(d.getEffectList());
	    }
	    else if(it.hasCapability(CapabilityCookingData.COOKING, CapabilityCookingData.DEFAULT_FACING)){
		this.effectlist.addAll(it.getCapability(CapabilityCookingData.COOKING, CapabilityCookingData.DEFAULT_FACING).getData().getEffectList());
	    }
	}
    }
    
    /**
     * Adds ingredients from EntityItems in the world to this pot.
     */
    public void addPotentialIngredients() {
	// all entities in range of this pot
	List<EntityItem> ingredients = this.world.getEntitiesWithinAABB(EntityItem.class, RADIUS.offset(pos));
	for (EntityItem e : ingredients) {
	    // entities need time before they can be added to the pot
	    if (!e.cannotPickup()) {
		ItemStack stack = e.getItem();
		int numberDecreased = 0;
		for (int i = 1; i <= stack.getCount(); i++) {
		    if (this.isPotFull()) break;
		    ItemStack stack2 = this.doesListContain(stack.getItem());
		    if (stack2 != null) stack2.setCount(stack2.getCount() + 1);
		    else this.ingredientList.add(new ItemStack(e.getItem().getItem()));
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

    /**
     * Checks if the ingredientList of this TileEntity already contains an
     * ItemStack of a given Item
     * 
     * @param item The Item
     * @return The ItemStack or null if not found
     */
    public ItemStack doesListContain(Item item) {
	Iterator it = this.ingredientList.iterator();
	while (it.hasNext()) {
	    ItemStack stack = (ItemStack) it.next();
	    if ((stack.getItem() == item)) return stack;
	}
	return null;
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
	    PacketUtil.spawnParticle((WorldServer) world, EnumParticleTypes.FLAME, this.pos.getX() + d0, this.pos.getY() + d1, this.pos.getZ() + d2, 1, 0D, 0);
	    if (this.cooking) {
		d0 = Math.random() * 0.6D + 0.2D;
		d1 = Math.random() * 0.1D + 0.55D;
		d2 = Math.random() * 0.6D + 0.2D;
		PacketUtil.spawnParticle((WorldServer) world, EnumParticleTypes.WATER_BUBBLE, this.pos.getX() + d0, this.pos.getY() + d1, this.pos.getZ() + d2, 1, 0D, 0);
	    }
	}
    }

    public void updateBlockState() {
	this.world.setBlockState(pos, world.getBlockState(pos).withProperty(BlockCookingPot.COOKING, this.cooking));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
	int[] data = { this.capacity, this.cookingTime, this.cookingTimeSave };
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
