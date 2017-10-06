/*   1:    */ package thKaguyaMod.entity.shot;
/*   2:    */ 
/*   3:    */ import java.util.List;
/*   4:    */ import net.minecraft.entity.Entity;
/*   5:    */ import net.minecraft.entity.EntityLivingBase;
/*   6:    */ import net.minecraft.entity.boss.EntityDragonPart;
/*   7:    */ import net.minecraft.entity.passive.EntityAnimal;
/*   8:    */ import net.minecraft.entity.passive.EntityVillager;
/*   9:    */ import net.minecraft.entity.player.EntityPlayer;
/*  10:    */ import net.minecraft.entity.player.InventoryPlayer;
/*  11:    */ import net.minecraft.item.ItemStack;
/*  12:    */ import net.minecraft.util.AxisAlignedBB;
/*  13:    */ import net.minecraft.util.DamageSource;
/*  14:    */ import net.minecraft.util.MovingObjectPosition;
/*  15:    */ import net.minecraft.util.Vec3;
/*  16:    */ import net.minecraft.world.World;
/*  17:    */ import thKaguyaMod.ShotData;
/*  18:    */ import thKaguyaMod.THShotLib;
/*  19:    */ import thKaguyaMod.entity.item.EntitySakuyaWatch;
/*  20:    */ import thKaguyaMod.entity.living.EntityTHFairy;
/*  21:    */ import thKaguyaMod.init.THKaguyaItems;
/*  22:    */ 
/*  23:    */ public class EntityNuclearShot
/*  24:    */   extends EntityTHShot
/*  25:    */ {
/*  26:    */   public boolean shootingFlag;
/*  27:    */   
/*  28:    */   public EntityNuclearShot(World world)
/*  29:    */   {
/*  30: 32 */     super(world);
/*  31:    */   }
/*  32:    */   
/*  33:    */   public EntityNuclearShot(World world, EntityLivingBase user, Entity entity, Vec3 pos, Vec3 angle, float slope, Vec3 rotate, float rotationSpeed, int rotationEnd, double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity, int color, float size, float damage, int delay, int end, int special)
/*  34:    */   {
/*  35: 64 */     super(world, user, entity, pos, angle, slope, rotate, rotationSpeed, rotationEnd, firstSpeed, limitSpeed, acceleration, gravity, color, size, damage, delay, end, special);
/*  36:    */     
/*  37: 66 */     this.shootingFlag = false;
/*  38:    */   }
/*  39:    */   
/*  40:    */   public EntityNuclearShot(World world, EntityLivingBase user, Entity entity, Vec3 pos, Vec3 angle, float slope, Vec3 rotate, float rotationSpeed, int rotationEnd, double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity, ShotData shot)
/*  41:    */   {
/*  42: 93 */     this(world, user, entity, pos, angle, slope, rotate, rotationSpeed, rotationEnd, firstSpeed, limitSpeed, acceleration, gravity, shot.color, shot.size, shot.damage, shot.delay, shot.end, shot.special);
/*  43:    */   }
/*  44:    */   
/*  45:    */   public void onUpdate()
/*  46:    */   {
/*  47:101 */     if (this.user != null)
/*  48:    */     {
/*  49:103 */       if ((this.user instanceof EntityPlayer))
/*  50:    */       {
/*  51:105 */         EntityPlayer player = (EntityPlayer)this.user;
/*  52:106 */         if ((player.isUsingItem()) && (!this.shootingFlag))
/*  53:    */         {
/*  54:108 */           Vec3 vec3 = THShotLib.getVecFromAngle(-this.rotationYaw + 20.0F, -this.rotationPitch, 0.3D + getShotSize() / 2.0D);
/*  55:    */           
/*  56:    */ 
/*  57:    */ 
/*  58:112 */           this.prevPosX = this.posX;
/*  59:113 */           this.prevPosY = this.posY;
/*  60:114 */           this.prevPosZ = this.posZ;
/*  61:    */           
/*  62:116 */           setPositionAndRotation(this.user.posX + vec3.xCoord, THShotLib.getPosYFromEye(this.user, -0.1D) + vec3.yCoord, this.user.posZ + vec3.zCoord, -this.user.rotationYaw, -this.user.rotationPitch);
/*  63:118 */           if (getShotSize() < 6.0F) {
/*  64:120 */             setShotSize(getShotSize() + 0.06F);
/*  65:    */           }
/*  66:122 */           if (player.inventory.getCurrentItem() != null)
/*  67:    */           {
/*  68:124 */             if (player.inventory.getCurrentItem().getItem() != THKaguyaItems.nuclear_control_rod) {
/*  69:127 */               if (!this.worldObj.isRemote)
/*  70:    */               {
/*  71:129 */                 this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, getShotSize(), true);
/*  72:130 */                 setDead();
/*  73:    */               }
/*  74:    */             }
/*  75:    */           }
/*  76:    */           else
/*  77:    */           {
/*  78:137 */             if (!this.worldObj.isRemote)
/*  79:    */             {
/*  80:139 */               this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, getShotSize(), true);
/*  81:140 */               setDead();
/*  82:    */             }
/*  83:142 */             return;
/*  84:    */           }
/*  85:144 */           return;
/*  86:    */         }
/*  87:146 */         if (!this.shootingFlag)
/*  88:    */         {
/*  89:149 */           if (player.inventory.getCurrentItem() != null)
/*  90:    */           {
/*  91:151 */             if (player.inventory.getCurrentItem().getItem() != THKaguyaItems.nuclear_control_rod) {
/*  92:154 */               if (!this.worldObj.isRemote)
/*  93:    */               {
/*  94:156 */                 this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, getShotSize(), true);
/*  95:157 */                 setDead();
/*  96:    */               }
/*  97:    */             }
/*  98:    */           }
/*  99:    */           else
/* 100:    */           {
/* 101:164 */             if (!this.worldObj.isRemote)
/* 102:    */             {
/* 103:166 */               this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, getShotSize(), true);
/* 104:167 */               setDead();
/* 105:    */             }
/* 106:169 */             return;
/* 107:    */           }
/* 108:171 */           this.ticksExisted = 1;
/* 109:172 */           setVector();
/* 110:173 */           this.shotAcceleration = 0.2D;
/* 111:174 */           this.shootingFlag = true;
/* 112:    */         }
/* 113:    */       }
/* 114:    */     }
/* 115:181 */     else if (!this.worldObj.isRemote)
/* 116:    */     {
/* 117:183 */       setDead();
/* 118:184 */       return;
/* 119:    */     }
/* 120:188 */     this.shotDamage = (getShotSize() * 8.0F);
/* 121:    */     
/* 122:190 */     super.onUpdate();
/* 123:    */   }
/* 124:    */   
/* 125:    */   public boolean userHitCheck(Entity entity)
/* 126:    */   {
/* 127:202 */     if ((this.shotSpecial == 1601) || (this.shotSpecial == 1600)) {
/* 128:204 */       return true;
/* 129:    */     }
/* 130:207 */     return !entity.isEntityEqual(this.user);
/* 131:    */   }
/* 132:    */   
/* 133:    */   public MovingObjectPosition hitEntityCheck(MovingObjectPosition movingObjectPosition, Vec3 vec3d, Vec3 vec3d1)
/* 134:    */   {
/* 135:213 */     Entity entity = null;
/* 136:214 */     double d = 0.0D;
/* 137:215 */     float hitSize = getShotSize() * 0.5F;
/* 138:    */     
/* 139:217 */     List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(hitSize, hitSize, hitSize));
/* 140:219 */     for (int j = 0; j < list.size(); j++)
/* 141:    */     {
/* 142:221 */       Entity entity1 = (Entity)list.get(j);
/* 143:223 */       if ((entity1.canBeCollidedWith()) && 
/* 144:224 */         (userHitCheck(entity1))) {
/* 145:226 */         if ((!hitCheckEx(entity1)) && (!(entity1 instanceof EntitySakuyaWatch)) && (!(entity1 instanceof EntityAnimal)) && (!(entity1 instanceof EntityVillager)) && (((entity1 instanceof EntityLivingBase)) || ((entity1 instanceof EntityDragonPart)) || ((entity1 instanceof EntityTHShot))) && ((!(this.user instanceof EntityTHFairy)) || (!(entity1 instanceof EntityTHFairy))))
/* 146:    */         {
/* 147:234 */           AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(hitSize, hitSize, hitSize);
/* 148:235 */           MovingObjectPosition movingObjectPosition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);
/* 149:238 */           if (movingObjectPosition1 != null)
/* 150:    */           {
/* 151:240 */             movingObjectPosition1 = new MovingObjectPosition(entity1);
/* 152:241 */             onImpact(movingObjectPosition1);
/* 153:    */           }
/* 154:    */         }
/* 155:    */       }
/* 156:    */     }
/* 157:255 */     if (movingObjectPosition != null) {
/* 158:258 */       onImpact(movingObjectPosition);
/* 159:    */     }
/* 160:261 */     return movingObjectPosition;
/* 161:    */   }
/* 162:    */   
/* 163:    */   protected boolean onImpact(MovingObjectPosition movingObjectPosition)
/* 164:    */   {
/* 165:293 */     if (!this.worldObj.isRemote)
/* 166:    */     {
/* 167:295 */       Entity hitEntity = movingObjectPosition.entityHit;
/* 168:298 */       if (hitEntity != null)
/* 169:    */       {
/* 170:301 */         if (!(hitEntity instanceof EntityTHShot))
/* 171:    */         {
/* 172:303 */           boolean isHitDelete = true;
/* 173:    */           
/* 174:305 */           isHitDelete = entityHitSpecialProcess(hitEntity);
/* 175:308 */           if (!hitEntity.isEntityEqual(this.user)) {
/* 176:310 */             if (hitEntity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, this.user), this.shotDamage)) {}
/* 177:    */           }
/* 178:    */         }
/* 179:    */         else
/* 180:    */         {
/* 181:316 */           EntityTHShot entityTHShot = (EntityTHShot)hitEntity;
/* 182:334 */           if (this.user != entityTHShot.user)
/* 183:    */           {
/* 184:338 */             float shotDamageA = this.shotDamage;
/* 185:339 */             this.shotDamage -= entityTHShot.shotDamage;
/* 186:340 */             entityTHShot.shotDamage -= shotDamageA;
/* 187:    */           }
/* 188:    */         }
/* 189:    */       }
/* 190:348 */       else if (blockHitSpecialProcess(movingObjectPosition))
/* 191:    */       {
/* 192:350 */         delete();
/* 193:351 */         return true;
/* 194:    */       }
/* 195:    */     }
/* 196:355 */     return false;
/* 197:    */   }
/* 198:    */   
/* 199:    */   public boolean hitCheckEx(Entity entity)
/* 200:    */   {
/* 201:362 */     return false;
/* 202:    */   }
/* 203:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.shot.EntityNuclearShot
 * JD-Core Version:    0.7.0.1
 */