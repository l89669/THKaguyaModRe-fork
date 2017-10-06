/*   1:    */ package thKaguyaMod.gui;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.common.eventhandler.EventBus;
/*   4:    */ import net.minecraft.entity.player.EntityPlayer;
/*   5:    */ import net.minecraft.entity.player.InventoryPlayer;
/*   6:    */ import net.minecraft.inventory.IInventory;
/*   7:    */ import net.minecraft.inventory.Slot;
/*   8:    */ import net.minecraft.item.Item;
/*   9:    */ import net.minecraft.item.ItemStack;
/*  10:    */ import net.minecraft.nbt.NBTTagCompound;
/*  11:    */ import net.minecraftforge.common.MinecraftForge;
/*  12:    */ import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
/*  13:    */ import thKaguyaMod.init.THKaguyaConfig;
/*  14:    */ 
/*  15:    */ public class SlotDanmakuResult
/*  16:    */   extends Slot
/*  17:    */ {
/*  18:    */   private final IInventory craftMaterial;
/*  19:    */   private final IInventory craftMatrix;
/*  20:    */   private final IInventory craftNum;
/*  21:    */   private final IInventory craftSpeed;
/*  22:    */   private final IInventory craftCopy;
/*  23:    */   private final IInventory craftGravity;
/*  24:    */   private final IInventory craftSpecial;
/*  25:    */   private EntityPlayer thePlayer;
/*  26:    */   private int amountCrafted;
/*  27:    */   
/*  28:    */   public SlotDanmakuResult(EntityPlayer player, IInventory material, IInventory formInventory, IInventory numInventory, IInventory speed, IInventory copy, IInventory gravity, IInventory special, IInventory iInventoryResult, int index, int x, int y)
/*  29:    */   {
/*  30: 30 */     super(iInventoryResult, index, x, y);
/*  31: 31 */     this.thePlayer = player;
/*  32: 32 */     this.craftMaterial = material;
/*  33: 33 */     this.craftMatrix = formInventory;
/*  34: 34 */     this.craftNum = numInventory;
/*  35: 35 */     this.craftSpeed = speed;
/*  36: 36 */     this.craftCopy = copy;
/*  37: 37 */     this.craftGravity = gravity;
/*  38: 38 */     this.craftSpecial = special;
/*  39:    */   }
/*  40:    */   
/*  41:    */   public ItemStack decrStackSize(int par1)
/*  42:    */   {
/*  43: 49 */     if (getHasStack()) {
/*  44: 51 */       this.amountCrafted += Math.min(par1, getStack().stackSize);
/*  45:    */     }
/*  46: 54 */     return super.decrStackSize(par1);
/*  47:    */   }
/*  48:    */   
/*  49:    */   public boolean isItemValid(ItemStack itemStack)
/*  50:    */   {
/*  51: 60 */     return false;
/*  52:    */   }
/*  53:    */   
/*  54:    */   public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack)
/*  55:    */   {
/*  56: 79 */     onCrafting(par2ItemStack);
/*  57:    */     
/*  58: 81 */     int copyNum = 1;
/*  59: 82 */     int oriNum = 1;
/*  60: 84 */     if (this.craftCopy.getStackInSlot(0) != null) {
/*  61: 86 */       copyNum += this.craftCopy.getStackInSlot(0).stackSize;
/*  62:    */     }
/*  63: 89 */     if (this.craftMaterial.getStackInSlot(0) != null) {
/*  64: 91 */       oriNum = this.craftMaterial.getStackInSlot(0).stackSize;
/*  65:    */     }
/*  66: 94 */     if (copyNum > oriNum) {
/*  67: 96 */       copyNum = oriNum;
/*  68:    */     }
/*  69:114 */     for (int i = 0; i < this.craftMatrix.getSizeInventory(); i++)
/*  70:    */     {
/*  71:116 */       ItemStack itemstack1 = this.craftMatrix.getStackInSlot(i);
/*  72:118 */       if (itemstack1 != null)
/*  73:    */       {
/*  74:120 */         this.craftMatrix.decrStackSize(i, 1);
/*  75:122 */         if (itemstack1.getItem().hasContainerItem())
/*  76:    */         {
/*  77:124 */           ItemStack itemstack2 = itemstack1;
/*  78:126 */           if ((itemstack2.isItemStackDamageable()) && (itemstack2.getItemDamage() > itemstack2.getMaxDamage()))
/*  79:    */           {
/*  80:128 */             MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(this.thePlayer, itemstack2));
/*  81:129 */             itemstack2 = null;
/*  82:    */           }
/*  83:132 */           if ((itemstack2 != null) && ((!itemstack1.getItem().doesContainerItemLeaveCraftingGrid(itemstack1)) || (!this.thePlayer.inventory.addItemStackToInventory(itemstack2)))) {
/*  84:134 */             if (this.craftMatrix.getStackInSlot(i) == null) {
/*  85:136 */               this.craftMatrix.setInventorySlotContents(i, itemstack2);
/*  86:    */             } else {
/*  87:141 */               this.thePlayer.dropItem(itemstack2.getItem(), itemstack2.getItemDamage());
/*  88:    */             }
/*  89:    */           }
/*  90:    */         }
/*  91:    */       }
/*  92:    */     }
/*  93:148 */     int usePoint = THKaguyaConfig.shotMaxNumber;
/*  94:150 */     if (this.craftMaterial.getStackInSlot(0) != null)
/*  95:    */     {
/*  96:152 */       NBTTagCompound nbt = this.craftMaterial.getStackInSlot(0).getTagCompound();
/*  97:153 */       if (nbt != null)
/*  98:    */       {
/*  99:157 */         int baseNum = nbt.getShort("Number");
/* 100:160 */         if (this.craftNum.getStackInSlot(0) != null) {
/* 101:162 */           if (this.craftNum.getStackInSlot(0).stackSize > usePoint - baseNum) {
/* 102:164 */             usePoint -= baseNum;
/* 103:    */           }
/* 104:    */         }
/* 105:    */       }
/* 106:170 */       else if (this.craftNum.getStackInSlot(0) != null)
/* 107:    */       {
/* 108:172 */         int numNum = this.craftNum.getStackInSlot(0).stackSize;
/* 109:173 */         if (numNum > usePoint) {
/* 110:175 */           usePoint--;
/* 111:    */         } else {
/* 112:179 */           usePoint = numNum;
/* 113:    */         }
/* 114:    */       }
/* 115:    */     }
/* 116:184 */     slotUseProcess(this.craftNum, usePoint);
/* 117:    */     
/* 118:186 */     usePoint = 64;
/* 119:188 */     if (this.craftMaterial.getStackInSlot(0) != null)
/* 120:    */     {
/* 121:190 */       NBTTagCompound nbt = this.craftMaterial.getStackInSlot(0).getTagCompound();
/* 122:191 */       if (nbt != null)
/* 123:    */       {
/* 124:193 */         int baseSpeed = 0;
/* 125:194 */         baseSpeed = nbt.getByte("Speed");
/* 126:196 */         if (this.craftSpeed.getStackInSlot(0) != null) {
/* 127:198 */           if (this.craftSpeed.getStackInSlot(0).stackSize > 64 - baseSpeed) {
/* 128:200 */             usePoint = 64 - baseSpeed;
/* 129:    */           }
/* 130:    */         }
/* 131:    */       }
/* 132:    */     }
/* 133:205 */     slotUseProcess(this.craftSpeed, usePoint);
/* 134:    */     
/* 135:207 */     usePoint = 16;
/* 136:209 */     if (this.craftMaterial.getStackInSlot(0) != null)
/* 137:    */     {
/* 138:211 */       NBTTagCompound nbt = this.craftMaterial.getStackInSlot(0).getTagCompound();
/* 139:212 */       if (nbt != null)
/* 140:    */       {
/* 141:214 */         int baseGravity = 0;
/* 142:215 */         baseGravity = nbt.getByte("Gravity");
/* 143:217 */         if (this.craftGravity.getStackInSlot(0) != null) {
/* 144:219 */           if (this.craftGravity.getStackInSlot(0).stackSize > 16 - baseGravity) {
/* 145:221 */             usePoint = 16 - baseGravity;
/* 146:    */           }
/* 147:    */         }
/* 148:    */       }
/* 149:    */     }
/* 150:226 */     slotUseProcess(this.craftGravity, usePoint);
/* 151:    */     
/* 152:228 */     usePoint = 4;
/* 153:230 */     if (this.craftMaterial.getStackInSlot(0) != null)
/* 154:    */     {
/* 155:232 */       NBTTagCompound nbt = this.craftMaterial.getStackInSlot(0).getTagCompound();
/* 156:233 */       if (nbt != null)
/* 157:    */       {
/* 158:235 */         int baseBound = 0;
/* 159:236 */         baseBound = nbt.getInteger("Special") - 40 + 1;
/* 160:238 */         if (this.craftSpecial.getStackInSlot(0) != null) {
/* 161:240 */           if (this.craftSpecial.getStackInSlot(0).stackSize > 4 - baseBound) {
/* 162:242 */             usePoint = 4 - baseBound;
/* 163:    */           }
/* 164:    */         }
/* 165:    */       }
/* 166:    */     }
/* 167:247 */     slotUseProcess(this.craftSpecial, usePoint);
/* 168:    */     
/* 169:249 */     slotUseProcess(this.craftMaterial, copyNum);
/* 170:250 */     slotUseProcess(this.craftCopy, copyNum - 1);
/* 171:    */   }
/* 172:    */   
/* 173:    */   public void slotUseProcess(IInventory inventory, int useNum)
/* 174:    */   {
/* 175:259 */     ItemStack itemstack1 = inventory.getStackInSlot(0);
/* 176:261 */     if (itemstack1 != null)
/* 177:    */     {
/* 178:263 */       inventory.decrStackSize(0, useNum);
/* 179:265 */       if (itemstack1.getItem().hasContainerItem())
/* 180:    */       {
/* 181:268 */         ItemStack itemstack2 = itemstack1.getItem().getContainerItem(itemstack1);
/* 182:270 */         if ((itemstack2.isItemStackDamageable()) && (itemstack2.getItemDamage() > itemstack2.getMaxDamage()))
/* 183:    */         {
/* 184:272 */           MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(this.thePlayer, itemstack2));
/* 185:273 */           itemstack2 = null;
/* 186:    */         }
/* 187:276 */         if ((itemstack2 != null) && ((!itemstack1.getItem().doesContainerItemLeaveCraftingGrid(itemstack1)) || (!this.thePlayer.inventory.addItemStackToInventory(itemstack2)))) {
/* 188:278 */           if (inventory.getStackInSlot(0) == null) {
/* 189:280 */             inventory.setInventorySlotContents(0, itemstack2);
/* 190:    */           } else {
/* 191:285 */             this.thePlayer.dropItem(itemstack2.getItem(), itemstack2.getItemDamage());
/* 192:    */           }
/* 193:    */         }
/* 194:    */       }
/* 195:    */     }
/* 196:    */   }
/* 197:    */   
/* 198:    */   public int getSlotStackLimit()
/* 199:    */   {
/* 200:295 */     return 64;
/* 201:    */   }
/* 202:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.gui.SlotDanmakuResult
 * JD-Core Version:    0.7.0.1
 */