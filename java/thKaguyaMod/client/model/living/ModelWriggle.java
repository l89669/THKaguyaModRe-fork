/*   1:    */ package thKaguyaMod.client.model.living;
/*   2:    */ 
/*   3:    */ import net.minecraft.client.model.ModelRenderer;
/*   4:    */ import thKaguyaMod.entity.living.EntityDanmakuMob;
/*   5:    */ 
/*   6:    */ public class ModelWriggle
/*   7:    */   extends ModelTouhouDefault
/*   8:    */ {
/*   9:    */   public ModelRenderer Manto1;
/*  10:    */   public ModelRenderer Manto2;
/*  11:    */   public ModelRenderer Manto3;
/*  12:    */   public ModelRenderer rightTouch;
/*  13:    */   public ModelRenderer leftTouch;
/*  14:    */   public ModelRenderer rightZubon;
/*  15:    */   public ModelRenderer leftZubon;
/*  16:    */   
/*  17:    */   public ModelWriggle()
/*  18:    */   {
/*  19: 19 */     this(0.0F);
/*  20:    */   }
/*  21:    */   
/*  22:    */   public ModelWriggle(float size)
/*  23:    */   {
/*  24: 24 */     this(size, 0.0F);
/*  25:    */   }
/*  26:    */   
/*  27:    */   public ModelWriggle(float size, float yOffset)
/*  28:    */   {
/*  29: 29 */     this(size, yOffset, 64, 64);
/*  30:    */   }
/*  31:    */   
/*  32:    */   public ModelWriggle(float size, float yOffset, int textureWidth, int textureHeight)
/*  33:    */   {
/*  34: 34 */     super(size, yOffset, textureWidth, textureHeight);
/*  35:    */   }
/*  36:    */   
/*  37:    */   protected void getTechneModelData()
/*  38:    */   {
/*  39: 41 */     this.bipedBody = new ModelRenderer(this, 32, 8);
/*  40: 42 */     this.bipedBody.addBox(-3.0F, 0.0F, -2.0F, 6, 7, 4);
/*  41: 43 */     this.bipedBody.setRotationPoint(0.0F, 5.0F, 0.0F);
/*  42: 44 */     this.bipedBody.setTextureSize(64, 64);
/*  43: 45 */     this.bipedBody.mirror = true;
/*  44: 46 */     setRotation(this.bipedBody, 0.0F, 0.0F, 0.0F);
/*  45: 47 */     this.bipedRightArm = new ModelRenderer(this, 56, 0);
/*  46: 48 */     this.bipedRightArm.addBox(-2.0F, -1.0F, -1.0F, 2, 8, 2);
/*  47: 49 */     this.bipedRightArm.setRotationPoint(-2.0F, 6.0F, 0.0F);
/*  48: 50 */     this.bipedRightArm.setTextureSize(64, 64);
/*  49: 51 */     this.bipedRightArm.mirror = true;
/*  50: 52 */     setRotation(this.bipedRightArm, 0.0F, 0.0F, 0.0F);
/*  51: 53 */     this.bipedLeftArm = new ModelRenderer(this, 56, 0);
/*  52: 54 */     this.bipedLeftArm.addBox(0.0F, -1.0F, -1.0F, 2, 8, 2);
/*  53: 55 */     this.bipedLeftArm.setRotationPoint(2.0F, 6.0F, 0.0F);
/*  54: 56 */     this.bipedLeftArm.setTextureSize(64, 64);
/*  55: 57 */     this.bipedLeftArm.mirror = true;
/*  56: 58 */     setRotation(this.bipedLeftArm, 0.0F, 0.0F, 0.0F);
/*  57: 59 */     this.bipedRightLeg = new ModelRenderer(this, 32, 19);
/*  58: 60 */     this.bipedRightLeg.addBox(-1.0F, 0.0F, -2.0F, 3, 11, 4);
/*  59: 61 */     this.bipedRightLeg.setRotationPoint(-2.0F, 13.0F, 0.0F);
/*  60: 62 */     this.bipedRightLeg.setTextureSize(64, 64);
/*  61: 63 */     this.bipedRightLeg.mirror = true;
/*  62: 64 */     setRotation(this.bipedRightLeg, 0.0F, 0.0F, 0.0F);
/*  63: 65 */     this.bipedLeftLeg = new ModelRenderer(this, 32, 19);
/*  64: 66 */     this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 3, 11, 4);
/*  65: 67 */     this.bipedLeftLeg.setRotationPoint(2.0F, 13.0F, 0.0F);
/*  66: 68 */     this.bipedLeftLeg.setTextureSize(64, 64);
/*  67: 69 */     this.bipedLeftLeg.mirror = true;
/*  68: 70 */     setRotation(this.bipedLeftLeg, 0.0F, 0.0F, 0.0F);
/*  69: 71 */     this.bipedHead = new ModelRenderer(this, 0, 0);
/*  70: 72 */     this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
/*  71: 73 */     this.bipedHead.setRotationPoint(0.0F, 5.0F, 0.0F);
/*  72: 74 */     this.bipedHead.setTextureSize(64, 64);
/*  73: 75 */     this.bipedHead.mirror = true;
/*  74: 76 */     setRotation(this.bipedHead, 0.0F, 0.0F, 0.0F);
/*  75: 77 */     this.longHair = new ModelRenderer(this, 24, 0);
/*  76: 78 */     this.longHair.addBox(-4.0F, 0.0F, -3.0F, 8, 2, 3);
/*  77: 79 */     this.longHair.setRotationPoint(0.0F, 0.0F, 4.0F);
/*  78: 80 */     this.longHair.setTextureSize(64, 64);
/*  79: 81 */     this.longHair.mirror = true;
/*  80: 82 */     setRotation(this.longHair, 0.0F, 0.0F, 0.0F);
/*  81: 83 */     this.leftBraid = new ModelRenderer(this, 0, 16);
/*  82: 84 */     this.leftBraid.addBox(0.0F, 0.0F, -1.0F, 1, 4, 1);
/*  83: 85 */     this.leftBraid.setRotationPoint(2.95F, -3.0F, -3.0F);
/*  84: 86 */     this.leftBraid.setTextureSize(64, 64);
/*  85: 87 */     this.leftBraid.mirror = true;
/*  86: 88 */     setRotation(this.leftBraid, -0.244346F, 0.0F, 0.0F);
/*  87: 89 */     this.rightBraid = new ModelRenderer(this, 0, 16);
/*  88: 90 */     this.rightBraid.addBox(-1.0F, 0.0F, -1.0F, 1, 4, 1);
/*  89: 91 */     this.rightBraid.setRotationPoint(-2.95F, -3.0F, -3.0F);
/*  90: 92 */     this.rightBraid.setTextureSize(64, 64);
/*  91: 93 */     this.rightBraid.mirror = true;
/*  92: 94 */     setRotation(this.rightBraid, -0.244346F, 0.0F, 0.0F);
/*  93: 95 */     this.rightTouch = new ModelRenderer(this, 4, 16);
/*  94: 96 */     this.rightTouch.addBox(0.0F, -6.0F, 0.0F, 1, 6, 1);
/*  95: 97 */     this.rightTouch.setRotationPoint(-3.0F, -8.0F, -4.0F);
/*  96: 98 */     this.rightTouch.setTextureSize(64, 64);
/*  97: 99 */     this.rightTouch.mirror = true;
/*  98:100 */     setRotation(this.rightTouch, 0.0F, 0.0F, 0.0F);
/*  99:101 */     this.leftTouch = new ModelRenderer(this, 4, 16);
/* 100:102 */     this.leftTouch.addBox(-1.0F, -6.0F, 0.0F, 1, 6, 1);
/* 101:103 */     this.leftTouch.setRotationPoint(3.0F, -8.0F, -4.0F);
/* 102:104 */     this.leftTouch.setTextureSize(64, 64);
/* 103:105 */     this.leftTouch.mirror = true;
/* 104:106 */     setRotation(this.leftTouch, 0.0F, 0.0F, 0.0F);
/* 105:107 */     this.Manto1 = new ModelRenderer(this, 38, 47);
/* 106:108 */     this.Manto1.addBox(-6.0F, 0.0F, -1.0F, 12, 6, 1);
/* 107:109 */     this.Manto1.setRotationPoint(0.0F, 0.0F, 2.0F);
/* 108:110 */     this.Manto1.setTextureSize(64, 64);
/* 109:111 */     this.Manto1.mirror = true;
/* 110:112 */     setRotation(this.Manto1, 0.0F, 0.0F, 0.0F);
/* 111:113 */     this.Manto2 = new ModelRenderer(this, 38, 54);
/* 112:114 */     this.Manto2.addBox(-6.0F, 0.0F, -1.0F, 12, 4, 1);
/* 113:115 */     this.Manto2.setRotationPoint(0.0F, 6.0F, 0.0F);
/* 114:116 */     this.Manto2.setTextureSize(64, 64);
/* 115:117 */     this.Manto2.mirror = true;
/* 116:118 */     setRotation(this.Manto2, 0.0F, 0.0F, 0.0F);
/* 117:119 */     this.Manto3 = new ModelRenderer(this, 38, 59);
/* 118:120 */     this.Manto3.addBox(-6.0F, 0.0F, -1.0F, 12, 4, 1);
/* 119:121 */     this.Manto3.setRotationPoint(0.0F, 4.0F, 0.0F);
/* 120:122 */     this.Manto3.setTextureSize(64, 64);
/* 121:123 */     this.Manto3.mirror = true;
/* 122:124 */     setRotation(this.Manto3, 0.0F, 0.0F, 0.0F);
/* 123:125 */     this.rightZubon = new ModelRenderer(this, 0, 32);
/* 124:126 */     this.rightZubon.addBox(-2.0F, 0.0F, -2.5F, 4, 6, 5);
/* 125:127 */     this.rightZubon.setRotationPoint(0.0F, -2.5F, 0.0F);
/* 126:128 */     this.rightZubon.setTextureSize(64, 64);
/* 127:129 */     this.rightZubon.mirror = true;
/* 128:130 */     setRotation(this.rightZubon, 0.0F, 0.0F, 0.0F);
/* 129:131 */     this.leftZubon = new ModelRenderer(this, 0, 32);
/* 130:132 */     this.leftZubon.addBox(-2.0F, 0.0F, -2.5F, 4, 6, 5);
/* 131:133 */     this.leftZubon.setRotationPoint(0.0F, -2.5F, 0.0F);
/* 132:134 */     this.leftZubon.setTextureSize(64, 64);
/* 133:135 */     this.leftZubon.mirror = true;
/* 134:136 */     setRotation(this.leftZubon, 0.0F, 0.0F, 0.0F);
/* 135:    */   }
/* 136:    */   
/* 137:    */   public void defaultAddChildSetting()
/* 138:    */   {
/* 139:141 */     super.defaultAddChildSetting();
/* 140:    */     
/* 141:143 */     this.bipedHead.addChild(this.rightTouch);
/* 142:144 */     this.bipedHead.addChild(this.leftTouch);
/* 143:145 */     this.bipedBody.addChild(this.Manto1);
/* 144:146 */     this.Manto1.addChild(this.Manto2);
/* 145:147 */     this.Manto2.addChild(this.Manto3);
/* 146:148 */     this.bipedRightLeg.addChild(this.rightZubon);
/* 147:149 */     this.bipedLeftLeg.addChild(this.leftZubon);
/* 148:    */   }
/* 149:    */   
/* 150:    */   protected void setTouhouModelData()
/* 151:    */   {
/* 152:156 */     setShoulderWidth(2.4F);
/* 153:157 */     setBodyHeight(7.0F);
/* 154:158 */     setLegHeight(12.0F);
/* 155:    */   }
/* 156:    */   
/* 157:    */   public void setRotationAnglesTHKaguya(float moveCounter, float moveCycle, float ticksExisted, float pheadYaw, float pheadPitch, float scale, EntityDanmakuMob entity)
/* 158:    */   {
/* 159:164 */     super.setRotationAnglesTHKaguya(moveCounter, moveCycle, ticksExisted, pheadYaw, pheadPitch, scale, entity);
/* 160:    */     
/* 161:    */ 
/* 162:167 */     this.Manto1.rotateAngleX = ((float)Math.sin((moveCounter + ticksExisted) / 10.0D) * 0.1F + 0.3F);
/* 163:168 */     this.Manto2.rotateAngleX = ((float)Math.sin((moveCounter * 1.2F + ticksExisted) / 7.8D) * 0.25F + 0.25F);
/* 164:169 */     this.Manto3.rotateAngleX = ((float)Math.sin((moveCounter * 1.5F + ticksExisted) / 5.3D) * 0.3F + 0.2F);
/* 165:171 */     if (!this.isRiding) {
/* 166:177 */       if (entity.getFlyingHeight() == 0)
/* 167:    */       {
/* 168:179 */         this.bipedRightLeg.rotateAngleX = (cos(moveCounter) * 0.7F * moveCycle);
/* 169:180 */         this.bipedLeftLeg.rotateAngleX = (cos(moveCounter + 3.141593F) * 0.7F * moveCycle);
/* 170:181 */         this.bipedRightLeg.rotateAngleZ = 0.0F;
/* 171:182 */         this.bipedLeftLeg.rotateAngleZ = 0.0F;
/* 172:184 */         if (moveCounter > 0.0F)
/* 173:    */         {
/* 174:186 */           this.bipedRightArm.rotateAngleX = (cos(moveCounter + 3.141593F) * 2.0F * moveCycle * 0.5F);
/* 175:187 */           this.bipedLeftArm.rotateAngleX = (cos(moveCounter) * 2.0F * moveCycle * 0.5F);
/* 176:188 */           this.bipedRightArm.rotateAngleY = rad(-10.0F);
/* 177:189 */           this.bipedRightArm.rotateAngleZ = rad(20.0F);
/* 178:190 */           this.bipedLeftArm.rotateAngleY = (-this.bipedRightArm.rotateAngleY);
/* 179:191 */           this.bipedLeftArm.rotateAngleZ = (-this.bipedRightArm.rotateAngleZ);
/* 180:    */         }
/* 181:    */         else
/* 182:    */         {
/* 183:195 */           this.bipedRightArm.rotateAngleX = (-0.7F - sin(ticksExisted / 10.0F) * 0.1F);
/* 184:196 */           this.bipedRightArm.rotateAngleY = 0.0F;
/* 185:197 */           this.bipedRightArm.rotateAngleZ = -0.645772F;
/* 186:198 */           this.bipedLeftArm.rotateAngleX = this.bipedRightArm.rotateAngleX;
/* 187:199 */           this.bipedLeftArm.rotateAngleY = 0.0F;
/* 188:200 */           this.bipedLeftArm.rotateAngleZ = 0.645772F;
/* 189:    */         }
/* 190:    */       }
/* 191:    */       else
/* 192:    */       {
/* 193:207 */         this.bipedRightLeg.rotateAngleZ = Math.abs(sin(ticksExisted / 10.0F) * 0.1F);
/* 194:208 */         this.bipedLeftLeg.rotateAngleZ = (-this.bipedRightLeg.rotateAngleZ);
/* 195:209 */         this.bipedRightLeg.rotateAngleX = Math.abs(sin(ticksExisted / 10.0F) * 0.2F);
/* 196:210 */         this.bipedLeftLeg.rotateAngleX = this.bipedRightLeg.rotateAngleZ;
/* 197:    */         
/* 198:    */ 
/* 199:    */ 
/* 200:    */ 
/* 201:    */ 
/* 202:    */ 
/* 203:    */ 
/* 204:    */ 
/* 205:    */ 
/* 206:220 */         this.bipedRightArm.rotateAngleX = rad(0.0F);
/* 207:221 */         this.bipedRightArm.rotateAngleZ = rad(20.0F + sin(ticksExisted / 10.0F) * 5.0F);
/* 208:222 */         this.bipedLeftArm.rotateAngleX = rad(0.0F);
/* 209:223 */         this.bipedLeftArm.rotateAngleZ = rad(-20.0F - sin(ticksExisted / 10.0F) * 5.0F);
/* 210:    */       }
/* 211:    */     }
/* 212:    */   }
/* 213:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.model.living.ModelWriggle
 * JD-Core Version:    0.7.0.1
 */