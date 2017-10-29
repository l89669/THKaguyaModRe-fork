package thKaguyaMod.client.render.living;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thKaguyaMod.entity.living.EntityTHPhantom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTHPhantom extends RenderLiving
{
	//幽霊を描画する
	private static final ResourceLocation phantomTexture = new ResourceLocation("thkaguyamod", "textures/shot/MusouFuuin.png");
	protected float colorR[] = { 224F/255F,   0F/255F,   0F/255F, 224F/255F, 224F/255F,   0F/255F, 255F/255F, 255F/255F};
	protected float colorG[] = {   0F/255F,   0F/255F, 224F/255F,  64F/255F,   0F/255F, 224F/255F, 128F/255F, 255F/255F};
	protected float colorB[] = {   0F/255F, 224F/255F,   0F/255F,   0F/255F, 224F/255F, 224F/255F,   0F/255F, 255F/255F};

    public RenderTHPhantom()
    {
    	super(null, 0.25F);
    }
    
    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
    	this.renderTHPhantom((EntityTHPhantom)entity, x, y, z, yaw, pitch);
    	
    }

	public void renderTHPhantom(EntityTHPhantom thPhantom, double x, double y, double z, float yaw, float pitch)
	{
        GL11.glPushMatrix();
        this.bindTexture(getEntityTexture(thPhantom));
        GL11.glTranslatef((float)x, (float)y, (float)z);
		//GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
    	//GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
    	GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
    	GL11.glDepthMask(false);
    	GL11.glScalef(1.0F, 1.0F, 1.0F);
        
    	
    	int color = thPhantom.getForm();
    	double time_r = (double)thPhantom.ticksExisted / 180.0D * Math.PI;;
    	
    	GL11.glRotatef(180F - renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
	
    	float size =  1.0F;//(300F - (float)thPhantom.ticksExisted) / 300F;
    	float size2 = size * 0.7F;
    	GL11.glScalef(size, size, size);
    	
    	
        renderPhantomPart(color, time_r, thPhantom.hurtTime);
    	
        GL11.glTranslatef((float)Math.sin(time_r * 5) * 0.2F, 0.1F + (thPhantom.ticksExisted % 20) * 0.06F, 0F);
        size = 0.9F * ((20F - (float)(thPhantom.ticksExisted % 20)) / 20F);
    	GL11.glScalef(size, size, size);
        renderPhantomPart(color, time_r, thPhantom.hurtTime);
        
        GL11.glTranslatef((float)Math.cos(time_r * 5) * 0.2F, 0.1F + (thPhantom.ticksExisted % 20) * 0.07F, 0F);
        size = 0.8F * ((20F - (float)(thPhantom.ticksExisted % 20)) / 20F);
    	GL11.glScalef(size, size, size);
        renderPhantomPart(color, time_r, thPhantom.hurtTime);

        GL11.glDisable(GL11.GL_BLEND);
        //GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDepthMask(true);
        GL11.glPopMatrix();
	}

	private void renderPhantomPart(int color, double time_r, int damage)
	{
		Tessellator tessellator = Tessellator.instance;
		int pattern = 0;//time % 2;
        float umin = (float)((pattern % 32) * 32 + 0) / 64F;
        float umax = (float)((pattern % 32) * 32 + 32) / 64F;
        float vmin = 0.0F;
        float vmax = 1.0F;
        
        float alpha = (40F - (float)damage) / 40F;
        
    	tessellator.startDrawingQuads();
    	//tessellator.setColorRGBA_F(MathHelper.sin(color), MathHelper.cos(color), -MathHelper.sin(color)*2F, 0.60F);
    	tessellator.setColorRGBA_F(colorR[color] * 0.3F, colorG[color] * 0.3F, colorB[color] * 0.3F, 0.3F * alpha);
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(-0.7F + Math.sin(time_r * 3.0D) * 0.1F, -0.2F - Math.cos(time_r * 7.0D) * 0.1F, 0.0D, umin, vmax);
        tessellator.addVertexWithUV( 0.7F - Math.cos(time_r * 4.0D) * 0.1F, -0.2F - Math.sin(time_r * 5.0D) * 0.1F, 0.0D, umax, vmax);
        tessellator.addVertexWithUV( 0.7F + Math.sin(time_r * 5.0D) * 0.1F,  1.2F + Math.cos(time_r * 4.0D) * 0.1F, 0.0D, umax, vmin);
        tessellator.addVertexWithUV(-0.7F - Math.cos(time_r * 7.0D) * 0.1F,  1.2F + Math.sin(time_r * 3.0D) * 0.1F, 0.0D, umin, vmin);
        
        
        tessellator.draw();
        tessellator.startDrawingQuads();
        //tessellator.setColorRGBA_F(MathHelper.sin(color), MathHelper.cos(color), -MathHelper.sin(color)*2F, 0.60F);
    	tessellator.setColorRGBA_F(colorR[color] * 0.3F, colorG[color] * 0.3F, colorB[color] * 0.3F, 0.7F * alpha);
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(-0.6F - Math.cos(time_r * 7.0D) * 0.1F, -0.1F + Math.sin(time_r * 5.0D) * 0.1F, 0.001D, umin, vmax);
        tessellator.addVertexWithUV( 0.6F + Math.sin(time_r * 3.0D) * 0.1F, -0.1F + Math.cos(time_r * 4.0D) * 0.1F, 0.001D, umax, vmax);
        tessellator.addVertexWithUV( 0.6F - Math.cos(time_r * 4.0D) * 0.1F,  1.1F - Math.sin(time_r * 3.0D) * 0.1F, 0.001D, umax, vmin);
        tessellator.addVertexWithUV(-0.6F + Math.sin(time_r * 5.0D) * 0.1F,  1.1F - Math.cos(time_r * 7.0D) * 0.1F, 0.001D, umin, vmin);
        tessellator.draw();
    	
    	
    	//
    	for(int i = 0; i < 3; i++)
    	{
    		tessellator.startDrawingQuads();
    		if(damage > 0)
    		{
    			tessellator.setColorRGBA_F(1.0F, 0.0F, 0.0F, alpha);
    		}
	        tessellator.setNormal(0.0F, 1.0F, 0.0F);
	        tessellator.addVertexWithUV(-0.5F,  0.0F, 0.002D, umin, vmax);
	        tessellator.addVertexWithUV( 0.5F,  0.0F, 0.002D, umax, vmax);
	        tessellator.addVertexWithUV( 0.5F,  1.0F, 0.002D, umax, vmin);
	        tessellator.addVertexWithUV(-0.5F,  1.0F, 0.002D, umin, vmin);
	        tessellator.draw();
    	}
	}
	
    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.getEntityTexture((EntityTHPhantom)entity);
    }
    
    protected ResourceLocation getEntityTexture(EntityTHPhantom thFairy)
    {
        return phantomTexture;
    }
}
