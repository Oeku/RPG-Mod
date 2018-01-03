package net.zeldadungeons.client.gui;

import java.io.IOException;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.zeldadungeons.init.blocks.BlockPressureSwitch;
import net.zeldadungeons.network.NetworkHandler;
import net.zeldadungeons.network.message.PacketPressureSwitch;

public class GuiPressureSwitch extends GuiScreen{
	
	public GuiTextField ticksDeactivationField;
	private Block block;
	private int x;
	private int y;
	private int z;
	
	public GuiPressureSwitch()
	{
		
	}
	
	public GuiPressureSwitch(BlockPressureSwitch block, int x, int y, int z)
	{
		this();
		this.block = block;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		this.fontRenderer.drawString("Activation Settings", width/12, height/12, 100000);
		this.fontRenderer.drawString("Deactivation Settings", 5*width/12, height/12, 100000);
		this.fontRenderer.drawString("Trigger Settings", 9*width/12, height/12, 100000);
		
		this.fontRenderer.drawString("Activate on Player", width/12, 2*height/12, 50000);
		this.fontRenderer.drawString("Activate on Heavy Object", width/12, 4*height/12, 50000);
		
		this.fontRenderer.drawString("Ticks till deactivation", 5*width/12, 2*height/12, 50000);
		this.fontRenderer.drawString("value<-1 or 100000>value", 5*width/12, 3*height/12, 50000);
		this.fontRenderer.drawString("means infinite", 5*width/12, 4*height/12, 50000);
		
		this.ticksDeactivationField.drawTextBox();



		for(int i = 0; this.buttonList.size() > i; i++){
			this.buttonList.get(i).drawButton(mc, mouseX, mouseY, partialTicks);
		}
	}
	
	@Override
	public void initGui()
	{
		GuiButton save = new GuiButton(10, width - width/12, height- height/14, 50, 20, "Save");
		GuiButton close = new GuiButton(11, width - 3*width/12, height-height/14, 50, 20, "Close");
		GuiButton b1 = new GuiButton(0, width/12, 3*height/12, 40, 20, "");
		GuiButton b2 = new GuiButton(1, width/12, 5*height/12, 40, 20, "");
		GuiTextField field = new GuiTextField(0, fontRenderer, 5*width/12, 5*height/12, 60, 20);
		this.buttonList.add(save);
		this.buttonList.add(close);
		this.buttonList.add(b1);
		this.buttonList.add(b2);
		this.ticksDeactivationField = field;
		this.ticksDeactivationField.setMaxStringLength(9);
	}
	
	@Override
	public void actionPerformed(GuiButton button)
	{
		switch(button.id)
		{
			case 0: toggleButton(button);
			case 1: toggleButton(button);
		}
		if(button.id == 10)this.saveEdit();
		else if(button.id == 11)mc.displayGuiScreen(null);
	}
	
	@Override
	public void keyTyped(char typedChar, int keyCode) throws IOException {
		this.ticksDeactivationField.textboxKeyTyped(typedChar, keyCode);
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException{
		super.mouseClicked(mouseX, mouseY, mouseButton);
		this.ticksDeactivationField.mouseClicked(mouseX, mouseY, mouseButton);
	}
	
	public void toggleButton(GuiButton button)
	{
		for(GuiButton b : this.buttonList)
		{
			if(b.id < 2)b.enabled=true;
		}
		button.enabled=false;
	}
	
	public void saveEdit()
	{
		int ticks = 0;
		boolean heavyObject = false;
		try {
			ticks = Integer.parseInt(this.ticksDeactivationField.getText());
		} catch (NumberFormatException e) {
		}
		if(this.buttonList.get(0).enabled==false)heavyObject = true;
		PacketPressureSwitch packet = new PacketPressureSwitch(ticks, heavyObject, x, y, z);
		NetworkHandler.getInstance().sendToServer(packet);
		
	}
}
