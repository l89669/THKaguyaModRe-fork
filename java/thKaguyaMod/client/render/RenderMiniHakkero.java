package thKaguyaMod.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thKaguyaMod.client.model.ModelMiniHakkero;
import thKaguyaMod.client.model.ModelMiniHakkero2;
import thKaguyaMod.entity.item.EntityMiniHakkero;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMiniHakkero extends Render
{
	
   /*
   ミニ八卦炉の描画
   */
	private static final ResourceLocation miniHakkeroTexture = new ResourceLocation("thkaguyamod", "textures/MiniHakkeroTexture.png");
    protected ModelBase modelMiniHakkero,modelMiniHakkero2;

    public RenderMiniHakkero()
    {
        shadowSize = 0.5F;//多分影のサイズ
        modelMiniHakkero = new ModelMiniHakkero();
        modelMiniHakkero2 = new ModelMiniHakkero2();
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
        renderMiniHakkero ((EntityMiniHakkero)entity, x, y, z, yaw, pitch);
        renderMiniHakkero2((EntityMiniHakkero)entity, x, y, z, yaw, pitch);
    }
    
    public void renderMiniHakkero(EntityMiniHakkero miniHakkero, double x, double y, double z, float yaw, float pitch)
    {
        GL11.glPushMatrix();
        this.bindTexture(getEntityTexture(miniHakkero));
        GL11.glTranslatef((float)x, (float)y, (float)z);
       GL11.glRotatef(miniHakkero.rotationPitch, -MathHelper.sin((yaw - 90F)/180F * 3.141593F), 0.0F, MathHelper.cos((yaw - 90F)/180F * 3.141593F));
       GL11.glRotatef(180F - yaw, 0.0F, 1.0F, 0.0F);

        GL11.glScalef(0.5F, 0.5F, 0.5F);//倍率　縦方向 高さ　幅
       modelMiniHakkero.render(miniHakkero, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
       GL11.glDisable(GL11.GL_LIGHTING);
       GL11.glEnable(GL11.GL_BLEND);
       GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
       Tessellator tessellator = Tessellator.instance;
       float constR[] = { 255F, 255F, 255F,   0F,   0F,  76F, 128F};
       float constG[] = {   0F, 165F, 255F, 128F,   0F,   0F,   0F};
       float constB[] = {   0F,   0F,   0F,   0F, 255F, 130F, 128F};
       float u_min  = 0F;
       float v_min  = 0F;
       float u_max  = 1F / 2F;
       float v_max  = 1F / 4F;
       
       float angle = miniHakkero.getCircleAngle();
       float pi18 = (float)Math.PI / 9F;
       int c = miniHakkero.ticksExisted;
       GL11.glRotatef(MathHelper.sin(angle) / 3.141593F * 180F, 0.0F, 0.0F, 1.0F);
       for(int i = 0; i < 19; i++)
       {
          //手前リング
          GL11.glRotatef(MathHelper.sin(pi18) / 3.141593F * 180F, 0.0F, 0.0F, 1.0F);
          tessellator.startDrawingQuads();
          tessellator.setColorRGBA_F(constR[c % 7]/255F * 0.5F,constG[c % 7]/255F * 0.5F, constB[c % 7]/255F * 0.5F, 0.6F);
          tessellator.addVertexWithUV(  0.77F,  4.5F, -6.0D, u_min, v_min);
           tessellator.addVertexWithUV( -0.77F,  4.5F, -6.0D, u_max, v_min);
           tessellator.addVertexWithUV( -0.77F,   4.5F,  -4.0, u_max, v_max);
           tessellator.addVertexWithUV(  0.77F,   4.5F,  -4.0D, u_min, v_max);
           tessellator.draw();
          tessellator.startDrawingQuads();
          tessellator.setColorRGBA_F(constR[c % 7]/255F * 0.5F,constG[c % 7]/255F * 0.5F, constB[c % 7]/255F * 0.5F, 0.6F);
           tessellator.addVertexWithUV( -0.77F,  4.5F, -6.0D, u_min, v_min);
           tessellator.addVertexWithUV(  0.77F,  4.5F, -6.0D, u_max, v_min);
           tessellator.addVertexWithUV(  0.77F,   4.5F,  -4.0D, u_max, v_max);
           tessellator.addVertexWithUV( -0.77F,   4.5F,  -4.0D, u_min, v_max);
          tessellator.draw();
          //奥リング
          tessellator.startDrawingQuads();
          tessellator.setColorRGBA_F(constR[(c+3) % 7]/255F * 0.5F,constG[(c+3) % 7]/255F * 0.5F, constB[(c+3) % 7]/255F * 0.5F, 0.6F);
          tessellator.addVertexWithUV(  1.11F,  6.5F, -12.0D, u_min, v_min);
           tessellator.addVertexWithUV( -1.11F,  6.5F, -12.0D, u_max, v_min);
           tessellator.addVertexWithUV( -1.11F,   6.5F, -10.0D, u_max, v_max);
           tessellator.addVertexWithUV(  1.11F,   6.5F, -10.0D, u_min, v_max);
           tessellator.draw();
          tessellator.startDrawingQuads();
          tessellator.setColorRGBA_F(constR[(c+3) % 7]/255F * 0.5F,constG[(c+3) % 7]/255F * 0.5F, constB[(c+3) % 7]/255F * 0.5F, 0.6F);
           tessellator.addVertexWithUV( -1.11F,  6.5F, -12.0D, u_min, v_min);
           tessellator.addVertexWithUV(  1.11F,  6.5F, -12.0D, u_max, v_min);
           tessellator.addVertexWithUV(  1.11F,   6.5F, -10.0D, u_max, v_max);
           tessellator.addVertexWithUV( -1.11F,   6.5F, -10.0D, u_min, v_max);
           tessellator.draw();
          angle += pi18;
       }
       
        
       GL11.glDisable(GL11.GL_BLEND);
       GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
    
    
    public void renderMiniHakkero2(EntityMiniHakkero miniHakkero, double x, double y, double z, float yaw, float pitch)
    {
       GL11.glPushMatrix();
       GL11.glTranslatef((float)x, (float)y, (float)z);
       GL11.glRotatef(miniHakkero.rotationPitch, -MathHelper.sin((miniHakkero.rotationYaw - 90F) / 180F * 3.141593F), 0.0F, MathHelper.cos((yaw - 90F) / 180F * 3.141593F));
       GL11.glRotatef(180F - yaw, 0.0F, 1.0F, 0.0F);
        
        //ずらした部分はここの倍率を変えて補う
        GL11.glScalef(0.501F, 0.501F, 0.501F);//倍率　縦方向 高さ　幅
       
    	modelMiniHakkero2.render(miniHakkero, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
       GL11.glDisable(GL11.GL_LIGHTING);
       GL11.glEnable(GL11.GL_BLEND);
       GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
       Tessellator tessellator = Tessellator.instance;
       
        GL11.glPopMatrix();
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return miniHakkeroTexture;
	}
}
