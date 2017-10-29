package thKaguyaMod.entity.item;

import static thKaguyaMod.DanmakuConstants.*;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.LaserData;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.shot.EntityTHShot;
import thKaguyaMod.init.THKaguyaConfig;
import thKaguyaMod.init.THKaguyaItems;

/** スキマ */
public class EntitySukima extends Entity
{
	private int count;
	
	private double warpPosX;
	private double warpPosY;
	private double warpPosZ;
	/** 使用者 */
	public EntityLivingBase userEntity;
	private float warpAngle;
	private boolean warp;
	private int pattern;//スキマのアニメーション用　未使用
	private int color;//スキマの色　同じ色のスキマ同士しか移動できない

    public EntitySukima(World world)
    {
        super(world);
        //preventEntitySpawning = true;
        //setSize(1.5F, 1.0F);//サイズを設定　平面上の横と奥行きサイズ、高さ
        //yOffset = 0.5F;//高さを設定
    	pattern = 0;
    }
	
	public EntitySukima(World world,EntityLivingBase entityLivingBase, double x, double y, double z, float angle)
    {
        super(world);
    	
        //yOffset = 1.5F;//高さを設定
    	//double x2 = (double)((int)x);
    	//double z2 = (double)((int)z);
    	
        //setPosition( x2 + 0.5D, (double)((int)y) + 1.0D, z2 + 0.5D );//初期位置を設定(x,y,z)
        setPosition(x, y, z);
    	//setPosition(x, y + 1.0D, z);
    	/*rotationYaw = EntityLivingBase.rotationYaw;
    	rotationYaw = (float)Math.atan2(x - EntityLivingBase.posX, z - EntityLivingBase.posZ) / 3.141593F * 180F;
    	//rotationYaw = rotationYaw % 180F;
    	if(rotationYaw < 0F)
    	{
    		rotationYaw += 360F;
    	}
    	if(rotationYaw % 90F < 45F)
    	{
    		rotationYaw = rotationYaw - (rotationYaw % 45F);
    	}
    	else
    	{
    		rotationYaw = rotationYaw - (rotationYaw % 45F) + 45F;
    	}*/
    	//setRotation( 360F - rotationYaw,  0.0F);
        setRotation(angle, 0.0F);
    	count = 0;
    	pattern = 0;
    	color = 1;
    	setColor(color);
    	setNewWarpPoint();
    	userEntity = entityLivingBase;

    }
	
	public EntitySukima(World world,EntityLivingBase entityLivingBase, double x, double y, double z, int sukimaColor)
    {
        this(world);
    	
    	
        setPosition( x, y, z );//初期位置を設定(x,y,z)
    	rotationYaw = entityLivingBase.rotationYaw;
    	rotationYaw = (float)Math.atan2(x - entityLivingBase.posX, z - entityLivingBase.posZ) / 3.141593F * 180F;
    	setRotation( 360F - rotationYaw,  0.0F);
    	count = 0;
    	pattern = 0;
    	color = sukimaColor;
    	setColor(color);
    	setNewWarpPoint();
    	userEntity = entityLivingBase;

    }
	
	public EntitySukima(World world,EntityLivingBase entityLivingBase, double x, double y, double z, float angle, int sukimaColor)
    {
        this(world);
    	
    	
        setPosition( x, y, z );//初期位置を設定(x,y,z)
    	rotationYaw = angle;
    	setRotation(angle + 180F,  0.0F);
    	count = 0;
    	pattern = 0;
    	color = sukimaColor;
    	setColor(sukimaColor);
    	setNewWarpPoint();
    	userEntity = entityLivingBase;

    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
	@Override
    protected boolean canTriggerWalking()
    {
        return false;
    }

	/** 生成時に一度だけ呼ばれる処理 */
	@Override
    protected void entityInit()
    {
    	dataWatcher.addObject(16, new Integer(0));//スキマの色
    }

	//押すことができるか
	@Override
    public boolean canBePushed()
    {
        return false;
    }


	//当たり判定の有無　falseだと右クリックの選択ですらできない。trueならsetSize()で設定したボックスの当たり判定が出現する
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
    	
    	//直接ワープスキマは時間消滅する
    	if(getColor() == 16 || getColor() >= 32)
    	{
    		if(ticksExisted > 24)
    		{
    			setDead();
    		}
    	}
    	
    	// 飛光虫ネストのスキマ
    	if(getColor() == 17)
    	{
    		if(ticksExisted == 5)
    		{
    			THShotLib.createLaserA(userEntity, this, THShotLib.pos_Entity(this), THShotLib.angle(rotationYaw - 180F, rotationPitch), 0.1D, 1.4D, 0.1D, THShotLib.gravity_Zero(),
    					LaserData.laser(PURPLE, 0.1F, 4.0F, 8.0F, 0, 40, 0));
    		}
    		if(ticksExisted > 9)
    		{
    			setDead();
    		}
    		return;
    	}
    	
    	MovingObjectPosition movingobjectposition = new MovingObjectPosition(this);
    	
    	//***********スキマとEntityの当たり判定を取る****************//
    	Entity entity = null;
    	List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(10.0D, 10.0D,10.0D));//判定を行う箱の大きさ

	
		double minX, minY, minZ, maxX, maxY, maxZ;
		float angleRad = rotationYaw / 180F * 3.141593F;
		minX = posX - 1.0D;
		minY = posY - 1.5D;
		minZ = posZ - 1.0D;
		maxX = posX + 1.0D;
		maxY = posY + 1.0D;
		maxZ = posZ + 1.0D;

