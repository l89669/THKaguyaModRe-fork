package thKaguyaMod.item;

import static thKaguyaMod.DanmakuConstants.*;
import static thKaguyaMod.THShotLib.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
//import net.minecraft.util.Icon;
import net.minecraft.util.Vec3;// 変更１
import net.minecraft.world.World;
import thKaguyaMod.ShotData;
import thKaguyaMod.THShotLib;

public class ItemNuclearControlRod extends Item
{
       
	//お空の制御棒
	//核弾を発射する。貯めるほど大きくなる
	
	public ItemNuclearControlRod()
	{
		super();
		this.setTextureName("thkaguyamod:nuclearControlRod");//テクスチャの指定
		setMaxDamage(36);//耐久値
		this.maxStackSize = 1;//最大スタック数
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
		player.setItemInUse(itemStack, getMaxItemUseDuration(itemStack));
		
		Vec3 vec3 = THShotLib.getVecFromAngle(player.rotationYaw, player.rotationPitch, 1.0D);
		Vec3 vec3_2 = THShotLib.getVecFromAngle(player.rotationYaw + 20F, player.rotationPitch, 0.3D + 0.3D / 2.0D);

		ShotData shot = ShotData.shot(0, ORANGE, 0.3F, 10.0F, 0, 70);
		THShotLib.createNuclearShot(player, player, pos(player.posX + vec3_2.xCoord, THShotLib.getPosYFromEye(player, -0.1D) + vec3_2.yCoord, player.posZ + vec3_2.zCoord), vec3, 0F,
				rotate_Default(), 0F, 9999, 0.0D, 0.8D, 0.0D, gravity_Zero(), shot);
		
		/*EntityNuclearShot nuclearShot = new EntityNuclearShot(world, player, player, player.posX + vec3_2.xCoord, THShotLib.getPosYFromEye(player, -0.1D) + vec3_2.yCoord, player.posZ + vec3_2.zCoord, vec3.xCoord, vec3.yCoord, vec3.zCoord,
				0.0D, 0.8D, 0.00D, 0.0D, 0.0D, 0.0D, 10.0F, THShotLib.ORANGE, 0.3F, 70, 0, 0);
		if(!world.isRemote)
		{
			world.spawnEntityInWorld(nuclearShot);//核弾を出現させる
		}*/
		
		return itemStack;
	}
       
	//右クリックを終了したときに呼び出されるメソッド
	@Override
	public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int usedTime)
	{
		int time =  getMaxItemUseDuration(itemStack) - usedTime;
		if(time > 100)
		{
			time = 100;
		}
		//爆発音
		world.playSoundEffect(player.posX, player.posY, player.posZ, "random.explode", 4.0F, (1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F) * 0.7F);

		//反動処理
		Vec3 vec3 = THShotLib.getVecFromAngle(player.rotationYaw, player.rotationPitch, time / 25.0D);
		player.motionX -= vec3.xCoord;
		player.motionY -= vec3.yCoord;
		player.motionZ -= vec3.zCoord;
		
		//耐久を１減らす
		itemStack.damageItem(1, player);
		
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
		return EnumAction.bow;
	}
	
	//アイテムを大きく表示するか
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