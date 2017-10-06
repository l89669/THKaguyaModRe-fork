/*   1:    */ package thKaguyaMod.gui;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.relauncher.Side;
/*   4:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   5:    */ import java.util.List;
/*   6:    */ import net.minecraft.entity.player.EntityPlayer;
/*   7:    */ import net.minecraft.inventory.Container;
/*   8:    */ import net.minecraft.inventory.ICrafting;
/*   9:    */ import net.minecraft.inventory.IInventory;
/*  10:    */ import net.minecraft.inventory.InventoryMerchant;
/*  11:    */ import net.minecraft.inventory.Slot;
/*  12:    */ import net.minecraft.inventory.SlotMerchantResult;
/*  13:    */ import net.minecraft.item.ItemStack;
/*  14:    */ import net.minecraft.world.World;
/*  15:    */ 
/*  16:    */ public class ContainerMerchantSanae
/*  17:    */   extends Container
/*  18:    */ {
/*  19:    */   private InventoryMerchant merchantInventory;
/*  20:    */   private final World theWorld;
/*  21:    */   private static final String __OBFID = "CL_00001757";
/*  22:    */   
/*  23:    */   public ContainerMerchantSanae(EntityPlayer player, World par3World)
/*  24:    */   {
/*  25: 33 */     this.theWorld = par3World;
/*  26: 34 */     this.merchantInventory = new InventoryMerchant(player, null);
/*  27: 35 */     addSlotToContainer(new Slot(this.merchantInventory, 0, 36, 53));
/*  28: 36 */     addSlotToContainer(new Slot(this.merchantInventory, 1, 62, 53));
/*  29: 37 */     addSlotToContainer(new SlotMerchantResult(player, null, this.merchantInventory, 2, 120, 53));
/*  30: 41 */     for (int i = 0; i < 3; i++) {
/*  31: 43 */       for (int j = 0; j < 9; j++) {
/*  32: 45 */         addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*  33:    */       }
/*  34:    */     }
/*  35: 50 */     for (int i = 0; i < 9; i++) {
/*  36: 52 */       addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 142));
/*  37:    */     }
/*  38:    */   }
/*  39:    */   
/*  40:    */   public InventoryMerchant getMerchantInventory()
/*  41:    */   {
/*  42: 62 */     return this.merchantInventory;
/*  43:    */   }
/*  44:    */   
/*  45:    */   public void addCraftingToCrafters(ICrafting par1ICrafting)
/*  46:    */   {
/*  47: 67 */     super.addCraftingToCrafters(par1ICrafting);
/*  48:    */   }
/*  49:    */   
/*  50:    */   public void detectAndSendChanges()
/*  51:    */   {
/*  52: 75 */     super.detectAndSendChanges();
/*  53:    */   }
/*  54:    */   
/*  55:    */   public void onCraftMatrixChanged(IInventory par1IInventory)
/*  56:    */   {
/*  57: 83 */     this.merchantInventory.resetRecipeAndSlots();
/*  58: 84 */     super.onCraftMatrixChanged(par1IInventory);
/*  59:    */   }
/*  60:    */   
/*  61:    */   public void setCurrentRecipeIndex(int par1)
/*  62:    */   {
/*  63: 89 */     this.merchantInventory.setCurrentRecipeIndex(par1);
/*  64:    */   }
/*  65:    */   
/*  66:    */   @SideOnly(Side.CLIENT)
/*  67:    */   public void updateProgressBar(int par1, int par2) {}
/*  68:    */   
/*  69:    */   public boolean canInteractWith(EntityPlayer par1EntityPlayer)
/*  70:    */   {
/*  71: 97 */     return true;
/*  72:    */   }
/*  73:    */   
/*  74:    */   public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
/*  75:    */   {
/*  76:105 */     ItemStack itemstack = null;
/*  77:106 */     Slot slot = (Slot)this.inventorySlots.get(par2);
/*  78:108 */     if ((slot != null) && (slot.getHasStack()))
/*  79:    */     {
/*  80:110 */       ItemStack itemstack1 = slot.getStack();
/*  81:111 */       itemstack = itemstack1.copy();
/*  82:113 */       if (par2 == 2)
/*  83:    */       {
/*  84:115 */         if (!mergeItemStack(itemstack1, 3, 39, true)) {
/*  85:117 */           return null;
/*  86:    */         }
/*  87:120 */         slot.onSlotChange(itemstack1, itemstack);
/*  88:    */       }
/*  89:122 */       else if ((par2 != 0) && (par2 != 1))
/*  90:    */       {
/*  91:124 */         if ((par2 >= 3) && (par2 < 30))
/*  92:    */         {
/*  93:126 */           if (!mergeItemStack(itemstack1, 30, 39, false)) {
/*  94:128 */             return null;
/*  95:    */           }
/*  96:    */         }
/*  97:131 */         else if ((par2 >= 30) && (par2 < 39) && (!mergeItemStack(itemstack1, 3, 30, false))) {
/*  98:133 */           return null;
/*  99:    */         }
/* 100:    */       }
/* 101:136 */       else if (!mergeItemStack(itemstack1, 3, 39, false))
/* 102:    */       {
/* 103:138 */         return null;
/* 104:    */       }
/* 105:141 */       if (itemstack1.stackSize == 0) {
/* 106:143 */         slot.putStack((ItemStack)null);
/* 107:    */       } else {
/* 108:147 */         slot.onSlotChanged();
/* 109:    */       }
/* 110:150 */       if (itemstack1.stackSize == itemstack.stackSize) {
/* 111:152 */         return null;
/* 112:    */       }
/* 113:155 */       slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
/* 114:    */     }
/* 115:158 */     return itemstack;
/* 116:    */   }
/* 117:    */   
/* 118:    */   public void onContainerClosed(EntityPlayer par1EntityPlayer)
/* 119:    */   {
/* 120:166 */     super.onContainerClosed(par1EntityPlayer);
/* 121:    */     
/* 122:168 */     super.onContainerClosed(par1EntityPlayer);
/* 123:170 */     if (!this.theWorld.isRemote)
/* 124:    */     {
/* 125:172 */       ItemStack itemstack = this.merchantInventory.getStackInSlotOnClosing(0);
/* 126:174 */       if (itemstack != null) {
/* 127:176 */         par1EntityPlayer.dropPlayerItemWithRandomChoice(itemstack, false);
/* 128:    */       }
/* 129:179 */       itemstack = this.merchantInventory.getStackInSlotOnClosing(1);
/* 130:181 */       if (itemstack != null) {
/* 131:183 */         par1EntityPlayer.dropPlayerItemWithRandomChoice(itemstack, false);
/* 132:    */       }
/* 133:    */     }
/* 134:    */   }
/* 135:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.gui.ContainerMerchantSanae
 * JD-Core Version:    0.7.0.1
 */