package thKaguyaMod.entity.effect;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.ShotData;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.shot.EntityTHLaser;
import thKaguyaMod.entity.shot.EntityTHShot;

/** 白楼剣の跳ね返すシールド */
public class EntityHakurouReflecter extends Entity
{
	/** 使用者 */
	public EntityPlayer user;
	public int damage;
	public int count;
	public double xVec;
	public double yVec;
	public double zVec;

	//ワールド読み込み時に呼び出されるコンストラクト
    public EntityHakurouReflecter(World world)
    {
        super(world);
        //preventEntitySpawning = true;
        setSize(1.5F, 1.5F);//サイズを設定　平面上の横と奥行きサイズ、高さ
        yOffset = 0.0F;//高さを設定*/
        count = 4;
    }

	//出現時に呼び出されるコンストラクト
    public EntityHakurouReflecter(World world, EntityPlayer entityPlayer)
    {
        this(world);
    	setSize(1.5F, 1.5F);//ワールド読み込み時のコンストラクトと同じ
        yOffset = 0.0F;//同上
    	user = entityPlayer;
    	Vec3 vec3 = THShotLib.getVecFromAngle(user.rotationYaw, user.rotationPitch, 1.0D);
        setPosition(user.posX + vec3.xCoord, user.posY + vec3.yCoord + user.getEyeHeight(), user.posZ + vec3.zCoord);//初期位置を設定(x,y,z)
    	setRotation(-user.rotationYaw,  user.rotationPitch);
    	Vec3 vec3_2 = THShotLib.getVecFromAngle(rotationYaw, rotationPitch, 0.18D);
		xVec = vec3_2.xCoord;
		zVec = vec3_2.zCoord;
		yVec = vec3_2.yCoord;
    	count = 0;
    }

	//他の物体と衝突したときのその物体の当たり判定？
    /*public AxisAlignedBB getCollisionBox(Entity entity)
    {
		double width = 0.75D;
		double length = 3.0D;
		double xLength = motionX + Math.sin(rotationYaw / 180F * 3.141593F) * Math.cos(rotationPitch / 180F * 3.141593F) * width;
		double yLength = motionY + Math.sin(rotationPitch / 180F * 3.141593F) * length;
		double zLength = motionZ + Math.cos(rotationYaw / 180F * 3.141593F) * Math.cos(rotationPitch / 180F * 3.141593F) * width;
		
		xLength -= xLength;
		zLength -= zLength;
        return entity.boundingBox.addCoord(xLength * 2D, yLength, zLength * 2D).expand(width * 2D, length, width * 2D);//指定範囲内のEntityをリストに登録;
    }

	//当たり判定を設定
    public AxisAlignedBB getBoundingBox()
    {
        double width = 0.75D;
		double length = 3.0D;
		double xLength = motionX + Math.sin(rotationYaw / 180F * 3.141593F) * Math.cos(rotationPitch / 180F * 3.141593F) * width;
		double yLength = motionY + Math.sin(rotationPitch / 180F * 3.141593F) * length;
		double zLength = motionZ + Math.cos(rotationYaw / 180F * 3.141593F) * Math.cos(rotationPitch / 180F * 3.141593F) * width;
		
		xLength -= xLength;
		zLength -= zLength;
        return boundingBox.addCoord(xLength * 2D, yLength, zLength * 2D).expand(width * 2D, length, width * 2D);//指定範囲内のEntityをリストに登録;;
    }*/

	//押すことができるかどうか
    public boolean canBePushed()
    {
        return false;
    }

	/** 生成時に一度だけ呼ばれる */
    @Override
    protected void entityInit()
    {
    	dataWatcher.addObject(19, new Integer(0));
    }

	//当たり判定の有無　falseだと右クリックの選択ですらできない。trueならsetSize()で設定したボックスの当たり判定が出現する
	@Override
    public boolean canBeCollidedWith()
    {
        return true;
    }

