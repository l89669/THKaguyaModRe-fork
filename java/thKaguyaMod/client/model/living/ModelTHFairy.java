/*   1:    */ package thKaguyaMod.client.model.living;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.relauncher.Side;
/*   4:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   5:    */ import net.minecraft.client.model.ModelBase;
/*   6:    */ import net.minecraft.client.model.ModelRenderer;
/*   7:    */ import net.minecraft.entity.Entity;
/*   8:    */ import net.minecraft.util.MathHelper;
/*   9:    */ import thKaguyaMod.entity.living.EntityTHFairy;
/*  10:    */ 
/*  11:    */ @SideOnly(Side.CLIENT)
/*  12:    */ public class ModelTHFairy
/*  13:    */   extends ModelBase
/*  14:    */ {
/*  15:    */   ModelRenderer head;
/*  16:    */   ModelRenderer body;
/*  17:    */   ModelRenderer rightArm;
/*  18:    */   ModelRenderer rightLeg;
/*  19:    */   ModelRenderer leftArm;
/*  20:    */   ModelRenderer leftLeg;
/*  21:    */   ModelRenderer skirt;
/*  22:    */   ModelRenderer ribbonCenter;
/*  23:    */   ModelRenderer longHair;
/*  24:    */   ModelRenderer ribbonLeft;
/*  25:    */   ModelRenderer ribbonRight;
/*  26:    */   ModelRenderer rightWing;
/*  27:    */   ModelRenderer rightWing2;
/*  28:    */   ModelRenderer leftWing;
/*  29:    */   ModelRenderer leftWing2;
/*  30:    */   ModelRenderer flowerCenter;
/*  31:    */   ModelRenderer flower1;
/*  32:    */   ModelRenderer flower2;
/*  33:    */   ModelRenderer flower3;
/*  34:    */   ModelRenderer flower4;
/*  35:    */   
/*  36:    */   public ModelTHFairy()
/*  37:    */   {
/*  38: 44 */     this.textureWidth = 64;
/*  39: 45 */     this.textureHeight = 64;
/*  40:    */     
/*  41: 47 */     this.head = new ModelRenderer(this, 0, 0);
/*  42: 48 */     this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
/*  43: 49 */     this.head.setRotationPoint(0.0F, 7.0F, 0.0F);
/*  44: 50 */     this.head.setTextureSize(64, 64);
/*  45: 51 */     this.head.mirror = true;
/*  46: 52 */     this.longHair = new ModelRenderer(this, 24, 0);
/*  47: 53 */     this.longHair.addBox(-4.0F, -7.0F, -3.0F, 8, 5, 3);
/*  48: 54 */     this.longHair.setRotationPoint(0.0F, 7.0F, 4.0F);
/*  49: 55 */     this.head.addChild(this.longHair);
/*  50:    */     
/*  51: 57 */     this.ribbonCenter = new ModelRenderer(this, 0, 32);
/*  52: 58 */     this.ribbonCenter.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 1);
/*  53: 59 */     this.ribbonCenter.setRotationPoint(0.0F, -8.0F, 4.0F);
/*  54: 60 */     this.head.addChild(this.ribbonCenter);
/*  55: 61 */     setRotation(this.ribbonCenter, 0.244346F, 0.0F, 0.0F);
/*  56: 62 */     this.ribbonLeft = new ModelRenderer(this, 6, 32);
/*  57: 63 */     this.ribbonLeft.addBox(0.0F, -2.0F, 0.0F, 5, 4, 1);
/*  58: 64 */     this.ribbonLeft.setRotationPoint(1.0F, -8.0F, 4.0F);
/*  59: 65 */     this.ribbonLeft.mirror = true;
/*  60: 66 */     this.head.addChild(this.ribbonLeft);
/*  61:    */     
/*  62: 68 */     this.ribbonRight = new ModelRenderer(this, 6, 32);
/*  63: 69 */     this.ribbonRight.addBox(-5.0F, -2.0F, 0.0F, 5, 4, 1);
/*  64: 70 */     this.ribbonRight.setRotationPoint(-1.0F, -8.0F, 4.0F);
/*  65: 71 */     this.ribbonRight.mirror = true;
/*  66: 72 */     this.head.addChild(this.ribbonRight);
/*  67:    */     
/*  68: 74 */     this.flowerCenter = new ModelRenderer(this, 18, 32);
/*  69: 75 */     this.flowerCenter.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 1);
/*  70: 76 */     this.flowerCenter.setRotationPoint(3.0F, -8.0F, -4.0F);
/*  71: 77 */     this.flowerCenter.setTextureSize(64, 64);
/*  72: 78 */     this.flowerCenter.mirror = true;
/*  73: 79 */     setRotation(this.flowerCenter, -0.349066F, 0.0F, 0.0F);
/*  74: 80 */     this.head.addChild(this.flowerCenter);
/*  75: 81 */     this.flower1 = new ModelRenderer(this, 24, 32);
/*  76: 82 */     this.flower1.addBox(1.0F, -1.0F, -1.0F, 1, 2, 1);
/*  77: 83 */     this.flower1.setRotationPoint(3.0F, -8.0F, -4.0F);
/*  78: 84 */     this.flower1.setTextureSize(64, 64);
/*  79: 85 */     this.flower1.mirror = true;
/*  80: 86 */     setRotation(this.flower1, -0.349066F, 0.0F, 0.0F);
/*  81: 87 */     this.head.addChild(this.flower1);
/*  82: 88 */     this.flower2 = new ModelRenderer(this, 24, 32);
/*  83: 89 */     this.flower2.addBox(-2.0F, -1.0F, -1.0F, 1, 2, 1);
/*  84: 90 */     this.flower2.setRotationPoint(3.0F, -8.0F, -4.0F);
/*  85: 91 */     this.flower2.setTextureSize(64, 64);
/*  86: 92 */     this.flower2.mirror = true;
/*  87: 93 */     setRotation(this.flower2, -0.349066F, 0.0F, 0.0F);
/*  88: 94 */     this.head.addChild(this.flower2);
/*  89: 95 */     this.flower3 = new ModelRenderer(this, 24, 32);
/*  90: 96 */     this.flower3.addBox(-1.0F, -2.0F, 0.0F, 2, 1, 1);
/*  91: 97 */     this.flower3.setRotationPoint(3.0F, -8.0F, -5.0F);
/*  92: 98 */     this.flower3.setTextureSize(64, 64);
/*  93: 99 */     this.flower3.mirror = true;
/*  94:100 */     setRotation(this.flower3, -0.349066F, 0.0F, 0.0F);
/*  95:101 */     this.head.addChild(this.flower3);
/*  96:102 */     this.flower4 = new ModelRenderer(this, 24, 32);
/*  97:103 */     this.flower4.addBox(-1.0F, 1.0F, -1.0F, 2, 1, 1);
/*  98:104 */     this.flower4.setRotationPoint(3.0F, -8.0F, -4.0F);
/*  99:105 */     this.flower4.setTextureSize(64, 64);
/* 100:106 */     this.flower4.mirror = true;
/* 101:107 */     setRotation(this.flower4, -0.349066F, 0.0F, 0.0F);
/* 102:108 */     this.head.addChild(this.flower4);
/* 103:    */     
/* 104:110 */     setRotation(this.head, 0.0F, 0.0F, 0.0F);
/* 105:111 */     this.body = new ModelRenderer(this, 32, 8);
/* 106:112 */     this.body.addBox(-3.0F, 0.0F, -2.0F, 6, 7, 4);
/* 107:113 */     this.body.setRotationPoint(0.0F, 7.0F, 0.0F);
/* 108:114 */     this.body.setTextureSize(64, 64);
/* 109:115 */     this.body.mirror = true;
/* 110:116 */     setRotation(this.body, 0.0F, 0.0F, 0.0F);
/* 111:117 */     this.rightArm = new ModelRenderer(this, 48, 0);
/* 112:118 */     this.rightArm.addBox(-1.0F, -1.0F, -1.0F, 2, 8, 2);
/* 113:119 */     this.rightArm.setRotationPoint(-4.0F, 8.0F, 0.0F);
/* 114:120 */     this.rightArm.setTextureSize(64, 64);
/* 115:121 */     this.rightArm.mirror = true;
/* 116:122 */     setRotation(this.rightArm, -0.7679449F, 0.0F, -0.645772F);
/* 117:123 */     this.rightLeg = new ModelRenderer(this, 32, 19);
/* 118:124 */     this.rightLeg.addBox(-1.0F, 0.0F, -2.0F, 3, 9, 4);
/* 119:125 */     this.rightLeg.setRotationPoint(-2.0F, 14.0F, 0.0F);
/* 120:126 */     this.rightLeg.setTextureSize(64, 64);
/* 121:127 */     this.rightLeg.mirror = true;
/* 122:128 */     setRotation(this.rightLeg, 0.0F, 0.0F, 0.0F);
/* 123:129 */     this.leftArm = new ModelRenderer(this, 56, 0);
/* 124:130 */     this.leftArm.addBox(-1.0F, -1.0F, -1.0F, 2, 8, 2);
/* 125:131 */     this.leftArm.setRotationPoint(4.0F, 8.0F, 0.0F);
/* 126:132 */     this.leftArm.setTextureSize(64, 64);
/* 127:133 */     this.leftArm.mirror = true;
/* 128:134 */     setRotation(this.leftArm, -0.7679449F, 0.0F, 0.645772F);
/* 129:135 */     this.leftLeg = new ModelRenderer(this, 32, 19);
/* 130:136 */     this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 3, 9, 4);
/* 131:137 */     this.leftLeg.setRotationPoint(2.0F, 14.0F, 0.0F);
/* 132:138 */     this.leftLeg.setTextureSize(64, 64);
/* 133:139 */     this.leftLeg.mirror = true;
/* 134:140 */     setRotation(this.leftLeg, 0.0F, 0.0F, 0.0F);
/* 135:141 */     this.skirt = new ModelRenderer(this, 0, 16);
/* 136:142 */     this.skirt.addBox(-4.0F, 0.0F, -4.0F, 8, 8, 8);
/* 137:143 */     this.skirt.setRotationPoint(0.0F, 12.0F, 0.0F);
/* 138:144 */     this.skirt.setTextureSize(64, 64);
/* 139:145 */     this.skirt.mirror = true;
/* 140:146 */     setRotation(this.skirt, 0.0F, 0.0F, 0.0F);
/* 141:    */     
/* 142:148 */     this.rightWing2 = new ModelRenderer(this, 0, 48);
/* 143:149 */     this.rightWing2.addBox(-14.0F, 0.0F, 0.0F, 14, 10, 1);
/* 144:150 */     this.rightWing2.setRotationPoint(-1.0F, 7.0F, 2.0F);
/* 145:151 */     this.rightWing2.setTextureSize(64, 64);
/* 146:152 */     this.rightWing2.mirror = true;
/* 147:153 */     setRotation(this.rightWing2, 0.0F, 0.4886922F, -0.314159F);
/* 148:154 */     this.rightWing2.mirror = false;
/* 149:155 */     this.leftWing = new ModelRenderer(this, 0, 48);
/* 150:156 */     this.leftWing.addBox(0.0F, 0.0F, 0.0F, 14, 10, 1);
/* 151:157 */     this.leftWing.setRotationPoint(0.0F, 4.0F, 2.0F);
/* 152:158 */     this.leftWing.setTextureSize(64, 64);
/* 153:159 */     this.leftWing.mirror = true;
/* 154:160 */     setRotation(this.leftWing, 0.0F, -0.4886922F, -0.314159F);
/* 155:161 */     this.leftWing2 = new ModelRenderer(this, 0, 48);
/* 156:162 */     this.leftWing2.addBox(0.0F, 0.0F, 0.0F, 14, 10, 1);
/* 157:163 */     this.leftWing2.setRotationPoint(1.0F, 7.0F, 2.0F);
/* 158:164 */     this.leftWing2.setTextureSize(64, 64);
/* 159:165 */     this.leftWing2.mirror = true;
/* 160:166 */     setRotation(this.leftWing2, 0.0F, -0.4886922F, 0.314159F);
/* 161:    */     
/* 162:168 */     this.rightWing = new ModelRenderer(this, 0, 48);
/* 163:169 */     this.rightWing.addBox(-14.0F, 0.0F, 0.0F, 14, 10, 1);
/* 164:170 */     this.rightWing.setRotationPoint(0.0F, 4.0F, 2.0F);
/* 165:171 */     this.rightWing.setTextureSize(64, 64);
/* 166:172 */     this.rightWing.mirror = true;
/* 167:173 */     setRotation(this.rightWing, 0.0F, 0.4886922F, 0.314159F);
/* 168:    */   }
/* 169:    */   
/* 170:    */   public void render(Entity entity, float movement, float far, float tick, float yaw, float pitch, float size)
/* 171:    */   {
/* 172:180 */     super.render(entity, movement, far, tick, yaw, pitch, size);
/* 173:181 */     setRotationAngles(movement, far, tick, yaw, pitch, size, entity);
/* 174:    */     
/* 175:183 */     this.head.render(size);
/* 176:184 */     this.body.render(size);
/* 177:185 */     this.rightArm.render(size);
/* 178:186 */     this.rightLeg.render(size);
/* 179:187 */     this.leftArm.render(size);
/* 180:188 */     this.leftLeg.render(size);
/* 181:189 */     this.skirt.render(size);
/* 182:    */     
/* 183:191 */     this.rightWing.render(size);
/* 184:192 */     this.rightWing2.render(size);
/* 185:193 */     this.leftWing.render(size);
/* 186:194 */     this.leftWing2.render(size);
/* 187:    */   }
/* 188:    */   
/* 189:    */   private void setRotation(ModelRenderer model, float x, float y, float z)
/* 190:    */   {
/* 191:199 */     model.rotateAngleX = x;
/* 192:200 */     model.rotateAngleY = y;
/* 193:201 */     model.rotateAngleZ = z;
/* 194:    */   }
/* 195:    */   
/* 196:    */   public void setRotationAngles(float movement, float far, float tick, float yaw, float pitch, float size, Entity entity)
/* 197:    */   {
/* 198:207 */     super.setRotationAngles(movement, far, tick, yaw, pitch, size, entity);
/* 199:208 */     EntityTHFairy thFairy = (EntityTHFairy)entity;
/* 200:    */     
/* 201:210 */     this.head.rotateAngleY = (yaw / 57.295776F);
/* 202:211 */     this.head.rotateAngleX = (pitch / 57.295776F);
/* 203:212 */     this.body.rotateAngleY = (MathHelper.sin(MathHelper.sqrt_float(this.onGround) * 3.141593F * 2.0F) * 0.2F);
/* 204:213 */     this.skirt.rotateAngleX = 0.0F;
/* 205:215 */     if (this.isRiding)
/* 206:    */     {
/* 207:217 */       this.rightArm.rotateAngleX = -0.6283186F;
/* 208:218 */       this.leftArm.rotateAngleX = -0.6283186F;
/* 209:219 */       this.rightLeg.rotateAngleX = -1.256637F;
/* 210:220 */       this.leftLeg.rotateAngleX = -1.256637F;
/* 211:221 */       this.rightLeg.rotateAngleY = 0.2243995F;
/* 212:222 */       this.leftLeg.rotateAngleY = -0.2243995F;
/* 213:223 */       this.rightLeg.rotateAngleZ = 0.2243995F;
/* 214:224 */       this.leftLeg.rotateAngleZ = -0.2243995F;
/* 215:    */       
/* 216:226 */       this.skirt.rotateAngleX += -0.6283186F;
/* 217:    */     }
/* 218:231 */     else if (thFairy.getFlyingHeight() == 0)
/* 219:    */     {
/* 220:233 */       this.rightLeg.rotateAngleX = (MathHelper.cos(movement) * 0.7F * far);
/* 221:234 */       this.leftLeg.rotateAngleX = (MathHelper.cos(movement + 3.141593F) * 0.7F * far);
/* 222:235 */       this.rightLeg.rotateAngleZ = 0.0F;
/* 223:236 */       this.leftLeg.rotateAngleZ = 0.0F;
/* 224:238 */       if (movement > 0.0F)
/* 225:    */       {
/* 226:240 */         this.rightArm.rotateAngleX = (MathHelper.cos(movement + 3.141593F) * 2.0F * far * 0.5F);
/* 227:241 */         this.leftArm.rotateAngleX = (MathHelper.cos(movement) * 2.0F * far * 0.5F);
/* 228:242 */         this.rightArm.rotateAngleY = -0.1745329F;
/* 229:243 */         this.rightArm.rotateAngleZ = 0.3490659F;
/* 230:244 */         this.leftArm.rotateAngleY = (-this.rightArm.rotateAngleY);
/* 231:245 */         this.leftArm.rotateAngleZ = (-this.rightArm.rotateAngleZ);
/* 232:    */       }
/* 233:    */       else
/* 234:    */       {
/* 235:249 */         this.rightArm.rotateAngleX = (-0.7F - MathHelper.sin(tick / 10.0F) * 0.1F);
/* 236:250 */         this.rightArm.rotateAngleY = 0.0F;
/* 237:251 */         this.rightArm.rotateAngleZ = -0.645772F;
/* 238:252 */         this.leftArm.rotateAngleX = this.rightArm.rotateAngleX;
/* 239:253 */         this.leftArm.rotateAngleY = 0.0F;
/* 240:254 */         this.leftArm.rotateAngleZ = 0.645772F;
/* 241:    */       }
/* 242:    */     }
/* 243:    */     else
/* 244:    */     {
/* 245:261 */       this.rightLeg.rotateAngleZ = Math.abs(MathHelper.sin(tick / 10.0F) * 0.1F);
/* 246:262 */       this.leftLeg.rotateAngleZ = (-this.rightLeg.rotateAngleZ);
/* 247:263 */       this.rightLeg.rotateAngleX = Math.abs(MathHelper.sin(tick / 10.0F) * 0.2F);
/* 248:264 */       this.leftLeg.rotateAngleX = this.rightLeg.rotateAngleZ;
/* 249:    */       
/* 250:266 */       this.rightArm.rotateAngleX = (-0.7F - MathHelper.sin(tick / 10.0F) * 0.1F);
/* 251:267 */       this.rightArm.rotateAngleY = 0.0F;
/* 252:268 */       this.rightArm.rotateAngleZ = -0.645772F;
/* 253:269 */       this.leftArm.rotateAngleX = this.rightArm.rotateAngleX;
/* 254:270 */       this.leftArm.rotateAngleY = 0.0F;
/* 255:271 */       this.leftArm.rotateAngleZ = 0.645772F;
/* 256:    */     }
/* 257:277 */     if (thFairy.getHealth() > 0.0F)
/* 258:    */     {
/* 259:279 */       this.body.rotateAngleX = 0.0F;
/* 260:280 */       if (thFairy.getFlyingHeight() > 0)
/* 261:    */       {
/* 262:282 */         this.rightWing.rotateAngleY = (MathHelper.cos(tick * 1.3F) * 3.141593F * 0.25F);
/* 263:283 */         this.leftWing.rotateAngleY = (-this.rightWing.rotateAngleY);
/* 264:284 */         this.rightWing2.rotateAngleY = (MathHelper.cos(tick * 1.3F) * 3.141593F * 0.25F);
/* 265:285 */         this.leftWing2.rotateAngleY = (-this.rightWing2.rotateAngleY);
/* 266:    */       }
/* 267:    */       else
/* 268:    */       {
/* 269:289 */         this.rightWing.rotateAngleY = (MathHelper.cos(tick * 0.5F) * 3.141593F * 0.1F + 0.4712389F);
/* 270:290 */         this.leftWing.rotateAngleY = (-this.rightWing.rotateAngleY);
/* 271:291 */         this.rightWing2.rotateAngleY = (MathHelper.cos(tick * 0.5F) * 3.141593F * 0.1F + 0.4712389F);
/* 272:292 */         this.leftWing2.rotateAngleY = (-this.rightWing2.rotateAngleY);
/* 273:    */       }
/* 274:    */     }
/* 275:    */     else
/* 276:    */     {
/* 277:297 */       setRotation(this.head, 70.0F, 0.0F, 0.0F);
/* 278:298 */       this.rightWing.rotateAngleY = -25.0F;
/* 279:299 */       this.leftWing.rotateAngleY = (-this.rightWing.rotateAngleY);
/* 280:300 */       this.rightWing2.rotateAngleY = -25.0F;
/* 281:301 */       this.leftWing2.rotateAngleY = (-this.rightWing2.rotateAngleY);
/* 282:    */     }
/* 283:    */   }
/* 284:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.model.living.ModelTHFairy
 * JD-Core Version:    0.7.0.1
 */