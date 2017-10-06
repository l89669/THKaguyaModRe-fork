package thKaguyaMod.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thKaguyaMod.ShotData;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.shot.EntityTHLaser;
import thKaguyaMod.entity.shot.EntityTHShot;
import thKaguyaMod.init.THKaguyaItems;

public class ItemRoukanken extends ItemSword
{

	//楼観剣　アンデッドに特攻　ガードはできない
	//基本鉄の剣と同じ
    
    public ItemRoukanken(ToolMaterial toolMaterial)
    {
        super(toolMaterial);
        this.setTextureName("thkaguyamod:roukanSword");//テクスチャの指定
    }
	
	//ブロックを破壊する速度
	@Override
	public float getDigSpeed(ItemStack itemStack, Block block, int metadata)
	{
		//蜘蛛の巣か、葉ブロックなら破壊がかなり速くなる
    	if (block == Blocks.web || block == Blocks.leaves || block == Blocks.leaves2)
        {
            return 30F;
        }
    	//羊毛でもそこそこ速い
    	else if (block == Blocks.wool)
        {
            return 20F;
        }
    	//その他のブロックでも通常の３倍速い
        else
        {
            return super.getDigSpeed(itemStack, block, metadata) * 3F;
        }
	}

	//Entityに当たった時に呼び出される
	@Override
    public boolean hitEntity(ItemStack itemStack, EntityLivingBase living_hit, EntityLivingBase living_user)
    {
    	//当たったEntityがアンデットなら
    	if(living_hit.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD)
    	{
    		//ハート１０個分のダメージ
    		living_hit.attackEntityFrom(DamageSource.causeMobDamage(living_user), 16.0F);
    		if(living_user instanceof EntityPlayer)
    		{
    			EntityPlayer player = (EntityPlayer)living_user;
    			player.onEnchantmentCritical(living_hit);
    		}
    	}
        itemStack.damageItem(1, living_user);//耐久を１減らす
    	
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
    	player.setItemInUse(itemStack, getMaxItemUseDuration(itemStack));//アイテムの使用継続時間を記憶させる
        return itemStack;
    }
	
	//右クリックを押し終わったときに呼び出されるメソッド
	@Override
	public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int usedTime)
    {	
    	if(player.onGround)//プレイヤーが地面に接しているなら
    	{
    		//右クリック時間でダッシュするパワーを決める
	    	double パワー = (double)getMaxItemUseDuration(itemStack) - usedTime;
	    	//Javaって日本語名の変数使えるのね・・・
	    	
    		//ダッシュするパワーは20が限界
    		if(パワー > 20.0)
	    	{
	    		パワー = 20.0;
	    	}
	    	
    		パワー *= 0.3;
    		
    		//プレイヤーをダッシュするパワーの分だけ前進させる
	    	player.motionX = -Math.sin(Math.toRadians(player.rotationYaw)) * パワー;
	    	player.motionZ =  Math.cos(Math.toRadians(player.rotationYaw)) * パワー;
	    	
			if(!player.capabilities.isCreativeMode)//クリエイティブでないなら
			{
				player.addExhaustion(1.5F);//使うたびに少し腹が減る
			}
       		
    		itemStack.damageItem(1, player);
    	}
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
				if(player.inventory.getCurrentItem().getItem() == THKaguyaItems.roukanken)
				{
					double xx, zz;
					boolean isShot;
					xx = entity.motionX;
					zz = entity.motionZ;
					if( Math.sqrt(xx*xx + zz*zz) > 3.0)//移動量が3.0を越しているなら
					{
						//指定範囲内のEntityのリストを取得
						List<?> list = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.addCoord(player.motionX, player.motionY, player.motionZ).expand(1.0D, 1.0D, 1.0D));
        				for (int j = 0; j < list.size(); j++)
        				{
							 Entity entity1 = (Entity)list.get(j);
            				if (!entity1.canBeCollidedWith())
            				{
                				continue;
            				}
            				isShot = false;
        					MovingObjectPosition movingobjectposition = new MovingObjectPosition(entity1);
    						if(movingobjectposition.entityHit instanceof EntityLivingBase)
    						{
    							EntityLivingBase living = (EntityLivingBase)movingobjectposition.entityHit;
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
    						else if(	movingobjectposition.entityHit instanceof EntityTHShot &&
    									movingobjectposition.entityHit instanceof EntityTHLaser == false)
    						{
    							EntityTHShot shot = (EntityTHShot)movingobjectposition.entityHit;
    							if(shot.user != null)
    							{
    								THShotLib.createRandomRingShot(shot.user, shot.pos_Shot(), shot.angle, 0.0D, 0.4D, 0.03D, ShotData.shot(shot.getShotForm(), THShotLib.WHITE, shot.getShotSize() / 2.0F, shot.shotDamage), 3, 0.0D, 90F);
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
    						}
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
	
	//右クリックを長押しした祭の動作を指定
	@Override
	public EnumAction getItemUseAction(ItemStack itemStack)
    {
        return EnumAction.bow;//弓の構えをする
    }

	//エンチャント可能か？
	@Override
    public int getItemEnchantability()
    {
        return 0;//エンチャント不可
    }
	
	//Forgeの追加メソッド　壊したブロックを取得できるかどうか？
	@Override
    public boolean canHarvestBlock(Block block, ItemStack itemStack)
    {
    	return true;//なんでも取得可能　切れぬものはあんm（ry
    }
	
	//Forgeの追加メソッド　エンチャントブックの使用を許可するか
	@Override
	public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2)
    {
        return false;
    }
	
}
