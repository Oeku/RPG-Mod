package net.zeldadungeons.init;

import net.minecraft.util.ResourceLocation;
import net.zeldadungeons.ZeldaDungeons;
import net.zeldadungeons.world.structure.ModTemplate;
import net.zeldadungeons.world.structure.TemplateBase;

public class Structurizer {
    public static ModTemplate mvh1;
    
    
    public static void initTemplates(){
	mvh1 = new ModTemplate(new ResourceLocation(ZeldaDungeons.MODID, "mvh1"));
    }
}
