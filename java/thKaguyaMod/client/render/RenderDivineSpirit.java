/*   1:    */ package thKaguyaMod.client.render;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.relauncher.Side;
/*   4:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   5:    */ import net.minecraft.client.renderer.Tessellator;
/*   6:    */ import net.minecraft.client.renderer.entity.Render;
/*   7:    */ import net.minecraft.entity.Entity;
/*   8:    */ import net.minecraft.util.ResourceLocation;
/*   9:    */ import org.lwjgl.opengl.GL11;
/*  10:    */ import thKaguyaMod.client.THKaguyaRenderLib;
/*  11:    */ import thKaguyaMod.entity.EntityDivineSpirit;
/*  12:    */ 
/*  13:    */ @SideOnly(Side.CLIENT)
/*  14:    */ public class RenderDivineSpirit
/*  15:    */   extends Render
/*  16:    */ {
/*  17: 21 */   private static final ResourceLocation divineSpiritTexture = new ResourceLocation("thkaguyamod", "textures/shot/MusouFuuin.png");
/*  18: 22 */   protected float[] colorR = { 0.8784314F, 0.0F, 0.0F, 0.8784314F, 0.8784314F, 0.0F, 1.0F, 1.0F };
/*  19: 23 */   protected float[] colorG = { 0.0F, 0.0F, 0.8784314F, 0.2509804F, 0.0F, 0.8784314F, 0.5019608F, 1.0F };
/*  20: 24 */   protected float[] colorB = { 0.0F, 0.8784314F, 0.0F, 0.0F, 0.8784314F, 0.8784314F, 0.0F, 1.0F };
/*  21:    */   
/*  22:    */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/*  23:    */   {
/*  24: 33 */     doRenderDivineSpirit((EntityDivineSpirit)entity, x, y, z, yaw, pitch);
/*  25:    */   }
/*  26:    */   
/*  27:    */   public void doRenderDivineSpirit(EntityDivineSpirit divineSpirit, double x, double y, double z, float yaw, float pitch)
/*  28:    */   {
/*  29: 38 */     GL11.glPushMatrix();
/*  30: 39 */     bindTexture(getEntityTexture(divineSpirit));
/*  31: 40 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/*  32:    */     
/*  33: 42 */     GL11.glEnable(3042);
/*  34: 43 */     GL11.glBlendFunc(1, 769);
/*  35: 44 */     GL11.glDepthMask(false);
/*  36: 45 */     GL11.glScalef(1.0F, 1.0F, 1.0F);
/*  37: 46 */     Tessellator tessellator = Tessellator.instance;
/*  38:    */     
/*  39: 48 */     int color = divineSpirit.getDivineSpiritColor();
/*  40: 49 */     double time_r = divineSpirit.ticksExisted / 180.0D * 3.141592653589793D;
/*  41:    */     
/*  42:    */ 
/*  43: 52 */     THKaguyaRenderLib.getGLRotatefByTherdPersonView(this.renderManager);
/*  44:    */     
/*  45: 54 */     float size = (300.0F - divineSpirit.getDivineSpiritCount()) / 300.0F;
/*  46: 55 */     float size2 = size * 0.7F;
/*  47: 56 */     GL11.glScalef(size, size, size);
/*  48:    */     
/*  49: 58 */     int pattern = 0;
/*  50: 59 */     float umin = (pattern % 32 * 32 + 0) / 64.0F;
/*  51: 60 */     float umax = (pattern % 32 * 32 + 32) / 64.0F;
/*  52: 61 */     float vmin = 0.0F;
/*  53: 62 */     float vmax = 1.0F;
/*  54:    */     
/*  55:    */ 
/*  56:    */ 
/*  57: 66 */     tessellator.startDrawingQuads();
/*  58:    */     
/*  59: 68 */     tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], 0.3F);
/*  60: 69 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  61: 70 */     tessellator.addVertexWithUV(-0.699999988079071D + Math.sin(time_r * 3.0D) * 0.1000000014901161D, -0.699999988079071D - Math.cos(time_r * 7.0D) * 0.1000000014901161D, 0.0D, umin, vmax);
/*  62: 71 */     tessellator.addVertexWithUV(0.699999988079071D - Math.cos(time_r * 4.0D) * 0.1000000014901161D, -0.699999988079071D - Math.sin(time_r * 5.0D) * 0.1000000014901161D, 0.0D, umax, vmax);
/*  63: 72 */     tessellator.addVertexWithUV(0.699999988079071D + Math.sin(time_r * 5.0D) * 0.1000000014901161D, 0.699999988079071D + Math.cos(time_r * 4.0D) * 0.1000000014901161D, 0.0D, umax, vmin);
/*  64: 73 */     tessellator.addVertexWithUV(-0.699999988079071D - Math.cos(time_r * 7.0D) * 0.1000000014901161D, 0.699999988079071D + Math.sin(time_r * 3.0D) * 0.1000000014901161D, 0.0D, umin, vmin);
/*  65:    */     
/*  66:    */ 
/*  67: 76 */     tessellator.draw();
/*  68: 77 */     tessellator.startDrawingQuads();
/*  69:    */     
/*  70: 79 */     tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], 0.7F);
/*  71: 80 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  72: 81 */     tessellator.addVertexWithUV(-0.6000000238418579D - Math.cos(time_r * 7.0D) * 0.1000000014901161D, -0.6000000238418579D + Math.sin(time_r * 5.0D) * 0.1000000014901161D, 0.001D, umin, vmax);
/*  73: 82 */     tessellator.addVertexWithUV(0.6000000238418579D + Math.sin(time_r * 3.0D) * 0.1000000014901161D, -0.6000000238418579D + Math.cos(time_r * 4.0D) * 0.1000000014901161D, 0.001D, umax, vmax);
/*  74: 83 */     tessellator.addVertexWithUV(0.6000000238418579D - Math.cos(time_r * 4.0D) * 0.1000000014901161D, 0.6000000238418579D - Math.sin(time_r * 3.0D) * 0.1000000014901161D, 0.001D, umax, vmin);
/*  75: 84 */     tessellator.addVertexWithUV(-0.6000000238418579D + Math.sin(time_r * 5.0D) * 0.1000000014901161D, 0.6000000238418579D - Math.cos(time_r * 7.0D) * 0.1000000014901161D, 0.001D, umin, vmin);
/*  76: 85 */     tessellator.draw();
/*  77: 86 */     tessellator.startDrawingQuads();
/*  78: 87 */     tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], 1.0F);
/*  79: 88 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  80: 89 */     tessellator.addVertexWithUV(-0.4000000059604645D, -0.4000000059604645D, 0.002D, umin, vmax);
/*  81: 90 */     tessellator.addVertexWithUV(0.4000000059604645D, -0.4000000059604645D, 0.002D, umax, vmax);
/*  82: 91 */     tessellator.addVertexWithUV(0.4000000059604645D, 0.4000000059604645D, 0.002D, umax, vmin);
/*  83: 92 */     tessellator.addVertexWithUV(-0.4000000059604645D, 0.4000000059604645D, 0.002D, umin, vmin);
/*  84: 93 */     tessellator.draw();
/*  85:    */     
/*  86: 95 */     GL11.glDisable(3042);
/*  87:    */     
/*  88: 97 */     GL11.glDepthMask(true);
/*  89: 98 */     GL11.glPopMatrix();
/*  90:    */   }
/*  91:    */   
/*  92:    */   protected ResourceLocation getEntityTexture(Entity entity)
/*  93:    */   {
/*  94:103 */     return divineSpiritTexture;
/*  95:    */   }
/*  96:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.RenderDivineSpirit
 * JD-Core Version:    0.7.0.1
 */