/*   1:    */ package thKaguyaMod.entity.living;
/*   2:    */ 
/*   3:    */ import java.util.ArrayList;
/*   4:    */ import java.util.Calendar;
/*   5:    */ import java.util.Map;
/*   6:    */ import java.util.Random;
/*   7:    */ import net.minecraft.block.Block;
/*   8:    */ import net.minecraft.entity.DataWatcher;
/*   9:    */ import net.minecraft.entity.EntityList;
/*  10:    */ import net.minecraft.entity.EntityLivingBase;
/*  11:    */ import net.minecraft.entity.IEntityLivingData;
/*  12:    */ import net.minecraft.init.Blocks;
/*  13:    */ import net.minecraft.item.Item;
/*  14:    */ import net.minecraft.util.AxisAlignedBB;
/*  15:    */ import net.minecraft.util.MathHelper;
/*  16:    */ import net.minecraft.util.StatCollector;
/*  17:    */ import net.minecraft.util.Vec3;
/*  18:    */ import net.minecraft.world.EnumSkyBlock;
/*  19:    */ import net.minecraft.world.World;
/*  20:    */ import net.minecraft.world.WorldProvider;
/*  21:    */ import thKaguyaMod.LaserData;
/*  22:    */ import thKaguyaMod.ShotData;
/*  23:    */ import thKaguyaMod.THShotLib;
/*  24:    */ import thKaguyaMod.init.THKaguyaConfig;
/*  25:    */ import thKaguyaMod.init.THKaguyaItems;
/*  26:    */ import thKaguyaMod.registry.DanmakuPatternRegistry;
/*  27:    */ 
/*  28:    */ public class EntityTHFairy
/*  29:    */   extends EntityDanmakuMob
/*  30:    */ {
/*  31:    */   public EntityTHFairy(World world)
/*  32:    */   {
/*  33: 30 */     super(world);
/*  34:    */     
/*  35: 32 */     setSize(0.6F, 1.5F);
/*  36: 33 */     this.lastTime = 0;
/*  37:    */     
/*  38: 35 */     this.experienceValue = 5;
/*  39:    */     
/*  40: 37 */     setForm((byte)this.rand.nextInt(3));
/*  41: 38 */     setPattern(this.rand.nextInt(DanmakuPatternRegistry.pattern.size()));
/*  42:    */     
/*  43: 40 */     this.lostTarget = 0;
/*  44: 41 */     setSpeed(0.3D);
/*  45: 42 */     setSpecies(64);
/*  46: 43 */     this.isFlyingMode = true;
/*  47:    */     
/*  48: 45 */     setAttackDistance(8.0D);
/*  49: 46 */     setDetectionDistance(8.0D);
/*  50: 47 */     setFlyingHeight(2);
/*  51:    */   }
/*  52:    */   
/*  53:    */   public IEntityLivingData onSpawnWithEgg(IEntityLivingData entityLivingData)
/*  54:    */   {
/*  55: 54 */     Object object = super.onSpawnWithEgg(entityLivingData);
/*  56: 56 */     if ((this.worldObj.rand.nextInt(100) < 2) && (getForm() != 10)) {}
/*  57: 94 */     return (IEntityLivingData)object;
/*  58:    */   }
/*  59:    */   
/*  60:    */   protected void setPattern(int patternId)
/*  61:    */   {
/*  62:100 */     String patternKey = (String)DanmakuPatternRegistry.pattern.get(patternId);
/*  63:101 */     int shot = ((Integer)DanmakuPatternRegistry.form.get(patternKey)).intValue();
/*  64:    */     
/*  65:103 */     setDanmakuPattern(((Integer)DanmakuPatternRegistry.danmaku.get(patternKey)).intValue());
/*  66:104 */     this.danmakuSpan = ((Integer)DanmakuPatternRegistry.span.get(patternKey)).intValue();
/*  67:105 */     this.speedRate = ((Float)DanmakuPatternRegistry.speed.get(patternKey)).floatValue();
/*  68:106 */     this.shotForm = ((byte)((shot - shot % 8) / 8));
/*  69:107 */     this.shotColor = ((byte)(shot % 8));
/*  70:    */   }
/*  71:    */   
/*  72:    */   protected void onDeathUpdate()
/*  73:    */   {
/*  74:113 */     Calendar calendar = this.worldObj.getCurrentDate();
/*  75:115 */     if ((this.worldObj.provider.isHellWorld) || (
/*  76:116 */       (calendar.get(2) + 1 == 10) && (calendar.get(5) == 31) && (!(this instanceof EntitySunFlowerFairy))))
/*  77:    */     {
/*  78:119 */       if (getDetectionDistance() > 2.0D)
/*  79:    */       {
/*  80:121 */         setFlyingHeight(0);
/*  81:122 */         setDetectionDistance(getDetectionDistance() - 0.03D);
/*  82:123 */         this.entityToAttack = null;
/*  83:    */       }
/*  84:    */       else
/*  85:    */       {
/*  86:127 */         setHealth(2.0F);
/*  87:128 */         setDetectionDistance(8.0D);
/*  88:129 */         setAttackDistance(2.0D);
/*  89:130 */         setFlyingHeight(2);
/*  90:131 */         setSpeed(0.03D);
/*  91:    */         
/*  92:133 */         setDanmakuPattern(0);
/*  93:134 */         this.danmakuSpan = (50 - THKaguyaConfig.danmakuLevel * 10);
/*  94:135 */         this.speedRate = 0.2F;
/*  95:136 */         this.shotForm = ((byte)(THShotLib.LIGHT[0] / 8));
/*  96:137 */         this.shotColor = 0;
/*  97:138 */         setForm((byte)-1);
/*  98:    */       }
/*  99:    */     }
/* 100:    */     else {
/* 101:144 */       super.onDeathUpdate();
/* 102:    */     }
/* 103:    */   }
/* 104:    */   
/* 105:    */   public void onUpdate()
/* 106:    */   {
/* 107:163 */     if (getHealth() <= 0.0F)
/* 108:    */     {
/* 109:165 */       this.motionX = 0.0D;
/* 110:166 */       this.motionY = 0.0D;
/* 111:167 */       this.motionZ = 0.0D;
/* 112:    */     }
/* 113:170 */     if (this.ticksExisted <= this.lastTime) {
/* 114:172 */       return;
/* 115:    */     }
/* 116:176 */     super.onUpdate();
/* 117:177 */     if (this.attackCounter > this.danmakuSpan) {
/* 118:179 */       this.attackCounter = 0;
/* 119:    */     }
/* 120:    */   }
/* 121:    */   
/* 122:    */   public EntityLivingBase getShooter()
/* 123:    */   {
/* 124:190 */     return this;
/* 125:    */   }
/* 126:    */   
/* 127:    */   public void danmakuPattern(int level)
/* 128:    */   {
/* 129:198 */     if (this.attackCounter > 0) {
/* 130:200 */       return;
/* 131:    */     }
/* 132:204 */     Vec3 look = THShotLib.angle(getShooter().rotationYaw, getShooter().rotationPitch);
/* 133:    */     
/* 134:206 */     look = THShotLib.angle_LimitRandom(look, THKaguyaConfig.danmakuAccuracy);
/* 135:    */     
/* 136:208 */     int d = getDanmakuPattern();
/* 137:    */     
/* 138:210 */     boolean isShot = this.shotForm < 32;
/* 139:211 */     boolean isLaser = this.shotForm == 64;
/* 140:212 */     int shot = this.shotForm * 8 + this.shotColor;
/* 141:213 */     ShotData shotData = ShotData.shot(this.shotForm, this.shotColor, 0, 80, this.special);
/* 142:214 */     LaserData laserData = LaserData.laser(0, this.shotColor, 0.1F, 2.0F, 3.0F, 0, 80, this.special);
/* 143:    */     
/* 144:    */ 
/* 145:217 */     THShotLib.playShotSound(this);
/* 146:220 */     if ((d >= 0) && (d <= 999))
/* 147:    */     {
/* 148:222 */       int way = d % 100 + 1;
/* 149:223 */       int num = d / 100 + 1;
/* 150:224 */       double speed = this.speedRate;
/* 151:225 */       double slowDownRate = speed / num;
/* 152:226 */       if (isShot) {
/* 153:228 */         for (int i = 0; i < num; i++)
/* 154:    */         {
/* 155:230 */           THShotLib.createSphereShot(getShooter(), pos(), look, speed, shotData, way, 0.0F);
/* 156:231 */           speed -= slowDownRate;
/* 157:    */         }
/* 158:234 */       } else if (isLaser) {
/* 159:236 */         for (int i = 0; i < num; i++)
/* 160:    */         {
/* 161:238 */           THShotLib.createSphereLaserA(getShooter(), getShooter(), pos(), look, speed, speed, 0.0D, gravity_Zero(), laserData, way, 0.0D, 0.0F);
/* 162:239 */           speed -= slowDownRate;
/* 163:    */         }
/* 164:    */       }
/* 165:    */     }
/* 166:244 */     else if ((d >= 1000) && (d <= 1999))
/* 167:    */     {
/* 168:246 */       int num = d % 100 + 1;
/* 169:247 */       float span = (d - 1000) / 50.0F + 1.0F;
/* 170:248 */       if (isShot) {
/* 171:250 */         THShotLib.createWideShot(getShooter(), pos(), look, this.speedRate, this.speedRate, 0.0D, shotData, num, num * span);
/* 172:252 */       } else if (isLaser) {
/* 173:254 */         THShotLib.createWideLaserA(getShooter(), pos(), look, this.speedRate, this.speedRate, 0.0D, laserData, num, num * span);
/* 174:    */       }
/* 175:    */     }
/* 176:258 */     else if ((d >= 2000) && (d <= 2999))
/* 177:    */     {
/* 178:260 */       int way = d % 100 + 1;
/* 179:261 */       int num = (d - 2000) / 100 + 1;
/* 180:262 */       double speed = this.speedRate;
/* 181:263 */       double slowDownRate = speed / num;
/* 182:264 */       if (isShot) {
/* 183:266 */         for (int i = 0; i < num; i++)
/* 184:    */         {
/* 185:268 */           THShotLib.createCircleShot(getShooter(), pos(), look, speed, shotData, way);
/* 186:269 */           speed -= slowDownRate;
/* 187:    */         }
/* 188:272 */       } else if (isLaser) {
/* 189:274 */         for (int i = 0; i < num; i++)
/* 190:    */         {
/* 191:276 */           THShotLib.createCircleLaserA(getShooter(), pos(), look, speed, laserData, way);
/* 192:277 */           speed -= slowDownRate;
/* 193:    */         }
/* 194:    */       }
/* 195:    */     }
/* 196:282 */     else if ((d >= 3000) && (d <= 3999))
/* 197:    */     {
/* 198:284 */       float shotSpan = d % 100 + 1.0F;
/* 199:285 */       int num = (d - 3000) / 100 + 1;
/* 200:286 */       if (isShot) {
/* 201:288 */         THShotLib.createRandomRingShot(getShooter(), pos(), look, this.speedRate, shotData, num, shotSpan);
/* 202:290 */       } else if (!isLaser) {}
/* 203:    */     }
/* 204:295 */     else if ((d >= 4000) && (d <= 4999))
/* 205:    */     {
/* 206:297 */       float shotSpan = ((d - 4000) % 100 + 1.0F) * 1.8F;
/* 207:298 */       int way = (d - 4000) / 100 * (d % 100 / 10) + 3;
/* 208:299 */       THShotLib.createRingShot(getShooter(), pos(), look, this.speedRate, shotData, way, 0.0D, shotSpan, this.rand.nextFloat() * 360.0F);
/* 209:    */     }
/* 210:301 */     else if ((d >= 5000) && (d <= 5999))
/* 211:    */     {
/* 212:303 */       int num = d % 100 + 1;
/* 213:304 */       THShotLib.createRandomRingShot(getShooter(), pos(), look, this.speedRate, shotData, num, 360.0F);
/* 214:    */     }
/* 215:    */   }
/* 216:    */   
/* 217:    */   protected boolean canFairyCall()
/* 218:    */   {
/* 219:312 */     return true;
/* 220:    */   }
/* 221:    */   
/* 222:    */   protected double getFairyCallDistance()
/* 223:    */   {
/* 224:319 */     return 30.0D;
/* 225:    */   }
/* 226:    */   
/* 227:    */   protected Item getDropItem()
/* 228:    */   {
/* 229:325 */     if (getForm() < 0) {
/* 230:327 */       return null;
/* 231:    */     }
/* 232:331 */     return THKaguyaItems.power_item;
/* 233:    */   }
/* 234:    */   
/* 235:    */   protected void dropFewItems(boolean hasBeenAttackedByPlayer, int lootingLevel)
/* 236:    */   {
/* 237:339 */     super.dropFewItems(hasBeenAttackedByPlayer, lootingLevel);
/* 238:341 */     if (getForm() < 0) {
/* 239:343 */       return;
/* 240:    */     }
/* 241:346 */     if (hasBeenAttackedByPlayer) {
/* 242:348 */       if (getMaxHealth() == 10.0F)
/* 243:    */       {
/* 244:350 */         dropShotItem(0, this.rand.nextInt(12) + lootingLevel * 2, 7, 8, 0, 0, 0, 2);
/* 245:    */         
/* 246:352 */         dropPowerUpItem(this.rand.nextInt(20) + this.rand.nextInt(3 + lootingLevel * 2));
/* 247:    */         
/* 248:354 */         dropPointItem(this.rand.nextInt(20) + this.rand.nextInt(3 + lootingLevel * 2));
/* 249:    */       }
/* 250:    */       else
/* 251:    */       {
/* 252:358 */         dropShotItem(0, this.rand.nextInt(2) + lootingLevel, 3, 0, 0, 0, 0, 2);
/* 253:    */         
/* 254:360 */         dropPowerUpItem(this.rand.nextInt(5) + this.rand.nextInt(1 + lootingLevel));
/* 255:    */         
/* 256:362 */         dropPointItem(this.rand.nextInt(5) + this.rand.nextInt(1 + lootingLevel));
/* 257:    */       }
/* 258:    */     }
/* 259:    */   }
/* 260:    */   
/* 261:    */   public int getMaxSpawnedInChunk()
/* 262:    */   {
/* 263:371 */     return 10;
/* 264:    */   }
/* 265:    */   
/* 266:    */   public boolean getCanSpawnHere()
/* 267:    */   {
/* 268:379 */     if (this.rand.nextInt(100) < THKaguyaConfig.fairySpawnRate) {
/* 269:381 */       return false;
/* 270:    */     }
/* 271:386 */     int yPosition = MathHelper.floor_double(this.boundingBox.minY);
/* 272:387 */     int xPosition = MathHelper.floor_double(this.posX);
/* 273:388 */     int zPosition = MathHelper.floor_double(this.posZ);
/* 274:390 */     if (yPosition < 64) {
/* 275:392 */       if (yPosition < this.rand.nextInt(64)) {
/* 276:394 */         return false;
/* 277:    */       }
/* 278:    */     }
/* 279:398 */     if (this.worldObj.getSavedLightValue(EnumSkyBlock.Sky, xPosition, yPosition, zPosition) - this.worldObj.skylightSubtracted <= 7) {
/* 280:400 */       return false;
/* 281:    */     }
/* 282:404 */     Block pointBlock = this.worldObj.getBlock(xPosition, yPosition - 1, zPosition);
/* 283:407 */     if ((pointBlock == Blocks.grass) || (pointBlock == Blocks.dirt) || (pointBlock == Blocks.sand)) {
/* 284:409 */       return true;
/* 285:    */     }
/* 286:411 */     return false;
/* 287:    */   }
/* 288:    */   
/* 289:    */   public byte getFairyAttackMotion()
/* 290:    */   {
/* 291:418 */     return this.dataWatcher.getWatchableObjectByte(17);
/* 292:    */   }
/* 293:    */   
/* 294:    */   public void setFairyAttackMotion(byte motion)
/* 295:    */   {
/* 296:424 */     this.dataWatcher.updateObject(17, Byte.valueOf(motion));
/* 297:    */   }
/* 298:    */   
/* 299:    */   public String getCommandSenderName()
/* 300:    */   {
/* 301:433 */     String s = EntityList.getEntityString(this);
/* 302:435 */     if (s == null) {
/* 303:437 */       s = "generic";
/* 304:441 */     } else if (getForm() >= 32) {
/* 305:443 */       s = "THGhost";
/* 306:    */     }
/* 307:447 */     return StatCollector.translateToLocal("entity." + s + ".name");
/* 308:    */   }
/* 309:    */   
/* 310:    */   public void initCreature() {}
/* 311:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.living.EntityTHFairy
 * JD-Core Version:    0.7.0.1
 */