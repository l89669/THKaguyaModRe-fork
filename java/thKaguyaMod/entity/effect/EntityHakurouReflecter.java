/*   1:    */ package thKaguyaMod.entity.effect;
/*   2:    */ 
/*   3:    */ import java.util.List;
/*   4:    */ import java.util.Random;
/*   5:    */ import net.minecraft.entity.DataWatcher;
/*   6:    */ import net.minecraft.entity.Entity;
/*   7:    */ import net.minecraft.entity.player.EntityPlayer;
/*   8:    */ import net.minecraft.nbt.NBTTagCompound;
/*   9:    */ import net.minecraft.util.AxisAlignedBB;
/*  10:    */ import net.minecraft.util.DamageSource;
/*  11:    */ import net.minecraft.util.MovingObjectPosition;
/*  12:    */ import net.minecraft.util.Vec3;
/*  13:    */ import net.minecraft.world.World;
/*  14:    */ import thKaguyaMod.ShotData;
/*  15:    */ import thKaguyaMod.THShotLib;
/*  16:    */ import thKaguyaMod.entity.shot.EntityTHLaser;
/*  17:    */ import thKaguyaMod.entity.shot.EntityTHShot;
/*  18:    */ 
/*  19:    */ public class EntityHakurouReflecter
/*  20:    */   extends Entity
/*  21:    */ {
/*  22:    */   public EntityPlayer user;
/*  23:    */   public int damage;
/*  24:    */   public int count;
/*  25:    */   public double xVec;
/*  26:    */   public double yVec;
/*  27:    */   public double zVec;
/*  28:    */   
/*  29:    */   public EntityHakurouReflecter(World world)
/*  30:    */   {
/*  31: 32 */     super(world);
/*  32:    */     
/*  33: 34 */     setSize(1.5F, 1.5F);
/*  34: 35 */     this.yOffset = 0.0F;
/*  35: 36 */     this.count = 4;
/*  36:    */   }
/*  37:    */   
/*  38:    */   public EntityHakurouReflecter(World world, EntityPlayer entityPlayer)
/*  39:    */   {
/*  40: 42 */     this(world);
/*  41: 43 */     setSize(1.5F, 1.5F);
/*  42: 44 */     this.yOffset = 0.0F;
/*  43: 45 */     this.user = entityPlayer;
/*  44: 46 */     Vec3 vec3 = THShotLib.getVecFromAngle(this.user.rotationYaw, this.user.rotationPitch, 1.0D);
/*  45: 47 */     setPosition(this.user.posX + vec3.xCoord, this.user.posY + vec3.yCoord + this.user.getEyeHeight(), this.user.posZ + vec3.zCoord);
/*  46: 48 */     setRotation(-this.user.rotationYaw, this.user.rotationPitch);
/*  47: 49 */     Vec3 vec3_2 = THShotLib.getVecFromAngle(this.rotationYaw, this.rotationPitch, 0.18D);
/*  48: 50 */     this.xVec = vec3_2.xCoord;
/*  49: 51 */     this.zVec = vec3_2.zCoord;
/*  50: 52 */     this.yVec = vec3_2.yCoord;
/*  51: 53 */     this.count = 0;
/*  52:    */   }
/*  53:    */   
/*  54:    */   public boolean canBePushed()
/*  55:    */   {
/*  56: 87 */     return false;
/*  57:    */   }
/*  58:    */   
/*  59:    */   protected void entityInit()
/*  60:    */   {
/*  61: 94 */     this.dataWatcher.addObject(19, new Integer(0));
/*  62:    */   }
/*  63:    */   
/*  64:    */   public boolean canBeCollidedWith()
/*  65:    */   {
/*  66:101 */     return true;
/*  67:    */   }
/*  68:    */   
/*  69:    */   public void onUpdate()
/*  70:    */   {
/*  71:108 */     super.onUpdate();
/*  72:112 */     if (!this.worldObj.isRemote)
/*  73:    */     {
/*  74:114 */       if ((this.user == null) || (this.user.isDead)) {
/*  75:116 */         setDead();
/*  76:    */       }
/*  77:118 */       if (this.ticksExisted >= 20) {
/*  78:120 */         setDead();
/*  79:    */       }
/*  80:    */     }
/*  81:125 */     this.motionX = this.xVec;
/*  82:126 */     this.motionY = this.yVec;
/*  83:127 */     this.motionZ = this.zVec;
/*  84:    */     
/*  85:129 */     this.posX -= this.motionX;
/*  86:130 */     this.posY += this.motionY;
/*  87:131 */     this.posZ += this.motionZ;
/*  88:    */     
/*  89:    */ 
/*  90:    */ 
/*  91:    */ 
/*  92:    */ 
/*  93:    */ 
/*  94:    */ 
/*  95:    */ 
/*  96:    */ 
/*  97:141 */     double speedRate = (4 - this.count) * 0.2D;
/*  98:    */     
/*  99:    */ 
/* 100:    */ 
/* 101:    */ 
/* 102:    */ 
/* 103:    */ 
/* 104:    */ 
/* 105:149 */     double width = 0.5D;
/* 106:150 */     double length = 0.5D;
/* 107:    */     
/* 108:152 */     Vec3 vec3d = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
/* 109:    */     
/* 110:    */ 
/* 111:155 */     Vec3 vec3d1 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
/* 112:    */     
/* 113:    */ 
/* 114:158 */     MovingObjectPosition movingObjectPosition = this.worldObj.rayTraceBlocks(vec3d, vec3d1, false);
/* 115:    */     
/* 116:    */ 
/* 117:161 */     vec3d = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
/* 118:162 */     vec3d1 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
/* 119:165 */     if (movingObjectPosition != null)
/* 120:    */     {
/* 121:168 */       vec3d1 = Vec3.createVectorHelper(movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
/* 122:169 */       if (movingObjectPosition.entityHit == null) {
/* 123:171 */         if (!this.worldObj.isRemote) {
/* 124:173 */           setDead();
/* 125:    */         }
/* 126:    */       }
/* 127:    */     }
/* 128:179 */     Entity entity = null;
/* 129:180 */     double d = 0.0D;
/* 130:    */     
/* 131:182 */     double xLength = this.motionX - Math.sin(this.rotationYaw / 180.0F * 3.141593F) * Math.cos(this.rotationPitch / 180.0F * 3.141593F) * length;
/* 132:183 */     double yLength = this.motionY - Math.sin(this.rotationPitch / 180.0F * 3.141593F) * length;
/* 133:184 */     double zLength = this.motionZ + Math.cos(this.rotationYaw / 180.0F * 3.141593F) * Math.cos(this.rotationPitch / 180.0F * 3.141593F) * length;
/* 134:    */     
/* 135:186 */     xLength -= xLength;
/* 136:187 */     zLength -= zLength;
/* 137:188 */     List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(xLength, yLength, zLength).expand(width * 2.0D, width * 2.0D, width * 2.0D));
/* 138:190 */     for (int j = 0; j < list.size(); j++)
/* 139:    */     {
/* 140:192 */       Entity entity1 = (Entity)list.get(j);
/* 141:194 */       if (((entity1 instanceof EntityTHShot)) && (!(entity1 instanceof EntityTHLaser)))
/* 142:    */       {
/* 143:197 */         EntityTHShot shot = (EntityTHShot)entity1;
/* 144:    */         
/* 145:199 */         AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(shot.getShotSize(), shot.getShotSize(), shot.getShotSize());
/* 146:200 */         MovingObjectPosition movingObjectPosition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);
/* 147:    */         
/* 148:    */ 
/* 149:    */ 
/* 150:204 */         movingObjectPosition = new MovingObjectPosition(entity1);
/* 151:219 */         if ((this.user != null) && (this.user != shot.user))
/* 152:    */         {
/* 153:221 */           ShotData reflectShot = ShotData.shot(6, 5, THShotLib.SIZE[6], 3.0F, 0, 40, 0);
/* 154:222 */           THShotLib.createShot(this.user, THShotLib.pos_Entity(shot), THShotLib.angle(-this.rotationYaw, this.rotationPitch), 0.3D, 0.7D, 0.05D, reflectShot);
/* 155:224 */           if (!this.worldObj.isRemote) {
/* 156:226 */             entity1.setDead();
/* 157:    */           }
/* 158:229 */           this.worldObj.playSoundAtEntity(shot, "random.bow", 0.5F, 0.4F / (this.rand.nextFloat() * 4.0F + 0.8F));
/* 159:    */         }
/* 160:235 */         if (movingObjectPosition != null) {
/* 161:237 */           onImpact(movingObjectPosition);
/* 162:    */         }
/* 163:    */       }
/* 164:    */     }
/* 165:244 */     setPosition(this.posX, this.posY, this.posZ);
/* 166:245 */     setRotation(this.rotationYaw, this.rotationPitch);
/* 167:    */     
/* 168:247 */     this.count += 1;
/* 169:    */   }
/* 170:    */   
/* 171:    */   protected void onImpact(MovingObjectPosition movingObjectPosition)
/* 172:    */   {
/* 173:253 */     if ((!this.worldObj.isRemote) && (movingObjectPosition.entityHit != null))
/* 174:    */     {
/* 175:256 */       Entity hitEntity = movingObjectPosition.entityHit;
/* 176:259 */       if (hitEntity != null) {
/* 177:262 */         if (!(hitEntity instanceof EntityTHShot))
/* 178:    */         {
/* 179:271 */           double slope = (this.posX + this.xVec - (this.posX - this.xVec)) / (this.posZ + this.zVec - (this.posZ - this.zVec));
/* 180:272 */           double height = this.posX - slope * this.posZ;
/* 181:274 */           if (hitEntity.posX > hitEntity.posZ * slope + height)
/* 182:    */           {
/* 183:276 */             hitEntity.motionX = (-Math.sin(this.rotationYaw / 180.0D * 3.141592653589793D));
/* 184:277 */             hitEntity.motionZ = (-Math.cos(this.rotationYaw / 180.0D * 3.141592653589793D));
/* 185:    */           }
/* 186:    */           else
/* 187:    */           {
/* 188:281 */             hitEntity.motionX = Math.sin(this.rotationYaw / 180.0D * 3.141592653589793D);
/* 189:282 */             hitEntity.motionZ = Math.cos(this.rotationYaw / 180.0D * 3.141592653589793D);
/* 190:    */           }
/* 191:285 */           if (hitEntity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, this.user), 1.0F)) {}
/* 192:    */         }
/* 193:291 */         else if (!this.worldObj.isRemote)
/* 194:    */         {
/* 195:293 */           hitEntity.setDead();
/* 196:    */         }
/* 197:    */       }
/* 198:    */     }
/* 199:300 */     else if (!this.worldObj.isRemote)
/* 200:    */     {
/* 201:302 */       setDead();
/* 202:303 */       return;
/* 203:    */     }
/* 204:    */   }
/* 205:    */   
/* 206:    */   public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
/* 207:    */   {
/* 208:313 */     return true;
/* 209:    */   }
/* 210:    */   
/* 211:    */   public void setParasolMode(int mode)
/* 212:    */   {
/* 213:318 */     this.dataWatcher.updateObject(19, Integer.valueOf(mode));
/* 214:    */   }
/* 215:    */   
/* 216:    */   public int getParasolMode()
/* 217:    */   {
/* 218:323 */     return this.dataWatcher.getWatchableObjectInt(19);
/* 219:    */   }
/* 220:    */   
/* 221:    */   protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {}
/* 222:    */   
/* 223:    */   protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {}
/* 224:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.effect.EntityHakurouReflecter
 * JD-Core Version:    0.7.0.1
 */