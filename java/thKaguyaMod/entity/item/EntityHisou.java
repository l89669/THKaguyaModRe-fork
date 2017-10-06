/*   1:    */ package thKaguyaMod.entity.item;
/*   2:    */ 
/*   3:    */ import net.minecraft.entity.DataWatcher;
/*   4:    */ import net.minecraft.entity.Entity;
/*   5:    */ import net.minecraft.entity.EntityLivingBase;
/*   6:    */ import net.minecraft.nbt.NBTTagCompound;
/*   7:    */ import net.minecraft.world.World;
/*   8:    */ import thKaguyaMod.ShotData;
/*   9:    */ import thKaguyaMod.THKaguyaLib;
/*  10:    */ import thKaguyaMod.THShotLib;
/*  11:    */ import thKaguyaMod.init.THKaguyaConfig;
/*  12:    */ import thKaguyaMod.init.THKaguyaItems;
/*  13:    */ 
/*  14:    */ public class EntityHisou
/*  15:    */   extends Entity
/*  16:    */ {
/*  17:    */   public EntityLivingBase userEntity;
/*  18:    */   private Entity motherEntity;
/*  19:    */   private int count;
/*  20:    */   public int num;
/*  21:    */   private int damage;
/*  22:    */   private int spellCardUsedTime;
/*  23:    */   
/*  24:    */   public EntityHisou(World world)
/*  25:    */   {
/*  26: 28 */     super(world);
/*  27: 29 */     setSize(4.4F, 4.4F);
/*  28: 30 */     this.yOffset = 0.0F;
/*  29:    */   }
/*  30:    */   
/*  31:    */   public EntityHisou(World world, EntityLivingBase entityLivingBase, Entity mother, int n, int da)
/*  32:    */   {
/*  33: 35 */     this(world);
/*  34:    */     
/*  35: 37 */     this.userEntity = entityLivingBase;
/*  36: 38 */     this.prevPosX = this.userEntity.posX;
/*  37: 39 */     this.prevPosY = this.userEntity.posY;
/*  38: 40 */     this.prevPosZ = this.userEntity.posZ;
/*  39: 41 */     setPosition(this.userEntity.posX - Math.sin(this.userEntity.rotationYaw / 180.0F * 3.141593F) * Math.cos(this.userEntity.rotationPitch / 180.0F * 3.141593F) * 1.2D, this.userEntity.posY - 
/*  40: 42 */       Math.sin(this.userEntity.rotationPitch / 180.0F * 3.141593F) * 1.2D + this.userEntity.getEyeHeight() - 0.7D, this.userEntity.posZ + 
/*  41: 43 */       Math.cos(this.userEntity.rotationYaw / 180.0F * 3.141593F) * Math.cos(this.userEntity.rotationPitch / 180.0F * 3.141593F) * 1.2D);
/*  42: 44 */     this.rotationYaw = this.userEntity.rotationYaw;
/*  43: 45 */     this.ridingEntity = this.userEntity;
/*  44: 46 */     this.motherEntity = mother;
/*  45: 47 */     this.count = 0;
/*  46: 48 */     this.num = n;
/*  47: 49 */     setNum(n);
/*  48: 50 */     this.damage = da;
/*  49: 51 */     this.spellCardUsedTime = 0;
/*  50: 52 */     this.worldObj.playSoundAtEntity(this, "thkaguyamod:masterspark", THKaguyaConfig.HisoutenVol, 1.0F);
/*  51:    */   }
/*  52:    */   
/*  53:    */   protected void entityInit()
/*  54:    */   {
/*  55: 59 */     this.dataWatcher.addObject(17, new Integer(0));
/*  56: 60 */     this.dataWatcher.addObject(18, new Integer(0));
/*  57:    */   }
/*  58:    */   
/*  59:    */   public boolean canBePushed()
/*  60:    */   {
/*  61: 69 */     return false;
/*  62:    */   }
/*  63:    */   
/*  64:    */   public boolean canBeCollidedWith()
/*  65:    */   {
/*  66: 78 */     return false;
/*  67:    */   }
/*  68:    */   
/*  69:    */   public void onUpdate()
/*  70:    */   {
/*  71: 87 */     super.onUpdate();
/*  72: 89 */     if ((!this.worldObj.isRemote) && (this.userEntity == null))
/*  73:    */     {
/*  74: 91 */       if (this.num == 8) {
/*  75: 93 */         THKaguyaLib.itemEffectFinish(this, this.userEntity, THKaguyaItems.hisou_sword, this.damage);
/*  76:    */       } else {
/*  77: 97 */         setDead();
/*  78:    */       }
/*  79: 99 */       return;
/*  80:    */     }
/*  81:102 */     setAngle(getAngle() + (this.count + getNum() / 3.0F) * 39.0F);
/*  82:    */     
/*  83:104 */     THKaguyaLib.itemEffectFollowUser(this, this.userEntity, 1.2D, 0.0F);
/*  84:105 */     if (this.userEntity != null)
/*  85:    */     {
/*  86:121 */       this.rotationYaw = this.userEntity.rotationYaw;
/*  87:122 */       this.rotationPitch = this.userEntity.rotationPitch;
/*  88:    */     }
/*  89:    */     else
/*  90:    */     {
/*  91:133 */       if (getNum() == 8) {
/*  92:135 */         THKaguyaLib.itemEffectFinish(this, this.userEntity, THKaguyaItems.hisou_sword, this.damage);
/*  93:    */       }
/*  94:137 */       if (!this.worldObj.isRemote) {
/*  95:139 */         setDead();
/*  96:    */       }
/*  97:    */     }
/*  98:144 */     if ((this.ticksExisted > this.spellCardUsedTime) && (this.userEntity != null) && (getNum() == 8))
/*  99:    */     {
/* 100:174 */       ShotData shot = ShotData.shot(31, 0, 0.5F, 6.0F, 3, 60, 700);
/* 101:175 */       THShotLib.createRandomRingShot(this.userEntity, this, THShotLib.pos_Entity(this), this.userEntity.getLookVec(), 0.0F, 9999, 0.1D, 0.8D, 0.05D, THShotLib.gravity_Zero(), shot, this.num, 0.1D, 30.0F);
/* 102:    */     }
/* 103:179 */     if (this.ticksExisted > this.spellCardUsedTime) {
/* 104:181 */       this.spellCardUsedTime = this.ticksExisted;
/* 105:    */     }
/* 106:185 */     if (this.ticksExisted >= 100)
/* 107:    */     {
/* 108:187 */       if (getNum() == 8) {
/* 109:189 */         THKaguyaLib.itemEffectFinish(this, this.userEntity, THKaguyaItems.hisou_sword, this.damage);
/* 110:    */       }
/* 111:191 */       if (!this.worldObj.isRemote) {
/* 112:193 */         setDead();
/* 113:    */       }
/* 114:    */     }
/* 115:    */   }
/* 116:    */   
/* 117:    */   public void updateRidden()
/* 118:    */   {
/* 119:203 */     if ((this.ridingEntity != null) && (getNum() == 8))
/* 120:    */     {
/* 121:205 */       this.ridingEntity.rotationYaw = (this.ridingEntity.prevRotationYaw + (this.ridingEntity.rotationYaw - this.ridingEntity.prevRotationYaw) * 0.3F);
/* 122:206 */       this.ridingEntity.rotationPitch = (this.ridingEntity.prevRotationPitch + (this.ridingEntity.rotationPitch - this.ridingEntity.prevRotationPitch) * 0.3F);
/* 123:207 */       this.ridingEntity.motionX *= 0.1D;
/* 124:208 */       this.ridingEntity.motionY *= 0.1D;
/* 125:209 */       this.ridingEntity.motionZ *= 0.1D;
/* 126:    */     }
/* 127:    */   }
/* 128:    */   
/* 129:    */   protected void writeEntityToNBT(NBTTagCompound nbtTagCompound)
/* 130:    */   {
/* 131:220 */     nbtTagCompound.setShort("count", (short)this.count);
/* 132:221 */     nbtTagCompound.setShort("damage", (short)this.damage);
/* 133:222 */     nbtTagCompound.setByte("number", (byte)this.num);
/* 134:    */   }
/* 135:    */   
/* 136:    */   protected void readEntityFromNBT(NBTTagCompound nbtTagCompound)
/* 137:    */   {
/* 138:232 */     this.count = nbtTagCompound.getShort("count");
/* 139:233 */     this.damage = nbtTagCompound.getShort("damage");
/* 140:234 */     this.num = nbtTagCompound.getByte("number");
/* 141:    */   }
/* 142:    */   
/* 143:    */   public float getShadowSize()
/* 144:    */   {
/* 145:239 */     return 0.5F;
/* 146:    */   }
/* 147:    */   
/* 148:    */   public void setNum(int num)
/* 149:    */   {
/* 150:244 */     this.dataWatcher.updateObject(17, Integer.valueOf(num));
/* 151:    */   }
/* 152:    */   
/* 153:    */   public int getNum()
/* 154:    */   {
/* 155:249 */     return this.dataWatcher.getWatchableObjectInt(17);
/* 156:    */   }
/* 157:    */   
/* 158:    */   public void setAngle(float angle)
/* 159:    */   {
/* 160:254 */     this.dataWatcher.updateObject(18, Integer.valueOf((int)(angle * 1000.0F)));
/* 161:    */   }
/* 162:    */   
/* 163:    */   public float getAngle()
/* 164:    */   {
/* 165:259 */     return this.dataWatcher.getWatchableObjectInt(18) / 1000.0F;
/* 166:    */   }
/* 167:    */   
/* 168:    */   protected boolean isValidLightLevel()
/* 169:    */   {
/* 170:264 */     return true;
/* 171:    */   }
/* 172:    */   
/* 173:    */   public float getBrightness(float par1)
/* 174:    */   {
/* 175:272 */     return 1.0F;
/* 176:    */   }
/* 177:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.item.EntityHisou
 * JD-Core Version:    0.7.0.1
 */