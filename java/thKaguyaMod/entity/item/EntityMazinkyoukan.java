package thKaguyaMod.entity.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import thKaguyaMod.THKaguyaLib;

/** 魔人経巻 */
public class EntityMazinkyoukan extends Entity
{
	/** 使用者 */
	public EntityPlayer user;
	public int count;
	public float turnAngle;
	public int befUseCount;

	/** ワールド読み込み時に呼び出されるコンストラクト */
    public EntityMazinkyoukan(World world)
    {
        super(world);
        setSize(2.0F, 2.0F);//サイズを設定　平面上の横と奥行きサイズ、高さ
    }

	/** 出現時に呼び出されるコンストラクト */
    public EntityMazinkyoukan(World world,EntityPlayer player)
    {
        this(world);
    	user = player;
    	prevPosX = player.posX;
        prevPosY = player.posY;
        prevPosZ = player.posZ;
        setPosition(player.posX, player.posY + player.getEyeHeight(), player.posZ);//初期位置を設定(x,y,z)
    	setRotation(player.rotationYaw,  0.0F);
    	count = 0;
    	befUseCount = 0;
    	turnAngle = 0F;
    }

	/** 生成時に一度だけ呼ばれる処理 */
    @Override
    protected void entityInit()
    {
    }

	//当たり判定の有無　falseだと右クリックの選択ですらできない。trueならsetSize()で設定したボックスの当たり判定が出現する
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
    	//使用者がいないなら消す
    	if(!worldObj.isRemote && (user == null || user.isDead) )
    	{
    		setDead();
    		return;
    	}
    	super.onUpdate();
    	
    	//使用者が右クリックをやめたら消す
    	if(user != null)
    	{
    		if(!user.isUsingItem())
    		{
    			int time = count;
    			time /= 3;
    			//if(user.inventory.hasItem(Items.redstone))
    			{
    				if(!worldObj.isRemote)
    				{
    					int removeLevel = (int)((float)time / 8.0F) + 1;
    					
    					if( (user.experienceLevel - removeLevel) < 0 )
    					{
    						time += (user.experienceLevel - removeLevel) * 8F;
    					}
    					user.addExperienceLevel( -removeLevel );
    					user.addPotionEffect(new PotionEffect( 1, time * 10, 4));//スピードアップ
        				user.addPotionEffect(new PotionEffect( 5, time * 10, 0));//攻撃力アップ
        				user.addPotionEffect(new PotionEffect( 8, time * 10, 1));//跳躍力アップ
        				user.addPotionEffect(new PotionEffect(11, time * 10, 0));//防御力アップ
        				//entityPlayer.addPotionEffect(new PotionEffect(12, 15 * 20, 1));//炎耐性
        				//user.inventory.consumeInventoryItem(Items.redstone);
        				
    				}
    			}
    			if(!worldObj.isRemote)
    			{
    				setDead();
    			}
    		}
    		else
    		{
    			THKaguyaLib.itemEffectFollowUser(this, user, 0.5D, 0F);
    			rotationYaw = user.rotationYaw;
    			rotationPitch = 0F;
    		}
    	}

    	count++;
    	if(count > 114)
    	{
    		count = 114;
    	}
    	else if(count > 50)
    	{
    		    turnAngle += 2.88F;
    	}


    	if(rotationYaw >  180F)rotationYaw -= 360F;
    	if(rotationYaw < -180F)rotationYaw += 360F;
    	if(rotationPitch >  180F)rotationPitch -= 360F;
    	if(rotationPitch < -180F)rotationPitch += 360F;
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
