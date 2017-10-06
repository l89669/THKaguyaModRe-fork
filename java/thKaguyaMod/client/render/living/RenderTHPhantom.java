/*   1:    */ package thKaguyaMod.client.render.living;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.relauncher.Side;
/*   4:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   5:    */ import net.minecraft.client.renderer.Tessellator;
/*   6:    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*   7:    */ import net.minecraft.client.renderer.entity.RenderManager;
/*   8:    */ import net.minecraft.entity.Entity;
/*   9:    */ import net.minecraft.util.ResourceLocation;
/*  10:    */ import org.lwjgl.opengl.GL11;
/*  11:    */ import thKaguyaMod.entity.living.EntityTHPhantom;
/*  12:    */ 
/*  13:    */ @SideOnly(Side.CLIENT)
/*  14:    */ public class RenderTHPhantom
/*  15:    */   extends RenderLiving
/*  16:    */ {
/*  17: 18 */   private static final ResourceLocation phantomTexture = new ResourceLocation("thkaguyamod", "textures/shot/MusouFuuin.png");
/*  18: 19 */   protected float[] colorR = { 0.8784314F, 0.0F, 0.0F, 0.8784314F, 0.8784314F, 0.0F, 1.0F, 1.0F };
/*  19: 20 */   protected float[] colorG = { 0.0F, 0.0F, 0.8784314F, 0.2509804F, 0.0F, 0.8784314F, 0.5019608F, 1.0F };
/*  20: 21 */   protected float[] colorB = { 0.0F, 0.8784314F, 0.0F, 0.0F, 0.8784314F, 0.8784314F, 0.0F, 1.0F };
/*  21:    */   
/*  22:    */   public RenderTHPhantom()
/*  23:    */   {
/*  24: 25 */     super(null, 0.25F);
/*  25:    */   }
/*  26:    */   
/*  27:    */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/*  28:    */   {
/*  29: 31 */     renderTHPhantom((EntityTHPhantom)entity, x, y, z, yaw, pitch);
/*  30:    */   }
/*  31:    */   
/*  32:    */   public void renderTHPhantom(EntityTHPhantom thPhantom, double x, double y, double z, float yaw, float pitch)
/*  33:    */   {
/*  34: 37 */     GL11.glPushMatrix();
/*  35: 38 */     bindTexture(getEntityTexture(thPhantom));
/*  36: 39 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/*  37:    */     
/*  38: 41 */     GL11.glEnable(3042);
/*  39:    */     
/*  40: 43 */     GL11.glBlendFunc(1, 1);
/*  41: 44 */     GL11.glDepthMask(false);
/*  42: 45 */     GL11.glScalef(1.0F, 1.0F, 1.0F);
/*  43:    */     
/*  44:    */ 
/*  45: 48 */     int color = thPhantom.getForm();
/*  46: 49 */     double time_r = thPhantom.ticksExisted / 180.0D * 3.141592653589793D;
/*  47:    */     
/*  48: 51 */     GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/*  49: 52 */     GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
/*  50:    */     
/*  51: 54 */     float size = 1.0F;
/*  52: 55 */     float size2 = size * 0.7F;
/*  53: 56 */     GL11.glScalef(size, size, size);
/*  54:    */     
/*  55:    */ 
/*  56: 59 */     renderPhantomPart(color, time_r, thPhantom.hurtTime);
/*  57:    */     
/*  58: 61 */     GL11.glTranslatef((float)Math.sin(time_r * 5.0D) * 0.2F, 0.1F + thPhantom.ticksExisted % 20 * 0.06F, 0.0F);
/*  59: 62 */     size = 0.9F * ((20.0F - thPhantom.ticksExisted % 20) / 20.0F);
/*  60: 63 */     GL11.glScalef(size, size, size);
/*  61: 64 */     renderPhantomPart(color, time_r, thPhantom.hurtTime);
/*  62:    */     
/*  63: 66 */     GL11.glTranslatef((float)Math.cos(time_r * 5.0D) * 0.2F, 0.1F + thPhantom.ticksExisted % 20 * 0.07F, 0.0F);
/*  64: 67 */     size = 0.8F * ((20.0F - thPhantom.ticksExisted % 20) / 20.0F);
/*  65: 68 */     GL11.glScalef(size, size, size);
/*  66: 69 */     renderPhantomPart(color, time_r, thPhantom.hurtTime);
/*  67:    */     
/*  68: 71 */     GL11.glDisable(3042);
/*  69:    */     
/*  70: 73 */     GL11.glDepthMask(true);
/*  71: 74 */     GL11.glPopMatrix();
/*  72:    */   }
/*  73:    */   
/*  74:    */   private void renderPhantomPart(int color, double time_r, int damage)
/*  75:    */   {
/*  76: 79 */     Tessellator tessellator = Tessellator.instance;
/*  77: 80 */     int pattern = 0;
/*  78: 81 */     float umin = (pattern % 32 * 32 + 0) / 64.0F;
/*  79: 82 */     float umax = (pattern % 32 * 32 + 32) / 64.0F;
/*  80: 83 */     float vmin = 0.0F;
/*  81: 84 */     float vmax = 1.0F;
/*  82:    */     
/*  83: 86 */     float alpha = (40.0F - damage) / 40.0F;
/*  84:    */     
/*  85: 88 */     tessellator.startDrawingQuads();
/*  86:    */     
/*  87: 90 */     tessellator.setColorRGBA_F(this.colorR[color] * 0.3F, this.colorG[color] * 0.3F, this.colorB[color] * 0.3F, 0.3F * alpha);
/*  88: 91 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  89: 92 */     tessellator.addVertexWithUV(-0.699999988079071D + Math.sin(time_r * 3.0D) * 0.1000000014901161D, -0.2000000029802322D - Math.cos(time_r * 7.0D) * 0.1000000014901161D, 0.0D, umin, vmax);
/*  90: 93 */     tessellator.addVertexWithUV(0.699999988079071D - Math.cos(time_r * 4.0D) * 0.1000000014901161D, -0.2000000029802322D - Math.sin(time_r * 5.0D) * 0.1000000014901161D, 0.0D, umax, vmax);
/*  91: 94 */     tessellator.addVertexWithUV(0.699999988079071D + Math.sin(time_r * 5.0D) * 0.1000000014901161D, 1.200000047683716D + Math.cos(time_r * 4.0D) * 0.1000000014901161D, 0.0D, umax, vmin);
/*  92: 95 */     tessellator.addVertexWithUV(-0.699999988079071D - Math.cos(time_r * 7.0D) * 0.1000000014901161D, 1.200000047683716D + Math.sin(time_r * 3.0D) * 0.1000000014901161D, 0.0D, umin, vmin);
/*  93:    */     
/*  94:    */ 
/*  95: 98 */     tessellator.draw();
/*  96: 99 */     tessellator.startDrawingQuads();
/*  97:    */     
/*  98:101 */     tessellator.setColorRGBA_F(this.colorR[color] * 0.3F, this.colorG[color] * 0.3F, this.colorB[color] * 0.3F, 0.7F * alpha);
/*  99:102 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 100:103 */     tessellator.addVertexWithUV(-0.6000000238418579D - Math.cos(time_r * 7.0D) * 0.1000000014901161D, -0.1000000014901161D + Math.sin(time_r * 5.0D) * 0.1000000014901161D, 0.001D, umin, vmax);
/* 101:104 */     tessellator.addVertexWithUV(0.6000000238418579D + Math.sin(time_r * 3.0D) * 0.1000000014901161D, -0.1000000014901161D + Math.cos(time_r * 4.0D) * 0.1000000014901161D, 0.001D, umax, vmax);
/* 102:105 */     tessellator.addVertexWithUV(0.6000000238418579D - Math.cos(time_r * 4.0D) * 0.1000000014901161D, 1.100000023841858D - Math.sin(time_r * 3.0D) * 0.1000000014901161D, 0.001D, umax, vmin);
/* 103:106 */     tessellator.addVertexWithUV(-0.6000000238418579D + Math.sin(time_r * 5.0D) * 0.1000000014901161D, 1.100000023841858D - Math.cos(time_r * 7.0D) * 0.1000000014901161D, 0.001D, umin, vmin);
/* 104:107 */     tessellator.draw();
/* 105:111 */     for (int i = 0; i < 3; i++)
/* 106:    */     {
/* 107:113 */       tessellator.startDrawingQuads();
/* 108:114 */       if (damage > 0) {
/* 109:116 */         tessellator.setColorRGBA_F(1.0F, 0.0F, 0.0F, alpha);
/* 110:    */       }
/* 111:118 */       tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 112:119 */       tessellator.addVertexWithUV(-0.5D, 0.0D, 0.002D, umin, vmax);
/* 113:120 */       tessellator.addVertexWithUV(0.5D, 0.0D, 0.002D, umax, vmax);
/* 114:121 */       tessellator.addVertexWithUV(0.5D, 1.0D, 0.002D, umax, vmin);
/* 115:122 */       tessellator.addVertexWithUV(-0.5D, 1.0D, 0.002D, umin, vmin);
/* 116:123 */       tessellator.draw();
/* 117:    */     }
/* 118:    */   }
/* 119:    */   
/* 120:    */   protected ResourceLocation getEntityTexture(Entity entity)
/* 121:    */   {
/* 122:130 */     return getEntityTexture((EntityTHPhantom)entity);
/* 123:    */   }
/* 124:    */   
/* 125:    */   protected ResourceLocation getEntityTexture(EntityTHPhantom thFairy)
/* 126:    */   {
/* 127:135 */     return phantomTexture;
/* 128:    */   }
/* 129:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.living.RenderTHPhantom
 * JD-Core Version:    0.7.0.1
 */