package thKaguyaMod.entity.item;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.THKaguyaLib;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.shot.EntityTHShot;
import thKaguyaMod.init.THKaguyaItems;

/** 銀のナイフ */
public class EntitySilverKnife extends Entity
{
	private final byte KNIFE_COLOR_BLUE	= 0x00;
	private final byte KNIFE_COLOR_RED	= 0x01;
	private final byte KNIFE_COLOR_GREEN	= 0x02;
	private final byte KNIFE_COLOR_WHITE	= 0x03;
	
	private byte color;//ナイフの色
	private int xTile;//Xの整数座標
    private int yTile;//Yの整数座標
    private int zTile;//Zの整数座標
    private int inTile;//今いる場所のブロックID
	private int inData;//今いる場所のブロックのメタデータ
    private boolean inGround;//地中にいるか
    private int ticksInAir;
    
    /** 使用者 */
    public EntityLivingBase shootingEntity;
	public EntityLivingBase playerEntity;
    
	private int ticksInGround;
    public double accelerationX;
    public double accelerationY;
    public double accelerationZ;
    
	private boolean hit;//衝突済み判定
	private boolean rockhit;//刺さらないブロックの上に乗っているかの判定
	
	public float angleZ;
	
	private int lastTime;
	private double lastShotMotionX;
	private double lastShotMotionY;
	private double lastShotMotionZ;
	private int delay;

	/** ワールド読み込み時に呼び出されるコンストラクト */
    public EntitySilverKnife(World world)
    {
        super(world);
        xTile = -1;
        yTile = -1;
        zTile = -1;
        inTile = 0;
    	inData = 0;
        inGround = false;
        setSize(0.1F, 0.1F);
    	
    	hit = false;
    	
    }
    
    public EntitySilverKnife(World world, Vec3 pos,  Vec3 angle, double speed, int t)
    {
    	this(world, null, pos, angle, speed, t);
    }

    public EntitySilverKnife(World world, EntityLivingBase entityLivingBase, Vec3 pos, Vec3 angle, double speed, int t)
    {
        super(world);
        setSize(0.1F, 0.1F);
    	color = (byte)t;//ナイフの色を保存
    	setKnifeColor(color);
        shootingEntity = entityLivingBase;
    	playerEntity = entityLivingBase;
    	
    	motionX = angle.xCoord * speed;
    	motionY = angle.yCoord * speed;
    	motionZ = angle.zCoord * speed;
    	lastShotMotionX = motionX;
    	lastShotMotionY = motionY;
    	lastShotMotionZ = motionZ;
    	accelerationX = (motionX/2.0);
        accelerationY = (motionY/2.0);
        accelerationZ = (motionZ/2.0);
    	setKnifeZAngle(90.0F);
		rotationYaw = (float)Math.atan2(motionX, -motionZ) / 3.141593F * 180F;
    	rotationPitch = (float)Math.atan2(motionY, Math.sqrt(motionX * motionX + motionZ * motionZ)) / 3.141593F * 180F;
    	setLocationAndAngles( 	pos.xCoord + angle.xCoord * speed, pos.yCoord + angle.yCoord * speed, pos.zCoord + angle.zCoord * speed,
    							rotationYaw, rotationPitch);
    	prevPosX = posX;
    	prevPosY = posY;
    	prevPosZ = posZ;
    	lastTime = 0;
    	
    	delay = 0;
    	if(t == 3)
    	{
    		delay = -30;
    		//setKnifeCount(-30);
    	}
    }
    
    @Override
    protected boolean canTriggerWalking()
    {
        return false;
    }
    
	/**
	 * 押すことができるかどうか
	 * @return true : 押せる
	 */
    @Override
    public boolean canBePushed()
    {
    	//押せない
    	return false;
    }
    
	/**
	 * 他の物体と衝突したときのその物体の当たり判定？
	 */
    @Override
    public AxisAlignedBB getCollisionBox(Entity entity)
    {
    	//当たり判定を取らない
        return null;
    }

	/**
	 * 当たり判定を設定
	 */
    @Override
    public AxisAlignedBB getBoundingBox()
    {
    	// 当たり判定のボックスを持たない
        return null;
    }

