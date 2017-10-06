/*   1:    */ package thKaguyaMod.client.render.effect;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.relauncher.Side;
/*   4:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   5:    */ import net.minecraft.client.renderer.Tessellator;
/*   6:    */ import net.minecraft.client.renderer.entity.Render;
/*   7:    */ import net.minecraft.entity.Entity;
/*   8:    */ import net.minecraft.util.MathHelper;
/*   9:    */ import net.minecraft.util.ResourceLocation;
/*  10:    */ import org.lwjgl.opengl.GL11;
/*  11:    */ import thKaguyaMod.client.THKaguyaRenderLib;
/*  12:    */ import thKaguyaMod.entity.effect.EntityMiracleCircle;
/*  13:    */ 
/*  14:    */ @SideOnly(Side.CLIENT)
/*  15:    */ public class RenderMiracleCircle
/*  16:    */   extends Render
/*  17:    */ {
/*  18: 21 */   private static final ResourceLocation miracleCircleTexture = new ResourceLocation("thkaguyamod", "textures/MiracleCircle.png");
/*  19:    */   
/*  20:    */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/*  21:    */   {
/*  22: 30 */     doRenderMiracleCircle((EntityMiracleCircle)entity, x, y, z, yaw, pitch);
/*  23:    */   }
/*  24:    */   
/*  25:    */   public void doRenderMiracleCircle(EntityMiracleCircle miracleCircle, double x, double y, double z, float yaw, float pitch)
/*  26:    */   {
/*  27: 36 */     float[] colorR = { 0.7F, 0.7F, 0.9F, 0.3F, 0.2F };
/*  28: 37 */     float[] colorG = { 0.1F, 0.7F, 0.1F, 0.3F, 0.9F };
/*  29: 38 */     float[] colorB = { 0.9F, 0.1F, 0.2F, 0.9F, 0.2F };
/*  30: 39 */     int color = miracleCircle.getColor();
/*  31:    */     
/*  32: 41 */     GL11.glPushMatrix();
/*  33: 42 */     bindTexture(getEntityTexture(miracleCircle));
/*  34: 43 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/*  35: 44 */     GL11.glDisable(2896);
/*  36:    */     
/*  37: 46 */     GL11.glEnable(3042);
/*  38: 47 */     GL11.glDisable(2884);
/*  39: 48 */     GL11.glBlendFunc(1, 769);
/*  40: 49 */     GL11.glScalef(1.0F, 1.0F, 1.0F);
/*  41:    */     
/*  42: 51 */     Tessellator tessellator = Tessellator.instance;
/*  43:    */     
/*  44: 53 */     THKaguyaRenderLib.getGLRotatefByTherdPersonView(this.renderManager);
/*  45: 54 */     GL11.glRotatef(miracleCircle.turnAngle, 0.0F, 0.0F, 1.0F);
/*  46:    */     
/*  47:    */ 
/*  48: 57 */     float umin = 0.5F;
/*  49: 58 */     float umax = 0.625F;
/*  50: 59 */     float vmin = 0.0F;
/*  51: 60 */     float vmax = 1.0F;
/*  52: 61 */     float angle = -108.0F;
/*  53: 62 */     float length = 3.0F;
/*  54: 63 */     float width = 0.1F;
/*  55: 64 */     float py = length / (2.0F * MathHelper.cos(0.314159F));
/*  56: 65 */     float px = 0.0F;
/*  57: 66 */     float nextpx = px + MathHelper.cos(angle / 180.0F * 3.141593F) * length;
/*  58: 67 */     float nextpy = py + MathHelper.sin(angle / 180.0F * 3.141593F) * length;
/*  59:    */     
/*  60:    */ 
/*  61:    */ 
/*  62:    */ 
/*  63: 72 */     int lastLine = miracleCircle.getNumberOfDrewLine() - 1;
/*  64: 73 */     int starNum = color;
/*  65: 74 */     int j = color;
/*  66: 77 */     for (int i = 0; i < lastLine; i++)
/*  67:    */     {
/*  68: 79 */       double zbuf = 0.001D * i;
/*  69: 80 */       float widthX = MathHelper.cos((angle + 90.0F) / 180.0F * 3.141593F) * width;
/*  70: 81 */       float widthY = MathHelper.sin((angle + 90.0F) / 180.0F * 3.141593F) * width;
/*  71: 82 */       tessellator.startDrawingQuads();
/*  72: 83 */       tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  73: 84 */       tessellator.setColorRGBA_F(colorR[j], colorG[j], colorB[j], 0.5F);
/*  74: 85 */       tessellator.addVertexWithUV(px - widthX, py - widthY, zbuf, umin, vmax);
/*  75: 86 */       tessellator.addVertexWithUV(px + widthX, py + widthY, zbuf, umax, vmax);
/*  76: 87 */       tessellator.addVertexWithUV(nextpx + widthX, nextpy + widthY, zbuf, umax, vmin);
/*  77: 88 */       tessellator.addVertexWithUV(nextpx - widthX, nextpy - widthY, zbuf, umin, vmin);
/*  78: 89 */       tessellator.draw();
/*  79: 90 */       angle += 144.0F;
/*  80: 91 */       px = nextpx;
/*  81: 92 */       py = nextpy;
/*  82: 93 */       nextpx = px + MathHelper.cos(angle / 180.0F * 3.141593F) * length;
/*  83: 94 */       nextpy = py + MathHelper.sin(angle / 180.0F * 3.141593F) * length;
/*  84:    */     }
/* 100:112 */     GL11.glDisable(3042);
/* 101:113 */     GL11.glEnable(2884);
/* 102:114 */     GL11.glEnable(2896);
/* 103:115 */     GL11.glPopMatrix();
/* 104:    */   }
/* 105:    */   
/* 106:    */   protected ResourceLocation getEntityTexture(Entity entity)
/* 107:    */   {
/* 108:120 */     return miracleCircleTexture;
/* 109:    */   }
/* 110:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.effect.RenderMiracleCircle
 * JD-Core Version:    0.7.0.1
 */