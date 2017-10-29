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

/** 僅かな間、時間を停止させる空間 */
public class EntitySakuyaStopWatch extends Entity
{
	/** 使用者 */
	public EntityLivingBase user;
	private int count;
	private float watchRotation;
	
	/** ストップウォッチのコンストラクタ（引数Worldのみは必須） */
    public EntitySakuyaStopWatch( World world )
    {
    	super(world);
    	
        ignoreFrustumCheck = true;		// 中心が画面から外れても描画される
        preventEntitySpawning = true;
        setSize(0.2F, 0.2F);			// サイズを設定　平面上の横と奥行きサイズ、高さ
        yOffset = 0.0F;					// 高さを設定
    	this.shouldRenderInPass(-2);
    }

    /** ストップウォッチのコンストラクタ */
    public EntitySakuyaStopWatch( World world, EntityLivingBase userLiving )
    {
        this(world);
        
        user = userLiving;
        THKaguyaLib.itemEffectFollowUser(this, user, 1.2D, -30F);
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

    	// 40ticksを超えている場合
    	if( ticksExisted > 40 )
    	{
    		// 消滅させる
    		if( !worldObj.isRemote )
    		{
    			setDead();
    		}
    		return;
    	}
    	
    	// 使用者がいない場合
    	if( user == null )
    	{
    		// 消滅させる
    		if( !worldObj.isRemote )
    		{
    			setDead();
    		}
    		return;
    	}
    	// 使用者がいる場合
    	else
    	{
    		// 使用者がダメージを受けた場合
    		if( user.hurtTime > 0 )
    		{
    			// 消滅させる
    			if(!worldObj.isRemote)
    			{
    					setDead();
    			}
    			return;
    		}
    	}

    	// ストップウォッチを使用者に追尾させる
    	THKaguyaLib.itemEffectFollowUser(this, user, 1.2D, -30F);
    	
    	// ***********停止空間（時計じゃなくてもっと大きな見えない壁）とEntityの当たり判定を取る**************** //
    	int playerCount = 0;//停止空間内のプレイヤーの数を数える　誰もいなければ消滅させる
    	MovingObjectPosition movingObjectPosition = new MovingObjectPosition(this);
    	Entity entity = null;
    	boolean dubbleWatch = false;
    	boolean pass = false;
    	double effectiveBoundary = 40.0D;//停止空間の有効範囲
    	// 停止空間内のEntityを全て取得
    	List<?> list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(effectiveBoundary, effectiveBoundary, effectiveBoundary));

    	// 取得したリストにEntityがいる場合
        if (list != null && list.size() > 0)
        {
        	// 取得したEntityリストを全て見る
            for ( int j1 = 0; j1 < list.size(); j1++ )
            {
                entity = (Entity)list.get(j1);//entityに取得したリストのEntityを代入
                
                // Entityがいる場合
            	if (entity != null )
            	{
            		movingObjectPosition = new MovingObjectPosition(entity);//MovingObjectPositionにEntityを登録
        		}
            	
            	// Entityがプレイヤーの場合
            	if( entity instanceof EntityPlayer )
            	{
            		playerCount++;//範囲内のプレイヤー数カウントを増加
            	}
            	
            	//movingObjectPositionがあり、それが使用者でない場合
        		if (	movingObjectPosition != null && movingObjectPosition.entityHit != user && movingObjectPosition.entityHit.riddenByEntity != user
        			&& !(movingObjectPosition.entityHit instanceof EntityItemFrame)
        			&& !(movingObjectPosition.entityHit instanceof EntityPainting))
        		{
        			pass = false;
        			//時計が同時に２つ出現しているときの処理。全ての時計を無効化しアイテムに戻す。
        			if( movingObjectPosition.entityHit instanceof EntitySakuyaWatch )
        			{
        				EntitySakuyaWatch watch = (EntitySakuyaWatch)movingObjectPosition.entityHit;
        				//アイテム化させる
        	    		THKaguyaLib.itemEffectFinish(watch, watch.user, THKaguyaItems.sakuya_watch);
        	    		dubbleWatch = true;
        			}
        			if( movingObjectPosition.entityHit instanceof EntitySakuyaStopWatch )
        			{
        				EntitySakuyaStopWatch watch = (EntitySakuyaStopWatch)movingObjectPosition.entityHit;
        				//アイテム化させる
        	    		//THKaguyaLib.itemEffectFinish(watch, watch.user, THKaguyaItems.sakuya_watch);
        				if( !watch.worldObj.isRemote )
        				{
        					watch.setDead();
        				}
        	    		dubbleWatch = true;
        			}
        			if(movingObjectPosition.entityHit instanceof EntitySpellCard)
        			{
        				EntitySpellCard spellCard = (EntitySpellCard)movingObjectPosition.entityHit;
        				if(spellCard.canMoveInTimeStop && spellCard.user.equals(this.user))
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

        // 範囲内にプレイヤーが一人もいない場合
    	if( playerCount == 0 )
    	{
    		// 消滅させる
    		if( !worldObj.isRemote )
    		{
    			setDead();
    		}
    		return;
    	}
    	
    	// 時計が２つ以上出現している場合
    	if( dubbleWatch )
    	{
    		// 消滅させる
    		if( !worldObj.isRemote )
    		{
    			setDead();
    		}
    		return;
    	}

        // ************************************************************************************ //

		count ++;

		if(count % 20 == 19)//20フレーム周期で音を出す
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
    }

    /**
	 * 保存したデータの読み込み
	 * @param nbtTagCompound : NBTタグ
	 */
    @Override
    protected void readEntityFromNBT(NBTTagCompound nbtTagCompound)
    {
    	count = nbtTagCompound.getShort("count");
    }

    @Override
    public float getShadowSize()
    {
        return 0.5F;
    }


	// 時間操作空間に入っていた時の処理
	protected void inPrivateSquare( MovingObjectPosition movingObjectPosition )
    {
    	Entity hitEntity = (Entity)movingObjectPosition.entityHit;

    	//Entityが誕生してから２フレーム経過したもののみ判定
    	if( hitEntity.ticksExisted >= 2 )
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
    		
	    	// 範囲内のEntityが生物の場合
	    	if( hitEntity instanceof EntityLivingBase )
    		{
    			EntityLivingBase living = (EntityLivingBase)hitEntity;
    			living.rotationYawHead = living.prevRotationYawHead;

    			living.attackTime++;

    			// クリーパーに属している場合
    			if( living instanceof EntityCreeper )
    			{
    				EntityCreeper entityCreeper = (EntityCreeper)living;

    				entityCreeper.setCreeperState(-1);//爆発のカウントを戻す

    			}
    			// ガストに属している場合
    			else if( living instanceof EntityGhast )
    			{
    				EntityGhast entityGhast = (EntityGhast)living;
    			
    				entityGhast.attackCounter--;//攻撃のカウントを戻す
    			}

    			// テイム可能な場合
    			if( living instanceof EntityTameable )
    			{
    				living.motionY-=0.000001D;//座っている動物は重力がなくなると立つみたい　わからない程度に重力をかける処理
    			}
    				
    			// プレイヤーの場合
    			if( living instanceof EntityPlayerMP )
    			{
    				EntityPlayerMP player = (EntityPlayerMP)living;
    				player.playerNetServerHandler.setPlayerLocation(player.prevPosX, player.prevPosY, player.prevPosZ, player.rotationYaw, player.rotationPitch);
    			}
    		}
    	}
    }
	
}

