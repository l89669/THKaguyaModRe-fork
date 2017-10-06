package thKaguyaMod.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

public class ItemBlockDanmakuCraftingTable extends ItemBlockWithMetadata
{
	private String[] name = {"shot", "laser"};
	
	public ItemBlockDanmakuCraftingTable(Block block)
	{
		super(block, block);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		 int i = MathHelper.clamp_int(itemStack.getItemDamage(), 0, 1);
		return super.getUnlocalizedName() + "." + name[i];
	}
}
