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
import thKaguyaMod.init.THKaguyaBlocks;

/** 小神霊 */
public class EntityDivineSpirit extends Entity
{
	/** 小神霊の色 */
	public short color;
	
	private int lastTime;
	private double lastShotMotionX;
	private double lastShotMotionY;
	private double lastShotMotionZ;
	private float lastRotationYaw;
	private float lastRotationPitch;
	
	private double speed;

    public EntityDivineSpirit(World world)
    {
        super(world);

    	setSize(1.2F, 1.2F);
    }

    public EntityDivineSpirit(World world, double posX, double posY, double posZ, int t)
    {
        super(world);
    	setDivineSpiritColor((short)t);
    	setDivineSpiritCount(0);
    	setLocationAndAngles(	posX + motionX * 0.1D,
    							posY + motionY * 0.1D,
    							posZ + motionZ * 0.1D,
    							rotationYaw, rotationPitch);
    	prevPosX = posX;
    	prevPosY = posY;
    	prevPosZ = posZ;
    	lastTime = 0;
    	speed = 0.0D;
    }
    
	/**
	 * 押すことができるか返す
	 * @return 押せるならtrue
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
	
	//Entity生成時に一度だけ呼ばれる
	@Override
    protected void entityInit()
    {
		dataWatcher.addObject(18, new Integer(0));
    	dataWatcher.addObject(19, new Integer(0));
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
        super.onUpdate();
    	
        //300tickで消滅
        if(getDivineSpiritCount() > 300)
        {
        	if(!worldObj.isRemote)
        	{
        		setDead();
        	}
        }
        
        setDivineSpiritCount(getDivineSpiritCount() + 1);
    	
    	//***********停止空間（時計じゃなくてもっと大きな見えない壁）とEntityの当たり判定を取る****************//
    	MovingObjectPosition movingObjectPosition = new MovingObjectPosition(this);
    	Entity entity = null;
    	double effectiveBoundary = 10.0D;//停止空間の有効範囲
    	double distance = 999.0D;
    	Entity nearEntity = null;
    	EntityPlayer nearPlayer = null;
    	//停止空間内のEntityを全て取得
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
        	if(speed < 0.5D)
        	{
        		speed += 0.03D;
        	}
        	
        	rotationYaw = (float)Math.atan2(nearPlayer.posX - posX, nearPlayer.posZ - posZ) / (float)Math.PI * 180F;
        	rotationPitch = (float)Math.atan2(nearPlayer.posY + nearPlayer.getEyeHeight() - posY, Math.sqrt((nearPlayer.posX - posX) * (nearPlayer.posX - posX) + (nearPlayer.posZ - posZ) * (nearPlayer.posZ - posZ))) / (float)Math.PI * 180F;
        	
        }
        else
        {
        	speed -= 0.03D;
        	if(speed < 0.0D)
        	{
        		speed = 0.0D;
        	}
        }
        
        Vec3 vec = THShotLib.getVecFromAngle(rotationYaw, rotationPitch, speed);
    	posX -= vec.xCoord;
    	posY -= vec.yCoord;
    	posZ += vec.zCoord;
    	setPosition(posX, posY, posZ);
        
        //posX += motionX;
        //posY += motionY;
        //posZ += motionZ;

    	
		if(ticksExisted > lastTime)
    	{
    		lastTime = ticksExisted;
    		lastShotMotionX = motionX;
    		lastShotMotionY = motionY;
    		lastShotMotionZ = motionZ;
    		lastRotationYaw = rotationYaw;
    		lastRotationPitch = rotationPitch;
    		
    	}
    }

	/**
	 * 保存するデータの書き込み
	 * @param nbtTagCompound : NTBタグ
	 */
	@Override
    public void writeEntityToNBT(NBTTagCompound nbtTagCompound)
    {	
    	nbtTagCompound.setShort("color", (short)getDivineSpiritColor());
    }

	/**
	 * 保存したデータの読み込み
	 * @param nbtTagCompound : NBTタグ
	 */
	@Override
    public void readEntityFromNBT(NBTTagCompound nbtTagCompound)
    {	
    	setDivineSpiritColor(nbtTagCompound.getShort("color"));	
    }
    
    /**
     * プレイヤーと接触したときの処理
     */
	@Override
    public void onCollideWithPlayer(EntityPlayer player)
    {
        if (player.inventory.addItemStackToInventory(new ItemStack(THKaguyaBlocks.divine_spirit, 1, getDivineSpiritColor() )))
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
    
	/**
	 * 小神霊のカウントを設定
	 * @param count 設定する小神霊のカウント
	 */
	public void setDivineSpiritCount(int count)
	{
		dataWatcher.updateObject(18, Integer.valueOf(count));
	}
	
	/**
	 * 小神霊のカウントを返す
	 * @return 小心霊のカウント
	 */
	public int getDivineSpiritCount()
	{
		return dataWatcher.getWatchableObjectInt(18);
	}
    
	/**
	 * 小神霊の色を設定
	 * @param color 設定する小心霊の色
	 */
	public void setDivineSpiritColor(int color)
	{
		dataWatcher.updateObject(19, Integer.valueOf(color));
	}
	
	/**
	 * 小神霊の色を返す
	 * @return 小心霊の色
	 */
	public int getDivineSpiritColor()
	{
		return dataWatcher.getWatchableObjectInt(19);
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