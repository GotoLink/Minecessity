package assets.minecessity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public final class Y_GuiCrafting extends GuiContainer {
	private static final ResourceLocation guiLoc = new ResourceLocation("textures/gui/container/crafting_table.png");

	public Y_GuiCrafting(InventoryPlayer inventoryplayer, World world, int i, int j, int k) {
		super(new PortableContainerWorkbench(inventoryplayer, world, i, j, k));
	}

	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
        inventorySlots.onContainerClosed(mc.thePlayer);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int par1, int par2) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(guiLoc);
		int j = (this.width - this.xSize) / 2;
		int k = (this.height - this.ySize) / 2;
		drawTexturedModalRect(j, k, 0, 0, xSize, ySize);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        fontRendererObj.drawString("Portable Crafting", 28, 6, 0x404040);
        fontRendererObj.drawString("Inventory", 8, (ySize - 96) + 2, 0x404040);
	}
}
