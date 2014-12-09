package assets.minecessity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderLightBullet extends Render {
	public RenderLightBullet() {
		super();
	}

	public void renderBullet(EntityLightBullet bullet, double d, double d1, double d2) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) d / 4F, (float) d1 / 4F, (float) d2 / 4F);
		GL11.glDepthMask(false);
		GL11.glEnable(GL11.GL_LIGHTING);
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		GL11.glDisable(32826 /* GL_RESCALE_NORMAL_EXT */);
		addCube(tessellator, -0.01F, -0.01F, -0.01F, 0.01F, 0.01F, 0.01F, 0D, 0D, 0D, 0D);
		tessellator.draw();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(32826 /* GL_RESCALE_NORMAL_EXT */);
		GL11.glDepthMask(true);
		GL11.glPopMatrix();
	}

	@Override
	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
		renderBullet((EntityLightBullet) entity, d, d1, d2);
	}

	public void addCube(Tessellator tessellator, float i1, float j1, float k1, float i2, float j2, float k2, double tX1, double tY1, double tX2, double tY2) {
		tessellator.addVertexWithUV(i1, j1, k1, tX1, tY1);
		tessellator.addVertexWithUV(i2, j1, k1, tX1, tY1);
		tessellator.addVertexWithUV(i2, j2, k1, tX2, tY2);
		tessellator.addVertexWithUV(i1, j2, k1, tX2, tY2);
		tessellator.addVertexWithUV(i1, j2, k1, tX2, tY2);
		tessellator.addVertexWithUV(i2, j2, k1, tX2, tY2);
		tessellator.addVertexWithUV(i2, j1, k1, tX1, tY1);
		tessellator.addVertexWithUV(i1, j1, k1, tX1, tY1);
		tessellator.addVertexWithUV(i1, j1, k2, tX1, tY1);
		tessellator.addVertexWithUV(i2, j1, k2, tX1, tY1);
		tessellator.addVertexWithUV(i2, j2, k2, tX2, tY2);
		tessellator.addVertexWithUV(i1, j2, k2, tX2, tY2);
		tessellator.addVertexWithUV(i1, j2, k2, tX2, tY2);
		tessellator.addVertexWithUV(i2, j2, k2, tX2, tY2);
		tessellator.addVertexWithUV(i2, j1, k2, tX1, tY1);
		tessellator.addVertexWithUV(i1, j1, k2, tX1, tY1);
		tessellator.addVertexWithUV(i1, j1, k1, tX1, tY1);
		tessellator.addVertexWithUV(i1, j1, k2, tX1, tY1);
		tessellator.addVertexWithUV(i1, j2, k2, tX2, tY2);
		tessellator.addVertexWithUV(i1, j2, k1, tX2, tY2);
		tessellator.addVertexWithUV(i1, j2, k1, tX2, tY2);
		tessellator.addVertexWithUV(i1, j2, k2, tX2, tY2);
		tessellator.addVertexWithUV(i1, j1, k2, tX1, tY1);
		tessellator.addVertexWithUV(i1, j1, k1, tX1, tY1);
		tessellator.addVertexWithUV(i2, j1, k1, tX1, tY1);
		tessellator.addVertexWithUV(i2, j1, k2, tX1, tY1);
		tessellator.addVertexWithUV(i2, j2, k2, tX2, tY2);
		tessellator.addVertexWithUV(i2, j2, k1, tX2, tY2);
		tessellator.addVertexWithUV(i2, j2, k1, tX2, tY2);
		tessellator.addVertexWithUV(i2, j2, k2, tX2, tY2);
		tessellator.addVertexWithUV(i2, j1, k2, tX1, tY1);
		tessellator.addVertexWithUV(i2, j1, k1, tX1, tY1);
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

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}
}
