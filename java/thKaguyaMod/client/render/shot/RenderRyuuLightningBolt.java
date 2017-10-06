/*  1:   */ package thKaguyaMod.client.render.shot;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import java.util.Random;
/*  6:   */ import net.minecraft.client.renderer.Tessellator;
/*  7:   */ import net.minecraft.client.renderer.entity.Render;
/*  8:   */ import net.minecraft.entity.Entity;
/*  9:   */ import net.minecraft.util.ResourceLocation;
/* 10:   */ import org.lwjgl.opengl.GL11;
/* 11:   */ import thKaguyaMod.client.THKaguyaRenderLib;
/* 12:   */ import thKaguyaMod.entity.shot.EntityDragonNeckJewel;
/* 13:   */ 
/* 14:   */ @SideOnly(Side.CLIENT)
/* 15:   */ public class RenderRyuuLightningBolt
/* 16:   */   extends Render
/* 17:   */ {
/* 18:22 */   private static final ResourceLocation dragonBulletTexture = new ResourceLocation("thkaguyamod", "textures/thKaguyaTerrain.png");
/* 19:23 */   private Random random = new Random();
/* 20:   */   
/* 21:   */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/* 22:   */   {
/* 23:32 */     renderDragonBullet((EntityDragonNeckJewel)entity, x, y, z, yaw, pitch);
/* 24:   */   }
/* 25:   */   
/* 26:   */   public void renderDragonBullet(EntityDragonNeckJewel dragonBullet, double x, double y, double z, float yaw, float pitch)
/* 27:   */   {
/* 28:37 */     GL11.glPushMatrix();
/* 29:38 */     bindEntityTexture(dragonBullet);
/* 30:39 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/* 31:40 */     GL11.glEnable(32826);
/* 32:41 */     float f2 = 1.0F;
/* 33:42 */     GL11.glScalef(f2 / 1.0F, f2 / 1.0F, f2 / 1.0F);
/* 34:43 */     Tessellator tessellator = Tessellator.instance;
/* 35:44 */     int color = 17;
/* 36:45 */     if (this.random.nextInt(3) == 0) {
/* 37:47 */       color = this.random.nextInt(5) + 16;
/* 38:   */     }
/* 39:49 */     float f3 = (color % 16 * 16 + 0) / 256.0F;
/* 40:50 */     float f4 = (color % 16 * 16 + 16) / 256.0F;
/* 41:51 */     float f5 = (color / 16 * 16 + 0) / 256.0F;
/* 42:52 */     float f6 = (color / 16 * 16 + 16) / 256.0F;
/* 43:53 */     float f7 = 1.0F;
/* 44:54 */     float f8 = 0.5F;
/* 45:55 */     float f9 = 0.25F;
/* 46:   */     
/* 47:57 */     THKaguyaRenderLib.getGLRotatefByTherdPersonView(this.renderManager);
/* 48:58 */     tessellator.startDrawingQuads();
/* 49:59 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 50:60 */     tessellator.addVertexWithUV(0.0F - f8, 0.0F - f9, 0.0D, f3, f6);
/* 51:61 */     tessellator.addVertexWithUV(f7 - f8, 0.0F - f9, 0.0D, f4, f6);
/* 52:62 */     tessellator.addVertexWithUV(f7 - f8, 1.0F - f9, 0.0D, f4, f5);
/* 53:63 */     tessellator.addVertexWithUV(0.0F - f8, 1.0F - f9, 0.0D, f3, f5);
/* 54:64 */     tessellator.draw();
/* 55:65 */     GL11.glDisable(32826);
/* 56:66 */     GL11.glPopMatrix();
/* 57:   */   }
/* 58:   */   
/* 59:   */   protected ResourceLocation getEntityTexture(Entity entity)
/* 60:   */   {
/* 61:71 */     return dragonBulletTexture;
/* 62:   */   }
/* 63:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.shot.RenderRyuuLightningBolt
 * JD-Core Version:    0.7.0.1
 */