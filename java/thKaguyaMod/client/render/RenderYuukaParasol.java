package thKaguyaMod.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thKaguyaMod.client.model.ModelYuukaParasol;
import thKaguyaMod.entity.item.EntityYuukaParasol;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderYuukaParasol extends Render
{
	
	//幽香の日傘の描画
	private static final ResourceLocation parasolTexture = new ResourceLocation("thkaguyamod", "textures/YuukaParasolTexture.png");
	protected ModelBase modelYuukaParasol;

    public RenderYuukaParasol()
    {
    	modelYuukaParasol = new ModelYuukaParasol();
    }
    
    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
        renderYuukaParasol((EntityYuukaParasol)entity, x, y, z, yaw, pitch);
    }

    public void renderYuukaParasol(EntityYuukaParasol yuukaParasol, double x, double y, double z, float yaw, float pitch)
    {
    	
        GL11.glPushMatrix();
        bindEntityTexture(yuukaParasol);
        GL11.glTranslatef((float)x, (float)y - 2.4F, (float)z);
    	GL11.glEnable(GL11.GL_LIGHTING);
    	GL11.glDisable(GL11.GL_CULL_FACE);//両面描画
    	
        Tessellator tessellator = Tessellator.instance;
    	
    	double stickSideLength = 0.07D;
    	double stickHeight = 2.7D;
    	float stickUMin = 0.0F;
    	float stickUMax = 5F / 64F;
    	float stickVMin = 0.0F;
    	float stickVMax = 1.0F;
    	GL11.glRotatef(0F, 0.0F, 1.0F, 0.0F);
    	
    	GL11.glRotatef(180F - yaw, 0.0F, 1.0F, 0.0F);
    	GL11.glRotatef(yuukaParasol.rotationPitch, 1.0F, 0.0F, 0.0F);
    	
    	float parasolUMin = 0.5F;
    	float parasolUMax = 0.75F;
    	float parasolVMin = 0.0F;
    	float parasolVMax = 0.5F;
    	float parasolLength = 2.2F;
    	float parasolWidth = 0.5F;
    	float angleSpan = 360F / 18F;
    	GL11.glScalef(0.7F, 0.7F, 0.7F);
    	for(int i = 0; i < 18; i++)
    	{
    		tessellator.startDrawingQuads();
    		tessellator.setNormal(0.0F, 1.0F, 0.0F);
        	tessellator.addVertexWithUV(          0.0F,   stickHeight, 0.0D, parasolUMin, parasolVMax);
        	tessellator.addVertexWithUV(          0.0F,   stickHeight, 0.0D, parasolUMax, parasolVMax);
        	tessellator.addVertexWithUV(  parasolWidth, parasolLength, -1.0D, parasolUMax, parasolVMin);
        	tessellator.addVertexWithUV( -parasolWidth, parasolLength, -1.0D, parasolUMin, parasolVMin);
        	tessellator.draw();
    		tessellator.startDrawingQuads();
    		tessellator.setNormal(0.0F, 1.0F, 0.0F);
        	tessellator.addVertexWithUV( -parasolWidth   , parasolLength     , -1.0D, parasolUMin, parasolVMax);
        	tessellator.addVertexWithUV(  parasolWidth   , parasolLength     , -1.0D, parasolUMax, parasolVMax);
        	tessellator.addVertexWithUV(  parasolWidth*2F, parasolLength-0.5F, -1.7D, parasolUMax, parasolVMin);
        	tessellator.addVertexWithUV( -parasolWidth*2F, parasolLength-0.5F, -1.7D, parasolUMin, parasolVMin);
        	tessellator.draw();
    		GL11.glRotatef(angleSpan, 0.0F, 1.0F, 0.0F);
    	}
    	GL11.glScalef(0.3F, 0.4F, 0.3F);
    	modelYuukaParasol.render(yuukaParasol, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
    	
    	
    	GL11.glEnable(GL11.GL_CULL_FACE);//表綿描画
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) 
	{
		return parasolTexture;
	}
}
