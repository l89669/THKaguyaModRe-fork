/*   1:    */ package thKaguyaMod.entity.shot;
/*   2:    */ 
/*   3:    */ import java.util.List;
/*   4:    */ import net.minecraft.entity.DataWatcher;
/*   5:    */ import net.minecraft.entity.Entity;
/*   6:    */ import net.minecraft.entity.EntityLivingBase;
/*   7:    */ import net.minecraft.entity.passive.EntityAnimal;
/*   8:    */ import net.minecraft.entity.passive.EntityVillager;
/*   9:    */ import net.minecraft.entity.player.EntityPlayer;
/*  10:    */ import net.minecraft.entity.player.PlayerCapabilities;
/*  11:    */ import net.minecraft.util.AxisAlignedBB;
/*  12:    */ import net.minecraft.util.DamageSource;
/*  13:    */ import net.minecraft.util.MathHelper;
/*  14:    */ import net.minecraft.util.MovingObjectPosition;
/*  15:    */ import net.minecraft.util.Vec3;
/*  16:    */ import net.minecraft.world.World;
/*  17:    */ import thKaguyaMod.LaserData;
/*  18:    */ import thKaguyaMod.ShotData;
/*  19:    */ import thKaguyaMod.THShotLib;
/*  20:    */ import thKaguyaMod.entity.EntityShotMaterial;
/*  21:    */ import thKaguyaMod.entity.item.EntitySakuyaWatch;
/*  22:    */ import thKaguyaMod.entity.living.EntityTHFairy;
/*  23:    */ 
/*  24:    */ public class EntityTHLaser
/*  25:    */   extends EntityTHShot
/*  26:    */ {
/*  27:    */   private EntityTHLaser mother;
/*  28:    */   
/*  29:    */   public EntityTHLaser(World world)
/*  30:    */   {
/*  31: 31 */     super(world);
/*  32: 32 */     this.ignoreFrustumCheck = true;
/*  33:    */   }
/*  34:    */   
/*  35:    */   public EntityTHLaser(World world, EntityLivingBase user, Entity source, Vec3 pos, Vec3 angle, float slope, Vec3 rotate, float rotationSpeed, int rotationEnd, double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity, int color, float width, float length, float damage, int delay, int end, int special)
/*  36:    */   {
/*  37: 41 */     super(world, user, source, pos, angle, slope, rotate, rotationSpeed, rotationEnd, firstSpeed, limitSpeed, acceleration, gravity, color, width, damage, delay, end, special);
/*  38:    */     
/*  39: 43 */     setLaserLength(0.0D);
/*  40: 44 */     setMaxLaserLength(length);
/*  41: 45 */     this.mother = null;
/*  42:    */   }
/*  43:    */   
/*  44:    */   public EntityTHLaser(World world, EntityLivingBase user, Entity source, Vec3 pos, Vec3 angle, float slope, Vec3 rotate, float rotationSpeed, int rotationEnd, double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity, LaserData laser)
/*  45:    */   {
/*  46: 54 */     this(world, user, source, pos, angle, slope, rotate, rotationSpeed, rotationEnd, firstSpeed, limitSpeed, acceleration, gravity, laser.form * 8 + laser.color, laser.width, laser.length, laser.damage, laser.delay, laser.end, laser.special);
/*  47:    */   }
/*  48:    */   
/*  49:    */   protected void entityInit()
/*  50:    */   {
/*  51: 64 */     super.entityInit();
/*  52: 65 */     this.dataWatcher.addObject(21, new Integer(0));
/*  53: 66 */     this.dataWatcher.addObject(22, new Integer(0));
/*  54: 67 */     this.dataWatcher.addObject(23, new Integer(0));
/*  55: 68 */     this.dataWatcher.addObject(24, new Integer(0));
/*  56: 69 */     this.dataWatcher.addObject(25, new Integer(0));
/*  57: 70 */     this.dataWatcher.addObject(26, new Integer(0));
/*  58:    */   }
/*  59:    */   
/*  60:    */   public void updateAngle()
/*  61:    */   {
/*  62: 77 */     float f = MathHelper.sqrt_double(this.angle.xCoord * this.angle.xCoord + this.angle.zCoord * this.angle.zCoord);
/*  63: 78 */     if (!this.worldObj.isRemote)
/*  64:    */     {
/*  65: 80 */       this.rotationYaw = ((float)(Math.atan2(this.angle.xCoord, this.angle.zCoord) * 180.0D / 3.141592741012573D));
/*  66: 81 */       if (this.rotationYaw >= 180.0F) {
/*  67: 83 */         this.rotationYaw = (-180.0F + (this.rotationYaw - 180.0F));
/*  68: 85 */       } else if (this.rotationYaw <= -180.0F) {
/*  69: 87 */         this.rotationYaw = (180.0F + (this.rotationYaw + 180.0F));
/*  70:    */       }
/*  71:    */     }
/*  72: 91 */     if (!this.worldObj.isRemote) {
/*  73: 93 */       this.rotationPitch = ((float)(Math.atan2(this.angle.yCoord, f) * 180.0D / 3.141592741012573D));
/*  74:    */     }
/*  75:    */   }
/*  76:    */   
/*  77:    */   public void shotFinishBonus()
/*  78:    */   {
/*  79:102 */     double zPos = 0.0D;
/*  80:103 */     while (zPos < getLaserLength())
/*  81:    */     {
/*  82:105 */       EntityShotMaterial shotMaterial = new EntityShotMaterial(this.worldObj, this.posX + this.angle.xCoord * zPos, this.posY + this.angle.yCoord * zPos, this.posZ + this.angle.zCoord * zPos);
/*  83:106 */       if (!this.worldObj.isRemote) {
/*  84:108 */         this.worldObj.spawnEntityInWorld(shotMaterial);
/*  85:    */       }
/*  86:110 */       zPos += 1.5D;
/*  87:    */     }
/*  88:112 */     if (!this.worldObj.isRemote) {
/*  89:114 */       setDead();
/*  90:    */     }
/*  91:    */   }
/*  92:    */   
/*  93:    */   public void onUpdate()
/*  94:    */   {
/*  95:122 */     super.onUpdate();
/*  96:124 */     if (!(this instanceof EntityTHSetLaser))
/*  97:    */     {
/*  98:126 */       double laserLength = getLaserLength() + getSpeed() * 2.0D;
/*  99:127 */       if (laserLength > getMaxLaserLength()) {
/* 100:129 */         setLaserLength(getMaxLaserLength());
/* 101:    */       } else {
/* 102:133 */         setLaserLength(laserLength);
/* 103:    */       }
/* 104:    */     }
/* 105:137 */     if (this.mother != null)
/* 106:    */     {
/* 107:139 */       this.motionY += 0.4D;
/* 108:    */       
/* 109:141 */       setMotherAndChild(1);
/* 110:142 */       setMotherPosX(this.mother.posX);
/* 111:143 */       setMotherPosY(this.mother.posY);
/* 112:144 */       setMotherPosZ(this.mother.posZ);
/* 113:    */     }
/* 114:    */     else
/* 115:    */     {
/* 116:148 */       setMotherAndChild(0);
/* 117:    */     }
/* 118:    */   }
/* 119:    */   
/* 120:    */   public void hitCheck()
/* 121:    */   {
/* 122:157 */     double width = getShotSize();
/* 123:158 */     double length = getLaserLength() - getShotSize();
/* 124:    */     
/* 125:160 */     Vec3 vec3d = Vec3.createVectorHelper(this.posX + this.angle.xCoord * width, this.posY + this.angle.yCoord * width, this.posZ + this.angle.zCoord * width);
/* 126:    */     
/* 127:162 */     Vec3 vec3d1 = Vec3.createVectorHelper(this.posX + this.motionX + this.angle.xCoord * length, this.posY + this.motionY + this.angle.yCoord * length, this.posZ + this.motionZ + this.angle.zCoord * length);
/* 128:    */     
/* 129:    */ 
/* 130:165 */     MovingObjectPosition movingObjectPosition = this.worldObj.func_147447_a(vec3d, vec3d1, false, true, true);
/* 131:166 */     vec3d = Vec3.createVectorHelper(this.posX + this.angle.xCoord * width, this.posY + this.angle.yCoord * width, this.posZ + this.angle.zCoord * width);
/* 132:167 */     vec3d1 = Vec3.createVectorHelper(this.posX + this.motionX + this.angle.xCoord * length, this.posY + this.motionY + this.angle.yCoord * length, this.posZ + this.motionZ + this.angle.zCoord * length);
/* 133:169 */     if (movingObjectPosition != null)
/* 134:    */     {
/* 135:172 */       vec3d1 = Vec3.createVectorHelper(movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
/* 136:    */       
/* 137:    */ 
/* 138:    */ 
/* 139:    */ 
/* 140:    */ 
/* 141:    */ 
/* 142:    */ 
/* 143:    */ 
/* 144:    */ 
/* 145:    */ 
/* 146:    */ 
/* 147:184 */       onImpact(movingObjectPosition);
/* 148:    */     }
/* 149:188 */     Entity entity = null;
/* 150:189 */     double d = 0.0D;
/* 151:    */     
/* 152:191 */     double xLength = this.motionX + Math.sin(this.rotationYaw / 180.0F * 3.141593F) * Math.cos(this.rotationPitch / 180.0F * 3.141593F) * getLaserLength();
/* 153:192 */     double yLength = this.motionY + Math.sin(this.rotationPitch / 180.0F * 3.141593F) * getLaserLength();
/* 154:193 */     double zLength = this.motionZ + Math.cos(this.rotationYaw / 180.0F * 3.141593F) * Math.cos(this.rotationPitch / 180.0F * 3.141593F) * getLaserLength();
/* 155:194 */     List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(xLength, yLength, zLength).expand(width, width, width));
/* 156:196 */     for (int j = 0; j < list.size(); j++)
/* 157:    */     {
/* 158:198 */       Entity entity1 = (Entity)list.get(j);
/* 159:200 */       if ((entity1.canBeCollidedWith()) && 
/* 160:201 */         (!entity1.isEntityEqual(this.user)) && 
/* 161:202 */         (!entity1.isEntityEqual(this.source)) && 
/* 162:203 */         (!hitCheckEx(entity1)) && (!(entity1 instanceof EntitySakuyaWatch)) && (!(entity1 instanceof EntityAnimal)) && (!(entity1 instanceof EntityVillager)) && ((entity1 instanceof EntityLivingBase)) && ((!(this.user instanceof EntityTHFairy)) || (!(entity1 instanceof EntityTHFairy))))
/* 163:    */       {
/* 164:213 */         AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(width, width, width);
/* 165:214 */         MovingObjectPosition movingObjectPosition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);
/* 166:216 */         if (movingObjectPosition1 != null)
/* 167:    */         {
/* 168:218 */           movingObjectPosition = new MovingObjectPosition(entity1);
/* 169:219 */           boolean creative = false;
/* 170:220 */           if ((movingObjectPosition != null) && (movingObjectPosition.entityHit != null) && ((movingObjectPosition.entityHit instanceof EntityPlayer)))
/* 171:    */           {
/* 172:222 */             EntityPlayer entityPlayer = (EntityPlayer)movingObjectPosition.entityHit;
/* 173:228 */             if ((entityPlayer.capabilities.disableDamage) || ((this.source instanceof EntityPlayer))) {
/* 174:231 */               movingObjectPosition = null;
/* 175:    */             }
/* 176:    */           }
/* 177:234 */           if (movingObjectPosition != null) {
/* 178:236 */             onImpact(movingObjectPosition);
/* 179:    */           }
/* 180:    */         }
/* 181:    */       }
/* 182:    */     }
/* 183:    */   }
/* 184:    */   
/* 185:    */   protected boolean onImpact(MovingObjectPosition movingObjectPosition)
/* 186:    */   {
/* 187:267 */     if (movingObjectPosition.entityHit != null)
/* 188:    */     {
/* 189:269 */       Entity hitEntity = movingObjectPosition.entityHit;
/* 190:272 */       if (hitEntity != null) {
/* 191:275 */         if (!(hitEntity instanceof EntityTHShot))
/* 192:    */         {
/* 193:278 */           entityHitSpecialProcess(hitEntity);
/* 194:280 */           if (((this.worldObj.isRemote) || 
/* 195:    */           
/* 196:282 */             (hitEntity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, this.user), getShotDamageWithDifficulty()))) || 
/* 197:    */             
/* 198:    */ 
/* 199:    */ 
/* 200:    */ 
/* 201:287 */             (hitEntity.hurtResistantTime == 0)) {
/* 202:289 */             this.shotDamage -= 1.0F;
/* 203:    */           }
/* 204:291 */           if (this.shotDamage <= 0.0F) {
/* 205:293 */             this.shotDamage = 1.0F;
/* 206:    */           }
/* 207:    */         }
/* 208:    */         else
/* 209:    */         {
/* 210:335 */           EntityTHShot entityTHShot = (EntityTHShot)hitEntity;
/* 211:337 */           if (this.user != entityTHShot.user)
/* 212:    */           {
/* 213:341 */             float shotDamageA = this.shotDamage;
/* 214:342 */             this.shotDamage -= entityTHShot.shotDamage;
/* 215:343 */             entityTHShot.shotDamage -= shotDamageA;
/* 216:344 */             if (this.shotDamage < 0.0F) {
/* 217:346 */               this.shotDamage = 0.0F;
/* 218:    */             }
/* 219:348 */             if (entityTHShot.shotDamage < 0.0F) {
/* 220:350 */               entityTHShot.shotDamage = 0.0F;
/* 221:    */             }
/* 222:    */           }
/* 223:    */         }
/* 224:    */       }
/* 225:    */     }
/* 226:    */     else
/* 227:    */     {
/* 228:358 */       blockHitSpecialProcess(movingObjectPosition);
/* 229:359 */       double hitDistance = getDistance(movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
/* 230:360 */       if (hitDistance > 0.3D)
/* 231:    */       {
/* 232:362 */         if (!this.worldObj.isRemote)
/* 233:    */         {
/* 234:364 */           setLaserLength(hitDistance);
/* 235:365 */           setMaxLaserLength(hitDistance);
/* 236:    */         }
/* 237:    */       }
/* 238:370 */       else if (!this.worldObj.isRemote) {
/* 239:372 */         setDead();
/* 240:    */       }
/* 241:    */     }
/* 242:383 */     if (this.shotDamage <= 0.0F) {
/* 243:385 */       if (!this.worldObj.isRemote) {
/* 244:387 */         setDead();
/* 245:    */       }
/* 246:    */     }
/* 247:391 */     return true;
/* 248:    */   }
/* 249:    */   
/* 250:    */   public void specialMotion()
/* 251:    */   {
/* 252:398 */     super.specialMotion();
/* 253:400 */     switch (this.shotSpecial)
/* 254:    */     {
/* 255:    */     case 285: 
/* 256:403 */       if (this.ticksExisted <= 10)
/* 257:    */       {
/* 258:405 */         Vec3 move = THShotLib.getVectorFromRotation(this.rotate, this.angle, 18.0F);
/* 259:406 */         if (getLaserLength() < getMaxLaserLength()) {
/* 260:408 */           setLaserLength(getLaserLength() + 1.0D);
/* 261:    */         } else {
/* 262:413 */           setLaserLength(getMaxLaserLength());
/* 263:    */         }
/* 264:415 */         this.angle = move;
/* 265:416 */         updateAngle();
/* 266:    */       }
/* 267:418 */       if (this.ticksExisted == 10)
/* 268:    */       {
/* 269:420 */         this.user.motionX -= this.angle.xCoord;
/* 270:421 */         this.user.motionZ -= this.angle.zCoord;
/* 271:422 */         this.shotLimitSpeed = 2.0D;
/* 272:423 */         this.shotAcceleration = 0.6D;
/* 273:    */       }
/* 274:425 */       if (this.ticksExisted > 10)
/* 275:    */       {
/* 276:427 */         ShotData shot = ShotData.shot(11, 0, 0.45F, 6.0F, 0, 40);
/* 277:428 */         THShotLib.createRingShot(this.user, this, THShotLib.pos_Entity(this), this.angle, 0.0F, 9999, 1.4D, 0.1D, -0.1D, THShotLib.gravity_Zero(), shot, 4, 0.3D, 240.0F, this.ticksExisted * 10.0F);
/* 278:    */       }
/* 279:429 */       break;
/* 280:    */     }
/* 281:    */   }
/* 282:    */   
/* 283:    */   public boolean attackEntityFrom(DamageSource damagesource, float i)
/* 284:    */   {
/* 285:442 */     return false;
/* 286:    */   }
/* 287:    */   
/* 288:    */   public void setLaserLength(double length)
/* 289:    */   {
/* 290:452 */     this.dataWatcher.updateObject(21, Integer.valueOf((int)(length * 10000.0D)));
/* 291:    */   }
/* 292:    */   
/* 293:    */   public double getLaserLength()
/* 294:    */   {
/* 295:461 */     return this.dataWatcher.getWatchableObjectInt(21) / 10000.0D;
/* 296:    */   }
/* 297:    */   
/* 298:    */   public void setMaxLaserLength(double maxLength)
/* 299:    */   {
/* 300:470 */     this.dataWatcher.updateObject(22, Integer.valueOf((int)(maxLength * 10000.0D)));
/* 301:    */   }
/* 302:    */   
/* 303:    */   public double getMaxLaserLength()
/* 304:    */   {
/* 305:479 */     return this.dataWatcher.getWatchableObjectInt(22) / 10000.0D;
/* 306:    */   }
/* 307:    */   
/* 308:    */   @Deprecated
/* 309:    */   public void setMotherPosX(double x)
/* 310:    */   {
/* 311:490 */     this.dataWatcher.updateObject(23, Integer.valueOf((int)(x * 10000.0D)));
/* 312:    */   }
/* 313:    */   
/* 314:    */   @Deprecated
/* 315:    */   public double getMotherPosX()
/* 316:    */   {
/* 317:501 */     return this.dataWatcher.getWatchableObjectInt(23) / 10000.0D;
/* 318:    */   }
/* 319:    */   
/* 320:    */   @Deprecated
/* 321:    */   public void setMotherPosY(double y)
/* 322:    */   {
/* 323:512 */     this.dataWatcher.updateObject(24, Integer.valueOf((int)(y * 10000.0D)));
/* 324:    */   }
/* 325:    */   
/* 326:    */   @Deprecated
/* 327:    */   public double getMotherPosY()
/* 328:    */   {
/* 329:523 */     return this.dataWatcher.getWatchableObjectInt(24) / 10000.0D;
/* 330:    */   }
/* 331:    */   
/* 332:    */   @Deprecated
/* 333:    */   public void setMotherPosZ(double z)
/* 334:    */   {
/* 335:534 */     this.dataWatcher.updateObject(25, Integer.valueOf((int)(z * 10000.0D)));
/* 336:    */   }
/* 337:    */   
/* 338:    */   @Deprecated
/* 339:    */   public double getMotherPosZ()
/* 340:    */   {
/* 341:545 */     return this.dataWatcher.getWatchableObjectInt(25) / 10000.0D;
/* 342:    */   }
/* 343:    */   
/* 344:    */   @Deprecated
/* 345:    */   public void setMotherAndChild(int b)
/* 346:    */   {
/* 347:556 */     this.dataWatcher.updateObject(26, Integer.valueOf(b));
/* 348:    */   }
/* 349:    */   
/* 350:    */   @Deprecated
/* 351:    */   public int isMotherAndChild()
/* 352:    */   {
/* 353:567 */     return this.dataWatcher.getWatchableObjectInt(26);
/* 354:    */   }
/* 355:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.shot.EntityTHLaser
 * JD-Core Version:    0.7.0.1
 */