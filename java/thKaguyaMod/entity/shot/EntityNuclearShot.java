package thKaguyaMod.entity.shot;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.ShotData;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.item.EntitySakuyaWatch;
import thKaguyaMod.entity.living.EntityTHFairy;
import thKaguyaMod.entity.spellcard.THSC_YouryokuSpoiler;
import thKaguyaMod.init.THKaguyaItems;

/** お空の核弾 */
public class EntityNuclearShot extends EntityTHShot
{
	/** すでに放たれたかどうかのフラグ */
	public boolean shootingFlag;
	
	//ワールド読み込み時に呼び出されるコンストラクト
    public EntityNuclearShot(World world)
    {
        super(world);
    }
	
    /**
     * 核弾を生成する。
     * 引数はあるものすべて（ShotDataを使用しない）
     * @param world
     * @param user
     * @param entity
     * @param pos
     * @param angle
     * @param slope
     * @param rotate
     * @param rotationSpeed
     * @param rotationEnd
     * @param firstSpeed
     * @param limitSpeed
     * @param acceleration
     * @param gravity
     * @param color
     * @param size
     * @param damage
     * @param delay
     * @param end
     * @param special
     */
	public EntityNuclearShot(World world, EntityLivingBase user, Entity entity,
		Vec3 pos, Vec3 angle, float slope,
		Vec3 rotate, float rotationSpeed, int rotationEnd,
    	double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity,
    	int color, float size, float damage, int delay, int end, int special)
    {
        super(world, user, entity, pos, angle, slope, rotate, rotationSpeed, rotationEnd,  firstSpeed, limitSpeed, acceleration,
        		gravity, color, size, damage, delay, end, special);
        shootingFlag = false;
    }
	
	/**
	 * 核弾を生成する。
	 * 引数はあるものすべて（ShotDataを使用する）
	 * @param world
	 * @param user
	 * @param entity
	 * @param pos
	 * @param angle
	 * @param slope
	 * @param rotate
	 * @param rotationSpeed
	 * @param rotationEnd
	 * @param firstSpeed
	 * @param limitSpeed
	 * @param acceleration
	 * @param gravity
	 * @param shot
	 */
	public EntityNuclearShot(World world, EntityLivingBase user, Entity entity,
			Vec3 pos, Vec3 angle, float slope,
			Vec3 rotate, float rotationSpeed, int rotationEnd,
	    	double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity,
	    	ShotData shot)
	    {
	        this(world, user, entity, pos, angle, slope, rotate, rotationSpeed, rotationEnd,  firstSpeed, limitSpeed, acceleration,
	        		gravity, shot.color, shot.size, shot.damage, shot.delay, shot.end, shot.special);
	    }
	
