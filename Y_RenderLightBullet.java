package mods.minecessity;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.src.ModLoader;

import org.lwjgl.opengl.GL11;

public class Y_RenderLightBullet extends Render
{

    public Y_RenderLightBullet()
    {
        super();
    }

    public void renderBullet(Y_EntityLightBullet bullet, double d, double d1, double d2, float f, float f1)
    {
		if(!ModLoader.getMinecraftInstance().thePlayer.canEntityBeSeen(bullet)) return;
		loadTexture("haha");
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d/4F, (float)d1/4F, (float)d2/4F);
		
		GL11.glDepthMask(false);
		GL11.glEnable(2896 /*GL_LIGHTING*/);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
		GL11.glDisable(32826 /*GL_RESCALE_NORMAL_EXT*/);
        addCube(tessellator,-0.01F,-0.01F,-0.01F,0.01F,0.01F,0.01F,0D,0D,0D,0D);
        tessellator.draw();
		GL11.glDisable(2896 /*GL_LIGHTING*/);
		GL11.glEnable(32826 /*GL_RESCALE_NORMAL_EXT*/);
		GL11.glDepthMask(true);
		
        GL11.glPopMatrix();
    }

    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
    {
        renderBullet((Y_EntityLightBullet)entity, d, d1, d2, f, f1);
    }
	
	public void addCube(Tessellator tessellator, float i1, float j1, float k1, float i2, float j2, float k2, double tX1, double tY1, double tX2, double tY2)
	{
		double x11 = 0;
		double y11 = 0;

		tessellator.addVertexWithUV(i1, j1, k1, tX1+x11, tY1+y11);
        tessellator.addVertexWithUV(i2, j1, k1, tX1+x11, tY1+y11);
        tessellator.addVertexWithUV(i2, j2, k1, tX2-x11, tY2-y11);
        tessellator.addVertexWithUV(i1, j2, k1, tX2-x11, tY2-y11);
        tessellator.addVertexWithUV(i1, j2, k1, tX2-x11, tY2-y11);
		tessellator.addVertexWithUV(i2, j2, k1, tX2-x11, tY2-y11);
		tessellator.addVertexWithUV(i2, j1, k1, tX1+x11, tY1+y11);
		tessellator.addVertexWithUV(i1, j1, k1, tX1+x11, tY1+y11);

		tessellator.addVertexWithUV(i1, j1, k2, tX1+x11, tY1+y11);
        tessellator.addVertexWithUV(i2, j1, k2, tX1+x11, tY1+y11);
        tessellator.addVertexWithUV(i2, j2, k2, tX2-x11, tY2-y11);
        tessellator.addVertexWithUV(i1, j2, k2, tX2-x11, tY2-y11);
        tessellator.addVertexWithUV(i1, j2, k2, tX2-x11, tY2-y11);
		tessellator.addVertexWithUV(i2, j2, k2, tX2-x11, tY2-y11);
		tessellator.addVertexWithUV(i2, j1, k2, tX1+x11, tY1+y11);
		tessellator.addVertexWithUV(i1, j1, k2, tX1+x11, tY1+y11);
		
		tessellator.addVertexWithUV(i1, j1, k1, tX1+x11, tY1+y11);
        tessellator.addVertexWithUV(i1, j1, k2, tX1+x11, tY1+y11);
        tessellator.addVertexWithUV(i1, j2, k2, tX2-x11, tY2-y11);
        tessellator.addVertexWithUV(i1, j2, k1, tX2-x11, tY2-y11);
        tessellator.addVertexWithUV(i1, j2, k1, tX2-x11, tY2-y11);
		tessellator.addVertexWithUV(i1, j2, k2, tX2-x11, tY2-y11);
		tessellator.addVertexWithUV(i1, j1, k2, tX1+x11, tY1+y11);
		tessellator.addVertexWithUV(i1, j1, k1, tX1+x11, tY1+y11);
		
		tessellator.addVertexWithUV(i2, j1, k1, tX1+x11, tY1+y11);
        tessellator.addVertexWithUV(i2, j1, k2, tX1+x11, tY1+y11);
        tessellator.addVertexWithUV(i2, j2, k2, tX2-x11, tY2-y11);
        tessellator.addVertexWithUV(i2, j2, k1, tX2-x11, tY2-y11);
        tessellator.addVertexWithUV(i2, j2, k1, tX2-x11, tY2-y11);
		tessellator.addVertexWithUV(i2, j2, k2, tX2-x11, tY2-y11);
		tessellator.addVertexWithUV(i2, j1, k2, tX1+x11, tY1+y11);
		tessellator.addVertexWithUV(i2, j1, k1, tX1+x11, tY1+y11);

		tessellator.addVertexWithUV(i1, j1, k1, tX1+x11, tY1+y11);
        tessellator.addVertexWithUV(i1, j1, k2, tX1+x11, tY2-y11);
        tessellator.addVertexWithUV(i2, j1, k1, tX2-x11, tY1+y11);
        tessellator.addVertexWithUV(i2, j1, k2, tX2-x11, tY2-y11);
        tessellator.addVertexWithUV(i2, j1, k2, tX2-x11, tY2-y11);
		tessellator.addVertexWithUV(i2, j1, k1, tX2-x11, tY1+y11);
		tessellator.addVertexWithUV(i1, j1, k2, tX1+x11, tY2-y11);
		tessellator.addVertexWithUV(i1, j1, k1, tX1+x11, tY1+y11);
		

		tessellator.addVertexWithUV(i1, j2, k1, tX1+x11, tY1+y11);
        tessellator.addVertexWithUV(i1, j2, k2, tX1+x11, tY2-y11);
        tessellator.addVertexWithUV(i2, j2, k1, tX2-x11, tY1+y11);
        tessellator.addVertexWithUV(i2, j2, k2, tX2-x11, tY2-y11);
        tessellator.addVertexWithUV(i2, j2, k2, tX2-x11, tY2-y11);
		tessellator.addVertexWithUV(i2, j2, k1, tX2-x11, tY1+y11);
		tessellator.addVertexWithUV(i1, j2, k2, tX1+x11, tY2-y11);
		tessellator.addVertexWithUV(i1, j2, k1, tX1+x11, tY1+y11);
	}
}
