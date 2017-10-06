package thKaguyaMod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thKaguyaMod.entity.item.EntitySpiritualStrikeTalisman;

public class ItemSpiritualStrikeTalisman extends Item
{

	//霊撃札
	
	public ItemSpiritualStrikeTalisman()
	{
		super();
		this.setTextureName("thkaguyamod:SpiritualStrikeTalisman");//テクスチャの指定
		maxStackSize = 16;//最大スタック数
		setCreativeTab(CreativeTabs.tabCombat);//クリエイティブのその他タブに登録
	}
	
	/** 右クリックを押した瞬間の処理
	 *  @param itemStack : 右クリックを押したItemStack
	 *  @param world     : ワールド
	 *  @param player    : 右クリックを押したプレイヤー
	 *  @return 右クリックを押したItemStackを返す
	 */
	@Override
   	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
		EntitySpiritualStrikeTalisman talisman = new EntitySpiritualStrikeTalisman(world, player);
		
		if(!world.isRemote)
		{
			world.spawnEntityInWorld(talisman);
		}
		world.playSoundAtEntity(player, "random.fizz", 2.0F, 12.0F);
		
		if(!player.capabilities.isCreativeMode)
		{
			itemStack.stackSize--;
		}
		
       	return itemStack;
    }
}