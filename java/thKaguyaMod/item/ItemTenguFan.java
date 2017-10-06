package thKaguyaMod.item;

import static thKaguyaMod.DanmakuConstants.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thKaguyaMod.ShotData;
import thKaguyaMod.THShotLib;

/** 風を起こす天狗団扇 */
public class ItemTenguFan extends Item
{	
	public ItemTenguFan()
	{
		super();
		this.setTextureName("thkaguyamod:tenguFan");//テクスチャの指定
		setMaxDamage(40);//耐久値
		maxStackSize = 1;//最大スタック数
		setCreativeTab(CreativeTabs.tabCombat);//クリエイティブの素材タブに登録
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
		player.setItemInUse(itemStack, getMaxItemUseDuration(itemStack));
		
		return itemStack;
	}
	
	//右クリックを押したときの処理
	@Override
	public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int usedTime)
	{
		ShotData shot = ShotData.shot(FORM_WIND, AQUA, 0, 20, WIND01);
		double speed = (double)(getMaxItemUseDuration(itemStack)-usedTime) / 7.0D;
		THShotLib.createShot(player, THShotLib.pos_Living(player), THShotLib.angle(player.rotationYaw, player.rotationPitch), speed, shot);
		player.swingItem();//投げる動作をさせる
		world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 4F + 0.8F));//音を出す
		itemStack.damageItem(1, player);
	}
	
    @Override
    public int getMaxItemUseDuration(ItemStack itemStack)
    {
        return 24;
    }
	
	//アイテムを使ったときのアクションを指定
	@Override
	public EnumAction getItemUseAction(ItemStack itemStack)
	{
		return EnumAction.bow;//右クリック時は剣のガードアクション
	}

	//剣のような表示方法にするか？
	@Override
    public boolean isFull3D()
    {
        return true;
    }
}