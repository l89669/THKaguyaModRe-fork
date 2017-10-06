package thKaguyaMod.item;

import static thKaguyaMod.THShotLib.*;

import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.shot.EntityDragonNeckJewel;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDragonNeckJewel extends Item
{
	
	/*
	 * 龍の頸の玉を投げつけて、落下地点に輝かしい弾幕を発生させる
	 */
	
	public ItemDragonNeckJewel()
	{
		super();
		this.setTextureName("thkaguyamod:ryuuTama");//テクスチャの指定
		setMaxDamage(300);//最大耐久値だが、ここではチャージ値として扱う
		maxStackSize = 1;//最大スタック数
		setCreativeTab(CreativeTabs.tabCombat);//クリエイティブの武器タブに登録
		setNoRepair();//修理不可
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
    	float[] r = {1.0F, 0.1F, 0.1F, 1.0F, 1.0F};
    	float[] g = {0.1F, 1.0F, 0.1F, 1.0F, 0.1F};
    	float[] b = {0.1F, 0.1F, 1.0F, 0.1F, 1.0F};
    	Vec3 vec3;
    	EntityDragonNeckJewel dragonNeckJewel;
    	
    	if(!player.isSneaking() && itemStack.getItemDamageForDisplay() < 229)//受けているダメージが229よりちいさいなら　つまり耐久値が70/300より大きいなら
    	{
    		vec3 = THShotLib.getVecFromAngle(player.rotationYaw, player.rotationPitch, 1.0D);
    		Random random = new Random();
    		int rand = random.nextInt(5);
    		dragonNeckJewel = new EntityDragonNeckJewel(world, player, player, pos(player.posX, THShotLib.getPosYFromEye(player, -0.2D), player.posZ), vec3, r[rand], g[rand], b[rand]);
       		
    		world.playSoundAtEntity(player, "random.orb", 0.5F, 0.4F / (itemRand.nextFloat() * 4F + 0.8F));//音を出す
       		if(!world.isRemote)
       		{
           		world.spawnEntityInWorld(dragonNeckJewel);//龍の頸の玉を発射
       		}
       		
       		itemStack.damageItem(70, player);//耐久を70減らす
    	}
    	else if(player.isSneaking() && itemStack.getItemDamageForDisplay() < 1)//スニーク時は５つ同時発射　5色の雷を落とす
    	{
    		float angle = -40F;
    		for(int i = 0; i < 5; i++)
    		{
    			vec3 = THShotLib.getRotationVectorFromAngle(player.rotationYaw, player.rotationPitch, angle, 1.0D);
    			dragonNeckJewel = new EntityDragonNeckJewel(world, player, player, pos(player.posX, THShotLib.getPosYFromEye(player, -0.2),player.posZ), vec3, r[i], g[i], b[i]);
    			if(!world.isRemote)
    			{
    				world.spawnEntityInWorld(dragonNeckJewel);//龍の頸の玉を発射
    			}
    			angle += 20F;
    		}
       		world.playSoundAtEntity(player, "random.orb", 0.5F, 0.4F / (itemRand.nextFloat() * 4F + 0.8F));//音を出す
       		
       		itemStack.damageItem(299, player);//耐久を最大まで減らす
    	}
    		
       	return itemStack;
    }
	
	//インベントリにあるかぎり呼び出されるメソッド
	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int i, boolean flag)
    {
    	//耐久が減っていたら徐々に回復
    	if(entity.ticksExisted % 20 == 0 && itemStack.isItemDamaged() == true)//ダメージを受けているなら
    	{
    		itemStack.damageItem(-20, (EntityLivingBase)entity);//毎フレーム耐久を1回復
    	}  
    }
	
	//アイテムを発光させる。 trueなら発光
	@SideOnly(Side.CLIENT)
	@Override
	public boolean hasEffect(ItemStack itemStack, int pass)
	{   
		return true;
    }
	
	//Forgeの追加メソッド　エンチャントブックの使用を許可するか？
	@Override
	public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2)
    {
        return false;
    }
}