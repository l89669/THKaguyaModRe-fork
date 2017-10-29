package thKaguyaMod.entity.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.LaserData;
import thKaguyaMod.THKaguyaLib;
import thKaguyaMod.THShotLib;
import thKaguyaMod.init.THKaguyaConfig;
import thKaguyaMod.init.THKaguyaItems;

/** ミニ八卦炉 */
public class EntityMiniHakkero extends Entity
{
	/** 使用者 */
	public EntityLivingBase user;
	private EntityLivingBase tgEntity;
	public int count;
	private float circleAngle;
	private int moveTexture;
	public int num;
	private int damage;
	private int lastTime;
	private boolean isSpellCard;

    public EntityMiniHakkero(World world)
    {
        super(world);
        preventEntitySpawning = true;
        setSize(0.4F, 0.4F);//サイズを設定　平面上の横と奥行きサイズ、高さ
        yOffset = 0.0F;//高さを設定
    }
	
    public EntityMiniHakkero(World world,EntityLivingBase EntityLivingBase, int da)
    {
        this(world);

    	user = EntityLivingBase;//使用者をuserに保存
        setPosition(user.posX - Math.sin(user.rotationYaw / 180F * 3.141593F) * Math.cos(user.rotationPitch / 180F * 3.141593F),
        			user.posY - Math.sin(user.rotationPitch / 180F * 3.141593F) + (double)user.getEyeHeight() - 0.10000000149011612D ,
					user.posZ + Math.cos(user.rotationYaw / 180F * 3.141593F) * Math.cos(user.rotationPitch / 180F * 3.141593F));//初期位置を設定(x,y,z)
    	rotationYaw = user.rotationYaw;
    	rotationPitch = user.rotationPitch;
    	tgEntity = null;
    	count = 0;
    	circleAngle = 0F;
    	moveTexture = 0;
    	damage = da;
    	isSpellCard = false;
    	
    	//worldObj.playSoundAtEntity(this, "thkaguyamod:sound.masterspark", THKaguyaConfig.MasterSparkVol, 1.0F);
    	worldObj.playSoundAtEntity(this, "thkaguyamod:masterspark", THKaguyaConfig.MasterSparkVol, 1.0F);
    	lastTime = 0;
    	
    	int special = THShotLib.EXPLOSION01;
    	Vec3 look = THShotLib.getVecFromAngle(rotationYaw, rotationPitch);
    	THShotLib.createLaserB(user, this, THShotLib.pos_Entity(this), look,
    			THShotLib.rotate_Default(), 0.0F, 9999, LaserData.laser(24, 4.2F, 40.0F, 8.0F, 30, 120, special), this, 1.0D, 0.0F);
    }
	
    public EntityMiniHakkero(World world,EntityLivingBase EntityLivingBase, EntityLivingBase target)
    {
        this(world);

    	user = EntityLivingBase;//使用者をuserに保存
        setPosition(user.posX - Math.sin(user.rotationYaw / 180F * 3.141593F) * Math.cos(user.rotationPitch / 180F * 3.141593F),
        			user.posY - Math.sin(user.rotationPitch / 180F * 3.141593F) + (double)user.getEyeHeight() - 0.10000000149011612D ,
					user.posZ + Math.cos(user.rotationYaw / 180F * 3.141593F) * Math.cos(user.rotationPitch / 180F * 3.141593F));//初期位置を設定(x,y,z)
    	rotationYaw = user.rotationYaw;
    	rotationPitch = user.rotationPitch;
    	tgEntity = target;
    	count = 0;
    	circleAngle = 0F;
    	moveTexture = 0;
    	damage = 1;
    	worldObj.playSoundAtEntity(this, "thkaguyamod:masterspark", THKaguyaConfig.MasterSparkVol, 1.0F);
    	lastTime = 0;
    	
    	isSpellCard = true;
    	
    	Vec3 look = THShotLib.getVecFromAngle(rotationYaw, rotationPitch);
    	THShotLib.createLaserB(user, this, THShotLib.pos_Entity(this), look,
    			THShotLib.rotate_Default(), 0.0F, 9999, LaserData.laser(24, 4.2F, 60.0F, 8.0F, 30, 120, 0), this, 1.0D, 0.0F);
    }

