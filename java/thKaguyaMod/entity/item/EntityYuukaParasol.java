package thKaguyaMod.entity.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import thKaguyaMod.THKaguyaLib;
import thKaguyaMod.init.THKaguyaItems;
import thKaguyaMod.item.ItemYuukaParasol;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/** 幽香の日傘 */
public class EntityYuukaParasol extends Entity
{
	/** 使用者 */
	public EntityPlayer user;
	public int damage;
	public int mode;
	
	/** ワールド読み込み時に呼び出されるコンストラクト */
    public EntityYuukaParasol(World world)
    {
        super(world);
        //preventEntitySpawning = true;
        setSize(0.1F, 0.1F);//サイズを設定　平面上の横と奥行きサイズ、高さ
        ignoreFrustumCheck = true;//中心が画面から外れても描画される
    	mode = getParasolMode();
    }

	/** 出現時に呼び出されるコンストラクト */
    public EntityYuukaParasol(World world,EntityPlayer entityPlayer, int da)
    {
        this(world);
    	setParasolMode(0);
    	ridingEntity = entityPlayer;
    	user = entityPlayer;
    	posX = user.posX - Math.sin((user.rotationYaw - 23F) / 180F * 3.141593F) * Math.cos(user.rotationPitch / 180F * 3.141593F) * 0.5D;
		posY = user.posY - Math.sin(user.rotationPitch / 180F * 3.141593F) * Math.cos(-23F / 180F * 3.141593F) * 0.5D + user.getEyeHeight() + 1.6D;
		posZ = user.posZ + Math.cos((user.rotationYaw - 23F) / 180F * 3.141593F) * Math.cos(user.rotationPitch / 180F * 3.141593F) * 0.5D;
        setPosition(posX, posY, posZ);//初期位置を設定(x,y,z)
    	setRotation(user.rotationYaw,  0.0F);
    	damage = da;
    }
    
	/** 生成時に一度だけ呼ばれる処理 */
    @Override
    protected void entityInit()
    {
    	dataWatcher.addObject(19, new Integer(0));
    }
    
	/*
	*わからんので、翻訳機使用　描画判定のチェックか？
	*エンティティかどうかをチェックするには、遠くに過去を使用し、その平均エッジと比較することで、レンダリングする範囲内にある
	*長さ* 64 * renderDistanceWeightの引数：距離
	*/
	@SideOnly(Side.CLIENT)
	public boolean isInRangeToRenderDist(double par1)
    {
        double d1 = this.boundingBox.getAverageEdgeLength() * 4.0D;
        d1 *= 64.0D;
        return par1 < d1 * d1;
    }
	
	
	/**
	 * 他の物体と衝突したときのその物体の当たり判定？
	 */
    @Override
    public AxisAlignedBB getCollisionBox(Entity entity)
    {
    	if(entity == user)
    	{
    		return null;
    	}
        return entity.boundingBox;
    }

	/**
	 * 当たり判定を設定
	 */
    @Override
    public AxisAlignedBB getBoundingBox()
    {
    	/*switch(getParasolMode())
    	{
    	case 0:
    		return boundingBox;
    	case 2:*/
    		return AxisAlignedBB.getBoundingBox(posX - 1.0D, posY - 1.0D, posZ - 1.0D, posX + 1.0D, posY + 1.0D, posZ + 1.0D);
    	//default:
    		//return boundingBox;
    	//}
    }

	
	/**
	 * 押すことができるかどうか
	 */
    @Override
    public boolean canBePushed()
    {
        return false;
    }
    

	/**
	 * 当たり判定の有無　falseだと右クリックの選択ですらできない。trueならsetSize()で設定したボックスの当たり判定が出現する
	 */
	@Override
    public boolean canBeCollidedWith()
    {
        return true;
    }
	


	/** 毎tick呼ばれる処理 */
	@Override
    public void onUpdate()
    {
    	super.onUpdate();
    	
    	//使用者がいないなら終了処理
    	if(!worldObj.isRemote && (user == null || user.isDead) )
    	{
    		THKaguyaLib.itemEffectFinish(this, user, THKaguyaItems.yuuka_parasol, damage);
    		return;
    	}
    	
    	float posAngle = -23F;//(float)motionY * -92F;
		double distance = 1.0D;
		double yPos = -0.2D;
    	
    	//使用者がいるなら
    	if(user != null)
    	{			

			
			if(getParasolMode() == 0)
			{
				//easyFalling();
				distance = 0.5F;
				rotationPitch = -user.rotationPitch;
			}
			else if(getParasolMode() == 1)
			{
				posAngle = -23F;
				rotationPitch = -user.rotationPitch + 40F;
				yPos = -0.2D;
			}
			else
			{
				posAngle = -23F;
				rotationPitch = -user.rotationPitch - 90F;
				distance = 0.7D;
				yPos = 0.2D;
			}
    		
    		
    		if(getParasolMode() == 0)
    		{
    			//使用者が地面に立っているなら
    			if(user.isSneaking() && ticksExisted > 10)
    			{
    				setParasolMode(1);
    				ticksExisted = 0;
    			}
    			user.fallDistance *= 0.7F;
    		}
    		else if(getParasolMode() == 1)
    		{
    			if(user.isSneaking() && ticksExisted > 10)
    			{
    				setParasolMode(2);
    				ticksExisted = 0;
    			}
    		}
    		else
    		{
    			if(user.isSneaking() && ticksExisted > 10)
    			{
    				THKaguyaLib.itemEffectFinish(this, user, THKaguyaItems.yuuka_parasol, damage);
    			}
    		}
    		rotationYaw = user.rotationYaw;
    		
    		//motionX = //user.posX - user.prevPosX;
    		//motionY = //user.posY - user.prevPosY;
    		//motionZ = //user.posZ - user.prevPosZ;
    		motionX = posX - prevPosX;
    		motionY = posY - prevPosY;
    		motionZ = posZ - prevPosZ;
    		
    		posX = user.posX - Math.sin((user.rotationYaw + posAngle) / 180F * 3.141593F) * Math.cos(user.rotationPitch / 180F * 3.141593F) * distance;
    		posY = user.posY - Math.sin(user.rotationPitch / 180F * 3.141593F) * Math.cos(posAngle / 180F * 3.141593F) * distance + user.getEyeHeight() + 1.6D;
    		posZ = user.posZ + Math.cos((user.rotationYaw + posAngle) / 180F * 3.141593F) * Math.cos(user.rotationPitch / 180F * 3.141593F) * distance;
    		//thKaguyaLib.itemEffectFollowUser(this, user, distance, posAngle, true, yPos + (float)user.getEyeHeight());
			//setPosX(posX);
			//setPosY(posY);
			//setPosZ(posZ);

    		
    		posX += motionX;
    		posY += motionY;
    		posZ += motionZ;
    		
    		setPosition(posX, posY, posZ);
    	}
    	
    	
    	if(!worldObj.isRemote && damage >= ItemYuukaParasol.PARASOL_MAX_DAMAGE)//ダメージが武器の耐久を越したら
    	{
    		setDead();//消滅
    	}

    	setRotation(rotationYaw, rotationPitch);
    }
    
