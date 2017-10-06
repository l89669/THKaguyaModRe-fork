package thKaguyaMod.entity.shot;

import static thKaguyaMod.DanmakuConstants.*;
import static thKaguyaMod.THShotLib.*;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
//import net.minecraft.entity.item.EntityFallingSand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.ShotData;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.EntityShotMaterial;
import thKaguyaMod.entity.item.EntityYuukaParasol;
import thKaguyaMod.entity.living.EntityDanmakuMob;
import thKaguyaMod.entity.living.EntityTHFairy;
import thKaguyaMod.entity.spellcard.THSC_YouryokuSpoiler;
import thKaguyaMod.init.THKaguyaConfig;
import thKaguyaMod.registry.SpecialShotRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/** ショット系のEntityの共通処理 */
public class EntityTHShot extends Entity
{
	
	//種類特性変数
	protected float shotSize;//弾のサイズ
	
	//個別特性変数
	/** 弾の持ち主 ダメージ判定や消滅判定に係る */
	public EntityLivingBase user;
	/** 弾の発射主 弾は発射主には当たらない。当てたくない相手の設定にでも*/
	public Entity source;
	/** sourceに変更 現在未使用の変数 */
	@Deprecated
	public Entity shootingEntity;
	/** 弾のスピード */
	public double shotSpeed;
	/** 弾の限界速度 */
	public double shotLimitSpeed;
	/** 弾の加速度 */
	public double shotAcceleration;
	/** 加速するか 現在未使用の変数 */
	@Deprecated
	public boolean hasAccelerations;
	/** 重力ベクトル */
	public Vec3 gravity = gravity(0.0D, 0.0D, 0.0D);
	/** 弾のダメージ */
	public float shotDamage;
	/** 弾の色 */
	protected int shotColor;
	/** 弾の形状 */
	protected int shotForm;
	/** ショットID */
	protected int shotId;
	/** 消滅時間 */
	private int shotEndTime;
	/** 取得はgetShotEndTime()、設定はsetShotEndTime()に変更 */
	//@Deprecated
	//public int end;
	/** 方向ベクトル */
	public Vec3 angle = angle(0.0D, 0.0D, 0.0D);
	/** 弾独自の動作を表す。0は何もしない */
	public int shotSpecial;
	/** 弾の消滅時間 */
	protected int lastTime;
	
	/** 時間が止められる前の速度 */
	public double lastSpeed;
	/** 時間が止められる前のYaw */
	public float lastRotationYaw;
	/** 時間が止められる前のPitch */
	public float lastRotationPitch;
	/** 時間が止められる前のX軸の移動量 */
	public double lastShotMotionX;
	/** 時間が止められる前のY軸の移動量 */
	public double lastShotMotionY;
	/** 時間が止められる前のZ軸の移動量 */
	public double lastShotMotionZ;
	
	//弓関連であった変数　いらないかもしれない
	@Deprecated
	protected int xTile;
	@Deprecated
    protected int yTile;
	@Deprecated
    protected int zTile;
	@Deprecated
    protected int inTile;
	@Deprecated
    protected boolean inGround;//地面に触れているかのチェック
	@Deprecated
	protected int ticksAlive;//
	@Deprecated
	protected int ticksInAir;//空中にいる時間
	
    /** 遅延時間 弾が実体化するまでの時間 */
	public int delayTime;
	
	/** 特殊な場合にかかるダメージ倍率 */
	public float damageRate;
	
	/** 回転軸ベクトル */
	public Vec3 rotate = rotate_Default();
	/** 回転速度 回転時の角度の移り変わり */
	public float rotationYawSpeed;
	/** 回転動作を終了させる時間 */
	public int rotationEnd;

	
	//private EntityTHShot child;
	
	
	//ワールド読み込み時に呼び出されるコンストラクト
    public EntityTHShot(World world)
    {
        super(world);
        setSize(0.1F, 0.1F);
    	setAnimationCount(0);
    	setAngleZ(0F);
    }
    
	/*
	*わからんので、翻訳機使用　描画判定のチェックか？
	*エンティティかどうかをチェックするには、遠くに過去を使用し、その平均エッジと比較することで、レンダリングする範囲内にある
	*長さ* 64 * renderDistanceWeightの引数：距離
	*/
	@SideOnly(Side.CLIENT)
	public boolean isInRangeToRenderDist(double par1)
    {
        double d1 = this.boundingBox.getAverageEdgeLength() * 4.0D;
        d1 *= 64.0D;
        return par1 < d1 * d1;
    }
	
	public EntityTHShot(World world, EntityLivingBase entityUser, Entity entity,
			Vec3 pos, Vec3 angle, float slope, 
	    	Vec3 rotate, float rotationSpeed, int rotationEnd,
	    	double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity,
	    	int form, float size, float damage, int delay,int end,  int special)
	    {
	        super(world);
	    	
	    	user = entityUser;//弾の持ち主
	    	source = entity;//弾を発射したもの
	    	
	    	//初期位置
	    	setPosition(pos.xCoord, pos.yCoord, pos.zCoord);
	    	
	    	//初期角度
	    	this.angle = angle;
	    	setAngleZ(slope);
	    	
	    	this.rotate = rotate;
	    	//setOverVector(rotate.xCoord, rotate.yCoord, rotate.zCoord);
	    	setRotationYawSpeed(rotationSpeed);
	    	this.rotationEnd = rotationEnd;
	    	
	    	//速さ関係の設定
			this.shotSpeed = firstSpeed;//弾の初期速度
	    	this.shotLimitSpeed = limitSpeed;//弾の最大速度、または最低速度
	    	this.shotAcceleration = acceleration;//弾の加速度
	    	
	    	//重力ベクトルの設定
	    	this.gravity = gravity;
	    	
	    	//弾の効果が形状を設定
	    	setShotId(form);//弾の色を設定
	    	//大きさを設定
	    	setShotSize(size);
	        setSize(size, size);
	    	shotDamage = damage;//当たったときのダメージ
	    	delayTime = delay;
	    	setShotEndTime(end);//弾の消滅時間を設定
			//this.end = end + delay;//消滅時間
	    	shotSpecial = special;//特殊動作や特殊効果のタイプ
	    	
	    	lastSpeed = shotSpeed;
	    	
	    	//移動量の設定
	    	motionX = angle.xCoord * shotSpeed;//初期移動量はベクトル*初期速度
	    	motionY = angle.yCoord * shotSpeed;
	    	motionZ = angle.zCoord * shotSpeed;
	    	lastShotMotionX = motionX;
	    	lastShotMotionY = motionY;
	    	lastShotMotionZ = motionZ;
	    	//角度の設定
	    	updateYawAndPitch();
	        setRotation(rotationYaw, rotationPitch);
	    	lastRotationYaw = rotationYaw;
	    	lastRotationPitch = rotationPitch;
	    	
	    	lastTime = 0;
	    	setAnimationCount(-delay);
	    	
	    	updateAngle();
	    }
	
	public EntityTHShot(World world, EntityLivingBase entityUser, Entity entity,
			Vec3 pos, Vec3 angle, float slope, 
	    	Vec3 rotate, float rotationSpeed, int rotationEnd,
	    	double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity,
	    	ShotData shot)
	    {
			this(world, entityUser, entity, pos, angle, slope,
					rotate, rotationSpeed, rotationEnd, 
					firstSpeed, limitSpeed, acceleration, gravity,
					shot.form * 8 + shot.color, shot.size, shot.damage, shot.delay, shot.end, shot.special);
	    }
	
	
	/**
	 * Entity生成時に一度だけ呼ばれる処理
	 */
	protected void entityInit()
	{
		dataWatcher.addObject(16, new Integer(0));
		dataWatcher.addObject(17, new Integer(0));
		dataWatcher.addObject(18, new Integer(0));
		dataWatcher.addObject(19, new Integer(0));
		dataWatcher.addObject(20, new Integer(0));
	}
	
	/**
	 * 現在の弾の情報を引き継ぎ再生成する。
	 * この弾は消えずに残り、コピーをそのまま作る（消したけりゃ消してね）
	 */
	public EntityTHShot reCreate()
	{
		if(!this.worldObj.isRemote)
		{
			return THShotLib.createShot(
					this.user, 
					this.source,
					THShotLib.pos_Entity(this), 
					this.angle, 
					this.getAngleZ(),
					this.rotate,
					this.rotationYawSpeed,
					this.rotationEnd,
					this.shotSpeed,
					this.shotLimitSpeed,
					this.shotAcceleration,
					this.gravity,
					ShotData.shot(	this.getShotForm(),
									this.getShotColor(),
									this.getShotSize(),
									this.shotDamage,
									this.delayTime,
									this.getShotEndTime(),
									this.shotSpecial
									)
					);
		}
		return null;
	}
	
//=========================================================================================//
//                                  位置取得メソッド                                       //
//=========================================================================================//
	/**
	 * この弾の位置ベクトルを取得
	 * @return この弾の位置ベクトル
	 */
	public Vec3 pos()
	{
		return THShotLib.pos(posX, posY, posZ);
	}
	
