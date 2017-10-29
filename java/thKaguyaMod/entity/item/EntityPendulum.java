package thKaguyaMod.entity.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thKaguyaMod.THKaguyaLib;
import thKaguyaMod.init.THKaguyaItems;

/** ナズーリンのペンデュラム
 *	指定したブロックが近くにあるか探す
 */ 
public class EntityPendulum extends Entity
{
	/** 使用者 */
	public EntityPlayer user;
	private int mode;//ペンデュラムのモード
	private int searchBlockID;//探しているブロックのID
	private int searchCount;//探索したブロックの数
	private int searchLength;//探索する距離
	private int searchX;
	private int searchY;
	private int searchZ;
	private int searchMaxX;
	private int searchMaxY;
	private int searchMaxZ;
	private float pendulumRotation;
	private int count;
	
	private String searchBlockName;
	private ItemStack searchItem;

    public EntityPendulum(World world)
    {
        super(world);
        preventEntitySpawning = true;
        setSize(0.1F, 0.1F);//サイズを設定　平面上の横と奥行きサイズ、高さ
        yOffset = 0.0F;//高さを設定
 
        searchBlockID = 0;
        mode = 0;
    	searchCount = 0;
    	pendulumRotation = 0.0F;
    }
    
    public EntityPendulum(World world,EntityPlayer player, int pmode, String name, ItemStack itemStack)
    {
        this(world);

        user = player;//使用者をshootingEntityに保存
        THKaguyaLib.itemEffectFollowUser(this, user, 1.2D, -30F, false, (double)user.getEyeHeight() - 0.5D);
        /*setPosition(user.posX - Math.sin((user.rotationYaw + 30.0F) / 180F * 3.141593F),
        			user.posY + (double)user.getEyeHeight() - 0.5D,
        			user.posZ + Math.cos((user.rotationYaw + 30.0F) / 180F * 3.141593F));//初期位置を設定(x,y,z)
    	rotationYaw = entityLiving.rotationYaw;*/

		mode = pmode;
    	if(mode == 1)
		{
			searchLength = 24;
		}
    	else
    	{
    		searchLength = 12;
    	}
    	
    	//ridingEntity = entityLiving;
    	//searchBlockID = blockID;
    	//pendulumRotation = 0.0F;
    	
    	searchBlockName = name;
    	searchItem = itemStack;
    }

	/** 生成時に一度だけ呼ばれる */
    @Override
    protected void entityInit()
    {
		dataWatcher.addObject(16, new Integer(0));
    	dataWatcher.addObject(17, new Integer(0));
    }

	//押すことができるか
    public boolean canBePushed()
    {
        return false;
    }
    
	//当たることができるか
    /*public boolean canBeCollidedWith()
    {
        return false;
    }*/

    

	//ペンデュラムを終了した時の処理
	private void finish()
	{
		if(user instanceof EntityPlayer)
		{
			EntityPlayer entityPlayer = (EntityPlayer)user;
			if(entityPlayer.capabilities.isCreativeMode)
			{
				setDead();
				return;
			}
		}
		if(user != null)
		{
			if(user.inventory.addItemStackToInventory(new ItemStack(THKaguyaItems.nazrin_pendulum, 1)) == false)
			{
				//インベントリが一杯なら使用者の目の前でアイテム化
				//（クリエイティブでは常にインベントリには物が吸収される仕様があるらしく、クリエイティブでは落とさない（false判定がでない)）
				if(!worldObj.isRemote)
				{
					//user.dropItemWithOffset(mod_thKaguya.pendulumItem.itemID, 1, 0.0F);
					user.dropItem(THKaguyaItems.nazrin_pendulum, 1);
				}
			}
			//}
		}
		else//使用者がいない場合（再起動した際など）にはその場にアイテム化
		{
			if(!worldObj.isRemote)
			{
				//dropItemWithOffset(mod_thKaguya.pendulumItem.itemID, 1, 0.0F);
				dropItem(THKaguyaItems.nazrin_pendulum, 1);
			}
    	}
		setDead();
	}

	/**
	 * 毎tick行う処理
	 */
	@Override
    public void onUpdate()
    {
        super.onUpdate();

    	if(!worldObj.isRemote && user == null)
    	{
    		finish();
    	}

    	if(!worldObj.isRemote && user != null)
    	{
    		if(ticksExisted > 10 && user.isSneaking())
    		{
    			finish();
    		}
    		if(user.hurtTime > 0)
    		{
    			finish();
    		}
    	}

    	//サーチの処理
    	if(count % 60 == 0 && !worldObj.isRemote)
		{
			searchCount = 0;
			searchX = (int)posX - searchLength;
			searchZ = (int)posZ - searchLength;
			searchY = (int)posY - searchLength;
			searchMaxX = searchX + searchLength * 2 + 1;
			searchMaxY = searchY + searchLength * 2 + 1;
			searchMaxZ = searchZ + searchLength * 2 + 1;

			String searchName;
			
			for(int si = searchX; si < searchMaxX; si++)
			{
				for(int sk = searchZ; sk < searchMaxZ; sk++)
				{
					for(int sj = searchY; sj < searchMaxY; sj++)
					{
						searchName = StatCollector.translateToLocal(worldObj.getBlock(si, sj, sk).getUnlocalizedName() + ".name");
						//if( worldObj.getBlockId( si, sj, sk) == searchBlockID)
						//if(StatCollector.translateToLocal(worldObj.getBlock(si, sj, sk).getLocalizedName() + ".name").equals(searchBlockName))
						if(searchName.equals(searchBlockName))
						{
							searchCount++;
						}
						/*if(user != null)
						{
							user.addChatMessage( new ChatComponentText(searchName) );
						}*/
					}
				}
			}
			if(searchCount > 32)
			{
				searchCount = 32;
			}
			setSearchCount(searchCount);
		}
		
    	if(getSearchCount() != 0)
    	{
    		//pendulumRotation = getSearchAngle();
    		//pendulumRotation += (float)getSearchCount() / 3.0F;
    		setSearchAngle( getSearchAngle() +  (float)getSearchCount() / 3.0F);
        	//rotationYaw += (float)getSearchCount() / 3.0F;
    		rotationYaw += 3.0F;
    	}
    	if(user != null)
    	{
    		THKaguyaLib.itemEffectFollowUser(this, user, 1.2D, -30F, false, (double)user.getEyeHeight() - 0.5D);
    	}
    	count ++;
	}

    public float getShadowSize()
    {
        return 0.5F;
    }

    /**
	 * 保存するデータの書き込み
	 * @param nbtTagCompound : NTBタグ
	 */
    protected void writeEntityToNBT(NBTTagCompound nbtTagCompound)
    {
    }

    /**
	 * 保存したデータの読み込み
	 * @param nbtTagCompound : NBTタグ
	 */
    protected void readEntityFromNBT(NBTTagCompound nbtTagCompound)
    {
    }
    
    //サーチによる回転角度を設定する
    public void setSearchAngle(float angle)
	{
		dataWatcher.updateObject(16, Integer.valueOf((int)(angle * 100.0F)));
	}
	
	//サーチによる回転角度を返す
	//@SideOnly(Side.CLIENT)
	public float getSearchAngle()
	{
		return (float)dataWatcher.getWatchableObjectInt(16) / 100.0F;
	}
	
	//サーチで引っかかったブロックの数を設定する
	public void setSearchCount(int count)
	{
		dataWatcher.updateObject(17, Integer.valueOf(count));
	}
	
	//サーチで引っかかったブロックの数を返す
	public int getSearchCount()
	{
		return dataWatcher.getWatchableObjectInt(17);
	}
}
