package net.zeldadungeons.init;

import net.zeldadungeons.ZeldaDungeons;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
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
	public static final ItemArmor.ArmorMaterial CUSTOM1 = EnumHelper.addArmorMaterial("custom1", "custom1", 15, new int[] { 3, 6, 8, 3 }, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);

    }

    /** Items **/
    // Resources
    public static ItemResource SAPPHIRE;
    public static ItemResource AMBER;
    public static ItemResource TOPAZ;
    public static ItemResource LUMINOUS_STONE;
    public static ItemResource SALT;

    public static ItemModPickaxe AMBER_PICKAXE;
    public static ItemModAxe AMBER_AXE;
    public static ItemModSpade AMBER_SHOVEL;
    public static ItemModSword AMBER_SWORD;

    public static ItemModPickaxe SAPPHIRE_PICKAXE;
    public static ItemModAxe SAPPHIRE_AXE;
    public static ItemModSpade SAPPHIRE_SHOVEL;
    public static ItemModSword SAPPHIRE_SWORD;

    public static ItemArmor SAPPIZE_BOOTS;
    public static ItemArmor SAPPIZE_LEGGINGS;
    public static ItemArmor SAPPIZE_CHESTPLATE;
    public static ItemArmor SAPPIZE_HELMET;

    // pickups
    public static Item HEALTH_SHARD;

    // Other

    public static Item HEART_CONTAINER;

    public static Item SMALL_KEY;
    public static Item FAIRY_SLINGSHOT;
    public static Item BLOCK_EDITOR;

    @SubscribeEvent
    public void registerItems(final RegistryEvent.Register<Item> event) {
	Log.getLogger().info("initItems");
	SAPPHIRE = new ItemResource("sapphire");
	AMBER = new ItemResource("amber");
	TOPAZ = new ItemResource("topaz");
	LUMINOUS_STONE = new ItemResource("luminous_stone");
	SALT = new ItemResource("salt");

	AMBER_PICKAXE = new ItemModPickaxe("amber_pickaxe", ToolMaterials.MATERIAL_AMBER);
	AMBER_AXE = new ItemModAxe("amber_axe", ToolMaterials.MATERIAL_AMBER, 0F, 15F);
	AMBER_SHOVEL = new ItemModSpade("amber_shovel", ToolMaterials.MATERIAL_AMBER);
	AMBER_SWORD = new ItemModSword("amber_sword", ToolMaterials.MATERIAL_AMBER);

	SAPPHIRE_PICKAXE = new ItemModPickaxe("sapphire_pickaxe", ToolMaterials.MATERIAL_SAPPHIRE);
	SAPPHIRE_AXE = new ItemModAxe("sapphire_axe", ToolMaterials.MATERIAL_SAPPHIRE, 0F, 15F);
	SAPPHIRE_SHOVEL = new ItemModSpade("sapphire_shovel", ToolMaterials.MATERIAL_SAPPHIRE);
	SAPPHIRE_SWORD = new ItemModSword("sapphire_sword", ToolMaterials.MATERIAL_SAPPHIRE);

	// Armor
	SAPPIZE_BOOTS = new ItemArmorSappize(1, EntityEquipmentSlot.FEET, 1, "sappize_boots");
	SAPPIZE_LEGGINGS = new ItemArmorSappize(1, EntityEquipmentSlot.LEGS, 3, "sappize_leggings");
	SAPPIZE_CHESTPLATE = new ItemArmorSappize(1, EntityEquipmentSlot.CHEST, 4, "sappize_chestplate");
	SAPPIZE_HELMET = new ItemArmorSappize(1, EntityEquipmentSlot.HEAD, 2, "sappize_helmet");

	HEALTH_SHARD = new Item().setRegistryName("health_shard").setUnlocalizedName("health_shard");

	// Other

	HEART_CONTAINER = new ItemHeartContainer("heart_container");

	SMALL_KEY = new ItemSmallKey();
	FAIRY_SLINGSHOT = new ItemFairySlingshot();
	BLOCK_EDITOR = new ItemBlockEditor();

	Item[] items = {
		// Resources
		SAPPHIRE, AMBER, TOPAZ, LUMINOUS_STONE, SALT,

		// Tools
		AMBER_PICKAXE, AMBER_AXE, AMBER_SHOVEL, AMBER_SWORD, SAPPHIRE_PICKAXE, SAPPHIRE_AXE, SAPPHIRE_SHOVEL, SAPPHIRE_SWORD,

		// Armor
		SAPPIZE_BOOTS, SAPPIZE_LEGGINGS, SAPPIZE_CHESTPLATE, SAPPIZE_HELMET,

		// pickups
		HEALTH_SHARD,

		// Other
		HEART_CONTAINER, SMALL_KEY, FAIRY_SLINGSHOT, BLOCK_EDITOR, };
	event.getRegistry().registerAll(items);

    }
}