        if (list != null && list.size() > 0)
        {
            for (int j1 = 0; j1 < list.size(); j1++)
            {
                entity = (Entity)list.get(j1);
        		if( entity instanceof EntitySukima == false &&
        			entity.posX > minX && entity.posX < maxX &&
        			entity.posY > minY && entity.posY < maxY &&
        			entity.posZ > minZ && entity.posZ < maxZ)
        		{
        			//通常のスキマなら新しいワープポイントを探す
        			//if(getColor() != 16)
        			//{
        				setNewWarpPoint();
        			//}
        			if(warp && entity.timeUntilPortal == 0 && !worldObj.isRemote)//ワープが有効だった場合の入ったEntityの処理
        			{
        				double entityY = entity.posY;
        				entity.prevPosX += warpPosX - entity.posX;
        				entity.prevPosY += warpPosY - entity.posY;
        				entity.prevPosZ += warpPosZ - entity.posZ;
        				entity.posY = warpPosY;
        				if(entity instanceof EntityPlayerMP)
        				{
        					EntityPlayerMP player = (EntityPlayerMP)entity;
        					player.playerNetServerHandler.setPlayerLocation(warpPosX, warpPosY+entity.yOffset, warpPosZ, player.rotationYaw, player.rotationPitch);
        			        /*int travelDimention = entity.dimension == 0 ? 114 : 0;
        			        ((EntityPlayerMP)entity).travelToDimension(travelDimention);*/
        			        
        					
        				}
        				
        				else if(!worldObj.isRemote)
        				{
        					entity.setPosition(warpPosX, warpPosY+entity.yOffset, warpPosZ);
        				}
        				//entity.prevPosY = warpPosY;
        				if(entity instanceof EntityTHShot)
        				{
        					EntityTHShot shot = (EntityTHShot)entity;
        					//shot.rotationYaw += warpAngle;
        					//shot.rotationYaw = warpAngle + thShotLib.getAngleAndAngleSpan(rotationYaw, shot.rotationYaw);
        					Vec3 move = THShotLib.getVecFromAngle(warpAngle - THShotLib.getAngleAndAngleSpan(rotationYaw, shot.rotationYaw), shot.rotationPitch);
        					shot.angle.xCoord = -move.xCoord;
        					shot.angle.yCoord = -move.yCoord;
        					shot.angle.zCoord = move.zCoord;
        					double speed = shot.getSpeed();
        					shot.motionX = shot.lastShotMotionX = shot.angle.xCoord * speed;
        					shot.motionY = shot.lastShotMotionY = shot.angle.yCoord * speed;
        					shot.motionZ = shot.lastShotMotionZ = shot.angle.zCoord * speed;
        					//shot.updateAngle();
        				}
        				else
        				{
        					entity.rotationYaw += warpAngle;
        					entity.motionX *= -Math.sin(entity.rotationYaw);
        					entity.motionZ *= Math.cos(entity.rotationYaw);
        				}
        				/*if(warpPosY < entityY)
        				{
        					entity.fallDistance = (float)(warpPosY - entityY);
        					//entity.fallDistance = -1.0F;
        				}
        				else
        				{
        					entity.fallDistance = 0.0F;
        				}*/
        				//entity.onGround = true;
        				entity.timeUntilPortal = 40;//２秒間スキマの影響を受けない
        				worldObj.playSoundAtEntity(entity, THKaguyaConfig.sukimaWarpSE, 1.0F, 1.0F);
        				//worldObj.playSoundAtEntity(entity, "THItems.yukariPortal_teleport", 1.9F, 1.0F);
        			}
        		}
            }
        }
	}

	//プレイヤーが、Entityに対して右クリックしたときに呼び出される
    public boolean interactFirst(EntityPlayer player)
    {
    	if(getColor() >= 16)
    	{
    		return false;
    	}
    	ItemStack itemstack = player.inventory.getCurrentItem();//プレイヤーの選択しているアイテムを取得
    	if(!player.worldObj.isRemote)
    	{
	    	//if(itemstack != null && itemstack.itemID == Item.dyePowder.itemID)//プレイヤーが染料を持っていれば
	    	if(itemstack != null && itemstack.getItem() == Item.getItemById(351))//プレイヤーが染料を持っていれば
	    	{
	    		if(THKaguyaConfig.useDefaultGapSE)
    			{
    				worldObj.playSoundAtEntity(player, "mob.endermen.portal", 0.5F, 0.4F);
    			}
    			else
    			{
    				worldObj.playSoundAtEntity(player,  THKaguyaConfig.sukimaWarpSE, 1.0F, 1.0F);
    			}
	    		//itemstack.stackSize--;
	    		//color = itemstack.getItemDamage();//スキマの色を、染料のダメージ値に対応する色にする
	    		setColor(itemstack.getItemDamage());
	    		color = getColor();
	    	}
	    	//else if(itemstack != null && itemstack.itemID == mod_thKaguya.kaigoItem.itemID)//悔悟の棒を持った状態
	    	else if(itemstack != null && itemstack.getItem() == THKaguyaItems.remorse_rod)//悔悟の棒を持った状態
	    	{
	    		//dropItemWithOffset(mod_thKaguya.sukimaItem.itemID, 1, 0.0F);//アイテム：スキマをドロップさせる
	    		this.dropItem(THKaguyaItems.gap, 1);
	    		setDead();//消滅させる
	    	}
	    	else if(player.isSneaking())//染料も持ってなく、スニークなら
	    	{
	    		//dropItemWithOffset(mod_thKaguya.sukimaItem.itemID, 1, 0.0F);//アイテム：スキマをドロップさせる
	    		this.dropItem(THKaguyaItems.gap, 1);
	    		setDead();//消滅させる
	    	}
    	}
        return true;
    }
    
    /**
	 * 保存するデータの書き込み
	 * @param nbtTagCompound : NTBタグ
	 */
    protected void writeEntityToNBT(NBTTagCompound nbtTagCompound)
    {
    	nbtTagCompound.setShort("color", (short)getColor());
    }

    /**
	 * 保存したデータの読み込み
	 * @param nbtTagCompound : NBTタグ
	 */
    protected void readEntityFromNBT(NBTTagCompound nbtTagCompound)
    {
    	setColor(nbtTagCompound.getShort("color"));
    	
    }

    public float getShadowSize()
    {
        return 2.0F;
    }
    
	//スキマの色を取得する
	public void setColor(int color)
	{
		dataWatcher.updateObject(16, Integer.valueOf(color));
	}
    //スキマの色を取得する
	public int getColor()
	{
		return dataWatcher.getWatchableObjectInt(16);
	}
    
    //新しいワープポイントを取得する
	public void setNewWarpPoint()
	{
		double nearDistance = 1000.0D;//一番スキマの距離として、ありえないデタラメな値を保存
		double distance;
		double dx, dy, dz;
		EntitySukima setSukima = null;
		EntitySukima nearSukima = null;
		MovingObjectPosition movingobjectposition = new MovingObjectPosition(this);
		Entity entity = null;
    	List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(128.0D, 128.0D,128.0D));//周囲256x256x256の立方体内が有効範囲 おそらくチャンクの読み込みの関係でこれが限界サイズ
		
        if (list != null && list.size() > 0)//範囲内のEntityをチェック
        {
            for (int j1 = 0; j1 < list.size(); j1++)
            {
            	entity = (Entity)list.get(j1);
            	if(entity instanceof EntitySukima)//スキマのみチェック
            	{
            		setSukima = (EntitySukima)entity;
            		if( (getColor() % 32) == (setSukima.getColor() % 32) )//スキマ同士の色が同じ場合のみ判定
            		{
            			distance = getDistanceToEntity(entity);
            			if(distance < nearDistance && distance > 5.0D)//距離が５m以上で、距離が近いなら一番近いスキマとして保存
            			{
            				nearDistance = distance;
            				nearSukima = setSukima;
            			}
            		}
            	}
            }
        	if(nearSukima != null)//範囲内に有効なスキマがあればワープさせる
        	{
        		float angleNS = nearSukima.rotationYaw / 180F * 3.141593F;
        		warpPosX = nearSukima.posX + (double)Math.sin(angleNS)*1.8D;
        		warpPosY = nearSukima.posY;
        		warpPosZ = nearSukima.posZ - (double)Math.cos(angleNS)*1.8D;
        		warpAngle = 180F - ((rotationYaw % 360) - (nearSukima.rotationYaw % 360));
        		//warpAngle = nearSukima.rotationYaw;
        		warp = true;
        	}
        	else//範囲内に有効なスキマがなければ何も起きない
        	{
        		warp = false;
        	}
        }
	}
    
	/*@SideOnly(Side.CLIENT)
	public int patternSukima()
	{
		return pattern/4;
	}*/
	
}
