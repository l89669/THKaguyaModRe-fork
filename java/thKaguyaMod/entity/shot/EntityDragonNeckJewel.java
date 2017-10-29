package thKaguyaMod.entity.shot;

import static thKaguyaMod.DanmakuConstants.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.LaserData;
import thKaguyaMod.ShotData;
import thKaguyaMod.THShotLib;

/** 龍の頸の玉 着弾地点に雷を落とす */
public class EntityDragonNeckJewel extends EntityTHShot
{
	public float color_r;
	public float color_g;
	public float color_b;

    public EntityDragonNeckJewel(World world)
    {
        super(world);
    }

    public EntityDragonNeckJewel(World world, EntityLivingBase user, Vec3 angle, float cr, float cg, float cb)
    {
    	super(world, user, user, THShotLib.pos_Living(user), angle, 0F, THShotLib.rotate_Default(), 0F, 9999, 0.9D, 0.9D, 0.0D, THShotLib.gravity_Default(), ShotData.shot(0, 0, 1.0F, 7.0F));
    	color_r = cr;
    	color_g = cg;
    	color_b = cb;
    }

    public EntityDragonNeckJewel(World world, EntityLivingBase user, Entity source, Vec3 pos, Vec3 angle, float cr, float cg, float cb)
    {
        super(world, user, source, pos, angle, 0F, THShotLib.rotate_Default(), 0F, 9999, 0.9D, 0.9D, 0.0D, THShotLib.gravity_Default(), ShotData.shot(0, 0, 1.0F, 7.0F));
    	color_r = cr;
    	color_g = cg;
    	color_b = cb;
    }

	//ブロックやEntityに当たったときの処理
	@Override
    protected boolean onImpact(MovingObjectPosition movingObjectPosition)
    {
    	super.onImpact(movingObjectPosition);
    	
    	if( !createLightningBoltDanmaku(movingObjectPosition) )
    	{
    		delete();
    		return true;
    	}
    		
    	return false;
    }
	
	protected boolean createLightningBoltDanmaku( MovingObjectPosition movingObjectPosition )
	{
    	if( movingObjectPosition.entityHit instanceof EntityTHShot )
    	{
    		return true;
    	}
    	
    	int colors[] = {0, 2, 4, 5, 6};
    	float angle = rand.nextFloat() * 360F;
    	for(int i = 0; i < 5; i++)
    	{
    		THShotLib.createLaserA(user, this, THShotLib.pos(posX, posY + 0.4D, posZ), THShotLib.angle(angle, -70F), 0.1D, 0.3D, 0.1D, THShotLib.gravity_Zero(), LaserData.laser(colors[i], 0.15F, 3.0F, 3.0F, 0, 40, 0));
    		angle += 72F;
    	}
    	for(int i = 0; i < 10; i++)
    	{
    		double xVector = rand.nextGaussian() * 0.2D;
    		double yVector = 0.8D + rand.nextDouble() * 0.2D;
    		double zVector = rand.nextGaussian() * 0.2D;

    		ShotData shot = ShotData.shot(FORM_LIGHT, colors[rand.nextInt(5)], 0.3F, 3.0F, 1, 120);
    		THShotLib.createShot(user, this, THShotLib.pos(posX, posY + 0.4D, posZ), THShotLib.angle(xVector, yVector, zVector), 0F, 0.4D, 0.4D, 0.00D, THShotLib.gravity(0.0D, -0.01D, 0.0D), shot);
    	}
		/*EntityLightningBolt lightningBolt = new EntityLightningBolt(worldObj, posX, posY, posZ);

    	if(!worldObj.isRemote)
    	{
    		worldObj.spawnEntityInWorld(lightningBolt);//雷を落とす
    	}*/
    	
    	return false;
	}
	
	@Override
	public boolean blockHitSpecialProcess(MovingObjectPosition movingObjectPosition)
	{	
		return createLightningBoltDanmaku(movingObjectPosition);
	}
	
	/**
	 * 当たり判定のチェックの追加処理
	 * @return 当たり判定を取るならtrue
	 */
	/*@Override
	public boolean hitCheckEx(Entity entity)
	{
		return entity instanceof EntityTHShot || entity instanceof EntityDragonNeckJewel;
	}*/
}
