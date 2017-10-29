package thKaguyaMod.entity.shot;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.entity.item.EntitySakuyaWatch;
import thKaguyaMod.entity.living.EntityTHFairy;
import thKaguyaMod.init.THKaguyaItems;

/** へにょりレーザー系の共通処理 */
public class EntityTHHenyoriLaser extends EntityTHLaser
{
	
	/** ワールド読み込み時に呼び出されるコンストラクト */
    public EntityTHHenyoriLaser(World world)
    {
        super(world);
        setSize(15.0F, 1.0F);
    }
	
	/*public EntityTHHenyoriLaser(World world, EntityLivingBase entityUser, Entity entity,
		double xPos, double yPos, double zPos,
    	double xVector, double yVector, double zVector,
    	double firstSpeed, double maxSpeed, double addSpeed,
    	double xVectorG, double yVectorG, double zVectorG, float damage, int c, float size, int endTime, int special, double length)
    {
        super(world, entityUser, entity, xPos, yPos, zPos, xVector, yVector, zVector,
        		firstSpeed, maxSpeed, addSpeed, xVectorG, yVectorG, zVectorG, damage, c, size, endTime, 0, special, length);
    }*/

	
	/** 生成時に一度だけ呼ばれる処理 */
	@Override
	protected void entityInit()
	{
		super.entityInit();
		//dataWatcher.addObject(21, new Integer(0));
		//dataWatcher.addObject(22, new Integer(0));
	}
	
	/** レーザーを消す。同時にレーザーが弾の素になる */
	@Override
	public void shotFinishBonus()
	{
		double zPos = 0.0D;
		while(zPos < getLaserLength())
		{
			EntityItem entityItem = new EntityItem(worldObj, posX - angle.xCoord * zPos, posY - angle.yCoord * zPos, posZ - angle.zCoord * zPos, new ItemStack(THKaguyaItems.shot_material, 1));
			worldObj.spawnEntityInWorld(entityItem);
			entityItem.age = 5700;//アイテムがすぐ消滅するよう設定（１5秒で消える）
			zPos += 0.3D;
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
	
	/** 衝突時の処理 */
	@Override
	public void hitCheck()
	{
		double width = getShotSize();
		double length = getLaserLength();
		
		float angleSpanY = (float)Math.PI / 30F;
		float baseAngle = (float)getAnimationCount() * angleSpanY;
		float baseAngle2 = baseAngle % 90F;
		int flontCount = 0;
		double moveLength = 0.0D;
		
		double nowX, nowY, nowZ, nextX, nextY, nextZ;
		nowX = posX;
		nowY = posY;
		nowZ = posZ;
		
		for(int i = 0; i < 13; i++)
		{
			baseAngle += angleSpanY;
			flontCount++;
			moveLength = flontCount * getLaserLength() / 13.0D;
			nextX = nowX + angle.xCoord * moveLength - Math.sin(baseAngle) * 3.0D + motionX;
			nextY = nowY + angle.yCoord * moveLength + motionY;
			nextZ = nowZ + angle.zCoord * moveLength + Math.cos(baseAngle) * 3.0D + motionZ;
			
			
		    //始点（現在地）
	    	Vec3 vec3d = Vec3.createVectorHelper(nowX, nowY, nowZ);
	    	//終点（現在地に移動量を足した点）
	    	Vec3 vec3d1 = Vec3.createVectorHelper(nextX, nextY, nextZ);
	        //始点と終点からブロックとの当たりを取得
	    	//MovingObjectPosition movingObjectPosition = worldObj.rayTraceBlocks_do_do(vec3d, vec3d1, false, true);
	    	MovingObjectPosition movingObjectPosition = worldObj.rayTraceBlocks(vec3d, vec3d1, true);
	    	vec3d = Vec3.createVectorHelper(nowX, nowY, nowZ);
	    	vec3d1 = Vec3.createVectorHelper(nextX, nextY, nextZ);
	    	//何らかのブロックに当たっているなら
	        if (movingObjectPosition != null)
	        {
	        	//終点を当たった点に変更
	        	vec3d1 = Vec3.createVectorHelper(movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
	        	onImpact(movingObjectPosition);
	        }
	    	
	    	
	        Entity entity = null;//実際に当たったことにするEntity
	    	double d = 0.0D;//そのEntityまでの仮の距離
	    	//ここから移動量分の線分を作り、それに弾の大きさの２倍の肉付けをし直方体を作る。それに当たったEntityをリスト化する
			double xLength = motionX + Math.sin(rotationYaw / 180F * 3.141593F) * Math.cos(rotationPitch / 180F * 3.141593F) * moveLength;
			double yLength = motionY + Math.sin(rotationPitch / 180F * 3.141593F) * moveLength;
			double zLength = motionZ + Math.cos(rotationYaw / 180F * 3.141593F) * Math.cos(rotationPitch / 180F * 3.141593F) * moveLength;
			EntityTHHenyoriLaser dummyEntity = new EntityTHHenyoriLaser(worldObj);
			dummyEntity.posX = nowX;
			dummyEntity.posY = nowY;
			dummyEntity.posZ = nowZ;
			//dummyEntity.boundingBox = this.boundingBox;
	        List list = worldObj.getEntitiesWithinAABBExcludingEntity(dummyEntity, this.boundingBox.addCoord(xLength, yLength, zLength).expand(width, width, width));//指定範囲内のEntityをリストに登録

	    	for (int j = 0; j < list.size(); j++)
	        {
	            Entity entity1 = (Entity)list.get(j);//entity1にリストの先端のentityを保存
	        	//entity1が、当たり判定を取らない　または　entity1が使用者　または　飛んで25カウント以下？　または　EntityTHShotならパス
	            if ( entity1.canBeCollidedWith() && 
	            	/*!entity1.isEntityEqual(user) && 
	            	!entity1.isEntityEqual(shootingEntity) && */
	            	!hitCheckEx(entity1) && 
	            	entity1 instanceof EntityTHShot == false &&  
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
			nowX = nowX + angle.xCoord * moveLength - Math.sin(baseAngle) * 3.0D;
			nowY = nowY + angle.yCoord * moveLength;
			nowZ = nowZ + angle.zCoord * moveLength + Math.cos(baseAngle) * 3.0D;
		}
		
		

	}

	/** ブロックやEntityに当たった時の処理
	 * @param movingObjectPosition : 移動しているものに関する情報
	 * @return trueを返す（trueしか返さない不具合) 
	 */
	@Override
    protected boolean onImpact(MovingObjectPosition movingObjectPosition)
    {
    	if(/*!worldObj.isRemote && */movingObjectPosition.entityHit != null)
    	{
    	//当たった時の処理
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
	        			if (!hitEntity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, user), shotDamage));
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
	        		//EntityTHShot entityTHShot = (EntityTHShot)hitEntity;
	        		//弾の強さを比べる
	        		//同じなら
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
    		delete();
    	}
    	
    	return true;
    }
    	
}
