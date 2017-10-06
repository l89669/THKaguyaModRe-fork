/*  1:   */ package thKaguyaMod.client.render.effect;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import net.minecraft.client.renderer.Tessellator;
/*  6:   */ import net.minecraft.client.renderer.entity.Render;
/*  7:   */ import net.minecraft.entity.Entity;
/*  8:   */ import net.minecraft.util.ResourceLocation;
/*  9:   */ import org.lwjgl.opengl.GL11;
/* 10:   */ import thKaguyaMod.entity.effect.EntityHakurouReflecter;
/* 11:   */ 
/* 12:   */ @SideOnly(Side.CLIENT)
/* 13:   */ public class RenderHakurouReflecter
/* 14:   */   extends Render
/* 15:   */ {
/* 16:19 */   private static final ResourceLocation hakurouReflecterTexture = new ResourceLocation("thkaguyamod", "textures/HakurouReflect.png");
/* 17:   */   
/* 18:   */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/* 19:   */   {
/* 20:28 */     doRenderHakurouReflecter((EntityHakurouReflecter)entity, x, y, z, yaw, pitch);
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void doRenderHakurouReflecter(EntityHakurouReflecter hakurouReflecter, double x, double y, double z, float yaw, float pitch)
/* 24:   */   {
/* 25:33 */     GL11.glPushMatrix();
/* 26:34 */     bindTexture(getEntityTexture(hakurouReflecter));
/* 27:35 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/* 28:36 */     GL11.glDisable(2896);
/* 29:37 */     GL11.glEnable(3042);
/* 30:38 */     GL11.glDisable(2884);
/* 31:39 */     GL11.glBlendFunc(1, 769);
/* 32:40 */     GL11.glDepthMask(false);
/* 33:41 */     float size = 1.0F;
/* 34:42 */     GL11.glScalef(size, size, size);
/* 35:   */     
/* 36:44 */     Tessellator tessellator = Tessellator.instance;
/* 37:   */     
/* 38:46 */     float minU = 0.0F;
/* 39:47 */     float maxU = 0.5F;
/* 40:48 */     float minV = 0.0F;
/* 41:49 */     float maxV = 1.0F;
/* 42:50 */     GL11.glRotatef(yaw, 0.0F, 1.0F, 0.0F);
/* 43:51 */     GL11.glRotatef(hakurouReflecter.rotationPitch, 1.0F, 0.0F, 0.0F);
/* 44:   */     
/* 45:53 */     float angle = hakurouReflecter.ticksExisted / 5.0F;
/* 46:54 */     float span = 0.3926991F;
/* 47:55 */     double length = 1.8D;
/* 48:56 */     for (int i = 0; i < 3; i++)
/* 49:   */     {
/* 50:58 */       double xPos1 = Math.cos(angle) * length;
/* 51:59 */       double xPos2 = Math.cos(angle + span) * length;
/* 52:60 */       double yPos1 = Math.sin(angle) * length;
/* 53:61 */       double yPos2 = Math.sin(angle + span) * length;
/* 54:62 */       for (int j = 0; j < 16; j++)
/* 55:   */       {
/* 56:64 */         tessellator.startDrawingQuads();
/* 57:65 */         tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 0.2F + j * 0.3F);
/* 58:66 */         tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 59:67 */         tessellator.addVertexWithUV(xPos1, yPos1, 0.0D, minU, maxV);
/* 60:68 */         tessellator.addVertexWithUV(xPos2, yPos2, 0.0D, maxU, maxV);
/* 61:69 */         tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, maxU, minV);
/* 62:70 */         tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, minU, minV);
/* 63:71 */         tessellator.draw();
/* 64:72 */         angle += span;
/* 65:73 */         xPos1 = Math.cos(angle) * length;
/* 66:74 */         xPos2 = Math.cos(angle + span) * length;
/* 67:75 */         yPos1 = Math.sin(angle) * length;
/* 68:76 */         yPos2 = Math.sin(angle + span) * length;
/* 69:   */       }
/* 70:78 */       length -= 0.3D;
/* 71:   */     }
/* 72:80 */     GL11.glDepthMask(true);
/* 73:81 */     GL11.glEnable(2884);
/* 74:82 */     GL11.glDisable(3042);
/* 75:83 */     GL11.glEnable(2896);
/* 76:84 */     GL11.glPopMatrix();
/* 77:   */   }
/* 78:   */   
/* 79:   */   protected ResourceLocation getEntityTexture(Entity entity)
/* 80:   */   {
/* 81:89 */     return hakurouReflecterTexture;
/* 82:   */   }
/* 83:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.effect.RenderHakurouReflecter
 * JD-Core Version:    0.7.0.1
 */