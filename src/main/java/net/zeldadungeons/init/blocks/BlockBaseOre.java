package net.zeldadungeons.init.blocks;

import java.util.Random;

import net.minecraft.block.BlockOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.zeldadungeons.init.Blockizer;
import net.zeldadungeons.init.Itemizer;

public class BlockBaseOre extends BlockOre{
	
	public BlockBaseOre(String name)
	{
		super();
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setHarvestLevels();
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        if(this == Blockizer.LUMINOUS_ORE)
        {
        	return Itemizer.LUMINOUS_STONE;
        }
        else if(this == Blockizer.SALT_ORE)
        {
        	return Itemizer.SALT;
        }
        else if(this == Blockizer.GOLD_ORE){
            return Items.GOLD_NUGGET;
        }
        else if(this == Blockizer.SILVER_ORE){
            return Itemizer.SILVER;
        }
        else if(this == Blockizer.COPPER_ORE){
            return Itemizer.COPPER;
        }
        else if(this == Blockizer.TIN_ORE){
            return Itemizer.TIN;
        }
        else return Item.getItemFromBlock(state.getBlock());
    }
	
    /**
     * Returns the quantity of items to drop on block destruction.
     */
    @Override
    public int quantityDropped(Random random)
    {
	if(this == Blockizer.GOLD_ORE)return random.nextInt(2)+6;
	else if(this == Blockizer.LUMINOUS_ORE || this == Blockizer.SALT_ORE)return random.nextInt(1)+1;
    	else return 1;
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random random)
    {
        if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped(this.getBlockState().getValidStates().iterator().next(), random, fortune))
        {
            int i = random.nextInt(fortune + 2) - 1;

            if (i < 0)
            {
                i = 0;
            }

            return this.quantityDropped(random) * (i + 1);
        }
        else
        {
            return this.quantityDropped(random);
        }
    }

 
    @Override
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {
        super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
    }
    @Override
    public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
    {
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        if (this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this))
        {
            int i = 0;

            if (this == Blockizer.LUMINOUS_ORE)
            {
                i = MathHelper.getInt(rand, 0, 20);
            }
            else if (this == Blockizer.TOPAZ_ORE)
            {
                i = MathHelper.getInt(rand, 5, 10);
            }
            else if (this == Blockizer.SAPPHIRE_ORE)
            {
                i = MathHelper.getInt(rand, 10, 10);
            }
            return i;
        }
        return 0;
    }
    
    public void setHarvestLevels()
    {
    	if(this != Blockizer.SALT_ORE)this.setHarvestLevel("pickaxe", 4);
    }
}
