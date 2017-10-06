package thKaguyaMod.item;

import static thKaguyaMod.DanmakuConstants.*;
import static thKaguyaMod.THShotLib.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.shot.EntityOnmyoudama;

public class ItemOharaibouR extends Item
{
	//博麗のお祓い棒
	
	public ItemOharaibouR()
	{
		super();
		this.setTextureName("thkaguyamod:HakureiOharaibou");//テクスチャの指定
		setMaxDamage(89);//耐久値
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
		
		Vec3 vec3 = THShotLib.getVecFromAngle(-player.rotationYaw, -player.rotationPitch, 0.6D);

		EntityOnmyoudama onmyoudama = new EntityOnmyoudama(world, player, player, pos(player.posX + vec3.xCoord, getPosYFromEye(player, -0.1D) + vec3.yCoord, player.posZ + vec3.zCoord), vec3, 0F,
				rotate_Default(), 0F, 9999, 0.0D, 1.8D, 0.00D, gravity_Default(), THShotLib.ORANGE, 0.3F, 10.0F, 0, 180, BOUND04);
		if(!world.isRemote)
		{
			world.spawnEntityInWorld(onmyoudama);//陰陽玉を出現させる
		}
		
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

		world.playSoundEffect(player.posX, player.posY, player.posZ, "random.bow", 2.0F, 0.3F);
		
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
		return EnumAction.block;
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