package thKaguyaMod.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/** こいしの閉ざした第三の目 */
public class ItemClosedThirdEye extends Item
{
	/** 閉ざしt第三の眼 */
	public ItemClosedThirdEye()
	{
		super();
		this.setTextureName("thkaguyamod:closedThirdEye");//テクスチャの指定
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
    	player.addPotionEffect(new PotionEffect(14, 20, 0));//敵に気づかれなくなる
    	Entity entity = null;
    	List list = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(30.0D, 30.0D,30.0D));//プレイヤーを中心に10ブロックの立方体内の当たりを取得

        if (list != null && list.size() > 0)
        {
            for (int j1 = 0; j1 < list.size(); j1++)
            {
                entity = (Entity)list.get(j1);
            	if ( entity instanceof EntityLiving)//生物なら
            	{
            		EntityLiving living = (EntityLiving)entity;
            		if(!living.canEntityBeSeen(player) || living.getDistanceToEntity(player) > 8.0D)//間に壁があるか、ある程度の距離があれば有効
            		{
            			if(living.getAttackTarget() == player)
            			{
            				living.setAttackTarget(null);//使用者をターゲットから外す
            			}
            		}
                }
            }
        }
    	return itemStack;
    }
}