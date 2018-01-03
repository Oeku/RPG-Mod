package net.zeldadungeons.init;

import net.zeldadungeons.ZeldaDungeons;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.zeldadungeons.init.items.ItemBlockEditor;
import net.zeldadungeons.init.items.ItemFairySlingshot;
import net.zeldadungeons.init.items.ItemHeartContainer;
import net.zeldadungeons.init.items.ItemResource;
import net.zeldadungeons.init.items.ItemSmallKey;
import net.zeldadungeons.init.items.armor.ItemArmorSappize;
import net.zeldadungeons.init.items.armor.ItemCustomArmor;
import net.zeldadungeons.init.items.tools.ItemModAxe;
import net.zeldadungeons.init.items.tools.ItemModPickaxe;
import net.zeldadungeons.init.items.tools.ItemModSpade;
import net.zeldadungeons.init.items.tools.ItemModSword;
import net.zeldadungeons.util.Log;

@Mod.EventBusSubscriber(modid = ZeldaDungeons.MODID)
@ObjectHolder(ZeldaDungeons.MODID)
public class Itemizer {

    public static class ToolMaterials {
	public static final Item.ToolMaterial MATERIAL_AMBER = EnumHelper.addToolMaterial("amber", 4, 2, 15.0F, 0.0F, 1);
	public static final Item.ToolMaterial MATERIAL_SAPPHIRE = EnumHelper.addToolMaterial("sapphire_pickaxe", 6, 1000, 10.0F, 0.0F, 20);
    }
    
    public static class ArmorMaterials {
	public static final ItemArmor.ArmorMaterial CUSTOM1 = EnumHelper.addArmorMaterial("custom1", "custom1", 15, new int[]{3, 6, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);

    }
    

    /** Items **/
    // Resources
    public static final ItemResource SAPPHIRE = new ItemResource("sapphire");
    public static final ItemResource AMBER = new ItemResource("amber");
    public static final ItemResource TOPAZ = new ItemResource("topaz");
    public static final ItemResource LUMINOUS_STONE = new ItemResource("luminous_stone");
    public static final ItemResource SALT = new ItemResource("salt");

    // Tools
    public static final ItemModPickaxe AMBER_PICKAXE = new ItemModPickaxe("amber_pickaxe", ToolMaterials.MATERIAL_AMBER);
    public static final ItemModAxe AMBER_AXE = new ItemModAxe("amber_axe", ToolMaterials.MATERIAL_AMBER, 0F, 15F);
    public static final ItemModSpade AMBER_SHOVEL = new ItemModSpade("amber_shovel", ToolMaterials.MATERIAL_AMBER);
    public static final ItemModSword AMBER_SWORD = new ItemModSword("amber_sword", ToolMaterials.MATERIAL_AMBER);

    public static final ItemModPickaxe SAPPHIRE_PICKAXE = new ItemModPickaxe("sapphire_pickaxe", ToolMaterials.MATERIAL_SAPPHIRE);
    public static final ItemModAxe SAPPHIRE_AXE = new ItemModAxe("sapphire_axe", ToolMaterials.MATERIAL_SAPPHIRE, 0F, 15F);
    public static final ItemModSpade SAPPHIRE_SHOVEL = new ItemModSpade("sapphire_shovel", ToolMaterials.MATERIAL_SAPPHIRE);
    public static final ItemModSword SAPPHIRE_SWORD = new ItemModSword("sapphire_sword", ToolMaterials.MATERIAL_SAPPHIRE);
    
    //Armor
    public static final ItemArmor SAPPIZE_BOOTS = new ItemArmorSappize(1, EntityEquipmentSlot.FEET, 1, "sappize_boots");
    public static final ItemArmor SAPPIZE_LEGGINGS = new ItemArmorSappize(1, EntityEquipmentSlot.LEGS, 3, "sappize_leggings");
    public static final ItemArmor SAPPIZE_CHESTPLATE = new ItemArmorSappize(1, EntityEquipmentSlot.CHEST, 4, "sappize_chestplate");
    public static final ItemArmor SAPPIZE_HELMET = new ItemArmorSappize(1, EntityEquipmentSlot.HEAD, 2, "sappize_helmet");

    
    // pickups
    public static final Item HEALTH_SHARD = new Item().setRegistryName("health_shard").setUnlocalizedName("health_shard");

    // Other

    public static final Item HEART_CONTAINER = new ItemHeartContainer("heart_container");

    public static final Item SMALL_KEY = new ItemSmallKey();
    public static final Item FAIRY_SLINGSHOT = new ItemFairySlingshot();
    public static final Item BLOCK_EDITOR = new ItemBlockEditor();

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
	Log.getLogger().info("initItems");
	Item[] items = {
		// Resources
		SAPPHIRE, AMBER, TOPAZ, LUMINOUS_STONE, SALT,

		// Tools
		AMBER_PICKAXE, AMBER_AXE, AMBER_SHOVEL, AMBER_SWORD, SAPPHIRE_PICKAXE, SAPPHIRE_AXE, SAPPHIRE_SHOVEL, SAPPHIRE_SWORD,
		
		//Armor
		SAPPIZE_BOOTS, SAPPIZE_LEGGINGS, SAPPIZE_CHESTPLATE, SAPPIZE_HELMET,
		
		
		// pickups
		HEALTH_SHARD,

		// Other
		HEART_CONTAINER, SMALL_KEY, FAIRY_SLINGSHOT, BLOCK_EDITOR, };
	event.getRegistry().registerAll(items);

    }
}
