package thKaguyaMod.entity.item;

import static thKaguyaMod.DanmakuConstants.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import thKaguyaMod.ShotData;
import thKaguyaMod.THKaguyaLib;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.spellcard.THSC_Zenzinrui_no_Hisouten;
import thKaguyaMod.init.THKaguyaConfig;
import thKaguyaMod.init.THKaguyaItems;

/** 緋想の剣 */
public class EntityHisou extends Entity
{
	/** 使用者 */
	public EntityLivingBase userEntity;
	private Entity motherEntity;
	private int count;
	public int num;
	private int damage;
	private int spellCardUsedTime;

    public EntityHisou(World world)
    {
        super(world);
        setSize(4.4F, 4.4F);//サイズを設定　平面上の横と奥行きサイズ、高さ
        yOffset = 0.0F;//-0.8F;//高さを設定
    }

	public EntityHisou(World world,EntityLivingBase entityLivingBase, Entity mother, int n, int da)
    {
        this(world);

    	userEntity = entityLivingBase;//使用者をshootingEntityに保存
    	prevPosX = userEntity.posX;
        prevPosY = userEntity.posY;
        prevPosZ = userEntity.posZ;
        setPosition(userEntity.posX - Math.sin(userEntity.rotationYaw / 180F * (float)Math.PI) * Math.cos(userEntity.rotationPitch / 180F * (float)Math.PI) * 1.2D,
        			userEntity.posY - Math.sin(userEntity.rotationPitch / 180F * (float)Math.PI) * 1.2D +  userEntity.getEyeHeight() - 0.7D,
        			userEntity.posZ + Math.cos(userEntity.rotationYaw / 180F * (float)Math.PI) * Math.cos(userEntity.rotationPitch / 180F * (float)Math.PI) * 1.2D);//初期位置を設定(x,y,z)
    	rotationYaw = userEntity.rotationYaw;
    	ridingEntity = userEntity;
    	motherEntity = mother; //残像に対して本物
    	count = 0;
    	num = n;
    	setNum(n);
    	damage = da;
    	spellCardUsedTime = 0;
    	worldObj.playSoundAtEntity(this, "thkaguyamod:masterspark", THKaguyaConfig.HisoutenVol, 1.0F);
    }

	/** 生成時に一度だけ呼ばれる */
	@Override
    protected void entityInit()
    {
    	dataWatcher.addObject(17, new Integer(0));
    	dataWatcher.addObject(18, new Integer(0));
    }

