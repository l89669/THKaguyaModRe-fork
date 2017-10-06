package thKaguyaMod.item;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thKaguyaMod.init.THKaguyaItems;

public class ItemRoukanSenpuuzin extends ItemRoukanken
{

	// 楼観旋風刃　アンデッドに特攻　ガードはできない
	// 基本鉄の剣と同じ
	// 近づいた相手を勝手に切り裂く
    
    public ItemRoukanSenpuuzin(ToolMaterial toolMaterial)
    {
        super(toolMaterial);
    }
	
	//所持している限り常に呼ばれるメソッド
	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int i, boolean flag)
	{
		//プレイヤーでなければ失敗する
		if(entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)entity;
			

			//プレイヤーが手に何か持っているなら
			if(player.inventory.getCurrentItem() != null)
			{
				//プレイヤーが手に何か持ってアイテムがこのアイテムでなければ終了する
				if(player.inventory.getCurrentItem() != itemStack)
				{
					return;
				}
				//プレイヤーが手にしているものが楼観剣なら
				if(player.inventory.getCurrentItem().getItem() == THKaguyaItems.roukanSenpuuzin)
				{
					double xx, zz;
					//boolean isShot;
					xx = entity.motionX;
					zz = entity.motionZ;
					//if( Math.sqrt(xx*xx + zz*zz) > 3.0)//移動量が3.0を越しているなら
					{
						//指定範囲内のEntityのリストを取得
						List<?> list = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.addCoord(player.motionX, player.motionY, player.motionZ).expand(1.5D, 1.5D, 1.5D));
        				for (int j = 0; j < list.size(); j++)
        				{
							 Entity entity1 = (Entity)list.get(j);
            				if (!entity1.canBeCollidedWith())
            				{
                				continue;
            				}
            				//isShot = false;
        					MovingObjectPosition movingobjectposition = new MovingObjectPosition(entity1);
    						if(movingobjectposition.entityHit instanceof EntityLivingBase)
    						{
    							EntityLivingBase living = (EntityLivingBase)movingobjectposition.entityHit;
    							if( ( living.hurtTime > 0 ) || ( living.getHealth() <= 0.0F ) )
    							{
    								break;
    							}
    							
    							if(player.canEntityBeSeen(living))
    							{
    								if(living.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD)//アンデッドに当たったなら
    								{
    									living.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)entity), 16.0F);//20ダメージを追加する
    									player.onEnchantmentCritical(living);
    								}
    								else
    								{
    									living.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)entity), 8F);//それ以外なら8ダメージ
    								}
    								world.playSoundAtEntity(entity, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 4F + 0.8F));//音を出す
    							}
    						}
    						/*else if(	movingobjectposition.entityHit instanceof EntityTHShot &&
    									movingobjectposition.entityHit instanceof EntityTHLaser == false)
    						{
    							EntityTHShot shot = (EntityTHShot)movingobjectposition.entityHit;
    							
    							if( ( shot.ticksExisted < 10 ) || ( shot.getShotSize() < 0.1F ) )
    							{
    								break;
    							}
    							else if(shot.user != null)
    							{
    								THShotLib.createRandomRingShot(shot.user, shot.pos_Shot(), shot.angle, 0.0D, 0.4D, 0.03D, ShotData.shot(shot.getShotForm(), THShotLib.WHITE, shot.getShotSize() / 2.0F, shot.shotDamage), 3, 0.0D, 360F);
    							}
    							if(!world.isRemote)
    							{
    								shot.setDead();
    							}
    							isShot = true;
    						}
    						if(!isShot)
    						{
    							itemStack.damageItem(1, (EntityLivingBase)entity);//アイテムの耐久を1減らす
    						}*/
        					player.swingItem();//投げる動作をさせる
        					if(itemStack.getItemDamageForDisplay() == getMaxDamage())//アイテムの耐久が限界なら
        					{
        						itemStack.stackSize--;//消滅させる
        						break;//これ以上の処理を終了させる
        					}
        				}
					}
				}
			}
		}
	}
	
	/** アイテムを発光させるか返す
	 * @param itemStack 	: アイテムスタック
	 * @param pass			: ？
	 * @return trueなら発光する
	 */
	@Override
	public boolean hasEffect(ItemStack itemStack, int pass)
    {   
		return true;
    }
	
}

