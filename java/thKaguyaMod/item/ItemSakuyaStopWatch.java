package thKaguyaMod.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thKaguyaMod.entity.item.EntitySakuyaStopWatch;
import thKaguyaMod.entity.item.EntitySakuyaWatch;

/** ストップウォッチ */
public class ItemSakuyaStopWatch extends Item
{

	/** ストップウォッチのコンストラクタ */
	public ItemSakuyaStopWatch()
	{
		super();
		this.setTextureName("thkaguyamod:SakuyaStopWatch");	//テクスチャの指定
		maxStackSize = 1;								//最大スタック
		setCreativeTab(CreativeTabs.tabMisc);			//クリエイティブのその他タブに登録
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
		//周囲のEntityを取得
		List<?> list = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.addCoord(0.0D, 0.0D, 0.0D).expand(20.0D, 20.0D, 20.0D));
		for(int k = 0; k < list.size(); k++)
		{
			Entity entity = (Entity)list.get(k);
			// すでに周囲にストップウォッチか咲夜の懐中時計が出現している場合
			if( (entity instanceof EntitySakuyaWatch ) || ( entity instanceof EntitySakuyaStopWatch ) )
			{
				// ストップウォッチの使用は失敗する
				return itemStack;
			}
		}

		world.playSoundAtEntity(player, "random.click", 0.5F, 0.4F / (itemRand.nextFloat() * 4F + 0.8F));

		EntitySakuyaStopWatch stopWatch = new EntitySakuyaStopWatch(world, player);
   		if(!world.isRemote)
   		{
     		world.spawnEntityInWorld(stopWatch);//時間停止空間を生み出す
   		}
   		
   		// 使用者がクリエイティブではない場合
   		if(! player.capabilities.isCreativeMode)
   		{
   			itemStack.stackSize--;
   		}

       	return itemStack;
    }
}