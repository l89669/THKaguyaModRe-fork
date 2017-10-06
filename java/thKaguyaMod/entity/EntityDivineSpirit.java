/*   1:    */ package thKaguyaMod.entity;
/*   2:    */ 
/*   3:    */ import java.util.List;
/*   4:    */ import java.util.Random;
/*   5:    */ import net.minecraft.entity.DataWatcher;
/*   6:    */ import net.minecraft.entity.Entity;
/*   7:    */ import net.minecraft.entity.player.EntityPlayer;
/*   8:    */ import net.minecraft.entity.player.InventoryPlayer;
/*   9:    */ import net.minecraft.item.ItemStack;
/*  10:    */ import net.minecraft.nbt.NBTTagCompound;
/*  11:    */ import net.minecraft.util.AxisAlignedBB;
/*  12:    */ import net.minecraft.util.MovingObjectPosition;
/*  13:    */ import net.minecraft.util.Vec3;
/*  14:    */ import net.minecraft.world.World;
/*  15:    */ import thKaguyaMod.THShotLib;
/*  16:    */ import thKaguyaMod.init.THKaguyaBlocks;
/*  17:    */ 
/*  18:    */ public class EntityDivineSpirit
/*  19:    */   extends Entity
/*  20:    */ {
/*  21:    */   public short color;
/*  22:    */   private int lastTime;
/*  23:    */   private double lastShotMotionX;
/*  24:    */   private double lastShotMotionY;
/*  25:    */   private double lastShotMotionZ;
/*  26:    */   private float lastRotationYaw;
/*  27:    */   private float lastRotationPitch;
/*  28:    */   private double speed;
/*  29:    */   
/*  30:    */   public EntityDivineSpirit(World world)
/*  31:    */   {
/*  32: 33 */     super(world);
/*  33:    */     
/*  34: 35 */     setSize(1.2F, 1.2F);
/*  35:    */   }
/*  36:    */   
/*  37:    */   public EntityDivineSpirit(World world, double posX, double posY, double posZ, int t)
/*  38:    */   {
/*  39: 40 */     super(world);
/*  40: 41 */     setDivineSpiritColor((short)t);
/*  41: 42 */     setDivineSpiritCount(0);
/*  42: 43 */     setLocationAndAngles(posX + this.motionX * 0.1D, posY + this.motionY * 0.1D, posZ + this.motionZ * 0.1D, this.rotationYaw, this.rotationPitch);
/*  43:    */     
/*  44:    */ 
/*  45:    */ 
/*  46: 47 */     this.prevPosX = posX;
/*  47: 48 */     this.prevPosY = posY;
/*  48: 49 */     this.prevPosZ = posZ;
/*  49: 50 */     this.lastTime = 0;
/*  50: 51 */     this.speed = 0.0D;
/*  51:    */   }
/*  52:    */   
/*  53:    */   public boolean canBePushed()
/*  54:    */   {
/*  55: 61 */     return false;
/*  56:    */   }
/*  57:    */   
/*  58:    */   public AxisAlignedBB getCollisionBox(Entity entity)
/*  59:    */   {
/*  60: 71 */     return null;
/*  61:    */   }
/*  62:    */   
/*  63:    */   public AxisAlignedBB getBoundingBox()
/*  64:    */   {
/*  65: 81 */     return null;
/*  66:    */   }
/*  67:    */   
/*  68:    */   public boolean isInRangeToRenderDist(double d)
/*  69:    */   {
/*  70: 87 */     double d1 = this.boundingBox.getAverageEdgeLength() * 4.0D;
/*  71: 88 */     d1 *= 64.0D;
/*  72: 89 */     return d < d1 * d1;
/*  73:    */   }
/*  74:    */   
/*  75:    */   protected void entityInit()
/*  76:    */   {
/*  77: 96 */     this.dataWatcher.addObject(18, new Integer(0));
/*  78: 97 */     this.dataWatcher.addObject(19, new Integer(0));
/*  79:    */   }
/*  80:    */   
/*  81:    */   public double getEntitySpeed()
/*  82:    */   {
/*  83:108 */     double xx = this.motionX;
/*  84:109 */     double yy = this.motionY;
/*  85:110 */     double zz = this.motionZ;
/*  86:111 */     return Math.sqrt(xx * xx + yy * yy + zz * zz);
/*  87:    */   }
/*  88:    */   
/*  89:    */   public void onUpdate()
/*  90:    */   {
/*  91:120 */     super.onUpdate();
/*  92:123 */     if (getDivineSpiritCount() > 300) {
/*  93:125 */       if (!this.worldObj.isRemote) {
/*  94:127 */         setDead();
/*  95:    */       }
/*  96:    */     }
/*  97:131 */     setDivineSpiritCount(getDivineSpiritCount() + 1);
/*  98:    */     
/*  99:    */ 
/* 100:134 */     MovingObjectPosition movingObjectPosition = new MovingObjectPosition(this);
/* 101:135 */     Entity entity = null;
/* 102:136 */     double effectiveBoundary = 10.0D;
/* 103:137 */     double distance = 999.0D;
/* 104:138 */     Entity nearEntity = null;
/* 105:139 */     EntityPlayer nearPlayer = null;
/* 106:    */     
/* 107:141 */     List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(effectiveBoundary, effectiveBoundary, effectiveBoundary));
/* 108:143 */     if ((list != null) && (list.size() > 0)) {
/* 109:145 */       for (int j1 = 0; j1 < list.size(); j1++)
/* 110:    */       {
/* 111:147 */         entity = (Entity)list.get(j1);
/* 112:148 */         if (entity != null) {
/* 113:150 */           movingObjectPosition = new MovingObjectPosition(entity);
/* 114:    */         }
/* 115:152 */         if ((entity instanceof EntityPlayer)) {
/* 116:154 */           if (distance > entity.getDistanceToEntity(this))
/* 117:    */           {
/* 118:156 */             distance = entity.getDistanceToEntity(this);
/* 119:157 */             nearPlayer = (EntityPlayer)entity;
/* 120:    */           }
/* 121:    */         }
/* 122:    */       }
/* 123:    */     }
/* 124:163 */     if (nearPlayer != null)
/* 125:    */     {
/* 126:165 */       if (this.speed < 0.5D) {
/* 127:167 */         this.speed += 0.03D;
/* 128:    */       }
/* 129:170 */       this.rotationYaw = ((float)Math.atan2(nearPlayer.posX - this.posX, nearPlayer.posZ - this.posZ) / 3.141593F * 180.0F);
/* 130:171 */       this.rotationPitch = ((float)Math.atan2(nearPlayer.posY + nearPlayer.getEyeHeight() - this.posY, Math.sqrt((nearPlayer.posX - this.posX) * (nearPlayer.posX - this.posX) + (nearPlayer.posZ - this.posZ) * (nearPlayer.posZ - this.posZ))) / 3.141593F * 180.0F);
/* 131:    */     }
/* 132:    */     else
/* 133:    */     {
/* 134:176 */       this.speed -= 0.03D;
/* 135:177 */       if (this.speed < 0.0D) {
/* 136:179 */         this.speed = 0.0D;
/* 137:    */       }
/* 138:    */     }
/* 139:183 */     Vec3 vec = THShotLib.getVecFromAngle(this.rotationYaw, this.rotationPitch, this.speed);
/* 140:184 */     this.posX -= vec.xCoord;
/* 141:185 */     this.posY -= vec.yCoord;
/* 142:186 */     this.posZ += vec.zCoord;
/* 143:187 */     setPosition(this.posX, this.posY, this.posZ);
/* 144:194 */     if (this.ticksExisted > this.lastTime)
/* 145:    */     {
/* 146:196 */       this.lastTime = this.ticksExisted;
/* 147:197 */       this.lastShotMotionX = this.motionX;
/* 148:198 */       this.lastShotMotionY = this.motionY;
/* 149:199 */       this.lastShotMotionZ = this.motionZ;
/* 150:200 */       this.lastRotationYaw = this.rotationYaw;
/* 151:201 */       this.lastRotationPitch = this.rotationPitch;
/* 152:    */     }
/* 153:    */   }
/* 154:    */   
/* 155:    */   public void writeEntityToNBT(NBTTagCompound nbtTagCompound)
/* 156:    */   {
/* 157:213 */     nbtTagCompound.setShort("color", (short)getDivineSpiritColor());
/* 158:    */   }
/* 159:    */   
/* 160:    */   public void readEntityFromNBT(NBTTagCompound nbtTagCompound)
/* 161:    */   {
/* 162:223 */     setDivineSpiritColor(nbtTagCompound.getShort("color"));
/* 163:    */   }
/* 164:    */   
/* 165:    */   public void onCollideWithPlayer(EntityPlayer player)
/* 166:    */   {
/* 167:232 */     if (player.inventory.addItemStackToInventory(new ItemStack(THKaguyaBlocks.divine_spirit, 1, getDivineSpiritColor())))
/* 168:    */     {
/* 169:234 */       this.worldObj.playSoundAtEntity(this, "random.pop", 4.0F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 0.3F);
/* 170:235 */       player.onItemPickup(this, 1);
/* 171:    */     }
/* 172:238 */     if (!this.worldObj.isRemote) {
/* 173:240 */       setDead();
/* 174:    */     }
/* 175:    */   }
/* 176:    */   
/* 177:    */   public boolean canBeCollidedWith()
/* 178:    */   {
/* 179:251 */     return true;
/* 180:    */   }
/* 181:    */   
/* 182:    */   public float getCollisionBorderSize()
/* 183:    */   {
/* 184:261 */     return 0.1F;
/* 185:    */   }
/* 186:    */   
/* 187:    */   public void setDivineSpiritCount(int count)
/* 188:    */   {
/* 189:270 */     this.dataWatcher.updateObject(18, Integer.valueOf(count));
/* 190:    */   }
/* 191:    */   
/* 192:    */   public int getDivineSpiritCount()
/* 193:    */   {
/* 194:279 */     return this.dataWatcher.getWatchableObjectInt(18);
/* 195:    */   }
/* 196:    */   
/* 197:    */   public void setDivineSpiritColor(int color)
/* 198:    */   {
/* 199:288 */     this.dataWatcher.updateObject(19, Integer.valueOf(color));
/* 200:    */   }
/* 201:    */   
/* 202:    */   public int getDivineSpiritColor()
/* 203:    */   {
/* 204:297 */     return this.dataWatcher.getWatchableObjectInt(19);
/* 205:    */   }
/* 206:    */   
/* 207:    */   public float getShadowSize()
/* 208:    */   {
/* 209:302 */     return 0.0F;
/* 210:    */   }
/* 211:    */   
/* 212:    */   public float getEntityBrightness(float f)
/* 213:    */   {
/* 214:307 */     return 0.0F;
/* 215:    */   }
/* 216:    */   
/* 217:    */   public int getEntityBrightnessForRender(float f)
/* 218:    */   {
/* 219:312 */     return 15728880;
/* 220:    */   }
/* 221:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.EntityDivineSpirit
 * JD-Core Version:    0.7.0.1
 */