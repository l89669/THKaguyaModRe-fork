package thKaguyaMod.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import thKaguyaMod.client.model.ModelPrivateSquare;
import thKaguyaMod.entity.item.EntitySakuyaWatch;
import thKaguyaMod.init.THKaguyaConfig;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/** 咲夜の懐中時計の描画 */
@SideOnly(Side.CLIENT)
public class RenderPrivateSquare extends Render
{
	private static final ResourceLocation sakuyaWatchTexture = new ResourceLocation("thkaguyamod", "textures/SakuyaWatchTexture.png");
	ResourceLocation darkTexture = new ResourceLocation("thkaguyamod", "textures/DarkTexture.png");
	protected ModelBase modelPrivateSquare;

	/** 咲夜の懐中時計の描画のコンストラクタ */
    public RenderPrivateSquare()
    {
        shadowSize = 0.5F;//多分影のサイズ
        modelPrivateSquare = new ModelPrivateSquare();
    }
    
    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
        renderPrivateSquare((EntitySakuyaWatch)entity, x, y, z, yaw, pitch);
    }

  
    public void renderPrivateSquare(EntitySakuyaWatch privateSquare, double x, double y, double z, float yaw, float pitch)
    {
        GL11.glPushMatrix();
        
        GL11.glTranslatef((float)x, (float)y, (float)z);
    	
        GL11.glScalef(0.3F, 0.3F, 0.3F);//倍率　縦方向 高さ　幅
        
        Tessellator tessellator = Tessellator.instance;
        
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glDisable(GL11.GL_CULL_FACE);//表綿描画
        //GL11.glDepthFunc(GL11.GL_ALWAYS);
        GL11.glDepthMask(false);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        //GL11.glDepthFunc(GL11.GL_GEQUAL);
        GL11.glEnable(GL11.GL_BLEND);
        
    	
        GL11.glBlendFunc(GL11.GL_ONE_MINUS_DST_COLOR, GL11.GL_ZERO);
        
        //GL11.glBlendFunc(GL11.GL_ONE_MINUS_DST_COLOR, GL11.GL_SRC_COLOR);
        
        //GL11.glBlendFunc(GL11.GL_ZERO, GL11.GL_SRC_COLOR);
    	this.bindTexture(darkTexture);
    	double size = privateSquare.ticksExisted * 12;
    	float alpha = 1.0F;
    	if(size > 240.0D)
    	{
    		size = 240.0D;
    	}
    	//if(privateSquare.ticksExisted <= 40)
    	{
    		if(privateSquare.ticksExisted > 20)
    		{
    			alpha -= (privateSquare.ticksExisted - 20) * 0.05F;
    		}
    		if(THKaguyaConfig.useTimeStopEffect)
    		{
    			renderDark(tessellator, size, (float)size, 0.0D, 1.0F, 0);
    		}
    	}
    	//GL11.glDepthFunc(GL11.GL_LEQUAL);
    	GL11.glDisable(GL11.GL_BLEND);
    	//GL11.glDepthFunc(GL11.GL_EQUAL);

    	GL11.glDepthMask(true);
    	GL11.glEnable(GL11.GL_CULL_FACE);//表綿描画
    	GL11.glDisable(GL12.GL_RESCALE_NORMAL);
    	
    	bindEntityTexture(privateSquare);
    	GL11.glRotatef(180F - yaw + privateSquare.ticksExisted * 7F, 0.0F, 1.0F, 0.0F);
    	modelPrivateSquare.render(privateSquare, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);//最後の引数以外関係ない？　最後の引数は大きさの倍率
        GL11.glPopMatrix();
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

    //テクスチャを返す
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return sakuyaWatchTexture;
	}
}
