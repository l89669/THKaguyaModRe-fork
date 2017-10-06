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
/*  12:    */ public class ModelSunFlowerFairy
/*  13:    */   extends ModelBase
/*  14:    */ {
/*  15:    */   ModelRenderer bipedBody;
/*  16:    */   ModelRenderer bipedRightArm;
/*  17:    */   ModelRenderer bipedLeftArm;
/*  18:    */   ModelRenderer bipedRightLeg;
/*  19:    */   ModelRenderer bipedLeftLeg;
/*  20:    */   ModelRenderer skirtTop;
/*  21:    */   ModelRenderer skirtBottom;
/*  22:    */   ModelRenderer leftWing;
/*  23:    */   ModelRenderer rightWing;
/*  24:    */   ModelRenderer himawari1;
/*  25:    */   ModelRenderer himawari2;
/*  26:    */   ModelRenderer bipedHead;
/*  27:    */   ModelRenderer longHair;
/*  28:    */   ModelRenderer rightRibbon;
/*  29:    */   ModelRenderer rightTail;
/*  30:    */   ModelRenderer leftRibbon;
/*  31:    */   ModelRenderer leftTail;
/*  32:    */   
/*  33:    */   public ModelSunFlowerFairy()
/*  34:    */   {
/*  35: 41 */     this.textureWidth = 128;
/*  36: 42 */     this.textureHeight = 64;
/*  37:    */     
/*  38: 44 */     this.bipedBody = new ModelRenderer(this, 32, 8);
/*  39: 45 */     this.bipedBody.addBox(-3.0F, 0.0F, -2.0F, 6, 7, 4);
/*  40: 46 */     this.bipedBody.setRotationPoint(0.0F, 5.0F, 0.0F);
/*  41: 47 */     this.bipedBody.setTextureSize(128, 64);
/*  42: 48 */     this.bipedBody.mirror = true;
/*  43: 49 */     setRotation(this.bipedBody, 0.0F, 0.0F, 0.0F);
/*  44: 50 */     this.bipedRightArm = new ModelRenderer(this, 48, 0);
/*  45: 51 */     this.bipedRightArm.addBox(-1.0F, -1.0F, -1.0F, 2, 8, 2);
/*  46: 52 */     this.bipedRightArm.setRotationPoint(-4.0F, 6.0F, 0.0F);
/*  47: 53 */     this.bipedRightArm.setTextureSize(128, 64);
/*  48: 54 */     this.bipedRightArm.mirror = true;
/*  49: 55 */     setRotation(this.bipedRightArm, -0.7679449F, 0.0F, -0.645772F);
/*  50: 56 */     this.bipedLeftArm = new ModelRenderer(this, 56, 0);
/*  51: 57 */     this.bipedLeftArm.addBox(-1.0F, -1.0F, -1.0F, 2, 8, 2);
/*  52: 58 */     this.bipedLeftArm.setRotationPoint(4.0F, 6.0F, 0.0F);
/*  53: 59 */     this.bipedLeftArm.setTextureSize(128, 64);
/*  54: 60 */     this.bipedLeftArm.mirror = true;
/*  55: 61 */     setRotation(this.bipedLeftArm, -0.7679449F, 0.0F, 0.645772F);
/*  56: 62 */     this.bipedRightLeg = new ModelRenderer(this, 50, 16);
/*  57: 63 */     this.bipedRightLeg.addBox(-1.0F, 0.0F, -2.0F, 3, 11, 4);
/*  58: 64 */     this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
/*  59: 65 */     this.bipedRightLeg.setTextureSize(128, 64);
/*  60: 66 */     this.bipedRightLeg.mirror = true;
/*  61: 67 */     setRotation(this.bipedRightLeg, 0.0F, 0.0F, 0.0F);
/*  62: 68 */     this.bipedLeftLeg = new ModelRenderer(this, 50, 16);
/*  63: 69 */     this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 3, 11, 4);
/*  64: 70 */     this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
/*  65: 71 */     this.bipedLeftLeg.setTextureSize(128, 64);
/*  66: 72 */     this.bipedLeftLeg.mirror = true;
/*  67: 73 */     setRotation(this.bipedLeftLeg, 0.0F, 0.0F, 0.0F);
/*  68: 74 */     this.skirtTop = new ModelRenderer(this, 0, 16);
/*  69: 75 */     this.skirtTop.addBox(-4.0F, 5.0F, -4.0F, 8, 4, 8);
/*  70: 76 */     this.skirtTop.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  71: 77 */     this.skirtTop.setTextureSize(128, 64);
/*  72: 78 */     this.skirtTop.mirror = true;
/*  73: 79 */     setRotation(this.skirtTop, 0.0F, 0.0F, 0.0F);
/*  74: 80 */     this.skirtBottom = new ModelRenderer(this, 32, 32);
/*  75: 81 */     this.skirtBottom.addBox(-5.0F, 0.0F, -5.0F, 10, 6, 10);
/*  76: 82 */     this.skirtBottom.setRotationPoint(0.0F, 9.0F, 0.0F);
/*  77: 83 */     this.skirtBottom.setTextureSize(128, 64);
/*  78: 84 */     this.skirtBottom.mirror = true;
/*  79: 85 */     setRotation(this.skirtBottom, 0.0F, 0.0F, 0.0F);
/*  80:    */     
/*  81: 87 */     this.leftWing = new ModelRenderer(this, 64, 0);
/*  82: 88 */     this.leftWing.addBox(-1.0F, -16.0F, 0.0F, 20, 26, 1);
/*  83: 89 */     this.leftWing.setRotationPoint(2.0F, 3.0F, 2.0F);
/*  84: 90 */     this.leftWing.setTextureSize(128, 64);
/*  85: 91 */     this.leftWing.mirror = false;
/*  86: 92 */     setRotation(this.leftWing, 0.0F, -0.4886922F, 0.01673F);
/*  87: 93 */     this.leftWing.mirror = false;
/*  88: 94 */     this.rightWing = new ModelRenderer(this, 72, 32);
/*  89: 95 */     this.rightWing.addBox(-1.0F, -16.0F, 0.0F, 20, 26, 1);
/*  90: 96 */     this.rightWing.setRotationPoint(-2.0F, 3.0F, 2.0F);
/*  91: 97 */     this.rightWing.setTextureSize(128, 64);
/*  92: 98 */     this.rightWing.mirror = false;
/*  93: 99 */     setRotation(this.rightWing, 0.0F, -2.6529F, 0.01673F);
/*  94:100 */     this.himawari1 = new ModelRenderer(this, 0, 32);
/*  95:101 */     this.himawari1.addBox(-7.0F, -20.0F, 0.0F, 12, 31, 1);
/*  96:102 */     this.himawari1.setRotationPoint(0.0F, 6.0F, -2.0F);
/*  97:103 */     this.himawari1.setTextureSize(128, 64);
/*  98:104 */     this.himawari1.mirror = true;
/*  99:105 */     setRotation(this.himawari1, 0.4089647F, -0.7435722F, 0.3666439F);
/* 100:106 */     this.himawari2 = new ModelRenderer(this, 0, 32);
/* 101:107 */     this.himawari2.addBox(-7.0F, -20.0F, 0.0F, 12, 31, 1);
/* 102:108 */     this.himawari2.setRotationPoint(-1.0F, 6.0F, -2.0F);
/* 103:109 */     this.himawari2.setTextureSize(128, 64);
/* 104:110 */     this.himawari2.mirror = true;
/* 105:111 */     setRotation(this.himawari2, 0.2230717F, -0.669215F, 0.2551081F);
/* 106:112 */     this.bipedHead = new ModelRenderer(this, 0, 0);
/* 107:113 */     this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
/* 108:114 */     this.bipedHead.setRotationPoint(0.0F, 5.0F, 0.0F);
/* 109:115 */     this.bipedHead.setTextureSize(128, 64);
/* 110:116 */     this.bipedHead.mirror = true;
/* 111:117 */     setRotation(this.bipedHead, 0.0F, 0.0F, 0.0F);
/* 112:118 */     this.longHair = new ModelRenderer(this, 24, 0);
/* 113:119 */     this.longHair.addBox(-4.0F, 4.0F, -3.0F, 8, 5, 3);
/* 114:120 */     this.longHair.setRotationPoint(0.0F, -5.0F, 4.0F);
/* 115:121 */     this.longHair.setTextureSize(128, 64);
/* 116:122 */     this.longHair.mirror = true;
/* 117:123 */     setRotation(this.longHair, 0.0F, 0.0F, 0.0F);
/* 118:124 */     this.rightRibbon = new ModelRenderer(this, 32, 19);
/* 119:125 */     this.rightRibbon.addBox(-3.0F, -2.0F, -1.0F, 5, 4, 1);
/* 120:126 */     this.rightRibbon.setRotationPoint(-4.0F, -7.0F, 0.0F);
/* 121:127 */     this.rightRibbon.setTextureSize(128, 64);
/* 122:128 */     this.rightRibbon.mirror = true;
/* 123:129 */     setRotation(this.rightRibbon, -0.4712389F, 1.570796F, 0.0F);
/* 124:130 */     this.rightTail = new ModelRenderer(this, 32, 24);
/* 125:131 */     this.rightTail.addBox(-2.0F, -1.0F, -2.0F, 3, 8, 2);
/* 126:132 */     this.rightTail.setRotationPoint(-5.0F, -7.0F, 0.0F);
/* 127:133 */     this.rightTail.setTextureSize(128, 64);
/* 128:134 */     this.rightTail.mirror = true;
/* 129:135 */     setRotation(this.rightTail, -0.261799F, 1.570796F, 0.0F);
/* 130:136 */     this.leftRibbon = new ModelRenderer(this, 32, 19);
/* 131:137 */     this.leftRibbon.addBox(-3.0F, -2.0F, 0.0F, 5, 4, 1);
/* 132:138 */     this.leftRibbon.setRotationPoint(4.0F, -7.0F, 0.0F);
/* 133:139 */     this.leftRibbon.setTextureSize(128, 64);
/* 134:140 */     this.leftRibbon.mirror = true;
/* 135:141 */     setRotation(this.leftRibbon, 0.4712389F, 1.570796F, 0.0F);
/* 136:142 */     this.leftTail = new ModelRenderer(this, 32, 24);
/* 137:143 */     this.leftTail.addBox(-2.0F, -1.0F, 0.0F, 3, 8, 2);
/* 138:144 */     this.leftTail.setRotationPoint(5.0F, -7.0F, 0.0F);
/* 139:145 */     this.leftTail.setTextureSize(128, 64);
/* 140:146 */     this.leftTail.mirror = true;
/* 141:147 */     setRotation(this.leftTail, 0.261799F, 1.570796F, 0.0F);
/* 142:    */     
/* 143:149 */     this.bipedBody.addChild(this.rightWing);
/* 144:150 */     this.bipedBody.addChild(this.leftWing);
/* 145:151 */     this.bipedBody.addChild(this.skirtTop);
/* 146:152 */     this.skirtTop.addChild(this.skirtBottom);
/* 147:153 */     this.bipedLeftArm.addChild(this.himawari1);
/* 148:154 */     this.bipedLeftArm.addChild(this.himawari2);
/* 149:    */     
/* 150:156 */     this.bipedHead.addChild(this.longHair);
/* 151:157 */     this.bipedHead.addChild(this.rightRibbon);
/* 152:158 */     this.bipedHead.addChild(this.leftRibbon);
/* 153:159 */     this.bipedHead.addChild(this.rightTail);
/* 154:160 */     this.bipedHead.addChild(this.leftTail);
/* 155:    */   }
/* 156:    */   
/* 157:    */   public void render(Entity entity, float movement, float far, float tick, float yaw, float pitch, float size)
/* 158:    */   {
/* 159:166 */     super.render(entity, movement, far, tick, yaw, pitch, size);
/* 160:167 */     setRotationAngles(movement, far, tick, yaw, pitch, size, entity);
/* 161:    */     
/* 162:    */ 
/* 163:    */ 
/* 164:171 */     this.bipedBody.render(size);
/* 165:172 */     this.bipedRightArm.render(size);
/* 166:173 */     this.bipedLeftArm.render(size);
/* 167:174 */     this.bipedRightLeg.render(size);
/* 168:175 */     this.bipedLeftLeg.render(size);
/* 169:176 */     this.bipedHead.render(size);
/* 170:    */   }
/* 171:    */   
/* 172:    */   private void setRotation(ModelRenderer model, float x, float y, float z)
/* 173:    */   {
/* 174:183 */     model.rotateAngleX = x;
/* 175:184 */     model.rotateAngleY = y;
/* 176:185 */     model.rotateAngleZ = z;
/* 177:    */   }
/* 178:    */   
/* 179:    */   public void setRotationAngles(float movement, float far, float tick, float yaw, float pitch, float size, Entity entity)
/* 180:    */   {
/* 181:191 */     super.setRotationAngles(movement, far, tick, yaw, pitch, size, entity);
/* 182:192 */     EntityTHFairy thFairy = (EntityTHFairy)entity;
/* 183:    */     
/* 184:194 */     this.bipedHead.rotateAngleY = (yaw / 57.295776F);
/* 185:195 */     this.bipedHead.rotateAngleX = (pitch / 57.295776F);
/* 186:196 */     this.bipedBody.rotateAngleY = (MathHelper.sin(MathHelper.sqrt_float(this.onGround) * 3.141593F * 2.0F) * 0.2F);
/* 187:197 */     this.skirtTop.rotateAngleX = 0.0F;
/* 188:199 */     if (this.isRiding)
/* 189:    */     {
/* 190:201 */       this.bipedRightArm.rotateAngleX = -0.6283186F;
/* 191:202 */       this.bipedLeftArm.rotateAngleX = -0.6283186F;
/* 192:203 */       this.bipedRightLeg.rotateAngleX = -1.256637F;
/* 193:204 */       this.bipedLeftLeg.rotateAngleX = -1.256637F;
/* 194:205 */       this.bipedRightLeg.rotateAngleY = 0.2243995F;
/* 195:206 */       this.bipedLeftLeg.rotateAngleY = -0.2243995F;
/* 196:207 */       this.bipedRightLeg.rotateAngleZ = 0.2243995F;
/* 197:208 */       this.bipedLeftLeg.rotateAngleZ = -0.2243995F;
/* 198:    */       
/* 199:210 */       this.skirtTop.rotateAngleX += -0.6283186F;
/* 200:    */     }
/* 201:215 */     else if (thFairy.getFlyingHeight() == 0)
/* 202:    */     {
/* 203:217 */       this.bipedRightLeg.rotateAngleX = (MathHelper.cos(movement) * 0.7F * far);
/* 204:218 */       this.bipedLeftLeg.rotateAngleX = (MathHelper.cos(movement + 3.141593F) * 0.7F * far);
/* 205:219 */       this.bipedRightLeg.rotateAngleZ = 0.0F;
/* 206:220 */       this.bipedLeftLeg.rotateAngleZ = 0.0F;
/* 207:222 */       if (movement > 0.0F)
/* 208:    */       {
/* 209:224 */         this.bipedRightArm.rotateAngleX = (MathHelper.cos(movement + 3.141593F) * 2.0F * far * 0.5F);
/* 210:225 */         this.bipedLeftArm.rotateAngleX = (MathHelper.cos(movement) * 2.0F * far * 0.5F);
/* 211:226 */         this.bipedRightArm.rotateAngleY = -0.1745329F;
/* 212:227 */         this.bipedRightArm.rotateAngleZ = 0.3490659F;
/* 213:228 */         this.bipedLeftArm.rotateAngleY = (-this.bipedRightArm.rotateAngleY);
/* 214:229 */         this.bipedLeftArm.rotateAngleZ = (-this.bipedRightArm.rotateAngleZ);
/* 215:    */       }
/* 216:    */       else
/* 217:    */       {
/* 218:233 */         this.bipedRightArm.rotateAngleX = (-0.7F - MathHelper.sin(tick / 10.0F) * 0.1F);
/* 219:234 */         this.bipedRightArm.rotateAngleY = 0.0F;
/* 220:235 */         this.bipedRightArm.rotateAngleZ = -0.645772F;
/* 221:236 */         this.bipedLeftArm.rotateAngleX = this.bipedRightArm.rotateAngleX;
/* 222:237 */         this.bipedLeftArm.rotateAngleY = 0.0F;
/* 223:238 */         this.bipedLeftArm.rotateAngleZ = 0.645772F;
/* 224:    */       }
/* 225:    */     }
/* 226:    */     else
/* 227:    */     {
/* 228:245 */       this.bipedRightLeg.rotateAngleZ = Math.abs(MathHelper.sin(tick / 10.0F) * 0.1F);
/* 229:246 */       this.bipedLeftLeg.rotateAngleZ = (-this.bipedRightLeg.rotateAngleZ);
/* 230:247 */       this.bipedRightLeg.rotateAngleX = Math.abs(MathHelper.sin(tick / 10.0F) * 0.2F);
/* 231:248 */       this.bipedLeftLeg.rotateAngleX = this.bipedRightLeg.rotateAngleZ;
/* 232:    */       
/* 233:250 */       this.bipedRightArm.rotateAngleX = (-0.7F - MathHelper.sin(tick / 10.0F) * 0.1F);
/* 234:251 */       this.bipedRightArm.rotateAngleY = 0.0F;
/* 235:252 */       this.bipedRightArm.rotateAngleZ = -0.645772F;
/* 236:253 */       this.bipedLeftArm.rotateAngleX = this.bipedRightArm.rotateAngleX;
/* 237:254 */       this.bipedLeftArm.rotateAngleY = 0.0F;
/* 238:255 */       this.bipedLeftArm.rotateAngleZ = 0.645772F;
/* 239:    */     }
/* 240:261 */     if (thFairy.getHealth() > 0.0F)
/* 241:    */     {
/* 242:263 */       this.bipedBody.rotateAngleX = 0.0F;
/* 243:264 */       if (thFairy.getFlyingHeight() > 0)
/* 244:    */       {
/* 245:266 */         this.rightWing.rotateAngleY = (MathHelper.cos(tick * 1.3F) * 3.141593F * 0.25F - 3.141593F);
/* 246:267 */         this.leftWing.rotateAngleY = (-MathHelper.cos(tick * 1.3F) * 3.141593F * 0.25F);
/* 247:    */       }
/* 248:    */       else
/* 249:    */       {
/* 250:271 */         this.rightWing.rotateAngleY = (MathHelper.cos(tick * 0.5F) * 3.141593F * 0.1F - 3.141593F);
/* 251:272 */         this.leftWing.rotateAngleY = (-MathHelper.cos(tick * 0.5F) * 3.141593F * 0.1F);
/* 252:    */       }
/* 253:    */     }
/* 254:    */     else
/* 255:    */     {
/* 256:277 */       setRotation(this.bipedHead, 70.0F, 0.0F, 0.0F);
/* 257:278 */       this.rightWing.rotateAngleY = -25.0F;
/* 258:279 */       this.leftWing.rotateAngleY = (-this.rightWing.rotateAngleY);
/* 259:    */     }
/* 260:    */   }
/* 261:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.model.living.ModelSunFlowerFairy
 * JD-Core Version:    0.7.0.1
 */