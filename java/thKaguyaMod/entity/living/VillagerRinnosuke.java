/*  1:   */ package thKaguyaMod.entity.living;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;
/*  4:   */ import java.util.Random;
/*  5:   */ import net.minecraft.entity.passive.EntityVillager;
/*  6:   */ import net.minecraft.init.Items;
/*  7:   */ import net.minecraft.item.ItemStack;
/*  8:   */ import net.minecraft.village.MerchantRecipe;
/*  9:   */ import net.minecraft.village.MerchantRecipeList;
/* 10:   */ import thKaguyaMod.init.THKaguyaItems;
/* 11:   */ 
/* 12:   */ public class VillagerRinnosuke implements IVillageTradeHandler
/* 14:   */ {
/* 15:   */   public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random)
/* 16:   */   {
/* 17:21 */     recipeList.add(new MerchantRecipe(new ItemStack(THKaguyaItems.power_item, 22, 0), THKaguyaItems.spiritual_strike_talisman));
/* 18:   */     
/* 19:23 */     recipeList.add(new MerchantRecipe(new ItemStack(Items.iron_ingot, 20, 0), THKaguyaItems.mini_hakkero));
/* 20:   */     
/* 21:25 */     recipeList.add(new MerchantRecipe(new ItemStack(Items.nether_star, 1, 0), THKaguyaItems.magic_bloom));
/* 22:   */     
/* 23:27 */     recipeList.add(new MerchantRecipe(new ItemStack(THKaguyaItems.power_item, 10, 0), new ItemStack(THKaguyaItems.point_item, 9, 0)));
/* 24:   */     
/* 25:29 */     recipeList.add(new MerchantRecipe(new ItemStack(THKaguyaItems.point_item, 10, 0), new ItemStack(THKaguyaItems.power_item, 9, 0)));
/* 26:   */     
/* 27:31 */     recipeList.add(new MerchantRecipe(new ItemStack(Items.redstone, 1, 0), new ItemStack(THKaguyaItems.power_item, 64, 0)));
/* 28:   */     
/* 29:33 */     recipeList.add(new MerchantRecipe(new ItemStack(Items.dye, 1, 4), new ItemStack(THKaguyaItems.point_item, 64, 0)));
/* 30:   */     
/* 31:35 */     recipeList.add(new MerchantRecipe(new ItemStack(Items.gold_ingot, 1, 0), new ItemStack(Items.ender_pearl, 1, 0)));
/* 32:   */   }
/* 33:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.living.VillagerRinnosuke
 * JD-Core Version:    0.7.0.1
 */