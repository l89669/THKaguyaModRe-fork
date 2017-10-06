/*  1:   */ package thKaguyaMod.gui;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.common.network.IGuiHandler;
/*  4:   */ import net.minecraft.entity.player.EntityPlayer;
/*  5:   */ import net.minecraft.world.World;
/*  6:   */ 
/*  7:   */ public class GuiHandler
/*  8:   */   implements IGuiHandler
/*  9:   */ {
/* 10:   */   public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
/* 11:   */   {
/* 12:16 */     switch (ID)
/* 13:   */     {
/* 14:   */     case 0: 
/* 15:19 */       return new GuiDanmakuCrafting(player, world, x, y, z);
/* 16:   */     case 1: 
/* 17:21 */       return new GuiDanmakuCraftingLaser(player, world, x, y, z);
/* 18:   */     case 2: 
/* 19:23 */       return new GuiMerchantSanae(player, world, "Sanae");
/* 20:   */     case 3: 
/* 21:25 */       return new GuiMiracleMallet(player, world, x, y, z);
/* 22:   */     }
/* 23:27 */     return null;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
/* 27:   */   {
/* 28:35 */     switch (ID)
/* 29:   */     {
/* 30:   */     case 0: 
/* 31:38 */       return new ContainerDanmakuCrafting(player.inventory, world, x, y, z);
/* 32:   */     case 1: 
/* 33:40 */       return new ContainerDanmakuCraftingLaser(player.inventory, world, x, y, z);
/* 34:   */     case 2: 
/* 35:42 */       return new ContainerMerchantSanae(player, world);
/* 36:   */     case 3: 
/* 37:44 */       return new ContainerMiracleMallet(player.inventory, world, x, y, z);
/* 38:   */     }
/* 39:46 */     return null;
/* 40:   */   }
/* 41:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.gui.GuiHandler
 * JD-Core Version:    0.7.0.1
 */