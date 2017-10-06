/*   1:    */ package thKaguyaMod.entity.shot;
/*   2:    */ 
/*   3:    */ import java.util.List;
/*   4:    */ import net.minecraft.entity.Entity;
/*   5:    */ import net.minecraft.entity.EntityLivingBase;
/*   6:    */ import net.minecraft.entity.item.EntityItem;
/*   7:    */ import net.minecraft.entity.passive.EntityAnimal;
/*   8:    */ import net.minecraft.entity.passive.EntityVillager;
/*   9:    */ import net.minecraft.entity.player.EntityPlayer;
/*  10:    */ import net.minecraft.entity.player.PlayerCapabilities;
/*  11:    */ import net.minecraft.item.ItemStack;
/*  12:    */ import net.minecraft.util.AxisAlignedBB;
/*  13:    */ import net.minecraft.util.DamageSource;
/*  14:    */ import net.minecraft.util.MovingObjectPosition;
/*  15:    */ import net.minecraft.util.Vec3;
/*  16:    */ import net.minecraft.world.World;
/*  17:    */ import thKaguyaMod.entity.item.EntitySakuyaWatch;
/*  18:    */ import thKaguyaMod.entity.living.EntityTHFairy;
/*  19:    */ import thKaguyaMod.init.THKaguyaItems;
/*  20:    */ 
/*  21:    */ public class EntityTHHenyoriLaser
/*  22:    */   extends EntityTHLaser
/*  23:    */ {
/*  24:    */   public EntityTHHenyoriLaser(World world)
/*  25:    */   {
/*  26: 28 */     super(world);
/*  27: 29 */     setSize(15.0F, 1.0F);
/*  28:    */   }
/*  29:    */   
/*  30:    */   protected void entityInit()
/*  31:    */   {
/*  32: 47 */     super.entityInit();
/*  33:    */   }
/*  34:    */   
/*  35:    */   public void shotFinishBonus()
/*  36:    */   {
/*  37: 56 */     double zPos = 0.0D;
/*  38: 57 */     while (zPos < getLaserLength())
/*  39:    */     {
/*  40: 59 */       EntityItem entityItem = new EntityItem(this.worldObj, this.posX - this.angle.xCoord * zPos, this.posY - this.angle.yCoord * zPos, this.posZ - this.angle.zCoord * zPos, new ItemStack(THKaguyaItems.shot_material, 1));
/*  41: 60 */       this.worldObj.spawnEntityInWorld(entityItem);
/*  42: 61 */       entityItem.age = 5700;
/*  43: 62 */       zPos += 0.3D;
/*  44:    */     }
/*  45: 64 */     if (!this.worldObj.isRemote) {
/*  46: 66 */       setDead();
/*  47:    */     }
/*  48:    */   }
/*  49:    */   
/*  50:    */   public void onUpdate()
/*  51:    */   {
/*  52: 74 */     super.onUpdate();
/*  53:    */     
/*  54: 76 */     double laserLength = getLaserLength() + getSpeed() * 2.0D;
/*  55: 77 */     if (laserLength > getMaxLaserLength()) {
/*  56: 79 */       setLaserLength(getMaxLaserLength());
/*  57:    */     } else {
/*  58: 83 */       setLaserLength(laserLength);
/*  59:    */     }
/*  60:    */   }
/*  61:    */   
/*  62:    */   public void hitCheck()
/*  63:    */   {
/*  64: 91 */     double width = getShotSize();
/*  65: 92 */     double length = getLaserLength();
/*  66:    */     
/*  67: 94 */     float angleSpanY = 0.1047198F;
/*  68: 95 */     float baseAngle = getAnimationCount() * angleSpanY;
/*  69: 96 */     float baseAngle2 = baseAngle % 90.0F;
/*  70: 97 */     int flontCount = 0;
/*  71: 98 */     double moveLength = 0.0D;
/*  72:    */     
/*  73:    */ 
/*  74:101 */     double nowX = this.posX;
/*  75:102 */     double nowY = this.posY;
/*  76:103 */     double nowZ = this.posZ;
/*  77:105 */     for (int i = 0; i < 13; i++)
/*  78:    */     {
/*  79:107 */       baseAngle += angleSpanY;
/*  80:108 */       flontCount++;
/*  81:109 */       moveLength = flontCount * getLaserLength() / 13.0D;
/*  82:110 */       double nextX = nowX + this.angle.xCoord * moveLength - Math.sin(baseAngle) * 3.0D + this.motionX;
/*  83:111 */       double nextY = nowY + this.angle.yCoord * moveLength + this.motionY;
/*  84:112 */       double nextZ = nowZ + this.angle.zCoord * moveLength + Math.cos(baseAngle) * 3.0D + this.motionZ;
/*  85:    */       
/*  86:    */ 
/*  87:    */ 
/*  88:116 */       Vec3 vec3d = Vec3.createVectorHelper(nowX, nowY, nowZ);
/*  89:    */       
/*  90:118 */       Vec3 vec3d1 = Vec3.createVectorHelper(nextX, nextY, nextZ);
/*  91:    */       
/*  92:    */ 
/*  93:121 */       MovingObjectPosition movingObjectPosition = this.worldObj.rayTraceBlocks(vec3d, vec3d1, true);
/*  94:122 */       vec3d = Vec3.createVectorHelper(nowX, nowY, nowZ);
/*  95:123 */       vec3d1 = Vec3.createVectorHelper(nextX, nextY, nextZ);
/*  96:125 */       if (movingObjectPosition != null)
/*  97:    */       {
/*  98:128 */         vec3d1 = Vec3.createVectorHelper(movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
/*  99:129 */         onImpact(movingObjectPosition);
/* 100:    */       }
/* 101:133 */       Entity entity = null;
/* 102:134 */       double d = 0.0D;
/* 103:    */       
/* 104:136 */       double xLength = this.motionX + Math.sin(this.rotationYaw / 180.0F * 3.141593F) * Math.cos(this.rotationPitch / 180.0F * 3.141593F) * moveLength;
/* 105:137 */       double yLength = this.motionY + Math.sin(this.rotationPitch / 180.0F * 3.141593F) * moveLength;
/* 106:138 */       double zLength = this.motionZ + Math.cos(this.rotationYaw / 180.0F * 3.141593F) * Math.cos(this.rotationPitch / 180.0F * 3.141593F) * moveLength;
/* 107:139 */       EntityTHHenyoriLaser dummyEntity = new EntityTHHenyoriLaser(this.worldObj);
/* 108:140 */       dummyEntity.posX = nowX;
/* 109:141 */       dummyEntity.posY = nowY;
/* 110:142 */       dummyEntity.posZ = nowZ;
/* 111:    */       
/* 112:144 */       List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(dummyEntity, this.boundingBox.addCoord(xLength, yLength, zLength).expand(width, width, width));
/* 113:146 */       for (int j = 0; j < list.size(); j++)
/* 114:    */       {
/* 115:148 */         Entity entity1 = (Entity)list.get(j);
/* 116:150 */         if (entity1.canBeCollidedWith()) {
/* 117:153 */           if ((!hitCheckEx(entity1)) && (!(entity1 instanceof EntityTHShot)) && (!(entity1 instanceof EntitySakuyaWatch)) && (!(entity1 instanceof EntityAnimal)) && (!(entity1 instanceof EntityVillager)) && ((entity1 instanceof EntityLivingBase)) && ((!(this.user instanceof EntityTHFairy)) || (!(entity1 instanceof EntityTHFairy))))
/* 118:    */           {
/* 119:163 */             AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(width, width, width);
/* 120:164 */             MovingObjectPosition movingObjectPosition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);
/* 121:166 */             if (movingObjectPosition1 != null)
/* 122:    */             {
/* 123:168 */               movingObjectPosition = new MovingObjectPosition(entity1);
/* 124:169 */               boolean creative = false;
/* 125:170 */               if ((movingObjectPosition != null) && (movingObjectPosition.entityHit != null) && ((movingObjectPosition.entityHit instanceof EntityPlayer)))
/* 126:    */               {
/* 127:172 */                 EntityPlayer entityPlayer = (EntityPlayer)movingObjectPosition.entityHit;
/* 128:178 */                 if ((entityPlayer.capabilities.disableDamage) || ((this.source instanceof EntityPlayer))) {
/* 129:181 */                   movingObjectPosition = null;
/* 130:    */                 }
/* 131:    */               }
/* 132:184 */               if (movingObjectPosition != null) {
/* 133:186 */                 onImpact(movingObjectPosition);
/* 134:    */               }
/* 135:    */             }
/* 136:    */           }
/* 137:    */         }
/* 138:    */       }
/* 139:192 */       nowX = nowX + this.angle.xCoord * moveLength - Math.sin(baseAngle) * 3.0D;
/* 140:193 */       nowY += this.angle.yCoord * moveLength;
/* 141:194 */       nowZ = nowZ + this.angle.zCoord * moveLength + Math.cos(baseAngle) * 3.0D;
/* 142:    */     }
/* 143:    */   }
/* 144:    */   
/* 145:    */   protected boolean onImpact(MovingObjectPosition movingObjectPosition)
/* 146:    */   {
/* 147:208 */     if (movingObjectPosition.entityHit != null)
/* 148:    */     {
/* 149:211 */       Entity hitEntity = movingObjectPosition.entityHit;
/* 150:214 */       if (hitEntity != null) {
/* 151:217 */         if (!(hitEntity instanceof EntityTHShot))
/* 152:    */         {
/* 153:220 */           entityHitSpecialProcess(hitEntity);
/* 154:222 */           if (((this.worldObj.isRemote) || 
/* 155:    */           
/* 156:224 */             (hitEntity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, this.user), this.shotDamage))) || 
/* 157:    */             
/* 158:    */ 
/* 159:    */ 
/* 160:    */ 
/* 161:229 */             (hitEntity.hurtResistantTime == 0)) {
/* 162:231 */             this.shotDamage -= 1.0F;
/* 163:    */           }
/* 164:233 */           if (this.shotDamage <= 0.0F) {
/* 165:235 */             this.shotDamage = 1.0F;
/* 166:    */           }
/* 167:    */         }
/* 168:    */       }
/* 169:    */     }
/* 170:    */     else
/* 171:    */     {
/* 172:293 */       blockHitSpecialProcess(movingObjectPosition);
/* 173:294 */       double hitDistance = getDistance(movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
/* 174:295 */       if (hitDistance > 0.3D)
/* 175:    */       {
/* 176:297 */         if (!this.worldObj.isRemote)
/* 177:    */         {
/* 178:299 */           setLaserLength(hitDistance);
/* 179:300 */           setMaxLaserLength(hitDistance);
/* 180:    */         }
/* 181:    */       }
/* 182:305 */       else if (!this.worldObj.isRemote) {
/* 183:307 */         setDead();
/* 184:    */       }
/* 185:    */     }
/* 186:318 */     if (this.shotDamage <= 0.0F) {
/* 187:320 */       delete();
/* 188:    */     }
/* 189:323 */     return true;
/* 190:    */   }
/* 191:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.shot.EntityTHHenyoriLaser
 * JD-Core Version:    0.7.0.1
 */