/*   1:    */ package thKaguyaMod.client.model.living;
/*   2:    */ 
/*   3:    */ import net.minecraft.client.model.ModelBase;
/*   4:    */ import net.minecraft.client.model.ModelRenderer;
/*   5:    */ import net.minecraft.entity.Entity;
/*   6:    */ import net.minecraft.util.MathHelper;
/*   7:    */ 
/*   8:    */ public class ModelReimu
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
/*  19:    */   public ModelRenderer handRight;
/*  20:    */   public ModelRenderer handLeft;
/*  21:    */   public ModelRenderer longHair;
/*  22:    */   public ModelRenderer braidRight;
/*  23:    */   public ModelRenderer braidLeft;
/*  24:    */   public ModelRenderer rightRibbon;
/*  25:    */   public ModelRenderer rightRibbon2;
/*  26:    */   public ModelRenderer leftRibbon;
/*  27:    */   public ModelRenderer leftRibbon2;
/*  28: 28 */   protected float legHeight = 12.0F;
/*  29: 29 */   protected float bodyHeight = 9.0F;
/*  30:    */   
/*  31:    */   public ModelReimu()
/*  32:    */   {
/*  33: 33 */     this(0.0F);
/*  34:    */   }
/*  35:    */   
/*  36:    */   public ModelReimu(float size)
/*  37:    */   {
/*  38: 38 */     this(size, 0.0F, 64, 64);
/*  39:    */   }
/*  40:    */   
/*  41:    */   public ModelReimu(float size, float yOffset, int textureXSize, int textureH)
/*  42:    */   {
/*  43: 43 */     this.textureHeight = 64;
/*  44: 44 */     this.textureWidth = 64;
/*  45:    */     
/*  46:    */ 
/*  47: 47 */     this.bipedBody = new ModelRenderer(this, 32, 8);
/*  48: 48 */     this.bipedBody.addBox(-3.0F, -1.0F, -2.0F, 6, 9, 4);
/*  49: 49 */     this.bipedBody.setRotationPoint(0.0F, 3.0F, 0.0F);
/*  50: 50 */     this.bipedBody.setTextureSize(64, 64);
/*  51: 51 */     this.bipedBody.mirror = true;
/*  52: 52 */     setRotation(this.bipedBody, 0.0F, 0.0F, 0.0F);
/*  53: 53 */     this.bipedRightArm = new ModelRenderer(this, 48, 0);
/*  54: 54 */     this.bipedRightArm.addBox(-1.0F, 0.0F, -1.0F, 4, 7, 2);
/*  55: 55 */     this.bipedRightArm.setRotationPoint(-3.0F, 3.0F, 0.0F);
/*  56: 56 */     this.bipedRightArm.setTextureSize(64, 64);
/*  57: 57 */     this.bipedRightArm.mirror = true;
/*  58: 58 */     setRotation(this.bipedRightArm, 0.0F, 0.0F, 0.0F);
/*  59: 59 */     this.handRight = new ModelRenderer(this, 52, 9);
/*  60: 60 */     this.handRight.addBox(-1.0F, 1.0F, -1.0F, 2, 1, 2);
/*  61: 61 */     this.handRight.setRotationPoint(-0.0F, 6.0F, 0.0F);
/*  62: 62 */     this.handRight.setTextureSize(64, 64);
/*  63: 63 */     this.handRight.mirror = true;
/*  64: 64 */     setRotation(this.handRight, 0.0F, 0.0F, 0.0F);
/*  65: 65 */     this.bipedLeftArm = new ModelRenderer(this, 48, 0);
/*  66: 66 */     this.bipedLeftArm.addBox(-3.0F, 0.0F, -1.0F, 4, 7, 2);
/*  67: 67 */     this.bipedLeftArm.setRotationPoint(3.0F, 3.0F, 0.0F);
/*  68: 68 */     this.bipedLeftArm.setTextureSize(64, 64);
/*  69: 69 */     this.bipedLeftArm.mirror = true;
/*  70: 70 */     setRotation(this.bipedLeftArm, 0.0F, 0.0F, 0.0F);
/*  71: 71 */     this.handLeft = new ModelRenderer(this, 52, 9);
/*  72: 72 */     this.handLeft.addBox(-1.0F, 1.0F, -1.0F, 2, 1, 2);
/*  73: 73 */     this.handLeft.setRotationPoint(0.0F, 6.0F, 0.0F);
/*  74: 74 */     this.handLeft.setTextureSize(64, 64);
/*  75: 75 */     this.handLeft.mirror = true;
/*  76: 76 */     setRotation(this.handLeft, 0.0F, 0.0F, 0.0F);
/*  77: 77 */     this.bipedRightLeg = new ModelRenderer(this, 50, 18);
/*  78: 78 */     this.bipedRightLeg.addBox(-1.0F, 0.0F, -2.0F, 3, 12, 4);
/*  79: 79 */     this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
/*  80: 80 */     this.bipedRightLeg.setTextureSize(64, 64);
/*  81: 81 */     this.bipedRightLeg.mirror = true;
/*  82: 82 */     setRotation(this.bipedRightLeg, 0.0F, 0.0F, 0.0F);
/*  83: 83 */     this.bipedLeftLeg = new ModelRenderer(this, 50, 18);
/*  84: 84 */     this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 3, 12, 4);
/*  85: 85 */     this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
/*  86: 86 */     this.bipedLeftLeg.setTextureSize(64, 64);
/*  87: 87 */     this.bipedLeftLeg.mirror = true;
/*  88: 88 */     setRotation(this.bipedLeftLeg, 0.0F, 0.0F, 0.0F);
/*  89: 89 */     this.skirtTop = new ModelRenderer(this, 0, 16);
/*  90: 90 */     this.skirtTop.addBox(-4.0F, 0.0F, -4.0F, 8, 4, 8);
/*  91: 91 */     this.skirtTop.setRotationPoint(0.0F, 9.0F, 0.0F);
/*  92: 92 */     this.skirtTop.setTextureSize(64, 64);
/*  93: 93 */     this.skirtTop.mirror = true;
/*  94: 94 */     setRotation(this.skirtTop, 0.0F, 0.0F, 0.0F);
/*  95: 95 */     this.skirtBottom = new ModelRenderer(this, 16, 33);
/*  96: 96 */     this.skirtBottom.addBox(-5.0F, 0.0F, -5.0F, 10, 7, 10);
/*  97: 97 */     this.skirtBottom.setRotationPoint(0.0F, 13.0F, 0.0F);
/*  98: 98 */     this.skirtBottom.setTextureSize(64, 64);
/*  99: 99 */     this.skirtBottom.mirror = true;
/* 100:100 */     setRotation(this.skirtBottom, 0.0F, 0.0F, 0.0F);
/* 101:101 */     this.bipedHead = new ModelRenderer(this, 0, 0);
/* 102:102 */     this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
/* 103:103 */     this.bipedHead.setRotationPoint(0.0F, 3.0F, 0.0F);
/* 104:104 */     this.bipedHead.setTextureSize(64, 64);
/* 105:105 */     this.bipedHead.mirror = true;
/* 106:106 */     setRotation(this.bipedHead, 0.0F, 0.0F, 0.0F);
/* 107:107 */     this.longHair = new ModelRenderer(this, 0, 50);
/* 108:108 */     this.longHair.addBox(-4.0F, -2.0F, -3.0F, 8, 9, 3);
/* 109:109 */     this.longHair.setRotationPoint(0.0F, 0.0F, 4.0F);
/* 110:110 */     this.longHair.setTextureSize(64, 64);
/* 111:111 */     this.longHair.mirror = true;
/* 112:112 */     setRotation(this.longHair, 0.20944F, 0.0F, 0.0F);
/* 113:113 */     this.braidLeft = new ModelRenderer(this, 0, 32);
/* 114:114 */     this.braidLeft.addBox(0.0F, -3.0F, -2.0F, 1, 6, 1);
/* 115:115 */     this.braidLeft.setRotationPoint(3.0F, 0.0F, -3.0F);
/* 116:116 */     this.braidLeft.setTextureSize(64, 64);
/* 117:117 */     this.braidLeft.mirror = true;
/* 118:118 */     setRotation(this.braidLeft, -0.244346F, 0.0F, 0.0F);
/* 119:119 */     this.braidRight = new ModelRenderer(this, 0, 32);
/* 120:120 */     this.braidRight.addBox(-1.0F, -3.0F, -2.0F, 1, 6, 1);
/* 121:121 */     this.braidRight.setRotationPoint(-3.0F, 0.0F, -3.0F);
/* 122:122 */     this.braidRight.setTextureSize(64, 64);
/* 123:123 */     this.braidRight.mirror = true;
/* 124:124 */     setRotation(this.braidRight, -0.244346F, 0.0F, 0.0F);
/* 125:125 */     this.leftRibbon = new ModelRenderer(this, 32, 56);
/* 126:126 */     this.leftRibbon.addBox(1.0F, -6.0F, 1.0F, 8, 7, 1);
/* 127:127 */     this.leftRibbon.setRotationPoint(0.0F, -3.0F, 4.0F);
/* 128:128 */     this.leftRibbon.setTextureSize(64, 64);
/* 129:129 */     this.leftRibbon.mirror = true;
/* 130:130 */     setRotation(this.leftRibbon, 0.446143F, 0.0F, -0.4089647F);
/* 131:131 */     this.rightRibbon = new ModelRenderer(this, 32, 56);
/* 132:132 */     this.rightRibbon.addBox(1.0F, -6.0F, -2.0F, 8, 7, 1);
/* 133:133 */     this.rightRibbon.setRotationPoint(0.0F, -3.0F, 4.0F);
/* 134:134 */     this.rightRibbon.setTextureSize(64, 64);
/* 135:135 */     this.rightRibbon.mirror = true;
/* 136:136 */     setRotation(this.rightRibbon, -0.4461411F, 3.141593F, 0.3764335F);
/* 137:137 */     this.rightRibbon2 = new ModelRenderer(this, 32, 56);
/* 138:138 */     this.rightRibbon2.addBox(-1.0F, -5.0F, -1.0F, 8, 3, 1);
/* 139:139 */     this.rightRibbon2.setRotationPoint(0.0F, -3.0F, 5.0F);
/* 140:140 */     this.rightRibbon2.setTextureSize(64, 64);
/* 141:141 */     this.rightRibbon2.mirror = true;
/* 142:142 */     setRotation(this.rightRibbon2, -0.260248F, 3.141593F, -0.706394F);
/* 143:143 */     this.leftRibbon2 = new ModelRenderer(this, 32, 56);
/* 144:144 */     this.leftRibbon2.addBox(-1.0F, -5.0F, 0.0F, 8, 3, 1);
/* 145:145 */     this.leftRibbon2.setRotationPoint(0.0F, -3.0F, 5.0F);
/* 146:146 */     this.leftRibbon2.setTextureSize(64, 64);
/* 147:147 */     this.leftRibbon2.mirror = true;
/* 148:148 */     setRotation(this.leftRibbon2, 0.260246F, 0.0F, 0.706394F);
/* 149:    */     
/* 150:150 */     this.bipedBody.addChild(this.bipedHead);
/* 151:151 */     this.bipedBody.addChild(this.bipedRightArm);
/* 152:152 */     this.bipedBody.addChild(this.bipedLeftArm);
/* 153:153 */     this.bipedBody.addChild(this.bipedRightLeg);
/* 154:154 */     this.bipedBody.addChild(this.bipedLeftLeg);
/* 155:155 */     this.bipedRightArm.addChild(this.handRight);
/* 156:156 */     this.bipedLeftArm.addChild(this.handLeft);
/* 157:    */     
/* 158:158 */     this.bipedHead.addChild(this.braidRight);
/* 159:159 */     this.bipedHead.addChild(this.braidLeft);
/* 160:160 */     this.bipedHead.addChild(this.rightRibbon);
/* 161:161 */     this.bipedHead.addChild(this.rightRibbon2);
/* 162:162 */     this.bipedHead.addChild(this.leftRibbon);
/* 163:163 */     this.bipedHead.addChild(this.leftRibbon2);
/* 164:164 */     this.bipedHead.addChild(this.longHair);
/* 165:165 */     this.bipedBody.addChild(this.skirtTop);
/* 166:166 */     this.skirtTop.addChild(this.skirtBottom);
/* 167:    */   }
/* 168:    */   
/* 169:    */   public void render(Entity entity, float moveCounter, float moveCycle, float ticksExisted, float headYaw, float headPitch, float scale)
/* 170:    */   {
/* 171:176 */     setRotationAngles(moveCounter, moveCycle, ticksExisted, headYaw, headPitch, scale, entity);
/* 172:177 */     this.bipedBody.render(scale);
/* 173:    */   }
/* 174:    */   
/* 175:    */   public void setRotationAngles(float moveCounter, float moveCycle, float ticksExisted, float pheadYaw, float pheadPitch, float scale, Entity entity)
/* 176:    */   {
/* 177:190 */     setDefaultPause(moveCounter, moveCycle, ticksExisted, pheadYaw, pheadPitch, scale, entity);
/* 178:191 */     this.bipedBody.rotationPointY = 5.0F;
/* 179:    */     
/* 180:193 */     this.bipedRightLeg.rotationPointX = -2.0F;
/* 181:194 */     this.bipedLeftLeg.rotationPointX = 2.0F;
/* 182:    */     
/* 183:196 */     this.bipedHead.rotationPointY = -1.0F;
/* 184:197 */     this.bipedHead.rotateAngleY = (pheadYaw / 57.29578F);
/* 185:198 */     this.bipedHead.rotateAngleX = (pheadPitch / 57.29578F);
/* 186:    */     
/* 187:    */ 
/* 188:    */ 
/* 189:202 */     this.bipedRightArm.rotateAngleX = (MathHelper.cos(moveCounter * 0.6662F + 3.141593F) * 2.0F * moveCycle * 0.5F);
/* 190:203 */     this.bipedLeftArm.rotateAngleX = (MathHelper.cos(moveCounter * 0.6662F) * 2.0F * moveCycle * 0.5F);
/* 191:204 */     this.bipedRightArm.rotateAngleZ = 0.5235989F;
/* 192:205 */     this.bipedLeftArm.rotateAngleZ = -0.5235989F;
/* 193:206 */     this.skirtTop.rotateAngleX = 0.0F;
/* 194:207 */     this.skirtBottom.rotationPointZ = 0.0F;
/* 195:208 */     this.skirtBottom.rotateAngleX = 0.0F;
/* 196:209 */     this.bipedLeftArm.rotationPointY = -1.0F;
/* 197:210 */     this.bipedRightArm.rotationPointY = -1.0F;
/* 198:212 */     if (entity.isSneaking())
/* 199:    */     {
/* 200:213 */       this.bipedBody.rotateAngleY = (MathHelper.cos(moveCounter * 0.6662F + 3.141593F) * 2.4F * moveCycle);
/* 201:214 */       this.skirtTop.rotateAngleY = (MathHelper.cos(moveCounter * 0.6662F + 3.141593F) * 2.4F * moveCycle);
/* 202:215 */       this.bipedRightArm.rotateAngleX = (MathHelper.cos(moveCounter * 0.6662F + 3.141593F) * 1.4F * moveCycle);
/* 203:216 */       this.bipedLeftArm.rotateAngleX = (MathHelper.cos(moveCounter * 0.6662F) * 1.4F * moveCycle);
/* 204:    */     }
/* 205:225 */     this.bipedRightLeg.rotateAngleX = (MathHelper.cos(moveCounter * 0.6662F) * 0.5F * moveCycle);
/* 206:226 */     this.bipedLeftLeg.rotateAngleX = (MathHelper.cos(moveCounter * 0.6662F + 3.141593F) * 0.5F * moveCycle);
/* 207:227 */     this.bipedRightLeg.rotateAngleY = (this.bipedLeftLeg.rotateAngleY = this.bipedRightLeg.rotateAngleZ = this.bipedLeftLeg.rotateAngleZ = 0.0F);
/* 208:229 */     if (entity.isRiding())
/* 209:    */     {
/* 210:231 */       this.bipedBody.rotationPointY = -1.0F;
/* 211:232 */       this.bipedRightLeg.rotationPointY = 5.0F;
/* 212:233 */       this.bipedLeftLeg.rotationPointY = 5.0F;
/* 213:234 */       this.bipedRightArm.rotateAngleX += -0.6283186F;
/* 214:235 */       this.bipedLeftArm.rotateAngleX += -0.6283186F;
/* 215:236 */       this.bipedRightLeg.rotateAngleX = -1.256637F;
/* 216:237 */       this.bipedLeftLeg.rotateAngleX = -1.256637F;
/* 217:238 */       this.bipedRightLeg.rotateAngleY = 0.2243995F;
/* 218:239 */       this.bipedLeftLeg.rotateAngleY = -0.2243995F;
/* 219:240 */       this.bipedRightLeg.rotateAngleZ = 0.2243995F;
/* 220:241 */       this.bipedLeftLeg.rotateAngleZ = -0.2243995F;
/* 221:    */       
/* 222:243 */       this.skirtTop.rotationPointY = 5.0F;
/* 223:244 */       this.skirtTop.rotationPointZ = 0.0F;
/* 224:245 */       this.skirtTop.rotateAngleX = -0.6283186F;
/* 225:246 */       this.skirtBottom.rotationPointZ = 0.0F;
/* 226:247 */       this.skirtBottom.rotationPointY = 4.0F;
/* 227:248 */       this.skirtBottom.rotateAngleX = -0.7853982F;
/* 228:    */     }
/* 229:    */     else
/* 230:    */     {
/* 231:250 */       this.skirtTop.rotationPointY = 5.0F;
/* 232:251 */       this.skirtTop.rotationPointZ = 0.0F;
/* 233:252 */       this.skirtBottom.rotationPointZ = 0.0F;
/* 234:253 */       this.skirtBottom.rotationPointY = 4.0F;
/* 235:254 */       this.bipedRightLeg.rotationPointY = 7.0F;
/* 236:255 */       this.bipedLeftLeg.rotationPointY = 7.0F;
/* 237:    */     }
/* 238:259 */     this.bipedRightArm.rotateAngleY = (this.bipedLeftArm.rotateAngleY = 0.0F);
/* 239:    */     
/* 240:261 */     this.bipedRightLeg.rotationPointZ = (this.bipedLeftLeg.rotationPointZ = this.bipedBody.rotateAngleX = this.bipedBody.rotateAngleZ = 0.0F);
/* 241:264 */     if (entity.isSneaking()) {
/* 242:266 */       this.bipedHead.rotateAngleX -= 0.5F;
/* 243:    */     }
/* 244:    */   }
/* 245:    */   
/* 246:    */   public void setDefaultPause(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/* 247:    */   {
/* 248:276 */     if (this.bipedHead != null) {
/* 249:276 */       this.bipedHead.setRotationPoint(0.0F, 5.0F, 0.0F);
/* 250:    */     }
/* 251:278 */     if (this.bipedBody != null) {
/* 252:278 */       this.bipedBody.setRotationPoint(0.0F, -5.0F, 0.0F);
/* 253:    */     }
/* 254:279 */     if (this.bipedRightArm != null) {
/* 255:279 */       this.bipedRightArm.setRotationPoint(-3.0F, 6.0F, 0.0F);
/* 256:    */     }
/* 257:280 */     if (this.bipedLeftArm != null) {
/* 258:280 */       this.bipedLeftArm.setRotationPoint(3.0F, 6.0F, 0.0F);
/* 259:    */     }
/* 260:281 */     if (this.bipedRightLeg != null) {
/* 261:281 */       this.bipedRightLeg.setRotationPoint(-2.0F, this.bodyHeight + 5.0F, 0.0F);
/* 262:    */     }
/* 263:282 */     if (this.bipedLeftLeg != null) {
/* 264:282 */       this.bipedLeftLeg.setRotationPoint(2.0F, this.bodyHeight + 5.0F, 0.0F);
/* 265:    */     }
/* 266:    */   }
/* 267:    */   
/* 268:    */   private void setRotation(ModelRenderer model, float rotateX, float rotateY, float rotateZ)
/* 269:    */   {
/* 270:287 */     model.rotateAngleX = rotateX;
/* 271:288 */     model.rotateAngleY = rotateY;
/* 272:289 */     model.rotateAngleZ = rotateZ;
/* 273:    */   }
/* 274:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.model.living.ModelReimu
 * JD-Core Version:    0.7.0.1
 */