package net.zeldadungeons.world;

import it.unimi.dsi.fastutil.longs.Long2ObjectLinkedOpenHashMap;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public interface ICustomCG {
	public final Long2ObjectLinkedOpenHashMap<Chunk> chunkCache = new Long2ObjectLinkedOpenHashMap<Chunk>(512);
	public Chunk getChunkAt(int x, int z);
	public World getWorld();
}
