/*   1:    */ package thKaguyaMod.entity.item;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.relauncher.Side;
/*   4:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   5:    */ import java.util.List;
/*   6:    */ import net.minecraft.block.material.Material;
/*   7:    */ import net.minecraft.entity.DataWatcher;
/*   8:    */ import net.minecraft.entity.Entity;
/*   9:    */ import net.minecraft.entity.EntityLivingBase;
/*  10:    */ import net.minecraft.entity.item.EntityBoat;
/*  11:    */ import net.minecraft.entity.item.EntityItem;
/*  12:    */ import net.minecraft.entity.item.EntityXPOrb;
/*  13:    */ import net.minecraft.entity.player.EntityPlayer;
/*  14:    */ import net.minecraft.entity.player.PlayerCapabilities;
/*  15:    */ import net.minecraft.nbt.NBTTagCompound;
/*  16:    */ import net.minecraft.util.AxisAlignedBB;
/*  17:    */ import net.minecraft.util.DamageSource;
/*  18:    */ import net.minecraft.util.MathHelper;
/*  19:    */ import net.minecraft.util.MovingObjectPosition;
/*  20:    */ import net.minecraft.world.World;
/*  21:    */ import thKaguyaMod.entity.shot.EntityTHShot;
/*  22:    */ import thKaguyaMod.init.THKaguyaItems;
/*  23:    */ 
/*  24:    */ public class EntityKinkakuzi
/*  25:    */   extends Entity
/*  26:    */ {
/*  27:    */   private boolean isBoatEmpty;
/*  28:    */   private double speedMultiplier;
/*  29:    */   private int boatPosRotationIncrements;
/*  30:    */   private double boatX;
/*  31:    */   private double boatY;
/*  32:    */   private double boatZ;
/*  33:    */   private double boatYaw;
/*  34:    */   private double boatPitch;
/*  35:    */   @SideOnly(Side.CLIENT)
/*  36:    */   private double velocityX;
/*  37:    */   @SideOnly(Side.CLIENT)
/*  38:    */   private double velocityY;
/*  39:    */   @SideOnly(Side.CLIENT)
/*  40:    */   private double velocityZ;
/*  41:    */   private EntityLivingBase shootingEntity;
/*  42:    */   private int lastTime;
/*  43:    */   public double lastMotionX;
/*  44:    */   public double lastMotionY;
/*  45:    */   public double lastMotionZ;
/*  46:    */   
/*  47:    */   public EntityKinkakuzi(World world)
/*  48:    */   {
/*  49: 50 */     super(world);
/*  50: 51 */     this.isBoatEmpty = true;
/*  51: 52 */     this.speedMultiplier = 0.07000000000000001D;
/*  52: 53 */     this.preventEntitySpawning = true;
/*  53: 54 */     setSize(6.0F, 0.3F);
/*  54: 55 */     this.yOffset = (this.height / 2.0F);
/*  55:    */   }
/*  56:    */   
/*  57:    */   public EntityKinkakuzi(World world, double x, double y, double z, EntityLivingBase entityLivingBase, double pow)
/*  58:    */   {
/*  59: 61 */     this(world);
/*  60: 62 */     this.motionX = (-Math.sin((float)Math.toRadians(entityLivingBase.rotationYaw)) * 0.2D);
/*  61: 63 */     this.motionY = pow;
/*  62: 64 */     this.motionZ = (Math.cos((float)Math.toRadians(entityLivingBase.rotationYaw)) * 0.2D);
/*  63: 65 */     this.lastMotionX = this.motionX;
/*  64: 66 */     this.lastMotionY = this.motionY;
/*  65: 67 */     this.lastMotionZ = this.motionZ;
/*  66: 68 */     this.lastTime = 0;
/*  67: 69 */     this.prevPosX = x;
/*  68: 70 */     this.prevPosY = y;
/*  69: 71 */     this.prevPosZ = z;
/*  70: 72 */     this.rotationYaw = entityLivingBase.rotationYaw;
/*  71: 73 */     this.rotationPitch = 0.0F;
/*  72: 74 */     setLocationAndAngles(x, y, z, this.rotationYaw, this.rotationPitch);
/*  73: 75 */     this.shootingEntity = entityLivingBase;
/*  74:    */   }
/*  75:    */   
/*  76:    */   protected void entityInit()
/*  77:    */   {
/*  78: 82 */     this.dataWatcher.addObject(17, new Integer(0));
/*  79: 83 */     this.dataWatcher.addObject(18, new Integer(1));
/*  80: 84 */     this.dataWatcher.addObject(19, new Float(0.0F));
/*  81:    */   }
/*  82:    */   
/*  83:    */   public AxisAlignedBB getCollisionBox(Entity entity)
/*  84:    */   {
/*  85: 95 */     return entity.boundingBox;
/*  86:    */   }
/*  87:    */   
/*  88:    */   public AxisAlignedBB getBoundingBox()
/*  89:    */   {
/*  90:102 */     return this.boundingBox;
/*  91:    */   }
/*  92:    */   
/*  93:    */   public boolean canBePushed()
/*  94:    */   {
/*  95:109 */     return true;
/*  96:    */   }
/*  97:    */   
/*  98:    */   public double getMountedYOffset()
/*  99:    */   {
/* 100:117 */     return this.height * 0.0D + 0.3D;
/* 101:    */   }
/* 102:    */   
/* 103:    */   public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
/* 104:    */   {
/* 105:123 */     if (isEntityInvulnerable()) {
/* 106:125 */       return false;
/* 107:    */     }
/* 108:127 */     if ((!this.worldObj.isRemote) && (!this.isDead))
/* 109:    */     {
/* 110:129 */       setForwardDirection(-getForwardDirection());
/* 111:130 */       setTimeSinceHit(10);
/* 112:131 */       setDamageTaken(getDamageTaken() + par2 * 10.0F);
/* 113:132 */       setBeenAttacked();
/* 114:133 */       boolean flag = ((par1DamageSource.getEntity() instanceof EntityPlayer)) && (((EntityPlayer)par1DamageSource.getEntity()).capabilities.isCreativeMode);
/* 115:135 */       if ((flag) || (getDamageTaken() > 40.0F))
/* 116:    */       {
/* 117:137 */         if (this.riddenByEntity != null) {
/* 118:139 */           this.riddenByEntity.mountEntity(this);
/* 119:    */         }
/* 120:141 */         if (!flag) {
/* 121:145 */           dropItem(THKaguyaItems.kinkakuji, 1);
/* 122:    */         }
/* 123:147 */         setDead();
/* 124:    */       }
/* 125:150 */       return true;
/* 126:    */     }
/* 127:154 */     return true;
/* 128:    */   }
/* 129:    */   
/* 130:    */   @SideOnly(Side.CLIENT)
/* 131:    */   public void performHurtAnimation()
/* 132:    */   {
/* 133:164 */     setForwardDirection(-getForwardDirection());
/* 134:165 */     setTimeSinceHit(10);
/* 135:166 */     setDamageTaken(getDamageTaken() * 11.0F);
/* 136:    */   }
/* 137:    */   
/* 138:    */   public boolean canBeCollidedWith()
/* 139:    */   {
/* 140:174 */     return !this.isDead;
/* 141:    */   }
/* 142:    */   
/* 143:    */   @SideOnly(Side.CLIENT)
/* 144:    */   public void setPositionAndRotation2(double x, double y, double z, float yaw, float pitch, int par9)
/* 145:    */   {
/* 146:184 */     if (this.isBoatEmpty)
/* 147:    */     {
/* 148:186 */       this.boatPosRotationIncrements = (par9 + 5);
/* 149:    */     }
/* 150:    */     else
/* 151:    */     {
/* 152:190 */       double xDistance = x - this.posX;
/* 153:191 */       double yDistance = y - this.posY;
/* 154:192 */       double zDistance = z - this.posZ;
/* 155:193 */       double distance = xDistance * xDistance + yDistance * yDistance + zDistance * zDistance;
/* 156:195 */       if (distance <= 1.0D) {
/* 157:197 */         return;
/* 158:    */       }
/* 159:200 */       this.boatPosRotationIncrements = 3;
/* 160:    */     }
/* 161:203 */     this.boatX = x;
/* 162:204 */     this.boatY = y;
/* 163:205 */     this.boatZ = z;
/* 164:206 */     this.boatYaw = yaw;
/* 165:207 */     this.boatPitch = pitch;
/* 166:208 */     this.motionX = this.velocityX;
/* 167:209 */     this.motionY = this.velocityY;
/* 168:210 */     this.motionZ = this.velocityZ;
/* 169:    */   }
/* 170:    */   
/* 171:    */   @SideOnly(Side.CLIENT)
/* 172:    */   public void setVelocity(double x, double y, double z)
/* 173:    */   {
/* 174:217 */     this.velocityX = (this.motionX = x);
/* 175:218 */     this.velocityY = (this.motionY = y);
/* 176:219 */     this.velocityZ = (this.motionZ = z);
/* 177:    */   }
/* 178:    */   
/* 179:    */   public void onUpdate()
/* 180:    */   {
/* 181:226 */     super.onUpdate();
/* 182:241 */     if (getTimeSinceHit() > 0) {
/* 183:243 */       setTimeSinceHit(getTimeSinceHit() - 1);
/* 184:    */     }
/* 185:246 */     if (getDamageTaken() > 0.0F) {
/* 186:248 */       setDamageTaken(getDamageTaken() - 1.0F);
/* 187:    */     }
/* 188:252 */     this.prevPosX = this.posX;
/* 189:253 */     this.prevPosY = this.posY;
/* 190:254 */     this.prevPosZ = this.posZ;
/* 191:    */     
/* 192:256 */     int i = 5;
/* 193:257 */     double d = 0.0D;
/* 194:259 */     for (int j = 0; j < i; j++)
/* 195:    */     {
/* 196:261 */       double d2 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (j + 0) / i - 0.125D;
/* 197:262 */       double d8 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (j + 1) / i - 0.125D;
/* 198:263 */       AxisAlignedBB axisalignedbb = AxisAlignedBB.getBoundingBox(this.boundingBox.minX, d2, this.boundingBox.minZ, this.boundingBox.maxX, d8, this.boundingBox.maxZ);
/* 199:265 */       if (this.worldObj.isAABBInMaterial(axisalignedbb, Material.water)) {
/* 200:267 */         d += 1.0D / i;
/* 201:    */       }
/* 202:    */     }
/* 203:272 */     double lengthXZ = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
/* 204:    */     
/* 205:    */ 
/* 206:    */ 
/* 207:    */ 
/* 208:    */ 
/* 209:    */ 
/* 210:    */ 
/* 211:    */ 
/* 212:281 */     MovingObjectPosition movingobjectposition = new MovingObjectPosition(this);
/* 213:334 */     if (d < 1.0D)
/* 214:    */     {
/* 215:336 */       double newPosX = d * 2.0D - 1.0D;
/* 216:337 */       this.motionY += 0.03999999910593033D * newPosX;
/* 217:    */     }
/* 218:    */     else
/* 219:    */     {
/* 220:342 */       if (this.motionY < 0.0D) {
/* 221:344 */         this.motionY /= 2.0D;
/* 222:    */       }
/* 223:348 */       this.motionY += 0.007000000216066837D;
/* 224:    */     }
/* 225:351 */     if ((this.riddenByEntity != null) && ((this.riddenByEntity instanceof EntityLivingBase)))
/* 226:    */     {
/* 227:357 */       double newPosX = ((EntityLivingBase)this.riddenByEntity).moveForward;
/* 228:359 */       if (newPosX > 0.0D)
/* 229:    */       {
/* 230:361 */         double newPosY = -Math.sin(this.riddenByEntity.rotationYaw * 3.141593F / 180.0F);
/* 231:362 */         double newPosZ = Math.cos(this.riddenByEntity.rotationYaw * 3.141593F / 180.0F);
/* 232:363 */         this.motionX += newPosY * this.speedMultiplier * 0.0500000007450581D;
/* 233:364 */         this.motionZ += newPosZ * this.speedMultiplier * 0.0500000007450581D;
/* 234:    */       }
/* 235:    */     }
/* 236:368 */     double newPosX = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
/* 237:370 */     if (newPosX > 0.35D)
/* 238:    */     {
/* 239:372 */       double newPosY = 0.35D / newPosX;
/* 240:373 */       this.motionX *= newPosY;
/* 241:374 */       this.motionZ *= newPosY;
/* 242:375 */       newPosX = 0.35D;
/* 243:    */     }
/* 244:378 */     if ((newPosX > lengthXZ) && (this.speedMultiplier < 0.35D))
/* 245:    */     {
/* 246:380 */       this.speedMultiplier += (0.35D - this.speedMultiplier) / 35.0D;
/* 247:382 */       if (this.speedMultiplier > 0.35D) {
/* 248:384 */         this.speedMultiplier = 0.35D;
/* 249:    */       }
/* 250:    */     }
/* 251:    */     else
/* 252:    */     {
/* 253:389 */       this.speedMultiplier -= (this.speedMultiplier - 0.07000000000000001D) / 35.0D;
/* 254:391 */       if (this.speedMultiplier < 0.07000000000000001D) {
/* 255:393 */         this.speedMultiplier = 0.07000000000000001D;
/* 256:    */       }
/* 257:    */     }
/* 258:420 */     if (this.onGround)
/* 259:    */     {
/* 260:422 */       this.motionX *= 0.5D;
/* 261:423 */       this.motionY *= 0.5D;
/* 262:424 */       this.motionZ *= 0.5D;
/* 263:    */     }
/* 264:428 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/* 265:430 */     if ((!this.isCollidedHorizontally) || (lengthXZ <= 0.2D))
/* 266:    */     {
/* 267:436 */       this.motionX *= 0.9900000095367432D;
/* 268:437 */       this.motionY *= 0.949999988079071D;
/* 269:438 */       this.motionZ *= 0.9900000095367432D;
/* 270:    */     }
/* 271:441 */     this.rotationPitch = 0.0F;
/* 272:442 */     double newPosY = this.rotationYaw;
/* 273:443 */     double newPosZ = this.prevPosX - this.posX;
/* 274:444 */     double d10 = this.prevPosZ - this.posZ;
/* 275:446 */     if (newPosZ * newPosZ + d10 * d10 > 0.001D) {
/* 276:448 */       newPosY = (float)(Math.atan2(d10, newPosZ) * 180.0D / 3.141592653589793D);
/* 277:    */     }
/* 278:451 */     double angleShift = MathHelper.wrapAngleTo180_double(newPosY - this.rotationYaw);
/* 279:460 */     if (angleShift > 20.0D) {
/* 280:462 */       angleShift = 20.0D;
/* 281:    */     }
/* 282:464 */     if (angleShift < -20.0D) {
/* 283:466 */       angleShift = -20.0D;
/* 284:    */     }
/* 285:470 */     this.rotationYaw = ((float)(this.rotationYaw + angleShift));
/* 286:    */     
/* 287:472 */     setRotation(this.rotationYaw, this.rotationPitch);
/* 288:474 */     if (!this.worldObj.isRemote)
/* 289:    */     {
/* 290:476 */       List<?> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(0.2000000029802322D, 0.0D, 0.2000000029802322D));
/* 291:479 */       if ((list != null) && (!list.isEmpty())) {
/* 292:481 */         for (int l = 0; l < list.size(); l++)
/* 293:    */         {
/* 294:483 */           Entity entity = (Entity)list.get(l);
/* 295:485 */           if ((entity != this.riddenByEntity) && (entity.canBePushed()) && ((entity instanceof EntityBoat))) {
/* 296:487 */             entity.applyEntityCollision(this);
/* 297:    */           }
/* 298:    */         }
/* 299:    */       }
/* 300:494 */       Entity entity = null;
/* 301:495 */       list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(MathHelper.sin((float)this.boatYaw / 180.0F * 3.141593F) * 0.1D, 0.5D, MathHelper.cos((float)this.boatYaw / 180.0F * 3.141593F) * 0.1D));
/* 302:499 */       if ((list != null) && (list.size() > 0)) {
/* 303:501 */         for (int j1 = 0; j1 < list.size(); j1++)
/* 304:    */         {
/* 305:503 */           entity = (Entity)list.get(j1);
/* 306:504 */           if ((entity != this.riddenByEntity) && (entity.canBePushed()) && ((entity instanceof EntityKinkakuzi))) {
/* 307:506 */             entity.applyEntityCollision(this);
/* 308:    */           }
/* 309:508 */           if (entity != null) {
/* 310:510 */             movingobjectposition = new MovingObjectPosition(entity);
/* 311:    */           }
/* 312:512 */           if ((movingobjectposition.entityHit != this.riddenByEntity) && (movingobjectposition != null) && (!(entity instanceof EntityKinkakuzi))) {
/* 313:514 */             hitOnEntity(movingobjectposition);
/* 314:    */           }
/* 315:    */         }
/* 316:    */       }
/* 317:522 */       if ((this.riddenByEntity != null) && (this.riddenByEntity.isDead)) {
/* 318:525 */         this.riddenByEntity = null;
/* 319:    */       }
/* 320:    */     }
/* 321:531 */     if (this.ticksExisted > this.lastTime)
/* 322:    */     {
/* 323:534 */       this.lastTime = this.ticksExisted;
/* 324:535 */       this.lastMotionX = this.motionX;
/* 325:536 */       this.lastMotionY = this.motionY;
/* 326:537 */       this.lastMotionZ = this.motionZ;
/* 327:    */     }
/* 328:    */   }
/* 329:    */   
/* 330:    */   protected void hitOnEntity(MovingObjectPosition movingobjectposition)
/* 331:    */   {
/* 332:548 */     if ((!this.worldObj.isRemote) && (movingobjectposition.entityHit != null)) {
/* 333:550 */       if ((movingobjectposition.entityHit.posY < this.posY) && (!(movingobjectposition.entityHit instanceof EntityItem)) && (!(movingobjectposition.entityHit instanceof EntityXPOrb)) && (!(movingobjectposition.entityHit instanceof EntityTHShot)) && (movingobjectposition.entityHit != this.shootingEntity))
/* 334:    */       {
/* 335:556 */         double yMove = this.prevPosY - this.posY;
/* 336:557 */         if (yMove >= 0.0D)
/* 337:    */         {
/* 338:559 */           int damage = (int)(yMove * 40.0D) + 2;
/* 339:560 */           movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, this.shootingEntity), damage);
/* 340:    */         }
/* 341:    */       }
/* 342:    */     }
/* 343:    */   }
/* 344:    */   
/* 345:    */   public void updateRiderPosition()
/* 346:    */   {
/* 347:569 */     if (this.riddenByEntity != null)
/* 348:    */     {
/* 349:571 */       double d = Math.cos(this.rotationYaw * 3.141592653589793D / 180.0D) * 0.5D;
/* 350:572 */       double d1 = Math.sin(this.rotationYaw * 3.141592653589793D / 180.0D) * 0.5D;
/* 351:573 */       this.riddenByEntity.setPosition(this.posX + d, this.posY + getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ + d1);
/* 352:    */     }
/* 353:    */   }
/* 354:    */   
/* 355:    */   protected void writeEntityToNBT(NBTTagCompound nbtTagCompound) {}
/* 356:    */   
/* 357:    */   protected void readEntityFromNBT(NBTTagCompound nbtTagCompound) {}
/* 358:    */   
/* 359:    */   @SideOnly(Side.CLIENT)
/* 360:    */   public float getShadowSize()
/* 361:    */   {
/* 362:596 */     return 0.0F;
/* 363:    */   }
/* 364:    */   
/* 365:    */   public boolean interactFirst(EntityPlayer entityPlayer)
/* 366:    */   {
/* 367:606 */     if ((this.riddenByEntity != null) && ((this.riddenByEntity instanceof EntityPlayer)) && (this.riddenByEntity != entityPlayer)) {
/* 368:608 */       return true;
/* 369:    */     }
/* 370:612 */     if (!this.worldObj.isRemote) {
/* 371:614 */       entityPlayer.mountEntity(this);
/* 372:    */     }
/* 373:617 */     return true;
/* 374:    */   }
/* 375:    */   
/* 376:    */   public void setDamageTaken(float par1)
/* 377:    */   {
/* 378:626 */     this.dataWatcher.updateObject(19, Float.valueOf(par1));
/* 379:    */   }
/* 380:    */   
/* 381:    */   public float getDamageTaken()
/* 382:    */   {
/* 383:635 */     return this.dataWatcher.getWatchableObjectFloat(19);
/* 384:    */   }
/* 385:    */   
/* 386:    */   public void setTimeSinceHit(int par1)
/* 387:    */   {
/* 388:645 */     this.dataWatcher.updateObject(17, Integer.valueOf(par1));
/* 389:    */   }
/* 390:    */   
/* 391:    */   public int getTimeSinceHit()
/* 392:    */   {
/* 393:653 */     return this.dataWatcher.getWatchableObjectInt(17);
/* 394:    */   }
/* 395:    */   
/* 396:    */   public void setForwardDirection(int par1)
/* 397:    */   {
/* 398:661 */     this.dataWatcher.updateObject(18, Integer.valueOf(par1));
/* 399:    */   }
/* 400:    */   
/* 401:    */   public int getForwardDirection()
/* 402:    */   {
/* 403:669 */     return this.dataWatcher.getWatchableObjectInt(18);
/* 404:    */   }
/* 405:    */   
/* 406:    */   @SideOnly(Side.CLIENT)
/* 407:    */   public void setIsBoatEmpty(boolean par1)
/* 408:    */   {
/* 409:675 */     this.isBoatEmpty = par1;
/* 410:    */   }
/* 411:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.item.EntityKinkakuzi
 * JD-Core Version:    0.7.0.1
 */