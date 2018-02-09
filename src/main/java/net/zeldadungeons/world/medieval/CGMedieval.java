package net.zeldadungeons.world.medieval;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.ChunkGeneratorSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.NoiseGeneratorSimplex;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.zeldadungeons.init.Blockizer;
import net.zeldadungeons.world.ICustomCG;
import net.zeldadungeons.world.structure.FortressGenerator;
import net.zeldadungeons.world.structure.ModStructure;

public class CGMedieval implements IChunkGenerator, ICustomCG {
    protected static final IBlockState STONE = Blockizer.MEDIEVAL_STONE.getDefaultState();
    private final Random rand;
    private NoiseGeneratorOctaves minLimitPerlinNoise;
    private NoiseGeneratorOctaves maxLimitPerlinNoise;
    private NoiseGeneratorOctaves mainPerlinNoise;
    private NoiseGeneratorPerlin surfaceNoise;
    public NoiseGeneratorOctaves scaleNoise;
    public NoiseGeneratorOctaves depthNoise;
    public NoiseGeneratorOctaves forestNoise;

    private final World world;
    private final boolean mapFeaturesEnabled;
    private final WorldType terrainType;
    private final double[] heightMap;
    private final float[] biomeWeights;
    private ChunkGeneratorSettings settings;
    private IBlockState oceanBlock = Blocks.WATER.getDefaultState();
    private double[] depthBuffer = new double[256];
    private MapGenBase caveGenerator = new MapGenCaves();
    private MapGenMineshaft mineshaftGenerator = new MapGenMineshaft();
    private MapGenBase ravineGenerator = new MapGenRavine();
    private Biome[] biomesForGeneration;
    double[] mainNoiseRegion;
    double[] minLimitRegion;
    double[] maxLimitRegion;
    double[] depthRegion;

    private NoiseGeneratorSimplex heightNoise;

    private NoiseGeneratorOctaves customNoise2;

    private FortressGenerator[] fortressGens = new FortressGenerator[1];

    private double[] heightGen;

    private List<ModStructure> structureList;
    private List<Chunk> cachedChunks;

    public CGMedieval(World worldIn, long seed, boolean mapFeaturesEnabledIn, String generatorOptions) {
	{
	    WorldServer server;
	    TemplateManager tm;
	    if (worldIn instanceof WorldServer) {
		server = (WorldServer) worldIn;
		tm = server.getStructureTemplateManager();

	    }
	    caveGenerator = net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(caveGenerator, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.CAVE);
	    mineshaftGenerator = (MapGenMineshaft) net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(mineshaftGenerator, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.MINESHAFT);
	    ravineGenerator = net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(ravineGenerator, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.RAVINE);
	    this.structureList = new ArrayList<ModStructure>();
	}
	this.world = worldIn;
	this.mapFeaturesEnabled = mapFeaturesEnabledIn;
	this.terrainType = worldIn.getWorldInfo().getTerrainType();
	this.rand = new Random(seed);
	this.minLimitPerlinNoise = new NoiseGeneratorOctaves(this.rand, 16);
	this.maxLimitPerlinNoise = new NoiseGeneratorOctaves(this.rand, 16);
	this.mainPerlinNoise = new NoiseGeneratorOctaves(this.rand, 8);
	this.surfaceNoise = new NoiseGeneratorPerlin(this.rand, 4);
	this.scaleNoise = new NoiseGeneratorOctaves(this.rand, 10);
	this.depthNoise = new NoiseGeneratorOctaves(this.rand, 16);
	this.forestNoise = new NoiseGeneratorOctaves(this.rand, 8);

	customNoise2 = new NoiseGeneratorOctaves(this.rand, 1);

	this.heightMap = new double[825];
	this.biomeWeights = new float[25];

	for (int i = -2; i <= 2; ++i) {
	    for (int j = -2; j <= 2; ++j) {
		float f = 10.0F / MathHelper.sqrt((float) (i * i + j * j) + 0.2F);
		this.biomeWeights[i + 2 + (j + 2) * 5] = f;
	    }
	}

	if (generatorOptions != null) {
	    this.settings = ChunkGeneratorSettings.Factory.jsonToFactory(generatorOptions).build();
	    this.oceanBlock = this.settings.useLavaOceans ? Blocks.LAVA.getDefaultState() : Blocks.WATER.getDefaultState();
	    worldIn.setSeaLevel(this.settings.seaLevel);
	}

	net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextOverworld ctx = new net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextOverworld(minLimitPerlinNoise, maxLimitPerlinNoise, mainPerlinNoise, surfaceNoise,
		scaleNoise, depthNoise, forestNoise);
	ctx = net.minecraftforge.event.terraingen.TerrainGen.getModdedNoiseGenerators(worldIn, this.rand, ctx);
	this.minLimitPerlinNoise = ctx.getLPerlin1();
	this.maxLimitPerlinNoise = ctx.getLPerlin2();
	this.mainPerlinNoise = ctx.getPerlin();
	this.surfaceNoise = ctx.getHeight();
	this.scaleNoise = ctx.getScale();
	this.depthNoise = ctx.getDepth();
	this.forestNoise = ctx.getForest();
    }

