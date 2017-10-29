package thKaguyaMod.client.render;

import java.util.Random;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thKaguyaMod.entity.effect.EntityCirnoIceBox;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCirnoIceBox extends Render
{
	//魔人経巻の描画
	private static final ResourceLocation iceBoxTexture = new ResourceLocation("textures/blocks/ice.png");
	private Random random = new Random();

    public RenderCirnoIceBox()
    {
    }
    
    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
        renderCirnoIceBox((EntityCirnoIceBox)entity, x, y, z, yaw, pitch);
    }

    public void renderCirnoIceBox(EntityCirnoIceBox iceBox, double x, double y, double z, float yaw, float pitch)
    {
        GL11.glPushMatrix();
        this.bindTexture(getEntityTexture(iceBox));
        GL11.glTranslatef((float)x, (float)y, (float)z);
    	GL11.glDisable(GL11.GL_LIGHTING);
    	GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
    	GL11.glDisable(GL11.GL_CULL_FACE);//両面描画
        GL11.glScalef(1.0F, 1.0F, 1.0F);
        Tessellator tessellator = Tessellator.instance;
        
        float width = iceBox.getIceBoxWidth() / 2.0F + 0.4F;
        float height = iceBox.getIceBoxHeight() + 0.8F;
        
        float px[] = { -width, width, width, -width, -width, width, width, -width};
        float py[] = { height, height, height, height, 0.0F, 0.0F, 0.0F, 0.0F};
        float pz[] = { width, width, -width, -width, -width, -width, width, width};
        
        int p[][] = {	{ 0, 1, 2, 3},
        				{ 3, 2, 5, 4},
        				{ 2, 1, 6, 5},
        				{ 1, 0, 7, 6},
        				{ 0, 3, 4, 7},
        				{ 4, 5, 6, 7}
        };
        
        for(int i = 0; i < 6; i++)
        {
	    	tessellator.startDrawingQuads();
	    	tessellator.setNormal(0.0F, 1.0F, 0.0F);
	    	tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
	        tessellator.addVertexWithUV( px[p[i][0]] ,  py[p[i][0]], pz[p[i][0]] , 0.0F , 1.0F);
	        tessellator.addVertexWithUV( px[p[i][1]] ,  py[p[i][1]], pz[p[i][1]] , 1.0F , 1.0F);
	        tessellator.addVertexWithUV( px[p[i][2]] ,  py[p[i][2]], pz[p[i][2]] , 1.0F , 0.0F);
	        tessellator.addVertexWithUV( px[p[i][3]] ,  py[p[i][3]], pz[p[i][3]] , 0.0F , 0.0F);
	        tessellator.draw();
        }
    	
        GL11.glDisable(GL11.GL_BLEND);
    	GL11.glEnable(GL11.GL_CULL_FACE);//表綿描画
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
    
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return iceBoxTexture;
	}
}