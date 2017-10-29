package thKaguyaMod.client.render.living;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thKaguyaMod.entity.living.EntityFamiliar;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderFamiliar extends Render
{
	
	//使い魔の描画
	private static final ResourceLocation familiarTexture = new ResourceLocation("thkaguyamod", "textures/mob/Familiar.png");
	protected float colorR[] = { 255F, 255F, 255F,   0F,   0F,   0F, 255F};
	protected float colorG[] = {   0F, 165F, 255F, 255F,   0F, 255F,   0F};
	protected float colorB[] = {   0F,   0F,   0F,   0F, 255F, 255F, 255F};

    public RenderFamiliar()
    {
    }
    
	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch) {
		renderFamiliar((EntityFamiliar)entity, x, y, z, yaw, pitch);
	}
    
    public void renderFamiliar(EntityFamiliar familiar, double x, double y, double z, float yaw, float pitch)
    {
    	//形状IDがマイナスなら描画しない
    	if(familiar.getForm() < 0)
    	{
    		return;
    	}
        GL11.glPushMatrix();
        bindEntityTexture(familiar);
        GL11.glTranslatef((float)x, (float)y, (float)z);
		GL11.glDisable(GL11.GL_LIGHTING);
    	GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
    	GL11.glDepthMask(false);
    	float size = 3.0F + (float)Math.sin(familiar.ticksExisted * 10 / 180.0D * Math.PI) * 0.6F;
        GL11.glScalef(size, size, size);
        Tessellator tessellator = Tessellator.instance;
    	
    	//int color = familiar.getShotColor();

    	//int pattern = familiar.getAnimationCount() % 2;
        float umin = 0.0F;
        float umax = 1.0F;
        float vmin = 0.0F;
        float vmax = 1.0F;
    	
        GL11.glRotatef(180F - renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(familiar.ticksExisted * 5F, 0.0F, 0.0F, 1.0F);

    	int color = familiar.getForm() % 8;
		tessellator.setColorRGBA_F(MathHelper.sin(color), MathHelper.cos(color), -MathHelper.sin(color)*2F, 0.60F);
    	
    	tessellator.startDrawingQuads();
    	tessellator.setColorRGBA_F(MathHelper.sin(color), MathHelper.cos(color), -MathHelper.sin(color)*2F, 0.60F);
    	//tessellator.setColorRGBA_F(colorR[randColor], colorG[randColor], colorB[randColor], 0.3F);
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(-0.6F, -0.6F, 0.0D, umin, vmax);
        tessellator.addVertexWithUV( 0.6F, -0.6F, 0.0D, umax, vmax);
        tessellator.addVertexWithUV( 0.6F,  0.6F, 0.0D, umax, vmin);
        tessellator.addVertexWithUV(-0.6F,  0.6F, 0.0D, umin, vmin);
        tessellator.draw();
        /*tessellator.startDrawingQuads();
        tessellator.setColorRGBA_F(MathHelper.sin(color), MathHelper.cos(color), -MathHelper.sin(color)*2F, 0.60F);
    	//tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 0.7F);
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(-0.5F, -0.5F, 0.001D, umin, vmax);
        tessellator.addVertexWithUV( 0.5F, -0.5F, 0.001D, umax, vmax);
        tessellator.addVertexWithUV( 0.5F,  0.5F, 0.001D, umax, vmin);
        tessellator.addVertexWithUV(-0.5F,  0.5F, 0.001D, umin, vmin);
        tessellator.draw();
    	tessellator.startDrawingQuads();
    	tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 0.9F);
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(-0.4F, -0.4F, 0.002D, umin, vmax);
        tessellator.addVertexWithUV( 0.4F, -0.4F, 0.002D, umax, vmax);
        tessellator.addVertexWithUV( 0.4F,  0.4F, 0.002D, umax, vmin);
        tessellator.addVertexWithUV(-0.4F,  0.4F, 0.002D, umin, vmin);
        tessellator.draw();*/
        GL11.glDepthMask(true);
    	GL11.glDisable(GL11.GL_BLEND);
    	GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return getEntityTexture((EntityFamiliar)entity);
	}
	
	protected ResourceLocation getEntityTexture(EntityFamiliar familiar) {
		return familiarTexture;
	}
}