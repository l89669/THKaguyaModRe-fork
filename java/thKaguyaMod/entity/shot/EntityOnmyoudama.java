package thKaguyaMod.entity.shot;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.THShotLib;
import thKaguyaMod.init.THKaguyaItems;

/** 陰陽玉 */
public class EntityOnmyoudama extends EntityTHShot
{	
	private boolean shootingFlag;
	private boolean userHit;
	private int hitTimer;
	
	//ワールド読み込み時に呼び出されるコンストラクト
    public EntityOnmyoudama(World world)
    {
        super(world);
    }
    
   
	
	public EntityOnmyoudama(World world, EntityLivingBase user, Entity source,
		Vec3 pos, Vec3 angle, float slope, Vec3 rotate, float rotationSpeed, int rotationEnd, 
    	double firstSpeed, double limitSpeed, double acceleration,
    	Vec3 gravity, int color, float size, float damage, int delay, int end, int special)
    {
        super(world, user, source, pos, angle, slope, rotate, rotationSpeed, rotationEnd, firstSpeed, limitSpeed, acceleration,
        		gravity, color, size, damage, delay, end, special);
        shootingFlag = false;
        userHit = false;
        hitTimer = 0;
    }
	
	//ショットが存在する限り呼び出されるメソッド
	@Override
    public void onUpdate()
    {	
		if(shootingFlag)
		{
			shotAcceleration = 0.0D;
		}
		if(user != null)
		{
			if(user instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer)user;
				if(player.isUsingItem() && !shootingFlag)
				{
					Vec3 vec3 = THShotLib.getVecFromAngle( -rotationYaw, -rotationPitch, 0.6D);
					/*double xPos = user.posX - (double)(MathHelper.cos(user.rotationYaw / 180.0F * (float)Math.PI) * 0.32F) * getShotSize() * 2.0D;
					double yPos = user.posY - 0.1D;
					double zPos = user.posZ - (double)(MathHelper.sin(user.rotationYaw / 180.0F * (float)Math.PI) * 0.32F) * getShotSize() * 2.0D;*/
					//prevPosX = posX;
		        	//prevPosY = posY;
		        	//prevPosZ = posZ;
		        	
					setPositionAndRotation(user.posX + vec3.xCoord * 2, THShotLib.getPosYFromEye(user, -0.1D) + vec3.yCoord, user.posZ + vec3.zCoord, -user.rotationYaw, -user.rotationPitch);
					
					
					if(getShotSize() < 3.00F)
					{
						setShotSize(getShotSize() + 0.06F);
					}
					if(player.inventory.getCurrentItem() != null)
					{
						if(player.inventory.getCurrentItem().getItem() != THKaguyaItems.hakurei_miko_stick)
						{
						
							if(!worldObj.isRemote)
							{
								//worldObj.createExplosion(this, this.posX, this.posY, this.posZ, getShotSize(), true);
								setDead();
							}
							return;
						}
					}
					else
					{
						if(!worldObj.isRemote)
						{
							//worldObj.createExplosion(this, this.posX, this.posY, this.posZ, getShotSize(), true);
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
						if(player.inventory.getCurrentItem().getItem() != THKaguyaItems.hakurei_miko_stick)
						{
						
							if(!worldObj.isRemote)
							{
								//worldObj.createExplosion(this, this.posX, this.posY, this.posZ, getShotSize(), true);
								setDead();
							}
							return;
						}
					}
					else
					{
						if(!worldObj.isRemote)
						{
							//worldObj.createExplosion(this, this.posX, this.posY, this.posZ, getShotSize(), true);
							setDead();
						}
						return;
					}
					ticksExisted = 1;
					shotSpeed = 0.5D;
					setVector();
					shotAcceleration = 1.0D;
					setSize(shotSize, shotSize);
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
		
		shotDamage = getShotSize() * 6.0F;

    	super.onUpdate();
    	
		//始点を登録
        Vec3 supVec3d1 = Vec3.createVectorHelper(posX, posY, posZ);
    	//終点を登録
    	Vec3 supVec3d2 = Vec3.createVectorHelper(posX, posY + getShotSize(), posZ);
        //始点と終点からブロックとの衝突を取得
    	//MovingObjectPosition movingObjectPosition = worldObj.rayTraceBlocks_do_do(supVec3d1, supVec3d2, false, true);
    	MovingObjectPosition movingObjectPosition = worldObj.func_147447_a(supVec3d1, supVec3d2, false, true, true);
    	
    	if(movingObjectPosition != null && movingObjectPosition.entityHit == null)
    	{
    		double diveY = movingObjectPosition.blockY - (posY + getShotSize());
    		posY = movingObjectPosition.blockY + getShotSize() + 0.01D + diveY;
    		setPosition(posX, posY, posZ);
    		rotationPitch *= -1.0D;
    		setVector();
    		motionY *= 0.9D;
    	}
    	
		if(!userHit)
		{
			//始点を登録
	        Vec3 vec3d = Vec3.createVectorHelper(posX, posY, posZ);
	    	//終点を登録
	    	Vec3 vec3d2 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
	        //始点と終点からブロックとの衝突を取得
	    	//movingObjectPosition = worldObj.rayTraceBlocks_do_do(vec3d, vec3d2, false, true);
	    	movingObjectPosition = worldObj.rayTraceBlocks(supVec3d1, supVec3d2, true);
			if(!hitUserCheck(movingObjectPosition, vec3d, vec3d2))
			{
				hitTimer++;
				if(hitTimer >= 2)
				{
					userHit = true;
				}
			}
		}
    }
	
	public boolean userHitCheck(Entity entity)
	{
		return userHit;
	}
	
	
	//使用者自身にも当たるかどうか
	@Override
	protected boolean isUserHit()
	{
		return true;
	}
	
	
	@Override
	public boolean entityHitSpecialProcess(Entity hitEntity)
	{
		rotationYaw *= -1.0F;
		rotationPitch *= -1.0F;
		setRotation(rotationYaw, rotationPitch);
		setVector();
		motionX *= 0.8F;
		motionY *= 0.8F;
		motionZ *= 0.8F;
		
		return false;
	}
	

	
    //跳ね返せる弾かどうか
    protected boolean isReturnableShot()
    {
    	return true;
    }
	
	
	//当たり判定の追加
	public boolean hitCheckEx(Entity entity)
	{
		return false;
	}
    	
    //弾の強さ
    public int getShotStrength()
    {
    	return 10;
    }
	
}
