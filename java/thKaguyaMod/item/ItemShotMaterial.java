package thKaguyaMod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thKaguyaMod.ShotData;
import thKaguyaMod.THShotLib;

//弾幕の素　弾幕を作成する為の素材
public class ItemShotMaterial extends Item
{
	
	public ItemShotMaterial()
	{
		super();
		this.setTextureName("thkaguyamod:material/ShotMaterial");//テクスチャの指定
		setCreativeTab(CreativeTabs.tabMaterials);//クリエイティブの素材タブに登録
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
		THShotLib.createShot(player, THShotLib.pos_Living(player), player.getLookVec(), 1.0D, ShotData.shot(THShotLib.FORM_TINY, THShotLib.WHITE));
		THShotLib.playShotSound(player);
		
		if(!world.isRemote)
		{
			if(!player.capabilities.isCreativeMode)
			{
				itemStack.stackSize--;
			}
		}
		return itemStack;
	}
	
}