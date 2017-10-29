package thKaguyaMod.entity.item;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.THKaguyaLib;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.shot.EntityTHShot;

/** 霊撃札 
 *  霊撃により相手を吹き飛ばし、弾も消し飛ばす
 */
public class EntitySpiritualStrikeTalisman extends Entity
{
	/** 使用者 */
	public EntityLivingBase userEntity;
	
	private int count;
	
	public EntitySpiritualStrikeTalisman(World world)
    {
        super(world);
        ignoreFrustumCheck = true;//中心が画面から外れても描画される
        preventEntitySpawning = true;
        setSize(0.2F, 0.2F);//サイズを設定　平面上の横と奥行きサイズ、高さ
        yOffset = 0.0F;//高さを設定

    }
	
    public EntitySpiritualStrikeTalisman(World world,EntityLivingBase living)
    {
        this(world);

    	userEntity = living;//使用者をuserEntityに保存
    	THKaguyaLib.itemEffectFollowUser(this, userEntity, 2.0D, 30F);

    }
    
    /** 生成時に一度だけ呼ばれる処理 */
    @Override
	protected void entityInit()
    {
    }
	
	/**
	 * 押すことができるか
	 */
    @Override
    public boolean canBePushed()
    {
        return false;
    }

	/**
	 * 他のEntityと衝突するか 右クリックできるかもこれで判定
	 */
    @Override
    public boolean canBeCollidedWith()
    {
        return false;
    }

	/** 毎tick呼ばれる処理 */
    @Override
    public void onUpdate()
    {
        super.onUpdate();
    	
    	//10ticksで消滅
    	if(ticksExisted > 3)
    	{
    		if(!worldObj.isRemote)
    		{
    			setDead();
    		}
    	}
    	
    	//***************************//
    	MovingObjectPosition movingObjectPosition = new MovingObjectPosition(this);
    	Entity entity = null;
    	double effectiveBoundary = (double)ticksExisted * 4.0D;//有効範囲
    	//効果範囲内のEntityを全て取得
    	List<?> list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(effectiveBoundary, effectiveBoundary, effectiveBoundary));

        if (list != null && list.size() > 0)//取得したリストにEntityがいるなら
        {
            for (int j1 = 0; j1 < list.size(); j1++)//取得したEntityリストの最初から最後まで見る
            {
                entity = (Entity)list.get(j1);//entityに取得したリストのEntityを代入
            	if (entity != null )//Entityがいるなら
        		{
            		movingObjectPosition = new MovingObjectPosition(entity);//MovingObjectPositionにEntityを登録
        		}
            	//movingObjectPositionがあり、それが使用者でないなら
        		if (movingObjectPosition != null && movingObjectPosition.entityHit != userEntity && movingObjectPosition.entityHit.riddenByEntity != userEntity)
        		{
        			double distance = entity.getDistanceToEntity(this);
        			if(distance <= effectiveBoundary)
        			{
        				Vec3 angle = THShotLib.angle_ToPos(THShotLib.pos_Entity(this),
        													THShotLib.pos(entity.posX,  entity.posY,  entity.posZ));
        				double effectivePower = (effectiveBoundary - distance) * 0.1D;
        				
        				if(entity instanceof EntityLivingBase)
        				{
        					if(entity instanceof EntityAnimal == false && entity instanceof EntityVillager == false)
        					{
        						entity.motionX = angle.xCoord * effectivePower;
        						entity.motionY = (/*angle.yCoord +*/ 0.5D) * effectivePower;
        						entity.motionZ = angle.zCoord * effectivePower;
        					}
        				}
        				else if(entity instanceof EntityTHShot)
        				{
        					EntityTHShot shot = (EntityTHShot)entity;
        					
        					shot.shotFinishBonus();
        				}
        			}
        		}
            }
        }

        //************************************************************************************//

    }

    /**
	 * 保存するデータの書き込み
	 * @param nbtTagCompound : NTBタグ
	 */
    @Override
    protected void writeEntityToNBT(NBTTagCompound nbtTagCompound)
    {
    	nbtTagCompound.setShort("count", (short)count);
    	//nbtTagCompound.setBoolean("isSpellCard", isSpellCard);
    }

    /**
	 * 保存したデータの読み込み
	 * @param nbtTagCompound : NBTタグ
	 */
    @Override
    protected void readEntityFromNBT(NBTTagCompound nbtTagCompound)
    {
    	count = nbtTagCompound.getShort("count");
    	//isSpellCard = nbtTagCompound.getBoolean("isSpellCard");
    }

    @Override
    public float getShadowSize()
    {
        return 0.5F;
    }

}
