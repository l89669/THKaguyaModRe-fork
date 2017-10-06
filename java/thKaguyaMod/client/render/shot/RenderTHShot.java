/*    1:     */ package thKaguyaMod.client.render.shot;
/*    2:     */ 
/*    3:     */ import cpw.mods.fml.relauncher.Side;
/*    4:     */ import cpw.mods.fml.relauncher.SideOnly;
/*    5:     */ import java.util.Random;
/*    6:     */ import net.minecraft.client.model.ModelBase;
/*    7:     */ import net.minecraft.client.renderer.Tessellator;
/*    8:     */ import net.minecraft.client.renderer.entity.Render;
/*    9:     */ import net.minecraft.entity.Entity;
/*   10:     */ import net.minecraft.util.MathHelper;
/*   11:     */ import net.minecraft.util.ResourceLocation;
/*   12:     */ import org.lwjgl.opengl.GL11;
/*   13:     */ import thKaguyaMod.client.THKaguyaRenderLib;
/*   14:     */ import thKaguyaMod.client.model.ModelSilverKnife;
/*   15:     */ import thKaguyaMod.entity.shot.EntityTHShot;
/*   16:     */ 
/*   17:     */ @SideOnly(Side.CLIENT)
/*   18:     */ public class RenderTHShot
/*   19:     */   extends Render
/*   20:     */ {
/*   21:  26 */   private static final ResourceLocation resourceLocation_Small = new ResourceLocation("thkaguyamod", "textures/shot/SmallShot.png");
/*   22:  27 */   private static final ResourceLocation resourceLocation_Pearl = new ResourceLocation("thkaguyamod", "textures/shot/PearlShot.png");
/*   23:  28 */   private static final ResourceLocation resourceLocation_Circle = new ResourceLocation("thkaguyamod", "textures/shot/CircleShot.png");
/*   24:  29 */   private static final ResourceLocation resourceLocation_Big = new ResourceLocation("thkaguyamod", "textures/shot/BigShot.png");
/*   25:  30 */   private static final ResourceLocation resourceLocation_Talisman = new ResourceLocation("thkaguyamod", "textures/shot/TalismanShot.png");
/*   26:  31 */   private static final ResourceLocation resourceLocation_Heart = new ResourceLocation("thkaguyamod", "textures/shot/HeartShot.png");
/*   27:  32 */   private static final ResourceLocation resourceLocation_Normal = new ResourceLocation("thkaguyamod", "textures/shot/Shot.png");
/*   28:  33 */   private static final ResourceLocation resourceLocation_Butterfly = new ResourceLocation("thkaguyamod", "textures/shot/ButterflyShot.png");
/*   29:  34 */   private static final ResourceLocation resourceLocation_Star = new ResourceLocation("thkaguyamod", "textures/shot/StarShot.png");
/*   30:  35 */   private static final ResourceLocation resourceLocation_Scale = new ResourceLocation("thkaguyamod", "textures/shot/ScaleShot.png");
/*   31:  36 */   private static final ResourceLocation resourceLocation_Wind = new ResourceLocation("thkaguyamod", "textures/shot/WindShot.png");
/*   32:  37 */   private static final ResourceLocation resourceLocation_Knife_Blue = new ResourceLocation("thkaguyamod", "textures/shot/SilverKnife_Blue.png");
/*   33:  38 */   private static final ResourceLocation resourceLocation_Knife_Red = new ResourceLocation("thkaguyamod", "textures/shot/SilverKnife_Red.png");
/*   34:  39 */   private static final ResourceLocation resourceLocation_Knife_Green = new ResourceLocation("thkaguyamod", "textures/shot/SilverKnife_Green.png");
/*   35:  40 */   private static final ResourceLocation resourceLocation_Knife_Yellow = new ResourceLocation("thkaguyamod", "textures/shot/SilverKnife_Yellow.png");
/*   36:  41 */   private static final ResourceLocation resourceLocation_Knife_Purple = new ResourceLocation("thkaguyamod", "textures/shot/SilverKnife_Purple.png");
/*   37:  42 */   private static final ResourceLocation resourceLocation_Knife_Orange = new ResourceLocation("thkaguyamod", "textures/shot/SilverKnife_Orange.png");
/*   38:  43 */   private static final ResourceLocation resourceLocation_Knife_Aqua = new ResourceLocation("thkaguyamod", "textures/shot/SilverKnife_Aqua.png");
/*   39:  44 */   private static final ResourceLocation resourceLocation_Knife_White = new ResourceLocation("thkaguyamod", "textures/shot/SilverKnife_White.png");
/*   40:  45 */   private static final ResourceLocation resourceLocation_Light = new ResourceLocation("thkaguyamod", "textures/shot/MusouFuuin.png");
/*   41:  46 */   private static final ResourceLocation resourceLocation_Kunai = new ResourceLocation("thkaguyamod", "textures/shot/KunaiShot.png");
/*   42:  47 */   private static final ResourceLocation resourceLocation_Laser = new ResourceLocation("thkaguyamod", "textures/shot/Laser.png");
/*   43:  48 */   private static final ResourceLocation resourceLocation_Phantom = new ResourceLocation("thkaguyamod", "textures/shot/MusouFuuin.png");
/*   44:  49 */   private static final ResourceLocation resourceLocation_Amulet = new ResourceLocation("thkaguyamod", "textures/shot/HomingAmulet.png");
/*   45:  50 */   private static final ResourceLocation resourceLocation_Diffusion = new ResourceLocation("thkaguyamod", "textures/shot/DiffusionAmulet.png");
/*   46:  52 */   private static final ResourceLocation resourceLocation_Familiar = new ResourceLocation("thkaguyamod", "textures/mob/Familiar.png");
/*   47:  53 */   private static final ResourceLocation resourceLocation_Arrow = new ResourceLocation("thkaguyamod", "textures/shot/ArrowShot.png");
/*   48:  55 */   protected float[] colorR = { 0.8784314F, 0.0F, 0.0F, 0.8784314F, 0.8784314F, 0.0F, 1.0F, 1.0F };
/*   49:  56 */   protected float[] colorG = { 0.0F, 0.0F, 0.8784314F, 0.8784314F, 0.0F, 0.8784314F, 0.5019608F, 1.0F };
/*   50:  57 */   protected float[] colorB = { 0.0F, 0.8784314F, 0.0F, 0.0F, 0.8784314F, 0.8784314F, 0.0F, 1.0F };
/*   51:     */   
/*   52:     */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/*   53:     */   {
/*   54:  67 */     renderTHShot((EntityTHShot)entity, x, y, z, yaw, pitch);
/*   55:     */   }
/*   56:     */   
/*   57:     */   public void renderTHShot(EntityTHShot thShot, double x, double y, double z, float yaw, float f1)
/*   58:     */   {
/*   59:  72 */     GL11.glPushMatrix();
/*   60:  73 */     bindEntityTexture(thShot);
/*   61:  74 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/*   62:  75 */     GL11.glDisable(2896);
/*   63:     */     
/*   64:  77 */     float size = thShot.getShotSize();
/*   65:     */     
/*   66:  79 */     int color = thShot.getShotColor();
/*   67:  80 */     int form = thShot.getShotForm();
/*   68:  83 */     if (thShot.getAnimationCount() < 0)
/*   69:     */     {
/*   70:  85 */       int delayCount = -thShot.getAnimationCount();
/*   71:  86 */       if (delayCount > 10) {
/*   72:  88 */         delayCount = 10;
/*   73:     */       }
/*   74:  90 */       float size2 = delayCount * 0.3F * size;
/*   75:  91 */       if (size2 > 1.0F) {
/*   76:  93 */         size2 = 1.0F;
/*   77:     */       }
/*   78:  95 */       GL11.glEnable(3042);
/*   79:  96 */       GL11.glBlendFunc(1, 769);
/*   80:     */       
/*   81:  98 */       THKaguyaRenderLib.getGLRotatefByTherdPersonView(this.renderManager);
/*   82:  99 */       GL11.glDepthMask(false);
/*   83: 100 */       renderLightEffect(color, size2, thShot.getAnimationCount());
/*   84: 101 */       GL11.glDepthMask(true);
/*   85: 102 */       GL11.glDisable(3042);
/*   86:     */     }
/*   87:     */     else
/*   88:     */     {
/*   89: 107 */       switch (form)
/*   90:     */       {
/*   91:     */       case 0: 
/*   92: 111 */         renderNormalShot(size, color, false);
/*   93: 112 */         break;
/*   94:     */       case 1: 
/*   95: 115 */         renderNormalShot(size, color, false);
/*   96: 116 */         break;
/*   97:     */       case 2: 
/*   98: 119 */         renderNormalShot(size, color, false);
/*   99: 120 */         break;
/*  100:     */       case 3: 
/*  101: 123 */         renderNormalShot(size, color, false);
/*  102: 124 */         break;
/*  103:     */       case 4: 
/*  104: 127 */         renderNormalShot(size, color, false);
/*  105: 128 */         break;
/*  106:     */       case 6: 
/*  107: 131 */         renderScaleShot(size, color, yaw, thShot.rotationPitch, thShot.getAngleZ(), x, y, z);
/*  108: 132 */         break;
/*  109:     */       case 7: 
/*  110: 135 */         renderButterflyShot(size, color, thShot.getAnimationCount(), yaw, thShot.rotationPitch, thShot.getAngleZ());
/*  111: 136 */         break;
/*  112:     */       case 8: 
/*  113: 139 */         renderStarShot(size, color, thShot.getAnimationCount());
/*  114: 140 */         break;
/*  115:     */       case 9: 
/*  116: 143 */         renderStarShot(size, color, thShot.getAnimationCount());
/*  117: 144 */         break;
/*  118:     */       case 10: 
/*  119: 147 */         renderRiceShot(size, yaw, thShot.rotationPitch, 4.0D, 1.2F, -2.0D, 7, 5, color, 0.7F);
/*  120: 148 */         break;
/*  121:     */       case 11: 
/*  122: 151 */         renderCrystalShot(size, color, yaw, thShot.rotationPitch);
/*  123: 152 */         break;
/*  124:     */       case 5: 
/*  125: 155 */         renderLightShot(size, color, thShot.getAnimationCount() % 2);
/*  126: 156 */         break;
/*  127:     */       case 12: 
/*  128: 159 */         renderNormalShot(size, color, false);
/*  129: 160 */         break;
/*  130:     */       case 13: 
/*  131: 163 */         renderKunaiShot(size, color, yaw, thShot.rotationPitch, thShot.getAngleZ());
/*  132: 164 */         break;
/*  133:     */       case 14: 
/*  134: 167 */         renderTalismanShot(size, color, yaw, thShot.rotationPitch, thShot.getAngleZ());
/*  135: 168 */         break;
/*  136:     */       case 15: 
/*  137: 171 */         renderLightShot(size, color, thShot.getAnimationCount() % 2);
/*  138: 172 */         break;
/*  139:     */       case 16: 
/*  140: 175 */         renderOvalShot(size, yaw, thShot.rotationPitch, 2.0D, 1.0F, -1.0D, 7, 5, color, 0.7F);
/*  141: 176 */         break;
/*  142:     */       case 17: 
/*  143: 179 */         renderFamiliar(size, color, thShot.getAnimationCount());
/*  144: 180 */         break;
/*  145:     */       case 18: 
/*  146: 183 */         renderArrowShot(size, color, yaw, thShot.rotationPitch, thShot.getAngleZ());
/*  147: 184 */         break;
/*  148:     */       case 26: 
/*  149: 187 */         GL11.glEnable(3042);
/*  150: 188 */         GL11.glBlendFunc(1, 1);
/*  151: 189 */         GL11.glDepthMask(false);
/*  152: 190 */         double time_r = thShot.getAnimationCount() / 180.0D * 3.141592653589793D;
/*  153:     */         
/*  154:     */ 
/*  155: 193 */         THKaguyaRenderLib.getGLRotatefByTherdPersonView(this.renderManager);
/*  156:     */         
/*  157: 195 */         size = 1.0F;
/*  158: 196 */         float size2 = size * 0.7F;
/*  159: 197 */         GL11.glScalef(size, size, size);
/*  160:     */         
/*  161:     */ 
/*  162: 200 */         renderPhantomPart(color, time_r, 0);
/*  163:     */         
/*  164: 202 */         GL11.glTranslatef((float)Math.sin(time_r * 5.0D) * 0.2F, 0.1F + thShot.getAnimationCount() % 20 * 0.06F, 0.0F);
/*  165: 203 */         size = 0.9F * ((20.0F - thShot.getAnimationCount() % 20) / 20.0F);
/*  166: 204 */         GL11.glScalef(size, size, size);
/*  167: 205 */         renderPhantomPart(color, time_r, 0);
/*  168:     */         
/*  169: 207 */         GL11.glTranslatef((float)Math.cos(time_r * 5.0D) * 0.2F, 0.1F + thShot.getAnimationCount() % 20 * 0.07F, 0.0F);
/*  170: 208 */         size = 0.8F * ((20.0F - thShot.getAnimationCount() % 20) / 20.0F);
/*  171: 209 */         GL11.glScalef(size, size, size);
/*  172: 210 */         renderPhantomPart(color, time_r, 0);
/*  173:     */         
/*  174: 212 */         GL11.glDisable(3042);
/*  175:     */         
/*  176: 214 */         GL11.glDepthMask(true);
/*  177: 215 */         break;
/*  178:     */       case 27: 
/*  179: 218 */         renderAmuletShot(size, color, thShot.getAnimationCount(), yaw, thShot.rotationPitch, thShot.getAngleZ());
/*  180: 219 */         break;
/*  181:     */       case 28: 
/*  182: 222 */         size2 = size * 3.0F;
/*  183: 223 */         GL11.glScalef(size2, size2, size2);
/*  184:     */         
/*  185: 225 */         ModelBase modelSilverKnife = new ModelSilverKnife();
/*  186: 226 */         GL11.glRotatef(yaw, 0.0F, 1.0F, 0.0F);
/*  187: 227 */         GL11.glRotatef(-thShot.rotationPitch, 1.0F, 0.0F, 0.0F);
/*  188: 228 */         GL11.glRotatef(thShot.getAngleZ(), 0.0F, 0.0F, 1.0F);
/*  189: 229 */         modelSilverKnife.render(thShot, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
/*  190: 230 */         break;
/*  191:     */       case 29: 
/*  192: 233 */         renderWindShot(size, color, thShot.getAnimationCount());
/*  193: 234 */         break;
/*  194:     */       case 30: 
/*  195: 237 */         renderNormalShot(size, color, true);
/*  196: 238 */         break;
/*  197:     */       case 31: 
/*  198: 241 */         renderKishituShot(size, color, thShot.getAnimationCount());
/*  199: 242 */         break;
/*  200:     */       }
/*  201:     */     }
/*  202: 247 */     GL11.glPopMatrix();
/*  203:     */     
/*  204:     */ 
/*  205: 250 */     GL11.glEnable(2896);
/*  206:     */   }
/*  207:     */   
/*  208:     */   public void renderFamiliar(float size, int color, int count)
/*  209:     */   {
/*  210: 258 */     Tessellator tessellator = Tessellator.instance;
/*  211:     */     
/*  212: 260 */     GL11.glEnable(3042);
/*  213: 261 */     GL11.glBlendFunc(1, 769);
/*  214: 262 */     GL11.glDepthMask(false);
/*  215: 263 */     GL11.glScalef(size, size, size);
/*  216:     */     
/*  217: 265 */     THKaguyaRenderLib.getGLRotatefByTherdPersonView(this.renderManager);
/*  218: 266 */     GL11.glRotatef(count * 3.7F, 0.0F, 0.0F, 1.0F);
/*  219: 267 */     float width = 1.0F;
/*  220: 268 */     float height = 1.0F;
/*  221:     */     
/*  222: 270 */     float u1 = 0.0F;
/*  223: 271 */     float u2 = 1.0F;
/*  224: 272 */     float v1 = 0.0F;
/*  225: 273 */     float v2 = 1.0F;
/*  226: 274 */     tessellator.startDrawingQuads();
/*  227: 275 */     tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], 0.5F);
/*  228: 276 */     tessellator.addVertexWithUV(-width, -height, 0.0D, u1, v2);
/*  229: 277 */     tessellator.addVertexWithUV(width, -height, 0.0D, u2, v2);
/*  230: 278 */     tessellator.addVertexWithUV(width, height, 0.0D, u2, v1);
/*  231: 279 */     tessellator.addVertexWithUV(-width, height, 0.0D, u1, v1);
/*  232: 280 */     tessellator.draw();
/*  233: 281 */     GL11.glDepthMask(true);
/*  234: 282 */     GL11.glDisable(3042);
/*  235:     */   }
/*  236:     */   
/*  237:     */   public void renderNormalShot(float size, int color, boolean blend)
/*  238:     */   {
/*  239: 288 */     Tessellator tessellator = Tessellator.instance;
/*  240: 289 */     if (blend)
/*  241:     */     {
/*  242: 291 */       GL11.glEnable(3042);
/*  243: 292 */       GL11.glBlendFunc(770, 1);
/*  244:     */       
/*  245: 294 */       GL11.glDepthMask(false);
/*  246:     */     }
/*  247: 297 */     GL11.glScalef(size, size, size);
/*  248:     */     
/*  249: 299 */     THKaguyaRenderLib.getGLRotatefByTherdPersonView(this.renderManager);
/*  250: 300 */     float width = 1.0F;
/*  251: 301 */     float height = 1.0F;
/*  252:     */     
/*  253: 303 */     float u1 = color / 8.0F;
/*  254: 304 */     float u2 = (color + 1) / 8.0F;
/*  255: 305 */     float v1 = 0.0F;
/*  256: 306 */     float v2 = 1.0F;
/*  257: 307 */     tessellator.startDrawingQuads();
/*  258: 308 */     tessellator.addVertexWithUV(-width, -height, 0.0D, u1, v2);
/*  259: 309 */     tessellator.addVertexWithUV(width, -height, 0.0D, u2, v2);
/*  260: 310 */     tessellator.addVertexWithUV(width, height, 0.0D, u2, v1);
/*  261: 311 */     tessellator.addVertexWithUV(-width, height, 0.0D, u1, v1);
/*  262: 312 */     tessellator.draw();
/*  263: 313 */     GL11.glDepthMask(true);
/*  264: 314 */     GL11.glDisable(3042);
/*  265:     */   }
/*  266:     */   
/*  267:     */   public void renderLightShot(float size, int color, int count)
/*  268:     */   {
/*  269: 320 */     Tessellator tessellator = Tessellator.instance;
/*  270: 321 */     GL11.glEnable(3042);
/*  271: 322 */     GL11.glBlendFunc(770, 1);
/*  272:     */     
/*  273: 324 */     GL11.glScalef(size, size, size);
/*  274:     */     
/*  275: 326 */     THKaguyaRenderLib.getGLRotatefByTherdPersonView(this.renderManager);
/*  276: 327 */     color %= 8;
/*  277: 328 */     float uMin = (count * 32 + 0) / 64.0F;
/*  278: 329 */     float uMax = (count * 32 + 32) / 64.0F;
/*  279: 330 */     float vMin = 0.0F;
/*  280: 331 */     float vMax = 1.0F;
/*  281: 332 */     float width = 2.0F;
/*  282:     */     
/*  283:     */ 
/*  284: 335 */     float alpha = 1.0F;
/*  285:     */     
/*  286:     */ 
/*  287:     */ 
/*  288:     */ 
/*  289: 340 */     GL11.glDepthMask(false);
/*  290: 341 */     tessellator.startDrawingQuads();
/*  291: 342 */     tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], 0.8F * alpha);
/*  292: 343 */     tessellator.addVertexWithUV(-width, -width, 0.0D, uMin, vMax);
/*  293: 344 */     tessellator.addVertexWithUV(width, -width, 0.0D, uMax, vMax);
/*  294: 345 */     tessellator.addVertexWithUV(width, width, 0.0D, uMax, vMin);
/*  295: 346 */     tessellator.addVertexWithUV(-width, width, 0.0D, uMin, vMin);
/*  296: 347 */     tessellator.draw();
/*  297:     */     
/*  298:     */ 
/*  299: 350 */     width = 1.0F;
/*  300: 351 */     tessellator.startDrawingQuads();
/*  301: 352 */     tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F * alpha);
/*  302: 353 */     tessellator.addVertexWithUV(-width, -width, 0.01D, uMin, vMax);
/*  303: 354 */     tessellator.addVertexWithUV(width, -width, 0.01D, uMax, vMax);
/*  304: 355 */     tessellator.addVertexWithUV(width, width, 0.01D, uMax, vMin);
/*  305: 356 */     tessellator.addVertexWithUV(-width, width, 0.01D, uMin, vMin);
/*  306: 357 */     tessellator.draw();
/*  307:     */     
/*  308: 359 */     GL11.glDepthMask(true);
/*  309:     */     
/*  310: 361 */     GL11.glDisable(3042);
/*  311:     */   }
/*  312:     */   
/*  313:     */   public void renderKishituShot(float size, int color, int count)
/*  314:     */   {
/*  315: 367 */     Tessellator tessellator = Tessellator.instance;
/*  316: 368 */     GL11.glEnable(3042);
/*  317: 369 */     GL11.glBlendFunc(770, 1);
/*  318: 370 */     GL11.glDepthMask(false);
/*  319: 371 */     GL11.glScalef(size, size, size);
/*  320:     */     
/*  321: 373 */     THKaguyaRenderLib.getGLRotatefByTherdPersonView(this.renderManager);
/*  322:     */     
/*  323: 375 */     count %= 2;
/*  324: 376 */     float uMin = (count * 32 + 0) / 64.0F;
/*  325: 377 */     float uMax = (count * 32 + 32) / 64.0F;
/*  326: 378 */     float vMin = 0.0F;
/*  327: 379 */     float vMax = 1.0F;
/*  328: 380 */     float width = 2.0F;
/*  329:     */     
/*  330: 382 */     tessellator.startDrawingQuads();
/*  331: 383 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  332: 384 */     tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], 0.5F);
/*  333: 385 */     tessellator.addVertexWithUV(-width, -width, 0.0D, uMin, vMax);
/*  334: 386 */     tessellator.addVertexWithUV(width, -width, 0.0D, uMax, vMax);
/*  335: 387 */     tessellator.addVertexWithUV(width, width, 0.0D, uMax, vMin);
/*  336: 388 */     tessellator.addVertexWithUV(-width, width, 0.0D, uMin, vMin);
/*  337: 389 */     tessellator.draw();
/*  338:     */     
/*  339: 391 */     width = 1.6F;
/*  340: 392 */     tessellator.startDrawingQuads();
/*  341: 393 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  342: 394 */     tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 0.8F);
/*  343: 395 */     tessellator.addVertexWithUV(-width, -width, 0.01D, uMin, vMax);
/*  344: 396 */     tessellator.addVertexWithUV(width, -width, 0.01D, uMax, vMax);
/*  345: 397 */     tessellator.addVertexWithUV(width, width, 0.01D, uMax, vMin);
/*  346: 398 */     tessellator.addVertexWithUV(-width, width, 0.01D, uMin, vMin);
/*  347: 399 */     tessellator.draw();
/*  348: 400 */     GL11.glDepthMask(true);
/*  349: 401 */     GL11.glDisable(3042);
/*  350:     */   }
/*  351:     */   
/*  352:     */   public void renderLightEffect(int color, float size, int count)
/*  353:     */   {
/*  354: 407 */     Tessellator tessellator = Tessellator.instance;
/*  355:     */     
/*  356: 409 */     count %= 2;
/*  357:     */     
/*  358: 411 */     float u1 = (count * 32 + 0) / 64.0F;
/*  359: 412 */     float u2 = (count * 32 + 32) / 64.0F;
/*  360: 413 */     float v1 = 0.0F;
/*  361: 414 */     float v2 = 1.0F;
/*  362: 415 */     float width = 1.0F;
/*  363: 416 */     GL11.glScalef(size, size, size);
/*  364: 417 */     tessellator.startDrawingQuads();
/*  365: 418 */     tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], 0.5F);
/*  366: 419 */     tessellator.addVertexWithUV(-width, -width, 0.0D, u1, v2);
/*  367: 420 */     tessellator.addVertexWithUV(width, -width, 0.0D, u2, v2);
/*  368: 421 */     tessellator.addVertexWithUV(width, width, 0.0D, u2, v1);
/*  369: 422 */     tessellator.addVertexWithUV(-width, width, 0.0D, u1, v1);
/*  370: 423 */     tessellator.draw();
/*  371:     */   }
/*  372:     */   
/*  373:     */   public void renderScaleShot(float size, int color, float yaw, float pitch, float slope, double x, double y, double z)
/*  374:     */   {
/*  375: 482 */     double length = 2.0D;
/*  376: 483 */     float width = 1.0F;
/*  377: 484 */     double zPos = -1.0D;
/*  378: 485 */     int zAngleDivNum = 7;
/*  379: 486 */     int zDivNum = 8;
/*  380: 487 */     float alpha = 0.31F;
/*  381:     */     
/*  382:     */ 
/*  383: 490 */     GL11.glScalef(size, size, size);
/*  384: 491 */     GL11.glRotatef(yaw + 180.0F, 0.0F, 1.0F, 0.0F);
/*  385: 492 */     GL11.glRotatef(pitch, 1.0F, 0.0F, 0.0F);
/*  386:     */     
/*  387:     */ 
/*  388: 495 */     renderOvalShotPart(size, yaw, pitch, length * 0.4D, width * 0.5F, -0.85D, zAngleDivNum, 5, 7, 1.0F);
/*  389:     */     
/*  390: 497 */     GL11.glEnable(3042);
/*  391: 498 */     GL11.glBlendFunc(770, 1);
/*  392: 499 */     GL11.glDepthMask(false);
/*  393: 500 */     renderHarfOvalShotPart(size, yaw, pitch, length, width * 1.2F, zPos, zAngleDivNum, zDivNum, color, alpha);
/*  394: 501 */     GL11.glDepthMask(true);
/*  395: 502 */     GL11.glDisable(3042);
/*  396:     */   }
/*  397:     */   
/*  398:     */   public void renderButterflyShot(float size, int color, int count, float yaw, float pitch, float slope)
/*  399:     */   {
/*  400: 508 */     Tessellator tessellator = Tessellator.instance;
/*  401: 509 */     GL11.glEnable(3042);
/*  402: 510 */     GL11.glBlendFunc(770, 1);
/*  403: 511 */     GL11.glDepthMask(false);
/*  404: 512 */     GL11.glDisable(2884);
/*  405: 513 */     GL11.glScalef(size, size, size);
/*  406: 514 */     float wingAngle = MathHelper.sin(count / 3.0F) * 45.0F;
/*  407: 515 */     if (pitch > 90.0F) {
/*  408: 517 */       pitch = 90.0F - pitch % 90.0F;
/*  409: 519 */     } else if (pitch < -90.0F) {
/*  410: 521 */       pitch = -90.0F - pitch % 90.0F;
/*  411:     */     }
/*  412: 523 */     GL11.glRotatef(yaw, 0.0F, 1.0F, 0.0F);
/*  413: 524 */     GL11.glRotatef(-pitch, 1.0F, 0.0F, 0.0F);
/*  414: 525 */     GL11.glRotatef(slope, 0.0F, 0.0F, 1.0F);
/*  415: 526 */     GL11.glRotatef(wingAngle, 0.0F, 0.0F, 1.0F);
/*  416:     */     
/*  417: 528 */     color %= 8;
/*  418: 529 */     float minU = 0.0F;
/*  419: 530 */     float maxU = 0.25F;
/*  420: 531 */     float minV = 0.0F;
/*  421: 532 */     float maxV = 1.0F;
/*  422: 533 */     float width = 2.0F;
/*  423: 534 */     float width2 = 1.8F;
/*  424:     */     
/*  425: 536 */     tessellator.startDrawingQuads();
/*  426: 537 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  427: 538 */     tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], 1.0F);
/*  428: 539 */     tessellator.addVertexWithUV(0.0D, 0.0D, width, maxU, minV);
/*  429: 540 */     tessellator.addVertexWithUV(width, 0.0D, width, minU, minV);
/*  430: 541 */     tessellator.addVertexWithUV(width, 0.0D, -width, minU, maxV);
/*  431: 542 */     tessellator.addVertexWithUV(0.0D, 0.0D, -width, maxU, maxV);
/*  432: 543 */     tessellator.draw();
/*  433: 544 */     tessellator.startDrawingQuads();
/*  434: 545 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  435: 546 */     tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 0.4F);
/*  436: 547 */     tessellator.addVertexWithUV(0.0D, 0.0D, width2, maxU, minV);
/*  437: 548 */     tessellator.addVertexWithUV(width2, 0.0D, width2, minU, minV);
/*  438: 549 */     tessellator.addVertexWithUV(width2, 0.0D, -width2, minU, maxV);
/*  439: 550 */     tessellator.addVertexWithUV(0.0D, 0.0D, -width2, maxU, maxV);
/*  440: 551 */     tessellator.draw();
/*  441: 552 */     GL11.glRotatef(-wingAngle * 2.0F, 0.0F, 0.0F, 1.0F);
/*  442: 553 */     width *= 1.0F;
/*  443: 554 */     width2 *= 1.0F;
/*  444:     */     
/*  445: 556 */     tessellator.startDrawingQuads();
/*  446: 557 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  447: 558 */     tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], 1.0F);
/*  448: 559 */     tessellator.addVertexWithUV(0.0D, 0.0D, width, maxU, minV);
/*  449: 560 */     tessellator.addVertexWithUV(-width, 0.0D, width, minU, minV);
/*  450: 561 */     tessellator.addVertexWithUV(-width, 0.0D, -width, minU, maxV);
/*  451: 562 */     tessellator.addVertexWithUV(0.0D, 0.0D, -width, maxU, maxV);
/*  452: 563 */     tessellator.draw();
/*  453: 564 */     tessellator.startDrawingQuads();
/*  454: 565 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  455: 566 */     tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 0.4F);
/*  456: 567 */     tessellator.addVertexWithUV(0.0D, 0.0D, width2, maxU, minV);
/*  457: 568 */     tessellator.addVertexWithUV(-width2, 0.0D, width2, minU, minV);
/*  458: 569 */     tessellator.addVertexWithUV(-width2, 0.0D, -width2, minU, maxV);
/*  459: 570 */     tessellator.addVertexWithUV(0.0D, 0.0D, -width2, maxU, maxV);
/*  460: 571 */     tessellator.draw();
/*  461: 572 */     GL11.glEnable(2884);
/*  462: 573 */     GL11.glDepthMask(true);
/*  463: 574 */     GL11.glDisable(3042);
/*  464:     */   }
/*  465:     */   
/*  466:     */   public void renderStarShot(float size, int color, int count)
/*  467:     */   {
/*  468: 580 */     Tessellator tessellator = Tessellator.instance;
/*  469: 581 */     GL11.glEnable(3042);
/*  470: 582 */     GL11.glBlendFunc(770, 1);
/*  471: 583 */     GL11.glDepthMask(false);
/*  472: 584 */     GL11.glScalef(size, size, size);
/*  473:     */     
/*  474: 586 */     THKaguyaRenderLib.getGLRotatefByTherdPersonView(this.renderManager);
/*  475: 587 */     GL11.glRotatef(count * 3.7F, 0.0F, 0.0F, 1.0F);
/*  476: 588 */     float[] topPointX = new float[11];
/*  477: 589 */     float[] topPointY = new float[11];
/*  478: 590 */     float angle = 0.0F;
/*  479: 591 */     float spanAngle = 0.6283186F;
/*  480: 592 */     for (int i = 0; i < 10; i += 2)
/*  481:     */     {
/*  482: 594 */       topPointX[i] = ((float)Math.cos(angle) * 2.0F);
/*  483: 595 */       topPointY[i] = ((float)Math.sin(angle) * 2.0F);
/*  484: 596 */       angle += spanAngle;
/*  485: 597 */       topPointX[(i + 1)] = ((float)Math.cos(angle) * 1.2F);
/*  486: 598 */       topPointY[(i + 1)] = ((float)Math.sin(angle) * 1.2F);
/*  487: 599 */       angle += spanAngle;
/*  488:     */     }
/*  489: 601 */     topPointX[10] = topPointX[0];
/*  490: 602 */     topPointY[10] = topPointY[0];
/*  491: 603 */     for (int i = 0; i < 9; i += 2)
/*  492:     */     {
/*  493: 606 */       tessellator.startDrawingQuads();
/*  494: 607 */       tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], 0.7F);
/*  495: 608 */       tessellator.addVertexWithUV(topPointX[i], topPointY[i], 0.0D, 0.0D, 0.0D);
/*  496: 609 */       tessellator.addVertexWithUV(topPointX[(i + 1)], topPointY[(i + 1)], 0.0D, 1.0D, 0.0D);
/*  497: 610 */       tessellator.addVertexWithUV(topPointX[(i + 2)], topPointY[(i + 2)], 0.0D, 1.0D, 1.0D);
/*  498: 611 */       tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, 0.0D, 1.0D);
/*  499: 612 */       tessellator.draw();
/*  500:     */       
/*  501: 614 */       tessellator.startDrawingQuads();
/*  502: 615 */       tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 0.7F);
/*  503: 616 */       tessellator.addVertexWithUV(topPointX[i] * 0.7F, topPointY[i] * 0.7F, 0.01D, 0.0D, 0.0D);
/*  504: 617 */       tessellator.addVertexWithUV(topPointX[(i + 1)] * 0.95F, topPointY[(i + 1)] * 0.95F, 0.01D, 1.0D, 0.0D);
/*  505: 618 */       tessellator.addVertexWithUV(topPointX[(i + 2)] * 0.7F, topPointY[(i + 2)] * 0.7F, 0.01D, 1.0D, 1.0D);
/*  506: 619 */       tessellator.addVertexWithUV(0.0D, 0.0D, 0.01D, 0.0D, 1.0D);
/*  507: 620 */       tessellator.draw();
/*  508:     */     }
/*  509: 622 */     GL11.glDepthMask(true);
/*  510: 623 */     GL11.glDisable(3042);
/*  511:     */   }
/*  512:     */   
/*  513:     */   public void renderCrystalShot(float size, int color, float yaw, float pitch)
/*  514:     */   {
/*  515: 629 */     Tessellator tessellator = Tessellator.instance;
/*  516: 630 */     GL11.glEnable(3042);
/*  517: 631 */     GL11.glBlendFunc(770, 1);
/*  518: 632 */     GL11.glDepthMask(false);
/*  519: 633 */     GL11.glScalef(size, size, size);
/*  520: 634 */     GL11.glRotatef(yaw, 0.0F, 1.0F, 0.0F);
/*  521: 635 */     GL11.glRotatef(-pitch, 1.0F, 0.0F, 0.0F);
/*  522: 636 */     float width = 0.96F;
/*  523: 637 */     double length = 4.0D;double length_b = 1.0D;
/*  524: 638 */     float width2 = width * 0.8F;
/*  525: 639 */     double length2 = length * 0.8D;double length2_b = length * 0.4D;
/*  526:     */     
/*  527:     */ 
/*  528:     */ 
/*  529: 643 */     float alpha = 1.0F;
/*  530: 650 */     for (int i = 0; i < 4; i++)
/*  531:     */     {
/*  532: 652 */       GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/*  533: 653 */       tessellator.startDrawingQuads();
/*  534: 654 */       tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  535: 655 */       tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, alpha);
/*  536: 656 */       tessellator.addVertexWithUV(0.0D, width2, 0.0D, 1.0D, 0.0D);
/*  537: 657 */       tessellator.addVertexWithUV(-width2, 0.0D, 0.0D, 0.0D, 0.0D);
/*  538: 658 */       tessellator.addVertexWithUV(0.0D, 0.0D, length2, 0.0D, 1.0D);
/*  539: 659 */       tessellator.addVertexWithUV(0.0D, 0.0D, length2, 0.0D, 1.0D);
/*  540: 660 */       tessellator.draw();
/*  541: 661 */       tessellator.startDrawingQuads();
/*  542: 662 */       tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  543: 663 */       tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, alpha);
/*  544: 664 */       tessellator.addVertexWithUV(0.0D, width2, 0.0D, 1.0D, 0.0D);
/*  545: 665 */       tessellator.addVertexWithUV(width2, 0.0D, 0.0D, 0.0D, 0.0D);
/*  546: 666 */       tessellator.addVertexWithUV(0.0D, 0.0D, -length2_b, 0.0D, 1.0D);
/*  547: 667 */       tessellator.addVertexWithUV(0.0D, 0.0D, -length2_b, 0.0D, 1.0D);
/*  548: 668 */       tessellator.draw();
/*  549:     */     }
/*  550: 670 */     for (int i = 0; i < 4; i++)
/*  551:     */     {
/*  552: 672 */       GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/*  553: 673 */       tessellator.startDrawingQuads();
/*  554: 674 */       tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  555: 675 */       tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], alpha * 0.7F);
/*  556: 676 */       tessellator.addVertexWithUV(0.0D, width, 0.0D, 1.0D, 0.0D);
/*  557: 677 */       tessellator.addVertexWithUV(-width, 0.0D, 0.0D, 0.0D, 0.0D);
/*  558: 678 */       tessellator.addVertexWithUV(0.0D, 0.0D, length, 0.0D, 1.0D);
/*  559: 679 */       tessellator.addVertexWithUV(0.0D, 0.0D, length, 0.0D, 1.0D);
/*  560: 680 */       tessellator.draw();
/*  561: 681 */       tessellator.startDrawingQuads();
/*  562: 682 */       tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  563: 683 */       tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], alpha * 0.7F);
/*  564: 684 */       tessellator.addVertexWithUV(0.0D, width, 0.0D, 1.0D, 0.0D);
/*  565: 685 */       tessellator.addVertexWithUV(width, 0.0D, 0.0D, 0.0D, 0.0D);
/*  566: 686 */       tessellator.addVertexWithUV(0.0D, 0.0D, -length_b, 0.0D, 1.0D);
/*  567: 687 */       tessellator.addVertexWithUV(0.0D, 0.0D, -length_b, 0.0D, 1.0D);
/*  568: 688 */       tessellator.draw();
/*  569:     */     }
/*  570: 690 */     GL11.glDepthMask(true);
/*  571: 691 */     GL11.glDisable(3042);
/*  572:     */   }
/*  573:     */   
/*  574:     */   public void renderKunaiShot(float size, int color, float yaw, float pitch, float slope)
/*  575:     */   {
/*  576: 697 */     Tessellator tessellator = Tessellator.instance;
/*  577: 698 */     GL11.glScalef(size, size, size);
/*  578: 699 */     GL11.glRotatef(yaw, 0.0F, 1.0F, 0.0F);
/*  579: 700 */     GL11.glRotatef(-pitch, 1.0F, 0.0F, 0.0F);
/*  580: 701 */     GL11.glRotatef(slope, 0.0F, 0.0F, 1.0F);
/*  581:     */     
/*  582: 703 */     float width = 1.0F;
/*  583: 704 */     float width2 = 0.6F;
/*  584: 705 */     float flont = 1.4F;
/*  585: 706 */     float back = -0.8F;
/*  586: 707 */     float back2 = -1.0F;
/*  587: 708 */     float root = -2.6F;
/*  588: 709 */     float height = 0.3F;
/*  589:     */     
/*  590: 711 */     width = 0.8F;
/*  591: 712 */     flont = 1.12F;
/*  592: 713 */     back = -0.8F;
/*  593: 714 */     back2 = -1.0F;
/*  594: 715 */     height = 0.24F;
/*  595:     */     
/*  596: 717 */     tessellator.startDrawingQuads();
/*  597: 718 */     tessellator.addVertexWithUV(-width, 0.0D, back, 0.5D, 1.0D);
/*  598: 719 */     tessellator.addVertexWithUV(0.0D, 0.0D, flont, 0.25D, 0.0D);
/*  599: 720 */     tessellator.addVertexWithUV(width, 0.0D, back, 0.0D, 1.0D);
/*  600: 721 */     tessellator.addVertexWithUV(0.0D, height, back, 0.25D, 1.0D);
/*  601: 722 */     tessellator.draw();
/*  602:     */     
/*  603:     */ 
/*  604: 725 */     tessellator.startDrawingQuads();
/*  605: 726 */     tessellator.addVertexWithUV(0.0D, 0.0D, flont, 0.25D, 0.0D);
/*  606: 727 */     tessellator.addVertexWithUV(0.0D, 0.0D, flont, 0.25D, 0.0D);
/*  607: 728 */     tessellator.addVertexWithUV(0.0D, -height, back, 0.25D, 1.0D);
/*  608: 729 */     tessellator.addVertexWithUV(width, 0.0D, back, 0.0D, 1.0D);
/*  609: 730 */     tessellator.draw();
/*  610:     */     
/*  611: 732 */     tessellator.startDrawingQuads();
/*  612: 733 */     tessellator.addVertexWithUV(0.0D, 0.0D, flont, 0.25D, 0.0D);
/*  613: 734 */     tessellator.addVertexWithUV(0.0D, 0.0D, flont, 0.25D, 0.0D);
/*  614: 735 */     tessellator.addVertexWithUV(-width, 0.0D, back, 0.5D, 1.0D);
/*  615: 736 */     tessellator.addVertexWithUV(0.0D, -height, back, 0.25D, 1.0D);
/*  616: 737 */     tessellator.draw();
/*  617:     */     
/*  618:     */ 
/*  619: 740 */     tessellator.startDrawingQuads();
/*  620: 741 */     tessellator.addVertexWithUV(0.0D, 0.0D, back2, 0.25D, 0.0D);
/*  621: 742 */     tessellator.addVertexWithUV(0.0D, 0.0D, back2, 0.25D, 0.0D);
/*  622: 743 */     tessellator.addVertexWithUV(0.0D, height, back, 0.25D, 1.0D);
/*  623: 744 */     tessellator.addVertexWithUV(width, 0.0D, back, 0.0D, 1.0D);
/*  624: 745 */     tessellator.draw();
/*  625:     */     
/*  626: 747 */     tessellator.startDrawingQuads();
/*  627: 748 */     tessellator.addVertexWithUV(0.0D, 0.0D, back2, 0.25D, 0.0D);
/*  628: 749 */     tessellator.addVertexWithUV(0.0D, 0.0D, back2, 0.25D, 0.0D);
/*  629: 750 */     tessellator.addVertexWithUV(width, 0.0D, back, 0.0D, 1.0D);
/*  630: 751 */     tessellator.addVertexWithUV(0.0D, -height, back, 0.25D, 1.0D);
/*  631: 752 */     tessellator.draw();
/*  632:     */     
/*  633: 754 */     tessellator.startDrawingQuads();
/*  634: 755 */     tessellator.addVertexWithUV(0.0D, 0.0D, back2, 0.25D, 0.0D);
/*  635: 756 */     tessellator.addVertexWithUV(0.0D, 0.0D, back2, 0.25D, 0.0D);
/*  636: 757 */     tessellator.addVertexWithUV(-width, 0.0D, back, 0.5D, 1.0D);
/*  637: 758 */     tessellator.addVertexWithUV(0.0D, height, back, 0.25D, 1.0D);
/*  638: 759 */     tessellator.draw();
/*  639:     */     
/*  640: 761 */     tessellator.startDrawingQuads();
/*  641: 762 */     tessellator.addVertexWithUV(0.0D, 0.0D, back2, 0.25D, 0.0D);
/*  642: 763 */     tessellator.addVertexWithUV(0.0D, 0.0D, back2, 0.25D, 0.0D);
/*  643: 764 */     tessellator.addVertexWithUV(0.0D, -height, back, 0.25D, 1.0D);
/*  644: 765 */     tessellator.addVertexWithUV(-width, 0.0D, back, 0.5D, 1.0D);
/*  645: 766 */     tessellator.draw();
/*  646:     */     
/*  647:     */ 
/*  648: 769 */     width = 1.0F;
/*  649: 770 */     flont = 1.4F;
/*  650: 771 */     back = -0.8F;
/*  651: 772 */     back2 = -1.0F;
/*  652: 773 */     height = 0.3F;
/*  653:     */     
/*  654: 775 */     GL11.glEnable(3042);
/*  655: 776 */     GL11.glBlendFunc(1, 769);
/*  656:     */     
/*  657: 778 */     tessellator.startDrawingQuads();
/*  658: 779 */     tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], 1.0F);
/*  659: 780 */     tessellator.addVertexWithUV(-width, 0.0D, back, 0.5D, 1.0D);
/*  660: 781 */     tessellator.addVertexWithUV(0.0D, 0.0D, flont, 0.25D, 0.0D);
/*  661: 782 */     tessellator.addVertexWithUV(width, 0.0D, back, 0.0D, 1.0D);
/*  662: 783 */     tessellator.addVertexWithUV(0.0D, height, back, 0.25D, 1.0D);
/*  663: 784 */     tessellator.draw();
/*  664:     */     
/*  665:     */ 
/*  666: 787 */     tessellator.startDrawingQuads();
/*  667: 788 */     tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], 1.0F);
/*  668: 789 */     tessellator.addVertexWithUV(0.0D, 0.0D, flont, 0.25D, 0.0D);
/*  669: 790 */     tessellator.addVertexWithUV(0.0D, 0.0D, flont, 0.25D, 0.0D);
/*  670: 791 */     tessellator.addVertexWithUV(0.0D, -height, back, 0.25D, 1.0D);
/*  671: 792 */     tessellator.addVertexWithUV(width, 0.0D, back, 0.0D, 1.0D);
/*  672: 793 */     tessellator.draw();
/*  673:     */     
/*  674: 795 */     tessellator.startDrawingQuads();
/*  675: 796 */     tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], 1.0F);
/*  676: 797 */     tessellator.addVertexWithUV(0.0D, 0.0D, flont, 0.25D, 0.0D);
/*  677: 798 */     tessellator.addVertexWithUV(0.0D, 0.0D, flont, 0.25D, 0.0D);
/*  678: 799 */     tessellator.addVertexWithUV(-width, 0.0D, back, 0.5D, 1.0D);
/*  679: 800 */     tessellator.addVertexWithUV(0.0D, -height, back, 0.25D, 1.0D);
/*  680: 801 */     tessellator.draw();
/*  681:     */     
/*  682:     */ 
/*  683: 804 */     tessellator.startDrawingQuads();
/*  684: 805 */     tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], 1.0F);
/*  685: 806 */     tessellator.addVertexWithUV(0.0D, 0.0D, back2, 0.25D, 0.0D);
/*  686: 807 */     tessellator.addVertexWithUV(0.0D, 0.0D, back2, 0.25D, 0.0D);
/*  687: 808 */     tessellator.addVertexWithUV(0.0D, height, back, 0.25D, 1.0D);
/*  688: 809 */     tessellator.addVertexWithUV(width, 0.0D, back, 0.0D, 1.0D);
/*  689: 810 */     tessellator.draw();
/*  690:     */     
/*  691: 812 */     tessellator.startDrawingQuads();
/*  692: 813 */     tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], 1.0F);
/*  693: 814 */     tessellator.addVertexWithUV(0.0D, 0.0D, back2, 0.25D, 0.0D);
/*  694: 815 */     tessellator.addVertexWithUV(0.0D, 0.0D, back2, 0.25D, 0.0D);
/*  695: 816 */     tessellator.addVertexWithUV(width, 0.0D, back, 0.0D, 1.0D);
/*  696: 817 */     tessellator.addVertexWithUV(0.0D, -height, back, 0.25D, 1.0D);
/*  697: 818 */     tessellator.draw();
/*  698:     */     
/*  699: 820 */     tessellator.startDrawingQuads();
/*  700: 821 */     tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], 1.0F);
/*  701: 822 */     tessellator.addVertexWithUV(0.0D, 0.0D, back2, 0.25D, 0.0D);
/*  702: 823 */     tessellator.addVertexWithUV(0.0D, 0.0D, back2, 0.25D, 0.0D);
/*  703: 824 */     tessellator.addVertexWithUV(-width, 0.0D, back, 0.5D, 1.0D);
/*  704: 825 */     tessellator.addVertexWithUV(0.0D, height, back, 0.25D, 1.0D);
/*  705: 826 */     tessellator.draw();
/*  706:     */     
/*  707: 828 */     tessellator.startDrawingQuads();
/*  708: 829 */     tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], 1.0F);
/*  709: 830 */     tessellator.addVertexWithUV(0.0D, 0.0D, back2, 0.25D, 0.0D);
/*  710: 831 */     tessellator.addVertexWithUV(0.0D, 0.0D, back2, 0.25D, 0.0D);
/*  711: 832 */     tessellator.addVertexWithUV(0.0D, -height, back, 0.25D, 1.0D);
/*  712: 833 */     tessellator.addVertexWithUV(-width, 0.0D, back, 0.5D, 1.0D);
/*  713: 834 */     tessellator.draw();
/*  714:     */     
/*  715:     */ 
/*  716: 837 */     GL11.glDisable(2884);
/*  717: 838 */     tessellator.startDrawingQuads();
/*  718: 839 */     tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], 1.0F);
/*  719: 840 */     tessellator.addVertexWithUV(width2, 0.0D, back2, 0.5D, 0.375D);
/*  720: 841 */     tessellator.addVertexWithUV(-width2, 0.0D, back2, 0.75D, 0.375D);
/*  721: 842 */     tessellator.addVertexWithUV(-width2, 0.0D, root, 0.75D, 1.0D);
/*  722: 843 */     tessellator.addVertexWithUV(width2, 0.0D, root, 0.5D, 1.0D);
/*  723: 844 */     tessellator.draw();
/*  724: 845 */     GL11.glEnable(2884);
/*  725:     */     
/*  726: 847 */     GL11.glDisable(3042);
/*  727:     */   }
/*  728:     */   
/*  729:     */   public void renderArrowShot(float size, int color, float yaw, float pitch, float slope)
/*  730:     */   {
/*  731: 853 */     Tessellator tessellator = Tessellator.instance;
/*  732: 854 */     GL11.glScalef(size, size, size);
/*  733: 855 */     GL11.glRotatef(yaw, 0.0F, 1.0F, 0.0F);
/*  734: 856 */     GL11.glRotatef(-pitch, 1.0F, 0.0F, 0.0F);
/*  735: 857 */     GL11.glRotatef(slope, 0.0F, 0.0F, 1.0F);
/*  736:     */     
/*  737: 859 */     float width = 1.0F;
/*  738: 860 */     float width2 = 0.6F;
/*  739: 861 */     float flont = 1.4F;
/*  740: 862 */     float back = -0.8F;
/*  741: 863 */     float back2 = -1.0F;
/*  742: 864 */     float root = -5.8F;
/*  743: 865 */     float height = 0.3F;
/*  744:     */     
/*  745: 867 */     width = 0.8F;
/*  746: 868 */     flont = 1.12F;
/*  747: 869 */     back = -0.8F;
/*  748: 870 */     back2 = -1.0F;
/*  749: 871 */     height = 0.24F;
/*  750:     */     
/*  751: 873 */     tessellator.startDrawingQuads();
/*  752: 874 */     tessellator.addVertexWithUV(-width, 0.0D, back, 0.5D, 1.0D);
/*  753: 875 */     tessellator.addVertexWithUV(0.0D, 0.0D, flont, 0.25D, 0.0D);
/*  754: 876 */     tessellator.addVertexWithUV(width, 0.0D, back, 0.0D, 1.0D);
/*  755: 877 */     tessellator.addVertexWithUV(0.0D, height, back, 0.25D, 1.0D);
/*  756: 878 */     tessellator.draw();
/*  757:     */     
/*  758:     */ 
/*  759: 881 */     tessellator.startDrawingQuads();
/*  760: 882 */     tessellator.addVertexWithUV(0.0D, 0.0D, flont, 0.25D, 0.0D);
/*  761: 883 */     tessellator.addVertexWithUV(0.0D, 0.0D, flont, 0.25D, 0.0D);
/*  762: 884 */     tessellator.addVertexWithUV(0.0D, -height, back, 0.25D, 1.0D);
/*  763: 885 */     tessellator.addVertexWithUV(width, 0.0D, back, 0.0D, 1.0D);
/*  764: 886 */     tessellator.draw();
/*  765:     */     
/*  766: 888 */     tessellator.startDrawingQuads();
/*  767: 889 */     tessellator.addVertexWithUV(0.0D, 0.0D, flont, 0.25D, 0.0D);
/*  768: 890 */     tessellator.addVertexWithUV(0.0D, 0.0D, flont, 0.25D, 0.0D);
/*  769: 891 */     tessellator.addVertexWithUV(-width, 0.0D, back, 0.5D, 1.0D);
/*  770: 892 */     tessellator.addVertexWithUV(0.0D, -height, back, 0.25D, 1.0D);
/*  771: 893 */     tessellator.draw();
/*  772:     */     
/*  773:     */ 
/*  774: 896 */     tessellator.startDrawingQuads();
/*  775: 897 */     tessellator.addVertexWithUV(0.0D, 0.0D, back2, 0.25D, 0.0D);
/*  776: 898 */     tessellator.addVertexWithUV(0.0D, 0.0D, back2, 0.25D, 0.0D);
/*  777: 899 */     tessellator.addVertexWithUV(0.0D, height, back, 0.25D, 1.0D);
/*  778: 900 */     tessellator.addVertexWithUV(width, 0.0D, back, 0.0D, 1.0D);
/*  779: 901 */     tessellator.draw();
/*  780:     */     
/*  781: 903 */     tessellator.startDrawingQuads();
/*  782: 904 */     tessellator.addVertexWithUV(0.0D, 0.0D, back2, 0.25D, 0.0D);
/*  783: 905 */     tessellator.addVertexWithUV(0.0D, 0.0D, back2, 0.25D, 0.0D);
/*  784: 906 */     tessellator.addVertexWithUV(width, 0.0D, back, 0.0D, 1.0D);
/*  785: 907 */     tessellator.addVertexWithUV(0.0D, -height, back, 0.25D, 1.0D);
/*  786: 908 */     tessellator.draw();
/*  787:     */     
/*  788: 910 */     tessellator.startDrawingQuads();
/*  789: 911 */     tessellator.addVertexWithUV(0.0D, 0.0D, back2, 0.25D, 0.0D);
/*  790: 912 */     tessellator.addVertexWithUV(0.0D, 0.0D, back2, 0.25D, 0.0D);
/*  791: 913 */     tessellator.addVertexWithUV(-width, 0.0D, back, 0.5D, 1.0D);
/*  792: 914 */     tessellator.addVertexWithUV(0.0D, height, back, 0.25D, 1.0D);
/*  793: 915 */     tessellator.draw();
/*  794:     */     
/*  795: 917 */     tessellator.startDrawingQuads();
/*  796: 918 */     tessellator.addVertexWithUV(0.0D, 0.0D, back2, 0.25D, 0.0D);
/*  797: 919 */     tessellator.addVertexWithUV(0.0D, 0.0D, back2, 0.25D, 0.0D);
/*  798: 920 */     tessellator.addVertexWithUV(0.0D, -height, back, 0.25D, 1.0D);
/*  799: 921 */     tessellator.addVertexWithUV(-width, 0.0D, back, 0.5D, 1.0D);
/*  800: 922 */     tessellator.draw();
/*  801:     */     
/*  802: 924 */     width = 1.0F;
/*  803: 925 */     flont = 1.4F;
/*  804: 926 */     back = -0.8F;
/*  805: 927 */     back2 = -1.0F;
/*  806: 928 */     height = 0.3F;
/*  807:     */     
/*  808: 930 */     GL11.glEnable(3042);
/*  809: 931 */     GL11.glBlendFunc(1, 769);
/*  810:     */     
/*  811: 933 */     tessellator.startDrawingQuads();
/*  812: 934 */     tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], 1.0F);
/*  813: 935 */     tessellator.addVertexWithUV(-width, 0.0D, back, 0.5D, 1.0D);
/*  814: 936 */     tessellator.addVertexWithUV(0.0D, 0.0D, flont, 0.25D, 0.0D);
/*  815: 937 */     tessellator.addVertexWithUV(width, 0.0D, back, 0.0D, 1.0D);
/*  816: 938 */     tessellator.addVertexWithUV(0.0D, height, back, 0.25D, 1.0D);
/*  817: 939 */     tessellator.draw();
/*  818:     */     
/*  819:     */ 
/*  820: 942 */     tessellator.startDrawingQuads();
/*  821: 943 */     tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], 1.0F);
/*  822: 944 */     tessellator.addVertexWithUV(0.0D, 0.0D, flont, 0.25D, 0.0D);
/*  823: 945 */     tessellator.addVertexWithUV(0.0D, 0.0D, flont, 0.25D, 0.0D);
/*  824: 946 */     tessellator.addVertexWithUV(0.0D, -height, back, 0.25D, 1.0D);
/*  825: 947 */     tessellator.addVertexWithUV(width, 0.0D, back, 0.0D, 1.0D);
/*  826: 948 */     tessellator.draw();
/*  827:     */     
/*  828: 950 */     tessellator.startDrawingQuads();
/*  829: 951 */     tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], 1.0F);
/*  830: 952 */     tessellator.addVertexWithUV(0.0D, 0.0D, flont, 0.25D, 0.0D);
/*  831: 953 */     tessellator.addVertexWithUV(0.0D, 0.0D, flont, 0.25D, 0.0D);
/*  832: 954 */     tessellator.addVertexWithUV(-width, 0.0D, back, 0.5D, 1.0D);
/*  833: 955 */     tessellator.addVertexWithUV(0.0D, -height, back, 0.25D, 1.0D);
/*  834: 956 */     tessellator.draw();
/*  835:     */     
/*  836:     */ 
/*  837: 959 */     tessellator.startDrawingQuads();
/*  838: 960 */     tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], 1.0F);
/*  839: 961 */     tessellator.addVertexWithUV(0.0D, 0.0D, back2, 0.25D, 0.0D);
/*  840: 962 */     tessellator.addVertexWithUV(0.0D, 0.0D, back2, 0.25D, 0.0D);
/*  841: 963 */     tessellator.addVertexWithUV(0.0D, height, back, 0.25D, 1.0D);
/*  842: 964 */     tessellator.addVertexWithUV(width, 0.0D, back, 0.0D, 1.0D);
/*  843: 965 */     tessellator.draw();
/*  844:     */     
/*  845: 967 */     tessellator.startDrawingQuads();
/*  846: 968 */     tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], 1.0F);
/*  847: 969 */     tessellator.addVertexWithUV(0.0D, 0.0D, back2, 0.25D, 0.0D);
/*  848: 970 */     tessellator.addVertexWithUV(0.0D, 0.0D, back2, 0.25D, 0.0D);
/*  849: 971 */     tessellator.addVertexWithUV(width, 0.0D, back, 0.0D, 1.0D);
/*  850: 972 */     tessellator.addVertexWithUV(0.0D, -height, back, 0.25D, 1.0D);
/*  851: 973 */     tessellator.draw();
/*  852:     */     
/*  853: 975 */     tessellator.startDrawingQuads();
/*  854: 976 */     tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], 1.0F);
/*  855: 977 */     tessellator.addVertexWithUV(0.0D, 0.0D, back2, 0.25D, 0.0D);
/*  856: 978 */     tessellator.addVertexWithUV(0.0D, 0.0D, back2, 0.25D, 0.0D);
/*  857: 979 */     tessellator.addVertexWithUV(-width, 0.0D, back, 0.5D, 1.0D);
/*  858: 980 */     tessellator.addVertexWithUV(0.0D, height, back, 0.25D, 1.0D);
/*  859: 981 */     tessellator.draw();
/*  860:     */     
/*  861: 983 */     tessellator.startDrawingQuads();
/*  862: 984 */     tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], 1.0F);
/*  863: 985 */     tessellator.addVertexWithUV(0.0D, 0.0D, back2, 0.25D, 0.0D);
/*  864: 986 */     tessellator.addVertexWithUV(0.0D, 0.0D, back2, 0.25D, 0.0D);
/*  865: 987 */     tessellator.addVertexWithUV(0.0D, -height, back, 0.25D, 1.0D);
/*  866: 988 */     tessellator.addVertexWithUV(-width, 0.0D, back, 0.5D, 1.0D);
/*  867: 989 */     tessellator.draw();
/*  868:     */     
/*  869:     */ 
/*  870:     */ 
/*  871:     */ 
/*  872:     */ 
/*  873:     */ 
/*  874: 996 */     GL11.glDisable(2884);
/*  875: 997 */     tessellator.startDrawingQuads();
/*  876: 998 */     tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], 1.0F);
/*  877: 999 */     tessellator.addVertexWithUV(width2, 0.0D, back2, 0.5D, 0.0D);
/*  878:1000 */     tessellator.addVertexWithUV(-width2, 0.0D, back2, 0.75D, 0.0D);
/*  879:1001 */     tessellator.addVertexWithUV(-width2, 0.0D, root, 0.75D, 1.0D);
/*  880:1002 */     tessellator.addVertexWithUV(width2, 0.0D, root, 0.5D, 1.0D);
/*  881:1003 */     tessellator.draw();
/*  882:1004 */     GL11.glEnable(2884);
/*  883:     */     
/*  884:1006 */     GL11.glDisable(3042);
/*  885:     */   }
/*  886:     */   
/*  887:     */   public void renderTalismanShot(float size, int color, float yaw, float pitch, float slope)
/*  888:     */   {
/*  889:1012 */     Tessellator tessellator = Tessellator.instance;
/*  890:1013 */     GL11.glScalef(size, size, size);
/*  891:1014 */     GL11.glRotatef(yaw, 0.0F, 1.0F, 0.0F);
/*  892:1015 */     GL11.glRotatef(-pitch, 1.0F, 0.0F, 0.0F);
/*  893:1016 */     GL11.glRotatef(slope, 0.0F, 0.0F, 1.0F);
/*  894:     */     
/*  895:1018 */     float u1 = color / 8.0F;
/*  896:1019 */     float u2 = (color + 1) / 8.0F;
/*  897:1020 */     float v1 = 0.0F;
/*  898:1021 */     float v2 = 1.0F;
/*  899:     */     
/*  900:1023 */     float width = 1.6F;
/*  901:1024 */     double length = 2.0D;
/*  902:     */     
/*  903:1026 */     GL11.glDisable(2884);
/*  904:1027 */     tessellator.startDrawingQuads();
/*  905:1028 */     tessellator.addVertexWithUV(width, 0.0D, length, u2, v1);
/*  906:1029 */     tessellator.addVertexWithUV(-width, 0.0D, length, u1, v1);
/*  907:1030 */     tessellator.addVertexWithUV(-width, 0.0D, -length, u1, v2);
/*  908:1031 */     tessellator.addVertexWithUV(width, 0.0D, -length, u2, v2);
/*  909:1032 */     tessellator.draw();
/*  910:1033 */     GL11.glEnable(2884);
/*  911:     */   }
/*  912:     */   
/*  913:     */   public void renderWindShot(float size, int color, int count)
/*  914:     */   {
/*  915:1039 */     Tessellator tessellator = Tessellator.instance;
/*  916:1040 */     GL11.glEnable(3042);
/*  917:     */     
/*  918:1042 */     GL11.glBlendFunc(1, 769);
/*  919:     */     
/*  920:1044 */     float size2 = size * 0.5F;
/*  921:1045 */     GL11.glScalef(size2, size2, size2);
/*  922:     */     
/*  923:1047 */     Random random = new Random();
/*  924:1048 */     float rand1 = random.nextInt(50) / 100.0F;
/*  925:1049 */     float rand2 = random.nextInt(100) / 100.0F + 4.0F;
/*  926:1050 */     int pattern = count % 4;
/*  927:1051 */     float u1 = (pattern % 2 * 32 + 0) / 64.0F;
/*  928:1052 */     float u2 = (pattern % 2 * 32 + 32) / 64.0F;
/*  929:1053 */     float v1 = (pattern / 2 * 16 + 0) / 32.0F;
/*  930:1054 */     float v2 = (pattern / 2 * 16 + 16) / 32.0F;
/*  931:1055 */     float f9 = 0.5F;
/*  932:     */     
/*  933:1057 */     GL11.glDisable(2884);
/*  934:1058 */     for (int i = 0; i < 8; i++)
/*  935:     */     {
/*  936:1060 */       GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*  937:1061 */       tessellator.startDrawingQuads();
/*  938:1062 */       tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  939:1063 */       tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], 0.7F);
/*  940:1064 */       tessellator.addVertexWithUV(0.0F - rand1, -1.0F - f9, 0.0D, u1, v2);
/*  941:1065 */       tessellator.addVertexWithUV(0.0F + rand1, -1.0F - f9, 0.0D, u2, v2);
/*  942:1066 */       tessellator.addVertexWithUV(0.0F + rand2, 2.0F - f9, 2.0D, u2, v1);
/*  943:1067 */       tessellator.addVertexWithUV(0.0F - rand2, 2.0F - f9, 2.0D, u1, v1);
/*  944:1068 */       tessellator.draw();
/*  945:     */     }
/*  946:1071 */     GL11.glEnable(2884);
/*  947:1072 */     GL11.glDisable(3042);
/*  948:     */   }
/*  949:     */   
/*  950:     */   protected void renderRiceShot(float size, float yaw, float pitch, double length, float width, double zPos, int zAngleDivNum, int zDivNum, int color, float alpha)
/*  951:     */   {
/*  952:1109 */     GL11.glRotatef(yaw, 0.0F, 1.0F, 0.0F);
/*  953:1110 */     GL11.glRotatef(-pitch, 1.0F, 0.0F, 0.0F);
/*  954:     */     
/*  955:1112 */     GL11.glScalef(size, size, size);
/*  956:     */     
/*  957:1114 */     renderRiceShotPart(size * 0.8F, yaw, pitch, length * 0.8D, width * 0.8F, zPos + length * 0.1D, zAngleDivNum, zDivNum, 7, 1.0F);
/*  958:1115 */     GL11.glEnable(3042);
/*  959:1116 */     GL11.glBlendFunc(770, 1);
/*  960:1117 */     GL11.glDepthMask(false);
/*  961:1118 */     renderRiceShotPart(size, yaw, pitch, length, width, zPos, zAngleDivNum, zDivNum, color, alpha);
/*  962:1119 */     GL11.glDepthMask(true);
/*  963:1120 */     GL11.glDisable(3042);
/*  964:     */   }
/*  965:     */   
/*  966:     */   private void renderRiceShotPart(float size, float yaw, float pitch, double length, float width, double zPos, int zAngleDivNum, int zDivNum, int color, float alpha)
/*  967:     */   {
/*  968:1128 */     Tessellator tessellator = Tessellator.instance;
/*  969:1129 */     float maxWidth = width;
/*  970:     */     
/*  971:1131 */     double angleZ = 0.0D;
/*  972:1132 */     double angleSpanZ = 6.283185307179586D / zAngleDivNum;
/*  973:     */     
/*  974:     */ 
/*  975:1135 */     double zLength = length;
/*  976:1136 */     double zDivLength = zLength / (zDivNum - 1);
/*  977:     */     
/*  978:     */ 
/*  979:     */ 
/*  980:1140 */     double zPosOld = zPos;
/*  981:     */     
/*  982:1142 */     float xPos = 0.0F;
/*  983:1143 */     float yPos = 0.0F;
/*  984:1144 */     float xPos2 = 0.0F;
/*  985:1145 */     float yPos2 = 0.0F;
/*  986:     */     
/*  987:1147 */     float xPosOld = xPos;
/*  988:1148 */     float yPosOld = yPos;
/*  989:1149 */     float xPos2Old = xPos2;
/*  990:1150 */     float yPos2Old = yPos2;
/*  991:     */     
/*  992:1152 */     float angle = -1.570796F;
/*  993:1153 */     float angleSpan = 3.141593F / zDivNum;
/*  994:1154 */     angle += angleSpan;
/*  995:     */     
/*  996:     */ 
/*  997:1157 */     float widthOld = 0.0F;
/*  998:1161 */     for (int j = 0; j < zDivNum; j++)
/*  999:     */     {
/* 1000:1163 */       zPos += zDivLength;
/* 1001:     */       
/* 1002:     */ 
/* 1003:1166 */       width = (float)Math.cos(angle) * maxWidth;
/* 1004:     */       
/* 1005:1168 */       xPos = width;
/* 1006:1169 */       yPos = 0.0F;
/* 1007:1170 */       xPosOld = (float)Math.cos(angleZ) * width;
/* 1008:1171 */       yPosOld = (float)Math.sin(angleZ) * width;
/* 1009:1172 */       xPos2Old = (float)Math.cos(angleZ) * widthOld;
/* 1010:1173 */       yPos2Old = (float)Math.sin(angleZ) * widthOld;
/* 1011:     */       
/* 1012:1175 */       angleZ = angleSpanZ;
/* 1013:1178 */       for (int i = 0; i <= zAngleDivNum; i++)
/* 1014:     */       {
/* 1015:1180 */         xPos = (float)Math.cos(angleZ) * width;
/* 1016:1181 */         yPos = (float)Math.sin(angleZ) * width;
/* 1017:1182 */         xPos2 = (float)Math.cos(angleZ) * widthOld;
/* 1018:1183 */         yPos2 = (float)Math.sin(angleZ) * widthOld;
/* 1019:     */         
/* 1020:1185 */         tessellator.startDrawingQuads();
/* 1021:1186 */         tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], alpha);
/* 1022:1187 */         tessellator.addVertexWithUV(xPosOld, yPosOld, zPos, 0.0D, 0.0D);
/* 1023:1188 */         tessellator.addVertexWithUV(xPos2Old, yPos2Old, zPosOld, 0.0D, 1.0D);
/* 1024:1189 */         tessellator.addVertexWithUV(xPos2, yPos2, zPosOld, 1.0D, 1.0D);
/* 1025:1190 */         tessellator.addVertexWithUV(xPos, yPos, zPos, 1.0D, 0.0D);
/* 1026:1191 */         tessellator.draw();
/* 1027:     */         
/* 1028:1193 */         xPosOld = xPos;
/* 1029:1194 */         yPosOld = yPos;
/* 1030:1195 */         xPos2Old = xPos2;
/* 1031:1196 */         yPos2Old = yPos2;
/* 1032:1197 */         angleZ += angleSpanZ;
/* 1033:     */       }
/* 1034:1202 */       zPosOld = zPos;
/* 1035:1203 */       angle += angleSpan;
/* 1036:1204 */       widthOld = width;
/* 1037:     */     }
/* 1038:     */   }
/* 1039:     */   
/* 1040:     */   protected void renderOvalShot(float size, float yaw, float pitch, double length, float width, double zPos, int zAngleDivNum, int zDivNum, int color, float alpha)
/* 1041:     */   {
/* 1042:1212 */     GL11.glScalef(size, size, size);
/* 1043:1213 */     GL11.glRotatef(yaw, 0.0F, 1.0F, 0.0F);
/* 1044:1214 */     GL11.glRotatef(-pitch, 1.0F, 0.0F, 0.0F);
/* 1045:     */     
/* 1046:     */ 
/* 1047:1217 */     renderOvalShotPart(size, yaw, pitch, length * 0.8D, width * 0.8F, zPos + length * 0.1D, zAngleDivNum, zDivNum, 7, 1.0F);
/* 1048:     */     
/* 1049:1219 */     GL11.glEnable(3042);
/* 1050:1220 */     GL11.glBlendFunc(770, 1);
/* 1051:1221 */     GL11.glDepthMask(false);
/* 1052:1222 */     renderOvalShotPart(size, yaw, pitch, length, width, zPos, zAngleDivNum, zDivNum, color, alpha);
/* 1053:1223 */     GL11.glDepthMask(true);
/* 1054:1224 */     GL11.glDisable(3042);
/* 1055:     */   }
/* 1056:     */   
/* 1057:     */   private void renderOvalShotPart(float size, float yaw, float pitch, double length, float width, double zOffset, int zAngleDivNum, int zDivNum, int color, float alpha)
/* 1058:     */   {
/* 1059:1230 */     Tessellator tessellator = Tessellator.instance;
/* 1060:1231 */     float maxWidth = width;
/* 1061:     */     
/* 1062:1233 */     double angleZ = 0.0D;
/* 1063:1234 */     double angleSpanZ = 6.283185307179586D / zAngleDivNum;
/* 1064:     */     
/* 1065:     */ 
/* 1066:     */ 
/* 1067:     */ 
/* 1068:     */ 
/* 1069:1240 */     double zPosOld = zOffset - length;
/* 1070:     */     
/* 1071:1242 */     float xPos = 0.0F;
/* 1072:1243 */     float yPos = 0.0F;
/* 1073:1244 */     float xPos2 = 0.0F;
/* 1074:1245 */     float yPos2 = 0.0F;
/* 1075:     */     
/* 1076:1247 */     float xPosOld = xPos;
/* 1077:1248 */     float yPosOld = yPos;
/* 1078:1249 */     float xPos2Old = xPos2;
/* 1079:1250 */     float yPos2Old = yPos2;
/* 1080:     */     
/* 1081:1252 */     float angle = -1.570796F;
/* 1082:1253 */     float angleSpan = 3.141593F / zDivNum;
/* 1083:1254 */     angle += angleSpan;
/* 1084:     */     
/* 1085:     */ 
/* 1086:1257 */     float widthOld = 0.0F;
/* 1087:1261 */     for (int j = 0; j < zDivNum; j++)
/* 1088:     */     {
/* 1089:1264 */       double zPos = zOffset + Math.sin(angle) * length;
/* 1090:     */       
/* 1091:     */ 
/* 1092:1267 */       width = (float)Math.cos(angle) * maxWidth;
/* 1093:     */       
/* 1094:1269 */       xPos = width;
/* 1095:1270 */       yPos = 0.0F;
/* 1096:1271 */       xPosOld = (float)Math.cos(angleZ) * width;
/* 1097:1272 */       yPosOld = (float)Math.sin(angleZ) * width;
/* 1098:1273 */       xPos2Old = (float)Math.cos(angleZ) * widthOld;
/* 1099:1274 */       yPos2Old = (float)Math.sin(angleZ) * widthOld;
/* 1100:     */       
/* 1101:1276 */       angleZ = angleSpanZ;
/* 1102:1279 */       for (int i = 0; i <= zAngleDivNum; i++)
/* 1103:     */       {
/* 1104:1281 */         xPos = (float)Math.cos(angleZ) * width;
/* 1105:1282 */         yPos = (float)Math.sin(angleZ) * width;
/* 1106:1283 */         xPos2 = (float)Math.cos(angleZ) * widthOld;
/* 1107:1284 */         yPos2 = (float)Math.sin(angleZ) * widthOld;
/* 1108:     */         
/* 1109:1286 */         tessellator.startDrawingQuads();
/* 1110:1287 */         tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], alpha);
/* 1111:1288 */         tessellator.addVertexWithUV(xPosOld, yPosOld, zPos, 0.0D, 0.0D);
/* 1112:1289 */         tessellator.addVertexWithUV(xPos2Old, yPos2Old, zPosOld, 0.0D, 1.0D);
/* 1113:1290 */         tessellator.addVertexWithUV(xPos2, yPos2, zPosOld, 1.0D, 1.0D);
/* 1114:1291 */         tessellator.addVertexWithUV(xPos, yPos, zPos, 1.0D, 0.0D);
/* 1115:1292 */         tessellator.draw();
/* 1116:     */         
/* 1117:1294 */         xPosOld = xPos;
/* 1118:1295 */         yPosOld = yPos;
/* 1119:1296 */         xPos2Old = xPos2;
/* 1120:1297 */         yPos2Old = yPos2;
/* 1121:1298 */         angleZ += angleSpanZ;
/* 1122:     */       }
/* 1123:1303 */       zPosOld = zPos;
/* 1124:1304 */       angle += angleSpan;
/* 1125:1305 */       widthOld = width;
/* 1126:     */     }
/* 1127:     */   }
/* 1128:     */   
/* 1129:     */   private void renderHarfOvalShotPart(float size, float yaw, float pitch, double length, float width, double zPos, int zAngleDivNum, int zDivNum, int color, float alpha)
/* 1130:     */   {
/* 1131:1313 */     Tessellator tessellator = Tessellator.instance;
/* 1132:1314 */     float maxWidth = width;
/* 1133:     */     
/* 1134:1316 */     double angleZ = 0.0D;
/* 1135:1317 */     double angleSpanZ = 6.283185307179586D / zAngleDivNum;
/* 1136:     */     
/* 1137:     */ 
/* 1138:     */ 
/* 1139:     */ 
/* 1140:1322 */     double zPosOld = -length;
/* 1141:     */     
/* 1142:1324 */     float xPos = 0.0F;
/* 1143:1325 */     float yPos = 0.0F;
/* 1144:1326 */     float xPos2 = 0.0F;
/* 1145:1327 */     float yPos2 = 0.0F;
/* 1146:     */     
/* 1147:1329 */     float xPosOld = xPos;
/* 1148:1330 */     float yPosOld = yPos;
/* 1149:1331 */     float xPos2Old = xPos2;
/* 1150:1332 */     float yPos2Old = yPos2;
/* 1151:     */     
/* 1152:1334 */     float angle = -1.570796F;
/* 1153:1335 */     float angleSpan = 3.141593F / zDivNum;
/* 1154:1336 */     angle += angleSpan;
/* 1155:     */     
/* 1156:     */ 
/* 1157:1339 */     float widthOld = 0.0F;
/* 1158:     */     
/* 1159:1341 */     zDivNum = zDivNum / 2 + 1;
/* 1160:     */     
/* 1161:1343 */     GL11.glDisable(2884);
/* 1162:1347 */     for (int j = 0; j < zDivNum; j++)
/* 1163:     */     {
/* 1164:1350 */       zPos = Math.sin(angle) * length;
/* 1165:     */       
/* 1166:     */ 
/* 1167:1353 */       width = (float)Math.cos(angle) * maxWidth;
/* 1168:     */       
/* 1169:1355 */       xPos = width;
/* 1170:1356 */       yPos = 0.0F;
/* 1171:1357 */       xPosOld = (float)Math.cos(angleZ) * width;
/* 1172:1358 */       yPosOld = (float)Math.sin(angleZ) * width;
/* 1173:1359 */       xPos2Old = (float)Math.cos(angleZ) * widthOld;
/* 1174:1360 */       yPos2Old = (float)Math.sin(angleZ) * widthOld;
/* 1175:     */       
/* 1176:1362 */       angleZ = angleSpanZ;
/* 1177:1365 */       for (int i = 0; i <= zAngleDivNum; i++)
/* 1178:     */       {
/* 1179:1367 */         xPos = (float)Math.cos(angleZ) * width;
/* 1180:1368 */         yPos = (float)Math.sin(angleZ) * width;
/* 1181:1369 */         xPos2 = (float)Math.cos(angleZ) * widthOld;
/* 1182:1370 */         yPos2 = (float)Math.sin(angleZ) * widthOld;
/* 1183:     */         
/* 1184:1372 */         tessellator.startDrawingQuads();
/* 1185:1373 */         tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], alpha);
/* 1186:1374 */         tessellator.addVertexWithUV(xPosOld, yPosOld, zPos, 0.0D, 0.0D);
/* 1187:1375 */         tessellator.addVertexWithUV(xPos2Old, yPos2Old, zPosOld, 0.0D, 1.0D);
/* 1188:1376 */         tessellator.addVertexWithUV(xPos2, yPos2, zPosOld, 1.0D, 1.0D);
/* 1189:1377 */         tessellator.addVertexWithUV(xPos, yPos, zPos, 1.0D, 0.0D);
/* 1190:1378 */         tessellator.draw();
/* 1191:     */         
/* 1192:1380 */         xPosOld = xPos;
/* 1193:1381 */         yPosOld = yPos;
/* 1194:1382 */         xPos2Old = xPos2;
/* 1195:1383 */         yPos2Old = yPos2;
/* 1196:1384 */         angleZ += angleSpanZ;
/* 1197:     */       }
/* 1198:1389 */       alpha -= j * 0.05F;
/* 1199:     */       
/* 1200:1391 */       zPosOld = zPos;
/* 1201:1392 */       angle += angleSpan;
/* 1202:1393 */       widthOld = width;
/* 1203:     */     }
/* 1204:1397 */     GL11.glEnable(2884);
/* 1205:     */   }
/* 1206:     */   
/* 1207:     */   protected void renderAmuletShot(float size, int color, int count, float yaw, float pitch, float slope)
/* 1208:     */   {
/* 1209:1403 */     Tessellator tessellator = Tessellator.instance;
/* 1210:1404 */     GL11.glEnable(3042);
/* 1211:1405 */     GL11.glBlendFunc(1, 769);
/* 1212:1406 */     GL11.glDepthMask(false);
/* 1213:1407 */     GL11.glScalef(size, size, size);
/* 1214:1408 */     GL11.glRotatef(yaw, 0.0F, 1.0F, 0.0F);
/* 1215:1409 */     GL11.glRotatef(-pitch, 1.0F, 0.0F, 0.0F);
/* 1216:1410 */     GL11.glRotatef(slope, 0.0F, 0.0F, 1.0F);
/* 1217:     */     
/* 1218:1412 */     float xLength = 1.0F;
/* 1219:1413 */     double zLength = 1.0D;
/* 1220:1414 */     float uMin = 0.0F;
/* 1221:1415 */     float uMax = 0.5F;
/* 1222:1416 */     float vMin = 0.0F;
/* 1223:1417 */     float vMax = 1.0F;
/* 1224:     */     
/* 1225:1419 */     GL11.glRotatef(180.0F - count * 23.0F, 0.0F, 1.0F, 0.0F);
/* 1226:1420 */     GL11.glDisable(2884);
/* 1227:1421 */     tessellator.startDrawingQuads();
/* 1228:     */     
/* 1229:1423 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 1230:1424 */     tessellator.addVertexWithUV(-xLength, 0.0D, zLength, uMin, vMin);
/* 1231:1425 */     tessellator.addVertexWithUV(xLength, 0.0D, zLength, uMax, vMin);
/* 1232:1426 */     tessellator.addVertexWithUV(xLength, 0.0D, -zLength, uMax, vMax);
/* 1233:1427 */     tessellator.addVertexWithUV(-xLength, 0.0D, -zLength, uMin, vMax);
/* 1234:1428 */     tessellator.draw();
/* 1235:     */     
/* 1236:     */ 
/* 1237:1431 */     xLength = 1.2F;
/* 1238:1432 */     zLength = 1.200000047683716D;
/* 1239:1433 */     uMin = 0.5F;
/* 1240:1434 */     uMax = 1.0F;
/* 1241:1435 */     tessellator.startDrawingQuads();
/* 1242:1436 */     tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], 0.6F);
/* 1243:1437 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 1244:1438 */     tessellator.addVertexWithUV(-xLength, 0.0D, zLength, uMin, vMin);
/* 1245:1439 */     tessellator.addVertexWithUV(xLength, 0.0D, zLength, uMax, vMin);
/* 1246:1440 */     tessellator.addVertexWithUV(xLength, 0.0D, -zLength, uMax, vMax);
/* 1247:1441 */     tessellator.addVertexWithUV(-xLength, 0.0D, -zLength, uMin, vMax);
/* 1248:1442 */     tessellator.draw();
/* 1249:1443 */     GL11.glDepthMask(true);
/* 1250:1444 */     GL11.glEnable(2884);
/* 1251:1445 */     GL11.glDisable(3042);
/* 1252:     */   }
/* 1253:     */   
/* 1254:     */   private void renderPhantomPart(int color, double time_r, int damage)
/* 1255:     */   {
/* 1256:1450 */     Tessellator tessellator = Tessellator.instance;
/* 1257:1451 */     int pattern = 0;
/* 1258:1452 */     float umin = (pattern % 32 * 32 + 0) / 64.0F;
/* 1259:1453 */     float umax = (pattern % 32 * 32 + 32) / 64.0F;
/* 1260:1454 */     float vmin = 0.0F;
/* 1261:1455 */     float vmax = 1.0F;
/* 1262:     */     
/* 1263:1457 */     float alpha = (40.0F - damage) / 40.0F;
/* 1264:     */     
/* 1265:1459 */     tessellator.startDrawingQuads();
/* 1266:     */     
/* 1267:1461 */     tessellator.setColorRGBA_F(this.colorR[color] * 0.3F, this.colorG[color] * 0.3F, this.colorB[color] * 0.3F, 0.3F * alpha);
/* 1268:1462 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 1269:1463 */     tessellator.addVertexWithUV(-0.699999988079071D + Math.sin(time_r * 3.0D) * 0.1000000014901161D, -0.2000000029802322D - Math.cos(time_r * 7.0D) * 0.1000000014901161D, 0.0D, umin, vmax);
/* 1270:1464 */     tessellator.addVertexWithUV(0.699999988079071D - Math.cos(time_r * 4.0D) * 0.1000000014901161D, -0.2000000029802322D - Math.sin(time_r * 5.0D) * 0.1000000014901161D, 0.0D, umax, vmax);
/* 1271:1465 */     tessellator.addVertexWithUV(0.699999988079071D + Math.sin(time_r * 5.0D) * 0.1000000014901161D, 1.200000047683716D + Math.cos(time_r * 4.0D) * 0.1000000014901161D, 0.0D, umax, vmin);
/* 1272:1466 */     tessellator.addVertexWithUV(-0.699999988079071D - Math.cos(time_r * 7.0D) * 0.1000000014901161D, 1.200000047683716D + Math.sin(time_r * 3.0D) * 0.1000000014901161D, 0.0D, umin, vmin);
/* 1273:     */     
/* 1274:     */ 
/* 1275:1469 */     tessellator.draw();
/* 1276:1470 */     tessellator.startDrawingQuads();
/* 1277:     */     
/* 1278:1472 */     tessellator.setColorRGBA_F(this.colorR[color] * 0.3F, this.colorG[color] * 0.3F, this.colorB[color] * 0.3F, 0.7F * alpha);
/* 1279:1473 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 1280:1474 */     tessellator.addVertexWithUV(-0.6000000238418579D - Math.cos(time_r * 7.0D) * 0.1000000014901161D, -0.1000000014901161D + Math.sin(time_r * 5.0D) * 0.1000000014901161D, 0.001D, umin, vmax);
/* 1281:1475 */     tessellator.addVertexWithUV(0.6000000238418579D + Math.sin(time_r * 3.0D) * 0.1000000014901161D, -0.1000000014901161D + Math.cos(time_r * 4.0D) * 0.1000000014901161D, 0.001D, umax, vmax);
/* 1282:1476 */     tessellator.addVertexWithUV(0.6000000238418579D - Math.cos(time_r * 4.0D) * 0.1000000014901161D, 1.100000023841858D - Math.sin(time_r * 3.0D) * 0.1000000014901161D, 0.001D, umax, vmin);
/* 1283:1477 */     tessellator.addVertexWithUV(-0.6000000238418579D + Math.sin(time_r * 5.0D) * 0.1000000014901161D, 1.100000023841858D - Math.cos(time_r * 7.0D) * 0.1000000014901161D, 0.001D, umin, vmin);
/* 1284:1478 */     tessellator.draw();
/* 1285:1482 */     for (int i = 0; i < 3; i++)
/* 1286:     */     {
/* 1287:1484 */       tessellator.startDrawingQuads();
/* 1288:1485 */       if (damage > 0) {
/* 1289:1487 */         tessellator.setColorRGBA_F(1.0F, 0.0F, 0.0F, alpha);
/* 1290:     */       }
/* 1291:1489 */       tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 1292:1490 */       tessellator.addVertexWithUV(-0.5D, 0.0D, 0.002D, umin, vmax);
/* 1293:1491 */       tessellator.addVertexWithUV(0.5D, 0.0D, 0.002D, umax, vmax);
/* 1294:1492 */       tessellator.addVertexWithUV(0.5D, 1.0D, 0.002D, umax, vmin);
/* 1295:1493 */       tessellator.addVertexWithUV(-0.5D, 1.0D, 0.002D, umin, vmin);
/* 1296:1494 */       tessellator.draw();
/* 1297:     */     }
/* 1298:     */   }
/* 1299:     */   
/* 1300:     */   protected ResourceLocation getEntityTexture(Entity entity)
/* 1301:     */   {
/* 1302:1501 */     return getEntityTexture((EntityTHShot)entity);
/* 1303:     */   }
/* 1304:     */   
/* 1305:     */   protected ResourceLocation getEntityTexture(EntityTHShot thShot)
/* 1306:     */   {
/* 1307:1506 */     int color = thShot.getShotColor();
/* 1308:1507 */     int form = thShot.getShotForm();
/* 1309:1509 */     if (thShot.getAnimationCount() < 0) {
/* 1310:1511 */       return resourceLocation_Light;
/* 1311:     */     }
/* 1312:1515 */     switch (form)
/* 1313:     */     {
/* 1314:     */     case 0: 
/* 1315:1518 */       return resourceLocation_Small;
/* 1316:     */     case 1: 
/* 1317:1520 */       return resourceLocation_Small;
/* 1318:     */     case 2: 
/* 1319:1522 */       return resourceLocation_Small;
/* 1320:     */     case 3: 
/* 1321:1524 */       return resourceLocation_Pearl;
/* 1322:     */     case 4: 
/* 1323:1526 */       return resourceLocation_Circle;
/* 1324:     */     case 6: 
/* 1325:1529 */       return resourceLocation_Laser;
/* 1326:     */     case 7: 
/* 1327:1531 */       return resourceLocation_Butterfly;
/* 1328:     */     case 8: 
/* 1329:1533 */       return resourceLocation_Star;
/* 1330:     */     case 9: 
/* 1331:1535 */       return resourceLocation_Star;
/* 1332:     */     case 10: 
/* 1333:1537 */       return resourceLocation_Laser;
/* 1334:     */     case 11: 
/* 1335:1539 */       return resourceLocation_Star;
/* 1336:     */     case 5: 
/* 1337:1541 */       return resourceLocation_Light;
/* 1338:     */     case 12: 
/* 1339:1543 */       return resourceLocation_Heart;
/* 1340:     */     case 13: 
/* 1341:1545 */       return resourceLocation_Kunai;
/* 1342:     */     case 14: 
/* 1343:1547 */       return resourceLocation_Talisman;
/* 1344:     */     case 15: 
/* 1345:1549 */       return resourceLocation_Light;
/* 1346:     */     case 16: 
/* 1347:1551 */       return resourceLocation_Laser;
/* 1348:     */     case 17: 
/* 1349:1553 */       return resourceLocation_Familiar;
/* 1350:     */     case 18: 
/* 1351:1555 */       return resourceLocation_Arrow;
/* 1352:     */     case 26: 
/* 1353:1557 */       return resourceLocation_Phantom;
/* 1354:     */     case 27: 
/* 1355:1559 */       switch (color)
/* 1356:     */       {
/* 1357:     */       case 1: 
/* 1358:1562 */         return resourceLocation_Diffusion;
/* 1359:     */       }
/* 1360:1564 */       return resourceLocation_Amulet;
/* 1361:     */     case 28: 
/* 1362:1567 */       switch (color)
/* 1363:     */       {
/* 1364:     */       case 0: 
/* 1365:1570 */         return resourceLocation_Knife_Red;
/* 1366:     */       case 1: 
/* 1367:1572 */         return resourceLocation_Knife_Blue;
/* 1368:     */       case 2: 
/* 1369:1574 */         return resourceLocation_Knife_Green;
/* 1370:     */       case 3: 
/* 1371:1576 */         return resourceLocation_Knife_Yellow;
/* 1372:     */       case 4: 
/* 1373:1578 */         return resourceLocation_Knife_Purple;
/* 1374:     */       case 5: 
/* 1375:1580 */         return resourceLocation_Knife_Aqua;
/* 1376:     */       case 6: 
/* 1377:1582 */         return resourceLocation_Knife_Orange;
/* 1378:     */       }
/* 1379:1584 */       return resourceLocation_Knife_White;
/* 1380:     */     case 29: 
/* 1381:1587 */       return resourceLocation_Wind;
/* 1382:     */     case 30: 
/* 1383:1589 */       return resourceLocation_Big;
/* 1384:     */     case 31: 
/* 1385:1591 */       return resourceLocation_Light;
/* 1386:     */     }
/* 1387:1593 */     return resourceLocation_Light;
/* 1388:     */   }
/* 1389:     */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.shot.RenderTHShot
 * JD-Core Version:    0.7.0.1
 */