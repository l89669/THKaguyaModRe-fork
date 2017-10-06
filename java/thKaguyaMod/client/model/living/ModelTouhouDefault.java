/*   1:    */ package thKaguyaMod.client.model.living;
/*   2:    */ 
/*   3:    */ import net.minecraft.client.model.ModelBase;
/*   4:    */ import net.minecraft.client.model.ModelRenderer;
/*   5:    */ import net.minecraft.entity.Entity;
/*   6:    */ import net.minecraft.util.MathHelper;
/*   7:    */ import thKaguyaMod.entity.living.EntityDanmakuMob;
/*   8:    */ 
/*   9:    */ public class ModelTouhouDefault
/*  10:    */   extends ModelBase
/*  11:    */ {
/*  12: 15 */   protected final float fPI = 3.141593F;
/*  13:    */   public ModelRenderer bipedHead;
/*  14:    */   public ModelRenderer bipedBody;
/*  15:    */   public ModelRenderer bipedRightArm;
/*  16:    */   public ModelRenderer bipedLeftArm;
/*  17:    */   public ModelRenderer bipedRightLeg;
/*  18:    */   public ModelRenderer bipedLeftLeg;
/*  19:    */   public ModelRenderer skirtTop;
/*  20:    */   public ModelRenderer skirtBottom;
/*  21:    */   public ModelRenderer longHair;
/*  22:    */   public ModelRenderer rightBraid;
/*  23:    */   public ModelRenderer leftBraid;
/*  24: 38 */   private float legHeight = 0.0F;
/*  25: 39 */   private float bodyHeight = 0.0F;
/*  26: 40 */   private float shoulderWidth = 0.0F;
/*  27:    */   
/*  28:    */   public ModelTouhouDefault()
/*  29:    */   {
/*  30: 46 */     this(0.0F);
/*  31:    */   }
/*  32:    */   
/*  33:    */   public ModelTouhouDefault(float f)
/*  34:    */   {
/*  35: 51 */     this(f, 0.0F);
/*  36:    */   }
/*  37:    */   
/*  38:    */   public ModelTouhouDefault(float f, float f1)
/*  39:    */   {
/*  40: 56 */     this(f, f1, 64, 64);
/*  41:    */   }
/*  42:    */   
/*  43:    */   public ModelTouhouDefault(float f, float f1, int t_height, int t_width)
/*  44:    */   {
/*  45: 61 */     this.textureHeight = t_height;
/*  46: 62 */     this.textureWidth = t_width;
/*  47:    */     
/*  48: 64 */     setTouhouModelData();
/*  49:    */     
/*  50: 66 */     initModel(f, f1, false);
/*  51:    */   }
/*  52:    */   
/*  53:    */   public void initModel(float f, float f1, boolean isAfterInit)
/*  54:    */   {
/*  55: 72 */     this.textureHeight = 64;
/*  56: 73 */     this.textureWidth = getTextureWidth();
/*  57:    */     
/*  58:    */ 
/*  59: 76 */     getTechneModelData();
/*  60:    */     
/*  61:    */ 
/*  62: 79 */     deleteParts();
/*  63:    */     
/*  64: 81 */     defaultAddChildSetting();
/*  65:    */   }
/*  66:    */   
/*  67:    */   public void render(Entity entity, float movement, float far, float tick, float yaw, float pitch, float size)
/*  68:    */   {
/*  69: 96 */     super.render(entity, movement, far, tick, yaw, pitch, size);
/*  70: 97 */     setRotationAngles(movement, far, tick, yaw, pitch, size, entity);
/*  71: 98 */     this.bipedBody.render(size);
/*  72:    */   }
/*  73:    */   
/*  74:    */   protected void getTechneModelData() {}
/*  75:    */   
/*  76:    */   protected int getTextureWidth()
/*  77:    */   {
/*  78:109 */     return 64;
/*  79:    */   }
/*  80:    */   
/*  81:    */   protected void setTouhouModelData()
/*  82:    */   {
/*  83:115 */     setShoulderWidth(3.0F);
/*  84:    */   }
/*  85:    */   
/*  86:    */   protected void deleteParts() {}
/*  87:    */   
/*  88:    */   public void defaultAddChildSetting()
/*  89:    */   {
/*  90:125 */     this.bipedBody.addChild(this.bipedHead);
/*  91:126 */     this.bipedBody.addChild(this.bipedRightArm);
/*  92:127 */     this.bipedBody.addChild(this.bipedLeftArm);
/*  93:128 */     this.bipedBody.addChild(this.bipedRightLeg);
/*  94:129 */     this.bipedBody.addChild(this.bipedLeftLeg);
/*  95:130 */     if (this.rightBraid != null) {
/*  96:130 */       this.bipedHead.addChild(this.rightBraid);
/*  97:    */     }
/*  98:131 */     if (this.leftBraid != null) {
/*  99:131 */       this.bipedHead.addChild(this.leftBraid);
/* 100:    */     }
/* 101:132 */     if (this.longHair != null) {
/* 102:132 */       this.bipedHead.addChild(this.longHair);
/* 103:    */     }
/* 104:133 */     if (this.skirtTop != null) {
/* 105:133 */       this.bipedBody.addChild(this.skirtTop);
/* 106:    */     }
/* 107:134 */     if (this.skirtBottom != null) {
/* 108:134 */       this.skirtTop.addChild(this.skirtBottom);
/* 109:    */     }
/* 110:    */   }
/* 111:    */   
/* 112:    */   public void setRotationAngles(float moveCounter, float moveCycle, float ticksExisted, float pheadYaw, float pheadPitch, float scale, Entity entity)
/* 113:    */   {
/* 114:161 */     setRotationAnglesTHKaguya(moveCounter, moveCycle, ticksExisted, pheadYaw, pheadPitch, scale, (EntityDanmakuMob)entity);
/* 115:162 */     setRotationAnglesAfterTHKaguya(moveCounter, moveCycle, ticksExisted, pheadYaw, pheadPitch, scale, (EntityDanmakuMob)entity);
/* 116:    */   }
/* 117:    */   
/* 118:    */   public void setRotationAnglesAfterTHKaguya(float moveCounter, float moveCycle, float ticksExisted, float pheadYaw, float pheadPitch, float scale, EntityDanmakuMob entity)
/* 119:    */   {
/* 120:169 */     if (entity.getSpellCardMotion() < -15) {
/* 121:171 */       this.bipedRightArm.rotateAngleX = (-0.7F - sin(ticksExisted / 10.0F) * 0.1F - sin(rad(30.0F + entity.getSpellCardMotion())) * 4.0F);
/* 122:173 */     } else if (entity.getSpellCardMotion() < 0) {
/* 123:175 */       this.bipedRightArm.rotateAngleX = (-0.7F - sin(ticksExisted / 10.0F) * 0.1F - sin(rad(-entity.getSpellCardMotion())) * 4.0F);
/* 124:    */     }
/* 125:    */   }
/* 126:    */   
/* 127:    */   public void setRotationAnglesTHKaguya(float moveCounter, float moveCycle, float ticksExisted, float pheadYaw, float pheadPitch, float scale, EntityDanmakuMob entity)
/* 128:    */   {
/* 129:190 */     setDefaultPause(moveCounter, moveCycle, ticksExisted, pheadYaw, pheadPitch, scale, entity);
/* 130:    */     
/* 131:192 */     this.bipedBody.rotateAngleX = 0.0F;
/* 132:193 */     this.bipedHead.rotateAngleY = (pheadYaw / 57.29578F);
/* 133:194 */     this.bipedHead.rotateAngleX = (pheadPitch / 57.29578F);
/* 134:    */     
/* 135:196 */     this.bipedRightArm.rotateAngleX = (cos(moveCounter * 0.6662F + 3.141593F) * 2.0F * moveCycle * 0.5F);
/* 136:197 */     this.bipedRightArm.rotateAngleY = rad(90.0F);
/* 137:198 */     this.bipedLeftArm.rotateAngleX = (cos(moveCounter * 0.6662F) * 2.0F * moveCycle * 0.5F);
/* 138:199 */     this.bipedLeftArm.rotateAngleY = rad(90.0F);
/* 139:    */     
/* 140:    */ 
/* 141:    */ 
/* 142:    */ 
/* 143:204 */     this.bipedRightLeg.rotateAngleX = (cos(moveCounter * 0.6662F) * 0.5F * moveCycle);
/* 144:205 */     this.bipedLeftLeg.rotateAngleX = (cos(moveCounter * 0.6662F + 3.141593F) * 0.5F * moveCycle);
/* 145:206 */     this.bipedRightLeg.rotateAngleY = (this.bipedLeftLeg.rotateAngleY = this.bipedRightLeg.rotateAngleZ = this.bipedLeftLeg.rotateAngleZ = 0.0F);
/* 146:208 */     if (this.skirtTop != null)
/* 147:    */     {
/* 148:210 */       this.skirtTop.rotateAngleX = 0.0F;
/* 149:211 */       if (this.skirtBottom != null) {
/* 150:213 */         this.skirtBottom.rotateAngleX = 0.0F;
/* 151:    */       }
/* 152:    */     }
/* 153:220 */     if (this.isRiding)
/* 154:    */     {
/* 155:223 */       this.bipedBody.rotationPointY = -1.0F;
/* 156:    */       
/* 157:    */ 
/* 158:226 */       this.bipedRightArm.rotateAngleX += -rad(36.0F);
/* 159:227 */       this.bipedLeftArm.rotateAngleX += -rad(36.0F);
/* 160:    */       
/* 161:229 */       this.bipedRightLeg.rotateAngleX = (-rad(72.0F));
/* 162:230 */       this.bipedLeftLeg.rotateAngleX = (-rad(72.0F));
/* 163:231 */       this.bipedRightLeg.rotateAngleY = rad(13.0F);
/* 164:232 */       this.bipedLeftLeg.rotateAngleY = (-rad(13.0F));
/* 165:233 */       this.bipedRightLeg.rotateAngleZ = rad(13.0F);
/* 166:234 */       this.bipedLeftLeg.rotateAngleZ = (-rad(13.0F));
/* 167:237 */       if (this.skirtTop != null)
/* 168:    */       {
/* 169:239 */         this.skirtTop.rotateAngleX = (-rad(20.0F));
/* 170:240 */         if (this.skirtBottom != null) {
/* 171:242 */           this.skirtBottom.rotateAngleX = (-rad(30.0F));
/* 172:    */         }
/* 173:    */       }
/* 174:246 */       InRidingMotion();
/* 175:    */     }
/* 176:253 */     this.bipedRightArm.rotateAngleY = (this.bipedLeftArm.rotateAngleY = 0.0F);
/* 177:    */   }
/* 178:    */   
/* 179:    */   protected void InRidingMotion() {}
/* 180:    */   
/* 181:    */   public void setDefaultPause(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/* 182:    */   {
/* 183:272 */     if (this.bipedHead != null) {
/* 184:272 */       this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 185:    */     }
/* 186:273 */     if (this.bipedBody != null) {
/* 187:273 */       this.bipedBody.setRotationPoint(0.0F, -(getBodyHeight() + getLegHeight() - 25.0F), 0.0F);
/* 188:    */     }
/* 189:274 */     if (this.bipedRightArm != null) {
/* 190:274 */       this.bipedRightArm.setRotationPoint(-getShoulderWidth(), 2.0F, 0.0F);
/* 191:    */     }
/* 192:275 */     if (this.bipedLeftArm != null) {
/* 193:275 */       this.bipedLeftArm.setRotationPoint(getShoulderWidth(), 2.0F, 0.0F);
/* 194:    */     }
/* 195:276 */     if (this.bipedRightLeg != null) {
/* 196:276 */       this.bipedRightLeg.setRotationPoint(-2.0F, getBodyHeight(), 0.0F);
/* 197:    */     }
/* 198:277 */     if (this.bipedLeftLeg != null) {
/* 199:277 */       this.bipedLeftLeg.setRotationPoint(2.0F, getBodyHeight(), 0.0F);
/* 200:    */     }
/* 201:    */   }
/* 202:    */   
/* 203:    */   protected void setRotation(ModelRenderer model, float rotateX, float rotateY, float rotateZ)
/* 204:    */   {
/* 205:290 */     model.rotateAngleX = rotateX;
/* 206:291 */     model.rotateAngleY = rotateY;
/* 207:292 */     model.rotateAngleZ = rotateZ;
/* 208:    */   }
/* 209:    */   
/* 210:    */   protected void setLegHeight(float height)
/* 211:    */   {
/* 212:300 */     this.legHeight = height;
/* 213:    */   }
/* 214:    */   
/* 215:    */   protected void setBodyHeight(float height)
/* 216:    */   {
/* 217:308 */     this.bodyHeight = height;
/* 218:    */   }
/* 219:    */   
/* 220:    */   protected void setShoulderWidth(float width)
/* 221:    */   {
/* 222:317 */     this.shoulderWidth = width;
/* 223:    */   }
/* 224:    */   
/* 225:    */   protected float rad(float degree)
/* 226:    */   {
/* 227:329 */     return degree / 180.0F * 3.141593F;
/* 228:    */   }
/* 229:    */   
/* 230:    */   protected float sin(float angle)
/* 231:    */   {
/* 232:335 */     return MathHelper.sin(angle);
/* 233:    */   }
/* 234:    */   
/* 235:    */   protected float cos(float angle)
/* 236:    */   {
/* 237:341 */     return MathHelper.cos(angle);
/* 238:    */   }
/* 239:    */   
/* 240:    */   protected float getLegHeight()
/* 241:    */   {
/* 242:376 */     return this.legHeight;
/* 243:    */   }
/* 244:    */   
/* 245:    */   protected float getBodyHeight()
/* 246:    */   {
/* 247:389 */     return this.bodyHeight;
/* 248:    */   }
/* 249:    */   
/* 250:    */   protected float getShoulderWidth()
/* 251:    */   {
/* 252:397 */     return this.shoulderWidth;
/* 253:    */   }
/* 254:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.model.living.ModelTouhouDefault
 * JD-Core Version:    0.7.0.1
 */