package net.zeldadungeons.init;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.zeldadungeons.world.medieval.WPMedieval;

public class Dimensionizer {
    
    public static int medievalID;
    
    public static void registerDimensions(){
	medievalID = DimensionManager.getNextFreeDimId();
	DimensionType.register("Medieval", "Dimension", medievalID, WPMedieval.class, false);
	DimensionManager.registerDimension(medievalID, DimensionType.getById(medievalID));
	DimensionManager.createProviderFor(medievalID);
    }
}
