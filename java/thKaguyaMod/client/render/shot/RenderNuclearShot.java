/*   1:    */ package thKaguyaMod.client.render.shot;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.relauncher.Side;
/*   4:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   5:    */ import net.minecraft.client.renderer.Tessellator;
/*   6:    */ import net.minecraft.client.renderer.entity.Render;
/*   7:    */ import net.minecraft.client.renderer.entity.RenderManager;
/*   8:    */ import net.minecraft.entity.Entity;
/*   9:    */ import net.minecraft.util.ResourceLocation;
/*  10:    */ import org.lwjgl.opengl.GL11;
/*  11:    */ import thKaguyaMod.entity.shot.EntityNuclearShot;
/*  12:    */ import thKaguyaMod.entity.shot.EntityTHShot;
/*  13:    */ 
/*  14:    */ @SideOnly(Side.CLIENT)
/*  15:    */ public class RenderNuclearShot
/*  16:    */   extends Render
/*  17:    */ {
/*  18: 22 */   private static final ResourceLocation nuclearShotTexture = new ResourceLocation("thkaguyamod", "textures/shot/Laser.png");
/*  19: 23 */   private static final ResourceLocation lightTexture = new ResourceLocation("thkaguyamod", "textures/shot/MusouFuuin.png");
/*  20: 24 */   protected float[] colorR = { 0.8784314F, 0.0F, 0.0F, 0.8784314F, 0.8784314F, 0.0F, 1.0F, 1.0F };
/*  21: 25 */   protected float[] colorG = { 0.0F, 0.0F, 0.8784314F, 0.2509804F, 0.0F, 0.8784314F, 0.5019608F, 1.0F };
/*  22: 26 */   protected float[] colorB = { 0.0F, 0.8784314F, 0.0F, 0.0F, 0.8784314F, 0.8784314F, 0.0F, 1.0F };
/*  23:    */   
/*  24:    */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/*  25:    */   {
/*  26: 35 */     renderNuclearShot((EntityNuclearShot)entity, x, y, z, yaw, pitch);
/*  27:    */   }
/*  28:    */   
/*  29:    */   public void renderNuclearShot(EntityNuclearShot nuclearShot, double x, double y, double z, float yaw, float pitch)
/*  30:    */   {
/*  31: 40 */     GL11.glPushMatrix();
/*  32: 41 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/*  33: 42 */     GL11.glDisable(2896);
/*  34: 43 */     GL11.glEnable(3042);
/*  35: 44 */     GL11.glBlendFunc(1, 769);
/*  36: 45 */     GL11.glScalef(1.0F, 1.0F, 1.0F);
/*  37: 46 */     Tessellator tessellator = Tessellator.instance;
/*  38:    */     
/*  39: 48 */     int color = nuclearShot.getShotColor();
/*  40: 49 */     int time = nuclearShot.ticksExisted;
/*  41:    */     
/*  42: 51 */     bindEntityTexture(nuclearShot);
/*  43: 53 */     if (nuclearShot.getAnimationCount() < 0)
/*  44:    */     {
/*  45: 56 */       int delayCount = -nuclearShot.getAnimationCount();
/*  46: 57 */       if (delayCount > 10) {
/*  47: 59 */         delayCount = 10;
/*  48:    */       }
/*  49: 61 */       float size2 = delayCount * nuclearShot.getShotSize() * 2.0F;
/*  50: 62 */       if (size2 > 1.0F) {
/*  51: 64 */         size2 = 1.0F;
/*  52:    */       }
/*  53: 66 */       GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/*  54: 67 */       GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
/*  55: 68 */       renderLightEffect(tessellator, color % 8, size2, nuclearShot);
/*  56:    */     }
/*  57:    */     else
/*  58:    */     {
/*  59: 72 */       GL11.glRotatef(yaw, 0.0F, 1.0F, 0.0F);
/*  60: 73 */       GL11.glRotatef(-nuclearShot.rotationPitch, 1.0F, 0.0F, 0.0F);
/*  61:    */       
/*  62: 75 */       double centerZ1 = nuclearShot.getShotSize() * 1.2D / 2.0D;
/*  63: 76 */       double centerZ2 = nuclearShot.getShotSize() / 2.0D;
/*  64:    */       
/*  65: 78 */       GL11.glEnable(2884);
/*  66: 79 */       renderLaser(tessellator, nuclearShot.getShotSize(), nuclearShot.getShotSize(), centerZ1 - centerZ2, 7, 0.8F, 0);
/*  67:    */       
/*  68: 81 */       GL11.glEnable(3042);
/*  69: 82 */       GL11.glBlendFunc(1, 1);
/*  70: 83 */       GL11.glDepthMask(false);
/*  71: 84 */       renderLaser(tessellator, nuclearShot.getShotSize() * 1.4D, nuclearShot.getShotSize() * 1.4F, 0.0D, color, 0.8F, time);
/*  72: 85 */       GL11.glDisable(3042);
/*  73: 86 */       GL11.glDepthMask(true);
/*  74:    */     }
/*  75: 90 */     GL11.glDisable(3042);
/*  76: 91 */     GL11.glPopMatrix();
/*  77:    */   }
/*  78:    */   
/*  79:    */   protected void renderLaser(Tessellator tessellator, double length, float width, double zPos, int color, float alpha, int time)
/*  80:    */   {
/*  81: 98 */     float maxWidth = width / 2.0F;
/*  82:    */     
/*  83:100 */     int zAngleDivNum = 18;
/*  84:101 */     float zSpan = 360.0F / zAngleDivNum;
/*  85:102 */     double angleZ = 0.0D;
/*  86:103 */     double angleSpanZ = 6.283185307179586D / zAngleDivNum;
/*  87:    */     
/*  88:105 */     int zDivNum = 9;
/*  89:106 */     double zLength = width;
/*  90:107 */     double zDivLength = zLength / (zDivNum - 1);
/*  91:108 */     double zLength2 = zLength / 2.0D;
/*  92:    */     
/*  93:    */ 
/*  94:111 */     zPos = Math.sin(-1.570796326794897D) * maxWidth;
/*  95:112 */     double zPosOld = zPos;
/*  96:    */     
/*  97:114 */     float xPos = 0.0F;
/*  98:115 */     float yPos = 0.0F;
/*  99:116 */     float xPos2 = 0.0F;
/* 100:117 */     float yPos2 = 0.0F;
/* 101:    */     
/* 102:119 */     float xPosOld = xPos;
/* 103:120 */     float yPosOld = yPos;
/* 104:121 */     float xPos2Old = xPos2;
/* 105:122 */     float yPos2Old = yPos2;
/* 106:    */     
/* 107:124 */     float angle = -1.570796F;
/* 108:125 */     float angleSpan = 3.141593F / zDivNum;
/* 109:126 */     angle += angleSpan;
/* 110:    */     
/* 111:    */ 
/* 112:129 */     float widthOld = 0.0F;
/* 113:133 */     for (int j = 0; j < zDivNum; j++)
/* 114:    */     {
/* 115:136 */       zPos = Math.sin(angle) * maxWidth;
/* 116:137 */       width = (float)Math.cos(angle) * maxWidth;
/* 117:    */       
/* 118:    */ 
/* 119:140 */       xPos = width;
/* 120:141 */       yPos = 0.0F;
/* 121:142 */       angleZ = 0.0D;
/* 122:143 */       xPosOld = (float)Math.cos(angleZ) * width;
/* 123:144 */       yPosOld = (float)Math.sin(angleZ) * width;
/* 124:145 */       xPos2Old = (float)Math.cos(angleZ) * widthOld;
/* 125:146 */       yPos2Old = (float)Math.sin(angleZ) * widthOld;
/* 126:    */       
/* 127:148 */       angleZ = angleSpanZ;
/* 128:150 */       for (int i = 1; i <= zAngleDivNum; i++)
/* 129:    */       {
/* 130:152 */         xPos = (float)Math.cos(angleZ) * width;
/* 131:153 */         yPos = (float)Math.sin(angleZ) * width;
/* 132:154 */         xPos2 = (float)Math.cos(angleZ) * widthOld;
/* 133:155 */         yPos2 = (float)Math.sin(angleZ) * widthOld;
/* 134:    */         
/* 135:157 */         double colorVar = 0.0D;
/* 136:158 */         if (time != 0) {
/* 137:160 */           colorVar = (time + j) / 10.0D;
/* 138:    */         }
/* 139:162 */         tessellator.startDrawingQuads();
/* 140:163 */         tessellator.setColorRGBA_F(this.colorR[color] + (float)Math.sin(colorVar) * 0.2F, this.colorG[color] + (float)Math.cos(colorVar) * 0.2F, this.colorB[color] - (float)Math.sin(colorVar) * 0.2F, alpha);
/* 141:164 */         tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 142:165 */         tessellator.addVertexWithUV(xPos, yPos, zPos, 1.0D, 0.0D);
/* 143:166 */         tessellator.addVertexWithUV(xPosOld, yPosOld, zPos, 0.0D, 0.0D);
/* 144:167 */         tessellator.addVertexWithUV(xPos2Old, yPos2Old, zPosOld, 0.0D, 1.0D);
/* 145:168 */         tessellator.addVertexWithUV(xPos2, yPos2, zPosOld, 1.0D, 1.0D);
/* 146:    */         
/* 147:170 */         tessellator.draw();
/* 148:    */         
/* 149:172 */         xPosOld = xPos;
/* 150:173 */         yPosOld = yPos;
/* 151:174 */         xPos2Old = xPos2;
/* 152:175 */         yPos2Old = yPos2;
/* 153:176 */         angleZ += angleSpanZ;
/* 154:    */       }
/* 155:179 */       zPosOld = zPos;
/* 156:180 */       angle += angleSpan;
/* 157:181 */       widthOld = width;
/* 158:    */     }
/* 159:    */   }
/* 160:    */   
/* 161:    */   public void renderLightEffect(Tessellator tessellator, int color, float size, EntityTHShot thShot)
/* 162:    */   {
/* 163:189 */     color %= 8;
/* 164:190 */     int count = thShot.getAnimationCount() % 2;
/* 165:191 */     float f3 = (count * 32 + 0) / 64.0F;
/* 166:192 */     float f4 = (count * 32 + 32) / 64.0F;
/* 167:193 */     float f5 = 0.0F;
/* 168:194 */     float f6 = 1.0F;
/* 169:195 */     float f7 = 1.0F;
/* 170:196 */     float f8 = 0.5F;
/* 171:197 */     float f9 = 0.25F;
/* 172:198 */     GL11.glScalef(size, size, size);
/* 173:199 */     tessellator.startDrawingQuads();
/* 174:200 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 175:201 */     tessellator.setColorRGBA_F(this.colorR[color] / 255.0F, this.colorG[color] / 255.0F, this.colorB[color] / 255.0F, 0.5F);
/* 176:202 */     tessellator.addVertexWithUV(0.0F - f8, 0.0F - f9, 0.0D, f3, f6);
/* 177:203 */     tessellator.addVertexWithUV(f7 - f8, 0.0F - f9, 0.0D, f4, f6);
/* 178:204 */     tessellator.addVertexWithUV(f7 - f8, 1.0F - f9, 0.0D, f4, f5);
/* 179:205 */     tessellator.addVertexWithUV(0.0F - f8, 1.0F - f9, 0.0D, f3, f5);
/* 180:206 */     tessellator.draw();
/* 181:    */   }
/* 182:    */   
/* 183:    */   protected ResourceLocation getEntityTexture(EntityNuclearShot nuclearShot)
/* 184:    */   {
/* 185:211 */     if (nuclearShot.getAnimationCount() < 0) {
/* 186:213 */       return lightTexture;
/* 187:    */     }
/* 188:215 */     return nuclearShotTexture;
/* 189:    */   }
/* 190:    */   
/* 191:    */   protected ResourceLocation getEntityTexture(Entity entity)
/* 192:    */   {
/* 193:220 */     return getEntityTexture((EntityNuclearShot)entity);
/* 194:    */   }
/* 195:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.shot.RenderNuclearShot
 * JD-Core Version:    0.7.0.1
 */