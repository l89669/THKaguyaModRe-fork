package thKaguyaMod.entity.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import thKaguyaMod.LaserData;
import thKaguyaMod.THShotLib;
import thKaguyaMod.item.ItemAjaRedStone;

/** エイジャの赤石の光のエフェクト */
public class EntityAjaRedStoneEffect extends Entity
{
	/** 使用者 */
	public EntityLivingBase user;
	/** 取り込んだ光の量 */
	public int lightLevel;
	private double length = 1.2D;

    public EntityAjaRedStoneEffect(World world)
    {
        super(world);
        setSize(2.0F, 0.0F);//サイズを設定　平面上の横と奥行きサイズ、高さ
        yOffset = 0.0F;//高さを設定
    	lightLevel = 0;
    	setLightPower(0);
    }

    public EntityAjaRedStoneEffect(World world, EntityLivingBase entityLiving)
    {
        this(world);

    	user = entityLiving;
    	posX = user.posX - Math.sin((user.rotationYaw) / 180F * 3.141593F) * Math.cos(user.rotationPitch / 180F * 3.141593F) * length;
    	posY = user.posY - Math.sin(user.rotationPitch / 180F * 3.141593F) * length + (double)user.getEyeHeight();
    	posZ = user.posZ + Math.cos((user.rotationYaw) / 180F * 3.141593F) * Math.cos(user.rotationPitch / 180F * 3.141593F) * length;
    	this.setPositionAndRotation(posX, posY, posZ, user.rotationYaw, user.rotationPitch);
    }
    
	/** 生成時に呼ばれる */
    @Override
    protected void entityInit()
    {
    	dataWatcher.addObject(19, new Integer(0));
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
    	//エイジャの赤石の使用者がいないなら
    	if(!worldObj.isRemote && user == null )
    	{
    		//消滅させる
    		setDead();
    		return;
    	}
    	super.onUpdate();

    	//使用者がいるなら
    	if(user != null)
    	{
    		if(user instanceof EntityPlayer)
    		{
    			EntityPlayer userEntity_p = (EntityPlayer)user;
    			if(!userEntity_p.isUsingItem())
    			{
	    			int damage = (int)((double)lightLevel / 40.0);
	    			if(damage > 30)//最大ダメージはハート15個分。頑張ってもそこまで
	    			{
	    				damage = 30;
	    			}

    				if(damage > 0)//ダメージがあるなら
    				{
    					//レーザーを発射する
    					THShotLib.createLaserA(user, this, THShotLib.pos(posX, posY, posZ), THShotLib.angle(user.rotationYaw, user.rotationPitch), 0.1D, 4.0D, 0.3D, THShotLib.gravity_Zero(), 
    							LaserData.laser(THShotLib.RED, (float)damage * 0.01F, (float)damage * 0.3F, damage, 0, 60, ItemAjaRedStone.SPECIAL_AJA_REDSTONE));
    					if(!worldObj.isRemote)//サーバーなら
    					{
    						setDead();//光のエフェクトを消滅させる
    					}
    				}
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

    	//今ある場所の光レベルに合わせて光の量を増やす
    	setLightPower( worldObj.getBlockLightValue((int)posX, (int)posY, (int)posZ));
    	lightLevel += getLightPower();

    	//使用者がいれば目の前に行くようにする
    	if(user != null)
    	{	
    		posX = user.posX - Math.sin((user.rotationYaw) / 180F * 3.141593F) * Math.cos(user.rotationPitch / 180F * 3.141593F) * length;
    		posY = user.posY - Math.sin(user.rotationPitch / 180F * 3.141593F) * length + user.getEyeHeight();
    		posZ = user.posZ + Math.cos((user.rotationYaw) / 180F * 3.141593F) * Math.cos(user.rotationPitch / 180F * 3.141593F) * length;
    		//thKaguyaLib.itemEffectFollowUser(this, userEntity, 0.8D, 0F);
    		//rotationYaw = userEntity.rotationYaw;
    		rotationYaw = user.rotationYawHead;
    		rotationPitch = user.rotationPitch;
    		setPosition(posX, posY, posZ);
    	}

    	if(rotationYaw >  180F)rotationYaw -= 360F;
    	if(rotationYaw < -180F)rotationYaw += 360F;
    	if(rotationPitch >  180F)rotationPitch -= 360F;
    	if(rotationPitch < -180F)rotationPitch += 360F;

    	setRotation(rotationYaw, rotationPitch);
    }

	/**
	 * 取り込んでいる光の量を設定
	 * @param lightPower 設定する光レベル
	 */
	public void setLightPower(int lightPower)
	{
		dataWatcher.updateObject(19, Integer.valueOf(lightPower));
	}

	/**
	 * 取り込んでいる光の量を返す
	 * @return 溜め込んだ光レベル
	 */
	public int getLightPower()
	{
		return dataWatcher.getWatchableObjectInt(19);
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
