package thKaguyaMod.entity.item;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thKaguyaMod.THKaguyaLib;
import thKaguyaMod.entity.living.EntityDanmakuMob;
import thKaguyaMod.init.THKaguyaItems;

/** 呪いのデコイ人形のEntity */
public class EntityCursedDecoyDoll extends EntityDanmakuMob
{
	/** 使用者 */
	public EntityLivingBase userEntity;

    public EntityCursedDecoyDoll(World world)
    {
        super(world);
        this.setSize(1.0F, 1.8F);//MOBの当たり判定の大きさ 横奥行き、高さ、大きさ
        
        this.experienceValue = 1;//経験値の量
        
        this.setDanmakuMobName("");
        this.setSpecies(this.SPECIES_SHIKIGAMI_DOLL);
    	
    	this.setDanmakuPattern(NOT_ATTACK);
    	this.setMaxHP(40.0F);
        this.setHealth(40.0F);
        this.setSpeed(0.0F);
        this.setAttackDistance(0.0D);
    	this.setDetectionDistance(0.0D);
    	this.setFlyingHeight(0);
    	this.isFlyingMode = false;
    	
    	this.isSpellCardMode = false;
    	this.attackInterval = 0;
    }
	
    public EntityCursedDecoyDoll(World world,EntityLivingBase living)
    {
        this(world);

    	userEntity = living;//使用者をuserEntityに保存
    	this.setPosition(living.posX, living.posY, living.posZ);

    }
    
    //周りの妖精を呼び出すことができるか
    @Override
    protected boolean canFairyCall()
    {
    	return false;
    }

	/**
	 * 毎tick行う処理
	 */
    @Override
    public void onUpdate()
    {
        super.onUpdate();

    	//使用者がいないなら
    	/*if(!worldObj.isRemote && userEntity == null)
    	{
    		//アイテム化させる
    		if(!worldObj.isRemote)
    		{
    			setDead();
    		}
    		return;
    	}*/
    	
    	//30ticksを超えているなら
    	if(ticksExisted > 180)
    	{
    		//アイテム化させる
    		//THKaguyaLib.itemEffectFinish(this, userEntity, THKaguyaItems.sakuya_watch);
    		if(!worldObj.isRemote)
    		{
    			setDead();
    		}
    	}


    	int playerCount = 0;//停止空間内のプレイヤーの数を数える　誰もいなければ消滅させる
    	
    	//***********停止空間（時計じゃなくてもっと大きな見えない壁）とEntityの当たり判定を取る****************//
    	MovingObjectPosition movingObjectPosition = new MovingObjectPosition(this);
    	Entity entity = null;
    	boolean dubbleWatch = false;
    	boolean pass = false;
    	double effectiveBoundary = 40.0D;//停止空間の有効範囲
    	//停止空間内のEntityを全て取得
    	List<?> list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(effectiveBoundary, effectiveBoundary, effectiveBoundary));

        if (list != null && list.size() > 0)//取得したリストにEntityがいるなら
        {
            for (int j1 = 0; j1 < list.size(); j1++)//取得したEntityリストの最初から最後まで見る
            {
                entity = (Entity)list.get(j1);//entityに取得したリストのEntityを代入
            	if (entity != null )//Entityがいるなら
        		{
            		movingObjectPosition = new MovingObjectPosition(entity);//MovingObjectPositionにEntityを登録
        		}
            	if(entity instanceof EntityPlayer)//プレイヤーなら
            	{
            		playerCount++;//範囲内のプレイヤー数カウントを増加
            	}
            	//movingObjectPositionがあり、それが使用者でないなら
        		if (/*!worldObj.isRemote &&*/ movingObjectPosition != null && movingObjectPosition.entityHit != userEntity && movingObjectPosition.entityHit.riddenByEntity != userEntity
        				&& !(movingObjectPosition.entityHit instanceof EntityItemFrame)
        				&& !(movingObjectPosition.entityHit instanceof EntityPainting))
        		{
        			pass = false;
        			//時計が同時に２つ出現しているときの処理。全ての時計を無効化しアイテムに戻す。
        			if(movingObjectPosition.entityHit instanceof EntityCursedDecoyDoll)
        			{
        				EntityCursedDecoyDoll decoy = (EntityCursedDecoyDoll)movingObjectPosition.entityHit;
        				//アイテム化させる
        	    		THKaguyaLib.itemEffectFinish(decoy, decoy.userEntity, THKaguyaItems.cursedDecoyDoll);
        	    		dubbleWatch = true;
        			}
        			if(!pass)
        			{
        				inDecoyEffectedArea(movingObjectPosition);//呪いのデコイ人形の有効範囲に入っていた場合の処理
        			}
        		}
            }
        }

