package thKaguyaMod.client.render.effect;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thKaguyaMod.client.THKaguyaRenderLib;
import thKaguyaMod.entity.effect.EntityMiracleCircle;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMiracleCircle extends Render
{
	
	//早苗の詠唱エフェクトの星の描画
	private static final ResourceLocation miracleCircleTexture = new ResourceLocation("thkaguyamod", "textures/MiracleCircle.png");

    public RenderMiracleCircle()
    {
    }
    
    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
        doRenderMiracleCircle((EntityMiracleCircle)entity, x, y, z, yaw, pitch);
    }

    public void doRenderMiracleCircle(EntityMiracleCircle miracleCircle, double x, double y, double z, float yaw, float pitch)
    {
    	//紫、黄、赤、青、緑
    	float colorR[] = {0.7F, 0.7F, 0.9F, 0.3F, 0.2F};
    	float colorG[] = {0.1F, 0.7F, 0.1F, 0.3F, 0.9F};
    	float colorB[] = {0.9F, 0.1F, 0.2F, 0.9F, 0.2F};
    	int color = miracleCircle.getColor();
    	
        GL11.glPushMatrix();
        this.bindTexture(getEntityTexture(miracleCircle));
        GL11.glTranslatef((float)x, (float)y, (float)z);
    	GL11.glDisable(GL11.GL_LIGHTING);
    	//GL11.glEnable(GL11.GL_NORMALIZE);
    	GL11.glEnable(GL11.GL_BLEND);
    	GL11.glDisable(GL11.GL_CULL_FACE);//両面描画
    	GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
        GL11.glScalef(1.0F, 1.0F, 1.0F);
    	
        Tessellator tessellator = Tessellator.instance;
        // プレイヤーに常に正面を向けるようにする
        THKaguyaRenderLib.getGLRotatefByTherdPersonView(renderManager);
    	GL11.glRotatef(miracleCircle.turnAngle, 0.0F, 0.0F, 1.0F);
    	
    	int i, j;
    	float umin = 0.5F;
    	float umax = 0.625F;
    	float vmin = 0.0F;
    	float vmax = 1.0F;
    	float angle = -108F;
    	float length = 3.0F;
    	float width = 0.10F;
    	float py =  length / (2.0F * MathHelper.cos(0.3141593F) );
    	float px = 0F;
    	float nextpx = px + MathHelper.cos(angle / 180F * 3.141593F) * length;
    	float nextpy = py + MathHelper.sin(angle / 180F * 3.141593F) * length;
    	float widthX;
    	float widthY;
    	float nextY;
    	double zbuf;
    	int lastLine = miracleCircle.getNumberOfDrewLine() - 1;
    	int starNum = color;
    	j = color;

    	//最後の１本を除く線を描く
    	for(i = 0; i < lastLine; i++)
    	{
    		zbuf = 0.001D * (double)i;
    		widthX = MathHelper.cos( (angle+90F) / 180F * 3.141593F) * width;
    		widthY = MathHelper.sin( (angle+90F) / 180F * 3.141593F) * width;
    		tessellator.startDrawingQuads();
    		tessellator.setNormal(0.0F, 1.0F, 0.0F);
    		tessellator.setColorRGBA_F(colorR[j], colorG[j], colorB[j], 0.5F);
        	tessellator.addVertexWithUV( px - widthX,  py - widthY,       zbuf, umin, vmax);
        	tessellator.addVertexWithUV( px + widthX,  py + widthY,       zbuf, umax, vmax);
        	tessellator.addVertexWithUV( nextpx + widthX,  nextpy + widthY, zbuf, umax, vmin);
        	tessellator.addVertexWithUV( nextpx - widthX,  nextpy - widthY, zbuf, umin, vmin);
        	tessellator.draw();
    		angle += 144F;
    		px = nextpx;
    		py = nextpy;
    		nextpx = px + MathHelper.cos(angle / 180F * 3.141593F) * length;
    		nextpy = py + MathHelper.sin(angle / 180F * 3.141593F) * length;
    	}
    	//最後の1本
    		float lastLineLength = length * (float)miracleCircle.getLastLineTime() / 10F;
    	    nextpx = px + MathHelper.cos(angle / 180F * 3.141593F) * lastLineLength;
    		nextpy = py + MathHelper.sin(angle / 180F * 3.141593F) * lastLineLength;
    	    zbuf = 0.001D * (double)i;
    		widthX = MathHelper.cos( (angle+90F) / 180F * 3.141593F) * width;
    		widthY = MathHelper.sin( (angle+90F) / 180F * 3.141593F) * width;
    		tessellator.startDrawingQuads();
    		tessellator.setNormal(0.0F, 1.0F, 0.0F);
    		tessellator.setColorRGBA_F(colorR[j], colorG[j], colorB[j], 0.5F);
        	tessellator.addVertexWithUV( px - widthX,  py - widthY,       zbuf, umin, vmax);
        	tessellator.addVertexWithUV( px + widthX,  py + widthY,       zbuf, umax, vmax);
        	tessellator.addVertexWithUV( nextpx + widthX,  nextpy + widthY, zbuf, umax, vmin);
        	tessellator.addVertexWithUV( nextpx - widthX,  nextpy - widthY, zbuf, umin, vmin);
        	tessellator.draw();
    	
        GL11.glDisable(GL11.GL_BLEND);
    	GL11.glEnable(GL11.GL_CULL_FACE);//表綿描画
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
    
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return miracleCircleTexture;
	}
}
