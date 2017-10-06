/*   1:    */ package thKaguyaMod.client.render.shot;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.relauncher.Side;
/*   4:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   5:    */ import net.minecraft.client.renderer.Tessellator;
/*   6:    */ import net.minecraft.client.renderer.entity.Render;
/*   7:    */ import net.minecraft.entity.Entity;
/*   8:    */ import net.minecraft.util.ResourceLocation;
/*   9:    */ import org.lwjgl.opengl.GL11;
/*  10:    */ import thKaguyaMod.client.THKaguyaRenderLib;
/*  11:    */ import thKaguyaMod.entity.shot.EntityOnmyoudama;
/*  12:    */ import thKaguyaMod.entity.shot.EntityTHShot;
/*  13:    */ 
/*  14:    */ @SideOnly(Side.CLIENT)
/*  15:    */ public class RenderOnmyoudama
/*  16:    */   extends Render
/*  17:    */ {
/*  18: 23 */   private static final ResourceLocation onmyoudamaTexture = new ResourceLocation("thkaguyamod", "textures/shot/Onmyoudama2.png");
/*  19: 24 */   private static final ResourceLocation lightTexture = new ResourceLocation("thkaguyamod", "textures/shot/MusouFuuin.png");
/*  20: 25 */   protected float[] colorR = { 0.8784314F, 0.0F, 0.0F, 0.8784314F, 0.8784314F, 0.0F, 1.0F, 1.0F };
/*  21: 26 */   protected float[] colorG = { 0.0F, 0.0F, 0.8784314F, 0.2509804F, 0.0F, 0.8784314F, 0.5019608F, 1.0F };
/*  22: 27 */   protected float[] colorB = { 0.0F, 0.8784314F, 0.0F, 0.0F, 0.8784314F, 0.8784314F, 0.0F, 1.0F };
/*  23:    */   
/*  24:    */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/*  25:    */   {
/*  26: 36 */     renderOnmyoudama((EntityOnmyoudama)entity, x, y, z, yaw, pitch);
/*  27:    */   }
/*  28:    */   
/*  29:    */   public void renderOnmyoudama(EntityOnmyoudama onmyoudama, double x, double y, double z, float yaw, float pitch)
/*  30:    */   {
/*  31: 41 */     GL11.glPushMatrix();
/*  32: 42 */     bindEntityTexture(onmyoudama);
/*  33: 43 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/*  34: 44 */     GL11.glDisable(2896);
/*  35: 45 */     GL11.glEnable(3042);
/*  36: 46 */     GL11.glBlendFunc(1, 769);
/*  37: 47 */     GL11.glDepthMask(false);
/*  38: 48 */     GL11.glScalef(1.0F, 1.0F, 1.0F);
/*  39: 49 */     Tessellator tessellator = Tessellator.instance;
/*  40:    */     
/*  41: 51 */     int color = onmyoudama.getShotColor();
/*  42: 52 */     int time = onmyoudama.ticksExisted;
/*  43:    */     
/*  44:    */ 
/*  45: 55 */     THKaguyaRenderLib.getGLRotatefByTherdPersonView(this.renderManager);
/*  46: 56 */     GL11.glRotatef(time * 5.0F, 0.0F, 0.0F, 1.0F);
/*  47:    */     
/*  48:    */ 
/*  49:    */ 
/*  50: 60 */     GL11.glScalef(onmyoudama.getShotSize(), onmyoudama.getShotSize(), onmyoudama.getShotSize());
/*  51:    */     
/*  52: 62 */     float f3 = 0.0F;
/*  53: 63 */     float f4 = 0.5F;
/*  54: 64 */     float f5 = 0.0F;
/*  55: 65 */     float f6 = 1.0F;
/*  56: 66 */     float f7 = 1.0F;
/*  57: 67 */     float f8 = 0.5F;
/*  58: 68 */     float f9 = 0.5F;
/*  59: 69 */     tessellator.startDrawingQuads();
/*  60: 70 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  61: 71 */     tessellator.addVertexWithUV(0.0F - f8, 0.0F - f9, 0.01D, f3, f6);
/*  62: 72 */     tessellator.addVertexWithUV(f7 - f8, 0.0F - f9, 0.01D, f4, f6);
/*  63: 73 */     tessellator.addVertexWithUV(f7 - f8, 1.0F - f9, 0.01D, f4, f5);
/*  64: 74 */     tessellator.addVertexWithUV(0.0F - f8, 1.0F - f9, 0.01D, f3, f5);
/*  65: 75 */     tessellator.draw();
/*  66:    */     
/*  67: 77 */     renderLightEffect(tessellator, 0, onmyoudama.getShotSize() + 0.2F, onmyoudama);
/*  68:    */     
/*  69: 79 */     GL11.glDepthMask(true);
/*  70: 80 */     GL11.glDisable(3042);
/*  71: 81 */     GL11.glEnable(2896);
/*  72: 82 */     GL11.glPopMatrix();
/*  73:    */   }
/*  74:    */   
/*  75:    */   protected void renderLaser(Tessellator tessellator, double length, float width, double zPos, int color, float alpha, int time)
/*  76:    */   {
/*  77: 89 */     float maxWidth = width / 2.0F;
/*  78:    */     
/*  79: 91 */     int zAngleDivNum = 72;
/*  80: 92 */     float zSpan = 360.0F / zAngleDivNum;
/*  81: 93 */     double angleZ = 0.0D;
/*  82: 94 */     double angleSpanZ = 6.283185307179586D / zAngleDivNum;
/*  83:    */     
/*  84: 96 */     int zDivNum = 36;
/*  85: 97 */     double zLength = width;
/*  86: 98 */     double zDivLength = zLength / (zDivNum - 1);
/*  87: 99 */     double zLength2 = zLength / 2.0D;
/*  88:    */     
/*  89:    */ 
/*  90:102 */     zPos = Math.sin(-1.570796326794897D) * maxWidth;
/*  91:103 */     double zPosOld = zPos;
/*  92:    */     
/*  93:105 */     float xPos = 0.0F;
/*  94:106 */     float yPos = 0.0F;
/*  95:107 */     float xPos2 = 0.0F;
/*  96:108 */     float yPos2 = 0.0F;
/*  97:    */     
/*  98:110 */     float xPosOld = xPos;
/*  99:111 */     float yPosOld = yPos;
/* 100:112 */     float xPos2Old = xPos2;
/* 101:113 */     float yPos2Old = yPos2;
/* 102:    */     
/* 103:115 */     float angle = -1.570796F;
/* 104:116 */     float angleSpan = 3.141593F / zDivNum;
/* 105:117 */     angle += angleSpan;
/* 106:    */     
/* 107:    */ 
/* 108:120 */     float widthOld = 0.0F;
/* 109:    */     
/* 110:122 */     float vPos = 0.0F;
/* 111:123 */     float vPosSpan = 1.0F / zDivNum;
/* 112:124 */     float vPosNext = vPosSpan;
/* 113:128 */     for (int j = 0; j < zDivNum; j++)
/* 114:    */     {
/* 115:130 */       zPos = Math.sin(angle) * maxWidth;
/* 116:131 */       width = (float)Math.cos(angle) * maxWidth;
/* 117:    */       
/* 118:    */ 
/* 119:134 */       xPos = width;
/* 120:135 */       yPos = 0.0F;
/* 121:136 */       angleZ = 0.0D;
/* 122:137 */       xPosOld = (float)Math.cos(angleZ) * width;
/* 123:138 */       yPosOld = (float)Math.sin(angleZ) * width;
/* 124:139 */       xPos2Old = (float)Math.cos(angleZ) * widthOld;
/* 125:140 */       yPos2Old = (float)Math.sin(angleZ) * widthOld;
/* 126:    */       
/* 127:142 */       angleZ = angleSpanZ;
/* 128:    */       
/* 129:144 */       float uPos = 0.0F;
/* 130:145 */       float uPosSpan = 1.0F / zAngleDivNum;
/* 131:146 */       float uPosNext = uPosSpan;
/* 132:148 */       for (int i = 1; i <= zAngleDivNum; i++)
/* 133:    */       {
/* 134:150 */         xPos = (float)Math.cos(angleZ) * width;
/* 135:151 */         yPos = (float)Math.sin(angleZ) * width;
/* 136:152 */         xPos2 = (float)Math.cos(angleZ) * widthOld;
/* 137:153 */         yPos2 = (float)Math.sin(angleZ) * widthOld;
/* 138:    */         
/* 139:155 */         double colorVar = 0.0D;
/* 140:156 */         if (time != 0) {
/* 141:158 */           colorVar = (time + j) / 10.0D;
/* 142:    */         }
/* 143:160 */         tessellator.startDrawingQuads();
/* 144:161 */         tessellator.setColorRGBA_F(this.colorR[color] + (float)Math.sin(colorVar) * 0.2F, this.colorG[color] + (float)Math.cos(colorVar) * 0.2F, this.colorB[color] - (float)Math.sin(colorVar) * 0.2F, alpha);
/* 145:162 */         tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 146:163 */         tessellator.addVertexWithUV(xPos, yPos, zPos, uPosNext, vPos);
/* 147:164 */         tessellator.addVertexWithUV(xPosOld, yPosOld, zPos, uPos, vPos);
/* 148:165 */         tessellator.addVertexWithUV(xPos2Old, yPos2Old, zPosOld, uPos, vPosNext);
/* 149:166 */         tessellator.addVertexWithUV(xPos2, yPos2, zPosOld, uPosNext, vPosNext);
/* 150:    */         
/* 151:168 */         tessellator.draw();
/* 152:    */         
/* 153:170 */         xPosOld = xPos;
/* 154:171 */         yPosOld = yPos;
/* 155:172 */         xPos2Old = xPos2;
/* 156:173 */         yPos2Old = yPos2;
/* 157:174 */         angleZ += angleSpanZ;
/* 158:175 */         uPos = uPosNext;
/* 159:176 */         uPosNext += uPosSpan;
/* 160:    */       }
/* 161:179 */       zPosOld = zPos;
/* 162:180 */       angle += angleSpan;
/* 163:181 */       widthOld = width;
/* 164:182 */       vPos = vPosNext;
/* 165:183 */       vPosNext += vPosSpan;
/* 166:    */     }
/* 167:    */   }
/* 168:    */   
/* 169:    */   public void renderLightEffect(Tessellator tessellator, int color, float size, EntityTHShot entityTHShot)
/* 170:    */   {
/* 171:191 */     color %= 8;
/* 172:192 */     int count = entityTHShot.getAnimationCount() % 2;
/* 173:193 */     float f3 = (64 + count * 32 + 0) / 128.0F;
/* 174:194 */     float f4 = (64 + count * 32 + 32) / 128.0F;
/* 175:195 */     float f5 = 0.0F;
/* 176:196 */     float f6 = 0.5F;
/* 177:197 */     float f7 = 1.4F;
/* 178:198 */     float f8 = 0.7F;
/* 179:199 */     float f9 = 0.7F;
/* 180:200 */     tessellator.startDrawingQuads();
/* 181:201 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 182:202 */     tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], 0.5F);
/* 183:203 */     tessellator.addVertexWithUV(0.0F - f8, 0.0F - f9, 0.0D, f3, f6);
/* 184:204 */     tessellator.addVertexWithUV(f7 - f8, 0.0F - f9, 0.0D, f4, f6);
/* 185:205 */     tessellator.addVertexWithUV(f7 - f8, f7 - f9, 0.0D, f4, f5);
/* 186:206 */     tessellator.addVertexWithUV(0.0F - f8, f7 - f9, 0.0D, f3, f5);
/* 187:207 */     tessellator.draw();
/* 188:    */   }
/* 189:    */   
/* 190:    */   protected ResourceLocation getEntityTexture(EntityOnmyoudama onmyoudama)
/* 191:    */   {
/* 192:212 */     if (onmyoudama.getAnimationCount() < 0) {
/* 193:214 */       return lightTexture;
/* 194:    */     }
/* 195:216 */     return onmyoudamaTexture;
/* 196:    */   }
/* 197:    */   
/* 198:    */   protected ResourceLocation getEntityTexture(Entity entity)
/* 199:    */   {
/* 200:221 */     return getEntityTexture((EntityOnmyoudama)entity);
/* 201:    */   }
/* 202:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.shot.RenderOnmyoudama
 * JD-Core Version:    0.7.0.1
 */