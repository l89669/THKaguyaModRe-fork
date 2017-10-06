package thKaguyaMod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thKaguyaMod.entity.item.EntityMiniHakkero;
import thKaguyaMod.init.THKaguyaConfig;

public class ItemMiniHakkero extends Item
{

	/*
	ミニ八卦炉
	おなじみ、マスタースパークの撃てるあれ
	*/
    
    public ItemMiniHakkero()
    {
        super();
        this.setTextureName("thkaguyamod:miniHakkero");//テクスチャの指定
    	maxStackSize = 1;//最大スタック数
    	setCreativeTab(CreativeTabs.tabCombat);//クリエイティブの武器タブに登録
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
    	//マスタースパークの使用を許可しない設定なら何も起きない
    	if(!THKaguyaConfig.UsingMasterSpark)
    	{
    		return itemStack;
    	}
    	
    	//ミニ八卦炉の設置地点にブロックがないことを確認
        if(!world.isBlockNormalCubeDefault((int)(player.posX - Math.sin(player.rotationYaw   / 180F * 3.141593F) * Math.cos(player.rotationPitch / 180F * 3.141593F) * 1.3D),
					(int)(player.posY - Math.sin(player.rotationPitch / 180F * 3.141593F) * 1.3D + player.getEyeHeight()),
					(int)(player.posZ + Math.cos(player.rotationYaw   / 180F * 3.141593F) * Math.cos(player.rotationPitch / 180F * 3.141593F) * 1.3D),
					true))
    	{
    		//ファイアーチャージを所持していないなら使用できない
    		if(!player.inventory.hasItem(Items.fire_charge))
    		{
    			return itemStack;
    		}
    		
    		int i = 0;//インベントリのスタックを順に追う
    		int count = 0;//ファイアーチャージの所持数
    		
    		//プレイヤーのインベントリを順番に見ていく
    		for(i = 0; i < player.inventory.mainInventory.length; i++){
    			if(player.inventory.mainInventory[i] != null && 
    				player.inventory.mainInventory[i].getItem() == Items.fire_charge)
    			{
    				//ファイアーチャージをスタックしているなら、そのスタック分countを追加
    				count += player.inventory.mainInventory[i].stackSize;
    			}
    		}
    		
    		//ファイアーチャージを３２個以上持っているなら
    		if(count >= 32)
    		{
    			//インベントリから３２回ファイアーチャージを減らす
    			for( i = 0; i < 32; i ++)
    			{
        				player.inventory.consumeInventoryItem(Items.fire_charge);
    			}
    		}
    		//ファイアーチャージを３２個以上持っていないなら使用できない
    		else
    		{
    			return itemStack;
    		}
    		
    		//ミニ八卦炉を目の前に出現させる
    		EntityMiniHakkero miniHakkero;
    		miniHakkero = new EntityMiniHakkero(world, player, itemStack.getItemDamageForDisplay());
    		
       		if(!world.isRemote)
       		{
        		world.spawnEntityInWorld(miniHakkero);//ミニ八卦炉を出す
       			itemStack.stackSize--;//スタックから消滅させる
       		}
    	}
    		
       	return itemStack;
    }
	
}
