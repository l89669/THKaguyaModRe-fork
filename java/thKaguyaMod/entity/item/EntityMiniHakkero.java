/*   1:    */ package thKaguyaMod.entity.item;
/*   2:    */ 
/*   3:    */ import net.minecraft.entity.Entity;
/*   4:    */ import net.minecraft.entity.EntityLivingBase;
/*   5:    */ import net.minecraft.entity.boss.EntityDragonPart;
/*   6:    */ import net.minecraft.entity.passive.EntityAnimal;
/*   7:    */ import net.minecraft.entity.passive.EntityVillager;
/*   8:    */ import net.minecraft.nbt.NBTTagCompound;
/*   9:    */ import net.minecraft.util.Vec3;
/*  10:    */ import net.minecraft.world.World;
/*  11:    */ import thKaguyaMod.LaserData;
/*  12:    */ import thKaguyaMod.THKaguyaLib;
/*  13:    */ import thKaguyaMod.THShotLib;
/*  14:    */ import thKaguyaMod.init.THKaguyaConfig;
/*  15:    */ import thKaguyaMod.init.THKaguyaItems;
/*  16:    */ 
/*  17:    */ public class EntityMiniHakkero
/*  18:    */   extends Entity
/*  19:    */ {
/*  20:    */   public EntityLivingBase user;
/*  21:    */   private EntityLivingBase tgEntity;
/*  22:    */   public int count;
/*  23:    */   private float circleAngle;
/*  24:    */   private int moveTexture;
/*  25:    */   public int num;
/*  26:    */   private int damage;
/*  27:    */   private int lastTime;
/*  28:    */   private boolean isSpellCard;
/*  29:    */   
/*  30:    */   public EntityMiniHakkero(World world)
/*  31:    */   {
/*  32: 33 */     super(world);
/*  33: 34 */     this.preventEntitySpawning = true;
/*  34: 35 */     setSize(0.4F, 0.4F);
/*  35: 36 */     this.yOffset = 0.0F;
/*  36:    */   }
/*  37:    */   
/*  38:    */   public EntityMiniHakkero(World world, EntityLivingBase EntityLivingBase, int da)
/*  39:    */   {
/*  40: 41 */     this(world);
/*  41:    */     
/*  42: 43 */     this.user = EntityLivingBase;
/*  43: 44 */     setPosition(this.user.posX - Math.sin(this.user.rotationYaw / 180.0F * 3.141593F) * Math.cos(this.user.rotationPitch / 180.0F * 3.141593F), this.user.posY - 
/*  44: 45 */       Math.sin(this.user.rotationPitch / 180.0F * 3.141593F) + this.user.getEyeHeight() - 0.1000000014901161D, this.user.posZ + 
/*  45: 46 */       Math.cos(this.user.rotationYaw / 180.0F * 3.141593F) * Math.cos(this.user.rotationPitch / 180.0F * 3.141593F));
/*  46: 47 */     this.rotationYaw = this.user.rotationYaw;
/*  47: 48 */     this.rotationPitch = this.user.rotationPitch;
/*  48: 49 */     this.tgEntity = null;
/*  49: 50 */     this.count = 0;
/*  50: 51 */     this.circleAngle = 0.0F;
/*  51: 52 */     this.moveTexture = 0;
/*  52: 53 */     this.damage = da;
/*  53: 54 */     this.isSpellCard = false;
/*  54:    */     
/*  55:    */ 
/*  56: 57 */     this.worldObj.playSoundAtEntity(this, "thkaguyamod:masterspark", THKaguyaConfig.MasterSparkVol, 1.0F);
/*  57: 58 */     this.lastTime = 0;
/*  58:    */     
/*  59: 60 */     int special = 70;
/*  60: 61 */     Vec3 look = THShotLib.getVecFromAngle(this.rotationYaw, this.rotationPitch);
/*  61: 62 */     THShotLib.createLaserB(this.user, this, THShotLib.pos_Entity(this), look, 
/*  62: 63 */       THShotLib.rotate_Default(), 0.0F, 9999, LaserData.laser(24, 4.2F, 40.0F, 8.0F, 30, 120, special), this, 1.0D, 0.0D);
/*  63:    */   }
/*  64:    */   
/*  65:    */   public EntityMiniHakkero(World world, EntityLivingBase EntityLivingBase, EntityLivingBase target)
/*  66:    */   {
/*  67: 68 */     this(world);
/*  68:    */     
/*  69: 70 */     this.user = EntityLivingBase;
/*  70: 71 */     setPosition(this.user.posX - Math.sin(this.user.rotationYaw / 180.0F * 3.141593F) * Math.cos(this.user.rotationPitch / 180.0F * 3.141593F), this.user.posY - 
/*  71: 72 */       Math.sin(this.user.rotationPitch / 180.0F * 3.141593F) + this.user.getEyeHeight() - 0.1000000014901161D, this.user.posZ + 
/*  72: 73 */       Math.cos(this.user.rotationYaw / 180.0F * 3.141593F) * Math.cos(this.user.rotationPitch / 180.0F * 3.141593F));
/*  73: 74 */     this.rotationYaw = this.user.rotationYaw;
/*  74: 75 */     this.rotationPitch = this.user.rotationPitch;
/*  75: 76 */     this.tgEntity = target;
/*  76: 77 */     this.count = 0;
/*  77: 78 */     this.circleAngle = 0.0F;
/*  78: 79 */     this.moveTexture = 0;
/*  79: 80 */     this.damage = 1;
/*  80: 81 */     this.worldObj.playSoundAtEntity(this, "thkaguyamod:masterspark", THKaguyaConfig.MasterSparkVol, 1.0F);
/*  81: 82 */     this.lastTime = 0;
/*  82:    */     
/*  83: 84 */     this.isSpellCard = true;
/*  84:    */     
/*  85: 86 */     Vec3 look = THShotLib.getVecFromAngle(this.rotationYaw, this.rotationPitch);
/*  86: 87 */     THShotLib.createLaserB(this.user, this, THShotLib.pos_Entity(this), look, 
/*  87: 88 */       THShotLib.rotate_Default(), 0.0F, 9999, LaserData.laser(24, 4.2F, 60.0F, 8.0F, 30, 120, 0), this, 1.0D, 0.0D);
/*  88:    */   }
/*  89:    */   
/*  90:    */   protected void entityInit() {}
/*  91:    */   
/*  92:    */   public boolean canBePushed()
/*  93:    */   {
/*  94:103 */     return false;
/*  95:    */   }
/*  96:    */   
/*  97:    */   public boolean canBeCollidedWith()
/*  98:    */   {
/*  99:110 */     return true;
/* 100:    */   }
/* 101:    */   
/* 102:    */   public void onUpdate()
/* 103:    */   {
/* 104:117 */     super.onUpdate();
/* 105:118 */     if ((!this.worldObj.isRemote) && (this.user == null))
/* 106:    */     {
/* 107:120 */       if (!this.isSpellCard) {
/* 108:122 */         THKaguyaLib.itemEffectFinish(this, this.user, THKaguyaItems.mini_hakkero);
/* 109:126 */       } else if (!this.worldObj.isRemote) {
/* 110:128 */         setDead();
/* 111:    */       }
/* 112:131 */       return;
/* 113:    */     }
/* 114:134 */     if (this.ticksExisted <= this.lastTime) {
/* 115:136 */       return;
/* 116:    */     }
/* 117:139 */     this.circleAngle += 4.7F;
/* 118:250 */     if (this.ticksExisted > this.lastTime) {
/* 119:252 */       this.lastTime = this.ticksExisted;
/* 120:    */     }
/* 121:255 */     if (this.ticksExisted >= 109) {
/* 122:257 */       if (!this.worldObj.isRemote) {
/* 123:259 */         if (!this.isSpellCard) {
/* 124:261 */           THKaguyaLib.itemEffectFinish(this, this.user, THKaguyaItems.mini_hakkero);
/* 125:265 */         } else if (!this.worldObj.isRemote) {
/* 126:267 */           setDead();
/* 127:    */         }
/* 128:    */       }
/* 129:    */     }
/* 130:    */   }
/* 131:    */   
/* 132:    */   public boolean hitCheckEx(Entity entity)
/* 133:    */   {
/* 134:276 */     if (this.tgEntity == null) {
/* 135:278 */       return true;
/* 136:    */     }
/* 137:282 */     return ((entity instanceof EntityDragonPart)) || (((entity instanceof EntityLivingBase)) && (!(entity instanceof EntityAnimal)) && (!(entity instanceof EntityVillager)));
/* 138:    */   }
/* 139:    */   
/* 140:    */   public float getExplosionLevel()
/* 141:    */   {
/* 142:292 */     return 5.0F;
/* 143:    */   }
/* 144:    */   
/* 145:    */   protected void writeEntityToNBT(NBTTagCompound nbtTagCompound)
/* 146:    */   {
/* 147:302 */     nbtTagCompound.setShort("count", (short)this.count);
/* 148:303 */     nbtTagCompound.setShort("damage", (short)this.damage);
/* 149:304 */     nbtTagCompound.setBoolean("isSpellCard", this.tgEntity != null);
/* 150:    */   }
/* 151:    */   
/* 152:    */   protected void readEntityFromNBT(NBTTagCompound nbtTagCompound)
/* 153:    */   {
/* 154:314 */     this.count = nbtTagCompound.getShort("count");
/* 155:315 */     this.damage = nbtTagCompound.getShort("damage");
/* 156:316 */     this.isSpellCard = nbtTagCompound.getBoolean("isSpellCard");
/* 157:    */   }
/* 158:    */   
/* 159:    */   public float getShadowSize()
/* 160:    */   {
/* 161:321 */     return 0.5F;
/* 162:    */   }
/* 163:    */   
/* 164:    */   protected boolean isValidLightLevel()
/* 165:    */   {
/* 166:326 */     return true;
/* 167:    */   }
/* 168:    */   
/* 169:    */   public int getBrightnessForRender(float par1)
/* 170:    */   {
/* 171:331 */     return 15728880;
/* 172:    */   }
/* 173:    */   
/* 174:    */   public float getBrightness(float par1)
/* 175:    */   {
/* 176:339 */     return 0.5F;
/* 177:    */   }
/* 178:    */   
/* 179:    */   public float getCircleAngle()
/* 180:    */   {
/* 181:344 */     return this.circleAngle;
/* 182:    */   }
/* 183:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.item.EntityMiniHakkero
 * JD-Core Version:    0.7.0.1
 */