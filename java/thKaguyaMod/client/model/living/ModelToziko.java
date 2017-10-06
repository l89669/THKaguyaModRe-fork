/*   1:    */ package thKaguyaMod.client.model.living;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.relauncher.Side;
/*   4:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   5:    */ import net.minecraft.client.model.ModelBase;
/*   6:    */ import net.minecraft.client.model.ModelRenderer;
/*   7:    */ import net.minecraft.entity.Entity;
/*   8:    */ import net.minecraft.util.MathHelper;
/*   9:    */ import thKaguyaMod.entity.living.EntityToziko;
/*  10:    */ 
/*  11:    */ @SideOnly(Side.CLIENT)
/*  12:    */ public class ModelToziko
/*  13:    */   extends ModelBase
/*  14:    */ {
/*  15:    */   ModelRenderer Body;
/*  16:    */   ModelRenderer RightArm;
/*  17:    */   ModelRenderer LeftArm;
/*  18:    */   ModelRenderer Leg1;
/*  19:    */   ModelRenderer Leg2;
/*  20:    */   ModelRenderer Leg3;
/*  21:    */   ModelRenderer SkirtTop;
/*  22:    */   ModelRenderer SkirtBottom;
/*  23:    */   ModelRenderer HeadBase;
/*  24:    */   ModelRenderer BackHair;
/*  25:    */   ModelRenderer LeftHair;
/*  26:    */   ModelRenderer RightHair;
/*  27:    */   ModelRenderer Cup;
/*  28:    */   ModelRenderer CupLeft;
/*  29:    */   ModelRenderer CupRight;
/*  30:    */   ModelRenderer CupRibbon;
/*  31:    */   
/*  32:    */   public ModelToziko()
/*  33:    */   {
/*  34: 34 */     this.textureWidth = 64;
/*  35: 35 */     this.textureHeight = 64;
/*  36:    */     
/*  37: 37 */     this.Body = new ModelRenderer(this, 32, 6);
/*  38: 38 */     this.Body.addBox(-3.0F, 0.0F, -2.0F, 6, 9, 4);
/*  39: 39 */     this.Body.setRotationPoint(0.0F, 2.0F, 0.0F);
/*  40: 40 */     this.Body.setTextureSize(64, 64);
/*  41: 41 */     this.Body.mirror = true;
/*  42: 42 */     setRotation(this.Body, 0.0F, 0.0F, 0.0F);
/*  43: 43 */     this.RightArm = new ModelRenderer(this, 48, 0);
/*  44: 44 */     this.RightArm.addBox(-2.0F, -1.0F, -1.0F, 2, 8, 2);
/*  45: 45 */     this.RightArm.setRotationPoint(-3.0F, 3.0F, 0.0F);
/*  46: 46 */     this.RightArm.setTextureSize(64, 64);
/*  47: 47 */     this.RightArm.mirror = true;
/*  48: 48 */     setRotation(this.RightArm, -1.570796F, 0.0F, 0.0F);
/*  49: 49 */     this.LeftArm = new ModelRenderer(this, 48, 0);
/*  50: 50 */     this.LeftArm.addBox(0.0F, -1.0F, -1.0F, 2, 8, 2);
/*  51: 51 */     this.LeftArm.setRotationPoint(3.0F, 3.0F, 0.0F);
/*  52: 52 */     this.LeftArm.setTextureSize(64, 64);
/*  53: 53 */     this.LeftArm.mirror = true;
/*  54: 54 */     setRotation(this.LeftArm, -1.570796F, 0.0F, 0.0F);
/*  55: 55 */     this.Leg1 = new ModelRenderer(this, 32, 17);
/*  56: 56 */     this.Leg1.addBox(-4.0F, 0.0F, -4.0F, 8, 7, 8);
/*  57: 57 */     this.Leg1.setRotationPoint(0.0F, 12.0F, 0.0F);
/*  58: 58 */     this.Leg1.setTextureSize(64, 64);
/*  59: 59 */     this.Leg1.mirror = true;
/*  60: 60 */     setRotation(this.Leg1, 0.0F, 0.0F, 0.0F);
/*  61: 61 */     this.Leg2 = new ModelRenderer(this, 32, 17);
/*  62: 62 */     this.Leg2.addBox(-3.0F, 0.0F, -3.0F, 6, 4, 6);
/*  63: 63 */     this.Leg2.setRotationPoint(0.0F, 17.0F, 0.0F);
/*  64: 64 */     this.Leg2.setTextureSize(64, 64);
/*  65: 65 */     this.Leg2.mirror = true;
/*  66: 66 */     setRotation(this.Leg2, 0.0F, 0.0F, 0.0F);
/*  67: 67 */     this.Leg3 = new ModelRenderer(this, 32, 17);
/*  68: 68 */     this.Leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 4, 4);
/*  69: 69 */     this.Leg3.setRotationPoint(0.0F, 20.0F, 0.0F);
/*  70: 70 */     this.Leg3.setTextureSize(64, 64);
/*  71: 71 */     this.Leg3.mirror = true;
/*  72: 72 */     setRotation(this.Leg3, 0.0F, 0.0F, 0.0F);
/*  73: 73 */     this.SkirtTop = new ModelRenderer(this, 0, 16);
/*  74: 74 */     this.SkirtTop.addBox(-4.0F, 0.0F, -4.0F, 8, 4, 8);
/*  75: 75 */     this.SkirtTop.setRotationPoint(0.0F, 8.0F, 0.0F);
/*  76: 76 */     this.SkirtTop.setTextureSize(64, 64);
/*  77: 77 */     this.SkirtTop.mirror = true;
/*  78: 78 */     setRotation(this.SkirtTop, 0.0F, 0.0F, 0.0F);
/*  79: 79 */     this.SkirtBottom = new ModelRenderer(this, 16, 32);
/*  80: 80 */     this.SkirtBottom.addBox(-5.0F, 0.0F, -5.0F, 10, 7, 10);
/*  81: 81 */     this.SkirtBottom.setRotationPoint(0.0F, 12.0F, 0.0F);
/*  82: 82 */     this.SkirtBottom.setTextureSize(64, 64);
/*  83: 83 */     this.SkirtBottom.mirror = true;
/*  84: 84 */     setRotation(this.SkirtBottom, 0.0F, 0.0F, 0.0F);
/*  85: 85 */     this.HeadBase = new ModelRenderer(this, 0, 0);
/*  86: 86 */     this.HeadBase.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
/*  87: 87 */     this.HeadBase.setRotationPoint(0.0F, 2.0F, 0.0F);
/*  88: 88 */     this.HeadBase.setTextureSize(64, 64);
/*  89: 89 */     this.HeadBase.mirror = true;
/*  90: 90 */     setRotation(this.HeadBase, 0.0F, 0.0F, 0.0F);
/*  91: 91 */     this.BackHair = new ModelRenderer(this, 0, 48);
/*  92: 92 */     this.BackHair.addBox(-4.0F, -2.0F, -3.0F, 8, 2, 3);
/*  93: 93 */     this.BackHair.setRotationPoint(0.0F, 2.0F, 4.0F);
/*  94: 94 */     this.BackHair.setTextureSize(64, 64);
/*  95: 95 */     this.BackHair.mirror = true;
/*  96: 96 */     this.HeadBase.addChild(this.BackHair);
/*  97: 97 */     setRotation(this.BackHair, 0.0F, 0.0F, 0.0F);
/*  98: 98 */     this.LeftHair = new ModelRenderer(this, 11, 56);
/*  99: 99 */     this.LeftHair.addBox(-4.0F, -2.0F, 0.0F, 8, 5, 1);
/* 100:100 */     this.LeftHair.setRotationPoint(5.0F, -3.0F, 0.0F);
/* 101:101 */     this.LeftHair.setTextureSize(64, 64);
/* 102:102 */     this.LeftHair.mirror = true;
/* 103:103 */     this.HeadBase.addChild(this.LeftHair);
/* 104:104 */     setRotation(this.LeftHair, 0.0F, -1.570796F, 0.0F);
/* 105:105 */     this.RightHair = new ModelRenderer(this, 11, 56);
/* 106:106 */     this.RightHair.addBox(-4.0F, -2.0F, 0.0F, 8, 5, 1);
/* 107:107 */     this.RightHair.setRotationPoint(-5.0F, -3.0F, 0.0F);
/* 108:108 */     this.RightHair.setTextureSize(64, 64);
/* 109:109 */     this.RightHair.mirror = true;
/* 110:110 */     this.HeadBase.addChild(this.RightHair);
/* 111:111 */     setRotation(this.RightHair, 0.0F, 1.570796F, 0.0F);
/* 112:112 */     this.Cup = new ModelRenderer(this, 0, 32);
/* 113:113 */     this.Cup.addBox(-2.0F, -8.0F, -2.0F, 4, 6, 4);
/* 114:114 */     this.Cup.setRotationPoint(0.0F, -6.0F, 0.0F);
/* 115:115 */     this.Cup.setTextureSize(64, 64);
/* 116:116 */     this.Cup.mirror = true;
/* 117:117 */     this.HeadBase.addChild(this.Cup);
/* 118:118 */     setRotation(this.Cup, 0.0F, 0.0F, 0.0F);
/* 119:119 */     this.CupLeft = new ModelRenderer(this, 0, 42);
/* 120:120 */     this.CupLeft.addBox(0.0F, 4.0F, 0.0F, 4, 1, 1);
/* 121:121 */     this.CupLeft.setRotationPoint(2.0F, -7.0F, 0.0F);
/* 122:122 */     this.CupLeft.setTextureSize(64, 64);
/* 123:123 */     this.CupLeft.mirror = true;
/* 124:124 */     this.Cup.addChild(this.CupLeft);
/* 125:125 */     setRotation(this.CupLeft, 0.0F, 0.0F, 0.0F);
/* 126:126 */     this.CupRight = new ModelRenderer(this, 0, 42);
/* 127:127 */     this.CupRight.addBox(-4.0F, 4.0F, 0.0F, 4, 1, 1);
/* 128:128 */     this.CupRight.setRotationPoint(-2.0F, -7.0F, 0.0F);
/* 129:129 */     this.CupRight.setTextureSize(64, 64);
/* 130:130 */     this.CupRight.mirror = true;
/* 131:131 */     this.Cup.addChild(this.CupRight);
/* 132:132 */     setRotation(this.CupRight, 0.0F, 0.0F, 0.0F);
/* 133:133 */     this.CupRibbon = new ModelRenderer(this, 0, 44);
/* 134:134 */     this.CupRibbon.addBox(-2.0F, 2.0F, 0.0F, 5, 4, 1);
/* 135:135 */     this.CupRibbon.setRotationPoint(2.0F, -7.0F, 1.0F);
/* 136:136 */     this.CupRibbon.setTextureSize(64, 64);
/* 137:137 */     this.CupRibbon.mirror = true;
/* 138:138 */     this.Cup.addChild(this.CupRibbon);
/* 139:139 */     setRotation(this.CupRibbon, 0.0F, -0.4833219F, -0.520501F);
/* 140:    */   }
/* 141:    */   
/* 142:    */   public void render(Entity entity, float movement, float far, float tick, float yaw, float pitch, float size)
/* 143:    */   {
/* 144:145 */     super.render(entity, movement, far, tick, yaw, pitch, size);
/* 145:146 */     setRotationAngles(movement, far, tick, yaw, pitch, size, entity);
/* 146:147 */     this.Body.render(size);
/* 147:148 */     this.RightArm.render(size);
/* 148:149 */     this.LeftArm.render(size);
/* 149:150 */     this.Leg1.render(size);
/* 150:151 */     this.Leg2.render(size);
/* 151:152 */     this.Leg3.render(size);
/* 152:153 */     this.SkirtTop.render(size);
/* 153:154 */     this.SkirtBottom.render(size);
/* 154:155 */     this.HeadBase.render(size);
/* 155:    */   }
/* 156:    */   
/* 157:    */   private void setRotation(ModelRenderer model, float rotateX, float rotateY, float rotateZ)
/* 158:    */   {
/* 159:167 */     model.rotateAngleX = rotateX;
/* 160:168 */     model.rotateAngleY = rotateY;
/* 161:169 */     model.rotateAngleZ = rotateZ;
/* 162:    */   }
/* 163:    */   
/* 164:    */   public void setRotationAngles(float movement, float far, float tick, float yaw, float pitch, float size, Entity entity)
/* 165:    */   {
/* 166:175 */     super.setRotationAngles(movement, far, tick, yaw, pitch, size, entity);
/* 167:176 */     EntityToziko toziko = (EntityToziko)entity;
/* 168:    */     
/* 169:178 */     this.HeadBase.rotateAngleY = (yaw / 57.295776F);
/* 170:179 */     this.HeadBase.rotateAngleX = (pitch / 57.295776F);
/* 171:180 */     this.Body.rotateAngleY = (MathHelper.sin(MathHelper.sqrt_float(this.onGround) * 3.141593F * 2.0F) * 0.2F);
/* 172:181 */     this.SkirtTop.rotateAngleX = 0.0F;
/* 173:184 */     if (toziko.getSpellCardMotion() < -15)
/* 174:    */     {
/* 175:186 */       this.RightArm.rotateAngleX = (-0.7F - MathHelper.sin(tick / 10.0F) * 0.1F - MathHelper.sin((30.0F + toziko.getSpellCardMotion()) / 180.0F * 3.141593F) * 4.0F);
/* 176:    */     }
/* 177:188 */     else if (toziko.getSpellCardMotion() < 0)
/* 178:    */     {
/* 179:190 */       this.RightArm.rotateAngleX = (-0.7F - MathHelper.sin(tick / 10.0F) * 0.1F - MathHelper.sin(-toziko.getSpellCardMotion() / 180.0F * 3.141593F) * 4.0F);
/* 180:    */     }
/* 181:    */     else
/* 182:    */     {
/* 183:194 */       this.RightArm.rotateAngleY = 1.570796F;
/* 184:195 */       this.LeftArm.rotateAngleY = (-this.RightArm.rotateAngleZ);
/* 185:    */     }
/* 186:198 */     if (this.isRiding)
/* 187:    */     {
/* 188:200 */       this.RightArm.rotateAngleX = -0.6283186F;
/* 189:201 */       this.LeftArm.rotateAngleX = -0.6283186F;
/* 190:    */       
/* 191:    */ 
/* 192:    */ 
/* 193:    */ 
/* 194:    */ 
/* 195:    */ 
/* 196:    */ 
/* 197:209 */       this.SkirtTop.rotateAngleX += -0.6283186F;
/* 198:    */     }
/* 199:214 */     else if (toziko.getFlyingHeight() == 0)
/* 200:    */     {
/* 201:221 */       if (movement > 0.0F)
/* 202:    */       {
/* 203:223 */         this.RightArm.rotateAngleX = (MathHelper.cos(movement + 3.141593F) * 2.0F * far * 0.5F);
/* 204:224 */         this.LeftArm.rotateAngleX = (MathHelper.cos(movement) * 2.0F * far * 0.5F);
/* 205:225 */         this.RightArm.rotateAngleY = -1.570796F;
/* 206:226 */         this.RightArm.rotateAngleZ = 0.3490659F;
/* 207:227 */         this.LeftArm.rotateAngleY = (-this.RightArm.rotateAngleY);
/* 208:228 */         this.LeftArm.rotateAngleZ = (-this.RightArm.rotateAngleZ);
/* 209:    */       }
/* 210:    */       else
/* 211:    */       {
/* 212:232 */         this.RightArm.rotateAngleX = (-0.7F - MathHelper.sin(tick / 10.0F) * 0.1F);
/* 213:233 */         this.RightArm.rotateAngleY = 0.0F;
/* 214:234 */         this.RightArm.rotateAngleZ = -0.645772F;
/* 215:235 */         this.LeftArm.rotateAngleX = this.RightArm.rotateAngleX;
/* 216:236 */         this.LeftArm.rotateAngleY = 0.0F;
/* 217:237 */         this.LeftArm.rotateAngleZ = 0.645772F;
/* 218:    */       }
/* 219:    */     }
/* 220:    */     else
/* 221:    */     {
/* 222:256 */       this.RightArm.rotateAngleZ = 0.0F;
/* 223:257 */       this.RightArm.rotateAngleY = (MathHelper.sin(tick / 13.0F) * 0.1F);
/* 224:258 */       this.RightArm.rotateAngleX = (-1.396264F + MathHelper.sin(tick / 10.0F) * 0.1F);
/* 225:259 */       this.LeftArm.rotateAngleZ = 0.0F;
/* 226:260 */       this.LeftArm.rotateAngleY = (-this.RightArm.rotateAngleY);
/* 227:261 */       this.LeftArm.rotateAngleX = this.RightArm.rotateAngleX;
/* 228:    */     }
/* 229:    */   }
/* 230:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.model.living.ModelToziko
 * JD-Core Version:    0.7.0.1
 */