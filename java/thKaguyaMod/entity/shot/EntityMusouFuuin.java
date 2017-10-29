package thKaguyaMod.entity.shot;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.THShotLib;

/** 夢想封印の光弾 */
public class EntityMusouFuuin extends EntityTHShot
{	
	/** スペルカードを宣言した相手 */
	public EntityLivingBase target;
	private float maxSize;
	
	/** ワールド読み込み時に呼び出されるコンストラクト */
    public EntityMusouFuuin(World world)
    {
        super(world);
    }

    public EntityMusouFuuin(World world, EntityLivingBase user, Entity source, Vec3 pos, Vec3 angle, float slope,
    		Vec3 rotate, float rotationSpeed, int rotationEnd,
    		double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity, float size, float damage, EntityLivingBase target)
    {
        super(world, user, source, pos, angle, slope, rotate, rotationSpeed, rotationEnd, firstSpeed, limitSpeed, acceleration, gravity, 0, size, damage, 0, 40, 0);
    	maxSize = size;
    	this.setShotSize(0.1F);
        this.target = target;
    }
	
	/**
	 * 弾幕の特殊な動きを記述
	 */
	@Override
	public void specialMotion()
	{
		if(this.getShotSize() < maxSize)
		{
			setShotSize(getShotSize() + 0.1F);
		}
		/*else
		{
			setShotSize(maxSize);
		}*/
		if(ticksExisted < 15)
		{
			motionX *= 0.8D;
			motionY *= 0.8D;
			motionZ *= 0.8D;
		}
		else
		{
			if(target != null)
			{
				if(target.isDead)
				{
					shotLimitSpeed = 0.6D;
					shotAcceleration = 0.03D;
					//shotAddSpeed = 0.2D;
					setVector();
					target = null;
					return;
				}
				shotLimitSpeed = 0.6D;
				//shotSpeed = 0.0D;
				//shotAddSpeed = 0.2D;
				shotAcceleration = 0.03D;
				//double xzDistance = Math.sqrt( (tgEntity.posX - posX) * (tgEntity.posX - posX) + (tgEntity.posZ - posZ) * (tgEntity.posZ - posZ));
				//rotationYaw = (float)Math.atan2( tgEntity.posX - posX, tgEntity.posZ - posZ) / 3.141593F * 180F;
				//rotationPitch = (float)Math.atan2( (tgEntity.posY + 1.0D) - posY, xzDistance) / 3.141593F * 180F;
				Vec3 targetVec = Vec3.createVectorHelper(target.posX - posX, target.posY + target.getEyeHeight() - posY, target.posZ - posZ);
	        	targetVec = THShotLib.getVectorNomalize(targetVec);//単位ベクトルに変換
				Vec3 rotate = THShotLib.getOuterProduct(this.getShotVector(), targetVec);
				float rotateAngle = THShotLib.getVectorAndVectorAngle(this.getShotVector(), targetVec);
				float homingLevel = 12.0F;
				
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
				//angle = THShotLib.angle_ToPos(THShotLib.pos(posX, posY, posZ), THShotLib.pos_Living(target));
				//setVector();
				//setRotation(rotationYaw, rotationPitch);
				updateYawAndPitch();
			}
			else
			{
				shotLimitSpeed = 0.6D;
				shotAcceleration = 0.03D;
			}
		}
	}
}
