package thKaguyaMod.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thKaguyaMod.client.model.ModelMarisaBroom;
import thKaguyaMod.entity.item.EntityMarisaBroom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMarisaBroom extends Render
{
    //魔理沙の箒を描画
	private static final ResourceLocation marisaBroomTexture = new ResourceLocation("thkaguyamod", "textures/MagicBloom.png");
    protected ModelBase modelMarisaBroom;

    public RenderMarisaBroom()
    {
        this.shadowSize = 0.5F;
        this.modelMarisaBroom = new ModelMarisaBroom();
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
        this.doRenderMarisaBroom((EntityMarisaBroom)entity, x, y, z, yaw, pitch);
    }

    public void doRenderMarisaBroom(EntityMarisaBroom marisaBroom, double x, double y, double z, float yaw, float pitch)
    {
        GL11.glPushMatrix();
        this.bindTexture(getEntityTexture(marisaBroom));
        GL11.glTranslatef((float)x, (float)y, (float)z);
        GL11.glRotatef(180.0F - yaw, 0.0F, 1.0F, 0.0F);
        float f2 = (float)marisaBroom.getTimeSinceHit() - pitch;
        float f3 = marisaBroom.getDamageTaken() - pitch;

        if (f3 < 0.0F)
        {
            f3 = 0.0F;
        }

        if (f2 > 0.0F)
        {
            GL11.glRotatef(MathHelper.sin(f2) * f2 * f3 / 10.0F * (float)marisaBroom.getForwardDirection(), 0.0F, 0.0F, 1.0F);
        }

        float size = 0.75F;
        GL11.glScalef(size, size, size);
        GL11.glScalef(1.0F / size, 1.0F / size, 1.0F / size);
        GL11.glScalef(-1.0F, -1.0F, 1.0F);
        Tessellator tessellator = Tessellator.instance;
        GL11.glRotatef(-90F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-marisaBroom.rotationPitch, 1.0F, 0.0F, 0.0F);
        float rodWidth = 0.05F;
        float rodWidth2 = 0.3F;
        float rodWidth3 = 0.4F;
        float rodWidth4 = 0.15F;
        double rodLength = 2.8D;
        double rodLengthHalf = rodLength / 2.0D;
    	
        for(int i = 0; i < 4; i++)
        {
	    	tessellator.startDrawingQuads();
	    	//tessellator.setColorRGBA_F(colorR[randColor], colorG[randColor], colorB[randColor], 0.3F);
	        tessellator.setNormal(0.0F, 1.0F, 0.0F);
	        tessellator.addVertexWithUV( rodWidth, -rodWidth,  rodLengthHalf, 0F, 2F / 32F);
	        tessellator.addVertexWithUV( rodWidth, -rodWidth, -rodLengthHalf, 1F, 2F / 32F);
	        tessellator.addVertexWithUV( rodWidth,  rodWidth, -rodLengthHalf, 1F, 0F);
	        tessellator.addVertexWithUV( rodWidth,  rodWidth,  rodLengthHalf, 0F, 0F);
	        tessellator.draw();
	        GL11.glRotatef(90F, 0.0F, 0.0F, 1.0F);
        }
    	tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV( -rodWidth, -rodWidth,  rodLengthHalf, 0F      , 2F / 32F);
        tessellator.addVertexWithUV(  rodWidth, -rodWidth,  rodLengthHalf, 2F / 64F, 2F / 32F);
        tessellator.addVertexWithUV(  rodWidth,  rodWidth,  rodLengthHalf, 2F / 64F, 4F / 32F);
        tessellator.addVertexWithUV( -rodWidth,  rodWidth,  rodLengthHalf, 0F      , 4F / 32F);
        tessellator.draw();
        
    	tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV( -rodWidth4, -rodWidth4,  -rodLengthHalf+0.3D, 0.25F, 0.5F);
        tessellator.addVertexWithUV(  rodWidth4, -rodWidth4,  -rodLengthHalf+0.3D, 0.50F, 0.5F);
        tessellator.addVertexWithUV(  rodWidth4,  rodWidth4,  -rodLengthHalf+0.3D, 0.50F, 1.0F);
        tessellator.addVertexWithUV( -rodWidth4,  rodWidth4,  -rodLengthHalf+0.3D, 0.25F, 1.0F);
        tessellator.draw();
        
    	tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV( -rodWidth3, -rodWidth3,  -rodLengthHalf-1.3D, 0.25F, 0.5F);
        tessellator.addVertexWithUV( -rodWidth3,  rodWidth3,  -rodLengthHalf-1.3D, 0.25F, 1.0F);
        tessellator.addVertexWithUV(  rodWidth3,  rodWidth3,  -rodLengthHalf-1.3D, 0.50F, 1.0F);
        tessellator.addVertexWithUV(  rodWidth3, -rodWidth3,  -rodLengthHalf-1.3D, 0.50F, 0.5F);
        
        
        tessellator.draw();
        
        for(int i = 0; i < 4; i++)
        {
        	tessellator.startDrawingQuads();
	        tessellator.setNormal(0.0F, 1.0F, 0.0F);
	        tessellator.addVertexWithUV( rodWidth4,   -rodWidth4, -rodLengthHalf+0.3D      , 0.5D, 0.5F);
	        tessellator.addVertexWithUV( rodWidth,  -rodWidth, -rodLengthHalf          , 0.5F, 3F / 32F);
	        tessellator.addVertexWithUV( rodWidth,   rodWidth, -rodLengthHalf          , 1.0F, 3F / 32F);
	        tessellator.addVertexWithUV( rodWidth4,    rodWidth4, -rodLengthHalf+0.3D      , 1.0D, 0.5F);
	        tessellator.draw();
	        
	    	tessellator.startDrawingQuads();
	        tessellator.setNormal(0.0F, 1.0F, 0.0F);
	        tessellator.addVertexWithUV( rodWidth,   -rodWidth, -rodLengthHalf      , 0.5D, 0.5F);
	        tessellator.addVertexWithUV( rodWidth2,  -rodWidth2, -rodLengthHalf-0.6D, 0.5F, 3F / 32F);
	        tessellator.addVertexWithUV( rodWidth2,   rodWidth2, -rodLengthHalf-0.6D, 1.0F, 3F / 32F);
	        tessellator.addVertexWithUV( rodWidth,    rodWidth, -rodLengthHalf      , 1.0D, 0.5F);
	        tessellator.draw();
	        
	    	tessellator.startDrawingQuads();
	        tessellator.setNormal(0.0F, 1.0F, 0.0F);
	        tessellator.addVertexWithUV( rodWidth2,  -rodWidth2, -rodLengthHalf-0.6D, 0.5F, 1.0F);
	        tessellator.addVertexWithUV( rodWidth3,  -rodWidth3, -rodLengthHalf-1.3D, 0.5F, 16F / 32F);
	        tessellator.addVertexWithUV( rodWidth3,   rodWidth3, -rodLengthHalf-1.3D, 1.0F, 16F / 32F);
	        tessellator.addVertexWithUV( rodWidth2,   rodWidth2, -rodLengthHalf-0.6D, 1.0F, 1.0F);
	        tessellator.draw();
	        GL11.glRotatef(90F, 0.0F, 0.0F, 1.0F);
        }
        GL11.glPopMatrix();
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return marisaBroomTexture;
	}
}
