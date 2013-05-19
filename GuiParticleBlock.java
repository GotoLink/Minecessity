package mods.minecessity;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.src.ModLoader;

public class GuiParticleBlock extends GuiScreen
{

	TileEntityParticleBlock currentTile;
	int type;
    public GuiParticleBlock(TileEntityParticleBlock tile)
    {
		currentTile = tile;
		if(tile==null) ModLoader.getMinecraftInstance().thePlayer.addChatMessage("tile null");
		type = tile.particlesType;
    }
    @Override
	public void initGui()
	{
		buttonList.clear();
		buttonList.add(new GuiButton(1, width/2-100, height/2-20, 150, 20, "Switch Particles Type"));
		buttonList.add(new GuiButton(2, width/2+60, height/2-20, 40, 20, "Close"));
        if(mc.session == null)
        {
            ((GuiButton)buttonList.get(1)).enabled = false;
            ((GuiButton)buttonList.get(2)).enabled = false;
        }
	}
	@Override
	public void drawScreen(int i, int j, float f)
	{
		drawDefaultBackground();
		drawCenteredString(fontRenderer, "Current particles type is: "+String.valueOf(type+1), width / 2, height/2+10, 0xffffff);
		drawCenteredString(fontRenderer, "Types: 1-flame ; 2-explosion ; 3-special ; 4-kid", width / 2, height/2+30, 0xffffff);
		super.drawScreen(i, j, f);
	}
	@Override
	protected void actionPerformed(GuiButton guibutton)
	{
		if(guibutton.id == 1)
		{
			currentTile.changeType();
			type = currentTile.particlesType;
		}
		if(guibutton.id == 2)
		{
			mc.displayGuiScreen(null);
		}
	}
}