package thKaguyaMod.entity.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/** スペルカード使用時の魔法陣のエフェクト */
public class EntitySpellCardCircle extends Entity
{
	/** 使用者 */
	public EntityLivingBase user;
	private int type;
	private int count;
	private int endTime;
	protected int lastTime;

    public EntitySpellCardCircle(World world)
    {
        super(world);
        ignoreFrustumCheck = true;//中心が画面から外れても描画される
        setSize(0.5F, 0.5F);//サイズを設定　平面上の横と奥行きサイズ、高さ
        lastTime = 0;
        setAnimationCount(0);
    }

    public EntitySpellCardCircle(World world, EntityLivingBase entityLiving, int type, int end)
    {
        this(world);

    	user = entityLiving;
    	setCircleType(type);
    	count = 0;
    	setEndTime(end);
    	/*if(endTime <= 0)
    	{
    		endTime = 1;
    	}*/
    	posX = user.posX;
    	if(entityLiving instanceof EntityPlayer)
    	{
    		posY = user.posY + 0.1D;
    	}
    	else
    	{
    		posY = user.posY + user.height / 2.0D;
    	}
    	posZ = user.posZ;
    	this.setPositionAndRotation(posX, posY, posZ, user.rotationYaw, user.rotationPitch);
    }
    
	/**
	 * 生成時に一度だけ呼ばれる
	 */
    @Override
    protected void entityInit()
    {
    	dataWatcher.addObject(19, new Float(0));
    	dataWatcher.addObject(20, new Integer(0));
    	dataWatcher.addObject(21, new Integer(0));
    	dataWatcher.addObject(22, new Integer(0));
    }

	/**
	 * 当たり判定を取るか返す
	 * falseだと右クリックの選択ですらできない。trueならsetSize()で設定したボックスの当たり判定が出現する
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
    	//スペルカードの使用者がいないなら
    	if(!worldObj.isRemote && user == null )
    	{
    		//消滅させる
    		setDead();
    		return;
    	}
    	super.onUpdate();
    	
    	if(ticksExisted <= lastTime)
    	{
    		return;//処理を終了させる
    		
    	}
    	else
    	{
    		if(!worldObj.isRemote)
    		{
    			setAnimationCount(lastTime);
    		}
    	}
    	
    	if(getEndTime() < ticksExisted && getEndTime() >= 0)
    	{
    		if(!worldObj.isRemote)
    		{
    			setDead();
    		}
    	}
    	
    	

    	//使用者がいるなら
    	if(user != null)
    	{
    	}

    	//endTime = 120;
    	if(getAnimationCount() < 5)
    	{
    		setCircleSize(((float)getAnimationCount()) / 5.0F);
    	}
    	else
    	{
    		float endTime2 = (float)getEndTime();
    		if(getCircleType() < 16)
    		{
    			setCircleSize((endTime2 - (float)getAnimationCount()) / endTime2);
    		}
    	}

    	/*if(getCircleType() < 8)
    	{
    		//count++;
    	}
    	else
    	{
    		if(getAnimationCount() >= 5)
    		{
    			setAnimationCount(5);
    		}
    	}*/

    	//使用者がいれば目の前に行くようにする
    	if(user != null)
    	{	
    		posX = user.posX;
        	if(user instanceof EntityPlayer)
        	{
        		posY = user.posY + 0.1D;
        	}
        	else
        	{
        		posY = user.posY + user.height / 2.0D;
        	}
    		posZ = user.posZ;
    		rotationYaw = user.rotationYawHead;
    		rotationPitch = user.rotationPitch;
    		setPosition(posX, posY, posZ);
    	}

    	if(rotationYaw >  180F)rotationYaw -= 360F;
    	if(rotationYaw < -180F)rotationYaw += 360F;
    	if(rotationPitch >  180F)rotationPitch -= 360F;
    	if(rotationPitch < -180F)rotationPitch += 360F;

    	setRotation(rotationYaw, rotationPitch);
    	
    	if(ticksExisted > lastTime)
    	{
    		//最後に時間が動いていたときの時間と移動量を保存する
    		lastTime = ticksExisted;
    	}
    }

	/**
	 * 魔法陣の大きさを設定する
	 * @param size 魔法陣の大きさ
	 */
	public void setCircleSize(float size)
	{
		dataWatcher.updateObject(19, Float.valueOf(size));
	}

	/**
	 * 魔法陣の大きさを返す
	 * @return 魔法陣の大きさ
	 */
	public float getCircleSize()
	{
		return dataWatcher.getWatchableObjectFloat(19);
	}
	
	/**
	 * 魔法陣の見た目を設定する
	 * @param type 魔法陣の見た目
	 */
	public void setCircleType(int type)
	{
		dataWatcher.updateObject(20, Integer.valueOf(type));
	}

	/**
	 * 魔法陣の見た目を返す
	 * @return 魔法陣の見た目
	 */
	public int getCircleType()
	{
		return dataWatcher.getWatchableObjectInt(20);
	}
	
	/**
	 * 魔法陣の見た目を設定する
	 * @param type 魔法陣の見た目
	 */
	public void setEndTime(int time)
	{
		dataWatcher.updateObject(21, Integer.valueOf(time));
	}

	/**
	 * 魔法陣の見た目を返す
	 * @return 魔法陣の見た目
	 */
	public int getEndTime()
	{
		return dataWatcher.getWatchableObjectInt(21);
	}
	
	/**
	 * 魔法陣のアニメーションのカウントを設定する
	 * @param type 魔法陣の見た目
	 */
	public void setAnimationCount(int time)
	{
		dataWatcher.updateObject(22, Integer.valueOf(time));
	}

	/**
	 * 魔法陣のアニメーションのカウントを返す
	 * @return 魔法陣の見た目
	 */
	public int getAnimationCount()
	{
		return dataWatcher.getWatchableObjectInt(22);
	}

	/**
	 * 保存するデータの書き込み
	 * @param nbtTagCompound : NTBタグ
	 */
	@Override
	protected void writeEntityToNBT(NBTTagCompound nbtTagCompound)
    {
    }

	/**
	 * 保存したデータの読み込み
	 * @param nbtTagCompound : NBTタグ
	 */
	@Override
    protected void readEntityFromNBT(NBTTagCompound nbtTagCompound)
    {
    }
}