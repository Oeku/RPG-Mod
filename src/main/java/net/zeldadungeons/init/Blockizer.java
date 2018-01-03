package net.zeldadungeons.init;

import java.util.HashSet;
import java.util.Set;

import net.zeldadungeons.ZeldaDungeons;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;
import net.zeldadungeons.init.blocks.BlockAmber;
import net.zeldadungeons.init.blocks.BlockBase;
import net.zeldadungeons.init.blocks.BlockBaseOre;
import net.zeldadungeons.init.blocks.BlockChest;
import net.zeldadungeons.init.blocks.BlockCookingPot;
import net.zeldadungeons.init.blocks.BlockPressureSwitch;
import net.zeldadungeons.init.entity.tile.TileEntityCookingPot;
import net.zeldadungeons.util.Log;

@ObjectHolder(ZeldaDungeons.MODID)
public class Blockizer {
    public static final Block SAPPHIRE_ORE = new BlockBaseOre("sapphire_ore").setHardness(15.0F).setResistance(20.0F);
    public static final Block TOPAZ_ORE = new BlockBaseOre("topaz_ore").setHardness(5.0F).setResistance(5.0F);
    public static final Block LUMINOUS_ORE = new BlockBaseOre("luminous_ore").setHardness(3.0F).setResistance(5.0F);
    public static final Block SALT_ORE = new BlockBaseOre("salt_ore").setHardness(0.6F).setResistance(1.5F);
    public static final Block AMBER_BLOCK = new BlockAmber().setHardness(1.0F).setResistance(1.0F);

    public static final Block COOKING_POT = new BlockCookingPot("cooking_pot").setHardness(10F).setResistance(12F);

    public static final Block PRESSURE_SWITCH = new BlockPressureSwitch(Material.ROCK);
    public static final Block CHEST = new BlockChest(Material.WOOD);

    public static void renderBlocks() {
	renderBlock(PRESSURE_SWITCH, "pressureSwitch");
    }

    public static void renderBlock(Block block, String name) {
	Item item = Item.getItemFromBlock(block);
	ModelResourceLocation model = new ModelResourceLocation(ZeldaDungeons.MODID + ":" + name, "inventory");
	ModelLoader.setCustomModelResourceLocation(item, 0, model);
	ModelBakery.registerItemVariants(item, model);
	Log.getLogger().info("renderBlock" + block.getRegistryName());
    }

    @Mod.EventBusSubscriber(modid = ZeldaDungeons.MODID)
    public static class RegistrationHandler {
	public static final Set<ItemBlock> ITEM_BLOCKS = new HashSet<>();

	/**
	 * Register this mod's {@link Block}s.
	 *
	 * @param event The event
	 */
	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
	    final IForgeRegistry<Block> registry = event.getRegistry();

	    final Block[] blocks = {
		    /** ores **/
		    SALT_ORE, LUMINOUS_ORE, TOPAZ_ORE, SAPPHIRE_ORE, AMBER_BLOCK,

		    /** decoration/generated **/
		    COOKING_POT,

		    /** deprecated **/
		    PRESSURE_SWITCH, CHEST, };

	    registry.registerAll(blocks);
	    registerTileEntities();
	}

	/**
	 * Register this mod's {@link ItemBlock}s.
	 *
	 * @param event The event
	 */
	@SubscribeEvent
	public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {
	    final ItemBlock[] items = { new ItemBlock(PRESSURE_SWITCH), new ItemBlock(CHEST), new ItemBlock(SALT_ORE), new ItemBlock(LUMINOUS_ORE), new ItemBlock(TOPAZ_ORE), new ItemBlock(SAPPHIRE_ORE), new ItemBlock(COOKING_POT),

	    };

	    final IForgeRegistry<Item> registry = event.getRegistry();

	    for (final ItemBlock item : items) {
		final Block block = item.getBlock();
		final ResourceLocation registryName = new ResourceLocation(block.getRegistryName() + "");
		registry.register(item.setRegistryName(registryName));
		ITEM_BLOCKS.add(item);
	    }
	}

	public static void registerTileEntities() {
	    TileEntity.register("cooking_pot", TileEntityCookingPot.class);
	}
    }
}