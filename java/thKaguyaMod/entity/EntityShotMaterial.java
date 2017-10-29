package thKaguyaMod.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.THShotLib;
import thKaguyaMod.init.THKaguyaItems;

public class EntityShotMaterial extends Entity
{
	//弾の素
	
	public short color;//弾の素
	
	protected int lastTime;
	private double lastSpeed;
	
	private double speed;
	protected double limitSpeed;

	
	protected double lastMotionX;
	protected double lastMotionY;
	protected double lastMotionZ;
	
	EntityPlayer target;

    public EntityShotMaterial(World world)
    {
        super(world);
    	setSize(1.2F, 1.2F);
    	limitSpeed = 0.5D;
    }

    public EntityShotMaterial(World world, double posX, double posY, double posZ)
    {
        super(world);
        setSize(1.2F, 1.2F);
    	setLocationAndAngles(	posX + motionX * 0.1D,
    							posY + motionY * 0.1D,
    							posZ + motionZ * 0.1D,
    							rotationYaw, rotationPitch);
    	
    	lastMotionX = motionX;
    	lastMotionY = motionY;
    	lastMotionZ = motionZ;
    	prevPosX = posX;
    	prevPosY = posY;
    	prevPosZ = posZ;
    	lastTime = -1;
    	speed = 0.0D;
    	lastSpeed = 0.0D;
    	limitSpeed = 0.5D;
    	
    	target = null;
    }
    
	//Entity生成時に一度だけ呼ばれる
	@Override
    protected void entityInit()
    {
    }
    
	/**
	 * 押すことができるか返す
	 * @return 押せるならtru
	 */
    @Override
    public boolean canBePushed()
    {
    	return false;
    }
    
	/**
	 * この物体の当たり判定を返す
	 * @return 当たり判定
	 */
    @Override
    public AxisAlignedBB getCollisionBox(Entity entity)
    {
        return null;//entity.boundingBox;
    }

	/**
	 * 当たり判定を設定
	 * @return 当たり判定のボックス
	 */
    @Override
    public AxisAlignedBB getBoundingBox()
    {
        return null;//boundingBox;
    }

    @Override
    public boolean isInRangeToRenderDist(double d)
    {
        double d1 = boundingBox.getAverageEdgeLength() * 4D;
        d1 *= 64D;
        return d < d1 * d1;
    }

	
	/**
	 * 移動速度を返す
	 * @return 移動速度
	 */
	public double getEntitySpeed()
	{
		double xx, yy, zz;
		xx = motionX;
		yy = motionY;
		zz = motionZ;
		return Math.sqrt(xx*xx + yy*yy + zz*zz);
	}
	
	/**
	 * 毎tick行う処理
	 */
	@Override
    public void onUpdate()
    {
	
		if(worldObj.isRemote)
		{
			return;
		}
		
    	//時間が進んでいないなら
    	if(ticksExisted < lastTime)
    	{
    		motionX = 0.0D;
    		motionY = 0.0D;
    		motionZ = 0.0D;
    		lastSpeed = 0.0D;
    		return;
    	}
    	else
    	{
    		super.onUpdate();
    		motionX = lastMotionX;
    		motionY = lastMotionY;
    		motionZ = lastMotionZ;
    		speed = lastSpeed;
    	}
		
        
    	
        //300tickで消滅
        if(ticksExisted > 300)
        {
        	if(!worldObj.isRemote)
        	{
        		setDead();
        	}
        }
        
        //setDivineSpiritCount(getDivineSpiritCount() + 1);
    	
    	//***********Entityの当たり判定を取る****************//
        if(target == null)
        {
	    	MovingObjectPosition movingObjectPosition = new MovingObjectPosition(this);
	    	Entity entity = null;
	    	double effectiveBoundary = 20.0D;//停止空間の有効範囲
	    	double distance = 999.0D;
	    	Entity nearEntity = null;
	    	EntityPlayer nearPlayer = null;
	    	//空間内のEntityを全て取得
	    	List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(effectiveBoundary, effectiveBoundary, effectiveBoundary));
	
	        if (list != null && list.size() > 0)//取得したリストにEntityがいるなら
	        {
	            for (int j1 = 0; j1 < list.size(); j1++)//取得したEntityリストの最初から最後まで見る
	            {
	                entity = (Entity)list.get(j1);//entityに取得したリストのEntityを代入
	            	if (entity != null )//Entityがいるなら
	        		{
	            		movingObjectPosition = new MovingObjectPosition(entity);//MovingObjectPositionにEntityを登録
	        		}
	            	if(entity instanceof EntityPlayer)//プレイヤーなら
	            	{
	            		if(distance > entity.getDistanceToEntity(this))
	            		{
	            			distance = entity.getDistanceToEntity(this);
	            			nearPlayer = (EntityPlayer)entity;
	            		}
	            	}
	            }
	        }
	        
	        if(nearPlayer != null)
	        {
	        	if(check(nearPlayer))
	        	{
	        		target = nearPlayer;
	        	}
	        }
	        else
	        {
	        	if(!worldObj.isRemote)
	        	{
	        		setDead();
	        	}
	        }
        }
        
