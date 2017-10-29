package thKaguyaMod.entity.living;

import java.util.Random;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import thKaguyaMod.init.THKaguyaItems;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;

public class VillagerRinnosuke implements IVillageTradeHandler
{


	@Override
	public void manipulateTradesForVillager(EntityVillager villager,
			MerchantRecipeList recipeList, Random random)
	{
		recipeList.add(new MerchantRecipe( new ItemStack(THKaguyaItems.power_item, 22, 0),
											THKaguyaItems.spiritual_strike_talisman));
		recipeList.add(new MerchantRecipe( new ItemStack(Items.iron_ingot, 20, 0),
											THKaguyaItems.mini_hakkero));
		recipeList.add(new MerchantRecipe( new ItemStack(Items.nether_star, 1, 0),
				THKaguyaItems.magic_bloom));
		recipeList.add(new MerchantRecipe( new ItemStack(THKaguyaItems.power_item, 10, 0),
				new ItemStack(THKaguyaItems.point_item, 9, 0)));
		recipeList.add(new MerchantRecipe( new ItemStack(THKaguyaItems.point_item, 10, 0),
				new ItemStack(THKaguyaItems.power_item, 9, 0)));
		recipeList.add(new MerchantRecipe( new ItemStack(Items.redstone, 1, 0),
				new ItemStack(THKaguyaItems.power_item, 64, 0)));
		recipeList.add(new MerchantRecipe( new ItemStack(Items.dye, 1, 4),
				new ItemStack(THKaguyaItems.point_item, 64, 0)));
		recipeList.add(new MerchantRecipe( new ItemStack(Items.gold_ingot, 1, 0),
				new ItemStack(Items.ender_pearl, 1, 0)));
		
	}

}
