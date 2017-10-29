package thKaguyaMod.entity.item;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thKaguyaMod.THKaguyaLib;
import thKaguyaMod.entity.spellcard.EntitySpellCard;
import thKaguyaMod.init.THKaguyaItems;

/** 時間を停止させる空間 */
public class EntitySakuyaWatch extends Entity
{
	/** 使用者 */
	public EntityLivingBase userEntity;
	private int count;
	private int mode;
	private float watchRotation;
	private boolean isSpellCard;
	
	/**時間停止*/
	public static final int TIME_STOP = 0;
	/**特定のスペルカード内の時間停止*/
	public static final int TIME_STOP_IN_SPELLCARD = 1;
	/**時間減速1/2*/
	public static final int TIME_HALF = 2;
	/**時間制限付き時間停止*/
	public static final int TIME_STOP_WITH_LIMIT = 5;
	/**時間制限付き時間減速1/2*/
	public static final int TIME_HALF_WITH_LIMIT = 6;

    public EntitySakuyaWatch(World world)
    {
        super(world);
        ignoreFrustumCheck = true;//中心が画面から外れても描画される
        preventEntitySpawning = true;
        setSize(1.0F, 1.0F);//サイズを設定　平面上の横と奥行きサイズ、高さ
        yOffset = 0.0F;//高さを設定
    	mode = 2;
    	//count = 0;
    	//watchRotation = 0.0F;
    	this.shouldRenderInPass(-2);
    }
	
    public EntitySakuyaWatch(World world,EntityLivingBase living, int pmode)
    {
        this(world);

    	userEntity = living;//使用者をshootingEntityに保存
    	THKaguyaLib.itemEffectFollowUser(this, userEntity, 1.2D, -30F);
		mode = pmode;

		isSpellCard = mode == TIME_STOP_IN_SPELLCARD;

    }
    
    /** 生成時に一度だけ呼ばれる処理 */
    @Override
	protected void entityInit()
    {
    }
	
	/**
	 * 押すことができるか
	 */
    @Override
    public boolean canBePushed()
    {
        return false;
    }

	/**
	 * 他のEntityと衝突するか 右クリックできるかもこれで判定
	 */
    @Override
    public boolean canBeCollidedWith()
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
    	if(!worldObj.isRemote && userEntity == null)
    	{
    		//アイテム化させる
    		if(!isSpellCard)
    		{
    			THKaguyaLib.itemEffectFinish(this, userEntity, THKaguyaItems.sakuya_watch);
    		}
    		else
    		{
    			if(!worldObj.isRemote)
    			{
    				setDead();
    			}
    		}
    		return;
    	}
    	
    	//スペルカード内の時間停止で、30ticksを超えているなら
    	if(mode == this.TIME_STOP_IN_SPELLCARD && ticksExisted > 60)
    	{
    		//アイテム化させる
    		//THKaguyaLib.itemEffectFinish(this, userEntity, THKaguyaItems.sakuya_watch);
    		if(!worldObj.isRemote)
    		{
    			setDead();
    		}
    	}
    	else if( mode == this.TIME_STOP_WITH_LIMIT && ticksExisted > 100)
    	{
     		//アイテム化させる
    		THKaguyaLib.itemEffectFinish(this, userEntity, THKaguyaItems.sakuya_watch);
    	}
    	else if(mode == this.TIME_HALF_WITH_LIMIT && ticksExisted > 160)
    	{
    		//アイテム化させる
    		THKaguyaLib.itemEffectFinish(this, userEntity, THKaguyaItems.sakuya_watch);
    	}
    	
