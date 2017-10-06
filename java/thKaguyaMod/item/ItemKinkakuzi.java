package thKaguyaMod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thKaguyaMod.entity.item.EntityKinkakuzi;

public class ItemKinkakuzi extends Item
{
	
	//巨大な板を投げつけるアイテム
	
	public ItemKinkakuzi()
	{
		super();
		this.setTextureName("thkaguyamod:kinkakuzi");//テクスチャの指定
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
    	double pow = 0.7;
    	if(player.isSneaking())
    	{
    		pow = 1.0;
    	}
    	EntityKinkakuzi entityKinkakuzi;
    	//使用者の上方向に発射
    	entityKinkakuzi = new EntityKinkakuzi(world, player.posX, player.posY + player.height, player.posZ, player, pow);
       	world.playSoundAtEntity(player, "random.drr", 0.5F, 0.4F / (itemRand.nextFloat() * 4F + 0.8F));//音を出す
       	if(!world.isRemote)
       	{
         	world.spawnEntityInWorld(entityKinkakuzi);//金閣寺の一枚天井を出現させる
       	}
    	itemStack.stackSize--;//スタックから消滅させる
    		
       	return itemStack;
    }
	
	//右クリック時の行動を設定
	@Override
    public EnumAction getItemUseAction(ItemStack itemStack)
    {
        return EnumAction.bow;//弓の構えをする
    }
	
	//右クリック時のカウントの最大値を指定
	@Override
	public int getMaxItemUseDuration(ItemStack itemStack)
    {
        return 72000;
    }
	
	//アイテムを発光させる。 trueなら発光
	@Override
	public boolean hasEffect(ItemStack itemStack, int pass)
	{   
		return true;
    }
}