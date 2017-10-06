/*   1:    */ package thKaguyaMod.entity.living;
/*   2:    */ 
/*   3:    */ import java.util.Random;
/*   4:    */ import net.minecraft.block.Block;
/*   5:    */ import net.minecraft.entity.EntityLivingBase;
/*   6:    */ import net.minecraft.entity.player.EntityPlayer;
/*   7:    */ import net.minecraft.init.Blocks;
/*   8:    */ import net.minecraft.util.AxisAlignedBB;
/*   9:    */ import net.minecraft.util.DamageSource;
/*  10:    */ import net.minecraft.util.MathHelper;
/*  11:    */ import net.minecraft.util.Vec3;
/*  12:    */ import net.minecraft.world.EnumDifficulty;
/*  13:    */ import net.minecraft.world.EnumSkyBlock;
/*  14:    */ import net.minecraft.world.World;
/*  15:    */ import thKaguyaMod.ShotData;
/*  16:    */ import thKaguyaMod.THShotLib;
/*  17:    */ 
/*  18:    */ public class EntityWriggle
/*  19:    */   extends EntityDanmakuMob
/*  20:    */ {
/*  21:    */   public EntityWriggle(World world)
/*  22:    */   {
/*  23: 27 */     super(world);
/*  24:    */     
/*  25: 29 */     setSize(1.0F, 1.8F);
/*  26:    */     
/*  27: 31 */     this.experienceValue = 140;
/*  28:    */     
/*  29: 33 */     setDanmakuPattern(1);
/*  30: 34 */     setMaxHP(30.0F);
/*  31: 35 */     setHealth(30.0F);
/*  32: 36 */     setDanmakuMobName("Wriggle");
/*  33: 37 */     setSpecies(139);
/*  34:    */     
/*  35: 39 */     setAttackDistance(14.0D);
/*  36: 40 */     setDetectionDistance(12.0D);
/*  37: 41 */     setFlyingHeight(3);
/*  38: 42 */     this.isFlyingMode = true;
/*  39:    */   }
/*  40:    */   
/*  41:    */   public int getUsingSpellCardNo()
/*  42:    */   {
/*  43: 53 */     switch (getDanmakuPattern())
/*  44:    */     {
/*  45:    */     case 101: 
/*  46: 56 */       return 35;
/*  47:    */     case 102: 
/*  48: 58 */       return 34;
/*  49:    */     }
/*  50: 60 */     return -1;
/*  51:    */   }
/*  52:    */   
/*  53:    */   protected void onDeathUpdate()
/*  54:    */   {
/*  55: 68 */     if (this.ticksExisted <= this.lastTime) {
/*  56: 70 */       return;
/*  57:    */     }
/*  58: 73 */     switch (getDanmakuPattern())
/*  59:    */     {
/*  60:    */     case 1: 
/*  61: 76 */       moveDanmakuAttack(101, 20, 36.0F, 40);
/*  62: 77 */       setAttackDistance(16.0D);
/*  63: 78 */       setDetectionDistance(30.0D);
/*  64: 79 */       setFlyingHeight(2);
/*  65: 80 */       break;
/*  66:    */     case 101: 
/*  67: 82 */       moveDanmakuAttack(102, 60, 42.0F, 80);
/*  68: 83 */       setAttackDistance(15.0D);
/*  69: 84 */       setDetectionDistance(30.0D);
/*  70: 85 */       setFlyingHeight(4);
/*  71: 86 */       break;
/*  72:    */     case 102: 
/*  73: 88 */       moveDanmakuAttack(127, 30, 0.0F, 60);
/*  74: 89 */       break;
/*  75:    */     default: 
/*  76: 91 */       if (this.deathTime % 9 == 0) {
/*  77: 93 */         THShotLib.explosionEffect(this.worldObj, this.posX, this.posY, this.posZ, 1.0F + this.deathTime * 0.1F);
/*  78:    */       }
/*  79: 95 */       super.onDeathUpdate();
/*  80:    */     }
/*  81:    */   }
/*  82:    */   
/*  83:    */   public void danmakuPattern(int level)
/*  84:    */   {
/*  85:104 */     switch (getDanmakuPattern())
/*  86:    */     {
/*  87:    */     case 1: 
/*  88:107 */       Vec3 look = getLookVec();
/*  89:108 */       Vec3 rotate = THShotLib.angle(this.rotationYaw, this.rotationPitch - 90.0F);
/*  90:111 */       if (this.attackCounter < 30)
/*  91:    */       {
/*  92:113 */         if ((this.attackCounter % 15 >= 1) && (this.attackCounter % 15 < 8))
/*  93:    */         {
/*  94:115 */           Vec3 angle = THShotLib.getRotationVectorFromAngle(this.rotationYaw, this.rotationPitch, this.attackCounter % 8 * 10.0F, 1.0D);
/*  95:116 */           THShotLib.playShotSound(this);
/*  96:117 */           THShotLib.createShot(this, pos(), angle, 1.0D, 0.0D, -0.2D, ShotData.shot(10, 3, 3, 15, 3450));
/*  97:118 */           if (level >= 3)
/*  98:    */           {
/*  99:120 */             Vec3 angle2 = THShotLib.getRotationVectorFromAngle(this.rotationYaw, this.rotationPitch, 5.0F + this.attackCounter % 8 * 10.0F, 1.0D);
/* 100:121 */             THShotLib.createShot(this, pos(), angle, 1.0D, 0.0D, -0.4D, ShotData.shot(10, 3, 3, 15, 3450));
/* 101:    */           }
/* 102:    */         }
/* 103:125 */         else if ((this.attackCounter % 15 >= 8) && (this.attackCounter % 15 < 15))
/* 104:    */         {
/* 105:127 */           Vec3 angle = THShotLib.getRotationVectorFromAngle(this.rotationYaw, this.rotationPitch, -(this.attackCounter % 8) * 10.0F, 1.0D);
/* 106:128 */           THShotLib.playShotSound(this);
/* 107:129 */           THShotLib.createShot(this, pos(), angle, 1.0D, 0.0D, -0.2D, ShotData.shot(10, 5, 3, 15, 3451));
/* 108:130 */           if (level >= 3)
/* 109:    */           {
/* 110:132 */             Vec3 angle2 = THShotLib.getRotationVectorFromAngle(this.rotationYaw, this.rotationPitch, -5.0F - this.attackCounter % 8 * 10.0F, 1.0D);
/* 111:133 */             THShotLib.createShot(this, pos(), angle, 1.0D, 0.0D, -0.4D, ShotData.shot(10, 5, 3, 15, 3451));
/* 112:    */           }
/* 113:    */         }
/* 114:    */       }
/* 115:137 */       else if (this.attackCounter == 24)
/* 116:    */       {
/* 117:139 */         moveRight(0.3D, 20);
/* 118:    */       }
/* 119:141 */       else if ((this.attackCounter >= 62) && (this.attackCounter < 72))
/* 120:    */       {
/* 121:143 */         THShotLib.playShotSound(this);
/* 122:144 */         int i = this.attackCounter - 62;
/* 123:145 */         double firstSpeed = 0.3D + 0.1D * level;
/* 124:146 */         double limit = 0.3D;
/* 125:147 */         double acceleration = -(this.attackCounter - 61) * 0.005D;
/* 126:148 */         THShotLib.createWideShot(this, pos(), look, firstSpeed, limit, acceleration, ShotData.shot(0, 3, 10), 5, 90.0F);
/* 127:    */       }
/* 128:150 */       else if (this.attackCounter == 67)
/* 129:    */       {
/* 130:152 */         moveLeft(0.3D, 20);
/* 131:    */       }
/* 132:154 */       if (this.attackCounter >= 147) {
/* 133:157 */         this.attackCounter = 0;
/* 134:    */       }
/* 135:    */       break;
/* 136:    */     case 101: 
/* 137:161 */       if ((this.attackCounter % 15 == 1) && (this.attackCounter < 120)) {
/* 138:163 */         if (level == 2) {
/* 139:165 */           THShotLib.createSphereShot(this, pos(), look(), 0.2D, ShotData.shot(2, 1), 16, this.ticksExisted * 13.0F);
/* 140:167 */         } else if (level == 3) {
/* 141:169 */           THShotLib.createSphereShot(this, pos(), look(), 0.3D, ShotData.shot(2, 1), 24, this.ticksExisted * 13.0F);
/* 142:171 */         } else if (level == 4) {
/* 143:173 */           THShotLib.createSphereShot(this, pos(), look(), 0.4D, ShotData.shot(2, 1), 32, this.ticksExisted * 13.0F);
/* 144:    */         }
/* 145:    */       }
/* 146:177 */       if (this.attackCounter == 1) {
/* 147:179 */         useSpellCard(getUsingSpellCardNo());
/* 148:181 */       } else if (this.attackCounter >= 180) {
/* 149:183 */         this.attackCounter = 0;
/* 150:    */       }
/* 151:    */       break;
/* 152:    */     case 102: 
/* 153:187 */       if (this.attackCounter == 1) {
/* 154:189 */         useSpellCard(getUsingSpellCardNo());
/* 155:191 */       } else if (this.attackCounter >= 260) {
/* 156:193 */         this.attackCounter = 0;
/* 157:    */       }
/* 158:    */       break;
/* 159:    */     }
/* 160:    */   }
/* 161:    */   
/* 162:    */   protected void dropFewItems(boolean hasBeenAttackedByPlayer, int lootingLevel)
/* 163:    */   {
/* 164:205 */     super.dropFewItems(hasBeenAttackedByPlayer, lootingLevel);
/* 165:207 */     if (hasBeenAttackedByPlayer) {
/* 166:209 */       if (isSpellCardAttack())
/* 167:    */       {
/* 168:212 */         dropShotItem(15, 8 + this.rand.nextInt(2) + lootingLevel, 5, 32, 3, 0, 0, 2);
/* 169:    */         
/* 170:214 */         dropPowerUpItem(8 + this.rand.nextInt(5) + this.rand.nextInt(1 + lootingLevel));
/* 171:    */         
/* 172:216 */         dropPointItem(8 + this.rand.nextInt(5) + this.rand.nextInt(1 + lootingLevel));
/* 173:    */       }
/* 174:    */     }
/* 175:    */   }
/* 176:    */   
/* 177:    */   public boolean attackEntityFrom(DamageSource damageSource, float damage)
/* 178:    */   {
/* 179:226 */     if ((super.attackEntityFrom(damageSource, applyPotionDamageCalculations(damageSource, damage))) && ((damageSource.getEntity() instanceof EntityLivingBase)))
/* 180:    */     {
/* 181:228 */       EntityLivingBase entity = (EntityLivingBase)damageSource.getEntity();
/* 182:    */       
/* 183:    */ 
/* 184:231 */       this.motionX *= 0.01D;
/* 185:232 */       this.motionY *= 0.01D;
/* 186:233 */       this.motionZ *= 0.01D;
/* 187:234 */       if ((this.riddenByEntity != entity) && (this.ridingEntity != entity))
/* 188:    */       {
/* 189:236 */         if ((entity instanceof EntityPlayer)) {
/* 190:238 */           this.entityToAttack = entity;
/* 191:    */         }
/* 192:241 */         return true;
/* 193:    */       }
/* 194:245 */       return true;
/* 195:    */     }
/* 196:250 */     return false;
/* 197:    */   }
/* 198:    */   
/* 199:    */   public int getMaxSpawnedInChunk()
/* 200:    */   {
/* 201:257 */     return 1;
/* 202:    */   }
/* 203:    */   
/* 204:    */   protected boolean isValidLightLevel()
/* 205:    */   {
/* 206:265 */     int i = MathHelper.floor_double(this.posX);
/* 207:266 */     int j = MathHelper.floor_double(this.boundingBox.minY);
/* 208:267 */     int k = MathHelper.floor_double(this.posZ);
/* 209:269 */     if (this.worldObj.getSavedLightValue(EnumSkyBlock.Sky, i, j, k) > this.rand.nextInt(32)) {
/* 210:271 */       return false;
/* 211:    */     }
/* 212:275 */     int l = this.worldObj.getBlockLightValue(i, j, k);
/* 213:277 */     if (this.worldObj.isThundering())
/* 214:    */     {
/* 215:279 */       int i1 = this.worldObj.skylightSubtracted;
/* 216:280 */       this.worldObj.skylightSubtracted = 10;
/* 217:281 */       l = this.worldObj.getBlockLightValue(i, j, k);
/* 218:282 */       this.worldObj.skylightSubtracted = i1;
/* 219:    */     }
/* 220:285 */     return l <= this.rand.nextInt(8);
/* 221:    */   }
/* 222:    */   
/* 223:    */   public boolean getCanSpawnHere()
/* 224:    */   {
/* 225:297 */     if ((this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL) || 
/* 226:298 */       (!isValidLightLevel())) {
/* 227:300 */       return false;
/* 228:    */     }
/* 229:303 */     int yPosition = MathHelper.floor_double(this.posY + 0.1D);
/* 230:304 */     int xPosition = MathHelper.floor_double(this.posX);
/* 231:305 */     int zPosition = MathHelper.floor_double(this.posZ);
/* 232:    */     Block pointBlock2;
/* 233:309 */     Block pointBlock = pointBlock2 = this.worldObj.getBlock(xPosition, yPosition, zPosition);
/* 234:311 */     for (int i = -2; i <= 2; i++) {
/* 235:313 */       for (int j = -2; j <= 2; j++)
/* 236:    */       {
/* 237:315 */         pointBlock2 = this.worldObj.getBlock(xPosition + i, yPosition - 1, zPosition + j);
/* 238:316 */         if (pointBlock2 == Blocks.water) {
/* 239:318 */           return true;
/* 240:    */         }
/* 241:    */       }
/* 242:    */     }
/* 243:322 */     return false;
/* 244:    */   }
/* 245:    */   
/* 246:    */   public float getBrightness(float par1)
/* 247:    */   {
/* 248:331 */     return 1.0F;
/* 249:    */   }
/* 250:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.living.EntityWriggle
 * JD-Core Version:    0.7.0.1
 */