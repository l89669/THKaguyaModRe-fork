/*   1:    */ package thKaguyaMod.entity;
/*   2:    */ 
/*   3:    */ import java.util.Random;
/*   4:    */ import net.minecraft.entity.DataWatcher;
/*   5:    */ import net.minecraft.entity.player.EntityPlayer;
/*   6:    */ import net.minecraft.entity.player.InventoryPlayer;
/*   7:    */ import net.minecraft.item.ItemStack;
/*   8:    */ import net.minecraft.nbt.NBTTagCompound;
/*   9:    */ import net.minecraft.util.MovingObjectPosition;
/*  10:    */ import net.minecraft.util.Vec3;
/*  11:    */ import net.minecraft.world.World;
/*  12:    */ import thKaguyaMod.THShotLib;
/*  13:    */ import thKaguyaMod.init.THKaguyaItems;
/*  14:    */ 
/*  15:    */ public class EntityTHItem
/*  16:    */   extends EntityShotMaterial
/*  17:    */ {
/*  18:    */   public static final byte ITEM_ID_SHOTMATERIAL = 0;
/*  19:    */   public static final byte ITEM_ID_SMALLPOWER = 1;
/*  20:    */   public static final byte ITEM_ID_BIGPOWER = 2;
/*  21:    */   public static final byte ITEM_ID_POINT = 4;
/*  22:    */   public static final byte ITEM_ID_BOMB = 10;
/*  23:    */   public static final byte ITEM_ID_EXTEND = 11;
/*  24:    */   protected byte itemId;
/*  25:    */   
/*  26:    */   public EntityTHItem(World world)
/*  27:    */   {
/*  28: 27 */     super(world);
/*  29: 28 */     this.limitSpeed = 0.5D;
/*  30: 29 */     setSize(1.2F, 1.2F);
/*  31:    */   }
/*  32:    */   
/*  33:    */   public EntityTHItem(World world, double posX, double posY, double posZ, Vec3 angle, byte itemId)
/*  34:    */   {
/*  35: 34 */     super(world, posX, posY, posZ);
/*  36:    */     
/*  37: 36 */     setItemType(itemId);
/*  38: 37 */     this.limitSpeed = 0.5D;
/*  39: 38 */     setSize(1.2F, 1.2F);
/*  40: 39 */     this.motionX = (angle.xCoord * this.limitSpeed);
/*  41: 40 */     this.motionY = (angle.yCoord * this.limitSpeed);
/*  42: 41 */     this.motionZ = (angle.zCoord * this.limitSpeed);
/*  43: 42 */     this.lastMotionX = this.motionX;
/*  44: 43 */     this.lastMotionY = this.motionY;
/*  45: 44 */     this.lastMotionZ = this.motionZ;
/*  46:    */   }
/*  47:    */   
/*  48:    */   protected void entityInit()
/*  49:    */   {
/*  50: 54 */     super.entityInit();
/*  51:    */     
/*  52: 56 */     this.dataWatcher.addObject(18, new Byte((byte)0));
/*  53:    */   }
/*  54:    */   
/*  55:    */   public void onUpdate()
/*  56:    */   {
/*  57: 67 */     if (this.worldObj.isRemote) {
/*  58: 69 */       return;
/*  59:    */     }
/*  60: 72 */     if (this.ticksExisted > 16) {
/*  61: 75 */       super.onUpdate();
/*  62:    */     }
/*  63: 78 */     if (this.target != null) {
/*  64: 80 */       return;
/*  65:    */     }
/*  66: 84 */     if (this.ticksExisted <= this.lastTime)
/*  67:    */     {
/*  68: 86 */       this.motionX = 0.0D;
/*  69: 87 */       this.motionY = 0.0D;
/*  70: 88 */       this.motionZ = 0.0D;
/*  71: 89 */       return;
/*  72:    */     }
/*  73: 93 */     this.motionX = this.lastMotionX;
/*  74: 94 */     this.motionY = this.lastMotionY;
/*  75: 95 */     this.motionZ = this.lastMotionZ;
/*  76:100 */     if (Math.abs(this.motionX) < 0.01D) {
/*  77:102 */       this.motionX = 0.0D;
/*  78:    */     } else {
/*  79:106 */       this.motionX *= 0.87D;
/*  80:    */     }
/*  81:109 */     if (Math.abs(this.motionZ) < 0.01D) {
/*  82:111 */       this.motionZ = 0.0D;
/*  83:    */     } else {
/*  84:115 */       this.motionZ *= 0.87D;
/*  85:    */     }
/*  86:119 */     if (this.motionY > -0.2D) {
/*  87:121 */       this.motionY -= 0.03D;
/*  88:    */     }
/*  89:124 */     if (this.motionY < -0.2D) {
/*  90:126 */       this.motionY = -0.2D;
/*  91:    */     }
/*  92:129 */     this.posX += this.motionX;
/*  93:130 */     this.posY += this.motionY;
/*  94:131 */     this.posZ += this.motionZ;
/*  95:    */     
/*  96:133 */     Vec3 startPos = THShotLib.pos(this.posX, this.posY, this.posZ);
/*  97:134 */     Vec3 endPos = THShotLib.pos(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
/*  98:135 */     MovingObjectPosition movingObjectPosition = this.worldObj.func_147447_a(startPos, endPos, false, true, true);
/*  99:137 */     if (movingObjectPosition != null) {
/* 100:139 */       if (movingObjectPosition.entityHit == null)
/* 101:    */       {
/* 102:141 */         switch (movingObjectPosition.sideHit)
/* 103:    */         {
/* 104:    */         case 0: 
/* 105:144 */           this.motionY = 0.0D;
/* 106:145 */           this.posY = movingObjectPosition.blockY;
/* 107:146 */           break;
/* 108:    */         case 1: 
/* 109:148 */           if (this.worldObj.isRemote) {
/* 110:    */             break;
/* 111:    */           }
/* 112:150 */           setDead(); break;
/* 113:    */         case 2: 
/* 114:154 */           this.motionZ = (-this.motionZ);
/* 115:155 */           this.posZ = movingObjectPosition.blockZ;
/* 116:    */         case 3: 
/* 117:157 */           this.motionZ = (-this.motionZ);
/* 118:158 */           this.posZ = movingObjectPosition.blockZ;
/* 119:    */         case 4: 
/* 120:160 */           this.motionX = (-this.motionX);
/* 121:161 */           this.posX = movingObjectPosition.blockX;
/* 122:    */         default: 
/* 123:163 */           this.motionX = (-this.motionX);
/* 124:164 */           this.posX = movingObjectPosition.blockX; break;
/* 125:    */         }
/* 126:    */       }
/* 127:169 */       else if ((movingObjectPosition.entityHit instanceof EntityPlayer))
/* 128:    */       {
/* 129:171 */         EntityPlayer player = (EntityPlayer)movingObjectPosition.entityHit;
/* 130:172 */         onCollideWithPlayer(player);
/* 131:    */       }
/* 132:    */     }
/* 133:178 */     this.lastMotionX = this.motionX;
/* 134:179 */     this.lastMotionY = this.motionY;
/* 135:180 */     this.lastMotionZ = this.motionZ;
/* 136:    */     
/* 137:182 */     setPosition(this.posX, this.posY, this.posZ);
/* 138:    */   }
/* 139:    */   
/* 140:    */   protected boolean check(EntityPlayer player)
/* 141:    */   {
/* 142:188 */     return player.isSneaking();
/* 143:    */   }
/* 144:    */   
/* 145:    */   protected void moveToPlayer(EntityPlayer nearPlayer)
/* 146:    */   {
/* 147:194 */     if (nearPlayer != null)
/* 148:    */     {
/* 149:196 */       this.limitSpeed = 0.3D;
/* 150:197 */       super.moveToPlayer(nearPlayer);
/* 151:    */     }
/* 152:    */   }
/* 153:    */   
/* 154:    */   public void writeEntityToNBT(NBTTagCompound nbtTagCompound) {}
/* 155:    */   
/* 156:    */   public void readEntityFromNBT(NBTTagCompound nbtTagCompound) {}
/* 157:    */   
/* 158:    */   public void onCollideWithPlayer(EntityPlayer player)
/* 159:    */   {
/* 160:219 */     boolean itemGet = false;
/* 161:221 */     if (!this.worldObj.isRemote)
/* 162:    */     {
/* 163:223 */       switch (getItemType())
/* 164:    */       {
/* 165:    */       case 1: 
/* 166:226 */         itemGet = player.inventory.addItemStackToInventory(new ItemStack(THKaguyaItems.power_item));
/* 167:227 */         break;
/* 168:    */       case 2: 
/* 169:229 */         itemGet = player.inventory.addItemStackToInventory(new ItemStack(THKaguyaItems.power_item, 1, 1));
/* 170:230 */         break;
/* 171:    */       case 4: 
/* 172:232 */         itemGet = player.inventory.addItemStackToInventory(new ItemStack(THKaguyaItems.point_item));
/* 173:233 */         break;
/* 174:    */       case 10: 
/* 175:235 */         itemGet = player.inventory.addItemStackToInventory(new ItemStack(THKaguyaItems.bomb_item));
/* 176:236 */         if (!itemGet) {
/* 177:238 */           player.dropItem(THKaguyaItems.bomb_item, 1);
/* 178:    */         }
/* 179:    */         break;
/* 180:    */       case 11: 
/* 181:242 */         itemGet = player.inventory.addItemStackToInventory(new ItemStack(THKaguyaItems.extend_item));
/* 182:243 */         if (!itemGet) {
/* 183:245 */           player.dropItem(THKaguyaItems.extend_item, 1);
/* 184:    */         }
/* 185:    */         break;
/* 186:    */       }
/* 187:252 */       if (itemGet)
/* 188:    */       {
/* 189:254 */         this.worldObj.playSoundAtEntity(this, "random.pop", 4.0F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 0.3F);
/* 190:255 */         player.onItemPickup(this, 1);
/* 191:    */       }
/* 192:258 */       setDead();
/* 193:    */     }
/* 194:    */   }
/* 195:    */   
/* 196:    */   public void setItemType(byte id)
/* 197:    */   {
/* 198:268 */     this.itemId = id;
/* 199:269 */     this.dataWatcher.updateObject(18, Byte.valueOf(id));
/* 200:    */   }
/* 201:    */   
/* 202:    */   public byte getItemType()
/* 203:    */   {
/* 204:278 */     return this.dataWatcher.getWatchableObjectByte(18);
/* 205:    */   }
/* 206:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.EntityTHItem
 * JD-Core Version:    0.7.0.1
 */