/*   1:    */ package thKaguyaMod.entity.item;
/*   2:    */ 
/*   3:    */ import java.util.List;
/*   4:    */ import net.minecraft.entity.Entity;
/*   5:    */ import net.minecraft.entity.EntityLivingBase;
/*   6:    */ import net.minecraft.entity.item.EntityItemFrame;
/*   7:    */ import net.minecraft.entity.item.EntityPainting;
/*   8:    */ import net.minecraft.entity.monster.EntityCreeper;
/*   9:    */ import net.minecraft.entity.monster.EntityGhast;
/*  10:    */ import net.minecraft.entity.passive.EntityTameable;
/*  11:    */ import net.minecraft.entity.player.EntityPlayer;
/*  12:    */ import net.minecraft.entity.player.EntityPlayerMP;
/*  13:    */ import net.minecraft.nbt.NBTTagCompound;
/*  14:    */ import net.minecraft.network.NetHandlerPlayServer;
/*  15:    */ import net.minecraft.util.AxisAlignedBB;
/*  16:    */ import net.minecraft.util.MovingObjectPosition;
/*  17:    */ import net.minecraft.world.World;
/*  18:    */ import thKaguyaMod.THKaguyaLib;
/*  19:    */ import thKaguyaMod.entity.spellcard.EntitySpellCard;
/*  20:    */ import thKaguyaMod.init.THKaguyaItems;
/*  21:    */ 
/*  22:    */ public class EntitySakuyaStopWatch
/*  23:    */   extends Entity
/*  24:    */ {
/*  25:    */   public EntityLivingBase user;
/*  26:    */   private int count;
/*  27:    */   private float watchRotation;
/*  28:    */   
/*  29:    */   public EntitySakuyaStopWatch(World world)
/*  30:    */   {
/*  31: 32 */     super(world);
/*  32:    */     
/*  33: 34 */     this.ignoreFrustumCheck = true;
/*  34: 35 */     this.preventEntitySpawning = true;
/*  35: 36 */     setSize(0.2F, 0.2F);
/*  36: 37 */     this.yOffset = 0.0F;
/*  37: 38 */     shouldRenderInPass(-2);
/*  38:    */   }
/*  39:    */   
/*  40:    */   public EntitySakuyaStopWatch(World world, EntityLivingBase userLiving)
/*  41:    */   {
/*  42: 44 */     this(world);
/*  43:    */     
/*  44: 46 */     this.user = userLiving;
/*  45: 47 */     THKaguyaLib.itemEffectFollowUser(this, this.user, 1.2D, -30.0F);
/*  46:    */   }
/*  47:    */   
/*  48:    */   protected void entityInit() {}
/*  49:    */   
/*  50:    */   public boolean canBePushed()
/*  51:    */   {
/*  52: 62 */     return false;
/*  53:    */   }
/*  54:    */   
/*  55:    */   public boolean canBeCollidedWith()
/*  56:    */   {
/*  57: 71 */     return false;
/*  58:    */   }
/*  59:    */   
/*  60:    */   public void onUpdate()
/*  61:    */   {
/*  62: 80 */     super.onUpdate();
/*  63: 83 */     if (this.ticksExisted > 40)
/*  64:    */     {
/*  65: 86 */       if (!this.worldObj.isRemote) {
/*  66: 88 */         setDead();
/*  67:    */       }
/*  68: 90 */       return;
/*  69:    */     }
/*  70: 94 */     if (this.user == null)
/*  71:    */     {
/*  72: 97 */       if (!this.worldObj.isRemote) {
/*  73: 99 */         setDead();
/*  74:    */       }
/*  75:101 */       return;
/*  76:    */     }
/*  77:107 */     if (this.user.hurtTime > 0)
/*  78:    */     {
/*  79:110 */       if (!this.worldObj.isRemote) {
/*  80:112 */         setDead();
/*  81:    */       }
/*  82:114 */       return;
/*  83:    */     }
/*  84:119 */     THKaguyaLib.itemEffectFollowUser(this, this.user, 1.2D, -30.0F);
/*  85:    */     
/*  86:    */ 
/*  87:122 */     int playerCount = 0;
/*  88:123 */     MovingObjectPosition movingObjectPosition = new MovingObjectPosition(this);
/*  89:124 */     Entity entity = null;
/*  90:125 */     boolean dubbleWatch = false;
/*  91:126 */     boolean pass = false;
/*  92:127 */     double effectiveBoundary = 40.0D;
/*  93:    */     
/*  94:129 */     List<?> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(effectiveBoundary, effectiveBoundary, effectiveBoundary));
/*  95:132 */     if ((list != null) && (list.size() > 0)) {
/*  96:135 */       for (int j1 = 0; j1 < list.size(); j1++)
/*  97:    */       {
/*  98:137 */         entity = (Entity)list.get(j1);
/*  99:140 */         if (entity != null) {
/* 100:142 */           movingObjectPosition = new MovingObjectPosition(entity);
/* 101:    */         }
/* 102:146 */         if ((entity instanceof EntityPlayer)) {
/* 103:148 */           playerCount++;
/* 104:    */         }
/* 105:152 */         if ((movingObjectPosition != null) && (movingObjectPosition.entityHit != this.user) && (movingObjectPosition.entityHit.riddenByEntity != this.user) && (!(movingObjectPosition.entityHit instanceof EntityItemFrame)) && (!(movingObjectPosition.entityHit instanceof EntityPainting)))
/* 106:    */         {
/* 107:156 */           pass = false;
/* 108:158 */           if ((movingObjectPosition.entityHit instanceof EntitySakuyaWatch))
/* 109:    */           {
/* 110:160 */             EntitySakuyaWatch watch = (EntitySakuyaWatch)movingObjectPosition.entityHit;
/* 111:    */             
/* 112:162 */             THKaguyaLib.itemEffectFinish(watch, watch.userEntity, THKaguyaItems.sakuya_watch);
/* 113:163 */             dubbleWatch = true;
/* 114:    */           }
/* 115:165 */           if ((movingObjectPosition.entityHit instanceof EntitySakuyaStopWatch))
/* 116:    */           {
/* 117:167 */             EntitySakuyaStopWatch watch = (EntitySakuyaStopWatch)movingObjectPosition.entityHit;
/* 118:170 */             if (!watch.worldObj.isRemote) {
/* 119:172 */               watch.setDead();
/* 120:    */             }
/* 121:174 */             dubbleWatch = true;
/* 122:    */           }
/* 123:176 */           if ((movingObjectPosition.entityHit instanceof EntitySpellCard))
/* 124:    */           {
/* 125:178 */             EntitySpellCard spellCard = (EntitySpellCard)movingObjectPosition.entityHit;
/* 126:179 */             if ((spellCard.canMoveInTimeStop) && (spellCard.user.equals(this.user)))
/* 127:    */             {
/* 128:181 */               pass = true;
/* 129:182 */               spellCard.specialProcessInTimeStop();
/* 130:    */             }
/* 131:    */           }
/* 132:185 */           if (!pass) {
/* 133:187 */             inPrivateSquare(movingObjectPosition);
/* 134:    */           }
/* 135:    */         }
/* 136:    */       }
/* 137:    */     }
/* 138:194 */     if (playerCount == 0)
/* 139:    */     {
/* 140:197 */       if (!this.worldObj.isRemote) {
/* 141:199 */         setDead();
/* 142:    */       }
/* 143:201 */       return;
/* 144:    */     }
/* 145:205 */     if (dubbleWatch)
/* 146:    */     {
/* 147:208 */       if (!this.worldObj.isRemote) {
/* 148:210 */         setDead();
/* 149:    */       }
/* 150:212 */       return;
/* 151:    */     }
/* 152:217 */     this.count += 1;
/* 153:219 */     if (this.count % 20 == 19)
/* 154:    */     {
/* 155:221 */       this.worldObj.playSoundAtEntity(this, "random.orb", 0.5F, 6.0F);
/* 156:222 */       this.worldObj.playSoundAtEntity(this, "random.click", 0.5F, 4.0F);
/* 157:    */     }
/* 158:    */   }
/* 159:    */   
/* 160:    */   protected void writeEntityToNBT(NBTTagCompound nbtTagCompound)
/* 161:    */   {
/* 162:233 */     nbtTagCompound.setShort("count", (short)this.count);
/* 163:    */   }
/* 164:    */   
/* 165:    */   protected void readEntityFromNBT(NBTTagCompound nbtTagCompound)
/* 166:    */   {
/* 167:243 */     this.count = nbtTagCompound.getShort("count");
/* 168:    */   }
/* 169:    */   
/* 170:    */   public float getShadowSize()
/* 171:    */   {
/* 172:249 */     return 0.5F;
/* 173:    */   }
/* 174:    */   
/* 175:    */   protected void inPrivateSquare(MovingObjectPosition movingObjectPosition)
/* 176:    */   {
/* 177:256 */     Entity hitEntity = movingObjectPosition.entityHit;
/* 178:259 */     if (hitEntity.ticksExisted >= 2)
/* 179:    */     {
/* 180:264 */       hitEntity.setPosition(hitEntity.prevPosX, hitEntity.prevPosY, hitEntity.prevPosZ);
/* 181:    */       
/* 182:266 */       hitEntity.rotationYaw = hitEntity.prevRotationYaw;
/* 183:267 */       hitEntity.rotationPitch = hitEntity.prevRotationPitch;
/* 184:268 */       hitEntity.motionX = 0.0D;
/* 185:271 */       if (!hitEntity.onGround) {
/* 186:273 */         if (this.worldObj.isRemote) {
/* 187:275 */           hitEntity.motionY = -0.0D;
/* 188:    */         } else {
/* 189:279 */           hitEntity.motionY = -0.0D;
/* 190:    */         }
/* 191:    */       }
/* 192:283 */       hitEntity.motionZ = 0.0D;
/* 193:    */       
/* 194:285 */       hitEntity.setAir(0);
/* 195:    */       
/* 196:287 */       hitEntity.ticksExisted -= 1;
/* 197:    */       
/* 198:289 */       hitEntity.fallDistance -= 0.076865F;
/* 199:292 */       if ((hitEntity instanceof EntityLivingBase))
/* 200:    */       {
/* 201:294 */         EntityLivingBase living = (EntityLivingBase)hitEntity;
/* 202:295 */         living.rotationYawHead = living.prevRotationYawHead;
/* 203:    */         
/* 204:297 */         living.attackTime += 1;
/* 205:300 */         if ((living instanceof EntityCreeper))
/* 206:    */         {
/* 207:302 */           EntityCreeper entityCreeper = (EntityCreeper)living;
/* 208:    */           
/* 209:304 */           entityCreeper.setCreeperState(-1);
/* 210:    */         }
/* 211:308 */         else if ((living instanceof EntityGhast))
/* 212:    */         {
/* 213:310 */           EntityGhast entityGhast = (EntityGhast)living;
/* 214:    */           
/* 215:312 */           entityGhast.attackCounter -= 1;
/* 216:    */         }
/* 217:316 */         if ((living instanceof EntityTameable)) {
/* 218:318 */           living.motionY -= 1.0E-006D;
/* 219:    */         }
/* 220:322 */         if ((living instanceof EntityPlayerMP))
/* 221:    */         {
/* 222:324 */           EntityPlayerMP player = (EntityPlayerMP)living;
/* 223:325 */           player.playerNetServerHandler.setPlayerLocation(player.prevPosX, player.prevPosY, player.prevPosZ, player.rotationYaw, player.rotationPitch);
/* 224:    */         }
/* 225:    */       }
/* 226:    */     }
/* 227:    */   }
/* 228:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.item.EntitySakuyaStopWatch
 * JD-Core Version:    0.7.0.1
 */