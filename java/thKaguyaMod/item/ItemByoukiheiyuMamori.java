package thKaguyaMod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemByoukiheiyuMamori extends Item
{
	
	/**
	 * 病気平癒守
	 * 一時的に体力を徐々に回復する。すでにかかっているポーション効果を全て消す
	 */
	
	public ItemByoukiheiyuMamori()
	{
		super();
		this.setTextureName("thkaguyamod:ByoukiheiyuMamori");//テクスチャの指定
		maxStackSize = 16;
		setCreativeTab(CreativeTabs.tabMisc);//クリエイティブのその他タブに登録
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
		if(player.capabilities.isCreativeMode)
		{
			onEaten(itemStack, world, player);
		}
		
		player.setItemInUse(itemStack, getMaxItemUseDuration(itemStack));
       	return itemStack;
    }
	
	/**
	 * 右クリックを終了したときの処理
	 * @param itemStack
	 * @param world
	 * @param player
	 * @param usedTime
	 */
	@Override
	//public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int usedTime)
	public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player)
	{
		//ダメージを受けている状態だと失敗する
		if(player.hurtTime > 0)
		{
			return itemStack;
		}
		//全てのポーション効果を消す
		player.clearActivePotions();
		
        if (!world.isRemote)
    	{
        	
        	int potionType = 10;//リジェネ効果付与
        	int effectTime = 3 * 20;//効果時間。tick単位なので、１５秒なら、15 * 20になる。
        	int potionLevel = 3;//ポーション効果のレベル
    		//ポーション効果を付与
        	player.addPotionEffect(new PotionEffect(potionType, effectTime, potionLevel));
    	}
       	if(! player.capabilities.isCreativeMode)
       	{
       		itemStack.stackSize--;
       	}
    	return itemStack;
	}
	
    
    @Override
    public int getMaxItemUseDuration(ItemStack itemStack)
    {
        return 24;
    }
	
	/** アイテムを使ったときのアクションを指定 */
	@Override
	public EnumAction getItemUseAction(ItemStack itemStack)
	{
		return EnumAction.bow;
	}
	
}