    @Override
    public boolean isInRangeToRenderDist(double d)
    {
        double d1 = boundingBox.getAverageEdgeLength() * 4D;
        d1 *= 64D;
        return d < d1 * d1;
    }
	
	/** 生成時に一度だけ呼ばれる処理 */
	@Override
    protected void entityInit()
    {
		dataWatcher.addObject(18, new Integer(0));
    	dataWatcher.addObject(19, new Byte((byte)0));
    	dataWatcher.addObject(20, new Integer(0));
    }

	
	/**
	 * 移動量から速度を取得する
	 * @return 速度を返す
	 */
	public double getEntitySpeed()
	{
		return Math.sqrt(motionX * motionX + motionY * motionY + motionZ * motionZ);
	}
	
	/** 消滅させる */
	public void delete()
	{
		if(!worldObj.isRemote)
		{
			setDead();//消滅させる
		}
	}
	
	/** 毎tick呼ばれる処理 */
	@Override
    public void onUpdate()
    {
        super.onUpdate();
    	
        if(ticksExisted > 1)
        {
	        //時間が停止しているなら
	        if(ticksExisted <= lastTime)
	        {
	        	setKnifeCount(getKnifeCount());
			    return;
	        }
	        //時間が通常通り流れているなら
			else
			{
				motionX = lastShotMotionX;
				motionY = lastShotMotionY;
				motionZ = lastShotMotionZ;
				if(!worldObj.isRemote)
			    {
			    	delay ++;
			    	if(delay >= 0)
			    	{
			    		setKnifeCount(getKnifeCount() + 1);
			    	}
			    }
	    	}
        }
        
        if(getKnifeCount() < 0)
        {
        	setKnifeCount(getKnifeCount() + 1);
        	return;
        }
    	
        //どこともぶつからずに長時間経過した場合、消滅させる
    	if(!hit && ticksExisted >= 300)
    	{
    		delete();
    	}
    	//ぶつかっている場合は、かなり長い間停滞する
    	else if(hit && ticksExisted >= 6000)
    	{
    		delete();
    	}
    	
    	//白色のナイフで、壁に衝突していたら消滅させる
    	if(getKnifeColor() == 3 && hit && ticksExisted >= 1)
    	{
    		delete();
    	}

    	rockhit = false;//硬いブロックと衝突しているかの判定変数

    	//地面の中なら
        if (inGround)
        {
        	//衝突しているブロックのIDを取得
            int hitBlockId = Block.getIdFromBlock(worldObj.getBlock(xTile, yTile, zTile));
            
        	//衝突しているブロックのメタデータを取得
            int hitBlockMetadata = worldObj.getBlockMetadata(xTile, yTile, zTile);
            
            if (hitBlockId != inTile || hitBlockMetadata != inData)
            {
                inGround = false;
                motionX *= rand.nextFloat() * 0.2F;
                motionY *= rand.nextFloat() * 0.2F;
                motionZ *= rand.nextFloat() * 0.2F;
            	ticksInGround = 0;
                setKnifeCount(3);
            }
			else
        	{
        		ticksInGround++;//地中カウンタを増やす

            	if (ticksInGround == 3000)//地中カウンタが3000なら消滅
            	{
            		delete();
            	}
        	}

            return;//これ以上の処理は行わない
        }
        else
        {
        	ticksInAir = 5;
        }
    	
    	//始点を登録
    	Vec3 posStart = THShotLib.pos_Entity(this);
    	//終点を登録
    	Vec3 posEnd = THShotLib.pos( this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        //始点と終点からブロックとの衝突を取得
    	MovingObjectPosition movingObjectPosition = worldObj.func_147447_a(posStart, posEnd, false, true, false);
    	
    	posStart = THShotLib.pos_Entity(this);
    	posEnd = THShotLib.pos( this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        //ブロックと衝突していたなら
    	if (movingObjectPosition != null)
        {
        	//終点を衝突した点に変更
        	posEnd = THShotLib.pos( movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
        }
    	
        Entity hitEntity = null;
    	//このEntityから移動後までの線分に、指定分の範囲を追加した直方体と衝突するEntityのリストを取得
        List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(0.01D, 0.01D, 0.01D));
        //現在最も近い距離を保存
        double nearDistance = 999.0D;
        
        for (int j = 0; j < list.size(); j++)
        {
        	// 衝突リストから、i番目のEntityを取得
            Entity entityCheck = (Entity)list.get(j);

    		if(	entityCheck.canBeCollidedWith() && // 当たり判定を持ったEntityであるか？
    			entityCheck != shootingEntity   && // 使用者ではないか？
    			ticksInAir >= 5                 &&
    			entityCheck instanceof EntityTHShot == false)//弾幕には当たらない
    		{
    			if(entityCheck instanceof EntitySilverKnife)
    			{
    				EntitySilverKnife entitySilverKnife = (EntitySilverKnife)entityCheck;
    				if(this.getKnifeColor() != 3  && entitySilverKnife.getKnifeColor() != 3)
    				{
    	        		float boxExtend = 0.01F;
    	            	AxisAlignedBB axisalignedbb = entityCheck.boundingBox.expand(boxExtend, boxExtend, boxExtend);
    	            	MovingObjectPosition movingObjectPosition1 = axisalignedbb.calculateIntercept(posStart, posEnd);
    	            	
    	        		//Entityとの衝突がないなら、このEntityはパスする
    	            	if (movingObjectPosition1 != null)
    	            	{
    	        			//始点からEntityに衝突した点までの距離を取得
    	            		double distance = posStart.distanceTo(movingObjectPosition1.hitVec);
    	        			//今までで一番近いEntityなら、Entityと距離を記憶する
    	            		if (distance < nearDistance || nearDistance == 999.0D)
    	            		{		
    	                		hitEntity = entityCheck;
    	                		nearDistance = distance;
    	            		}
    	            	}
    				}
    			}
    			else
    			{
	        		float boxExtend = 0.1F;
	            	AxisAlignedBB axisalignedbb = entityCheck.boundingBox.expand(boxExtend, boxExtend, boxExtend);
	            	MovingObjectPosition movingObjectPosition1 = axisalignedbb.calculateIntercept(posStart, posEnd);
	        		//Entityとの衝突がないなら、このEntityはパスする
	            	if (movingObjectPosition1 != null)
	            	{
	        			//始点からEntityに衝突した点までの距離を取得
	            		double d1 = posStart.distanceTo(movingObjectPosition1.hitVec);
	        			//今までで一番近いEntityなら、Entityと距離を記憶する
	            		if (d1 < nearDistance || nearDistance == 999.0D)
	            		{		
	                		hitEntity = entityCheck;
	                		nearDistance = d1;
	            		}
	            	}
            	}
            }
        }

    	//Entityに当たっていたなら
        if (hitEntity != null)
        {
            movingObjectPosition = new MovingObjectPosition(hitEntity);
        }
    	
    	if (movingObjectPosition != null && movingObjectPosition.entityHit != null && movingObjectPosition.entityHit instanceof EntityPlayer)
        {
        	EntityPlayer entityPlayer = (EntityPlayer)movingObjectPosition.entityHit;

            //if (entityPlayer.capabilities.disableDamage || this.shootingEntity instanceof EntityPlayer && !((EntityPlayer)this.shootingEntity).func_96122_a(entityPlayer))
            if (entityPlayer.capabilities.disableDamage || this.shootingEntity instanceof EntityPlayer /*&& !((EntityPlayer)this.shootingEntity).func_96122_a(entityPlayer)*/)
            {
            	movingObjectPosition = null;
            }
        }
    	
    	if (movingObjectPosition != null)
        {
        	//Entityに当たっていたなら
            if (movingObjectPosition.entityHit != null)
            {
            	//当たったEntityがEntitySilverKnifeに属していなければ
            	if(!(movingObjectPosition.entityHit instanceof EntitySilverKnife)  )
            	{
            		//衝突処理
            		onImpact(movingObjectPosition);
            		//
	                float f1 = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);

            		//ナイフの動きを鈍らせ、逆方向に働かせる
	                motionX *= -0.210000000149011612D;
	                motionY *= -0.210000000149011612D;
	                motionZ *= -0.210000000149011612D;
            		accelerationX *= -0.01;
            		accelerationY *= -0.01;
            		accelerationZ *= -0.01;
            		rotationYaw += 180F;
	                prevRotationYaw += 180F;
            		//生存カウンタを3まで戻す
            		setKnifeCount(3);
            		//衝突チェックをtrueにする
	                hit = true;
            		setKnifeZAngle(0.0F);
            	}
            }
            	
            xTile = movingObjectPosition.blockX;
            yTile = movingObjectPosition.blockY;
            zTile = movingObjectPosition.blockZ;
            //inTile = worldObj.getBlockId(xTile, yTile, zTile);
            inTile = Block.getIdFromBlock(worldObj.getBlock(xTile, yTile, zTile));
            inData = worldObj.getBlockMetadata(xTile, yTile, zTile);
            //当たったブロックの材質を取得
           // Material material = worldObj.getBlockMaterial(xTile, yTile, zTile);
            Material material = worldObj.getBlock(xTile, yTile, zTile).getMaterial();

            //当たったブロックの材質が、rock,iron,glass,iceなら
            if( (material == Material.rock || material == Material.iron || material == Material.glass || material == Material.ice) )
            {
            	//速度が遅すぎでなければ
            	if(Math.abs(getEntitySpeed()) > 0.08F)
            	{
            		//銀のナイフに衝突しているなら
            		if(movingObjectPosition.entityHit instanceof EntitySilverKnife)
            		{
            			EntitySilverKnife entitySilverKnife = (EntitySilverKnife)movingObjectPosition.entityHit;
            			//どちらかが白のナイフでなければ
            			if(entitySilverKnife.getKnifeColor() != 3 && this.getKnifeColor() != 3)
            			{
            				//衝突時の金属音のような音を出す
                			worldObj.playSoundAtEntity(this, "random.orb", 0.5F, 5.0F);

	            			//衝突処理をし、少しランダムに向きを変える
	            			Random rand = new Random();
	            			float randangle = (float)rand.nextInt(120);
	            			rotationYaw += 165.0F - 15.0F + randangle;
	            			motionX += Math.sin(randangle);
	            			motionZ += Math.cos(randangle);
	            			motionX *= -0.150000000149011612D * Math.sin(randangle);//移動方向を逆にする
		                    motionY *= -0.150000000149011612D;
	            			motionZ *= -0.150000000149011612D * Math.cos(randangle);
	            			accelerationX *= -0.01;
		            		accelerationZ *= -0.01;
	            			//このナイフと当たったナイフの上下関係で速度に変化が出る
	            			if(posY > movingObjectPosition.entityHit.posY)
	            			{
	            				accelerationY *=  0.01;
	            			}
	            			else
	            			{
	            				accelerationY *= -1.2;
	            			}
            			}
            		}
            		//硬いブロックに当たっているなら
            		if(movingObjectPosition.entityHit == null)
            		{
            			//衝突時の金属音のような音を出す
            			worldObj.playSoundAtEntity(this, "random.orb", 0.5F, 5.0F);
            			
            			//跳ね返り係数
            			double returnRate = 0.25D * getEntitySpeed();
            			
            			if(getKnifeColor() == 0)
            			{
            				returnRate = 0.01D;
            			}

            			double supPosX1 = posX;
            			double supPosX2 = posX + motionX;
            			double supPosY1 = posY;
            			double supPosY2 = posY + motionY;
            			double supPosZ1 = posZ;
            			double supPosZ2 = posZ + motionZ;
            			//始点を登録
            			Vec3 supVec3d1 = Vec3.createVectorHelper(supPosX1, supPosY1, supPosZ1);
    					//終点を登録
    					Vec3 supVec3d2 = Vec3.createVectorHelper(supPosX2, supPosY2, supPosZ2);
        				//始点と終点からブロックとの衝突を取得
    					//MovingObjectPosition supMovingObjectPosition = worldObj.rayTraceBlocks_do_do(supVec3d1, supVec3d2, false, true);
    					MovingObjectPosition supMovingObjectPosition = worldObj.func_147447_a(supVec3d1, supVec3d2, false, true, false);

    					//何かにあたっている間
            			while(supMovingObjectPosition != null)
            			{
            				returnRate = 0.25D * getEntitySpeed();
            				if(getKnifeColor() != 1)
            				{
            					returnRate = 0.00001D;
            				}
            				//衝突したブロックの面ごとに判定
	            			switch(supMovingObjectPosition.sideHit)
	            			{
	            				case 0://下面
	            					motionY *= -returnRate;
	            					accelerationY *= -returnRate;
	            					break;
	            				case 1://上面
	            					motionY *= -returnRate;
	            					accelerationY *= -returnRate;
	            					setKnifeCount(0);
	            					if(motionY < 0.2D)
	            					{
	            						motionX *= 0.3D;
	            						motionZ *= 0.3D;
	            					}
	            					if(getKnifeZAngle() > 0.0F)
	            					{
	            						setKnifeZAngle(getKnifeZAngle() - 21.0F);
	            					}
	            					else
	            					{
	            						setKnifeZAngle(0.0F);
	            					}
	            					break;
	            				case 2:
	            					motionZ *= -returnRate;
	            					accelerationZ *= -returnRate;
	            					break;
	            				case 3:
	            					motionZ *= -returnRate;
	            					accelerationZ *= -returnRate;
	            					break;
	            				case 4:
	            					motionX *= -returnRate;
	            					accelerationX *= -returnRate;
	            					break;
	            				default:
	            					motionX *= -returnRate;
	            					accelerationX *= -returnRate;
	            					break;
	            			}
            				if(getKnifeColor() != 1)
            				{
            					motionX *= 0.3D;
            					motionY *= 0.3D;
            					motionZ *= 0.3D;
            					accelerationX *= 0.3D;
            					accelerationY *= 0.3D;
            					accelerationZ *= 0.3D;
            				}
            				//rotationYaw += 180F;//向きを180度変える
            				supPosX1 = supPosX1 + (supMovingObjectPosition.hitVec.xCoord - supPosX1) * 0.999D;
            				supPosX2 = supPosX1 + motionX;
            				supPosY1 = supPosY1 + (supMovingObjectPosition.hitVec.yCoord - supPosY1) * 0.999D;
            				//supPosY1 = supMovingObjectPosition.hitVec.yCoord;
            				supPosY2 = supPosY1 + motionY;
            				supPosZ1 = supPosZ1 + (supMovingObjectPosition.hitVec.zCoord - supPosZ1) * 0.999D;
            				supPosZ2 = supPosZ1 + motionZ;
            				//始点を登録
            				supVec3d1 = Vec3.createVectorHelper(supPosX1, supPosY1, supPosZ1);
    						//終点を登録
    						supVec3d2 = Vec3.createVectorHelper(supPosX2, supPosY2, supPosZ2);
        					//始点と終点からブロックとの衝突を取得
    						//supMovingObjectPosition = worldObj.rayTraceBlocks_do_do(supVec3d1, supVec3d2, false, true);
    						supMovingObjectPosition = worldObj.func_147447_a(supVec3d1, supVec3d2, false, true, false);
    						//count++;
            			}
            		}
            		if(Math.abs(motionX) < 0.1D)
            		{
            			motionX = 0.0D;
            		}
            		if(Math.abs(motionZ) < 0.1D)
            		{
            			motionZ = 0.0D;
            		}
	            	setKnifeCount(3);
            		hit = false;
            	}
            	//速度が遅すぎて、かつ、衝突先のブロックが空気でなければ
            	else if(material != Material.air)
            	{
            		//遅すぎる場合はほぼ地面についていると考える　　・・・これ、空中停止バグの原因じゃ・・・
            		//角度を水平にする
            		if(movingObjectPosition.sideHit == 1)
            		{
            			rotationPitch = 0F;
						motionX = movingObjectPosition.hitVec.xCoord - posX;
	                	motionY = movingObjectPosition.hitVec.yCoord - posY;
	                	motionZ = movingObjectPosition.hitVec.zCoord - posZ;
	            		accelerationX = 0.0D;
	            		accelerationY = 0.0D;
	            		accelerationZ = 0.0D;
	            		//当たったブロックとナイフの距離
		            	double f2 = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
	            		//めり込んだ分だけ引き戻し
		            	posX -= (motionX / f2) * 0.05000000074505806D;
		            	posY -= (motionY / f2) * 0.05000000074505806D;
		            	posZ -= (motionZ / f2) * 0.05000000074505806D;
	            		//地中チェックをtrue
		            	inGround = true;
	            		//衝突チェックをtrue
		            	hit = true;
	            		//石衝突チェックをtrue
	            		rockhit = true;	
            			setKnifeCount(0);
            			setKnifeZAngle(0.0F);
            		}
            		else if(movingObjectPosition != null)
            		{
            			//rotationPitch = 0F;
		            	motionX = movingObjectPosition.hitVec.xCoord - posX;
	                	motionY = movingObjectPosition.hitVec.yCoord - posY;
	                	motionZ = movingObjectPosition.hitVec.zCoord - posZ;
	            		accelerationX *= -0.01;
	            		accelerationY *= -0.01;
	            		accelerationZ *= -0.01;
	            		//当たったブロックとナイフの距離
		            	double f2 = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
	            		//めり込んだ分だけ引き戻し
		            	posX -= (motionX / f2) * 0.05000000074505806D;
		            	posY -= (motionY / f2) * 0.05000000074505806D;
		            	posZ -= (motionZ / f2) * 0.05000000074505806D;
	            		//地中チェックをtrue
		            	inGround = true;
	            		//衝突チェックをtrue
		            	hit = true;
	            		//石衝突チェックをtrue
	            		rockhit = true;	
            		}
            	}
            	else
            	{
            		hit = false;
            	}
            }
        	//衝突先のブロックの材質が、硬いものでなく、空気でもなければ
            else if(material != Material.air)
            {
            	//その場で刺さって停止
                motionX = (float)(movingObjectPosition.hitVec.xCoord - posX);
                motionY = (float)(movingObjectPosition.hitVec.yCoord - posY);
                motionZ = (float)(movingObjectPosition.hitVec.zCoord - posZ);
            	accelerationX *= -0.01;
            	accelerationY *= -0.01;
            	accelerationZ *= -0.01;
	            float f2 = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);//当たったブロックとナイフの距離
	            posX -= (motionX / (double)f2) * 0.05000000074505806D;//めり込んだ分だけ引き戻し
	            posY -= (motionY / (double)f2) * 0.05000000074505806D;
	            posZ -= (motionZ / (double)f2) * 0.05000000074505806D;
	            //worldObj.playSoundAtEntity(this, "mob.blaze.hit1", 1.0F, 1.2F / (rand.nextFloat() * 0.2F + 0.9F));
	            inGround = true;
            	setKnifeCount(3);
	            hit = true;
	          //衝突時の金属音のような音を出す
    			//worldObj.playSoundAtEntity(this, worldObj.getBlock(xTile, yTile, zTile), 0.5F, 5.0F);
            }
        }
    	
    	if(delay < 0)
    	{
    		return;
    	}
    	
    	if(!worldObj.isRemote && getKnifeCount() > 2 && delay >= 0)
    	{
    		posX += motionX;
    		posY += motionY;
    		posZ += motionZ;
    	}
    	
    	//緑のナイフの白ナイフ出現処理
    	if(getKnifeColor() == 2 && Math.abs(getEntitySpeed()) > 0.08F && ticksExisted < 60 && ticksExisted > 2 && ticksExisted % 2 == 1)
    	{
        	Vec3 angle = THShotLib.angle(motionX, motionY, motionZ);
        	THShotLib.getVectorNomalize(angle);
        	
        	if(!worldObj.isRemote)
        	{
        		EntitySilverKnife knife;
        		if(this.shootingEntity == null || this.playerEntity == null)
        		{
        			knife = new EntitySilverKnife(worldObj, THShotLib.pos_Entity(this), angle, getEntitySpeed(), 3);
        			worldObj.spawnEntityInWorld(knife);
        		}
        		else
        		{
        			knife = THKaguyaLib.createSilverKnife(shootingEntity, THShotLib.pos_Entity(this), angle, getEntitySpeed(), 3, /*getKnifeCount()*/ 0 );
        		}
        		knife.accelerationX = this.accelerationX;
        		knife.accelerationY = this.accelerationY;
        		knife.accelerationZ = this.accelerationZ;

        	}
    	}
    	
        float f = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
        rotationYaw = (float)((Math.atan2(motionX, -motionZ) * 180D) / 3.1415927410125732D);
        //for (rotationPitch = (float)((Math.atan2(motionY, f) * 180D) / 3.1415927410125732D); rotationPitch - prevRotationPitch < -180F; prevRotationPitch -= 360F) { }
        if(!worldObj.isRemote)
        {
        	for (rotationPitch = (float)((Math.atan2(motionY, f) * 180D) / 3.1415927410125732D); rotationPitch - prevRotationPitch < -180F; prevRotationPitch -= 360F) { }
        }
        for (; rotationPitch - prevRotationPitch >= 180F; prevRotationPitch += 360F) { }
        for (; rotationYaw - prevRotationYaw < -180F; prevRotationYaw -= 360F) { }
        for (; rotationYaw - prevRotationYaw >= 180F; prevRotationYaw += 360F) { }
        rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 1.0F;
    	
    	if(rockhit == true)
    	{
    		rotationPitch = 0F;
    	}
        
    	rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 1.0F;
        float slowDownRate = 0.95F;//減速率
        if (isInWater())//水に入っている場合
        {
            slowDownRate = 0.8F;//さらに減速率を上げる
        }
        
        if(delay >= 0/*getKnifeCount() >= 0*/)
        {
	        motionX += accelerationX;
	        motionY += accelerationY;
	        motionZ += accelerationZ;
	
	    	//重力処理
	        //if(lastTime < getKnifeCount())
	        {
	        	motionY -= 0.008 * getKnifeCount();
	        }
	    		
	        motionX *= slowDownRate;
	        motionY *= slowDownRate;
	        motionZ *= slowDownRate;
        }
    	
        setPosition(posX, posY, posZ);
    	
		if(ticksExisted > lastTime)
    	{
    		lastTime = ticksExisted;
    		lastShotMotionX = motionX;
    		lastShotMotionY = motionY;
    		lastShotMotionZ = motionZ;
    	}
    }

