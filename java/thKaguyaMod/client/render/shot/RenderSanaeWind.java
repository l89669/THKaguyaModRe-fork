package thKaguyaMod.client.render.shot;

import java.util.Random;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thKaguyaMod.entity.shot.EntitySanaeWind;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSanaeWind extends Render
{
	
	//早苗の風の描画
	private static final ResourceLocation windTexture = new ResourceLocation("thkaguyamod", "textures/shot/WindShot.png");
	
    public RenderSanaeWind()
    {
    }
    
    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
        renderSanaeWind((EntitySanaeWind)entity, x, y, z, yaw, pitch);
    }

    public void renderSanaeWind(EntitySanaeWind sanaeWind, double x, double y, double z, float yaw, float pitch)
    {
        GL11.glPushMatrix();
        bindEntityTexture(sanaeWind);
        GL11.glTranslatef((float)x, (float)y, (float)z);
    	GL11.glDisable(GL11.GL_LIGHTING);
    	GL11.glEnable(GL11.GL_BLEND);
    	GL11.glDisable(GL11.GL_CULL_FACE);//両面描画
    	//GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
    	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
    	//GL11.glDepthMask(false);
        float f2 = 0.5F;
        GL11.glScalef(f2 / 1.0F, f2 / 1.0F, f2 / 1.0F);
        Tessellator tessellator = Tessellator.instance;
    	Random random = new Random();
    	float rand1 = (float)random.nextInt(50) / 100F;
    	float rand2 = (float)random.nextInt(100) / 100F;
    	int pattern = sanaeWind.ticksExisted % 4;
        float f3 = (float)((pattern % 2) * 32 + 0) / 64F;
        float f4 = (float)((pattern % 2) * 32 + 32) / 64F;
        float f5 = (float)((int)(pattern / 2) * 16 + 0) / 32F;
        float f6 = (float)((int)(pattern / 2) * 16 + 16) / 32F;
    	float f7 = rand1;
    	float f8 = 4.0F + rand2;
        float f9 = 0.5F;

    	for(int i = 0; i < 8; i++)
    	{
    		GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F);
	        tessellator.startDrawingQuads();
	    	tessellator.setColorRGBA_F(0.1F, 0.9F, 0.6F, 0.7F);
	        tessellator.setNormal(0.0F, 1.0F, 0.0F);
	        tessellator.addVertexWithUV(0.0F - f7, -1.0F - f9, 0.0D, f3, f6);
	        tessellator.addVertexWithUV(0.0F + f7, -1.0F - f9, 0.0D, f4, f6);
	        tessellator.addVertexWithUV(0.0F + f8,  2.0F - f9, 2.0D, f4, f5);
	        tessellator.addVertexWithUV(0.0F - f8,  2.0F - f9, 2.0D, f3, f5);
			tessellator.draw();
    	}
    	//GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
    	GL11.glEnable(GL11.GL_CULL_FACE);//表綿描画
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) 
	{
		return windTexture;
	}
}
