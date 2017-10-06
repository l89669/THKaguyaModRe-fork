/*  1:   */ package thKaguyaMod.client.render;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import java.util.Random;
/*  6:   */ import net.minecraft.client.renderer.Tessellator;
/*  7:   */ import net.minecraft.client.renderer.entity.Render;
/*  8:   */ import net.minecraft.entity.Entity;
/*  9:   */ import net.minecraft.util.ResourceLocation;
/* 10:   */ import org.lwjgl.opengl.GL11;
/* 11:   */ import thKaguyaMod.entity.effect.EntityCirnoIceBox;
/* 12:   */ 
/* 13:   */ @SideOnly(Side.CLIENT)
/* 14:   */ public class RenderCirnoIceBox
/* 15:   */   extends Render
/* 16:   */ {
/* 17:20 */   private static final ResourceLocation iceBoxTexture = new ResourceLocation("textures/blocks/ice.png");
/* 18:21 */   private Random random = new Random();
/* 19:   */   
/* 20:   */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/* 21:   */   {
/* 22:30 */     renderCirnoIceBox((EntityCirnoIceBox)entity, x, y, z, yaw, pitch);
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void renderCirnoIceBox(EntityCirnoIceBox iceBox, double x, double y, double z, float yaw, float pitch)
/* 26:   */   {
/* 27:35 */     GL11.glPushMatrix();
/* 28:36 */     bindTexture(getEntityTexture(iceBox));
/* 29:37 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/* 30:38 */     GL11.glDisable(2896);
/* 31:39 */     GL11.glEnable(3042);
/* 32:40 */     GL11.glBlendFunc(1, 769);
/* 33:41 */     GL11.glDisable(2884);
/* 34:42 */     GL11.glScalef(1.0F, 1.0F, 1.0F);
/* 35:43 */     Tessellator tessellator = Tessellator.instance;
/* 36:   */     
/* 37:45 */     float width = iceBox.getIceBoxWidth() / 2.0F + 0.4F;
/* 38:46 */     float height = iceBox.getIceBoxHeight() + 0.8F;
/* 39:   */     
/* 40:48 */     float[] px = { -width, width, width, -width, -width, width, width, -width };
/* 41:49 */     float[] py = { height, height, height, height, 0.0F, 0.0F, 0.0F, 0.0F };
/* 42:50 */     float[] pz = { width, width, -width, -width, -width, -width, width, width };
/* 43:   */     
/* 44:52 */     int[][] p = { { 0, 1, 2, 3 }, { 3, 2, 5, 4 }, { 2, 1, 6, 5 }, { 1, 0, 7, 6 }, { 0, 3, 4, 7 }, { 4, 5, 6, 7 } };
/* 45:60 */     for (int i = 0; i < 6; i++)
/* 46:   */     {
/* 47:62 */       tessellator.startDrawingQuads();
/* 48:63 */       tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 49:64 */       tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
/* 50:65 */       tessellator.addVertexWithUV(px[p[i][0]], py[p[i][0]], pz[p[i][0]], 0.0D, 1.0D);
/* 51:66 */       tessellator.addVertexWithUV(px[p[i][1]], py[p[i][1]], pz[p[i][1]], 1.0D, 1.0D);
/* 52:67 */       tessellator.addVertexWithUV(px[p[i][2]], py[p[i][2]], pz[p[i][2]], 1.0D, 0.0D);
/* 53:68 */       tessellator.addVertexWithUV(px[p[i][3]], py[p[i][3]], pz[p[i][3]], 0.0D, 0.0D);
/* 54:69 */       tessellator.draw();
/* 55:   */     }
/* 56:72 */     GL11.glDisable(3042);
/* 57:73 */     GL11.glEnable(2884);
/* 58:74 */     GL11.glEnable(2896);
/* 59:75 */     GL11.glPopMatrix();
/* 60:   */   }
/* 61:   */   
/* 62:   */   protected ResourceLocation getEntityTexture(Entity entity)
/* 63:   */   {
/* 64:80 */     return iceBoxTexture;
/* 65:   */   }
/* 66:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.RenderCirnoIceBox
 * JD-Core Version:    0.7.0.1
 */