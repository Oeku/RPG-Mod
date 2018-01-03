package net.zeldadungeons.client.render.entity;

import net.zeldadungeons.ZeldaDungeons;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.zeldadungeons.init.entity.living.overworld.EntityGorok;

public class RenderGorok extends RenderLiving<EntityGorok>{

	public static final ResourceLocation GOROK = new ResourceLocation(ZeldaDungeons.MODID,
			"textures/entities/gorok.png");
	
	public RenderGorok(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
		super(rendermanagerIn, modelbaseIn, shadowsizeIn);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityGorok entity) {
		return GOROK;
	}

}
