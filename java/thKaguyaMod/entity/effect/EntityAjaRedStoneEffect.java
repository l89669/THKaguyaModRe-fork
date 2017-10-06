/*   1:    */ package thKaguyaMod.entity.effect;
/*   2:    */ 
/*   3:    */ import net.minecraft.entity.DataWatcher;
/*   4:    */ import net.minecraft.entity.Entity;
/*   5:    */ import net.minecraft.entity.EntityLivingBase;
/*   6:    */ import net.minecraft.entity.player.EntityPlayer;
/*   7:    */ import net.minecraft.nbt.NBTTagCompound;
/*   8:    */ import net.minecraft.world.World;
/*   9:    */ import thKaguyaMod.LaserData;
/*  10:    */ import thKaguyaMod.THShotLib;
/*  11:    */ 
/*  12:    */ public class EntityAjaRedStoneEffect
/*  13:    */   extends Entity
/*  14:    */ {
/*  15:    */   public EntityLivingBase user;
/*  16:    */   public int lightLevel;
/*  17: 19 */   private double length = 1.2D;
/*  18:    */   
/*  19:    */   public EntityAjaRedStoneEffect(World world)
/*  20:    */   {
/*  21: 23 */     super(world);
/*  22: 24 */     setSize(2.0F, 0.0F);
/*  23: 25 */     this.yOffset = 0.0F;
/*  24: 26 */     this.lightLevel = 0;
/*  25: 27 */     setLightPower(0);
/*  26:    */   }
/*  27:    */   
/*  28:    */   public EntityAjaRedStoneEffect(World world, EntityLivingBase entityLiving)
/*  29:    */   {
/*  30: 32 */     this(world);
/*  31:    */     
/*  32: 34 */     this.user = entityLiving;
/*  33: 35 */     this.posX = (this.user.posX - Math.sin(this.user.rotationYaw / 180.0F * 3.141593F) * Math.cos(this.user.rotationPitch / 180.0F * 3.141593F) * this.length);
/*  34: 36 */     this.posY = (this.user.posY - Math.sin(this.user.rotationPitch / 180.0F * 3.141593F) * this.length + this.user.getEyeHeight());
/*  35: 37 */     this.posZ = (this.user.posZ + Math.cos(this.user.rotationYaw / 180.0F * 3.141593F) * Math.cos(this.user.rotationPitch / 180.0F * 3.141593F) * this.length);
/*  36: 38 */     setPositionAndRotation(this.posX, this.posY, this.posZ, this.user.rotationYaw, this.user.rotationPitch);
/*  37:    */   }
/*  38:    */   
/*  39:    */   protected void entityInit()
/*  40:    */   {
/*  41: 45 */     this.dataWatcher.addObject(19, new Integer(0));
/*  42:    */   }
/*  43:    */   
/*  44:    */   public boolean canBeCollidedWith()
/*  45:    */   {
/*  46: 55 */     return false;
/*  47:    */   }
/*  48:    */   
/*  49:    */   public void onUpdate()
/*  50:    */   {
/*  51: 65 */     if ((!this.worldObj.isRemote) && (this.user == null))
/*  52:    */     {
/*  53: 68 */       setDead();
/*  54: 69 */       return;
/*  55:    */     }
/*  56: 71 */     super.onUpdate();
/*  57: 74 */     if (this.user != null) {
/*  58: 76 */       if ((this.user instanceof EntityPlayer))
/*  59:    */       {
/*  60: 78 */         EntityPlayer userEntity_p = (EntityPlayer)this.user;
/*  61: 79 */         if (!userEntity_p.isUsingItem())
/*  62:    */         {
/*  63: 81 */           int damage = (int)(this.lightLevel / 40.0D);
/*  64: 82 */           if (damage > 30) {
/*  65: 84 */             damage = 30;
/*  66:    */           }
/*  67: 87 */           if (damage > 0)
/*  68:    */           {
/*  69: 90 */             THShotLib.createLaserA(this.user, this, THShotLib.pos(this.posX, this.posY, this.posZ), THShotLib.angle(this.user.rotationYaw, this.user.rotationPitch), 0.1D, 4.0D, 0.3D, THShotLib.gravity_Zero(), 
/*  70: 91 */               LaserData.laser(0, damage * 0.01F, damage * 0.3F, damage, 0, 60, 351));
/*  71: 92 */             if (!this.worldObj.isRemote) {
/*  72: 94 */               setDead();
/*  73:    */             }
/*  74:    */           }
/*  75:    */         }
/*  76:    */       }
/*  77:101 */       else if (!this.worldObj.isRemote)
/*  78:    */       {
/*  79:103 */         setDead();
/*  80:    */       }
/*  81:    */     }
/*  82:109 */     setLightPower(this.worldObj.getBlockLightValue((int)this.posX, (int)this.posY, (int)this.posZ));
/*  83:110 */     this.lightLevel += getLightPower();
/*  84:113 */     if (this.user != null)
/*  85:    */     {
/*  86:115 */       this.posX = (this.user.posX - Math.sin(this.user.rotationYaw / 180.0F * 3.141593F) * Math.cos(this.user.rotationPitch / 180.0F * 3.141593F) * this.length);
/*  87:116 */       this.posY = (this.user.posY - Math.sin(this.user.rotationPitch / 180.0F * 3.141593F) * this.length + this.user.getEyeHeight());
/*  88:117 */       this.posZ = (this.user.posZ + Math.cos(this.user.rotationYaw / 180.0F * 3.141593F) * Math.cos(this.user.rotationPitch / 180.0F * 3.141593F) * this.length);
/*  89:    */       
/*  90:    */ 
/*  91:120 */       this.rotationYaw = this.user.rotationYawHead;
/*  92:121 */       this.rotationPitch = this.user.rotationPitch;
/*  93:122 */       setPosition(this.posX, this.posY, this.posZ);
/*  94:    */     }
/*  95:125 */     if (this.rotationYaw > 180.0F) {
/*  96:125 */       this.rotationYaw -= 360.0F;
/*  97:    */     }
/*  98:126 */     if (this.rotationYaw < -180.0F) {
/*  99:126 */       this.rotationYaw += 360.0F;
/* 100:    */     }
/* 101:127 */     if (this.rotationPitch > 180.0F) {
/* 102:127 */       this.rotationPitch -= 360.0F;
/* 103:    */     }
/* 104:128 */     if (this.rotationPitch < -180.0F) {
/* 105:128 */       this.rotationPitch += 360.0F;
/* 106:    */     }
/* 107:130 */     setRotation(this.rotationYaw, this.rotationPitch);
/* 108:    */   }
/* 109:    */   
/* 110:    */   public void setLightPower(int lightPower)
/* 111:    */   {
/* 112:139 */     this.dataWatcher.updateObject(19, Integer.valueOf(lightPower));
/* 113:    */   }
/* 114:    */   
/* 115:    */   public int getLightPower()
/* 116:    */   {
/* 117:148 */     return this.dataWatcher.getWatchableObjectInt(19);
/* 118:    */   }
/* 119:    */   
/* 120:    */   protected void writeEntityToNBT(NBTTagCompound nbtTagCompound) {}
/* 121:    */   
/* 122:    */   protected void readEntityFromNBT(NBTTagCompound nbtTagCompound) {}
/* 123:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.effect.EntityAjaRedStoneEffect
 * JD-Core Version:    0.7.0.1
 */