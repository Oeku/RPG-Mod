package net.zeldadungeons.client.render.entity;

import net.zeldadungeons.ZeldaDungeons;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.zeldadungeons.client.render.entity.model.ModelSlingshotPellet;
import net.zeldadungeons.init.entity.projectile.EntitySlingshotPellet;

public class RenderSlingshotPellet extends Render<EntitySlingshotPellet> {
	private static ModelSlingshotPellet model = new ModelSlingshotPellet();
	private static final ResourceLocation SLINGSHOT_PELLET = new ResourceLocation(ZeldaDungeons.MODID,
			"textures/entities/slingshot_pellet.png");

	public RenderSlingshotPellet(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntitySlingshotPellet entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		this.setupTranslation(x, y, z);
		this.bindTexture(SLINGSHOT_PELLET);

		if (this.renderOutlines) {
			GlStateManager.enableColorMaterial();
		}

		RenderSlingshotPellet.model.render(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

		if (this.renderOutlines) {
			GlStateManager.disableOutlineMode();
			GlStateManager.disableColorMaterial();
		}

		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySlingshotPellet entity) {
		return SLINGSHOT_PELLET;
	}

	public void setupTranslation(double p_188309_1_, double p_188309_3_, double p_188309_5_) {
		GlStateManager.translate((float) p_188309_1_, (float) p_188309_3_ + 0.375F, (float) p_188309_5_);
	}

}
