/*   1:    */ package thKaguyaMod.gui;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.common.eventhandler.EventBus;
/*   4:    */ import net.minecraft.entity.player.EntityPlayer;
/*   5:    */ import net.minecraft.entity.player.InventoryPlayer;
/*   6:    */ import net.minecraft.inventory.IInventory;
/*   7:    */ import net.minecraft.inventory.Slot;
/*   8:    */ import net.minecraft.item.Item;
/*   9:    */ import net.minecraft.item.ItemStack;
/*  10:    */ import net.minecraftforge.common.MinecraftForge;
/*  11:    */ import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
/*  12:    */ 
/*  13:    */ public class SlotMiracleMalletResult
/*  14:    */   extends Slot
/*  15:    */ {
/*  16:    */   private final IInventory craftMaterial;
/*  17:    */   private EntityPlayer thePlayer;
/*  18:    */   private int amountCrafted;
/*  19:    */   
/*  20:    */   public SlotMiracleMalletResult(EntityPlayer player, IInventory material, IInventory iInventoryResult, int index, int x, int y)
/*  21:    */   {
/*  22: 24 */     super(iInventoryResult, index, x, y);
/*  23: 25 */     this.thePlayer = player;
/*  24: 26 */     this.craftMaterial = material;
/*  25:    */   }
/*  26:    */   
/*  27:    */   public ItemStack decrStackSize(int par1)
/*  28:    */   {
/*  29: 37 */     if (getHasStack()) {
/*  30: 39 */       this.amountCrafted += Math.min(par1, getStack().stackSize);
/*  31:    */     }
/*  32: 42 */     return super.decrStackSize(par1);
/*  33:    */   }
/*  34:    */   
/*  35:    */   public boolean isItemValid(ItemStack itemStack)
/*  36:    */   {
/*  37: 53 */     return false;
/*  38:    */   }
/*  39:    */   
/*  40:    */   public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack itemStack)
/*  41:    */   {
/*  42: 60 */     onCrafting(itemStack);
/*  43:    */     
/*  44:    */ 
/*  45: 63 */     slotUseProcess(this.craftMaterial, 1);
/*  46:    */   }
/*  47:    */   
/*  48:    */   public void slotUseProcess(IInventory inventory, int useNum)
/*  49:    */   {
/*  50: 72 */     ItemStack itemstack1 = inventory.getStackInSlot(0);
/*  51: 74 */     if (itemstack1 != null)
/*  52:    */     {
/*  53: 76 */       inventory.decrStackSize(0, useNum);
/*  54: 78 */       if (itemstack1.getItem().hasContainerItem())
/*  55:    */       {
/*  56: 81 */         ItemStack itemstack2 = itemstack1.getItem().getContainerItem(itemstack1);
/*  57: 83 */         if ((itemstack2.isItemStackDamageable()) && (itemstack2.getItemDamage() > itemstack2.getMaxDamage()))
/*  58:    */         {
/*  59: 85 */           MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(this.thePlayer, itemstack2));
/*  60: 86 */           itemstack2 = null;
/*  61:    */         }
/*  62: 89 */         if ((itemstack2 != null) && ((!itemstack1.getItem().doesContainerItemLeaveCraftingGrid(itemstack1)) || (!this.thePlayer.inventory.addItemStackToInventory(itemstack2)))) {
/*  63: 91 */           if (inventory.getStackInSlot(0) == null) {
/*  64: 93 */             inventory.setInventorySlotContents(0, itemstack2);
/*  65:    */           } else {
/*  66: 98 */             this.thePlayer.dropItem(itemstack2.getItem(), itemstack2.getItemDamage());
/*  67:    */           }
/*  68:    */         }
/*  69:    */       }
/*  70:    */     }
/*  71:    */   }
/*  72:    */   
/*  73:    */   public int getSlotStackLimit()
/*  74:    */   {
/*  75:108 */     return 64;
/*  76:    */   }
/*  77:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.gui.SlotMiracleMalletResult
 * JD-Core Version:    0.7.0.1
 */