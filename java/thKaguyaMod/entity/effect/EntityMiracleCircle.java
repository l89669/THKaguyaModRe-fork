package thKaguyaMod.entity.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.THShotLib;

/** 風祝のお祓い棒の詠唱時のサークル */
public class EntityMiracleCircle extends Entity
{
	/** 五芒星を描いているプレイヤー */
	public EntityPlayer user;
	private int color;//五芒星の色番号
	public int count;//五芒星を描いている時間
	public float turnAngle;//五芒星の角度
	public int befUseCount;//前に使用していたときのカウント

    public EntityMiracleCircle(World world)
    {
        super(world);
    	setSize(2.0F, 0.0F);
        yOffset = 0.0F;
    }

    public EntityMiracleCircle(World world,EntityPlayer entityPlayer, int cl)
    {
        this(world);

    	user = entityPlayer;
    	Vec3 vec3 = THShotLib.getRotationVectorFromAngle(user.rotationYaw, user.rotationPitch, turnAngle, 3.0D);
    	posX = user.posX + vec3.xCoord;
    	posY = user.posY + vec3.yCoord + (double)user.getEyeHeight();
    	posZ = user.posZ + vec3.zCoord;
        setPosition(posX, posY, posZ);//初期位置を設定(x,y,z)
    	setRotation(entityPlayer.rotationYaw,  0.0F);
    	color = cl;
    	setColor(color);
    	count = 0;
    	befUseCount = 0;
    	turnAngle = 0F;
    }
	
	/** 生成時に一度だけ呼ばれる */
    @Override
    protected void entityInit()
    {
    	dataWatcher.addObject(19, new Integer(0));
    }

	/**
	 * 毎tick行う処理
	 */
	@Override
    public void onUpdate()
    {
    	if(!worldObj.isRemote && user == null)
    	{
    		setDead();
    	}
    	super.onUpdate();
    	if(user != null)
    	{
    		//if(userPlayer.getItemInUseCount() == 0)
    		if(!user.isUsingItem())
    		{
    			if(-befUseCount >= 250 && user.experienceLevel >= 40)//大奇跡「八坂の神風」の使用条件を満たしているなら
    			{
    				/*EntitySanaeWind entitySanaeWind = new EntitySanaeWind(worldObj, user, this, 0.6D, 1);//強力な風になる
    				if(!worldObj.isRemote)
    				{
    					worldObj.spawnEntityInWorld(entitySanaeWind);
    				}*/
    			}
    			if(!worldObj.isRemote)
    			{
    				setDead();
    			}
    		}
    	}
    	count++;
    	if(count == 30 && getColor() < 4)//カウントが50なら
    	{
    		if(user != null)
    		{
	    		EntityMiracleCircle entityMiracle = new EntityMiracleCircle(worldObj, user, getColor() + 1);//次の五芒星を作る
	    		if(!worldObj.isRemote)
	    		{
	    			worldObj.spawnEntityInWorld(entityMiracle);
	    		}
    		}
    	}

    	if(user != null)
    	{
        	prevPosX = posX;
        	prevPosY = posY;
        	prevPosZ = posZ;

    		//posX = user.posX - Math.sin((user.rotationYaw + turnAngle) / 180F * 3.141593F) * Math.cos(user.rotationPitch / 180F * 3.141593F) * 3.0D;
    		//posY = user.posY - Math.sin(user.rotationPitch / 180F * 3.141593F) * Math.cos(turnAngle / 180F * 3.141593F) * 3.0D + user.getEyeHeight();
    		//posZ = user.posZ + Math.cos((user.rotationYaw + turnAngle) / 180F * 3.141593F) * Math.cos(user.rotationPitch / 180F * 3.141593F) * 3.0D;
    		Vec3 vec3 = THShotLib.getRotationVectorFromAngle(user.rotationYaw, user.rotationPitch, turnAngle, 3.0D);
        	/*posX = user.posX - Math.sin((user.rotationYaw + turnAngle) / 180F * 3.141593F) * Math.cos(user.rotationPitch / 180F * 3.141593F) * 3.0D;
        	posY = user.posY - Math.sin(user.rotationPitch / 180F * 3.141593F) * Math.cos(turnAngle / 180F * 3.141593F) * 3.0D + (double)user.getEyeHeight();
        	posZ = user.posZ + Math.cos((user.rotationYaw + turnAngle) / 180F * 3.141593F) * Math.cos(user.rotationPitch / 180F * 3.141593F) * 3.0D;*/
        	posX = user.posX + vec3.xCoord;
        	posY = user.posY + vec3.yCoord + (double)user.getEyeHeight();
        	posZ = user.posZ + vec3.zCoord;
    		rotationYaw = user.rotationYaw;
    		rotationPitch = user.rotationPitch;
    		befUseCount = getColor() * 30 + count;//userPlayer.getItemInUseCount();
    	}
    	setPosition(posX, posY, posZ);

    	if(count > 30)
    	{
    		turnAngle += 2.4F;
    		//turnAngle += 2.88F;
    	}

    	if(rotationYaw >  180F)rotationYaw -= 360F;
    	if(rotationYaw < -180F)rotationYaw += 360F;
    	if(rotationPitch >  180F)rotationPitch -= 360F;
    	if(rotationPitch < -180F)rotationPitch += 360F;

    	setRotation(rotationYaw, rotationPitch);
    }
	
	/**
	 * 五芒星の色を設定
	 * @param color 設定する色
	 */
	public void setColor(int color)
	{
		dataWatcher.updateObject(19, Integer.valueOf(color));
	}

	/**
	 * 五芒星の色を返す
	 * @return 五芒星の色
	 */
	public int getColor()
	{
		return dataWatcher.getWatchableObjectInt(19);
	}

	/**
	 * 現在の五芒星の描いた線の数を返す
	 * @return 描いた線の数
	 */
	public int getNumberOfDrewLine()
	{
		int time = count;
		if(time > 30)
		{
			return 5;
		}
		else
		{
			return time / 6 + 1;
		}
	}

	/**
	 * 今描いている線が何本目か返す
	 * @return 今書いている線が何本目か
	 */
	public int getLastLineTime()
	{
		int time = count;
		if(time > 30)
		{
			return 10;
		}
		else
		{
			return time % 6;
		}
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
