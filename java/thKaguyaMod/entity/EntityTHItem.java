package thKaguyaMod.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.THShotLib;
import thKaguyaMod.init.THKaguyaItems;

public class EntityTHItem extends EntityShotMaterial
{
	//弾の素
	
	public static final byte ITEM_ID_SHOTMATERIAL	=  0;
	public static final byte ITEM_ID_SMALLPOWER	=  1;
	public static final byte ITEM_ID_BIGPOWER		=  2;
	public static final byte ITEM_ID_POINT			=  4;
	public static final byte ITEM_ID_BOMB			= 10;
	public static final byte ITEM_ID_EXTEND		= 11;
	
	protected byte itemId;

    public EntityTHItem(World world)
    {
        super(world);
        limitSpeed = 0.5D;
        setSize(1.2F, 1.2F);
    }

    public EntityTHItem(World world, double posX, double posY, double posZ, Vec3 angle, byte itemId)
    {
        super(world, posX, posY, posZ);
        
        setItemType(itemId);
        limitSpeed = 0.5D;
        setSize(1.2F, 1.2F);
        motionX = angle.xCoord * limitSpeed;
        motionY = angle.yCoord * limitSpeed;
        motionZ = angle.zCoord * limitSpeed;
        lastMotionX = motionX;
        lastMotionY = motionY;
        lastMotionZ = motionZ;
        
    }
    
	/**
	 * Entity生成時に一度だけ呼ばれる
	 */
	@Override
    protected void entityInit()
    {
		super.entityInit();
		
		dataWatcher.addObject(18, new Byte((byte)0));
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
		
		if(ticksExisted > 16)
		{
			//limitSpeed = 0.4D;
			super.onUpdate();
		}
		
    	if(target != null)
    	{
    		return;
    	}
		
    	//時間が進んでいないなら
    	if(ticksExisted <= lastTime)
    	{
    		motionX = 0.0D;
    		motionY = 0.0D;
    		motionZ = 0.0D;
    		return;
    	}
    	else
    	{
    		motionX = lastMotionX;
    		motionY = lastMotionY;
    		motionZ = lastMotionZ;
    	}
    	

		
		if(Math.abs(motionX) < 0.01)
		{
			motionX = 0.0D;
		}
		else
		{
			motionX *= 0.87D;
		}
		
		if(Math.abs(motionZ) < 0.01)
		{
			motionZ = 0.0D;
		}
		else
		{
			motionZ *= 0.87D;
		}
		
		//motionY -= 0.03D;
		if(motionY > -0.2D)
		{
			motionY -= 0.03D;
			
		}
		if(motionY < -0.2D)
		{
			motionY = -0.2D;
		}
		
		posX += motionX;
		posY += motionY;
		posZ += motionZ;
		
    	Vec3 startPos = THShotLib.pos(posX, posY, posZ);
    	Vec3 endPos = THShotLib.pos(posX + motionX, posY + motionY, posZ + motionZ);
    	MovingObjectPosition movingObjectPosition = worldObj.func_147447_a(startPos, endPos, false, true, true);
    	//何らかのブロックに当たっているなら
    	if(movingObjectPosition != null )
        {
    		if(movingObjectPosition.entityHit == null)
    		{
	    		switch(movingObjectPosition.sideHit)
	    		{
	    			case 0:
	    				motionY = 0.0;
	    				posY = movingObjectPosition.blockY;
	    				break;
	    			case 1:
	    	    		if(!worldObj.isRemote)
	    	    		{
	    	    			setDead();
	    	    		}
	    	    		break;
	    			case 2:
	    				motionZ = -motionZ;
	    				posZ = movingObjectPosition.blockZ;
	    			case 3:
	    				motionZ = -motionZ;
	    				posZ = movingObjectPosition.blockZ;
	    			case 4:
	    				motionX = -motionX;
	    				posX = movingObjectPosition.blockX;
	    			default:
	    				motionX = -motionX;
	    				posX = movingObjectPosition.blockX;
	    		}
    		}
    		else
    		{
    			if(movingObjectPosition.entityHit instanceof EntityPlayer)
    			{
    				EntityPlayer player = (EntityPlayer)movingObjectPosition.entityHit;
    				this.onCollideWithPlayer(player);
    			}
    		}

        }
		
		lastMotionX = motionX;
		lastMotionY = motionY;
		lastMotionZ = motionZ;
		
    	setPosition(posX, posY, posZ);
    }
	
	@Override
	protected boolean check(EntityPlayer player)
	{
		return player.isSneaking();
	}
	
	@Override
	protected void moveToPlayer(EntityPlayer nearPlayer)
	{
		if(nearPlayer != null)
		{
			limitSpeed = 0.3D;
			super.moveToPlayer(nearPlayer);
		}
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
		boolean itemGet = false;
		
		if(!worldObj.isRemote)
		{
			switch(getItemType())
			{
				case ITEM_ID_SMALLPOWER:
			        itemGet = player.inventory.addItemStackToInventory(new ItemStack(THKaguyaItems.power_item));
					break;
				case ITEM_ID_BIGPOWER:
					itemGet = player.inventory.addItemStackToInventory(new ItemStack(THKaguyaItems.power_item, 1, 1));
					break;
				case ITEM_ID_POINT:
					itemGet = player.inventory.addItemStackToInventory(new ItemStack(THKaguyaItems.point_item));
					break;
				case ITEM_ID_BOMB:
					itemGet = player.inventory.addItemStackToInventory(new ItemStack(THKaguyaItems.bomb_item));
					if(!itemGet)
					{
						player.dropItem(THKaguyaItems.bomb_item, 1);
					}
					break;
				case ITEM_ID_EXTEND:
					itemGet = player.inventory.addItemStackToInventory(new ItemStack(THKaguyaItems.extend_item));
					if(!itemGet)
					{
						player.dropItem(THKaguyaItems.extend_item, 1);
					}
					break;
				default:
					break;
			}
			
	        if (itemGet)
	        {
	            worldObj.playSoundAtEntity(this, "random.pop", 4.0F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 0.3F);
	            player.onItemPickup(this, 1 );
	        }
	        
	        setDead();
		}
    }
	
	/**
	 * アイテムの種類を設定
	 * @param id アイテムの種類ID
	 */
	public void setItemType(byte id)
	{
		itemId = id;
		dataWatcher.updateObject(18, Byte.valueOf(id));
	}
	
	/**
	 * アイテムの種類を返す
	 * @return アイテムの種類ID
	 */
	public byte getItemType()
	{
		return dataWatcher.getWatchableObjectByte(18);
	}
}