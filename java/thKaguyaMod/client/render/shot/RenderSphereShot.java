package thKaguyaMod.client.render.shot;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thKaguyaMod.THShotLib;
import thKaguyaMod.client.THKaguyaRenderLib;

public class RenderSphereShot extends Render
{
	public RenderSphereShot()
	{
		
	}
	
	public void render(float size, int color)
	{
		Tessellator tessellator = Tessellator.instance;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
		GL11.glDepthMask(true);
		GL11.glScalef(size, size, size);
		// プレイヤーに常に正面を向けるようにする
        THKaguyaRenderLib.getGLRotatefByTherdPersonView(renderManager);
		float width = 1.0F;
		float height = 1.0F;
		
		//ハート弾か大弾なら
		if(color >= THShotLib.HEART[0])
		{
			if(color >= THShotLib.BIG[0])
			{
				width = 1.5F;
				height = 1.5F;
			}
			float u1 = (float)((color % 16) * 32 +  0) / 256F;
	        float u2 = (float)((color % 16) * 32 + 32) / 256F;
	        float v1 = (float)((color / 16) * 32 +  0) / 256F;
	        float v2 = (float)((color / 16) * 32 + 32) / 256F;
	        tessellator.startDrawingQuads();
	        tessellator.addVertexWithUV(-width, -height, 0.0D, u1, v2);
	        tessellator.addVertexWithUV( width, -height, 0.0D, u2, v2);
	        tessellator.addVertexWithUV( width,  height, 0.0D, u2, v1);
	        tessellator.addVertexWithUV(-width,  height, 0.0D, u1, v1);
			tessellator.draw();
		}
		//その他の円形弾なら
		else
		{
			float u1 = (float)((color % 16) * 16 +  0) / 256F;
	        float u2 = (float)((color % 16) * 16 + 16) / 256F;
	        float v1 = (float)((color / 16) * 16 +  0) / 256F;
	        float v2 = (float)((color / 16) * 16 + 16) / 256F;
	        tessellator.startDrawingQuads();
	        tessellator.addVertexWithUV(-width, -height, 0.0D, u1, v2);
	        tessellator.addVertexWithUV( width, -height, 0.0D, u2, v2);
	        tessellator.addVertexWithUV( width,  height, 0.0D, u2, v1);
	        tessellator.addVertexWithUV(-width,  height, 0.0D, u1, v1);
			tessellator.draw();
		}
		GL11.glDepthMask(false);
		GL11.glDisable(GL11.GL_BLEND);
	}

	@Override
	public void doRender(Entity var1, double var2, double var4, double var6,
			float var8, float var9) {
		
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
