package thKaguyaMod.client.render.shot;

import java.util.Random;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thKaguyaMod.client.THKaguyaRenderLib;
import thKaguyaMod.entity.shot.EntityMusouFuuin;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMusouFuuin extends Render
{
	
	//夢想封印の光弾の描画
	private static final ResourceLocation musouFuuinTexture = new ResourceLocation("thkaguyamod", "textures/shot/MusouFuuin.png");
	private Random random = new Random();

    public RenderMusouFuuin()
    {
    }
    
	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch) {
		renderMusouFuuin((EntityMusouFuuin)entity, x, y, z, yaw, pitch);
	}
    
    public void renderMusouFuuin(EntityMusouFuuin musouFuuin, double x, double y, double z, float yaw, float pitch)
    {
        GL11.glPushMatrix();
        bindEntityTexture(musouFuuin);
        GL11.glTranslatef((float)x, (float)y, (float)z);
		GL11.glDisable(GL11.GL_LIGHTING);
    	GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
    	GL11.glDepthMask(false);
    	float size = musouFuuin.getShotSize();
        GL11.glScalef(size, size, size);
        Tessellator tessellator = Tessellator.instance;
    	
    	//int color = musouFuuin.getShotColor();
    	float colorR[] = { 255F, 255F, 255F,   0F,   0F,   0F, 255F};
    	float colorG[] = {   0F, 165F, 255F, 255F,   0F, 255F,   0F};
    	float colorB[] = {   0F,   0F,   0F,   0F, 255F, 255F, 255F};
    	int pattern = musouFuuin.getAnimationCount() % 2;
        float umin = (float)((pattern % 32) * 32 + 0) / 64F;
        float umax = (float)((pattern % 32) * 32 + 32) / 64F;
        float vmin = 0.0F;
        float vmax = 1.0F;
    	
        // プレイヤーに常に正面を向けるようにする
        THKaguyaRenderLib.getGLRotatefByTherdPersonView(renderManager);

    	int color = musouFuuin.getAnimationCount();
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
        tessellator.startDrawingQuads();
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
        tessellator.draw();
        GL11.glDepthMask(true);
    	GL11.glDisable(GL11.GL_BLEND);
    	GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return getEntityTexture((EntityMusouFuuin)entity);
	}
	
	protected ResourceLocation getEntityTexture(EntityMusouFuuin musouFuuin) {
		return musouFuuinTexture;
	}
}
