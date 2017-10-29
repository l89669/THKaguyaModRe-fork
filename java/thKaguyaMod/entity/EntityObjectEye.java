package thKaguyaMod.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityObjectEye extends Entity
{
	//フランドールの棒で出る目

	public EntityPlayer user;
	public Entity target;
	public int count;
	public float turnAngle;
	public int befUseCount;

	//ワールド読み込み時に呼び出されるコンストラクト
    public EntityObjectEye(World world)
    {
        super(world);
        setSize(1.0F, 1.0F);//サイズを設定　平面上の横と奥行きサイズ、高さ
    }

	//出現時に呼び出されるコンストラクト
    public EntityObjectEye(World world,EntityPlayer userEntity, Entity targetEntity)
    {
        this(world);
    	user = userEntity;
    	target = targetEntity;
    	prevPosX = target.posX;
        prevPosY = target.posY;
        prevPosZ = target.posZ;
        setPosition(target.posX, target.posY + target.getEyeHeight(), target.posZ);//初期位置を設定(x,y,z)
    	//setRotation(entityLiving.rotationYaw,  0.0F);
    	count = 0;
    	befUseCount = 0;
    	turnAngle = 0F;
    }

	//生成時に呼ばれる
    protected void entityInit()
    {
    }
	
	/**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking()
    {
        return false;
    }

	//当たり判定の有無　falseだと右クリックの選択ですらできない。trueならsetSize()で設定したボックスの当たり判定が出現する
    public boolean canBeCollidedWith()
    {
        return false;
    }

	//Entityが存在する限り毎フレーム呼び出されるメソッド
	@Override
    public void onUpdate()
    {
    	//使用者かターゲットがいないなら消す
    	if(!worldObj.isRemote && (user == null || user.isDead || target == null || target.isDead) )
    	{
    		setDead();
    		return;
    	}
    	super.onUpdate();
    	
    	//使用者が右クリックをやめたら消す
    	if(user != null)
    	{
    		//if(userPlayer.getItemInUseCount() == 0)
    		if(!user.isUsingItem())
    		{
    			int time = count;
    			time /= 3;
    			/*if( userPlayer.inventory.hasItem(Item.redstone.itemID))
    			{
    				if(!worldObj.isRemote)
    				{
    					userPlayer.addPotionEffect(new PotionEffect( 1, time * 20, 1));//スピードアップ
        				userPlayer.addPotionEffect(new PotionEffect( 5, time * 20, 1));//攻撃力アップ
        				userPlayer.addPotionEffect(new PotionEffect( 8, time * 20, 1));//跳躍力アップ
        				userPlayer.addPotionEffect(new PotionEffect(11, time * 20, 1));//防御力アップ
        				//entityPlayer.addPotionEffect(new PotionEffect(12, 15 * 20, 1));//炎耐性
        				userPlayer.inventory.consumeInventoryItem(Item.redstone.itemID);//レッドストーン消費
    				}
    			}*/
    			setDead();
    		}
    		else
    		{
    			//thKaguyaLib.itemEffectFollowUser(this, user, 0.5D, 0F);
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
	}

	protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
    }

    protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
    }
}
