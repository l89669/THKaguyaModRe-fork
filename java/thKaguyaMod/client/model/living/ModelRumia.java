/*   1:    */ package thKaguyaMod.client.model.living;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.relauncher.Side;
/*   4:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   5:    */ import net.minecraft.client.model.ModelBase;
/*   6:    */ import net.minecraft.client.model.ModelRenderer;
/*   7:    */ import net.minecraft.entity.Entity;
/*   8:    */ import net.minecraft.util.MathHelper;
/*   9:    */ import thKaguyaMod.entity.living.EntityRumia;
/*  10:    */ 
/*  11:    */ @SideOnly(Side.CLIENT)
/*  12:    */ public class ModelRumia
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
/*  23:    */   ModelRenderer longHair;
/*  24:    */   
/*  25:    */   public ModelRumia()
/*  26:    */   {
/*  27: 29 */     this.textureWidth = 64;
/*  28: 30 */     this.textureHeight = 64;
/*  29:    */     
/*  30: 32 */     this.head = new ModelRenderer(this, 0, 0);
/*  31: 33 */     this.head.addBox(-4.0F, -3.0F, -4.0F, 8, 8, 8);
/*  32: 34 */     this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  33: 35 */     this.head.setTextureSize(64, 64);
/*  34: 36 */     this.head.mirror = true;
/*  35:    */     
/*  36: 38 */     this.longHair = new ModelRenderer(this, 24, 0);
/*  37: 39 */     this.longHair.addBox(-4.0F, 4.0F, -3.0F, 8, 5, 3);
/*  38: 40 */     this.longHair.setRotationPoint(0.0F, 0.0F, 4.0F);
/*  39:    */     
/*  40:    */ 
/*  41:    */ 
/*  42: 44 */     this.head.addChild(this.longHair);
/*  43:    */     
/*  44: 46 */     this.body = new ModelRenderer(this, 32, 8);
/*  45: 47 */     this.body.addBox(-3.0F, 5.0F, -2.0F, 6, 7, 4);
/*  46: 48 */     this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  47: 49 */     this.body.setTextureSize(64, 64);
/*  48: 50 */     this.body.mirror = true;
/*  49: 51 */     setRotation(this.body, 0.0F, 0.0F, 0.0F);
/*  50:    */     
/*  51: 53 */     this.skirt = new ModelRenderer(this, 0, 16);
/*  52: 54 */     this.skirt.addBox(-4.0F, 0.0F, -4.0F, 8, 5, 8);
/*  53: 55 */     this.skirt.setRotationPoint(0.0F, 10.0F, 0.0F);
/*  54:    */     
/*  55:    */ 
/*  56: 58 */     setRotation(this.skirt, 0.0F, 0.0F, 0.0F);
/*  57: 59 */     this.skirt2 = new ModelRenderer(this, 24, 32);
/*  58: 60 */     this.skirt2.addBox(-5.0F, -5.0F, -5.0F, 10, 6, 10);
/*  59: 61 */     this.skirt2.setRotationPoint(0.0F, 9.0F, 0.0F);
/*  60:    */     
/*  61:    */ 
/*  62: 64 */     this.skirt.addChild(this.skirt2);
/*  63: 65 */     setRotation(this.skirt2, 0.0F, 0.0F, 0.0F);
/*  64:    */     
/*  65: 67 */     this.rightArm = new ModelRenderer(this, 48, 0);
/*  66: 68 */     this.rightArm.addBox(-1.0F, -1.0F, -1.0F, 2, 8, 2);
/*  67: 69 */     this.rightArm.setRotationPoint(-4.0F, 6.0F, 0.0F);
/*  68: 70 */     this.rightArm.setTextureSize(64, 64);
/*  69: 71 */     this.rightArm.mirror = true;
/*  70:    */     
/*  71: 73 */     setRotation(this.rightArm, 0.0F, 0.0F, 0.0F);
/*  72:    */     
/*  73:    */ 
/*  74:    */ 
/*  75:    */ 
/*  76:    */ 
/*  77:    */ 
/*  78:    */ 
/*  79:    */ 
/*  80: 82 */     this.leftArm = new ModelRenderer(this, 56, 0);
/*  81: 83 */     this.leftArm.addBox(-1.0F, -1.0F, -1.0F, 2, 8, 2);
/*  82: 84 */     this.leftArm.setRotationPoint(4.0F, 6.0F, 0.0F);
/*  83:    */     
/*  84:    */ 
/*  85: 87 */     setRotation(this.leftArm, 0.0F, 0.0F, 0.0F);
/*  86: 88 */     this.rightLeg = new ModelRenderer(this, 50, 16);
/*  87: 89 */     this.rightLeg.addBox(-1.0F, 0.0F, -2.0F, 3, 12, 4);
/*  88: 90 */     this.rightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
/*  89:    */     
/*  90:    */ 
/*  91: 93 */     setRotation(this.rightLeg, 0.0F, 0.0F, 0.0F);
/*  92: 94 */     this.leftLeg = new ModelRenderer(this, 50, 16);
/*  93: 95 */     this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 3, 12, 4);
/*  94: 96 */     this.leftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
/*  95:    */     
/*  96:    */ 
/*  97: 99 */     setRotation(this.leftLeg, 0.0F, 0.0F, 0.0F);
/*  98:    */   }
/*  99:    */   
/* 100:    */   public void render(Entity entity, float movement, float far, float tick, float yaw, float pitch, float size)
/* 101:    */   {
/* 102:105 */     super.render(entity, movement, far, tick, yaw, pitch, size);
/* 103:106 */     setRotationAngles(movement, far, tick, yaw, pitch, size, entity);
/* 104:107 */     this.head.render(size);
/* 105:108 */     this.body.render(size);
/* 106:109 */     this.skirt.render(size);
/* 107:110 */     this.rightArm.render(size);
/* 108:111 */     this.rightLeg.render(size);
/* 109:112 */     this.leftArm.render(size);
/* 110:113 */     this.leftLeg.render(size);
/* 111:    */   }
/* 112:    */   
/* 113:    */   private void setRotation(ModelRenderer model, float rotateX, float rotateY, float rotateZ)
/* 114:    */   {
/* 115:118 */     model.rotateAngleX = rotateX;
/* 116:119 */     model.rotateAngleY = rotateY;
/* 117:120 */     model.rotateAngleZ = rotateZ;
/* 118:    */   }
/* 119:    */   
/* 120:    */   public void setRotationAngles(float movement, float far, float tick, float yaw, float pitch, float size, Entity entity)
/* 121:    */   {
/* 122:126 */     super.setRotationAngles(movement, far, tick, yaw, pitch, size, entity);
/* 123:127 */     EntityRumia rumia = (EntityRumia)entity;
/* 124:    */     
/* 125:129 */     this.head.rotateAngleY = (yaw / 57.295776F);
/* 126:130 */     this.head.rotateAngleX = (pitch / 57.295776F);
/* 127:131 */     this.body.rotateAngleY = (MathHelper.sin(MathHelper.sqrt_float(this.onGround) * 3.141593F * 2.0F) * 0.2F);
/* 128:132 */     this.skirt.rotateAngleX = 0.0F;
/* 129:135 */     if (rumia.getSpellCardMotion() < -15)
/* 130:    */     {
/* 131:137 */       this.rightArm.rotateAngleX = (-0.7F - MathHelper.sin(tick / 10.0F) * 0.1F - MathHelper.sin((30.0F + rumia.getSpellCardMotion()) / 180.0F * 3.141593F) * 4.0F);
/* 132:    */     }
/* 133:139 */     else if (rumia.getSpellCardMotion() < 0)
/* 134:    */     {
/* 135:141 */       this.rightArm.rotateAngleX = (-0.7F - MathHelper.sin(tick / 10.0F) * 0.1F - MathHelper.sin(-rumia.getSpellCardMotion() / 180.0F * 3.141593F) * 4.0F);
/* 136:    */     }
/* 137:    */     else
/* 138:    */     {
/* 139:145 */       this.rightArm.rotateAngleZ = 1.570796F;
/* 140:146 */       this.leftArm.rotateAngleZ = (-this.rightArm.rotateAngleZ);
/* 141:    */     }
/* 142:149 */     if (this.isRiding)
/* 143:    */     {
/* 144:151 */       this.rightArm.rotateAngleX = -0.6283186F;
/* 145:152 */       this.leftArm.rotateAngleX = -0.6283186F;
/* 146:153 */       this.rightLeg.rotateAngleX = -1.256637F;
/* 147:154 */       this.leftLeg.rotateAngleX = -1.256637F;
/* 148:155 */       this.rightLeg.rotateAngleY = 0.2243995F;
/* 149:156 */       this.leftLeg.rotateAngleY = -0.2243995F;
/* 150:157 */       this.rightLeg.rotateAngleZ = 0.2243995F;
/* 151:158 */       this.leftLeg.rotateAngleZ = -0.2243995F;
/* 152:    */       
/* 153:160 */       this.skirt.rotateAngleX += -0.6283186F;
/* 154:    */     }
/* 155:165 */     else if (rumia.getFlyingHeight() == 0)
/* 156:    */     {
/* 157:167 */       this.rightLeg.rotateAngleX = (MathHelper.cos(movement) * 0.7F * far);
/* 158:168 */       this.leftLeg.rotateAngleX = (MathHelper.cos(movement + 3.141593F) * 0.7F * far);
/* 159:169 */       this.rightLeg.rotateAngleZ = 0.0F;
/* 160:170 */       this.leftLeg.rotateAngleZ = 0.0F;
/* 161:172 */       if (movement > 0.0F)
/* 162:    */       {
/* 163:174 */         this.rightArm.rotateAngleX = (MathHelper.cos(movement + 3.141593F) * 2.0F * far * 0.5F);
/* 164:175 */         this.leftArm.rotateAngleX = (MathHelper.cos(movement) * 2.0F * far * 0.5F);
/* 165:176 */         this.rightArm.rotateAngleY = -0.1745329F;
/* 166:177 */         this.rightArm.rotateAngleZ = 0.3490659F;
/* 167:178 */         this.leftArm.rotateAngleY = (-this.rightArm.rotateAngleY);
/* 168:179 */         this.leftArm.rotateAngleZ = (-this.rightArm.rotateAngleZ);
/* 169:    */       }
/* 170:    */       else
/* 171:    */       {
/* 172:183 */         this.rightArm.rotateAngleX = (-0.7F - MathHelper.sin(tick / 10.0F) * 0.1F);
/* 173:184 */         this.rightArm.rotateAngleY = 0.0F;
/* 174:185 */         this.rightArm.rotateAngleZ = -0.645772F;
/* 175:186 */         this.leftArm.rotateAngleX = this.rightArm.rotateAngleX;
/* 176:187 */         this.leftArm.rotateAngleY = 0.0F;
/* 177:188 */         this.leftArm.rotateAngleZ = 0.645772F;
/* 178:    */       }
/* 179:    */     }
/* 180:    */     else
/* 181:    */     {
/* 182:195 */       this.rightLeg.rotateAngleZ = Math.abs(MathHelper.sin(tick / 10.0F) * 0.1F);
/* 183:196 */       this.leftLeg.rotateAngleZ = (-this.rightLeg.rotateAngleZ);
/* 184:197 */       this.rightLeg.rotateAngleX = Math.abs(MathHelper.sin(tick / 10.0F) * 0.2F);
/* 185:198 */       this.leftLeg.rotateAngleX = this.rightLeg.rotateAngleZ;
/* 186:    */       
/* 187:    */ 
/* 188:    */ 
/* 189:    */ 
/* 190:    */ 
/* 191:    */ 
/* 192:    */ 
/* 193:    */ 
/* 194:207 */       this.rightArm.rotateAngleZ = 1.570796F;
/* 195:208 */       this.rightArm.rotateAngleY = 0.0F;
/* 196:209 */       this.rightArm.rotateAngleX = 0.0F;
/* 197:210 */       this.leftArm.rotateAngleZ = (-this.rightArm.rotateAngleZ);
/* 198:211 */       this.leftArm.rotateAngleY = 0.0F;
/* 199:212 */       this.leftArm.rotateAngleX = 0.0F;
/* 200:    */     }
/* 201:    */   }
/* 202:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.model.living.ModelRumia
 * JD-Core Version:    0.7.0.1
 */