    //乗っているEntityの処理
    //ここでは使用者の処理。使用者のmotionXなどは恐らくここでしかまともに操作できない
    @Override
    public void updateRidden()
	{
		if(ridingEntity != null)
		{
			float posAngle = (float)motionY * -92F;
			double distance = 1.0D;
			double yPos = -0.8D;
			
			if(getParasolMode() == 0)
			{
				easyFalling();
			}
			ridingEntity.motionX *= 0.95D;
			ridingEntity.motionZ *= 0.95D;
			/*else if(getParasolMode() == 1)
			{
				posAngle = 23F;
				rotationPitch = 40F;
				yPos = -0.6D;
			}
			else
			{
				posAngle = 23F;
				rotationPitch = -90F;
				distance = 0.4D;
				yPos = -0.4D;
			}
			//ridingEntity.fallDistance *= 0.7F;
			posX = ridingEntity.posX - Math.sin((ridingEntity.rotationYaw + posAngle) / 180F * (float)Math.PI) * distance;
    		posY = ridingEntity.posY + ridingEntity.getEyeHeight() + yPos;
    		posZ = ridingEntity.posZ + Math.cos((ridingEntity.rotationYaw + posAngle) / 180F * (float)Math.PI) * distance;
			*/
			//setPosition(posX, posY, posZ);
			motionY = ridingEntity.motionY;
			motionX = ridingEntity.motionX;
			motionZ = ridingEntity.motionZ;
			//posX += 0.05D;
			/*setPosition(posX, posY, posZ);
			rotationYaw = ridingEntity.rotationYaw;
			*/
			/*if(ridingEntity.isSneaking())
			{
				if(!worldObj.isRemote)
				{
					finish();
				}
			}*/
			
			//setPosX(ridingEntity.posX);
			//setPosY(ridingEntity.posY);
			//setPosZ(ridingEntity.posZ);
			
		}
		else
		{
			if(!worldObj.isRemote)
			{
				THKaguyaLib.itemEffectFinish(this, user, THKaguyaItems.yuuka_parasol, damage);
			}
		}
	}
	
	protected void easyFalling()
	{
		if(ridingEntity.motionY < 0.0D)
		{
			ridingEntity.motionY *= 0.7D;
		}
	}
	
	//プレイヤーから右クリックされたときの処理
	/*@Override
	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		if(!worldObj.isRemote)
		{
			finish();
			return true;
		}
		return true;
	}*/
	
	//Entityに攻撃されたときに呼び出されるメソッド　破壊とか関係なしに攻撃されれば呼び出される
	/*@Override
	public boolean attackEntityFrom(DamageSource damageSource, float par2)
    {
    	if(!worldObj.isRemote && getParasolMode() == 0 && ticksExisted >= 3)
    	{
    		THKaguyaLib.itemEffectFinish(this, user, THKaguyaItems.yuuka_parasol, damage);
    	}

        return true;
    }*/
    
	/**
	 * 保存するデータの書き込み
	 * @param nbtTagCompound : NTBタグ
	 */
	@Override
    protected void writeEntityToNBT(NBTTagCompound nbtTagCompound)
    {
    	nbtTagCompound.setInteger("damage", damage);
    }

	/**
	 * 保存したデータの読み込み
	 * @param nbtTagCompound : NBTタグ
	 */
	@Override
    protected void readEntityFromNBT(NBTTagCompound nbtTagCompound)
    {
    	damage = nbtTagCompound.getInteger("damage");
    }
    
	/**
	 * 日傘のモードチェンジ
	 * @param mode チェンジするモード
	 */
    public void setParasolMode(int mode)
	{
		dataWatcher.updateObject(19, Integer.valueOf(mode));
	}
	
    /**
     * パラソルのモードを返す
     * @return パラソルのモード
     */
	public int getParasolMode()
	{
		return dataWatcher.getWatchableObjectInt(19);
	}
}
