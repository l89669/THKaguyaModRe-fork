package thKaguyaMod.client.render;

import java.util.Random;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thKaguyaMod.entity.EntityShotMaterial;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderShotMaterial extends Render
{
	
	//弾の素の描画
	
	private Random random = new Random();

    public RenderShotMaterial()
    {
    }
    
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
        renderShotMaterial((EntityShotMaterial)entity, x, y, z, yaw, pitch);
    }


    public void renderShotMaterial(EntityShotMaterial shotMaterial, double x, double y, double z, float yaw, float pitch)
    {
        GL11.glPushMatrix();
        bindEntityTexture(shotMaterial);
        GL11.glTranslatef((float)x, (float)y, (float)z);
    	GL11.glDisable(GL11.GL_LIGHTING);
        float sizeRate = 1.0F;
        GL11.glScalef(sizeRate, sizeRate, sizeRate);
        Tessellator tessellator = Tessellator.instance;
    	int color2 = 0;
    	float rvl = 0.0F;
    	float rul = 0.0F;
    	float rvr = 1.00F;
    	float rur = 1.00F;
		GL11.glRotatef(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
    	GL11.glRotatef( renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
    	
    	//表面
    	tessellator.startDrawingQuads();
    	tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV( -0.2F,  0.2F, 0.0D, rvl, rul);
        tessellator.addVertexWithUV(  0.2F,  0.2F, 0.0D, rvr, rul);
        tessellator.addVertexWithUV(  0.2F, -0.2F, 0.0D, rvr, rur);
        tessellator.addVertexWithUV( -0.2F, -0.2F, 0.0D, rvl, rur);
        tessellator.draw();

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
    


 
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return getEntityTexture((EntityShotMaterial)entity);
	}
	
    protected ResourceLocation getEntityTexture(EntityShotMaterial shotMaterial)
    {
    	return new ResourceLocation("thkaguyamod", "textures/items/material/ShotMaterial.png");
    }
}