package net.zeldadungeons.init;

import net.zeldadungeons.ZeldaDungeons;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = ZeldaDungeons.MODID)
public class Modelizer {
	public static final Modelizer INSTANCE = new Modelizer();

	@SubscribeEvent
	public static void registerAll(final ModelRegistryEvent e) {
		INSTANCE.registerItemModels();
		INSTANCE.registerBlockModels();
	}

	private void registerItemModels() {
		registerItemModel(Itemizer.SAPPHIRE);
		registerItemModel(Itemizer.TOPAZ);
		registerItemModel(Itemizer.AMBER);
		registerItemModel(Itemizer.LUMINOUS_STONE);
		registerItemModel(Itemizer.SALT);
		registerItemModel(Itemizer.SILVER);
		registerItemModel(Itemizer.COPPER);
		registerItemModel(Itemizer.TIN);
		registerItemModel(Itemizer.BRONZE);

		
		registerItemModel(Itemizer.AMBER_PICKAXE);
		registerItemModel(Itemizer.AMBER_AXE);
		registerItemModel(Itemizer.AMBER_SHOVEL);
		registerItemModel(Itemizer.AMBER_SWORD);
		registerItemModel(Itemizer.SAPPHIRE_PICKAXE);
		registerItemModel(Itemizer.SAPPHIRE_AXE);
		registerItemModel(Itemizer.SAPPHIRE_SHOVEL);
		registerItemModel(Itemizer.SAPPHIRE_SWORD);
		
		registerItemModel(Itemizer.SAPPIZE_BOOTS);
		registerItemModel(Itemizer.SAPPIZE_LEGGINGS);
		registerItemModel(Itemizer.SAPPIZE_CHESTPLATE);
		registerItemModel(Itemizer.SAPPIZE_HELMET);

		
		registerItemModel(Itemizer.HEART_CONTAINER);
		registerItemModel(Itemizer.HEALTH_SHARD);
		
		registerItemModel(Itemizer.FAIRY_SLINGSHOT);
		registerItemModel(Itemizer.BLOCK_EDITOR);
		registerItemModel(Itemizer.SMALL_KEY);

	}

	private void registerBlockModels() {
		registerBlockItemModel(Blockizer.PRESSURE_SWITCH.getDefaultState());
		registerBlockItemModel(Blockizer.LUMINOUS_ORE.getDefaultState());
		registerBlockItemModel(Blockizer.SALT_ORE.getDefaultState());
		registerBlockItemModel(Blockizer.TOPAZ_ORE.getDefaultState());
		registerBlockItemModel(Blockizer.SAPPHIRE_ORE.getDefaultState());
		registerBlockItemModel(Blockizer.SILVER_ORE.getDefaultState());
		registerBlockItemModel(Blockizer.COPPER_ORE.getDefaultState());
		registerBlockItemModel(Blockizer.TIN_ORE.getDefaultState());
		registerBlockItemModel(Blockizer.GOLD_ORE.getDefaultState());
		registerBlockItemModel(Blockizer.MEDIEVAL_STONE.getDefaultState());
		registerBlockItemModel(Blockizer.FLOWER.getDefaultState());
		
		registerBlockItemModel(Blockizer.AMBER_BLOCK.getDefaultState());
		
		registerBlockItemModel(Blockizer.COOKING_POT.getDefaultState());

		
	}

	private void registerItemModel(Item item) {
		final ModelResourceLocation resource = new ModelResourceLocation(item.getRegistryName(), "inventory");
		ModelLoader.setCustomModelResourceLocation(item, 0, resource);
		
	}

	private void registerBlockItemModel(final IBlockState state) {
		final Block block = state.getBlock();
		final Item item = Item.getItemFromBlock(block);

		if (item != Items.AIR) {
			registerItemModel(item);
		}
	}
	
	private void registerItemObjModel(Item item){
		final ModelResourceLocation resource = new ModelResourceLocation(item.getRegistryName(), "inventory");
	}
	
}
