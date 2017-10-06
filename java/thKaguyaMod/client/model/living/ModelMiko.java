/*   1:    */ package thKaguyaMod.client.model.living;
/*   2:    */ 
/*   3:    */ import net.minecraft.client.model.ModelBase;
/*   4:    */ import net.minecraft.client.model.ModelRenderer;
/*   5:    */ import net.minecraft.entity.Entity;
/*   6:    */ import net.minecraft.util.MathHelper;
/*   7:    */ 
/*   8:    */ public class ModelMiko
/*   9:    */   extends ModelBase
/*  10:    */ {
/*  11:    */   public ModelRenderer bipedHead;
/*  12:    */   public ModelRenderer bipedBody;
/*  13:    */   public ModelRenderer bipedRightArm;
/*  14:    */   public ModelRenderer bipedLeftArm;
/*  15:    */   public ModelRenderer bipedRightLeg;
/*  16:    */   public ModelRenderer bipedLeftLeg;
/*  17:    */   public ModelRenderer skirtTop;
/*  18:    */   public ModelRenderer skirtBottom;
/*  19:    */   public ModelRenderer cape;
/*  20:    */   public ModelRenderer Manto1;
/*  21:    */   public ModelRenderer Manto2;
/*  22:    */   public ModelRenderer Manto3;
/*  23:    */   public ModelRenderer Manto4;
/*  24:    */   public ModelRenderer longHair;
/*  25:    */   public ModelRenderer leftBraid;
/*  26:    */   public ModelRenderer rightBraid;
/*  27:    */   public ModelRenderer MimiateRight;
/*  28:    */   public ModelRenderer MimiateLeft;
/*  29:    */   public ModelRenderer HearLeft1;
/*  30:    */   public ModelRenderer HearLeft2;
/*  31:    */   public ModelRenderer HearRight1;
/*  32:    */   public ModelRenderer HearRight2;
/*  33:    */   public ModelRenderer eyeR;
/*  34:    */   public ModelRenderer eyeL;
/*  35: 38 */   protected float legHeight = 12.0F;
/*  36: 39 */   protected float bodyHeight = 9.0F;
/*  37:    */   
/*  38:    */   public ModelMiko()
/*  39:    */   {
/*  40: 43 */     this(0.0F);
/*  41:    */   }
/*  42:    */   
/*  43:    */   public ModelMiko(float size)
/*  44:    */   {
/*  45: 48 */     this(size, 0.0F, 64, 64);
/*  46:    */   }
/*  47:    */   
/*  48:    */   public ModelMiko(float size, float yOffset)
/*  49:    */   {
/*  50: 53 */     this(size, yOffset, 64, 64);
/*  51:    */   }
/*  52:    */   
/*  53:    */   public ModelMiko(float size, float yOffset, int textureWidth, int textureHeight)
/*  54:    */   {
/*  55: 59 */     this.textureHeight = 64;
/*  56: 60 */     this.textureWidth = 64;
/*  57:    */     
/*  58:    */ 
/*  59: 63 */     this.bipedBody = new ModelRenderer(this, 32, 8);
/*  60: 64 */     this.bipedBody.addBox(-3.0F, 0.0F, -2.0F, 6, 9, 4);
/*  61: 65 */     this.bipedBody.setRotationPoint(0.0F, 3.0F, 0.0F);
/*  62: 66 */     this.bipedBody.setTextureSize(64, 64);
/*  63: 67 */     this.bipedBody.mirror = true;
/*  64: 68 */     setRotation(this.bipedBody, 0.0F, 0.0F, 0.0F);
/*  65: 69 */     this.bipedRightArm = new ModelRenderer(this, 56, 0);
/*  66: 70 */     this.bipedRightArm.addBox(-0.8F, -1.0F, -1.0F, 2, 8, 2);
/*  67: 71 */     this.bipedRightArm.setRotationPoint(-4.0F, 4.0F, 0.0F);
/*  68: 72 */     this.bipedRightArm.setTextureSize(64, 64);
/*  69: 73 */     this.bipedRightArm.mirror = true;
/*  70: 74 */     setRotation(this.bipedRightArm, 0.0F, 0.0F, 0.0F);
/*  71: 75 */     this.bipedLeftArm = new ModelRenderer(this, 56, 0);
/*  72: 76 */     this.bipedLeftArm.addBox(-1.2F, -1.0F, -1.0F, 2, 8, 2);
/*  73: 77 */     this.bipedLeftArm.setRotationPoint(4.0F, 4.0F, 0.0F);
/*  74: 78 */     this.bipedLeftArm.setTextureSize(64, 64);
/*  75: 79 */     this.bipedLeftArm.mirror = true;
/*  76: 80 */     setRotation(this.bipedLeftArm, 0.0F, 0.0F, 0.0F);
/*  77: 81 */     this.bipedRightLeg = new ModelRenderer(this, 50, 18);
/*  78: 82 */     this.bipedRightLeg.addBox(-1.0F, 0.0F, -2.0F, 3, 12, 4);
/*  79: 83 */     this.bipedRightLeg.setRotationPoint(-2.0F, 9.0F, 0.0F);
/*  80: 84 */     this.bipedRightLeg.setTextureSize(64, 64);
/*  81: 85 */     this.bipedRightLeg.mirror = true;
/*  82: 86 */     setRotation(this.bipedRightLeg, 0.0F, 0.0F, 0.0F);
/*  83: 87 */     this.bipedLeftLeg = new ModelRenderer(this, 50, 18);
/*  84: 88 */     this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 3, 12, 4);
/*  85: 89 */     this.bipedLeftLeg.setRotationPoint(2.0F, 0.0F, 0.0F);
/*  86: 90 */     this.bipedLeftLeg.setTextureSize(64, 64);
/*  87: 91 */     this.bipedLeftLeg.mirror = true;
/*  88: 92 */     setRotation(this.bipedLeftLeg, 0.0F, 0.0F, 0.0F);
/*  89: 93 */     this.skirtTop = new ModelRenderer(this, 0, 16);
/*  90: 94 */     this.skirtTop.addBox(-4.0F, 0.0F, -6.0F, 8, 4, 8);
/*  91: 95 */     this.skirtTop.setRotationPoint(0.0F, 6.0F, 2.0F);
/*  92: 96 */     this.skirtTop.setTextureSize(64, 64);
/*  93: 97 */     this.skirtTop.mirror = true;
/*  94: 98 */     setRotation(this.skirtTop, 0.0F, 0.0F, 0.0F);
/*  95: 99 */     this.skirtBottom = new ModelRenderer(this, 16, 31);
/*  96:100 */     this.skirtBottom.addBox(-5.0F, 0.0F, -10.0F, 10, 8, 10);
/*  97:101 */     this.skirtBottom.setRotationPoint(0.0F, 4.0F, 3.0F);
/*  98:102 */     this.skirtBottom.setTextureSize(64, 64);
/*  99:103 */     this.skirtBottom.mirror = true;
/* 100:104 */     setRotation(this.skirtBottom, 0.0F, 0.0F, 0.0F);
/* 101:105 */     this.cape = new ModelRenderer(this, 30, 54);
/* 102:106 */     this.cape.addBox(-5.0F, -4.0F, -2.5F, 10, 3, 7);
/* 103:107 */     this.cape.setRotationPoint(0.0F, 2.0F, 0.0F);
/* 104:108 */     this.cape.setTextureSize(64, 64);
/* 105:109 */     this.cape.mirror = true;
/* 106:110 */     setRotation(this.cape, 0.0F, 0.0F, 0.0F);
/* 107:111 */     this.Manto1 = new ModelRenderer(this, 0, 49);
/* 108:112 */     this.Manto1.addBox(-5.5F, 0.0F, -1.0F, 11, 3, 1);
/* 109:113 */     this.Manto1.setRotationPoint(0.0F, 0.0F, 3.0F);
/* 110:114 */     this.Manto1.setTextureSize(64, 64);
/* 111:115 */     this.Manto1.mirror = true;
/* 112:116 */     setRotation(this.Manto1, 0.0F, 0.0F, 0.0F);
/* 113:117 */     this.Manto2 = new ModelRenderer(this, 0, 53);
/* 114:118 */     this.Manto2.addBox(-6.0F, 0.0F, -1.0F, 12, 4, 1);
/* 115:119 */     this.Manto2.setRotationPoint(0.0F, 3.0F, 0.0F);
/* 116:120 */     this.Manto2.setTextureSize(64, 64);
/* 117:121 */     this.Manto2.mirror = true;
/* 118:122 */     setRotation(this.Manto2, 0.0F, 0.0F, 0.0F);
/* 119:123 */     this.Manto3 = new ModelRenderer(this, 0, 58);
/* 120:124 */     this.Manto3.addBox(-6.5F, 0.0F, -1.0F, 13, 5, 1);
/* 121:125 */     this.Manto3.setRotationPoint(0.0F, 4.0F, 0.0F);
/* 122:126 */     this.Manto3.setTextureSize(64, 64);
/* 123:127 */     this.Manto3.mirror = true;
/* 124:128 */     setRotation(this.Manto3, 0.0F, 0.0F, 0.0F);
/* 125:129 */     this.Manto4 = new ModelRenderer(this, 0, 58);
/* 126:130 */     this.Manto4.addBox(-6.5F, 0.0F, -1.0F, 13, 5, 1);
/* 127:131 */     this.Manto4.setRotationPoint(0.0F, 5.0F, 0.0F);
/* 128:132 */     this.Manto4.setTextureSize(64, 64);
/* 129:133 */     this.Manto4.mirror = true;
/* 130:134 */     setRotation(this.Manto4, 0.0F, 0.0F, 0.0F);
/* 131:135 */     this.bipedHead = new ModelRenderer(this, 0, 0);
/* 132:136 */     this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
/* 133:137 */     this.bipedHead.setRotationPoint(0.0F, 3.0F, 0.0F);
/* 134:138 */     this.bipedHead.setTextureSize(64, 64);
/* 135:139 */     this.bipedHead.mirror = true;
/* 136:140 */     setRotation(this.bipedHead, 0.0F, 0.0F, 0.0F);
/* 137:141 */     this.longHair = new ModelRenderer(this, 0, 28);
/* 138:142 */     this.longHair.addBox(-4.0F, -2.0F, -3.0F, 8, 3, 3);
/* 139:143 */     this.longHair.setRotationPoint(0.0F, 0.0F, 4.0F);
/* 140:144 */     this.longHair.setTextureSize(64, 64);
/* 141:145 */     this.longHair.mirror = true;
/* 142:146 */     setRotation(this.longHair, 0.20944F, 0.0F, 0.0F);
/* 143:147 */     this.leftBraid = new ModelRenderer(this, 0, 16);
/* 144:148 */     this.leftBraid.addBox(0.0F, -3.0F, -1.0F, 1, 4, 1);
/* 145:149 */     this.leftBraid.setRotationPoint(3.0F, 0.0F, -3.0F);
/* 146:150 */     this.leftBraid.setTextureSize(64, 64);
/* 147:151 */     this.leftBraid.mirror = true;
/* 148:152 */     setRotation(this.leftBraid, -0.244346F, 0.0F, 0.0F);
/* 149:153 */     this.rightBraid = new ModelRenderer(this, 0, 16);
/* 150:154 */     this.rightBraid.addBox(-1.0F, -3.0F, -1.0F, 1, 4, 1);
/* 151:155 */     this.rightBraid.setRotationPoint(-3.0F, 0.0F, -3.0F);
/* 152:156 */     this.rightBraid.setTextureSize(64, 64);
/* 153:157 */     this.rightBraid.mirror = true;
/* 154:158 */     setRotation(this.rightBraid, -0.244346F, 0.0F, 0.0F);
/* 155:159 */     this.MimiateRight = new ModelRenderer(this, 0, 41);
/* 156:160 */     this.MimiateRight.addBox(-2.0F, -5.0F, -1.0F, 4, 4, 1);
/* 157:161 */     this.MimiateRight.setRotationPoint(-4.0F, -1.0F, 0.0F);
/* 158:162 */     this.MimiateRight.setTextureSize(64, 64);
/* 159:163 */     this.MimiateRight.mirror = true;
/* 160:164 */     setRotation(this.MimiateRight, 0.0F, 1.570796F, 0.0F);
/* 161:165 */     this.MimiateLeft = new ModelRenderer(this, 0, 41);
/* 162:166 */     this.MimiateLeft.addBox(-2.0F, -5.0F, -1.0F, 4, 4, 1);
/* 163:167 */     this.MimiateLeft.setRotationPoint(4.0F, -1.0F, 0.0F);
/* 164:168 */     this.MimiateLeft.setTextureSize(64, 64);
/* 165:169 */     this.MimiateLeft.mirror = true;
/* 166:170 */     setRotation(this.MimiateLeft, 0.0F, -1.570796F, 0.0F);
/* 167:171 */     this.HearLeft1 = new ModelRenderer(this, 24, 0);
/* 168:172 */     this.HearLeft1.addBox(0.0F, -5.0F, -2.0F, 4, 2, 4);
/* 169:173 */     this.HearLeft1.setRotationPoint(0.0F, -5.0F, 0.0F);
/* 170:174 */     this.HearLeft1.setTextureSize(64, 64);
/* 171:175 */     this.HearLeft1.mirror = true;
/* 172:176 */     setRotation(this.HearLeft1, 0.0F, 0.0F, 0.1745329F);
/* 173:177 */     this.HearLeft2 = new ModelRenderer(this, 38, 1);
/* 174:178 */     this.HearLeft2.addBox(0.0F, -1.0F, -1.5F, 3, 3, 3);
/* 175:179 */     this.HearLeft2.setRotationPoint(1.0F, -7.0F, 0.0F);
/* 176:180 */     this.HearLeft2.setTextureSize(64, 64);
/* 177:181 */     this.HearLeft2.mirror = true;
/* 178:182 */     setRotation(this.HearLeft2, 0.0F, 0.0F, 0.1745329F);
/* 179:183 */     this.HearRight1 = new ModelRenderer(this, 24, 0);
/* 180:184 */     this.HearRight1.addBox(-4.0F, -5.0F, -2.0F, 4, 2, 4);
/* 181:185 */     this.HearRight1.setRotationPoint(0.0F, -5.0F, 0.0F);
/* 182:186 */     this.HearRight1.setTextureSize(64, 64);
/* 183:187 */     this.HearRight1.mirror = true;
/* 184:188 */     setRotation(this.HearRight1, 0.0F, 0.0F, -0.1745329F);
/* 185:189 */     this.HearRight2 = new ModelRenderer(this, 38, 1);
/* 186:190 */     this.HearRight2.addBox(-3.0F, -1.0F, -1.5F, 3, 3, 3);
/* 187:191 */     this.HearRight2.setRotationPoint(-1.0F, -7.0F, 0.0F);
/* 188:192 */     this.HearRight2.setTextureSize(64, 64);
/* 189:193 */     this.HearRight2.mirror = true;
/* 190:194 */     setRotation(this.HearRight2, 0.0F, 0.0F, -0.1745329F);
/* 191:    */     
/* 192:196 */     this.bipedBody.addChild(this.bipedHead);
/* 193:197 */     this.bipedBody.addChild(this.bipedRightArm);
/* 194:198 */     this.bipedBody.addChild(this.bipedLeftArm);
/* 195:199 */     this.bipedBody.addChild(this.bipedRightLeg);
/* 196:200 */     this.bipedBody.addChild(this.bipedLeftLeg);
/* 197:    */     
/* 198:202 */     this.bipedHead.addChild(this.rightBraid);
/* 199:203 */     this.bipedHead.addChild(this.leftBraid);
/* 200:204 */     this.bipedHead.addChild(this.MimiateRight);
/* 201:205 */     this.bipedHead.addChild(this.MimiateLeft);
/* 202:206 */     this.bipedHead.addChild(this.HearLeft1);
/* 203:207 */     this.HearLeft1.addChild(this.HearLeft2);
/* 204:208 */     this.bipedHead.addChild(this.HearRight1);
/* 205:209 */     this.HearRight1.addChild(this.HearRight2);
/* 206:210 */     this.bipedBody.addChild(this.Manto1);
/* 207:211 */     this.Manto1.addChild(this.Manto2);
/* 208:212 */     this.Manto2.addChild(this.Manto3);
/* 209:213 */     this.Manto3.addChild(this.Manto4);
/* 210:214 */     this.bipedBody.addChild(this.cape);
/* 211:215 */     this.bipedHead.addChild(this.longHair);
/* 212:216 */     this.bipedBody.addChild(this.skirtTop);
/* 213:217 */     this.skirtTop.addChild(this.skirtBottom);
/* 214:    */   }
/* 215:    */   
/* 216:    */   public void render(Entity entity, float moveCounter, float moveCycle, float ticksExisted, float headYaw, float headPitch, float scale)
/* 217:    */   {
/* 218:226 */     setRotationAngles(moveCounter, moveCycle, ticksExisted, headYaw, headPitch, scale, entity);
/* 219:227 */     this.bipedBody.render(scale);
/* 220:    */   }
/* 221:    */   
/* 222:    */   public void setRotationAngles(float moveCounter, float moveCycle, float ticksExisted, float pheadYaw, float pheadPitch, float scale, Entity entity)
/* 223:    */   {
/* 224:240 */     setDefaultPause(moveCounter, moveCycle, ticksExisted, pheadYaw, pheadPitch, scale, entity);
/* 225:241 */     this.bipedBody.rotateAngleX = 0.0F;
/* 226:242 */     this.bipedHead.rotateAngleY = (pheadYaw / 57.29578F);
/* 227:243 */     this.bipedHead.rotateAngleX = (pheadPitch / 57.29578F);
/* 228:    */     
/* 229:245 */     this.bipedRightArm.rotateAngleX = (MathHelper.cos(moveCounter * 0.6662F + 3.141593F) * 2.0F * moveCycle * 0.5F);
/* 230:246 */     this.bipedRightArm.rotateAngleY = 1.570797F;
/* 231:247 */     this.bipedLeftArm.rotateAngleX = (MathHelper.cos(moveCounter * 0.6662F) * 2.0F * moveCycle * 0.5F);
/* 232:248 */     this.bipedLeftArm.rotateAngleY = 1.570797F;
/* 233:249 */     this.bipedRightArm.rotateAngleZ = 0.349066F;
/* 234:250 */     this.bipedLeftArm.rotateAngleZ = -0.349066F;
/* 235:    */     
/* 236:    */ 
/* 237:    */ 
/* 238:    */ 
/* 239:255 */     this.bipedRightLeg.rotateAngleX = (MathHelper.cos(moveCounter * 0.6662F) * 0.5F * moveCycle);
/* 240:256 */     this.bipedLeftLeg.rotateAngleX = (MathHelper.cos(moveCounter * 0.6662F + 3.141593F) * 0.5F * moveCycle);
/* 241:257 */     this.bipedRightLeg.rotateAngleY = (this.bipedLeftLeg.rotateAngleY = this.bipedRightLeg.rotateAngleZ = this.bipedLeftLeg.rotateAngleZ = 0.0F);
/* 242:    */     
/* 243:259 */     this.skirtTop.rotateAngleX = 0.0F;
/* 244:260 */     this.skirtBottom.rotateAngleX = 0.0F;
/* 245:    */     
/* 246:    */ 
/* 247:263 */     this.Manto1.rotateAngleX = ((float)Math.sin((moveCounter + ticksExisted) / 10.0D) * 0.1F + 0.3F);
/* 248:264 */     this.Manto2.rotateAngleX = ((float)Math.sin((moveCounter * 1.2F + ticksExisted) / 7.8D) * 0.25F + 0.25F);
/* 249:265 */     this.Manto3.rotateAngleX = ((float)Math.sin((moveCounter * 1.5F + ticksExisted) / 5.3D) * 0.3F + 0.2F);
/* 250:266 */     this.Manto4.rotateAngleX = ((float)Math.sin((moveCounter * 2.0F + ticksExisted) / 4.1D) * 0.34F + 0.0F);
/* 251:286 */     if (entity.isRiding())
/* 252:    */     {
/* 253:289 */       this.bipedBody.rotationPointY = -1.0F;
/* 254:290 */       this.bipedRightArm.rotateAngleX += -0.6283186F;
/* 255:291 */       this.bipedLeftArm.rotateAngleX += -0.6283186F;
/* 256:292 */       this.bipedRightLeg.rotateAngleX = -1.256637F;
/* 257:293 */       this.bipedLeftLeg.rotateAngleX = -1.256637F;
/* 258:294 */       this.bipedRightLeg.rotateAngleY = 0.2243995F;
/* 259:295 */       this.bipedLeftLeg.rotateAngleY = -0.2243995F;
/* 260:296 */       this.bipedRightLeg.rotateAngleZ = 0.2243995F;
/* 261:297 */       this.bipedLeftLeg.rotateAngleZ = -0.2243995F;
/* 262:    */       
/* 263:299 */       this.skirtTop.rotateAngleX = -0.3490659F;
/* 264:300 */       this.skirtBottom.rotateAngleX = -0.5235988F;
/* 265:    */     }
/* 266:305 */     this.bipedRightArm.rotateAngleY = (this.bipedLeftArm.rotateAngleY = 0.0F);
/* 267:    */   }
/* 268:    */   
/* 269:    */   public void setDefaultPause(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/* 270:    */   {
/* 271:313 */     if (this.bipedHead != null) {
/* 272:313 */       this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 273:    */     }
/* 274:315 */     if (this.bipedBody != null) {
/* 275:315 */       this.bipedBody.setRotationPoint(0.0F, 3.0F, 0.0F);
/* 276:    */     }
/* 277:316 */     if (this.bipedRightArm != null) {
/* 278:316 */       this.bipedRightArm.setRotationPoint(-3.0F, 1.0F, 0.0F);
/* 279:    */     }
/* 280:317 */     if (this.bipedLeftArm != null) {
/* 281:317 */       this.bipedLeftArm.setRotationPoint(3.0F, 1.0F, 0.0F);
/* 282:    */     }
/* 283:318 */     if (this.bipedRightLeg != null) {
/* 284:318 */       this.bipedRightLeg.setRotationPoint(-2.0F, this.bodyHeight, 0.0F);
/* 285:    */     }
/* 286:319 */     if (this.bipedLeftLeg != null) {
/* 287:319 */       this.bipedLeftLeg.setRotationPoint(2.0F, this.bodyHeight, 0.0F);
/* 288:    */     }
/* 289:    */   }
/* 290:    */   
/* 291:    */   private void setRotation(ModelRenderer model, float rotateX, float rotateY, float rotateZ)
/* 292:    */   {
/* 293:325 */     model.rotateAngleX = rotateX;
/* 294:326 */     model.rotateAngleY = rotateY;
/* 295:327 */     model.rotateAngleZ = rotateZ;
/* 296:    */   }
/* 297:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.model.living.ModelMiko
 * JD-Core Version:    0.7.0.1
 */