    /**
     * Returns true if this entity should push and be pushed by other entities when colliding.
     */
	@Override
    public boolean canBePushed()
    {
        return false;
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
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

    	if(!worldObj.isRemote && userEntity == null)
    	{
    		if(num == 8)
    		{
    			THKaguyaLib.itemEffectFinish(this, userEntity, THKaguyaItems.hisou_sword, damage);
    		}
    		else
    		{
    			setDead();
    		}
    		return;
    	}

    	setAngle(getAngle() + ((float)count+(float)getNum()/3.0F) * 39.0F);

    	THKaguyaLib.itemEffectFollowUser(this, userEntity, 1.2D, 0F);
    	if(userEntity != null)//shootingEntityが存在するなら、それをぬるぬる追尾する
    	{
    		/*float angleXZ,angleY, speed;
    		float disXZ;
    		double px, py, pz;
    		px = userEntity.posX - Math.sin(userEntity.rotationYaw / 180F * (float)Math.PI) * Math.cos(userEntity.rotationPitch / 180F * (float)Math.PI)* 1.2D;
    		py = userEntity.posY - Math.sin(userEntity.rotationPitch / 180F * (float)Math.PI) * 1.2D + userEntity.getEyeHeight();
    		pz = userEntity.posZ + Math.cos(userEntity.rotationYaw / 180F * (float)Math.PI) * Math.cos(userEntity.rotationPitch / 180F * (float)Math.PI) * 1.2D;
    		disXZ = (float)Math.sqrt( (px-posX)*(px-posX) + (pz-posZ)*(pz-posZ) );
    		angleXZ = (float)Math.atan2(pz-posZ, px-posX);
    		angleY  = (float)Math.atan2(disXZ, py-posY);
    		speed = (0.25F+((float)num * 0.1F)) * (float)Math.sqrt( (px-posX)*(px-posX) + (py-posY)*(py-posY) + (pz-posZ)*(pz-posZ) );//離れるほど速くなる
    		motionX = userEntity.motionX;
    		motionY = userEntity.motionY;
    		motionZ = userEntity.motionZ;
    		setPosition(px, py, pz);*/
    		rotationYaw = userEntity.rotationYaw;
    		rotationPitch = userEntity.rotationPitch;
    		/*if(getNum() == 8)
    		{
    			shootingEntity.motionX -= (shootingEntity.posX - shootingEntity.prevPosX)*0.6D;
    			shootingEntity.motionZ -= (shootingEntity.posZ - shootingEntity.prevPosZ)*0.6D;
    			shootingEntity.rotationYawHead = shootingEntity.prevRotationYawHead + (shootingEntity.rotationYawHead - shootingEntity.prevRotationYawHead)*0.2F;
    			shootingEntity.rotationYaw = shootingEntity.prevRotationYaw + (shootingEntity.rotationYaw - shootingEntity.prevRotationYaw)*0.2F;
    		}*/
    	}
    	//else//使用者がいないならその場でアイテム化
    	else{
    		if(getNum() == 8)
    		{
				THKaguyaLib.itemEffectFinish(this, userEntity, THKaguyaItems.hisou_sword, damage);
    		}
    		if(!worldObj.isRemote)
    		{
    			setDead();
    		}
    	}

	//****気質弾の発射****//
	if(ticksExisted > spellCardUsedTime && userEntity != null && getNum() == 8)
	{
		/*double vecX, vecY, vecZ, dx, dy, dz;
		float angleXZ, angleY;

		dx = posX - userEntity.posX;
		dy = posY - (userEntity.posY + userEntity.getEyeHeight());
		dz = posZ - userEntity.posZ;

		angleXZ  = (float)Math.atan2(dz, dx);
		angleY = (float)Math.atan2( dy, Math.sqrt(dx * dx + dz * dz));

		EntityTHShot entityTHShot;
		for(int i = 0; i < 12; i++)
		{
			angleXZ = (userEntity.rotationYaw + rand.nextFloat() * 30F - 15F)/180F * (float)Math.PI;
			angleY = (userEntity.rotationPitch + rand.nextFloat() * 30F - 15F)/180F * (float)Math.PI;
			vecX = -Math.sin(angleXZ) * Math.cos(angleY);//X方向　水平方向
			vecY = -Math.sin(angleY);//Y方向　上下
			vecZ =  Math.cos(angleXZ) * Math.cos(angleY);//Z方向　水平方向
			entityTHShot = new EntityTHShot(worldObj, userEntity, this,
				this.posX, this.posY, this.posZ,
				vecX, vecY, vecZ, 0F, 0.0D, 1.0D, 0.0D,
				0.1D, 0.8D, 0.05D,
				0.0D, 0.0D, 0.0D, 6, THShotLib.KISHITU[THShotLib.RED], 0.5F, 60, 3, THShotLib.KISHITU01);
			if(!worldObj.isRemote)
			{
				worldObj.spawnEntityInWorld(entityTHShot);
			}
		}*/
		ShotData shot = ShotData.shot(FORM_KISHITU, RED, 0.5F, 6.0F, 3, 60, THSC_Zenzinrui_no_Hisouten.SPECIAL_HISOUTEN01);
		THShotLib.createRandomRingShot(userEntity, this, THShotLib.pos_Entity(this), userEntity.getLookVec(), 0F, 9999, 0.1D, 0.8D, 0.05D, THShotLib.gravity_Zero(), shot, num, 0.1D, 30F);
	}
	//********//

    if(ticksExisted > spellCardUsedTime)
    {
    	spellCardUsedTime = ticksExisted;
    }

		//時間で消滅
	if(ticksExisted >= 100)
	{
		if(getNum() == 8)
		{
			THKaguyaLib.itemEffectFinish(this, userEntity, THKaguyaItems.hisou_sword, damage);
		}
    	if(!worldObj.isRemote)
    	{
			setDead();
    	}
	}
}

    //乗っているEntityの処理
    //ここでは使用者の処理。使用者のmotionXなどは恐らくここでしかまともに操作できない
    @Override
    public void updateRidden()
	{
		if(ridingEntity != null && getNum() == 8)
		{
			ridingEntity.rotationYaw = ridingEntity.prevRotationYaw + (ridingEntity.rotationYaw - ridingEntity.prevRotationYaw) * 0.3F;
			ridingEntity.rotationPitch = ridingEntity.prevRotationPitch + (ridingEntity.rotationPitch - ridingEntity.prevRotationPitch) * 0.3F;
			ridingEntity.motionX *= 0.1D;
			ridingEntity.motionY *= 0.1D;
			ridingEntity.motionZ *= 0.1D;
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
    	nbtTagCompound.setShort("damage", (short)damage);
    	nbtTagCompound.setByte("number", (byte)num);
    }

    /**
	 * 保存したデータの読み込み
	 * @param nbtTagCompound : NBTタグ
	 */
    @Override
    protected void readEntityFromNBT(NBTTagCompound nbtTagCompound)
    {
    	count = nbtTagCompound.getShort("count");
    	damage = nbtTagCompound.getShort("damage");
    	num = (int)nbtTagCompound.getByte("number");
    }

    public float getShadowSize()
    {
        return 0.5F;
    }

	public void setNum(int num)
	{
		dataWatcher.updateObject(17, Integer.valueOf(num));
	}

	public int getNum()
	{
		return dataWatcher.getWatchableObjectInt(17);
	}

	public void setAngle(float angle)
	{
		dataWatcher.updateObject(18, Integer.valueOf((int)(angle * 1000F)));
	}

	public float getAngle()
	{
		return (float)dataWatcher.getWatchableObjectInt(18) / 1000F;
	}

    protected boolean isValidLightLevel()
    {
        return true;
    }

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness(float par1)
    {
        return 1.0F;
    }
}