	/** 生成時に一度だけ呼ばれる処理 */
    @Override
    protected void entityInit()
    {
    }

    /**
     * 押すことができるか？
     */
    @Override
    public boolean canBePushed()
    {
        return false;
    }

    //他のEntityと当たり判定を取るか
    @Override
    public boolean canBeCollidedWith()
    {
    	return true;
    }
	
	//毎tick呼ばれる処理 */
	@Override
    public void onUpdate()
    {
        super.onUpdate();
    	if(!worldObj.isRemote && user == null)
    	{
    		if(!isSpellCard)
    		{
    			THKaguyaLib.itemEffectFinish(this, user, THKaguyaItems.mini_hakkero);
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
    	
    	if(ticksExisted <= lastTime)
    	{
    		return;
    	}

    	circleAngle += 4.7F;

    
/*	    double length = 0.0D;
	    double speed = 0.0D;
	    Vec3 vec3d;
	    Vec3 vec3d1;
	    MovingObjectPosition movingObjectPosition;
	    float yawRad = rotationYaw / 180F * 3.141593F;
	    float pitchRad = rotationPitch / 180F * 3.141593F;
	    double sinYaw = Math.sin(yawRad);
	    double cosYaw = Math.cos(yawRad);
	    double sinPitch = Math.sin(pitchRad);
	    double cosPitch = Math.cos(pitchRad);
	    double ax, ay, az, dx, dy, dz, px, py, pz;
	    
	    if(user != null)
	    {
	    	do
	    	{	
	    		
	    		vec3d = worldObj.getWorldVec3Pool().getVecFromPool(posX - sinYaw * cosPitch * length, posY - sinPitch * length, posZ + cosYaw * cosPitch * length);
	    		if(speed < 1.6D)
	    		{
	    			speed += 0.2D;
	    		}
	    		else
	    		{
	    			speed = 1.6D;
	    		}
	    		length += speed;
	    		ax = -sinYaw * cosPitch * length;
	    		ay = -sinPitch * length;
	    		az =  cosYaw * cosPitch * length;
	    		px = posX + ax;
	    		py = posY + ay;
	    		pz = posZ + az;
	    		vec3d1 = worldObj.getWorldVec3Pool().getVecFromPool(px, py, pz);
	    		//movingObjectPosition = worldObj.rayTraceBlocks_do_do(vec3d, vec3d1, false, true);
	    		movingObjectPosition = worldObj.func_147447_a(vec3d, vec3d1, false, true, true);
				float angleXZ, angleY;

				dx = posX - user.posX;
				dy = posY - user.posY;
				dz = posZ - user.posZ;

				angleY  = (float)Math.atan2(dx, dz);
				angleXZ = (float)Math.atan2( Math.sqrt(dx*dx + dz*dz), dy);
			}while(movingObjectPosition == null && length < 30.0D);
	    	
	    	ax = -sinYaw * cosPitch;
	    	ay = -sinPitch;
	    	az =  cosYaw * cosPitch;

	    	EntityMasterSpark entityMasterSpark;
			entityMasterSpark = new EntityMasterSpark(worldObj, user, this, posX, posY, posZ, ax, ay, az, 0.3F, length, ticksExisted);

			if(!worldObj.isRemote)
			{
				worldObj.spawnEntityInWorld(entityMasterSpark);
    		}
	    	if(ticksExisted > 30)//30ticks以上ならマスタースパークの当たり判定を出現させる
	    	{	
	    		double shotSize = 2.0D;
	        	List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(-sinYaw * cosPitch * length, -sinPitch * length, cosYaw * cosPitch * length).expand(shotSize , shotSize , 0.1D ));//指定範囲内のEntityをリストに登録
	    		Entity entity = null;
	    		double d = 0.0D;
	    		vec3d = worldObj.getWorldVec3Pool().getVecFromPool(posX - sinYaw * cosPitch, posY - sinPitch, posZ + cosYaw * cosPitch);
	    		vec3d1 = worldObj.getWorldVec3Pool().getVecFromPool(posX - sinYaw * cosPitch * length, posY - sinPitch * length, posZ + cosYaw * cosPitch * length);
	    		
	    		for (int j = 0; j < list.size(); j++)
	        	{
	            	Entity entity1 = (Entity)list.get(j);//entity1にリストの先端のentityを保存
	        		//entity1に当たり判定があり、ミニ八卦炉ではなく、使用者でもなく、弾幕でもなければ
	            	if ( entity1.canBeCollidedWith() && entity1 instanceof EntityMiniHakkero == false && !entity1.isEntityEqual(user) && entity1 instanceof EntityTHShot == false &&
	            			hitCheckEx(entity1))
	            	{
	            		AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(shotSize, shotSize, shotSize);
	            		MovingObjectPosition movingObjectPosition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);
	            		if (movingObjectPosition1 != null)
	            		{
	            			movingObjectPosition = new MovingObjectPosition(entity1);
	        	        	if (movingObjectPosition != null && movingObjectPosition.entityHit != null)//Entityに当たっているなら
	        	        	{
	        	        		if(tgEntity != null)
	        	    			{
	        	    				if (!movingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, user), 8));
	        	    			}
	        	        		else
	        	        		{
	        	            		if(!worldObj.isRemote)
	        	    				{
	        	    					//威力5.0の強力な爆発を起こす。ブロックを破壊するかはコンフィグで設定（デフォは破壊する）
	        	    					worldObj.createExplosion(user, movingObjectPosition.entityHit.posX, movingObjectPosition.entityHit.posY, movingObjectPosition.entityHit.posZ, getExplosionLevel() * 0.1F, tgEntity == null && THKaguyaConfig.MasterSparkDestroysBlocks);
	        	    				}
	        	        		}
	        	        	}
	            		}
	            	}
	        	}
	        	if( movingObjectPosition != null && tgEntity == null)//ブロックに当たっているなら
	    		{
	    			if(!worldObj.isRemote)
	    			{
	    				//同上
	    				worldObj.createExplosion(user, movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord, getExplosionLevel(), tgEntity == null && THKaguyaConfig.MasterSparkDestroysBlocks);
	    			}
	    		}
	    	}
    	}
*/
    	if(ticksExisted > lastTime)
    	{
    		lastTime = ticksExisted;
    	}
		//時間で消滅
		if(ticksExisted >= 109)
		{
			if(!worldObj.isRemote)
			{
				if(!isSpellCard)
				{
					THKaguyaLib.itemEffectFinish(this, user, THKaguyaItems.mini_hakkero);
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
	}
	
	public boolean hitCheckEx(Entity entity)
	{
		if(tgEntity == null)
		{
			return true;
		}
		else
		{
			return entity instanceof EntityDragonPart || entity instanceof EntityLivingBase && !(entity instanceof EntityAnimal) && !(entity instanceof EntityVillager);
		}
	}
	
	/**
	 * マスタースパークの爆発の威力を返す
	 * @return 爆発の威力
	 */
	public float getExplosionLevel()
	{
		return 5.0F;
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
    	nbtTagCompound.setBoolean("isSpellCard", tgEntity != null);
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
    	isSpellCard = nbtTagCompound.getBoolean("isSpellCard");
    }

    public float getShadowSize()
    {
        return 0.5F;
    }

    protected boolean isValidLightLevel()
    {
        return true;
    }

    public int getBrightnessForRender(float par1)
    {
        return 0xf000f0;
    }

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness(float par1)
    {
        return 0.5F;
    }

	public float getCircleAngle()
	{
		return circleAngle;
	}
}
