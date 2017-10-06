/*  1:   */ package thKaguyaMod.client.render;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import java.util.Random;
/*  6:   */ import net.minecraft.client.renderer.Tessellator;
/*  7:   */ import net.minecraft.client.renderer.entity.Render;
/*  8:   */ import net.minecraft.client.renderer.entity.RenderManager;
/*  9:   */ import net.minecraft.entity.Entity;
/* 10:   */ import net.minecraft.util.MathHelper;
/* 11:   */ import net.minecraft.util.ResourceLocation;
/* 12:   */ import org.lwjgl.opengl.GL11;
/* 13:   */ import thKaguyaMod.entity.effect.EntityAjaRedStoneEffect;
/* 14:   */ 
/* 15:   */ @SideOnly(Side.CLIENT)
/* 16:   */ public class RenderAjaRedStoneEffect
/* 17:   */   extends Render
/* 18:   */ {
/* 19:22 */   private static final ResourceLocation ajaRedStoneEffectTexture = new ResourceLocation("thkaguyamod", "textures/MiracleCircle.png");
/* 20:23 */   private Random random = new Random();
/* 21:   */   
/* 22:   */   public void doRender(Entity entity, double x, double y, double z, float f, float f1)
/* 23:   */   {
/* 24:33 */     doRenderAjaRedStoneEffect((EntityAjaRedStoneEffect)entity, x, y, z, f, f1);
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void doRenderAjaRedStoneEffect(EntityAjaRedStoneEffect ajaRedStoneEffect, double x, double y, double z, float yaw, float pitch)
/* 28:   */   {
/* 29:38 */     GL11.glPushMatrix();
/* 30:39 */     bindTexture(getEntityTexture(ajaRedStoneEffect));
/* 31:40 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/* 32:41 */     GL11.glDisable(2896);
/* 33:   */     
/* 34:43 */     GL11.glEnable(3042);
/* 35:44 */     GL11.glDisable(2884);
/* 36:45 */     GL11.glBlendFunc(1, 769);
/* 37:46 */     GL11.glDepthMask(false);
/* 38:47 */     GL11.glScalef(1.0F, 1.0F, 1.0F);
/* 39:   */     
/* 40:49 */     Tessellator tessellator = Tessellator.instance;
/* 41:50 */     GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/* 42:51 */     GL11.glRotatef(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
/* 43:   */     
/* 44:53 */     int lightNum = ajaRedStoneEffect.getLightPower();
/* 45:54 */     lightNum *= lightNum;
/* 46:55 */     float widthX = 0.01F;
/* 47:56 */     float lengthY = lightNum / 120.0F;
/* 48:57 */     float lengthY2 = lengthY;
/* 49:   */     
/* 50:   */ 
/* 51:60 */     float iniAngle = this.random.nextInt(36000) / 100.0F;
/* 52:61 */     float angleSpan = 360.0F / lightNum;
/* 53:   */     
/* 54:63 */     float umin = 0.5F;
/* 55:64 */     float umax = 0.59375F;
/* 56:65 */     float vmin = 0.0F;
/* 57:66 */     float vmax = 1.0F;
/* 58:67 */     GL11.glRotatef(MathHelper.sin(iniAngle / 180.0F * 3.141593F) / 3.141593F * 180.0F, 0.0F, 0.0F, 1.0F);
/* 59:68 */     for (int i = 0; i < lightNum; i++)
/* 60:   */     {
/* 61:70 */       lengthY2 = lengthY * (0.2F + this.random.nextInt(8000) / 8000.0F);
/* 62:71 */       double zPos = this.random.nextInt(10000) / 10000.0D;
/* 63:72 */       GL11.glRotatef(MathHelper.sin(angleSpan) / 3.141593F * 180.0F, 0.0F, 0.0F, 1.0F);
/* 64:73 */       tessellator.startDrawingQuads();
/* 65:74 */       tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 66:75 */       tessellator.setColorRGBA_F(1.0F * this.random.nextInt(10000) / 10000.0F, 0.0F, 0.0F, 0.7F);
/* 67:76 */       tessellator.addVertexWithUV(-widthX, 0.0D, 0.0D, umin, vmax);
/* 68:77 */       tessellator.addVertexWithUV(widthX, 0.0D, 0.0D, umax, vmax);
/* 69:78 */       tessellator.addVertexWithUV(0.0D, lengthY2, zPos, umax, vmin);
/* 70:79 */       tessellator.addVertexWithUV(0.0D, lengthY2, zPos, umin, vmin);
/* 71:80 */       tessellator.draw();
/* 72:   */     }
/* 73:83 */     GL11.glDisable(3042);
/* 74:84 */     GL11.glEnable(2884);
/* 75:85 */     GL11.glEnable(2896);
/* 76:86 */     GL11.glDepthMask(false);
/* 77:87 */     GL11.glPopMatrix();
/* 78:   */   }
/* 79:   */   
/* 80:   */   protected ResourceLocation getEntityTexture(Entity entity)
/* 81:   */   {
/* 82:93 */     return ajaRedStoneEffectTexture;
/* 83:   */   }
/* 84:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.RenderAjaRedStoneEffect
 * JD-Core Version:    0.7.0.1
 */