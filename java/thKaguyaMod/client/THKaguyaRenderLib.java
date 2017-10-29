package thKaguyaMod.client;

import net.minecraft.client.renderer.entity.RenderManager;

import org.lwjgl.opengl.GL11;

/** 五つの難題MOD+用の描画補助ライブラリ */
public class THKaguyaRenderLib 
{
    /** カメラの位置で描画角度を変える。
     * 主に常時正面を向いている必要のある描画処理向け
     *	@param RenderManager : レンダーマネージャー。Render継承クラスならrenderManagerと指定すればOKだと思う
     */
    public static final void getGLRotatefByTherdPersonView(RenderManager renderManager)
    {
    	// 共通処理
    	GL11.glRotatef(180 - renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
    	
    	// 正面にカメラがある場合
		if(renderManager.options.thirdPersonView == 2)
		{
			GL11.glRotatef(renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		}
		// 後方にカメラがある場合
		else
		{
			GL11.glRotatef(-renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		}
    }
}
