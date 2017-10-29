package thKaguyaMod.client.render;

import java.util.Random;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thKaguyaMod.entity.item.EntityMazinkyoukan;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMazinkyoukan extends Render
{
	
	//魔人経巻の描画
	private static final ResourceLocation mazinkyoukanTexture = new ResourceLocation("thkaguyamod", "textures/Mazinkyoukan_Texture.png");
	private Random random = new Random();

    public RenderMazinkyoukan()
    {
    }
    
    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
        renderMazinkyoukan((EntityMazinkyoukan)entity, x, y, z, yaw, pitch);
    }

    public void renderMazinkyoukan(EntityMazinkyoukan mazinkyoukan, double x, double y, double z, float yaw, float pitch)
    {
        GL11.glPushMatrix();
        this.bindTexture(getEntityTexture(mazinkyoukan));
        GL11.glTranslatef((float)x, (float)y, (float)z);
    	GL11.glDisable(GL11.GL_LIGHTING);
    	//GL11.glEnable(GL11.GL_NORMALIZE);
    	GL11.glDisable(GL11.GL_CULL_FACE);//両面描画
        GL11.glScalef(1.0F, 1.0F, 1.0F);
        Tessellator tessellator = Tessellator.instance;
    	
    	int i;
    	int count = mazinkyoukan.count;
    	float umin;
    	float umax;
    	float vmin;
    	float vmax;
    	float angle = mazinkyoukan.rotationYaw - 57F;
    	float py = -0.3F;
    	float px = -MathHelper.sin(angle / 180F * 3.141593F) * 1.6F ;
    	double pz = Math.cos(angle / 180F * 3.141593F) * 1.6D ;//-length / 2F;
    	float nextpx =  - MathHelper.sin((angle+1F) / 180F * 3.141593F) * 1.6F ;
    	double nextpz = Math.cos((angle+1F) / 180F * 3.141593F) * 1.6D ;
    	float widthY = 0.3F;
    	
    	//取手　左側
    	float widthY2 = widthY+0.1F;
    	float px2min = -MathHelper.sin((angle-2F) / 180F * 3.141593F) * 1.55F;
    	float px2max = -MathHelper.sin((angle+2F) / 180F * 3.141593F) * 1.65F;
    	float pz2min =  MathHelper.cos((angle-2F) / 180F * 3.141593F) * 1.55F;
    	float pz2max =  MathHelper.cos((angle+2F) / 180F * 3.141593F) * 1.65F;
    	tessellator.startDrawingQuads();
    	tessellator.setNormal(0.0F, 1.0F, 0.0F);
    	tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
        tessellator.addVertexWithUV( px2min ,  py - widthY2, pz2min , 0.875F, 1.0F);
        tessellator.addVertexWithUV( px2max ,  py - widthY2, pz2max , 1.0F  , 1.0F);
        tessellator.addVertexWithUV( px2max ,  py + widthY2, pz2max , 1.0F  , 0.5F);
        tessellator.addVertexWithUV( px2min ,  py + widthY2, pz2min , 0.875 , 0.5F);
        tessellator.draw();
    	
    	float color;
    	float colorRev = 3.141593F * 3F / 180F;
    	float ticks = (float)mazinkyoukan.ticksExisted;
    	//文字の書いてある部分
    	GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
    	for(i = 0; i < count; i++)
    	{
    		color = (angle+ticks) * colorRev;
    		umin = (float)(i%63) / 64F;
    		umax = ((float)((i+1)%63)) / 64F;
    		vmin = ((float)(i/63) * 16F) / 32F;
    		vmax = vmin + 0.5F;
    		tessellator.startDrawingQuads();
    		tessellator.setNormal(0.0F, 1.0F, 0.0F);
    		tessellator.setColorRGBA_F(MathHelper.sin(color), MathHelper.cos(color), -MathHelper.sin(color), 0.5F);
        	tessellator.addVertexWithUV( px     ,  py - widthY, pz     , umin, vmax);
        	tessellator.addVertexWithUV( nextpx ,  py - widthY, nextpz , umax, vmax);
        	tessellator.addVertexWithUV( nextpx ,  py + widthY, nextpz , umax, vmin);
        	tessellator.addVertexWithUV( px     ,  py + widthY, pz     , umin, vmin);
        	tessellator.draw();
    		angle += 1.0F;
    		px = nextpx;
    		pz = nextpz;
    		nextpx = -MathHelper.sin((angle+1F) / 180F * 3.141593F) * 1.6F ;
    		nextpz = Math.cos((angle+1F) / 180F * 3.141593F) * 1.6D ;
    	}
    	GL11.glDisable(GL11.GL_BLEND);
    	//取手　右側
    	px2min = -MathHelper.sin((angle-2F) / 180F * 3.141593F) * 1.55F;
    	px2max = -MathHelper.sin((angle+2F) / 180F * 3.141593F) * 1.65F;
    	pz2min =  MathHelper.cos((angle-2F) / 180F * 3.141593F) * 1.55F;
    	pz2max =  MathHelper.cos((angle+2F) / 180F * 3.141593F) * 1.65F;
    	tessellator.startDrawingQuads();
    	tessellator.setNormal(0.0F, 1.0F, 0.0F);
    	tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
        tessellator.addVertexWithUV( px2min ,  py - widthY2, pz2min , 1.0F  , 1.0F);
        tessellator.addVertexWithUV( px2max ,  py - widthY2, pz2max , 0.875F, 1.0F);
        tessellator.addVertexWithUV( px2max ,  py + widthY2, pz2max , 0.875F, 0.5F);
        tessellator.addVertexWithUV( px2min ,  py + widthY2, pz2min , 1.0F  , 0.5F);
        tessellator.draw();
    	
    	GL11.glEnable(GL11.GL_CULL_FACE);//表綿描画
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
    
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return mazinkyoukanTexture;
	}




}
