/*   1:    */ package thKaguyaMod.client.render;
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
/*  12:    */ import thKaguyaMod.entity.item.EntityMazinkyoukan;
/*  13:    */ 
/*  14:    */ @SideOnly(Side.CLIENT)
/*  15:    */ public class RenderMazinkyoukan
/*  16:    */   extends Render
/*  17:    */ {
/*  18: 22 */   private static final ResourceLocation mazinkyoukanTexture = new ResourceLocation("thkaguyamod", "textures/Mazinkyoukan_Texture.png");
/*  19: 23 */   private Random random = new Random();
/*  20:    */   
/*  21:    */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/*  22:    */   {
/*  23: 32 */     renderMazinkyoukan((EntityMazinkyoukan)entity, x, y, z, yaw, pitch);
/*  24:    */   }
/*  25:    */   
/*  26:    */   public void renderMazinkyoukan(EntityMazinkyoukan mazinkyoukan, double x, double y, double z, float yaw, float pitch)
/*  27:    */   {
/*  28: 37 */     GL11.glPushMatrix();
/*  29: 38 */     bindTexture(getEntityTexture(mazinkyoukan));
/*  30: 39 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/*  31: 40 */     GL11.glDisable(2896);
/*  32:    */     
/*  33: 42 */     GL11.glDisable(2884);
/*  34: 43 */     GL11.glScalef(1.0F, 1.0F, 1.0F);
/*  35: 44 */     Tessellator tessellator = Tessellator.instance;
/*  36:    */     
/*  37:    */ 
/*  38: 47 */     int count = mazinkyoukan.count;
/*  39:    */     
/*  40:    */ 
/*  41:    */ 
/*  42:    */ 
/*  43: 52 */     float angle = mazinkyoukan.rotationYaw - 57.0F;
/*  44: 53 */     float py = -0.3F;
/*  45: 54 */     float px = -MathHelper.sin(angle / 180.0F * 3.141593F) * 1.6F;
/*  46: 55 */     double pz = Math.cos(angle / 180.0F * 3.141593F) * 1.6D;
/*  47: 56 */     float nextpx = -MathHelper.sin((angle + 1.0F) / 180.0F * 3.141593F) * 1.6F;
/*  48: 57 */     double nextpz = Math.cos((angle + 1.0F) / 180.0F * 3.141593F) * 1.6D;
/*  49: 58 */     float widthY = 0.3F;
/*  50:    */     
/*  51:    */ 
/*  52: 61 */     float widthY2 = widthY + 0.1F;
/*  53: 62 */     float px2min = -MathHelper.sin((angle - 2.0F) / 180.0F * 3.141593F) * 1.55F;
/*  54: 63 */     float px2max = -MathHelper.sin((angle + 2.0F) / 180.0F * 3.141593F) * 1.65F;
/*  55: 64 */     float pz2min = MathHelper.cos((angle - 2.0F) / 180.0F * 3.141593F) * 1.55F;
/*  56: 65 */     float pz2max = MathHelper.cos((angle + 2.0F) / 180.0F * 3.141593F) * 1.65F;
/*  57: 66 */     tessellator.startDrawingQuads();
/*  58: 67 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  59: 68 */     tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
/*  60: 69 */     tessellator.addVertexWithUV(px2min, py - widthY2, pz2min, 0.875D, 1.0D);
/*  61: 70 */     tessellator.addVertexWithUV(px2max, py - widthY2, pz2max, 1.0D, 1.0D);
/*  62: 71 */     tessellator.addVertexWithUV(px2max, py + widthY2, pz2max, 1.0D, 0.5D);
/*  63: 72 */     tessellator.addVertexWithUV(px2min, py + widthY2, pz2min, 0.875D, 0.5D);
/*  64: 73 */     tessellator.draw();
/*  65:    */     
/*  66:    */ 
/*  67: 76 */     float colorRev = 0.05235988F;
/*  68: 77 */     float ticks = mazinkyoukan.ticksExisted;
/*  69:    */     
/*  70: 79 */     GL11.glEnable(3042);
/*  71: 80 */     GL11.glBlendFunc(1, 769);
/*  72: 81 */     for (int i = 0; i < count; i++)
/*  73:    */     {
/*  74: 83 */       float color = (angle + ticks) * colorRev;
/*  75: 84 */       float umin = i % 63 / 64.0F;
/*  76: 85 */       float umax = (i + 1) % 63 / 64.0F;
/*  77: 86 */       float vmin = i / 63 * 16.0F / 32.0F;
/*  78: 87 */       float vmax = vmin + 0.5F;
/*  79: 88 */       tessellator.startDrawingQuads();
/*  80: 89 */       tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  81: 90 */       tessellator.setColorRGBA_F(MathHelper.sin(color), MathHelper.cos(color), -MathHelper.sin(color), 0.5F);
/*  82: 91 */       tessellator.addVertexWithUV(px, py - widthY, pz, umin, vmax);
/*  83: 92 */       tessellator.addVertexWithUV(nextpx, py - widthY, nextpz, umax, vmax);
/*  84: 93 */       tessellator.addVertexWithUV(nextpx, py + widthY, nextpz, umax, vmin);
/*  85: 94 */       tessellator.addVertexWithUV(px, py + widthY, pz, umin, vmin);
/*  86: 95 */       tessellator.draw();
/*  87: 96 */       angle += 1.0F;
/*  88: 97 */       px = nextpx;
/*  89: 98 */       pz = nextpz;
/*  90: 99 */       nextpx = -MathHelper.sin((angle + 1.0F) / 180.0F * 3.141593F) * 1.6F;
/*  91:100 */       nextpz = Math.cos((angle + 1.0F) / 180.0F * 3.141593F) * 1.6D;
/*  92:    */     }
/*  93:102 */     GL11.glDisable(3042);
/*  94:    */     
/*  95:104 */     px2min = -MathHelper.sin((angle - 2.0F) / 180.0F * 3.141593F) * 1.55F;
/*  96:105 */     px2max = -MathHelper.sin((angle + 2.0F) / 180.0F * 3.141593F) * 1.65F;
/*  97:106 */     pz2min = MathHelper.cos((angle - 2.0F) / 180.0F * 3.141593F) * 1.55F;
/*  98:107 */     pz2max = MathHelper.cos((angle + 2.0F) / 180.0F * 3.141593F) * 1.65F;
/*  99:108 */     tessellator.startDrawingQuads();
/* 100:109 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 101:110 */     tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
/* 102:111 */     tessellator.addVertexWithUV(px2min, py - widthY2, pz2min, 1.0D, 1.0D);
/* 103:112 */     tessellator.addVertexWithUV(px2max, py - widthY2, pz2max, 0.875D, 1.0D);
/* 104:113 */     tessellator.addVertexWithUV(px2max, py + widthY2, pz2max, 0.875D, 0.5D);
/* 105:114 */     tessellator.addVertexWithUV(px2min, py + widthY2, pz2min, 1.0D, 0.5D);
/* 106:115 */     tessellator.draw();
/* 107:    */     
/* 108:117 */     GL11.glEnable(2884);
/* 109:118 */     GL11.glEnable(2896);
/* 110:119 */     GL11.glPopMatrix();
/* 111:    */   }
/* 112:    */   
/* 113:    */   protected ResourceLocation getEntityTexture(Entity entity)
/* 114:    */   {
/* 115:124 */     return mazinkyoukanTexture;
/* 116:    */   }
/* 117:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.RenderMazinkyoukan
 * JD-Core Version:    0.7.0.1
 */