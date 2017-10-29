package thKaguyaMod.client.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thKaguyaMod.entity.item.EntitySukima;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSukima extends Render
{
	
	//スキマの描画
	private static final ResourceLocation sukimaTexture = new ResourceLocation("thkaguyamod", "textures/SukimaTexture3.png");

    public RenderSukima()
    {
    }
    
    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
        renderSukima((EntitySukima)entity, x, y, z, yaw, pitch);
    }

    public void renderSukima(EntitySukima sukima, double x, double y, double z, float yaw, float pitch)
    {
    	int colorR[] = { 30,179, 59, 81, 37,123, 40,128, 51,216, 65,222,102,211,237,240,179};
    	int colorG[] = { 27, 49, 81, 48, 49, 47,118,128, 51,129,203,207,133, 84,136,240, 49};
    	int colorB[] = { 27, 44, 26, 26,146,190,151,128, 51,152, 51, 42,219,205, 68,240, 44};

        GL11.glPushMatrix();
        bindEntityTexture(sukima);
        GL11.glTranslatef((float)x, (float)y + 1.0F+ MathHelper.sin((float)sukima.ticksExisted / 20F) * 0.1F, (float)z);
    	GL11.glDisable(GL11.GL_LIGHTING);
    	GL11.glDepthMask(true);
    	
        float f2 = 1.0F;
        GL11.glScalef(f2 / 1.0F, f2 / 1.0F, f2 / 1.0F);
        Tessellator tessellator = Tessellator.instance;
    	int color = sukima.getColor();
    	int color2 = 0;
        float f3 = (float)(color2 * 16) / 64F;
        float f4 = (float)(color2 * 16 + 16) / 64F;
        float f5 = 0.0F;
        float f6 = 1.0F;
        float f7 = 1.0F;
        float f8 = 0.5F;
        float f9 = 0.25F;
    	float xl =  1.0F;
    	float yl = -0.75F;
    	float xr = -1.0F;
    	float yr =  0.75F;
    	int time = sukima.ticksExisted;
    	float openRate = MathHelper.sin((float)time * 15F / 360F * 3.141593F);

    	if(color == 17)
    	{
    		openRate = MathHelper.sin((float)time * 40F / 360F * 3.141593F);
    		color = 16;
    	}
    	if(color >= 16)
    	{
    		xl *= openRate;
    		yl *= openRate;
    		xr *= openRate;
    		yr *= openRate;
    	}
    	
    	color = color % 32;
    	
    	GL11.glRotatef(180F - yaw, 0.0F, 1.0F, 0.0F);
    	GL11.glRotatef(0F, 1.0F, 0.0F, 0.0F);
    	//スキマ本体
    	tessellator.startDrawingQuads();
    	tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(-1.0F, -0.75F, 0.0D, 0.00F, 1.00F);
        tessellator.addVertexWithUV( xl,       yl, 0.0D, 0.75F, 1.00F);
        tessellator.addVertexWithUV( 1.0F,  0.75F, 0.0D, 0.75F, 0.00F);
        tessellator.addVertexWithUV( xr,       yr, 0.0D, 0.00,  0.00F);
        tessellator.draw();
        
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_SRC_ALPHA);
    	tessellator.startDrawingQuads();
    	tessellator.setColorRGBA_F(0F, 0F, 0F, 0.7F);
    	tessellator.addVertexWithUV(   xl,     yl, -0.0009D, 0.75F, 1.00F);
        tessellator.addVertexWithUV(-1.0F, -0.75F, -0.0009D, 0.00F, 1.00F);
        tessellator.addVertexWithUV(   xr,     yr, -0.0009D, 0.00F, 0.00F);
        tessellator.addVertexWithUV( 1.0F,  0.75F, -0.0009D, 0.75F, 0.00F);
        tessellator.draw();
        GL11.glDisable(GL11.GL_BLEND);
    	//スキマのリボン　２つ
    	//右上　表
    	GL11.glRotatef(30F, 0.0F, 0.0F, 1.0F);//Z軸（奥行軸）で30度回転
    	tessellator.startDrawingQuads();
    	tessellator.setColorRGBA_F((float)colorR[color]/255F, (float)colorG[color]/255F, (float)colorB[color]/255F, 0.7F);
    	tessellator.setNormal(0.0F, 1.0F, 0.0F);
    	tessellator.addVertexWithUV( 1.00F,  -0.2F, 0.001D, 0.75F, 1.00F);
        tessellator.addVertexWithUV( 1.25F,  -0.2F, 0.001D, 1.00F, 1.00F);
        tessellator.addVertexWithUV( 1.25F,   0.3F, 0.001D, 1.00F, 0.00F);
        tessellator.addVertexWithUV( 1.00F,   0.3F, 0.001D, 0.75F, 0.00F);
        tessellator.draw();

    	//左下　表
    	tessellator.startDrawingQuads();
    	tessellator.setColorRGBA_F((float)colorR[color]/255F, (float)colorG[color]/255F, (float)colorB[color]/255F, 0.7F);
    	tessellator.setNormal(0.0F, 1.0F, 0.0F);
    	tessellator.addVertexWithUV( -1.30F,  -0.3F, 0.001D, 0.75F, 1.00F);
        tessellator.addVertexWithUV( -1.05F,  -0.3F, 0.001D, 1.00F, 1.00F);
        tessellator.addVertexWithUV( -1.05F,   0.2F, 0.001D, 1.00F, 0.00F);
        tessellator.addVertexWithUV( -1.30F,   0.2F, 0.001D, 0.75F, 0.00F);
        tessellator.draw();
        
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_SRC_ALPHA);
    	//右上　裏
    	tessellator.startDrawingQuads();
    	tessellator.setColorRGBA_F(0F, 0F, 0F, 0.7F);
    	tessellator.addVertexWithUV( 1.00F,   0.3F, -0.001D, 0.75F, 0.00F);
        tessellator.addVertexWithUV( 1.25F,   0.3F, -0.001D, 1.00F, 0.00F);
        tessellator.addVertexWithUV( 1.25F,  -0.2F, -0.001D, 1.00F, 1.00F);
        tessellator.addVertexWithUV( 1.00F,  -0.2F, -0.001D, 0.75F, 1.00F);
        tessellator.draw();
    	//左下　裏
    	tessellator.startDrawingQuads();
    	tessellator.setColorRGBA_F(0F, 0F, 0F, 0.7F);
    	tessellator.addVertexWithUV( -1.30F,   0.2F, -0.001D, 0.75F, 0.00F);
        tessellator.addVertexWithUV( -1.05F,   0.2F, -0.001D, 1.00F, 0.00F);
        tessellator.addVertexWithUV( -1.05F,  -0.3F, -0.001D, 1.00F, 1.00F);
        tessellator.addVertexWithUV( -1.30F,  -0.3F, -0.001D, 0.75F, 1.00F);
        tessellator.draw();
        
        GL11.glDisable(GL11.GL_BLEND);
        
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return sukimaTexture;
	}
}
