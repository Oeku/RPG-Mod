package net.zeldadungeons.client.render.entity;

import net.zeldadungeons.ZeldaDungeons;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.zeldadungeons.init.entity.living.overworld.EntityLandamus;

public class RenderLandamus extends RenderLiving<EntityLandamus>{

	public static final ResourceLocation LANDAMUS = new ResourceLocation(ZeldaDungeons.MODID,
			"textures/entities/landamus.png");
	
	public RenderLandamus(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
		super(rendermanagerIn, modelbaseIn, shadowsizeIn);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityLandamus entity) {
		return LANDAMUS;
	}

}
