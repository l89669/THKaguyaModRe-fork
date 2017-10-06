/*   1:    */ package thKaguyaMod.client.render.shot;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.relauncher.Side;
/*   4:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   5:    */ import java.util.Random;
/*   6:    */ import net.minecraft.client.renderer.Tessellator;
/*   7:    */ import net.minecraft.client.renderer.entity.Render;
/*   8:    */ import net.minecraft.entity.Entity;
/*   9:    */ import net.minecraft.util.ResourceLocation;
/*  10:    */ import org.lwjgl.opengl.GL11;
/*  11:    */ import thKaguyaMod.entity.shot.EntityTHHenyoriLaser;
/*  12:    */ 
/*  13:    */ @SideOnly(Side.CLIENT)
/*  14:    */ public class RenderTHHenyoriLaser
/*  15:    */   extends Render
/*  16:    */ {
/*  17: 23 */   private static final ResourceLocation boatTextures = new ResourceLocation("thkaguyamod", "textures/Laser.png");
/*  18: 24 */   private Random random = new Random();
/*  19:    */   
/*  20:    */   public void doRenderTHHenyoriLaser(EntityTHHenyoriLaser entityTHLaser, double xpos, double ypos, double zpos, float yaw, float pitch)
/*  21:    */   {
/*  22: 33 */     GL11.glPushMatrix();
/*  23: 34 */     bindEntityTexture(entityTHLaser);
/*  24: 35 */     GL11.glTranslatef((float)xpos, (float)ypos, (float)zpos);
/*  25: 36 */     GL11.glDisable(2896);
/*  26:    */     
/*  27: 38 */     GL11.glEnable(32826);
/*  28: 39 */     GL11.glBlendFunc(1, 769);
/*  29: 40 */     GL11.glScalef(1.0F, 1.0F, 1.0F);
/*  30:    */     
/*  31: 42 */     Tessellator tessellator = Tessellator.instance;
/*  32:    */     
/*  33: 44 */     int color = entityTHLaser.getShotColor();
/*  34:    */     
/*  35: 46 */     GL11.glRotatef(entityTHLaser.rotationYaw, 0.0F, 1.0F, 0.0F);
/*  36: 47 */     GL11.glRotatef(-entityTHLaser.rotationPitch, 1.0F, 0.0F, 0.0F);
/*  37:    */     
/*  38: 49 */     double centerZ1 = entityTHLaser.getLaserLength() * 1.2D / 2.0D;
/*  39: 50 */     double centerZ2 = entityTHLaser.getLaserLength() / 2.0D;
/*  40:    */     
/*  41: 52 */     GL11.glEnable(2884);
/*  42: 53 */     GL11.glEnable(32826);
/*  43:    */     
/*  44: 55 */     GL11.glDisable(32826);
/*  45:    */     
/*  46: 57 */     GL11.glEnable(3042);
/*  47: 58 */     renderLaser(tessellator, entityTHLaser.getLaserLength() * 1.2D, entityTHLaser.getShotSize() * 1.2F, 0.0D, color, 0.6F, entityTHLaser.getAnimationCount());
/*  48: 59 */     GL11.glDisable(3042);
/*  49:    */     
/*  50:    */ 
/*  51:    */ 
/*  52:    */ 
/*  53: 64 */     GL11.glEnable(2896);
/*  54:    */     
/*  55:    */ 
/*  56: 67 */     GL11.glPopMatrix();
/*  57:    */   }
/*  58:    */   
/*  59:    */   protected void renderLaser(Tessellator tessellator, double length, float width, double zPos, int color, float alpha, int time)
/*  60:    */   {
/*  61: 73 */     float[] colorR = { 224.0F, 0.0F, 0.0F, 224.0F, 224.0F, 0.0F, 255.0F, 224.0F };
/*  62: 74 */     float[] colorG = { 0.0F, 0.0F, 224.0F, 224.0F, 0.0F, 224.0F, 128.0F, 224.0F };
/*  63: 75 */     float[] colorB = { 0.0F, 224.0F, 0.0F, 0.0F, 224.0F, 224.0F, 0.0F, 224.0F };
/*  64:    */     
/*  65: 77 */     float maxWidth = width;
/*  66:    */     
/*  67: 79 */     int zAngleDivNum = 8;
/*  68:    */     
/*  69: 81 */     double angleSpanZ = 6.283185307179586D / zAngleDivNum;
/*  70:    */     
/*  71: 83 */     int zDivNum = 13;
/*  72: 84 */     double zLength = length;
/*  73: 85 */     double zDivLength = zLength / (zDivNum - 1);
/*  74: 86 */     double zLength2 = zLength / 2.0D;
/*  75:    */     
/*  76:    */ 
/*  77: 89 */     double zPosOld = zPos;
/*  78:    */     
/*  79:    */ 
/*  80: 92 */     float angleSpanY = 0.1047198F;
/*  81: 93 */     float angleY = angleSpanY * time;
/*  82:    */     
/*  83:    */ 
/*  84: 96 */     float angle = 0.0F;
/*  85: 97 */     float angleSpan = 3.141593F / (zDivNum - 1);
/*  86:    */     
/*  87:    */ 
/*  88:100 */     width = (float)Math.sin(angle) * maxWidth;
/*  89:101 */     float widthOld = width;
/*  90:    */     
/*  91:    */ 
/*  92:104 */     float xPos = (float)Math.sin(angleY) * 3.0F;
/*  93:105 */     float yPos = 0.0F;
/*  94:106 */     float xPos2 = (float)Math.sin(angleY - angleSpanY) * 3.0F;
/*  95:107 */     float yPos2 = 0.0F;
/*  96:    */     
/*  97:109 */     float xPosOld = xPos;
/*  98:110 */     float yPosOld = yPos;
/*  99:111 */     float xPos2Old = xPos2;
/* 100:112 */     float yPos2Old = yPos2;
/* 101:123 */     for (int j = 1; j < zDivNum; j++)
/* 102:    */     {
/* 103:125 */       zPos += zDivLength;
/* 104:126 */       widthOld = width;
/* 105:127 */       angle += angleSpan;
/* 106:128 */       width = (float)Math.sin(angle) * maxWidth;
/* 107:    */       
/* 108:    */ 
/* 109:    */ 
/* 110:    */ 
/* 111:133 */       double angleZ = angleSpanZ;
/* 112:134 */       angleY += angleSpanY;
/* 113:136 */       for (int i = 0; i <= zAngleDivNum; i++)
/* 114:    */       {
/* 115:138 */         xPos = (float)Math.cos(angleZ) * width + (float)Math.sin(angleY) * 3.0F;
/* 116:139 */         yPos = (float)Math.sin(angleZ) * width;
/* 117:140 */         xPos2 = (float)Math.cos(angleZ) * widthOld + (float)Math.sin(angleY - angleSpanY) * 3.0F;
/* 118:141 */         yPos2 = (float)Math.sin(angleZ) * widthOld;
/* 119:    */         
/* 120:143 */         tessellator.startDrawingQuads();
/* 121:144 */         tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], alpha);
/* 122:145 */         tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 123:146 */         tessellator.addVertexWithUV(xPosOld, yPosOld, zPos, 0.0D, 0.0D);
/* 124:147 */         tessellator.addVertexWithUV(xPos, yPos, zPos, 1.0D, 0.0D);
/* 125:148 */         tessellator.addVertexWithUV(xPos2, yPos2, zPosOld, 1.0D, 1.0D);
/* 126:149 */         tessellator.addVertexWithUV(xPos2Old, yPos2Old, zPosOld, 0.0D, 1.0D);
/* 127:150 */         tessellator.draw();
/* 128:    */         
/* 129:152 */         xPosOld = xPos;
/* 130:153 */         yPosOld = yPos;
/* 131:154 */         xPos2Old = xPos2;
/* 132:155 */         yPos2Old = yPos2;
/* 133:156 */         angleZ += angleSpanZ;
/* 134:    */       }
/* 135:160 */       zPosOld = zPos;
/* 136:    */     }
/* 137:    */   }
/* 138:    */   
/* 139:    */   protected ResourceLocation func_110781_a(EntityTHHenyoriLaser entityTHLaser)
/* 140:    */   {
/* 141:166 */     return boatTextures;
/* 142:    */   }
/* 143:    */   
/* 144:    */   protected ResourceLocation getEntityTexture(Entity entity)
/* 145:    */   {
/* 146:171 */     return func_110781_a((EntityTHHenyoriLaser)entity);
/* 147:    */   }
/* 148:    */   
/* 149:    */   public void doRender(Entity entity, double xpos, double ypos, double zpos, float yaw, float pitch)
/* 150:    */   {
/* 151:177 */     doRenderTHHenyoriLaser((EntityTHHenyoriLaser)entity, xpos, ypos, zpos, yaw, pitch);
/* 152:    */   }
/* 158:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.shot.RenderTHHenyoriLaser
 * JD-Core Version:    0.7.0.1
 */