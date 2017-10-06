/*   1:    */ package thKaguyaMod.entity.item;
/*   2:    */ 
/*   3:    */ import java.util.List;
/*   4:    */ import net.minecraft.entity.Entity;
/*   5:    */ import net.minecraft.entity.EntityLivingBase;
/*   6:    */ import net.minecraft.entity.item.EntityItemFrame;
/*   7:    */ import net.minecraft.entity.item.EntityPainting;
/*   8:    */ import net.minecraft.entity.monster.EntityCreeper;
/*   9:    */ import net.minecraft.entity.monster.EntityGhast;
/*  10:    */ import net.minecraft.entity.passive.EntityTameable;
/*  11:    */ import net.minecraft.entity.player.EntityPlayer;
/*  12:    */ import net.minecraft.entity.player.EntityPlayerMP;
/*  13:    */ import net.minecraft.entity.player.PlayerCapabilities;
/*  14:    */ import net.minecraft.nbt.NBTTagCompound;
/*  15:    */ import net.minecraft.network.NetHandlerPlayServer;
/*  16:    */ import net.minecraft.util.AxisAlignedBB;
/*  17:    */ import net.minecraft.util.MovingObjectPosition;
/*  18:    */ import net.minecraft.world.World;
/*  19:    */ import thKaguyaMod.THKaguyaLib;
/*  20:    */ import thKaguyaMod.entity.spellcard.EntitySpellCard;
/*  21:    */ import thKaguyaMod.init.THKaguyaItems;
/*  22:    */ 
/*  23:    */ public class EntitySakuyaWatch
/*  24:    */   extends Entity
/*  25:    */ {
/*  26:    */   public EntityLivingBase userEntity;
/*  27:    */   private int count;
/*  28:    */   private int mode;
/*  29:    */   private float watchRotation;
/*  30:    */   private boolean isSpellCard;
/*  31:    */   public static final int TIME_STOP = 0;
/*  32:    */   public static final int TIME_STOP_IN_SPELLCARD = 1;
/*  33:    */   public static final int TIME_HALF = 2;
/*  34:    */   public static final int TIME_STOP_WITH_LIMIT = 5;
/*  35:    */   public static final int TIME_HALF_WITH_LIMIT = 6;
/*  36:    */   
/*  37:    */   public EntitySakuyaWatch(World world)
/*  38:    */   {
/*  39: 44 */     super(world);
/*  40: 45 */     this.ignoreFrustumCheck = true;
/*  41: 46 */     this.preventEntitySpawning = true;
/*  42: 47 */     setSize(1.0F, 1.0F);
/*  43: 48 */     this.yOffset = 0.0F;
/*  44: 49 */     this.mode = 2;
/*  45:    */     
/*  46:    */ 
/*  47: 52 */     shouldRenderInPass(-2);
/*  48:    */   }
/*  49:    */   
/*  50:    */   public EntitySakuyaWatch(World world, EntityLivingBase living, int pmode)
/*  51:    */   {
/*  52: 57 */     this(world);
/*  53:    */     
/*  54: 59 */     this.userEntity = living;
/*  55: 60 */     THKaguyaLib.itemEffectFollowUser(this, this.userEntity, 1.2D, -30.0F);
/*  56: 61 */     this.mode = pmode;
/*  57:    */     
/*  58: 63 */     this.isSpellCard = (this.mode == 1);
/*  59:    */   }
/*  60:    */   
/*  61:    */   protected void entityInit() {}
/*  62:    */   
/*  63:    */   public boolean canBePushed()
/*  64:    */   {
/*  65: 79 */     return false;
/*  66:    */   }
/*  67:    */   
/*  68:    */   public boolean canBeCollidedWith()
/*  69:    */   {
/*  70: 88 */     return false;
/*  71:    */   }
/*  72:    */   
/*  73:    */   public void onUpdate()
/*  74:    */   {
/*  75: 97 */     super.onUpdate();
/*  76:100 */     if ((!this.worldObj.isRemote) && (this.userEntity == null))
/*  77:    */     {
/*  78:103 */       if (!this.isSpellCard) {
/*  79:105 */         THKaguyaLib.itemEffectFinish(this, this.userEntity, THKaguyaItems.sakuya_watch);
/*  80:109 */       } else if (!this.worldObj.isRemote) {
/*  81:111 */         setDead();
/*  82:    */       }
/*  83:114 */       return;
/*  84:    */     }
/*  85:118 */     if ((this.mode == 1) && (this.ticksExisted > 60))
/*  86:    */     {
/*  87:122 */       if (!this.worldObj.isRemote) {
/*  88:124 */         setDead();
/*  89:    */       }
/*  90:    */     }
/*  91:127 */     else if ((this.mode == 5) && (this.ticksExisted > 100)) {
/*  92:130 */       THKaguyaLib.itemEffectFinish(this, this.userEntity, THKaguyaItems.sakuya_watch);
/*  93:132 */     } else if ((this.mode == 6) && (this.ticksExisted > 160)) {
/*  94:135 */       THKaguyaLib.itemEffectFinish(this, this.userEntity, THKaguyaItems.sakuya_watch);
/*  95:    */     }
/*  96:139 */     if ((!this.worldObj.isRemote) && (this.userEntity != null))
/*  97:    */     {
/*  98:141 */       if ((this.userEntity instanceof EntityPlayer))
/*  99:    */       {
/* 100:143 */         EntityPlayer userEntity_p = (EntityPlayer)this.userEntity;
/* 101:144 */         if (!userEntity_p.capabilities.isCreativeMode) {
/* 102:146 */           if (this.mode == 0) {
/* 103:148 */             userEntity_p.addExhaustion(0.25F);
/* 104:150 */           } else if (this.mode == 2) {
/* 105:152 */             userEntity_p.addExhaustion(0.1F);
/* 106:    */           }
/* 107:    */         }
/* 108:    */       }
/* 109:156 */       if ((this.ticksExisted > 10) && (this.userEntity.isSneaking())) {
/* 110:158 */         if (!this.isSpellCard) {
/* 111:160 */           THKaguyaLib.itemEffectFinish(this, this.userEntity, THKaguyaItems.sakuya_watch);
/* 112:164 */         } else if (!this.worldObj.isRemote) {
/* 113:166 */           setDead();
/* 114:    */         }
/* 115:    */       }
/* 116:170 */       if (this.userEntity.hurtTime > 0) {
/* 117:172 */         if (!this.isSpellCard) {
/* 118:174 */           THKaguyaLib.itemEffectFinish(this, this.userEntity, THKaguyaItems.sakuya_watch);
/* 119:178 */         } else if (!this.worldObj.isRemote) {
/* 120:180 */           setDead();
/* 121:    */         }
/* 122:    */       }
/* 123:    */     }
/* 124:198 */     THKaguyaLib.itemEffectFollowUser(this, this.userEntity, 1.2D, -30.0F);
/* 125:202 */     if ((this.mode == 3) && (this.ticksExisted == 45)) {
/* 126:204 */       this.mode = 0;
/* 127:    */     }
/* 128:206 */     if ((this.mode == 4) && (this.ticksExisted == 45)) {
/* 129:208 */       this.mode = 2;
/* 130:    */     }
/* 131:211 */     int playerCount = 0;
/* 132:    */     
/* 133:    */ 
/* 134:214 */     MovingObjectPosition movingObjectPosition = new MovingObjectPosition(this);
/* 135:215 */     Entity entity = null;
/* 136:216 */     boolean dubbleWatch = false;
/* 137:217 */     boolean pass = false;
/* 138:218 */     double effectiveBoundary = 40.0D;
/* 139:    */     
/* 140:220 */     List<?> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(effectiveBoundary, effectiveBoundary, effectiveBoundary));
/* 141:222 */     if ((list != null) && (list.size() > 0)) {
/* 142:224 */       for (int j1 = 0; j1 < list.size(); j1++)
/* 143:    */       {
/* 144:226 */         entity = (Entity)list.get(j1);
/* 145:227 */         if (entity != null) {
/* 146:229 */           movingObjectPosition = new MovingObjectPosition(entity);
/* 147:    */         }
/* 148:231 */         if ((entity instanceof EntityPlayer)) {
/* 149:233 */           playerCount++;
/* 150:    */         }
/* 151:236 */         if ((movingObjectPosition != null) && (movingObjectPosition.entityHit != this.userEntity) && (movingObjectPosition.entityHit.riddenByEntity != this.userEntity) && (!(movingObjectPosition.entityHit instanceof EntityItemFrame)) && (!(movingObjectPosition.entityHit instanceof EntityPainting)))
/* 152:    */         {
/* 153:240 */           pass = false;
/* 154:242 */           if ((movingObjectPosition.entityHit instanceof EntitySakuyaWatch))
/* 155:    */           {
/* 156:244 */             EntitySakuyaWatch watch = (EntitySakuyaWatch)movingObjectPosition.entityHit;
/* 157:    */             
/* 158:246 */             THKaguyaLib.itemEffectFinish(watch, watch.userEntity, THKaguyaItems.sakuya_watch);
/* 159:247 */             dubbleWatch = true;
/* 160:    */           }
/* 161:249 */           if ((movingObjectPosition.entityHit instanceof EntitySpellCard))
/* 162:    */           {
/* 163:251 */             EntitySpellCard spellCard = (EntitySpellCard)movingObjectPosition.entityHit;
/* 164:252 */             if ((spellCard.canMoveInTimeStop) && (spellCard.user.equals(this.userEntity)))
/* 165:    */             {
/* 166:254 */               pass = true;
/* 167:255 */               spellCard.specialProcessInTimeStop();
/* 168:    */             }
/* 169:    */           }
/* 170:258 */           if (!pass) {
/* 171:260 */             inPrivateSquare(movingObjectPosition);
/* 172:    */           }
/* 173:    */         }
/* 174:    */       }
/* 175:    */     }
/* 176:266 */     if (playerCount == 0) {
/* 177:268 */       if (!this.worldObj.isRemote) {
/* 178:270 */         THKaguyaLib.itemEffectFinish(this, this.userEntity, THKaguyaItems.sakuya_watch);
/* 179:    */       }
/* 180:    */     }
/* 181:274 */     if (dubbleWatch) {
/* 182:277 */       THKaguyaLib.itemEffectFinish(this, this.userEntity, THKaguyaItems.sakuya_watch);
/* 183:    */     }
/* 184:282 */     this.count += 1;
/* 185:292 */     if (this.mode > 2)
/* 186:    */     {
/* 187:294 */       if (this.count % 20 == 19)
/* 188:    */       {
/* 189:300 */         this.worldObj.playSoundAtEntity(this, "random.orb", 0.5F, 6.0F);
/* 190:301 */         this.worldObj.playSoundAtEntity(this, "random.click", 0.5F, 4.0F);
/* 191:    */       }
/* 192:    */     }
/* 193:304 */     else if (this.count % 20 == 19)
/* 194:    */     {
/* 195:306 */       this.worldObj.playSoundAtEntity(this, "random.orb", 0.5F, 6.0F);
/* 196:307 */       this.worldObj.playSoundAtEntity(this, "random.click", 0.5F, 4.0F);
/* 197:    */     }
/* 198:    */   }
/* 199:    */   
/* 200:    */   protected void writeEntityToNBT(NBTTagCompound nbtTagCompound)
/* 201:    */   {
/* 202:318 */     nbtTagCompound.setShort("count", (short)this.count);
/* 203:319 */     nbtTagCompound.setBoolean("isSpellCard", this.isSpellCard);
/* 204:    */   }
/* 205:    */   
/* 206:    */   protected void readEntityFromNBT(NBTTagCompound nbtTagCompound)
/* 207:    */   {
/* 208:329 */     this.count = nbtTagCompound.getShort("count");
/* 209:330 */     this.isSpellCard = nbtTagCompound.getBoolean("isSpellCard");
/* 210:    */   }
/* 211:    */   
/* 212:    */   public float getShadowSize()
/* 213:    */   {
/* 214:336 */     return 0.5F;
/* 215:    */   }
/* 216:    */   
/* 217:    */   protected void inPrivateSquare(MovingObjectPosition movingObjectPosition)
/* 218:    */   {
/* 219:343 */     Entity hitEntity = movingObjectPosition.entityHit;
/* 220:346 */     if (hitEntity.ticksExisted >= 2) {
/* 221:349 */       if ((this.mode == 0) || (this.mode == 1) || (this.mode == 5))
/* 222:    */       {
/* 223:353 */         hitEntity.setPosition(hitEntity.prevPosX, hitEntity.prevPosY, hitEntity.prevPosZ);
/* 224:    */         
/* 225:    */ 
/* 226:    */ 
/* 227:    */ 
/* 228:    */ 
/* 229:    */ 
/* 230:    */ 
/* 231:    */ 
/* 232:    */ 
/* 233:    */ 
/* 234:    */ 
/* 235:    */ 
/* 236:    */ 
/* 237:367 */         hitEntity.rotationYaw = hitEntity.prevRotationYaw;
/* 238:368 */         hitEntity.rotationPitch = hitEntity.prevRotationPitch;
/* 239:369 */         hitEntity.motionX = 0.0D;
/* 240:370 */         if (!hitEntity.onGround) {
/* 241:372 */           if (this.worldObj.isRemote) {
/* 242:374 */             hitEntity.motionY = -0.0D;
/* 243:    */           } else {
/* 244:378 */             hitEntity.motionY = -0.0D;
/* 245:    */           }
/* 246:    */         }
/* 247:381 */         hitEntity.motionZ = 0.0D;
/* 248:    */         
/* 249:    */ 
/* 250:    */ 
/* 251:    */ 
/* 252:    */ 
/* 253:    */ 
/* 254:388 */         hitEntity.setAir(0);
/* 255:    */         
/* 256:    */ 
/* 257:    */ 
/* 258:    */ 
/* 259:    */ 
/* 260:    */ 
/* 261:    */ 
/* 262:    */ 
/* 263:    */ 
/* 264:    */ 
/* 265:    */ 
/* 266:    */ 
/* 267:    */ 
/* 268:    */ 
/* 269:    */ 
/* 270:404 */         hitEntity.ticksExisted -= 1;
/* 271:    */         
/* 272:406 */         hitEntity.fallDistance -= 0.076865F;
/* 273:407 */         if ((hitEntity instanceof EntityLivingBase))
/* 274:    */         {
/* 275:409 */           EntityLivingBase living = (EntityLivingBase)hitEntity;
/* 276:410 */           living.rotationYawHead = living.prevRotationYawHead;
/* 277:    */           
/* 278:    */ 
/* 279:    */ 
/* 280:    */ 
/* 281:    */ 
/* 282:    */ 
/* 283:    */ 
/* 284:    */ 
/* 285:    */ 
/* 286:    */ 
/* 287:    */ 
/* 288:    */ 
/* 289:423 */           living.attackTime += 1;
/* 290:427 */           if ((living instanceof EntityCreeper))
/* 291:    */           {
/* 292:429 */             EntityCreeper entityCreeper = (EntityCreeper)living;
/* 293:    */             
/* 294:    */ 
/* 295:432 */             entityCreeper.setCreeperState(-1);
/* 296:    */           }
/* 297:442 */           else if ((living instanceof EntityGhast))
/* 298:    */           {
/* 299:444 */             EntityGhast entityGhast = (EntityGhast)living;
/* 300:445 */             entityGhast.attackCounter -= 1;
/* 301:    */           }
/* 302:448 */           if ((living instanceof EntityTameable)) {
/* 303:453 */             living.motionY -= 1.0E-006D;
/* 304:    */           }
/* 305:463 */           if ((living instanceof EntityPlayerMP))
/* 306:    */           {
/* 307:465 */             EntityPlayerMP player = (EntityPlayerMP)living;
/* 308:466 */             player.playerNetServerHandler.setPlayerLocation(player.prevPosX, player.prevPosY, player.prevPosZ, player.rotationYaw, player.rotationPitch);
/* 309:    */           }
/* 310:    */         }
/* 311:    */       }
/* 312:476 */       else if ((this.mode == 2) || (this.mode == 6))
/* 313:    */       {
/* 314:478 */         slowDownSpeed(hitEntity, 1, 2);
/* 315:    */       }
/* 316:480 */       else if (this.mode == 3)
/* 317:    */       {
/* 318:482 */         slowDownSpeed(hitEntity, 5 - this.ticksExisted / 9, 5);
/* 319:    */       }
/* 320:484 */       else if (this.mode == 4)
/* 321:    */       {
/* 322:486 */         slowDownSpeed(hitEntity, 10 - this.ticksExisted / 9, 10);
/* 323:    */       }
/* 324:    */     }
/* 325:    */   }
/* 326:    */   
/* 327:    */   private void slowDownSpeed(Entity hitEntity, int timeRateNum, int timeRateDen)
/* 328:    */   {
/* 329:497 */     float timeRate = timeRateNum / timeRateDen;
/* 330:500 */     if (timeRate < 1.0D)
/* 331:    */     {
/* 332:502 */       hitEntity.motionX -= (hitEntity.posX - hitEntity.prevPosX) * timeRate;
/* 333:503 */       hitEntity.motionY -= (hitEntity.posY - hitEntity.prevPosY) * timeRate;
/* 334:504 */       hitEntity.motionZ -= (hitEntity.posZ - hitEntity.prevPosZ) * timeRate;
/* 335:505 */       hitEntity.fallDistance -= 0.076865F * timeRate;
/* 336:506 */       if (this.count % timeRateDen < timeRateNum)
/* 337:    */       {
/* 338:508 */         hitEntity.ticksExisted -= 1;
/* 339:509 */         if ((hitEntity instanceof EntityLivingBase))
/* 340:    */         {
/* 341:511 */           EntityLivingBase EntityLivingBase = (EntityLivingBase)hitEntity;
/* 342:512 */           EntityLivingBase.rotationYawHead = EntityLivingBase.prevRotationYawHead;
/* 343:514 */           if ((EntityLivingBase instanceof EntityCreeper))
/* 344:    */           {
/* 345:516 */             EntityCreeper entityCreeper = (EntityCreeper)EntityLivingBase;
/* 346:    */             
/* 347:    */ 
/* 348:519 */             entityCreeper.setCreeperState(-1);
/* 349:    */           }
/* 350:523 */           else if ((EntityLivingBase instanceof EntityGhast))
/* 351:    */           {
/* 352:525 */             EntityGhast entityGhast = (EntityGhast)EntityLivingBase;
/* 353:526 */             entityGhast.attackCounter -= 1;
/* 354:    */           }
/* 355:    */         }
/* 356:    */       }
/* 357:    */     }
/* 358:    */     else
/* 359:    */     {
/* 360:533 */       int timeRate2 = (int)timeRate;
/* 361:534 */       for (int i = 2; i <= timeRate2; i++)
/* 362:    */       {
/* 363:536 */         hitEntity.ticksExisted += 1;
/* 364:537 */         hitEntity.onUpdate();
/* 365:    */       }
/* 366:    */     }
/* 367:    */   }
/* 368:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.item.EntitySakuyaWatch
 * JD-Core Version:    0.7.0.1
 */