	/**
	 * この弾の位置ベクトルを取得
	 * @return この弾の位置ベクトル
	 */
	public Vec3 pos_Shot()
	{
		return pos();
	}
	
	/**
	 * この弾の持ち主の位置ベクトルを取得
	 * 持ち主がいない場合はこの弾自身の位置ベクトルを取得する
	 * @return この弾の持ち主の位置ベクトル 持ち主がいないとこの弾の位置ベクトル
	 */
	public Vec3 pos_User()
	{
		if(user != null)
		{
			return THShotLib.pos_Living(user);
		}
		return pos();
	}
	
//=========================================================================================//
	
	/**
	
	/**
	 * 移動量から弾の速度を取得
	 * @return 移動量から求めた速度
	 */
	public double getSpeed()
	{
		return (double)MathHelper.sqrt_double( motionX * motionX + motionY * motionY + motionZ * motionZ);
	}
	
	
	/**
	 * 最後に時間が動いていた時の弾の速度を取得
	 * @return 最後に時間が動いていた時の弾の速度
	 */
	public double getLastSpeed()
	{
		return (double)MathHelper.sqrt_double( lastShotMotionX * lastShotMotionX + lastShotMotionY * lastShotMotionY + lastShotMotionZ * lastShotMotionZ);
	}
	
	/**
	 * この弾から任意のEntityLivingBaseへの角度ベクトルを取得
	 * @param living この弾を向かわせる相手
	 * @return livingへの角度ベクトル
	 */
	public Vec3 angle_ToLiving(EntityLivingBase living)
	{
		return THShotLib.angle_ToPos(pos_Shot(), THShotLib.pos_Living(living));
	}

	/**
	 * 移動ベクトルを更新する（角度を変更する処理をしたあとは必ず入れる）
	 */
	public void setVector()
	{	
        //角度に合わせてベクトルを更新
        angle.xCoord =  Math.sin( rotationYaw / 180F * (float)Math.PI) * Math.cos( rotationPitch / 180F * (float)Math.PI);//X方向　水平方向
        angle.yCoord =  Math.sin( rotationPitch / 180F * (float)Math.PI);//Y方向　上下
        angle.zCoord =  Math.cos( rotationYaw / 180F * (float)Math.PI) * Math.cos( rotationPitch / 180F * (float)Math.PI);//Z方向　水平方向
        
        updateMotion();
	}
	
	/**
	 * 削除予定
	 * メソッド名を、updateMotion()に変更
	 */
	@Deprecated
	public void setMotion()
	{
		double speed = getSpeed();
        motionX = angle.xCoord * speed;
        motionY = angle.yCoord * speed;
        motionZ = angle.zCoord * speed;
        
        lastShotMotionX = motionX;
        lastShotMotionY = motionY;
        lastShotMotionZ = motionZ;
	}
	
	/**
	 * 移動ベクトルを更新する
	 */
	public void updateMotion()
	{
		double speed = getSpeed();
        motionX = angle.xCoord * speed;
        motionY = angle.yCoord * speed;
        motionZ = angle.zCoord * speed;
        
        lastShotMotionX = motionX;
        lastShotMotionY = motionY;
        lastShotMotionZ = motionZ;
	}
	
	/**
	 * 速度を設定
	 * ただし、限界速度は越せない
	 * @param speed 変更する速度
	 */
	public void setSpeed(double speed)
	{
		shotSpeed = speed;
        motionX = angle.xCoord * speed;
        motionY = angle.yCoord * speed;
        motionZ = angle.zCoord * speed;
        
        lastShotMotionX = motionX;
        lastShotMotionY = motionY;
        lastShotMotionZ = motionZ;
	}
	
	/**
	 * 削除予定
	 * setRotateVactor(double,double,double)に変更
	 * 
	 * 進行方向に対して真上になるベクトルを設定
	 * @param vectorX
	 * @param vectorY
	 * @param vectorZ
	 */
	@Deprecated
	public void setOverVector(double vectorX, double vectorY, double vectorZ)
	{
		rotate.xCoord = vectorX;
		rotate.yCoord = vectorY;
		rotate.zCoord = vectorZ;
	}
	
	/**
	 * 回転軸ベクトルを設定
	 * @param vectorX 回転軸のX要素
	 * @param vectorY 回転軸のY要素
	 * @param vectorZ 回転軸のZ要素
	 */
	public void setRotateVector(double vectorX, double vectorY, double vectorZ)
	{
		rotate.xCoord = vectorX;
		rotate.yCoord = vectorY;
		rotate.zCoord = vectorZ;
	}
	
	/**
	 * ショットの左右の方向の角度を変更する
	 * 回転軸にそって角度を変える
	 * @param rotateAngle
	 */
	public void setShotRotationYaw(float rotateAngle)
	{
		Vec3 newAngle = getVectorFromRotation(rotate.xCoord, rotate.yCoord, rotate.zCoord, angle.xCoord, angle.yCoord, angle.zCoord, rotateAngle);
		
		angle = newAngle;
		updateMotion();
	}
	
	/**
	 * 任意の回転軸に対して、任意のベクトルがA度回転したベクトルを取得
	 * 各要素指定版
	 * @param rotationVecX 回転軸のX要素
	 * @param rotationVecY 回転軸のY要素
	 * @param rotationVecZ 回転軸のZ要素
	 * @param angleVecX 回転させる角度ベクトルのX要素
	 * @param angleVecY 回転させる角度ベクトルのY要素
	 * @param angleVecZ 回転させる角度ベクトルのZ要素
	 * @param rotationAngle 回転させる角度（-360.0F～360.0F）
	 * @return
	 */
	public Vec3 getVectorFromRotation(double rotationVecX, double rotationVecY, double rotationVecZ, double angleVecX, double angleVecY, double angleVecZ, float rotationAngle)
	{
		double angleRad = (double)rotationAngle / 180.0D * (double)Math.PI;
		double sinA = Math.sin(angleRad);
		double cosA = Math.cos(angleRad);
		double returnVectorX = (rotationVecX * rotationVecX * (1 - cosA) + cosA)              * angleVecX + (rotationVecX * rotationVecY * (1 - cosA) - rotationVecZ * sinA) * angleVecY + (rotationVecZ * rotationVecX * (1 - cosA) + rotationVecY * sinA) * angleVecZ;
		double returnVectorY = (rotationVecX * rotationVecY * (1 - cosA) + rotationVecZ * sinA) * angleVecX + (rotationVecY * rotationVecY * (1 - cosA) + cosA)              * angleVecY + (rotationVecY * rotationVecZ * (1 - cosA) - rotationVecX * sinA) * angleVecZ;
		double returnVectorZ = (rotationVecZ * rotationVecX * (1 - cosA) - rotationVecY * sinA) * angleVecX + (rotationVecY * rotationVecZ * (1 - cosA) + rotationVecX * sinA) * angleVecY + (rotationVecZ * rotationVecZ * (1 - cosA) + cosA)              * angleVecZ;
		
		return Vec3.createVectorHelper(returnVectorX, returnVectorY, returnVectorZ);
	}
	
	/**
	 * 任意の回転軸に対して、任意のベクトルがA度回転したベクトルを取得
	 * ベクトル指定版
	 * @param rotationVecX 回転軸になるベクトル
	 * @param angleVecX 回転させる角度ベクトル
	 * @param rotationAngle 回転させる角度（-360.0F～360.0F）
	 * @return
	 */
	public Vec3 getVectorFromRotation(Vec3 rotation, Vec3 angle, float rotationAngle)
	{	
		return getVectorFromRotation(rotation.xCoord, rotation.yCoord, rotation.zCoord, angle.xCoord, angle.yCoord, angle.zCoord, rotationAngle);
	}
	
	/**
	 * 弾の角度ベクトルの単位ベクトルを取得する
	 * @return 弾の移動方向の単位ベクトル
	 */
	public Vec3 getShotVector()
	{
		return THShotLib.getVectorNomalize(angle);
	}
	
	/**
	 * 角度の更新処理
	 */
	public void updateAngle()
	{	
    	float f = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
    	
    	if(!worldObj.isRemote)
    	{
    		rotationYaw = (float)((Math.atan2(motionX, motionZ) * 180D) / 3.1415927410125732D);
    		for (rotationPitch = (float)((Math.atan2(motionY, f) * 180D) / 3.1415927410125732D); rotationPitch - prevRotationPitch < -180F; prevRotationPitch -= 360F) { }
        	for (rotationPitch = (float)((Math.atan2(motionY, f) * 180D) / 3.1415927410125732D); rotationPitch - prevRotationPitch < -180F; prevRotationPitch -= 360F) { }
        }
        if(rotationYaw - prevRotationYaw > 180F)
        for (; rotationPitch - prevRotationPitch >= 180F; prevRotationPitch += 360F) { }
        for (; rotationYaw - prevRotationYaw < -180F; prevRotationYaw -= 360F) { }
        for (; rotationYaw - prevRotationYaw >= 180F; prevRotationYaw += 360F) { }
        rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 1.0F;
	}
	
