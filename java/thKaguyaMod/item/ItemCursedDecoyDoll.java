package thKaguyaMod.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import thKaguyaMod.entity.item.EntityCursedDecoyDoll;

/** 呪いのデコイ人形 */
public class ItemCursedDecoyDoll extends Item
{

	/** 呪いのデコイ人形のコンストラクタ */
	public ItemCursedDecoyDoll()
	{
		super();
		this.setTextureName("thkaguyamod:cursedDecoyDoll");	//テクスチャの指定
		maxStackSize = 1;	//最大スタック
		setMaxDamage(30);//耐久値
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
		List<?> list = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.addCoord(0.0D, 0.0D, 0.0D).expand(40.0D, 40.0D, 40.0D));
		for(int k = 0; k < list.size(); k++)
		{
			Entity entity = (Entity)list.get(k);
			// すでに周囲に呪いのデコイ人形が出現している場合
			if(entity instanceof EntityCursedDecoyDoll)
			{
				// 呪いのデコイ人形の使用は失敗する
				return itemStack;
			}
		}
		
		EntityCursedDecoyDoll decoy = new EntityCursedDecoyDoll(world, player);
   		if(!world.isRemote)
   		{
     		world.spawnEntityInWorld(decoy);	// 呪いのデコイ人形を出現させる
   		}

		
		//player.setItemInUse(itemStack, getMaxItemUseDuration(itemStack));
   		itemStack.setItemDamage(itemStack.getItemDamage() + 1);
   		
		NBTTagCompound	nbt			= itemStack.getTagCompound();
		
		if( itemStack.getItemDamage() >= this.getMaxDamage() )
		{
			int originalDamage	= 0;
			int originID		= 0;
			
    		if( nbt != null )
    		{
    			originalDamage = nbt.getInteger("OriginalDamage");
    			nbt.removeTag("OriginalDamage");
    			originID = nbt.getInteger("OriginalItemID");
    			nbt.removeTag("OriginItemID");
    		}
    		
    		itemStack = new ItemStack( itemStack.getItem().getItemById( originID ) );
    		itemStack.setItemDamage( originalDamage );
		}
		
       	return itemStack;
    }
	
	
	/** アイテムを発光させるか返す
	 * @param itemStack 	: アイテムスタック
	 * @param pass			: ？
	 * @return trueなら発光する
	 */
	@Override
	public boolean hasEffect(ItemStack itemStack, int pass)
    {   
    	return true;
    }
}