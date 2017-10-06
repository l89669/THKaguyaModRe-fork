/*   1:    */ package thKaguyaMod.client.render.shot;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.relauncher.Side;
/*   4:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   5:    */ import java.util.Random;
/*   6:    */ import net.minecraft.client.renderer.Tessellator;
/*   7:    */ import net.minecraft.client.renderer.entity.Render;
/*   8:    */ import net.minecraft.entity.Entity;
/*   9:    */ import net.minecraft.util.MathHelper;
/*  10:    */ import net.minecraft.util.ResourceLocation;
/*  11:    */ import org.lwjgl.opengl.GL11;
/*  12:    */ import thKaguyaMod.client.THKaguyaRenderLib;
/*  13:    */ import thKaguyaMod.entity.shot.EntityMusouFuuin;
/*  14:    */ 
/*  15:    */ @SideOnly(Side.CLIENT)
/*  16:    */ public class RenderMusouFuuin
/*  17:    */   extends Render
/*  18:    */ {
/*  19: 23 */   private static final ResourceLocation musouFuuinTexture = new ResourceLocation("thkaguyamod", "textures/shot/MusouFuuin.png");
/*  20: 24 */   private Random random = new Random();
/*  21:    */   
/*  22:    */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/*  23:    */   {
/*  24: 32 */     renderMusouFuuin((EntityMusouFuuin)entity, x, y, z, yaw, pitch);
/*  25:    */   }
/*  26:    */   
/*  27:    */   public void renderMusouFuuin(EntityMusouFuuin musouFuuin, double x, double y, double z, float yaw, float pitch)
/*  28:    */   {
/*  29: 37 */     GL11.glPushMatrix();
/*  30: 38 */     bindEntityTexture(musouFuuin);
/*  31: 39 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/*  32: 40 */     GL11.glDisable(2896);
/*  33: 41 */     GL11.glEnable(3042);
/*  34: 42 */     GL11.glBlendFunc(1, 769);
/*  35: 43 */     GL11.glDepthMask(false);
/*  36: 44 */     float size = musouFuuin.getShotSize();
/*  37: 45 */     GL11.glScalef(size, size, size);
/*  38: 46 */     Tessellator tessellator = Tessellator.instance;
/*  39:    */     
/*  40:    */ 
/*  41: 49 */     float[] colorR = { 255.0F, 255.0F, 255.0F, 0.0F, 0.0F, 0.0F, 255.0F };
/*  42: 50 */     float[] colorG = { 0.0F, 165.0F, 255.0F, 255.0F, 0.0F, 255.0F, 0.0F };
/*  43: 51 */     float[] colorB = { 0.0F, 0.0F, 0.0F, 0.0F, 255.0F, 255.0F, 255.0F };
/*  44: 52 */     int pattern = musouFuuin.getAnimationCount() % 2;
/*  45: 53 */     float umin = (pattern % 32 * 32 + 0) / 64.0F;
/*  46: 54 */     float umax = (pattern % 32 * 32 + 32) / 64.0F;
/*  47: 55 */     float vmin = 0.0F;
/*  48: 56 */     float vmax = 1.0F;
/*  49:    */     
/*  50:    */ 
/*  51: 59 */     THKaguyaRenderLib.getGLRotatefByTherdPersonView(this.renderManager);
/*  52:    */     
/*  53: 61 */     int color = musouFuuin.getAnimationCount();
/*  54: 62 */     tessellator.setColorRGBA_F(MathHelper.sin(color), MathHelper.cos(color), -MathHelper.sin(color) * 2.0F, 0.6F);
/*  55:    */     
/*  56: 64 */     tessellator.startDrawingQuads();
/*  57: 65 */     tessellator.setColorRGBA_F(MathHelper.sin(color), MathHelper.cos(color), -MathHelper.sin(color) * 2.0F, 0.6F);
/*  58:    */     
/*  59: 67 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  60: 68 */     tessellator.addVertexWithUV(-0.6000000238418579D, -0.6000000238418579D, 0.0D, umin, vmax);
/*  61: 69 */     tessellator.addVertexWithUV(0.6000000238418579D, -0.6000000238418579D, 0.0D, umax, vmax);
/*  62: 70 */     tessellator.addVertexWithUV(0.6000000238418579D, 0.6000000238418579D, 0.0D, umax, vmin);
/*  63: 71 */     tessellator.addVertexWithUV(-0.6000000238418579D, 0.6000000238418579D, 0.0D, umin, vmin);
/*  64: 72 */     tessellator.draw();
/*  65: 73 */     tessellator.startDrawingQuads();
/*  66: 74 */     tessellator.setColorRGBA_F(MathHelper.sin(color), MathHelper.cos(color), -MathHelper.sin(color) * 2.0F, 0.6F);
/*  67:    */     
/*  68: 76 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  69: 77 */     tessellator.addVertexWithUV(-0.5D, -0.5D, 0.001D, umin, vmax);
/*  70: 78 */     tessellator.addVertexWithUV(0.5D, -0.5D, 0.001D, umax, vmax);
/*  71: 79 */     tessellator.addVertexWithUV(0.5D, 0.5D, 0.001D, umax, vmin);
/*  72: 80 */     tessellator.addVertexWithUV(-0.5D, 0.5D, 0.001D, umin, vmin);
/*  73: 81 */     tessellator.draw();
/*  74: 82 */     tessellator.startDrawingQuads();
/*  75: 83 */     tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 0.9F);
/*  76: 84 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  77: 85 */     tessellator.addVertexWithUV(-0.4000000059604645D, -0.4000000059604645D, 0.002D, umin, vmax);
/*  78: 86 */     tessellator.addVertexWithUV(0.4000000059604645D, -0.4000000059604645D, 0.002D, umax, vmax);
/*  79: 87 */     tessellator.addVertexWithUV(0.4000000059604645D, 0.4000000059604645D, 0.002D, umax, vmin);
/*  80: 88 */     tessellator.addVertexWithUV(-0.4000000059604645D, 0.4000000059604645D, 0.002D, umin, vmin);
/*  81: 89 */     tessellator.draw();
/*  82: 90 */     GL11.glDepthMask(true);
/*  83: 91 */     GL11.glDisable(3042);
/*  84: 92 */     GL11.glEnable(2896);
/*  85: 93 */     GL11.glPopMatrix();
/*  86:    */   }
/*  87:    */   
/*  88:    */   protected ResourceLocation getEntityTexture(Entity entity)
/*  89:    */   {
/*  90: 98 */     return getEntityTexture((EntityMusouFuuin)entity);
/*  91:    */   }
/*  92:    */   
/*  93:    */   protected ResourceLocation getEntityTexture(EntityMusouFuuin musouFuuin)
/*  94:    */   {
/*  95:102 */     return musouFuuinTexture;
/*  96:    */   }
/*  97:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.shot.RenderMusouFuuin
 * JD-Core Version:    0.7.0.1
 */