    public void setBlocksInChunk(int x, int z, ChunkPrimer primer) {
	this.biomesForGeneration = this.world.getBiomeProvider().getBiomesForGeneration(this.biomesForGeneration, x * 4 - 2, z * 4 - 2, 10, 10);
	this.generateHeightmap(x * 4, 0, z * 4);

	for (int i = 0; i < 4; ++i) {
	    int j = i * 5;
	    int k = (i + 1) * 5;

	    for (int l = 0; l < 4; ++l) {
		int i1 = (j + l) * 33;
		int j1 = (j + l + 1) * 33;
		int k1 = (k + l) * 33;
		int l1 = (k + l + 1) * 33;

		for (int i2 = 0; i2 < 32; ++i2) {
		    double d0 = 0.125D;
		    double d1 = this.heightMap[i1 + i2];
		    double d2 = this.heightMap[j1 + i2];
		    double d3 = this.heightMap[k1 + i2];
		    double d4 = this.heightMap[l1 + i2];
		    double d5 = (this.heightMap[i1 + i2 + 1] - d1) * 0.125D;
		    double d6 = (this.heightMap[j1 + i2 + 1] - d2) * 0.125D;
		    double d7 = (this.heightMap[k1 + i2 + 1] - d3) * 0.125D;
		    double d8 = (this.heightMap[l1 + i2 + 1] - d4) * 0.125D;

		    for (int j2 = 0; j2 < 8; ++j2) {
			double d9 = 0.25D;
			double d10 = d1;
			double d11 = d2;
			double d12 = (d3 - d1) * 0.25D;
			double d13 = (d4 - d2) * 0.25D;

			for (int k2 = 0; k2 < 4; ++k2) {
			    double d14 = 0.25D;
			    double d16 = (d11 - d10) * 0.25D;
			    double lvt_45_1_ = d10 - d16;

			    for (int l2 = 0; l2 < 4; ++l2) {
				if ((lvt_45_1_ += d16) > 0.0D) {
				    primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, STONE);
				}
				else if (i2 * 8 + j2 < this.settings.seaLevel) {
				    primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, this.oceanBlock);
				}
			    }

			    d10 += d12;
			    d11 += d13;
			}

			d1 += d5;
			d2 += d6;
			d3 += d7;
			d4 += d8;
		    }
		}
	    }
	}
    }

    public void replaceBiomeBlocks(int x, int z, ChunkPrimer primer, Biome[] biomesIn) {
	if (!net.minecraftforge.event.ForgeEventFactory.onReplaceBiomeBlocks(this, x, z, primer, this.world)) return;
	double d0 = 0.03125D;
	this.depthBuffer = this.surfaceNoise.getRegion(this.depthBuffer, (double) (x * 16), (double) (z * 16), 16, 16, 0.0625D, 0.0625D, 1.0D);

	for (int i = 0; i < 16; ++i) {
	    for (int j = 0; j < 16; ++j) {
		Biome biome = biomesIn[j + i * 16];
		biome.genTerrainBlocks(this.world, this.rand, primer, x * 16 + i, z * 16 + j, this.depthBuffer[j + i * 16]);
	    }
	}
    }

    public Chunk getChunkAt(int x, int z){
	long key = ChunkPos.asLong(x, z);
	if(this.world.isChunkGeneratedAt(x, z))
	{
	    return this.world.getChunkFromChunkCoords(x, z);
	}
	else if(this.chunkCache.containsKey(key))
	{
	    return this.chunkCache.get(key);
	}
	else return preGenerate(x, z);
    }
    
    public Chunk preGenerate(int x, int z){
	return generateChunk(x, z);
	
    }
    
    /**
     * Generates the chunk at the specified position, from scratch
     */
    public Chunk generateChunk(int x, int z) {
	long key = ChunkPos.asLong(x, z);
	if(this.chunkCache.containsKey(key)){
	    return this.chunkCache.get(key);
	}
	long begin = System.nanoTime() / 100;
	this.rand.setSeed((long) x * 341873128712L + (long) z * 132897987541L);
	ChunkPrimer chunkprimer = new ChunkPrimer();
	this.setBlocksInChunk(x, z, chunkprimer);
	long setBlocks = System.nanoTime() / 100;
	this.biomesForGeneration = this.world.getBiomeProvider().getBiomes(this.biomesForGeneration, x * 16, z * 16, 16, 16);
	this.replaceBiomeBlocks(x, z, chunkprimer, this.biomesForGeneration);
	long setBiome = System.nanoTime() / 100;
	if (this.settings.useCaves) {
	    this.caveGenerator.generate(this.world, x, z, chunkprimer);
	}

	if (this.settings.useRavines) {
	    this.ravineGenerator.generate(this.world, x, z, chunkprimer);
	}

	if (this.mapFeaturesEnabled) {
	    if (this.settings.useMineShafts) {
		this.mineshaftGenerator.generate(this.world, x, z, chunkprimer);
	    }
	}
	long genStructures = System.nanoTime() / 100;
	Chunk chunk = new Chunk(this.world, chunkprimer, x, z);
	chunk.generateSkylightMap();
	return chunk;
    }

    private void generateHeightmap(int x, int y, int z) {
	this.heightGen = this.customNoise2.generateNoiseOctaves(this.depthRegion, x, z, 5, 5, (double) this.settings.depthNoiseScaleX, (double) this.settings.depthNoiseScaleZ, (double) this.settings.depthNoiseScaleExponent);
	this.depthRegion = this.depthNoise.generateNoiseOctaves(this.depthRegion, x, z, 5, 5, (double) this.settings.depthNoiseScaleX, (double) this.settings.depthNoiseScaleZ, (double) this.settings.depthNoiseScaleExponent);
	float f = this.settings.coordinateScale * 0.4F; // Flatten Terrain
	float f1 = this.settings.heightScale * 1F; // Enlarge Height !!!!!!
	this.mainNoiseRegion = this.mainPerlinNoise.generateNoiseOctaves(this.mainNoiseRegion, x, y, z, 5, 33, 5, (double) (f / this.settings.mainNoiseScaleX), (double) (f1 / this.settings.mainNoiseScaleY),
		(double) (f / this.settings.mainNoiseScaleZ));
	this.minLimitRegion = this.minLimitPerlinNoise.generateNoiseOctaves(this.minLimitRegion, x, y, z, 5, 33, 5, (double) f, (double) f1, (double) f);
	this.maxLimitRegion = this.maxLimitPerlinNoise.generateNoiseOctaves(this.maxLimitRegion, x, y, z, 5, 33, 5, (double) f, (double) f1, (double) f);
	int i = 0;
	int j = 0;

	for (int k = 0; k < 5; ++k) {
	    for (int l = 0; l < 5; ++l) {
		float f2 = 0.0F;
		float f3 = 0.0F;
		float f4 = 0.0F;
		int i1 = 2;
		Biome biome = this.biomesForGeneration[k + 2 + (l + 2) * 10];

		for (int j1 = -2; j1 <= 2; ++j1) {
		    for (int k1 = -2; k1 <= 2; ++k1) {
			Biome biome1 = this.biomesForGeneration[k + j1 + 2 + (l + k1 + 2) * 10];
			float f5 = this.settings.biomeDepthOffSet + biome1.getBaseHeight() * this.settings.biomeDepthWeight;
			float f6 = this.settings.biomeScaleOffset + biome1.getHeightVariation() * this.settings.biomeScaleWeight;

			if (this.terrainType == WorldType.AMPLIFIED && f5 > 0.0F) {
			    f5 = 1.0F + f5 * 2.0F;
			    f6 = 1.0F + f6 * 4.0F;
			}

			float f7 = this.biomeWeights[j1 + 2 + (k1 + 2) * 5] / (f5 + 2.0F);

			if (biome1.getBaseHeight() > biome.getBaseHeight()) {
			    f7 /= 2.0F;
			}

			f2 += f6 * f7;
			f3 += f5 * f7;
			f4 += f7;
		    }
		}

		f2 = f2 / f4;
		f3 = f3 / f4;
		f2 = f2 * 0.9F + 0.1F;
		f3 = (f3 * 4.0F - 1.0F) / 8.0F;
		double d7 = this.depthRegion[j] / 2000.0D; // 8000

		if (d7 < 0.0D) {
		    d7 = -d7 * 0.3D;
		}

		d7 = d7 * 3.0D - 2.0D;

		if (d7 < 0.0D) {
		    d7 = d7 / 2.0D;

		    if (d7 < -1.0D) {
			d7 = -1.0D;
		    }

		    d7 = d7 / 1.4D;
		    d7 = d7 / 2.0D;
		}
		else {
		    if (d7 > 1.0D) {
			d7 = 1.0D;
		    }

		    d7 = d7 / 8.0D;
		}

		++j;
		double d8 = (double) f3;
		double d9 = (double) f2;
		d8 *= this.heightGen[j - 1] * 0.0001D;
		d8 = d8 + d7 * 0.2D;
		d8 = d8 * (double) this.settings.baseSize / 8.0D;
		double d0 = (double) this.settings.baseSize + d8 * 4.0D;

		for (int l1 = 0; l1 < 33; ++l1) {
		    double d1 = ((double) l1 - d0) * (double) this.settings.stretchY * 256.0D / 256.0D / d9; //

		    if (d1 < 0.0D) {
			d1 *= 4.0D;
		    }

		    double d2 = this.minLimitRegion[i] / (double) this.settings.lowerLimitScale;
		    double d3 = this.maxLimitRegion[i] / (double) this.settings.upperLimitScale;
		    double d4 = (this.mainNoiseRegion[i] / 10.0D + 1.0D) / 2.0D;
		    double d5 = MathHelper.clampedLerp(d2, d3, d4) - d1;

		    if (l1 > 29) {
			double d6 = (double) ((float) (l1 - 29) / 3.0F);
			d5 = d5 * (1.0D - d6) + -10.0D * d6;
		    }

		    this.heightMap[i] = d5;
		    ++i;
		}
	    }
	}
    }

    /**
     * Generate initial structures in this chunk, e.g. mineshafts, temples,
     * lakes, and dungeons
     */
    public void populate(int x, int z) {

	BlockFalling.fallInstantly = true;
	int i = x * 16;
	int j = z * 16;
	BlockPos blockpos = new BlockPos(i, 0, j);
	Biome biome = this.world.getBiome(blockpos.add(16, 0, 16));
	this.rand.setSeed(this.world.getSeed());
	long k = this.rand.nextLong() / 2L * 2L + 1L;
	long l = this.rand.nextLong() / 2L * 2L + 1L;
	this.rand.setSeed((long) x * k + (long) z * l ^ this.world.getSeed());
	boolean flag = false;
	ChunkPos chunkpos = new ChunkPos(x, z);

	net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(true, this, this.world, this.rand, x, z, flag);

	if (this.mapFeaturesEnabled) {
	    if (this.settings.useMineShafts) {
		this.mineshaftGenerator.generateStructure(this.world, this.rand, chunkpos);
	    }
	}

	if (this.settings.useDungeons) if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.world, this.rand, x, z, flag, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.DUNGEON)) {
	    for (int j2 = 0; j2 < this.settings.dungeonChance; ++j2) {
		int i3 = this.rand.nextInt(16) + 8;
		int l3 = this.rand.nextInt(256);
		int l1 = this.rand.nextInt(16) + 8;
		(new WorldGenDungeons()).generate(this.world, this.rand, blockpos.add(i3, l3, l1));
	    }
	}

	biome.decorate(this.world, this.rand, new BlockPos(i, 0, j));
	if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.world, this.rand, x, z, flag, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.ANIMALS))
	    WorldEntitySpawner.performWorldGenSpawning(this.world, biome, i + 8, j + 8, 16, 16, this.rand);
	blockpos = blockpos.add(8, 0, 8);

	if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.world, this.rand, x, z, flag, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.ICE)) {
	    for (int k2 = 0; k2 < 16; ++k2) {
		for (int j3 = 0; j3 < 16; ++j3) {
		    BlockPos blockpos1 = this.world.getPrecipitationHeight(blockpos.add(k2, 0, j3));
		    BlockPos blockpos2 = blockpos1.down();

		    if (this.world.canBlockFreezeWater(blockpos2)) {
			this.world.setBlockState(blockpos2, Blocks.ICE.getDefaultState(), 2);
		    }

		    if (this.world.canSnowAt(blockpos1, true)) {
			this.world.setBlockState(blockpos1, Blocks.SNOW_LAYER.getDefaultState(), 2);
		    }
		}
	    }
	}

	/** MOD STRUCTURE PART **/
	for (ModStructure structure : this.structureList) {
	    structure.generate();
	}

	net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(false, this, this.world, this.rand, x, z, flag);

	BlockFalling.fallInstantly = false;
    }

    /**
     * Called to generate additional structures after initial worldgen, used by
     * ocean monuments
     */
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
	boolean flag = false;

	return flag;
    }

    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
	Biome biome = this.world.getBiome(pos);

	return biome.getSpawnableList(creatureType);
    }

    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
	return false;
    }

    @Nullable
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
	return null;
    }

    /**
     * Recreates data about structures intersecting given chunk (used for
     * example by getPossibleCreatures), without placing any blocks. When called
     * for the first time before any chunk is generated - also initializes the
     * internal state needed by getPossibleCreatures.
     */
    public void recreateStructures(Chunk chunkIn, int x, int z) {

    }

    public void checkBiomes(Biome bin) {
	if (bin.getBaseHeight() < 1.2F) {

	}
    }

    public String getBiomeAt(BlockPos pos) {
	return this.world.getChunkFromBlockCoords(pos).getBiome(pos, this.world.getBiomeProvider()).getBiomeName();
    }
}