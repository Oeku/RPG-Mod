package net.zeldadungeons.client.render.entity;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.zeldadungeons.client.render.entity.model.ModelPlayerPulling;
import net.zeldadungeons.init.entity.EntityPlayerPulling;

public class RenderPlayerPulling extends Render<EntityPlayerPulling>{
	public ModelBase model;
	public AbstractClientPlayer player;

	public RenderPlayerPulling(RenderManager renderManager) {
		super(renderManager);
		this.model = new ModelPlayerPulling();
	}
	
	public void doRender(EntityPlayerPulling entity1, AbstractClientPlayer entity, double x, double y, double z, float entityYaw, float partialTicks){
		GlStateManager.pushMatrix();
		this.bindTexture(entity.getLocationSkin());

		if (this.renderOutlines) {
			GlStateManager.enableColorMaterial();
		}

		this.model.render(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

		if (this.renderOutlines) {
			GlStateManager.disableOutlineMode();
			GlStateManager.disableColorMaterial();
		}

		GlStateManager.popMatrix();
		super.doRender(entity1, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityPlayerPulling entity) {
		this.player = (AbstractClientPlayer) entity.parent;
		return player.getLocationSkin();
	}
}