    	//使用者がいるなら
    	if(!worldObj.isRemote && userEntity != null)
    	{
    		if(userEntity instanceof EntityPlayer)
    		{
    			EntityPlayer userEntity_p = (EntityPlayer)userEntity;
	    		if(!userEntity_p.capabilities.isCreativeMode)//クリエイティブモードでないなら
	    		{
	    			if(mode == this.TIME_STOP)//時間停止モードなら
	    			{
	    				userEntity_p.addExhaustion(0.25F);//満腹度を大きく減らす
	    			}
	    			else if(mode == this.TIME_HALF)//時間減速モードなら
	    			{
	    				userEntity_p.addExhaustion(0.10F);//満腹度を少し減らす
	    			}
	    		}
    		}
    		if(ticksExisted > 10 && userEntity.isSneaking())//出現から時間が経っていて、使用者がスニークモードなら
    		{
    			if(!isSpellCard)
    			{
    				THKaguyaLib.itemEffectFinish(this, userEntity, THKaguyaItems.sakuya_watch);//終了処理に移る
    			}
    			else
    			{
    				if(!worldObj.isRemote)
    				{
    					setDead();
    				}
    			}
    		}
    		if(userEntity.hurtTime > 0)//使用者ががダメージを受けたら終了
    		{
    			if(!isSpellCard)
    			{
    				THKaguyaLib.itemEffectFinish(this, userEntity, THKaguyaItems.sakuya_watch);//終了処理に移る
    			}
    			else
    			{
    				if(!worldObj.isRemote)
    				{
    					setDead();
    				}
    			}
    		}
    	}

    	//世界の時間を止める
    	/*if(mode == 0)
    	{
    		worldObj.worldInfo.setWorldTime(worldObj.worldInfo.getWorldTime() - 1L);
    	}
    	else if(count % 2 == 0)//世界の時間を遅くする
    	{
    		worldObj.worldInfo.setWorldTime(worldObj.worldInfo.getWorldTime() - 1L);
    	}*/


    	//懐中時計を使用者に追尾させる
    	THKaguyaLib.itemEffectFollowUser(this, userEntity, 1.2D, -30F);

    	//rotationYaw = ((float)ticksExisted * 10.0F) % 360F;
    	
    	if(mode == 3 && ticksExisted == 45)
    	{
    		mode = this.TIME_STOP;
    	}
    	if(mode == 4 && ticksExisted == 45)
    	{
    		mode = this.TIME_HALF;
    	}

    	int playerCount = 0;//停止空間内のプレイヤーの数を数える　誰もいなければ消滅させる
    	
