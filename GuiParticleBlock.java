package mods.minecessity;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
@SideOnly(Side.CLIENT)
public class GuiParticleBlock extends GuiScreen
{

	TileEntityParticleBlock currentTile;
	int type;
    public GuiParticleBlock(TileEntityParticleBlock tile)
    {
		currentTile = tile;
		type = tile.particlesType;
    }
    public GuiParticleBlock(World world, int x, int y, int z) {
		this((TileEntityParticleBlock) world.getBlockTileEntity(x, y, z));
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