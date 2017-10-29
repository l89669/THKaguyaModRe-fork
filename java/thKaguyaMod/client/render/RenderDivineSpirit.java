package thKaguyaMod.client.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thKaguyaMod.client.THKaguyaRenderLib;
import thKaguyaMod.entity.EntityDivineSpirit;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDivineSpirit extends Render
{
	/*
	小神霊の描画
	*/
	private static final ResourceLocation divineSpiritTexture = new ResourceLocation("thkaguyamod", "textures/shot/MusouFuuin.png");
	protected float colorR[] = { 224F/255F,   0F/255F,   0F/255F, 224F/255F, 224F/255F,   0F/255F, 255F/255F, 255F/255F};
	protected float colorG[] = {   0F/255F,   0F/255F, 224F/255F,  64F/255F,   0F/255F, 224F/255F, 128F/255F, 255F/255F};
	protected float colorB[] = {   0F/255F, 224F/255F,   0F/255F,   0F/255F, 224F/255F, 224F/255F,   0F/255F, 255F/255F};
	
    public RenderDivineSpirit()
    {
    }
    
    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
        doRenderDivineSpirit((EntityDivineSpirit)entity, x, y, z, yaw, pitch);
    }

    public void doRenderDivineSpirit(EntityDivineSpirit divineSpirit, double x, double y, double z, float yaw, float pitch)
    {
        GL11.glPushMatrix();
        this.bindTexture(getEntityTexture(divineSpirit));
        GL11.glTranslatef((float)x, (float)y, (float)z);
		//GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
    	GL11.glDepthMask(false);
    	GL11.glScalef(1.0F, 1.0F, 1.0F);
        Tessellator tessellator = Tessellator.instance;
    	
    	int color = divineSpirit.getDivineSpiritColor();
    	double time_r = (double)divineSpirit.ticksExisted / 180.0D * Math.PI;;
    	
    	// プレイヤーに常に正面を向けるようにする
        THKaguyaRenderLib.getGLRotatefByTherdPersonView(renderManager);
	
    	float size =  (300F - (float)divineSpirit.getDivineSpiritCount()) / 300F;
    	float size2 = size * 0.7F;
    	GL11.glScalef(size, size, size);
    	
    	int pattern = 0;//time % 2;
        float umin = (float)((pattern % 32) * 32 + 0) / 64F;
        float umax = (float)((pattern % 32) * 32 + 32) / 64F;
        float vmin = 0.0F;
        float vmax = 1.0F;
    	//int color = 0;
		//tessellator.setColorRGBA_F(MathHelper.sin(color), MathHelper.cos(color), -MathHelper.sin(color)*2F, 0.60F);
    	
    	tessellator.startDrawingQuads();
    	//tessellator.setColorRGBA_F(MathHelper.sin(color), MathHelper.cos(color), -MathHelper.sin(color)*2F, 0.60F);
    	tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 0.3F);
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(-0.7F + Math.sin(time_r * 3.0D) * 0.1F, -0.7F - Math.cos(time_r * 7.0D) * 0.1F, 0.0D, umin, vmax);
        tessellator.addVertexWithUV( 0.7F - Math.cos(time_r * 4.0D) * 0.1F, -0.7F - Math.sin(time_r * 5.0D) * 0.1F, 0.0D, umax, vmax);
        tessellator.addVertexWithUV( 0.7F + Math.sin(time_r * 5.0D) * 0.1F,  0.7F + Math.cos(time_r * 4.0D) * 0.1F, 0.0D, umax, vmin);
        tessellator.addVertexWithUV(-0.7F - Math.cos(time_r * 7.0D) * 0.1F,  0.7F + Math.sin(time_r * 3.0D) * 0.1F, 0.0D, umin, vmin);
        
        
        tessellator.draw();
        tessellator.startDrawingQuads();
        //tessellator.setColorRGBA_F(MathHelper.sin(color), MathHelper.cos(color), -MathHelper.sin(color)*2F, 0.60F);
    	tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 0.7F);
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(-0.6F - Math.cos(time_r * 7.0D) * 0.1F, -0.6F + Math.sin(time_r * 5.0D) * 0.1F, 0.001D, umin, vmax);
        tessellator.addVertexWithUV( 0.6F + Math.sin(time_r * 3.0D) * 0.1F, -0.6F + Math.cos(time_r * 4.0D) * 0.1F, 0.001D, umax, vmax);
        tessellator.addVertexWithUV( 0.6F - Math.cos(time_r * 4.0D) * 0.1F,  0.6F - Math.sin(time_r * 3.0D) * 0.1F, 0.001D, umax, vmin);
        tessellator.addVertexWithUV(-0.6F + Math.sin(time_r * 5.0D) * 0.1F,  0.6F - Math.cos(time_r * 7.0D) * 0.1F, 0.001D, umin, vmin);
        tessellator.draw();
    	tessellator.startDrawingQuads();
    	tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 1.0F);
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(-0.4F, -0.4F, 0.002D, umin, vmax);
        tessellator.addVertexWithUV( 0.4F, -0.4F, 0.002D, umax, vmax);
        tessellator.addVertexWithUV( 0.4F,  0.4F, 0.002D, umax, vmin);
        tessellator.addVertexWithUV(-0.4F,  0.4F, 0.002D, umin, vmin);
        tessellator.draw();

		GL11.glDisable(GL11.GL_BLEND);
        //GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDepthMask(true);
        GL11.glPopMatrix();
    }
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return divineSpiritTexture;
	}
}
