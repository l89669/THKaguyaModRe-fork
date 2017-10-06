/*  1:   */ package thKaguyaMod.client.render.shot;
/*  2:   */ 
/*  3:   */ import net.minecraft.client.renderer.Tessellator;
/*  4:   */ import net.minecraft.client.renderer.entity.Render;
/*  5:   */ import net.minecraft.entity.Entity;
/*  6:   */ import net.minecraft.util.ResourceLocation;
/*  7:   */ import org.lwjgl.opengl.GL11;
/*  8:   */ import thKaguyaMod.client.THKaguyaRenderLib;
/*  9:   */ 
/* 10:   */ public class RenderSphereShot
/* 11:   */   extends Render
/* 12:   */ {
/* 13:   */   public void render(float size, int color)
/* 14:   */   {
/* 15:22 */     Tessellator tessellator = Tessellator.instance;
/* 16:23 */     GL11.glEnable(3042);
/* 17:24 */     GL11.glBlendFunc(1, 769);
/* 18:25 */     GL11.glDepthMask(true);
/* 19:26 */     GL11.glScalef(size, size, size);
/* 20:   */     
/* 21:28 */     THKaguyaRenderLib.getGLRotatefByTherdPersonView(this.renderManager);
/* 22:29 */     float width = 1.0F;
/* 23:30 */     float height = 1.0F;
/* 24:33 */     if (color >= thKaguyaMod.THShotLib.HEART[0])
/* 25:   */     {
/* 26:35 */       if (color >= thKaguyaMod.THShotLib.BIG[0])
/* 27:   */       {
/* 28:37 */         width = 1.5F;
/* 29:38 */         height = 1.5F;
/* 30:   */       }
/* 31:40 */       float u1 = (color % 16 * 32 + 0) / 256.0F;
/* 32:41 */       float u2 = (color % 16 * 32 + 32) / 256.0F;
/* 33:42 */       float v1 = (color / 16 * 32 + 0) / 256.0F;
/* 34:43 */       float v2 = (color / 16 * 32 + 32) / 256.0F;
/* 35:44 */       tessellator.startDrawingQuads();
/* 36:45 */       tessellator.addVertexWithUV(-width, -height, 0.0D, u1, v2);
/* 37:46 */       tessellator.addVertexWithUV(width, -height, 0.0D, u2, v2);
/* 38:47 */       tessellator.addVertexWithUV(width, height, 0.0D, u2, v1);
/* 39:48 */       tessellator.addVertexWithUV(-width, height, 0.0D, u1, v1);
/* 40:49 */       tessellator.draw();
/* 41:   */     }
/* 42:   */     else
/* 43:   */     {
/* 44:54 */       float u1 = (color % 16 * 16 + 0) / 256.0F;
/* 45:55 */       float u2 = (color % 16 * 16 + 16) / 256.0F;
/* 46:56 */       float v1 = (color / 16 * 16 + 0) / 256.0F;
/* 47:57 */       float v2 = (color / 16 * 16 + 16) / 256.0F;
/* 48:58 */       tessellator.startDrawingQuads();
/* 49:59 */       tessellator.addVertexWithUV(-width, -height, 0.0D, u1, v2);
/* 50:60 */       tessellator.addVertexWithUV(width, -height, 0.0D, u2, v2);
/* 51:61 */       tessellator.addVertexWithUV(width, height, 0.0D, u2, v1);
/* 52:62 */       tessellator.addVertexWithUV(-width, height, 0.0D, u1, v1);
/* 53:63 */       tessellator.draw();
/* 54:   */     }
/* 55:65 */     GL11.glDepthMask(false);
/* 56:66 */     GL11.glDisable(3042);
/* 57:   */   }
/* 58:   */   
/* 59:   */   public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {}
/* 60:   */   
/* 61:   */   protected ResourceLocation getEntityTexture(Entity var1)
/* 62:   */   {
/* 63:78 */     return null;
/* 64:   */   }
/* 65:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.shot.RenderSphereShot
 * JD-Core Version:    0.7.0.1
 */