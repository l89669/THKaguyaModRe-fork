/*   1:    */ package thKaguyaMod.entity.effect;
/*   2:    */ 
/*   3:    */ import net.minecraft.entity.DataWatcher;
/*   4:    */ import net.minecraft.entity.Entity;
/*   5:    */ import net.minecraft.entity.EntityLivingBase;
/*   6:    */ import net.minecraft.entity.player.EntityPlayer;
/*   7:    */ import net.minecraft.nbt.NBTTagCompound;
/*   8:    */ import net.minecraft.world.World;
/*   9:    */ 
/*  10:    */ public class EntitySpellCardCircle
/*  11:    */   extends Entity
/*  12:    */ {
/*  13:    */   public EntityLivingBase user;
/*  14:    */   private int type;
/*  15:    */   private int count;
/*  16:    */   private int endTime;
/*  17:    */   protected int lastTime;
/*  18:    */   
/*  19:    */   public EntitySpellCardCircle(World world)
/*  20:    */   {
/*  21: 21 */     super(world);
/*  22: 22 */     this.ignoreFrustumCheck = true;
/*  23: 23 */     setSize(0.5F, 0.5F);
/*  24: 24 */     this.lastTime = 0;
/*  25: 25 */     setAnimationCount(0);
/*  26:    */   }
/*  27:    */   
/*  28:    */   public EntitySpellCardCircle(World world, EntityLivingBase entityLiving, int type, int end)
/*  29:    */   {
/*  30: 30 */     this(world);
/*  31:    */     
/*  32: 32 */     this.user = entityLiving;
/*  33: 33 */     setCircleType(type);
/*  34: 34 */     this.count = 0;
/*  35: 35 */     setEndTime(end);
/*  36:    */     
/*  37:    */ 
/*  38:    */ 
/*  39:    */ 
/*  40: 40 */     this.posX = this.user.posX;
/*  41: 41 */     if ((entityLiving instanceof EntityPlayer)) {
/*  42: 43 */       this.posY = (this.user.posY + 0.1D);
/*  43:    */     } else {
/*  44: 47 */       this.posY = (this.user.posY + this.user.height / 2.0D);
/*  45:    */     }
/*  46: 49 */     this.posZ = this.user.posZ;
/*  47: 50 */     setPositionAndRotation(this.posX, this.posY, this.posZ, this.user.rotationYaw, this.user.rotationPitch);
/*  48:    */   }
/*  49:    */   
/*  50:    */   protected void entityInit()
/*  51:    */   {
/*  52: 59 */     this.dataWatcher.addObject(19, new Float(0.0F));
/*  53: 60 */     this.dataWatcher.addObject(20, new Integer(0));
/*  54: 61 */     this.dataWatcher.addObject(21, new Integer(0));
/*  55: 62 */     this.dataWatcher.addObject(22, new Integer(0));
/*  56:    */   }
/*  57:    */   
/*  58:    */   public boolean canBeCollidedWith()
/*  59:    */   {
/*  60: 72 */     return false;
/*  61:    */   }
/*  62:    */   
/*  63:    */   public void onUpdate()
/*  64:    */   {
/*  65: 82 */     if ((!this.worldObj.isRemote) && (this.user == null))
/*  66:    */     {
/*  67: 85 */       setDead();
/*  68: 86 */       return;
/*  69:    */     }
/*  70: 88 */     super.onUpdate();
/*  71: 90 */     if (this.ticksExisted <= this.lastTime) {
/*  72: 92 */       return;
/*  73:    */     }
/*  74: 97 */     if (!this.worldObj.isRemote) {
/*  75: 99 */       setAnimationCount(this.lastTime);
/*  76:    */     }
/*  77:103 */     if ((getEndTime() < this.ticksExisted) && (getEndTime() >= 0)) {
/*  78:105 */       if (!this.worldObj.isRemote) {
/*  79:107 */         setDead();
/*  80:    */       }
/*  81:    */     }
/*  82:114 */     if ((this.user == null) || 
/*  83:    */     
/*  84:    */ 
/*  85:    */ 
/*  86:    */ 
/*  87:119 */       (getAnimationCount() < 5))
/*  88:    */     {
/*  89:121 */       setCircleSize(getAnimationCount() / 5.0F);
/*  90:    */     }
/*  91:    */     else
/*  92:    */     {
/*  93:125 */       float endTime2 = getEndTime();
/*  94:126 */       if (getCircleType() < 16) {
/*  95:128 */         setCircleSize((endTime2 - getAnimationCount()) / endTime2);
/*  96:    */       }
/*  97:    */     }
/*  98:145 */     if (this.user != null)
/*  99:    */     {
/* 100:147 */       this.posX = this.user.posX;
/* 101:148 */       if ((this.user instanceof EntityPlayer)) {
/* 102:150 */         this.posY = (this.user.posY + 0.1D);
/* 103:    */       } else {
/* 104:154 */         this.posY = (this.user.posY + this.user.height / 2.0D);
/* 105:    */       }
/* 106:156 */       this.posZ = this.user.posZ;
/* 107:157 */       this.rotationYaw = this.user.rotationYawHead;
/* 108:158 */       this.rotationPitch = this.user.rotationPitch;
/* 109:159 */       setPosition(this.posX, this.posY, this.posZ);
/* 110:    */     }
/* 111:162 */     if (this.rotationYaw > 180.0F) {
/* 112:162 */       this.rotationYaw -= 360.0F;
/* 113:    */     }
/* 114:163 */     if (this.rotationYaw < -180.0F) {
/* 115:163 */       this.rotationYaw += 360.0F;
/* 116:    */     }
/* 117:164 */     if (this.rotationPitch > 180.0F) {
/* 118:164 */       this.rotationPitch -= 360.0F;
/* 119:    */     }
/* 120:165 */     if (this.rotationPitch < -180.0F) {
/* 121:165 */       this.rotationPitch += 360.0F;
/* 122:    */     }
/* 123:167 */     setRotation(this.rotationYaw, this.rotationPitch);
/* 124:169 */     if (this.ticksExisted > this.lastTime) {
/* 125:172 */       this.lastTime = this.ticksExisted;
/* 126:    */     }
/* 127:    */   }
/* 128:    */   
/* 129:    */   public void setCircleSize(float size)
/* 130:    */   {
/* 131:182 */     this.dataWatcher.updateObject(19, Float.valueOf(size));
/* 132:    */   }
/* 133:    */   
/* 134:    */   public float getCircleSize()
/* 135:    */   {
/* 136:191 */     return this.dataWatcher.getWatchableObjectFloat(19);
/* 137:    */   }
/* 138:    */   
/* 139:    */   public void setCircleType(int type)
/* 140:    */   {
/* 141:200 */     this.dataWatcher.updateObject(20, Integer.valueOf(type));
/* 142:    */   }
/* 143:    */   
/* 144:    */   public int getCircleType()
/* 145:    */   {
/* 146:209 */     return this.dataWatcher.getWatchableObjectInt(20);
/* 147:    */   }
/* 148:    */   
/* 149:    */   public void setEndTime(int time)
/* 150:    */   {
/* 151:218 */     this.dataWatcher.updateObject(21, Integer.valueOf(time));
/* 152:    */   }
/* 153:    */   
/* 154:    */   public int getEndTime()
/* 155:    */   {
/* 156:227 */     return this.dataWatcher.getWatchableObjectInt(21);
/* 157:    */   }
/* 158:    */   
/* 159:    */   public void setAnimationCount(int time)
/* 160:    */   {
/* 161:236 */     this.dataWatcher.updateObject(22, Integer.valueOf(time));
/* 162:    */   }
/* 163:    */   
/* 164:    */   public int getAnimationCount()
/* 165:    */   {
/* 166:245 */     return this.dataWatcher.getWatchableObjectInt(22);
/* 167:    */   }
/* 168:    */   
/* 169:    */   protected void writeEntityToNBT(NBTTagCompound nbtTagCompound) {}
/* 170:    */   
/* 171:    */   protected void readEntityFromNBT(NBTTagCompound nbtTagCompound) {}
/* 172:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.effect.EntitySpellCardCircle
 * JD-Core Version:    0.7.0.1
 */