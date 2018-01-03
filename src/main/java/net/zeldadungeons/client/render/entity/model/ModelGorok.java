
package net.zeldadungeons.client.render.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelGorok extends ModelBase {
    ModelRenderer body;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer rightarm;
    ModelRenderer rightarm2;
    ModelRenderer leftarm;
    ModelRenderer leftarm2;

    public ModelGorok() {

	textureWidth = 64;
	textureHeight = 64;

	body = new ModelRenderer(this, 0, 0);
	body.addBox(-8F, 0F, -4F, 16, 9, 8);
	body.setRotationPoint(0F, 8F, 0F);
	setRotation(body, 0F, 0F, 0F);
	body.setTextureSize(64, 64);
	body.mirror = true;

	rightleg = new ModelRenderer(this, 48, 0);
	rightleg.addBox(-2F, 0F, -2F, 4, 7, 4);
	rightleg.setRotationPoint(4F, 17F, 0F);
	rightleg.setTextureSize(64, 64);
	rightleg.mirror = true;

	leftleg = new ModelRenderer(this, 48, 11);
	leftleg.addBox(-2F, 0F, -2F, 4, 7, 4);
	leftleg.setRotationPoint(-4F, 17F, 0F);
	leftleg.setTextureSize(64, 64);
	leftleg.mirror = true;

	rightarm = new ModelRenderer(this, 0, 17);
	rightarm2 = new ModelRenderer(this, 0, 29);
	rightarm.addBox(0F, 0F, -3F, 5, 7, 5);
	rightarm2.addBox(-1F, 7F, -4F, 7, 5, 7);
	rightarm.setRotationPoint(8F, 12F, 0F);
	rightarm.addChild(rightarm2);
	rightarm.setTextureSize(64, 64);
	rightarm2.setTextureSize(64, 64);
	rightarm.mirror = true;
	rightarm2.mirror = true;

	leftarm = new ModelRenderer(this, 0, 17);
	leftarm2 = new ModelRenderer(this, 0, 29);
	leftarm.addBox(-5F, 0F, -3F, 5, 7, 5);
	leftarm2.addBox(-6F, 7F, -4F, 7, 5, 7);
	leftarm.setRotationPoint(-8F, 12F, 0F);
	leftarm.addChild(leftarm2);
	leftarm.setTextureSize(64, 64);
	leftarm2.setTextureSize(64, 64);
	leftarm.mirror = true;
	leftarm2.mirror = true;

    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
	GlStateManager.pushMatrix();
	setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	body.render(f5);
	rightleg.render(f5);
	leftleg.render(f5);
	rightarm.render(f5);
	leftarm.render(f5);
	GlStateManager.popMatrix();
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
	model.rotateAngleX = x;
	model.rotateAngleY = y;
	model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        this.rightarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI)* limbSwingAmount;
        this.leftarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount;

        this.rightleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;



    }

}
