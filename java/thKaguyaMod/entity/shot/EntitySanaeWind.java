package thKaguyaMod.entity.shot;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

/** 早苗の風起こし */
public class EntitySanaeWind extends EntityTHShot
{
	
	public int type;
	//public EntityPlayer playerEntity;
	public int kamikaze;
	
	private double lastPlayerPosX;
	private double lastPlayerPosY;
	private double lastPlayerPosZ;

	/** ワールド読み込み時に呼び出されるコンストラクト */
    public EntitySanaeWind(World world)
    {
        super(world);
    }

    public EntitySanaeWind(World world, EntityLivingBase user, Entity source, 
    		Vec3 pos, Vec3 angle, float slope,
    		Vec3 rotate, float rotationSpeed, int rotationEnd,
    		double firstSpeed, double limitSpeed, double acceleration,  Vec3 gravity,
    		int kazetype, float size, float damage, int delay, int end, int special)
    {
    	super(world, user, source, pos, angle, slope,
    			rotate, rotationSpeed, rotationEnd, firstSpeed, limitSpeed, acceleration,
    	    	gravity, kazetype, size, damage, delay, end, special);

    	lastPlayerPosX = user.posX;
    	lastPlayerPosY = user.posY;
    	lastPlayerPosZ = user.posZ;
    }

    /** 毎tick呼ばれる処理 */
    @Override
    public void onUpdate()
    {
    	if(user != null)
    	{
    		double userMotionX = user.posX - lastPlayerPosX;
    		double userMotionY = user.posY - lastPlayerPosY;
    		double userMotionZ = user.posZ - lastPlayerPosZ;
    		
    		if(getShotColor() == 1)
    		{
    			rotationYaw += 2F;
    			shotSpeed = (double)MathHelper.sqrt_double( userMotionX * userMotionX + userMotionY * userMotionY + userMotionZ * userMotionZ);
    			setVector();
    		}
    		else
    		{
	    		gravity.xCoord = userMotionX * 0.2D;
	    		gravity.yCoord = userMotionY * 0.2D;
	    		gravity.zCoord = userMotionZ * 0.2D;
	    		//setYawAndPitch();
	    		if(gravity.yCoord < 0.0D)
	    		{
	    			gravity.yCoord = 0.0D;
	    		}
	    		if(user.isSneaking())
	    		{
	    			gravity.yCoord = -0.1D;
	    		}
    		}
    	}
    	super.onUpdate();
    	
    	if(user != null)
    	{
    		lastPlayerPosX = user.posX;
    		lastPlayerPosY = user.posY;
    		lastPlayerPosZ = user.posZ;
    	}
    }
    
	//存在する限り呼び出されるメソッド
	/*@Override
    public void onUpdate()
    {
    	if(!worldObj.isRemote && (playerEntity == null || playerEntity.isDead) )
    	{
    		setDead();
    		return;
    	}
    	
        //super.onUpdate();

    	if(ticksExisted == 60 && getKamikazeMode() == 0)//60フレームで自動消滅
    	{
    		if(!worldObj.isRemote)
    		{
    			setDead();
    		}
    	}
    	else if(ticksExisted == 180 && getKamikazeMode() != 3)
    	{
    		if(getKamikazeMode() == 2)
    		{
    			setKamikazeMode(3);
    			ticksExisted = 0;
    		}
    		else
    		{
    			if(!worldObj.isRemote)
    			{
    				setDead();
    			}
    		}
    	}
    	if(playerEntity != null)
    	{
    		if(getKamikazeMode() == 3 && playerEntity.onGround)
    		{
    			if(!worldObj.isRemote)
    			{
    				setDead();
    			}
    		}
    		if(getKamikazeMode() == 2)
    		{
    			playerEntity.fallDistance = 0.0F;
    			playerEntity.motionY = 0.0D;
    		}
    		else if(getKamikazeMode() == 3)
    		{
    			playerEntity.fallDistance = 0.0F;
    			playerEntity.motionY = 0.0D;
    		}
    	}
        
    	//当たり判定===================
    	Vec3 vec3d = worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
    	Vec3 vec3d1 = worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        MovingObjectPosition movingobjectposition = worldObj.rayTraceBlocks_do_do(vec3d, vec3d1, false, true);
    	vec3d = worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
    	vec3d1 = worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        if (movingobjectposition != null)
        {
        	vec3d1 = worldObj.getWorldVec3Pool().getVecFromPool(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
        }
        Entity entity = null;
        List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));
        double d = 0.0D;
        for (int j = 0; j < list.size(); j++)
        {
            Entity entity1 = (Entity)list.get(j);
            if ((!entity1.canBeCollidedWith() && !(entity1 instanceof EntityItem)) || entity1 instanceof EntitySanaeWind)
            {
                continue;
            }
        	float f2 = 0.6F;
            AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(f2, f2, f2);
            MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);
            if (movingobjectposition1 == null)
            {
                continue;
            }
            double d1 = vec3d.distanceTo(movingobjectposition1.hitVec);
            if (d1 < d || d == 0.0D)
            {
                entity = entity1;
                d = d1;
            }
        }

        if (entity != null)
        {
            movingobjectposition = new MovingObjectPosition(entity);
        }
        if (movingobjectposition != null)
        {
            onImpact(movingobjectposition);
        }
    	//=======================================
    
    	
    	/*if(playerEntity != null)
    	{
    		if(ticksExisted > 3)
    		{
    			if(kamikaze != 3)
    			{
    				if(playerEntity.isSneaking())
    				{
    					addVelocity(playerEntity.motionX * 0.5D, -0.05D, playerEntity.motionZ * 0.5D);
    				}
    				else if(playerEntity.motionY > 0.0D)
    				{
    					addVelocity(playerEntity.motionX * 0.5D, playerEntity.motionY, playerEntity.motionZ * 0.5D);
    				}
    				else
    				{
    					addVelocity(playerEntity.motionX * 0.5D, 0.0D, playerEntity.motionZ * 0.5D);
    				}
    			}
    			else
    			{
    				motionX += playerEntity.motionX * 0.3D;
					motionZ += playerEntity.motionZ * 0.3D;
    				motionY -= 0.03D;
    				
    			}
    		}
    	}*/
    	/*
        float f = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
        rotationYaw = (float)((Math.atan2(motionX, motionZ) * 180D) / 3.1415927410125732D);
        for (rotationPitch = (float)((Math.atan2(motionY, f) * 180D) / 3.1415927410125732D); rotationPitch - prevRotationPitch < -180F; prevRotationPitch -= 360F) { }
        for (; rotationPitch - prevRotationPitch >= 180F; prevRotationPitch += 360F) { }
        for (; rotationYaw - prevRotationYaw < -180F; prevRotationYaw -= 360F) { }
        for (; rotationYaw - prevRotationYaw >= 180F; prevRotationYaw += 360F) { }
        rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2F;
        rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;
        float f1 = 0.95F;
        if (isInWater())
        {
            for (int k = 0; k < 4; k++)
            {
                float f3 = 0.25F;
                worldObj.spawnParticle("bubble", posX - motionX * (double)f3, posY - motionY * (double)f3, posZ - motionZ * (double)f3, motionX, motionY, motionZ);
            }

            f1 = 0.8F;
        }

        /*motionX += accelerationX;
        motionY += accelerationY;
        motionZ += accelerationZ;
        motionX *= f1;
        motionY *= f1;
        motionZ *= f1;
    	accelerationX *= 0.4D;
    	accelerationY *= 0.4D;
    	accelerationZ *= 0.4D;
    	posX += motionX;
        posY += motionY;
        posZ += motionZ;
        setPosition(posX, posY, posZ);*/
    	/*if(playerEntity != null)
    	{
    		if(getKamikazeMode() >= 2)
    		{
    			playerEntity.setPosition(posX, posY + playerEntity.yOffset + 1.0D, posZ);
    		}
    	}*/
    	//this.worldObj.playSoundAtEntity(this, "random.bow", 0.3F, 0.8F);//音を出す
    //}


