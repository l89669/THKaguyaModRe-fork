/*   1:    */ package thKaguyaMod.client.model.living;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.relauncher.Side;
/*   4:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   5:    */ import net.minecraft.client.model.ModelBase;
/*   6:    */ import net.minecraft.client.model.ModelRenderer;
/*   7:    */ import net.minecraft.entity.Entity;
/*   8:    */ import net.minecraft.util.MathHelper;
/*   9:    */ import thKaguyaMod.entity.living.EntityDanmakuMob;
/*  10:    */ 
/*  11:    */ @SideOnly(Side.CLIENT)
/*  12:    */ public class ModelCirno
/*  13:    */   extends ModelBase
/*  14:    */ {
/*  15:    */   ModelRenderer body;
/*  16:    */   ModelRenderer head;
/*  17:    */   ModelRenderer rightArm;
/*  18:    */   ModelRenderer leftArm;
/*  19:    */   ModelRenderer rightLeg;
/*  20:    */   ModelRenderer leftLeg;
/*  21:    */   ModelRenderer skirt;
/*  22:    */   ModelRenderer skirt2;
/*  23:    */   ModelRenderer rightWing;
/*  24:    */   ModelRenderer leftWing;
/*  25:    */   ModelRenderer rightWing2;
/*  26:    */   ModelRenderer leftWing2;
/*  27:    */   ModelRenderer rightWing3;
/*  28:    */   ModelRenderer leftWing3;
/*  29:    */   ModelRenderer longHair;
/*  30:    */   ModelRenderer ribbonCenter;
/*  31:    */   ModelRenderer ribbonRight;
/*  32:    */   ModelRenderer ribbonLeft;
/*  33:    */   
/*  34:    */   public ModelCirno()
/*  35:    */   {
/*  36: 43 */     this.textureWidth = 64;
/*  37: 44 */     this.textureHeight = 64;
/*  38:    */     
/*  39: 46 */     this.head = new ModelRenderer(this, 0, 0);
/*  40: 47 */     this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
/*  41: 48 */     this.head.setRotationPoint(0.0F, 5.0F, 0.0F);
/*  42: 49 */     this.head.setTextureSize(64, 64);
/*  43: 50 */     this.head.mirror = true;
/*  44:    */     
/*  45: 52 */     this.longHair = new ModelRenderer(this, 24, 0);
/*  46: 53 */     this.longHair.addBox(-4.0F, 0.0F, -3.0F, 8, 5, 3);
/*  47: 54 */     this.longHair.setRotationPoint(0.0F, 0.0F, 4.0F);
/*  48:    */     
/*  49:    */ 
/*  50:    */ 
/*  51: 58 */     this.head.addChild(this.longHair);
/*  52:    */     
/*  53: 60 */     this.ribbonCenter = new ModelRenderer(this, 0, 32);
/*  54: 61 */     this.ribbonCenter.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 1);
/*  55: 62 */     this.ribbonCenter.setRotationPoint(0.0F, -8.0F, 4.0F);
/*  56:    */     
/*  57:    */ 
/*  58: 65 */     this.head.addChild(this.ribbonCenter);
/*  59: 66 */     setRotation(this.ribbonCenter, 0.244346F, 0.0F, 0.0F);
/*  60: 67 */     this.ribbonLeft = new ModelRenderer(this, 6, 32);
/*  61: 68 */     this.ribbonLeft.addBox(0.0F, -2.0F, 0.0F, 5, 4, 1);
/*  62: 69 */     this.ribbonLeft.setRotationPoint(1.0F, -8.0F, 4.0F);
/*  63:    */     
/*  64:    */ 
/*  65: 72 */     this.head.addChild(this.ribbonLeft);
/*  66: 73 */     setRotation(this.ribbonLeft, 0.418879F, 0.0F, 0.0F);
/*  67: 74 */     this.ribbonRight = new ModelRenderer(this, 6, 32);
/*  68: 75 */     this.ribbonRight.addBox(-5.0F, -2.0F, 0.0F, 5, 4, 1);
/*  69: 76 */     this.ribbonRight.setRotationPoint(-1.0F, -8.0F, 4.0F);
/*  70:    */     
/*  71:    */ 
/*  72:    */ 
/*  73: 80 */     this.head.addChild(this.ribbonRight);
/*  74:    */     
/*  75: 82 */     this.body = new ModelRenderer(this, 32, 8);
/*  76: 83 */     this.body.addBox(-3.0F, 0.0F, -2.0F, 6, 7, 4);
/*  77: 84 */     this.body.setRotationPoint(0.0F, 5.0F, 0.0F);
/*  78: 85 */     this.body.setTextureSize(64, 64);
/*  79: 86 */     this.body.mirror = true;
/*  80: 87 */     setRotation(this.body, 0.0F, 0.0F, 0.0F);
/*  81:    */     
/*  82: 89 */     this.skirt = new ModelRenderer(this, 0, 16);
/*  83: 90 */     this.skirt.addBox(-4.0F, 0.0F, -4.0F, 8, 5, 8);
/*  84: 91 */     this.skirt.setRotationPoint(0.0F, 10.0F, 0.0F);
/*  85:    */     
/*  86:    */ 
/*  87: 94 */     setRotation(this.skirt, 0.0F, 0.0F, 0.0F);
/*  88: 95 */     this.skirt2 = new ModelRenderer(this, 24, 32);
/*  89: 96 */     this.skirt2.addBox(-5.0F, 0.0F, -5.0F, 10, 6, 10);
/*  90: 97 */     this.skirt2.setRotationPoint(0.0F, 4.0F, 0.0F);
/*  91:    */     
/*  92:    */ 
/*  93:100 */     this.skirt.addChild(this.skirt2);
/*  94:101 */     setRotation(this.skirt2, 0.0F, 0.0F, 0.0F);
/*  95:    */     
/*  96:103 */     this.rightArm = new ModelRenderer(this, 48, 0);
/*  97:104 */     this.rightArm.addBox(-1.0F, -1.0F, -1.0F, 2, 8, 2);
/*  98:105 */     this.rightArm.setRotationPoint(-4.0F, 6.0F, 0.0F);
/*  99:106 */     this.rightArm.setTextureSize(64, 64);
/* 100:107 */     this.rightArm.mirror = true;
/* 101:108 */     setRotation(this.rightArm, -0.7679449F, 0.0F, -0.645772F);
/* 102:    */     
/* 103:    */ 
/* 104:    */ 
/* 105:    */ 
/* 106:    */ 
/* 107:    */ 
/* 108:    */ 
/* 109:    */ 
/* 110:117 */     this.leftArm = new ModelRenderer(this, 56, 0);
/* 111:118 */     this.leftArm.addBox(-1.0F, -1.0F, -1.0F, 2, 8, 2);
/* 112:119 */     this.leftArm.setRotationPoint(4.0F, 6.0F, 0.0F);
/* 113:    */     
/* 114:    */ 
/* 115:122 */     setRotation(this.leftArm, -0.7679449F, 0.0F, 0.645772F);
/* 116:123 */     this.rightLeg = new ModelRenderer(this, 50, 16);
/* 117:124 */     this.rightLeg.addBox(-1.0F, 0.0F, -2.0F, 3, 12, 4);
/* 118:125 */     this.rightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
/* 119:    */     
/* 120:    */ 
/* 121:128 */     setRotation(this.rightLeg, 0.0F, 0.0F, 0.0F);
/* 122:129 */     this.leftLeg = new ModelRenderer(this, 50, 16);
/* 123:130 */     this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 3, 12, 4);
/* 124:131 */     this.leftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
/* 125:    */     
/* 126:    */ 
/* 127:134 */     setRotation(this.leftLeg, 0.0F, 0.0F, 0.0F);
/* 128:    */     
/* 129:    */ 
/* 130:    */ 
/* 131:    */ 
/* 132:139 */     this.rightWing = new ModelRenderer(this, 0, 48);
/* 133:140 */     this.rightWing.addBox(-14.0F, 0.0F, 0.0F, 14, 3, 3);
/* 134:141 */     this.rightWing.setRotationPoint(-1.0F, 3.0F, 2.0F);
/* 135:    */     
/* 136:    */ 
/* 137:144 */     setRotation(this.rightWing, 0.0F, 0.4886922F, 0.314159F);
/* 138:    */     
/* 139:146 */     this.leftWing = new ModelRenderer(this, 0, 48);
/* 140:147 */     this.leftWing.addBox(0.0F, 0.0F, 0.0F, 14, 3, 3);
/* 141:148 */     this.leftWing.setRotationPoint(0.0F, 3.0F, 2.0F);
/* 142:    */     
/* 143:    */ 
/* 144:151 */     setRotation(this.leftWing, 0.0F, -0.4886922F, -0.314159F);
/* 145:    */     
/* 146:153 */     this.rightWing2 = new ModelRenderer(this, 0, 48);
/* 147:154 */     this.rightWing2.addBox(-14.0F, 0.0F, 0.0F, 14, 3, 3);
/* 148:155 */     this.rightWing2.setRotationPoint(-1.0F, 6.0F, 2.0F);
/* 149:    */     
/* 150:    */ 
/* 151:158 */     setRotation(this.rightWing2, 0.0F, 0.4886922F, -0.314159F);
/* 152:    */     
/* 153:160 */     this.leftWing2 = new ModelRenderer(this, 0, 48);
/* 154:161 */     this.leftWing2.addBox(0.0F, 0.0F, 0.0F, 14, 3, 3);
/* 155:162 */     this.leftWing2.setRotationPoint(1.0F, 6.0F, 2.0F);
/* 156:    */     
/* 157:    */ 
/* 158:165 */     setRotation(this.leftWing2, 0.0F, -0.4886922F, 0.314159F);
/* 159:166 */     this.rightWing3 = new ModelRenderer(this, 0, 48);
/* 160:167 */     this.rightWing3.addBox(-14.0F, 0.0F, 0.0F, 14, 3, 3);
/* 161:168 */     this.rightWing3.setRotationPoint(-1.0F, 5.0F, 2.0F);
/* 162:    */     
/* 163:    */ 
/* 164:171 */     setRotation(this.rightWing3, 0.0F, 0.4886922F, 0.0F);
/* 165:172 */     this.leftWing3 = new ModelRenderer(this, 0, 48);
/* 166:173 */     this.leftWing3.addBox(0.0F, 0.0F, 0.0F, 14, 3, 3);
/* 167:174 */     this.leftWing3.setRotationPoint(1.0F, 5.0F, 2.0F);
/* 168:    */     
/* 169:    */ 
/* 170:177 */     setRotation(this.leftWing3, 0.0F, -0.4886922F, 0.0F);
/* 171:    */   }
/* 172:    */   
/* 173:    */   public void render(Entity entity, float movement, float far, float tick, float yaw, float pitch, float size)
/* 174:    */   {
/* 175:186 */     super.render(entity, movement, far, tick, yaw, pitch, size);
/* 176:187 */     setRotationAngles(movement, far, tick, yaw, pitch, size, entity);
/* 177:188 */     this.head.render(size);
/* 178:189 */     this.body.render(size);
/* 179:190 */     this.skirt.render(size);
/* 180:191 */     this.rightArm.render(size);
/* 181:192 */     this.rightLeg.render(size);
/* 182:193 */     this.leftArm.render(size);
/* 183:194 */     this.leftLeg.render(size);
/* 184:    */     
/* 185:196 */     this.rightWing.render(size);
/* 186:197 */     this.rightWing2.render(size);
/* 187:198 */     this.rightWing3.render(size);
/* 188:199 */     this.leftWing.render(size);
/* 189:200 */     this.leftWing2.render(size);
/* 190:201 */     this.leftWing3.render(size);
/* 191:    */   }
/* 192:    */   
/* 193:    */   private void setRotation(ModelRenderer model, float x, float y, float z)
/* 194:    */   {
/* 195:206 */     model.rotateAngleX = x;
/* 196:207 */     model.rotateAngleY = y;
/* 197:208 */     model.rotateAngleZ = z;
/* 198:    */   }
/* 199:    */   
/* 200:    */   public void setRotationAngles(float movement, float far, float tick, float yaw, float pitch, float size, Entity entity)
/* 201:    */   {
/* 202:214 */     super.setRotationAngles(movement, far, tick, yaw, pitch, size, entity);
/* 203:215 */     EntityDanmakuMob thFairy = (EntityDanmakuMob)entity;
/* 204:    */     
/* 205:217 */     this.head.rotateAngleY = (yaw / 57.295776F);
/* 206:218 */     this.head.rotateAngleX = (pitch / 57.295776F);
/* 207:219 */     this.body.rotateAngleY = (MathHelper.sin(MathHelper.sqrt_float(this.onGround) * 3.141593F * 2.0F) * 0.2F);
/* 208:220 */     this.skirt.rotateAngleX = 0.0F;
/* 209:223 */     if (thFairy.getSpellCardMotion() < -15)
/* 210:    */     {
/* 211:225 */       this.rightArm.rotateAngleX = (-0.7F - MathHelper.sin(tick / 10.0F) * 0.1F - MathHelper.sin((30.0F + thFairy.getSpellCardMotion()) / 180.0F * 3.141593F) * 4.0F);
/* 212:    */     }
/* 213:227 */     else if (thFairy.getSpellCardMotion() < 0)
/* 214:    */     {
/* 215:229 */       this.rightArm.rotateAngleX = (-0.7F - MathHelper.sin(tick / 10.0F) * 0.1F - MathHelper.sin(-thFairy.getSpellCardMotion() / 180.0F * 3.141593F) * 4.0F);
/* 216:    */     }
/* 217:    */     else
/* 218:    */     {
/* 219:233 */       this.rightArm.rotateAngleX = (-0.7F - MathHelper.sin(tick / 10.0F) * 0.1F);
/* 220:234 */       this.leftArm.rotateAngleX = this.rightArm.rotateAngleX;
/* 221:    */     }
/* 222:237 */     if (this.isRiding)
/* 223:    */     {
/* 224:239 */       this.rightArm.rotateAngleX = -0.6283186F;
/* 225:240 */       this.leftArm.rotateAngleX = -0.6283186F;
/* 226:241 */       this.rightLeg.rotateAngleX = -1.256637F;
/* 227:242 */       this.leftLeg.rotateAngleX = -1.256637F;
/* 228:243 */       this.rightLeg.rotateAngleY = 0.2243995F;
/* 229:244 */       this.leftLeg.rotateAngleY = -0.2243995F;
/* 230:245 */       this.rightLeg.rotateAngleZ = 0.2243995F;
/* 231:246 */       this.leftLeg.rotateAngleZ = -0.2243995F;
/* 232:    */       
/* 233:248 */       this.skirt.rotateAngleX += -0.6283186F;
/* 234:    */     }
/* 235:253 */     else if (thFairy.getFlyingHeight() == 0)
/* 236:    */     {
/* 237:255 */       this.rightLeg.rotateAngleX = (MathHelper.cos(movement) * 0.7F * far);
/* 238:256 */       this.leftLeg.rotateAngleX = (MathHelper.cos(movement + 3.141593F) * 0.7F * far);
/* 239:257 */       this.rightLeg.rotateAngleZ = 0.0F;
/* 240:258 */       this.leftLeg.rotateAngleZ = 0.0F;
/* 241:260 */       if (movement > 0.0F)
/* 242:    */       {
/* 243:262 */         this.rightArm.rotateAngleX = (MathHelper.cos(movement + 3.141593F) * 2.0F * far * 0.5F);
/* 244:263 */         this.leftArm.rotateAngleX = (MathHelper.cos(movement) * 2.0F * far * 0.5F);
/* 245:264 */         this.rightArm.rotateAngleY = -0.1745329F;
/* 246:265 */         this.rightArm.rotateAngleZ = 0.3490659F;
/* 247:266 */         this.leftArm.rotateAngleY = (-this.rightArm.rotateAngleY);
/* 248:267 */         this.leftArm.rotateAngleZ = (-this.rightArm.rotateAngleZ);
/* 249:    */       }
/* 250:    */       else
/* 251:    */       {
/* 252:271 */         this.rightArm.rotateAngleX = (-0.7F - MathHelper.sin(tick / 10.0F) * 0.1F);
/* 253:272 */         this.rightArm.rotateAngleY = 0.0F;
/* 254:273 */         this.rightArm.rotateAngleZ = -0.645772F;
/* 255:274 */         this.leftArm.rotateAngleX = this.rightArm.rotateAngleX;
/* 256:275 */         this.leftArm.rotateAngleY = 0.0F;
/* 257:276 */         this.leftArm.rotateAngleZ = 0.645772F;
/* 258:    */       }
/* 259:    */     }
/* 260:    */     else
/* 261:    */     {
/* 262:283 */       this.rightLeg.rotateAngleZ = Math.abs(MathHelper.sin(tick / 10.0F) * 0.1F);
/* 263:284 */       this.leftLeg.rotateAngleZ = (-this.rightLeg.rotateAngleZ);
/* 264:285 */       this.rightLeg.rotateAngleX = Math.abs(MathHelper.sin(tick / 10.0F) * 0.2F);
/* 265:286 */       this.leftLeg.rotateAngleX = this.rightLeg.rotateAngleZ;
/* 266:    */       
/* 267:288 */       this.rightArm.rotateAngleX = (-0.7F - MathHelper.sin(tick / 10.0F) * 0.1F);
/* 268:289 */       this.rightArm.rotateAngleY = 0.0F;
/* 269:290 */       this.rightArm.rotateAngleZ = -0.645772F;
/* 270:291 */       this.leftArm.rotateAngleX = this.rightArm.rotateAngleX;
/* 271:292 */       this.leftArm.rotateAngleY = 0.0F;
/* 272:293 */       this.leftArm.rotateAngleZ = 0.645772F;
/* 273:    */     }
/* 274:300 */     this.body.rotateAngleX = 0.0F;
/* 275:301 */     if (thFairy.getFlyingHeight() > 0)
/* 276:    */     {
/* 277:303 */       this.rightWing.rotateAngleY = (MathHelper.cos(tick * 0.4F) * 3.141593F * 0.25F);
/* 278:304 */       this.leftWing.rotateAngleY = (-this.rightWing.rotateAngleY);
/* 279:305 */       this.rightWing2.rotateAngleY = (MathHelper.cos(tick * 0.4F) * 3.141593F * 0.25F);
/* 280:306 */       this.leftWing2.rotateAngleY = (-this.rightWing2.rotateAngleY);
/* 281:307 */       this.rightWing3.rotateAngleY = (MathHelper.cos(tick * 0.4F) * 3.141593F * 0.25F);
/* 282:308 */       this.leftWing3.rotateAngleY = (-this.rightWing2.rotateAngleY);
/* 283:    */     }
/* 284:    */     else
/* 285:    */     {
/* 286:312 */       this.rightWing.rotateAngleY = (MathHelper.cos(tick * 0.1F) * 3.141593F * 0.1F + 0.4712389F);
/* 287:313 */       this.leftWing.rotateAngleY = (-this.rightWing.rotateAngleY);
/* 288:314 */       this.rightWing2.rotateAngleY = (MathHelper.cos(tick * 0.1F) * 3.141593F * 0.1F + 0.4712389F);
/* 289:315 */       this.leftWing2.rotateAngleY = (-this.rightWing2.rotateAngleY);
/* 290:316 */       this.rightWing3.rotateAngleY = (MathHelper.cos(tick * 0.1F) * 3.141593F * 0.1F + 0.4712389F);
/* 291:317 */       this.leftWing3.rotateAngleY = (-this.rightWing2.rotateAngleY);
/* 292:    */     }
/* 293:    */   }
/* 294:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.model.living.ModelCirno
 * JD-Core Version:    0.7.0.1
 */