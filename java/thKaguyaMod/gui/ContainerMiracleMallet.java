/*   1:    */ package thKaguyaMod.gui;
/*   2:    */ 
/*   3:    */ import java.util.List;
/*   4:    */ import net.minecraft.entity.player.EntityPlayer;
/*   5:    */ import net.minecraft.entity.player.InventoryPlayer;
/*   6:    */ import net.minecraft.inventory.Container;
/*   7:    */ import net.minecraft.inventory.IInventory;
/*   8:    */ import net.minecraft.inventory.InventoryCraftResult;
/*   9:    */ import net.minecraft.inventory.InventoryCrafting;
/*  10:    */ import net.minecraft.inventory.Slot;
/*  11:    */ import net.minecraft.item.Item;
/*  12:    */ import net.minecraft.item.ItemStack;
/*  13:    */ import net.minecraft.nbt.NBTTagCompound;
/*  14:    */ import net.minecraft.world.World;
/*  15:    */ import thKaguyaMod.init.THKaguyaItems;
/*  16:    */ 
/*  17:    */ public class ContainerMiracleMallet
/*  18:    */   extends Container
/*  19:    */ {
/*  20: 19 */   public IInventory craftMaterial = new InventoryCrafting(this, 1, 1);
/*  21: 21 */   public IInventory craftResult = new InventoryCraftResult();
/*  22:    */   private World world;
/*  23:    */   private int xCoord;
/*  24:    */   private int yCoord;
/*  25:    */   private int zCoord;
/*  26:    */   private ItemStack resultItem;
/*  27:    */   
/*  28:    */   public ContainerMiracleMallet(InventoryPlayer inventoryPlayer, World world, int x, int y, int z)
/*  29:    */   {
/*  30: 33 */     this.world = world;
/*  31: 34 */     this.xCoord = x;
/*  32: 35 */     this.yCoord = y;
/*  33: 36 */     this.zCoord = z;
/*  34:    */     
/*  35: 38 */     addSlotToContainer(new SlotMiracleMalletResult(inventoryPlayer.player, this.craftMaterial, this.craftResult, 0, 228, 35));
/*  36:    */     
/*  37: 40 */     addSlotToContainer(new Slot(this.craftMaterial, 0, 8, 17));
/*  38: 45 */     for (int l = 0; l < 3; l++) {
/*  39: 47 */       for (int i1 = 0; i1 < 9; i1++) {
/*  40: 49 */         addSlotToContainer(new Slot(inventoryPlayer, i1 + l * 9 + 9, 48 + i1 * 18, 84 + l * 18));
/*  41:    */       }
/*  42:    */     }
/*  43: 53 */     for (int l = 0; l < 9; l++) {
/*  44: 55 */       addSlotToContainer(new Slot(inventoryPlayer, l, 48 + l * 18, 142));
/*  45:    */     }
/*  46:    */   }
/*  47:    */   
/*  48:    */   public boolean canInteractWith(EntityPlayer player)
/*  49:    */   {
/*  50: 69 */     return true;
/*  51:    */   }
/*  52:    */   
/*  53:    */   public void onCraftMatrixChanged(IInventory iInventory)
/*  54:    */   {
/*  55: 78 */     ItemStack originItem = iInventory.getStackInSlot(0);
/*  56: 79 */     int originID = -1;
/*  57: 83 */     if (originItem != null)
/*  58:    */     {
/*  59: 85 */       boolean isCommonDamage = false;
/*  60: 88 */       if (originItem.getItem() == THKaguyaItems.yin_yang_orb)
/*  61:    */       {
/*  62: 91 */         this.resultItem = new ItemStack(THKaguyaItems.bloodthirsty_yin_yang_orb);
/*  63:    */       }
/*  64: 94 */       else if (originItem.getItem() == THKaguyaItems.gap)
/*  65:    */       {
/*  66: 97 */         this.resultItem = new ItemStack(THKaguyaItems.gapFoldingUmbrella);
/*  67:    */       }
/*  68:100 */       else if (originItem.getUnlocalizedName().equals("item.alicedoll.bare"))
/*  69:    */       {
/*  70:104 */         this.resultItem = new ItemStack(THKaguyaItems.cursedDecoyDoll);
/*  71:105 */         originItem.getItem();originID = Item.getIdFromItem(originItem.getItem());
/*  72:    */       }
/*  73:108 */       else if (originItem.getItem() == THKaguyaItems.roukanken)
/*  74:    */       {
/*  75:111 */         this.resultItem = new ItemStack(THKaguyaItems.roukanSenpuuzin);
/*  76:112 */         this.resultItem.setItemDamage(originItem.getItemDamage());
/*  77:113 */         isCommonDamage = true;
/*  78:    */       }
/*  79:    */       else
/*  80:    */       {
/*  81:117 */         this.resultItem = null;
/*  82:    */       }
/*  83:120 */       if ((this.resultItem != null) && (!isCommonDamage))
/*  84:    */       {
/*  85:122 */         NBTTagCompound nbt = new NBTTagCompound();
/*  86:    */         
/*  87:124 */         nbt.setInteger("OriginalDamage", originItem.getItemDamage());
/*  88:125 */         if (originID >= 0) {
/*  89:127 */           nbt.setInteger("OriginalItemID", originID);
/*  90:    */         }
/*  91:129 */         this.resultItem.setTagCompound(nbt);
/*  92:    */       }
/*  93:    */     }
/*  94:    */     else
/*  95:    */     {
/*  96:135 */       this.resultItem = null;
/*  97:    */     }
/*  98:139 */     this.craftResult.setInventorySlotContents(0, this.resultItem);
/*  99:    */     
/* 100:141 */     detectAndSendChanges();
/* 101:    */   }
/* 102:    */   
/* 103:    */   public void onContainerClosed(EntityPlayer player)
/* 104:    */   {
/* 105:151 */     super.onContainerClosed(player);
/* 106:153 */     if (!this.world.isRemote)
/* 107:    */     {
/* 108:156 */       ItemStack itemStack = this.craftMaterial.getStackInSlotOnClosing(0);
/* 109:157 */       if (itemStack != null) {
/* 110:159 */         player.dropPlayerItemWithRandomChoice(itemStack, false);
/* 111:    */       }
/* 112:    */     }
/* 113:    */   }
/* 114:    */   
/* 115:    */   public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
/* 116:    */   {
/* 117:169 */     ItemStack itemstack = null;
/* 118:170 */     Slot slot = (Slot)this.inventorySlots.get(par2);
/* 119:172 */     if ((slot != null) && (slot.getHasStack()))
/* 120:    */     {
/* 121:174 */       ItemStack itemstack1 = slot.getStack();
/* 122:175 */       itemstack = itemstack1.copy();
/* 123:177 */       if (par2 == 0)
/* 124:    */       {
/* 125:179 */         if (!mergeItemStack(itemstack1, 10, 46, true)) {
/* 126:181 */           return null;
/* 127:    */         }
/* 128:184 */         slot.onSlotChange(itemstack1, itemstack);
/* 129:    */       }
/* 130:186 */       else if ((par2 >= 10) && (par2 < 37))
/* 131:    */       {
/* 132:188 */         if (!mergeItemStack(itemstack1, 37, 46, false)) {
/* 133:190 */           return null;
/* 134:    */         }
/* 135:    */       }
/* 136:193 */       else if ((par2 >= 37) && (par2 < 46))
/* 137:    */       {
/* 138:195 */         if (!mergeItemStack(itemstack1, 10, 37, false)) {
/* 139:197 */           return null;
/* 140:    */         }
/* 141:    */       }
/* 142:200 */       else if (!mergeItemStack(itemstack1, 10, 46, false))
/* 143:    */       {
/* 144:202 */         return null;
/* 145:    */       }
/* 146:205 */       if (itemstack1.stackSize == 0) {
/* 147:207 */         slot.putStack((ItemStack)null);
/* 148:    */       } else {
/* 149:211 */         slot.onSlotChanged();
/* 150:    */       }
/* 151:214 */       if (itemstack1.stackSize == itemstack.stackSize) {
/* 152:216 */         return null;
/* 153:    */       }
/* 154:220 */       slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
/* 155:    */     }
/* 156:223 */     return itemstack;
/* 157:    */   }
/* 158:    */   
/* 159:    */   public ItemStack slotClick(int par1, int par2, int par3, EntityPlayer player)
/* 160:    */   {
/* 161:248 */     super.slotClick(par1, par2, par3, player);
/* 162:251 */     if (player.experienceLevel >= 30) {
/* 163:253 */       onCraftMatrixChanged(this.craftMaterial);
/* 164:    */     }
/* 165:257 */     return this.resultItem;
/* 166:    */   }
/* 167:    */   
/* 168:    */   public boolean func_94530_a(ItemStack par1ItemStack, Slot par2Slot)
/* 169:    */   {
/* 170:262 */     return (par2Slot.inventory != this.craftResult) && (super.func_94530_a(par1ItemStack, par2Slot));
/* 171:    */   }
/* 172:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.gui.ContainerMiracleMallet
 * JD-Core Version:    0.7.0.1
 */