package thKaguyaMod.entity.shot;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.LaserData;
import thKaguyaMod.ShotData;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.EntityShotMaterial;
import thKaguyaMod.entity.item.EntitySakuyaWatch;
import thKaguyaMod.entity.living.EntityTHFairy;

/** レーザー系の共通処理 */
public class EntityTHLaser extends EntityTHShot
{
	private EntityTHLaser mother;
	
	//ワールド読み込み時に呼び出されるコンストラクト
    public EntityTHLaser(World world)
    {
        super(world);
        ignoreFrustumCheck = true;//レーザーの中心が画面から外れても描画される
    }
    
	public EntityTHLaser(World world, EntityLivingBase user, Entity source,
			Vec3 pos, Vec3 angle, float slope,
	    	Vec3 rotate, float rotationSpeed, int rotationEnd,
	    	double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity,
	    	int color, float width, float length, float damage, int delay, int end, int special)
	    {
	        super(world, user, source, pos, angle, slope, rotate, rotationSpeed, rotationEnd, 
	        		firstSpeed, limitSpeed, acceleration, gravity, color, width, damage, delay, end, special);
	    	setLaserLength(0.0D);
	    	setMaxLaserLength(length);
	    	mother = null;
	    }
	
	public EntityTHLaser(World world, EntityLivingBase user, Entity source,
		Vec3 pos, Vec3 angle, float slope,
		Vec3 rotate, float rotationSpeed, int rotationEnd,
    	double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity,
    	LaserData laser)
    {
        this(world, user, source, pos, angle, slope, rotate, rotationSpeed, rotationEnd,
        		firstSpeed, limitSpeed, acceleration, gravity,
        		laser.form * 8 + laser.color, laser.width, laser.length, laser.damage, laser.delay, laser.end, laser.special);
    }

	
	/** 生成時に一度だけ呼ばれる処理 */
	@Override
	protected void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(21, new Integer(0));
		dataWatcher.addObject(22, new Integer(0));
		dataWatcher.addObject(23, new Integer(0));
		dataWatcher.addObject(24, new Integer(0));
		dataWatcher.addObject(25, new Integer(0));
		dataWatcher.addObject(26, new Integer(0));
	}
	
	//角度の更新処理
	@Override
	public void updateAngle()
	{
    	float f = MathHelper.sqrt_double(angle.xCoord * angle.xCoord + angle.zCoord * angle.zCoord);
    	if(!worldObj.isRemote)
    	{
    		rotationYaw = (float)((Math.atan2(angle.xCoord, angle.zCoord) * 180D) / 3.1415927410125732D);
    		if(rotationYaw >= 180F)
    		{
    			rotationYaw = -180F + (rotationYaw - 180F);
    		}
    		else if(rotationYaw <= -180F)
    		{
    			rotationYaw = 180F + (rotationYaw + 180F);
    		}
    	}
        //for (rotationPitch = (float)((Math.atan2(motionY, f) * 180D) / 3.1415927410125732D); rotationPitch - prevRotationPitch < -180F; prevRotationPitch -= 360F) { }
        if(!worldObj.isRemote)
        {
        	rotationPitch = (float)((Math.atan2(angle.yCoord, f) * 180D) / 3.1415927410125732D);
        	//for (rotationPitch = (float)((Math.atan2(shotVectorY, f) * 180D) / 3.1415927410125732D); rotationPitch - prevRotationPitch < -180F; prevRotationPitch -= 360F) { }
        }
	}

	/** レーザーを消す。同時にレーザーが弾の素になる */
	@Override
	public void shotFinishBonus()
	{
		double zPos = 0.0D;
		while(zPos < getLaserLength())
		{
			EntityShotMaterial shotMaterial = new EntityShotMaterial(worldObj, posX + angle.xCoord * zPos, posY + angle.yCoord * zPos, posZ + angle.zCoord * zPos);
			if(!worldObj.isRemote)
			{
				worldObj.spawnEntityInWorld(shotMaterial);
			}
			zPos += 1.5D;
		}
		if(!worldObj.isRemote)
		{
			setDead();
		}
	}
	
	/** 毎tick呼ばれる処理 */
	@Override
	public void  onUpdate()
	{
		super.onUpdate();
		
		if(this instanceof EntityTHSetLaser == false)
		{
			double laserLength = getLaserLength() + getSpeed() * 2.0D;
			if(laserLength > getMaxLaserLength())
			{
				setLaserLength(getMaxLaserLength());
			}
			else
			{
				setLaserLength(laserLength);
			}
		}
		
		if(mother != null)
		{
			motionY += 0.4D;
			
			setMotherAndChild(1);
			setMotherPosX(mother.posX);
			setMotherPosY(mother.posY);
			setMotherPosZ(mother.posZ);
		}
		else
		{
			setMotherAndChild(0);
			//setMotherPosY(-9999999);
		}
	}
	
	/** 衝突時の処理 */
	@Override
	public void hitCheck()
	{
		double width = getShotSize();
		double length = getLaserLength() - getShotSize();
	    //始点（現在地）
    	Vec3 vec3d = Vec3.createVectorHelper(posX + angle.xCoord * width, posY + angle.yCoord * width, posZ + angle.zCoord * width);
    	//終点（現在地に移動量を足した点）
    	Vec3 vec3d1 = Vec3.createVectorHelper(posX + motionX + angle.xCoord * length, posY + motionY + angle.yCoord * length, posZ + motionZ + angle.zCoord * length);
        //始点と終点からブロックとの当たりを取得
    	//MovingObjectPosition movingObjectPosition = worldObj.rayTraceBlocks_do_do(vec3d, vec3d1, false, true);
    	MovingObjectPosition movingObjectPosition = worldObj.func_147447_a(vec3d, vec3d1, false, true, true);
    	vec3d = Vec3.createVectorHelper(posX + angle.xCoord * width, posY + angle.yCoord * width, posZ + angle.zCoord * width);
    	vec3d1 = Vec3.createVectorHelper(posX + motionX + angle.xCoord * length, posY + motionY + angle.yCoord * length, posZ + motionZ + angle.zCoord * length);
    	//何らかのブロックに当たっているなら
        if (movingObjectPosition != null)
        {
        	//終点を当たった点に変更
        	vec3d1 = Vec3.createVectorHelper(movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
        	/*if(movingObjectPosition.entityHit == null)
        	{
        		shotDamage -= 2;
        		if(shotDamage <= 0)
        		{
        			if(!worldObj.isRemote)
        			{
        				setDead();
        			}
        		}
        	}*/
        	onImpact(movingObjectPosition);
        }
    	
    	
        Entity entity = null;//実際に当たったことにするEntity
    	double d = 0.0D;//そのEntityまでの仮の距離
    	//ここから移動量分の線分を作り、それに弾の大きさの２倍の肉付けをし直方体を作る。それに当たったEntityをリスト化する
		double xLength = motionX + Math.sin(rotationYaw / 180F * 3.141593F) * Math.cos(rotationPitch / 180F * 3.141593F) * getLaserLength();
		double yLength = motionY + Math.sin(rotationPitch / 180F * 3.141593F) * getLaserLength();
		double zLength = motionZ + Math.cos(rotationYaw / 180F * 3.141593F) * Math.cos(rotationPitch / 180F * 3.141593F) * getLaserLength();
        List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(xLength, yLength, zLength).expand(width, width, width));//指定範囲内のEntityをリストに登録

    	for (int j = 0; j < list.size(); j++)
        {
            Entity entity1 = (Entity)list.get(j);//entity1にリストの先端のentityを保存
        	//entity1が、当たり判定を取らない　または　entity1が使用者　または　飛んで25カウント以下？　または　EntityTHShotならパス
            if ( entity1.canBeCollidedWith() && 
            	!entity1.isEntityEqual(user) && 
            	!entity1.isEntityEqual(source) && 
            	!hitCheckEx(entity1) && 
            	/*entity1 instanceof EntityTHShot == false &&*/  
            	entity1 instanceof EntitySakuyaWatch == false &&
            	entity1 instanceof EntityAnimal == false &&
            	entity1 instanceof EntityVillager == false &&
            	entity1 instanceof EntityLivingBase &&
            	!(user instanceof EntityTHFairy && entity1 instanceof EntityTHFairy))
            {
        		//float f2 = 0.3F;
        		//判定を弾の大きさに変更
            	AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(width, width, width);
            	MovingObjectPosition movingObjectPosition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);
        		//この判定で何も当たっていないならパス
            	if (movingObjectPosition1 != null)
            	{
            		movingObjectPosition = new MovingObjectPosition(entity1);
            		boolean creative = false;
            		if (movingObjectPosition != null && movingObjectPosition.entityHit != null && movingObjectPosition.entityHit instanceof EntityPlayer)
        			{
        				EntityPlayer entityPlayer = (EntityPlayer)movingObjectPosition.entityHit;
						/*if(entityPlayer.capabilities.isCreativeMode)
        				{
        					creative = true;
        				}*/
            			//if (entityPlayer.capabilities.disableDamage || shootingEntity instanceof EntityPlayer && !((EntityPlayer)shootingEntity).func_96122_a(entityPlayer))
            			if (entityPlayer.capabilities.disableDamage || source instanceof EntityPlayer /*&& !((EntityPlayer)shootingEntity).func_96122_a(entityPlayer)*/)
            				//if ( entityPlayer.capabilities.disableDamage && !((EntityPlayer)shootingEntity).func_96122_a(entityPlayer))
            			{
            				movingObjectPosition = null;
            			}
        			}
            		if(movingObjectPosition != null)
            		{
            			onImpact(movingObjectPosition);
            		}
            	}
        	
            }
        }

    	//当たったEntityがいるなら、当たったEntityをMovingObjectPositionで登録
        /*if (entity != null)
        {
            movingObjectPosition = new MovingObjectPosition(entity);
        }
    	//MovingObjectPositionで当たっているなら
        if (movingObjectPosition != null)
        {
        	//当たった場合の処理をする
            onImpact(movingObjectPosition);
        }*/
    	/*if(worldObj.isRemote)
    	{
    		setSize((float)getLaserLength(), getShotSize());
    	}*/
	}

	/** ブロックやEntityに当たった時の処理
	 * @param movingObjectPosition : 移動しているものに関する情報
	 * @return trueを返す（trueしか返さない不具合) 
	 */
	@Override
    protected boolean onImpact(MovingObjectPosition movingObjectPosition)
    {
    	if(movingObjectPosition.entityHit != null)
    	{
	    	Entity hitEntity = movingObjectPosition.entityHit;
        
	    	//当たったEntityがいるなら
	    	if ( hitEntity != null )
	        {
	        	//それがEntityTHShotに属していないなら
	        	if(hitEntity instanceof EntityTHShot == false)
	        	{
	        		//Entityに当たった時の特殊な処理
	        		entityHitSpecialProcess(hitEntity);
	        		//指定したダメージ分の魔法ダメージを与える
	        		if(!worldObj.isRemote)
	        		{
	        			if (!hitEntity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, user), this.getShotDamageWithDifficulty()));
	        		}
	        		/*hitEntity.motionX = 0.0D;
	        		hitEntity.motionY = 0.0D;
	        		hitEntity.motionZ = 0.0D;*/
					if(hitEntity.hurtResistantTime == 0)
	        		{
	        			shotDamage--;
	        		}
	        		if(shotDamage <= 0)
	        		{
	        			shotDamage = 1;
	        		}
	        		
	        		/*double distance = this.getDistance(hitEntity.posX, hitEntity.posY, hitEntity.posZ);
	        		EntityTHLaser laser = thShotLib.createLaserA(user, this, user.posX + shotVectorX * distance, user.posY + shotVectorY * distance, user.posZ + shotVectorZ * distance, this.shotVectorX, this.shotVectorY, this.shotVectorZ, 
	        						this.shotSpeed, this.shotLimitSpeed, this.shotAcceleration, 0.0D, 0.0D, 0.0D, 200F, this.shotType, this.shotSize, this.lastTime - this.ticksExisted, 0, this.shotType, this.getMaxLaserLength() - distance);
	        		laser.setLaserLength(this.getLaserLength() - distance);
	        		setLaserLength(distance);
	        		setMaxLaserLength(distance);*/
	        		
	        		
	        		//shotDamage -= 2;ww
	        		/*double hitLength = this.getDistanceSqToEntity(hitEntity);
	        		//if(hitLength > 0.3D && hitLength < getLaserLength() - 0.3D)
	        		if(hitEntity instanceof EntityLivingBase)
	        		{
	        			EntityLivingBase hitLivingEntity = (EntityLivingBase)hitEntity;
		        		if(hitLivingEntity.hurtTime >= hitLivingEntity.maxHurtTime - 3 || !hitLivingEntity.isDead)
		        		{
		        			if(!worldObj.isRemote)
		        			{
		        				if(this.getLaserLength() > hitLength + 0.4D)
		        				{
		        					thShotLib.createLaserABaseA(user, this, posX + xVec * (hitLength + 0.1D), posY + yVec * (hitLength + 0.1D), posZ + zVec * (hitLength + 0.1D),
		        							xVec, yVec, zVec, shotSpeed, shotMaxSpeed, shotAddSpeed, shotGravityX, shotGravityY, shotGravityZ, shotDamage, color, width, deadTime - ticksExisted, shotType, getLaserLength() - hitLength - 0.1D);
		        				}
		        			}
		        			if(!worldObj.isRemote)
		        			{
		        				if(this.getLaserLength() > hitLength - 0.1D)
		        				{
		        					this.setLaserLength(hitLength - 0.1D);
		        				}
		        				this.setMaxLaserLength(hitLength - 0.1D);
		        			}
		        			
		        		}
	        		}*/
	        	}
	        	//EntityTHShotに属しているなら
	        	else
	        	{
        			EntityTHShot entityTHShot = (EntityTHShot)hitEntity;

        			if(user != entityTHShot.user)//使用者の違う弾同士は打ち消し合う
        			{
        				//弾同士の相殺
        				//お互い弾のダメージ分だけ小さくする
        				float shotDamageA = this.shotDamage;
        				this.shotDamage -= entityTHShot.shotDamage;
        				entityTHShot.shotDamage -= shotDamageA;
        				if(this.shotDamage < 0.0F)
        				{
        					shotDamage = 0.0F;
        				}
        				if(entityTHShot.shotDamage < 0.0F)
        				{
        					entityTHShot.shotDamage = 0.0F;
        				}
        			}
	        	}
			}
    	}
    	else
    	{
    		blockHitSpecialProcess(movingObjectPosition);
    		double hitDistance = this.getDistance(movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
    		if(hitDistance > 0.3D)
    		{
    			if(!worldObj.isRemote)
    			{
    				setLaserLength(hitDistance);
    				setMaxLaserLength(hitDistance);
    			}
    		}
    		else
    		{
    			if(!worldObj.isRemote)
    			{
    				setDead();
    			}
    		}
    		//shotDamage -= 20;
    		/*if(!worldObj.isRemote)
    		{
    			setDead();
    			return;
    		}*/
    	}
    	
    	if(shotDamage <= 0)
    	{
    		if(!worldObj.isRemote)
    		{
        		this.setDead();//ブロックに当たったら消滅
    		}
    	}
    	
    	return true;
    }
	
	//独自の動きを追加するためのもの
	@Override
	public void specialMotion()
	{
		super.specialMotion();
		
		switch(shotSpecial)
		{
			case THShotLib.GUNGNIR:
				if(ticksExisted <= 10)
				{
					Vec3 move = THShotLib.getVectorFromRotation(rotate, angle, 18F);
					if(this.getLaserLength() < this.getMaxLaserLength())
					{
						this.setLaserLength(this.getLaserLength() + 1.0D);
						
					}
					else
					{
						this.setLaserLength(this.getMaxLaserLength());
					}
					angle = move;
					updateAngle();
				}
				if(ticksExisted == 10)
				{
					user.motionX -= angle.xCoord;
					user.motionZ -= angle.zCoord;
					this.shotLimitSpeed = 2.0D;
					this.shotAcceleration = 0.6D;
				}
				if(ticksExisted > 10)
				{
					ShotData shot = ShotData.shot(THShotLib.FORM_CRYSTAL, THShotLib.RED, 0.45F, 6.0F, 0, 40);
					THShotLib.createRingShot(this.user, this, THShotLib.pos_Entity(this), angle, 0F, 9999, 1.4D, 0.1D, -0.1D, THShotLib.gravity_Zero(), shot, 4, 0.3D, 240F, ticksExisted * 10F);
				}
				break;
			default:
				break;
			
		}
	}
    	

    //Entityからの攻撃を受けたときの処理　要は跳ね返す処理
	@Override
    public boolean attackEntityFrom(DamageSource damagesource, float i)
    {
		return false;
    }
	
	
	/**
	 * レーザーの長さを設定
	 * @param length レーザーの長さ
	 */
	public void setLaserLength(double length)
	{
		dataWatcher.updateObject(21, Integer.valueOf((int)(length * 10000.0D)));
	}
	
	/**
	 * レーザーの長さを取得する
	 * @return レーザーの長さを返す
	 */
	public double getLaserLength()
	{
		return (double)dataWatcher.getWatchableObjectInt(21) / 10000.0D;
	}
	
	/**
	 * レーザーの最大の長さを設定
	 * @param maxLength レーザーの最大の長さ
	 */
	public void setMaxLaserLength(double maxLength)
	{
		dataWatcher.updateObject(22, Integer.valueOf((int)(maxLength * 10000.0D)));
	}
	
	/**
	 * レーザーの最大の長さを取得
	 * @return レーザーの最大の長さを返す
	 */
	public double getMaxLaserLength()
	{
		return (double)dataWatcher.getWatchableObjectInt(22) / 10000.0D;
	}
	
	/**
	 * 未実装メソッド
	 * 親のレーザーのX座標を設定する
	 * @param x
	 */
	@Deprecated
	public void setMotherPosX(double x)
	{
		dataWatcher.updateObject(23, Integer.valueOf((int)(x * 10000.0D)));
	}
	
	/**
	 * 未実装メソッド
	 * 親のレーザーのX座標を返す
	 * @return
	 */
	@Deprecated
	public double getMotherPosX()
	{
		return dataWatcher.getWatchableObjectInt(23) / 10000.0D;
	}
	
	/**
	 * 未実装メソッド
	 * 親のレーザーのY座標を設定する
	 * @param y
	 */
	@Deprecated
	public void setMotherPosY(double y)
	{
		dataWatcher.updateObject(24, Integer.valueOf((int)(y * 10000.0D)));
	}
	
	/**
	 * 未実装メソッド
	 * 親のレーザーのY座標を返す
	 * @return
	 */
	@Deprecated
	public double getMotherPosY()
	{
		return dataWatcher.getWatchableObjectInt(24) / 10000.0D;
	}
	
	/**
	 * 未実装メソッド
	 * 親のレーザーのZ座標を設定する
	 * @param z
	 */
	@Deprecated
	public void setMotherPosZ(double z)
	{
		dataWatcher.updateObject(25, Integer.valueOf((int)(z * 10000.0D)));
	}
	
	/**
	 * 未実装メソッド
	 * 親のレーザーのZ座標を返す
	 * @return
	 */
	@Deprecated
	public double getMotherPosZ()
	{
		return dataWatcher.getWatchableObjectInt(25) / 10000.0D;
	}
	
	/**
	 * 未実装メソッド
	 * 親子がいるかを設定する
	 * @param b
	 */
	@Deprecated
	public void setMotherAndChild(int b)
	{
		dataWatcher.updateObject(26, Integer.valueOf(b));
	}
	
	/**
	 * 未実装メソッド
	 * 親子がいるかを返す
	 * @return
	 */
	@Deprecated
	public int isMotherAndChild()
	{
		return dataWatcher.getWatchableObjectInt(26);
	}
}
