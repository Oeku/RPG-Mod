package net.zeldadungeons.init;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.zeldadungeons.ZeldaDungeons;
import net.zeldadungeons.init.cooking.CookingMeal;
import net.zeldadungeons.util.Log;

@ObjectHolder(ZeldaDungeons.MODID)
@Mod.EventBusSubscriber(modid = ZeldaDungeons.MODID)
public class Mealizer {
    public static List<CookingMeal> REGISTRY;
    public static CookingMeal FRIES;
    public static CookingMeal DISGUSTING;

    @SubscribeEvent
    public static void registerMeals(RegistryEvent.Register<CookingMeal> e) {
	FRIES = new CookingMeal("fries", "Fries").setIngredients(Items.POTATO, Itemizer.SALT).setValues(new int[] { 1, 2 }, new int[]{ 2, 2 }, new int[] { 2, 4 });
	DISGUSTING = new CookingMeal("disgusting", "Disgusting Something").setIngredients(Items.AIR).setValues(new int[]{1}, new int[]{30}, new int[]{50});
	CookingMeal[] meals = {
		FRIES,
		DISGUSTING,
	};
	e.getRegistry().registerAll(meals);
	REGISTRY = e.getRegistry().getValues();
	Log.getLogger().info("Registered " + meals.length + " Meals");
    }

    @SubscribeEvent
    public static void registerRegistries(RegistryEvent.NewRegistry e) {
	net.minecraftforge.registries.RegistryBuilder<CookingMeal> builder = new net.minecraftforge.registries.RegistryBuilder<CookingMeal>();
	builder.setType(CookingMeal.class);
	ResourceLocation key = new ResourceLocation(ZeldaDungeons.MODID, "cooking_meal");
	builder.setName(key);
	builder.setDefaultKey(key);
	builder.create();
    }
}
