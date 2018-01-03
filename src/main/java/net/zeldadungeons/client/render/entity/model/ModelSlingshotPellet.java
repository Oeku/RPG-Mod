
package net.zeldadungeons.client.render.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSlingshotPellet extends ModelBase {
	ModelRenderer c1;

	public ModelSlingshotPellet() {
		textureWidth = 32;
		textureHeight = 32;

		c1 = new ModelRenderer(this, 0, 0);
		c1.addBox(-1F, -2F, -1F, 3, 3, 3);
		c1.setRotationPoint(0F, 0F, 0F);
		c1.setTextureSize(32, 32);
		c1.mirror = true;
		setRotation(c1, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		c1.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
