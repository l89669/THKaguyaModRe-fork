/*  1:   */ package thKaguyaMod.client;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.renderer.entity.RenderManager;
/*  4:   */ import net.minecraft.client.settings.GameSettings;
/*  5:   */ import org.lwjgl.opengl.GL11;
/*  6:   */ 
/*  7:   */ public class THKaguyaRenderLib
/*  8:   */ {
/*  9:   */   public static final void getGLRotatefByTherdPersonView(RenderManager renderManager)
/* 10:   */   {
/* 11:17 */     GL11.glRotatef(180.0F - renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/* 12:20 */     if (renderManager.options.thirdPersonView == 2) {
/* 13:22 */       GL11.glRotatef(renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
/* 14:   */     } else {
/* 15:27 */       GL11.glRotatef(-renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
/* 16:   */     }
/* 17:   */   }
/* 18:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.THKaguyaRenderLib
 * JD-Core Version:    0.7.0.1
 */