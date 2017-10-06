/*   1:    */ package thKaguyaMod.client.model.living;
/*   2:    */ 
/*   3:    */ import net.minecraft.client.model.ModelBase;
/*   4:    */ import net.minecraft.client.model.ModelRenderer;
/*   5:    */ import net.minecraft.entity.Entity;
/*   6:    */ import net.minecraft.util.MathHelper;
/*   7:    */ 
/*   8:    */ public class ModelSakuya
/*   9:    */   extends ModelBase
/*  10:    */ {
/*  11:    */   public ModelRenderer bipedBody;
/*  12:    */   public ModelRenderer bipedHead;
/*  13:    */   public ModelRenderer bipedRightArm;
/*  14:    */   public ModelRenderer bipedLeftArm;
/*  15:    */   public ModelRenderer bipedRightLeg;
/*  16:    */   public ModelRenderer bipedLeftLeg;
/*  17:    */   public ModelRenderer skirtTop;
/*  18:    */   public ModelRenderer skirtBottom;
/*  19:    */   public ModelRenderer longHair;
/*  20:    */   public ModelRenderer leftRibbon;
/*  21:    */   public ModelRenderer rightRibbon;
/*  22:    */   public ModelRenderer leftKatyusha;
/*  23:    */   public ModelRenderer rightKatyusha;
/*  24:    */   public ModelRenderer rightBraid;
/*  25:    */   public ModelRenderer leftBraid;
/*  26: 26 */   protected float legHeight = 14.0F;
/*  27: 27 */   protected float bodyHeight = 9.0F;
/*  28:    */   
/*  29:    */   public ModelSakuya()
/*  30:    */   {
/*  31: 31 */     this(0.0F);
/*  32:    */   }
/*  33:    */   
/*  34:    */   public ModelSakuya(float size)
/*  35:    */   {
/*  36: 36 */     this(size, 0.0F, 64, 64);
/*  37:    */   }
/*  38:    */   
/*  39:    */   public ModelSakuya(float size, float yOffset, int textureXSize, int textureH)
/*  40:    */   {
/*  41: 41 */     this.textureHeight = 64;
/*  42: 42 */     this.textureWidth = 64;
/*  43:    */     
/*  44:    */ 
/*  45: 45 */     this.bipedBody = new ModelRenderer(this, 32, 8);
/*  46: 46 */     this.bipedBody.addBox(-3.0F, 0.0F, -2.0F, 6, 9, 4);
/*  47: 47 */     this.bipedBody.setRotationPoint(0.0F, 1.0F, 0.0F);
/*  48: 48 */     this.bipedBody.setTextureSize(64, 64);
/*  49: 49 */     this.bipedBody.mirror = true;
/*  50: 50 */     setRotation(this.bipedBody, 0.0F, 0.0F, 0.0F);
/*  51: 51 */     this.bipedRightArm = new ModelRenderer(this, 48, 0);
/*  52: 52 */     this.bipedRightArm.addBox(-1.0F, -1.0F, -1.0F, 2, 8, 2);
/*  53: 53 */     this.bipedRightArm.setRotationPoint(-4.0F, 2.0F, 0.0F);
/*  54: 54 */     this.bipedRightArm.setTextureSize(64, 64);
/*  55: 55 */     this.bipedRightArm.mirror = true;
/*  56: 56 */     setRotation(this.bipedRightArm, -0.7679449F, 0.0F, -0.645772F);
/*  57: 57 */     this.bipedLeftArm = new ModelRenderer(this, 56, 0);
/*  58: 58 */     this.bipedLeftArm.addBox(-1.0F, -1.0F, -1.0F, 2, 8, 2);
/*  59: 59 */     this.bipedLeftArm.setRotationPoint(4.0F, 2.0F, 0.0F);
/*  60: 60 */     this.bipedLeftArm.setTextureSize(64, 64);
/*  61: 61 */     this.bipedLeftArm.mirror = true;
/*  62: 62 */     setRotation(this.bipedLeftArm, -0.7679449F, 0.0F, 0.645772F);
/*  63: 63 */     this.bipedRightLeg = new ModelRenderer(this, 50, 18);
/*  64: 64 */     this.bipedRightLeg.addBox(-1.0F, 0.0F, -2.0F, 3, 14, 4);
/*  65: 65 */     this.bipedRightLeg.setRotationPoint(-2.0F, 10.0F, 0.0F);
/*  66: 66 */     this.bipedRightLeg.setTextureSize(64, 64);
/*  67: 67 */     this.bipedRightLeg.mirror = true;
/*  68: 68 */     setRotation(this.bipedRightLeg, 0.0F, 0.0F, 0.0F);
/*  69: 69 */     this.bipedLeftLeg = new ModelRenderer(this, 50, 18);
/*  70: 70 */     this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 3, 14, 4);
/*  71: 71 */     this.bipedLeftLeg.setRotationPoint(2.0F, 10.0F, 0.0F);
/*  72: 72 */     this.bipedLeftLeg.setTextureSize(64, 64);
/*  73: 73 */     this.bipedLeftLeg.mirror = true;
/*  74: 74 */     setRotation(this.bipedLeftLeg, 0.0F, 0.0F, 0.0F);
/*  75: 75 */     this.skirtTop = new ModelRenderer(this, 0, 16);
/*  76: 76 */     this.skirtTop.addBox(-4.0F, 0.0F, -4.0F, 8, 4, 8);
/*  77: 77 */     this.skirtTop.setRotationPoint(0.0F, 6.0F, 0.0F);
/*  78: 78 */     this.skirtTop.setTextureSize(64, 64);
/*  79: 79 */     this.skirtTop.mirror = true;
/*  80: 80 */     setRotation(this.skirtTop, 0.0F, 0.0F, 0.0F);
/*  81: 81 */     this.skirtBottom = new ModelRenderer(this, 16, 32);
/*  82: 82 */     this.skirtBottom.addBox(-5.0F, 0.0F, -5.0F, 10, 5, 10);
/*  83: 83 */     this.skirtBottom.setRotationPoint(0.0F, 4.0F, 0.0F);
/*  84: 84 */     this.skirtBottom.setTextureSize(64, 64);
/*  85: 85 */     this.skirtBottom.mirror = true;
/*  86: 86 */     setRotation(this.skirtBottom, 0.0F, 0.0F, 0.0F);
/*  87: 87 */     this.leftRibbon = new ModelRenderer(this, 0, 38);
/*  88: 88 */     this.leftRibbon.addBox(0.0F, 0.0F, 0.0F, 5, 4, 1);
/*  89: 89 */     this.leftRibbon.setRotationPoint(0.0F, 5.0F, 3.0F);
/*  90: 90 */     this.leftRibbon.setTextureSize(64, 64);
/*  91: 91 */     this.leftRibbon.mirror = true;
/*  92: 92 */     setRotation(this.leftRibbon, 0.4886922F, -0.3F, -0.296706F);
/*  93: 93 */     this.rightRibbon = new ModelRenderer(this, 0, 38);
/*  94: 94 */     this.rightRibbon.addBox(0.0F, 0.0F, -1.0F, 5, 4, 1);
/*  95: 95 */     this.rightRibbon.setRotationPoint(0.0F, 5.0F, 3.0F);
/*  96: 96 */     this.rightRibbon.setTextureSize(64, 64);
/*  97: 97 */     this.rightRibbon.mirror = true;
/*  98: 98 */     setRotation(this.rightRibbon, -0.4886922F, -2.841593F, 0.296706F);
/*  99: 99 */     this.bipedHead = new ModelRenderer(this, 0, 0);
/* 100:100 */     this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
/* 101:101 */     this.bipedHead.setRotationPoint(0.0F, 1.0F, 0.0F);
/* 102:102 */     this.bipedHead.setTextureSize(64, 64);
/* 103:103 */     this.bipedHead.mirror = true;
/* 104:104 */     setRotation(this.bipedHead, 0.0F, 0.0F, 0.0F);
/* 105:105 */     this.longHair = new ModelRenderer(this, 24, 0);
/* 106:106 */     this.longHair.addBox(-4.0F, -2.0F, -4.0F, 8, 5, 3);
/* 107:107 */     this.longHair.setRotationPoint(0.0F, 0.0F, 4.0F);
/* 108:108 */     this.longHair.setTextureSize(64, 64);
/* 109:109 */     this.longHair.mirror = true;
/* 110:110 */     setRotation(this.longHair, 0.20944F, 0.0F, 0.0F);
/* 111:111 */     this.leftBraid = new ModelRenderer(this, 0, 32);
/* 112:112 */     this.leftBraid.addBox(0.0F, -1.0F, -1.0F, 1, 5, 1);
/* 113:113 */     this.leftBraid.setRotationPoint(3.0F, -1.0F, -3.0F);
/* 114:114 */     this.leftBraid.setTextureSize(64, 64);
/* 115:115 */     this.leftBraid.mirror = true;
/* 116:116 */     setRotation(this.leftBraid, -0.244346F, 0.0F, 0.0F);
/* 117:117 */     this.rightBraid = new ModelRenderer(this, 0, 32);
/* 118:118 */     this.rightBraid.addBox(-1.0F, -1.0F, -1.0F, 1, 5, 1);
/* 119:119 */     this.rightBraid.setRotationPoint(-3.0F, -1.0F, -3.0F);
/* 120:120 */     this.rightBraid.setTextureSize(64, 64);
/* 121:121 */     this.rightBraid.mirror = true;
/* 122:122 */     setRotation(this.rightBraid, -0.244346F, 0.0F, 0.0F);
/* 123:123 */     this.leftKatyusha = new ModelRenderer(this, 4, 32);
/* 124:124 */     this.leftKatyusha.addBox(0.0F, -1.0F, 0.0F, 4, 1, 1);
/* 125:125 */     this.leftKatyusha.setRotationPoint(0.0F, -8.0F, -5.0F);
/* 126:126 */     this.leftKatyusha.setTextureSize(64, 64);
/* 127:127 */     this.leftKatyusha.mirror = true;
/* 128:128 */     setRotation(this.leftKatyusha, 0.0F, -0.1745329F, 0.226893F);
/* 129:129 */     this.rightKatyusha = new ModelRenderer(this, 4, 32);
/* 130:130 */     this.rightKatyusha.addBox(-4.0F, -1.0F, 0.0F, 4, 1, 1);
/* 131:131 */     this.rightKatyusha.setRotationPoint(0.0F, -8.0F, -5.0F);
/* 132:132 */     this.rightKatyusha.setTextureSize(64, 64);
/* 133:133 */     this.rightKatyusha.mirror = true;
/* 134:134 */     setRotation(this.rightKatyusha, 0.0F, 0.1745329F, -0.226893F);
/* 135:    */     
/* 136:136 */     this.bipedBody.addChild(this.bipedHead);
/* 137:137 */     this.bipedBody.addChild(this.bipedRightArm);
/* 138:138 */     this.bipedBody.addChild(this.bipedLeftArm);
/* 139:139 */     this.bipedBody.addChild(this.bipedRightLeg);
/* 140:140 */     this.bipedBody.addChild(this.bipedLeftLeg);
/* 141:    */     
/* 142:142 */     this.bipedHead.addChild(this.rightBraid);
/* 143:143 */     this.bipedHead.addChild(this.leftBraid);
/* 144:144 */     this.bipedBody.addChild(this.rightRibbon);
/* 145:145 */     this.bipedBody.addChild(this.leftRibbon);
/* 146:146 */     this.bipedHead.addChild(this.longHair);
/* 147:147 */     this.bipedHead.addChild(this.rightKatyusha);
/* 148:148 */     this.bipedHead.addChild(this.leftKatyusha);
/* 149:149 */     this.bipedBody.addChild(this.skirtTop);
/* 150:150 */     this.skirtTop.addChild(this.skirtBottom);
/* 151:    */   }
/* 152:    */   
/* 153:    */   public void render(Entity entity, float moveCounter, float moveCycle, float ticksExisted, float headYaw, float headPitch, float scale)
/* 154:    */   {
/* 155:160 */     setRotationAngles(moveCounter, moveCycle, ticksExisted, headYaw, headPitch, scale, entity);
/* 156:161 */     this.bipedBody.render(scale);
/* 157:    */   }
/* 158:    */   
/* 159:    */   public void setRotationAngles(float moveCounter, float moveCycle, float ticksExisted, float pheadYaw, float pheadPitch, float scale, Entity entity)
/* 160:    */   {
/* 161:174 */     setDefaultPause(moveCounter, moveCycle, ticksExisted, pheadYaw, pheadPitch, scale, entity);
/* 162:    */     
/* 163:    */ 
/* 164:177 */     this.bipedRightLeg.rotationPointX = -2.0F;
/* 165:178 */     this.bipedLeftLeg.rotationPointX = 2.0F;
/* 166:    */     
/* 167:    */ 
/* 168:181 */     this.bipedHead.rotateAngleY = (pheadYaw / 57.29578F);
/* 169:182 */     this.bipedHead.rotateAngleX = (pheadPitch / 57.29578F);
/* 170:    */     
/* 171:    */ 
/* 172:    */ 
/* 173:186 */     this.bipedRightArm.rotateAngleX = (MathHelper.cos(moveCounter * 0.6662F + 3.141593F) * 2.0F * moveCycle * 0.5F);
/* 174:187 */     this.bipedLeftArm.rotateAngleX = (MathHelper.cos(moveCounter * 0.6662F) * 2.0F * moveCycle * 0.5F);
/* 175:188 */     this.bipedRightArm.rotateAngleZ = 0.5235989F;
/* 176:189 */     this.bipedLeftArm.rotateAngleZ = -0.5235989F;
/* 177:190 */     this.skirtTop.rotateAngleX = 0.0F;
/* 178:191 */     this.skirtBottom.rotationPointZ = 0.0F;
/* 179:192 */     this.skirtBottom.rotateAngleX = 0.0F;
/* 180:196 */     if (entity.isSneaking())
/* 181:    */     {
/* 182:197 */       this.bipedBody.rotateAngleY = (MathHelper.cos(moveCounter * 0.6662F + 3.141593F) * 2.4F * moveCycle);
/* 183:198 */       this.skirtTop.rotateAngleY = (MathHelper.cos(moveCounter * 0.6662F + 3.141593F) * 2.4F * moveCycle);
/* 184:199 */       this.bipedRightArm.rotateAngleX = (MathHelper.cos(moveCounter * 0.6662F + 3.141593F) * 1.4F * moveCycle);
/* 185:200 */       this.bipedLeftArm.rotateAngleX = (MathHelper.cos(moveCounter * 0.6662F) * 1.4F * moveCycle);
/* 186:    */     }
/* 187:209 */     this.bipedRightLeg.rotateAngleX = (MathHelper.cos(moveCounter * 0.6662F) * 0.5F * moveCycle);
/* 188:210 */     this.bipedLeftLeg.rotateAngleX = (MathHelper.cos(moveCounter * 0.6662F + 3.141593F) * 0.5F * moveCycle);
/* 189:211 */     this.bipedRightLeg.rotateAngleY = (this.bipedLeftLeg.rotateAngleY = this.bipedRightLeg.rotateAngleZ = this.bipedLeftLeg.rotateAngleZ = 0.0F);
/* 190:213 */     if (entity.isRiding())
/* 191:    */     {
/* 192:215 */       this.bipedBody.rotationPointY = -1.0F;
/* 193:216 */       this.bipedRightLeg.rotationPointY = 5.0F;
/* 194:217 */       this.bipedLeftLeg.rotationPointY = 5.0F;
/* 195:218 */       this.bipedRightArm.rotateAngleX += -0.6283186F;
/* 196:219 */       this.bipedLeftArm.rotateAngleX += -0.6283186F;
/* 197:220 */       this.bipedRightLeg.rotateAngleX = -1.256637F;
/* 198:221 */       this.bipedLeftLeg.rotateAngleX = -1.256637F;
/* 199:222 */       this.bipedRightLeg.rotateAngleY = 0.2243995F;
/* 200:223 */       this.bipedLeftLeg.rotateAngleY = -0.2243995F;
/* 201:224 */       this.bipedRightLeg.rotateAngleZ = 0.2243995F;
/* 202:225 */       this.bipedLeftLeg.rotateAngleZ = -0.2243995F;
/* 203:    */       
/* 204:227 */       this.skirtTop.rotationPointY = 5.0F;
/* 205:228 */       this.skirtTop.rotationPointZ = 0.0F;
/* 206:229 */       this.skirtTop.rotateAngleX = -0.6283186F;
/* 207:230 */       this.skirtBottom.rotationPointZ = 0.0F;
/* 208:231 */       this.skirtBottom.rotationPointY = 4.0F;
/* 209:232 */       this.skirtBottom.rotateAngleX = -0.7853982F;
/* 210:    */     }
/* 211:    */     else
/* 212:    */     {
/* 213:234 */       this.skirtTop.rotationPointY = 5.0F;
/* 214:235 */       this.skirtTop.rotationPointZ = 0.0F;
/* 215:236 */       this.skirtBottom.rotationPointZ = 0.0F;
/* 216:237 */       this.skirtBottom.rotationPointY = 4.0F;
/* 217:238 */       this.bipedRightLeg.rotationPointY = this.bodyHeight;
/* 218:239 */       this.bipedLeftLeg.rotationPointY = this.bodyHeight;
/* 219:    */     }
/* 220:243 */     this.bipedRightArm.rotateAngleY = (this.bipedLeftArm.rotateAngleY = 0.0F);
/* 221:    */     
/* 222:245 */     this.bipedRightLeg.rotationPointZ = (this.bipedLeftLeg.rotationPointZ = this.bipedBody.rotateAngleX = this.bipedBody.rotateAngleZ = 0.0F);
/* 223:248 */     if (entity.isSneaking()) {
/* 224:250 */       this.bipedHead.rotateAngleX -= 0.5F;
/* 225:    */     }
/* 226:    */   }
/* 227:    */   
/* 228:    */   public void setDefaultPause(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/* 229:    */   {
/* 230:260 */     if (this.bipedHead != null) {
/* 231:260 */       this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 232:    */     }
/* 233:262 */     if (this.bipedBody != null) {
/* 234:262 */       this.bipedBody.setRotationPoint(0.0F, 1.0F, 0.0F);
/* 235:    */     }
/* 236:263 */     if (this.bipedRightArm != null) {
/* 237:263 */       this.bipedRightArm.setRotationPoint(-3.0F, 1.0F, 0.0F);
/* 238:    */     }
/* 239:264 */     if (this.bipedLeftArm != null) {
/* 240:264 */       this.bipedLeftArm.setRotationPoint(3.0F, 1.0F, 0.0F);
/* 241:    */     }
/* 242:265 */     if (this.bipedRightLeg != null) {
/* 243:265 */       this.bipedRightLeg.setRotationPoint(-2.0F, this.bodyHeight, 0.0F);
/* 244:    */     }
/* 245:266 */     if (this.bipedLeftLeg != null) {
/* 246:266 */       this.bipedLeftLeg.setRotationPoint(2.0F, this.bodyHeight, 0.0F);
/* 247:    */     }
/* 248:    */   }
/* 249:    */   
/* 250:    */   private void setRotation(ModelRenderer model, float rotateX, float rotateY, float rotateZ)
/* 251:    */   {
/* 252:271 */     model.rotateAngleX = rotateX;
/* 253:272 */     model.rotateAngleY = rotateY;
/* 254:273 */     model.rotateAngleZ = rotateZ;
/* 255:    */   }
/* 256:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.model.living.ModelSakuya
 * JD-Core Version:    0.7.0.1
 */