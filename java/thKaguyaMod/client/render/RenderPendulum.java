package thKaguyaMod.client.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thKaguyaMod.entity.item.EntityPendulum;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPendulum extends Render
{
	
	//ナズーリンペンデュラムの描画

	private static final ResourceLocation pendulumTexture = new ResourceLocation("thkaguyamod", "textures/PendulumTexture.png");
    
	public RenderPendulum()
    {
        shadowSize = 0.5F;//多分影のサイズ
    }
	
	@Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
        renderPendulum((EntityPendulum)entity, x, y, z, yaw, pitch);
    }

    //ペンデュラムの実際の描画処理
    public void renderPendulum(EntityPendulum pendulum, double x, double y, double z, float yaw, float pitch)
    {
    	GL11.glPushMatrix();
    	this.bindEntityTexture(pendulum);
    	float rotationLength = 0.0F;
    	rotationLength = (float)pendulum.getSearchCount() /48.0F;
    	float xDrawPos =  -MathHelper.sin(pendulum.getSearchAngle() / 180F * 3.141593F) * rotationLength;
    	float zDrawPos =   MathHelper.cos(pendulum.getSearchAngle() / 180F * 3.141593F) * rotationLength;
        GL11.glTranslatef(	(float)x + xDrawPos,
        					(float)y,
        					(float)z + zDrawPos);
    	
    	GL11.glEnable(GL11.GL_LIGHTING);
    	GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
    	
    	GL11.glRotatef(180F - yaw, 0.0F, 1.0F, 0.0F);

    	int i, j;
    	
    	float u_min = 16F/64F;
    	float v_min = 20F/32F;
    	float u_max = 28F/64F;
    	float v_max = 32F/32F;
    	
    	float top_p1_u = 12F/64F;
    	float top_p1_v =  4F/32F;
    	float top_p2_u =  1F/64F;
    	float top_p2_v = 19F/32F;
    	float top_p3_u = 22F/64F;
    	float top_p3_v = 19F/32F;
    	
    	float down_p1_u = 33F/64F;
    	float down_p1_v =  0F/32F;
    	float down_p2_u = 55F/64F;
    	float down_p2_v =  0F/32F;
    	float down_p3_u = 44F/64F;
    	float down_p3_v = 31F/32F;

    	Tessellator tessellator = Tessellator.instance;
        float rate = 0.3F;
        GL11.glScalef(rate, rate, rate);

    	for(i = 0; i < 4; i++)
    	{
    		GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);
    		tessellator.startDrawingQuads();
		    tessellator.addVertexWithUV( 0.0F,  1.75F,  0.0D, top_p1_u, top_p1_v);
	        tessellator.addVertexWithUV( 0.0F,  1.75F,  0.0D, top_p1_u, top_p1_v);
	        tessellator.addVertexWithUV( 0.3F,  1.25F, -0.3D, top_p3_u, top_p3_v);
	        tessellator.addVertexWithUV(-0.3F,  1.25F, -0.3D, top_p2_u, top_p2_v);
	        tessellator.draw();
    		tessellator.startDrawingQuads();
		    tessellator.addVertexWithUV(-0.3F,  1.25F, -0.3D, down_p1_u, down_p1_v);
	        tessellator.addVertexWithUV( 0.3F,  1.25F, -0.3D, down_p2_u, down_p2_v);
	        tessellator.addVertexWithUV( 0.0F,  0.25F,  0.0D, down_p3_u, down_p3_v);
	        tessellator.addVertexWithUV( 0.0F,  0.25F,  0.0D, down_p3_u, down_p3_v);
	        tessellator.draw();
    	}
    	GL11.glDisable(GL11.GL_CULL_FACE);//両面描画
    	for(i = 0; i < 4; i++)
    	{
    		GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F);
    		tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(-0.2F,  1.90F,  0.0D, u_min, v_max);
	    	tessellator.addVertexWithUV( 0.2F,  1.90F,  0.0D, u_max, v_max);
	    	tessellator.addVertexWithUV( 0.2F,  1.60F,  0.0D, u_max, v_min);
	    	tessellator.addVertexWithUV(-0.2F,  1.60F,  0.0D, u_min, v_min);
	    	tessellator.draw();
    	}
    	GL11.glEnable(GL11.GL_CULL_FACE);//表綿描画    	
    	GL11.glDisable(GL11.GL_BLEND);
        //GL11.glDisable(GL11.GL_LIGHTING);
    	
        GL11.glPopMatrix();
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return pendulumTexture;
	}
}