	/**
	 * 弾を消滅させる
	 */
	public void delete()
	{
		if(!worldObj.isRemote)
		{
			setDead();
		}
	}
	
	/**
	 * 弾消しボーナス
	 * 弾を消滅と同時に弾アイテムに変化させる
	 */
	public void shotFinishBonus()
	{
		EntityShotMaterial shotMaterial = new EntityShotMaterial(worldObj, posX, posY, posZ);
		
		if(!worldObj.isRemote)
		{
			worldObj.spawnEntityInWorld(shotMaterial);
			setDead();
		}
	}
	
	/**
	 * 水平方向に変化する回転速度を設定する（毎tick変化する角度）
	 * @param rotation 毎tick変化する角度
	 */
	public void setRotationYawSpeed(float rotation)
	{
		rotationYawSpeed = rotation;
	}
	
	/**
	 * 水平方向に変化する回転速度を返す
	 * @return 水平方向に変化する回転速度
	 */
	public float getRotationYawSpeed()
	{
		return rotationYawSpeed;
	}
	

	
	/**
	 * 特殊弾から普通の弾に戻す
	 */
	public void specialEnd()
	{
		shotSpecial = 0;
	}
	
	/**
	 * ショットが存在する限り呼び出されるメソッド
	 */
	@Override
    public void onUpdate()
    {	
    	//弾の主がいないか、死んでいる場合消える
    	if (!worldObj.isRemote && user == null)
        {
            setDead();
        	return;
        }
    	else
    	{
    		//super.onUpdate();
    	}
    	
    	//if(this instanceof EntityTHSetLaser && shootingEntity == null)
    	if(this instanceof EntityTHSetLaser && source == null)
    	{
    		if(!worldObj.isRemote)
    		{
    			setDead();
    			return;
    		}
    	}
    	
    	//Entityの基本的な処理で必要なもの
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.prevRotationPitch = this.rotationPitch;
        this.prevRotationYaw = this.rotationYaw;
    	
    	//ダメージがない弾なら消滅させる
    	if(shotDamage <= 0.0F)
    	{
    		if(!worldObj.isRemote)
    		{
    			setDead();
    			return;
    		}
    	}
	
    	//使用者が死んでいるなら弾をアイテム化させ、消滅させる
    	if(user != null)
    	{
			if(!worldObj.isRemote && (user.isDead))
			{
				if(user instanceof EntityPlayer == false)//仕様者がEntityPlayerに属していないなら弾消しボーナスを出す
				{
					shotFinishBonus();
					return;
				}
			}
    	}

    	//消滅時間を過ぎたら消滅
    	if(!worldObj.isRemote && getAnimationCount() > getDeadTime())
    	{
    		/*if(!worldObj.isRemote && child != null)
    		{
    			worldObj.spawnEntityInWorld(child);
    		}*/
    		setDead();
    	}
    	
    	extinguish();//火はつかないようにする
    	
    	//時間が進んでいないなら
    	if(ticksExisted <= lastTime)
    	{
    		//衝突処理
        	//hitCheck();
    		return;//処理を終了させる
    		
    	}
    	else//時間が通常通り進んでいるなら
    	{
    		//何事もなかったように移動量を設定する
    		motionX = lastShotMotionX;
    		motionY = lastShotMotionY;
    		motionZ = lastShotMotionZ;
    		if(!worldObj.isRemote)
    		{
	    		if(getAnimationCount() >= 0)
	    		{
	    			setAnimationCount(lastTime);
	    		}
	    		else
	    		{
	    			setAnimationCount(getAnimationCount() + 1);
	    			timeProcess();
	    			return;
	    		}
    		}
    	}
    	
		
    	if(getRotationYawSpeed() != 0F)
    	{
    		setShotRotationYaw(getRotationYawSpeed());
    	}
    	
    	
    	/*if(rotationPitch > 180F || rotationPitch < -180F)
    	{
    		rotationPitch = -rotationPitch;
    		//rotationYaw += 180F;
    	}*/
    	updateAngle();//角度を更新する
    	
    	//加速減速の処理
    	shotAcceleration();
    	
    	//弾ごとの特殊な動作を発生させる
    	specialMotion();
    	setGravityLevel();
    	//衝突処理
    	hitCheck();
        
        shotSpeed = getSpeed();
        
        if(rotationEnd < lastTime)
        {
        	setRotationYawSpeed(0F);
        }
    	
    	//最終的な移動量を現在地に加える
    	if(/*!worldObj.isRemote &&*/ getAnimationCount() >= 0)
    	{
    		this.posX += this.motionX;
    		this.posY += this.motionY;
    		this.posZ += this.motionZ;
    	}
    	
        setPosition(this.posX, this.posY, this.posZ);//位置の更新を行う
    	
    	//通常通り時間が進んでいるなら
        timeProcess();
    }
	
	/**
	 * 弾の加速処理をする
	 */
	public void shotAcceleration()
	{
    	//加速弾で速度が限界値より遅いなら
    	if( shotAcceleration > 0.0D && getSpeed() < shotLimitSpeed)
    	{
    		motionX += angle.xCoord * shotAcceleration;
    		motionY += angle.yCoord * shotAcceleration;
    		motionZ += angle.zCoord * shotAcceleration;
    		
    		//移動量に加速分加える
    		if( shotAcceleration > 0.0D && getSpeed() > shotLimitSpeed )//上限下限速を越していたら上限か下限にする
    		{
    			motionX = angle.xCoord * shotLimitSpeed;
    			motionY = angle.yCoord * shotLimitSpeed;
    			motionZ = angle.zCoord * shotLimitSpeed;
    		}
    	}
    	//減速弾で速度が限界値より速いなら
    	else if(shotAcceleration < 0.0D && getSpeed() > shotLimitSpeed)
    	{	
    		//減速値が現在の速度から最低速を引いた値より大きいなら（今の速度に対して過剰な減速値なら）
    		if(Math.abs(shotAcceleration) > getSpeed() - shotLimitSpeed)
    		{
    			motionX = angle.xCoord * shotLimitSpeed;
        		motionY = angle.yCoord * shotLimitSpeed;
        		motionZ = angle.zCoord * shotLimitSpeed;
    		}
    		else
    		{
    			motionX += angle.xCoord * shotAcceleration;
        		motionY += angle.yCoord * shotAcceleration;
        		motionZ += angle.zCoord * shotAcceleration;
    		}
    	}
	}
	
	/**
	 * 時間経過に関する処理
	 */
	protected void timeProcess()
	{
    	//通常通り時間が進んでいるなら
    	if(ticksExisted > lastTime)
    	{
    		//最後に時間が動いていたときの時間と移動量を保存する
    		lastTime = ticksExisted;
    		lastShotMotionX = motionX;
    		lastShotMotionY = motionY;
    		lastShotMotionZ = motionZ;
    		lastRotationYaw = (float)Math.atan2(lastShotMotionX, lastShotMotionZ) / 3.141593F * 180F;
    		lastRotationPitch = (float)Math.atan2(lastShotMotionY, Math.sqrt( lastShotMotionX * lastShotMotionX + lastShotMotionZ * lastShotMotionZ)) / 3.141593F * 180F;
    	}
	}
	
	/**
	 * 例外処理。通常当たり判定を持たないEntityにも当たり判定を持つようになる
	 * @param entity 通常当たらないけど、当たるようにしたいEntity
	 * @return trueなら当たる
	 */
	public boolean userHitCheck(Entity entity)
	{
		if(this.shotSpecial == THSC_YouryokuSpoiler.SPECIAL_SPOILER01 || shotSpecial == THSC_YouryokuSpoiler.SPECIAL_SPOILER02)
		{
			return true;
			
		}
		return !entity.isEntityEqual(user);
	}
	
	/**
	 * Entityとの当たり判定をとる
	 * @param movingObjectPosition
	 * @param vec3d
	 * @param vec3d1
	 * @return
	 */
	public MovingObjectPosition hitEntityCheck(MovingObjectPosition movingObjectPosition, Vec3 vec3d, Vec3 vec3d1)
	{
        Entity entity = null;//実際に当たったことにするEntity
    	double d = 0.0D;//そのEntityまでの仮の距離
		float hitSize = getShotSize() * 0.5F;
    	//ここから移動量分の線分を作り、それに弾の大きさの２倍の肉付けをし直方体を作る。それに当たったEntityをリスト化する\\
        List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(this.angle.xCoord, this.angle.yCoord, this.angle.zCoord).expand(hitSize, hitSize, hitSize));//指定範囲内のEntityをリストに登録
		
