/*   1:    */ package thKaguyaMod.client.render.shot;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.relauncher.Side;
/*   4:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   5:    */ import net.minecraft.client.renderer.Tessellator;
/*   6:    */ import net.minecraft.client.renderer.entity.Render;
/*   7:    */ import net.minecraft.entity.Entity;
/*   8:    */ import net.minecraft.util.ResourceLocation;
/*   9:    */ import org.lwjgl.opengl.GL11;
/*  10:    */ import thKaguyaMod.entity.shot.EntityHomingAmulet;
/*  11:    */ 
/*  12:    */ @SideOnly(Side.CLIENT)
/*  13:    */ public class RenderHomingAmulet
/*  14:    */   extends Render
/*  15:    */ {
/*  16: 19 */   private static final ResourceLocation homingTexture = new ResourceLocation("thkaguyamod", "textures/shot/HomingAmulet.png");
/*  17: 20 */   private static final ResourceLocation diffusionTexture = new ResourceLocation("thkaguyamod", "textures/shot/DiffusionAmulet.png");
/*  18: 22 */   float[] colorR = { 255.0F, 25.0F, 0.0F, 255.0F, 255.0F, 0.0F, 255.0F, 255.0F };
/*  19: 23 */   float[] colorG = { 25.0F, 25.0F, 255.0F, 255.0F, 0.0F, 255.0F, 165.0F, 255.0F };
/*  20: 24 */   float[] colorB = { 25.0F, 255.0F, 0.0F, 0.0F, 255.0F, 255.0F, 0.0F, 255.0F };
/*  21:    */   
/*  22:    */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/*  23:    */   {
/*  24: 32 */     doRenderHomingAmulet((EntityHomingAmulet)entity, x, y, z, yaw, pitch);
/*  25:    */   }
/*  26:    */   
/*  27:    */   public void doRenderHomingAmulet(EntityHomingAmulet homingAmulet, double x, double y, double z, float yaw, float pitch)
/*  28:    */   {
/*  29: 37 */     GL11.glPushMatrix();
/*  30:    */     
/*  31: 39 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/*  32: 40 */     GL11.glDisable(2896);
/*  33:    */     
/*  34: 42 */     GL11.glEnable(3042);
/*  35: 43 */     GL11.glDisable(2884);
/*  36: 44 */     GL11.glBlendFunc(1, 769);
/*  37:    */     
/*  38: 46 */     int color = homingAmulet.getShotColor() / 2;
/*  39:    */     
/*  40: 48 */     bindTexture(getEntityTexture(homingAmulet));
/*  41:    */     
/*  42: 50 */     float size = 0.5F;
/*  43: 51 */     if (homingAmulet.getShotColor() % 2 == 1) {
/*  44: 53 */       size = 1.2F;
/*  45:    */     }
/*  46: 55 */     GL11.glScalef(size, size, size);
/*  47:    */     
/*  48: 57 */     Tessellator tessellator = Tessellator.instance;
/*  49: 58 */     float xLength = 0.5F;
/*  50: 59 */     double zLength = 0.5D;
/*  51: 60 */     float uMin = 0.0F;
/*  52: 61 */     float uMax = 0.5F;
/*  53: 62 */     float vMin = 0.0F;
/*  54: 63 */     float vMax = 1.0F;
/*  55:    */     
/*  56: 65 */     GL11.glRotatef(-homingAmulet.rotationPitch, 1.0F, 0.0F, 0.0F);
/*  57: 66 */     GL11.glRotatef(180.0F - homingAmulet.getAnimationCount() * 23.0F, 0.0F, 1.0F, 0.0F);
/*  58: 67 */     tessellator.startDrawingQuads();
/*  59: 68 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  60: 69 */     tessellator.addVertexWithUV(-xLength, 0.0D, zLength, uMin, vMin);
/*  61: 70 */     tessellator.addVertexWithUV(xLength, 0.0D, zLength, uMax, vMin);
/*  62: 71 */     tessellator.addVertexWithUV(xLength, 0.0D, -zLength, uMax, vMax);
/*  63: 72 */     tessellator.addVertexWithUV(-xLength, 0.0D, -zLength, uMin, vMax);
/*  64: 73 */     tessellator.draw();
/*  65:    */     
/*  66: 75 */     size *= 1.1F;
/*  67: 76 */     GL11.glScalef(size, size, size);
/*  68:    */     
/*  69: 78 */     tessellator.startDrawingQuads();
/*  70: 79 */     tessellator.setColorRGBA_F(this.colorR[color] / 255.0F, this.colorG[color] / 255.0F, this.colorB[color] / 255.0F, 0.6F);
/*  71: 80 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  72: 81 */     tessellator.addVertexWithUV(-xLength, 0.0D, zLength, uMin, vMin);
/*  73: 82 */     tessellator.addVertexWithUV(xLength, 0.0D, zLength, uMax, vMin);
/*  74: 83 */     tessellator.addVertexWithUV(xLength, 0.0D, -zLength, uMax, vMax);
/*  75: 84 */     tessellator.addVertexWithUV(-xLength, 0.0D, -zLength, uMin, vMax);
/*  76: 85 */     tessellator.draw();
/*  77:    */     
/*  78: 87 */     GL11.glDisable(3042);
/*  79: 88 */     GL11.glEnable(2896);
/*  80: 89 */     GL11.glEnable(2884);
/*  81: 90 */     GL11.glPopMatrix();
/*  82:    */   }
/*  83:    */   
/*  84:    */   protected ResourceLocation getEntityTexture(Entity entity)
/*  85:    */   {
/*  86: 95 */     return getEntityTexture((EntityHomingAmulet)entity);
/*  87:    */   }
/*  88:    */   
/*  89:    */   protected ResourceLocation getEntityTexture(EntityHomingAmulet homingAmulet)
/*  90:    */   {
/*  91:100 */     if (homingAmulet.getShotColor() / 2 == 0) {
/*  92:102 */       return homingTexture;
/*  93:    */     }
/*  94:106 */     return diffusionTexture;
/*  95:    */   }
/*  96:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.shot.RenderHomingAmulet
 * JD-Core Version:    0.7.0.1
 */