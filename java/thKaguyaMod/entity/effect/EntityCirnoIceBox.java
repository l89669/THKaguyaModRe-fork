/*   1:    */ package thKaguyaMod.entity.effect;
/*   2:    */ 
/*   3:    */ import net.minecraft.entity.DataWatcher;
/*   4:    */ import net.minecraft.entity.Entity;
/*   5:    */ import net.minecraft.entity.EntityLivingBase;
/*   6:    */ import net.minecraft.entity.monster.EntityCreeper;
/*   7:    */ import net.minecraft.entity.monster.EntityGhast;
/*   8:    */ import net.minecraft.entity.passive.EntityTameable;
/*   9:    */ import net.minecraft.entity.player.EntityPlayerMP;
/*  10:    */ import net.minecraft.nbt.NBTTagCompound;
/*  11:    */ import net.minecraft.network.NetHandlerPlayServer;
/*  12:    */ import net.minecraft.util.AxisAlignedBB;
/*  13:    */ import net.minecraft.world.World;
/*  14:    */ import thKaguyaMod.entity.living.EntityCirno;
/*  15:    */ 
/*  16:    */ public class EntityCirnoIceBox
/*  17:    */   extends Entity
/*  18:    */ {
/*  19:    */   public EntityLivingBase frozen;
/*  20:    */   private int count;
/*  21:    */   protected float iceBoxWidth;
/*  22:    */   protected float iceBoxHeight;
/*  23:    */   
/*  24:    */   public EntityCirnoIceBox(World world)
/*  25:    */   {
/*  26: 25 */     super(world);
/*  27: 26 */     this.preventEntitySpawning = true;
/*  28: 27 */     setSize(getIceBoxWidth() + 0.8F, getIceBoxHeight() + 0.8F);
/*  29:    */   }
/*  30:    */   
/*  31:    */   public EntityCirnoIceBox(World world, EntityLivingBase frozenEntity)
/*  32:    */   {
/*  33: 32 */     this(world);
/*  34:    */     
/*  35: 34 */     this.frozen = frozenEntity;
/*  36: 35 */     setIceBoxWidth(1.0F);
/*  37: 36 */     setIceBoxHeight(this.frozen.height);
/*  38: 37 */     setSize(getIceBoxWidth() + 0.8F, getIceBoxHeight() + 0.8F);
/*  39:    */     
/*  40: 39 */     setPosition(this.frozen.posX, this.frozen.posY, this.frozen.posZ);
/*  41:    */   }
/*  42:    */   
/*  43:    */   protected void entityInit()
/*  44:    */   {
/*  45: 47 */     this.dataWatcher.addObject(17, new Float(0.5F));
/*  46: 48 */     this.dataWatcher.addObject(18, new Float(0.5F));
/*  47:    */   }
/*  48:    */   
/*  49:    */   protected void setIceBoxWidth(float width)
/*  50:    */   {
/*  51: 53 */     this.dataWatcher.updateObject(17, Float.valueOf(width));
/*  52:    */   }
/*  53:    */   
/*  54:    */   public float getIceBoxWidth()
/*  55:    */   {
/*  56: 58 */     return this.dataWatcher.getWatchableObjectFloat(17);
/*  57:    */   }
/*  58:    */   
/*  59:    */   protected void setIceBoxHeight(float height)
/*  60:    */   {
/*  61: 63 */     this.dataWatcher.updateObject(18, Float.valueOf(height));
/*  62:    */   }
/*  63:    */   
/*  64:    */   public float getIceBoxHeight()
/*  65:    */   {
/*  66: 68 */     return this.dataWatcher.getWatchableObjectFloat(18);
/*  67:    */   }
/*  68:    */   
/*  69:    */   public AxisAlignedBB getBoundingBox()
/*  70:    */   {
/*  71: 86 */     return this.boundingBox;
/*  72:    */   }
/*  73:    */   
/*  74:    */   public boolean canBePushed()
/*  75:    */   {
/*  76: 95 */     return true;
/*  77:    */   }
/*  78:    */   
/*  79:    */   public boolean canBeCollidedWith()
/*  80:    */   {
/*  81:104 */     return true;
/*  82:    */   }
/*  83:    */   
/*  84:    */   public void onUpdate()
/*  85:    */   {
/*  86:113 */     super.onUpdate();
/*  87:136 */     if (this.frozen != null) {
/*  88:138 */       if (!this.frozen.isDead)
/*  89:    */       {
/*  90:144 */         this.width = getIceBoxWidth();
/*  91:145 */         this.height = getIceBoxHeight();
/*  92:146 */         this.boundingBox.minX = (this.frozen.posX - this.width / 2.0D);
/*  93:147 */         this.boundingBox.minZ = (this.frozen.posZ - this.width / 2.0D);
/*  94:148 */         this.boundingBox.minY = this.frozen.posY;
/*  95:149 */         this.boundingBox.maxX = (this.boundingBox.minX + this.width);
/*  96:150 */         this.boundingBox.maxZ = (this.boundingBox.minZ + this.width);
/*  97:151 */         this.boundingBox.maxY = (this.boundingBox.minY + this.height);
/*  98:152 */         setSize(getIceBoxWidth(), getIceBoxHeight());
/*  99:153 */         frozenInIce();
/* 100:    */       }
/* 101:    */     }
/* 102:157 */     if (this.ticksExisted > 50) {
/* 103:159 */       setIceBoxHeight(getIceBoxHeight() - 0.08F);
/* 104:    */     }
/* 105:161 */     if ((this.frozen != null) && ((this.frozen instanceof EntityCirno))) {
/* 106:163 */       setIceBoxHeight(getIceBoxHeight() - 0.16F);
/* 107:    */     }
/* 108:166 */     if (getIceBoxHeight() <= -0.7F) {
/* 109:168 */       if (!this.worldObj.isRemote) {
/* 110:170 */         setDead();
/* 111:    */       }
/* 112:    */     }
/* 113:    */   }
/* 114:    */   
/* 115:    */   protected void writeEntityToNBT(NBTTagCompound nbtTagCompound)
/* 116:    */   {
/* 117:179 */     nbtTagCompound.setShort("count", (short)this.count);
/* 118:    */   }
/* 119:    */   
/* 120:    */   protected void readEntityFromNBT(NBTTagCompound nbtTagCompound)
/* 121:    */   {
/* 122:186 */     this.count = nbtTagCompound.getShort("count");
/* 123:    */   }
/* 124:    */   
/* 125:    */   public float getShadowSize()
/* 126:    */   {
/* 127:192 */     return 0.5F;
/* 128:    */   }
/* 129:    */   
/* 130:    */   protected void frozenInIce()
/* 131:    */   {
/* 132:200 */     this.frozen.setPosition(this.frozen.prevPosX, this.frozen.prevPosY, this.frozen.prevPosZ);
/* 133:    */     
/* 134:    */ 
/* 135:203 */     this.frozen.motionX *= 1.E-009D;
/* 136:204 */     this.frozen.motionY *= 1.E-009D;
/* 137:205 */     this.frozen.motionZ *= 1.E-009D;
/* 138:    */     
/* 139:207 */     this.frozen.rotationYaw = this.frozen.prevRotationYaw;
/* 140:208 */     this.frozen.rotationPitch = this.frozen.prevRotationPitch;
/* 141:209 */     this.frozen.ticksExisted -= 1;
/* 142:210 */     this.frozen.fallDistance -= 0.0F;
/* 143:    */     
/* 144:    */ 
/* 145:213 */     this.frozen.rotationYawHead = this.frozen.prevRotationYawHead;
/* 146:    */     
/* 147:215 */     this.frozen.attackTime += 1;
/* 148:216 */     if (this.frozen.isDead) {
/* 149:218 */       this.frozen.deathTime = 0;
/* 150:    */     }
/* 151:221 */     if ((this.frozen instanceof EntityCreeper))
/* 152:    */     {
/* 153:223 */       EntityCreeper creeper = (EntityCreeper)this.frozen;
/* 154:224 */       creeper.setCreeperState(-1);
/* 155:    */     }
/* 156:226 */     else if ((this.frozen instanceof EntityGhast))
/* 157:    */     {
/* 158:228 */       EntityGhast ghast = (EntityGhast)this.frozen;
/* 159:229 */       ghast.attackCounter -= 1;
/* 160:    */     }
/* 161:231 */     if ((this.frozen instanceof EntityTameable)) {
/* 162:233 */       this.frozen.motionY -= 1.0E-006D;
/* 163:    */     }
/* 164:236 */     if ((this.frozen instanceof EntityPlayerMP))
/* 165:    */     {
/* 166:238 */       EntityPlayerMP player = (EntityPlayerMP)this.frozen;
/* 167:239 */       player.playerNetServerHandler.setPlayerLocation(player.prevPosX, player.prevPosY, player.prevPosZ, player.rotationYaw, player.rotationPitch);
/* 168:    */     }
/* 169:    */   }
/* 170:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.effect.EntityCirnoIceBox
 * JD-Core Version:    0.7.0.1
 */