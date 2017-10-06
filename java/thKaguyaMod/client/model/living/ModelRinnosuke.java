/*   1:    */ package thKaguyaMod.client.model.living;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.relauncher.Side;
/*   4:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   5:    */ import net.minecraft.client.model.ModelBase;
/*   6:    */ import net.minecraft.client.model.ModelRenderer;
/*   7:    */ import net.minecraft.entity.Entity;
/*   8:    */ import net.minecraft.util.MathHelper;
/*   9:    */ 
/*  10:    */ @SideOnly(Side.CLIENT)
/*  11:    */ public class ModelRinnosuke
/*  12:    */   extends ModelBase
/*  13:    */ {
/*  14:    */   ModelRenderer bipedBody;
/*  15:    */   ModelRenderer bipedRightArm;
/*  16:    */   ModelRenderer bipedLeftArm;
/*  17:    */   ModelRenderer bipedRightLeg;
/*  18:    */   ModelRenderer bipedLeftLeg;
/*  19:    */   ModelRenderer box;
/*  20:    */   ModelRenderer bottom;
/*  21:    */   ModelRenderer bipedHead;
/*  22:    */   ModelRenderer longHair;
/*  23:    */   ModelRenderer Grass;
/*  24:    */   
/*  25:    */   public ModelRinnosuke()
/*  26:    */   {
/*  27: 28 */     this.textureWidth = 64;
/*  28: 29 */     this.textureHeight = 64;
/*  29:    */     
/*  30: 31 */     this.bipedBody = new ModelRenderer(this, 0, 16);
/*  31: 32 */     this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 10, 4);
/*  32: 33 */     this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  33: 34 */     this.bipedBody.setTextureSize(64, 64);
/*  34: 35 */     this.bipedBody.mirror = true;
/*  35: 36 */     setRotation(this.bipedBody, 0.0F, 0.0F, 0.0F);
/*  36: 37 */     this.bipedRightArm = new ModelRenderer(this, 0, 32);
/*  37: 38 */     this.bipedRightArm.addBox(-2.0F, -1.0F, -1.5F, 3, 10, 3);
/*  38: 39 */     this.bipedRightArm.setRotationPoint(-5.0F, 1.0F, 0.0F);
/*  39: 40 */     this.bipedRightArm.setTextureSize(64, 64);
/*  40: 41 */     this.bipedRightArm.mirror = true;
/*  41: 42 */     setRotation(this.bipedRightArm, 0.0F, 0.0F, 0.0F);
/*  42: 43 */     this.bipedLeftArm = new ModelRenderer(this, 12, 32);
/*  43: 44 */     this.bipedLeftArm.addBox(-1.0F, -1.0F, -1.5F, 3, 10, 3);
/*  44: 45 */     this.bipedLeftArm.setRotationPoint(5.0F, 1.0F, 0.0F);
/*  45: 46 */     this.bipedLeftArm.setTextureSize(64, 64);
/*  46: 47 */     this.bipedLeftArm.mirror = true;
/*  47: 48 */     setRotation(this.bipedLeftArm, 0.0F, 0.0F, 0.0F);
/*  48: 49 */     this.bipedRightLeg = new ModelRenderer(this, 48, 16);
/*  49: 50 */     this.bipedRightLeg.addBox(-1.0F, 0.0F, -2.0F, 4, 14, 4);
/*  50: 51 */     this.bipedRightLeg.setRotationPoint(-3.0F, 10.0F, 0.0F);
/*  51: 52 */     this.bipedRightLeg.setTextureSize(64, 64);
/*  52: 53 */     this.bipedRightLeg.mirror = true;
/*  53: 54 */     setRotation(this.bipedRightLeg, 0.0F, 0.0F, 0.0F);
/*  54: 55 */     this.bipedLeftLeg = new ModelRenderer(this, 32, 16);
/*  55: 56 */     this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 14, 4);
/*  56: 57 */     this.bipedLeftLeg.setRotationPoint(2.0F, 10.0F, 0.0F);
/*  57: 58 */     this.bipedLeftLeg.setTextureSize(64, 64);
/*  58: 59 */     this.bipedLeftLeg.mirror = true;
/*  59: 60 */     setRotation(this.bipedLeftLeg, 0.0F, 0.0F, 0.0F);
/*  60: 61 */     this.box = new ModelRenderer(this, 32, 10);
/*  61: 62 */     this.box.addBox(-3.0F, -2.0F, 0.0F, 6, 4, 2);
/*  62: 63 */     this.box.setRotationPoint(0.0F, 8.0F, -4.0F);
/*  63: 64 */     this.box.setTextureSize(64, 64);
/*  64: 65 */     this.box.mirror = true;
/*  65: 66 */     setRotation(this.box, 0.0F, 0.0F, 0.0F);
/*  66: 67 */     this.bottom = new ModelRenderer(this, 0, 46);
/*  67: 68 */     this.bottom.addBox(-4.5F, 0.0F, -2.5F, 9, 13, 5);
/*  68: 69 */     this.bottom.setRotationPoint(0.0F, 8.0F, 0.0F);
/*  69: 70 */     this.bottom.setTextureSize(64, 64);
/*  70: 71 */     this.bottom.mirror = true;
/*  71: 72 */     setRotation(this.bottom, 0.0F, 0.0F, 0.0F);
/*  72: 73 */     this.bipedHead = new ModelRenderer(this, 0, 0);
/*  73: 74 */     this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
/*  74: 75 */     this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  75: 76 */     this.bipedHead.setTextureSize(64, 64);
/*  76: 77 */     this.bipedHead.mirror = true;
/*  77: 78 */     setRotation(this.bipedHead, 0.0F, 0.0F, 0.0F);
/*  78: 79 */     this.longHair = new ModelRenderer(this, 24, 0);
/*  79: 80 */     this.longHair.addBox(-4.0F, 0.0F, -3.0F, 8, 2, 3);
/*  80: 81 */     this.longHair.setRotationPoint(0.0F, 0.0F, 4.0F);
/*  81: 82 */     this.longHair.setTextureSize(64, 64);
/*  82: 83 */     this.longHair.mirror = true;
/*  83: 84 */     setRotation(this.longHair, 0.0F, 0.0F, 0.0F);
/*  84: 85 */     this.Grass = new ModelRenderer(this, 48, 0);
/*  85: 86 */     this.Grass.addBox(-4.0F, 0.0F, 0.0F, 8, 2, 1);
/*  86: 87 */     this.Grass.setRotationPoint(0.0F, -3.0F, -4.2F);
/*  87: 88 */     this.Grass.setTextureSize(64, 64);
/*  88: 89 */     this.Grass.mirror = true;
/*  89: 90 */     setRotation(this.Grass, 0.0F, 0.0F, 0.0F);
/*  90:    */     
/*  91: 92 */     this.bipedHead.addChild(this.longHair);
/*  92: 93 */     this.bipedHead.addChild(this.Grass);
/*  93: 94 */     this.bipedBody.addChild(this.box);
/*  94: 95 */     this.bipedBody.addChild(this.bottom);
/*  95:    */   }
/*  96:    */   
/*  97:    */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*  98:    */   {
/*  99:100 */     super.render(entity, f, f1, f2, f3, f4, f5);
/* 100:101 */     setRotationAngles(f, f1, f2, f3, f4, f5, entity);
/* 101:102 */     this.bipedBody.render(f5);
/* 102:103 */     this.bipedRightArm.render(f5);
/* 103:    */     
/* 104:105 */     this.bipedLeftArm.render(f5);
/* 105:    */     
/* 106:107 */     this.bipedRightLeg.render(f5);
/* 107:108 */     this.bipedLeftLeg.render(f5);
/* 108:    */     
/* 109:    */ 
/* 110:111 */     this.bipedHead.render(f5);
/* 111:    */     
/* 112:    */ 
/* 113:    */ 
/* 114:    */ 
/* 115:    */ 
/* 116:    */ 
/* 117:    */ 
/* 118:    */ 
/* 119:    */ 
/* 120:    */ 
/* 121:    */ 
/* 122:    */ 
/* 123:    */ 
/* 124:    */ 
/* 125:    */ 
/* 126:    */ 
/* 127:    */ 
/* 128:    */ 
/* 129:    */ 
/* 130:    */ 
/* 131:    */ 
/* 132:133 */     this.bipedRightArm.rotateAngleX = -0.2094395F;
/* 133:134 */     this.bipedRightArm.rotateAngleY = -0.1745329F;
/* 134:135 */     this.bipedRightArm.rotateAngleZ = 0.5235988F;
/* 135:    */     
/* 136:137 */     this.bipedLeftArm.rotateAngleX = this.bipedRightArm.rotateAngleX;
/* 137:138 */     this.bipedLeftArm.rotateAngleY = (-this.bipedRightArm.rotateAngleY);
/* 138:139 */     this.bipedLeftArm.rotateAngleZ = (-this.bipedRightArm.rotateAngleZ);
/* 139:    */   }
/* 140:    */   
/* 141:    */   private void setRotation(ModelRenderer model, float rotateX, float rotateY, float rotateZ)
/* 142:    */   {
/* 143:153 */     model.rotateAngleX = rotateX;
/* 144:154 */     model.rotateAngleY = rotateY;
/* 145:155 */     model.rotateAngleZ = rotateZ;
/* 146:    */   }
/* 147:    */   
/* 148:    */   public void setRotationAngles(float movement, float far, float f2, float f3, float f4, float f5, Entity entity)
/* 149:    */   {
/* 150:162 */     this.bipedHead.rotateAngleY = (f3 / 57.295776F);
/* 151:163 */     this.bipedHead.rotateAngleX = (f4 / 57.295776F);
/* 152:164 */     this.bipedBody.rotateAngleY = (MathHelper.sin(MathHelper.sqrt_float(this.onGround) * 3.141593F * 2.0F) * 0.2F);
/* 153:    */     
/* 154:166 */     this.bipedRightLeg.rotateAngleX = (MathHelper.cos(movement * 0.6662F) * 0.7F * far);
/* 155:167 */     this.bipedLeftLeg.rotateAngleX = (MathHelper.cos(movement * 0.6662F + 3.141593F) * 0.7F * far);
/* 156:168 */     this.bipedRightLeg.rotateAngleY = 0.0F;
/* 157:169 */     this.bipedLeftLeg.rotateAngleY = 0.0F;
/* 158:170 */     this.bipedRightLeg.rotateAngleZ = 0.0F;
/* 159:171 */     this.bipedLeftLeg.rotateAngleZ = 0.0F;
/* 160:    */     
/* 161:173 */     this.bipedRightArm.rotateAngleX = (MathHelper.cos(movement * 0.6662F + 3.141593F) * 2.0F * far * 0.5F);
/* 162:174 */     this.bipedLeftArm.rotateAngleX = (MathHelper.cos(movement * 0.6662F) * 2.0F * far * 0.5F);
/* 163:    */     
/* 164:176 */     this.bipedRightArm.rotateAngleZ = 0.1745329F;
/* 165:    */     
/* 166:178 */     this.bipedLeftArm.rotateAngleZ = (-this.bipedRightArm.rotateAngleZ);
/* 167:180 */     if (this.isRiding)
/* 168:    */     {
/* 169:182 */       this.bipedRightArm.rotateAngleX = -0.6283186F;
/* 170:183 */       this.bipedLeftArm.rotateAngleX = -0.6283186F;
/* 171:184 */       this.bipedRightLeg.rotateAngleX = -1.256637F;
/* 172:185 */       this.bipedLeftLeg.rotateAngleX = -1.256637F;
/* 173:186 */       this.bipedRightLeg.rotateAngleY = 0.2243995F;
/* 174:187 */       this.bipedLeftLeg.rotateAngleY = -0.2243995F;
/* 175:188 */       this.bipedRightLeg.rotateAngleZ = 0.2243995F;
/* 176:189 */       this.bipedLeftLeg.rotateAngleZ = -0.2243995F;
/* 177:    */     }
/* 178:217 */     this.bipedRightArm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
/* 179:218 */     this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
/* 180:219 */     this.bipedRightArm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
/* 181:220 */     this.bipedLeftArm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
/* 182:    */   }
/* 183:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.model.living.ModelRinnosuke
 * JD-Core Version:    0.7.0.1
 */