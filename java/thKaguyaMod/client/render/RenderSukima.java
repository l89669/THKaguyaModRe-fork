/*   1:    */ package thKaguyaMod.client.render;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.relauncher.Side;
/*   4:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   5:    */ import net.minecraft.client.renderer.Tessellator;
/*   6:    */ import net.minecraft.client.renderer.entity.Render;
/*   7:    */ import net.minecraft.entity.Entity;
/*   8:    */ import net.minecraft.util.MathHelper;
/*   9:    */ import net.minecraft.util.ResourceLocation;
/*  10:    */ import org.lwjgl.opengl.GL11;
/*  11:    */ import thKaguyaMod.entity.item.EntitySukima;
/*  12:    */ 
/*  13:    */ @SideOnly(Side.CLIENT)
/*  14:    */ public class RenderSukima
/*  15:    */   extends Render
/*  16:    */ {
/*  17: 20 */   private static final ResourceLocation sukimaTexture = new ResourceLocation("thkaguyamod", "textures/SukimaTexture3.png");
/*  18:    */   
/*  19:    */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/*  20:    */   {
/*  21: 29 */     renderSukima((EntitySukima)entity, x, y, z, yaw, pitch);
/*  22:    */   }
/*  23:    */   
/*  24:    */   public void renderSukima(EntitySukima sukima, double x, double y, double z, float yaw, float pitch)
/*  25:    */   {
/*  26: 34 */     int[] colorR = { 30, 179, 59, 81, 37, 123, 40, 128, 51, 216, 65, 222, 102, 211, 237, 240, 179 };
/*  27: 35 */     int[] colorG = { 27, 49, 81, 48, 49, 47, 118, 128, 51, 129, 203, 207, 133, 84, 136, 240, 49 };
/*  28: 36 */     int[] colorB = { 27, 44, 26, 26, 146, 190, 151, 128, 51, 152, 51, 42, 219, 205, 68, 240, 44 };
/*  29:    */     
/*  30: 38 */     GL11.glPushMatrix();
/*  31: 39 */     bindEntityTexture(sukima);
/*  32: 40 */     GL11.glTranslatef((float)x, (float)y + 1.0F + MathHelper.sin(sukima.ticksExisted / 20.0F) * 0.1F, (float)z);
/*  33: 41 */     GL11.glDisable(2896);
/*  34: 42 */     GL11.glDepthMask(true);
/*  35:    */     
/*  36: 44 */     float f2 = 1.0F;
/*  37: 45 */     GL11.glScalef(f2 / 1.0F, f2 / 1.0F, f2 / 1.0F);
/*  38: 46 */     Tessellator tessellator = Tessellator.instance;
/*  39: 47 */     int color = sukima.getColor();
/*  40: 48 */     int color2 = 0;
/*  41: 49 */     float f3 = color2 * 16 / 64.0F;
/*  42: 50 */     float f4 = (color2 * 16 + 16) / 64.0F;
/*  43: 51 */     float f5 = 0.0F;
/*  44: 52 */     float f6 = 1.0F;
/*  45: 53 */     float f7 = 1.0F;
/*  46: 54 */     float f8 = 0.5F;
/*  47: 55 */     float f9 = 0.25F;
/*  48: 56 */     float xl = 1.0F;
/*  49: 57 */     float yl = -0.75F;
/*  50: 58 */     float xr = -1.0F;
/*  51: 59 */     float yr = 0.75F;
/*  52: 60 */     int time = sukima.ticksExisted;
/*  53: 61 */     float openRate = MathHelper.sin(time * 15.0F / 360.0F * 3.141593F);
/*  54: 63 */     if (color == 17)
/*  55:    */     {
/*  56: 65 */       openRate = MathHelper.sin(time * 40.0F / 360.0F * 3.141593F);
/*  57: 66 */       color = 16;
/*  58:    */     }
/*  59: 68 */     if (color >= 16)
/*  60:    */     {
/*  61: 70 */       xl *= openRate;
/*  62: 71 */       yl *= openRate;
/*  63: 72 */       xr *= openRate;
/*  64: 73 */       yr *= openRate;
/*  65:    */     }
/*  66: 76 */     color %= 32;
/*  67:    */     
/*  68: 78 */     GL11.glRotatef(180.0F - yaw, 0.0F, 1.0F, 0.0F);
/*  69: 79 */     GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
/*  70:    */     
/*  71: 81 */     tessellator.startDrawingQuads();
/*  72: 82 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  73: 83 */     tessellator.addVertexWithUV(-1.0D, -0.75D, 0.0D, 0.0D, 1.0D);
/*  74: 84 */     tessellator.addVertexWithUV(xl, yl, 0.0D, 0.75D, 1.0D);
/*  75: 85 */     tessellator.addVertexWithUV(1.0D, 0.75D, 0.0D, 0.75D, 0.0D);
/*  76: 86 */     tessellator.addVertexWithUV(xr, yr, 0.0D, 0.0D, 0.0D);
/*  77: 87 */     tessellator.draw();
/*  78:    */     
/*  79: 89 */     GL11.glEnable(3042);
/*  80: 90 */     GL11.glBlendFunc(770, 770);
/*  81: 91 */     tessellator.startDrawingQuads();
/*  82: 92 */     tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.7F);
/*  83: 93 */     tessellator.addVertexWithUV(xl, yl, -0.0009D, 0.75D, 1.0D);
/*  84: 94 */     tessellator.addVertexWithUV(-1.0D, -0.75D, -0.0009D, 0.0D, 1.0D);
/*  85: 95 */     tessellator.addVertexWithUV(xr, yr, -0.0009D, 0.0D, 0.0D);
/*  86: 96 */     tessellator.addVertexWithUV(1.0D, 0.75D, -0.0009D, 0.75D, 0.0D);
/*  87: 97 */     tessellator.draw();
/*  88: 98 */     GL11.glDisable(3042);
/*  89:    */     
/*  90:    */ 
/*  91:101 */     GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
/*  92:102 */     tessellator.startDrawingQuads();
/*  93:103 */     tessellator.setColorRGBA_F(colorR[color] / 255.0F, colorG[color] / 255.0F, colorB[color] / 255.0F, 0.7F);
/*  94:104 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  95:105 */     tessellator.addVertexWithUV(1.0D, -0.2000000029802322D, 0.001D, 0.75D, 1.0D);
/*  96:106 */     tessellator.addVertexWithUV(1.25D, -0.2000000029802322D, 0.001D, 1.0D, 1.0D);
/*  97:107 */     tessellator.addVertexWithUV(1.25D, 0.300000011920929D, 0.001D, 1.0D, 0.0D);
/*  98:108 */     tessellator.addVertexWithUV(1.0D, 0.300000011920929D, 0.001D, 0.75D, 0.0D);
/*  99:109 */     tessellator.draw();
/* 100:    */     
/* 101:    */ 
/* 102:112 */     tessellator.startDrawingQuads();
/* 103:113 */     tessellator.setColorRGBA_F(colorR[color] / 255.0F, colorG[color] / 255.0F, colorB[color] / 255.0F, 0.7F);
/* 104:114 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 105:115 */     tessellator.addVertexWithUV(-1.299999952316284D, -0.300000011920929D, 0.001D, 0.75D, 1.0D);
/* 106:116 */     tessellator.addVertexWithUV(-1.049999952316284D, -0.300000011920929D, 0.001D, 1.0D, 1.0D);
/* 107:117 */     tessellator.addVertexWithUV(-1.049999952316284D, 0.2000000029802322D, 0.001D, 1.0D, 0.0D);
/* 108:118 */     tessellator.addVertexWithUV(-1.299999952316284D, 0.2000000029802322D, 0.001D, 0.75D, 0.0D);
/* 109:119 */     tessellator.draw();
/* 110:    */     
/* 111:121 */     GL11.glEnable(3042);
/* 112:122 */     GL11.glBlendFunc(770, 770);
/* 113:    */     
/* 114:124 */     tessellator.startDrawingQuads();
/* 115:125 */     tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.7F);
/* 116:126 */     tessellator.addVertexWithUV(1.0D, 0.300000011920929D, -0.001D, 0.75D, 0.0D);
/* 117:127 */     tessellator.addVertexWithUV(1.25D, 0.300000011920929D, -0.001D, 1.0D, 0.0D);
/* 118:128 */     tessellator.addVertexWithUV(1.25D, -0.2000000029802322D, -0.001D, 1.0D, 1.0D);
/* 119:129 */     tessellator.addVertexWithUV(1.0D, -0.2000000029802322D, -0.001D, 0.75D, 1.0D);
/* 120:130 */     tessellator.draw();
/* 121:    */     
/* 122:132 */     tessellator.startDrawingQuads();
/* 123:133 */     tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.7F);
/* 124:134 */     tessellator.addVertexWithUV(-1.299999952316284D, 0.2000000029802322D, -0.001D, 0.75D, 0.0D);
/* 125:135 */     tessellator.addVertexWithUV(-1.049999952316284D, 0.2000000029802322D, -0.001D, 1.0D, 0.0D);
/* 126:136 */     tessellator.addVertexWithUV(-1.049999952316284D, -0.300000011920929D, -0.001D, 1.0D, 1.0D);
/* 127:137 */     tessellator.addVertexWithUV(-1.299999952316284D, -0.300000011920929D, -0.001D, 0.75D, 1.0D);
/* 128:138 */     tessellator.draw();
/* 129:    */     
/* 130:140 */     GL11.glDisable(3042);
/* 131:    */     
/* 132:142 */     GL11.glEnable(2896);
/* 133:143 */     GL11.glPopMatrix();
/* 134:    */   }
/* 135:    */   
/* 136:    */   protected ResourceLocation getEntityTexture(Entity entity)
/* 137:    */   {
/* 138:148 */     return sukimaTexture;
/* 139:    */   }
/* 140:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.RenderSukima
 * JD-Core Version:    0.7.0.1
 */