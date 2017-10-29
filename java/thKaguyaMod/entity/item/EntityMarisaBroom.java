package thKaguyaMod.entity.item;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import thKaguyaMod.init.THKaguyaItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/** 魔法の箒 */
public class EntityMarisaBroom extends Entity
{
    private boolean isEmpty;
    private double speedMultiplier;
    private int boatPosRotationIncrements;
    private double broomX;
    private double broomY;
    private double broomZ;
    private double broomYaw;
    private double broomPitch;
    @SideOnly(Side.CLIENT)
    private double velocityX;
    @SideOnly(Side.CLIENT)
    private double velocityY;
    @SideOnly(Side.CLIENT)
    private double velocityZ;

    public EntityMarisaBroom(World world)
    {
        super(world);
        this.isEmpty = true;
        this.speedMultiplier = 0.07D;
        this.preventEntitySpawning = true;
        this.setSize(0.9F, 0.5F);
        this.yOffset = 0.8F;
    }
    
    public EntityMarisaBroom(World world, double x, double y, double z)
    {
        this(world);
        this.setPosition(x, y + (double)this.yOffset, z);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
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
        this.dataWatcher.addObject(17, new Integer(0));
        this.dataWatcher.addObject(18, new Integer(1));
        this.dataWatcher.addObject(19, new Float(0.0F));
    }

    /**
     * Returns a boundingBox used to collide the entity with other entities and blocks. This enables the entity to be
     * pushable on contact, like boats or minecarts.
     */
    @Override
    public AxisAlignedBB getCollisionBox(Entity entity)
    {
        return entity.boundingBox;
    }

    /**
     * returns the bounding box for this entity
     */
    @Override
    public AxisAlignedBB getBoundingBox()
    {
        return this.boundingBox;
    }

    /**
     * Returns true if this entity should push and be pushed by other entities when colliding.
     */
    @Override
    public boolean canBePushed()
    {
        return true;
    }

    

	//魔理沙の箒に乗っているEntityが乗る高さを返す
    @Override
    public double getMountedYOffset()
    {
        return  -0.15D;
    }

