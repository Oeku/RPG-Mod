package net.zeldadungeons.init;

import java.util.Iterator;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.zeldadungeons.ZeldaDungeons;
import net.zeldadungeons.init.entity.EntityPlayerPulling;
import net.zeldadungeons.init.entity.living.overworld.EntityGorok;
import net.zeldadungeons.init.entity.living.overworld.EntityLandamus;
import net.zeldadungeons.init.entity.living.overworld.EntityProoper;
import net.zeldadungeons.init.entity.projectile.EntitySlingshotPellet;

@Mod.EventBusSubscriber(modid = ZeldaDungeons.MODID)
public class Entitizer {
    private static int entityID = 0;

    @SubscribeEvent
    public void registerEntities(RegistryEvent.Register<EntityEntry> event) {
	final EntityEntry[] entries = { createBuilder("landamus").entity(EntityLandamus.class).tracker(100, 1, true).build(), createBuilder("gorok").entity(EntityGorok.class).tracker(100, 1, true).build(),
		createBuilder("prooper").entity(EntityProoper.class).tracker(100, 1, true).build(),

		createBuilder("slingshot_pellet").entity(EntitySlingshotPellet.class).tracker(100, 1, true).build(), createBuilder("player_pulling").entity(EntityPlayerPulling.class).tracker(100, 1, false).egg(1, 1).build(), };
	event.getRegistry().registerAll(entries);
	addSpawns();
    }

    private <E extends Entity> EntityEntryBuilder<E> createBuilder(final String name) {
	final EntityEntryBuilder<E> builder = EntityEntryBuilder.create();
	final ResourceLocation registryName = new ResourceLocation(ZeldaDungeons.MODID, name);
	return builder.id(registryName, entityID++).name(registryName.toString());
    }

    public static void addSpawns() {
	Iterator<Biome> it = Biome.REGISTRY.iterator();
	while (it.hasNext()) {
	    Biome b = it.next();
	    if (BiomeDictionary.hasType(b, BiomeDictionary.Type.PLAINS)) {
		EntityRegistry.addSpawn(EntityLandamus.class, 100, 1, 5, EnumCreatureType.MONSTER, b);

	    }
	    if (BiomeDictionary.hasType(b, BiomeDictionary.Type.MOUNTAIN)) {

	    }

	    // Add for all Biomes
	    EntityRegistry.addSpawn(EntityGorok.class, 100, 1, 5, EnumCreatureType.MONSTER, b);
	    EntityRegistry.addSpawn(EntityProoper.class, 100, 1, 5, EnumCreatureType.MONSTER, b);
	}
    }

    private static Biome[] getBiomes(final BiomeDictionary.Type type) {
	return BiomeDictionary.getBiomes(type).toArray(new Biome[0]);
    }

    private static boolean overworldBiome(Biome b) {
	if (b.getSpawnableList(EnumCreatureType.MONSTER).contains(EntityZombie.class)) return true;
	else return false;
    }

}
