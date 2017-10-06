/*  1:   */ package thKaguyaMod.gui;
/*  2:   */ 
/*  3:   */ import net.minecraft.inventory.IInventory;
/*  4:   */ import net.minecraft.inventory.Slot;
/*  5:   */ import net.minecraft.item.ItemStack;
/*  6:   */ 
/*  7:   */ public class SlotDanmakuCraftingA
/*  8:   */   extends Slot
/*  9:   */ {
/* 10:   */   protected int slotType;
/* 11:   */   
/* 12:   */   public SlotDanmakuCraftingA(IInventory iInventory, int index, int x, int y, int type)
/* 13:   */   {
/* 14:18 */     super(iInventory, index, x, y);
/* 15:19 */     this.slotType = type;
/* 16:   */   }
/* 17:   */   
/* 18:   */   public boolean isItemValid(ItemStack itemStack)
/* 19:   */   {
/* 20:54 */     switch (this.slotType)
/* 21:   */     {
/* 22:   */     case 0: 
/* 23:57 */       return itemStack != null;
/* 24:   */     case 1: 
/* 25:59 */       return itemStack != null;
/* 26:   */     case 2: 
/* 27:61 */       return itemStack != null;
/* 28:   */     case 3: 
/* 29:65 */       return itemStack != null;
/* 30:   */     case 4: 
/* 31:67 */       return itemStack != null;
/* 32:   */     case 5: 
/* 33:69 */       return itemStack != null;
/* 34:   */     }
/* 35:71 */     return false;
/* 36:   */   }
/* 37:   */   
/* 38:   */   public int getSlotStackLimit()
/* 39:   */   {
/* 40:81 */     return 64;
/* 41:   */   }
/* 42:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.gui.SlotDanmakuCraftingA
 * JD-Core Version:    0.7.0.1
 */