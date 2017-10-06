/*   1:    */ package thKaguyaMod.entity.shot;
/*   2:    */ 
/*   3:    */ import net.minecraft.entity.Entity;
/*   4:    */ import net.minecraft.entity.EntityLivingBase;
/*   5:    */ import net.minecraft.entity.player.EntityPlayer;
/*   6:    */ import net.minecraft.entity.player.InventoryPlayer;
/*   7:    */ import net.minecraft.item.ItemStack;
/*   8:    */ import net.minecraft.util.MovingObjectPosition;
/*   9:    */ import net.minecraft.util.Vec3;
/*  10:    */ import net.minecraft.world.World;
/*  11:    */ import thKaguyaMod.THShotLib;
/*  12:    */ import thKaguyaMod.init.THKaguyaItems;
/*  13:    */ 
/*  14:    */ public class EntityOnmyoudama
/*  15:    */   extends EntityTHShot
/*  16:    */ {
/*  17:    */   private boolean shootingFlag;
/*  18:    */   private boolean userHit;
/*  19:    */   private int hitTimer;
/*  20:    */   
/*  21:    */   public EntityOnmyoudama(World world)
/*  22:    */   {
/*  23: 22 */     super(world);
/*  24:    */   }
/*  25:    */   
/*  26:    */   public EntityOnmyoudama(World world, EntityLivingBase user, Entity source, Vec3 pos, Vec3 angle, float slope, Vec3 rotate, float rotationSpeed, int rotationEnd, double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity, int color, float size, float damage, int delay, int end, int special)
/*  27:    */   {
/*  28: 32 */     super(world, user, source, pos, angle, slope, rotate, rotationSpeed, rotationEnd, firstSpeed, limitSpeed, acceleration, gravity, color, size, damage, delay, end, special);
/*  29:    */     
/*  30: 34 */     this.shootingFlag = false;
/*  31: 35 */     this.userHit = false;
/*  32: 36 */     this.hitTimer = 0;
/*  33:    */   }
/*  34:    */   
/*  35:    */   public void onUpdate()
/*  36:    */   {
/*  37: 43 */     if (this.shootingFlag) {
/*  38: 45 */       this.shotAcceleration = 0.0D;
/*  39:    */     }
/*  40: 47 */     if (this.user != null)
/*  41:    */     {
/*  42: 49 */       if ((this.user instanceof EntityPlayer))
/*  43:    */       {
/*  44: 51 */         EntityPlayer player = (EntityPlayer)this.user;
/*  45: 52 */         if ((player.isUsingItem()) && (!this.shootingFlag))
/*  46:    */         {
/*  47: 54 */           Vec3 vec3 = THShotLib.getVecFromAngle(-this.rotationYaw, -this.rotationPitch, 0.6D);
/*  48:    */           
/*  49:    */ 
/*  50:    */ 
/*  51:    */ 
/*  52:    */ 
/*  53:    */ 
/*  54:    */ 
/*  55: 62 */           setPositionAndRotation(this.user.posX + vec3.xCoord * 2.0D, THShotLib.getPosYFromEye(this.user, -0.1D) + vec3.yCoord, this.user.posZ + vec3.zCoord, -this.user.rotationYaw, -this.user.rotationPitch);
/*  56: 65 */           if (getShotSize() < 3.0F) {
/*  57: 67 */             setShotSize(getShotSize() + 0.06F);
/*  58:    */           }
/*  59: 69 */           if (player.inventory.getCurrentItem() != null)
/*  60:    */           {
/*  61: 71 */             if (player.inventory.getCurrentItem().getItem() != THKaguyaItems.hakurei_miko_stick) {
/*  62: 74 */               if (!this.worldObj.isRemote) {
/*  63: 77 */                 setDead();
/*  64:    */               }
/*  65:    */             }
/*  66:    */           }
/*  67:    */           else
/*  68:    */           {
/*  69: 84 */             if (!this.worldObj.isRemote) {
/*  70: 87 */               setDead();
/*  71:    */             }
/*  72: 89 */             return;
/*  73:    */           }
/*  74: 91 */           return;
/*  75:    */         }
/*  76: 93 */         if (!this.shootingFlag)
/*  77:    */         {
/*  78: 96 */           if (player.inventory.getCurrentItem() != null)
/*  79:    */           {
/*  80: 98 */             if (player.inventory.getCurrentItem().getItem() != THKaguyaItems.hakurei_miko_stick) {
/*  81:101 */               if (!this.worldObj.isRemote) {
/*  82:104 */                 setDead();
/*  83:    */               }
/*  84:    */             }
/*  85:    */           }
/*  86:    */           else
/*  87:    */           {
/*  88:111 */             if (!this.worldObj.isRemote) {
/*  89:114 */               setDead();
/*  90:    */             }
/*  91:116 */             return;
/*  92:    */           }
/*  93:118 */           this.ticksExisted = 1;
/*  94:119 */           this.shotSpeed = 0.5D;
/*  95:120 */           setVector();
/*  96:121 */           this.shotAcceleration = 1.0D;
/*  97:122 */           setSize(this.shotSize, this.shotSize);
/*  98:123 */           this.shootingFlag = true;
/*  99:    */         }
/* 100:    */       }
/* 101:    */     }
/* 102:130 */     else if (!this.worldObj.isRemote)
/* 103:    */     {
/* 104:132 */       setDead();
/* 105:133 */       return;
/* 106:    */     }
/* 107:137 */     this.shotDamage = (getShotSize() * 6.0F);
/* 108:    */     
/* 109:139 */     super.onUpdate();
/* 110:    */     
/* 111:    */ 
/* 112:142 */     Vec3 supVec3d1 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
/* 113:    */     
/* 114:144 */     Vec3 supVec3d2 = Vec3.createVectorHelper(this.posX, this.posY + getShotSize(), this.posZ);
/* 115:    */     
/* 116:    */ 
/* 117:147 */     MovingObjectPosition movingObjectPosition = this.worldObj.func_147447_a(supVec3d1, supVec3d2, false, true, true);
/* 118:149 */     if ((movingObjectPosition != null) && (movingObjectPosition.entityHit == null))
/* 119:    */     {
/* 120:151 */       double diveY = movingObjectPosition.blockY - (this.posY + getShotSize());
/* 121:152 */       this.posY = (movingObjectPosition.blockY + getShotSize() + 0.01D + diveY);
/* 122:153 */       setPosition(this.posX, this.posY, this.posZ);
/* 123:154 */       this.rotationPitch = ((float)(this.rotationPitch * -1.0D));
/* 124:155 */       setVector();
/* 125:156 */       this.motionY *= 0.9D;
/* 126:    */     }
/* 127:159 */     if (!this.userHit)
/* 128:    */     {
/* 129:162 */       Vec3 vec3d = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
/* 130:    */       
/* 131:164 */       Vec3 vec3d2 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
/* 132:    */       
/* 133:    */ 
/* 134:167 */       movingObjectPosition = this.worldObj.rayTraceBlocks(supVec3d1, supVec3d2, true);
/* 135:168 */       if (!hitUserCheck(movingObjectPosition, vec3d, vec3d2))
/* 136:    */       {
/* 137:170 */         this.hitTimer += 1;
/* 138:171 */         if (this.hitTimer >= 2) {
/* 139:173 */           this.userHit = true;
/* 140:    */         }
/* 141:    */       }
/* 142:    */     }
/* 143:    */   }
/* 144:    */   
/* 145:    */   public boolean userHitCheck(Entity entity)
/* 146:    */   {
/* 147:181 */     return this.userHit;
/* 148:    */   }
/* 149:    */   
/* 150:    */   protected boolean isUserHit()
/* 151:    */   {
/* 152:189 */     return true;
/* 153:    */   }
/* 154:    */   
/* 155:    */   public boolean entityHitSpecialProcess(Entity hitEntity)
/* 156:    */   {
/* 157:196 */     this.rotationYaw *= -1.0F;
/* 158:197 */     this.rotationPitch *= -1.0F;
/* 159:198 */     setRotation(this.rotationYaw, this.rotationPitch);
/* 160:199 */     setVector();
/* 161:200 */     this.motionX *= 0.800000011920929D;
/* 162:201 */     this.motionY *= 0.800000011920929D;
/* 163:202 */     this.motionZ *= 0.800000011920929D;
/* 164:    */     
/* 165:204 */     return false;
/* 166:    */   }
/* 167:    */   
/* 168:    */   protected boolean isReturnableShot()
/* 169:    */   {
/* 170:212 */     return true;
/* 171:    */   }
/* 172:    */   
/* 173:    */   public boolean hitCheckEx(Entity entity)
/* 174:    */   {
/* 175:219 */     return false;
/* 176:    */   }
/* 177:    */   
/* 178:    */   public int getShotStrength()
/* 179:    */   {
/* 180:225 */     return 10;
/* 181:    */   }
/* 182:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.shot.EntityOnmyoudama
 * JD-Core Version:    0.7.0.1
 */