	//ショットが存在する限り呼び出されるメソッド
	@Override
    public void onUpdate()
    {	
		if(user != null)
		{
			if(user instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer)user;
				if(player.isUsingItem() && !shootingFlag)
				{
					Vec3 vec3 = THShotLib.getVecFromAngle(-rotationYaw + 20F, -rotationPitch, 0.3D + getShotSize() / 2.0D);
					/*double xPos = user.posX - (double)(MathHelper.cos(user.rotationYaw / 180.0F * (float)Math.PI) * 0.32F) * getShotSize() * 2.0D;
					double yPos = user.posY - 0.1D;
					double zPos = user.posZ - (double)(MathHelper.sin(user.rotationYaw / 180.0F * (float)Math.PI) * 0.32F) * getShotSize() * 2.0D;*/
					prevPosX = posX;
		        	prevPosY = posY;
		        	prevPosZ = posZ;
		        	
					setPositionAndRotation(user.posX + vec3.xCoord, THShotLib.getPosYFromEye(user, -0.1D) + vec3.yCoord, user.posZ + vec3.zCoord, -user.rotationYaw, -user.rotationPitch);
					
					if(getShotSize() < 6.00F)
					{
						setShotSize(getShotSize() + 0.06F);
					}
					if(player.inventory.getCurrentItem() != null)
					{
						if(player.inventory.getCurrentItem().getItem() != THKaguyaItems.nuclear_control_rod)
						{
						
							if(!worldObj.isRemote)
							{
								worldObj.createExplosion(this, this.posX, this.posY, this.posZ, getShotSize(), true);
								setDead();
							}
							return;
						}
					}
					else
					{
						if(!worldObj.isRemote)
						{
							worldObj.createExplosion(this, this.posX, this.posY, this.posZ, getShotSize(), true);
							setDead();
						}
						return;
					}
					return;
				}
				else if(shootingFlag == false)
				{
					
					if(player.inventory.getCurrentItem() != null)
					{
						if(player.inventory.getCurrentItem().getItem() != THKaguyaItems.nuclear_control_rod)
						{
						
							if(!worldObj.isRemote)
							{
								worldObj.createExplosion(this, this.posX, this.posY, this.posZ, getShotSize(), true);
								setDead();
							}
							return;
						}
					}
					else
					{
						if(!worldObj.isRemote)
						{
							worldObj.createExplosion(this, this.posX, this.posY, this.posZ, getShotSize(), true);
							setDead();
						}
						return;
					}
					ticksExisted = 1;
					setVector();
					shotAcceleration = 0.2D;
					shootingFlag = true;
				}
			}

		}
		else
		{
			if(!worldObj.isRemote)
			{
				setDead();
				return;
			}
		}
		
		shotDamage = getShotSize() * 8.0F;

