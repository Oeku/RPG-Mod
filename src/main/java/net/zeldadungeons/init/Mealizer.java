package net.zeldadungeons.init;

import org.apache.http.config.RegistryBuilder;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;
import net.zeldadungeons.ZeldaDungeons;
import net.zeldadungeons.init.crafting.CookingMeal;
import net.zeldadungeons.util.Log;

@EventBusSubscriber(modid = ZeldaDungeons.MODID)
@ObjectHolder("zeldadungeons:cooking_meal")
public class Mealizer {
    
    public static final CookingMeal FRIES = new CookingMeal("fries", "Fries").setIngredients(Items.POTATO, Itemizer.SALT).setValues(new int[]{1, 2}, new int[]{2, 4});
    
    
    
    @SubscribeEvent
    public static void registerMeals(RegistryEvent.Register<CookingMeal> e){
	CookingMeal[] meals = {
		FRIES,
	};
	e.getRegistry().registerAll(meals);
	Log.getLogger().info("Registered "+meals.length+" Meals");
    }
    
    @SubscribeEvent
    public static void registerRegistries(RegistryEvent.NewRegistry e){
	net.minecraftforge.registries.RegistryBuilder<CookingMeal> builder = new net.minecraftforge.registries.RegistryBuilder<CookingMeal>();
	builder.setType(CookingMeal.class);
	ResourceLocation key = new ResourceLocation(ZeldaDungeons.MODID, "cooking_meal");
	builder.setName(key);
	builder.setDefaultKey(key);
	builder.create();
	Log.getLogger().info(key.getResourceDomain());
	Log.getLogger().info(key.getResourcePath());

    }
}
