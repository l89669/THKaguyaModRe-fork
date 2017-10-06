/*   1:    */ package thKaguyaMod.entity.effect;
/*   2:    */ 
/*   3:    */ import net.minecraft.entity.DataWatcher;
/*   4:    */ import net.minecraft.entity.Entity;
/*   5:    */ import net.minecraft.entity.player.EntityPlayer;
/*   6:    */ import net.minecraft.nbt.NBTTagCompound;
/*   7:    */ import net.minecraft.util.Vec3;
/*   8:    */ import net.minecraft.world.World;
/*   9:    */ import thKaguyaMod.THShotLib;
/*  10:    */ 
/*  11:    */ public class EntityMiracleCircle
/*  12:    */   extends Entity
/*  13:    */ {
/*  14:    */   public EntityPlayer user;
/*  15:    */   private int color;
/*  16:    */   public int count;
/*  17:    */   public float turnAngle;
/*  18:    */   public int befUseCount;
/*  19:    */   
/*  20:    */   public EntityMiracleCircle(World world)
/*  21:    */   {
/*  22: 22 */     super(world);
/*  23: 23 */     setSize(2.0F, 0.0F);
/*  24: 24 */     this.yOffset = 0.0F;
/*  25:    */   }
/*  26:    */   
/*  27:    */   public EntityMiracleCircle(World world, EntityPlayer entityPlayer, int cl)
/*  28:    */   {
/*  29: 29 */     this(world);
/*  30:    */     
/*  31: 31 */     this.user = entityPlayer;
/*  32: 32 */     Vec3 vec3 = THShotLib.getRotationVectorFromAngle(this.user.rotationYaw, this.user.rotationPitch, this.turnAngle, 3.0D);
/*  33: 33 */     this.posX = (this.user.posX + vec3.xCoord);
/*  34: 34 */     this.posY = (this.user.posY + vec3.yCoord + this.user.getEyeHeight());
/*  35: 35 */     this.posZ = (this.user.posZ + vec3.zCoord);
/*  36: 36 */     setPosition(this.posX, this.posY, this.posZ);
/*  37: 37 */     setRotation(entityPlayer.rotationYaw, 0.0F);
/*  38: 38 */     this.color = cl;
/*  39: 39 */     setColor(this.color);
/*  40: 40 */     this.count = 0;
/*  41: 41 */     this.befUseCount = 0;
/*  42: 42 */     this.turnAngle = 0.0F;
/*  43:    */   }
/*  44:    */   
/*  45:    */   protected void entityInit()
/*  46:    */   {
/*  47: 49 */     this.dataWatcher.addObject(19, new Integer(0));
/*  48:    */   }
/*  49:    */   
/*  50:    */   public void onUpdate()
/*  51:    */   {
/*  52: 58 */     if ((!this.worldObj.isRemote) && (this.user == null)) {
/*  53: 60 */       setDead();
/*  54:    */     }
/*  55: 62 */     super.onUpdate();
/*  56: 63 */     if (this.user != null) {
/*  57: 66 */       if (!this.user.isUsingItem()) {
/*  58: 68 */         if (((-this.befUseCount < 250) || (this.user.experienceLevel < 40)) || 
/*  59:    */         
/*  60:    */ 
/*  61:    */ 
/*  62:    */ 
/*  63:    */ 
/*  64:    */ 
/*  65:    */ 
/*  66: 76 */           (!this.worldObj.isRemote)) {
/*  67: 78 */           setDead();
/*  68:    */         }
/*  69:    */       }
/*  70:    */     }
/*  71: 82 */     this.count += 1;
/*  72: 83 */     if ((this.count == 30) && (getColor() < 4)) {
/*  73: 85 */       if (this.user != null)
/*  74:    */       {
/*  75: 87 */         EntityMiracleCircle entityMiracle = new EntityMiracleCircle(this.worldObj, this.user, getColor() + 1);
/*  76: 88 */         if (!this.worldObj.isRemote) {
/*  77: 90 */           this.worldObj.spawnEntityInWorld(entityMiracle);
/*  78:    */         }
/*  79:    */       }
/*  80:    */     }
/*  81: 95 */     if (this.user != null)
/*  82:    */     {
/*  83: 97 */       this.prevPosX = this.posX;
/*  84: 98 */       this.prevPosY = this.posY;
/*  85: 99 */       this.prevPosZ = this.posZ;
/*  86:    */       
/*  87:    */ 
/*  88:    */ 
/*  89:    */ 
/*  90:104 */       Vec3 vec3 = THShotLib.getRotationVectorFromAngle(this.user.rotationYaw, this.user.rotationPitch, this.turnAngle, 3.0D);
/*  91:    */       
/*  92:    */ 
/*  93:    */ 
/*  94:108 */       this.posX = (this.user.posX + vec3.xCoord);
/*  95:109 */       this.posY = (this.user.posY + vec3.yCoord + this.user.getEyeHeight());
/*  96:110 */       this.posZ = (this.user.posZ + vec3.zCoord);
/*  97:111 */       this.rotationYaw = this.user.rotationYaw;
/*  98:112 */       this.rotationPitch = this.user.rotationPitch;
/*  99:113 */       this.befUseCount = (getColor() * 30 + this.count);
/* 100:    */     }
/* 101:115 */     setPosition(this.posX, this.posY, this.posZ);
/* 102:117 */     if (this.count > 30) {
/* 103:119 */       this.turnAngle += 2.4F;
/* 104:    */     }
/* 105:123 */     if (this.rotationYaw > 180.0F) {
/* 106:123 */       this.rotationYaw -= 360.0F;
/* 107:    */     }
/* 108:124 */     if (this.rotationYaw < -180.0F) {
/* 109:124 */       this.rotationYaw += 360.0F;
/* 110:    */     }
/* 111:125 */     if (this.rotationPitch > 180.0F) {
/* 112:125 */       this.rotationPitch -= 360.0F;
/* 113:    */     }
/* 114:126 */     if (this.rotationPitch < -180.0F) {
/* 115:126 */       this.rotationPitch += 360.0F;
/* 116:    */     }
/* 117:128 */     setRotation(this.rotationYaw, this.rotationPitch);
/* 118:    */   }
/* 119:    */   
/* 120:    */   public void setColor(int color)
/* 121:    */   {
/* 122:137 */     this.dataWatcher.updateObject(19, Integer.valueOf(color));
/* 123:    */   }
/* 124:    */   
/* 125:    */   public int getColor()
/* 126:    */   {
/* 127:146 */     return this.dataWatcher.getWatchableObjectInt(19);
/* 128:    */   }
/* 129:    */   
/* 130:    */   public int getNumberOfDrewLine()
/* 131:    */   {
/* 132:155 */     int time = this.count;
/* 133:156 */     if (time > 30) {
/* 134:158 */       return 5;
/* 135:    */     }
/* 136:162 */     return time / 6 + 1;
/* 137:    */   }
/* 138:    */   
/* 139:    */   public int getLastLineTime()
/* 140:    */   {
/* 141:172 */     int time = this.count;
/* 142:173 */     if (time > 30) {
/* 143:175 */       return 10;
/* 144:    */     }
/* 145:179 */     return time % 6;
/* 146:    */   }
/* 147:    */   
/* 148:    */   protected void writeEntityToNBT(NBTTagCompound nbtTagCompound) {}
/* 149:    */   
/* 150:    */   protected void readEntityFromNBT(NBTTagCompound nbtTagCompound) {}
/* 151:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.effect.EntityMiracleCircle
 * JD-Core Version:    0.7.0.1
 */