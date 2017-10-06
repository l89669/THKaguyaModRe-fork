package thKaguyaMod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thKaguyaMod.entity.item.EntityMazinkyoukan;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/** アイテム　魔人経巻 */
public class ItemMazinKyoukan extends Item
{
	
	public ItemMazinKyoukan()
	{
		super();
		this.setTextureName("thkaguyamod:mazinKyoukan");//テクスチャの指定
		maxStackSize = 1;//最大スタック数
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
    	player.setItemInUse(itemStack, getMaxItemUseDuration(itemStack));//アイテムの使用継続時間を記憶させる？
    	EntityMazinkyoukan mazinkyoukan = new EntityMazinkyoukan(world, player);
    	if(!world.isRemote)
    	{
    		world.spawnEntityInWorld(mazinkyoukan);
    	}
    	return itemStack;
    }
	
	//右クリックを終了したときに呼び出されるメソッド
	@Override
	public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int usedTime)
    {

   	}
	
	@Override
    public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player)
    {
        return itemStack;
    }
    
	@Override
    public int getMaxItemUseDuration(ItemStack itemStack)
    {
        return 72000;
    }
	
	//アイテムを使ったときのアクションを指定
	@Override
	public EnumAction getItemUseAction(ItemStack itemStack)
    {
        return EnumAction.block;//右クリック時は剣のガードアクション
    }
	
	//アイテムを発光させる。 trueなら発光
	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack itemStack, int pass)
    {   
		return true;
    }
}