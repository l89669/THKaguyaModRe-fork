package thKaguyaMod.entity.shot;

import static thKaguyaMod.THShotLib.*;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.LaserData;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.item.EntitySakuyaWatch;
import thKaguyaMod.entity.living.EntityTHFairy;

/** 固定レーザー系の共通処理 */
public class EntityTHSetLaser extends EntityTHLaser
{
	//public Entity settingEntity;//レーザーを固定させるEntity。nullならその場から動かない
	//private float settingAngle;//設置した場所からの角度
	private double settingLength;//設置した場所からの距離
	private double settingYOffset;//設置する高さ
	
	//float rotationSpeed;//レーザーの回転速度
	
	private Vec3 setPos;//設置地点
	
	public Entity setting;
	
	
	/** ワールド読み込み時に呼び出されるコンストラクト */
    public EntityTHSetLaser(World world)
    {
        super(world);

    	if(worldObj.isRemote)
    	{
    		setSize((float)getLaserLength(), getShotSize());
    	}
    }
	
	public EntityTHSetLaser(World world, EntityLivingBase user, Entity source,
		Vec3 pos, Vec3 angle, float slope, Vec3 rotate, float rotationSpeed, int rotationEnd, 
    	int color, float width, float length, float damage, int delay, int end, int special,
    	Entity set, double setLength, double setYOffset)
    {
        super(world, user, source, pos, angle, slope, rotate, rotationSpeed, rotationEnd,
        		0.0D, 0.0D, 0.0D, gravity_Zero(), color, width, length, damage, delay, end, special);
        setLaserLength(length);
        setMaxLaserLength(length);
        //settingEntity = setEntity;
        //settingAngle = setAngle / 180F * 3.141593F;
        settingLength = setLength;
        settingYOffset = setYOffset;
        
		/*posX = shootingEntity.posX - xVector + this.overVectorX * settingYOffset;
		posY = shootingEntity.posY - yVector + this.overVectorY * settingYOffset;
		posZ = shootingEntity.posZ + zVector + this.overVectorZ * settingYOffset;*/

        //rotationSpeed = rotaSpeed;
        //rotationYaw = (float)Math.atan2(xVector, zVector) / 3.141593F * 180F;//移動量から水平方向角度を算出
    	//rotationPitch = (float)Math.atan2( yVector, Math.sqrt(xVector * xVector + zVector * zVector)) / 3.141593F * 180F;//移動量から垂直方向角度を算出
		setting = set;
        if(set != null)
		{
        	setPos = pos;
	        posX = set.posX + angle.xCoord * settingLength;// + this.overVectorX * settingYOffset;
			posY = set.posY + angle.yCoord * settingLength + settingYOffset;
			posZ = set.posZ + angle.zCoord * settingLength;// + this.overVectorZ * settingYOffset;
			setPosition(posX, posY, posZ);
		}
        
        
    }
	
	public EntityTHSetLaser(World world, EntityLivingBase user, Entity source,
			Vec3 pos, Vec3 angle, float slope, Vec3 rotate, float rotationSpeed, int rotationEnd, 
	    	LaserData laser,
	    	Entity set, double setLength, double setYOffset)
	{
		this(world, user, source, pos, angle, slope, rotate, rotationSpeed, rotationEnd,
		laser.color, laser.width, laser.length, laser.damage, laser.delay, laser.end, laser.special, set, setLength, setYOffset);
	}

	/** 生成時に一度だけ呼ばれる処理 */
	@Override
	protected void entityInit()
	{
		super.entityInit();
		//dataWatcher.addObject(21, new Integer(0));
		//dataWatcher.addObject(22, new Integer(0));
	}
	