    	super.onUpdate();
    	

    }
	
	/**
	 * 使用者との当たり判定をとるかを返す
	 * @return 使用者と当たり判定をとるならtrue
	 */
	@Override
	public boolean userHitCheck(Entity entity)
	{
		if(this.shotSpecial == THSC_YouryokuSpoiler.SPECIAL_SPOILER02 || this.shotSpecial == THSC_YouryokuSpoiler.SPECIAL_SPOILER01)
		{
			return true;
			
		}
		return !entity.isEntityEqual(user);
	}
	
	@Override
	public MovingObjectPosition hitEntityCheck(MovingObjectPosition movingObjectPosition, Vec3 vec3d, Vec3 vec3d1)
	{
        Entity entity = null;//実際に当たったことにするEntity
    	double d = 0.0D;//そのEntityまでの仮の距離
		float hitSize = getShotSize() * 0.5F;
    	//ここから移動量分の線分を作り、それに弾の大きさの２倍の肉付けをし直方体を作る。それに当たったEntityをリスト化する\\
        List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(hitSize, hitSize, hitSize));//指定範囲内のEntityをリストに登録
		
    	for (int j = 0; j < list.size(); j++)
        {
            Entity entity1 = (Entity)list.get(j);//entity1にリストの先端のentityを保存
        	//entity1が、当たり判定を取らない　または　entity1が使用者　または　飛んで25カウント以下？　または　EntityTHShotならパス
            if ( entity1.canBeCollidedWith() && 
            	userHitCheck(entity1) && 
            	/*!entity1.isEntityEqual(shootingEntity) &&*/
            	!hitCheckEx(entity1) && 
            	entity1 instanceof EntitySakuyaWatch == false &&
            	entity1 instanceof EntityAnimal == false &&
            	entity1 instanceof EntityVillager == false &&
            	(entity1 instanceof EntityLivingBase || entity1 instanceof EntityDragonPart || entity1 instanceof EntityTHShot) &&
            	!(user instanceof EntityTHFairy && entity1 instanceof EntityTHFairy))
        	{
        		//判定を弾の大きさに変更
            	AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(hitSize, hitSize, hitSize);
            	MovingObjectPosition movingObjectPosition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);
            	
        		//この判定で当たっているなら
            	if (movingObjectPosition1 != null)
            	{
            		movingObjectPosition1 = new MovingObjectPosition(entity1);
            		onImpact(movingObjectPosition1);
        		}
        	}
        }
    	
    	

    	//当たったEntityがいるなら、当たったEntityをMovingObjectPositionで登録
        /*if (entity != null)
        {
            movingObjectPosition = new MovingObjectPosition(entity);
        }
		*/
    	//MovingObjectPositionで当たっているなら
        if (movingObjectPosition != null)
        {
        	//当たった場合の処理をする
            onImpact(movingObjectPosition);
        }
    	
    	return movingObjectPosition;
	}
	
	//衝突処理
	/*@Override
	public void hitCheck()
	{
	    //始点（現在地）
    	Vec3 vec3d = Vec3.createVectorHelper(posX, posY, posZ);
    	//終点（現在地に移動量を足した点）
    	Vec3 vec3d1 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
        //始点と終点からブロックとの当たりを取得
    	//MovingObjectPosition movingObjectPosition = worldObj.rayTraceBlocks_do_do(vec3d, vec3d1, false, true);
    	MovingObjectPosition movingObjectPosition = worldObj.func_147447_a(vec3d, vec3d1, false, true, true);
    	vec3d = Vec3.createVectorHelper(posX, posY, posZ);
    	vec3d1 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
    	//何らかのブロックに当たっているなら
        if (movingObjectPosition != null)
        {
        	//終点を当たった点に変更
        	vec3d1 = Vec3.createVectorHelper(movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
        }
    	
        hitEntityCheck(movingObjectPosition, vec3d, vec3d1);

	}*/

	//ブロックやEntityに当たった時の処理
	@Override
    protected boolean onImpact(MovingObjectPosition movingObjectPosition)
    {
    	//当たった時の処理
    	if (!worldObj.isRemote)
    	{
    		Entity hitEntity = movingObjectPosition.entityHit;
        
    		//当たったEntityがいるなら
    		if ( hitEntity != null )
        	{
        		//それがEntityTHShotに属していないなら
        		if(hitEntity instanceof EntityTHShot == false)
        		{
        			boolean isHitDelete = true;
        			//Entityに当たった時の特殊な処理
        			isHitDelete = entityHitSpecialProcess(hitEntity);
        			//指定したダメージ分の魔法ダメージを与える
        			//if(shotType != thShotLib.SPOILER01 && shotType != thShotLib.SPOILER02)
        			if(!hitEntity.isEntityEqual(user))
        			{
        				if (!hitEntity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, user), shotDamage));
        			}
        		}
        		//EntityTHShotに属しているなら
        		else
        		{
        			EntityTHShot entityTHShot = (EntityTHShot)hitEntity;
        			/*if(getShotStrength() == entityTHShot.getShotStrength())
        			{
        				//setDead();
        				//entityTHShot.setDead();
        			}
        			//相手の方が強いなら
        			else if(getShotStrength() < entityTHShot.getShotStrength())
        			{
        				//この弾を消滅させる
        				this.setDead();
        			}
        			//自分の方が強いなら
        			else
        			{
        				//相手の弾を消滅させる
        				entityTHShot.setDead();
        			}*/
        			if(user != entityTHShot.user)//使用者の違う弾同士は打ち消し合う
        			{
        				//弾同士の相殺
        				//お互い弾のダメージ分だけ小さくする
        				float shotDamageA = this.shotDamage;
        				this.shotDamage -= entityTHShot.shotDamage;
        				entityTHShot.shotDamage -= shotDamageA;
        				//setDead();
        				//entityTHShot.setDead();
        			}
        		}
			}
    		else
    		{
    			if(blockHitSpecialProcess(movingObjectPosition))
    			{
        			delete();//ブロックに当たったら消滅
        			return true;
    			}
    		}
    	}
    	return false;
    }
	
	//当たり判定の追加
	@Override
	public boolean hitCheckEx(Entity entity)
	{
		return false;
	}
    	
    //弾の強さ
    /*public int getShotStrength()
    {
    	return 10;
    }*/
	
}

