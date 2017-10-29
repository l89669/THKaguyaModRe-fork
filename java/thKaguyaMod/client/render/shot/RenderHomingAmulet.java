package thKaguyaMod.client.render.shot;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thKaguyaMod.entity.shot.EntityHomingAmulet;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHomingAmulet extends Render
{
	
	//ホーミングアミュレットの描画
	private static final ResourceLocation homingTexture = new ResourceLocation("thkaguyamod", "textures/shot/HomingAmulet.png");
	private static final ResourceLocation diffusionTexture = new ResourceLocation("thkaguyamod", "textures/shot/DiffusionAmulet.png");
	
	float colorR[] = { 255F,  25F,   0F, 255F, 255F,   0F, 255F, 255F};
    float colorG[] = {  25F,  25F, 255F, 255F,   0F, 255F, 165F, 255F};
    float colorB[] = {  25F, 255F,   0F,   0F, 255F, 255F,   0F, 255F};

    public RenderHomingAmulet()
    {
    }
    
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
        doRenderHomingAmulet((EntityHomingAmulet)entity, x, y, z, yaw, pitch);
    }

    public void doRenderHomingAmulet(EntityHomingAmulet homingAmulet, double x, double y, double z, float yaw, float pitch)
    {
        GL11.glPushMatrix();
        
        GL11.glTranslatef((float)x, (float)y, (float)z);
    	GL11.glDisable(GL11.GL_LIGHTING);
    	//GL11.glEnable(GL11.GL_NORMALIZE);
    	GL11.glEnable(GL11.GL_BLEND);
    	GL11.glDisable(GL11.GL_CULL_FACE);//両面描画
    	GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
    	
    	int color = homingAmulet.getShotColor() / 2;
    	
    	this.bindTexture(getEntityTexture(homingAmulet));
    	
        float size = 0.5F;
    	if(homingAmulet.getShotColor() % 2 == 1)
    	{
    		size = 1.2F;
    	}
        GL11.glScalef(size, size, size);
        
        Tessellator tessellator = Tessellator.instance;
    	float xLength = 0.5F;
    	double zLength = 0.5F;
    	float uMin = 0.0F;
    	float uMax = 0.5F;
    	float vMin = 0.0F;
    	float vMax = 1.0F;
    	
     	GL11.glRotatef(-homingAmulet.rotationPitch, 1.0F, 0.0F, 0.0F);
    	GL11.glRotatef(180F - (float)homingAmulet.getAnimationCount() * 23F, 0.0F, 1.0F, 0.0F);
    	tessellator.startDrawingQuads();
    	tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(-xLength, 0.0F,  zLength, uMin, vMin);
        tessellator.addVertexWithUV( xLength, 0.0F,  zLength, uMax, vMin);
        tessellator.addVertexWithUV( xLength, 0.0F, -zLength, uMax, vMax);
        tessellator.addVertexWithUV(-xLength, 0.0F, -zLength, uMin, vMax);
		tessellator.draw();
    	
    	size *= 1.1F;
    	GL11.glScalef(size, size, size);
    	
    	tessellator.startDrawingQuads();
    	tessellator.setColorRGBA_F(colorR[color] / 255F, colorG[color] / 255F, colorB[color] / 255F, 0.6F);
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(-xLength, 0.0F,  zLength, uMin, vMin);
        tessellator.addVertexWithUV( xLength, 0.0F,  zLength, uMax, vMin);
        tessellator.addVertexWithUV( xLength, 0.0F, -zLength, uMax, vMax);
        tessellator.addVertexWithUV(-xLength, 0.0F, -zLength, uMin, vMax);
		tessellator.draw();
        
        GL11.glDisable(GL11.GL_BLEND);
    	GL11.glEnable(GL11.GL_LIGHTING);
    	GL11.glEnable(GL11.GL_CULL_FACE);//表綿描画
        GL11.glPopMatrix();
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return getEntityTexture((EntityHomingAmulet)entity);
	}
	
    protected ResourceLocation getEntityTexture(EntityHomingAmulet homingAmulet)
    {
    	if(homingAmulet.getShotColor() / 2 == 0)
    	{
    		return homingTexture;
    	}
    	else
    	{
    		return diffusionTexture;
    	}
    }
}
