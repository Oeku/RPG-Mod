package net.zeldadungeons.world.structure;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.structure.template.Template;

public class ModTemplate {

    private int offX;
    private int offY;
    private int offZ;
    private final ResourceLocation path;

    public ModTemplate(int offX, int offY, int offZ, ResourceLocation resource) {
	this.path = resource;
	this.offX = offX;
	this.offY = offY;
	this.offZ = offZ;
    }
    
    public ModTemplate(ResourceLocation resource) {
	this(0, 0, 0, resource);
    }

    public int getOffX() {
	return offX;
    }

    public void setOffX(int offX) {
	this.offX = offX;
    }

    public int getOffY() {
	return offY;
    }

    public void setOffY(int offY) {
	this.offY = offY;
    }

    public int getOffZ() {
	return offZ;
    }

    public void setOffZ(int offZ) {
	this.offZ = offZ;
    }
    
    public ResourceLocation getPath(){
	return this.path;
    }

}
