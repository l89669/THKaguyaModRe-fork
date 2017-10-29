package thKaguyaMod.client.render.effect;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thKaguyaMod.entity.effect.EntitySpellCardCircle;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSpellCardCircle extends Render
{
	
	//使い魔の描画
	private static final ResourceLocation circleTexture = new ResourceLocation("thkaguyamod", "textures/mob/Familiar.png");
	protected float colorR[] = { 255F, 255F, 255F,   0F,   0F,   0F, 255F};
	protected float colorG[] = {   0F, 165F, 255F, 255F,   0F, 255F,   0F};
	protected float colorB[] = {   0F,   0F,   0F,   0F, 255F, 255F, 255F};

    public RenderSpellCardCircle()
    {
    }
    
	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch) {
		renderSpellCardCircle((EntitySpellCardCircle)entity, x, y, z, yaw, pitch);
	}
    
    public void renderSpellCardCircle(EntitySpellCardCircle circle, double x, double y, double z, float yaw, float pitch)
    {
    	//形状IDがマイナスなら描画しない
    	/*if(circle.getCircleType() < 0)
    	{
    		return;
    	}*/
        GL11.glPushMatrix();
        bindEntityTexture(circle);
        GL11.glTranslatef((float)x, (float)y, (float)z);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_CULL_FACE);//両面描画
    	GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
    	float size = circle.getCircleSize() * 6.0F;
        GL11.glScalef(size, size, size);
        Tessellator tessellator = Tessellator.instance;
    	
    	//int color = circle.getShotColor();

    	//int pattern = circle.getAnimationCount() % 2;
        float umin = 0.0F;
        float umax = 1.0F;
        float vmin = 0.0F;
        float vmax = 1.0F;

        boolean rainbow = false;
    	int color = circle.getCircleType();
    	if(color >= 16)
    	{   
    		float angle = MathHelper.sin(2F * (float)circle.getAnimationCount() / 180F * (float)Math.PI) * 40F;
    		GL11.glRotatef(180F - renderManager.playerViewY + angle, 0.0F, 1.0F, 0.0F);
        	GL11.glRotatef(-renderManager.playerViewX - angle, 1.0F, 0.0F, 0.0F);
        	GL11.glRotatef(circle.getAnimationCount() * 5F, 0.0F, 0.0F, 1.0F);
    		color %= 16;
    	}
    	else
    	{
    		GL11.glRotatef(90F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(circle.getAnimationCount() * 5F, 0.0F, 0.0F, 1.0F);
    	}
    	
    	if(color == 9)
    	{
    		rainbow = true;
    	}
    	else
    	{
    		color %= 8;
    	}
    	
    	tessellator.startDrawingQuads();
    	if(rainbow)
    	{
    		float rainbowTime = circle.ticksExisted / 3.0F;
    		tessellator.setColorRGBA_F(MathHelper.sin(rainbowTime), MathHelper.cos(rainbowTime), -MathHelper.sin(rainbowTime) * 2F, 1.0F);
    	}
    	else
    	{
    		tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 1.0F);
    	}
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(-0.6F, -0.6F, 0.0D, umin, vmax);
        tessellator.addVertexWithUV( 0.6F, -0.6F, 0.0D, umax, vmax);
        tessellator.addVertexWithUV( 0.6F,  0.6F, 0.0D, umax, vmin);
        tessellator.addVertexWithUV(-0.6F,  0.6F, 0.0D, umin, vmin);
        tessellator.draw();
    	GL11.glDisable(GL11.GL_BLEND);
    	GL11.glEnable(GL11.GL_LIGHTING);
    	GL11.glEnable(GL11.GL_CULL_FACE);//両面描画
        GL11.glPopMatrix();
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return getEntityTexture((EntitySpellCardCircle)entity);
	}
	
	protected ResourceLocation getEntityTexture(EntitySpellCardCircle circle) {
		return circleTexture;
	}
}