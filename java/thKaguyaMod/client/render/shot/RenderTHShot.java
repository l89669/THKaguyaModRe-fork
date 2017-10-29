package thKaguyaMod.client.render.shot;

import java.util.Random;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thKaguyaMod.THShotLib;
import thKaguyaMod.client.THKaguyaRenderLib;
import thKaguyaMod.client.model.ModelSilverKnife;
import thKaguyaMod.entity.shot.EntityTHShot;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTHShot extends Render
{
	
	//弾の描画
	private static final ResourceLocation resourceLocation_Small = new ResourceLocation("thkaguyamod", "textures/shot/SmallShot.png");
	private static final ResourceLocation resourceLocation_Pearl = new ResourceLocation("thkaguyamod", "textures/shot/PearlShot.png");
	private static final ResourceLocation resourceLocation_Circle = new ResourceLocation("thkaguyamod", "textures/shot/CircleShot.png");
	private static final ResourceLocation resourceLocation_Big = new ResourceLocation("thkaguyamod", "textures/shot/BigShot.png");
	private static final ResourceLocation resourceLocation_Talisman = new ResourceLocation("thkaguyamod", "textures/shot/TalismanShot.png");
	private static final ResourceLocation resourceLocation_Heart = new ResourceLocation("thkaguyamod", "textures/shot/HeartShot.png");
	private static final ResourceLocation resourceLocation_Normal = new ResourceLocation("thkaguyamod", "textures/shot/Shot.png");
	private static final ResourceLocation resourceLocation_Butterfly = new ResourceLocation("thkaguyamod", "textures/shot/ButterflyShot.png");
	private static final ResourceLocation resourceLocation_Star = new ResourceLocation("thkaguyamod", "textures/shot/StarShot.png");
	private static final ResourceLocation resourceLocation_Scale = new ResourceLocation("thkaguyamod", "textures/shot/ScaleShot.png");
	private static final ResourceLocation resourceLocation_Wind = new ResourceLocation("thkaguyamod", "textures/shot/WindShot.png");
	private static final ResourceLocation resourceLocation_Knife_Blue = new ResourceLocation("thkaguyamod", "textures/shot/SilverKnife_Blue.png");
	private static final ResourceLocation resourceLocation_Knife_Red = new ResourceLocation("thkaguyamod", "textures/shot/SilverKnife_Red.png");
	private static final ResourceLocation resourceLocation_Knife_Green = new ResourceLocation("thkaguyamod", "textures/shot/SilverKnife_Green.png");
	private static final ResourceLocation resourceLocation_Knife_Yellow = new ResourceLocation("thkaguyamod", "textures/shot/SilverKnife_Yellow.png");
	private static final ResourceLocation resourceLocation_Knife_Purple = new ResourceLocation("thkaguyamod", "textures/shot/SilverKnife_Purple.png");
	private static final ResourceLocation resourceLocation_Knife_Orange = new ResourceLocation("thkaguyamod", "textures/shot/SilverKnife_Orange.png");
	private static final ResourceLocation resourceLocation_Knife_Aqua = new ResourceLocation("thkaguyamod", "textures/shot/SilverKnife_Aqua.png");
	private static final ResourceLocation resourceLocation_Knife_White= new ResourceLocation("thkaguyamod", "textures/shot/SilverKnife_White.png");
	private static final ResourceLocation resourceLocation_Light= new ResourceLocation("thkaguyamod", "textures/shot/MusouFuuin.png");
	private static final ResourceLocation resourceLocation_Kunai= new ResourceLocation("thkaguyamod", "textures/shot/KunaiShot.png");
	private static final ResourceLocation resourceLocation_Laser= new ResourceLocation("thkaguyamod", "textures/shot/Laser.png");
	private static final ResourceLocation resourceLocation_Phantom = new ResourceLocation("thkaguyamod", "textures/shot/MusouFuuin.png");
	private static final ResourceLocation resourceLocation_Amulet = new ResourceLocation("thkaguyamod", "textures/shot/HomingAmulet.png");
	private static final ResourceLocation resourceLocation_Diffusion = new ResourceLocation("thkaguyamod", "textures/shot/DiffusionAmulet.png");
	
	private static final ResourceLocation resourceLocation_Familiar = new ResourceLocation("thkaguyamod", "textures/mob/Familiar.png");
	private static final ResourceLocation resourceLocation_Arrow = new ResourceLocation("thkaguyamod", "textures/shot/ArrowShot.png");
	
	protected float colorR[] = { 224F/255F,   0F/255F,   0F/255F, 224F/255F, 224F/255F,   0F/255F, 255F/255F, 255F/255F};
	protected float colorG[] = {   0F/255F,   0F/255F, 224F/255F, 224F/255F,   0F/255F, 224F/255F, 128F/255F, 255F/255F};
	protected float colorB[] = {   0F/255F, 224F/255F,   0F/255F,   0F/255F, 224F/255F, 224F/255F,   0F/255F, 255F/255F};

	/** コンストラクタ */
    public RenderTHShot()
    {
    }
    
    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
    	renderTHShot((EntityTHShot)entity, x, y, z, yaw, pitch);
    }

    public void renderTHShot(EntityTHShot thShot, double x, double y, double z, float yaw, float f1)
    {
        GL11.glPushMatrix();
        bindEntityTexture(thShot);
        GL11.glTranslatef((float)x, (float)y, (float)z);
    	GL11.glDisable(GL11.GL_LIGHTING);

    	float size = thShot.getShotSize();
    	float size2;
        int color = thShot.getShotColor();//色
    	int form = thShot.getShotForm();//形状
    	
    	// 弾の遅延描画（光だけ見える）
    	if(thShot.getAnimationCount() < 0)
    	{
    		int delayCount = -thShot.getAnimationCount();
    		if(delayCount > 10)
    		{
    			delayCount = 10;
    		}
    		size2 = delayCount * 0.3F * size;
    		if(size2 > 1.0F)
    		{
    			size2 = 1.0F;
    		}
    		GL11.glEnable(GL11.GL_BLEND);
        	GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
        	// プレイヤーに常に正面を向けるようにする
            THKaguyaRenderLib.getGLRotatefByTherdPersonView(renderManager);
        	GL11.glDepthMask(false);
        	renderLightEffect(color, size2, thShot.getAnimationCount());
        	GL11.glDepthMask(true);
        	GL11.glDisable(GL11.GL_BLEND);
    	}
    	// 弾が実体化している場合
    	else
    	{
    		switch(form)
    		{
    			// 小弾
    			case THShotLib.FORM_SMALL:
    				renderNormalShot(size, color, false);
    				break;
    			// 粒弾
    			case THShotLib.FORM_TINY:
    				renderNormalShot(size, color, false);
    				break;
    			// 中弾
    			case THShotLib.FORM_MEDIUM:
    				renderNormalShot(size, color, false);
    				break;
    			// 真珠弾
    			case THShotLib.FORM_PEARL:
    				renderNormalShot(size, color, false);
    				break;
    			// 輪弾
    			case THShotLib.FORM_CIRCLE:
    				renderNormalShot(size, color, false);
    				break;
    			// 鱗弾
    			case THShotLib.FORM_SCALE:
    				renderScaleShot(size, color, yaw, thShot.rotationPitch, thShot.getAngleZ(), x, y, z);
    				break;
    			// 蝶弾
    			case THShotLib.FORM_BUTTERFLY:
    				renderButterflyShot(size, color, thShot.getAnimationCount(), yaw, thShot.rotationPitch, thShot.getAngleZ());
    				break;
    			// 小星弾
    			case THShotLib.FORM_SMALLSTAR:
    				renderStarShot(size, color, thShot.getAnimationCount());
    				break;
    			// 星弾
    			case THShotLib.FORM_STAR:
    				renderStarShot(size, color, thShot.getAnimationCount());
    				break;
    			// 米弾
    			case THShotLib.FORM_RICE:
    				renderRiceShot(size, yaw, thShot.rotationPitch, 4.0F,  1.20F, -2.0D, 7, 5, color, 0.7F);
    				break;
    			// 結晶弾
    			case THShotLib.FORM_CRYSTAL:
    				renderCrystalShot(size, color, yaw, thShot.rotationPitch);
    				break;
    			// 光弾
    			case THShotLib.FORM_LIGHT:
    				renderLightShot(size, color, thShot.getAnimationCount() % 2);
    				break;
    			// ハート弾
    			case THShotLib.FORM_HEART:
    				renderNormalShot(size, color, false);
    				break;
    			// クナイ弾
    			case THShotLib.FORM_KUNAI:
    				renderKunaiShot(size, color, yaw, thShot.rotationPitch, thShot.getAngleZ());
    				break;
    			// 札弾
    			case THShotLib.FORM_TALISMAN:
    				renderTalismanShot(size, color, yaw, thShot.rotationPitch, thShot.getAngleZ());
    				break;
    			// 大光弾
    			case THShotLib.FORM_BIGLIGHT:
    				renderLightShot(size, color, thShot.getAnimationCount() % 2);
    				break;
    			// 楕円弾
    			case THShotLib.FORM_OVAL:
    				renderOvalShot(size, yaw, thShot.rotationPitch, 2.0F,  1.0F, -1.0D, 7, 5, color, 0.7F);
    				break;
    			// 使い魔
    			case THShotLib.FORM_FAMILIAR:
    				renderFamiliar(size, color, thShot.getAnimationCount());
    				break;
    			// 矢弾
    			case THShotLib.FORM_ARROW:
    				renderArrowShot(size, color, yaw, thShot.rotationPitch, thShot.getAngleZ());
    				break;
    			// 幽霊弾
    			case THShotLib.FORM_PHANTOM:
    				GL11.glEnable(GL11.GL_BLEND);
    				GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
    		    	GL11.glDepthMask(false);
    		    	double time_r = (double)thShot.getAnimationCount() / 180.0D * Math.PI;;
    		    	
    		    	// プレイヤーに常に正面を向けるようにする
    		        THKaguyaRenderLib.getGLRotatefByTherdPersonView(renderManager);
    			
    		    	size =  1.0F;//(300F - (float)thPhantom.ticksExisted) / 300F;
    		    	size2 = size * 0.7F;
    		    	GL11.glScalef(size, size, size);
    		    	
    		    	
    		        renderPhantomPart(color, time_r, 0);
    		    	
    		        GL11.glTranslatef((float)Math.sin(time_r * 5) * 0.2F, 0.1F + (thShot.getAnimationCount() % 20) * 0.06F, 0F);
    		        size = 0.9F * ((20F - (float)(thShot.getAnimationCount() % 20)) / 20F);
    		    	GL11.glScalef(size, size, size);
    		        renderPhantomPart(color, time_r, 0);
    		        
    		        GL11.glTranslatef((float)Math.cos(time_r * 5) * 0.2F, 0.1F + (thShot.getAnimationCount() % 20) * 0.07F, 0F);
    		        size = 0.8F * ((20F - (float)(thShot.getAnimationCount() % 20)) / 20F);
    		    	GL11.glScalef(size, size, size);
    		        renderPhantomPart(color, time_r,0);

    		        GL11.glDisable(GL11.GL_BLEND);
    		        //GL11.glEnable(GL11.GL_LIGHTING);
    		        GL11.glDepthMask(true);
    		        break;
    		    // アミュレット弾    
    			case THShotLib.FORM_AMULET:
    				renderAmuletShot(size, color, thShot.getAnimationCount(), yaw, thShot.rotationPitch, thShot.getAngleZ());
    				break;
    			// ナイフ弾
    			case THShotLib.FORM_KNIFE:
    	    		size2 = size * 3.0F;
    	    		GL11.glScalef(size2, size2, size2);
    	    		ModelBase modelSilverKnife;
    	    		modelSilverKnife = new ModelSilverKnife();
    	    		GL11.glRotatef( yaw,   0F, 1F, 0F);
    	        	GL11.glRotatef(-thShot.rotationPitch, 1F, 0F, 0F);
    	        	GL11.glRotatef(thShot.getAngleZ(), 0F, 0F, 1F);
    	    		modelSilverKnife.render(thShot, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
    	    		break;
    	    	// 風弾
    			case THShotLib.FORM_WIND:
    				renderWindShot(size, color, thShot.getAnimationCount());
    				break;
    			// 大弾
    			case THShotLib.FORM_BIG:
    				renderNormalShot(size, color, true);
    				break;
    			// 気質弾
    			case THShotLib.FORM_KISHITU:
    				renderKishituShot(size, color, thShot.getAnimationCount());
    				break;
    			default:
    		}
    	}

        GL11.glPopMatrix();
        
        //後処理
        GL11.glEnable(GL11.GL_LIGHTING);
    }
    

    
	/** 使い魔の描画 */
	public void renderFamiliar(float size, int color, int count)
	{
		Tessellator tessellator = Tessellator.instance;

			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
			GL11.glDepthMask(false);
		GL11.glScalef(size, size, size);
		// プレイヤーに常に正面を向けるようにする
        THKaguyaRenderLib.getGLRotatefByTherdPersonView(renderManager);
    	GL11.glRotatef((float)count * 3.7F, 0.0F, 0.0F, 1.0F);
		float width = 1.0F;
		float height = 1.0F;
		
		float u1 = 0F; 
        float u2 = 1F;
        float v1 = 0F;
        float v2 = 1F;
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 0.5F);
        tessellator.addVertexWithUV(-width, -height, 0.0D, u1, v2);
        tessellator.addVertexWithUV( width, -height, 0.0D, u2, v2);
        tessellator.addVertexWithUV( width,  height, 0.0D, u2, v1);
        tessellator.addVertexWithUV(-width,  height, 0.0D, u1, v1);
		tessellator.draw();
		GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_BLEND);
	}
	
	/** 円形弾の描画 */
	public void renderNormalShot(float size, int color, boolean blend)
	{
		Tessellator tessellator = Tessellator.instance;
		if(blend)
		{
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
			//GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
			GL11.glDepthMask(false);
		}
		//GL11.glDepthMask(false);
		GL11.glScalef(size, size, size);
		// プレイヤーに常に正面を向けるようにする
        THKaguyaRenderLib.getGLRotatefByTherdPersonView(renderManager);
		float width = 1.0F;
		float height = 1.0F;
		
		float u1 = (float)color / 8F; 
        float u2 = (float)(color + 1) / 8F;
        float v1 = 0F;
        float v2 = 1F;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(-width, -height, 0.0D, u1, v2);
        tessellator.addVertexWithUV( width, -height, 0.0D, u2, v2);
        tessellator.addVertexWithUV( width,  height, 0.0D, u2, v1);
        tessellator.addVertexWithUV(-width,  height, 0.0D, u1, v1);
		tessellator.draw();
		GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_BLEND);
	}
	
	/** 光の弾の描画 */
	public void renderLightShot(float size, int color, int count)
	{
		Tessellator tessellator = Tessellator.instance;
		GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
    	
		GL11.glScalef(size, size, size);
		// プレイヤーに常に正面を向けるようにする
        THKaguyaRenderLib.getGLRotatefByTherdPersonView(renderManager);
		color %= 8;
		float uMin = (float)(count * 32 +  0) / 64F;
	    float uMax = (float)(count * 32 + 32) / 64F;
	    float vMin = 0F;
	    float vMax = 1F;
		float width = 2.0F;

		//double distance = RenderManager.instance.getDistanceToCamera(thShot.posX, thShot.posY, thShot.posZ);
		float alpha = 1.0F;// - (float)distance / 512F;
		/*if(alpha < 0.2F)
		{
			alpha = 0.2F;
		}*/
		GL11.glDepthMask(false);
	    tessellator.startDrawingQuads();
		tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 0.8F * alpha);
	    tessellator.addVertexWithUV(-width, -width, 0.0D, uMin, vMax);
	    tessellator.addVertexWithUV( width, -width, 0.0D, uMax, vMax);
	    tessellator.addVertexWithUV( width,  width, 0.0D, uMax, vMin);
	    tessellator.addVertexWithUV(-width,  width, 0.0D, uMin, vMin);
		tessellator.draw();
		
		
		width = 1.0F;
		tessellator.startDrawingQuads();
		tessellator.setColorRGBA_F(1F, 1F, 1F, 1.0F * alpha);
	    tessellator.addVertexWithUV(-width, -width, 0.01D, uMin, vMax);
	    tessellator.addVertexWithUV( width, -width, 0.01D, uMax, vMax);
	    tessellator.addVertexWithUV( width,  width, 0.01D, uMax, vMin);
	    tessellator.addVertexWithUV(-width,  width, 0.01D, uMin, vMin);
		tessellator.draw();
		
		GL11.glDepthMask(true);
		
		GL11.glDisable(GL11.GL_BLEND);
	}
	
	/** 気質弾の描画 */
	public void renderKishituShot(float size, int color, int count)
	{
		Tessellator tessellator = Tessellator.instance;
		GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
    	GL11.glDepthMask(false);
		GL11.glScalef(size, size, size);
		// プレイヤーに常に正面を向けるようにする
        THKaguyaRenderLib.getGLRotatefByTherdPersonView(renderManager);

		count = count % 2;
		float uMin = (float)(count * 32 +  0) / 64F;
	    float uMax = (float)(count * 32 + 32) / 64F;
	    float vMin = 0F;
	    float vMax = 1F;
		float width = 2.0F;

	    tessellator.startDrawingQuads();
	    tessellator.setNormal(0.0F, 1.0F, 0.0F);
		tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 0.5F);
	    tessellator.addVertexWithUV(-width, -width, 0.0D, uMin, vMax);
	    tessellator.addVertexWithUV( width, -width, 0.0D, uMax, vMax);
	    tessellator.addVertexWithUV( width,  width, 0.0D, uMax, vMin);
	    tessellator.addVertexWithUV(-width,  width, 0.0D, uMin, vMin);
		tessellator.draw();

		width = 1.6F;
		tessellator.startDrawingQuads();
	    tessellator.setNormal(0.0F, 1.0F, 0.0F);
		tessellator.setColorRGBA_F(1F, 1F, 1F, 0.8F);
	    tessellator.addVertexWithUV(-width, -width, 0.01D, uMin, vMax);
	    tessellator.addVertexWithUV( width, -width, 0.01D, uMax, vMax);
	    tessellator.addVertexWithUV( width,  width, 0.01D, uMax, vMin);
	    tessellator.addVertexWithUV(-width,  width, 0.01D, uMin, vMin);
		tessellator.draw();
		GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_BLEND);
	}
	
	/** 光のエフェクト */
	public void renderLightEffect(int color, float size, int count)
	{
		Tessellator tessellator = Tessellator.instance;

		count = count % 2;
		//float size = thShot.getShotSize();
		float u1 = (float)(count * 32 +  0) / 64F;
	    float u2 = (float)(count * 32 + 32) / 64F;
	    float v1 = 0F;
	    float v2 = 1F;
	    float width = 1.0F;
		GL11.glScalef(size, size, size);
	    tessellator.startDrawingQuads();
		tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 0.5F);
	    tessellator.addVertexWithUV(-width, -width, 0.0D, u1, v2);
	    tessellator.addVertexWithUV( width, -width, 0.0D, u2, v2);
	    tessellator.addVertexWithUV( width,  width, 0.0D, u2, v1);
	    tessellator.addVertexWithUV(-width,  width, 0.0D, u1, v1);
		tessellator.draw();
	}
	
	/** 鱗弾の描画 */
	public void renderScaleShot(float size, int color, float yaw, float pitch, float slope, double x, double y, double z)
	{
/*
		Tessellator tessellator = Tessellator.instance;
		GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
    	GL11.glDisable(GL11.GL_CULL_FACE);//両面描画
    	GL11.glDepthMask(false);
		size *= 2.0F;
		GL11.glScalef(size, size, size);
		GL11.glRotatef( yaw,   0F, 1F, 0F);
    	GL11.glRotatef(-pitch, 1F, 0F, 0F);
    	GL11.glRotatef( slope, 0F, 0F, 1F);
		
    	float xLength = 1.00F;
    	double zLength = 1.00F;
    	float uMin = 0.0F;
    	float uMax = 0.5F;
    	float vMin = 0.0F;
    	float vMax = 1.0F;
    	tessellator.startDrawingQuads();  
    	tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 0.8F);
        tessellator.addVertexWithUV(-xLength, 0.1F,  zLength + 0.15F, uMin, vMin);
        tessellator.addVertexWithUV( xLength, 0.1F,  zLength + 0.15F, uMax, vMin);
        tessellator.addVertexWithUV( xLength, 0.1F, -zLength + 0.15F, uMax, vMax);
        tessellator.addVertexWithUV(-xLength, 0.1F, -zLength + 0.15F, uMin, vMax);
		tessellator.draw();
		GL11.glEnable(GL11.GL_CULL_FACE);//表綿描画
		GL11.glPopMatrix();//行列を元に戻す
		
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x, (float)y, (float)z);
		// プレイヤーに常に正面を向けるようにする
        THKaguyaRenderLib.getGLRotatefByTherdPersonView(renderManager);
    	
		float size3 = size * 0.9F;
		GL11.glScalef(size3, size3, size3);
		
		float f3 = 0.5F;
        float u2 = 1.0F;
        float f5 = 0.0F;
        float f6 = 1.0F;
        float f7 = 1.0F;
        float f8 = 0.5F;
        float f9 = 0.25F;
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(0.0F - f8, 0.0F - f9, 0.0D, f3, f6);
        tessellator.addVertexWithUV(f7 - f8, 0.0F - f9, 0.0D, u2, f6);
        tessellator.addVertexWithUV(f7 - f8, 1.0F - f9, 0.0D, u2, f5);
        tessellator.addVertexWithUV(0.0F - f8, 1.0F - f9, 0.0D, f3, f5);
		tessellator.draw();
		GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_BLEND);
*/
		double length = 2.0D;
		float width = 1.0F;
		double zPos = -1.0D;
		int zAngleDivNum = 7;
		int zDivNum = 8;
		float alpha = 0.31F;
		
		
		GL11.glScalef(size, size, size);
		GL11.glRotatef( yaw + 180F,   0F, 1F, 0F);
    	GL11.glRotatef( pitch, 1F, 0F, 0F);
		//renderNormalShot(tessellator, color);
		
    	renderOvalShotPart(size, yaw, pitch ,length * 0.4D, width * 0.5F, -0.85D, zAngleDivNum, 5, 7, 1.0F);
		//renderOvalShot(tessellator, 1.6F, 0.8F, -0.8D, 7, 5, 7, 1.0F);
		GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
    	GL11.glDepthMask(false);
    	renderHarfOvalShotPart(size, yaw, pitch ,length, width * 1.2F, zPos, zAngleDivNum, zDivNum, color, alpha);
    	GL11.glDepthMask(true);
    	GL11.glDisable(GL11.GL_BLEND);
	}
	
	/** 蝶弾 */
	public void renderButterflyShot(float size, int color, int count, float yaw, float pitch, float slope)
	{
		Tessellator tessellator = Tessellator.instance;
		GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
    	GL11.glDepthMask(false);
    	GL11.glDisable(GL11.GL_CULL_FACE);//両面描画
		GL11.glScalef(size, size, size);
		float wingAngle = MathHelper.sin((float)count / 3.0F) * 45F;
		if(pitch > 90F)
		{
			pitch = 90F - pitch % 90F;
		}
		else if(pitch < -90F)
		{
			pitch = -90F - pitch % 90F;
		}
		GL11.glRotatef( yaw,   0F, 1F, 0F);
    	GL11.glRotatef(-pitch, 1F, 0F, 0F);
    	GL11.glRotatef( slope, 0F, 0F, 1F);
		GL11.glRotatef(wingAngle, 0.0F, 0.0F, 1.0F);
		
		color %= 8;
		float minU =  0F / 128F;
        float maxU = 32F / 128F;
        float minV = 0F;
        float maxV = 1F;
        float width = 2.0F;
        float width2 = 1.8F;

	    	tessellator.startDrawingQuads();
    		tessellator.setNormal(0.0F, 1.0F, 0.0F);
			tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 1.0F);
	        tessellator.addVertexWithUV( 0.0F, 0.0F,  width, maxU, minV);
	        tessellator.addVertexWithUV(width, 0.0F,  width, minU, minV);
	        tessellator.addVertexWithUV(width, 0.0F, -width, minU, maxV);
	        tessellator.addVertexWithUV( 0.0F, 0.0F, -width, maxU, maxV);
	        tessellator.draw();	
			tessellator.startDrawingQuads();
    		tessellator.setNormal(0.0F, 1.0F, 0.0F);
			tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 0.4F);
	        tessellator.addVertexWithUV( 0.0F , 0.000F,  width2, maxU, minV);
	        tessellator.addVertexWithUV(width2, 0.000F,  width2, minU, minV);
	        tessellator.addVertexWithUV(width2, 0.000F, -width2, minU, maxV);
	        tessellator.addVertexWithUV( 0.0F , 0.000F, -width2, maxU, maxV);
	        tessellator.draw();	
	        GL11.glRotatef(-wingAngle * 2F, 0.0F, 0.0F, 1.0F);
	        width *= 1.0F;
	        width2 *= 1.0F;

	    	tessellator.startDrawingQuads();
    		tessellator.setNormal(0.0F, 1.0F, 0.0F);
			tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 1.0F);
	    	tessellator.addVertexWithUV( 0.0F, 0.0F,  width, maxU, minV);
	    	tessellator.addVertexWithUV(-width, 0.0F, width, minU, minV);
	    	tessellator.addVertexWithUV(-width, 0.0F, -width, minU, maxV);
	    	tessellator.addVertexWithUV( 0.0F, 0.0F, -width, maxU, maxV);
	    	tessellator.draw();
			tessellator.startDrawingQuads();
    		tessellator.setNormal(0.0F, 1.0F, 0.0F);
			tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 0.4F);
	    	tessellator.addVertexWithUV( 0.0F , 0.000F,  width2, maxU, minV);
	    	tessellator.addVertexWithUV(-width2, 0.000F, width2, minU, minV);
	    	tessellator.addVertexWithUV(-width2, 0.000F, -width2, minU, maxV);
	    	tessellator.addVertexWithUV( 0.0F , 0.000F, -width2, maxU, maxV);
	    	tessellator.draw();
		GL11.glEnable(GL11.GL_CULL_FACE);//表綿描画
		GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_BLEND);
	}

	/** 星弾の描画 */
	public void renderStarShot(float size, int color, int count)
	{
		Tessellator tessellator = Tessellator.instance;
		GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
    	GL11.glDepthMask(false);
		GL11.glScalef(size, size, size);
		// プレイヤーに常に正面を向けるようにする
        THKaguyaRenderLib.getGLRotatefByTherdPersonView(renderManager);
		GL11.glRotatef((float)count * 3.7F, 0.0F, 0.0F, 1.0F);
		float[] topPointX = new float[11];
		float[] topPointY = new float[11];
		float angle = 0F;
		float spanAngle = (float)Math.PI * 0.2F;//36°
		for(int i = 0; i < 10; i+=2)
		{
			topPointX[i] = (float)Math.cos(angle) * 2.0F;
			topPointY[i] = (float)Math.sin(angle) * 2.0F;
			angle += spanAngle;
			topPointX[i+1] = (float)Math.cos(angle) * 1.2F;
			topPointY[i+1] = (float)Math.sin(angle) * 1.2F;
			angle += spanAngle;
		}
		topPointX[10] = topPointX[0];
		topPointY[10] = topPointY[0];
		for(int i = 0; i < 9; i+=2)
		{
			//星の枠部
			tessellator.startDrawingQuads();
			tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 0.7F);
        	tessellator.addVertexWithUV(topPointX[i]  , topPointY[i]  , 0.0D, 0F, 0F);
        	tessellator.addVertexWithUV(topPointX[i+1], topPointY[i+1], 0.0D, 1F, 0F);
        	tessellator.addVertexWithUV(topPointX[i+2], topPointY[i+2], 0.0D, 1F, 1F);
			tessellator.addVertexWithUV(          0.0F,           0.0F, 0.0D, 0F, 1F);
			tessellator.draw();
			//星の中心部　白い
			tessellator.startDrawingQuads();
			tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 0.7F);
			tessellator.addVertexWithUV(topPointX[i]   * 0.7F, topPointY[i]   * 0.7F,  0.01D, 0F, 0F);
			tessellator.addVertexWithUV(topPointX[i+1] * 0.95F, topPointY[i+1] * 0.95F,  0.01D, 1F, 0F);
        	tessellator.addVertexWithUV(topPointX[i+2] * 0.7F, topPointY[i+2] * 0.7F,  0.01D, 1F, 1F);
			tessellator.addVertexWithUV(          0.0F,           0.0F, 0.01D, 0F, 1F);
			tessellator.draw();
		}
		GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_BLEND);
	}
	
	/** 結晶弾の描画 */
	public void renderCrystalShot(float size, int color, float yaw, float pitch)
	{
		Tessellator tessellator = Tessellator.instance;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		GL11.glDepthMask(false);
		GL11.glScalef(size, size, size);
		GL11.glRotatef( yaw,   0F, 1F, 0F);
    	GL11.glRotatef(-pitch, 1F, 0F, 0F);
		float width = 0.96F;
		double length = 4.0D, length_b = 1.0D;
		float width2 = width * 0.8F;
		double length2 = length * 0.8D, length2_b = length * 0.4D;
		int i;
		
		//double distance = RenderManager.instance.getDistanceToCamera(thShot.posX, thShot.posY, thShot.posZ);
		float alpha = 1.0F;// - (float)distance / 512F;
		/*if(alpha < 0.2F)
		{
			alpha = 0.2F;
		}*/
		
		
		for(i = 0; i < 4; i++)
		{
			GL11.glRotatef(90F, 0.0F, 0.0F, 1.0F);
			tessellator.startDrawingQuads();
		    tessellator.setNormal(0.0F, 1.0F, 0.0F);
			tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, alpha);
			tessellator.addVertexWithUV(  0.0F  , width2  , 0.0D  , 1F, 0F);
			tessellator.addVertexWithUV(-width2  , 0.0F   , 0.0D  , 0F, 0F);
			tessellator.addVertexWithUV(  0.0F  , 0.0F   , length2, 0F, 1F);
		    tessellator.addVertexWithUV(  0.0F  , 0.0F   , length2, 0F, 1F);
			tessellator.draw();
			tessellator.startDrawingQuads();
		    tessellator.setNormal(0.0F, 1.0F, 0.0F);
			tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, alpha);
			tessellator.addVertexWithUV( 0.0F   , width2  ,  0.0D  , 1F, 0F);
			tessellator.addVertexWithUV( width2  , 0.0F   ,  0.0D  , 0F, 0F);
			tessellator.addVertexWithUV( 0.0F   , 0.0F   , -length2_b, 0F, 1F);
		    tessellator.addVertexWithUV( 0.0F   , 0.0F   , -length2_b, 0F, 1F);
			tessellator.draw();
		}
		for(i = 0; i < 4; i++)
		{
			GL11.glRotatef(90F, 0.0F, 0.0F, 1.0F);
			tessellator.startDrawingQuads();
		    tessellator.setNormal(0.0F, 1.0F, 0.0F);
			tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], alpha * 0.7F);
			tessellator.addVertexWithUV(  0.0F  , width  , 0.0D  , 1F, 0F);
			tessellator.addVertexWithUV(-width  , 0.0F   , 0.0D  , 0F, 0F);
			tessellator.addVertexWithUV(  0.0F  , 0.0F   , length, 0F, 1F);
		    tessellator.addVertexWithUV(  0.0F  , 0.0F   , length, 0F, 1F);
			tessellator.draw();
			tessellator.startDrawingQuads();
		    tessellator.setNormal(0.0F, 1.0F, 0.0F);
			tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], alpha * 0.7F);
			tessellator.addVertexWithUV( 0.0F   , width  ,  0.0D  , 1F, 0F);
			tessellator.addVertexWithUV( width  , 0.0F   ,  0.0D  , 0F, 0F);
			tessellator.addVertexWithUV( 0.0F   , 0.0F   , -length_b, 0F, 1F);
		    tessellator.addVertexWithUV( 0.0F   , 0.0F   , -length_b, 0F, 1F);
			tessellator.draw();
		}
		GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_BLEND);
	}
	
	/** クナイ弾の描画 */
	public void renderKunaiShot(float size, int color, float yaw, float pitch, float slope)
	{
		Tessellator tessellator = Tessellator.instance;
		GL11.glScalef(size, size, size);
		GL11.glRotatef( yaw,   0F, 1F, 0F);
    	GL11.glRotatef(-pitch, 1F, 0F, 0F);
    	GL11.glRotatef( slope, 0F, 0F, 1F);
		
		float width = 1.0F;//クナイの幅
		float width2 = 0.6F;//取っ手の幅
		float flont = 1.4F;//クナイの先端
		float back  = -0.8F;//クナイの中心
		float back2 = -1.0F;//クナイの後ろ
		float root  = -2.6F;//取っ手の先端
		float height = 0.3F;//クナイの厚さ
		
		width = 0.8F;//クナイの幅
		flont = 1.12F;//クナイの先端
		back  = -0.8F;//クナイの中心
		back2 = -1.0F;//クナイの後ろ
		height = 0.24F;//クナイの厚さ
		//上
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(-width,   0.0F,  back, 0.50F, 1.0F);
        tessellator.addVertexWithUV(  0.0F,   0.0F,  flont, 0.25F, 0.0F);
        tessellator.addVertexWithUV( width,   0.0F,  back, 0.00F, 1.0F);
        tessellator.addVertexWithUV(  0.0F, height,  back, 0.25F, 1.0F);
		tessellator.draw();
		
		//下左
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0F,  0.0F,  flont, 0.25F, 0.0F);
        tessellator.addVertexWithUV(0.0F,  0.0F,  flont, 0.25F, 0.0F);
        tessellator.addVertexWithUV(0.0F, -height,  back, 0.25F, 1.0F);
        tessellator.addVertexWithUV(width,  0.0F,  back, 0.00F, 1.0F);
		tessellator.draw();
		//下右
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0F,  0.0F,  flont, 0.25F, 0.0F);
        tessellator.addVertexWithUV(0.0F,  0.0F,  flont, 0.25F, 0.0F);
        tessellator.addVertexWithUV(-width,  0.0F, back, 0.50F, 1.0F);
        tessellator.addVertexWithUV(0.0F, -height,  back, 0.25F, 1.0F);
		tessellator.draw();
		
		//上左
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0F,  0.0F, back2, 0.25F, 0.0F);
        tessellator.addVertexWithUV(0.0F,  0.0F, back2, 0.25F, 0.0F);
        tessellator.addVertexWithUV(0.0F,  height,  back, 0.25F, 1.0F);
        tessellator.addVertexWithUV(width,  0.0F,  back, 0.00F, 1.0F);
		tessellator.draw();
		//下左
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0F,  0.0F, back2, 0.25F, 0.0F);
        tessellator.addVertexWithUV(0.0F,  0.0F, back2, 0.25F, 0.0F);
        tessellator.addVertexWithUV(width,  0.0F,  back, 0.00F, 1.0F);
        tessellator.addVertexWithUV(0.0F, -height,  back, 0.25F, 1.0F);
		tessellator.draw();
		//上右
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0F,  0.0F, back2, 0.25F, 0.0F);
        tessellator.addVertexWithUV(0.0F,  0.0F, back2, 0.25F, 0.0F);
        tessellator.addVertexWithUV(-width, 0.0F,  back, 0.50F, 1.0F);
        tessellator.addVertexWithUV(0.0F,  height,  back, 0.25F, 1.0F);
		tessellator.draw();
		//下右
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0F,  0.0F, back2, 0.25F, 0.0F);
        tessellator.addVertexWithUV(0.0F,  0.0F, back2, 0.25F, 0.0F);
        tessellator.addVertexWithUV(0.0F, -height,  back, 0.25F, 1.0F);
        tessellator.addVertexWithUV(-width, 0.0F,  back, 0.50F, 1.0F);
		tessellator.draw();
		
		
		width = 1.0F;//クナイの幅
		flont = 1.4F;//クナイの先端
		back  = -0.8F;//クナイの中心
		back2 = -1.0F;//クナイの後ろ
		height = 0.3F;//クナイの厚さ
		
		GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
		//上
        tessellator.startDrawingQuads();
    	tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 1.0F);
        tessellator.addVertexWithUV(-width,   0.0F,  back, 0.50F, 1.0F);
        tessellator.addVertexWithUV(  0.0F,   0.0F,  flont, 0.25F, 0.0F);
        tessellator.addVertexWithUV( width,   0.0F,  back, 0.00F, 1.0F);
        tessellator.addVertexWithUV(  0.0F, height,  back, 0.25F, 1.0F);
		tessellator.draw();
		
		//下左
        tessellator.startDrawingQuads();
    	tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 1.0F);
        tessellator.addVertexWithUV(0.0F,  0.0F,  flont, 0.25F, 0.0F);
        tessellator.addVertexWithUV(0.0F,  0.0F,  flont, 0.25F, 0.0F);
        tessellator.addVertexWithUV(0.0F, -height,  back, 0.25F, 1.0F);
        tessellator.addVertexWithUV(width,  0.0F,  back, 0.00F, 1.0F);
		tessellator.draw();
		//下右
        tessellator.startDrawingQuads();
    	tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 1.0F);
        tessellator.addVertexWithUV(0.0F,  0.0F,  flont, 0.25F, 0.0F);
        tessellator.addVertexWithUV(0.0F,  0.0F,  flont, 0.25F, 0.0F);
        tessellator.addVertexWithUV(-width,  0.0F, back, 0.50F, 1.0F);
        tessellator.addVertexWithUV(0.0F, -height,  back, 0.25F, 1.0F);
		tessellator.draw();
		
		//上左
        tessellator.startDrawingQuads();
    	tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 1.0F);
        tessellator.addVertexWithUV(0.0F,  0.0F, back2, 0.25F, 0.0F);
        tessellator.addVertexWithUV(0.0F,  0.0F, back2, 0.25F, 0.0F);
        tessellator.addVertexWithUV(0.0F,  height,  back, 0.25F, 1.0F);
        tessellator.addVertexWithUV(width,  0.0F,  back, 0.00F, 1.0F);
		tessellator.draw();
		//下左
        tessellator.startDrawingQuads();
    	tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 1.0F);
        tessellator.addVertexWithUV(0.0F,  0.0F, back2, 0.25F, 0.0F);
        tessellator.addVertexWithUV(0.0F,  0.0F, back2, 0.25F, 0.0F);
        tessellator.addVertexWithUV(width,  0.0F,  back, 0.00F, 1.0F);
        tessellator.addVertexWithUV(0.0F, -height,  back, 0.25F, 1.0F);
		tessellator.draw();
		//上右
        tessellator.startDrawingQuads();
    	tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 1.0F);
        tessellator.addVertexWithUV(0.0F,  0.0F, back2, 0.25F, 0.0F);
        tessellator.addVertexWithUV(0.0F,  0.0F, back2, 0.25F, 0.0F);
        tessellator.addVertexWithUV(-width, 0.0F,  back, 0.50F, 1.0F);
        tessellator.addVertexWithUV(0.0F,  height,  back, 0.25F, 1.0F);
		tessellator.draw();
		//下右
        tessellator.startDrawingQuads();
    	tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 1.0F);
        tessellator.addVertexWithUV(0.0F,  0.0F, back2, 0.25F, 0.0F);
        tessellator.addVertexWithUV(0.0F,  0.0F, back2, 0.25F, 0.0F);
        tessellator.addVertexWithUV(0.0F, -height,  back, 0.25F, 1.0F);
        tessellator.addVertexWithUV(-width, 0.0F,  back, 0.50F, 1.0F);
		tessellator.draw();
		
		//取っ手
		GL11.glDisable(GL11.GL_CULL_FACE);//両面描画
        tessellator.startDrawingQuads();
    	tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 1.0F);
        tessellator.addVertexWithUV( width2,  0.0F,  back2, 0.50F, 0.375F);
        tessellator.addVertexWithUV(-width2,  0.0F,  back2, 0.75F, 0.375F);
        tessellator.addVertexWithUV(-width2,  0.0F,  root, 0.75F, 1.0F);
        tessellator.addVertexWithUV( width2,  0.0F,  root, 0.50F, 1.0F);
		tessellator.draw();
		GL11.glEnable(GL11.GL_CULL_FACE);//表面描画
		
		GL11.glDisable(GL11.GL_BLEND);
	}
	
	//矢弾の描画
	public void renderArrowShot(float size, int color, float yaw, float pitch, float slope)
	{
		Tessellator tessellator = Tessellator.instance;
		GL11.glScalef(size, size, size);
		GL11.glRotatef( yaw,   0F, 1F, 0F);
    	GL11.glRotatef(-pitch, 1F, 0F, 0F);
    	GL11.glRotatef( slope, 0F, 0F, 1F);
		
		float width = 1.0F;//クナイの幅
		float width2 = 0.6F;//取っ手の幅
		float flont = 1.4F;//クナイの先端
		float back  = -0.8F;//クナイの中心
		float back2 = -1.0F;//クナイの後ろ
		float root  = -5.8F;//取っ手の先端
		float height = 0.3F;//クナイの厚さ
		
		width = 0.8F;//クナイの幅
		flont = 1.12F;//クナイの先端
		back  = -0.8F;//クナイの中心
		back2 = -1.0F;//クナイの後ろ
		height = 0.24F;//クナイの厚さ
		//上
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(-width,   0.0F,  back, 0.50F, 1.0F);
        tessellator.addVertexWithUV(  0.0F,   0.0F,  flont, 0.25F, 0.0F);
        tessellator.addVertexWithUV( width,   0.0F,  back, 0.00F, 1.0F);
        tessellator.addVertexWithUV(  0.0F, height,  back, 0.25F, 1.0F);
		tessellator.draw();
		
		//下左
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0F,  0.0F,  flont, 0.25F, 0.0F);
        tessellator.addVertexWithUV(0.0F,  0.0F,  flont, 0.25F, 0.0F);
        tessellator.addVertexWithUV(0.0F, -height,  back, 0.25F, 1.0F);
        tessellator.addVertexWithUV(width,  0.0F,  back, 0.00F, 1.0F);
		tessellator.draw();
		//下右
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0F,  0.0F,  flont, 0.25F, 0.0F);
        tessellator.addVertexWithUV(0.0F,  0.0F,  flont, 0.25F, 0.0F);
        tessellator.addVertexWithUV(-width,  0.0F, back, 0.50F, 1.0F);
        tessellator.addVertexWithUV(0.0F, -height,  back, 0.25F, 1.0F);
		tessellator.draw();
		
		//上左
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0F,  0.0F, back2, 0.25F, 0.0F);
        tessellator.addVertexWithUV(0.0F,  0.0F, back2, 0.25F, 0.0F);
        tessellator.addVertexWithUV(0.0F,  height,  back, 0.25F, 1.0F);
        tessellator.addVertexWithUV(width,  0.0F,  back, 0.00F, 1.0F);
		tessellator.draw();
		//下左
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0F,  0.0F, back2, 0.25F, 0.0F);
        tessellator.addVertexWithUV(0.0F,  0.0F, back2, 0.25F, 0.0F);
        tessellator.addVertexWithUV(width,  0.0F,  back, 0.00F, 1.0F);
        tessellator.addVertexWithUV(0.0F, -height,  back, 0.25F, 1.0F);
		tessellator.draw();
		//上右
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0F,  0.0F, back2, 0.25F, 0.0F);
        tessellator.addVertexWithUV(0.0F,  0.0F, back2, 0.25F, 0.0F);
        tessellator.addVertexWithUV(-width, 0.0F,  back, 0.50F, 1.0F);
        tessellator.addVertexWithUV(0.0F,  height,  back, 0.25F, 1.0F);
		tessellator.draw();
		//下右
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0F,  0.0F, back2, 0.25F, 0.0F);
        tessellator.addVertexWithUV(0.0F,  0.0F, back2, 0.25F, 0.0F);
        tessellator.addVertexWithUV(0.0F, -height,  back, 0.25F, 1.0F);
        tessellator.addVertexWithUV(-width, 0.0F,  back, 0.50F, 1.0F);
		tessellator.draw();
		
		width = 1.0F;//クナイの幅
		flont = 1.4F;//クナイの先端
		back  = -0.8F;//クナイの中心
		back2 = -1.0F;//クナイの後ろ
		height = 0.3F;//クナイの厚さ
		
		GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
		//上
        tessellator.startDrawingQuads();
    	tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 1.0F);
        tessellator.addVertexWithUV(-width,   0.0F,  back, 0.50F, 1.0F);
        tessellator.addVertexWithUV(  0.0F,   0.0F,  flont, 0.25F, 0.0F);
        tessellator.addVertexWithUV( width,   0.0F,  back, 0.00F, 1.0F);
        tessellator.addVertexWithUV(  0.0F, height,  back, 0.25F, 1.0F);
		tessellator.draw();
		
		//下左
        tessellator.startDrawingQuads();
    	tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 1.0F);
        tessellator.addVertexWithUV(0.0F,  0.0F,  flont, 0.25F, 0.0F);
        tessellator.addVertexWithUV(0.0F,  0.0F,  flont, 0.25F, 0.0F);
        tessellator.addVertexWithUV(0.0F, -height,  back, 0.25F, 1.0F);
        tessellator.addVertexWithUV(width,  0.0F,  back, 0.00F, 1.0F);
		tessellator.draw();
		//下右
        tessellator.startDrawingQuads();
    	tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 1.0F);
        tessellator.addVertexWithUV(0.0F,  0.0F,  flont, 0.25F, 0.0F);
        tessellator.addVertexWithUV(0.0F,  0.0F,  flont, 0.25F, 0.0F);
        tessellator.addVertexWithUV(-width,  0.0F, back, 0.50F, 1.0F);
        tessellator.addVertexWithUV(0.0F, -height,  back, 0.25F, 1.0F);
		tessellator.draw();
		
		//上左
        tessellator.startDrawingQuads();
    	tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 1.0F);
        tessellator.addVertexWithUV(0.0F,  0.0F, back2, 0.25F, 0.0F);
        tessellator.addVertexWithUV(0.0F,  0.0F, back2, 0.25F, 0.0F);
        tessellator.addVertexWithUV(0.0F,  height,  back, 0.25F, 1.0F);
        tessellator.addVertexWithUV(width,  0.0F,  back, 0.00F, 1.0F);
		tessellator.draw();
		//下左
        tessellator.startDrawingQuads();
    	tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 1.0F);
        tessellator.addVertexWithUV(0.0F,  0.0F, back2, 0.25F, 0.0F);
        tessellator.addVertexWithUV(0.0F,  0.0F, back2, 0.25F, 0.0F);
        tessellator.addVertexWithUV(width,  0.0F,  back, 0.00F, 1.0F);
        tessellator.addVertexWithUV(0.0F, -height,  back, 0.25F, 1.0F);
		tessellator.draw();
		//上右
        tessellator.startDrawingQuads();
    	tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 1.0F);
        tessellator.addVertexWithUV(0.0F,  0.0F, back2, 0.25F, 0.0F);
        tessellator.addVertexWithUV(0.0F,  0.0F, back2, 0.25F, 0.0F);
        tessellator.addVertexWithUV(-width, 0.0F,  back, 0.50F, 1.0F);
        tessellator.addVertexWithUV(0.0F,  height,  back, 0.25F, 1.0F);
		tessellator.draw();
		//下右
        tessellator.startDrawingQuads();
    	tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 1.0F);
        tessellator.addVertexWithUV(0.0F,  0.0F, back2, 0.25F, 0.0F);
        tessellator.addVertexWithUV(0.0F,  0.0F, back2, 0.25F, 0.0F);
        tessellator.addVertexWithUV(0.0F, -height,  back, 0.25F, 1.0F);
        tessellator.addVertexWithUV(-width, 0.0F,  back, 0.50F, 1.0F);
		tessellator.draw();
		
		
		

		
		//取っ手
		GL11.glDisable(GL11.GL_CULL_FACE);//両面描画
        tessellator.startDrawingQuads();
    	tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 1.0F);
        tessellator.addVertexWithUV( width2,  0.0F,  back2, 0.50F, 0.0F);
        tessellator.addVertexWithUV(-width2,  0.0F,  back2, 0.75F, 0.0F);
        tessellator.addVertexWithUV(-width2,  0.0F,  root, 0.75F, 1.0F);
        tessellator.addVertexWithUV( width2,  0.0F,  root, 0.50F, 1.0F);
		tessellator.draw();
		GL11.glEnable(GL11.GL_CULL_FACE);//表面描画
		
		GL11.glDisable(GL11.GL_BLEND);
	}
	
	//札弾の描画
	public void renderTalismanShot(float size, int color, float yaw, float pitch, float slope)
	{
		Tessellator tessellator = Tessellator.instance;
		GL11.glScalef(size, size, size);
		GL11.glRotatef( yaw,   0F, 1F, 0F);
    	GL11.glRotatef(-pitch, 1F, 0F, 0F);
    	GL11.glRotatef( slope, 0F, 0F, 1F);
    	
		float u1 = (float)color / 8F;
		float u2 = (float)(color + 1) / 8F;
		float v1 = 0F;
		float v2 = 1F;
		
		float width = 1.6F;
		double length = 2.0D;
		
		GL11.glDisable(GL11.GL_CULL_FACE);//両面描画
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV( width,  0.0F,  length, u2, v1);
        tessellator.addVertexWithUV(-width,  0.0F,  length, u1, v1);
        tessellator.addVertexWithUV(-width,  0.0F, -length, u1, v2);
        tessellator.addVertexWithUV( width,  0.0F, -length, u2, v2);
		tessellator.draw();
		GL11.glEnable(GL11.GL_CULL_FACE);//表面描画
	}
	
	//風弾の描画
	public void renderWindShot(float size, int color, int count)
	{
		Tessellator tessellator = Tessellator.instance;
		GL11.glEnable(GL11.GL_BLEND);
    	//GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
    	GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
    	//GL11.glDepthMask(false);
		float size2 = size * 0.5F;
		GL11.glScalef(size2, size2, size2);
		
    	Random random = new Random();
    	float rand1 = (float)random.nextInt(50) / 100F;
    	float rand2 = (float)random.nextInt(100) / 100F + 4F;
    	int pattern = count % 4;
        float u1 = (float)((pattern % 2) * 32 + 0) / 64F;
        float u2 = (float)((pattern % 2) * 32 + 32) / 64F;
        float v1 = (float)((int)(pattern / 2) * 16 + 0) / 32F;
        float v2 = (float)((int)(pattern / 2) * 16 + 16) / 32F;
        float f9 = 0.5F;

		GL11.glDisable(GL11.GL_CULL_FACE);//両面描画
    	for(int i = 0; i < 8; i++)
    	{
    		GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F);
	        tessellator.startDrawingQuads();
    		tessellator.setNormal(0.0F, 1.0F, 0.0F);
	    	tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 0.7F);
	        tessellator.addVertexWithUV(0.0F - rand1, -1.0F - f9, 0.0D, u1, v2);
	        tessellator.addVertexWithUV(0.0F + rand1, -1.0F - f9, 0.0D, u2, v2);
	        tessellator.addVertexWithUV(0.0F + rand2,  2.0F - f9, 2.0D, u2, v1);
	        tessellator.addVertexWithUV(0.0F - rand2,  2.0F - f9, 2.0D, u1, v1);
			tessellator.draw();
    	}
    	//GL11.glDepthMask(true);
		GL11.glEnable(GL11.GL_CULL_FACE);//表面描画
		GL11.glDisable(GL11.GL_BLEND);
	}
	
	//米弾の描画をする
	protected void renderRiceShot(float size, float yaw, float pitch,  double length, float width, double zPos, int zAngleDivNum, int zDivNum, int color, float alpha)
	{
		
		
        /*float viewY = renderManager.playerViewY % 360F;
        if(viewY > 180F)
        {
        	viewY -= 360F;
        }
        else if(viewY <= -180)
        {
        	viewY += 360F;
        }
        Vec3 look = THShotLib.getVecFromAngle(viewY, renderManager.playerViewX);
        //Vec3 toEntity = Vec3.createVectorHelper(x, y, z);
        Vec3 entityVec = THShotLib.getVecFromAngle(yaw, pitch);
        float span = Math.abs(THShotLib.getVectorAndVectorAngle(look, entityVec));
        Vec3 rotate, rotate2;
        
		GL11.glScalef(size * ( 1 + MathHelper.sin(span / 180F * (float)Math.PI)), size, size);
		//GL11.glRotatef(thShot.rotationYaw, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(180F - renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		rotate = THShotLib.getVecFromAngle(180F -renderManager.playerViewY, 0);
    	GL11.glRotatef(-renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
    	rotate2 = THShotLib.getVecFromAngle(0F, -renderManager.playerViewX);
    	Vec3 rotate3 = THShotLib.getOuterProduct(rotate, rotate);
    	GL11.glRotatef(span, 0.0F, 0.0F, 1.0F);*/
    	
		
		
    	//Vec3 view = THShotLib.getVecFromAngle(180F - renderManager.playerViewY, (-renderManager.playerViewX));
    	
		
		GL11.glRotatef( yaw,   0F, 1F, 0F);
    	GL11.glRotatef(-pitch, 1F, 0F, 0F);
    	//GL11.glRotatef( slope, 0F, 0F, 1F);
    	GL11.glScalef(size, size, size);
    	
    	renderRiceShotPart(size * 0.8F, yaw, pitch, length * 0.8D, width * 0.8F, zPos + length * 0.1D, zAngleDivNum, zDivNum, 7, 1.0F);
    	GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
    	GL11.glDepthMask(false);
    	renderRiceShotPart(size, yaw, pitch, length, width, zPos, zAngleDivNum, zDivNum, color, alpha);
    	GL11.glDepthMask(true);
    	GL11.glDisable(GL11.GL_BLEND);

	}
	
	private void renderRiceShotPart(float size, float yaw, float pitch,  double length, float width, double zPos, int zAngleDivNum, int zDivNum, int color, float alpha)
	{
		
		
		Tessellator tessellator = Tessellator.instance;
		float maxWidth = (float)width;//最大の太さをmaxWidthとして保存
		
    	double angleZ = 0F;//Z軸回転変数
    	double angleSpanZ = Math.PI * 2.0D / (double)zAngleDivNum;//Z軸回転の変化量
    	
    	//int zDivNum = 5;//レーザーの奥方向への分割数。必ず奇数
    	double zLength = length;//レーザーの長さ（Z方向の長さ、奥行き）
    	double zDivLength = zLength / (double)(zDivNum - 1);//Z方向へ分割したときの1分割分の長さ
    	//double zPos = 0.0D;
    	//double zPos = -zLength2;//奥行き方向の現在の描画位置
    	//zPos = Math.sin(-Math.PI / 2.0D) * maxWidth;
    	double zPosOld = zPos;//ひとつ前の描画位置
		//初期のXとYの座標（レーザーの始点は点）
    	float xPos = 0F;
    	float yPos = 0F;
    	float xPos2 = 0F;
    	float yPos2 = 0F;
    	//初期のXとYの座標
    	float xPosOld = xPos;
    	float yPosOld = yPos;
    	float xPos2Old = xPos2;
    	float yPos2Old = yPos2;
    	//半円を描くようにレーザーが太くなるための変数。cos0 ~ cos180で処理
    	float angle = -(float)Math.PI / 2.0F;
    	float angleSpan = (float)Math.PI / (float)(zDivNum);
    	angle += angleSpan;
    	//レーザーの太さ。Z軸方向への進行で２つ必要
    	//width = (float)Math.sin(angle) * maxWidth;
    	float widthOld = 0.0F;
    	
    	//奥行きが長さの半分に達するまで（奥行きの初期値は長さの半分のマイナス値）
    	//while(zPos < zLength2)
		for(int j = 0; j < zDivNum; j++)
		{
    		zPos += zDivLength;//奥行きを１段階増やす
    		//widthOld = width;
    		//angle += angleSpan;//レーザーの描く半円の角度を更新
    		width = (float)Math.cos(angle) * maxWidth;
    		//XとY座標は初期値、0度のときの座標に戻る。
    		xPos = width;
    		yPos = 0F;
    		xPosOld = (float)Math.cos(angleZ) * width;
			yPosOld = (float)Math.sin(angleZ) * width;
			xPos2Old = (float)Math.cos(angleZ) * widthOld;
			yPos2Old = (float)Math.sin(angleZ) * widthOld;
    		//Z軸回転の始点
    		angleZ = angleSpanZ;
    		
    		
    		for(int i = 0; i <= zAngleDivNum; i++)
    		{
    			xPos = (float)Math.cos(angleZ) * width;
    			yPos = (float)Math.sin(angleZ) * width;
    			xPos2 = (float)Math.cos(angleZ) * widthOld;
    			yPos2 = (float)Math.sin(angleZ) * widthOld;
	    			
    			tessellator.startDrawingQuads();
    			tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], alpha);
	        	tessellator.addVertexWithUV(  xPosOld , yPosOld , zPos   , 0.0F, 0.0F);
	        	tessellator.addVertexWithUV(  xPos2Old, yPos2Old, zPosOld, 0.0F, 1.0F);
	        	tessellator.addVertexWithUV(  xPos2   , yPos2   , zPosOld, 1.0F, 1.0F);
	        	tessellator.addVertexWithUV(  xPos    , yPos    , zPos   , 1.0F, 0.0F);
	    		tessellator.draw();
    			
    			xPosOld = xPos;
    			yPosOld = yPos;
    			xPos2Old = xPos2;
    			yPos2Old = yPos2;
    			angleZ += angleSpanZ;
    			
    			
    		}
    		
    		zPosOld = zPos;//古い奥行きを今の奥行きに更新
    		angle += angleSpan;//レーザーの描く半円の角度を更新
    		widthOld = width;
    		
    	}
	}
	
	//楕円弾の描画をする
	protected void renderOvalShot(float size, float yaw, float pitch, double length, float width, double zPos, int zAngleDivNum, int zDivNum, int color, float alpha)
	{
		GL11.glScalef(size, size, size);
		GL11.glRotatef( yaw,   0F, 1F, 0F);
    	GL11.glRotatef(-pitch, 1F, 0F, 0F);
		//renderNormalShot(tessellator, color);
		
    	renderOvalShotPart(size, yaw, pitch ,length * 0.8D, width * 0.8F, zPos + length * 0.1D, zAngleDivNum, zDivNum, 7, 1.0F);
		//renderOvalShot(tessellator, 1.6F, 0.8F, -0.8D, 7, 5, 7, 1.0F);
		GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
    	GL11.glDepthMask(false);
    	renderOvalShotPart(size, yaw, pitch ,length, width, zPos, zAngleDivNum, zDivNum, color, alpha);
    	GL11.glDepthMask(true);
    	GL11.glDisable(GL11.GL_BLEND);
	}
		
		
	private void renderOvalShotPart(float size, float yaw, float pitch, double length, float width, double zOffset, int zAngleDivNum, int zDivNum, int color, float alpha)
	{	
		Tessellator tessellator = Tessellator.instance;
		float maxWidth = (float)width;//最大の太さをmaxWidthとして保存
		
    	double angleZ = 0F;//Z軸回転変数
    	double angleSpanZ = Math.PI * 2.0D / (double)zAngleDivNum;//Z軸回転の変化量
    	
    	//double zPos = 0.0D;
    	//double zPos = -zLength2;//奥行き方向の現在の描画位置
    	//zPos = Math.sin(-Math.PI / 2.0D) * maxWidth;
    	double zPos;
    	double zPosOld = zOffset - length;//zPos;//ひとつ前の描画位置
		//初期のXとYの座標（レーザーの始点は点）
    	float xPos = 0F;
    	float yPos = 0F;
    	float xPos2 = 0F;
    	float yPos2 = 0F;
    	//初期のXとYの座標
    	float xPosOld = xPos;
    	float yPosOld = yPos;
    	float xPos2Old = xPos2;
    	float yPos2Old = yPos2;
    	//半円を描くようにレーザーが太くなるための変数。cos0 ~ cos180で処理
    	float angle = -(float)Math.PI / 2.0F;
    	float angleSpan = (float)Math.PI / (float)(zDivNum);
    	angle += angleSpan;
    	//レーザーの太さ。Z軸方向への進行で２つ必要
    	//width = (float)Math.sin(angle) * maxWidth;
    	float widthOld = 0.0F;
    	
    	//奥行きが長さの半分に達するまで（奥行きの初期値は長さの半分のマイナス値）
    	//while(zPos < zLength2)
		for(int j = 0; j < zDivNum; j++)
		{
    		//zPos += zDivLength;//奥行きを１段階増やす
			zPos = zOffset + Math.sin(angle) * length;
    		//widthOld = width;
    		//angle += angleSpan;//レーザーの描く半円の角度を更新
    		width = (float)Math.cos(angle) * maxWidth;
    		//XとY座標は初期値、0度のときの座標に戻る。
    		xPos = width;
    		yPos = 0F;
    		xPosOld = (float)Math.cos(angleZ) * width;
			yPosOld = (float)Math.sin(angleZ) * width;
			xPos2Old = (float)Math.cos(angleZ) * widthOld;
			yPos2Old = (float)Math.sin(angleZ) * widthOld;
    		//Z軸回転の始点
    		angleZ = angleSpanZ;
    		
    		
    		for(int i = 0; i <= zAngleDivNum; i++)
    		{
    			xPos = (float)Math.cos(angleZ) * width;
    			yPos = (float)Math.sin(angleZ) * width;
    			xPos2 = (float)Math.cos(angleZ) * widthOld;
    			yPos2 = (float)Math.sin(angleZ) * widthOld;
	    			
    			tessellator.startDrawingQuads();
    			tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], alpha);
	        	tessellator.addVertexWithUV(  xPosOld , yPosOld , zPos   , 0.0F, 0.0F);
	        	tessellator.addVertexWithUV(  xPos2Old, yPos2Old, zPosOld, 0.0F, 1.0F);
	        	tessellator.addVertexWithUV(  xPos2   , yPos2   , zPosOld, 1.0F, 1.0F);
	        	tessellator.addVertexWithUV(  xPos    , yPos    , zPos   , 1.0F, 0.0F);
	    		tessellator.draw();
    			
    			xPosOld = xPos;
    			yPosOld = yPos;
    			xPos2Old = xPos2;
    			yPos2Old = yPos2;
    			angleZ += angleSpanZ;
    			
    			
    		}
    		
    		zPosOld = zPos;//古い奥行きを今の奥行きに更新
    		angle += angleSpan;//レーザーの描く半円の角度を更新
    		widthOld = width;
    		
    	}
		
	}
	
	private void renderHarfOvalShotPart(float size, float yaw, float pitch, double length, float width, double zPos, int zAngleDivNum, int zDivNum, int color, float alpha)
	{	
		Tessellator tessellator = Tessellator.instance;
		float maxWidth = (float)width;//最大の太さをmaxWidthとして保存
		
    	double angleZ = 0F;//Z軸回転変数
    	double angleSpanZ = Math.PI * 2.0D / (double)zAngleDivNum;//Z軸回転の変化量
    	
    	//double zPos = 0.0D;
    	//double zPos = -zLength2;//奥行き方向の現在の描画位置
    	//zPos = Math.sin(-Math.PI / 2.0D) * maxWidth;
    	double zPosOld = -length;//zPos;//ひとつ前の描画位置
		//初期のXとYの座標（レーザーの始点は点）
    	float xPos = 0F;
    	float yPos = 0F;
    	float xPos2 = 0F;
    	float yPos2 = 0F;
    	//初期のXとYの座標
    	float xPosOld = xPos;
    	float yPosOld = yPos;
    	float xPos2Old = xPos2;
    	float yPos2Old = yPos2;
    	//半円を描くようにレーザーが太くなるための変数。cos0 ~ cos180で処理
    	float angle = -(float)Math.PI / 2.0F;
    	float angleSpan = (float)Math.PI / (float)(zDivNum);
    	angle += angleSpan;
    	//レーザーの太さ。Z軸方向への進行で２つ必要
    	//width = (float)Math.sin(angle) * maxWidth;
    	float widthOld = 0.0F;
    	
    	zDivNum = (zDivNum / 2) + 1;
    	
    	GL11.glDisable(GL11.GL_CULL_FACE);//両面描画
    	
    	//奥行きが長さの半分に達するまで（奥行きの初期値は長さの半分のマイナス値）
    	//while(zPos < zLength2)
		for(int j = 0; j < zDivNum; j++)
		{
    		//zPos += zDivLength;//奥行きを１段階増やす
			zPos = Math.sin(angle) * length;
    		//widthOld = width;
    		//angle += angleSpan;//レーザーの描く半円の角度を更新
    		width = (float)Math.cos(angle) * maxWidth;
    		//XとY座標は初期値、0度のときの座標に戻る。
    		xPos = width;
    		yPos = 0F;
    		xPosOld = (float)Math.cos(angleZ) * width;
			yPosOld = (float)Math.sin(angleZ) * width;
			xPos2Old = (float)Math.cos(angleZ) * widthOld;
			yPos2Old = (float)Math.sin(angleZ) * widthOld;
    		//Z軸回転の始点
    		angleZ = angleSpanZ;
    		
    		
    		for(int i = 0; i <= zAngleDivNum; i++)
    		{
    			xPos = (float)Math.cos(angleZ) * width;
    			yPos = (float)Math.sin(angleZ) * width;
    			xPos2 = (float)Math.cos(angleZ) * widthOld;
    			yPos2 = (float)Math.sin(angleZ) * widthOld;
	    			
    			tessellator.startDrawingQuads();
    			tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], alpha);
	        	tessellator.addVertexWithUV(  xPosOld , yPosOld , zPos   , 0.0F, 0.0F);
	        	tessellator.addVertexWithUV(  xPos2Old, yPos2Old, zPosOld, 0.0F, 1.0F);
	        	tessellator.addVertexWithUV(  xPos2   , yPos2   , zPosOld, 1.0F, 1.0F);
	        	tessellator.addVertexWithUV(  xPos    , yPos    , zPos   , 1.0F, 0.0F);
	    		tessellator.draw();
    			
    			xPosOld = xPos;
    			yPosOld = yPos;
    			xPos2Old = xPos2;
    			yPos2Old = yPos2;
    			angleZ += angleSpanZ;
    			
    			
    		}
    		
    		alpha -= j * 0.05F;
    		
    		zPosOld = zPos;//古い奥行きを今の奥行きに更新
    		angle += angleSpan;//レーザーの描く半円の角度を更新
    		widthOld = width;
    		
    	}
		
		GL11.glEnable(GL11.GL_CULL_FACE);//両面描画
		
	}
	
	protected void renderAmuletShot(float size, int color, int count, float yaw, float pitch, float slope)
	{
		Tessellator tessellator = Tessellator.instance;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
		GL11.glDepthMask(false);
		GL11.glScalef(size, size, size);
		GL11.glRotatef( yaw,   0F, 1F, 0F);
    	GL11.glRotatef(-pitch, 1F, 0F, 0F);
    	GL11.glRotatef( slope, 0F, 0F, 1F);
    	
    	float xLength = 1.0F;
    	double zLength = 1.0F;
    	float uMin = 0.0F;
    	float uMax = 0.5F;
    	float vMin = 0.0F;
    	float vMax = 1.0F;
    	
    	GL11.glRotatef(180F - (float)count * 23F, 0.0F, 1.0F, 0.0F);
    	GL11.glDisable(GL11.GL_CULL_FACE);//両面描画
    	tessellator.startDrawingQuads();
    	//tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 1.0F);
    	tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(-xLength, 0.0F,  zLength, uMin, vMin);
        tessellator.addVertexWithUV( xLength, 0.0F,  zLength, uMax, vMin);
        tessellator.addVertexWithUV( xLength, 0.0F, -zLength, uMax, vMax);
        tessellator.addVertexWithUV(-xLength, 0.0F, -zLength, uMin, vMax);
		tessellator.draw();
    	
    	//GL11.glScalef(size + 0.1F, size + 0.1F, size + 0.1F);
    	xLength = 1.2F;
    	zLength = 1.2F;
    	uMin = 0.5F;
    	uMax = 1.0F;
    	tessellator.startDrawingQuads();
    	tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 0.6F);
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(-xLength, 0.0F,  zLength, uMin, vMin);
        tessellator.addVertexWithUV( xLength, 0.0F,  zLength, uMax, vMin);
        tessellator.addVertexWithUV( xLength, 0.0F, -zLength, uMax, vMax);
        tessellator.addVertexWithUV(-xLength, 0.0F, -zLength, uMin, vMax);
		tessellator.draw();
		GL11.glDepthMask(true);
		GL11.glEnable(GL11.GL_CULL_FACE);//両面描画
		GL11.glDisable(GL11.GL_BLEND);
	}
	
	private void renderPhantomPart(int color, double time_r, int damage)
	{
		Tessellator tessellator = Tessellator.instance;
		int pattern = 0;//time % 2;
        float umin = (float)((pattern % 32) * 32 + 0) / 64F;
        float umax = (float)((pattern % 32) * 32 + 32) / 64F;
        float vmin = 0.0F;
        float vmax = 1.0F;
        
        float alpha = (40F - (float)damage) / 40F;
        
    	tessellator.startDrawingQuads();
    	//tessellator.setColorRGBA_F(MathHelper.sin(color), MathHelper.cos(color), -MathHelper.sin(color)*2F, 0.60F);
    	tessellator.setColorRGBA_F(colorR[color] * 0.3F, colorG[color] * 0.3F, colorB[color] * 0.3F, 0.3F * alpha);
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(-0.7F + Math.sin(time_r * 3.0D) * 0.1F, -0.2F - Math.cos(time_r * 7.0D) * 0.1F, 0.0D, umin, vmax);
        tessellator.addVertexWithUV( 0.7F - Math.cos(time_r * 4.0D) * 0.1F, -0.2F - Math.sin(time_r * 5.0D) * 0.1F, 0.0D, umax, vmax);
        tessellator.addVertexWithUV( 0.7F + Math.sin(time_r * 5.0D) * 0.1F,  1.2F + Math.cos(time_r * 4.0D) * 0.1F, 0.0D, umax, vmin);
        tessellator.addVertexWithUV(-0.7F - Math.cos(time_r * 7.0D) * 0.1F,  1.2F + Math.sin(time_r * 3.0D) * 0.1F, 0.0D, umin, vmin);
        
        
        tessellator.draw();
        tessellator.startDrawingQuads();
        //tessellator.setColorRGBA_F(MathHelper.sin(color), MathHelper.cos(color), -MathHelper.sin(color)*2F, 0.60F);
    	tessellator.setColorRGBA_F(colorR[color] * 0.3F, colorG[color] * 0.3F, colorB[color] * 0.3F, 0.7F * alpha);
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(-0.6F - Math.cos(time_r * 7.0D) * 0.1F, -0.1F + Math.sin(time_r * 5.0D) * 0.1F, 0.001D, umin, vmax);
        tessellator.addVertexWithUV( 0.6F + Math.sin(time_r * 3.0D) * 0.1F, -0.1F + Math.cos(time_r * 4.0D) * 0.1F, 0.001D, umax, vmax);
        tessellator.addVertexWithUV( 0.6F - Math.cos(time_r * 4.0D) * 0.1F,  1.1F - Math.sin(time_r * 3.0D) * 0.1F, 0.001D, umax, vmin);
        tessellator.addVertexWithUV(-0.6F + Math.sin(time_r * 5.0D) * 0.1F,  1.1F - Math.cos(time_r * 7.0D) * 0.1F, 0.001D, umin, vmin);
        tessellator.draw();
    	
    	
    	//
    	for(int i = 0; i < 3; i++)
    	{
    		tessellator.startDrawingQuads();
    		if(damage > 0)
    		{
    			tessellator.setColorRGBA_F(1.0F, 0.0F, 0.0F, alpha);
    		}
	        tessellator.setNormal(0.0F, 1.0F, 0.0F);
	        tessellator.addVertexWithUV(-0.5F,  0.0F, 0.002D, umin, vmax);
	        tessellator.addVertexWithUV( 0.5F,  0.0F, 0.002D, umax, vmax);
	        tessellator.addVertexWithUV( 0.5F,  1.0F, 0.002D, umax, vmin);
	        tessellator.addVertexWithUV(-0.5F,  1.0F, 0.002D, umin, vmin);
	        tessellator.draw();
    	}
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) 
	{
		return getEntityTexture((EntityTHShot)entity);
	}
	
    protected ResourceLocation getEntityTexture(EntityTHShot thShot)
    {
    	int color = thShot.getShotColor();
    	int form = thShot.getShotForm();//形状
    	
       	if(thShot.getAnimationCount() < 0)
    	{
    		return resourceLocation_Light;
    	}
       	else
       	{
	   		switch(form)
			{
				case THShotLib.FORM_SMALL:
					return resourceLocation_Small;
				case THShotLib.FORM_TINY:
					return resourceLocation_Small;
				case THShotLib.FORM_MEDIUM:
					return resourceLocation_Small;
				case THShotLib.FORM_PEARL:
					return resourceLocation_Pearl;
				case THShotLib.FORM_CIRCLE:
					return resourceLocation_Circle;
				case THShotLib.FORM_SCALE:
					//return resourceLocation_Scale;
					return resourceLocation_Laser;
				case THShotLib.FORM_BUTTERFLY:
					return resourceLocation_Butterfly;
				case THShotLib.FORM_SMALLSTAR:
					return resourceLocation_Star;
				case THShotLib.FORM_STAR:
					return resourceLocation_Star;
				case THShotLib.FORM_RICE:
					return resourceLocation_Laser;
				case THShotLib.FORM_CRYSTAL:
					return resourceLocation_Star;
				case THShotLib.FORM_LIGHT:
					return resourceLocation_Light;
				case THShotLib.FORM_HEART:
					return resourceLocation_Heart;
				case THShotLib.FORM_KUNAI:
					return resourceLocation_Kunai;
				case THShotLib.FORM_TALISMAN:
					return resourceLocation_Talisman;
				case THShotLib.FORM_BIGLIGHT:
					return resourceLocation_Light;
				case THShotLib.FORM_OVAL:
					return resourceLocation_Laser;
				case THShotLib.FORM_FAMILIAR:
					return resourceLocation_Familiar;
				case THShotLib.FORM_ARROW:
					return resourceLocation_Arrow;
				case THShotLib.FORM_PHANTOM:
					return resourceLocation_Phantom;
				case THShotLib.FORM_AMULET:
					switch(color)
					{
						case 1:
							return resourceLocation_Diffusion;
						default:
							return resourceLocation_Amulet;
					}
				case THShotLib.FORM_KNIFE:
		    		switch(color)
		    		{
		    			case 0:
		        			return resourceLocation_Knife_Red;
		    			case 1:
		    				return resourceLocation_Knife_Blue;
		    			case 2:
		    				return resourceLocation_Knife_Green;
		    			case 3:
		    				return resourceLocation_Knife_Yellow;
		    			case 4:
		    				return resourceLocation_Knife_Purple;
		    			case 5:
		    				return resourceLocation_Knife_Aqua;
		    			case 6:
		    				return resourceLocation_Knife_Orange;
		    			default:
		    				return resourceLocation_Knife_White;
		    		}
				case THShotLib.FORM_WIND:
					return resourceLocation_Wind;
				case THShotLib.FORM_BIG:
					return resourceLocation_Big;
				case THShotLib.FORM_KISHITU:
					return resourceLocation_Light;
				default:
					return resourceLocation_Light;
			}
       	}
    }
}