    	for (int j = 0; j < list.size(); j++)
        {
            Entity entity1 = (Entity)list.get(j);//entity1にリストの先端のentityを保存
        	//entity1が、当たり判定を取らない　または　entity1が使用者　または　飛んで25カウント以下？　または　EntityTHShotならパス
            if ( entity1.canBeCollidedWith() && 
            	userHitCheck(entity1) && 
            	/*!entity1.isEntityEqual(shootingEntity) &&*/
            	!hitCheckEx(entity1) && 
            	entity1 instanceof EntityAnimal == false &&
            	entity1 instanceof EntityVillager == false &&
            	(entity1 instanceof EntityLivingBase || entity1 instanceof EntityDragonPart || entity1 instanceof EntityTHShot ||
            	entity1 instanceof EntityYuukaParasol) &&
            	!(user instanceof EntityTHFairy && entity1 instanceof EntityTHFairy))
        	{
            	if(entity1 instanceof EntityTHShot)
            	{
            		EntityTHShot shot = (EntityTHShot)entity1;
            		if(user != shot.user)
            		{
    	        		//判定を弾の大きさに変更
    	            	AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(hitSize, hitSize, hitSize);
    	            	MovingObjectPosition movingObjectPosition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);
    	        		//この判定で当たっているなら
    	            	if (movingObjectPosition1 != null)
    	            	{
    	        			//当たっているならここからその点までの距離を取得
    	            		double d1 = vec3d.distanceTo(movingObjectPosition1.hitVec);
    	        			//今までの一番近くにいるなら、一番近いEntityを更新する
    	            		if (d1 < d || d == 0.0D)
    	            		{
    	                		entity = entity1;
    	                		d = d1;
    	            		}
    	        		}
            		}
            	}
            	else
            	{
	        		//判定を弾の大きさに変更
	            	AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(hitSize, hitSize, hitSize);
	            	MovingObjectPosition movingObjectPosition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);
	        		//この判定で当たっているなら
	            	if (movingObjectPosition1 != null)
	            	{
	        			//当たっているならここからその点までの距離を取得
	            		double d1 = vec3d.distanceTo(movingObjectPosition1.hitVec);
	        			//今までの一番近くにいるなら、一番近いEntityを更新する
	            		if (d1 < d || d == 0.0D)
	            		{
	                		entity = entity1;
	                		d = d1;
	            		}
	        		}
            	}
        	}
        }

    	//当たったEntityがいるなら、当たったEntityをMovingObjectPositionで登録
        if (entity != null)
        {
            movingObjectPosition = new MovingObjectPosition(entity);
        }
		
		/*if (movingObjectPosition != null && movingObjectPosition.entityHit != null && movingObjectPosition.entityHit instanceof EntityPlayer)
        {
        	EntityPlayer entityPlayer = (EntityPlayer)movingObjectPosition.entityHit;

            if (entityPlayer.capabilities.disableDamage || shootingEntity instanceof EntityPlayer && !((EntityPlayer)shootingEntity).func_96122_a(entityPlayer))
            {
            	movingObjectPosition = null;
            }
        }*/
		
        return movingObjectPosition;
	}
	
	/**
	 * 使用者に当たっているかを判定する
	 * @param movingObjectPosition
	 * @param vec3d
	 * @param vec3d1
	 * @return
	 */
	public boolean hitUserCheck(MovingObjectPosition movingObjectPosition, Vec3 vec3d, Vec3 vec3d1)
	{
		float hitSize = getShotSize() * 0.5F;
    	//ここから移動量分の線分を作り、それに弾の大きさの２倍の肉付けをし直方体を作る。それに当たったEntityをリスト化する\\
        List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(hitSize, hitSize, hitSize));//指定範囲内のEntityをリストに登録
		
    	for (int j = 0; j < list.size(); j++)
        {
            Entity entity1 = (Entity)list.get(j);//entity1にリストの先端のentityを保存
        	//entity1が、当たり判定を取らない　または　entity1が使用者　または　飛んで25カウント以下？　または　EntityTHShotならパス
            if ( entity1.equals(user)  )
        	{
    	        //判定を弾の大きさに変更
    	        AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(hitSize, hitSize, hitSize);
    	        MovingObjectPosition movingObjectPosition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);
    	        //この判定で当たっているなら
    	        if (movingObjectPosition1 != null)
    	        {
    	        	return true;
    	        }
        	}
        }
		
        return false;
	}
	
	/**
	 * 衝突処理
	 */
	protected void hitCheck()
	{
		double harfSize = this.getShotSize() / 2.0D;
		double shotSizeX = angle.xCoord * harfSize;
		double shotSizeY = angle.yCoord * harfSize;
		double shotSizeZ = angle.zCoord * harfSize;
		
	    //始点（現在地）
    	Vec3 startPos = Vec3.createVectorHelper(posX - shotSizeX, posY - shotSizeY, posZ - shotSizeZ);
    	//終点（現在地に移動量を足した点）
    	Vec3 endPos = Vec3.createVectorHelper(posX + motionX + shotSizeX, posY + motionY + shotSizeY, posZ + motionZ + shotSizeZ);
        //始点と終点からブロックとの当たりを取得
    	//MovingObjectPosition movingObjectPosition = worldObj.rayTraceBlocks_do_do(vec3d, vec3d1, false, true);
    	MovingObjectPosition movingObjectPosition = worldObj.func_147447_a(startPos, endPos, false, true, true);
    	startPos = Vec3.createVectorHelper(posX - shotSizeX, posY - shotSizeY, posZ - shotSizeZ);
    	endPos = Vec3.createVectorHelper(posX + motionX + shotSizeX, posY + motionY + shotSizeY, posZ + motionZ + shotSizeZ);
    	//何らかのブロックに当たっているなら
        if (movingObjectPosition != null)
        {
        	//終点を当たった点に変更
        	endPos = Vec3.createVectorHelper(movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
        }
        
        movingObjectPosition = hitEntityCheck(movingObjectPosition, startPos, endPos);
        
        boolean isHit = false;
        
        if (movingObjectPosition != null)
        {
        	//当たった場合の処理をし、弾がまだ存在するなら、上下左右の当たり判定も取る
            isHit = onImpact(movingObjectPosition);
        }
        
        if(!isHit)
        {
        	startPos = Vec3.createVectorHelper(posX, posY - harfSize, posZ);
        	endPos = Vec3.createVectorHelper(posX, posY + harfSize, posZ);
        	movingObjectPosition = worldObj.func_147447_a(startPos, endPos, false, true, true);
        	//何らかのブロックに当たっているなら
        	if(movingObjectPosition != null && !blockHitSpecialProcess(movingObjectPosition))
            {
            	this.delete();
            	
            }
        	
            startPos = Vec3.createVectorHelper(posX - harfSize, posY, posZ);
            endPos = Vec3.createVectorHelper(posX + harfSize, posY, posZ);
            movingObjectPosition = worldObj.func_147447_a(startPos, endPos, false, true, true);
            //何らかのブロックに当たっているなら
            if(movingObjectPosition != null && !blockHitSpecialProcess(movingObjectPosition))
            {
            	this.delete();
            }
                
            startPos = Vec3.createVectorHelper(posX, posY, posZ - harfSize);
            endPos = Vec3.createVectorHelper(posX, posY, posZ + harfSize);
            movingObjectPosition = worldObj.func_147447_a(startPos, endPos, false, true, true);
            //何らかのブロックに当たっているなら
            if(movingObjectPosition != null && !blockHitSpecialProcess(movingObjectPosition))
            {
              	this.delete();
            }
        }

	}
	
	/**
	 * 独自の動きを追加するためのもの
	 */
	protected void specialMotion()
	{
		ISpecialShot iSpecialShot;
		Class<? extends ISpecialShot> specialShotClass;
		if( (specialShotClass = SpecialShotRegistry.getSpecialShotClass(shotSpecial)) != null)
		{
			try {
				iSpecialShot = (ISpecialShot)specialShotClass.newInstance();
				iSpecialShot.specialShot_move(worldObj, shotSpecial, this);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			return;
		}
		
		switch(shotSpecial)
		{
			case HOMING01:
				homing(4.0F);
				break;
			case DIFFUSION01:
				diffusion(ShotData.shot(FORM_AMULET, BLUE, 0.4F, 5.0F, 0, 60), 0.09D);
				break;
			case FALL01:
				motionY -= 0.02;
				break;

			default:
				break;
		}
	}
	
	/**
	 * 使用者と当たり判定を取るかどうか
	 * @return 使用者に当たるならtrue
	 */
	protected boolean isUserHit()
	{
		return false;
	}

	/**
	 * ブロックやEntityに当たった時の処理
	 * @param movingObjectPosition
	 * @return 消滅したらtrue
	 */
    protected boolean onImpact(MovingObjectPosition movingObjectPosition)
    {
    	//当たった時の処理
    	if (!worldObj.isRemote)
    	{
    		Entity hitEntity = movingObjectPosition.entityHit;
        
    		//当たったEntityがいるなら
    		if ( hitEntity != null )
        	{
    			if(this.getShotForm() == FORM_FAMILIAR)
    			{
    				return false;
    			}
        		//それがEntityTHShotに属していないなら
        		if(hitEntity instanceof EntityTHShot == false)
        		{
        			boolean isHitDelete = true;
        			boolean flag = true;

        			/*if(hitEntity instanceof EntityLivingBase)
        			{
        				EntityLivingBase living = (EntityLivingBase)hitEntity;
        				if(living.isSneaking());
        				{
        					flag = false;
        				}
        			}*/
        			if(flag)
        			{
	        			damageRate = 1.0F;
	        			float damageRateEx = 1.0F;
	        			if(hitEntity instanceof EntityDanmakuMob)
	        			{
	        				EntityDanmakuMob danmakuMob = (EntityDanmakuMob)hitEntity;
	        				if(danmakuMob.isYoukai() || danmakuMob.isFairy() || danmakuMob.isPhantom())
	        				{
	        					damageRateEx = 2.0F;
	        				}
	        			}
	        			//Entityに当たった時の特殊な処理
	        			isHitDelete = entityHitSpecialProcess(hitEntity);
	        			//指定したダメージ分の魔法ダメージを与える
	        			if(isUserHit() || !hitEntity.isEntityEqual(user))
	        			{
	        				if (!hitEntity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, user), getShotDamageWithDifficulty() * damageRate * damageRateEx));
	        			}
        			}
        			
        			//弾を消滅させる
					if(!isHitDelete)
					{
						delete();
						return true;
					}
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
        				float shotDamageB = entityTHShot.shotDamage;
        				/*if(user instanceof EntityPlayer == false)
        				{
        					this.shotDamage -= shotDamageB;
        				}
        				else
        				{
        					//this.shotDamage -= entityTHShot.shotDamage * 0.1F;
        					shotDamage -= shotDamageB * 0.2F;
        					entityTHShot.shotDamage -= shotDamageA * 0.2F;
        				}
        				if(entityTHShot.user instanceof EntityPlayer == false)
        				{
        					entityTHShot.shotDamage -= shotDamageA;
        				}
        				else
        				{
        					//entityTHShot.shotDamage -= shotDamageA * 0.1F;
        					shotDamage -= shotDamageB * 0.2F;
        					entityTHShot.shotDamage -= shotDamageA * 0.2F;
        				}*/
        				if(user instanceof EntityPlayer)
        				{
        					shotDamage -= shotDamageB * 0.1F;
        				}
        				else
        				{
        					shotDamage -= shotDamageB * 0.3F;
        				}
        				if(entityTHShot.user instanceof EntityPlayer)
        				{
        					entityTHShot.shotDamage -= shotDamageA * 0.1F;
        				}
        				else
        				{
        					entityTHShot.shotDamage -= shotDamageA * 0.3F;
        				}
        				if(entityTHShot.shotDamage < 0.0F)
        				{
        					entityTHShot.shotDamage = 0.0F;
        				}
        				if(this.shotDamage < 0.0F)
        				{
        					shotDamage = 0.0F;
        					return true;
        				}
        			}
        		}
			}
    		else
    		{
    			if(!blockHitSpecialProcess(movingObjectPosition))
    			{
    				delete();
    				return true;
    			}
    		}
    	}
    	
    	return false;
    }
    
    /**
     * 難易度の違いによる弾のダメージを返す
     * プレイヤーの出した弾の威力は難易度の影響を受けない
     * ノーマルを通常ダメージとし、イージーで０．７倍、ハードで１．５倍になる
     * @return 補正付きダメージ
     */
    protected float getShotDamageWithDifficulty()
    {
    	if(this.user instanceof EntityPlayer)
    	{
    		return shotDamage;
    	}
    	
    	//Danmaku 1 Kill Modeなら
    	if(THKaguyaConfig.danmakuOneKillMode)
    	{
    		return 999999F;//ほぼ確定で倒れるダメージを返す
    	}
    	
    	switch(this.worldObj.difficultySetting)
    	{
    		case PEACEFUL:
    			return shotDamage * 1.0F;//0.7F;
    		case EASY:
    			return shotDamage * 0.7F;
    		case NORMAL:
    			return shotDamage;
    		case HARD:
    			return shotDamage * 1.5F;
    		default:
    			return shotDamage;
    	}
    }
	
    /**
     * Entityと衝突したときの特殊な処理
     * @param hitEntity 当たったEntity
     * @return 通常通り弾が消えるならfalse、消えずに残るならtrue
     */
	public boolean entityHitSpecialProcess(Entity hitEntity)
	{
		ISpecialShot iSpecialShot;
		Class<? extends ISpecialShot> specialShotClass;
		if( (specialShotClass = SpecialShotRegistry.getSpecialShotClass(shotSpecial)) != null)
		{
			try {
				iSpecialShot = (ISpecialShot)specialShotClass.newInstance();
				return iSpecialShot.specialShot_hitEntity(worldObj, shotSpecial, this, hitEntity);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			return false;
		}
		
		
		switch(this.shotSpecial)
		{
			case FIRE:
				hitEntity.setFire((int)shotDamage);
				return false;
			case EXPLOSION01:
        		if(!worldObj.isRemote)
				{
					//威力5.0の強力な爆発を起こす。ブロックを破壊するかはコンフィグで設定（デフォは破壊する）
					worldObj.createExplosion(user, hitEntity.posX, hitEntity.posY, hitEntity.posZ, 3.0F, THKaguyaConfig.MasterSparkDestroysBlocks);
				}
				return false;
			case WIND01:
				hitEntity.motionX += motionX * 1.7D;
				hitEntity.motionY += motionY * 1.7D + 2.0D;
				hitEntity.motionZ += motionZ * 1.7D;
				if(!hitEntity.onGround)
				{
					damageRate *= 2.0F;
				}
				return false;
			default:
				return false;
		}
	}
	
	/**
	 * ブロックとの衝突時の特別な処理 shotSpecialで処理は決めている
	 * 返り値：弾が消滅するならfalse、消滅しないならtrue
	 */
	public boolean blockHitSpecialProcess(MovingObjectPosition movingObjectPosition)
	{
		ISpecialShot iSpecialShot;
		Class<? extends ISpecialShot> specialShotClass;
		if( (specialShotClass = SpecialShotRegistry.getSpecialShotClass(shotSpecial)) != null)
		{
			try {
				iSpecialShot = (ISpecialShot)specialShotClass.newInstance();
				return iSpecialShot.specialShot_hitBlock(worldObj, shotSpecial, this, movingObjectPosition);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			return false;
		}
		
		
		switch(this.shotSpecial)
		{
			case BOUND01:
				bound(movingObjectPosition, 0.9D, 2);
				shotSpecial = 0;
				return true;
			case BOUND02:
				bound(movingObjectPosition, 0.9D, 3);
				shotSpecial = BOUND01;
				return true;
			case BOUND03:
				bound(movingObjectPosition, 0.9D, 4);
				shotSpecial = BOUND02;
				return true;
			case BOUND04:
				bound(movingObjectPosition, 0.9D, 9999);
				//shotSpecial = thShotLib.BOUND03;
				return true;
			case EXPLOSION01:
    			if(!worldObj.isRemote)
    			{
    				//同上
    				worldObj.createExplosion(user, movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord, 5.0F, THKaguyaConfig.MasterSparkDestroysBlocks);
    			}
				return false;
			default:
				return false;
			
		}
	}
	
	//モーゼの奇跡の海を割る弾
	/*private void umiware()
	{
    	int pi;
    	int px  = (int)posX;
    	int pyd = (int)posY;
    	int pyu = pyd;
    	int pz  = (int)posZ;
    	double pxr = posX;
    	double pxl = posX;
    	double pzr = posZ;
    	double pzl = posZ;
    	double movingLengthX;
    	double movingLengthZ;
    	Material mate = null;
    	movingLengthX = -Math.sin( (rotationYaw+90F) / 180F * 3.141593F)*0.5D;
    	movingLengthZ =  Math.cos( (rotationYaw+90F) / 180F * 3.141593F)*0.5D;
    	while( ( worldObj.getBlockMaterial(px, pyd, pz) == Material.water || worldObj.isAirBlock(px, pyd, pz) ) && pyd > 0)
    	{
    		//worldObj.setBlockAndMetadataWithNotify(px, pyd, pz, 0, 0, 0);//空気を設置
    		if(worldObj.isRemote)
    		{
    			worldObj.setBlock(px, pyd, pz, 0, 0, 0);//空気を設置
    		}

    		pxr = posX;
    		pxl = posX;
    		pzr = posZ;
    		pzl = posZ;
    		pi = 0;
    		do
    		{
    			pxr += movingLengthX;
    			pzr -= movingLengthZ;
    			if( (mate = worldObj.getBlockMaterial((int)pxr, pyd, (int)pzr)) == Material.water)
    			{
    				//worldObj.setBlockAndMetadataWithNotify((int)pxr, pyd, (int)pzr, 0, 0, 0);
    				worldObj.setBlock((int)pxr, pyd, (int)pzr, 0, 0, 0);
    			}
    			pi++;
    		}while(pi <= 12 && (mate == Material.water  || worldObj.isAirBlock((int)pxr, pyd, (int)pzr)));
    		pi = 0;
    		do
    		{
    			pxl -= movingLengthX;
    			pzl += movingLengthZ;
    			if( (mate = worldObj.getBlockMaterial((int)pxl, pyd, (int)pzl)) == Material.water)
    			{
    				//worldObj.setBlockAndMetadataWithNotify((int)pxl, pyd, (int)pzl, 0, 0, 0);
    				worldObj.setBlock((int)pxl, pyd, (int)pzl, 0, 0, 0);
    			}
    			pi++;
    		}while(pi <= 12 && (mate == Material.water ||  worldObj.isAirBlock((int)pxl, pyd, (int)pzl)));
    		pyd--;
    	}
    	while(( worldObj.getBlockMaterial(px, pyu, pz) == Material.water || worldObj.isAirBlock(px, pyu, pz) ) && pyu < 256)
    	{
    		//worldObj.setBlockAndMetadataWithNotify(px, pyu, pz, 0, 0, 0);//空気を設置
    		worldObj.setBlock(px, pyu, pz, 0, 0, 0);//空気を設置
    		
    		pxr = posX;
    		pxl = posX;
    		pzr = posZ;
    		pzl = posZ;
    		pi = 0;
    		do
    		{
    			pxr += movingLengthX;
    			pzr -= movingLengthZ;
    			if( (mate = worldObj.getBlockMaterial((int)pxr, pyu, (int)pzr)) == Material.water)
    			{
    				//worldObj.setBlockAndMetadataWithNotify((int)pxr, pyu, (int)pzr, 0, 0, 0);
    				worldObj.setBlock((int)pxr, pyu, (int)pzr, 0, 0, 0);
    			}
    			pi++;
    		}while(pi <= 12 && (mate == Material.water  || worldObj.isAirBlock((int)pxr, pyu, (int)pzr)));
    		pi = 0;
    		do
    		{
    			pxl -= movingLengthX;
    			pzl += movingLengthZ;
    			if( (mate = worldObj.getBlockMaterial((int)pxl, pyu, (int)pzl)) == Material.water)
    			{
    				//worldObj.setBlockAndMetadataWithNotify((int)pxl, pyu, (int)pzl, 0, 0, 0);
    				worldObj.setBlock((int)pxl, pyu, (int)pzl, 0, 0, 0);
    			}
    			pi++;
    		}while(pi <= 12 && (mate == Material.water ||  worldObj.isAirBlock((int)pxl, pyu, (int)pzl)));
    		pyu++;
    	}
	}*/
	
	//跳ね返り弾
	public void bound(MovingObjectPosition movingObjectPosition, double returnRate, int returnCount)
	{
		//returnRate = 1.0D;
		
		double supPosX1 = posX;
        double supPosX2 = posX + motionX;//movingObjectPosition.hitVec.xCoord;
        double supPosY1 = posY;
        double supPosY2 = posY + motionY;//movingObjectPosition.hitVec.yCoord;
        double supPosZ1 = posZ;
        double supPosZ2 = posZ + motionZ;//movingObjectPosition.hitVec.zCoord;
		
		//始点を登録
        Vec3 supVec3d1 = Vec3.createVectorHelper(supPosX1, supPosY1, supPosZ1);
    	//終点を登録
    	Vec3 supVec3d2 = Vec3.createVectorHelper(supPosX2, supPosY2, supPosZ2);
        //始点と終点からブロックとの衝突を取得
    	//movingObjectPosition = worldObj.rayTraceBlocks_do_do(supVec3d1, supVec3d2, false, true);
    	movingObjectPosition = worldObj.rayTraceBlocks(supVec3d1, supVec3d2, true);
        //始点を登録
        supVec3d1 = Vec3.createVectorHelper(supPosX1, supPosY1, supPosZ1);
    	//終点を登録
    	supVec3d2 = Vec3.createVectorHelper(supPosX2, supPosY2, supPosZ2);
		
		//int returnCount = 0;//跳ね返り過ぎて無限ループに陥ったときの対処
		
		while(movingObjectPosition != null && movingObjectPosition.entityHit == null)
        {
	        switch(movingObjectPosition.sideHit)
	        {
	            case 0:
	            	motionY *= -returnRate;
	            	//accelerationY *= -returnRate;
	            	break;
	            case 1:
	            	motionY *= -returnRate;
	            	//accelerationY *= -returnRate;
	            	break;
	            case 2:
	            	motionZ *= -returnRate;
	            	//accelerationZ *= -returnRate;
	            	break;
	            case 3:
	            	motionZ *= -returnRate;
	            	//accelerationZ *= -returnRate;
	            	break;
	            case 4:
	            	motionX *= -returnRate;
	            	//accelerationX *= -returnRate;
	            	break;
	            case 5:
	            	motionX *= -returnRate;
	            	//accelerationX *= -returnRate;
	            	break;
	            default:
	            	return;
	        }
	        //motionX -= (movingObjectPosition.hitVec.xCoord - supPosX1);
	        //motionY -= (movingObjectPosition.hitVec.yCoord - supPosY1);
	        //motionZ -= (movingObjectPosition.hitVec.zCoord - supPosZ1);
	        //if()
            supPosX1 = supPosX1 + (movingObjectPosition.hitVec.xCoord - supPosX1) * 0.99D;
            supPosX2 = supPosX1 + motionX;
            supPosY1 = supPosY1 + (movingObjectPosition.hitVec.yCoord - supPosY1) * 0.99D;
            //supPosY1 = supMovingObjectPosition.hitVec.yCoord;
            supPosY2 = supPosY1 + motionY;
            supPosZ1 = supPosZ1 + (movingObjectPosition.hitVec.zCoord - supPosZ1) * 0.99D;
            supPosZ2 = supPosZ1 + motionZ;
        	//始点を登録
            supVec3d1 = Vec3.createVectorHelper(supPosX1, supPosY1, supPosZ1);
    		//終点を登録
    		supVec3d2 = Vec3.createVectorHelper(supPosX2, supPosY2, supPosZ2);
        	//始点と終点からブロックとの衝突を取得
    		//movingObjectPosition = worldObj.rayTraceBlocks_do_do(supVec3d1, supVec3d2, false, true);
    		movingObjectPosition = worldObj.rayTraceBlocks(supVec3d1, supVec3d2, true);
            //始点を登録
            supVec3d1 = Vec3.createVectorHelper(supPosX1, supPosY1, supPosZ1);
    		//終点を登録
    		supVec3d2 = Vec3.createVectorHelper(supPosX2, supPosY2, supPosZ2);
    		
    		//hitEntityCheck(movingObjectPosition, supVec3d1, supVec3d2);
    		//setVector();
        	
        	returnCount--;
        	if(returnCount <= 0)
        	{
        		setDead();
        		return;
        	}
        }
	}
	
	/**
	 * ホーミング
	 */
	public void homing(float homingLevel)
	{	
		//追尾する動きをする
		Entity entity = null;
        List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(24.0D, 24.0D, 24.0D));//指定範囲内のEntityをリストに登録

		EntityLivingBase nearEntity = null;
		double nearDistance = 999.9D;
		float nearAngle = 180F;
		double nearValue = nearDistance * THShotLib.halfAbsSin(nearAngle / 180F * (float)Math.PI);
		Vec3 shotVec = this.getShotVector();		
		
		for (int j = 0; j < list.size(); j++)
        {
        	Entity entitys = (Entity)list.get(j);//entity1にリストの先端のentityを保存
        	//動物や生物でないものなら反応しない
        	if ( (entitys instanceof EntityLivingBase) == false || entitys instanceof EntityAnimal ||  entitys instanceof EntityVillager || entitys == source || entitys == user)
            {
                continue;
            }
        	//if(entitys.getEntityName().equals(this.getOwnerName()))
        	/*if(entitys.get)
        	{
        		continue;
        	}*/
        	EntityLivingBase entity1 = (EntityLivingBase)entitys;
        	//生物が死んでいるなら無視する
        	if(entity1.isDead)
        	{
        		continue;
        	}
        	
        	//始点（現在地）
    		Vec3 vec3d = Vec3.createVectorHelper(posX, posY, posZ);
    		//終点（現在地に移動量を足した点）
    		Vec3 vec3d1 = Vec3.createVectorHelper(entity1.posX, entity1.posY + entity1.getEyeHeight(), entity1.posZ);
        	//始点と終点からブロックとの当たりを取得
    		//MovingObjectPosition movingObjectPosition = worldObj.rayTraceBlocks_do_do(vec3d, vec3d1, false, true);
    		MovingObjectPosition movingObjectPosition = worldObj.func_147447_a(vec3d, vec3d1, false, true, false);
    		vec3d = Vec3.createVectorHelper(posX, posY, posZ);
    		vec3d1 = Vec3.createVectorHelper(entity1.posX, entity1.posY + entity1.getEyeHeight(), entity1.posZ);
    		//何らかのブロックに当たっているなら
        	if (movingObjectPosition != null && movingObjectPosition.entityHit == null)
        	{
        		continue;
        	}
        	
        	Vec3 targetVec = Vec3.createVectorHelper(entity1.posX - posX, entity1.posY + entity1.getEyeHeight() - posY, entity1.posZ - posZ);
        	targetVec = THShotLib.getVectorNomalize(targetVec);//単位ベクトルに変換
        	float angleSpan = Math.abs(THShotLib.getVectorAndVectorAngle(shotVec, targetVec));
        	double toEntity1Distance = this.getDistance(entity1.posX, entity1.posY + entity1.getEyeHeight(), entity1.posZ);//getDistanceToEntity(entity1);
        	double value = toEntity1Distance * THShotLib.halfAbsSin(angleSpan / 180F * (float)Math.PI);
        	if(nearValue > value)
        	{
            	//double toEntity1Distance;
            	//if((toEntity1Distance = getDistanceToEntity(entity1)) < nearDistance)
            	{
            		nearEntity = entity1;
            		nearAngle = angleSpan;
            		nearValue = value;
            		nearDistance = toEntity1Distance;
            	}
            	/*else
            	{
            		continue;
            	}*/
        	}
        	

        }

		//追尾対象がいるならば
		if(nearEntity != null)
		{
			Vec3 targetVec = Vec3.createVectorHelper(nearEntity.posX - posX, nearEntity.posY + nearEntity.getEyeHeight() - posY, nearEntity.posZ - posZ);
        	targetVec = THShotLib.getVectorNomalize(targetVec);//単位ベクトルに変換
			Vec3 rotate = THShotLib.getOuterProduct(this.getShotVector(), targetVec);
			float rotateAngle = THShotLib.getVectorAndVectorAngle(shotVec, targetVec);
			if(rotateAngle > homingLevel)
			{
				rotateAngle = homingLevel;
			}
			else if(rotateAngle < -homingLevel)
			{
				rotateAngle = -homingLevel;
			}
			Vec3 newVec = THShotLib.getVectorFromRotation(rotate, angle, rotateAngle);
			angle = newVec;
			if(!worldObj.isRemote)
			{
				//this.setMotion();
				shotAcceleration();
				//this.updateAngle();
			}
		}
	}
	
	/**
	 * ホーミング 壁を無視して動く
	 */
	public void homing2(float homingLevel)
	{	
		//追尾する動きをする
		Entity entity = null;
        List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(24.0D, 24.0D, 24.0D));//指定範囲内のEntityをリストに登録

		EntityLivingBase nearEntity = null;
		double nearDistance = 999.9D;
		float nearAngle = 180F;
		double nearValue = nearDistance * THShotLib.halfAbsSin(nearAngle / 180F * (float)Math.PI);
		Vec3 shotVec = this.getShotVector();		
		
		for (int j = 0; j < list.size(); j++)
        {
        	Entity entitys = (Entity)list.get(j);//entity1にリストの先端のentityを保存
        	//動物や生物でないものなら反応しない
        	if ( (entitys instanceof EntityLivingBase) == false || entitys instanceof EntityAnimal ||  entitys instanceof EntityVillager || entitys == source || entitys == user)
            {
                continue;
            }

        	EntityLivingBase entity1 = (EntityLivingBase)entitys;
        	//生物が死んでいるなら無視する
        	if(entity1.isDead)
        	{
        		continue;
        	}
        	
        	//始点（現在地）
    		Vec3 vec3d = Vec3.createVectorHelper(posX, posY, posZ);
    		//終点（現在地に移動量を足した点）
    		Vec3 vec3d1 = Vec3.createVectorHelper(entity1.posX, entity1.posY + entity1.getEyeHeight(), entity1.posZ);
        	//始点と終点からブロックとの当たりを取得
    		MovingObjectPosition movingObjectPosition = worldObj.func_147447_a(vec3d, vec3d1, false, true, false);
    		vec3d = Vec3.createVectorHelper(posX, posY, posZ);
    		vec3d1 = Vec3.createVectorHelper(entity1.posX, entity1.posY + entity1.getEyeHeight(), entity1.posZ);
    		//何らかのブロックに当たっているなら
        	/*if (movingObjectPosition != null && movingObjectPosition.entityHit == null)
        	{
        		continue;
        	}*/
        	
        	Vec3 targetVec = Vec3.createVectorHelper(entity1.posX - posX, entity1.posY + entity1.getEyeHeight() - posY, entity1.posZ - posZ);
        	targetVec = THShotLib.getVectorNomalize(targetVec);//単位ベクトルに変換
        	float angleSpan = Math.abs(THShotLib.getVectorAndVectorAngle(shotVec, targetVec));
        	double toEntity1Distance = this.getDistance(entity1.posX, entity1.posY + entity1.getEyeHeight(), entity1.posZ);//getDistanceToEntity(entity1);
        	double value = toEntity1Distance * THShotLib.halfAbsSin(angleSpan / 180F * (float)Math.PI);
        	if(nearValue > value)
        	{
            	nearEntity = entity1;
            	nearAngle = angleSpan;
            	nearValue = value;
            	nearDistance = toEntity1Distance;
        	}
        	

        }

		//追尾対象がいるならば
		if(nearEntity != null)
		{
			Vec3 targetVec = Vec3.createVectorHelper(nearEntity.posX - posX, nearEntity.posY + nearEntity.getEyeHeight() - posY, nearEntity.posZ - posZ);
        	targetVec = THShotLib.getVectorNomalize(targetVec);//単位ベクトルに変換
			Vec3 rotate = THShotLib.getOuterProduct(this.getShotVector(), targetVec);
			float rotateAngle = THShotLib.getVectorAndVectorAngle(shotVec, targetVec);
			if(rotateAngle > homingLevel)
			{
				rotateAngle = homingLevel;
			}
			else if(rotateAngle < -homingLevel)
			{
				rotateAngle = -homingLevel;
			}
			Vec3 newVec = THShotLib.getVectorFromRotation(rotate, angle, rotateAngle);
			angle = newVec;
			if(!worldObj.isRemote)
			{
				//this.setMotion();
				shotAcceleration();
				
				//this.updateAngle();
				
			}
		}
	}
	
	/*拡散アミュレット*/
	public void diffusion(ShotData shot, double gravityLevel)
	{
		//弾の消滅時間なら
		if(isShotEndTime())
		{
			double vectorX, vectorY, vectorZ;
			double vectorXG = 0.0D, vectorYG = 0.0D, vectorZG = 0.0D;
			if(user != null)
			{
				vectorXG = -Math.sin(user.rotationYaw / 180F * 3.141593F) * Math.cos(user.rotationPitch / 180F * 3.141593F) * gravityLevel;
				vectorYG = -Math.sin(user.rotationPitch / 180F * 3.141593F) * gravityLevel;
				vectorZG =  Math.cos(user.rotationYaw / 180F * 3.141593F) * Math.cos(user.rotationPitch / 180F * 3.141593F) * gravityLevel;
				THShotLib.createSphereShot(user, user, THShotLib.pos_Entity(this), angle, 0F, rotate_Default(), 0F, 9999, 0.6D, 0.6D, 0.00D, THShotLib.gravity(vectorXG, vectorYG, vectorZG), shot, 20, 0.0D, rand.nextFloat() * 360F);
				THShotLib.createSphereShot(user, user, THShotLib.pos_Entity(this), angle, 0F, rotate_Default(), 0F, 9999, 0.4D, 0.6D, 0.01D, THShotLib.gravity(vectorXG, vectorYG, vectorZG), shot, 16, 0.0D, rand.nextFloat() * 360F);
				THShotLib.createSphereShot(user, user, THShotLib.pos_Entity(this), angle, 0F, rotate_Default(), 0F, 9999, 0.2D, 0.6D, 0.01D, THShotLib.gravity(vectorXG, vectorYG, vectorZG), shot, 8, 0.0D, rand.nextFloat() * 360F);
			}
			
			if(!worldObj.isRemote)
			{
				setDead();
			}
			return;
		}
	}
	
	//データを保存する
	@Override
    public void writeEntityToNBT(NBTTagCompound nbtTagCompound)
    {
    }

    //データを読み込む
	@Override
    public void readEntityFromNBT(NBTTagCompound nbtTagCompound)
    {
    }
	
	//当たり判定の追加
	protected boolean hitCheckEx(Entity entity)
	{
		return false;
	}
    	
    //弾の強さ
    /*
    public int getShotStrength()
    {
    	return 10;
    }*/
    

	

    /**
     * Entityからの攻撃を受けたときの処理　要は跳ね返す処理
     */
    public boolean attackEntityFrom(DamageSource damageSource, float i)
    {
        setBeenAttacked();
        if (damageSource.getEntity() != null)
        {
            if(damageSource.getEntity() instanceof EntityPlayer)
            {
            	if(damageSource.getEntity() != this.user)
            	{
	            	if(!worldObj.isRemote)
	            	{
	            		this.shotFinishBonus();
	            		THShotLib.danmakuRemove(this, this.shotSize * 4.0F, "Other", true);
	            	}
	            	return true;
            	}
            }
	        /*if(isReturnableShot())
	        {
	        	Vec3 look = damageSource.getEntity().getLookVec();
	            rotationYaw = -damageSource.getEntity().rotationYaw;
	            rotationPitch = -damageSource.getEntity().rotationPitch;
	            setRotation(rotationYaw, rotationPitch);
	            setVector();
	            motionX += look.xCoord * 2.0D;
	            motionY += look.yCoord * 2.0D;
	            motionZ -= look.zCoord * 2.0D;
	            worldObj.playSoundAtEntity(this, "random.bow", 2.0F, 0.3F);//音を出す
	
	            if (damageSource.getEntity() instanceof EntityLivingBase)
	            {
	                source = (EntityLivingBase)damageSource.getEntity();
	            }
	            return true;
	        }*/
	        return true;
        }
        else
        {
            return false;
        }
    }
    
    
    //================================= 更新系メソッド =====================================//
    
	/**
	 * 削除予定
	 * updateYawAndPitch()に変更
	 * 方向ベクトルをもとに水平方向と垂直方向を変更する
	 */
	@Deprecated
	protected void setYawAndPitch()
	{
		rotationYaw = THShotLib.getYawFromVector(angle.xCoord, angle.zCoord);
		rotationPitch = THShotLib.getPitchFromVector(angle.xCoord, angle.yCoord, angle.zCoord);
		setRotation(rotationYaw, rotationPitch);
	}
	
	/**
	 * 水平角度と垂直角度を更新
	 */
	protected void updateYawAndPitch()
	{
		rotationYaw = THShotLib.getYawFromVector(angle.xCoord, angle.zCoord);
		rotationPitch = THShotLib.getPitchFromVector(angle.xCoord, angle.yCoord, angle.zCoord);
		setRotation(rotationYaw, rotationPitch);
	}
    
	//削除予定。updateGravityLevel()に変更
    @Deprecated
	public void setGravityLevel()
	{
		motionX += gravity.xCoord;
		motionY += gravity.yCoord;
		motionZ += gravity.zCoord;
	}
    
	//重力値を更新する　
	public void updateGravityLevel()
	{
		motionX += gravity.xCoord;
		motionY += gravity.yCoord;
		motionZ += gravity.zCoord;
	}
    
  //================================= 設定系メソッド =====================================//
	

    
	/**
	 * 削除予定。setShotEndTime()に名称変更
	 * @param time 弾の消滅時間
	 */
    @Deprecated
	public void setDeadTime(int time)
	{
		dataWatcher.updateObject(18, Integer.valueOf(time));
	}
	
	/**
	 * 弾の消滅時間を設定
	 * @param time 弾の消滅時間
	 */
	public void setShotEndTime(int time)
	{
		dataWatcher.updateObject(18, Integer.valueOf(time));
	}
	
	/**
	 * 弾のIDを設定
	 * @param id 弾のID。弾の形状ID * 8 + 弾の色IDで求まる値
	 */
	public void setShotId(int id)
	{
		int mask = dataWatcher.getWatchableObjectInt(19) | 0x0;
		dataWatcher.updateObject(19, Integer.valueOf(id));
	}
	
	/**
	 * 弾の形状を設定
	 * @param form : FORM_SMALLなどで指定
	 */
	public void setShotForm(int form)
	{
		setShotId(form * 8 + getShotColor());
	}
	
	/**
	 * 弾の色を設定
	 * @param color : RED、BLUE、GREEN、YELLOW、PURPLE、AQUA、ORANGE、WHITEで指定
	 */
	public void setShotColor(int color)
	{
		setShotId(getShotForm() * 8 + color);
	}

	/**
	 * ショットの大きさを設定（レーザーの場合は太さ）
	 * @param size
	 */
	public void setShotSize(float size)
	{
		dataWatcher.updateObject(20, Integer.valueOf((int)(size * 100F)));
	}
	
	/**
	 * ショットのアニメーションのカウントを設定
	 * @param count
	 */
	public void setAnimationCount(int count)
	{
		dataWatcher.updateObject(17, Integer.valueOf(count + 1000));
	}
	
	/**
	 * Z軸の角度を設定する
	 * @param angle_Z
	 */
	public void setAngleZ(float angle_Z)
	{
		dataWatcher.updateObject(16, Integer.valueOf((int)(angle_Z * 10000F)));
	}
	