	//ブロックやEntityに衝突したときの処理
    /*@Override
    protected void onImpact(MovingObjectPosition movingobjectposition)
    {
    	Entity entity = movingobjectposition.entityHit;
    	if (playerEntity != null)
        {
            if ( entity != null)
            {
            	if(!(entity instanceof EntityItem) && !(entity instanceof EntityXPOrb) )
            	{
	            	int power = playerEntity.experienceLevel;
	            	if(power > 50)
	            	{
	            		power = 50;
	            	}
	            	switch(getShotColor())
	            	{
	            		case 1:
	            			entity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, playerEntity), 3+power/2);
	            			break;
	            		case 2:
	            			entity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, playerEntity), 5+power);
	            			break;
	            		case 3:
	            			entity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, playerEntity), 3+power/4);
	            			break;
	            		default:
	            			entity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, playerEntity), 1+power/5);
	            			break;
	            	}
            	}
            		//if (!entity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, playerEntity), 3));
            	entity.motionY += 0.2F;
            	entity.motionX += motionX * 0.5F;
            	entity.motionZ += motionZ * 0.5F;
            }
        	else 
        	{
        		if(!worldObj.isRemote)
        		{
        			setDead();
        		}
        	}
        }
    }*/

    /*public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setShort("xTile", (short)xTile);
        nbttagcompound.setShort("yTile", (short)yTile);
        nbttagcompound.setShort("zTile", (short)zTile);
        nbttagcompound.setByte("inTile", (byte)inTile);
        nbttagcompound.setByte("inGround", (byte)(inGround ? 1 : 0));
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        xTile = nbttagcompound.getShort("xTile");
        yTile = nbttagcompound.getShort("yTile");
        zTile = nbttagcompound.getShort("zTile");
        inTile = nbttagcompound.getByte("inTile") & 0xff;
        inGround = nbttagcompound.getByte("inGround") == 1;
    }
	
	public void setKamikazeMode(int mode)
	{
		dataWatcher.updateObject(19, Integer.valueOf(mode));
	}
	
	public int getKamikazeMode()
	{
		return dataWatcher.getWatchableObjectInt(19);
	}

    public boolean canBeCollidedWith()
    {
        return true;
    }

    public float getCollisionBorderSize()
    {
        return 1.0F;
    }

    public float getShadowSize()
    {
        return 2.0F;
    }

    public float getEntityBrightness(float f)
    {
        return 1.0F;
    }

    public int getEntityBrightnessForRender(float f)
    {
        return 0xf000f0;
    	//return 0xff0000;
    }
	
	protected boolean isValidLightLevel()
    {
        return true;
    }*/
	
	public int getWindRenderPattern()
	{
		return ticksExisted % 4;
	}
}
