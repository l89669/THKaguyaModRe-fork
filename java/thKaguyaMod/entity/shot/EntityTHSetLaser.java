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
/*  13:    */ import net.minecraft.util.MovingObjectPosition;
/*  14:    */ import net.minecraft.util.Vec3;
/*  15:    */ import net.minecraft.world.World;
/*  16:    */ import thKaguyaMod.LaserData;
/*  17:    */ import thKaguyaMod.THShotLib;
/*  18:    */ import thKaguyaMod.entity.item.EntitySakuyaWatch;
/*  19:    */ import thKaguyaMod.entity.living.EntityTHFairy;
/*  20:    */ 
/*  21:    */ public class EntityTHSetLaser
/*  22:    */   extends EntityTHLaser
/*  23:    */ {
/*  24:    */   private double settingLength;
/*  25:    */   private double settingYOffset;
/*  26:    */   private Vec3 setPos;
/*  27:    */   public Entity setting;
/*  28:    */   
/*  29:    */   public EntityTHSetLaser(World world)
/*  30:    */   {
/*  31: 40 */     super(world);
/*  32: 42 */     if (this.worldObj.isRemote) {
/*  33: 44 */       setSize((float)getLaserLength(), getShotSize());
/*  34:    */     }
/*  35:    */   }
/*  36:    */   
/*  37:    */   public EntityTHSetLaser(World world, EntityLivingBase user, Entity source, Vec3 pos, Vec3 angle, float slope, Vec3 rotate, float rotationSpeed, int rotationEnd, int color, float width, float length, float damage, int delay, int end, int special, Entity set, double setLength, double setYOffset)
/*  38:    */   {
/*  39: 53 */     super(world, user, source, pos, angle, slope, rotate, rotationSpeed, rotationEnd, 0.0D, 0.0D, 0.0D, 
/*  40: 54 */       THShotLib.gravity_Zero(), color, width, length, damage, delay, end, special);
/*  41: 55 */     setLaserLength(length);
/*  42: 56 */     setMaxLaserLength(length);
/*  43:    */     
/*  44:    */ 
/*  45: 59 */     this.settingLength = setLength;
/*  46: 60 */     this.settingYOffset = setYOffset;
/*  47:    */     
/*  48:    */ 
/*  49:    */ 
/*  50:    */ 
/*  51:    */ 
/*  52:    */ 
/*  53:    */ 
/*  54:    */ 
/*  55: 69 */     this.setting = set;
/*  56: 70 */     if (set != null)
/*  57:    */     {
/*  58: 72 */       this.setPos = pos;
/*  59: 73 */       this.posX = (set.posX + angle.xCoord * this.settingLength);
/*  60: 74 */       this.posY = (set.posY + angle.yCoord * this.settingLength + this.settingYOffset);
/*  61: 75 */       this.posZ = (set.posZ + angle.zCoord * this.settingLength);
/*  62: 76 */       setPosition(this.posX, this.posY, this.posZ);
/*  63:    */     }
/*  64:    */   }
/*  65:    */   
/*  66:    */   public EntityTHSetLaser(World world, EntityLivingBase user, Entity source, Vec3 pos, Vec3 angle, float slope, Vec3 rotate, float rotationSpeed, int rotationEnd, LaserData laser, Entity set, double setLength, double setYOffset)
/*  67:    */   {
/*  68: 87 */     this(world, user, source, pos, angle, slope, rotate, rotationSpeed, rotationEnd, laser.color, laser.width, laser.length, laser.damage, laser.delay, laser.end, laser.special, set, setLength, setYOffset);
/*  69:    */   }
/*  70:    */   
/*  71:    */   protected void entityInit()
/*  72:    */   {
/*  73: 95 */     super.entityInit();
/*  74:    */   }
/*  75:    */   
/*  76:    */   public void onUpdate()
/*  77:    */   {
/*  78:104 */     super.onUpdate();
/*  79:115 */     if (this.setting != null)
/*  80:    */     {
/*  81:117 */       if (this.setting.isDead)
/*  82:    */       {
/*  83:119 */         setDead();
/*  84:120 */         return;
/*  85:    */       }
/*  86:122 */       this.posX = (this.setting.posX + this.settingLength * this.angle.xCoord);
/*  87:123 */       this.posY = (this.setting.posY + this.settingLength * this.angle.yCoord + this.settingYOffset);
/*  88:124 */       this.posZ = (this.setting.posZ + this.settingLength * this.angle.zCoord);
/*  89:125 */       setPosition(this.posX, this.posY, this.posZ);
/*  90:127 */       if (getLaserLength() < getMaxLaserLength()) {
/*  91:129 */         setLaserLength(getLaserLength() + 6.0D);
/*  92:    */       } else {
/*  93:133 */         setLaserLength(getMaxLaserLength());
/*  94:    */       }
/*  95:    */     }
/*  96:    */     else
/*  97:    */     {
/*  98:138 */       this.motionX = this.angle.xCoord;
/*  99:139 */       this.motionY = this.angle.yCoord;
/* 100:140 */       this.motionZ = this.angle.zCoord;
/* 101:141 */       this.posX += this.motionX;
/* 102:142 */       this.posY += this.motionY;
/* 103:143 */       this.posZ += this.motionZ;
/* 104:144 */       setPosition(this.posX, this.posY, this.posZ);
/* 105:    */       
/* 106:146 */       this.lastTime = 1;
/* 107:148 */       if (!this.worldObj.isRemote)
/* 108:    */       {
/* 109:150 */         setDead();
/* 110:151 */         return;
/* 111:    */       }
/* 112:    */     }
/* 113:    */   }
/* 114:    */   
/* 115:    */   public void hitCheck()
/* 116:    */   {
/* 117:169 */     double width = getShotSize();
/* 118:170 */     double length = getLaserLength();
/* 119:171 */     Vec3 vec = THShotLib.getVecFromAngle(this.rotationYaw, this.rotationPitch, length - getShotSize());
/* 120:172 */     Vec3 vec2 = THShotLib.getVecFromAngle(this.rotationYaw, this.rotationPitch, getShotSize());
/* 121:    */     
/* 122:174 */     Vec3 vec3d = Vec3.createVectorHelper(this.posX - vec2.xCoord, this.posY - vec2.yCoord, this.posZ + vec2.zCoord);
/* 123:    */     
/* 124:176 */     Vec3 vec3d1 = Vec3.createVectorHelper(this.posX - vec.xCoord, this.posY - vec.yCoord, this.posZ + vec.zCoord);
/* 125:    */     
/* 126:    */ 
/* 127:179 */     MovingObjectPosition movingObjectPosition = this.worldObj.func_147447_a(vec3d, vec3d1, false, true, true);
/* 128:180 */     vec3d = Vec3.createVectorHelper(this.posX - vec2.xCoord, this.posY - vec2.yCoord, this.posZ + vec2.zCoord);
/* 129:181 */     vec3d1 = Vec3.createVectorHelper(this.posX - vec.xCoord, this.posY - vec.yCoord, this.posZ + vec.zCoord);
/* 130:183 */     if (movingObjectPosition != null)
/* 131:    */     {
/* 132:186 */       vec3d1 = Vec3.createVectorHelper(movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
/* 133:    */       
/* 134:    */ 
/* 135:    */ 
/* 136:    */ 
/* 137:    */ 
/* 138:    */ 
/* 139:    */ 
/* 140:    */ 
/* 141:    */ 
/* 142:    */ 
/* 143:    */ 
/* 144:198 */       onImpact(movingObjectPosition);
/* 145:    */     }
/* 146:202 */     Entity entity = null;
/* 147:203 */     double d = 0.0D;
/* 148:    */     
/* 149:205 */     double xLength = this.motionX + Math.sin(this.rotationYaw / 180.0F * 3.141593F) * Math.cos(this.rotationPitch / 180.0F * 3.141593F) * getLaserLength();
/* 150:206 */     double yLength = this.motionY + Math.sin(this.rotationPitch / 180.0F * 3.141593F) * getLaserLength();
/* 151:207 */     double zLength = this.motionZ + Math.cos(this.rotationYaw / 180.0F * 3.141593F) * Math.cos(this.rotationPitch / 180.0F * 3.141593F) * getLaserLength();
/* 152:208 */     List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(xLength, yLength, zLength).expand(getLaserLength(), getLaserLength(), getLaserLength()));
/* 153:210 */     for (int j = 0; j < list.size(); j++)
/* 154:    */     {
/* 155:212 */       Entity entity1 = (Entity)list.get(j);
/* 156:214 */       if ((entity1.canBeCollidedWith()) && 
/* 157:215 */         (!entity1.isEntityEqual(this.user)) && 
/* 158:216 */         (!entity1.isEntityEqual(this.source)) && 
/* 159:217 */         (!hitCheckEx(entity1)) && (!(entity1 instanceof EntitySakuyaWatch)) && (!(entity1 instanceof EntityAnimal)) && (!(entity1 instanceof EntityVillager)) && ((entity1 instanceof EntityLivingBase)) && ((!(this.user instanceof EntityTHFairy)) || (!(entity1 instanceof EntityTHFairy))))
/* 160:    */       {
/* 161:227 */         AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(width, width, width);
/* 162:228 */         MovingObjectPosition movingObjectPosition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);
/* 163:230 */         if (movingObjectPosition1 != null)
/* 164:    */         {
/* 165:232 */           movingObjectPosition = new MovingObjectPosition(entity1);
/* 166:233 */           boolean creative = false;
/* 167:234 */           if ((movingObjectPosition != null) && (movingObjectPosition.entityHit != null) && ((movingObjectPosition.entityHit instanceof EntityPlayer)))
/* 168:    */           {
/* 169:236 */             EntityPlayer entityPlayer = (EntityPlayer)movingObjectPosition.entityHit;
/* 170:242 */             if ((entityPlayer.capabilities.disableDamage) || ((this.source instanceof EntityPlayer))) {
/* 171:245 */               movingObjectPosition = null;
/* 172:    */             }
/* 173:    */           }
/* 174:248 */           if (movingObjectPosition != null) {
/* 175:250 */             onImpact(movingObjectPosition);
/* 176:    */           }
/* 177:    */         }
/* 178:    */       }
/* 179:    */     }
/* 180:268 */     if (this.worldObj.isRemote) {
/* 181:271 */       setSize(getShotSize(), getShotSize());
/* 182:    */     }
/* 183:    */   }
/* 184:    */   
/* 185:    */   protected boolean onImpact(MovingObjectPosition movingObjectPosition)
/* 186:    */   {
/* 187:282 */     if (movingObjectPosition.entityHit != null)
/* 188:    */     {
/* 189:284 */       Entity hitEntity = movingObjectPosition.entityHit;
/* 190:287 */       if (hitEntity != null) {
/* 191:290 */         if (!(hitEntity instanceof EntityTHShot))
/* 192:    */         {
/* 193:293 */           entityHitSpecialProcess(hitEntity);
/* 194:295 */           if (((this.worldObj.isRemote) || 
/* 195:    */           
/* 196:297 */             (hitEntity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, this.user), getShotDamageWithDifficulty()))) || 
/* 197:    */             
/* 198:    */ 
/* 199:    */ 
/* 200:    */ 
/* 201:302 */             (hitEntity.hurtResistantTime == 0)) {
/* 202:304 */             this.shotDamage -= 1.0F;
/* 203:    */           }
/* 204:306 */           if (this.shotDamage <= 0.0F) {
/* 205:308 */             this.shotDamage = 1.0F;
/* 206:    */           }
/* 207:    */         }
/* 208:    */         else
/* 209:    */         {
/* 210:341 */           EntityTHShot entityTHShot = (EntityTHShot)hitEntity;
/* 211:343 */           if (this.user != entityTHShot.user)
/* 212:    */           {
/* 213:347 */             float shotDamageA = this.shotDamage;
/* 214:348 */             this.shotDamage -= entityTHShot.shotDamage;
/* 215:349 */             entityTHShot.shotDamage -= shotDamageA;
/* 216:350 */             if (this.shotDamage < 0.0F) {
/* 217:352 */               this.shotDamage = 0.0F;
/* 218:    */             }
/* 219:354 */             if (entityTHShot.shotDamage < 0.0F) {
/* 220:356 */               entityTHShot.shotDamage = 0.0F;
/* 221:    */             }
/* 222:    */           }
/* 223:    */         }
/* 224:    */       }
/* 225:    */     }
/* 226:    */     else
/* 227:    */     {
/* 228:364 */       blockHitSpecialProcess(movingObjectPosition);
/* 229:365 */       double hitDistance = getDistance(movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
/* 230:366 */       if (hitDistance > 0.3D) {
/* 231:368 */         if (!this.worldObj.isRemote) {
/* 232:370 */           setLaserLength(hitDistance);
/* 233:    */         }
/* 234:    */       }
/* 235:    */     }
/* 236:389 */     if (this.shotDamage <= 0.0F) {
/* 237:391 */       if (!this.worldObj.isRemote) {
/* 238:393 */         setDead();
/* 239:    */       }
/* 240:    */     }
/* 241:397 */     return true;
/* 242:    */   }
/* 243:    */   
/* 244:    */   public boolean attackEntityFrom(DamageSource damagesource, float i)
/* 245:    */   {
/* 246:405 */     return false;
/* 247:    */   }
/* 248:    */   
/* 249:    */   public void setLaserLength(double length)
/* 250:    */   {
/* 251:412 */     this.dataWatcher.updateObject(21, Integer.valueOf((int)(length * 10000.0D)));
/* 252:    */   }
/* 253:    */   
/* 254:    */   public double getLaserLength()
/* 255:    */   {
/* 256:417 */     return this.dataWatcher.getWatchableObjectInt(21) / 10000.0D;
/* 257:    */   }
/* 258:    */   
/* 259:    */   public void setMaxLaserLength(double length)
/* 260:    */   {
/* 261:423 */     this.dataWatcher.updateObject(22, Integer.valueOf((int)(length * 10000.0D)));
/* 262:    */   }
/* 263:    */   
/* 264:    */   public double getMaxLaserLength()
/* 265:    */   {
/* 266:428 */     return this.dataWatcher.getWatchableObjectInt(22) / 10000.0D;
/* 267:    */   }
/* 268:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.shot.EntityTHSetLaser
 * JD-Core Version:    0.7.0.1
 */