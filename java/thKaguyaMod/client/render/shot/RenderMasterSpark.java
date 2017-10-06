/*   1:    */ package thKaguyaMod.client.render.shot;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.relauncher.Side;
/*   4:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   5:    */ import net.minecraft.client.renderer.Tessellator;
/*   6:    */ import net.minecraft.client.renderer.entity.Render;
/*   7:    */ import net.minecraft.entity.Entity;
/*   8:    */ import net.minecraft.util.MathHelper;
/*   9:    */ import net.minecraft.util.ResourceLocation;
/*  10:    */ import org.lwjgl.opengl.GL11;
/*  11:    */ import thKaguyaMod.entity.shot.EntityMasterSpark;
/*  12:    */ 
/*  13:    */ @SideOnly(Side.CLIENT)
/*  14:    */ public class RenderMasterSpark
/*  15:    */   extends Render
/*  16:    */ {
/*  17: 22 */   private static final ResourceLocation masuterSparkTexture = new ResourceLocation("thkaguyamod", "textures/Laser.png");
/*  18:    */   
/*  19:    */   public void doRender(Entity entity, double xpos, double ypos, double zpos, float yaw, float pitch)
/*  20:    */   {
/*  21: 32 */     renderMasterSpark((EntityMasterSpark)entity, xpos, ypos, zpos, yaw, pitch);
/*  22:    */   }
/*  23:    */   
/*  24:    */   public void renderMasterSpark(EntityMasterSpark masterSpark, double xpos, double ypos, double zpos, float yaw, float pitch)
/*  25:    */   {
/*  26: 38 */     GL11.glPushMatrix();
/*  27: 39 */     bindEntityTexture(masterSpark);
/*  28: 40 */     GL11.glTranslatef((float)xpos, (float)ypos, (float)zpos);
/*  29: 41 */     GL11.glDisable(2896);
/*  30:    */     
/*  31: 43 */     GL11.glDisable(2884);
/*  32:    */     
/*  33: 45 */     GL11.glScalef(1.0F, 1.0F, 1.0F);
/*  34: 46 */     Tessellator tessellator = Tessellator.instance;
/*  35:    */     
/*  36: 48 */     GL11.glRotatef(yaw, 0.0F, 1.0F, 0.0F);
/*  37: 49 */     GL11.glRotatef(-masterSpark.rotationPitch, 1.0F, 0.0F, 0.0F);
/*  38:    */     
/*  39: 51 */     float ticks = masterSpark.getIniTime() * 10.0F;
/*  40: 53 */     if (masterSpark.getIniTime() > 30)
/*  41:    */     {
/*  42: 56 */       renderMasterSparkLaser(tessellator, masterSpark.getMaxLength(), 4.2F, 1.0F, false, ticks);
/*  43:    */       
/*  44: 58 */       GL11.glEnable(3042);
/*  45: 59 */       GL11.glBlendFunc(1, 769);
/*  46: 60 */       renderMasterSparkLaser(tessellator, masterSpark.getMaxLength(), 5.0F, 0.6F, true, ticks);
/*  47: 61 */       GL11.glDisable(3042);
/*  48:    */     }
/*  49:    */     else
/*  50:    */     {
/*  51: 66 */       renderMasterSparkLaser(tessellator, masterSpark.getMaxLength(), 0.168F, 1.0F, false, ticks);
/*  52:    */       
/*  53: 68 */       GL11.glEnable(3042);
/*  54: 69 */       GL11.glBlendFunc(1, 769);
/*  55: 70 */       renderMasterSparkLaser(tessellator, masterSpark.getMaxLength(), 0.2F, 0.6F, true, ticks);
/*  56: 71 */       GL11.glDisable(3042);
/*  57:    */     }
/*  58: 74 */     GL11.glEnable(2896);
/*  59: 75 */     GL11.glEnable(2884);
/*  60: 76 */     GL11.glPopMatrix();
/*  61:    */   }
/*  62:    */   
/*  63:    */   protected void renderMasterSparkLaser(Tessellator tessellator, double length, float width, float alpha, boolean rainbowFlag, float ticks)
/*  64:    */   {
/*  65: 84 */     float[] colorR = { 255.0F, 255.0F, 224.0F, 0.0F, 0.0F, 0.0F, 224.0F, 255.0F };
/*  66: 85 */     float[] colorG = { 0.0F, 165.0F, 224.0F, 255.0F, 0.0F, 255.0F, 0.0F, 255.0F };
/*  67: 86 */     float[] colorB = { 0.0F, 0.0F, 0.0F, 0.0F, 255.0F, 255.0F, 224.0F, 255.0F };
/*  68:    */     
/*  69: 88 */     float maxWidth = width;
/*  70:    */     
/*  71: 90 */     int zAngleDivNum = 64;
/*  72: 91 */     if (!rainbowFlag) {
/*  73: 93 */       zAngleDivNum = 16;
/*  74:    */     }
/*  75: 96 */     double angleSpanZ = 6.283185307179586D / zAngleDivNum;
/*  76:    */     
/*  77: 98 */     int zDivNum = 13;
/*  78: 99 */     double zLength = length;
/*  79:100 */     double zDivLength = zLength / (zDivNum - 1);
/*  80:101 */     double zLength2 = zLength / 2.0D;
/*  81:102 */     double zPos = 0.0D;
/*  82:    */     
/*  83:104 */     double zPosOld = zPos;
/*  84:    */     
/*  85:106 */     float xPos = 0.0F;
/*  86:107 */     float yPos = 0.0F;
/*  87:108 */     float xPos2 = 0.0F;
/*  88:109 */     float yPos2 = 0.0F;
/*  89:    */     
/*  90:111 */     float xPosOld = xPos;
/*  91:112 */     float yPosOld = yPos;
/*  92:113 */     float xPos2Old = xPos2;
/*  93:114 */     float yPos2Old = yPos2;
/*  94:    */     
/*  95:116 */     float angle = 0.0F;
/*  96:117 */     float angleSpan = 3.141593F / (zDivNum - 1);
/*  97:    */     
/*  98:119 */     width = (float)Math.sin(angle) * maxWidth;
/*  99:120 */     float widthOld = width;
/* 100:    */     
/* 101:    */ 
/* 102:123 */     float colorAngle = 0.0F;
/* 103:124 */     float colorRev = 0.09795661F;
/* 104:    */     
/* 105:126 */     float width2 = width;
/* 106:127 */     float widthOld2 = widthOld;
/* 107:130 */     while (zPos < zLength)
/* 108:    */     {
/* 109:132 */       zPos += zDivLength;
/* 110:133 */       widthOld = width;
/* 111:134 */       angle += angleSpan;
/* 112:135 */       if (width < maxWidth) {
/* 113:137 */         width = (float)Math.sin(angle) * maxWidth;
/* 114:    */       } else {
/* 115:141 */         width = maxWidth;
/* 116:    */       }
/* 117:146 */       widthOld2 = width2;
/* 118:147 */       width2 = width * ((float)Math.sin(angle + ticks / 40.0F) * 0.1F + 1.0F);
/* 119:    */       
/* 120:    */ 
/* 121:    */ 
/* 122:    */ 
/* 123:    */ 
/* 124:    */ 
/* 125:    */ 
/* 126:155 */       xPos = 0.0F;
/* 127:156 */       yPos = width;
/* 128:    */       
/* 129:158 */       double angleZ = angleSpanZ;
/* 130:160 */       for (int i = 0; i <= zAngleDivNum; i++)
/* 131:    */       {
/* 132:162 */         xPos = (float)Math.cos(angleZ) * width2;
/* 133:163 */         yPos = (float)Math.sin(angleZ) * width2;
/* 134:164 */         xPos2 = (float)Math.cos(angleZ) * widthOld2;
/* 135:165 */         yPos2 = (float)Math.sin(angleZ) * widthOld2;
/* 136:    */         
/* 137:167 */         tessellator.startDrawingQuads();
/* 138:168 */         if (rainbowFlag)
/* 139:    */         {
/* 140:170 */           float color = (colorAngle + ticks) * colorRev;
/* 141:171 */           colorAngle += 1.0F;
/* 142:172 */           tessellator.setColorRGBA_F(MathHelper.sin(color), MathHelper.cos(color), -MathHelper.sin(color) * 2.0F, 0.6F);
/* 143:    */         }
/* 144:    */         else
/* 145:    */         {
/* 146:176 */           tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, alpha);
/* 147:    */         }
/* 148:178 */         tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 149:179 */         tessellator.addVertexWithUV(xPosOld, yPosOld, zPos, 0.0D, 0.0D);
/* 150:180 */         tessellator.addVertexWithUV(xPos, yPos, zPos, 1.0D, 0.0D);
/* 151:181 */         tessellator.addVertexWithUV(xPos2, yPos2, zPosOld, 1.0D, 1.0D);
/* 152:182 */         tessellator.addVertexWithUV(xPos2Old, yPos2Old, zPosOld, 0.0D, 1.0D);
/* 153:183 */         tessellator.draw();
/* 154:    */         
/* 155:185 */         xPosOld = xPos;
/* 156:186 */         yPosOld = yPos;
/* 157:187 */         xPos2Old = xPos2;
/* 158:188 */         yPos2Old = yPos2;
/* 159:189 */         angleZ += angleSpanZ;
/* 160:    */       }
/* 161:193 */       ticks += 1.0F;
/* 162:194 */       zPosOld = zPos;
/* 163:    */     }
/* 164:    */   }
/* 165:    */   
/* 166:    */   protected ResourceLocation getEntityTexture(EntityMasterSpark masterSpark)
/* 167:    */   {
/* 168:201 */     return masuterSparkTexture;
/* 169:    */   }
/* 170:    */   
/* 171:    */   protected ResourceLocation getEntityTexture(Entity entity)
/* 172:    */   {
/* 173:207 */     return getEntityTexture((EntityMasterSpark)entity);
/* 174:    */   }
/* 175:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.shot.RenderMasterSpark
 * JD-Core Version:    0.7.0.1
 */