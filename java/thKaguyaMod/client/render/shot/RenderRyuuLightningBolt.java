package thKaguyaMod.client.render.shot;

import java.util.Random;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thKaguyaMod.client.THKaguyaRenderLib;
import thKaguyaMod.entity.shot.EntityDragonNeckJewel;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderRyuuLightningBolt extends Render
{
	//龍の玉の描画
	//ほとんど雪玉と同じ　なのでよくわからん
	private static final ResourceLocation dragonBulletTexture = new ResourceLocation("thkaguyamod", "textures/thKaguyaTerrain.png");
	private Random random = new Random();

    public RenderRyuuLightningBolt()
    {
    }
    
    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
        renderDragonBullet((EntityDragonNeckJewel)entity, x, y, z, yaw, pitch);
    }

    public void renderDragonBullet(EntityDragonNeckJewel dragonBullet, double x, double y, double z, float yaw, float pitch)
    {
        GL11.glPushMatrix();
        bindEntityTexture(dragonBullet);
        GL11.glTranslatef((float)x, (float)y, (float)z);
        GL11.glEnable(32826 /*GL_RESCALE_NORMAL_EXT*/);
        float f2 = 1.0F;
        GL11.glScalef(f2 / 1.0F, f2 / 1.0F, f2 / 1.0F);
        Tessellator tessellator = Tessellator.instance;
    	int color = 1+16;
    	if(random.nextInt(3)==0)
    	{	
    		color = random.nextInt(5)+16;
    	}
        float f3 = (float)((color % 16) * 16 + 0) / 256F;
        float f4 = (float)((color % 16) * 16 + 16) / 256F;
        float f5 = (float)((color / 16) * 16 + 0) / 256F;
        float f6 = (float)((color / 16) * 16 + 16) / 256F;
        float f7 = 1.0F;
        float f8 = 0.5F;
        float f9 = 0.25F;
        // プレイヤーに常に正面を向けるようにする
        THKaguyaRenderLib.getGLRotatefByTherdPersonView(renderManager);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(0.0F - f8, 0.0F - f9, 0.0D, f3, f6);
        tessellator.addVertexWithUV(f7 - f8, 0.0F - f9, 0.0D, f4, f6);
        tessellator.addVertexWithUV(f7 - f8, 1.0F - f9, 0.0D, f4, f5);
        tessellator.addVertexWithUV(0.0F - f8, 1.0F - f9, 0.0D, f3, f5);
        tessellator.draw();
        GL11.glDisable(32826 /*GL_RESCALE_NORMAL_EXT*/);
        GL11.glPopMatrix();
    }
    
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return dragonBulletTexture;
	}
}
