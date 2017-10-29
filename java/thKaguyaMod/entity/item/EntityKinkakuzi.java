package thKaguyaMod.entity.item;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thKaguyaMod.entity.shot.EntityTHShot;
import thKaguyaMod.init.THKaguyaItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/** 金閣寺の一枚天井 */
public class EntityKinkakuzi extends Entity
{
	private boolean field_70279_a;
	private double speedMultiplier;
    private int boatPosRotationIncrements;
    private double boatX;
    private double boatY;
    private double boatZ;
    private double boatYaw;
    private double boatPitch;
	@SideOnly(Side.CLIENT)
    private double velocityX;
	@SideOnly(Side.CLIENT)
    private double velocityY;
	@SideOnly(Side.CLIENT)
    private double velocityZ;
	private EntityLivingBase shootingEntity;
	
	private int lastTime;
	public double lastMotionX;
	public double lastMotionY;
	public double lastMotionZ;
	
	/** ワールド読み込み時に呼び出されるコンストラクト */
    public EntityKinkakuzi(World world)
    {
        super(world);
    	field_70279_a = true;
    	this.speedMultiplier = 0.07D;
        preventEntitySpawning = true;
        setSize(6F, 0.3F);//サイズを設定　第一引数が横と奥行き、第二引数が高さ
        yOffset = height / 2.0F;//高さを設定
    }
    
    /** 出現時に呼び出されるコンストラクト */
    public EntityKinkakuzi(World world,double x, double y, double z,EntityLivingBase entityLivingBase, double pow)
    {
        this(world);
    	motionX = -Math.sin((float)Math.toRadians(entityLivingBase.rotationYaw)) * 0.2D;//出現時の移動速度　向いている方向に動く
        motionY = pow;//少し上方向に持ち上げる
    	motionZ =  Math.cos((float)Math.toRadians(entityLivingBase.rotationYaw)) * 0.2D;
    	lastMotionX = motionX;
    	lastMotionY = motionY;
    	lastMotionZ = motionZ;
    	lastTime = 0;
    	prevPosX = x;
    	prevPosY = y;
    	prevPosZ = z;
    	rotationYaw = entityLivingBase.rotationYaw;
    	rotationPitch = 0.0F;
    	setLocationAndAngles(x, y, z, rotationYaw, rotationPitch);
    	shootingEntity = entityLivingBase;//使用者をshootingEntityに保存
    }

	/** 生成時に一度だけ呼ばれる処理 */
    @Override
    protected void entityInit()
    {
        dataWatcher.addObject(17, new Integer(0));//dataWatcharの17～19を使用
        dataWatcher.addObject(18, new Integer(1));
        dataWatcher.addObject(19, new Float(0.0F));
    }

    /**
     * Returns a boundingBox used to collide the entity with other entities and blocks. This enables the entity to be
     * pushable on contact, like boats or minecarts.
     */
	//他の物体と衝突したときのその物体の当たり判定？
    @Override
    public AxisAlignedBB getCollisionBox(Entity entity)
    {
        return entity.boundingBox;
    }

	//当たり判定を設定
    @Override
    public AxisAlignedBB getBoundingBox()
    {
        return boundingBox;
    }

	//押すことができるかどうか
    @Override
    public boolean canBePushed()
    {
        return true;
    }

	

	//Entityが乗ったときのそのEntityを置く高さ
    public double getMountedYOffset()
    {
        return (double)height * 0.0D + 0.3D;
    }

	//Entityに攻撃されたときに呼び出されるメソッド　破壊とか関係なしに攻撃されれば呼び出される
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
    {
    	if (this.isEntityInvulnerable())
        {
            return false;
        }
        else if (!worldObj.isRemote && !isDead)
        {
        	setForwardDirection(-getForwardDirection());
        	setTimeSinceHit(10);
        	setDamageTaken(getDamageTaken() + par2 * 10.0F);
        	setBeenAttacked();
        	boolean flag = par1DamageSource.getEntity() instanceof EntityPlayer && ((EntityPlayer)par1DamageSource.getEntity()).capabilities.isCreativeMode;

        	if (flag || getDamageTaken() > 40.0F)//相手がプレイヤーでクリエイティブ、または、４以上のダメージを受けているなら
        	{
            	if (riddenByEntity != null)
            	{
                	riddenByEntity.mountEntity(this);
            	}
        		if(!flag)
        		{
            		//dropItemWithOffset(mod_thKaguya.kinkakuziItem.itemID, 1, 0.0F);//破壊されたら、金閣寺の一枚天井を一つ落とす
            		//dropItemWithOffset( 1, 0.0F);//破壊されたら、金閣寺の一枚天井を一つ落とす
            		this.dropItem(THKaguyaItems.kinkakuji, 1);
        		}
        		setDead();
        	}

        	return true;
        }
    	else
    	{
        	return true;
		}
    }