	//Entityに攻撃されたときに呼ばれる
    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float par2)
    {
    	//無敵ならfalseを返す
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        //無敵でないなら
        else if (!this.worldObj.isRemote && !this.isDead)
        {
            this.setForwardDirection(-this.getForwardDirection());
            this.setTimeSinceHit(10);
            this.setDamageTaken(this.getDamageTaken() + par2 * 10.0F);
            this.setBeenAttacked();
            boolean flag = damageSource.getEntity() instanceof EntityPlayer && ((EntityPlayer)damageSource.getEntity()).capabilities.isCreativeMode;

            if (flag || this.getDamageTaken() > 40.0F)//相手がプレイヤーでクリエイティブ、または、４以上のダメージを受けているなら
            {
                if (this.riddenByEntity != null)
                {
                    this.riddenByEntity.mountEntity(this);
                }

                if (!flag)
                {
                    this.dropItem(THKaguyaItems.magic_bloom, 1);
                }

                this.setDead();
            }

            return true;
        }
        else
        {
            return true;
        }
    }

    @SideOnly(Side.CLIENT)

	//攻撃された時のアニメーション関連
    @Override
    public void performHurtAnimation()
    {
        this.setForwardDirection(-this.getForwardDirection());
        this.setTimeSinceHit(10);
        this.setDamageTaken(this.getDamageTaken() * 11.0F);
    }

	//触れることができるかどうか
    @Override
    public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Sets the position and rotation. Only difference from the other one is no bounding on the rotation. Args: posX,
     * posY, posZ, yaw, pitch
     */
    public void setPositionAndRotation2(double x, double y, double z, float yaw, float pitch, int par9)
    {
        if (this.isEmpty)
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

            this.boatPosRotationIncrements = 3;
        }

        this.broomX = x;
        this.broomY = y;
        this.broomZ = z;
        this.broomYaw = (double)yaw;
        this.broomPitch = (double)pitch;
        this.motionX = this.velocityX;
        this.motionY = this.velocityY;
        this.motionZ = this.velocityZ;
    }

    @SideOnly(Side.CLIENT)

	//ベクトルを設定
    public void setVelocity(double x, double y, double z)
    {
        this.velocityX = this.motionX = x;
        this.velocityY = this.motionY = y;
        this.velocityZ = this.motionZ = z;
    }
    
	@SideOnly(Side.CLIENT)
	public boolean isPushKeyUp()
	{
    	Minecraft mc = Minecraft.getMinecraft();
    	GameSettings gameSettings = mc.gameSettings;
    	return gameSettings.keyBindForward.getIsKeyPressed();
	}
	
	@SideOnly(Side.CLIENT)
	public boolean isPushKeyBack()
	{
    	Minecraft mc = Minecraft.getMinecraft();
    	GameSettings gameSettings = mc.gameSettings;
    	return gameSettings.keyBindBack.getIsKeyPressed();
	}
	
	@SideOnly(Side.CLIENT)
	public boolean isPushKeyRight()
	{
    	Minecraft mc = Minecraft.getMinecraft();
    	GameSettings gameSettings = mc.gameSettings;
    	return gameSettings.keyBindRight.getIsKeyPressed();
	}
	
	@SideOnly(Side.CLIENT)
	public boolean isPushKeyLeft()
	{
    	Minecraft mc = Minecraft.getMinecraft();
    	GameSettings gameSettings = mc.gameSettings;
    	return gameSettings.keyBindLeft.getIsKeyPressed();
	}
	
	@SideOnly(Side.CLIENT)
	public boolean isPushKeyJump()
	{
    	Minecraft mc = Minecraft.getMinecraft();
    	GameSettings gameSettings = mc.gameSettings;
    	return gameSettings.keyBindJump.getIsKeyPressed();
	}

	/** 毎tick呼ばれる処理 */
    @Override
    public void onUpdate()
    {
        super.onUpdate();

    	//ダメージアニメーション関連
        if (this.getTimeSinceHit() > 0)
        {
            this.setTimeSinceHit(this.getTimeSinceHit() - 1);
        }

        if (this.getDamageTaken() > 0.0F)
        {
            this.setDamageTaken(this.getDamageTaken() - 1.0F);
        }
    	
		//前のポジションを今のポジションにする
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
    	
        byte b0 = 5;
        double d0 = 0.0D;

        for (int i = 0; i < b0; ++i)
        {
            double d1 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double)(i + 0) / (double)b0 - 0.125D;
            double d2 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double)(i + 1) / (double)b0 - 0.125D;
            AxisAlignedBB axisalignedbb = AxisAlignedBB.getBoundingBox(this.boundingBox.minX, d1, this.boundingBox.minZ, this.boundingBox.maxX, d2, this.boundingBox.maxZ);

            if (this.worldObj.isAABBInMaterial(axisalignedbb, Material.water))
            {
                d0 += 1.0D / (double)b0;
            }
        }

        double motionXZ = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);//平面方向の移動量
        double cos_yaw_rad;//d4
        double sin_yaw_rad;//d5

        double d10;
        double d11;

        if (this.worldObj.isRemote && this.isEmpty)
        {
        	//箒の回転増加量が０より大きいなら
            if (this.boatPosRotationIncrements > 0)
            {
            	
                double x_increments = this.posX + (this.broomX - this.posX) / (double)this.boatPosRotationIncrements;
                double y_increments = this.posY + (this.broomY - this.posY) / (double)this.boatPosRotationIncrements;
                double z_increments = this.posZ + (this.broomZ - this.posZ) / (double)this.boatPosRotationIncrements;
                d10 = MathHelper.wrapAngleTo180_double(this.broomYaw - (double)this.rotationYaw);
                this.rotationYaw = (float)((double)this.rotationYaw + d10 / (double)this.boatPosRotationIncrements);
                this.rotationPitch = (float)((double)this.rotationPitch + (this.broomPitch - (double)this.rotationPitch) / (double)this.boatPosRotationIncrements);
                --this.boatPosRotationIncrements;
                this.setPosition(x_increments, y_increments, z_increments);
                this.setRotation(this.rotationYaw, this.rotationPitch);
            }
        	//０より小さいなら
            else
            {
                double x_increments = this.posX + this.motionX;
                double y_increments = this.posY + this.motionY;
                double z_increments = this.posZ + this.motionZ;
                this.setPosition(x_increments, y_increments, z_increments);

                if (this.onGround)//地面の上なら
                {
                	//移動量を半減する
                    this.motionX *= 0.5D;
                    this.motionY *= 0.5D;
                    this.motionZ *= 0.5D;
                }

                this.motionX *= 0.9900000095367432D;
                this.motionY *= 0.949999988079071D;
                this.motionZ *= 0.9900000095367432D;
            }
        }
        else
        {
            if (d0 < 1.0D)
            {
                double d4 = d0 * 2.0D - 1.0D;
            }
            else
            {
                /*if (this.motionY < 0.0D)//下降気味なら
                {
                    this.motionY /= 2.0D;//下降速度を半減
                }

                this.motionY += 0.007000000216066837D;//若干浮遊させる
            */}

            //riddenByEntity = new EntityZombie(worldObj);
        	//Entityが乗っているなら
            if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer)
            {
            	
            	EntityPlayer living = (EntityPlayer)this.riddenByEntity;
            	
        		boolean move_up = true;
        		boolean move_right = false;
        		boolean move_left = false;
        		boolean move_back = false;
        		boolean move_jump = false;
            	
            	
            	if(worldObj.isRemote)
            	{
            		move_up = isPushKeyUp();
            		move_right = isPushKeyRight();
            		move_left = isPushKeyLeft();
            		move_back = isPushKeyBack();
            		move_jump = isPushKeyJump();
            	}
            	
            	/*@SideOnly(Side.CLIENT)
            	//if(worldObj.isRemote)
            	{
	            	Minecraft mc = Minecraft.getMinecraft();
	            	GameSettings gameSettings = mc.gameSettings;
	            	//gameSettings.sendSettingsToServer();
	            	move_up = gameSettings.keyBindForward.getIsKeyPressed();
	            	move_right = gameSettings.keyBindRight.getIsKeyPressed();
	            	move_left = gameSettings.keyBindLeft.getIsKeyPressed();
	            	move_back = gameSettings.keyBindBack.getIsKeyPressed();
	            	move_jump = gameSettings.keyBindJump.getIsKeyPressed();
            	}*/
	            	//KeyBinding key;
	            	
	            	double acceleration = 0.04D;
	            	double acceleration2 = 0.08D;
	            	if(move_jump)
	            	{
	            		acceleration = 0.5D;
	            		//motionY = -Math.sin(riddenByEntity.rotationPitch / 180F * 3.141593F) * acceleration;
		            	//if(gameSettings.keyBindForward.getIsKeyPressed() && gameSettings.keyBindBack.getIsKeyPressed())
		            	{
		            		motionX *= 0.0D;
		            		motionZ *= 0.0D;
		            		motionY *= 0.0D;
		            	}
		            	//else
		            	{
		            		
			            	if(move_up)
			            	{
			            		motionY = -Math.sin(-90F / 180F * 3.141593F) * acceleration;
			            		//motionX = 0.0D;
			            		//motionZ = 0.0D;
			            		//motionX = -Math.sin(riddenByEntity.rotationYaw / 180F * (float)Math.PI) * acceleration;
			            		//motionZ =  Math.cos(riddenByEntity.rotationYaw / 180F * (float)Math.PI) * acceleration;
			            	}
			            	if(move_back)
			            	{
			            		motionY = -Math.sin(90F / 180F * 3.141593F) * acceleration;
			            		//motionX = 0.0D;
			            		//motionZ = 0.0D;
			            		//motionX =  Math.sin(riddenByEntity.rotationYaw / 180F * (float)Math.PI) * acceleration;
			            		//motionZ = -Math.cos(riddenByEntity.rotationYaw / 180F * (float)Math.PI) * acceleration;
			            	}
		            	}
		            	if(move_right)
		            	{
		            		motionX =  Math.sin((rotationYaw + 0F) / 180F * (float)Math.PI) * acceleration;
		            		motionZ = -Math.cos((rotationYaw + 0F) / 180F * (float)Math.PI) * acceleration;
		            	}
		            	if(move_left)
		            	{
		            		motionX =  Math.sin((rotationYaw - 180F) / 180F * (float)Math.PI) * acceleration;
		            		motionZ = -Math.cos((rotationYaw - 180F) / 180F * (float)Math.PI) * acceleration;
		            	}
		            	double moveSpeed =  Math.sqrt(motionX * motionX + motionY * motionY + motionZ * motionZ);
		            	if(moveSpeed > 0.5D)
		            	{
		            		motionX *= moveSpeed;
		            		motionY *= moveSpeed;
		            		motionZ *= moveSpeed;
		            	}
	            	}
	            	else
	            	{
	            		motionY += -Math.sin(rotationPitch / 180F * 3.141593F) * acceleration;
		            	if(!move_up && !move_back &&
		            			!move_right && !move_left)
		            	{
		            		motionX *= 0.97D;
		            		motionZ *= 0.97D;
		            		motionY *= 0.97D;
		            	}
		            	else
		            	{
			            	if(move_up)
			            	{
			            		motionX +=  Math.sin((rotationYaw - 90F) / 180F * (float)Math.PI) * acceleration;
			            		motionZ += -Math.cos((rotationYaw - 90F) / 180F * (float)Math.PI) * acceleration;
			            	}
			            	if(move_back)
			            	{
			            		motionX += -Math.sin((rotationYaw - 90F) / 180F * (float)Math.PI) * acceleration2;
			            		motionZ +=  Math.cos((rotationYaw - 90F) / 180F * (float)Math.PI) * acceleration2;
			            	}
		            	}
		            	if(move_right)
		            	{
		            		motionX +=  Math.sin((rotationYaw + 0F) / 180F * (float)Math.PI) * acceleration;
		            		motionZ += -Math.cos((rotationYaw + 0F) / 180F * (float)Math.PI) * acceleration;
		            	}
		            	if(move_left)
		            	{
		            		motionX +=  Math.sin((rotationYaw - 180F) / 180F * (float)Math.PI) * acceleration;
		            		motionZ += -Math.cos((rotationYaw - 180F) / 180F * (float)Math.PI) * acceleration;
		            	}
	            	
            	}

            	float f = this.riddenByEntity.rotationYaw;// + -living.moveStrafing * 90.0F;
            	//f *= 20;
            	speedMultiplier = 0.6D;

            	
            	float pitch = riddenByEntity.rotationPitch;// * 3F;
            	rotationPitch = pitch;

            	if(pitch > 90F)
            	{
            		pitch = 90F;
            	}
            	else if(pitch < -90F)
            	{
            		pitch = -90F;
            	}
            	//Entityの移動量に箒の移動量を合わす
            	//motionX -=  Math.sin(riddenByEntity.rotationYaw / 180F * 3.141593F) * speedMultiplier * 0.6D;
            	//motionZ +=  Math.cos(riddenByEntity.rotationYaw / 180F * 3.141593F) * speedMultiplier * 0.6D;
            	// = -Math.sin(pitch / 180F * 3.141593F) * speedMultiplier * 1.0D;
            	if(pitch < 0F)
            	{
            		pitch *= 0.1F;
            	}
            	
            	//rotationYaw = living.moveStrafing;
            	rotationYaw = riddenByEntity.rotationYaw - 90F;
            	
            	
            	double moveSpeed =  Math.sqrt(motionX * motionX + motionY * motionY + motionZ * motionZ);
            	if(moveSpeed > 0.8D)
            	{
            		motionX *= moveSpeed;
            		motionY *= moveSpeed;
            		motionZ *= moveSpeed;
            	}
            }
            

            /*double thisMotionXZ = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);

            if (motionXZ > 0.7D)//一定の水平移動量があるなら
            {
                double motionXZ_rate = 0.7D / motionXZ;
                this.motionX *= motionXZ_rate;//一定の速度を超えないように移動量を制限
                this.motionZ *= motionXZ_rate;
                motionXZ = 0.7D;
            }

            if (thisMotionXZ > motionXZ && this.speedMultiplier < 0.7D)
            {
                this.speedMultiplier += (0.70D - this.speedMultiplier) / 70.0D;

                if (this.speedMultiplier > 0.7D)
                {
                    this.speedMultiplier = 0.7D;
                }
            }
            else
            {
                this.speedMultiplier -= (this.speedMultiplier - 0.07D) / 70.0D;

                if (this.speedMultiplier < 0.07D)
                {
                    this.speedMultiplier = 0.07D;
                }
            }*/

            /*if (this.onGround)
            {
                this.motionX *= 0.5D;
                this.motionY *= 0.5D;
                this.motionZ *= 0.5D;
            }*/
            if(this.inWater)
            {
            	motionX *= 0.5D;
            	motionY *= 0.5D;
            	motionZ *= 0.5D;
            }

            this.moveEntity(this.motionX, this.motionY, this.motionZ);

            /*if (this.isCollidedHorizontally && motionXZ > 0.2D)
            {
                if (!this.worldObj.isRemote && !this.isDead)
                {
                    this.setDead();
                    int k;

                    for (k = 0; k < 3; ++k)
                    {
                        this.dropItemWithOffset(Block.planks.blockID, 1, 0.0F);
                    }

                    for (k = 0; k < 2; ++k)
                    {
                        this.dropItemWithOffset(Item.stick.itemID, 1, 0.0F);
                    }
                }
            }
            else*/
            {
                this.motionX *= 0.9900000095367432D;
                this.motionY *= 0.949999988079071D;
                this.motionZ *= 0.9900000095367432D;
            }

        	//角度の修正********
            //this.rotationPitch = 0.0F;//垂直角度は常に一定で動かない
            /*double thisYaw = (double)this.rotationYaw;
            double prevXtoX = this.prevPosX - this.posX;
            double prevZtoZ = this.prevPosZ - this.posZ;

            if (prevXtoX * prevXtoX + prevZtoZ * prevZtoZ > 0.001D)//少しでも動いているなら
            {
                thisYaw = (double)((float)(Math.atan2(prevZtoZ, prevXtoX) * 180.0D / Math.PI));
            }

            double d12 = MathHelper.wrapAngleTo180_double(thisYaw - (double)this.rotationYaw);

            if (d12 > 20.0D)
            {
                d12 = 20.0D;
            }

            if (d12 < -20.0D)
            {
                d12 = -20.0D;
            }

            this.rotationYaw = (float)((double)this.rotationYaw + d12);*/
            
            this.setRotation(this.rotationYaw, this.rotationPitch);
        	//*******************

            if (!this.worldObj.isRemote)
            {
                List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(0.20000000298023224D, 0.0D, 0.20000000298023224D));
                int l;

                if (list != null && !list.isEmpty())
                {
                    for (l = 0; l < list.size(); ++l)
                    {
                        Entity entity = (Entity)list.get(l);

                        if (entity != this.riddenByEntity && entity.canBePushed() && entity instanceof EntityMarisaBroom)
                        {
                            entity.applyEntityCollision(this);
                        }
                    }
                }

                /*for (l = 0; l < 4; ++l)
                {
                    int i1 = MathHelper.floor_double(this.posX + ((double)(l % 2) - 0.5D) * 0.8D);
                    int j1 = MathHelper.floor_double(this.posZ + ((double)(l / 2) - 0.5D) * 0.8D);

                    for (int k1 = 0; k1 < 2; ++k1)
                    {
                        int l1 = MathHelper.floor_double(this.posY) + k1;
                        int i2 = this.worldObj.getBlockId(i1, l1, j1);//箒のいる場所のブロックIDを取得

                        if (i2 == Block.snow.blockID)//雪に衝突したなら
                        {
                            this.worldObj.setBlockToAir(i1, l1, j1);//空気ブロックに入れ替える
                        }
                        else if (i2 == Block.waterlily.blockID)//蓮に衝突したなら
                        {
                            this.worldObj.destroyBlock(i1, l1, j1, true);//蓮を破壊し、蓮をアイテム化する
                        }
                    }
                }*/

            	//乗っているEntityがおり、それが死んでいるなら
                if (this.riddenByEntity != null && this.riddenByEntity.isDead)
                {
                    this.riddenByEntity = null;//乗っているEntityをいないことにする
                }
                else
                {
                	//riddenByEntity.fallDistance = 0.0F;
                }
                if(this.riddenByEntity != null)
                {
                	riddenByEntity.fallDistance = 0.0F;
                	fallDistance = 0.0F;
                }
                else
                {
                	motionX *= 0.9D;
                	motionY -= 0.03D;
                	motionZ *= 0.9D;
                	if(rotationPitch > 1.0F)
                	{
                		rotationPitch--;
                	}
                	else if(rotationPitch < -1.0F)
                	{
                		rotationPitch++;
                	}
                	if(onGround)
                	{
                		motionY += 0.06D;
                	}
                }
            }
        }
        

    }
    

	//乗っているEntityの位置を更新
    public void updateRiderPosition2()
    {
        if (this.riddenByEntity != null)
        {
        	//
            double d0 = 0D;//Math.cos((double)this.rotationYaw * Math.PI / 180.0D) * 0.4D;
            double d1 = 0D;//Math.sin((double)this.rotationYaw * Math.PI / 180.0D) * 0.4D;
            this.riddenByEntity.setPosition(this.posX + d0, this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ + d1);
        }
    }
    
    /**
     * Handles updating while being ridden by an entity
     */
    public void updateRidden()
    {
    	super.updateRidden();
        if (this.ridingEntity.isDead)
        {
            this.ridingEntity = null;
        }
        else
        {
        	updateRiderPosition2();
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
	//public boolean func_130002_c(EntityPlayer entityPlayer)
    public boolean interactFirst(EntityPlayer entityPlayer)
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
        this.isEmpty = par1;
    }
}