        if(target != null)
        {
        	moveToPlayer(target);
        }
        
        if(!worldObj.isRemote)
        {
        	setPosition(posX, posY, posZ);
        }
    	
		if(ticksExisted > lastTime)
    	{
    		lastMotionX = motionX;
    		lastMotionY = motionY;
    		lastMotionZ = motionZ;
    		lastSpeed = speed;
    		
    	}
    }
	
	protected boolean check(EntityPlayer player)
	{
		return true;
	}
	
	protected void moveToPlayer(EntityPlayer nearPlayer)
	{
		if(speed < limitSpeed)
    	{
			if(!worldObj.isRemote)
			{
				speed += 0.1D;
			}
    	}
		else
		{
			speed = limitSpeed;
		}
    	
    	//rotationYaw = (float)Math.atan2(nearPlayer.posX - posX, nearPlayer.posZ - posZ) / (float)Math.PI * 180F;
    	rotationPitch = (float)Math.atan2(nearPlayer.posY + nearPlayer.getEyeHeight() - posY, Math.sqrt((nearPlayer.posX - posX) * (nearPlayer.posX - posX) + (nearPlayer.posZ - posZ) * (nearPlayer.posZ - posZ))) / (float)Math.PI * 180F;
    	
        //Vec3 vec = THShotLib.getVecFromAngle(rotationYaw, rotationPitch, speed);
    	Vec3 vec = THShotLib.angle_ToPos(THShotLib.pos_Entity(this), THShotLib.pos_Living(nearPlayer));

        motionX += vec.xCoord * limitSpeed;
        motionY += vec.yCoord * limitSpeed;
        motionZ += vec.zCoord * limitSpeed;
        
        posX += motionX;
        posY += motionY;
        posZ += motionZ;
	}

	//保存するデータの書き込み
	@Override
    public void writeEntityToNBT(NBTTagCompound nbtTagCompound)
    {	
    }

	//保存したデータの読み込み
	@Override
    public void readEntityFromNBT(NBTTagCompound nbtTagCompound)
    {	
    }
    
    /**
     * プレイヤーと接触したときの処理
     */
	@Override
    public void onCollideWithPlayer(EntityPlayer player)
    {
        if (!worldObj.isRemote && player.inventory.addItemStackToInventory(new ItemStack(THKaguyaItems.shot_material)))
        {
            worldObj.playSoundAtEntity(this, "random.pop", 4.0F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 0.3F);
            player.onItemPickup(this, 1 );
        }
        
        if(!worldObj.isRemote)
        {
        	setDead();
        }
    }

	/**
	 * 当たり判定を取るか返す
	 * @return 当たり判定を取るならtrue
	 */
	@Override
    public boolean canBeCollidedWith()
    {
        return true;
    }

	/**
	 * 当たり判定のサイズを返す
	 * @return 当たり判定のサイズ
	 */
	@Override
    public float getCollisionBorderSize()
    {
        return 0.1F;
    }

    public float getShadowSize()
    {
        return 0.0F;
    }

    public float getEntityBrightness(float f)
    {
        return 0.0F;
    }

    public int getEntityBrightnessForRender(float f)
    {
        return 0xf000f0;
    }
}