package thKaguyaMod.item;

import static thKaguyaMod.DanmakuConstants.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.ShotData;
import thKaguyaMod.THShotLib;

public class ItemKappaWaterPistol extends Item
{
	
	//河童の水鉄砲
	//水を補充して水の弾幕を発射できる
	
	public ItemKappaWaterPistol()
	{
		super();
		this.setTextureName("thkaguyamod:kappaWaterPistol");//テクスチャの指定
		setMaxDamage(39);//耐久値
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
		//水を補充する処理
		if(player.isSneaking())
		{
	    	int blockX = 0, blockY = 0, blockZ = 0;
	    	
	        MovingObjectPosition movingobjectposition = getMovingObjectPositionFromPlayer(world, player, true);
	        
	        if (movingobjectposition != null)
	        {
	        	blockX = movingobjectposition.blockX;
				blockY = movingobjectposition.blockY;
	        	blockZ = movingobjectposition.blockZ;
	        	
	        	if (world.getBlock(blockX, blockY, blockZ) == Blocks.water && world.getBlockMetadata(blockX, blockY, blockZ) == 0)
		        {
		        	//world.setBlockToAir(blockX, blockY, blockZ);
		        	itemStack.setItemDamage(itemStack.getItemDamage() - 2);
		        	player.swingItem();//投げる動作をさせる
		        	return itemStack;//補充ができたら弾幕は出さずに終了
		        }
			}
	        

		}
		
		//壊れないようにする処理
        if(itemStack.getItemDamage() == itemStack.getMaxDamage() - 1)
        {
        	return itemStack;
        }
        
        //水弾幕を出す処理
		double speed = 0.3D;
		Vec3 vec3 = THShotLib.getVecFromAngle(player.rotationYaw + 40F, player.rotationPitch, 0.4D);
		double xPos = player.posX + vec3.xCoord;
		double yPos = THShotLib.getPosYFromEye(player, -0.1D) + vec3.yCoord;
		double zPos = player.posZ + vec3.zCoord;
		Vec3 pos = THShotLib.pos(xPos, yPos, zPos);
		ShotData shot = ShotData.shot(FORM_LIGHT, BLUE, 0.2F, 4.0F, 0, 120);

		THShotLib.createShot(player, player, pos, THShotLib.angle(player.rotationYaw, player.rotationPitch - 7F), 0F, 1.0D - speed, 0.0D, 0.4D, THShotLib.gravity_Default(), shot);
        THShotLib.createShot(player, player, pos, THShotLib.angle(player.rotationYaw, player.rotationPitch), 0F, 	1.1D - speed, 0.0D, 0.4D, THShotLib.gravity_Default(), shot);
        THShotLib.createShot(player, player, pos, THShotLib.angle(player.rotationYaw, player.rotationPitch + 7F), 0F, 1.2D - speed, 0.0D, 0.4D, THShotLib.gravity_Default(), shot);
        speed += 0.07D;

        world.playSoundAtEntity(player, "liquid.splash", 0.5F, 4.0F);//音を出す
		itemStack.damageItem(1, player);//

        return itemStack;
    }
	
	//アイテムを使ったときのアクションを指定
	@Override
	public EnumAction getItemUseAction(ItemStack itemStack)
    {
        return EnumAction.bow;//右クリック時は剣のガードアクション
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
