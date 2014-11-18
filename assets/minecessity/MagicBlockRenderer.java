package assets.minecessity;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MagicBlockRenderer {
    public static final MagicBlockRenderer INSTANCE = new MagicBlockRenderer();

	public boolean renderCeilLamp(IBlockAccess iblockaccess, int i, int j, int k, Block block) {
		Tessellator tessellator = Tessellator.instance;
		int side = 0;
		IIcon texture = block.getIcon(iblockaccess, i, j, k, side);
		int f = block.colorMultiplier(iblockaccess, i, j, k);
		tessellator.setColorOpaque_F((float)(f >> 16 & 255) / 255.0F, (float)(f >> 8 & 255) / 255.0F, (float)(f & 255) / 255.0F);
		//int p1 = (texture & 0xf) << 4;
		//int p2 = texture & 0xf0;
		double tX1 = texture.getMinU();//((float)p1 + 15.99F) / 256F;
		double tY1 = texture.getMinV();//(float)p2 / 256F;
		double tX2 = texture.getMaxU();//(float)p1 / 256F;
		double tY2 = texture.getMaxV();//((float)p2 + 15.99F) / 256F;
		addCube(tessellator, i + 0.3F, j + 0.6F, k + 0.3F, i + 0.7F, j + 1F, k + 0.7F, tX1, tY1, tX2, tY2);
		addCube(tessellator, i + 0.4F, j - 0.6F, k + 0.4F, i + 0.6F, j + 0.6F, k + 0.6F, tX1, tY1, tX2, tY2);
		addCube(tessellator, i - 0.3F, j - 0.8F, k + 0.4F, i + 1.3F, j - 0.6F, k + 0.6F, tX1, tY1, tX2, tY2);
		addCube(tessellator, i + 0.4F, j - 0.8F, k - 0.3F, i + 0.6F, j - 0.6F, k + 1.3F, tX1, tY1, tX2, tY2);
		addCube(tessellator, i - 0.3F, j - 0.6F, k + 0.4F, i - 0.1F, j - 0.45F, k + 0.6F, tX1, tY1, tX2, tY2);
		addCube(tessellator, i + 0.4F, j - 0.6F, k - 0.3F, i + 0.6F, j - 0.45F, k - 0.1F, tX1, tY1, tX2, tY2);
		addCube(tessellator, i + 1.1F, j - 0.6F, k + 0.4F, i + 1.3F, j - 0.45F, k + 0.6F, tX1, tY1, tX2, tY2);
		addCube(tessellator, i + 0.4F, j - 0.6F, k + 1.1F, i + 0.6F, j - 0.45F, k + 1.3F, tX1, tY1, tX2, tY2);
		texture = Blocks.glowstone.getIcon(0, 0);
		//p1 = (texture & 0xf) << 4;
		//p2 = texture & 0xf0;
		tX1 = texture.getMinU();//((float)p1 + 15.99F) / 256F;
		tY1 = texture.getMinV();//(float)p2 / 256F;
		tX2 = texture.getMaxU();//(float)p1 / 256F;
		tY2 = texture.getMaxV();//((float)p2 + 15.99F) / 256F;
		addCube(tessellator, i - 0.4F, j - 0.45F, k + 0.3F, i, j - 0.05F, k + 0.7F, tX1, tY1, tX2, tY2);
		addCube(tessellator, i + 0.3F, j - 0.45F, k - 0.4F, i + 0.7F, j - 0.05F, k, tX1, tY1, tX2, tY2);
		addCube(tessellator, i + 1F, j - 0.45F, k + 0.3F, i + 1.4F, j - 0.05F, k + 0.7F, tX1, tY1, tX2, tY2);
		addCube(tessellator, i + 0.3F, j - 0.45F, k + 1F, i + 0.7F, j - 0.05F, k + 1.4F, tX1, tY1, tX2, tY2);
		return true;
	}

	public boolean renderChair(IBlockAccess iblockaccess, int i, int j, int k, Block block) {
		Tessellator tessellator = Tessellator.instance;
		int side = 0;
		IIcon texture = block.getIcon(iblockaccess, i, j, k, side);
        int f = block.colorMultiplier(iblockaccess, i, j, k);
        tessellator.setColorOpaque_F((float)(f >> 16 & 255) / 255.0F, (float)(f >> 8 & 255) / 255.0F, (float)(f & 255) / 255.0F);
		tessellator.setColorOpaque_F(f, f, f);
		//int p1 = (texture & 0xf) << 4;
		//int p2 = texture & 0xf0;
		double tX1 = texture.getMinU();//((float)p1 + 15.99F) / 256F;
		double tY1 = texture.getMinV();//(float)p2 / 256F;
		double tX2 = texture.getMaxU();//(float)p1 / 256F;
		double tY2 = texture.getMaxV();//((float)p2 + 15.99F) / 256F;
		float f1 = 0.12F;
		float f2 = 0.2F;
		float f3 = 1 - f2 - f1;
		float f4 = 1 - f2;
		float f5 = f2 + f1;
		addCube(tessellator, i + f2, j, k + f2, i + f5, j + 0.4F, k + f5, tX1, tY1, tX2, tY2);
		addCube(tessellator, i + f3, j, k + f2, i + f4, j + 0.4F, k + f5, tX1, tY1, tX2, tY2);
		addCube(tessellator, i + f2, j, k + f3, i + f5, j + 0.4F, k + f4, tX1, tY1, tX2, tY2);
		addCube(tessellator, i + f3, j, k + f3, i + f4, j + 0.4F, k + f4, tX1, tY1, tX2, tY2);
		addCube(tessellator, i + f2 - 0.05F, j + 0.4F, k + f2 - 0.05F, i + f4 + 0.05F, j + 0.55F, k + f4 + 0.05F, tX1, tY1, tX2, tY2);
		return true;
	}

	public boolean renderTable(IBlockAccess iblockaccess, int i, int j, int k, Block block) {
		Tessellator tessellator = Tessellator.instance;
		int side = 0;
		IIcon texture = block.getIcon(iblockaccess, i, j, k, side);
        int f = block.colorMultiplier(iblockaccess, i, j, k);
        tessellator.setColorOpaque_F((float)(f >> 16 & 255) / 255.0F, (float)(f >> 8 & 255) / 255.0F, (float)(f & 255) / 255.0F);
		//int p1 = (texture & 0xf) << 4;
		//int p2 = texture & 0xf0;
		double tX1 = texture.getMinU();//((float)p1 + 15.99F) / 256F;
		double tY1 = texture.getMinV();//(float)p2 / 256F;
		double tX2 = texture.getMaxU();//(float)p1 / 256F;
		double tY2 = texture.getMaxV();//((float)p2 + 15.99F) / 256F;
		addCube(tessellator, i + 0.3F, j, k + 0.3F, i + 0.7F, j + 0.85F, k + 0.7F, tX1, tY1, tX2, tY2);
		addCube(tessellator, i, j + 0.85F, k, i + 1, j + 1, k + 1, tX1, tY1, tX2, tY2);
		return true;
	}

	public void addCube(Tessellator tessellator, float i1, float j1, float k1, float i2, float j2, float k2, double tX1, double tY1, double tX2, double tY2) {
		tessellator.addVertexWithUV(i2, j1, k1, tX1, tY1);
		tessellator.addVertexWithUV(i1, j1, k1, tX2, tY1);
		tessellator.addVertexWithUV(i1, j2, k1, tX2, tY2);
		tessellator.addVertexWithUV(i2, j2, k1, tX1, tY2);
		tessellator.addVertexWithUV(i2, j2, k1, tX1, tY2);
		tessellator.addVertexWithUV(i1, j2, k1, tX2, tY2);
		tessellator.addVertexWithUV(i1, j1, k1, tX2, tY1);
		tessellator.addVertexWithUV(i2, j1, k1, tX1, tY1);
		tessellator.addVertexWithUV(i2, j1, k2, tX1, tY1);
		tessellator.addVertexWithUV(i1, j1, k2, tX2, tY1);
		tessellator.addVertexWithUV(i1, j2, k2, tX2, tY2);
		tessellator.addVertexWithUV(i2, j2, k2, tX1, tY2);
		tessellator.addVertexWithUV(i2, j2, k2, tX1, tY2);
		tessellator.addVertexWithUV(i1, j2, k2, tX2, tY2);
		tessellator.addVertexWithUV(i1, j1, k2, tX2, tY1);
		tessellator.addVertexWithUV(i2, j1, k2, tX1, tY1);
		tessellator.addVertexWithUV(i1, j1, k2, tX1, tY1);
		tessellator.addVertexWithUV(i1, j1, k1, tX2, tY1);
		tessellator.addVertexWithUV(i1, j2, k1, tX2, tY2);
		tessellator.addVertexWithUV(i1, j2, k2, tX1, tY2);
		tessellator.addVertexWithUV(i1, j2, k2, tX1, tY2);
		tessellator.addVertexWithUV(i1, j2, k1, tX2, tY2);
		tessellator.addVertexWithUV(i1, j1, k1, tX2, tY1);
		tessellator.addVertexWithUV(i1, j1, k2, tX1, tY1);
		tessellator.addVertexWithUV(i2, j1, k2, tX1, tY1);
		tessellator.addVertexWithUV(i2, j1, k1, tX2, tY1);
		tessellator.addVertexWithUV(i2, j2, k1, tX2, tY2);
		tessellator.addVertexWithUV(i2, j2, k2, tX1, tY2);
		tessellator.addVertexWithUV(i2, j2, k2, tX1, tY2);
		tessellator.addVertexWithUV(i2, j2, k1, tX2, tY2);
		tessellator.addVertexWithUV(i2, j1, k1, tX2, tY1);
		tessellator.addVertexWithUV(i2, j1, k2, tX1, tY1);
		tessellator.addVertexWithUV(i1, j1, k1, tX1, tY1);
		tessellator.addVertexWithUV(i1, j1, k2, tX1, tY2);
		tessellator.addVertexWithUV(i2, j1, k1, tX2, tY1);
		tessellator.addVertexWithUV(i2, j1, k2, tX2, tY2);
		tessellator.addVertexWithUV(i2, j1, k2, tX2, tY2);
		tessellator.addVertexWithUV(i2, j1, k1, tX2, tY1);
		tessellator.addVertexWithUV(i1, j1, k2, tX1, tY2);
		tessellator.addVertexWithUV(i1, j1, k1, tX1, tY1);
		tessellator.addVertexWithUV(i1, j2, k1, tX1, tY1);
		tessellator.addVertexWithUV(i1, j2, k2, tX1, tY2);
		tessellator.addVertexWithUV(i2, j2, k1, tX2, tY1);
		tessellator.addVertexWithUV(i2, j2, k2, tX2, tY2);
		tessellator.addVertexWithUV(i2, j2, k2, tX2, tY2);
		tessellator.addVertexWithUV(i2, j2, k1, tX2, tY1);
		tessellator.addVertexWithUV(i1, j2, k2, tX1, tY2);
		tessellator.addVertexWithUV(i1, j2, k1, tX1, tY1);
	}
}