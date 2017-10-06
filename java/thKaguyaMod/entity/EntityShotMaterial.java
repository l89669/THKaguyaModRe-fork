/*   1:    */ package thKaguyaMod.entity;
/*   2:    */ 
/*   3:    */ import java.util.List;
/*   4:    */ import java.util.Random;
/*   5:    */ import net.minecraft.entity.Entity;
/*   6:    */ import net.minecraft.entity.player.EntityPlayer;
/*   7:    */ import net.minecraft.entity.player.InventoryPlayer;
/*   8:    */ import net.minecraft.item.ItemStack;
/*   9:    */ import net.minecraft.nbt.NBTTagCompound;
/*  10:    */ import net.minecraft.util.AxisAlignedBB;
/*  11:    */ import net.minecraft.util.MovingObjectPosition;
/*  12:    */ import net.minecraft.util.Vec3;
/*  13:    */ import net.minecraft.world.World;
/*  14:    */ import thKaguyaMod.THShotLib;
/*  15:    */ import thKaguyaMod.init.THKaguyaItems;
/*  16:    */ 
/*  17:    */ public class EntityShotMaterial
/*  18:    */   extends Entity
/*  19:    */ {
/*  20:    */   public short color;
/*  21:    */   protected int lastTime;
/*  22:    */   private double lastSpeed;
/*  23:    */   private double speed;
/*  24:    */   protected double limitSpeed;
/*  25:    */   protected double lastMotionX;
/*  26:    */   protected double lastMotionY;
/*  27:    */   protected double lastMotionZ;
/*  28:    */   EntityPlayer target;
/*  29:    */   
/*  30:    */   public EntityShotMaterial(World world)
/*  31:    */   {
/*  32: 37 */     super(world);
/*  33: 38 */     setSize(1.2F, 1.2F);
/*  34: 39 */     this.limitSpeed = 0.5D;
/*  35:    */   }
/*  36:    */   
/*  37:    */   public EntityShotMaterial(World world, double posX, double posY, double posZ)
/*  38:    */   {
/*  39: 44 */     super(world);
/*  40: 45 */     setSize(1.2F, 1.2F);
/*  41: 46 */     setLocationAndAngles(posX + this.motionX * 0.1D, posY + this.motionY * 0.1D, posZ + this.motionZ * 0.1D, this.rotationYaw, this.rotationPitch);
/*  42:    */     
/*  43:    */ 
/*  44:    */ 
/*  45:    */ 
/*  46: 51 */     this.lastMotionX = this.motionX;
/*  47: 52 */     this.lastMotionY = this.motionY;
/*  48: 53 */     this.lastMotionZ = this.motionZ;
/*  49: 54 */     this.prevPosX = posX;
/*  50: 55 */     this.prevPosY = posY;
/*  51: 56 */     this.prevPosZ = posZ;
/*  52: 57 */     this.lastTime = -1;
/*  53: 58 */     this.speed = 0.0D;
/*  54: 59 */     this.lastSpeed = 0.0D;
/*  55: 60 */     this.limitSpeed = 0.5D;
/*  56:    */     
/*  57: 62 */     this.target = null;
/*  58:    */   }
/*  59:    */   
/*  60:    */   protected void entityInit() {}
/*  61:    */   
/*  62:    */   public boolean canBePushed()
/*  63:    */   {
/*  64: 78 */     return false;
/*  65:    */   }
/*  66:    */   
/*  67:    */   public AxisAlignedBB getCollisionBox(Entity entity)
/*  68:    */   {
/*  69: 88 */     return null;
/*  70:    */   }
/*  71:    */   
/*  72:    */   public AxisAlignedBB getBoundingBox()
/*  73:    */   {
/*  74: 98 */     return null;
/*  75:    */   }
/*  76:    */   
/*  77:    */   public boolean isInRangeToRenderDist(double d)
/*  78:    */   {
/*  79:104 */     double d1 = this.boundingBox.getAverageEdgeLength() * 4.0D;
/*  80:105 */     d1 *= 64.0D;
/*  81:106 */     return d < d1 * d1;
/*  82:    */   }
/*  83:    */   
/*  84:    */   public double getEntitySpeed()
/*  85:    */   {
/*  86:117 */     double xx = this.motionX;
/*  87:118 */     double yy = this.motionY;
/*  88:119 */     double zz = this.motionZ;
/*  89:120 */     return Math.sqrt(xx * xx + yy * yy + zz * zz);
/*  90:    */   }
/*  91:    */   
/*  92:    */   public void onUpdate()
/*  93:    */   {
/*  94:130 */     if (this.worldObj.isRemote) {
/*  95:132 */       return;
/*  96:    */     }
/*  97:136 */     if (this.ticksExisted < this.lastTime)
/*  98:    */     {
/*  99:138 */       this.motionX = 0.0D;
/* 100:139 */       this.motionY = 0.0D;
/* 101:140 */       this.motionZ = 0.0D;
/* 102:141 */       this.lastSpeed = 0.0D;
/* 103:142 */       return;
/* 104:    */     }
/* 105:146 */     super.onUpdate();
/* 106:147 */     this.motionX = this.lastMotionX;
/* 107:148 */     this.motionY = this.lastMotionY;
/* 108:149 */     this.motionZ = this.lastMotionZ;
/* 109:150 */     this.speed = this.lastSpeed;
/* 110:156 */     if (this.ticksExisted > 300) {
/* 111:158 */       if (!this.worldObj.isRemote) {
/* 112:160 */         setDead();
/* 113:    */       }
/* 114:    */     }
/* 115:167 */     if (this.target == null)
/* 116:    */     {
/* 117:169 */       MovingObjectPosition movingObjectPosition = new MovingObjectPosition(this);
/* 118:170 */       Entity entity = null;
/* 119:171 */       double effectiveBoundary = 20.0D;
/* 120:172 */       double distance = 999.0D;
/* 121:173 */       Entity nearEntity = null;
/* 122:174 */       EntityPlayer nearPlayer = null;
/* 123:    */       
/* 124:176 */       List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(effectiveBoundary, effectiveBoundary, effectiveBoundary));
/* 125:178 */       if ((list != null) && (list.size() > 0)) {
/* 126:180 */         for (int j1 = 0; j1 < list.size(); j1++)
/* 127:    */         {
/* 128:182 */           entity = (Entity)list.get(j1);
/* 129:183 */           if (entity != null) {
/* 130:185 */             movingObjectPosition = new MovingObjectPosition(entity);
/* 131:    */           }
/* 132:187 */           if ((entity instanceof EntityPlayer)) {
/* 133:189 */             if (distance > entity.getDistanceToEntity(this))
/* 134:    */             {
/* 135:191 */               distance = entity.getDistanceToEntity(this);
/* 136:192 */               nearPlayer = (EntityPlayer)entity;
/* 137:    */             }
/* 138:    */           }
/* 139:    */         }
/* 140:    */       }
/* 141:198 */       if (nearPlayer != null)
/* 142:    */       {
/* 143:200 */         if (check(nearPlayer)) {
/* 144:202 */           this.target = nearPlayer;
/* 145:    */         }
/* 146:    */       }
/* 147:207 */       else if (!this.worldObj.isRemote) {
/* 148:209 */         setDead();
/* 149:    */       }
/* 150:    */     }
/* 151:214 */     if (this.target != null) {
/* 152:216 */       moveToPlayer(this.target);
/* 153:    */     }
/* 154:219 */     if (!this.worldObj.isRemote) {
/* 155:221 */       setPosition(this.posX, this.posY, this.posZ);
/* 156:    */     }
/* 157:224 */     if (this.ticksExisted > this.lastTime)
/* 158:    */     {
/* 159:226 */       this.lastMotionX = this.motionX;
/* 160:227 */       this.lastMotionY = this.motionY;
/* 161:228 */       this.lastMotionZ = this.motionZ;
/* 162:229 */       this.lastSpeed = this.speed;
/* 163:    */     }
/* 164:    */   }
/* 165:    */   
/* 166:    */   protected boolean check(EntityPlayer player)
/* 167:    */   {
/* 168:236 */     return true;
/* 169:    */   }
/* 170:    */   
/* 171:    */   protected void moveToPlayer(EntityPlayer nearPlayer)
/* 172:    */   {
/* 173:241 */     if (this.speed < this.limitSpeed)
/* 174:    */     {
/* 175:243 */       if (!this.worldObj.isRemote) {
/* 176:245 */         this.speed += 0.1D;
/* 177:    */       }
/* 178:    */     }
/* 179:    */     else {
/* 180:250 */       this.speed = this.limitSpeed;
/* 181:    */     }
/* 182:254 */     this.rotationPitch = ((float)Math.atan2(nearPlayer.posY + nearPlayer.getEyeHeight() - this.posY, Math.sqrt((nearPlayer.posX - this.posX) * (nearPlayer.posX - this.posX) + (nearPlayer.posZ - this.posZ) * (nearPlayer.posZ - this.posZ))) / 3.141593F * 180.0F);
/* 183:    */     
/* 184:    */ 
/* 185:257 */     Vec3 vec = THShotLib.angle_ToPos(THShotLib.pos_Entity(this), THShotLib.pos_Living(nearPlayer));
/* 186:    */     
/* 187:259 */     this.motionX += vec.xCoord * this.limitSpeed;
/* 188:260 */     this.motionY += vec.yCoord * this.limitSpeed;
/* 189:261 */     this.motionZ += vec.zCoord * this.limitSpeed;
/* 190:    */     
/* 191:263 */     this.posX += this.motionX;
/* 192:264 */     this.posY += this.motionY;
/* 193:265 */     this.posZ += this.motionZ;
/* 194:    */   }
/* 195:    */   
/* 196:    */   public void writeEntityToNBT(NBTTagCompound nbtTagCompound) {}
/* 197:    */   
/* 198:    */   public void readEntityFromNBT(NBTTagCompound nbtTagCompound) {}
/* 199:    */   
/* 200:    */   public void onCollideWithPlayer(EntityPlayer player)
/* 201:    */   {
/* 202:286 */     if ((!this.worldObj.isRemote) && (player.inventory.addItemStackToInventory(new ItemStack(THKaguyaItems.shot_material))))
/* 203:    */     {
/* 204:288 */       this.worldObj.playSoundAtEntity(this, "random.pop", 4.0F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 0.3F);
/* 205:289 */       player.onItemPickup(this, 1);
/* 206:    */     }
/* 207:292 */     if (!this.worldObj.isRemote) {
/* 208:294 */       setDead();
/* 209:    */     }
/* 210:    */   }
/* 211:    */   
/* 212:    */   public boolean canBeCollidedWith()
/* 213:    */   {
/* 214:305 */     return true;
/* 215:    */   }
/* 216:    */   
/* 217:    */   public float getCollisionBorderSize()
/* 218:    */   {
/* 219:315 */     return 0.1F;
/* 220:    */   }
/* 221:    */   
/* 222:    */   public float getShadowSize()
/* 223:    */   {
/* 224:320 */     return 0.0F;
/* 225:    */   }
/* 226:    */   
/* 227:    */   public float getEntityBrightness(float f)
/* 228:    */   {
/* 229:325 */     return 0.0F;
/* 230:    */   }
/* 231:    */   
/* 232:    */   public int getEntityBrightnessForRender(float f)
/* 233:    */   {
/* 234:330 */     return 15728880;
/* 235:    */   }
/* 236:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.EntityShotMaterial
 * JD-Core Version:    0.7.0.1
 */