	//衝突したときの処理
	protected void onImpact(MovingObjectPosition movingObjectPosition)
    {
    	//当たったEntityがnullでなければ
    	if ( !worldObj.isRemote && movingObjectPosition.entityHit != null )
        {
        	//当たったEntityがナイフを投げたEntityでなければ
            if ( movingObjectPosition.entityHit != shootingEntity )
            {
            	float damage = 4.0F;
            	//当たったEntityがEntityLivingBaseに属しているなら
            	if(movingObjectPosition.entityHit instanceof EntityLivingBase)
            	{
            		//当たったEntityをEntityLivingBaseとして扱う
            		EntityLivingBase entityLivingBase = (EntityLivingBase)movingObjectPosition.entityHit;
            		//当たったEntityLivingBaseが無敵時間なら
            		if(entityLivingBase.hurtTime > 0)
            		{
            			boolean isLive = false;
            			if(entityLivingBase.getHealth() > 0.0F)
            			{
            				isLive = true;
            			}
            			//強引にダメージを入れ込む（おいw
            			entityLivingBase.heal(-damage);
            			if(entityLivingBase.getHealth() <= 0.0F && isLive)
            			{
            				entityLivingBase.onDeath(DamageSource.causeMobDamage(shootingEntity));
            			}
            			//衝突チェックをtrueにする
            			hit = true;
            		}
            		//当たったEntityLivingBaseが無敵時間でないなら
            		else
            		{
            			//ハート2.0の通常ダメージ
            			movingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeMobDamage(shootingEntity), damage);
            		}
            	}
            }
        }
    }

	/**
	 * 保存するデータの書き込み
	 * @param nbtTagCompound : NTBタグ
	 */
	@Override
    public void writeEntityToNBT(NBTTagCompound nbtTagCompound)
    {
        nbtTagCompound.setShort("xTile", (short)xTile);
        nbtTagCompound.setShort("yTile", (short)yTile);
        nbtTagCompound.setShort("zTile", (short)zTile);
        nbtTagCompound.setByte("inTile", (byte)inTile);
    	nbtTagCompound.setByte("inData", (byte)inData);
    	//nbtTagCompount.setByte("shootingEntity;", (byte)inData);
    	
        nbtTagCompound.setByte("inGround", (byte)(inGround ? 1 : 0));
    	
    	nbtTagCompound.setShort("color", (byte)getKnifeColor());
    	nbtTagCompound.setShort("zAngle",(short)(getKnifeZAngle() * 10000.0F));
    }

	/**
	 * 保存したデータの読み込み
	 * @param nbtTagCompound : NBTタグ
	 */
	@Override
    public void readEntityFromNBT(NBTTagCompound nbtTagCompound)
    {
        xTile = nbtTagCompound.getShort("xTile");
        yTile = nbtTagCompound.getShort("yTile");
        zTile = nbtTagCompound.getShort("zTile");
        inTile = nbtTagCompound.getByte("inTile") & 0xff;
    	inData = nbtTagCompound.getByte("inData") & 0xff;
        inGround = nbtTagCompound.getByte("inGround") == 1;
    	
    	setKnifeColor(nbtTagCompound.getByte("color"));
    	setKnifeZAngle((float)nbtTagCompound.getShort("zAngle") / 10000.0F);
    	
    }
    
    /**
     * 刺さっているものなら回収可能
     * @param player : 落ちているナイフに触れたプレイヤー
     */
	@Override
    public void onCollideWithPlayer(EntityPlayer player)
    {
        if (!worldObj.isRemote && inGround && player.inventory.addItemStackToInventory(new ItemStack(THKaguyaItems.silver_knife, 1, getKnifeColor() )))
        {
            worldObj.playSoundAtEntity(this, "random.pop", 4.0F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            player.onItemPickup(this, 1 );
            setDead();
        }
    }

	@Override
    public boolean canBeCollidedWith()
    {
        return true;
    }

	//物体の当たり判定　大きいと透明な判定が邪魔だよ
	@Override
    public float getCollisionBorderSize()
    {
        return 0.01F;
    }

    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
        setBeenAttacked();
        if (damagesource.getEntity() != null)
        {
            Vec3 vec3d = damagesource.getEntity().getLookVec();
            if (vec3d != null)
            {
                motionX = vec3d.xCoord;
                motionY = vec3d.yCoord;
                motionZ = vec3d.zCoord;
                accelerationX = motionX * 0.10000000000000001D;
                accelerationY = motionY * 0.10000000000000001D;
                accelerationZ = motionZ * 0.10000000000000001D;
            }
            if (damagesource.getEntity() instanceof EntityLivingBase)
            {
                shootingEntity = (EntityLivingBase)damagesource.getEntity();
            }
            return true;
        }
        else
        {
            return false;
        }
    }
    
	/**
	 * ナイフのカウントを設定
	 * @param count ナイフのカウント
	 */
	public void setKnifeCount(int count)
	{
		dataWatcher.updateObject(18, Integer.valueOf(count));
	}
	
	/**
	 * ナイフのカウントを取得
	 * @return ナイフのカウントを返す
	 */
	public int getKnifeCount()
	{
		return dataWatcher.getWatchableObjectInt(18);
	}
    
	/**
	 * ナイフの色を設定
	 * @param color ナイフの色
	 */
	public void setKnifeColor(int color)
	{
		dataWatcher.updateObject(19, Byte.valueOf((byte)color));
	}
	
	/**
	 * ナイフの色を取得
	 * @return ナイフの色を返す
	 */
	public byte getKnifeColor()
	{
		return dataWatcher.getWatchableObjectByte(19);
	}
	
	//ナイフの奥行き方向への角度設定する
	public void setKnifeZAngle(float angle)
	{
		dataWatcher.updateObject(20, Integer.valueOf((int)(angle * 10000.0F)));
	}
	
	//ナイフの奥行き方向への角度を返す
	public float getKnifeZAngle()
	{
		return (float)dataWatcher.getWatchableObjectInt(20) / 10000.0F;
	}

    public float getShadowSize()
    {
        return 0.0F;
    }

    public float getEntityBrightness(float f)
    {
        return 0.0F;
    }

    public int getEntityBrightnessForRender(float f)
    {
        return 0xf000f0;
    }
}
