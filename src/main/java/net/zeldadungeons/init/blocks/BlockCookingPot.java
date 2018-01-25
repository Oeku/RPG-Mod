package net.zeldadungeons.init.blocks;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.zeldadungeons.ZeldaDungeons;
import net.zeldadungeons.init.Generizer;
import net.zeldadungeons.init.entity.tile.TileEntityCookingPot;
import net.zeldadungeons.util.Log;

public class BlockCookingPot extends Block{

    public static final AxisAlignedBB BOX = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1D, 0.55D, 1D);
    public static final IProperty<Boolean> COOKING = PropertyBool.create("cooking");
    {
	this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	setDefaultState(getBlockState().getBaseState().withProperty(COOKING, false));
    }

    public BlockCookingPot(String blockName) {
	super(Material.IRON, MapColor.BLACK);
	this.setRegistryName(ZeldaDungeons.MODID, blockName);
	this.setUnlocalizedName(blockName);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
	return false;
    }
    
    @Override
    public boolean isFullCube(IBlockState state) {
	return false;
    }

    @Override
    protected BlockStateContainer createBlockState() {
	return new BlockStateContainer(this, COOKING);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }
    
    @Nullable
    public TileEntity createTileEntity(World world, IBlockState state)
    {
	Log.logString("create");
       return new TileEntityCookingPot(10, 1000);
    }
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
	if(worldIn.isRemote)return false;
	Log.getLogger().info(pos.getX()+"  "+pos.getY()+ " " + pos.getZ());
	return true;
    }
    
    @Override
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
	if(worldIn.isRemote)return;
	ItemStack hand = playerIn.inventory.getCurrentItem();
	boolean flag;
	if(hand.getItem() == Items.LAVA_BUCKET)flag = true;
	int i = TileEntityFurnace.getItemBurnTime(hand);
	if(i > 0)hand.shrink(1);
	((TileEntityCookingPot) worldIn.getTileEntity(pos)).addFireTime(i);
    }
    

    @Override
    public int getMetaFromState(IBlockState state) {
	return 0;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
	return getBlockState().getBaseState().withProperty(COOKING, false);
    }
    
    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }
    
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return this.BOX;
    }

    public static void setState(TileEntityCookingPot tileEntityCookingPot, BlockPos pos, World worldIn) {
	worldIn.setBlockState(pos, worldIn.getBlockState(pos).withProperty(COOKING, tileEntityCookingPot.isCooking()));
	tileEntityCookingPot.validate();
	worldIn.setTileEntity(pos, tileEntityCookingPot);	
    }
}
