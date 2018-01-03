package net.zeldadungeons.init;

import org.apache.http.config.RegistryBuilder;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;
import net.zeldadungeons.ZeldaDungeons;
import net.zeldadungeons.init.crafting.CookingMeal;
import net.zeldadungeons.util.Log;

@Mod.EventBusSubscriber(modid = ZeldaDungeons.MODID)
public class Mealizer {
    
    public static CookingMeal FRIES;
    
    @SubscribeEvent
    public void registerMeals(RegistryEvent.Register<CookingMeal> e){
	FRIES = new CookingMeal("fries", "Fries").setIngredients(Items.POTATO, Itemizer.SALT).setValues(new int[]{1, 2}, new int[]{2, 4});
	CookingMeal[] meals = {
		FRIES,
	};
	e.getRegistry().registerAll(meals);
	Log.getLogger().info("Registered "+meals.length+" Meals");
    }
    
    @SubscribeEvent
    public void registerRegistries(RegistryEvent.NewRegistry e){
	
    }
}
