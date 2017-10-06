/*   1:    */ package thKaguyaMod.entity.living;
/*   2:    */ 
/*   3:    */ import java.util.Random;
/*   4:    */ import net.minecraft.block.Block;
/*   5:    */ import net.minecraft.init.Blocks;
/*   6:    */ import net.minecraft.util.DamageSource;
/*   7:    */ import net.minecraft.util.MathHelper;
/*   8:    */ import net.minecraft.util.Vec3;
/*   9:    */ import net.minecraft.world.World;
/*  10:    */ import thKaguyaMod.LaserData;
/*  11:    */ import thKaguyaMod.ShotData;
/*  12:    */ import thKaguyaMod.THShotLib;
/*  13:    */ import thKaguyaMod.init.THKaguyaConfig;
/*  14:    */ import thKaguyaMod.init.THKaguyaItems;
/*  15:    */ 
/*  16:    */ public class EntityCirno
/*  17:    */   extends EntityDanmakuMob
/*  18:    */ {
/*  19:    */   private int random;
/*  20:    */   
/*  21:    */   public EntityCirno(World world)
/*  22:    */   {
/*  23: 26 */     super(world);
/*  24:    */     
/*  25: 28 */     setSize(1.0F, 1.8F);
/*  26:    */     
/*  27: 30 */     this.experienceValue = 250;
/*  28:    */     
/*  29: 32 */     setDanmakuMobName("Cirno");
/*  30: 33 */     setSpecies(64, 65);
/*  31:    */     
/*  32:    */ 
/*  33: 36 */     setDanmakuPattern(1);
/*  34: 37 */     setMaxHP(38.0F);
/*  35: 38 */     setHealth(38.0F);
/*  36: 39 */     setSpeed(0.5D);
/*  37: 40 */     setAttackDistance(14.0D);
/*  38: 41 */     setDetectionDistance(30.0D);
/*  39: 42 */     setFlyingHeight(2);
/*  40: 43 */     this.isFlyingMode = true;
/*  41:    */     
/*  42: 45 */     this.isSpellCardMode = false;
/*  43: 46 */     this.attackInterval = 0;
/*  44: 47 */     this.random = 0;
/*  45:    */   }
/*  46:    */   
/*  47:    */   public int getUsingSpellCardNo()
/*  48:    */   {
/*  49: 56 */     switch (getDanmakuPattern())
/*  50:    */     {
/*  51:    */     case 101: 
/*  52: 59 */       return 19;
/*  53:    */     case 102: 
/*  54: 61 */       return 9;
/*  55:    */     }
/*  56: 63 */     return -1;
/*  57:    */   }
/*  58:    */   
/*  59:    */   protected void onDeathUpdate()
/*  60:    */   {
/*  61: 71 */     if (this.ticksExisted <= this.lastTime) {
/*  62: 73 */       return;
/*  63:    */     }
/*  64: 76 */     switch (getDanmakuPattern())
/*  65:    */     {
/*  66:    */     case 1: 
/*  67: 79 */       moveDanmakuAttack(101, 40, 46.0F, 60);
/*  68: 80 */       break;
/*  69:    */     case 101: 
/*  70: 82 */       moveDanmakuAttack(2, 60, 32.0F, 20);
/*  71: 83 */       break;
/*  72:    */     case 2: 
/*  73: 85 */       moveDanmakuAttack(102, 40, 58.0F, 60);
/*  74: 86 */       break;
/*  75:    */     case 102: 
/*  76: 88 */       moveDanmakuAttack(127, 30, 0.0F, 60);
/*  77: 89 */       break;
/*  78:    */     default: 
/*  79: 91 */       if (this.deathTime % 9 == 0) {
/*  80: 93 */         THShotLib.explosionEffect(this.worldObj, this.posX, this.posY, this.posZ, 1.0F + this.deathTime * 0.1F);
/*  81:    */       }
/*  82: 95 */       super.onDeathUpdate();
/*  83:    */     }
/*  84:    */   }
/*  85:    */   
/*  86:    */   public void onUpdate()
/*  87:    */   {
/*  88:105 */     super.onUpdate();
/*  89:    */   }
/*  90:    */   
/*  91:    */   public void danmakuPattern(int level)
/*  92:    */   {
/*  93:116 */     Vec3 angle = THShotLib.angle(this.rotationYaw, this.rotationPitch);
/*  94:118 */     switch (getDanmakuPattern())
/*  95:    */     {
/*  96:    */     case 1: 
/*  97:121 */       danmaku01(angle, level);
/*  98:122 */       break;
/*  99:    */     case 101: 
/* 100:124 */       spellcard01(angle, level);
/* 101:125 */       break;
/* 102:    */     case 2: 
/* 103:127 */       danmaku02(angle, level);
/* 104:128 */       break;
/* 105:    */     case 102: 
/* 106:130 */       spellcard02(angle, level);
/* 107:131 */       break;
/* 108:    */     }
/* 109:    */   }
/* 110:    */   
/* 111:    */   private void danmaku01(Vec3 angle, int level)
/* 112:    */   {
/* 113:140 */     if (this.attackCounter == 40)
/* 114:    */     {
/* 115:142 */       angle = getLookVec();
/* 116:143 */       for (int i = 0; i < 7; i++) {
/* 117:145 */         THShotLib.createRingShot(this, this, pos(), angle, 0.0F, 9999, 0.6D + level * 0.15D, 0.6D + level * 0.15D, 0.1D, gravity_Zero(), ShotData.shot(11, 5, 0.2F, 4.0F, i, 120, 0), i + 2, 0.0D, i * level, this.rand.nextFloat() * 360.0F);
/* 118:    */       }
/* 119:147 */       this.worldObj.playSoundAtEntity(this, "random.bow", 2.0F, 0.8F);
/* 120:    */     }
/* 121:149 */     if (this.attackCounter >= 50) {
/* 122:151 */       this.attackCounter = 0;
/* 123:    */     }
/* 124:    */   }
/* 125:    */   
/* 126:    */   private void spellcard01(Vec3 angle, int level)
/* 127:    */   {
/* 128:158 */     if (this.attackCounter == 1)
/* 129:    */     {
/* 130:160 */       useSpellCard(getUsingSpellCardNo());
/* 131:    */     }
/* 132:162 */     else if ((this.attackCounter > 30) && (this.attackCounter < 170) && (this.attackCounter % 17 == 0) && (level >= 2))
/* 133:    */     {
/* 134:165 */       float size = THShotLib.SIZE[(THShotLib.SMALL[0] / 8)];
/* 135:166 */       if (level >= 3) {
/* 136:168 */         size = THShotLib.SIZE[(THShotLib.MEDIUM[0] / 8)];
/* 137:    */       }
/* 138:170 */       THShotLib.createWideShot(this, this, pos(), angle, 0.3D + level * 0.1D, 0.5D, 1.0D, gravity_Zero(), ShotData.shot(0, 3, size, 4.0F, 3, 80), 3, 70.0F, 0.2D);
/* 139:171 */       this.worldObj.playSoundAtEntity(this, "random.bow", 2.0F, 0.8F);
/* 140:    */     }
/* 141:174 */     else if (this.attackCounter >= 240)
/* 142:    */     {
/* 143:176 */       this.attackCounter = 0;
/* 144:    */     }
/* 145:    */   }
/* 146:    */   
/* 147:    */   private void danmaku02(Vec3 angle, int level)
/* 148:    */   {
/* 149:183 */     if ((this.attackCounter < 50) && (this.attackCounter % 6 == 0))
/* 150:    */     {
/* 151:185 */       THShotLib.createCircleShot(this, pos(), angle, 0.2D, ShotData.shot(1, 7), 5 + level * 2);
/* 152:186 */       THShotLib.createCircleShot(this, pos(), angle, 0.3D + level * 0.1D, ShotData.shot(4, 1), 5 + level * 2);
/* 153:187 */       this.worldObj.playSoundAtEntity(this, "random.bow", 2.0F, 0.8F);
/* 154:    */     }
/* 155:189 */     else if ((this.attackCounter == 55) || (this.attackCounter == 65) || (this.attackCounter == 75))
/* 156:    */     {
/* 157:192 */       THShotLib.createWideLaserA(this, pos(), getLookVec(), 0.3D + level * 0.2D, LaserData.laser(7, 0.1F, 4.0F, 4.0F), 3, 30.0F);
/* 158:    */     }
/* 159:194 */     if (this.attackCounter > 128) {
/* 160:196 */       this.attackCounter = 0;
/* 161:    */     }
/* 162:    */   }
/* 163:    */   
/* 164:    */   private void spellcard02(Vec3 angle, int level)
/* 165:    */   {
/* 166:203 */     if (this.attackCounter == 1)
/* 167:    */     {
/* 168:205 */       this.random = this.rand.nextInt(2);
/* 169:206 */       useSpellCard(getUsingSpellCardNo());
/* 170:    */     }
/* 171:208 */     else if ((this.attackCounter >= 70) && (this.attackCounter <= 90) && (this.attackCounter % 3 == 0))
/* 172:    */     {
/* 173:210 */       int way = 4 + this.random;
/* 174:211 */       for (int i = 0; i < 3 + level; i++) {
/* 175:213 */         THShotLib.createWideShot(this, this, pos(), angle, 0.3D + level * 0.1D, 0.5D, 1.0D, gravity_Zero(), ShotData.shot(4, 1, 0.3F, 4.0F, i, 80), way, 70.0F, 0.2D);
/* 176:    */       }
/* 177:215 */       this.worldObj.playSoundAtEntity(this, "random.bow", 2.0F, 0.8F);
/* 178:    */     }
/* 179:217 */     else if (this.attackCounter >= 170)
/* 180:    */     {
/* 181:219 */       this.attackCounter = 0;
/* 182:    */     }
/* 183:    */   }
/* 184:    */   
/* 185:    */   protected boolean canFairyCall()
/* 186:    */   {
/* 187:227 */     return false;
/* 188:    */   }
/* 189:    */   
/* 190:    */   protected float applyPotionDamageCalculations(DamageSource damageSource, float damage)
/* 191:    */   {
/* 192:270 */     damage = super.applyPotionDamageCalculations(damageSource, damage);
/* 193:272 */     if (isEntityInvulnerable()) {
/* 194:274 */       damage = (float)(damage * 0.05D);
/* 195:    */     }
/* 196:277 */     return damage;
/* 197:    */   }
/* 198:    */   
/* 199:    */   protected void updateAITasks()
/* 200:    */   {
/* 201:283 */     super.updateAITasks();
/* 202:    */   }
/* 203:    */   
/* 204:    */   protected void dropFewItems(boolean hasBeenAttackedByPlayer, int lootingLevel)
/* 205:    */   {
/* 206:347 */     super.dropFewItems(hasBeenAttackedByPlayer, lootingLevel);
/* 207:349 */     if ((hasBeenAttackedByPlayer) && (isSpellCardAttack()))
/* 208:    */     {
/* 209:351 */       int j = 40;
/* 210:356 */       for (int k = 0; k < j; k += 2)
/* 211:    */       {
/* 212:358 */         float yaw = 360.0F / j * k;
/* 213:359 */         float pitch = MathHelper.sin(yaw / 180.0F * 3.141593F * 4.0F) * 20.0F - 60.0F;
/* 214:360 */         Vec3 vec3 = THShotLib.getVecFromAngle(yaw, pitch, 1.0D);
/* 215:361 */         dropPointItem(pos(), vec3);
/* 216:362 */         yaw = 360.0F / j * (k + 1);
/* 217:363 */         pitch = MathHelper.cos(yaw / 180.0F * 3.141593F * 4.0F) * 20.0F - 60.0F;
/* 218:364 */         vec3 = THShotLib.getVecFromAngle(yaw, pitch, 1.0D);
/* 219:365 */         dropPowerUpItem(pos(), vec3);
/* 220:    */       }
/* 221:368 */       dropShotItem(17, 17 + this.rand.nextInt(2) + lootingLevel, 5, 32, 5, 0, 0, 2);
/* 222:    */     }
/* 223:370 */     if ((hasBeenAttackedByPlayer) && (getDanmakuPattern() == 102))
/* 224:    */     {
/* 225:372 */       dropItem(THKaguyaItems.icicle_sword, 1);
/* 226:373 */       dropExtendItem(pos(), angle(this.rotationYaw, -90.0F));
/* 227:    */     }
/* 228:    */   }
/* 229:    */   
/* 230:    */   public boolean doesEntityNotTriggerPressurePlate()
/* 231:    */   {
/* 232:384 */     return true;
/* 233:    */   }
/* 234:    */   
/* 235:    */   public int getMaxSpawnedInChunk()
/* 236:    */   {
/* 237:390 */     return 1;
/* 238:    */   }
/* 239:    */   
/* 240:    */   public boolean getCanSpawnHere()
/* 241:    */   {
/* 242:400 */     if (this.rand.nextInt(100) < THKaguyaConfig.fairySpawnRate) {
/* 243:402 */       return false;
/* 244:    */     }
/* 245:406 */     int yPosition = MathHelper.floor_double(this.posY + 0.1D);
/* 246:407 */     int xPosition = MathHelper.floor_double(this.posX);
/* 247:408 */     int zPosition = MathHelper.floor_double(this.posZ);
/* 248:    */     Block pointBlock2;
/* 249:412 */     Block pointBlock = pointBlock2 = this.worldObj.getBlock(xPosition, yPosition, zPosition);
/* 250:414 */     if (pointBlock != Blocks.snow_layer) {
/* 251:416 */       return false;
/* 252:    */     }
/* 253:418 */     for (int i = -2; i <= 2; i++) {
/* 254:420 */       for (int j = -2; j <= 2; j++)
/* 255:    */       {
/* 256:423 */         pointBlock2 = this.worldObj.getBlock(xPosition + i, yPosition - 1, zPosition + j);
/* 257:425 */         if (pointBlock2 == Blocks.ice) {
/* 258:427 */           return true;
/* 259:    */         }
/* 260:    */       }
/* 261:    */     }
/* 262:431 */     return false;
/* 263:    */   }
/* 264:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.living.EntityCirno
 * JD-Core Version:    0.7.0.1
 */