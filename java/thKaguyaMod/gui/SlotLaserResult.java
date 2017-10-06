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
/*  15:    */ public class SlotLaserResult
/*  16:    */   extends Slot
/*  17:    */ {
/*  18:    */   private final IInventory craftMaterial;
/*  19:    */   private final IInventory craftMatrix;
/*  20:    */   private final IInventory craftNum;
/*  21:    */   private final IInventory craftSpeed;
/*  22:    */   private final IInventory craftCopy;
/*  23:    */   private EntityPlayer thePlayer;
/*  24:    */   private int amountCrafted;
/*  25:    */   
/*  26:    */   public SlotLaserResult(EntityPlayer player, IInventory material, IInventory formInventory, IInventory numInventory, IInventory speed, IInventory copy, IInventory iInventoryResult, int index, int x, int y)
/*  27:    */   {
/*  28: 29 */     super(iInventoryResult, index, x, y);
/*  29: 30 */     this.thePlayer = player;
/*  30: 31 */     this.craftMaterial = material;
/*  31: 32 */     this.craftMatrix = formInventory;
/*  32: 33 */     this.craftNum = numInventory;
/*  33: 34 */     this.craftSpeed = speed;
/*  34: 35 */     this.craftCopy = copy;
/*  35:    */   }
/*  36:    */   
/*  37:    */   public ItemStack decrStackSize(int par1)
/*  38:    */   {
/*  39: 48 */     if (getHasStack()) {
/*  40: 50 */       this.amountCrafted += Math.min(par1, getStack().stackSize);
/*  41:    */     }
/*  42: 53 */     return super.decrStackSize(par1);
/*  43:    */   }
/*  44:    */   
/*  45:    */   public boolean isItemValid(ItemStack itemStack)
/*  46:    */   {
/*  47: 59 */     return false;
/*  48:    */   }
/*  49:    */   
/*  50:    */   public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack)
/*  51:    */   {
/*  52: 78 */     onCrafting(par2ItemStack);
/*  53:    */     
/*  54: 80 */     int copyNum = 1;
/*  55: 81 */     int oriNum = 1;
/*  56: 83 */     if (this.craftCopy.getStackInSlot(0) != null) {
/*  57: 85 */       copyNum += this.craftCopy.getStackInSlot(0).stackSize;
/*  58:    */     }
/*  59: 88 */     if (this.craftMaterial.getStackInSlot(0) != null) {
/*  60: 90 */       oriNum = this.craftMaterial.getStackInSlot(0).stackSize;
/*  61:    */     }
/*  62: 93 */     if (copyNum > oriNum) {
/*  63: 95 */       copyNum = oriNum;
/*  64:    */     }
/*  65: 98 */     for (int i = 0; i < this.craftMatrix.getSizeInventory(); i++)
/*  66:    */     {
/*  67:100 */       ItemStack itemstack1 = this.craftMatrix.getStackInSlot(i);
/*  68:102 */       if (itemstack1 != null)
/*  69:    */       {
/*  70:104 */         this.craftMatrix.decrStackSize(i, 1);
/*  71:106 */         if (itemstack1.getItem().hasContainerItem())
/*  72:    */         {
/*  73:108 */           ItemStack itemstack2 = itemstack1;
/*  74:110 */           if ((itemstack2.isItemStackDamageable()) && (itemstack2.getItemDamage() > itemstack2.getMaxDamage()))
/*  75:    */           {
/*  76:112 */             MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(this.thePlayer, itemstack2));
/*  77:113 */             itemstack2 = null;
/*  78:    */           }
/*  79:116 */           if ((itemstack2 != null) && ((!itemstack1.getItem().doesContainerItemLeaveCraftingGrid(itemstack1)) || (!this.thePlayer.inventory.addItemStackToInventory(itemstack2)))) {
/*  80:118 */             if (this.craftMatrix.getStackInSlot(i) == null) {
/*  81:120 */               this.craftMatrix.setInventorySlotContents(i, itemstack2);
/*  82:    */             } else {
/*  83:125 */               this.thePlayer.dropItem(itemstack2.getItem(), 2);
/*  84:    */             }
/*  85:    */           }
/*  86:    */         }
/*  87:    */       }
/*  88:    */     }
/*  89:132 */     int usePoint = THKaguyaConfig.laserMaxNumber;
/*  90:134 */     if (this.craftMaterial.getStackInSlot(0) != null)
/*  91:    */     {
/*  92:136 */       NBTTagCompound nbt = this.craftMaterial.getStackInSlot(0).getTagCompound();
/*  93:137 */       if (nbt != null)
/*  94:    */       {
/*  95:139 */         int baseNum = 0;
/*  96:140 */         baseNum = nbt.getShort("Number");
/*  97:142 */         if (this.craftNum.getStackInSlot(0) != null) {
/*  98:144 */           if (this.craftNum.getStackInSlot(0).stackSize > usePoint - baseNum) {
/*  99:146 */             usePoint -= baseNum;
/* 100:    */           }
/* 101:    */         }
/* 102:    */       }
/* 103:152 */       else if (this.craftNum.getStackInSlot(0) != null)
/* 104:    */       {
/* 105:154 */         int numNum = this.craftNum.getStackInSlot(0).stackSize;
/* 106:155 */         if (numNum > usePoint) {
/* 107:157 */           usePoint--;
/* 108:    */         } else {
/* 109:161 */           usePoint = numNum;
/* 110:    */         }
/* 111:    */       }
/* 112:    */     }
/* 113:166 */     slotUseProcess(this.craftNum, usePoint);
/* 114:    */     
/* 115:168 */     usePoint = 64;
/* 116:170 */     if (this.craftMaterial.getStackInSlot(0) != null)
/* 117:    */     {
/* 118:172 */       NBTTagCompound nbt = this.craftMaterial.getStackInSlot(0).getTagCompound();
/* 119:173 */       if (nbt != null)
/* 120:    */       {
/* 121:175 */         int baseSpeed = 0;
/* 122:176 */         baseSpeed = nbt.getByte("Speed");
/* 123:178 */         if (this.craftSpeed.getStackInSlot(0) != null) {
/* 124:180 */           if (this.craftSpeed.getStackInSlot(0).stackSize > 64 - baseSpeed) {
/* 125:182 */             usePoint = 64 - baseSpeed;
/* 126:    */           }
/* 127:    */         }
/* 128:    */       }
/* 129:    */     }
/* 130:187 */     slotUseProcess(this.craftSpeed, usePoint);
/* 131:    */     
/* 132:    */ 
/* 133:    */ 
/* 134:    */ 
/* 135:    */ 
/* 136:    */ 
/* 137:    */ 
/* 138:    */ 
/* 139:    */ 
/* 140:    */ 
/* 141:    */ 
/* 142:    */ 
/* 143:    */ 
/* 144:    */ 
/* 145:    */ 
/* 146:    */ 
/* 147:    */ 
/* 148:    */ 
/* 149:    */ 
/* 150:    */ 
/* 151:    */ 
/* 152:    */ 
/* 153:    */ 
/* 154:    */ 
/* 155:    */ 
/* 156:    */ 
/* 157:    */ 
/* 158:    */ 
/* 159:    */ 
/* 160:    */ 
/* 161:    */ 
/* 162:    */ 
/* 163:    */ 
/* 164:    */ 
/* 165:    */ 
/* 166:    */ 
/* 167:    */ 
/* 168:    */ 
/* 169:    */ 
/* 170:    */ 
/* 171:    */ 
/* 172:    */ 
/* 173:    */ 
/* 174:231 */     slotUseProcess(this.craftMaterial, copyNum);
/* 175:232 */     slotUseProcess(this.craftCopy, copyNum - 1);
/* 176:    */   }
/* 177:    */   
/* 178:    */   public void slotUseProcess(IInventory inventory, int useNum)
/* 179:    */   {
/* 180:239 */     ItemStack itemstack1 = inventory.getStackInSlot(0);
/* 181:241 */     if (itemstack1 != null)
/* 182:    */     {
/* 183:243 */       inventory.decrStackSize(0, useNum);
/* 184:245 */       if (itemstack1.getItem().hasContainerItem())
/* 185:    */       {
/* 186:248 */         ItemStack itemstack2 = itemstack1.getItem().getContainerItem(itemstack1);
/* 187:250 */         if ((itemstack2.isItemStackDamageable()) && (itemstack2.getItemDamage() > itemstack2.getMaxDamage()))
/* 188:    */         {
/* 189:252 */           MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(this.thePlayer, itemstack2));
/* 190:253 */           itemstack2 = null;
/* 191:    */         }
/* 192:256 */         if ((itemstack2 != null) && ((!itemstack1.getItem().doesContainerItemLeaveCraftingGrid(itemstack1)) || (!this.thePlayer.inventory.addItemStackToInventory(itemstack2)))) {
/* 193:258 */           if (inventory.getStackInSlot(0) == null) {
/* 194:260 */             inventory.setInventorySlotContents(0, itemstack2);
/* 195:    */           } else {
/* 196:265 */             this.thePlayer.dropItem(itemstack2.getItem(), 1);
/* 197:    */           }
/* 198:    */         }
/* 199:    */       }
/* 200:    */     }
/* 201:    */   }
/* 202:    */   
/* 203:    */   public int getSlotStackLimit()
/* 204:    */   {
/* 205:274 */     return 64;
/* 206:    */   }
/* 207:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.gui.SlotLaserResult
 * JD-Core Version:    0.7.0.1
 */