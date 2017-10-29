package thKaguyaMod.entity.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import thKaguyaMod.entity.living.EntityCirno;

/** アイシクルソードの氷ブロック */
public class EntityCirnoIceBox extends Entity
{
	/** 凍らせた相手 */
	public EntityLivingBase frozen;
	private int count;
	protected float iceBoxWidth;
	protected float iceBoxHeight;

    public EntityCirnoIceBox(World world)
    {
        super(world);
        preventEntitySpawning = true;
        setSize(getIceBoxWidth() + 0.8F, getIceBoxHeight() + 0.8F);//サイズを設定　平面上の横と奥行きサイズ、高さ
    }
	
    public EntityCirnoIceBox(World world, EntityLivingBase frozenEntity)
    {
        this(world);

    	frozen = frozenEntity;//使用者をshootingEntityに保存
    	setIceBoxWidth(1.0F);
    	setIceBoxHeight(frozen.height);
    	setSize(getIceBoxWidth() + 0.8F, getIceBoxHeight() + 0.8F);
    	
    	setPosition(frozen.posX, frozen.posY, frozen.posZ);

    }
    
    /** 生成時に一度だけ呼ばれる */
    @Override
	protected void entityInit()
    {
    	dataWatcher.addObject(17, new Float(0.5F));
    	dataWatcher.addObject(18, new Float(0.5F));
    }
    
    protected void setIceBoxWidth(float width)
    {
    	dataWatcher.updateObject(17, Float.valueOf(width));
    }
    
    public float getIceBoxWidth()
    {
    	return dataWatcher.getWatchableObjectFloat(17);
    }
    
    protected void setIceBoxHeight(float height)
    {
    	dataWatcher.updateObject(18, Float.valueOf(height));
    }
    
    public float getIceBoxHeight()
    {
    	return dataWatcher.getWatchableObjectFloat(18);
    }
    
	//他の物体と衝突したときのその物体の当たり判定？
    /*@Override
    public AxisAlignedBB getCollisionBox(Entity entity)
    {
    	if(!entity.equals(frozen))
    	{
    		return entity.boundingBox;
    	}
    	return null;
    }*/

	//当たり判定を設定
    @Override
    public AxisAlignedBB getBoundingBox()
    {
        return boundingBox;
    }
	
	/**
	 * 押すことができるか
	 */
    @Override
    public boolean canBePushed()
    {
        return true;
    }

	/**
	 * 他のEntityと衝突するか 右クリックできるかもこれで判定
	 */
    @Override
    public boolean canBeCollidedWith()
    {
        return true;
    }

	/**
	 * 毎tick行う処理
	 */
    @Override
    public void onUpdate()
    {
        super.onUpdate();

    	//使用者がいないなら
    	/*if(frozen == null || frozen.isDead)
    	{
    		if(!worldObj.isRemote)
    		{
    				setDead();
    		}
    		return;
    	}*/
        /*if(!this.onGround)
        {
        	motionY -= 0.03D;
        }
        else
        {
        	motionY = 0.0D;
        }
        moveEntity(motionX, motionY, motionZ);*/
        //setPosition(posX, posY + motionY, posZ);
        

    	if(frozen != null)
    	{
    		if(frozen.isDead)
    		{
    			//frozen.deathTime--;
    		}
    		else
    		{
	    		this.width = getIceBoxWidth();
	            this.height = getIceBoxHeight();
	            this.boundingBox.minX = frozen.posX - (double)width / 2.0D;
	            this.boundingBox.minZ = frozen.posZ - (double)width / 2.0D;
	            this.boundingBox.minY = frozen.posY;
	            this.boundingBox.maxX = this.boundingBox.minX + (double)this.width;
	            this.boundingBox.maxZ = this.boundingBox.minZ + (double)this.width;
	            this.boundingBox.maxY = this.boundingBox.minY + (double)this.height;
	    		setSize(getIceBoxWidth(), getIceBoxHeight());
	    		frozenInIce();
    		}
    	}
    	
    	if(ticksExisted > 50)
    	{
    		setIceBoxHeight(getIceBoxHeight() - 0.08F);
    	}
    	if(frozen != null && frozen instanceof EntityCirno)
    	{
    		setIceBoxHeight(getIceBoxHeight() - 0.16F);
    	}
    	
		if(getIceBoxHeight() <= -0.7F)
		{
			if(!worldObj.isRemote)
			{
				setDead();
			}
		}
}

    //EntityのデータをNBTに書き込む
    @Override
    protected void writeEntityToNBT(NBTTagCompound nbtTagCompound)
    {
    	nbtTagCompound.setShort("count", (short)count);
    }

	//NBTからEntityのデータを読み込む
    @Override
    protected void readEntityFromNBT(NBTTagCompound nbtTagCompound)
    {
    	count = nbtTagCompound.getShort("count");
    }

    @Override
    public float getShadowSize()
    {
        return 0.5F;
    }


	//時間操作空間に入っていた時の処理
	protected void frozenInIce()
    {
    	//Entityを１フレーム前の状態に、できる限りもっていく処理
	    frozen.setPosition( frozen.prevPosX, frozen.prevPosY, frozen.prevPosZ);
	    //frozen.setPosition( posX, posY, posZ);
	    //frozen.setPosition(this.lastTickPosX, this.lastTickPosY, this.lastTickPosZ);
    	frozen.motionX *= 0.000000001;
	    frozen.motionY *= 0.000000001;
	    frozen.motionZ *= 0.000000001;
	    //frozen.motionY = motionY;
	    frozen.rotationYaw = frozen.prevRotationYaw;
	    frozen.rotationPitch = frozen.prevRotationPitch;
	    frozen.ticksExisted--;//誕生してからのカウントを増やさない
	    frozen.fallDistance -= 0.0F;//落下距離を変動しないように　この値はフレームに比例するが、どこに書いてあるかわからん（一応かなり近い値ではある）
	    
	    
    	frozen.rotationYawHead = frozen.prevRotationYawHead;

    	frozen.attackTime++;
    	if(frozen.isDead)
    	{
    		frozen.deathTime = 0;
    	}

    	if(frozen instanceof EntityCreeper)//EntityCreeperに属しているなら
    	{
    		EntityCreeper creeper = (EntityCreeper)frozen;
    			creeper.setCreeperState(-1);//爆発のカウントを戻す
    	}
    	else if(frozen instanceof EntityGhast)//EntityGhastに属しているなら
    	{
    		EntityGhast ghast = (EntityGhast)frozen;
    		ghast.attackCounter--;//攻撃のカウントを戻す
    	}
		if(frozen instanceof EntityTameable)//テイム可能なら
    	{
    		frozen.motionY-=0.000001D;//座っている動物は重力がなくなると立つみたい　わからない程度に重力をかける処理
    	}
    	//プレイヤーの動きを止める
    	if(frozen instanceof EntityPlayerMP)
    	{
    		EntityPlayerMP player = (EntityPlayerMP)frozen;
    		player.playerNetServerHandler.setPlayerLocation(player.prevPosX, player.prevPosY, player.prevPosZ, player.rotationYaw, player.rotationPitch);
        }
    }

}
