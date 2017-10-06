/*   1:    */ package thKaguyaMod.entity.item;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.relauncher.Side;
/*   4:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   5:    */ import java.util.List;
/*   6:    */ import net.minecraft.block.material.Material;
/*   7:    */ import net.minecraft.client.Minecraft;
/*   8:    */ import net.minecraft.client.settings.GameSettings;
/*   9:    */ import net.minecraft.client.settings.KeyBinding;
/*  10:    */ import net.minecraft.entity.DataWatcher;
/*  11:    */ import net.minecraft.entity.Entity;
/*  12:    */ import net.minecraft.entity.player.EntityPlayer;
/*  13:    */ import net.minecraft.entity.player.PlayerCapabilities;
/*  14:    */ import net.minecraft.nbt.NBTTagCompound;
/*  15:    */ import net.minecraft.util.AxisAlignedBB;
/*  16:    */ import net.minecraft.util.DamageSource;
/*  17:    */ import net.minecraft.util.MathHelper;
/*  18:    */ import net.minecraft.world.World;
/*  19:    */ import thKaguyaMod.init.THKaguyaItems;
/*  20:    */ 
/*  21:    */ public class EntityMarisaBroom
/*  22:    */   extends Entity
/*  23:    */ {
/*  24:    */   private boolean isEmpty;
/*  25:    */   private double speedMultiplier;
/*  26:    */   private int boatPosRotationIncrements;
/*  27:    */   private double broomX;
/*  28:    */   private double broomY;
/*  29:    */   private double broomZ;
/*  30:    */   private double broomYaw;
/*  31:    */   private double broomPitch;
/*  32:    */   @SideOnly(Side.CLIENT)
/*  33:    */   private double velocityX;
/*  34:    */   @SideOnly(Side.CLIENT)
/*  35:    */   private double velocityY;
/*  36:    */   @SideOnly(Side.CLIENT)
/*  37:    */   private double velocityZ;
/*  38:    */   
/*  39:    */   public EntityMarisaBroom(World world)
/*  40:    */   {
/*  41: 39 */     super(world);
/*  42: 40 */     this.isEmpty = true;
/*  43: 41 */     this.speedMultiplier = 0.07000000000000001D;
/*  44: 42 */     this.preventEntitySpawning = true;
/*  45: 43 */     setSize(0.9F, 0.5F);
/*  46: 44 */     this.yOffset = 0.8F;
/*  47:    */   }
/*  48:    */   
/*  49:    */   public EntityMarisaBroom(World world, double x, double y, double z)
/*  50:    */   {
/*  51: 49 */     this(world);
/*  52: 50 */     setPosition(x, y + this.yOffset, z);
/*  53: 51 */     this.motionX = 0.0D;
/*  54: 52 */     this.motionY = 0.0D;
/*  55: 53 */     this.motionZ = 0.0D;
/*  56: 54 */     this.prevPosX = x;
/*  57: 55 */     this.prevPosY = y;
/*  58: 56 */     this.prevPosZ = z;
/*  59:    */   }
/*  60:    */   
/*  61:    */   protected boolean canTriggerWalking()
/*  62:    */   {
/*  63: 66 */     return false;
/*  64:    */   }
/*  65:    */   
/*  66:    */   protected void entityInit()
/*  67:    */   {
/*  68: 73 */     this.dataWatcher.addObject(17, new Integer(0));
/*  69: 74 */     this.dataWatcher.addObject(18, new Integer(1));
/*  70: 75 */     this.dataWatcher.addObject(19, new Float(0.0F));
/*  71:    */   }
/*  72:    */   
/*  73:    */   public AxisAlignedBB getCollisionBox(Entity entity)
/*  74:    */   {
/*  75: 85 */     return entity.boundingBox;
/*  76:    */   }
/*  77:    */   
/*  78:    */   public AxisAlignedBB getBoundingBox()
/*  79:    */   {
/*  80: 94 */     return this.boundingBox;
/*  81:    */   }
/*  82:    */   
/*  83:    */   public boolean canBePushed()
/*  84:    */   {
/*  85:103 */     return true;
/*  86:    */   }
/*  87:    */   
/*  88:    */   public double getMountedYOffset()
/*  89:    */   {
/*  90:112 */     return -0.15D;
/*  91:    */   }
/*  92:    */   
/*  93:    */   public boolean attackEntityFrom(DamageSource damageSource, float par2)
/*  94:    */   {
/*  95:120 */     if (isEntityInvulnerable()) {
/*  96:122 */       return false;
/*  97:    */     }
/*  98:125 */     if ((!this.worldObj.isRemote) && (!this.isDead))
/*  99:    */     {
/* 100:127 */       setForwardDirection(-getForwardDirection());
/* 101:128 */       setTimeSinceHit(10);
/* 102:129 */       setDamageTaken(getDamageTaken() + par2 * 10.0F);
/* 103:130 */       setBeenAttacked();
/* 104:131 */       boolean flag = ((damageSource.getEntity() instanceof EntityPlayer)) && (((EntityPlayer)damageSource.getEntity()).capabilities.isCreativeMode);
/* 105:133 */       if ((flag) || (getDamageTaken() > 40.0F))
/* 106:    */       {
/* 107:135 */         if (this.riddenByEntity != null) {
/* 108:137 */           this.riddenByEntity.mountEntity(this);
/* 109:    */         }
/* 110:140 */         if (!flag) {
/* 111:142 */           dropItem(THKaguyaItems.magic_bloom, 1);
/* 112:    */         }
/* 113:145 */         setDead();
/* 114:    */       }
/* 115:148 */       return true;
/* 116:    */     }
/* 117:152 */     return true;
/* 118:    */   }
/* 119:    */   
/* 120:    */   @SideOnly(Side.CLIENT)
/* 121:    */   public void performHurtAnimation()
/* 122:    */   {
/* 123:162 */     setForwardDirection(-getForwardDirection());
/* 124:163 */     setTimeSinceHit(10);
/* 125:164 */     setDamageTaken(getDamageTaken() * 11.0F);
/* 126:    */   }
/* 127:    */   
/* 128:    */   public boolean canBeCollidedWith()
/* 129:    */   {
/* 130:171 */     return !this.isDead;
/* 131:    */   }
/* 132:    */   
/* 133:    */   @SideOnly(Side.CLIENT)
/* 134:    */   public void setPositionAndRotation2(double x, double y, double z, float yaw, float pitch, int par9)
/* 135:    */   {
/* 136:182 */     if (this.isEmpty)
/* 137:    */     {
/* 138:184 */       this.boatPosRotationIncrements = (par9 + 5);
/* 139:    */     }
/* 140:    */     else
/* 141:    */     {
/* 142:188 */       double xDistance = x - this.posX;
/* 143:189 */       double yDistance = y - this.posY;
/* 144:190 */       double zDistance = z - this.posZ;
/* 145:191 */       double distance = xDistance * xDistance + yDistance * yDistance + zDistance * zDistance;
/* 146:193 */       if (distance <= 1.0D) {
/* 147:195 */         return;
/* 148:    */       }
/* 149:198 */       this.boatPosRotationIncrements = 3;
/* 150:    */     }
/* 151:201 */     this.broomX = x;
/* 152:202 */     this.broomY = y;
/* 153:203 */     this.broomZ = z;
/* 154:204 */     this.broomYaw = yaw;
/* 155:205 */     this.broomPitch = pitch;
/* 156:206 */     this.motionX = this.velocityX;
/* 157:207 */     this.motionY = this.velocityY;
/* 158:208 */     this.motionZ = this.velocityZ;
/* 159:    */   }
/* 160:    */   
/* 161:    */   @SideOnly(Side.CLIENT)
/* 162:    */   public void setVelocity(double x, double y, double z)
/* 163:    */   {
/* 164:216 */     this.velocityX = (this.motionX = x);
/* 165:217 */     this.velocityY = (this.motionY = y);
/* 166:218 */     this.velocityZ = (this.motionZ = z);
/* 167:    */   }
/* 168:    */   
/* 169:    */   @SideOnly(Side.CLIENT)
/* 170:    */   public boolean isPushKeyUp()
/* 171:    */   {
/* 172:224 */     Minecraft mc = Minecraft.getMinecraft();
/* 173:225 */     GameSettings gameSettings = mc.gameSettings;
/* 174:226 */     return gameSettings.keyBindForward.getIsKeyPressed();
/* 175:    */   }
/* 176:    */   
/* 177:    */   @SideOnly(Side.CLIENT)
/* 178:    */   public boolean isPushKeyBack()
/* 179:    */   {
/* 180:232 */     Minecraft mc = Minecraft.getMinecraft();
/* 181:233 */     GameSettings gameSettings = mc.gameSettings;
/* 182:234 */     return gameSettings.keyBindBack.getIsKeyPressed();
/* 183:    */   }
/* 184:    */   
/* 185:    */   @SideOnly(Side.CLIENT)
/* 186:    */   public boolean isPushKeyRight()
/* 187:    */   {
/* 188:240 */     Minecraft mc = Minecraft.getMinecraft();
/* 189:241 */     GameSettings gameSettings = mc.gameSettings;
/* 190:242 */     return gameSettings.keyBindRight.getIsKeyPressed();
/* 191:    */   }
/* 192:    */   
/* 193:    */   @SideOnly(Side.CLIENT)
/* 194:    */   public boolean isPushKeyLeft()
/* 195:    */   {
/* 196:248 */     Minecraft mc = Minecraft.getMinecraft();
/* 197:249 */     GameSettings gameSettings = mc.gameSettings;
/* 198:250 */     return gameSettings.keyBindLeft.getIsKeyPressed();
/* 199:    */   }
/* 200:    */   
/* 201:    */   @SideOnly(Side.CLIENT)
/* 202:    */   public boolean isPushKeyJump()
/* 203:    */   {
/* 204:256 */     Minecraft mc = Minecraft.getMinecraft();
/* 205:257 */     GameSettings gameSettings = mc.gameSettings;
/* 206:258 */     return gameSettings.keyBindJump.getIsKeyPressed();
/* 207:    */   }
/* 208:    */   
/* 209:    */   public void onUpdate()
/* 210:    */   {
/* 211:265 */     super.onUpdate();
/* 212:268 */     if (getTimeSinceHit() > 0) {
/* 213:270 */       setTimeSinceHit(getTimeSinceHit() - 1);
/* 214:    */     }
/* 215:273 */     if (getDamageTaken() > 0.0F) {
/* 216:275 */       setDamageTaken(getDamageTaken() - 1.0F);
/* 217:    */     }
/* 218:279 */     this.prevPosX = this.posX;
/* 219:280 */     this.prevPosY = this.posY;
/* 220:281 */     this.prevPosZ = this.posZ;
/* 221:    */     
/* 222:283 */     byte b0 = 5;
/* 223:284 */     double d0 = 0.0D;
/* 224:286 */     for (int i = 0; i < b0; i++)
/* 225:    */     {
/* 226:288 */       double d1 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (i + 0) / b0 - 0.125D;
/* 227:289 */       double d2 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (i + 1) / b0 - 0.125D;
/* 228:290 */       AxisAlignedBB axisalignedbb = AxisAlignedBB.getBoundingBox(this.boundingBox.minX, d1, this.boundingBox.minZ, this.boundingBox.maxX, d2, this.boundingBox.maxZ);
/* 229:292 */       if (this.worldObj.isAABBInMaterial(axisalignedbb, Material.water)) {
/* 230:294 */         d0 += 1.0D / b0;
/* 231:    */       }
/* 232:    */     }
/* 233:298 */     double motionXZ = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);

/* 235:305 */     if ((this.worldObj.isRemote) && (this.isEmpty))
/* 236:    */     {
/* 237:308 */       if (this.boatPosRotationIncrements > 0)
/* 238:    */       {
/* 239:311 */         double x_increments = this.posX + (this.broomX - this.posX) / this.boatPosRotationIncrements;
/* 240:312 */         double y_increments = this.posY + (this.broomY - this.posY) / this.boatPosRotationIncrements;
/* 241:313 */         double z_increments = this.posZ + (this.broomZ - this.posZ) / this.boatPosRotationIncrements;
/* 242:314 */         double d10 = MathHelper.wrapAngleTo180_double(this.broomYaw - this.rotationYaw);
/* 243:315 */         this.rotationYaw = ((float)(this.rotationYaw + d10 / this.boatPosRotationIncrements));
/* 244:316 */         this.rotationPitch = ((float)(this.rotationPitch + (this.broomPitch - this.rotationPitch) / this.boatPosRotationIncrements));
/* 245:317 */         this.boatPosRotationIncrements -= 1;
/* 246:318 */         setPosition(x_increments, y_increments, z_increments);
/* 247:319 */         setRotation(this.rotationYaw, this.rotationPitch);
/* 248:    */       }
/* 249:    */       else
/* 250:    */       {
/* 251:324 */         double x_increments = this.posX + this.motionX;
/* 252:325 */         double y_increments = this.posY + this.motionY;
/* 253:326 */         double z_increments = this.posZ + this.motionZ;
/* 254:327 */         setPosition(x_increments, y_increments, z_increments);
/* 255:329 */         if (this.onGround)
/* 256:    */         {
/* 257:332 */           this.motionX *= 0.5D;
/* 258:333 */           this.motionY *= 0.5D;
/* 259:334 */           this.motionZ *= 0.5D;
/* 260:    */         }
/* 261:337 */         this.motionX *= 0.9900000095367432D;
/* 262:338 */         this.motionY *= 0.949999988079071D;
/* 263:339 */         this.motionZ *= 0.9900000095367432D;
/* 264:    */       }
/* 265:    */     }
/* 266:    */     else
/* 267:    */     {
/* 268:344 */       if (d0 < 1.0D) {
/* 269:346 */        double x_increments = d0 * 2.0D - 1.0D;
/* 270:    */       }
/* 271:360 */       if ((this.riddenByEntity != null) && ((this.riddenByEntity instanceof EntityPlayer)))
/* 272:    */       {
/* 273:363 */         EntityPlayer living = (EntityPlayer)this.riddenByEntity;
/* 274:    */         
/* 275:365 */         boolean move_up = true;
/* 276:366 */         boolean move_right = false;
/* 277:367 */         boolean move_left = false;
/* 278:368 */         boolean move_back = false;
/* 279:369 */         boolean move_jump = false;
/* 280:372 */         if (this.worldObj.isRemote)
/* 281:    */         {
/* 282:374 */           move_up = isPushKeyUp();
/* 283:375 */           move_right = isPushKeyRight();
/* 284:376 */           move_left = isPushKeyLeft();
/* 285:377 */           move_back = isPushKeyBack();
/* 286:378 */           move_jump = isPushKeyJump();
/* 287:    */         }
/* 288:395 */         double acceleration = 0.04D;
/* 289:396 */         double acceleration2 = 0.08D;
/* 290:397 */         if (move_jump)
/* 291:    */         {
/* 292:399 */           acceleration = 0.5D;
/* 293:    */           
/* 294:    */ 
/* 295:    */ 
/* 296:403 */           this.motionX *= 0.0D;
/* 297:404 */           this.motionZ *= 0.0D;
/* 298:405 */           this.motionY *= 0.0D;
/* 299:410 */           if (move_up) {
/* 300:412 */             this.motionY = (-Math.sin(-1.570796489715576D) * acceleration);
/* 301:    */           }
/* 302:418 */           if (move_back) {
/* 303:420 */             this.motionY = (-Math.sin(1.570796489715576D) * acceleration);
/* 304:    */           }
/* 305:427 */           if (move_right)
/* 306:    */           {
/* 307:429 */             this.motionX = (Math.sin((this.rotationYaw + 0.0F) / 180.0F * 3.141593F) * acceleration);
/* 308:430 */             this.motionZ = (-Math.cos((this.rotationYaw + 0.0F) / 180.0F * 3.141593F) * acceleration);
/* 309:    */           }
/* 310:432 */           if (move_left)
/* 311:    */           {
/* 312:434 */             this.motionX = (Math.sin((this.rotationYaw - 180.0F) / 180.0F * 3.141593F) * acceleration);
/* 313:435 */             this.motionZ = (-Math.cos((this.rotationYaw - 180.0F) / 180.0F * 3.141593F) * acceleration);
/* 314:    */           }
/* 315:437 */           double moveSpeed = Math.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
/* 316:438 */           if (moveSpeed > 0.5D)
/* 317:    */           {
/* 318:440 */             this.motionX *= moveSpeed;
/* 319:441 */             this.motionY *= moveSpeed;
/* 320:442 */             this.motionZ *= moveSpeed;
/* 321:    */           }
/* 322:    */         }
/* 323:    */         else
/* 324:    */         {
/* 325:447 */           this.motionY += -Math.sin(this.rotationPitch / 180.0F * 3.141593F) * acceleration;
/* 326:448 */           if ((!move_up) && (!move_back) && (!move_right) && (!move_left))
/* 327:    */           {
/* 328:451 */             this.motionX *= 0.97D;
/* 329:452 */             this.motionZ *= 0.97D;
/* 330:453 */             this.motionY *= 0.97D;
/* 331:    */           }
/* 332:    */           else
/* 333:    */           {
/* 334:457 */             if (move_up)
/* 335:    */             {
/* 336:459 */               this.motionX += Math.sin((this.rotationYaw - 90.0F) / 180.0F * 3.141593F) * acceleration;
/* 337:460 */               this.motionZ += -Math.cos((this.rotationYaw - 90.0F) / 180.0F * 3.141593F) * acceleration;
/* 338:    */             }
/* 339:462 */             if (move_back)
/* 340:    */             {
/* 341:464 */               this.motionX += -Math.sin((this.rotationYaw - 90.0F) / 180.0F * 3.141593F) * acceleration2;
/* 342:465 */               this.motionZ += Math.cos((this.rotationYaw - 90.0F) / 180.0F * 3.141593F) * acceleration2;
/* 343:    */             }
/* 344:    */           }
/* 345:468 */           if (move_right)
/* 346:    */           {
/* 347:470 */             this.motionX += Math.sin((this.rotationYaw + 0.0F) / 180.0F * 3.141593F) * acceleration;
/* 348:471 */             this.motionZ += -Math.cos((this.rotationYaw + 0.0F) / 180.0F * 3.141593F) * acceleration;
/* 349:    */           }
/* 350:473 */           if (move_left)
/* 351:    */           {
/* 352:475 */             this.motionX += Math.sin((this.rotationYaw - 180.0F) / 180.0F * 3.141593F) * acceleration;
/* 353:476 */             this.motionZ += -Math.cos((this.rotationYaw - 180.0F) / 180.0F * 3.141593F) * acceleration;
/* 354:    */           }
/* 355:    */         }
/* 356:481 */         float f = this.riddenByEntity.rotationYaw;
/* 357:    */         
/* 358:483 */         this.speedMultiplier = 0.6D;
/* 359:    */         
/* 360:    */ 
/* 361:486 */         float pitch = this.riddenByEntity.rotationPitch;
/* 362:487 */         this.rotationPitch = pitch;
/* 363:489 */         if (pitch > 90.0F) {
/* 364:491 */           pitch = 90.0F;
/* 365:493 */         } else if (pitch < -90.0F) {
/* 366:495 */           pitch = -90.0F;
/* 367:    */         }
/* 368:501 */         if (pitch < 0.0F) {
/* 369:503 */           pitch *= 0.1F;
/* 370:    */         }
/* 371:507 */         this.rotationYaw = (this.riddenByEntity.rotationYaw - 90.0F);
/* 372:    */         
/* 373:    */ 
/* 374:510 */         double moveSpeed = Math.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
/* 375:511 */         if (moveSpeed > 0.8D)
/* 376:    */         {
/* 377:513 */           this.motionX *= moveSpeed;
/* 378:514 */           this.motionY *= moveSpeed;
/* 379:515 */           this.motionZ *= moveSpeed;
/* 380:    */         }
/* 381:    */       }
/* 382:555 */       if (this.inWater)
/* 383:    */       {
/* 384:557 */         this.motionX *= 0.5D;
/* 385:558 */         this.motionY *= 0.5D;
/* 386:559 */         this.motionZ *= 0.5D;
/* 387:    */       }
/* 388:562 */       moveEntity(this.motionX, this.motionY, this.motionZ);
/* 389:    */       
/* 390:    */ 
/* 391:    */ 
/* 392:    */ 
/* 393:    */ 
/* 394:    */ 
/* 395:    */ 
/* 396:    */ 
/* 397:    */ 
/* 398:    */ 
/* 399:    */ 
/* 400:    */ 
/* 401:    */ 
/* 402:    */ 
/* 403:    */ 
/* 404:    */ 
/* 405:    */ 
/* 406:    */ 
/* 407:    */ 
/* 408:    */ 
/* 409:    */ 
/* 410:584 */       this.motionX *= 0.9900000095367432D;
/* 411:585 */       this.motionY *= 0.949999988079071D;
/* 412:586 */       this.motionZ *= 0.9900000095367432D;
/* 413:    */       
/* 414:    */ 
/* 415:    */ 
/* 416:    */ 
/* 417:    */ 
/* 418:    */ 
/* 419:    */ 
/* 420:    */ 
/* 421:    */ 
/* 422:    */ 
/* 423:    */ 
/* 424:    */ 
/* 425:    */ 
/* 426:    */ 
/* 427:    */ 
/* 428:    */ 
/* 429:    */ 
/* 430:    */ 
/* 431:    */ 
/* 432:    */ 
/* 433:    */ 
/* 434:    */ 
/* 435:    */ 
/* 436:    */ 
/* 437:    */ 
/* 438:    */ 
/* 439:    */ 
/* 440:614 */       setRotation(this.rotationYaw, this.rotationPitch);
/* 441:617 */       if (!this.worldObj.isRemote)
/* 442:    */       {
/* 443:619 */         List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(0.2000000029802322D, 0.0D, 0.2000000029802322D));
/* 444:622 */         if ((list != null) && (!list.isEmpty())) {
/* 445:624 */           for (int l = 0; l < list.size(); l++)
/* 446:    */           {
/* 447:626 */             Entity entity = (Entity)list.get(l);
/* 448:628 */             if ((entity != this.riddenByEntity) && (entity.canBePushed()) && ((entity instanceof EntityMarisaBroom))) {
/* 449:630 */               entity.applyEntityCollision(this);
/* 450:    */             }
/* 451:    */           }
/* 452:    */         }
/* 453:657 */         if ((this.riddenByEntity != null) && (this.riddenByEntity.isDead)) {
/* 454:659 */           this.riddenByEntity = null;
/* 455:    */         }
/* 456:665 */         if (this.riddenByEntity != null)
/* 457:    */         {
/* 458:667 */           this.riddenByEntity.fallDistance = 0.0F;
/* 459:668 */           this.fallDistance = 0.0F;
/* 460:    */         }
/* 461:    */         else
/* 462:    */         {
/* 463:672 */           this.motionX *= 0.9D;
/* 464:673 */           this.motionY -= 0.03D;
/* 465:674 */           this.motionZ *= 0.9D;
/* 466:675 */           if (this.rotationPitch > 1.0F) {
/* 467:677 */             this.rotationPitch -= 1.0F;
/* 468:679 */           } else if (this.rotationPitch < -1.0F) {
/* 469:681 */             this.rotationPitch += 1.0F;
/* 470:    */           }
/* 471:683 */           if (this.onGround) {
/* 472:685 */             this.motionY += 0.06D;
/* 473:    */           }
/* 474:    */         }
/* 475:    */       }
/* 476:    */     }
/* 477:    */   }
/* 478:    */   
/* 479:    */   public void updateRiderPosition2()
/* 480:    */   {
/* 481:698 */     if (this.riddenByEntity != null)
/* 482:    */     {
/* 483:701 */       double d0 = 0.0D;
/* 484:702 */       double d1 = 0.0D;
/* 485:703 */       this.riddenByEntity.setPosition(this.posX + d0, this.posY + getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ + d1);
/* 486:    */     }
/* 487:    */   }
/* 488:    */   
/* 489:    */   public void updateRidden()
/* 490:    */   {
/* 491:712 */     super.updateRidden();
/* 492:713 */     if (this.ridingEntity.isDead) {
/* 493:715 */       this.ridingEntity = null;
/* 494:    */     } else {
/* 495:719 */       updateRiderPosition2();
/* 496:    */     }
/* 497:    */   }
/* 498:    */   
/* 499:    */   protected void writeEntityToNBT(NBTTagCompound nbtTagCompound) {}
/* 500:    */   
/* 501:    */   protected void readEntityFromNBT(NBTTagCompound nbtTagCompound) {}
/* 502:    */   
/* 503:    */   @SideOnly(Side.CLIENT)
/* 504:    */   public float getShadowSize()
/* 505:    */   {
/* 506:742 */     return 0.0F;
/* 507:    */   }
/* 508:    */   
/* 509:    */   public boolean interactFirst(EntityPlayer entityPlayer)
/* 510:    */   {
/* 511:750 */     if ((this.riddenByEntity != null) && ((this.riddenByEntity instanceof EntityPlayer)) && (this.riddenByEntity != entityPlayer)) {
/* 512:752 */       return true;
/* 513:    */     }
/* 514:756 */     if (!this.worldObj.isRemote) {
/* 515:758 */       entityPlayer.mountEntity(this);
/* 516:    */     }
/* 517:761 */     return true;
/* 518:    */   }
/* 519:    */   
/* 520:    */   public void setDamageTaken(float par1)
/* 521:    */   {
/* 522:770 */     this.dataWatcher.updateObject(19, Float.valueOf(par1));
/* 523:    */   }
/* 524:    */   
/* 525:    */   public float getDamageTaken()
/* 526:    */   {
/* 527:779 */     return this.dataWatcher.getWatchableObjectFloat(19);
/* 528:    */   }
/* 529:    */   
/* 530:    */   public void setTimeSinceHit(int par1)
/* 531:    */   {
/* 532:787 */     this.dataWatcher.updateObject(17, Integer.valueOf(par1));
/* 533:    */   }
/* 534:    */   
/* 535:    */   public int getTimeSinceHit()
/* 536:    */   {
/* 537:795 */     return this.dataWatcher.getWatchableObjectInt(17);
/* 538:    */   }
/* 539:    */   
/* 540:    */   public void setForwardDirection(int par1)
/* 541:    */   {
/* 542:803 */     this.dataWatcher.updateObject(18, Integer.valueOf(par1));
/* 543:    */   }
/* 544:    */   
/* 545:    */   public int getForwardDirection()
/* 546:    */   {
/* 547:811 */     return this.dataWatcher.getWatchableObjectInt(18);
/* 548:    */   }
/* 549:    */   
/* 550:    */   @SideOnly(Side.CLIENT)
/* 551:    */   public void setIsBoatEmpty(boolean par1)
/* 552:    */   {
/* 553:817 */     this.isEmpty = par1;
/* 554:    */   }
/* 555:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.item.EntityMarisaBroom
 * JD-Core Version:    0.7.0.1
 */