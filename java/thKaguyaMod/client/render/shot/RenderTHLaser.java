package thKaguyaMod.client.render.shot;

import java.util.Random;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thKaguyaMod.entity.shot.EntityTHLaser;
import thKaguyaMod.entity.shot.EntityTHSetLaser;
import thKaguyaMod.entity.shot.EntityTHShot;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTHLaser extends Render
{
	
	/*
	通常レーザーの描画
	*/
	private static final ResourceLocation laserTexture = new ResourceLocation("thkaguyamod", "textures/shot/Laser.png");
	private static final ResourceLocation lightTexture= new ResourceLocation("thkaguyamod", "textures/shot/MusouFuuin.png");
	private Random random = new Random();
	protected float colorR[] = { 224F/255F,   0F/255F,   0F/255F, 224F/255F, 224F/255F,   0F/255F, 255F/255F, 255F/255F};
	protected float colorG[] = {   0F/255F,   0F/255F, 224F/255F, 224F/255F,   0F/255F, 224F/255F, 128F/255F, 255F/255F};
	protected float colorB[] = {   0F/255F, 224F/255F,   0F/255F,   0F/255F, 224F/255F, 224F/255F,   0F/255F, 255F/255F};

    public RenderTHLaser()
    {
    }
    
    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
        renderTHLaser((EntityTHLaser)entity, x, y, z, yaw, pitch);
    }

    public void renderTHLaser(EntityTHLaser thLaser, double x, double y, double z, float yaw, float pitch)
    {
        GL11.glPushMatrix();
        bindEntityTexture(thLaser);
        GL11.glTranslatef((float)x, (float)y, (float)z);
		GL11.glDisable(GL11.GL_LIGHTING);
		//GL11.glEnable(GL11.GL_NORMALIZE);
    	GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
    	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
    	
        Tessellator tessellator = Tessellator.instance;
        getEntityTexture(thLaser);
    	
    	int color = thLaser.getShotId();
    	
    	float width = 1.0F;
    	if(thLaser.getAnimationCount() < 0 && thLaser instanceof EntityTHSetLaser)
    	{
    		width = 0.1F;
    	}
    	
    	GL11.glScalef(1.0F, 1.0F, 1.0F);
    	
    	if(thLaser.getAnimationCount() < 0 && thLaser instanceof EntityTHSetLaser == false)
    	{
    		int delayCount = -thLaser.getAnimationCount();
    		if(delayCount > 10)
    		{
    			delayCount = 10;
    		}
    		float size2 = delayCount * thLaser.getShotSize() * 2F;
    		if(size2 > 1.0F)
    		{
    			size2 = 1.0F;
    		}
    		GL11.glRotatef(180F - renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        	GL11.glRotatef(-renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        	GL11.glDepthMask(false);
        	renderLightEffect(tessellator, color % 8, size2, thLaser);
        	GL11.glDepthMask(true);
    	}
    	else
    	{
    		GL11.glRotatef(yaw, 0.0F, 1.0F, 0.0F);
	    	GL11.glRotatef(-thLaser.rotationPitch, 1.0F, 0.0F, 0.0F);
	    	double centerZ1 = thLaser.getLaserLength() * 1.2D / 2.0D;
	    	double centerZ2 = thLaser.getLaserLength() / 2.0D;
	    	
	    	if(color >= 24)//マスタースパークレーザーなら
	    	{
	    		GL11.glScalef(width, width, 1.0F);
	        	float ticks = (float)thLaser.getAnimationCount() * 10.0F;
	        	ticks = Math.abs(ticks);
	        	GL11.glDisable(GL11.GL_CULL_FACE);//両面描画
	        	GL11.glDepthFunc(GL11.GL_ALWAYS);
	        	renderMasterSparkLaser(tessellator, thLaser.getLaserLength(), thLaser.getShotSize(), 1.0F, false, ticks);
	        	//GL11.glDepthMask(false);
	        	GL11.glEnable(GL11.GL_BLEND);
	        	GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
	        	renderMasterSparkLaser(tessellator, thLaser.getLaserLength(), thLaser.getShotSize() * 1.2F, 0.6F, true, ticks);
	        	GL11.glDisable(GL11.GL_BLEND);
	        	//GL11.glDepthMask(true);
	        	GL11.glDepthFunc(GL11.GL_LEQUAL);
	        	GL11.glEnable(GL11.GL_CULL_FACE);//両面描画
	    	}
	    	else if(color >= 16)//レーヴァテインレーザーなら
	    	{
	    		GL11.glRotatef(thLaser.getAngleZ(), 0.0F, 0.0F, 1.0F);
		    	GL11.glDisable(GL11.GL_CULL_FACE);//表綿描画
		    	GL11.glScalef(1.0F, 1.0F, 1.0F * (float)thLaser.getLaserLength() / 20.8F);
		    	renderLaevateinn(tessellator, thLaser.getLaserLength(), thLaser.getShotSize(),  1.6D, 7, 1.0F, thLaser.ticksExisted);
		    	
		    	GL11.glDepthMask(false);
		    	GL11.glEnable(GL11.GL_BLEND);
		    	GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
		    	GL11.glScalef(1.2F, 1.2F, 1.2F);
		    	renderLaevateinn(tessellator, thLaser.getLaserLength() * 1.2D, thLaser.getShotSize() * 1.2F, 1.0D, color, 0.6F,  thLaser.ticksExisted);
		    	GL11.glDisable(GL11.GL_BLEND);
		    	GL11.glEnable(GL11.GL_CULL_FACE);//表綿描画
		    	GL11.glDepthMask(true);
	    	}
	    	else if(color >= 8)//グングニルレーザーなら
	    	{
	    		GL11.glRotatef((float)thLaser.ticksExisted * 30F, 0.0F, 0.0F, 1.0F);
		    	GL11.glDisable(GL11.GL_CULL_FACE);//表綿描画
		    	GL11.glScalef(0.3F, 0.3F, 0.5F  * (float)thLaser.getLaserLength() / 10F);
		    	renderSpearTheGungnir(tessellator, thLaser.getLaserLength(), thLaser.getShotSize(), centerZ1 - centerZ2, 7, 1.0F);
		    	
		    	GL11.glDepthMask(false);
		    	GL11.glEnable(GL11.GL_BLEND);
		    	GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
		    	GL11.glScalef(1.2F, 1.2F, 1.2F);
		    	renderSpearTheGungnir(tessellator, thLaser.getLaserLength() * 1.2D, thLaser.getShotSize() * 1.2F, -1.0D, color, 0.6F);
		    	GL11.glDisable(GL11.GL_BLEND);
		    	GL11.glEnable(GL11.GL_CULL_FACE);//表綿描画
		    	GL11.glDepthMask(true);
	    	}
	    	
	    	else if(thLaser.isMotherAndChild() != (byte)0)
	    	{
	    		//GL11.glRotatef(yaw, 0.0F, 1.0F, 0.0F);
		    	//GL11.glRotatef(-thLaser.rotationPitch, 1.0F, 0.0F, 0.0F);
	    		double toMotherLength = thLaser.getDistance(thLaser.getMotherPosX(), thLaser.getMotherPosY(), thLaser.getMotherPosZ());
	    		GL11.glEnable(GL11.GL_CULL_FACE);//表綿描画
		    	//GL11.glEnable(32826);
	    		renderLaser(tessellator, toMotherLength, thLaser.getShotSize(), 0.0D, color, 1.0F, 0.0D);
	    		//GL11.glDisable(32826);
	    	}
	    	else
	    	{
	    		GL11.glScalef(width, width, 1.0F);
	    		//GL11.glRotatef(yaw, 0.0F, 1.0F, 0.0F);
		    	//GL11.glRotatef(-thLaser.rotationPitch, 1.0F, 0.0F, 0.0F);
		    	GL11.glEnable(GL11.GL_CULL_FACE);//表綿描画
		    	//GL11.glEnable(32826);
		    	renderLaser(tessellator, thLaser.getLaserLength(), thLaser.getShotSize(), centerZ1 - centerZ2, 7, 1.0F);
		    	//GL11.glDisable(32826);
		    	GL11.glDepthMask(false);
		    	GL11.glEnable(GL11.GL_BLEND);
		    	GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
		    	renderLaser(tessellator, thLaser.getLaserLength() * 1.2D, thLaser.getShotSize() * 1.2F, 0.0D, color, 0.6F);
		    	GL11.glDisable(GL11.GL_BLEND);
		    	GL11.glDepthMask(true);
	    	}
    	}

    	
    	GL11.glDisable(GL11.GL_BLEND);
    	GL11.glEnable(GL11.GL_LIGHTING);
    	
        GL11.glPopMatrix();
    }
    
	//レーザーの描画をする
	protected void renderLaser(Tessellator tessellator, double length, float width, double zPos, int color, float alpha, double d)
	{
		float maxWidth = (float)width;//最大の太さをmaxWidthとして保存
		
    	int zAngleDivNum = 8;//Z軸回転の分割数
    	float zSpan = 360F / zAngleDivNum;
    	double angleZ = 0F;//Z軸回転変数
    	double angleSpanZ = Math.PI * 2.0D / (double)zAngleDivNum;//Z軸回転の変化量
    	
    	int zDivNum = 13;//レーザーの奥方向への分割数。必ず奇数
    	double zLength = length;//レーザーの長さ（Z方向の長さ、奥行き）
    	double zDivLength = zLength / (double)(zDivNum - 1);//Z方向へ分割したときの1分割分の長さ
    	double zLength2 = zLength / 2.0D;//長さの半分
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
	    		tessellator.setNormal(0.0F, 1.0F, 0.0F);
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
    		//widthOld = width;
    		
    	}
	}
	
	//レーザーの描画をする
	protected void renderLaser(Tessellator tessellator, double length, float width, double zPos, int color, float alpha)
	{
		float maxWidth = (float)width;//最大の太さをmaxWidthとして保存
		
    	int zAngleDivNum = 8;//Z軸回転の分割数
    	float zSpan = 360F / zAngleDivNum;
    	double angleZ = 0F;//Z軸回転変数
    	double angleSpanZ = Math.PI * 2.0D / (double)zAngleDivNum;//Z軸回転の変化量
    	
    	int zDivNum = 13;//レーザーの奥方向への分割数。必ず奇数
    	double zLength = length;//レーザーの長さ（Z方向の長さ、奥行き）
    	double zDivLength = zLength / (double)(zDivNum - 1);//Z方向へ分割したときの1分割分の長さ
    	double zLength2 = zLength / 2.0D;//長さの半分
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
	    		tessellator.setNormal(0.0F, 1.0F, 0.0F);
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
	
	//スピア・ザ・グングニルの描画をする
	protected void renderSpearTheGungnir(Tessellator tessellator, double length, float width, double zPos, int color, float alpha)
	{
		color %= 8;
		double vertex[][] = {
				 {0.000000,0.681531,-5.956767}
				 ,{0.000000,0.999996,6.000000}
				 ,{-0.681527,0.000004,-5.956767}
				 ,{-1.000000,-0.000004,6.000000}
				 ,{-0.481912,0.481916,-5.956767}
				 ,{-0.707107,0.707103,6.000000}
				 ,{-0.000000,1.957804,8.160793}
				 ,{-1.733818,-0.000005,4.933474}
				 ,{-1.461115,0.707101,6.843752}
				 ,{-2.247491,-0.00000,6.180964}
				 ,{-3.164763,-0.00000,6.988164}
				 ,{-4.155417,-0.00000,6.107583}
				 ,{-5.292834,-0.00000,3.575912}
				 ,{-5.476289,-0.00000,7.743996}
				 ,{-4.321013,-0.00000,11.094296}
				 ,{-0.787193,-0.00000,13.423745}
				 ,{0.005330,-0.00000,15.669228}
				 ,{-3.180439,1.438709,7.695314}
				 ,{-4.787621,0.707101,6.067568}
				 ,{-4.588031,0.707101,7.597761}
				 ,{0.00,1.996092,10.168588}
				 ,{-0.00,1.370200,12.822122}
				 ,{-3.372246,1.295732,10.534312}
				 ,{-1.406365,1.812266,10.156220}
				 ,{-1.433740,1.775428,8.389470}
				 ,{-0.000000,0.000006,-9.680047}
				 ,{-0.000000,1.076022,0.000000}
				 ,{-1.076022,0.000000,0.000000}
				 ,{-0.760863,0.760862,0.000000}
		};
		
		int order[][] = {
		{29,6,2,27}
		,{28,4,6,29}
		,{23,15,14,20}
		,{13,19,12,12}
		,{14,20,19,13}
		,{8,9,4,4}
		,{9,8,10,10}
		,{9,10,11,18}
		,{18,11,12,19}
		,{20,18,23,23}
		,{19,20,18,18}
		,{17,22,16,16}
		,{22,16,15,23}
		,{4,9,6,6}
		,{9,18,25,25}
		,{23,18,25,24}
		,{24,25,7,21}
		,{22,23,24,21}
		,{6,25,7,2}
		,{9,25,6,6}
		,{5,1,26,26}
		,{3,5,26,26}
		,{5,29,27,1}
		,{3,28,29,5}
		};
		
		for(int i = 0; i < 24; i++)
		{    			
			tessellator.startDrawingQuads();
    		tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], alpha);
    		tessellator.addVertexWithUV(  (float)vertex[order[i][0] - 1][0] , (float)vertex[order[i][0] - 1][1] , vertex[order[i][0] - 1][2] + zPos   , 0.0F, 0.0F);
    		tessellator.addVertexWithUV(  (float)vertex[order[i][1] - 1][0] , (float)vertex[order[i][1] - 1][1] , vertex[order[i][1] - 1][2] + zPos   , 0.0F, 1.0F);
    		tessellator.addVertexWithUV(  (float)vertex[order[i][2] - 1][0] , (float)vertex[order[i][2] - 1][1] , vertex[order[i][2] - 1][2] + zPos  , 1.0F, 1.0F);
    		tessellator.addVertexWithUV(  (float)vertex[order[i][3] - 1][0] , (float)vertex[order[i][3] - 1][1] , vertex[order[i][3] - 1][2] + zPos  , 1.0F, 0.0F);
    		tessellator.draw();
    		
			tessellator.startDrawingQuads();
    		tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], alpha);
    		tessellator.addVertexWithUV(  -(float)vertex[order[i][0] - 1][0] , (float)vertex[order[i][0] - 1][1] , vertex[order[i][0] - 1][2] + zPos   , 0.0F, 0.0F);
    		tessellator.addVertexWithUV(  -(float)vertex[order[i][1] - 1][0] , (float)vertex[order[i][1] - 1][1] , vertex[order[i][1] - 1][2] + zPos   , 0.0F, 1.0F);
    		tessellator.addVertexWithUV(  -(float)vertex[order[i][2] - 1][0] , (float)vertex[order[i][2] - 1][1] , vertex[order[i][2] - 1][2] + zPos  , 1.0F, 1.0F);
    		tessellator.addVertexWithUV(  -(float)vertex[order[i][3] - 1][0] , (float)vertex[order[i][3] - 1][1] , vertex[order[i][3] - 1][2] + zPos  , 1.0F, 0.0F);
    		tessellator.draw();
    		
			tessellator.startDrawingQuads();
    		tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], alpha);
    		tessellator.addVertexWithUV(  (float)vertex[order[i][0] - 1][0] , -(float)vertex[order[i][0] - 1][1] , vertex[order[i][0] - 1][2] + zPos   , 0.0F, 0.0F);
    		tessellator.addVertexWithUV(  (float)vertex[order[i][1] - 1][0] , -(float)vertex[order[i][1] - 1][1] , vertex[order[i][1] - 1][2] + zPos   , 0.0F, 1.0F);
    		tessellator.addVertexWithUV(  (float)vertex[order[i][2] - 1][0] , -(float)vertex[order[i][2] - 1][1] , vertex[order[i][2] - 1][2] + zPos  , 1.0F, 1.0F);
    		tessellator.addVertexWithUV(  (float)vertex[order[i][3] - 1][0] , -(float)vertex[order[i][3] - 1][1] , vertex[order[i][3] - 1][2] + zPos  , 1.0F, 0.0F);
    		tessellator.draw();
    		
			tessellator.startDrawingQuads();
    		tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], alpha);
    		tessellator.addVertexWithUV(  -(float)vertex[order[i][0] - 1][0] , -(float)vertex[order[i][0] - 1][1] , vertex[order[i][0] - 1][2] + zPos   , 0.0F, 0.0F);
    		tessellator.addVertexWithUV(  -(float)vertex[order[i][1] - 1][0] , -(float)vertex[order[i][1] - 1][1] , vertex[order[i][1] - 1][2] + zPos   , 0.0F, 1.0F);
    		tessellator.addVertexWithUV(  -(float)vertex[order[i][2] - 1][0] , -(float)vertex[order[i][2] - 1][1] , vertex[order[i][2] - 1][2] + zPos  , 1.0F, 1.0F);
    		tessellator.addVertexWithUV(  -(float)vertex[order[i][3] - 1][0] , -(float)vertex[order[i][3] - 1][1] , vertex[order[i][3] - 1][2] + zPos  , 1.0F, 0.0F);
    		tessellator.draw();
		}
	}
	
	//レーヴァテインの描画をする
	protected void renderLaevateinn(Tessellator tessellator, double length, float width, double zPos, int color, float alpha, int time)
	{
		color %= 8;
		double vertex[][] = {
				{-0.012712,0.860909,19.683685},
				{0.010621,0.799994,3.958289},
				{-0.411288,0.576921,19.683094},
				{-0.555063,0.565679,3.957450},
				{-0.576384,0.000006,19.682850},
				{-0.789378,-0.000006,3.957103},
				{0.944878  + Math.sin(time * 60 / 180F * 3.141593F) * 0.5,0.000006,19.685106},
				{0.810621,-0.000006,3.959476},
				{0.385864  + Math.sin(time * 86 / 180F * 3.141593F) * 0.5,0.576921,19.684277},
				{0.576306,0.565679,3.959129},
				{0.000000,1.097230,11.116394},
				{-0.565685,0.803469,11.115555},
				{-0.978337,-0.000000,11.115208},
				{0.799999,0.000000,11.117582},
				{0.565685,0.922361,11.117234},
				{0.010666,0.838774,3.928527},
				{-2.699365,0.740431,3.924505},
				{-3.821897,-0.000008,3.922842},
				{3.843230,-0.000008,3.934216},
				{2.720698,0.819214,3.932549},
				{0.011292,0.878166,3.507193},
				{-2.698740,0.819214,3.503171},
				{-3.821272,-0.000009,3.501507},
				{3.843855,-0.000009,3.512882},
				{2.721323,0.819214,3.511215},
				{0.011649,0.801885,3.266243},
				{-0.603876,0.559341,3.293626},
				{-0.844117,-0.001264,3.318336},
				{0.867178,-0.000875,3.320815},
				{0.627090,0.559341,3.295454},
				{0.015762,0.620065,0.494560},
				{-0.422696,0.438449,0.493910},
				{-0.604311,-0.000009,0.493641},
				{0.635834,-0.000009,0.495482},
				{0.454219,0.438449,0.495212},
				{0.017635,-0.000009,-0.767668},
				{-0.017040,0.277390,22.599312},
				{-0.213180,0.196148,22.599022},
				{-0.294422,0.000008,22.598900},
				{0.260342 + Math.sin(time * 50 / 180F * 3.141593F) * 0.5,0.000008,22.599724},
				{0.179100,0.196148,22.599602},
				{-0.019185,0.000008,24.045269},
				{-0.488487,0.482134,15.399324},
				{-1.282651,0.000003,15.399029},
				{1.620884 + Math.sin(time * 43 / 180F * 3.141593F) * 0.5,0.000003,15.402454},
				{0.869693,0.482134,15.401340},
				{-0.006356,0.681839,15.400040},
				{0.005311,0.799997,7.537342},
				{-0.560374,0.565682,7.536503},
				{-1.151364,-0.000003,7.536155},
				{2.026458 + Math.sin(time * 80 / 180F * 3.141593F) * 0.5,-0.000003,7.540341},
				{1.161873,0.565682,7.539058}
		};
		
		int order[][] = {
				{48,2,4,49},
				{49,4,6,50},
				{2,10,20,16},
				{52,10,2,48},
				{51,8,10,52},
				{9,1,37,41},
				{47,11,12,43},
				{43,12,13,44},
				{46,15,11,47},
				{45,14,15,46},
				{16,20,25,21},
				{10,8,19,20},
				{6,4,17,18},
				{4,2,16,17},
				{20,19,24,25},
				{18,17,22,23},
				{17,16,21,22},
				{21,25,30,26},
				{25,24,29,30},
				{23,22,27,28},
				{22,21,26,27},
				{26,30,35,31},
				{30,29,34,35},
				{28,27,32,33},
				{27,26,31,32},
				{31,35,36,36},
				{35,34,36,36},
				{33,32,36,36},
				{32,31,36,36},
				{7,9,41,40},
				{3,5,39,38},
				{1,3,38,37},
				{41,37,42,42},
				{40,41,42,42},
				{38,39,42,42},
				{37,38,42,42},
				{1,47,43,3},
				{3,43,44,5},
				{9,46,47,1},
				{7,45,46,9},
				{11,48,49,12},
				{12,49,50,13},
				{15,52,48,11},
				{14,51,52,15}
		};
		
		for(int i = 0; i < order.length; i++)
		{    			
			tessellator.startDrawingQuads();
    		tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], alpha);
    		tessellator.addVertexWithUV(  (float)vertex[order[i][0] - 1][0] , (float)vertex[order[i][0] - 1][1] , vertex[order[i][0] - 1][2] + zPos   , 0.0F, 0.0F);
    		tessellator.addVertexWithUV(  (float)vertex[order[i][1] - 1][0] , (float)vertex[order[i][1] - 1][1] , vertex[order[i][1] - 1][2] + zPos   , 0.0F, 1.0F);
    		tessellator.addVertexWithUV(  (float)vertex[order[i][2] - 1][0] , (float)vertex[order[i][2] - 1][1] , vertex[order[i][2] - 1][2] + zPos  , 1.0F, 1.0F);
    		tessellator.addVertexWithUV(  (float)vertex[order[i][3] - 1][0] , (float)vertex[order[i][3] - 1][1] , vertex[order[i][3] - 1][2] + zPos  , 1.0F, 0.0F);
    		tessellator.draw();
    		
			tessellator.startDrawingQuads();
    		tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], alpha);
    		tessellator.addVertexWithUV(  (float)vertex[order[i][0] - 1][0] , -(float)vertex[order[i][0] - 1][1] , vertex[order[i][0] - 1][2] + zPos   , 0.0F, 0.0F);
    		tessellator.addVertexWithUV(  (float)vertex[order[i][1] - 1][0] , -(float)vertex[order[i][1] - 1][1] , vertex[order[i][1] - 1][2] + zPos   , 0.0F, 1.0F);
    		tessellator.addVertexWithUV(  (float)vertex[order[i][2] - 1][0] , -(float)vertex[order[i][2] - 1][1] , vertex[order[i][2] - 1][2] + zPos  , 1.0F, 1.0F);
    		tessellator.addVertexWithUV(  (float)vertex[order[i][3] - 1][0] , -(float)vertex[order[i][3] - 1][1] , vertex[order[i][3] - 1][2] + zPos  , 1.0F, 0.0F);
    		tessellator.draw();
    		
		}
	}
	
	//マスタースパークのレーザー
	protected void renderMasterSparkLaser(Tessellator tessellator, double length, float width, float alpha, boolean rainbowFlag, float ticks)
	{
		float colorR[] = { 255F, 255F, 224F,   0F,   0F,   0F, 224F, 255F};
    	float colorG[] = {   0F, 165F, 224F, 255F,   0F, 255F,   0F, 255F};
    	float colorB[] = {   0F,   0F,   0F,   0F, 255F, 255F, 224F, 255F};
		
		float maxWidth = (float)width * 1.6F;//最大の太さをmaxWidthとして保存
		
    	int zAngleDivNum = 64;//Z軸回転の分割数
		if(!rainbowFlag)
		{
			zAngleDivNum = 16;
		}
    	double angleZ;//Z軸回転変数
    	double angleSpanZ = Math.PI * 2.0D / (double)zAngleDivNum;//Z軸回転の変化量
    	
    	int zDivNum = 13;//レーザーの奥方向への分割数。必ず奇数
    	double zLength = length;//レーザーの長さ（Z方向の長さ、奥行き）
    	double zDivLength = zLength / (double)(zDivNum - 1);//Z方向へ分割したときの1分割分の長さ
    	double zLength2 = zLength / 2.0D;//長さの半分
		double zPos = 0.0D;
    	//double zPos = -zLength2;//奥行き方向の現在の描画位置
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
    	float angle = 0F;
    	float angleSpan = (float)Math.PI / (float)(zDivNum - 1);
    	//レーザーの太さ。Z軸方向への進行で２つ必要
    	width = (float)Math.sin(angle) * maxWidth;
    	float widthOld = width;
		
		float color;
		float colorAngle = 0F;
		float colorRev = 3.141593F * 5.6125F / 180F;
    	
		float width2 = width;
		float widthOld2 = widthOld;
		
    	//奥行きが長さの半分に達するまで（奥行きの初期値は長さの半分のマイナス値）
    	while(zPos < zLength)
		{
    		zPos += zDivLength;//奥行きを１段階増やす
    		widthOld = width;
    		angle += angleSpan;//レーザーの描く半円の角度を更新
			if(width < maxWidth)
			{
    			width = (float)Math.sin(angle) * maxWidth;
			}
			else
			{
				width = maxWidth;
			}
			
			//if(rainbowFlag)
			{
				widthOld2= width2;
				width2 = width * ((float)Math.sin(angle + (float)ticks / 40F) * 0.1F + 1.0F);
			}
			/*else
			{
				widthOld2 = widthOld;
				width2 = width;
			}*/
    		//XとY座標は初期値、0度のときの座標に戻る。
    		xPos = 0F;
    		yPos = (float)width;
    		//Z軸回転の始点
    		angleZ = angleSpanZ;
    		
    		for(int i = 0; i <= zAngleDivNum; i++)
    		{
    			xPos = (float)Math.cos(angleZ) * width2;
    			yPos = (float)Math.sin(angleZ) * width2;
    			xPos2 = (float)Math.cos(angleZ) * widthOld2;
    			yPos2 = (float)Math.sin(angleZ) * widthOld2;
	    			
    			tessellator.startDrawingQuads();
    			if(rainbowFlag)
    			{
    				color = ((float)colorAngle + (float)ticks) * colorRev;
    				colorAngle ++;
    				tessellator.setColorRGBA_F(MathHelper.sin(color), MathHelper.cos(color), -MathHelper.sin(color)*2F, 0.60F);
    			}
    			else
    			{
	    			tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, alpha);
    			}
	    		tessellator.setNormal(0.0F, 1.0F, 0.0F);
	        	tessellator.addVertexWithUV(  xPosOld , yPosOld , zPos   , 0.0F, 0.0F);
	        	tessellator.addVertexWithUV(  xPos    , yPos    , zPos   , 1.0F, 0.0F);
	        	tessellator.addVertexWithUV(  xPos2   , yPos2   , zPosOld, 1.0F, 1.0F);
	        	tessellator.addVertexWithUV(  xPos2Old, yPos2Old, zPosOld, 0.0F, 1.0F);
	    		tessellator.draw();
    			
    			xPosOld = xPos;
    			yPosOld = yPos;
    			xPos2Old = xPos2;
    			yPos2Old = yPos2;
    			angleZ += angleSpanZ;
    			
    			
    		}
			ticks += 1.0F;
    		zPosOld = zPos;//古い奥行きを今の奥行きに更新
    		
    	}
	}
	
	//光のエフェクト
	public void renderLightEffect(Tessellator tessellator, int color, float size, EntityTHShot thShot)
	{
		color %= 8;
		int count = thShot.getAnimationCount() % 2;
		float f3 = (float)(count * 32 +  0) / 64F;
	    float f4 = (float)(count * 32 + 32) / 64F;
	    float f5 = 0F;
	    float f6 = 1F;
	    float f7 = 1.0F;
	    float f8 = 0.5F;
	    float f9 = 0.25F;
		GL11.glScalef(size, size, size);
	    tessellator.startDrawingQuads();
		tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 0.5F);
	    tessellator.addVertexWithUV(0.0F - f8, 0.0F - f9, 0.0D, f3, f6);
	    tessellator.addVertexWithUV(f7 - f8, 0.0F - f9, 0.0D, f4, f6);
	    tessellator.addVertexWithUV(f7 - f8, 1.0F - f9, 0.0D, f4, f5);
	    tessellator.addVertexWithUV(0.0F - f8, 1.0F - f9, 0.0D, f3, f5);
		tessellator.draw();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) 
	{
		return getEntityTexture((EntityTHLaser)entity);
	}
	
    protected ResourceLocation getEntityTexture(EntityTHLaser thLaser)
    {
    	if(thLaser.getAnimationCount() < 0 && thLaser instanceof EntityTHSetLaser == false)
    	{
    		return lightTexture;
    	}
        return laserTexture;
    }
    
}
