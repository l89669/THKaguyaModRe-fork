/*   1:    */ package thKaguyaMod.client.render.effect;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.relauncher.Side;
/*   4:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   5:    */ import net.minecraft.client.renderer.Tessellator;
/*   6:    */ import net.minecraft.client.renderer.entity.Render;
/*   7:    */ import net.minecraft.client.renderer.entity.RenderManager;
/*   8:    */ import net.minecraft.entity.Entity;
/*   9:    */ import net.minecraft.util.MathHelper;
/*  10:    */ import net.minecraft.util.ResourceLocation;
/*  11:    */ import org.lwjgl.opengl.GL11;
/*  12:    */ import thKaguyaMod.entity.effect.EntitySpellCardCircle;
/*  13:    */ 
/*  14:    */ @SideOnly(Side.CLIENT)
/*  15:    */ public class RenderSpellCardCircle
/*  16:    */   extends Render
/*  17:    */ {
/*  18: 20 */   private static final ResourceLocation circleTexture = new ResourceLocation("thkaguyamod", "textures/mob/Familiar.png");
/*  19: 21 */   protected float[] colorR = { 255.0F, 255.0F, 255.0F, 0.0F, 0.0F, 0.0F, 255.0F };
/*  20: 22 */   protected float[] colorG = { 0.0F, 165.0F, 255.0F, 255.0F, 0.0F, 255.0F, 0.0F };
/*  21: 23 */   protected float[] colorB = { 0.0F, 0.0F, 0.0F, 0.0F, 255.0F, 255.0F, 255.0F };
/*  22:    */   
/*  23:    */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/*  24:    */   {
/*  25: 31 */     renderSpellCardCircle((EntitySpellCardCircle)entity, x, y, z, yaw, pitch);
/*  26:    */   }
/*  27:    */   
/*  28:    */   public void renderSpellCardCircle(EntitySpellCardCircle circle, double x, double y, double z, float yaw, float pitch)
/*  29:    */   {
/*  30: 41 */     GL11.glPushMatrix();
/*  31: 42 */     bindEntityTexture(circle);
/*  32: 43 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/*  33: 44 */     GL11.glDisable(2896);
/*  34: 45 */     GL11.glDisable(2884);
/*  35: 46 */     GL11.glEnable(3042);
/*  36: 47 */     GL11.glBlendFunc(1, 769);
/*  37: 48 */     float size = circle.getCircleSize() * 6.0F;
/*  38: 49 */     GL11.glScalef(size, size, size);
/*  39: 50 */     Tessellator tessellator = Tessellator.instance;
/*  40:    */     
/*  41:    */ 
/*  42:    */ 
/*  43:    */ 
/*  44: 55 */     float umin = 0.0F;
/*  45: 56 */     float umax = 1.0F;
/*  46: 57 */     float vmin = 0.0F;
/*  47: 58 */     float vmax = 1.0F;
/*  48:    */     
/*  49: 60 */     boolean rainbow = false;
/*  50: 61 */     int color = circle.getCircleType();
/*  51: 62 */     if (color >= 16)
/*  52:    */     {
/*  53: 64 */       float angle = MathHelper.sin(2.0F * circle.getAnimationCount() / 180.0F * 3.141593F) * 40.0F;
/*  54: 65 */       GL11.glRotatef(180.0F - this.renderManager.playerViewY + angle, 0.0F, 1.0F, 0.0F);
/*  55: 66 */       GL11.glRotatef(-this.renderManager.playerViewX - angle, 1.0F, 0.0F, 0.0F);
/*  56: 67 */       GL11.glRotatef(circle.getAnimationCount() * 5.0F, 0.0F, 0.0F, 1.0F);
/*  57: 68 */       color %= 16;
/*  58:    */     }
/*  59:    */     else
/*  60:    */     {
/*  61: 72 */       GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/*  62: 73 */       GL11.glRotatef(circle.getAnimationCount() * 5.0F, 0.0F, 0.0F, 1.0F);
/*  63:    */     }
/*  64: 76 */     if (color == 9) {
/*  65: 78 */       rainbow = true;
/*  66:    */     } else {
/*  67: 82 */       color %= 8;
/*  68:    */     }
/*  69: 85 */     tessellator.startDrawingQuads();
/*  70: 86 */     if (rainbow)
/*  71:    */     {
/*  72: 88 */       float rainbowTime = circle.ticksExisted / 3.0F;
/*  73: 89 */       tessellator.setColorRGBA_F(MathHelper.sin(rainbowTime), MathHelper.cos(rainbowTime), -MathHelper.sin(rainbowTime) * 2.0F, 1.0F);
/*  74:    */     }
/*  75:    */     else
/*  76:    */     {
/*  77: 93 */       tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], 1.0F);
/*  78:    */     }
/*  79: 95 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  80: 96 */     tessellator.addVertexWithUV(-0.6000000238418579D, -0.6000000238418579D, 0.0D, umin, vmax);
/*  81: 97 */     tessellator.addVertexWithUV(0.6000000238418579D, -0.6000000238418579D, 0.0D, umax, vmax);
/*  82: 98 */     tessellator.addVertexWithUV(0.6000000238418579D, 0.6000000238418579D, 0.0D, umax, vmin);
/*  83: 99 */     tessellator.addVertexWithUV(-0.6000000238418579D, 0.6000000238418579D, 0.0D, umin, vmin);
/*  84:100 */     tessellator.draw();
/*  85:101 */     GL11.glDisable(3042);
/*  86:102 */     GL11.glEnable(2896);
/*  87:103 */     GL11.glEnable(2884);
/*  88:104 */     GL11.glPopMatrix();
/*  89:    */   }
/*  90:    */   
/*  91:    */   protected ResourceLocation getEntityTexture(Entity entity)
/*  92:    */   {
/*  93:109 */     return getEntityTexture((EntitySpellCardCircle)entity);
/*  94:    */   }
/*  95:    */   
/*  96:    */   protected ResourceLocation getEntityTexture(EntitySpellCardCircle circle)
/*  97:    */   {
/*  98:113 */     return circleTexture;
/*  99:    */   }
/* 100:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.effect.RenderSpellCardCircle
 * JD-Core Version:    0.7.0.1
 */