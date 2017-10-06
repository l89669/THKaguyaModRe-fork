package thKaguyaMod.item;

import static thKaguyaMod.DanmakuConstants.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.LaserData;
import thKaguyaMod.THKaguyaLib;
import thKaguyaMod.THShotLib;

public class ItemSpearTheGungnir extends Item
{
	
	//スピア・ザ・グングニル
	
	public ItemSpearTheGungnir()
	{
		super();
		this.setTextureName("thkaguyamod:Gungnir");//テクスチャの指定
		setMaxDamage(95);//耐久値
		this.maxStackSize = 1;//最大スタック数
		setNoRepair();//修理不可
		this.setCreativeTab(CreativeTabs.tabCombat);//クリエイティブタブの設定
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
    	//神槍「スピア・ザ・グングニル」を使用する

		boolean spellCard = THKaguyaLib.checkSpellCardDeclaration(world, itemStack, player, 27, 2, true);
		//スペルカード神槍「スピア・ザ・グングニル」を宣言
		if(spellCard)
		{
    		Vec3 look = player.getLookVec();
    		THShotLib.createLaserA(player, player, THShotLib.pos(player.posX, THShotLib.getPosYFromEye(player, + 0.6D), player.posZ), THShotLib.angle(-look.xCoord, look.yCoord, -look.zCoord), 0.0D, 2.0D, 0.0D, THShotLib.gravity_Zero(),
    				LaserData.laser(LASER_GUNGNIR, RED, 1.0F, 10.0F, 14F, 0, 120, GUNGNIR));
    		if(!world.isRemote)
    		{
    			world.playSoundAtEntity(player, "mob.zombie.remedy", 0.5F, 2.7F);//音を出す
    			world.playSoundAtEntity(player, "mob.wither.spawn", 0.2F, 10.0F);//音を出す
    		}
        	if(!player.capabilities.isCreativeMode)
        	{
        		itemStack.setItemDamage(itemStack.getItemDamage() + 1);
        	}
    		//itemStack.damageItem(1, player);//
		}

    		
       	return itemStack;
    }
	
	//アイテムを大きく表示する
	@Override
	public boolean isFull3D()
    {
        return true;
    }
    
    //エンチャント不可
    @Override
    public int getItemEnchantability()
    {
        return 0;
    }
	
	//Forgeの追加メソッド　エンチャントブックの使用を許可するか
	@Override
	public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2)
    {
        return false;
    }
}