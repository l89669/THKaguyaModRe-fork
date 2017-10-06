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
/* 11:   */ import thKaguyaMod.entity.shot.EntitySanaeWind;
/* 12:   */ 
/* 13:   */ @SideOnly(Side.CLIENT)
/* 14:   */ public class RenderSanaeWind
/* 15:   */   extends Render
/* 16:   */ {
/* 17:21 */   private static final ResourceLocation windTexture = new ResourceLocation("thkaguyamod", "textures/shot/WindShot.png");
/* 18:   */   
/* 19:   */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/* 20:   */   {
/* 21:30 */     renderSanaeWind((EntitySanaeWind)entity, x, y, z, yaw, pitch);
/* 22:   */   }
/* 23:   */   
/* 24:   */   public void renderSanaeWind(EntitySanaeWind sanaeWind, double x, double y, double z, float yaw, float pitch)
/* 25:   */   {
/* 26:35 */     GL11.glPushMatrix();
/* 27:36 */     bindEntityTexture(sanaeWind);
/* 28:37 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/* 29:38 */     GL11.glDisable(2896);
/* 30:39 */     GL11.glEnable(3042);
/* 31:40 */     GL11.glDisable(2884);
/* 32:   */     
/* 33:42 */     GL11.glBlendFunc(770, 1);
/* 34:   */     
/* 35:44 */     float f2 = 0.5F;
/* 36:45 */     GL11.glScalef(f2 / 1.0F, f2 / 1.0F, f2 / 1.0F);
/* 37:46 */     Tessellator tessellator = Tessellator.instance;
/* 38:47 */     Random random = new Random();
/* 39:48 */     float rand1 = random.nextInt(50) / 100.0F;
/* 40:49 */     float rand2 = random.nextInt(100) / 100.0F;
/* 41:50 */     int pattern = sanaeWind.ticksExisted % 4;
/* 42:51 */     float f3 = (pattern % 2 * 32 + 0) / 64.0F;
/* 43:52 */     float f4 = (pattern % 2 * 32 + 32) / 64.0F;
/* 44:53 */     float f5 = (pattern / 2 * 16 + 0) / 32.0F;
/* 45:54 */     float f6 = (pattern / 2 * 16 + 16) / 32.0F;
/* 46:55 */     float f7 = rand1;
/* 47:56 */     float f8 = 4.0F + rand2;
/* 48:57 */     float f9 = 0.5F;
/* 49:59 */     for (int i = 0; i < 8; i++)
/* 50:   */     {
/* 51:61 */       GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/* 52:62 */       tessellator.startDrawingQuads();
/* 53:63 */       tessellator.setColorRGBA_F(0.1F, 0.9F, 0.6F, 0.7F);
/* 54:64 */       tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 55:65 */       tessellator.addVertexWithUV(0.0F - f7, -1.0F - f9, 0.0D, f3, f6);
/* 56:66 */       tessellator.addVertexWithUV(0.0F + f7, -1.0F - f9, 0.0D, f4, f6);
/* 57:67 */       tessellator.addVertexWithUV(0.0F + f8, 2.0F - f9, 2.0D, f4, f5);
/* 58:68 */       tessellator.addVertexWithUV(0.0F - f8, 2.0F - f9, 2.0D, f3, f5);
/* 59:69 */       tessellator.draw();
/* 60:   */     }
/* 61:72 */     GL11.glDisable(3042);
/* 62:73 */     GL11.glEnable(2884);
/* 63:74 */     GL11.glEnable(2896);
/* 64:75 */     GL11.glPopMatrix();
/* 65:   */   }
/* 66:   */   
/* 67:   */   protected ResourceLocation getEntityTexture(Entity entity)
/* 68:   */   {
/* 69:81 */     return windTexture;
/* 70:   */   }
/* 71:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.shot.RenderSanaeWind
 * JD-Core Version:    0.7.0.1
 */