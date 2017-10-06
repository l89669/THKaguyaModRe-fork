/*   1:    */ package thKaguyaMod.entity.item;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.relauncher.Side;
/*   4:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   5:    */ import net.minecraft.entity.DataWatcher;
/*   6:    */ import net.minecraft.entity.Entity;
/*   7:    */ import net.minecraft.entity.player.EntityPlayer;
/*   8:    */ import net.minecraft.nbt.NBTTagCompound;
/*   9:    */ import net.minecraft.util.AxisAlignedBB;
/*  10:    */ import net.minecraft.world.World;
/*  11:    */ import thKaguyaMod.THKaguyaLib;
/*  12:    */ import thKaguyaMod.init.THKaguyaItems;
/*  13:    */ 
/*  14:    */ public class EntityYuukaParasol
/*  15:    */   extends Entity
/*  16:    */ {
/*  17:    */   public EntityPlayer user;
/*  18:    */   public int damage;
/*  19:    */   public int mode;
/*  20:    */   
/*  21:    */   public EntityYuukaParasol(World world)
/*  22:    */   {
/*  23: 25 */     super(world);
/*  24:    */     
/*  25: 27 */     setSize(0.1F, 0.1F);
/*  26: 28 */     this.ignoreFrustumCheck = true;
/*  27: 29 */     this.mode = getParasolMode();
/*  28:    */   }
/*  29:    */   
/*  30:    */   public EntityYuukaParasol(World world, EntityPlayer entityPlayer, int da)
/*  31:    */   {
/*  32: 35 */     this(world);
/*  33: 36 */     setParasolMode(0);
/*  34: 37 */     this.ridingEntity = entityPlayer;
/*  35: 38 */     this.user = entityPlayer;
/*  36: 39 */     this.posX = (this.user.posX - Math.sin((this.user.rotationYaw - 23.0F) / 180.0F * 3.141593F) * Math.cos(this.user.rotationPitch / 180.0F * 3.141593F) * 0.5D);
/*  37: 40 */     this.posY = (this.user.posY - Math.sin(this.user.rotationPitch / 180.0F * 3.141593F) * Math.cos(-0.4014257788658142D) * 0.5D + this.user.getEyeHeight() + 1.6D);
/*  38: 41 */     this.posZ = (this.user.posZ + Math.cos((this.user.rotationYaw - 23.0F) / 180.0F * 3.141593F) * Math.cos(this.user.rotationPitch / 180.0F * 3.141593F) * 0.5D);
/*  39: 42 */     setPosition(this.posX, this.posY, this.posZ);
/*  40: 43 */     setRotation(this.user.rotationYaw, 0.0F);
/*  41: 44 */     this.damage = da;
/*  42:    */   }
/*  43:    */   
/*  44:    */   protected void entityInit()
/*  45:    */   {
/*  46: 51 */     this.dataWatcher.addObject(19, new Integer(0));
/*  47:    */   }
/*  48:    */   
/*  49:    */   @SideOnly(Side.CLIENT)
/*  50:    */   public boolean isInRangeToRenderDist(double par1)
/*  51:    */   {
/*  52: 62 */     double d1 = this.boundingBox.getAverageEdgeLength() * 4.0D;
/*  53: 63 */     d1 *= 64.0D;
/*  54: 64 */     return par1 < d1 * d1;
/*  55:    */   }
/*  56:    */   
/*  57:    */   public AxisAlignedBB getCollisionBox(Entity entity)
/*  58:    */   {
/*  59: 74 */     if (entity == this.user) {
/*  60: 76 */       return null;
/*  61:    */     }
/*  62: 78 */     return entity.boundingBox;
/*  63:    */   }
/*  64:    */   
/*  65:    */   public AxisAlignedBB getBoundingBox()
/*  66:    */   {
/*  67: 92 */     return AxisAlignedBB.getBoundingBox(this.posX - 1.0D, this.posY - 1.0D, this.posZ - 1.0D, this.posX + 1.0D, this.posY + 1.0D, this.posZ + 1.0D);
/*  68:    */   }
/*  69:    */   
/*  70:    */   public boolean canBePushed()
/*  71:    */   {
/*  72:105 */     return false;
/*  73:    */   }
/*  74:    */   
/*  75:    */   public boolean canBeCollidedWith()
/*  76:    */   {
/*  77:115 */     return true;
/*  78:    */   }
/*  79:    */   
/*  80:    */   public void onUpdate()
/*  81:    */   {
/*  82:124 */     super.onUpdate();
/*  83:127 */     if ((!this.worldObj.isRemote) && ((this.user == null) || (this.user.isDead)))
/*  84:    */     {
/*  85:129 */       THKaguyaLib.itemEffectFinish(this, this.user, THKaguyaItems.yuuka_parasol, this.damage);
/*  86:130 */       return;
/*  87:    */     }
/*  88:133 */     float posAngle = -23.0F;
/*  89:134 */     double distance = 1.0D;
/*  90:135 */     double yPos = -0.2D;
/*  91:138 */     if (this.user != null)
/*  92:    */     {
/*  93:142 */       if (getParasolMode() == 0)
/*  94:    */       {
/*  95:145 */         distance = 0.5D;
/*  96:146 */         this.rotationPitch = (-this.user.rotationPitch);
/*  97:    */       }
/*  98:148 */       else if (getParasolMode() == 1)
/*  99:    */       {
/* 100:150 */         posAngle = -23.0F;
/* 101:151 */         this.rotationPitch = (-this.user.rotationPitch + 40.0F);
/* 102:152 */         yPos = -0.2D;
/* 103:    */       }
/* 104:    */       else
/* 105:    */       {
/* 106:156 */         posAngle = -23.0F;
/* 107:157 */         this.rotationPitch = (-this.user.rotationPitch - 90.0F);
/* 108:158 */         distance = 0.7D;
/* 109:159 */         yPos = 0.2D;
/* 110:    */       }
/* 111:163 */       if (getParasolMode() == 0)
/* 112:    */       {
/* 113:166 */         if ((this.user.isSneaking()) && (this.ticksExisted > 10))
/* 114:    */         {
/* 115:168 */           setParasolMode(1);
/* 116:169 */           this.ticksExisted = 0;
/* 117:    */         }
/* 118:171 */         this.user.fallDistance *= 0.7F;
/* 119:    */       }
/* 120:173 */       else if (getParasolMode() == 1)
/* 121:    */       {
/* 122:175 */         if ((this.user.isSneaking()) && (this.ticksExisted > 10))
/* 123:    */         {
/* 124:177 */           setParasolMode(2);
/* 125:178 */           this.ticksExisted = 0;
/* 126:    */         }
/* 127:    */       }
/* 128:183 */       else if ((this.user.isSneaking()) && (this.ticksExisted > 10))
/* 129:    */       {
/* 130:185 */         THKaguyaLib.itemEffectFinish(this, this.user, THKaguyaItems.yuuka_parasol, this.damage);
/* 131:    */       }
/* 132:188 */       this.rotationYaw = this.user.rotationYaw;
/* 133:    */       
/* 134:    */ 
/* 135:    */ 
/* 136:    */ 
/* 137:193 */       this.motionX = (this.posX - this.prevPosX);
/* 138:194 */       this.motionY = (this.posY - this.prevPosY);
/* 139:195 */       this.motionZ = (this.posZ - this.prevPosZ);
/* 140:    */       
/* 141:197 */       this.posX = (this.user.posX - Math.sin((this.user.rotationYaw + posAngle) / 180.0F * 3.141593F) * Math.cos(this.user.rotationPitch / 180.0F * 3.141593F) * distance);
/* 142:198 */       this.posY = (this.user.posY - Math.sin(this.user.rotationPitch / 180.0F * 3.141593F) * Math.cos(posAngle / 180.0F * 3.141593F) * distance + this.user.getEyeHeight() + 1.6D);
/* 143:199 */       this.posZ = (this.user.posZ + Math.cos((this.user.rotationYaw + posAngle) / 180.0F * 3.141593F) * Math.cos(this.user.rotationPitch / 180.0F * 3.141593F) * distance);
/* 144:    */       
/* 145:    */ 
/* 146:    */ 
/* 147:    */ 
/* 148:    */ 
/* 149:    */ 
/* 150:206 */       this.posX += this.motionX;
/* 151:207 */       this.posY += this.motionY;
/* 152:208 */       this.posZ += this.motionZ;
/* 153:    */       
/* 154:210 */       setPosition(this.posX, this.posY, this.posZ);
/* 155:    */     }
/* 156:214 */     if ((!this.worldObj.isRemote) && (this.damage >= 60)) {
/* 157:216 */       setDead();
/* 158:    */     }
/* 159:219 */     setRotation(this.rotationYaw, this.rotationPitch);
/* 160:    */   }
/* 161:    */   
/* 162:    */   public void updateRidden()
/* 163:    */   {
/* 164:227 */     if (this.ridingEntity != null)
/* 165:    */     {
/* 166:229 */       float posAngle = (float)this.motionY * -92.0F;
/* 167:230 */       double distance = 1.0D;
/* 168:231 */       double yPos = -0.8D;
/* 169:233 */       if (getParasolMode() == 0) {
/* 170:235 */         easyFalling();
/* 171:    */       }
/* 172:237 */       this.ridingEntity.motionX *= 0.95D;
/* 173:238 */       this.ridingEntity.motionZ *= 0.95D;
/* 174:    */       
/* 175:    */ 
/* 176:    */ 
/* 177:    */ 
/* 178:    */ 
/* 179:    */ 
/* 180:    */ 
/* 181:    */ 
/* 182:    */ 
/* 183:    */ 
/* 184:    */ 
/* 185:    */ 
/* 186:    */ 
/* 187:    */ 
/* 188:    */ 
/* 189:    */ 
/* 190:    */ 
/* 191:    */ 
/* 192:    */ 
/* 193:258 */       this.motionY = this.ridingEntity.motionY;
/* 194:259 */       this.motionX = this.ridingEntity.motionX;
/* 195:260 */       this.motionZ = this.ridingEntity.motionZ;
/* 196:    */     }
/* 197:280 */     else if (!this.worldObj.isRemote)
/* 198:    */     {
/* 199:282 */       THKaguyaLib.itemEffectFinish(this, this.user, THKaguyaItems.yuuka_parasol, this.damage);
/* 200:    */     }
/* 201:    */   }
/* 202:    */   
/* 203:    */   protected void easyFalling()
/* 204:    */   {
/* 205:289 */     if (this.ridingEntity.motionY < 0.0D) {
/* 206:291 */       this.ridingEntity.motionY *= 0.7D;
/* 207:    */     }
/* 208:    */   }
/* 209:    */   
/* 210:    */   protected void writeEntityToNBT(NBTTagCompound nbtTagCompound)
/* 211:    */   {
/* 212:326 */     nbtTagCompound.setInteger("damage", this.damage);
/* 213:    */   }
/* 214:    */   
/* 215:    */   protected void readEntityFromNBT(NBTTagCompound nbtTagCompound)
/* 216:    */   {
/* 217:336 */     this.damage = nbtTagCompound.getInteger("damage");
/* 218:    */   }
/* 219:    */   
/* 220:    */   public void setParasolMode(int mode)
/* 221:    */   {
/* 222:345 */     this.dataWatcher.updateObject(19, Integer.valueOf(mode));
/* 223:    */   }
/* 224:    */   
/* 225:    */   public int getParasolMode()
/* 226:    */   {
/* 227:354 */     return this.dataWatcher.getWatchableObjectInt(19);
/* 228:    */   }
/* 229:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.item.EntityYuukaParasol
 * JD-Core Version:    0.7.0.1
 */