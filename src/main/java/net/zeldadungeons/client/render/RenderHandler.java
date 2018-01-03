package net.zeldadungeons.client.render;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.zeldadungeons.client.render.entity.RenderGorok;
import net.zeldadungeons.client.render.entity.RenderLandamus;
import net.zeldadungeons.client.render.entity.RenderPlayerPulling;
import net.zeldadungeons.client.render.entity.RenderProoper;
import net.zeldadungeons.client.render.entity.RenderSlingshotPellet;
import net.zeldadungeons.client.render.entity.model.ModelGorok;
import net.zeldadungeons.client.render.entity.model.ModelLandamus;
import net.zeldadungeons.client.render.entity.model.ModelProoper;
import net.zeldadungeons.init.entity.EntityPlayerPulling;
import net.zeldadungeons.init.entity.living.overworld.EntityGorok;
import net.zeldadungeons.init.entity.living.overworld.EntityLandamus;
import net.zeldadungeons.init.entity.living.overworld.EntityProoper;
import net.zeldadungeons.init.entity.projectile.EntitySlingshotPellet;

public class RenderHandler {

	public static void register()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityLandamus.class, renderManager -> new RenderLandamus(renderManager, new ModelLandamus(), 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityGorok.class, renderManager -> new RenderGorok(renderManager, new ModelGorok(), 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityProoper.class, renderManager -> new RenderProoper(renderManager, new ModelProoper(), 1.0F));


		RenderingRegistry.registerEntityRenderingHandler(EntitySlingshotPellet.class, renderManager -> new RenderSlingshotPellet(renderManager));
		RenderingRegistry.registerEntityRenderingHandler(EntityPlayerPulling.class, renderManager -> new RenderPlayerPulling(renderManager));

	}
}
