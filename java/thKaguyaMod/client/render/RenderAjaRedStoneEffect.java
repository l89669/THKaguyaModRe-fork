package thKaguyaMod.client.render;

import java.util.Random;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thKaguyaMod.entity.effect.EntityAjaRedStoneEffect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderAjaRedStoneEffect extends Render
{
	
	//エイジャの赤石のエフェクトの描画
	private static final ResourceLocation ajaRedStoneEffectTexture = new ResourceLocation("thkaguyamod", "textures/MiracleCircle.png");
	private Random random = new Random();
	
    public RenderAjaRedStoneEffect()
    {
    }
    
    @Override
    public void doRender(Entity entity, double x, double y, double z,
            float f, float f1)
    {
        doRenderAjaRedStoneEffect((EntityAjaRedStoneEffect)entity, x, y, z, f, f1);
    }

    public void doRenderAjaRedStoneEffect(EntityAjaRedStoneEffect ajaRedStoneEffect, double x, double y, double z, float yaw, float pitch)
    {	
        GL11.glPushMatrix();
        this.bindTexture(getEntityTexture(ajaRedStoneEffect));
        GL11.glTranslatef((float)x, (float)y, (float)z);
    	GL11.glDisable(GL11.GL_LIGHTING);
    	//GL11.glEnable(GL11.GL_NORMALIZE);
    	GL11.glEnable(GL11.GL_BLEND);
    	GL11.glDisable(GL11.GL_CULL_FACE);//両面描画
    	GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
    	GL11.glDepthMask(false);
        GL11.glScalef(1.0F, 1.0F, 1.0F);
    	
        Tessellator tessellator = Tessellator.instance;
    	GL11.glRotatef(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
    	GL11.glRotatef(renderManager.playerViewX, 1.0F, 0.0F, 0.0F);

    	int lightNum = ajaRedStoneEffect.getLightPower();
    	lightNum = lightNum * lightNum;
    	float widthX =  0.01F;
    	float lengthY = (float)lightNum / 120.0F;
    	float lengthY2 = lengthY;
    	double zPos;
    	
    	float iniAngle = (float)random.nextInt(36000) / 100F;
    	float angleSpan = 360F / (float)lightNum;
    	
    	float umin = 0.5F;
    	float umax = 38F / 64F;
    	float vmin = 0.0F;
    	float vmax = 1.0F;
    	GL11.glRotatef(MathHelper.sin(iniAngle / 180F * 3.141593F) /3.141593F * 180F, 0.0F, 0.0F, 1.0F);
    	for(int i = 0; i < lightNum; i++)
    	{
    		lengthY2 = lengthY * (0.2F + (float)random.nextInt(8000) / 8000.0F);
    		zPos = (double)random.nextInt(10000) / 10000.0D;
    		GL11.glRotatef(MathHelper.sin(angleSpan) / 3.141593F * 180F, 0.0F, 0.0F, 1.0F);
    		tessellator.startDrawingQuads();
    		tessellator.setNormal(0.0F, 1.0F, 0.0F);
    		tessellator.setColorRGBA_F(1.0F * (float)random.nextInt(10000) / 10000.0F, 0.0F, 0.0F, 0.7F);
        	tessellator.addVertexWithUV( -widthX,  0.0F    , 0.0D, umin, vmax);
        	tessellator.addVertexWithUV(  widthX,  0.0F    , 0.0D, umax, vmax);
        	tessellator.addVertexWithUV(    0.0F,  lengthY2, zPos, umax, vmin);
        	tessellator.addVertexWithUV(    0.0F,  lengthY2, zPos, umin, vmin);
        	tessellator.draw();
    	}
    	
        GL11.glDisable(GL11.GL_BLEND);
    	GL11.glEnable(GL11.GL_CULL_FACE);//表綿描画
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDepthMask(false);
        GL11.glPopMatrix();
    }
    
	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return ajaRedStoneEffectTexture;
	}
}
