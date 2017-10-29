package thKaguyaMod.client.render.shot;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thKaguyaMod.client.THKaguyaRenderLib;
import thKaguyaMod.entity.shot.EntityOnmyoudama;
import thKaguyaMod.entity.shot.EntityTHShot;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderOnmyoudama extends Render
{
	
	/*
	通常レーザーの描画
	*/
	private static final ResourceLocation onmyoudamaTexture = new ResourceLocation("thkaguyamod", "textures/shot/Onmyoudama2.png");
	private static final ResourceLocation lightTexture = new ResourceLocation("thkaguyamod", "textures/shot/MusouFuuin.png");
	protected float colorR[] = { 224F/255F,   0F/255F,   0F/255F, 224F/255F, 224F/255F,   0F/255F, 255F/255F, 255F/255F};
	protected float colorG[] = {   0F/255F,   0F/255F, 224F/255F,  64F/255F,   0F/255F, 224F/255F, 128F/255F, 255F/255F};
	protected float colorB[] = {   0F/255F, 224F/255F,   0F/255F,   0F/255F, 224F/255F, 224F/255F,   0F/255F, 255F/255F};
	
    public RenderOnmyoudama()
    {
    }
    
    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
        renderOnmyoudama((EntityOnmyoudama)entity, x, y, z, yaw, pitch);
    }

    public void renderOnmyoudama(EntityOnmyoudama onmyoudama, double x, double y, double z, float yaw, float pitch)
    {
        GL11.glPushMatrix();
        bindEntityTexture(onmyoudama);
        GL11.glTranslatef((float)x, (float)y, (float)z);
		GL11.glDisable(GL11.GL_LIGHTING);
    	GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
    	GL11.glDepthMask(false);
    	GL11.glScalef(1.0F, 1.0F, 1.0F);
        Tessellator tessellator = Tessellator.instance;
    	
    	int color = onmyoudama.getShotColor();
    	int time = onmyoudama.ticksExisted;
    	
    	// プレイヤーに常に正面を向けるようにする
        THKaguyaRenderLib.getGLRotatefByTherdPersonView(renderManager);
    	GL11.glRotatef(time * 5F, 0.0F, 0.0F, 1.0F);
    	
    	
    	
    	GL11.glScalef(onmyoudama.getShotSize(), onmyoudama.getShotSize(), onmyoudama.getShotSize());
    	
    	float f3 = 0.0F;
        float f4 = 0.5F;
        float f5 = 0.0F;
        float f6 = 1.0F;
        float f7 = 1.0F;
        float f8 = 0.5F;
        float f9 = 0.5F;
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(0.0F - f8, 0.0F - f9, 0.01D, f3, f6);
        tessellator.addVertexWithUV(f7 - f8, 0.0F - f9, 0.01D, f4, f6);
        tessellator.addVertexWithUV(f7 - f8, 1.0F - f9, 0.01D, f4, f5);
        tessellator.addVertexWithUV(0.0F - f8, 1.0F - f9, 0.01D, f3, f5);
		tessellator.draw();
		
		renderLightEffect(tessellator, 0, onmyoudama.getShotSize() + 0.2F, onmyoudama);
    	
    	GL11.glDepthMask(true);
    	GL11.glDisable(GL11.GL_BLEND);
    	GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
	
	//レーザーの描画をする
	protected void renderLaser(Tessellator tessellator, double length, float width, double zPos, int color, float alpha, int time)
	{
		
		float maxWidth = (float)width / 2.0F;//最大の太さをmaxWidthとして保存
		
    	int zAngleDivNum = 72;//Z軸回転の分割数
    	float zSpan = 360F / zAngleDivNum;
    	double angleZ = 0F;//Z軸回転変数
    	double angleSpanZ = Math.PI * 2.0D / (double)zAngleDivNum;//Z軸回転の変化量
    	
    	int zDivNum = 36;//レーザーの奥方向への分割数。必ず奇数
    	double zLength = width;//レーザーの長さ（Z方向の長さ、奥行き）
    	double zDivLength = zLength / (double)(zDivNum - 1);//Z方向へ分割したときの1分割分の長さ
    	double zLength2 = zLength / 2.0D;//長さの半分
		//double zPos = 0.0D;
    	//double zPos = -zLength2;//奥行き方向の現在の描画位置
    	zPos = Math.sin(-Math.PI / 2.0D) * maxWidth;
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
    	//width = (float)Math.cos(angle) * maxWidth;
    	float widthOld = 0.0F;
    	
    	float vPos = 0.0F;
    	float vPosSpan = 1.0F / zDivNum;
    	float vPosNext = vPosSpan;
    	
    	//奥行きが長さの半分に達するまで（奥行きの初期値は長さの半分のマイナス値）
    	//while(zPos < zLength2)
		for(int j = 0; j < zDivNum; j++)
		{
			zPos = Math.sin(angle) * maxWidth;
    		width = (float)Math.cos(angle) * maxWidth;
    		
    		//XとY座標は初期値、0度のときの座標に戻る。
    		xPos = width;
    		yPos = 0F;//(float)width;
    		angleZ = 0F;
    		xPosOld = (float)Math.cos(angleZ) * width;
			yPosOld = (float)Math.sin(angleZ) * width;
			xPos2Old = (float)Math.cos(angleZ) * widthOld;
			yPos2Old = (float)Math.sin(angleZ) * widthOld;
    		//Z軸回転の始点
    		angleZ = angleSpanZ;
    		
    		float uPos = 0F;
    		float uPosSpan = 1.0F / zAngleDivNum;
    		float uPosNext = uPosSpan;
    		
    		for(int i = 1; i <= zAngleDivNum; i++)
    		{
	    		xPos = (float)Math.cos(angleZ) * width;
	    		yPos = (float)Math.sin(angleZ) * width;
	    		xPos2 = (float)Math.cos(angleZ) * widthOld;
	    		yPos2 = (float)Math.sin(angleZ) * widthOld;
	    		
    			double colorVar = 0.0D;
    			if(time != 0)
    			{
    				colorVar = (time + j) / 10.0D;
    			}
    			tessellator.startDrawingQuads();
	    		tessellator.setColorRGBA_F(colorR[color] + (float)Math.sin(colorVar) * 0.2F, colorG[color] + (float)Math.cos(colorVar) * 0.2F, colorB[color] - (float)Math.sin(colorVar) * 0.2F , alpha);
	    		tessellator.setNormal(0.0F, 1.0F, 0.0F);
	    		tessellator.addVertexWithUV(  xPos    , yPos    , zPos   , uPosNext, vPos);
	    		tessellator.addVertexWithUV(  xPosOld , yPosOld , zPos   , uPos, vPos);
	    		tessellator.addVertexWithUV(  xPos2Old, yPos2Old, zPosOld, uPos, vPosNext);
	        	tessellator.addVertexWithUV(  xPos2   , yPos2   , zPosOld, uPosNext, vPosNext);
	        	
	    		tessellator.draw();
    			
    			xPosOld = xPos;
    			yPosOld = yPos;
    			xPos2Old = xPos2;
    			yPos2Old = yPos2;
    			angleZ += angleSpanZ;
    			uPos = uPosNext;
    			uPosNext += uPosSpan;
    			
    		}
    		zPosOld = zPos;//古い奥行きを今の奥行きに更新
    		angle += angleSpan;//レーザーの描く半円の角度を更新
    		widthOld = width;
    		vPos = vPosNext;
    		vPosNext += vPosSpan;
    		
    	}
	}
	
	//光のエフェクト
	public void renderLightEffect(Tessellator tessellator, int color, float size, EntityTHShot entityTHShot)
	{
		color %= 8;
		int count = entityTHShot.getAnimationCount() % 2;
		float f3 = (float)(64 + count * 32 +  0) / 128F;
	    float f4 = (float)(64 + count * 32 + 32) / 128F;
	    float f5 = 0F;
	    float f6 = 0.5F;
	    float f7 = 1.4F;
	    float f8 = 0.7F;
	    float f9 = 0.7F;
	    tessellator.startDrawingQuads();
	    tessellator.setNormal(0.0F, 1.0F, 0.0F);
		tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 0.5F);
	    tessellator.addVertexWithUV(0.0F - f8, 0.0F - f9, 0.0D, f3, f6);
	    tessellator.addVertexWithUV(f7 - f8, 0.0F - f9, 0.0D, f4, f6);
	    tessellator.addVertexWithUV(f7 - f8, f7 - f9, 0.0D, f4, f5);
	    tessellator.addVertexWithUV(0.0F - f8, f7 - f9, 0.0D, f3, f5);
		tessellator.draw();
	}
	
    protected ResourceLocation getEntityTexture(EntityOnmyoudama onmyoudama)
    {
    	if(onmyoudama.getAnimationCount() < 0)
    	{
    		return lightTexture;
    	}
        return onmyoudamaTexture;
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return getEntityTexture((EntityOnmyoudama)entity);
	}
}