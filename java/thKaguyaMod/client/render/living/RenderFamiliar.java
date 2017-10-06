/*   1:    */ package thKaguyaMod.client.render.living;
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
/*  12:    */ import thKaguyaMod.entity.living.EntityFamiliar;
/*  13:    */ 
/*  14:    */ @SideOnly(Side.CLIENT)
/*  15:    */ public class RenderFamiliar
/*  16:    */   extends Render
/*  17:    */ {
/*  18: 20 */   private static final ResourceLocation familiarTexture = new ResourceLocation("thkaguyamod", "textures/mob/Familiar.png");
/*  19: 21 */   protected float[] colorR = { 255.0F, 255.0F, 255.0F, 0.0F, 0.0F, 0.0F, 255.0F };
/*  20: 22 */   protected float[] colorG = { 0.0F, 165.0F, 255.0F, 255.0F, 0.0F, 255.0F, 0.0F };
/*  21: 23 */   protected float[] colorB = { 0.0F, 0.0F, 0.0F, 0.0F, 255.0F, 255.0F, 255.0F };
/*  22:    */   
/*  23:    */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/*  24:    */   {
/*  25: 31 */     renderFamiliar((EntityFamiliar)entity, x, y, z, yaw, pitch);
/*  26:    */   }
/*  27:    */   
/*  28:    */   public void renderFamiliar(EntityFamiliar familiar, double x, double y, double z, float yaw, float pitch)
/*  29:    */   {
/*  30: 37 */     if (familiar.getForm() < 0) {
/*  31: 39 */       return;
/*  32:    */     }
/*  33: 41 */     GL11.glPushMatrix();
/*  34: 42 */     bindEntityTexture(familiar);
/*  35: 43 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/*  36: 44 */     GL11.glDisable(2896);
/*  37: 45 */     GL11.glEnable(3042);
/*  38: 46 */     GL11.glBlendFunc(1, 769);
/*  39: 47 */     GL11.glDepthMask(false);
/*  40: 48 */     float size = 3.0F + (float)Math.sin(familiar.ticksExisted * 10 / 180.0D * 3.141592653589793D) * 0.6F;
/*  41: 49 */     GL11.glScalef(size, size, size);
/*  42: 50 */     Tessellator tessellator = Tessellator.instance;
/*  43:    */     
/*  44:    */ 
/*  45:    */ 
/*  46:    */ 
/*  47: 55 */     float umin = 0.0F;
/*  48: 56 */     float umax = 1.0F;
/*  49: 57 */     float vmin = 0.0F;
/*  50: 58 */     float vmax = 1.0F;
/*  51:    */     
/*  52: 60 */     GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/*  53: 61 */     GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
/*  54: 62 */     GL11.glRotatef(familiar.ticksExisted * 5.0F, 0.0F, 0.0F, 1.0F);
/*  55:    */     
/*  56: 64 */     int color = familiar.getForm() % 8;
/*  57: 65 */     tessellator.setColorRGBA_F(MathHelper.sin(color), MathHelper.cos(color), -MathHelper.sin(color) * 2.0F, 0.6F);
/*  58:    */     
/*  59: 67 */     tessellator.startDrawingQuads();
/*  60: 68 */     tessellator.setColorRGBA_F(MathHelper.sin(color), MathHelper.cos(color), -MathHelper.sin(color) * 2.0F, 0.6F);
/*  61:    */     
/*  62: 70 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  63: 71 */     tessellator.addVertexWithUV(-0.6000000238418579D, -0.6000000238418579D, 0.0D, umin, vmax);
/*  64: 72 */     tessellator.addVertexWithUV(0.6000000238418579D, -0.6000000238418579D, 0.0D, umax, vmax);
/*  65: 73 */     tessellator.addVertexWithUV(0.6000000238418579D, 0.6000000238418579D, 0.0D, umax, vmin);
/*  66: 74 */     tessellator.addVertexWithUV(-0.6000000238418579D, 0.6000000238418579D, 0.0D, umin, vmin);
/*  67: 75 */     tessellator.draw();
/*  68:    */     
/*  69:    */ 
/*  70:    */ 
/*  71:    */ 
/*  72:    */ 
/*  73:    */ 
/*  74:    */ 
/*  75:    */ 
/*  76:    */ 
/*  77:    */ 
/*  78:    */ 
/*  79:    */ 
/*  80:    */ 
/*  81:    */ 
/*  82:    */ 
/*  83:    */ 
/*  84:    */ 
/*  85: 93 */     GL11.glDepthMask(true);
/*  86: 94 */     GL11.glDisable(3042);
/*  87: 95 */     GL11.glEnable(2896);
/*  88: 96 */     GL11.glPopMatrix();
/*  89:    */   }
/*  90:    */   
/*  91:    */   protected ResourceLocation getEntityTexture(Entity entity)
/*  92:    */   {
/*  93:101 */     return getEntityTexture((EntityFamiliar)entity);
/*  94:    */   }
/*  95:    */   
/*  96:    */   protected ResourceLocation getEntityTexture(EntityFamiliar familiar)
/*  97:    */   {
/*  98:105 */     return familiarTexture;
/*  99:    */   }
/* 100:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.living.RenderFamiliar
 * JD-Core Version:    0.7.0.1
 */