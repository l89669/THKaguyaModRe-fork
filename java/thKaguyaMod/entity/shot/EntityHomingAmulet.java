package thKaguyaMod.entity.shot;

import static thKaguyaMod.DanmakuConstants.*;
import static thKaguyaMod.THShotLib.*;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.ShotData;
import thKaguyaMod.THShotLib;

/** ホーミングアミュレット */
public class EntityHomingAmulet extends EntityTHShot
{	
	private String userPlayerName;

	//ワールド読み込み時に呼び出されるコンストラクト
    public EntityHomingAmulet(World world)
    {
        super(world);
    }

    /*public EntityHomingAmulet(World world, EntityLivingBase user, Vec3 angle,
    		double speed, float power, int color, float size)
    {
        super(world, user, angle, speed, power, color, size);
    }*/
    
    public EntityHomingAmulet(World world, EntityLivingBase user, Entity source,
    		Vec3 pos, Vec3 angle, float slope, Vec3 rotate, float rotationSpeed, int rotationEnd,
        	double firstSpeed, double limitSpeed, double acceleration,
        	Vec3 gravity, int color, float size, float damage, int delay, int end, int special)
    {
    	super(world, user, source, pos, angle, slope, rotate, rotationSpeed, rotationEnd, firstSpeed, limitSpeed, acceleration, gravity, color, size, damage, delay, end, special);
    	
    	if(user instanceof EntityTameable)
    	{
    		EntityTameable tameable = (EntityTameable)user;
    		this.setOwner(tameable.getOwner().getCommandSenderName());
    	}
    	else
    	{
    		this.setOwner("");
    	}
    }
    
	//Entity生成時に一度だけ呼ばれる
    @Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(21, "");
	}

	/**
	 * 弾幕の特殊な動きを記述
	 */
	@Override
	public void specialMotion()
	{	
		//大きな拡散アミュレットで、１６tick目なら
		if(ticksExisted == 16 && this.getShotColor() == 3)
		{
			EntityHomingAmulet amulet;
			double vectorX, vectorY, vectorZ;
			//float angle = rand.nextFloat() * 360F;
			double vectorXG = 0.0D, vectorYG = 0.0D, vectorZG = 0.0D;
			if(user != null)
			{
				vectorXG = -Math.sin(user.rotationYaw / 180F * 3.141593F) * Math.cos(user.rotationPitch / 180F * 3.141593F) * 0.09D;
				vectorYG = -Math.sin(user.rotationPitch / 180F * 3.141593F) * 0.09D;
				vectorZG =  Math.cos(user.rotationYaw / 180F * 3.141593F) * Math.cos(user.rotationPitch / 180F * 3.141593F) * 0.09D;
				ShotData shot = ShotData.shot(FORM_AMULET, BLUE, 0.4F, 5.0F, 0, 60);
				THShotLib.createSphereShot(user, user, THShotLib.pos_Entity(this), angle, 0F, rotate_Default(), 0F, 9999, 0.6D, 0.6D, 0.0D, THShotLib.gravity(vectorXG, vectorYG, vectorZG), shot, 32, 0.0D, rand.nextFloat() * 360F);
			}
			
			if(!worldObj.isRemote)
			{
				setDead();
			}
			return;
		}
		//小さな拡散アミュレットなら特殊な処理はしない
		if(getShotColor() >= 2)
		{
			return;
		}
		
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
			float homingLevel = 4.0F;
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
				this.updateMotion();
				this.updateAngle();
			}
		}
	}
	
    public String getOwnerName()
    {
        return this.dataWatcher.getWatchableObjectString(21);
    }

    public void setOwner(String str)
    {
        this.dataWatcher.updateObject(21, str);
    }
}