//================================= 取得系メソッド =====================================//
    
	/**
     * 削除予定。isShotEndTime()に変更
     * @return 消える直前のフレームならtrue
     */
    @Deprecated
    public boolean isEndTime()
    {
    	return ticksExisted == getShotEndTime();
    }
    
    /**
     * 弾が消える直前のフレームかどうかを返す
     * @return 消える直前のフレームならtrue
     */
    public boolean isShotEndTime()
    {
    	return ticksExisted == getShotEndTime();
    }
    
    /**
     * 未実装メソッド
     * 跳ね返せる弾かどうか
     * @return
     */
	@Deprecated
    protected boolean isReturnableShot()
    {
    	return false;
    }
	
	/**
	 * 重力の影響を受けるかどうか返す
	 * @return 重力の影響を受けるならtrue
	 */
	public boolean hasGravityLevel()
	{
		if(gravity.xCoord == 0.0D && gravity.yCoord == 0.0D && gravity.zCoord == 0.0D)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	
	/**
	 * 削除予定。getShotEndTime()に変更
	 * @return 弾の消滅時間
	 */
	@Deprecated
	public int getDeadTime()
	{
		return dataWatcher.getWatchableObjectInt(18);
	}
	
	
	/**
	 * 弾の消滅時間を返す
	 * @return 弾の消滅時間
	 */
	public int getShotEndTime()
	{
		return dataWatcher.getWatchableObjectInt(18);
	}
	
	/**
	 * 弾のIDを返す
	 * @return 弾のID。弾の形状ID * 8 + 弾の色IDで求まる値
	 */
	public int getShotId()
	{
		return dataWatcher.getWatchableObjectInt(19);
	}
	
	/**
	 * 弾の形状を返す（基準となるその形状の赤色のIDが返る）
	 * @return 弾の形状
	 */
	public int getShotForm()
	{
		return (getShotId() - getShotColor()) / 8;
	}
	
	/**
	 * 弾の色を返す
	 * @return ０～７が返る。THShotLib.REDなどで比較できる
	 */
	public int getShotColor()
	{
		return getShotId() % 8;
	}
	
	/**
	 * ショットの大きさを返す（レーザーの場合は太さ）
	 * @return
	 */
	public float getShotSize()
	{
		return (float)dataWatcher.getWatchableObjectInt(20) / 100F;
	}
	
	/**
	 * Z軸の角度を返す
	 * @return
	 */
	public float getAngleZ()
	{
		return (float)dataWatcher.getWatchableObjectInt(16) / 10000F;
	}
	
	/**
	 * ショットのアニメーションのカウントを取得
	 * @return
	 */
	public int getAnimationCount()
	{
		return dataWatcher.getWatchableObjectInt(17) - 1000;
	}
	
	/**
	 * 他のEntityとの当たり判定の有無を取得
	 * @return trueなら当たり判定あり
	 */
	@Override
    public boolean canBeCollidedWith()
    {
        return getAnimationCount() >= 1;//遅延時間でないなら当たり判定を取る
    }
	
	/**
	 * ？？？
	 */
	@Override
    public float getCollisionBorderSize()
    {
        return getShotSize();
    }
	
	@SideOnly(Side.CLIENT)
    public float getShadowSize()
    {
        return 0.0F;
    }

    public float getBrightness(float f)
    {
        return 1.0F;
    }

	@SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float f)
    {
        return 0xf000f0;
    }
	
}
