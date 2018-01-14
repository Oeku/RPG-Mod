package net.zeldadungeons.world;

import java.util.List;
import java.util.Random;

import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.ChunkGeneratorSettings;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraft.world.storage.WorldInfo;
import net.zeldadungeons.init.Generizer;
import net.zeldadungeons.util.Log;

public class BiomeProviderCustom extends BiomeProvider {
    public BiomeProviderCustom(WorldInfo info) {
	super(info);
	
    }

}
