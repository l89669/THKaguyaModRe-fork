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
/*  12:    */ public class ModelSanae
/*  13:    */   extends ModelBase
/*  14:    */ {
/*  15:    */   ModelRenderer bipedBody;
/*  16:    */   ModelRenderer bipedRightArm;
/*  17:    */   ModelRenderer rightHand;
/*  18:    */   ModelRenderer bipedLeftArm;
/*  19:    */   ModelRenderer leftHand;
/*  20:    */   ModelRenderer bipedRightLeg;
/*  21:    */   ModelRenderer bipedLeftLeg;
/*  22:    */   ModelRenderer skirtTop;
/*  23:    */   ModelRenderer skirtBottom;
/*  24:    */   ModelRenderer bipedHead;
/*  25:    */   ModelRenderer longHair;
/*  26:    */   ModelRenderer leftBraid2;
/*  27:    */   ModelRenderer rightBraid;
/*  28:    */   ModelRenderer leftBraid;
/*  29:    */   ModelRenderer kaeruBase;
/*  30:    */   ModelRenderer kaeruEyeRight;
/*  31:    */   ModelRenderer kaeruEyeLeft;
/*  32:    */   
/*  33:    */   public ModelSanae()
/*  34:    */   {
/*  35: 36 */     this.textureWidth = 64;
/*  36: 37 */     this.textureHeight = 64;
/*  37:    */     
/*  38: 39 */     this.bipedBody = new ModelRenderer(this, 32, 8);
/*  39: 40 */     this.bipedBody.addBox(-3.0F, 0.0F, -2.0F, 6, 9, 4);
/*  40: 41 */     this.bipedBody.setRotationPoint(0.0F, 2.0F, 0.0F);
/*  41: 42 */     this.bipedBody.setTextureSize(64, 64);
/*  42: 43 */     this.bipedBody.mirror = true;
/*  43: 44 */     setRotation(this.bipedBody, 0.0F, 0.0F, 0.0F);
/*  44: 45 */     this.bipedRightArm = new ModelRenderer(this, 48, 0);
/*  45: 46 */     this.bipedRightArm.addBox(0.0F, 0.0F, -1.0F, 4, 7, 2);
/*  46: 47 */     this.bipedRightArm.setRotationPoint(-3.0F, 2.0F, 0.0F);
/*  47: 48 */     this.bipedRightArm.setTextureSize(64, 64);
/*  48: 49 */     this.bipedRightArm.mirror = true;
/*  49: 50 */     setRotation(this.bipedRightArm, 0.0F, 0.0F, 0.0F);
/*  50: 51 */     this.rightHand = new ModelRenderer(this, 52, 9);
/*  51: 52 */     this.rightHand.addBox(0.0F, 1.0F, -1.0F, 2, 1, 2);
/*  52: 53 */     this.rightHand.setRotationPoint(-0.0F, 6.0F, 0.0F);
/*  53: 54 */     this.rightHand.setTextureSize(64, 64);
/*  54: 55 */     this.rightHand.mirror = true;
/*  55: 56 */     this.bipedRightArm.addChild(this.rightHand);
/*  56: 57 */     setRotation(this.rightHand, 0.0F, 0.0F, 0.0F);
/*  57: 58 */     this.bipedLeftArm = new ModelRenderer(this, 48, 0);
/*  58: 59 */     this.bipedLeftArm.addBox(-4.0F, 0.0F, -1.0F, 4, 7, 2);
/*  59: 60 */     this.bipedLeftArm.setRotationPoint(3.0F, 2.0F, 0.0F);
/*  60: 61 */     this.bipedLeftArm.setTextureSize(64, 64);
/*  61: 62 */     this.bipedLeftArm.mirror = true;
/*  62: 63 */     setRotation(this.bipedLeftArm, 0.0F, 0.0F, 0.0F);
/*  63: 64 */     this.leftHand = new ModelRenderer(this, 52, 9);
/*  64: 65 */     this.leftHand.addBox(-2.0F, 1.0F, -1.0F, 2, 1, 2);
/*  65: 66 */     this.leftHand.setRotationPoint(0.0F, 6.0F, 0.0F);
/*  66: 67 */     this.leftHand.setTextureSize(64, 64);
/*  67: 68 */     this.leftHand.mirror = true;
/*  68: 69 */     this.bipedLeftArm.addChild(this.leftHand);
/*  69: 70 */     setRotation(this.leftHand, 0.0F, 0.0F, 0.0F);
/*  70: 71 */     this.bipedRightLeg = new ModelRenderer(this, 50, 18);
/*  71: 72 */     this.bipedRightLeg.addBox(-1.0F, 0.0F, -2.0F, 3, 13, 4);
/*  72: 73 */     this.bipedRightLeg.setRotationPoint(-2.0F, 11.0F, 0.0F);
/*  73: 74 */     this.bipedRightLeg.setTextureSize(64, 64);
/*  74: 75 */     this.bipedRightLeg.mirror = true;
/*  75: 76 */     setRotation(this.bipedRightLeg, 0.0F, 0.0F, 0.0F);
/*  76: 77 */     this.bipedLeftLeg = new ModelRenderer(this, 50, 18);
/*  77: 78 */     this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 3, 13, 4);
/*  78: 79 */     this.bipedLeftLeg.setRotationPoint(2.0F, 11.0F, 0.0F);
/*  79: 80 */     this.bipedLeftLeg.setTextureSize(64, 64);
/*  80: 81 */     this.bipedLeftLeg.mirror = true;
/*  81: 82 */     setRotation(this.bipedLeftLeg, 0.0F, 0.0F, 0.0F);
/*  82: 83 */     this.skirtTop = new ModelRenderer(this, 0, 16);
/*  83: 84 */     this.skirtTop.addBox(-4.0F, -2.0F, -4.0F, 8, 4, 8);
/*  84: 85 */     this.skirtTop.setRotationPoint(0.0F, 8.0F, 0.0F);
/*  85: 86 */     this.skirtTop.setTextureSize(64, 64);
/*  86: 87 */     this.skirtTop.mirror = true;
/*  87: 88 */     this.bipedBody.addChild(this.skirtTop);
/*  88: 89 */     setRotation(this.skirtTop, 0.0F, 0.0F, 0.0F);
/*  89: 90 */     this.skirtBottom = new ModelRenderer(this, 16, 32);
/*  90: 91 */     this.skirtBottom.addBox(-5.0F, -2.0F, -5.0F, 10, 8, 10);
/*  91: 92 */     this.skirtBottom.setRotationPoint(0.0F, 4.0F, 0.0F);
/*  92: 93 */     this.skirtBottom.setTextureSize(64, 64);
/*  93: 94 */     this.skirtBottom.mirror = true;
/*  94: 95 */     this.skirtTop.addChild(this.skirtBottom);
/*  95: 96 */     setRotation(this.skirtBottom, 0.0F, 0.0F, 0.0F);
/*  96: 97 */     this.bipedHead = new ModelRenderer(this, 0, 0);
/*  97: 98 */     this.bipedHead.addBox(-4.0F, -6.0F, -4.0F, 8, 8, 8);
/*  98: 99 */     this.bipedHead.setRotationPoint(0.0F, 2.0F, 0.0F);
/*  99:100 */     this.bipedHead.setTextureSize(64, 64);
/* 100:101 */     this.bipedHead.mirror = true;
/* 101:102 */     setRotation(this.bipedHead, 0.0F, 0.0F, 0.0F);
/* 102:103 */     this.longHair = new ModelRenderer(this, 0, 50);
/* 103:104 */     this.longHair.addBox(-4.0F, 1.0F, -3.0F, 8, 9, 3);
/* 104:105 */     this.longHair.setRotationPoint(0.0F, -1.0F, 4.0F);
/* 105:106 */     this.longHair.setTextureSize(64, 64);
/* 106:107 */     this.longHair.mirror = true;
/* 107:108 */     this.bipedHead.addChild(this.longHair);
/* 108:109 */     setRotation(this.longHair, 0.20944F, 0.0F, 0.0F);
/* 109:110 */     this.leftBraid = new ModelRenderer(this, 4, 32);
/* 110:111 */     this.leftBraid.addBox(0.0F, 1.0F, -1.0F, 1, 5, 1);
/* 111:112 */     this.leftBraid.setRotationPoint(3.0F, -3.0F, -3.0F);
/* 112:113 */     this.leftBraid.setTextureSize(64, 64);
/* 113:114 */     this.leftBraid.mirror = true;
/* 114:115 */     this.bipedHead.addChild(this.leftBraid);
/* 115:116 */     setRotation(this.leftBraid, -0.244346F, 0.0F, 0.0F);
/* 116:117 */     this.rightBraid = new ModelRenderer(this, 0, 32);
/* 117:118 */     this.rightBraid.addBox(-1.0F, 1.0F, -1.0F, 1, 5, 1);
/* 118:119 */     this.rightBraid.setRotationPoint(-3.0F, -3.0F, -3.0F);
/* 119:120 */     this.rightBraid.setTextureSize(64, 64);
/* 120:121 */     this.rightBraid.mirror = true;
/* 121:122 */     this.bipedHead.addChild(this.rightBraid);
/* 122:123 */     setRotation(this.rightBraid, -0.244346F, 0.0F, 0.0F);
/* 123:124 */     this.leftBraid2 = new ModelRenderer(this, 8, 31);
/* 124:125 */     this.leftBraid2.addBox(-1.0F, 2.0F, -1.0F, 2, 5, 2);
/* 125:126 */     this.leftBraid2.setRotationPoint(3.0F, 0.0F, -4.0F);
/* 126:127 */     this.leftBraid2.setTextureSize(64, 64);
/* 127:128 */     this.leftBraid2.mirror = true;
/* 128:129 */     this.bipedHead.addChild(this.leftBraid2);
/* 129:130 */     setRotation(this.leftBraid2, -0.244346F, 0.0F, 0.0F);
/* 130:131 */     this.kaeruBase = new ModelRenderer(this, 0, 38);
/* 131:132 */     this.kaeruBase.addBox(-2.0F, 1.0F, -1.0F, 3, 2, 1);
/* 132:133 */     this.kaeruBase.setRotationPoint(3.0F, -7.0F, -4.0F);
/* 133:134 */     this.kaeruBase.setTextureSize(64, 64);
/* 134:135 */     this.kaeruBase.mirror = true;
/* 135:136 */     this.bipedHead.addChild(this.kaeruBase);
/* 136:137 */     setRotation(this.kaeruBase, 0.0F, 0.0F, 0.3839724F);
/* 137:138 */     this.kaeruEyeRight = new ModelRenderer(this, 8, 38);
/* 138:139 */     this.kaeruEyeRight.addBox(-1.0F, 2.0F, -1.0F, 1, 1, 1);
/* 139:140 */     this.kaeruEyeRight.setRotationPoint(2.7F, -9.0F, -4.0F);
/* 140:141 */     this.kaeruEyeRight.setTextureSize(64, 64);
/* 141:142 */     this.kaeruEyeRight.mirror = true;
/* 142:143 */     this.bipedHead.addChild(this.kaeruEyeRight);
/* 143:144 */     setRotation(this.kaeruEyeRight, 0.0F, 0.0F, 0.3839724F);
/* 144:145 */     this.kaeruEyeLeft = new ModelRenderer(this, 8, 38);
/* 145:146 */     this.kaeruEyeLeft.addBox(0.0F, 2.0F, -1.0F, 1, 1, 1);
/* 146:147 */     this.kaeruEyeLeft.setRotationPoint(3.7F, -8.7F, -4.0F);
/* 147:148 */     this.kaeruEyeLeft.setTextureSize(64, 64);
/* 148:149 */     this.kaeruEyeLeft.mirror = true;
/* 149:150 */     this.bipedHead.addChild(this.kaeruEyeLeft);
/* 150:151 */     setRotation(this.kaeruEyeLeft, 0.0F, 0.0F, 0.3839724F);
/* 151:    */   }
/* 152:    */   
/* 153:    */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/* 154:    */   {
/* 155:156 */     super.render(entity, f, f1, f2, f3, f4, f5);
/* 156:157 */     setRotationAngles(f, f1, f2, f3, f4, f5, entity);
/* 157:158 */     this.bipedBody.render(f5);
/* 158:159 */     this.bipedRightArm.render(f5);
/* 159:    */     
/* 160:161 */     this.bipedLeftArm.render(f5);
/* 161:    */     
/* 162:163 */     this.bipedRightLeg.render(f5);
/* 163:164 */     this.bipedLeftLeg.render(f5);
/* 164:    */     
/* 165:    */ 
/* 166:167 */     this.bipedHead.render(f5);
/* 167:    */     
/* 168:    */ 
/* 169:    */ 
/* 170:    */ 
/* 171:    */ 
/* 172:    */ 
/* 173:    */ 
/* 174:    */ 
/* 175:176 */     EntityDanmakuMob danmakuMob = (EntityDanmakuMob)entity;
/* 176:179 */     if (danmakuMob.getSpellCardMotion() < -15)
/* 177:    */     {
/* 178:181 */       this.bipedRightArm.rotateAngleX = (-0.7F - MathHelper.sin(danmakuMob.ticksExisted / 10.0F) * 0.1F - MathHelper.sin((30.0F + danmakuMob.getSpellCardMotion()) / 180.0F * 3.141593F) * 4.0F);
/* 179:    */     }
/* 180:183 */     else if (danmakuMob.getSpellCardMotion() < 0)
/* 181:    */     {
/* 182:185 */       this.bipedRightArm.rotateAngleX = (-0.7F - MathHelper.sin(danmakuMob.ticksExisted / 10.0F) * 0.1F - MathHelper.sin(-danmakuMob.getSpellCardMotion() / 180.0F * 3.141593F) * 4.0F);
/* 183:    */     }
/* 184:    */     else
/* 185:    */     {
/* 186:189 */       this.bipedRightArm.rotateAngleX = -0.2094395F;
/* 187:190 */       this.bipedRightArm.rotateAngleY = -0.1745329F;
/* 188:191 */       this.bipedRightArm.rotateAngleZ = 0.5235988F;
/* 189:    */       
/* 190:193 */       this.bipedLeftArm.rotateAngleX = this.bipedRightArm.rotateAngleX;
/* 191:194 */       this.bipedLeftArm.rotateAngleY = (-this.bipedRightArm.rotateAngleY);
/* 192:195 */       this.bipedLeftArm.rotateAngleZ = (-this.bipedRightArm.rotateAngleZ);
/* 193:    */     }
/* 194:    */   }
/* 195:    */   
/* 196:    */   private void setRotation(ModelRenderer model, float rotateX, float rotateY, float rotateZ)
/* 197:    */   {
/* 198:209 */     model.rotateAngleX = rotateX;
/* 199:210 */     model.rotateAngleY = rotateY;
/* 200:211 */     model.rotateAngleZ = rotateZ;
/* 201:    */   }
/* 202:    */   
/* 203:    */   public void setRotationAngles(float movement, float far, float f2, float f3, float f4, float f5, Entity entity)
/* 204:    */   {
/* 205:217 */     this.skirtTop.rotateAngleX = 0.0F;
/* 206:218 */     this.bipedHead.rotateAngleY = (f3 / 57.295776F);
/* 207:219 */     this.bipedHead.rotateAngleX = (f4 / 57.295776F);
/* 208:220 */     this.bipedBody.rotateAngleY = (MathHelper.sin(MathHelper.sqrt_float(this.onGround) * 3.141593F * 2.0F) * 0.2F);
/* 209:    */     
/* 210:222 */     this.bipedRightLeg.rotateAngleX = (MathHelper.cos(movement * 0.6662F) * 0.7F * far);
/* 211:223 */     this.bipedLeftLeg.rotateAngleX = (MathHelper.cos(movement * 0.6662F + 3.141593F) * 0.7F * far);
/* 212:224 */     this.bipedRightLeg.rotateAngleY = 0.0F;
/* 213:225 */     this.bipedLeftLeg.rotateAngleY = 0.0F;
/* 214:226 */     this.bipedRightLeg.rotateAngleZ = 0.0F;
/* 215:227 */     this.bipedLeftLeg.rotateAngleZ = 0.0F;
/* 216:    */     
/* 217:229 */     this.bipedRightArm.rotateAngleX = (MathHelper.cos(movement * 0.6662F + 3.141593F) * 2.0F * far * 0.5F);
/* 218:230 */     this.bipedLeftArm.rotateAngleX = (MathHelper.cos(movement * 0.6662F) * 2.0F * far * 0.5F);
/* 219:231 */     this.bipedRightArm.rotateAngleY = -0.1745329F;
/* 220:232 */     this.bipedRightArm.rotateAngleZ = 0.5235988F;
/* 221:233 */     this.bipedLeftArm.rotateAngleY = (-this.bipedRightArm.rotateAngleY);
/* 222:234 */     this.bipedLeftArm.rotateAngleZ = (-this.bipedRightArm.rotateAngleZ);
/* 223:236 */     if (this.isRiding)
/* 224:    */     {
/* 225:238 */       this.bipedRightArm.rotateAngleX = -0.6283186F;
/* 226:239 */       this.bipedLeftArm.rotateAngleX = -0.6283186F;
/* 227:240 */       this.bipedRightLeg.rotateAngleX = -1.256637F;
/* 228:241 */       this.bipedLeftLeg.rotateAngleX = -1.256637F;
/* 229:242 */       this.bipedRightLeg.rotateAngleY = 0.2243995F;
/* 230:243 */       this.bipedLeftLeg.rotateAngleY = -0.2243995F;
/* 231:244 */       this.bipedRightLeg.rotateAngleZ = 0.2243995F;
/* 232:245 */       this.bipedLeftLeg.rotateAngleZ = -0.2243995F;
/* 233:    */       
/* 234:247 */       this.skirtTop.rotateAngleX += -0.6283186F;
/* 235:    */     }
/* 236:273 */     this.bipedRightArm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
/* 237:274 */     this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
/* 238:275 */     this.bipedRightArm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
/* 239:276 */     this.bipedLeftArm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
/* 240:    */   }
/* 241:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.model.living.ModelSanae
 * JD-Core Version:    0.7.0.1
 */