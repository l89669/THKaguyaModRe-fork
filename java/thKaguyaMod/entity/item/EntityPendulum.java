/*   1:    */ package thKaguyaMod.entity.item;
/*   2:    */ 
/*   3:    */ import net.minecraft.block.Block;
/*   4:    */ import net.minecraft.entity.DataWatcher;
/*   5:    */ import net.minecraft.entity.Entity;
/*   6:    */ import net.minecraft.entity.player.EntityPlayer;
/*   7:    */ import net.minecraft.entity.player.InventoryPlayer;
/*   8:    */ import net.minecraft.entity.player.PlayerCapabilities;
/*   9:    */ import net.minecraft.item.ItemStack;
/*  10:    */ import net.minecraft.nbt.NBTTagCompound;
/*  11:    */ import net.minecraft.util.StatCollector;
/*  12:    */ import net.minecraft.world.World;
/*  13:    */ import thKaguyaMod.THKaguyaLib;
/*  14:    */ import thKaguyaMod.init.THKaguyaItems;
/*  15:    */ 
/*  16:    */ public class EntityPendulum
/*  17:    */   extends Entity
/*  18:    */ {
/*  19:    */   public EntityPlayer user;
/*  20:    */   private int mode;
/*  21:    */   private int searchBlockID;
/*  22:    */   private int searchCount;
/*  23:    */   private int searchLength;
/*  24:    */   private int searchX;
/*  25:    */   private int searchY;
/*  26:    */   private int searchZ;
/*  27:    */   private int searchMaxX;
/*  28:    */   private int searchMaxY;
/*  29:    */   private int searchMaxZ;
/*  30:    */   private float pendulumRotation;
/*  31:    */   private int count;
/*  32:    */   private String searchBlockName;
/*  33:    */   private ItemStack searchItem;
/*  34:    */   
/*  35:    */   public EntityPendulum(World world)
/*  36:    */   {
/*  37: 37 */     super(world);
/*  38: 38 */     this.preventEntitySpawning = true;
/*  39: 39 */     setSize(0.1F, 0.1F);
/*  40: 40 */     this.yOffset = 0.0F;
/*  41:    */     
/*  42: 42 */     this.searchBlockID = 0;
/*  43: 43 */     this.mode = 0;
/*  44: 44 */     this.searchCount = 0;
/*  45: 45 */     this.pendulumRotation = 0.0F;
/*  46:    */   }
/*  47:    */   
/*  48:    */   public EntityPendulum(World world, EntityPlayer player, int pmode, String name, ItemStack itemStack)
/*  49:    */   {
/*  50: 50 */     this(world);
/*  51:    */     
/*  52: 52 */     this.user = player;
/*  53: 53 */     THKaguyaLib.itemEffectFollowUser(this, this.user, 1.2D, -30.0F, false, this.user.getEyeHeight() - 0.5D);
/*  54:    */     
/*  55:    */ 
/*  56:    */ 
/*  57:    */ 
/*  58:    */ 
/*  59: 59 */     this.mode = pmode;
/*  60: 60 */     if (this.mode == 1) {
/*  61: 62 */       this.searchLength = 24;
/*  62:    */     } else {
/*  63: 66 */       this.searchLength = 12;
/*  64:    */     }
/*  65: 73 */     this.searchBlockName = name;
/*  66: 74 */     this.searchItem = itemStack;
/*  67:    */   }
/*  68:    */   
/*  69:    */   protected void entityInit()
/*  70:    */   {
/*  71: 81 */     this.dataWatcher.addObject(16, new Integer(0));
/*  72: 82 */     this.dataWatcher.addObject(17, new Integer(0));
/*  73:    */   }
/*  74:    */   
/*  75:    */   public boolean canBePushed()
/*  76:    */   {
/*  77: 88 */     return false;
/*  78:    */   }
/*  79:    */   
/*  80:    */   private void finish()
/*  81:    */   {
/*  82:102 */     if ((this.user instanceof EntityPlayer))
/*  83:    */     {
/*  84:104 */       EntityPlayer entityPlayer = this.user;
/*  85:105 */       if (entityPlayer.capabilities.isCreativeMode)
/*  86:    */       {
/*  87:107 */         setDead();
/*  88:108 */         return;
/*  89:    */       }
/*  90:    */     }
/*  91:111 */     if (this.user != null)
/*  92:    */     {
/*  93:113 */       if (!this.user.inventory.addItemStackToInventory(new ItemStack(THKaguyaItems.nazrin_pendulum, 1))) {
/*  94:117 */         if (!this.worldObj.isRemote) {
/*  95:120 */           this.user.dropItem(THKaguyaItems.nazrin_pendulum, 1);
/*  96:    */         }
/*  97:    */       }
/*  98:    */     }
/*  99:127 */     else if (!this.worldObj.isRemote) {
/* 100:130 */       dropItem(THKaguyaItems.nazrin_pendulum, 1);
/* 101:    */     }
/* 102:133 */     setDead();
/* 103:    */   }
/* 104:    */   
/* 105:    */   public void onUpdate()
/* 106:    */   {
/* 107:142 */     super.onUpdate();
/* 108:144 */     if ((!this.worldObj.isRemote) && (this.user == null)) {
/* 109:146 */       finish();
/* 110:    */     }
/* 111:149 */     if ((!this.worldObj.isRemote) && (this.user != null))
/* 112:    */     {
/* 113:151 */       if ((this.ticksExisted > 10) && (this.user.isSneaking())) {
/* 114:153 */         finish();
/* 115:    */       }
/* 116:155 */       if (this.user.hurtTime > 0) {
/* 117:157 */         finish();
/* 118:    */       }
/* 119:    */     }
/* 120:162 */     if ((this.count % 60 == 0) && (!this.worldObj.isRemote))
/* 121:    */     {
/* 122:164 */       this.searchCount = 0;
/* 123:165 */       this.searchX = ((int)this.posX - this.searchLength);
/* 124:166 */       this.searchZ = ((int)this.posZ - this.searchLength);
/* 125:167 */       this.searchY = ((int)this.posY - this.searchLength);
/* 126:168 */       this.searchMaxX = (this.searchX + this.searchLength * 2 + 1);
/* 127:169 */       this.searchMaxY = (this.searchY + this.searchLength * 2 + 1);
/* 128:170 */       this.searchMaxZ = (this.searchZ + this.searchLength * 2 + 1);
/* 129:174 */       for (int si = this.searchX; si < this.searchMaxX; si++) {
/* 130:176 */         for (int sk = this.searchZ; sk < this.searchMaxZ; sk++) {
/* 131:178 */           for (int sj = this.searchY; sj < this.searchMaxY; sj++)
/* 132:    */           {
/* 133:180 */             String searchName = StatCollector.translateToLocal(this.worldObj.getBlock(si, sj, sk).getUnlocalizedName() + ".name");
/* 134:183 */             if (searchName.equals(this.searchBlockName)) {
/* 135:185 */               this.searchCount += 1;
/* 136:    */             }
/* 137:    */           }
/* 138:    */         }
/* 139:    */       }
/* 140:194 */       if (this.searchCount > 32) {
/* 141:196 */         this.searchCount = 32;
/* 142:    */       }
/* 143:198 */       setSearchCount(this.searchCount);
/* 144:    */     }
/* 145:201 */     if (getSearchCount() != 0)
/* 146:    */     {
/* 147:205 */       setSearchAngle(getSearchAngle() + getSearchCount() / 3.0F);
/* 148:    */       
/* 149:207 */       this.rotationYaw += 3.0F;
/* 150:    */     }
/* 151:209 */     if (this.user != null) {
/* 152:211 */       THKaguyaLib.itemEffectFollowUser(this, this.user, 1.2D, -30.0F, false, this.user.getEyeHeight() - 0.5D);
/* 153:    */     }
/* 154:213 */     this.count += 1;
/* 155:    */   }
/* 156:    */   
/* 157:    */   public float getShadowSize()
/* 158:    */   {
/* 159:218 */     return 0.5F;
/* 160:    */   }
/* 161:    */   
/* 162:    */   protected void writeEntityToNBT(NBTTagCompound nbtTagCompound) {}
/* 163:    */   
/* 164:    */   protected void readEntityFromNBT(NBTTagCompound nbtTagCompound) {}
/* 165:    */   
/* 166:    */   public void setSearchAngle(float angle)
/* 167:    */   {
/* 168:240 */     this.dataWatcher.updateObject(16, Integer.valueOf((int)(angle * 100.0F)));
/* 169:    */   }
/* 170:    */   
/* 171:    */   public float getSearchAngle()
/* 172:    */   {
/* 173:247 */     return this.dataWatcher.getWatchableObjectInt(16) / 100.0F;
/* 174:    */   }
/* 175:    */   
/* 176:    */   public void setSearchCount(int count)
/* 177:    */   {
/* 178:253 */     this.dataWatcher.updateObject(17, Integer.valueOf(count));
/* 179:    */   }
/* 180:    */   
/* 181:    */   public int getSearchCount()
/* 182:    */   {
/* 183:259 */     return this.dataWatcher.getWatchableObjectInt(17);
/* 184:    */   }
/* 185:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.item.EntityPendulum
 * JD-Core Version:    0.7.0.1
 */