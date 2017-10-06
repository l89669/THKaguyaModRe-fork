/*  1:   */ package thKaguyaMod.client.render;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import java.util.Random;
/*  6:   */ import net.minecraft.client.renderer.Tessellator;
/*  7:   */ import net.minecraft.client.renderer.entity.Render;
/*  8:   */ import net.minecraft.client.renderer.entity.RenderManager;
/*  9:   */ import net.minecraft.entity.Entity;
/* 10:   */ import net.minecraft.util.ResourceLocation;
/* 11:   */ import org.lwjgl.opengl.GL11;
/* 12:   */ import thKaguyaMod.entity.EntityShotMaterial;
/* 13:   */ 
/* 14:   */ @SideOnly(Side.CLIENT)
/* 15:   */ public class RenderShotMaterial
/* 16:   */   extends Render
/* 17:   */ {
/* 18:22 */   private Random random = new Random();
/* 19:   */   
/* 20:   */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/* 21:   */   {
/* 22:30 */     renderShotMaterial((EntityShotMaterial)entity, x, y, z, yaw, pitch);
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void renderShotMaterial(EntityShotMaterial shotMaterial, double x, double y, double z, float yaw, float pitch)
/* 26:   */   {
/* 27:36 */     GL11.glPushMatrix();
/* 28:37 */     bindEntityTexture(shotMaterial);
/* 29:38 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/* 30:39 */     GL11.glDisable(2896);
/* 31:40 */     float sizeRate = 1.0F;
/* 32:41 */     GL11.glScalef(sizeRate, sizeRate, sizeRate);
/* 33:42 */     Tessellator tessellator = Tessellator.instance;
/* 34:43 */     int color2 = 0;
/* 35:44 */     float rvl = 0.0F;
/* 36:45 */     float rul = 0.0F;
/* 37:46 */     float rvr = 1.0F;
/* 38:47 */     float rur = 1.0F;
/* 39:48 */     GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/* 40:49 */     GL11.glRotatef(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
/* 41:   */     
/* 42:   */ 
/* 43:52 */     tessellator.startDrawingQuads();
/* 44:53 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 45:54 */     tessellator.addVertexWithUV(-0.2000000029802322D, 0.2000000029802322D, 0.0D, rvl, rul);
/* 46:55 */     tessellator.addVertexWithUV(0.2000000029802322D, 0.2000000029802322D, 0.0D, rvr, rul);
/* 47:56 */     tessellator.addVertexWithUV(0.2000000029802322D, -0.2000000029802322D, 0.0D, rvr, rur);
/* 48:57 */     tessellator.addVertexWithUV(-0.2000000029802322D, -0.2000000029802322D, 0.0D, rvl, rur);
/* 49:58 */     tessellator.draw();
/* 50:   */     
/* 51:60 */     GL11.glEnable(2896);
/* 52:61 */     GL11.glPopMatrix();
/* 53:   */   }
/* 54:   */   
/* 55:   */   protected ResourceLocation getEntityTexture(Entity entity)
/* 56:   */   {
/* 57:69 */     return getEntityTexture((EntityShotMaterial)entity);
/* 58:   */   }
/* 59:   */   
/* 60:   */   protected ResourceLocation getEntityTexture(EntityShotMaterial shotMaterial)
/* 61:   */   {
/* 62:74 */     return new ResourceLocation("thkaguyamod", "textures/items/material/ShotMaterial.png");
/* 63:   */   }
/* 64:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.RenderShotMaterial
 * JD-Core Version:    0.7.0.1
 */