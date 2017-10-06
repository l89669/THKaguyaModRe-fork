package thKaguyaMod.item;

import static thKaguyaMod.DanmakuConstants.*;
import static thKaguyaMod.THShotLib.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.ShotData;
import thKaguyaMod.THShotLib;

public class ItemYuyukoFan extends Item
{
	
	//幽々子の扇
	//死に誘う扇
	//蝶の弾幕をいくらでも出せるが、どんどん腹が減る
	//
	
	public ItemYuyukoFan()
	{
		super();
		this.setTextureName("thkaguyamod:yuyukoOugi");//テクスチャの指定
		setMaxDamage(444);
		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabs.tabCombat);
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
        player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));

        return itemStack;
    }
	
	//右クリックを終了したときに呼ばれる
	@Override
	public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int usedTime)
    {
    	int color, color2, shotNum,shotNum3;
    	shotNum = 32 + getMaxItemUseDuration(itemStack) - usedTime;//発射される弾数を代入。　最低32発。　getMaxItemUseDuration(itemStack)-usedTimeは右クリック継続時間で変化する部分
    	if(shotNum > 72)
    	{
    		shotNum = 72;//最大でも72発まで
    	}
    	else if(shotNum % 3 != 0)
    	{
    		shotNum = (int)(shotNum / 3) * 3;
    	}
    	shotNum3 = shotNum / 3;
    	
    	if(player.isSneaking())//スニーク状態なら
    	{
			int color3;
			color = BLUE + itemRand.nextInt(4);//弾の色をランダムで決める
			color2 = color + 1;
    		if(color2 >= BLUE + 4)
    		{
    			color2 = BLUE;
    		}

    		//弾幕を発射。　単純な全方位ではなく、弾速の違う３つをセットに全方位に放つ
    		for(int i = 0 ; i < 3; i++)
    		{
        		color3 = color;
        		if(i == 1)
        		{
        			color3 = color2;
        		}
    			double speed = 0.1D + (double)(i * 0.1D);
    			THShotLib.createCircleShot(player, player, THShotLib.pos_Living(player), player.getLookVec(), 12F, 10,
    					speed, speed, 0.0D, THShotLib.gravity_Zero(), ShotData.shot(FORM_BUTTERFLY, color3, SIZE[FORM_BUTTERFLY], 4.0F, 1, 80), shotNum3, 0.5D, 0F);
    			THShotLib.createCircleShot(player, player, THShotLib.pos_Living(player), player.getLookVec(), -12F, 10,
    					speed, speed, 0.0D, THShotLib.gravity_Zero(), ShotData.shot(FORM_BUTTERFLY, color3, SIZE[FORM_BUTTERFLY], 4.0F, 1, 80), shotNum3, 0.5D, 0F);
    		}
    	}
    	else//スニーク状態でないなら
    	{
    		color = BLUE + itemRand.nextInt(4);//弾の色をランダムで決める
    		color2 = color + 1;
    		if(color2 >= BLUE + 4)
    		{
    			color2 = BLUE;
    		}
    		Vec3 look = angle(player.rotationYaw, player.rotationPitch);
    		
			THShotLib.createWideShot(player, player, THShotLib.pos(player.posX + look.xCoord * 0.5D, THShotLib.getPosYFromEye(player) + look.yCoord * 0.5D, player.posZ + look.zCoord * 0.5D) , look,
					14.4F, 8, 0.0D, 0.45D, 0.02D, THShotLib.gravity_Zero(), ShotData.shot(FORM_BUTTERFLY, color, SIZE[FORM_BUTTERFLY], 4.0F, 1, 80), shotNum3 + 1, 90F, 0.2D, -120F);
			THShotLib.createWideShot(player, player, THShotLib.pos(player.posX + look.xCoord * 0.5D, THShotLib.getPosYFromEye(player) + look.yCoord * 0.5D, player.posZ + look.zCoord * 0.5D), look,
					-14.4F, 8, 0.0D, 0.45D, 0.02D, THShotLib.gravity_Zero(), ShotData.shot(FORM_BUTTERFLY, color2, SIZE[FORM_BUTTERFLY], 4.0F, 1, 80), shotNum3 + 1, 90F, 0.2D, 120F);
    	}
		
		if(!player.capabilities.isCreativeMode)//クリエイティブでないなら
		{
			player.addExhaustion(1.5F);//使うたびに少し腹が減る
		}
		world.playSoundAtEntity(player, "random.orb", 0.5F, 1.0F);//音を出す
		itemStack.damageItem(1, player);//
   	}
	
	//Entityに当てたときに呼ばれる
	@Override
    public boolean hitEntity(ItemStack itemStack, EntityLivingBase living_hit, EntityLivingBase living_used)
    {
		//体力がハート１０以下で、無敵時間でないなら
		if(living_hit.getHealth() > 0 && living_hit.getHealth() < 20 && living_hit.hurtTime > 0)
		{
			//５％の確立で
			if(itemRand.nextInt(100) < 5)
			{
				//使用者は当てた相手の体力を全て吸収する
				living_used.heal(living_hit.getHealth());
				//当てた相手に4444ダメージ（即死級）
				living_hit.attackEntityFrom(DamageSource.causeMobDamage(living_used), 4444);
				//村人ゾンビが回復するときの音を出す
				living_used.worldObj.playSoundAtEntity(living_used, "mob.zombie.unfect", 1.0F, 40.0F);
			}
		}
		//耐久を1減らす
		itemStack.damageItem(1, living_used);
		return true;
    }
	
	//
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
	public boolean hasEffect(ItemStack itemStack, int pass)
	{   
		return true;
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