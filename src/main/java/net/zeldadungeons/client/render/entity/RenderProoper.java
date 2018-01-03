package net.zeldadungeons.client.render.entity;

import net.zeldadungeons.ZeldaDungeons;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.zeldadungeons.init.entity.living.overworld.EntityProoper;

public class RenderProoper extends RenderLiving<EntityProoper>{

	public static final ResourceLocation PROOPER = new ResourceLocation(ZeldaDungeons.MODID,
			"textures/entities/prooper.png");
	
	public RenderProoper(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
		super(rendermanagerIn, modelbaseIn, shadowsizeIn);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityProoper entity) {
		return PROOPER;
	}

}
