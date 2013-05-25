package mods.minecessity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;


public class CommonProxy implements IGuiHandler{
	
	public static int rendererTable=-10,rendererChair=-11,rendererCeilLamp=-12,
			CRAFT_GUI_ID=123,PARTICLE_GUI=124;
	
	public void registerRenderer(){}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if(ID==CRAFT_GUI_ID)
			return new PortableContainerWorkbench(player.inventory, world, x, y, z);
		else if(ID==PARTICLE_GUI)
			return null;
		else
			return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if(ID==CRAFT_GUI_ID)
			return new Y_GuiCrafting(player.inventory, world, x, y, z);
		else if(ID==PARTICLE_GUI)
			return new GuiParticleBlock((TileEntityParticleBlock)world.getBlockTileEntity(x, y, z));
		else
			return null;
	}

}