    	if(playerCount == 0)//範囲内にプレイヤーが一人もいなかったなら
    	{
    		if(!worldObj.isRemote)
    		{
    			THKaguyaLib.itemEffectFinish(this, userEntity, THKaguyaItems.cursedDecoyDoll);//終了処理に移る
    		}
    	}
    	
    	if(dubbleWatch)
    	{
    		//アイテム化させる
    		THKaguyaLib.itemEffectFinish(this, userEntity, THKaguyaItems.cursedDecoyDoll);
    	}

        //************************************************************************************//
    	
    	this.setRotation(this.prevRotationYaw, this.prevRotationPitch);
    	
    }


	/**
	 *  呪いのデコイ人形の有効範囲に入っていたときの処理
	 * @param movingObjectPosition
	 */
	public void inDecoyEffectedArea(MovingObjectPosition movingObjectPosition)
    {
		if(movingObjectPosition.entityHit instanceof EntityCreature)
    	{
			EntityCreature effectedCreature = (EntityCreature)movingObjectPosition.entityHit;
			
			if( effectedCreature.getEntityToAttack() != null )
			{
				effectedCreature.setTarget(this);
			}
		}
		if(movingObjectPosition.entityHit instanceof EntityLivingBase)
    	{
			EntityLivingBase effectedLiving = (EntityLivingBase)movingObjectPosition.entityHit;
			
			//if(effectedLiving)
			effectedLiving.setRevengeTarget(this);
    	}
    	
    }
	
	/*時間を操る
	 * hitEntity : 操る対象
	 * timeRate  : 進む時間の倍率。減速しか扱えず、整数分の１の速度にしかできない。ex 1/2, 1/3, 1/4, 1/5...
	 */
	/*private void slowDownSpeed(Entity hitEntity, int timeRateNum,int timeRateDen)
	{
		float timeRate = (float)timeRateNum / (float)timeRateDen;
		
		
		if(timeRate < 1.0)
		{
			hitEntity.motionX -= (hitEntity.posX-(hitEntity.prevPosX)) * timeRate;
			hitEntity.motionY -= (hitEntity.posY-(hitEntity.prevPosY)) * timeRate;
			hitEntity.motionZ -= (hitEntity.posZ-(hitEntity.prevPosZ)) * timeRate;
			hitEntity.fallDistance -= 0.076865F * timeRate;//落下距離を半減（空中待機時間で変動してしまう）
			if( ticksExisted % timeRateDen < timeRateNum)//偶数フレームのみ処理
			{
				hitEntity.ticksExisted--;//誕生してからのカウントを増やさない
				if(hitEntity instanceof EntityLivingBase)
				{
					EntityLivingBase EntityLivingBase = (EntityLivingBase)hitEntity;
					EntityLivingBase.rotationYawHead = EntityLivingBase.prevRotationYawHead;
	
					if(EntityLivingBase instanceof EntityCreeper)
					{
						EntityCreeper entityCreeper = (EntityCreeper)EntityLivingBase;
						//if( count % timeRateDen < timeRateNum)
						{
							entityCreeper.setCreeperState(-1);
						}
						//entitycreeper.timeSinceIgnited --;
					}
					else if(EntityLivingBase instanceof EntityGhast)
					{
						EntityGhast entityGhast = (EntityGhast)EntityLivingBase;
						entityGhast.attackCounter--;
					}
				}
			}
		}
		else
		{
			int timeRate2 = (int)timeRate;
			for(int i = 2; i <= timeRate2; i++)
			{
				hitEntity.ticksExisted++;
				hitEntity.onUpdate();
			}
		}
	}*/
	
    //生きてるときに出す音
    @Override
    protected String getLivingSound()
    {
        return "";
    }

    //攻撃を受けたときの音
    @Override
    protected String getHurtSound()
    {
        return "";
    }

	//倒れたときの音
    @Override
    protected String getDeathSound()
    {
        return "";
    }

}