    /**
     * Setups the entity to do the hurt animation. Only used by packets in multiplayer.
     */
	@SideOnly(Side.CLIENT)
    public void performHurtAnimation()
    {
        setForwardDirection(-getForwardDirection());
        setTimeSinceHit(10);
        setDamageTaken(getDamageTaken() * 11);
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return !isDead;
    }

    /**
     * Sets the position and rotation. Only difference from the other one is no bounding on the rotation. Args: posX,
     * posY, posZ, yaw, pitch
     */
	@SideOnly(Side.CLIENT)
    public void setPositionAndRotation2(double x, double y, double z, float yaw, float pitch, int par9)
    {
    	if (this.field_70279_a)
        {
            this.boatPosRotationIncrements = par9 + 5;
        }
    	else
    	{
			double xDistance = x - this.posX;
        	double yDistance = y - this.posY;
        	double zDistance = z - this.posZ;
        	double distance = xDistance * xDistance + yDistance * yDistance + zDistance * zDistance;

        	if (distance <= 1.0D)
        	{
            	return;
        	}
    		
    		boatPosRotationIncrements = 3;
    	}
    	
        boatX = x;
        boatY = y;
        boatZ = z;
        boatYaw = yaw;
        boatPitch = pitch;
        motionX = velocityX;
        motionY = velocityY;
        motionZ = velocityZ;
    }

	//ベクトルを設定
	@SideOnly(Side.CLIENT)
    public void setVelocity(double x, double y, double z)
    {
        velocityX = motionX = x;
        velocityY = motionY = y;
        velocityZ = motionZ = z;
    }

