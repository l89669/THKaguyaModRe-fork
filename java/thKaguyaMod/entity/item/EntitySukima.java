/*   1:    */ package thKaguyaMod.entity.item;
/*   2:    */ 
/*   3:    */ import java.util.List;
/*   4:    */ import net.minecraft.entity.DataWatcher;
/*   5:    */ import net.minecraft.entity.Entity;
/*   6:    */ import net.minecraft.entity.EntityLivingBase;
/*   7:    */ import net.minecraft.entity.player.EntityPlayer;
/*   8:    */ import net.minecraft.entity.player.EntityPlayerMP;
/*   9:    */ import net.minecraft.entity.player.InventoryPlayer;
/*  10:    */ import net.minecraft.item.Item;
/*  11:    */ import net.minecraft.item.ItemStack;
/*  12:    */ import net.minecraft.nbt.NBTTagCompound;
/*  13:    */ import net.minecraft.network.NetHandlerPlayServer;
/*  14:    */ import net.minecraft.util.AxisAlignedBB;
/*  15:    */ import net.minecraft.util.MovingObjectPosition;
/*  16:    */ import net.minecraft.util.Vec3;
/*  17:    */ import net.minecraft.world.World;
/*  18:    */ import thKaguyaMod.LaserData;
/*  19:    */ import thKaguyaMod.THShotLib;
/*  20:    */ import thKaguyaMod.entity.shot.EntityTHShot;
/*  21:    */ import thKaguyaMod.init.THKaguyaConfig;
/*  22:    */ import thKaguyaMod.init.THKaguyaItems;
/*  23:    */ 
/*  24:    */ public class EntitySukima
/*  25:    */   extends Entity
/*  26:    */ {
/*  27:    */   private int count;
/*  28:    */   private double warpPosX;
/*  29:    */   private double warpPosY;
/*  30:    */   private double warpPosZ;
/*  31:    */   public EntityLivingBase userEntity;
/*  32:    */   private float warpAngle;
/*  33:    */   private boolean warp;
/*  34:    */   private int pattern;
/*  35:    */   private int color;
/*  36:    */   
/*  37:    */   public EntitySukima(World world)
/*  38:    */   {
/*  39: 40 */     super(world);
/*  40:    */     
/*  41:    */ 
/*  42:    */ 
/*  43: 44 */     this.pattern = 0;
/*  44:    */   }
/*  45:    */   
/*  46:    */   public EntitySukima(World world, EntityLivingBase entityLivingBase, double x, double y, double z, float angle)
/*  47:    */   {
/*  48: 49 */     super(world);
/*  49:    */     
/*  50:    */ 
/*  51:    */ 
/*  52:    */ 
/*  53:    */ 
/*  54:    */ 
/*  55: 56 */     setPosition(x, y, z);
/*  56:    */     
/*  57:    */ 
/*  58:    */ 
/*  59:    */ 
/*  60:    */ 
/*  61:    */ 
/*  62:    */ 
/*  63:    */ 
/*  64:    */ 
/*  65:    */ 
/*  66:    */ 
/*  67:    */ 
/*  68:    */ 
/*  69:    */ 
/*  70:    */ 
/*  71:    */ 
/*  72:    */ 
/*  73: 74 */     setRotation(angle, 0.0F);
/*  74: 75 */     this.count = 0;
/*  75: 76 */     this.pattern = 0;
/*  76: 77 */     this.color = 1;
/*  77: 78 */     setColor(this.color);
/*  78: 79 */     setNewWarpPoint();
/*  79: 80 */     this.userEntity = entityLivingBase;
/*  80:    */   }
/*  81:    */   
/*  82:    */   public EntitySukima(World world, EntityLivingBase entityLivingBase, double x, double y, double z, int sukimaColor)
/*  83:    */   {
/*  84: 86 */     this(world);
/*  85:    */     
/*  86:    */ 
/*  87: 89 */     setPosition(x, y, z);
/*  88: 90 */     this.rotationYaw = entityLivingBase.rotationYaw;
/*  89: 91 */     this.rotationYaw = ((float)Math.atan2(x - entityLivingBase.posX, z - entityLivingBase.posZ) / 3.141593F * 180.0F);
/*  90: 92 */     setRotation(360.0F - this.rotationYaw, 0.0F);
/*  91: 93 */     this.count = 0;
/*  92: 94 */     this.pattern = 0;
/*  93: 95 */     this.color = sukimaColor;
/*  94: 96 */     setColor(this.color);
/*  95: 97 */     setNewWarpPoint();
/*  96: 98 */     this.userEntity = entityLivingBase;
/*  97:    */   }
/*  98:    */   
/*  99:    */   public EntitySukima(World world, EntityLivingBase entityLivingBase, double x, double y, double z, float angle, int sukimaColor)
/* 100:    */   {
/* 101:104 */     this(world);
/* 102:    */     
/* 103:    */ 
/* 104:107 */     setPosition(x, y, z);
/* 105:108 */     this.rotationYaw = angle;
/* 106:109 */     setRotation(angle + 180.0F, 0.0F);
/* 107:110 */     this.count = 0;
/* 108:111 */     this.pattern = 0;
/* 109:112 */     this.color = sukimaColor;
/* 110:113 */     setColor(sukimaColor);
/* 111:114 */     setNewWarpPoint();
/* 112:115 */     this.userEntity = entityLivingBase;
/* 113:    */   }
/* 114:    */   
/* 115:    */   protected boolean canTriggerWalking()
/* 116:    */   {
/* 117:126 */     return false;
/* 118:    */   }
/* 119:    */   
/* 120:    */   protected void entityInit()
/* 121:    */   {
/* 122:133 */     this.dataWatcher.addObject(16, new Integer(0));
/* 123:    */   }
/* 124:    */   
/* 125:    */   public boolean canBePushed()
/* 126:    */   {
/* 127:140 */     return false;
/* 128:    */   }
/* 129:    */   
/* 130:    */   public boolean canBeCollidedWith()
/* 131:    */   {
/* 132:148 */     return true;
/* 133:    */   }
/* 134:    */   
/* 135:    */   public void onUpdate()
/* 136:    */   {
/* 137:155 */     super.onUpdate();
/* 138:158 */     if ((getColor() == 16) || (getColor() >= 32)) {
/* 139:160 */       if (this.ticksExisted > 24) {
/* 140:162 */         setDead();
/* 141:    */       }
/* 142:    */     }
/* 143:167 */     if (getColor() == 17)
/* 144:    */     {
/* 145:169 */       if (this.ticksExisted == 5) {
/* 146:171 */         THShotLib.createLaserA(this.userEntity, this, THShotLib.pos_Entity(this), THShotLib.angle(this.rotationYaw - 180.0F, this.rotationPitch), 0.1D, 1.4D, 0.1D, THShotLib.gravity_Zero(), 
/* 147:172 */           LaserData.laser(4, 0.1F, 4.0F, 8.0F, 0, 40, 0));
/* 148:    */       }
/* 149:174 */       if (this.ticksExisted > 9) {
/* 150:176 */         setDead();
/* 151:    */       }
/* 152:178 */       return;
/* 153:    */     }
/* 154:181 */     MovingObjectPosition movingobjectposition = new MovingObjectPosition(this);
/* 155:    */     
/* 156:    */ 
/* 157:184 */     Entity entity = null;
/* 158:185 */     List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(10.0D, 10.0D, 10.0D));
/* 159:    */     
/* 160:    */ 
/* 161:    */ 
/* 162:189 */     float angleRad = this.rotationYaw / 180.0F * 3.141593F;
/* 163:190 */     double minX = this.posX - 1.0D;
/* 164:191 */     double minY = this.posY - 1.5D;
/* 165:192 */     double minZ = this.posZ - 1.0D;
/* 166:193 */     double maxX = this.posX + 1.0D;
/* 167:194 */     double maxY = this.posY + 1.0D;
/* 168:195 */     double maxZ = this.posZ + 1.0D;
/* 169:197 */     if ((list != null) && (list.size() > 0)) {
/* 170:199 */       for (int j1 = 0; j1 < list.size(); j1++)
/* 171:    */       {
/* 172:201 */         entity = (Entity)list.get(j1);
/* 173:202 */         if ((!(entity instanceof EntitySukima)) && (entity.posX > minX) && (entity.posX < maxX) && (entity.posY > minY) && (entity.posY < maxY) && (entity.posZ > minZ) && (entity.posZ < maxZ))
/* 174:    */         {
/* 175:210 */           setNewWarpPoint();
/* 176:212 */           if ((this.warp) && (entity.timeUntilPortal == 0) && (!this.worldObj.isRemote))
/* 177:    */           {
/* 178:214 */             double entityY = entity.posY;
/* 179:215 */             entity.prevPosX += this.warpPosX - entity.posX;
/* 180:216 */             entity.prevPosY += this.warpPosY - entity.posY;
/* 181:217 */             entity.prevPosZ += this.warpPosZ - entity.posZ;
/* 182:218 */             entity.posY = this.warpPosY;
/* 183:219 */             if ((entity instanceof EntityPlayerMP))
/* 184:    */             {
/* 185:221 */               EntityPlayerMP player = (EntityPlayerMP)entity;
/* 186:222 */               player.playerNetServerHandler.setPlayerLocation(this.warpPosX, this.warpPosY + entity.yOffset, this.warpPosZ, player.rotationYaw, player.rotationPitch);
/* 187:    */             }
/* 188:229 */             else if (!this.worldObj.isRemote)
/* 189:    */             {
/* 190:231 */               entity.setPosition(this.warpPosX, this.warpPosY + entity.yOffset, this.warpPosZ);
/* 191:    */             }
/* 192:234 */             if ((entity instanceof EntityTHShot))
/* 193:    */             {
/* 194:236 */               EntityTHShot shot = (EntityTHShot)entity;
/* 195:    */               
/* 196:    */ 
/* 197:239 */               Vec3 move = THShotLib.getVecFromAngle(this.warpAngle - THShotLib.getAngleAndAngleSpan(this.rotationYaw, shot.rotationYaw), shot.rotationPitch);
/* 198:240 */               shot.angle.xCoord = (-move.xCoord);
/* 199:241 */               shot.angle.yCoord = (-move.yCoord);
/* 200:242 */               shot.angle.zCoord = move.zCoord;
/* 201:243 */               double speed = shot.getSpeed();
/* 202:244 */               shot.motionX = (shot.lastShotMotionX = shot.angle.xCoord * speed);
/* 203:245 */               shot.motionY = (shot.lastShotMotionY = shot.angle.yCoord * speed);
/* 204:246 */               shot.motionZ = (shot.lastShotMotionZ = shot.angle.zCoord * speed);
/* 205:    */             }
/* 206:    */             else
/* 207:    */             {
/* 208:251 */               entity.rotationYaw += this.warpAngle;
/* 209:252 */               entity.motionX *= -Math.sin(entity.rotationYaw);
/* 210:253 */               entity.motionZ *= Math.cos(entity.rotationYaw);
/* 211:    */             }
/* 212:265 */             entity.timeUntilPortal = 40;
/* 213:266 */             this.worldObj.playSoundAtEntity(entity, THKaguyaConfig.sukimaWarpSE, 1.0F, 1.0F);
/* 214:    */           }
/* 215:    */         }
/* 216:    */       }
/* 217:    */     }
/* 218:    */   }
/* 219:    */   
/* 220:    */   public boolean interactFirst(EntityPlayer player)
/* 221:    */   {
/* 222:277 */     if (getColor() >= 16) {
/* 223:279 */       return false;
/* 224:    */     }
/* 225:281 */     ItemStack itemstack = player.inventory.getCurrentItem();
/* 226:282 */     if (!player.worldObj.isRemote) {
/* 227:285 */       if ((itemstack != null) && (itemstack.getItem() == Item.getItemById(351)))
/* 228:    */       {
/* 229:287 */         if (THKaguyaConfig.useDefaultGapSE) {
/* 230:289 */           this.worldObj.playSoundAtEntity(player, "mob.endermen.portal", 0.5F, 0.4F);
/* 231:    */         } else {
/* 232:293 */           this.worldObj.playSoundAtEntity(player, THKaguyaConfig.sukimaWarpSE, 1.0F, 1.0F);
/* 233:    */         }
/* 234:297 */         setColor(itemstack.getItemDamage());
/* 235:298 */         this.color = getColor();
/* 236:    */       }
/* 237:301 */       else if ((itemstack != null) && (itemstack.getItem() == THKaguyaItems.remorse_rod))
/* 238:    */       {
/* 239:304 */         dropItem(THKaguyaItems.gap, 1);
/* 240:305 */         setDead();
/* 241:    */       }
/* 242:307 */       else if (player.isSneaking())
/* 243:    */       {
/* 244:310 */         dropItem(THKaguyaItems.gap, 1);
/* 245:311 */         setDead();
/* 246:    */       }
/* 247:    */     }
/* 248:314 */     return true;
/* 249:    */   }
/* 250:    */   
/* 251:    */   protected void writeEntityToNBT(NBTTagCompound nbtTagCompound)
/* 252:    */   {
/* 253:323 */     nbtTagCompound.setShort("color", (short)getColor());
/* 254:    */   }
/* 255:    */   
/* 256:    */   protected void readEntityFromNBT(NBTTagCompound nbtTagCompound)
/* 257:    */   {
/* 258:332 */     setColor(nbtTagCompound.getShort("color"));
/* 259:    */   }
/* 260:    */   
/* 261:    */   public float getShadowSize()
/* 262:    */   {
/* 263:338 */     return 2.0F;
/* 264:    */   }
/* 265:    */   
/* 266:    */   public void setColor(int color)
/* 267:    */   {
/* 268:344 */     this.dataWatcher.updateObject(16, Integer.valueOf(color));
/* 269:    */   }
/* 270:    */   
/* 271:    */   public int getColor()
/* 272:    */   {
/* 273:349 */     return this.dataWatcher.getWatchableObjectInt(16);
/* 274:    */   }
/* 275:    */   
/* 276:    */   public void setNewWarpPoint()
/* 277:    */   {
/* 278:355 */     double nearDistance = 1000.0D;
/* 279:    */     
/* 280:    */ 
/* 281:358 */     EntitySukima setSukima = null;
/* 282:359 */     EntitySukima nearSukima = null;
/* 283:360 */     MovingObjectPosition movingobjectposition = new MovingObjectPosition(this);
/* 284:361 */     Entity entity = null;
/* 285:362 */     List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(128.0D, 128.0D, 128.0D));
/* 286:364 */     if ((list != null) && (list.size() > 0))
/* 287:    */     {
/* 288:366 */       for (int j1 = 0; j1 < list.size(); j1++)
/* 289:    */       {
/* 290:368 */         entity = (Entity)list.get(j1);
/* 291:369 */         if ((entity instanceof EntitySukima))
/* 292:    */         {
/* 293:371 */           setSukima = (EntitySukima)entity;
/* 294:372 */           if (getColor() % 32 == setSukima.getColor() % 32)
/* 295:    */           {
/* 296:374 */             double distance = getDistanceToEntity(entity);
/* 297:375 */             if ((distance < nearDistance) && (distance > 5.0D))
/* 298:    */             {
/* 299:377 */               nearDistance = distance;
/* 300:378 */               nearSukima = setSukima;
/* 301:    */             }
/* 302:    */           }
/* 303:    */         }
/* 304:    */       }
/* 305:383 */       if (nearSukima != null)
/* 306:    */       {
/* 307:385 */         float angleNS = nearSukima.rotationYaw / 180.0F * 3.141593F;
/* 308:386 */         this.warpPosX = (nearSukima.posX + Math.sin(angleNS) * 1.8D);
/* 309:387 */         this.warpPosY = nearSukima.posY;
/* 310:388 */         this.warpPosZ = (nearSukima.posZ - Math.cos(angleNS) * 1.8D);
/* 311:389 */         this.warpAngle = (180.0F - (this.rotationYaw % 360.0F - nearSukima.rotationYaw % 360.0F));
/* 312:    */         
/* 313:391 */         this.warp = true;
/* 314:    */       }
/* 315:    */       else
/* 316:    */       {
/* 317:395 */         this.warp = false;
/* 318:    */       }
/* 319:    */     }
/* 320:    */   }
/* 321:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.item.EntitySukima
 * JD-Core Version:    0.7.0.1
 */