	//Entityが存在する限り毎フレーム呼び出されるメソッド
	@Override
    public void onUpdate()
    {
    	super.onUpdate();

    	//AxisAlignedBB getCollisionBox(this);
    	//使用者がいないなら終了処理
    	if(!worldObj.isRemote)
    	{
    		if(user == null || user.isDead)
    		{
    			setDead();
    		}
    		if(ticksExisted >= 20)
    		{
    			setDead();
    		}
    	}
    	
    	
    	motionX = xVec;
    	motionY = yVec;
    	motionZ = zVec;
    	
    	posX -= motionX;
    	posY += motionY;
    	posZ += motionZ;
    	
    	
    	

    	/*if(damage >= ItemYuukaParasol.PARASOL_MAX_DAMAGE)//ダメージが武器の耐久を越したら
    	{
    		setDead();//消滅
    	}*/
    	
    	double speedRate = (double)(4 - count) * 0.2D;
    	
    	/*if(count < 4)
    	{
    		this.posX -= Math.sin((double)rotationYaw / 180.0D * Math.PI) * speedRate;
    		this.posZ += Math.cos((double)rotationYaw / 180.0D * Math.PI) * speedRate;
    	}*/
    	
		double width = 0.5D;
		double length = 0.5D;
	    //始点（現在地）
    	Vec3 vec3d = Vec3.createVectorHelper(posX , posY, posZ);
    	//終点（現在地に移動量を足した点）
    	//Vec3 vec3d1 = Vec3.createVectorHelper(posX + motionX + xVec * width, posY + motionY + yVec * length, posZ + motionZ + zVec * width);
    	Vec3 vec3d1 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
        //始点と終点からブロックとの当たりを取得
    	//MovingObjectPosition movingObjectPosition = worldObj.rayTraceBlocks_do_do(vec3d, vec3d1, false, true);
    	MovingObjectPosition movingObjectPosition = worldObj.rayTraceBlocks(vec3d, vec3d1, false);
    	//vec3d = Vec3.createVectorHelper(posX, posY, posZ);
    	//vec3d1 = Vec3.createVectorHelper(posX + motionX + xVec * width, posY + motionY + yVec * length, posZ + motionZ + zVec * width);
    	vec3d = Vec3.createVectorHelper(posX , posY, posZ);
    	vec3d1 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
    	
    	//何らかのブロックに当たっているなら
        if (movingObjectPosition != null)
        {
        	//終点を当たった点に変更
        	vec3d1 = Vec3.createVectorHelper(movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
        	if(movingObjectPosition.entityHit == null)
        	{
        		if(!worldObj.isRemote)
        		{
        			setDead();
        		}
        	}
        }
    	
    	
        Entity entity = null;//実際に当たったことにするEntity
    	double d = 0.0D;//そのEntityまでの仮の距離
    	//ここから移動量分の線分を作り、それに弾の大きさの２倍の肉付けをし直方体を作る。それに当たったEntityをリスト化する
		double xLength = motionX - Math.sin(rotationYaw / 180F * 3.141593F) * Math.cos(rotationPitch / 180F * 3.141593F) * length;
		double yLength = motionY - Math.sin(rotationPitch / 180F * 3.141593F) * length;
		double zLength = motionZ + Math.cos(rotationYaw / 180F * 3.141593F) * Math.cos(rotationPitch / 180F * 3.141593F) * length;
		
		xLength -= xLength;
		zLength -= zLength;
        List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(xLength, yLength, zLength).expand(width * 2D, width * 2D, width * 2D));//指定範囲内のEntityをリストに登録

    	for (int j = 0; j < list.size(); j++)
        {
            Entity entity1 = (Entity)list.get(j);//entity1にリストの先端のentityを保存
        	//entity1が、当たり判定を取らない　または　entity1が使用者　または　飛んで25カウント以下？　または　EntityTHShotならパス
            if ( entity1 instanceof EntityTHShot && !(entity1 instanceof EntityTHLaser))
            {
        		//float f2 = 0.3F;
            	EntityTHShot shot = (EntityTHShot)entity1;
        		//判定を弾の大きさに変更
            	AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(shot.getShotSize(), shot.getShotSize(), shot.getShotSize());
            	MovingObjectPosition movingObjectPosition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);
        		//この判定で何も当たっていないならパス
            	//if (movingObjectPosition1 != null)
            	{
            		movingObjectPosition = new MovingObjectPosition(entity1);
            		//double xVec = -Math.sin(player.rotationYaw / 180F * 3.141593F) * Math.cos(player.rotationPitch / 180F * 3.141593F);
            		//double yVec = -Math.sin(player.rotationPitch / 180F * 3.141593F);
            		//double zVec =  Math.cos(player.rotationYaw / 180F * 3.141593F) * Math.cos(player.rotationPitch / 180F * 3.141593F);
            		//指定範囲内のEntityのリストを取得
            		//List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(xVec, yVec, zVec).expand(size, size, size));
            		//double d = 0.0D;
            		//for (int j = 0; j < list.size(); j++)
            		//{
            			//Entity entity1 = (Entity)list.get(j);
            			//if(entity1 instanceof EntityTHShot && !(entity1 instanceof EntityTHLaser))
            			//{
            				
            				//if(entity1.getDistanceSq(player.posX, player.posY + player.getEyeHeight(), player.posZ) < size * 2.0D + shot.getShotSize())
            				
            					if(user != null && user != shot.user)
            					{
            						ShotData reflectShot = ShotData.shot(THShotLib.FORM_SCALE, THShotLib.AQUA, THShotLib.SIZE[THShotLib.FORM_SCALE], 3.0F, 0, 40, 0);
            						THShotLib.createShot(user, THShotLib.pos_Entity(shot), THShotLib.angle(-rotationYaw, rotationPitch), 0.3D, 0.7D, 0.05D, reflectShot);
            					
	            					if(!worldObj.isRemote)
	            					{
	            						entity1.setDead();
	            					}
	            					//hit = true;
	            					worldObj.playSoundAtEntity(shot, "random.bow", 0.5F, 0.4F / (rand.nextFloat() * 4F + 0.8F));//音を出す
            					}
            				//}
            				
            			//}
            		//}
            		if(movingObjectPosition != null)
            		{
            			onImpact(movingObjectPosition);
            		}
            	}
        	
            }
        }

