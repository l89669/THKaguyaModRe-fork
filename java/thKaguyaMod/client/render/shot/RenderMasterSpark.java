package thKaguyaMod.client.render.shot;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thKaguyaMod.entity.shot.EntityMasterSpark;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMasterSpark extends Render
{
	
	/*
	マスタースパークの描画
	*/
	private static final ResourceLocation masuterSparkTexture = new ResourceLocation("thkaguyamod", "textures/Laser.png");

    public RenderMasterSpark()
    {
    }
    
    @Override
    public void doRender(Entity entity, double xpos, double ypos, double zpos,
            float yaw, float pitch)
    {
        renderMasterSpark((EntityMasterSpark)entity, xpos, ypos, zpos, yaw, pitch);
    }

    public void renderMasterSpark(EntityMasterSpark masterSpark, double xpos, double ypos, double zpos,
            float yaw, float pitch)
    {
        GL11.glPushMatrix();
        bindEntityTexture(masterSpark);
    	GL11.glTranslatef((float)xpos, (float)ypos, (float)zpos);
		GL11.glDisable(GL11.GL_LIGHTING);

    	GL11.glDisable(GL11.GL_CULL_FACE);//両面描画
    	//GL11.glDepthMask(true);
    	GL11.glScalef(1.0F, 1.0F, 1.0F);
        Tessellator tessellator = Tessellator.instance;
    	
    	GL11.glRotatef(yaw, 0.0F, 1.0F, 0.0F);
    	GL11.glRotatef(-masterSpark.rotationPitch, 1.0F, 0.0F, 0.0F);
    	
    	float ticks = (float)masterSpark.getIniTime()*10.0F;
    	
    	if(masterSpark.getIniTime() > 30)
    	{
    		//GL11.glEnable(32826);
    		renderMasterSparkLaser(tessellator, masterSpark.getMaxLength(), 4.2F, 1.0F, false, ticks);
    		//GL11.glDisable(32826);
    		GL11.glEnable(GL11.GL_BLEND);
    		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
    		renderMasterSparkLaser(tessellator, masterSpark.getMaxLength(), 5.0F, 0.6F, true, ticks);
    		GL11.glDisable(GL11.GL_BLEND);
    	}
    	else
    	{
    		//GL11.glEnable(32826);
    		renderMasterSparkLaser(tessellator, masterSpark.getMaxLength(), 0.168F, 1.0F, false, ticks);
    		//GL11.glDisable(32826);
    		GL11.glEnable(GL11.GL_BLEND);
    		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
    		renderMasterSparkLaser(tessellator, masterSpark.getMaxLength(), 0.2F, 0.6F, true, ticks);
    		GL11.glDisable(GL11.GL_BLEND);
    	}
    	
    	GL11.glEnable(GL11.GL_LIGHTING);
    	GL11.glEnable(GL11.GL_CULL_FACE);//表綿描画
    	GL11.glPopMatrix();
    	
        
    	//GL11.glDepthMask(false);
    }
	
	protected void renderMasterSparkLaser(Tessellator tessellator, double length, float width, float alpha, boolean rainbowFlag, float ticks)
	{
		float colorR[] = { 255F, 255F, 224F,   0F,   0F,   0F, 224F, 255F};
    	float colorG[] = {   0F, 165F, 224F, 255F,   0F, 255F,   0F, 255F};
    	float colorB[] = {   0F,   0F,   0F,   0F, 255F, 255F, 224F, 255F};
		
		float maxWidth = (float)width;//最大の太さをmaxWidthとして保存
		
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
	
    protected ResourceLocation getEntityTexture(EntityMasterSpark masterSpark)
    {
        return masuterSparkTexture;
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return getEntityTexture((EntityMasterSpark)entity);
	}
}
