package assets.minecessity;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy implements ISimpleBlockRenderingHandler {
	@Override
	public void registerRenderer() {
		RenderingRegistry.registerEntityRenderingHandler(EntityLightBullet.class, new RenderLightBullet());
		RenderingRegistry.registerBlockHandler(rendererTable, this);
		RenderingRegistry.registerBlockHandler(rendererChair, this);
		RenderingRegistry.registerBlockHandler(rendererCeilLamp, this);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		if (modelId == rendererTable) {
			new MagicBlockRenderer().renderTable(renderer, world, x, y, z, block);
			return true;
		}
		if (modelId == rendererChair) {
			new MagicBlockRenderer().renderChair(renderer, world, x, y, z, block);
			return true;
		}
		if (modelId == rendererCeilLamp) {
			new MagicBlockRenderer().renderCeilLamp(renderer, world, x, y, z, block);
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