    	setPosition(posX, posY, posZ);
    	setRotation(rotationYaw, rotationPitch);
    	
    	count ++;
    }
	
	//ブロックやEntityに当たった時の処理
    protected void onImpact(MovingObjectPosition movingObjectPosition)
    {
    	if(!worldObj.isRemote && movingObjectPosition.entityHit != null)
    	{
    	//当たった時の処理
    	Entity hitEntity = movingObjectPosition.entityHit;
        
    	//当たったEntityがいるなら
    	if ( hitEntity != null )
    	{
        	//それがEntityTHShotに属していないなら
        	if(hitEntity instanceof EntityTHShot == false)
        	{
        		
        		//double angleXZ = Math.atan2(hitEntity.posX - hitEntity.lastTickPosX, hitEntity.posZ - hitEntity.lastTickPosZ);
        		//hitEntity.motionX = -hitEntity.motionX * 2.0D;
        		//hitEntity.motionY = -Math.sin(hitEntity.rotationPitch / 180.0D * Math.PI) * 2.0D;
        		//hitEntity.motionZ = -hitEntity.motionZ * 2.0D;
        		//指定したダメージ分の魔法ダメージを与える

           		double slope = ((this.posX + this.xVec) - (this.posX - this.xVec)) / ((this.posZ + this.zVec) - (this.posZ - this.zVec));
        		double height = this.posX - slope * this.posZ;
        		
        		if(hitEntity.posX > hitEntity.posZ * slope + height)
        		{
        			hitEntity.motionX = -Math.sin(this.rotationYaw / 180.0D * Math.PI);
        			hitEntity.motionZ = -Math.cos(this.rotationYaw / 180.0D * Math.PI);
        		}
        		else
        		{
        			hitEntity.motionX = Math.sin(this.rotationYaw / 180.0D * Math.PI);
        			hitEntity.motionZ = Math.cos(this.rotationYaw / 180.0D * Math.PI);
        		}
        		
        		if (!hitEntity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, user), 1.0F));
        		
        	}
        	//EntityTHShotに属しているなら
        	else
        	{
        		if(!worldObj.isRemote)
        		{
        			hitEntity.setDead();
        		}
        	}
		}
    	}
    	else
    	{
    		if(!worldObj.isRemote)
    		{
    			setDead();
    			return;
    		}
    	}
    	
    }

	//Entityに攻撃されたときに呼び出されるメソッド　破壊とか関係なしに攻撃されれば呼び出される
	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
    {
        return true;
    }

    public void setParasolMode(int mode)
	{
		dataWatcher.updateObject(19, Integer.valueOf(mode));
	}

	public int getParasolMode()
	{
		return dataWatcher.getWatchableObjectInt(19);
	}

	protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
    }

    protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
    }
}