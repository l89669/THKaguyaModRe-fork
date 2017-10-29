package thKaguyaMod.client.render.shot;

import java.util.Random;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thKaguyaMod.entity.shot.EntityTHHenyoriLaser;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTHHenyoriLaser extends Render
{
	
	/*
	通常レーザーの描画
	*/
	private static final ResourceLocation field_110782_f = new ResourceLocation("thkaguyamod", "textures/Laser.png");
	private Random random = new Random();

    public RenderTHHenyoriLaser()
    {
    }

    public void doRenderTHHenyoriLaser(EntityTHHenyoriLaser entityTHLaser, double xpos, double ypos, double zpos,
            float yaw, float pitch)
    {
        GL11.glPushMatrix();
        bindEntityTexture(entityTHLaser);
        GL11.glTranslatef((float)xpos, (float)ypos, (float)zpos);
		GL11.glDisable(GL11.GL_LIGHTING);
    	//GL11.glEnable(GL11.GL_BLEND);
    	GL11.glEnable(32826);
    	GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
    	GL11.glScalef(1.0F, 1.0F, 1.0F);
        //loadTexture("/textures/Laser.png");
        Tessellator tessellator = Tessellator.instance;
    	
    	int color = entityTHLaser.getShotColor();
    	
    	GL11.glRotatef(entityTHLaser.rotationYaw, 0.0F, 1.0F, 0.0F);
    	GL11.glRotatef(-entityTHLaser.rotationPitch, 1.0F, 0.0F, 0.0F);

    	double centerZ1 = entityTHLaser.getLaserLength() * 1.2D / 2.0D;
    	double centerZ2 = entityTHLaser.getLaserLength() / 2.0D;
    	
    	GL11.glEnable(GL11.GL_CULL_FACE);//表綿描画
    	GL11.glEnable(32826);
    	//renderLaser(tessellator, entityTHLaser.getLaserLength(), entityTHLaser.getShotSize(), centerZ1 - centerZ2, 7, 1.0F, entityTHLaser.getAnimationCount());
    	GL11.glDisable(32826);
    	
    	GL11.glEnable(GL11.GL_BLEND);
    	renderLaser(tessellator, entityTHLaser.getLaserLength() * 1.2D, entityTHLaser.getShotSize() * 1.2F, 0.0D, color, 0.6F, entityTHLaser.getAnimationCount());
    	GL11.glDisable(GL11.GL_BLEND);

    	
    	//GL11.glDisable(GL11.GL_BLEND);
        //GL11.glDisable(32826);
    	GL11.glEnable(GL11.GL_LIGHTING);
    	//GL11.glDisable(GL11.GL_CULL_FACE);//両面描画
    	//GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glPopMatrix();
    }
	
	//レーザーの描画をする
	protected void renderLaser(Tessellator tessellator, double length, float width, double zPos, int color, float alpha, int time)
	{
		float colorR[] = { 224F,   0F,   0F, 224F, 224F,   0F, 255F, 224F};
    	float colorG[] = {   0F,   0F, 224F, 224F,   0F, 224F, 128F, 224F};
    	float colorB[] = {   0F, 224F,   0F,   0F, 224F, 224F,   0F, 224F};
		
		float maxWidth = (float)width;//最大の太さをmaxWidthとして保存
		
    	int zAngleDivNum = 8;//Z軸回転の分割数
    	double angleZ;//Z軸回転変数
    	double angleSpanZ = Math.PI * 2.0D / (double)zAngleDivNum;//Z軸回転の変化量
    	
    	int zDivNum = 13;//レーザーの奥方向への分割数。必ず奇数
    	double zLength = length;//レーザーの長さ（Z方向の長さ、奥行き）
    	double zDivLength = zLength / (double)(zDivNum - 1);//Z方向へ分割したときの1分割分の長さ
    	double zLength2 = zLength / 2.0D;//長さの半分
		//double zPos = 0.0D;
    	//double zPos = -zLength2;//奥行き方向の現在の描画位置
    	double zPosOld = zPos;//ひとつ前の描画位置
    	
    	//へにょりレーザー独特のY軸回転
    	float angleSpanY = (float)Math.PI / 30F;
    	float angleY = angleSpanY * time;//0.0F;
    	
    	//半円を描くようにレーザーが太くなるための変数。cos0 ~ cos180で処理
    	float angle = 0F;
    	float angleSpan = (float)Math.PI / (float)(zDivNum - 1);
    	
    	//レーザーの太さ。Z軸方向への進行で２つ必要
    	width = (float)Math.sin(angle) * maxWidth;
    	float widthOld = width;
    	
		//初期のXとYの座標（レーザーの始点は点）
    	float xPos = (float)Math.sin(angleY) * 3.0F;
    	float yPos = 0F;
    	float xPos2 = (float)Math.sin(angleY - angleSpanY) * 3.0F;
    	float yPos2 = 0F;
    	//初期のXとYの座標
    	float xPosOld = xPos;
    	float yPosOld = yPos;
    	float xPos2Old = xPos2;
    	float yPos2Old = yPos2;
    	
    	//angleY += angleSpanY;


    	

    	//if()
    	
    	//奥行きが長さの半分に達するまで（奥行きの初期値は長さの半分のマイナス値）
    	//while(zPos < zLength2)
		for(int j = 1; j < zDivNum; j++)
		{
    		zPos += zDivLength/* * (float)Math.cos(angleY)*/;//奥行きを１段階増やす
    		widthOld = width;
    		angle += angleSpan;//レーザーの描く半円の角度を更新
    		width = (float)Math.sin(angle) * maxWidth;
    		//XとY座標は初期値、0度のときの座標に戻る。
    		//xPos = (float)Math.sin(angleY);//0F;
    		//yPos = (float)width;
    		//Z軸回転の始点
    		angleZ = angleSpanZ;
    		angleY += angleSpanY;
    		
    		for(int i = 0; i <= zAngleDivNum; i++)
    		{
    			xPos = (float)Math.cos(angleZ) * width + (float)Math.sin(angleY) * 3.0F;
    			yPos = (float)Math.sin(angleZ)/* * (float)Math.cos(angleY)*/ * width;
    			xPos2 = (float)Math.cos(angleZ) * widthOld + (float)Math.sin(angleY - angleSpanY) * 3.0F;
    			yPos2 = (float)Math.sin(angleZ)/* * (float)Math.cos(angleY)*/ * widthOld;
	    			
    			tessellator.startDrawingQuads();
	    		tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], alpha);
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
    		zPosOld = zPos;//古い奥行きを今の奥行きに更新
    	}
	}
	
    protected ResourceLocation func_110781_a(EntityTHHenyoriLaser entityTHLaser)
    {
        return field_110782_f;
    }

    protected ResourceLocation func_110775_a(Entity entity)
    {
        return this.func_110781_a((EntityTHHenyoriLaser)entity);
    }

    public void doRender(Entity entity, double xpos, double ypos, double zpos,
            float yaw, float pitch)
    {
        doRenderTHHenyoriLaser((EntityTHHenyoriLaser)entity, xpos, ypos, zpos, yaw, pitch);
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
