package thKaguyaMod.client.render.effect;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thKaguyaMod.entity.effect.EntityHakurouReflecter;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHakurouReflecter extends Render
{
	
	//白楼剣の反射エフェクトの描画
	private static final ResourceLocation hakurouReflecterTexture = new ResourceLocation("thkaguyamod", "textures/HakurouReflect.png");

    public RenderHakurouReflecter()
    {
    }
    
    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
        doRenderHakurouReflecter((EntityHakurouReflecter)entity, x, y, z, yaw, pitch);
    }

    public void doRenderHakurouReflecter(EntityHakurouReflecter hakurouReflecter, double x, double y, double z, float yaw, float pitch)
    {
        GL11.glPushMatrix();
        this.bindTexture(getEntityTexture(hakurouReflecter));
        GL11.glTranslatef((float)x, (float)y, (float)z);
		GL11.glDisable(GL11.GL_LIGHTING);
    	GL11.glEnable(GL11.GL_BLEND);
    	GL11.glDisable(GL11.GL_CULL_FACE);//両面描画
    	GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
    	GL11.glDepthMask(false);
        float size = 1.0F;
        GL11.glScalef(size, size, size);
        
    	Tessellator tessellator = Tessellator.instance;

        float minU = 0.0F;
        float maxU = 0.5F;
        float minV = 0.0F;
        float maxV = 1.0F;
        GL11.glRotatef(yaw, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(hakurouReflecter.rotationPitch, 1.0F, 0.0F, 0.0F);
        
        float angle = hakurouReflecter.ticksExisted / 5F;
        float span = (float)Math.PI / 8F;
        double length = 1.8D;
        for(int i = 0; i < 3; i++)
        {
	        double xPos1 = Math.cos(angle) * length; 
	        double xPos2 = Math.cos(angle + span) * length;
	        double yPos1 = Math.sin(angle) * length;
	        double yPos2 = Math.sin(angle + span) * length;
	        for(int j = 0; j < 16; j++)
	        {
	        	tessellator.startDrawingQuads();
		    	tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 0.2F + j * 0.3F);
		        tessellator.setNormal(0.0F, 1.0F, 0.0F);
		        tessellator.addVertexWithUV( xPos1,  yPos1, 0.0D, minU, maxV);
		        tessellator.addVertexWithUV( xPos2,  yPos2, 0.0D, maxU, maxV);
		        tessellator.addVertexWithUV(     0,      0, 0.0D, maxU, minV);
		        tessellator.addVertexWithUV(     0,      0, 0.0D, minU, minV);	
		        tessellator.draw();
		        angle += span;
		        xPos1 = Math.cos(angle) * length; 
		        xPos2 = Math.cos(angle + span) * length;
		        yPos1 = Math.sin(angle) * length;
		        yPos2 = Math.sin(angle + span) * length;
	        }
	        length -= 0.3D;
        }
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_CULL_FACE);//表綿描画
    	GL11.glDisable(GL11.GL_BLEND);
    	GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return hakurouReflecterTexture;
	}
}