    	//***********停止空間（時計じゃなくてもっと大きな見えない壁）とEntityの当たり判定を取る****************//
    	MovingObjectPosition movingObjectPosition = new MovingObjectPosition(this);
    	Entity entity = null;
    	boolean dubbleWatch = false;
    	boolean pass = false;
    	double effectiveBoundary = 40.0D;
    	List<?> list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(effectiveBoundary, effectiveBoundary, effectiveBoundary));

        if (list != null && list.size() > 0)
        {
            for (int j1 = 0; j1 < list.size(); j1++)
            {
                entity = (Entity)list.get(j1);
            	if (entity != null )
        		{
            		movingObjectPosition = new MovingObjectPosition(entity);
        		}
            	if(entity instanceof EntityPlayer)
            	{
            		playerCount++;//
            	}
            	
        		if (!worldObj.isRemote && movingObjectPosition != null && movingObjectPosition.entityHit != userEntity && movingObjectPosition.entityHit.riddenByEntity != userEntity
        				&& !(movingObjectPosition.entityHit instanceof EntityItemFrame)
        				&& !(movingObjectPosition.entityHit instanceof EntityPainting))
        		{
        			pass = false;
        			//時計が同時に２つ出現しているときの処理。全ての時計を無効化しアイテムに戻す。
        			if(movingObjectPosition.entityHit instanceof EntitySakuyaWatch)
        			{
        				EntitySakuyaWatch watch = (EntitySakuyaWatch)movingObjectPosition.entityHit;
        				//アイテム化させる
        	    		THKaguyaLib.itemEffectFinish(watch, watch.userEntity, THKaguyaItems.sakuya_watch);
        	    		dubbleWatch = true;
        			}
        			if(movingObjectPosition.entityHit instanceof EntitySpellCard)
        			{
        				EntitySpellCard spellCard = (EntitySpellCard)movingObjectPosition.entityHit;
        				if(spellCard.canMoveInTimeStop && spellCard.user.equals(this.userEntity))
        				{
        					pass = true;
        					spellCard.specialProcessInTimeStop();
        				}
        			}
        			if(!pass)
        			{
        				inPrivateSquare(movingObjectPosition);//時間操作空間の処理
        			}
        		}
            }
        }

    	if(playerCount == 0)//範囲内にプレイヤーが一人もいなかったなら
    	{
    		if(!worldObj.isRemote)
    		{
    			THKaguyaLib.itemEffectFinish(this, userEntity, THKaguyaItems.sakuya_watch);//終了処理に移る
    		}
    	}
    	
    	if(dubbleWatch)
    	{
    		//アイテム化させる
    		THKaguyaLib.itemEffectFinish(this, userEntity, THKaguyaItems.sakuya_watch);
    	}

        //************************************************************************************//

		count ++;

		if(mode > 2)
		{
			if(count % 20 == 19)

			{
	    		worldObj.playSoundAtEntity(this, "random.orb", 0.5F, 6.0F);
	    		worldObj.playSoundAtEntity(this, "random.click", 0.5F, 4.0F);
			}
		}
		else if(count % 20 == 19)
    	{
    		worldObj.playSoundAtEntity(this, "random.orb", 0.5F, 6.0F);
    		worldObj.playSoundAtEntity(this, "random.click", 0.5F, 4.0F);
    	}
}

    /**
	 * 保存するデータの書き込み
	 * @param nbtTagCompound : NTBタグ
	 */
    @Override
    protected void writeEntityToNBT(NBTTagCompound nbtTagCompound)
    {
    	nbtTagCompound.setShort("count", (short)count);
    	nbtTagCompound.setBoolean("isSpellCard", isSpellCard);
    }

    /**
	 * 保存したデータの読み込み
	 * @param nbtTagCompound : NBTタグ
	 */
    @Override
    protected void readEntityFromNBT(NBTTagCompound nbtTagCompound)
    {
    	count = nbtTagCompound.getShort("count");
    	isSpellCard = nbtTagCompound.getBoolean("isSpellCard");
    }

    @Override
    public float getShadowSize()
    {
        return 0.5F;
    }


	//時間操作空間に入っていた時の処理
	protected void inPrivateSquare(MovingObjectPosition movingObjectPosition)
    {
    	Entity hitEntity = (Entity)movingObjectPosition.entityHit;
    	//SoundManager soundManager = new SoundManager();
    	//Entityが誕生してから２フレーム経過したもののみ判定
    	if(hitEntity != null && hitEntity.ticksExisted >= 2)
    	{
    		//====時間停止空間処理====//

    		//Entityを１フレーム前の状態に、できる限りもっていく処理
	    	hitEntity.setPosition( hitEntity.prevPosX, hitEntity.prevPosY, hitEntity.prevPosZ);
    			
	    	hitEntity.rotationYaw = hitEntity.prevRotationYaw;
	    	hitEntity.rotationPitch = hitEntity.prevRotationPitch;
	    	hitEntity.motionX = 0.0D;//*= -1.0D;
	    	
	    	// 空間内のEntityが空中にいる場合
	    	if( !hitEntity.onGround )
	    	{
	    		if(worldObj.isRemote)
	    		{
	    			hitEntity.motionY = -0.0D;
	    		}
	    		else
	    		{
	    			hitEntity.motionY = -0.0D;//*= -1.0D;
	    		}
	    	}
	    	
	    	hitEntity.motionZ = 0.0D;//*= -1.0D;

	    	hitEntity.setAir(0);
	    		
	    	hitEntity.ticksExisted--;//誕生してからのカウントを増やさない

	    	hitEntity.fallDistance -= 0.076865F;//落下距離を変動しないように　この値はフレームに比例するが、どこに書いてあるかわからん（一応かなり近い値ではある）
    		
    			if(hitEntity instanceof EntityLivingBase)//範囲内のEntityがEntityLivingBaseに属しているなら
    			{
    				EntityLivingBase living = (EntityLivingBase)hitEntity;
    				living.rotationYawHead = living.prevRotationYawHead;
    				//living.setAir(0);
    				
    			//EntityLivingBase.setAttackTarget(null);
    				//EntityLivingBase.rotationPitchHead = EntityLivingBase.prevRotationPitchHead;
    				/*if(EntityLivingBase.hurtTime > 0)
    				{
    					EntityLivingBase.hurtTime++;
    				}*/
    				/*if(EntityLivingBase.deathTime > 1)
    				{
    					EntityLivingBase.deathTime--;
    				}*/
    				living.attackTime++;
    				//EntityLivingBase.livingSoundTime--;
    				//EntityLivingBase.setLastAttackingEntity(null);

    				if(living instanceof EntityCreeper)//EntityCreeperに属しているなら
    				{
    					EntityCreeper entityCreeper = (EntityCreeper)living;
    					//if(count % 2 == 0)
    					{
    						entityCreeper.setCreeperState(-1);//爆発のカウントを戻す
    					}
    					//entitycreeper.timeSinceIgnited --;
    				}
    				/*else if(EntityLivingBase instanceof EntitySkeleton)
    				{
    					EntitySkeleton entityskeleton = (EntitySkeleton)EntityLivingBase;
    					//entityskeleton.resetTask();
    					entitySkelton.func_48090_aM()
    				}*/
    				else if(living instanceof EntityGhast)//EntityGhastに属しているなら
    				{
    					EntityGhast entityGhast = (EntityGhast)living;
    					entityGhast.attackCounter--;//攻撃のカウントを戻す
    				}

    				if(living instanceof EntityTameable)//テイム可能なら
    				{
    					//EntityTameable entitytameable = (EntityTameable)EntityLivingBase;
    					//if(entitytameable.isTamed())
    					//{
    						living.motionY-=0.000001D;//座っている動物は重力がなくなると立つみたい　わからない程度に重力をかける処理
    						//entitytameable.motionY-=0.000001D;
    						//entitytameable.func_48140_f(true);
    						//entitytameable.aiSit.func_48407_a(entitytameable.isSitting());
    						//entitytameable.setPathToEntity(null);
    					//}
    					//entitytameable.aiSit.func_48407_a(false);
    				}
    				
    				//プレイヤーの動きを止める
    				if(living instanceof EntityPlayerMP)
    				{
    					EntityPlayerMP player = (EntityPlayerMP)living;
    					player.playerNetServerHandler.setPlayerLocation(player.prevPosX, player.prevPosY, player.prevPosZ, player.rotationYaw, player.rotationPitch);
    					
    					
    				}
    			}
    			//soundManager.stopEntitySound(movingobjectposition.entityHit);
    			
	    		//加速だけは全Entityが処理していないため無理そう　　だから重力の影響を受けるものは扱いにくい
    		}
    		//====時間減速空間の場合====//
    		else if(mode == this.TIME_HALF || mode == this.TIME_HALF_WITH_LIMIT)//時間を1/2の進みにする
    		{
    			slowDownSpeed(hitEntity, 1, 2);
    		}
    		else if(mode == 3)
    		{
    			slowDownSpeed(hitEntity, 5 - (ticksExisted / 9), 5);
    		}
    		else if(mode == 4)
    		{
    			slowDownSpeed(hitEntity, 10 - (ticksExisted / 9), 10);
    		}
        }
    
	
	/*時間を操る
	 * hitEntity : 操る対象
	 * timeRate  : 進む時間の倍率。減速しか扱えず、整数分の１の速度にしかできない。ex 1/2, 1/3, 1/4, 1/5...
	 */
	private void slowDownSpeed(Entity hitEntity, int timeRateNum,int timeRateDen)
	{
		float timeRate = (float)timeRateNum / (float)timeRateDen;
		
		
		if(timeRate < 1.0)
		{
			hitEntity.motionX -= (hitEntity.posX-(hitEntity.prevPosX)) * timeRate;
			hitEntity.motionY -= (hitEntity.posY-(hitEntity.prevPosY)) * timeRate;
			hitEntity.motionZ -= (hitEntity.posZ-(hitEntity.prevPosZ)) * timeRate;
			hitEntity.fallDistance -= 0.076865F * timeRate;//落下距離を半減（空中待機時間で変動してしまう）
			if( count % timeRateDen < timeRateNum)//偶数フレームのみ処理
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
	}

}