	/** 毎tick呼ばれる処理 */
	@Override
    public void onUpdate()
    {
        super.onUpdate();

    	//時間が進んでいないなら
    	/*if(ticksExisted <= lastTime)
    	{
    		return;//処理を終了させる
    	}
    	else//時間が通常通り進んでいるなら
    	{
    		//何事もなかったように移動量を設定する
    		motionX = lastMotionX;
    		motionY = lastMotionY;
    		motionZ = lastMotionZ;
    	}*/
    	
        if (getTimeSinceHit() > 0)
        {
            setTimeSinceHit(getTimeSinceHit() - 1);
        }

        if (getDamageTaken() > 0)
        {
            setDamageTaken(getDamageTaken() - 1);
        }

    	//以前のポジションを保存
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
    	
        int i = 5;
        double d = 0.0D;

        for (int j = 0; j < i; j++)
        {
            double d2 = (boundingBox.minY + ((boundingBox.maxY - boundingBox.minY) * (double)(j + 0)) / (double)i) - 0.125D;
            double d8 = (boundingBox.minY + ((boundingBox.maxY - boundingBox.minY) * (double)(j + 1)) / (double)i) - 0.125D;
        	AxisAlignedBB axisalignedbb = AxisAlignedBB.getBoundingBox(this.boundingBox.minX, d2, this.boundingBox.minZ, this.boundingBox.maxX, d8, this.boundingBox.maxZ);

            if (worldObj.isAABBInMaterial(axisalignedbb, Material.water))
            {
                d += 1.0D / (double)i;
            }
        }

    	//
        double lengthXZ = Math.sqrt(motionX * motionX + motionZ * motionZ);
        double newPosX;//ボート比較　使ってないと思う
        double newPosY;//同上
        
        //ボートの場合、ここに水しぶきに関して書いてあるよ
        
        double d10;//ボート比較　以下略
        double newPosZ;//同上

    	MovingObjectPosition movingobjectposition = new MovingObjectPosition(this);

    	
        /*if (worldObj.isRemote && this.field_70279_a)
        {
            if (boatPosRotationIncrements > 0)
            {
                newPosX = posX + (boatX - posX) / (double)boatPosRotationIncrements;
                newPosY = posY + (boatY - posY) / (double)boatPosRotationIncrements;
                newPosZ = posZ + (boatZ - posZ) / (double)boatPosRotationIncrements;
                d10 = MathHelper.wrapAngleTo180_double(this.boatYaw - (double)this.rotationYaw);
                this.rotationYaw = (float)((double)this.rotationYaw + d10 / (double)this.boatPosRotationIncrements);
                this.rotationPitch = (float)((double)this.rotationPitch + (this.boatPitch - (double)this.rotationPitch) / (double)this.boatPosRotationIncrements);
                --this.boatPosRotationIncrements;
                this.setPosition(newPosX, newPosY, newPosZ);
                this.setRotation(this.rotationYaw, this.rotationPitch);
                //double d17;

                //for (d17 = boatYaw - (double)rotationYaw; d17 < -180D; d17 += 360D) { }

                //for (; d17 >= 180D; d17 -= 360D) { }

                //rotationYaw += d17 / (double)boatPosRotationIncrements;
                //rotationPitch += (boatPitch - (double)rotationPitch) / (double)boatPosRotationIncrements;
                //boatPosRotationIncrements--;
                //setPosition(d4, d10, d13);
                //setRotation(rotationYaw, rotationPitch);
            }
            else
            {
                newPosX = posX + motionX;
                newPosY = posY + motionY;
                newPosZ = posZ + motionZ;
                setPosition(newPosX, newPosY, newPosZ);

                if (onGround)//地面に接しているなら
                {
                	//動きを鈍らせる
                    motionX *= 0.5D;
                    motionY *= 0.5D;
                    motionZ *= 0.5D;
                	posY += 0.3D;
                }
				//若干減速させる
                motionX *= 0.99000000953674316D;
                motionY *= 0.94999998807907104D;
                motionZ *= 0.99000000953674316D;
            }
        }*/
    	
    	//else
    	//if(!worldObj.isRemote)
    	{
        	if (d < 1.0D)
        	{
            	newPosX = d * 2D - 1.0D;
            	motionY += 0.039999999105930328D * newPosX;
        	}
        	else
        	{
        		//上昇していないなら
            	if (motionY < 0.0D)
            	{
                	motionY /= 2.0D;//落下速度を半分にする
            	}

        		//わずかに浮上させる
            	motionY += 0.0070000002160668373D;
        	}

        	if (riddenByEntity != null && this.riddenByEntity instanceof EntityLivingBase)//誰かが乗っているなら
        	{
        		//乗っているEntityの水平方向の移動量を、金閣寺の移動量に反映させる
            	//motionX += riddenByEntity.motionX * 0.20000000000000001D;
            	//motionZ += riddenByEntity.motionZ * 0.20000000000000001D;
            	
            	newPosX = (double)((EntityLivingBase)this.riddenByEntity).moveForward;

                if (newPosX > 0.0D)
                {
                    newPosY = -Math.sin((double)(this.riddenByEntity.rotationYaw * (float)Math.PI / 180.0F));
                    newPosZ = Math.cos((double)(this.riddenByEntity.rotationYaw * (float)Math.PI / 180.0F));
                    this.motionX += newPosY * this.speedMultiplier * 0.05000000074505806D;
                    this.motionZ += newPosZ * this.speedMultiplier * 0.05000000074505806D;
                }
        	}
        	
        	newPosX = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
        	
        	if(newPosX > 0.35D)
        	{
        		newPosY = 0.35D / newPosX;
        		motionX *= newPosY;
        		motionZ *= newPosY;
        		newPosX = 0.35D;
        	}
        	
        	if(newPosX > lengthXZ && speedMultiplier < 0.35D)
        	{
                this.speedMultiplier += (0.35D - this.speedMultiplier) / 35.0D;

                if (this.speedMultiplier > 0.35D)
                {
                    this.speedMultiplier = 0.35D;
                }
        	}
        	else
        	{
                this.speedMultiplier -= (this.speedMultiplier - 0.07D) / 35.0D;

                if (this.speedMultiplier < 0.07D)
                {
                    this.speedMultiplier = 0.07D;
                }
        	}
        	
/*
    		//最大の移動量 移動速度の制限
	        double maxMotion = 0.40000000000000002D;
			//移動量を最大値に収める
	        if (motionX < -maxMotion)
	        {
	            motionX = -maxMotion;
	        }
	        if (motionX > maxMotion)
	        {
	            motionX = maxMotion;
	        }
	        if (motionZ < -maxMotion)
	        {
	            motionZ = -maxMotion;
	        }
	        if (motionZ > maxMotion)
	        {
	            motionZ = maxMotion;
        	}
        	*/

	    	//地面に付いているなら
	        if (onGround)
	        {
	            motionX *= 0.5D;
	            motionY *= 0.5D;
	            motionZ *= 0.5D;
	        }
    	
    		//移動量分だけEntityを動かす
        	moveEntity(motionX, motionY, motionZ);

        	if (isCollidedHorizontally && lengthXZ > 0.2D)
        	{
        		//ボートが勢い良くぶつかったときの処理。壊れないから無視
        	}
        	else
        	{
            	motionX *= 0.99000000953674316D;
            	motionY *= 0.94999998807907104D;
            	motionZ *= 0.99000000953674316D;
        	}

        	rotationPitch = 0.0F;
        	newPosY = rotationYaw;
        	newPosZ = prevPosX - posX;
        	d10 = prevPosZ - posZ;

        	if (newPosZ * newPosZ + d10 * d10 > 0.001D)
        	{
            	newPosY = (float)((Math.atan2(d10, newPosZ) * 180D) / Math.PI);
        	}

        	double angleShift = MathHelper.wrapAngleTo180_double(newPosY - (double)this.rotationYaw);
        	
        	//double angleShift;//水平角度変化

        	//for (angleShift = d12 - (double)rotationYaw; angleShift >= 180D; angleShift -= 360D) { }

        	//for (; angleShift < -180D; angleShift += 360D) { }

    		//変化量を上限下限に収める
        	if (angleShift > 20D)
        	{
            	angleShift = 20D;
        	}
        	if (angleShift < -20D)
        	{
            	angleShift = -20D;
        	}
			//水平角度に角度の変化量を加算する
	        //rotationYaw += angleShift;
	    	rotationYaw = (float)((double)this.rotationYaw + angleShift);
        	//水平角度と垂直角度をセットする;
	        setRotation(rotationYaw, rotationPitch);

	        if(!worldObj.isRemote)
	        {
                List<?> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(0.20000000298023224D, 0.0D, 0.20000000298023224D));
                int l;

                if (list != null && !list.isEmpty())
                {
                    for (l = 0; l < list.size(); ++l)
                    {
                        Entity entity = (Entity)list.get(l);

                        if (entity != this.riddenByEntity && entity.canBePushed() && entity instanceof EntityBoat)
                        {
                            entity.applyEntityCollision(this);
                        }
                    }
                }
	        	
		    	//***********　金閣寺の一枚天井とEntityの当たり判定を取る****************//
		    	//ほとんどボートと同じだけど、ここはボートにない処理
		    	Entity entity = null;
		        list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(MathHelper.sin((float)boatYaw / 180.0F * (float)Math.PI) * 0.1D, 0.5D,MathHelper.cos((float)boatYaw / 180.0F * (float)Math.PI)* 0.1D));//(0.20000000298023224D, 0.0D, 0.20000000298023224D));
		        //List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(3.0D, 0.15D, 3.0D));
				//List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(1.2D, 1.2D,1.2D));//(0.20000000298023224D, 0.0D, 0.20000000298023224D));
	
		        if (list != null && list.size() > 0)
		        {
		            for (int j1 = 0; j1 < list.size(); j1++)
		            {
		                entity = (Entity)list.get(j1);
		                if (entity != riddenByEntity && entity.canBePushed() && (entity instanceof EntityKinkakuzi))
		                {
		                    entity.applyEntityCollision(this);
		                }
		            	if (entity != null )
		        		{
		            		movingobjectposition = new MovingObjectPosition(entity);
		        		}
		        		if (movingobjectposition.entityHit != riddenByEntity && movingobjectposition != null && entity instanceof EntityKinkakuzi == false)
		        		{
		            		hitOnEntity(movingobjectposition);//ダメージを与える処理とかしてる
		        		}
		            }
		        }
	
		        //************************************************************************************//
	
				//誰かが乗っていて、それが死んでいるなら
		        if (riddenByEntity != null && riddenByEntity.isDead)
		        {
		        	//乗っているEntityをいないことにする
		            riddenByEntity = null;
		        }
	        }
		}
		
		//通常通り時間が進んでいるなら
    	if(ticksExisted > lastTime)
    	{
    		//最後に時間が動いていたときの時間と移動量を保存する
    		lastTime = ticksExisted;
    		lastMotionX = motionX;
    		lastMotionY = motionY;
    		lastMotionZ = motionZ;
    		//lastRotationYaw = (float)Math.atan2(lastShotMotionX, lastShotMotionZ) / 3.141593F * 180F;
    		//lastRotationPitch = (float)Math.atan2(lastShotMotionY, Math.sqrt( lastShotMotionX * lastShotMotionX + lastShotMotionZ * lastShotMotionZ)) / 3.141593F * 180F;
    	}
		
    }
    
    //Entityと衝突したときの処理
    protected void hitOnEntity(MovingObjectPosition movingobjectposition)
    {
    	//金閣寺の一枚天井が当たったEntityより上にあり、当たったEntityが、金閣寺の一枚天井、アイテム、使用者、プレイヤー、経験値オーブでないなら
    	if(!worldObj.isRemote && movingobjectposition.entityHit != null)
    	{
    		if ( movingobjectposition.entityHit.posY < this.posY &&
    			!(movingobjectposition.entityHit instanceof EntityItem)
    			&& !(movingobjectposition.entityHit instanceof EntityXPOrb)
    			&& !(movingobjectposition.entityHit instanceof EntityTHShot)
    	    	&& movingobjectposition.entityHit != shootingEntity)
       		{
       			double yMove = prevPosY - posY;
       			if(yMove >= 0.0)
       			{
       				int damage = (int)(yMove * 40.0) + 2;//ダメージは落下速度依存　最低でもハート１個分はダメージを与える
                	movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, shootingEntity), damage);
       			}
            }
        }
    }

	//金閣寺の一枚天井に乗っているEntityの位置の更新
    public void updateRiderPosition()
    {
        if (riddenByEntity != null)
        {
            double d = Math.cos(((double)rotationYaw * Math.PI) / 180D) * 0.5D;
            double d1 = Math.sin(((double)rotationYaw * Math.PI) / 180D) * 0.5D;
            riddenByEntity.setPosition(posX + d, posY + getMountedYOffset() + riddenByEntity.getYOffset(), posZ + d1);
        }
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
    
	@SideOnly(Side.CLIENT)
    public float getShadowSize()
    {
        return 0.0F;
    }

	
	//プレイヤーに右クリックされたときに呼ばれる
    //public boolean interact(EntityPlayer entityPlayer)
	@Override
    public boolean interactFirst(EntityPlayer entityPlayer)
	//public boolean func_130002_c(EntityPlayer entityPlayer)
    {
        if (riddenByEntity != null && riddenByEntity instanceof EntityPlayer && riddenByEntity != entityPlayer)
        {
            return true;
        }
		else
    	{
        	if (!worldObj.isRemote)
        	{
            	entityPlayer.mountEntity(this);
        	}

        	return true;
    	}
    }

    /**
     * Sets the damage taken from the last hit.
     */
    public void setDamageTaken(float par1)
    {
        this.dataWatcher.updateObject(19, Float.valueOf(par1));
    }

    /**
     * Gets the damage taken from the last hit.
     */
    public float getDamageTaken()
    {
        //return this.dataWatcher.func_111145_d(19);
        return this.dataWatcher.getWatchableObjectFloat(19);
    }
    
    

    /**
     * Sets the time to count down from since the last time entity was hit.
     */
    public void setTimeSinceHit(int par1)
    {
        this.dataWatcher.updateObject(17, Integer.valueOf(par1));
    }

    /**
     * Gets the time since the last hit.
     */
    public int getTimeSinceHit()
    {
        return this.dataWatcher.getWatchableObjectInt(17);
    }

    /**
     * Sets the forward direction of the entity.
     */
    public void setForwardDirection(int par1)
    {
        this.dataWatcher.updateObject(18, Integer.valueOf(par1));
    }

    /**
     * Gets the forward direction of the entity.
     */
    public int getForwardDirection()
    {
        return this.dataWatcher.getWatchableObjectInt(18);
    }
    
    @SideOnly(Side.CLIENT)
    public void func_70270_d(boolean par1)
    {
        this.field_70279_a = par1;
    }


}
