/*   1:    */ package thKaguyaMod.client.render.shot;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.relauncher.Side;
/*   4:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   5:    */ import java.util.Random;
/*   6:    */ import net.minecraft.client.renderer.Tessellator;
/*   7:    */ import net.minecraft.client.renderer.entity.Render;
/*   8:    */ import net.minecraft.client.renderer.entity.RenderManager;
/*   9:    */ import net.minecraft.entity.Entity;
/*  10:    */ import net.minecraft.util.MathHelper;
/*  11:    */ import net.minecraft.util.ResourceLocation;
/*  12:    */ import org.lwjgl.opengl.GL11;
/*  13:    */ import thKaguyaMod.entity.shot.EntityTHLaser;
/*  14:    */ import thKaguyaMod.entity.shot.EntityTHSetLaser;
/*  15:    */ import thKaguyaMod.entity.shot.EntityTHShot;
/*  16:    */ 
/*  17:    */ @SideOnly(Side.CLIENT)
/*  18:    */ public class RenderTHLaser
/*  19:    */   extends Render
/*  20:    */ {
/*  21: 26 */   private static final ResourceLocation laserTexture = new ResourceLocation("thkaguyamod", "textures/shot/Laser.png");
/*  22: 27 */   private static final ResourceLocation lightTexture = new ResourceLocation("thkaguyamod", "textures/shot/MusouFuuin.png");
/*  23: 28 */   private Random random = new Random();
/*  24: 29 */   protected float[] colorR = { 0.8784314F, 0.0F, 0.0F, 0.8784314F, 0.8784314F, 0.0F, 1.0F, 1.0F };
/*  25: 30 */   protected float[] colorG = { 0.0F, 0.0F, 0.8784314F, 0.8784314F, 0.0F, 0.8784314F, 0.5019608F, 1.0F };
/*  26: 31 */   protected float[] colorB = { 0.0F, 0.8784314F, 0.0F, 0.0F, 0.8784314F, 0.8784314F, 0.0F, 1.0F };
/*  27:    */   
/*  28:    */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/*  29:    */   {
/*  30: 40 */     renderTHLaser((EntityTHLaser)entity, x, y, z, yaw, pitch);
/*  31:    */   }
/*  32:    */   
/*  33:    */   public void renderTHLaser(EntityTHLaser thLaser, double x, double y, double z, float yaw, float pitch)
/*  34:    */   {
/*  35: 45 */     GL11.glPushMatrix();
/*  36: 46 */     bindEntityTexture(thLaser);
/*  37: 47 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/*  38: 48 */     GL11.glDisable(2896);
/*  39:    */     
/*  40: 50 */     GL11.glEnable(3042);
/*  41: 51 */     GL11.glBlendFunc(1, 769);
/*  42: 52 */     GL11.glBlendFunc(770, 1);
/*  43:    */     
/*  44: 54 */     Tessellator tessellator = Tessellator.instance;
/*  45: 55 */     getEntityTexture(thLaser);
/*  46:    */     
/*  47: 57 */     int color = thLaser.getShotId();
/*  48:    */     
/*  49: 59 */     float width = 1.0F;
/*  50: 60 */     if ((thLaser.getAnimationCount() < 0) && ((thLaser instanceof EntityTHSetLaser))) {
/*  51: 62 */       width = 0.1F;
/*  52:    */     }
/*  53: 65 */     GL11.glScalef(1.0F, 1.0F, 1.0F);
/*  54: 67 */     if ((thLaser.getAnimationCount() < 0) && (!(thLaser instanceof EntityTHSetLaser)))
/*  55:    */     {
/*  56: 69 */       int delayCount = -thLaser.getAnimationCount();
/*  57: 70 */       if (delayCount > 10) {
/*  58: 72 */         delayCount = 10;
/*  59:    */       }
/*  60: 74 */       float size2 = delayCount * thLaser.getShotSize() * 2.0F;
/*  61: 75 */       if (size2 > 1.0F) {
/*  62: 77 */         size2 = 1.0F;
/*  63:    */       }
/*  64: 79 */       GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/*  65: 80 */       GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
/*  66: 81 */       GL11.glDepthMask(false);
/*  67: 82 */       renderLightEffect(tessellator, color % 8, size2, thLaser);
/*  68: 83 */       GL11.glDepthMask(true);
/*  69:    */     }
/*  70:    */     else
/*  71:    */     {
/*  72: 87 */       GL11.glRotatef(yaw, 0.0F, 1.0F, 0.0F);
/*  73: 88 */       GL11.glRotatef(-thLaser.rotationPitch, 1.0F, 0.0F, 0.0F);
/*  74: 89 */       double centerZ1 = thLaser.getLaserLength() * 1.2D / 2.0D;
/*  75: 90 */       double centerZ2 = thLaser.getLaserLength() / 2.0D;
/*  76: 92 */       if (color >= 24)
/*  77:    */       {
/*  78: 94 */         GL11.glScalef(width, width, 1.0F);
/*  79: 95 */         float ticks = thLaser.getAnimationCount() * 10.0F;
/*  80: 96 */         ticks = Math.abs(ticks);
/*  81: 97 */         GL11.glDisable(2884);
/*  82: 98 */         GL11.glDepthFunc(519);
/*  83: 99 */         renderMasterSparkLaser(tessellator, thLaser.getLaserLength(), thLaser.getShotSize(), 1.0F, false, ticks);
/*  84:    */         
/*  85:101 */         GL11.glEnable(3042);
/*  86:102 */         GL11.glBlendFunc(1, 769);
/*  87:103 */         renderMasterSparkLaser(tessellator, thLaser.getLaserLength(), thLaser.getShotSize() * 1.2F, 0.6F, true, ticks);
/*  88:104 */         GL11.glDisable(3042);
/*  89:    */         
/*  90:106 */         GL11.glDepthFunc(515);
/*  91:107 */         GL11.glEnable(2884);
/*  92:    */       }
/*  93:109 */       else if (color >= 16)
/*  94:    */       {
/*  95:111 */         GL11.glRotatef(thLaser.getAngleZ(), 0.0F, 0.0F, 1.0F);
/*  96:112 */         GL11.glDisable(2884);
/*  97:113 */         GL11.glScalef(1.0F, 1.0F, 1.0F * (float)thLaser.getLaserLength() / 20.799999F);
/*  98:114 */         renderLaevateinn(tessellator, thLaser.getLaserLength(), thLaser.getShotSize(), 1.6D, 7, 1.0F, thLaser.ticksExisted);
/*  99:    */         
/* 100:116 */         GL11.glDepthMask(false);
/* 101:117 */         GL11.glEnable(3042);
/* 102:118 */         GL11.glBlendFunc(1, 769);
/* 103:119 */         GL11.glScalef(1.2F, 1.2F, 1.2F);
/* 104:120 */         renderLaevateinn(tessellator, thLaser.getLaserLength() * 1.2D, thLaser.getShotSize() * 1.2F, 1.0D, color, 0.6F, thLaser.ticksExisted);
/* 105:121 */         GL11.glDisable(3042);
/* 106:122 */         GL11.glEnable(2884);
/* 107:123 */         GL11.glDepthMask(true);
/* 108:    */       }
/* 109:125 */       else if (color >= 8)
/* 110:    */       {
/* 111:127 */         GL11.glRotatef(thLaser.ticksExisted * 30.0F, 0.0F, 0.0F, 1.0F);
/* 112:128 */         GL11.glDisable(2884);
/* 113:129 */         GL11.glScalef(0.3F, 0.3F, 0.5F * (float)thLaser.getLaserLength() / 10.0F);
/* 114:130 */         renderSpearTheGungnir(tessellator, thLaser.getLaserLength(), thLaser.getShotSize(), centerZ1 - centerZ2, 7, 1.0F);
/* 115:    */         
/* 116:132 */         GL11.glDepthMask(false);
/* 117:133 */         GL11.glEnable(3042);
/* 118:134 */         GL11.glBlendFunc(1, 769);
/* 119:135 */         GL11.glScalef(1.2F, 1.2F, 1.2F);
/* 120:136 */         renderSpearTheGungnir(tessellator, thLaser.getLaserLength() * 1.2D, thLaser.getShotSize() * 1.2F, -1.0D, color, 0.6F);
/* 121:137 */         GL11.glDisable(3042);
/* 122:138 */         GL11.glEnable(2884);
/* 123:139 */         GL11.glDepthMask(true);
/* 124:    */       }
/* 125:142 */       else if (thLaser.isMotherAndChild() != 0)
/* 126:    */       {
/* 127:146 */         double toMotherLength = thLaser.getDistance(thLaser.getMotherPosX(), thLaser.getMotherPosY(), thLaser.getMotherPosZ());
/* 128:147 */         GL11.glEnable(2884);
/* 129:    */         
/* 130:149 */         renderLaser(tessellator, toMotherLength, thLaser.getShotSize(), 0.0D, color, 1.0F, 0.0D);
/* 131:    */       }
/* 132:    */       else
/* 133:    */       {
/* 134:154 */         GL11.glScalef(width, width, 1.0F);
/* 135:    */         
/* 136:    */ 
/* 137:157 */         GL11.glEnable(2884);
/* 138:    */         
/* 139:159 */         renderLaser(tessellator, thLaser.getLaserLength(), thLaser.getShotSize(), centerZ1 - centerZ2, 7, 1.0F);
/* 140:    */         
/* 141:161 */         GL11.glDepthMask(false);
/* 142:162 */         GL11.glEnable(3042);
/* 143:163 */         GL11.glBlendFunc(1, 769);
/* 144:164 */         renderLaser(tessellator, thLaser.getLaserLength() * 1.2D, thLaser.getShotSize() * 1.2F, 0.0D, color, 0.6F);
/* 145:165 */         GL11.glDisable(3042);
/* 146:166 */         GL11.glDepthMask(true);
/* 147:    */       }
/* 148:    */     }
/* 149:171 */     GL11.glDisable(3042);
/* 150:172 */     GL11.glEnable(2896);
/* 151:    */     
/* 152:174 */     GL11.glPopMatrix();
/* 153:    */   }
/* 154:    */   
/* 155:    */   protected void renderLaser(Tessellator tessellator, double length, float width, double zPos, int color, float alpha, double d)
/* 156:    */   {
/* 157:180 */     float maxWidth = width;
/* 158:    */     
/* 159:182 */     int zAngleDivNum = 8;
/* 160:183 */     float zSpan = 360.0F / zAngleDivNum;
/* 161:184 */     double angleZ = 0.0D;
/* 162:185 */     double angleSpanZ = 6.283185307179586D / zAngleDivNum;
/* 163:    */     
/* 164:187 */     int zDivNum = 13;
/* 165:188 */     double zLength = length;
/* 166:189 */     double zDivLength = zLength / (zDivNum - 1);
/* 167:190 */     double zLength2 = zLength / 2.0D;
/* 168:191 */     double zPosOld = zPos;
/* 169:    */     
/* 170:193 */     float xPos = 0.0F;
/* 171:194 */     float yPos = 0.0F;
/* 172:195 */     float xPos2 = 0.0F;
/* 173:196 */     float yPos2 = 0.0F;
/* 174:    */     
/* 175:198 */     float xPosOld = xPos;
/* 176:199 */     float yPosOld = yPos;
/* 177:200 */     float xPos2Old = xPos2;
/* 178:201 */     float yPos2Old = yPos2;
/* 179:    */     
/* 180:203 */     float angle = -1.570796F;
/* 181:204 */     float angleSpan = 3.141593F / zDivNum;
/* 182:205 */     angle += angleSpan;
/* 183:    */     
/* 184:    */ 
/* 185:208 */     float widthOld = 0.0F;
/* 186:212 */     for (int j = 0; j < zDivNum; j++)
/* 187:    */     {
/* 188:214 */       zPos += zDivLength;
/* 189:    */       
/* 190:216 */       xPos = width;
/* 191:217 */       yPos = 0.0F;
/* 192:218 */       xPosOld = (float)Math.cos(angleZ) * width;
/* 193:219 */       yPosOld = (float)Math.sin(angleZ) * width;
/* 194:220 */       xPos2Old = (float)Math.cos(angleZ) * widthOld;
/* 195:221 */       yPos2Old = (float)Math.sin(angleZ) * widthOld;
/* 196:    */       
/* 197:223 */       angleZ = angleSpanZ;
/* 198:225 */       for (int i = 0; i <= zAngleDivNum; i++)
/* 199:    */       {
/* 200:227 */         xPos = (float)Math.cos(angleZ) * width;
/* 201:228 */         yPos = (float)Math.sin(angleZ) * width;
/* 202:229 */         xPos2 = (float)Math.cos(angleZ) * widthOld;
/* 203:230 */         yPos2 = (float)Math.sin(angleZ) * widthOld;
/* 204:    */         
/* 205:232 */         tessellator.startDrawingQuads();
/* 206:233 */         tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], alpha);
/* 207:234 */         tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 208:235 */         tessellator.addVertexWithUV(xPosOld, yPosOld, zPos, 0.0D, 0.0D);
/* 209:236 */         tessellator.addVertexWithUV(xPos2Old, yPos2Old, zPosOld, 0.0D, 1.0D);
/* 210:237 */         tessellator.addVertexWithUV(xPos2, yPos2, zPosOld, 1.0D, 1.0D);
/* 211:238 */         tessellator.addVertexWithUV(xPos, yPos, zPos, 1.0D, 0.0D);
/* 212:    */         
/* 213:    */ 
/* 214:241 */         tessellator.draw();
/* 215:    */         
/* 216:243 */         xPosOld = xPos;
/* 217:244 */         yPosOld = yPos;
/* 218:245 */         xPos2Old = xPos2;
/* 219:246 */         yPos2Old = yPos2;
/* 220:247 */         angleZ += angleSpanZ;
/* 221:    */       }
/* 222:251 */       zPosOld = zPos;
/* 223:252 */       angle += angleSpan;
/* 224:    */     }
/* 225:    */   }
/* 226:    */   
/* 227:    */   protected void renderLaser(Tessellator tessellator, double length, float width, double zPos, int color, float alpha)
/* 228:    */   {
/* 229:261 */     float maxWidth = width;
/* 230:    */     
/* 231:263 */     int zAngleDivNum = 8;
/* 232:264 */     float zSpan = 360.0F / zAngleDivNum;
/* 233:265 */     double angleZ = 0.0D;
/* 234:266 */     double angleSpanZ = 6.283185307179586D / zAngleDivNum;
/* 235:    */     
/* 236:268 */     int zDivNum = 13;
/* 237:269 */     double zLength = length;
/* 238:270 */     double zDivLength = zLength / (zDivNum - 1);
/* 239:271 */     double zLength2 = zLength / 2.0D;
/* 240:272 */     double zPosOld = zPos;
/* 241:    */     
/* 242:274 */     float xPos = 0.0F;
/* 243:275 */     float yPos = 0.0F;
/* 244:276 */     float xPos2 = 0.0F;
/* 245:277 */     float yPos2 = 0.0F;
/* 246:    */     
/* 247:279 */     float xPosOld = xPos;
/* 248:280 */     float yPosOld = yPos;
/* 249:281 */     float xPos2Old = xPos2;
/* 250:282 */     float yPos2Old = yPos2;
/* 251:    */     
/* 252:284 */     float angle = -1.570796F;
/* 253:285 */     float angleSpan = 3.141593F / zDivNum;
/* 254:286 */     angle += angleSpan;
/* 255:    */     
/* 256:    */ 
/* 257:289 */     float widthOld = 0.0F;
/* 258:293 */     for (int j = 0; j < zDivNum; j++)
/* 259:    */     {
/* 260:295 */       zPos += zDivLength;
/* 261:    */       
/* 262:    */ 
/* 263:298 */       width = (float)Math.cos(angle) * maxWidth;
/* 264:    */       
/* 265:300 */       xPos = width;
/* 266:301 */       yPos = 0.0F;
/* 267:302 */       xPosOld = (float)Math.cos(angleZ) * width;
/* 268:303 */       yPosOld = (float)Math.sin(angleZ) * width;
/* 269:304 */       xPos2Old = (float)Math.cos(angleZ) * widthOld;
/* 270:305 */       yPos2Old = (float)Math.sin(angleZ) * widthOld;
/* 271:    */       
/* 272:307 */       angleZ = angleSpanZ;
/* 273:309 */       for (int i = 0; i <= zAngleDivNum; i++)
/* 274:    */       {
/* 275:311 */         xPos = (float)Math.cos(angleZ) * width;
/* 276:312 */         yPos = (float)Math.sin(angleZ) * width;
/* 277:313 */         xPos2 = (float)Math.cos(angleZ) * widthOld;
/* 278:314 */         yPos2 = (float)Math.sin(angleZ) * widthOld;
/* 279:    */         
/* 280:316 */         tessellator.startDrawingQuads();
/* 281:317 */         tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], alpha);
/* 282:318 */         tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 283:319 */         tessellator.addVertexWithUV(xPosOld, yPosOld, zPos, 0.0D, 0.0D);
/* 284:320 */         tessellator.addVertexWithUV(xPos2Old, yPos2Old, zPosOld, 0.0D, 1.0D);
/* 285:321 */         tessellator.addVertexWithUV(xPos2, yPos2, zPosOld, 1.0D, 1.0D);
/* 286:322 */         tessellator.addVertexWithUV(xPos, yPos, zPos, 1.0D, 0.0D);
/* 287:    */         
/* 288:    */ 
/* 289:325 */         tessellator.draw();
/* 290:    */         
/* 291:327 */         xPosOld = xPos;
/* 292:328 */         yPosOld = yPos;
/* 293:329 */         xPos2Old = xPos2;
/* 294:330 */         yPos2Old = yPos2;
/* 295:331 */         angleZ += angleSpanZ;
/* 296:    */       }
/* 297:335 */       zPosOld = zPos;
/* 298:336 */       angle += angleSpan;
/* 299:337 */       widthOld = width;
/* 300:    */     }
/* 301:    */   }
/* 302:    */   
/* 303:    */   protected void renderSpearTheGungnir(Tessellator tessellator, double length, float width, double zPos, int color, float alpha)
/* 304:    */   {
/* 305:345 */     color %= 8;
/* 306:346 */     double[][] vertex = { { 0.0D, 0.681531D, -5.956767D }, { 0.0D, 0.999996D, 6.0D }, { -0.681527D, 4.0E-006D, -5.956767D }, { -1.0D, -4.0E-006D, 6.0D }, { -0.481912D, 0.481916D, -5.956767D }, { -0.707107D, 0.707103D, 6.0D }, { -0.0D, 1.957804D, 8.160793D }, { -1.733818D, -5.E-006D, 4.933474D }, { -1.461115D, 0.707101D, 6.843752D }, { -2.247491D, -0.0D, 6.180964D }, { -3.164763D, -0.0D, 6.988164D }, { -4.155417D, -0.0D, 6.107583D }, { -5.292834D, -0.0D, 3.575912D }, { -5.476289D, -0.0D, 7.743996D }, { -4.321013D, -0.0D, 11.094296D }, { -0.787193D, -0.0D, 13.423745D }, { 0.00533D, -0.0D, 15.669228D }, { -3.180439D, 1.438709D, 7.695314D }, { -4.787621D, 0.707101D, 6.067568D }, { -4.588031D, 0.707101D, 7.597761D }, { 0.0D, 1.996092D, 10.168588D }, { -0.0D, 1.3702D, 12.822122D }, { -3.372246D, 1.295732D, 10.534312D }, { -1.406365D, 1.812266D, 10.156219999999999D }, { -1.43374D, 1.775428D, 8.389469999999999D }, { -0.0D, 6.E-006D, -9.680047D }, { -0.0D, 1.076022D, 0.0D }, { -1.076022D, 0.0D, 0.0D }, { -0.760863D, 0.760862D, 0.0D } };
/* 307:    */     
/* 308:    */ 
/* 309:    */ 
/* 310:    */ 
/* 311:    */ 
/* 312:    */ 
/* 313:    */ 
/* 314:    */ 
/* 315:    */ 
/* 316:    */ 
/* 317:    */ 
/* 318:    */ 
/* 319:    */ 
/* 320:    */ 
/* 321:    */ 
/* 322:    */ 
/* 323:    */ 
/* 324:    */ 
/* 325:    */ 
/* 326:    */ 
/* 327:    */ 
/* 328:    */ 
/* 329:    */ 
/* 330:    */ 
/* 331:    */ 
/* 332:    */ 
/* 333:    */ 
/* 334:    */ 
/* 335:    */ 
/* 336:    */ 
/* 337:    */ 
/* 338:378 */     int[][] order = { { 29, 6, 2, 27 }, { 28, 4, 6, 29 }, { 23, 15, 14, 20 }, { 13, 19, 12, 12 }, { 14, 20, 19, 13 }, { 8, 9, 4, 4 }, { 9, 8, 10, 10 }, { 9, 10, 11, 18 }, { 18, 11, 12, 19 }, { 20, 18, 23, 23 }, { 19, 20, 18, 18 }, { 17, 22, 16, 16 }, { 22, 16, 15, 23 }, { 4, 9, 6, 6 }, { 9, 18, 25, 25 }, { 23, 18, 25, 24 }, { 24, 25, 7, 21 }, { 22, 23, 24, 21 }, { 6, 25, 7, 2 }, { 9, 25, 6, 6 }, { 5, 1, 26, 26 }, { 3, 5, 26, 26 }, { 5, 29, 27, 1 }, { 3, 28, 29, 5 } };
/* 339:405 */     for (int i = 0; i < 24; i++)
/* 340:    */     {
/* 341:407 */       tessellator.startDrawingQuads();
/* 342:408 */       tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], alpha);
/* 343:409 */       tessellator.addVertexWithUV((float)vertex[(order[i][0] - 1)][0], (float)vertex[(order[i][0] - 1)][1], vertex[(order[i][0] - 1)][2] + zPos, 0.0D, 0.0D);
/* 344:410 */       tessellator.addVertexWithUV((float)vertex[(order[i][1] - 1)][0], (float)vertex[(order[i][1] - 1)][1], vertex[(order[i][1] - 1)][2] + zPos, 0.0D, 1.0D);
/* 345:411 */       tessellator.addVertexWithUV((float)vertex[(order[i][2] - 1)][0], (float)vertex[(order[i][2] - 1)][1], vertex[(order[i][2] - 1)][2] + zPos, 1.0D, 1.0D);
/* 346:412 */       tessellator.addVertexWithUV((float)vertex[(order[i][3] - 1)][0], (float)vertex[(order[i][3] - 1)][1], vertex[(order[i][3] - 1)][2] + zPos, 1.0D, 0.0D);
/* 347:413 */       tessellator.draw();
/* 348:    */       
/* 349:415 */       tessellator.startDrawingQuads();
/* 350:416 */       tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], alpha);
/* 351:417 */       tessellator.addVertexWithUV(-(float)vertex[(order[i][0] - 1)][0], (float)vertex[(order[i][0] - 1)][1], vertex[(order[i][0] - 1)][2] + zPos, 0.0D, 0.0D);
/* 352:418 */       tessellator.addVertexWithUV(-(float)vertex[(order[i][1] - 1)][0], (float)vertex[(order[i][1] - 1)][1], vertex[(order[i][1] - 1)][2] + zPos, 0.0D, 1.0D);
/* 353:419 */       tessellator.addVertexWithUV(-(float)vertex[(order[i][2] - 1)][0], (float)vertex[(order[i][2] - 1)][1], vertex[(order[i][2] - 1)][2] + zPos, 1.0D, 1.0D);
/* 354:420 */       tessellator.addVertexWithUV(-(float)vertex[(order[i][3] - 1)][0], (float)vertex[(order[i][3] - 1)][1], vertex[(order[i][3] - 1)][2] + zPos, 1.0D, 0.0D);
/* 355:421 */       tessellator.draw();
/* 356:    */       
/* 357:423 */       tessellator.startDrawingQuads();
/* 358:424 */       tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], alpha);
/* 359:425 */       tessellator.addVertexWithUV((float)vertex[(order[i][0] - 1)][0], -(float)vertex[(order[i][0] - 1)][1], vertex[(order[i][0] - 1)][2] + zPos, 0.0D, 0.0D);
/* 360:426 */       tessellator.addVertexWithUV((float)vertex[(order[i][1] - 1)][0], -(float)vertex[(order[i][1] - 1)][1], vertex[(order[i][1] - 1)][2] + zPos, 0.0D, 1.0D);
/* 361:427 */       tessellator.addVertexWithUV((float)vertex[(order[i][2] - 1)][0], -(float)vertex[(order[i][2] - 1)][1], vertex[(order[i][2] - 1)][2] + zPos, 1.0D, 1.0D);
/* 362:428 */       tessellator.addVertexWithUV((float)vertex[(order[i][3] - 1)][0], -(float)vertex[(order[i][3] - 1)][1], vertex[(order[i][3] - 1)][2] + zPos, 1.0D, 0.0D);
/* 363:429 */       tessellator.draw();
/* 364:    */       
/* 365:431 */       tessellator.startDrawingQuads();
/* 366:432 */       tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], alpha);
/* 367:433 */       tessellator.addVertexWithUV(-(float)vertex[(order[i][0] - 1)][0], -(float)vertex[(order[i][0] - 1)][1], vertex[(order[i][0] - 1)][2] + zPos, 0.0D, 0.0D);
/* 368:434 */       tessellator.addVertexWithUV(-(float)vertex[(order[i][1] - 1)][0], -(float)vertex[(order[i][1] - 1)][1], vertex[(order[i][1] - 1)][2] + zPos, 0.0D, 1.0D);
/* 369:435 */       tessellator.addVertexWithUV(-(float)vertex[(order[i][2] - 1)][0], -(float)vertex[(order[i][2] - 1)][1], vertex[(order[i][2] - 1)][2] + zPos, 1.0D, 1.0D);
/* 370:436 */       tessellator.addVertexWithUV(-(float)vertex[(order[i][3] - 1)][0], -(float)vertex[(order[i][3] - 1)][1], vertex[(order[i][3] - 1)][2] + zPos, 1.0D, 0.0D);
/* 371:437 */       tessellator.draw();
/* 372:    */     }
/* 373:    */   }
/* 374:    */   
/* 375:    */   protected void renderLaevateinn(Tessellator tessellator, double length, float width, double zPos, int color, float alpha, int time)
/* 376:    */   {
/* 377:444 */     color %= 8;
/* 378:    */     
/* 379:    */ 
/* 380:    */ 
/* 381:    */ 
/* 382:    */ 
/* 383:    */ 
/* 384:    */ 
/* 385:    */ 
/* 386:    */ 
/* 387:    */ 
/* 388:    */ 
/* 389:    */ 
/* 390:    */ 
/* 391:    */ 
/* 392:    */ 
/* 393:    */ 
/* 394:    */ 
/* 395:    */ 
/* 396:    */ 
/* 397:    */ 
/* 398:    */ 
/* 399:    */ 
/* 400:    */ 
/* 401:    */ 
/* 402:    */ 
/* 403:    */ 
/* 404:    */ 
/* 405:    */ 
/* 406:    */ 
/* 407:    */ 
/* 408:    */ 
/* 409:    */ 
/* 410:    */ 
/* 411:    */ 
/* 412:    */ 
/* 413:    */ 
/* 414:    */ 
/* 415:    */ 
/* 416:    */ 
/* 417:    */ 
/* 418:    */ 
/* 419:    */ 
/* 420:    */ 
/* 421:    */ 
/* 422:    */ 
/* 423:    */ 
/* 424:    */ 
/* 425:    */ 
/* 426:    */ 
/* 427:    */ 
/* 428:    */ 
/* 429:496 */     double[][] vertex = { { -0.012712D, 0.860909D, 19.683685000000001D }, { 0.010621D, 0.799994D, 3.958289D }, { -0.411288D, 0.576921D, 19.683094000000001D }, { -0.555063D, 0.565679D, 3.95745D }, { -0.576384D, 6.E-006D, 19.682849999999998D }, { -0.789378D, -6.E-006D, 3.957103D }, {0.944878D + Math.sin(time * 60 / 180.0F * 3.141593F) * 0.5D, 6.E-006D, 19.685106000000001D }, { 0.810621D, -6.E-006D, 3.959476D }, {0.385864D + Math.sin(time * 86 / 180.0F * 3.141593F) * 0.5D, 0.576921D, 19.684277000000002D }, { 0.576306D, 0.565679D, 3.959129D }, { 0.0D, 1.09723D, 11.116394D }, { -0.565685D, 0.803469D, 11.115555000000001D }, { -0.978337D, -0.0D, 11.115208000000001D }, { 0.799999D, 0.0D, 11.117582000000001D }, { 0.565685D, 0.922361D, 11.117234D }, { 0.010666D, 0.838774D, 3.928527D }, { -2.699365D, 0.740431D, 3.924505D }, { -3.821897D, -8.0E-006D, 3.922842D }, { 3.84323D, -8.0E-006D, 3.934216D }, { 2.720698D, 0.819214D, 3.932549D }, { 0.011292D, 0.878166D, 3.507193D }, { -2.69874D, 0.819214D, 3.503171D }, { -3.821272D, -9.E-006D, 3.501507D }, { 3.843855D, -9.E-006D, 3.512882D }, { 2.721323D, 0.819214D, 3.511215D }, { 0.011649D, 0.801885D, 3.266243D }, { -0.603876D, 0.559341D, 3.293626D }, { -0.844117D, -0.001264D, 3.318336D }, { 0.867178D, -0.000875D, 3.320815D }, { 0.62709D, 0.559341D, 3.295454D }, { 0.015762D, 0.620065D, 0.49456D }, { -0.422696D, 0.438449D, 0.49391D }, { -0.604311D, -9.E-006D, 0.493641D }, { 0.635834D, -9.E-006D, 0.495482D }, { 0.454219D, 0.438449D, 0.495212D }, { 0.017635D, -9.E-006D, -0.767668D }, { -0.01704D, 0.27739D, 22.599312000000001D }, { -0.21318D, 0.196148D, 22.599022000000001D }, { -0.294422D, 8.0E-006D, 22.5989D }, {0.260342D + Math.sin(time * 50 / 180.0F * 3.141593F) * 0.5D, 8.0E-006D, 22.599723999999998D }, { 0.1791D, 0.196148D, 22.599602000000001D }, { -0.019185D, 8.0E-006D, 24.045269000000001D }, { -0.488487D, 0.482134D, 15.399324D }, { -1.282651D, 3.E-006D, 15.399029000000001D }, {1.620884D + Math.sin(time * 43 / 180.0F * 3.141593F) * 0.5D, 3.E-006D, 15.402454000000001D }, { 0.8696930000000001D, 0.482134D, 15.401339999999999D }, { -0.006356D, 0.681839D, 15.400040000000001D }, { 0.005311D, 0.799997D, 7.537342D }, { -0.560374D, 0.565682D, 7.536503D }, { -1.151364D, -3.E-006D, 7.536155D }, {2.026458D + Math.sin(time * 80 / 180.0F * 3.141593F) * 0.5D, -3.E-006D, 7.540341D }, { 1.161873D, 0.565682D, 7.539058D } };
/* 430:    */     
/* 431:    */ 
/* 432:    */ 
/* 433:500 */     int[][] order = { { 48, 2, 4, 49 }, { 49, 4, 6, 50 }, { 2, 10, 20, 16 }, { 52, 10, 2, 48 }, { 51, 8, 10, 52 }, { 9, 1, 37, 41 }, { 47, 11, 12, 43 }, { 43, 12, 13, 44 }, { 46, 15, 11, 47 }, { 45, 14, 15, 46 }, { 16, 20, 25, 21 }, { 10, 8, 19, 20 }, { 6, 4, 17, 18 }, { 4, 2, 16, 17 }, { 20, 19, 24, 25 }, { 18, 17, 22, 23 }, { 17, 16, 21, 22 }, { 21, 25, 30, 26 }, { 25, 24, 29, 30 }, { 23, 22, 27, 28 }, { 22, 21, 26, 27 }, { 26, 30, 35, 31 }, { 30, 29, 34, 35 }, { 28, 27, 32, 33 }, { 27, 26, 31, 32 }, { 31, 35, 36, 36 }, { 35, 34, 36, 36 }, { 33, 32, 36, 36 }, { 32, 31, 36, 36 }, { 7, 9, 41, 40 }, { 3, 5, 39, 38 }, { 1, 3, 38, 37 }, { 41, 37, 42, 42 }, { 40, 41, 42, 42 }, { 38, 39, 42, 42 }, { 37, 38, 42, 42 }, { 1, 47, 43, 3 }, { 3, 43, 44, 5 }, { 9, 46, 47, 1 }, { 7, 45, 46, 9 }, { 11, 48, 49, 12 }, { 12, 49, 50, 13 }, { 15, 52, 48, 11 }, { 14, 51, 52, 15 } };
/* 434:547 */     for (int i = 0; i < order.length; i++)
/* 435:    */     {
/* 436:549 */       tessellator.startDrawingQuads();
/* 437:550 */       tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], alpha);
/* 438:551 */       tessellator.addVertexWithUV((float)vertex[(order[i][0] - 1)][0], (float)vertex[(order[i][0] - 1)][1], vertex[(order[i][0] - 1)][2] + zPos, 0.0D, 0.0D);
/* 439:552 */       tessellator.addVertexWithUV((float)vertex[(order[i][1] - 1)][0], (float)vertex[(order[i][1] - 1)][1], vertex[(order[i][1] - 1)][2] + zPos, 0.0D, 1.0D);
/* 440:553 */       tessellator.addVertexWithUV((float)vertex[(order[i][2] - 1)][0], (float)vertex[(order[i][2] - 1)][1], vertex[(order[i][2] - 1)][2] + zPos, 1.0D, 1.0D);
/* 441:554 */       tessellator.addVertexWithUV((float)vertex[(order[i][3] - 1)][0], (float)vertex[(order[i][3] - 1)][1], vertex[(order[i][3] - 1)][2] + zPos, 1.0D, 0.0D);
/* 442:555 */       tessellator.draw();
/* 443:    */       
/* 444:557 */       tessellator.startDrawingQuads();
/* 445:558 */       tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], alpha);
/* 446:559 */       tessellator.addVertexWithUV((float)vertex[(order[i][0] - 1)][0], -(float)vertex[(order[i][0] - 1)][1], vertex[(order[i][0] - 1)][2] + zPos, 0.0D, 0.0D);
/* 447:560 */       tessellator.addVertexWithUV((float)vertex[(order[i][1] - 1)][0], -(float)vertex[(order[i][1] - 1)][1], vertex[(order[i][1] - 1)][2] + zPos, 0.0D, 1.0D);
/* 448:561 */       tessellator.addVertexWithUV((float)vertex[(order[i][2] - 1)][0], -(float)vertex[(order[i][2] - 1)][1], vertex[(order[i][2] - 1)][2] + zPos, 1.0D, 1.0D);
/* 449:562 */       tessellator.addVertexWithUV((float)vertex[(order[i][3] - 1)][0], -(float)vertex[(order[i][3] - 1)][1], vertex[(order[i][3] - 1)][2] + zPos, 1.0D, 0.0D);
/* 450:563 */       tessellator.draw();
/* 451:    */     }
/* 452:    */   }
/* 453:    */   
/* 454:    */   protected void renderMasterSparkLaser(Tessellator tessellator, double length, float width, float alpha, boolean rainbowFlag, float ticks)
/* 455:    */   {
/* 456:571 */     float[] colorR = { 255.0F, 255.0F, 224.0F, 0.0F, 0.0F, 0.0F, 224.0F, 255.0F };
/* 457:572 */     float[] colorG = { 0.0F, 165.0F, 224.0F, 255.0F, 0.0F, 255.0F, 0.0F, 255.0F };
/* 458:573 */     float[] colorB = { 0.0F, 0.0F, 0.0F, 0.0F, 255.0F, 255.0F, 224.0F, 255.0F };
/* 459:    */     
/* 460:575 */     float maxWidth = width * 1.6F;
/* 461:    */     
/* 462:577 */     int zAngleDivNum = 64;
/* 463:578 */     if (!rainbowFlag) {
/* 464:580 */       zAngleDivNum = 16;
/* 465:    */     }
/* 466:583 */     double angleSpanZ = 6.283185307179586D / zAngleDivNum;
/* 467:    */     
/* 468:585 */     int zDivNum = 13;
/* 469:586 */     double zLength = length;
/* 470:587 */     double zDivLength = zLength / (zDivNum - 1);
/* 471:588 */     double zLength2 = zLength / 2.0D;
/* 472:589 */     double zPos = 0.0D;
/* 473:    */     
/* 474:591 */     double zPosOld = zPos;
/* 475:    */     
/* 476:593 */     float xPos = 0.0F;
/* 477:594 */     float yPos = 0.0F;
/* 478:595 */     float xPos2 = 0.0F;
/* 479:596 */     float yPos2 = 0.0F;
/* 480:    */     
/* 481:598 */     float xPosOld = xPos;
/* 482:599 */     float yPosOld = yPos;
/* 483:600 */     float xPos2Old = xPos2;
/* 484:601 */     float yPos2Old = yPos2;
/* 485:    */     
/* 486:603 */     float angle = 0.0F;
/* 487:604 */     float angleSpan = 3.141593F / (zDivNum - 1);
/* 488:    */     
/* 489:606 */     width = (float)Math.sin(angle) * maxWidth;
/* 490:607 */     float widthOld = width;
/* 491:    */     
/* 492:    */ 
/* 493:610 */     float colorAngle = 0.0F;
/* 494:611 */     float colorRev = 0.09795661F;
/* 495:    */     
/* 496:613 */     float width2 = width;
/* 497:614 */     float widthOld2 = widthOld;
/* 498:617 */     while (zPos < zLength)
/* 499:    */     {
/* 500:619 */       zPos += zDivLength;
/* 501:620 */       widthOld = width;
/* 502:621 */       angle += angleSpan;
/* 503:622 */       if (width < maxWidth) {
/* 504:624 */         width = (float)Math.sin(angle) * maxWidth;
/* 505:    */       } else {
/* 506:628 */         width = maxWidth;
/* 507:    */       }
/* 508:633 */       widthOld2 = width2;
/* 509:634 */       width2 = width * ((float)Math.sin(angle + ticks / 40.0F) * 0.1F + 1.0F);
/* 510:    */       
/* 511:    */ 
/* 512:    */ 
/* 513:    */ 
/* 514:    */ 
/* 515:    */ 
/* 516:    */ 
/* 517:642 */       xPos = 0.0F;
/* 518:643 */       yPos = width;
/* 519:    */       
/* 520:645 */       double angleZ = angleSpanZ;
/* 521:647 */       for (int i = 0; i <= zAngleDivNum; i++)
/* 522:    */       {
/* 523:649 */         xPos = (float)Math.cos(angleZ) * width2;
/* 524:650 */         yPos = (float)Math.sin(angleZ) * width2;
/* 525:651 */         xPos2 = (float)Math.cos(angleZ) * widthOld2;
/* 526:652 */         yPos2 = (float)Math.sin(angleZ) * widthOld2;
/* 527:    */         
/* 528:654 */         tessellator.startDrawingQuads();
/* 529:655 */         if (rainbowFlag)
/* 530:    */         {
/* 531:657 */           float color = (colorAngle + ticks) * colorRev;
/* 532:658 */           colorAngle += 1.0F;
/* 533:659 */           tessellator.setColorRGBA_F(MathHelper.sin(color), MathHelper.cos(color), -MathHelper.sin(color) * 2.0F, 0.6F);
/* 534:    */         }
/* 535:    */         else
/* 536:    */         {
/* 537:663 */           tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, alpha);
/* 538:    */         }
/* 539:665 */         tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 540:666 */         tessellator.addVertexWithUV(xPosOld, yPosOld, zPos, 0.0D, 0.0D);
/* 541:667 */         tessellator.addVertexWithUV(xPos, yPos, zPos, 1.0D, 0.0D);
/* 542:668 */         tessellator.addVertexWithUV(xPos2, yPos2, zPosOld, 1.0D, 1.0D);
/* 543:669 */         tessellator.addVertexWithUV(xPos2Old, yPos2Old, zPosOld, 0.0D, 1.0D);
/* 544:670 */         tessellator.draw();
/* 545:    */         
/* 546:672 */         xPosOld = xPos;
/* 547:673 */         yPosOld = yPos;
/* 548:674 */         xPos2Old = xPos2;
/* 549:675 */         yPos2Old = yPos2;
/* 550:676 */         angleZ += angleSpanZ;
/* 551:    */       }
/* 552:680 */       ticks += 1.0F;
/* 553:681 */       zPosOld = zPos;
/* 554:    */     }
/* 555:    */   }
/* 556:    */   
/* 557:    */   public void renderLightEffect(Tessellator tessellator, int color, float size, EntityTHShot thShot)
/* 558:    */   {
/* 559:689 */     color %= 8;
/* 560:690 */     int count = thShot.getAnimationCount() % 2;
/* 561:691 */     float f3 = (count * 32 + 0) / 64.0F;
/* 562:692 */     float f4 = (count * 32 + 32) / 64.0F;
/* 563:693 */     float f5 = 0.0F;
/* 564:694 */     float f6 = 1.0F;
/* 565:695 */     float f7 = 1.0F;
/* 566:696 */     float f8 = 0.5F;
/* 567:697 */     float f9 = 0.25F;
/* 568:698 */     GL11.glScalef(size, size, size);
/* 569:699 */     tessellator.startDrawingQuads();
/* 570:700 */     tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], 0.5F);
/* 571:701 */     tessellator.addVertexWithUV(0.0F - f8, 0.0F - f9, 0.0D, f3, f6);
/* 572:702 */     tessellator.addVertexWithUV(f7 - f8, 0.0F - f9, 0.0D, f4, f6);
/* 573:703 */     tessellator.addVertexWithUV(f7 - f8, 1.0F - f9, 0.0D, f4, f5);
/* 574:704 */     tessellator.addVertexWithUV(0.0F - f8, 1.0F - f9, 0.0D, f3, f5);
/* 575:705 */     tessellator.draw();
/* 576:    */   }
/* 577:    */   
/* 578:    */   protected ResourceLocation getEntityTexture(Entity entity)
/* 579:    */   {
/* 580:711 */     return getEntityTexture((EntityTHLaser)entity);
/* 581:    */   }
/* 582:    */   
/* 583:    */   protected ResourceLocation getEntityTexture(EntityTHLaser thLaser)
/* 584:    */   {
/* 585:716 */     if ((thLaser.getAnimationCount() < 0) && (!(thLaser instanceof EntityTHSetLaser))) {
/* 586:718 */       return lightTexture;
/* 587:    */     }
/* 588:720 */     return laserTexture;
/* 589:    */   }
/* 590:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.shot.RenderTHLaser
 * JD-Core Version:    0.7.0.1
 */