	/** 毎tick呼ばれる処理 */
	@Override
	public void  onUpdate()
	{
		super.onUpdate();
		
		/*if(shootingEntity == null || setting == null)
		{
			if(!worldObj.isRemote)
			{
				setDead();
				return;
			}
		}*/
		
		if(this.setting != null)
		{
			if(setting.isDead)
			{
				setDead();
				return;
			}
			posX = setting.posX + settingLength * angle.xCoord;// + this.overVectorX * settingYOffset;
			posY = setting.posY + settingLength * angle.yCoord + settingYOffset;
			posZ = setting.posZ + settingLength * angle.zCoord;// + this.overVectorZ * settingYOffset;
			setPosition(posX, posY, posZ);
			//長さが限界でないなら伸ばす
			if(getLaserLength() < getMaxLaserLength())
			{
				setLaserLength(getLaserLength() + 6.0D);
			}
			else
			{
				setLaserLength(getMaxLaserLength());
			}
		}
		else
		{
			motionX = angle.xCoord;
			motionY = angle.yCoord;
			motionZ = angle.zCoord;
			posX += motionX;
			posY += motionY;
			posZ += motionZ;
			setPosition(posX, posY, posZ);
			//thShotLib.createLaserA(this.user, this.shootingEntity, posX, posY, posZ, rotationYaw, rotationPitch, 0.0D, 1.0D, 0.1D, 0.0D, 0.0D, 0.0D, this.shotDamage, this.color, this.getShotSize(), this.lastTime - ticksExisted, 0, this.shotType, this.getLaserLength());
			this.lastTime = 1;
			//setPosition(posX, posY + 1.0D, posZ);
			if(!worldObj.isRemote)
			{
				setDead();
				return;
			}
			//posX = posX - Math.sin(settingAngle) * settingLength;
			//posZ = posZ + Math.cos(settingAngle) * settingLength;
		}
		

	}
	
	/** 衝突時の処理 */
	@Override
	public void hitCheck()
	{
		/*if(getAnimationCount() < 0)
		{
			return;
		}*/
		
		double width = getShotSize();
		double length = getLaserLength();
		Vec3 vec = THShotLib.getVecFromAngle(rotationYaw, rotationPitch, length - getShotSize());
		Vec3 vec2 =  THShotLib.getVecFromAngle(rotationYaw, rotationPitch, getShotSize());
	    //始点（現在地）
    	Vec3 vec3d = Vec3.createVectorHelper(posX - vec2.xCoord, posY - vec2.yCoord, posZ + vec2.zCoord);
    	//終点（現在地に移動量を足した点）
    	Vec3 vec3d1 = Vec3.createVectorHelper(posX - vec.xCoord, posY - vec.yCoord, posZ + vec.zCoord);
        //始点と終点からブロックとの当たりを取得
    	//MovingObjectPosition movingObjectPosition = worldObj.rayTraceBlocks_do_do(vec3d, vec3d1, false, true);
    	MovingObjectPosition movingObjectPosition = worldObj.func_147447_a(vec3d, vec3d1, false, true, true);
    	vec3d = Vec3.createVectorHelper(posX - vec2.xCoord, posY - vec2.yCoord, posZ + vec2.zCoord);
    	vec3d1 = Vec3.createVectorHelper(posX - vec.xCoord, posY - vec.yCoord, posZ + vec.zCoord);
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
        List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(xLength, yLength, zLength).expand(getLaserLength(), getLaserLength(), getLaserLength()));//指定範囲内のEntityをリストに登録

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
    	if(worldObj.isRemote)
    	{
    		//setSize((float)getLaserLength(), getShotSize());
    		setSize(getShotSize(), getShotSize());
    	}
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
    				//setMaxLaserLength(hitDistance);
    			}
    		}
    		/*else
    		{
    			if(!worldObj.isRemote)
    			{
    				setDead();
    			}
    		}*/
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
    	

    //Entityからの攻撃を受けたときの処理　要は跳ね返す処理
	@Override
    public boolean attackEntityFrom(DamageSource damagesource, float i)
    {
		return false;
    }
	
	
	//レーザーの長さを設定
	public void setLaserLength(double length)
	{
		dataWatcher.updateObject(21, Integer.valueOf((int)(length * 10000.0D)));
	}
	//レーザーの長さを返す
	public double getLaserLength()
	{
		return (double)dataWatcher.getWatchableObjectInt(21) / 10000.0D;
	}
	
	//レーザーの最大の長さを設定
	public void setMaxLaserLength(double length)
	{
		dataWatcher.updateObject(22, Integer.valueOf((int)(length * 10000.0D)));
	}
	//レーザーの最大の長さを返す
	public double getMaxLaserLength()
	{
		return (double)dataWatcher.getWatchableObjectInt(22) / 10000.0D;
	}
}
