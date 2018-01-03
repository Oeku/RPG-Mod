package net.zeldadungeons.client.gui.overlay;

import net.zeldadungeons.ZeldaDungeons;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiOverlay {

    public static final GuiOverlay INSTANCE = new GuiOverlay();
    public static double renderCurrentHealth = 11.0D;
    public static double renderMaxHealth = 11.0D;
    
    public static double renderCurrentStamina = 11.0D;
    public static double renderMaxStamina = 11.0D;

    public void onRenderGameOverlay(ScaledResolution res) {
	Minecraft mc = Minecraft.getMinecraft();
	this.renderBackground(res, mc);
	this.renderHealthBar(res, mc);
	this.renderStaminaBar(res, mc);
    }

    private void renderBackground(ScaledResolution res, Minecraft mc) {

	GlStateManager.pushMatrix();
	mc.renderEngine.bindTexture(new ResourceLocation(ZeldaDungeons.MODID, "textures/gui/background.png"));
	mc.ingameGUI.drawScaledCustomSizeModalRect(0, 0, 0, 0, 320, 180, res.getScaledWidth(), res.getScaledHeight(), 320, 180);
	GlStateManager.popMatrix();

    }

    private void renderHealthBar(ScaledResolution res, Minecraft mc) {
	GlStateManager.pushMatrix();
	double d = renderCurrentHealth / renderMaxHealth;
	int i = (int) (104 * d);
	double d2 = 104D / 320D;
	int j = (int) (res.getScaledWidth() * d * d2);
	int k = (int) (res.getScaledWidth() * 0.05D);
	mc.renderEngine.bindTexture(new ResourceLocation(ZeldaDungeons.MODID, "textures/gui/health_bar.png"));
	mc.ingameGUI.drawScaledCustomSizeModalRect(k, 0, 16F, 0F, i, 180, j, res.getScaledHeight(), 320, 180);
	j = (int) (res.getScaledWidth_double() * (120D / 320D));
	k = (int) (res.getScaledHeight_double() * (10D / 180D));
	mc.ingameGUI.drawString(mc.fontRenderer, (int) renderCurrentHealth + "/" + (int) renderMaxHealth, j, k, 0xffffff);
	GlStateManager.popMatrix();
    }
    
    private void renderStaminaBar(ScaledResolution res, Minecraft mc){
	GlStateManager.pushMatrix();
	double d = renderCurrentStamina / renderMaxStamina;
	int i = (int)(104 * d);
	double d2 = 104D / 320D;
	int j = (int) (res.getScaledWidth() * d * d2);
	int k = (int) (res.getScaledWidth() * 0.05D);
	mc.renderEngine.bindTexture(new ResourceLocation(ZeldaDungeons.MODID, "textures/gui/stamina_bar.png"));
	mc.ingameGUI.drawScaledCustomSizeModalRect(k, 0, 16F, 0F, i, 180, j, res.getScaledHeight(), 320, 180);
	j = (int) (res.getScaledWidth_double() * (120D / 320D));
	k = (int) (res.getScaledHeight_double() * (30D / 180D));
	mc.ingameGUI.drawString(mc.fontRenderer, (int) renderCurrentStamina + "/" + (int) renderMaxStamina, j, k, 0xffffff);
	GlStateManager.popMatrix();
    }

}
