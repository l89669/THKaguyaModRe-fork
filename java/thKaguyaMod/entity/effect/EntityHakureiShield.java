/*   1:    */ package thKaguyaMod.entity.effect;
/*   2:    */ 
/*   3:    */ import java.util.List;
/*   4:    */ import net.minecraft.entity.DataWatcher;
/*   5:    */ import net.minecraft.entity.Entity;
/*   6:    */ import net.minecraft.entity.player.EntityPlayer;
/*   7:    */ import net.minecraft.entity.player.PlayerCapabilities;
/*   8:    */ import net.minecraft.nbt.NBTTagCompound;
/*   9:    */ import net.minecraft.util.AxisAlignedBB;
/*  10:    */ import net.minecraft.util.DamageSource;
/*  11:    */ import net.minecraft.util.MovingObjectPosition;
/*  12:    */ import net.minecraft.util.Vec3;
/*  13:    */ import net.minecraft.world.World;
/*  14:    */ import thKaguyaMod.entity.item.EntitySakuyaWatch;
/*  15:    */ import thKaguyaMod.entity.shot.EntityTHShot;
/*  16:    */ 
/*  17:    */ public class EntityHakureiShield
/*  18:    */   extends Entity
/*  19:    */ {
/*  20:    */   public EntityPlayer user;
/*  21:    */   public int damage;
/*  22:    */   public int count;
/*  23:    */   public double xVec;
/*  24:    */   public double yVec;
/*  25:    */   public double zVec;
/*  26:    */   
/*  27:    */   public EntityHakureiShield(World world)
/*  28:    */   {
/*  29: 30 */     super(world);
/*  30:    */     
/*  31: 32 */     setSize(1.5F, 1.5F);
/*  32: 33 */     this.yOffset = 0.0F;
/*  33: 34 */     this.count = 4;
/*  34:    */   }
/*  35:    */   
/*  36:    */   public EntityHakureiShield(World world, EntityPlayer entityPlayer)
/*  37:    */   {
/*  38: 40 */     this(world);
/*  39: 41 */     setSize(1.5F, 1.5F);
/*  40: 42 */     this.yOffset = 0.0F;
/*  41: 43 */     this.user = entityPlayer;
/*  42: 44 */     this.prevPosX = this.user.posX;
/*  43: 45 */     this.prevPosY = this.user.posY;
/*  44: 46 */     this.prevPosZ = this.user.posZ;
/*  45: 47 */     setPosition(this.user.posX, this.user.posY + this.user.getEyeHeight() - 0.4D, this.user.posZ);
/*  46: 48 */     setRotation(-this.user.rotationYaw, 0.0F);
/*  47: 49 */     this.xVec = (-Math.sin(this.rotationYaw / 180.0D * 3.141592653589793D));
/*  48: 50 */     this.zVec = Math.cos(this.rotationYaw / 180.0D * 3.141592653589793D);
/*  49: 51 */     this.yVec = 0.0D;
/*  50: 52 */     this.count = 0;
/*  51:    */   }
/*  52:    */   
/*  53:    */   public boolean canBePushed()
/*  54:    */   {
/*  55: 86 */     return false;
/*  56:    */   }
/*  57:    */   
/*  58:    */   protected void entityInit()
/*  59:    */   {
/*  60: 92 */     this.dataWatcher.addObject(19, new Integer(0));
/*  61:    */   }
/*  62:    */   
/*  63:    */   protected boolean canTriggerWalking()
/*  64:    */   {
/*  65:102 */     return false;
/*  66:    */   }
/*  67:    */   
/*  68:    */   public boolean canBeCollidedWith()
/*  69:    */   {
/*  70:109 */     return true;
/*  71:    */   }
/*  72:    */   
/*  73:    */   public void onUpdate()
/*  74:    */   {
/*  75:116 */     super.onUpdate();
/*  76:120 */     if (!this.worldObj.isRemote)
/*  77:    */     {
/*  78:122 */       if ((this.user == null) || (this.user.isDead)) {
/*  79:124 */         setDead();
/*  80:    */       }
/*  81:126 */       if (this.count < 120) {}
/*  82:    */     }
/*  83:138 */     double speedRate = (4 - this.count) * 0.2D;
/*  84:140 */     if (this.count < 4)
/*  85:    */     {
/*  86:142 */       this.posX -= Math.sin(this.rotationYaw / 180.0D * 3.141592653589793D) * speedRate;
/*  87:143 */       this.posZ += Math.cos(this.rotationYaw / 180.0D * 3.141592653589793D) * speedRate;
/*  88:    */     }
/*  89:146 */     double width = 1.5D;
/*  90:147 */     double length = 3.0D;
/*  91:    */     
/*  92:149 */     Vec3 vec3d = Vec3.createVectorHelper(this.posX - this.xVec * width, this.posY - this.yVec * length / 2.0D, this.posZ - this.zVec * width);
/*  93:    */     
/*  94:    */ 
/*  95:152 */     Vec3 vec3d1 = Vec3.createVectorHelper(this.posX + this.motionX + this.xVec * width, this.posY + this.yVec * length / 2.0D, this.posZ + this.motionZ + this.zVec * width);
/*  96:    */     
/*  97:    */ 
/*  98:155 */     MovingObjectPosition movingObjectPosition = this.worldObj.rayTraceBlocks(vec3d, vec3d1, true);
/*  99:    */     
/* 100:    */ 
/* 101:158 */     vec3d = Vec3.createVectorHelper(this.posX - this.xVec * width, this.posY - this.yVec * length / 2.0D, this.posZ - this.zVec * width);
/* 102:159 */     vec3d1 = Vec3.createVectorHelper(this.posX + this.motionX + this.xVec * width, this.posY + this.yVec * length / 2.0D, this.posZ + this.motionZ + this.zVec * width);
/* 103:162 */     if (movingObjectPosition != null)
/* 104:    */     {
/* 105:165 */       vec3d1 = Vec3.createVectorHelper(movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
/* 106:166 */       if (movingObjectPosition.entityHit == null) {
/* 107:168 */         if (!this.worldObj.isRemote) {
/* 108:170 */           setDead();
/* 109:    */         }
/* 110:    */       }
/* 111:    */     }
/* 112:176 */     Entity entity = null;
/* 113:177 */     double d = 0.0D;
/* 114:    */     
/* 115:179 */     double xLength = this.motionX - Math.sin(this.rotationYaw / 180.0F * 3.141593F) * Math.cos(this.rotationPitch / 180.0F * 3.141593F) * length;
/* 116:180 */     double yLength = this.motionY + Math.sin(this.rotationPitch / 180.0F * 3.141593F) * length;
/* 117:181 */     double zLength = this.motionZ + Math.cos(this.rotationYaw / 180.0F * 3.141593F) * Math.cos(this.rotationPitch / 180.0F * 3.141593F) * length;
/* 118:    */     
/* 119:183 */     xLength -= xLength;
/* 120:184 */     zLength -= zLength;
/* 121:185 */     List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(xLength, yLength, zLength).expand(width * 2.0D, length, 0.01D));
/* 122:187 */     for (int j = 0; j < list.size(); j++)
/* 123:    */     {
/* 124:189 */       Entity entity1 = (Entity)list.get(j);
/* 125:191 */       if (!(entity1 instanceof EntitySakuyaWatch))
/* 126:    */       {
/* 127:203 */         AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(width, width, width);
/* 128:204 */         MovingObjectPosition movingObjectPosition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);
/* 129:206 */         if (movingObjectPosition1 != null)
/* 130:    */         {
/* 131:208 */           movingObjectPosition = new MovingObjectPosition(entity1);
/* 132:209 */           boolean creative = false;
/* 133:210 */           if ((movingObjectPosition != null) && (movingObjectPosition.entityHit != null) && ((movingObjectPosition.entityHit instanceof EntityPlayer)))
/* 134:    */           {
/* 135:212 */             EntityPlayer entityPlayer = (EntityPlayer)movingObjectPosition.entityHit;
/* 136:218 */             if ((entityPlayer.capabilities.disableDamage) || ((this.user instanceof EntityPlayer))) {
/* 137:221 */               movingObjectPosition = null;
/* 138:    */             }
/* 139:    */           }
/* 140:224 */           if (movingObjectPosition != null) {
/* 141:226 */             onImpact(movingObjectPosition);
/* 142:    */           }
/* 143:    */         }
/* 144:    */       }
/* 145:    */     }
/* 146:233 */     setPosition(this.posX, this.posY, this.posZ);
/* 147:234 */     setRotation(this.rotationYaw, this.rotationPitch);
/* 148:    */     
/* 149:236 */     this.count += 1;
/* 150:    */   }
/* 151:    */   
/* 152:    */   protected void onImpact(MovingObjectPosition movingObjectPosition)
/* 153:    */   {
/* 154:242 */     if ((!this.worldObj.isRemote) && (movingObjectPosition.entityHit != null))
/* 155:    */     {
/* 156:245 */       Entity hitEntity = movingObjectPosition.entityHit;
/* 157:248 */       if (hitEntity != null) {
/* 158:251 */         if (!(hitEntity instanceof EntityTHShot))
/* 159:    */         {
/* 160:260 */           double slope = (this.posX + this.xVec - (this.posX - this.xVec)) / (this.posZ + this.zVec - (this.posZ - this.zVec));
/* 161:261 */           double height = this.posX - slope * this.posZ;
/* 162:263 */           if (hitEntity.posX > hitEntity.posZ * slope + height)
/* 163:    */           {
/* 164:265 */             hitEntity.motionX = (-Math.sin(this.rotationYaw / 180.0D * 3.141592653589793D));
/* 165:266 */             hitEntity.motionZ = (-Math.cos(this.rotationYaw / 180.0D * 3.141592653589793D));
/* 166:    */           }
/* 167:    */           else
/* 168:    */           {
/* 169:270 */             hitEntity.motionX = Math.sin(this.rotationYaw / 180.0D * 3.141592653589793D);
/* 170:271 */             hitEntity.motionZ = Math.cos(this.rotationYaw / 180.0D * 3.141592653589793D);
/* 171:    */           }
/* 172:274 */           if (hitEntity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, this.user), 1.0F)) {}
/* 173:    */         }
/* 174:280 */         else if (!this.worldObj.isRemote)
/* 175:    */         {
/* 176:282 */           hitEntity.setDead();
/* 177:    */         }
/* 178:    */       }
/* 179:    */     }
/* 180:289 */     else if (!this.worldObj.isRemote)
/* 181:    */     {
/* 182:291 */       setDead();
/* 183:292 */       return;
/* 184:    */     }
/* 185:    */   }
/* 186:    */   
/* 187:    */   public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
/* 188:    */   {
/* 189:302 */     return true;
/* 190:    */   }
/* 191:    */   
/* 192:    */   public void setParasolMode(int mode)
/* 193:    */   {
/* 194:307 */     this.dataWatcher.updateObject(19, Integer.valueOf(mode));
/* 195:    */   }
/* 196:    */   
/* 197:    */   public int getParasolMode()
/* 198:    */   {
/* 199:312 */     return this.dataWatcher.getWatchableObjectInt(19);
/* 200:    */   }
/* 201:    */   
/* 202:    */   protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {}
/* 203:    */   
/* 204:    */   protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {}
/* 205:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.effect.EntityHakureiShield
 * JD-Core Version:    0.7.0.1
 */