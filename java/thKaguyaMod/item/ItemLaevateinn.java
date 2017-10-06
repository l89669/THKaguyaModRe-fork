package thKaguyaMod.item;

import static thKaguyaMod.DanmakuConstants.*;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.LaserData;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.shot.EntityTHSetLaser;

public class ItemLaevateinn extends ItemSword
{
	
	/*
	 * レーヴァテイン
	 * 通常の剣としても使えて、相手は燃える
	 * 右クリックで大剣を横に振り回してかなりの範囲をなぎ払う
	 * Shift＋右クリックで縦にぶった切る。前方に死角なし
	 */
	
	public ItemLaevateinn(ToolMaterial toolMaterial)
	{
		super(toolMaterial);
		this.setTextureName("thkaguyamod:Laevateinn");//テクスチャの指定
		setNoRepair();//修理不可
	}
	
	//左クリックでEntityに当たった場合に呼び出されるメソッド
	@Override
	public boolean hitEntity(ItemStack itemStack, EntityLivingBase living_hit, EntityLivingBase living_used)
    {
        super.hitEntity(itemStack, living_hit, living_used);
    	living_hit.setFire(itemRand.nextInt(3) + 3);//当たったEntityに着火　数値が大きいほど長くなるのかな？
        return true;
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
		//周囲のEntityを取得
		List<?> list = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.addCoord(0.0D, 0.0D, 0.0D).expand(20.0D, 20.0D, 20.0D));
		for(int k = 0; k < list.size(); k++)
		{
			Entity entity = (Entity)list.get(k);
			if(entity instanceof EntityTHSetLaser)//設置レーザーがあるなら
			{
				EntityTHSetLaser laser = (EntityTHSetLaser)entity;
				if(laser.source == player)//その設置レーザーの持ち主がレーヴァテインの持ち主と同じなら
				{
					return itemStack;//レーヴァテインは出せない
				}
			}
		}
		
		Vec3 rotate;
		Vec3 move;// = thShotLib.getRotationVectorFromAngle(player.rotationYaw, player.rotationPitch + 155F, 0F, 1.0D);
		
		if(player.isSneaking())
		{	
			rotate = THShotLib.getVecFromAngle(player.rotationYaw + 90F,0F);
			move = THShotLib.getVecFromAngle(player.rotationYaw, player.rotationPitch - 90F);
			THShotLib.createLaserB(player, player, THShotLib.pos(0.0D, THShotLib.getPosYFromEye(player) - player.posY, 0.0D), move, rotate, -6F, 9999, LaserData.laser(RED + 16, 0.6F, 20.8F, 7.0F, 0, 30, FIRE), player, 1.5D, 1.0F).setAngleZ(90F);
		}
		else
		{
			Vec3 look = player.getLookVec();
			rotate = THShotLib.getVecFromAngle(player.rotationYaw, player.rotationPitch - 90F);
			//move = thShotLib.getVecFromAngle(player.rotationYaw - 90F, player.rotationPitch);
			move = THShotLib.getVectorFromRotation(rotate.xCoord, rotate.yCoord, rotate.zCoord, look.xCoord, look.yCoord, look.zCoord, 90F);
			THShotLib.createLaserB(player, player, THShotLib.pos(0.0D, THShotLib.getPosYFromEye(player) - player.posY, 0.0D), move, rotate, -6F, 9999, LaserData.laser(RED + 16, 0.6F, 20.8F, 7.0F, 0, 30, FIRE), player, 1.5D, 1.0F);
		}
        world.playSoundAtEntity(player, "mob.ghast.fireball", 0.5F, 1.0F);//音を出す
		itemStack.damageItem(1, player);//

        return itemStack;
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
