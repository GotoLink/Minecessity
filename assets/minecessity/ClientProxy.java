package assets.minecessity;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ModContainer;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public class ClientProxy extends CommonProxy implements ISimpleBlockRenderingHandler {
	@Override
	public void registerRenderer() {
		RenderingRegistry.registerEntityRenderingHandler(EntityLightBullet.class, new RenderLightBullet());
		RenderingRegistry.registerBlockHandler(rendererTable, this);
		RenderingRegistry.registerBlockHandler(rendererChair, this);
		RenderingRegistry.registerBlockHandler(rendererCeilLamp, this);
	}

    @Override
    public void trySendUpdate(){
        try {
            Class.forName("mods.mud.ModUpdateDetector").getDeclaredMethod("registerMod", ModContainer.class, String.class, String.class).invoke(null,
                    FMLCommonHandler.instance().findContainerFor(this),
                    "https://raw.github.com/GotoLink/Minecessity/master/update.xml",
                    "https://raw.github.com/GotoLink/Minecessity/master/changelog.md"
            );
        } catch (Throwable ignored) {
        }
    }

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		if (modelId == rendererTable) {
			MagicBlockRenderer.INSTANCE.renderTable(world, x, y, z, block);
			return true;
		}
		if (modelId == rendererChair) {
            MagicBlockRenderer.INSTANCE.renderChair(world, x, y, z, block);
			return true;
		}
		if (modelId == rendererCeilLamp) {
            MagicBlockRenderer.INSTANCE.renderCeilLamp(world, x, y, z, block);
			return true;
		}
		return false;
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
	}

	@Override
	public boolean shouldRender3DInInventory(int i) {
		return false;
	}

	@Override
	public int getRenderId() {
		return 0;
	}
}
