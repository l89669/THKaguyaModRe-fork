package thKaguyaMod.client.render.living;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thKaguyaMod.client.model.living.ModelTHFairy;
import thKaguyaMod.entity.living.EntityTHFairy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/** 妖精を描画する */
@SideOnly(Side.CLIENT)
public class RenderTHFairy extends RenderLiving
{	

    public RenderTHFairy()
    {
        super(new ModelTHFairy(), 0.25F);
        
    }
    
    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
    	super.doRender(entity, x, y, z, yaw, pitch);
    	this.renderTHFairy((EntityTHFairy)entity, x, y, z, yaw, pitch);
    	
    }

	public void renderTHFairy(EntityTHFairy thFairy, double x, double y, double z, float yaw, float pitch)
	{
		
		//ゾンビフェアリーかつ体力がないなら、輪っかを描画する
        if(thFairy.getHealth() <= 0)
        {
        	if(thFairy.getDanmakuPattern() < 0)
        	{
	        	GL11.glDisable(GL11.GL_LIGHTING);
	    		GL11.glEnable(GL11.GL_BLEND);
	        	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
	        	GL11.glDisable(GL11.GL_CULL_FACE);//両面描画
	
	        	Tessellator tessellator = Tessellator.instance;
	        	float f3 = 0.75F;
	            float f4 = 1.0F;
	            float f5 = 0.5F;
	            float f6 = 0.75F;
	            float f7 = 1.0F;
	            float f8 = 0.5F;
	            float f9 = 0.5F;
	            
	            tessellator.startDrawingQuads();
	            tessellator.setNormal(0.0F, 1.0F, 0.0F);
	            tessellator.addVertexWithUV( x - 0.5F, y + 2.5F, z + 0.5D, f3, f6);
	            tessellator.addVertexWithUV( x + 0.5F, y + 2.5F, z + 0.5D, f4, f6);
	            tessellator.addVertexWithUV( x + 0.5F, y + 2.5F, z - 0.5D, f4, f5);
	            tessellator.addVertexWithUV( x - 0.5F, y + 2.5F, z - 0.5D, f3, f5);
	    		tessellator.draw();
	    		GL11.glEnable(GL11.GL_CULL_FACE);//両面描画
	    		GL11.glDisable(GL11.GL_BLEND);
	    		GL11.glEnable(GL11.GL_LIGHTING);
        	}
        	/*else if(thFairy.deathTime <= 7)
        	{
            	Tessellator tessellator = Tessellator.instance;
            	GL11.glTranslatef((float)x, (float)y, (float)z);
    	        //GL11.glPushMatrix();
    	        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
    	        //GL11.glDisable(GL11.GL_CULL_FACE);//表綿描画
    	        //GL11.glDepthFunc(GL11.GL_ALWAYS);
    	        //GL11.glDepthMask(false);
    	        GL11.glDepthFunc(GL11.GL_LEQUAL);
    	        GL11.glEnable(GL11.GL_BLEND);
    	        
    	        //GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
    	        GL11.glBlendFunc(GL11.GL_ONE_MINUS_DST_COLOR, GL11.GL_ZERO);
    	    	this.bindTexture(darkTexture);
    	    	double size = 0.7D * thFairy.deathTime;
    	    	float alpha = 0.5F;
    	    	//if(privateSquare.ticksExisted <= 40)
    	    	{
    	    		if(THKaguyaConfig.useTimeStopEffect)
    	    		{
    	    			renderDark(tessellator, (float)size, (float)size, 0.0D, 0.4F, 0);
    	    		}
    	    	}
    	    	GL11.glDepthFunc(GL11.GL_LEQUAL);
    	    	GL11.glDisable(GL11.GL_BLEND);
    	    	//GL11.glDepthFunc(GL11.GL_EQUAL);
    	
    	    	GL11.glDepthMask(true);
    	    	GL11.glEnable(GL11.GL_CULL_FACE);//表綿描画
    	    	GL11.glDisable(GL12.GL_RESCALE_NORMAL);
    	    	//GL11.glPopMatrix();
        	}*/
        }
	}
	
	//暗闇の描画をする
	protected void renderDark(Tessellator tessellator, double length, float width, double zPos, float alpha, int time)
	{
		
		float maxWidth = (float)width / 2.0F;//最大の太さをmaxWidthとして保存
		
    	int zAngleDivNum = 18;//Z軸回転の分割数
    	float zSpan = 360F / zAngleDivNum;
    	double angleZ = 0F;//Z軸回転変数
    	double angleSpanZ = Math.PI * 2.0D / (double)zAngleDivNum;//Z軸回転の変化量
    	
    	int zDivNum = 9;//レーザーの奥方向への分割数。必ず奇数
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
    	
    	//奥行きが長さの半分に達するまで（奥行きの初期値は長さの半分のマイナス値）
    	//while(zPos < zLength2)
		for(int j = 0; j < zDivNum; j++)
		{
    		//zPos += Math.sin(angle)* zDivLength;//奥行きを１段階増やす
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
	    		tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F , alpha);
	    		tessellator.setNormal(0.0F, 1.0F, 0.0F);
	    		tessellator.addVertexWithUV(  xPos    , yPos    , zPos   , 1.0F, 0.0F);
	    		tessellator.addVertexWithUV(  xPosOld , yPosOld , zPos   , 0.0F, 0.0F);
	    		tessellator.addVertexWithUV(  xPos2Old, yPos2Old, zPosOld, 0.0F, 1.0F);
	        	tessellator.addVertexWithUV(  xPos2   , yPos2   , zPosOld, 1.0F, 1.0F);
	        	
	        	
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

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.getEntityTexture((EntityTHFairy)entity);
    }
    
    protected ResourceLocation getEntityTexture(EntityTHFairy thFairy)
    {
    	ResourceLocation resourceLocation;
    	if(thFairy.getForm() >= 0)
    	{
    		resourceLocation = new ResourceLocation("thkaguyamod", "textures/mob/FairyTexture_" + thFairy.getDanmakuPattern() % 3 + ".png");
    	}
    	else
    	{
    		resourceLocation = new ResourceLocation("thkaguyamod", "textures/mob/ZombieFairyTexture.png");
    	}
    	
    	
        return resourceLocation;
    }
}
