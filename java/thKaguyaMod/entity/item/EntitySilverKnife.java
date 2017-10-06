package thKaguyaMod.entity.item;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
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

public class EntitySilverKnife
  extends Entity
{
  private final byte KNIFE_COLOR_BLUE = 0;
  private final byte KNIFE_COLOR_RED = 1;
  private final byte KNIFE_COLOR_GREEN = 2;
  private final byte KNIFE_COLOR_WHITE = 3;
  private byte color;
  private int xTile;
  private int yTile;
  private int zTile;
  private int inTile;
  private int inData;
  private boolean inGround;
  private int ticksInAir;
  public EntityLivingBase shootingEntity;
  public EntityLivingBase playerEntity;
  private int ticksInGround;
  public double accelerationX;
  public double accelerationY;
  public double accelerationZ;
  private boolean hit;
  private boolean rockhit;
  public float angleZ;
  private int lastTime;
  private double lastShotMotionX;
  private double lastShotMotionY;
  private double lastShotMotionZ;
  private int delay;
  
  public EntitySilverKnife(World world)
  {
    super(world);
    this.xTile = -1;
    this.yTile = -1;
    this.zTile = -1;
    this.inTile = 0;
    this.inData = 0;
    this.inGround = false;
    setSize(0.1F, 0.1F);
    
    this.hit = false;
  }
  
  public EntitySilverKnife(World world, Vec3 pos, Vec3 angle, double speed, int t)
  {
    this(world, null, pos, angle, speed, t);
  }
  
  public EntitySilverKnife(World world, EntityLivingBase entityLivingBase, Vec3 pos, Vec3 angle, double speed, int t)
  {
    super(world);
    setSize(0.1F, 0.1F);
    this.color = ((byte)t);
    setKnifeColor(this.color);
    this.shootingEntity = entityLivingBase;
    this.playerEntity = entityLivingBase;
    
    this.motionX = (angle.xCoord * speed);
    this.motionY = (angle.yCoord * speed);
    this.motionZ = (angle.zCoord * speed);
    this.lastShotMotionX = this.motionX;
    this.lastShotMotionY = this.motionY;
    this.lastShotMotionZ = this.motionZ;
    this.accelerationX = (this.motionX / 2.0D);
    this.accelerationY = (this.motionY / 2.0D);
    this.accelerationZ = (this.motionZ / 2.0D);
    setKnifeZAngle(90.0F);
    this.rotationYaw = ((float)Math.atan2(this.motionX, -this.motionZ) / 3.141593F * 180.0F);
    this.rotationPitch = ((float)Math.atan2(this.motionY, Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ)) / 3.141593F * 180.0F);
    setLocationAndAngles(pos.xCoord + angle.xCoord * speed, pos.yCoord + angle.yCoord * speed, pos.zCoord + angle.zCoord * speed, this.rotationYaw, this.rotationPitch);
    
    this.prevPosX = this.posX;
    this.prevPosY = this.posY;
    this.prevPosZ = this.posZ;
    this.lastTime = 0;
    
    this.delay = 0;
    if (t == 3) {
      this.delay = -30;
    }
  }
  
  protected boolean canTriggerWalking()
  {
    return false;
  }
  
  public boolean canBePushed()
  {
    return false;
  }
  
  public AxisAlignedBB getCollisionBox(Entity entity)
  {
    return null;
  }
  
  public AxisAlignedBB getBoundingBox()
  {
    return null;
  }
  
  public boolean isInRangeToRenderDist(double d)
  {
    double d1 = this.boundingBox.getAverageEdgeLength() * 4.0D;
    d1 *= 64.0D;
    return d < d1 * d1;
  }
  
  protected void entityInit()
  {
    this.dataWatcher.addObject(18, new Integer(0));
    this.dataWatcher.addObject(19, new Byte((byte)0));
    this.dataWatcher.addObject(20, new Integer(0));
  }
  
  public double getEntitySpeed()
  {
    return Math.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
  }
  
  public void delete()
  {
    if (!this.worldObj.isRemote) {
      setDead();
    }
  }
  
  public void onUpdate()
  {
    super.onUpdate();
    if (this.ticksExisted > 1)
    {
      if (this.ticksExisted <= this.lastTime)
      {
        setKnifeCount(getKnifeCount());
        return;
      }
      this.motionX = this.lastShotMotionX;
      this.motionY = this.lastShotMotionY;
      this.motionZ = this.lastShotMotionZ;
      if (!this.worldObj.isRemote)
      {
        this.delay += 1;
        if (this.delay >= 0) {
          setKnifeCount(getKnifeCount() + 1);
        }
      }
    }
    if (getKnifeCount() < 0)
    {
      setKnifeCount(getKnifeCount() + 1);
      return;
    }
    if ((!this.hit) && (this.ticksExisted >= 300)) {
      delete();
    } else if ((this.hit) && (this.ticksExisted >= 6000)) {
      delete();
    }
    if ((getKnifeColor() == 3) && (this.hit) && (this.ticksExisted >= 1)) {
      delete();
    }
    this.rockhit = false;
    if (this.inGround)
    {
      int hitBlockId = Block.getIdFromBlock(this.worldObj.getBlock(this.xTile, this.yTile, this.zTile));
      

      int hitBlockMetadata = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);
      if ((hitBlockId != this.inTile) || (hitBlockMetadata != this.inData))
      {
        this.inGround = false;
        this.motionX *= this.rand.nextFloat() * 0.2F;
        this.motionY *= this.rand.nextFloat() * 0.2F;
        this.motionZ *= this.rand.nextFloat() * 0.2F;
        this.ticksInGround = 0;
        setKnifeCount(3);
      }
      else
      {
        this.ticksInGround += 1;
        if (this.ticksInGround == 3000) {
          delete();
        }
      }
      return;
    }
    this.ticksInAir = 5;
    


    Vec3 posStart = THShotLib.pos_Entity(this);
    
    Vec3 posEnd = THShotLib.pos(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
    
    MovingObjectPosition movingObjectPosition = this.worldObj.func_147447_a(posStart, posEnd, false, true, false);
    
    posStart = THShotLib.pos_Entity(this);
    posEnd = THShotLib.pos(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
    if (movingObjectPosition != null) {
      posEnd = THShotLib.pos(movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
    }
    Entity hitEntity = null;
    
    List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(0.01D, 0.01D, 0.01D));
    
    double nearDistance = 999.0D;
    for (int j = 0; j < list.size(); j++)
    {
      Entity entityCheck = (Entity)list.get(j);
      if ((entityCheck.canBeCollidedWith()) && (entityCheck != this.shootingEntity) && (this.ticksInAir >= 5) && (!(entityCheck instanceof EntityTHShot))) {
        if ((entityCheck instanceof EntitySilverKnife))
        {
          EntitySilverKnife entitySilverKnife = (EntitySilverKnife)entityCheck;
          if ((getKnifeColor() != 3) && (entitySilverKnife.getKnifeColor() != 3))
          {
            float boxExtend = 0.01F;
            AxisAlignedBB axisalignedbb = entityCheck.boundingBox.expand(boxExtend, boxExtend, boxExtend);
            MovingObjectPosition movingObjectPosition1 = axisalignedbb.calculateIntercept(posStart, posEnd);
            if (movingObjectPosition1 != null)
            {
              double distance = posStart.distanceTo(movingObjectPosition1.hitVec);
              if ((distance < nearDistance) || (nearDistance == 999.0D))
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
          if (movingObjectPosition1 != null)
          {
            double d1 = posStart.distanceTo(movingObjectPosition1.hitVec);
            if ((d1 < nearDistance) || (nearDistance == 999.0D))
            {
              hitEntity = entityCheck;
              nearDistance = d1;
            }
          }
        }
      }
    }
    if (hitEntity != null) {
      movingObjectPosition = new MovingObjectPosition(hitEntity);
    }
    if ((movingObjectPosition != null) && (movingObjectPosition.entityHit != null) && ((movingObjectPosition.entityHit instanceof EntityPlayer)))
    {
      EntityPlayer entityPlayer = (EntityPlayer)movingObjectPosition.entityHit;
      if ((entityPlayer.capabilities.disableDamage) || ((this.shootingEntity instanceof EntityPlayer))) {
        movingObjectPosition = null;
      }
    }
    if (movingObjectPosition != null)
    {
      if (movingObjectPosition.entityHit != null) {
        if (!(movingObjectPosition.entityHit instanceof EntitySilverKnife))
        {
          onImpact(movingObjectPosition);
          
          float f1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
          

          this.motionX *= -0.210000000149012D;
          this.motionY *= -0.210000000149012D;
          this.motionZ *= -0.210000000149012D;
          this.accelerationX *= -0.01D;
          this.accelerationY *= -0.01D;
          this.accelerationZ *= -0.01D;
          this.rotationYaw += 180.0F;
          this.prevRotationYaw += 180.0F;
          
          setKnifeCount(3);
          
          this.hit = true;
          setKnifeZAngle(0.0F);
        }
      }
      this.xTile = movingObjectPosition.blockX;
      this.yTile = movingObjectPosition.blockY;
      this.zTile = movingObjectPosition.blockZ;
      
      this.inTile = Block.getIdFromBlock(this.worldObj.getBlock(this.xTile, this.yTile, this.zTile));
      this.inData = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);
      

      Material material = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile).getMaterial();
      if ((material == Material.rock) || (material == Material.iron) || (material == Material.glass) || (material == Material.ice))
      {
        if (Math.abs(getEntitySpeed()) > 0.07999999821186066D)
        {
          if ((movingObjectPosition.entityHit instanceof EntitySilverKnife))
          {
            EntitySilverKnife entitySilverKnife = (EntitySilverKnife)movingObjectPosition.entityHit;
            if ((entitySilverKnife.getKnifeColor() != 3) && (getKnifeColor() != 3))
            {
              this.worldObj.playSoundAtEntity(this, "random.orb", 0.5F, 5.0F);
              

              Random rand = new Random();
              float randangle = rand.nextInt(120);
              this.rotationYaw += 150.0F + randangle;
              this.motionX += Math.sin(randangle);
              this.motionZ += Math.cos(randangle);
              this.motionX *= -0.150000000149012D * Math.sin(randangle);
              this.motionY *= -0.150000000149012D;
              this.motionZ *= -0.150000000149012D * Math.cos(randangle);
              this.accelerationX *= -0.01D;
              this.accelerationZ *= -0.01D;
              if (this.posY > movingObjectPosition.entityHit.posY) {
                this.accelerationY *= 0.01D;
              } else {
                this.accelerationY *= -1.2D;
              }
            }
          }
          if (movingObjectPosition.entityHit == null)
          {
            this.worldObj.playSoundAtEntity(this, "random.orb", 0.5F, 5.0F);
            

            double returnRate = 0.25D * getEntitySpeed();
            if (getKnifeColor() == 0) {
              returnRate = 0.01D;
            }
            double supPosX1 = this.posX;
            double supPosX2 = this.posX + this.motionX;
            double supPosY1 = this.posY;
            double supPosY2 = this.posY + this.motionY;
            double supPosZ1 = this.posZ;
            double supPosZ2 = this.posZ + this.motionZ;
            
            Vec3 supVec3d1 = Vec3.createVectorHelper(supPosX1, supPosY1, supPosZ1);
            
            Vec3 supVec3d2 = Vec3.createVectorHelper(supPosX2, supPosY2, supPosZ2);
            

            MovingObjectPosition supMovingObjectPosition = this.worldObj.func_147447_a(supVec3d1, supVec3d2, false, true, false);
            while (supMovingObjectPosition != null)
            {
              returnRate = 0.25D * getEntitySpeed();
              if (getKnifeColor() != 1) {
                returnRate = 1.E-005D;
              }
              switch (supMovingObjectPosition.sideHit)
              {
              case 0: 
                this.motionY *= -returnRate;
                this.accelerationY *= -returnRate;
                break;
              case 1: 
                this.motionY *= -returnRate;
                this.accelerationY *= -returnRate;
                setKnifeCount(0);
                if (this.motionY < 0.2D)
                {
                  this.motionX *= 0.3D;
                  this.motionZ *= 0.3D;
                }
                if (getKnifeZAngle() > 0.0F) {
                  setKnifeZAngle(getKnifeZAngle() - 21.0F);
                } else {
                  setKnifeZAngle(0.0F);
                }
                break;
              case 2: 
                this.motionZ *= -returnRate;
                this.accelerationZ *= -returnRate;
                break;
              case 3: 
                this.motionZ *= -returnRate;
                this.accelerationZ *= -returnRate;
                break;
              case 4: 
                this.motionX *= -returnRate;
                this.accelerationX *= -returnRate;
                break;
              default: 
                this.motionX *= -returnRate;
                this.accelerationX *= -returnRate;
              }
              if (getKnifeColor() != 1)
              {
                this.motionX *= 0.3D;
                this.motionY *= 0.3D;
                this.motionZ *= 0.3D;
                this.accelerationX *= 0.3D;
                this.accelerationY *= 0.3D;
                this.accelerationZ *= 0.3D;
              }
              supPosX1 += (supMovingObjectPosition.hitVec.xCoord - supPosX1) * 0.999D;
              supPosX2 = supPosX1 + this.motionX;
              supPosY1 += (supMovingObjectPosition.hitVec.yCoord - supPosY1) * 0.999D;
              
              supPosY2 = supPosY1 + this.motionY;
              supPosZ1 += (supMovingObjectPosition.hitVec.zCoord - supPosZ1) * 0.999D;
              supPosZ2 = supPosZ1 + this.motionZ;
              
              supVec3d1 = Vec3.createVectorHelper(supPosX1, supPosY1, supPosZ1);
              
              supVec3d2 = Vec3.createVectorHelper(supPosX2, supPosY2, supPosZ2);
              

              supMovingObjectPosition = this.worldObj.func_147447_a(supVec3d1, supVec3d2, false, true, false);
            }
          }
          if (Math.abs(this.motionX) < 0.1D) {
            this.motionX = 0.0D;
          }
          if (Math.abs(this.motionZ) < 0.1D) {
            this.motionZ = 0.0D;
          }
          setKnifeCount(3);
          this.hit = false;
        }
        else if (material != Material.air)
        {
          if (movingObjectPosition.sideHit == 1)
          {
            this.rotationPitch = 0.0F;
            this.motionX = (movingObjectPosition.hitVec.xCoord - this.posX);
            this.motionY = (movingObjectPosition.hitVec.yCoord - this.posY);
            this.motionZ = (movingObjectPosition.hitVec.zCoord - this.posZ);
            this.accelerationX = 0.0D;
            this.accelerationY = 0.0D;
            this.accelerationZ = 0.0D;
            
            double f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
            
            this.posX -= this.motionX / f2 * 0.0500000007450581D;
            this.posY -= this.motionY / f2 * 0.0500000007450581D;
            this.posZ -= this.motionZ / f2 * 0.0500000007450581D;
            
            this.inGround = true;
            
            this.hit = true;
            
            this.rockhit = true;
            setKnifeCount(0);
            setKnifeZAngle(0.0F);
          }
          else if (movingObjectPosition != null)
          {
            this.motionX = (movingObjectPosition.hitVec.xCoord - this.posX);
            this.motionY = (movingObjectPosition.hitVec.yCoord - this.posY);
            this.motionZ = (movingObjectPosition.hitVec.zCoord - this.posZ);
            this.accelerationX *= -0.01D;
            this.accelerationY *= -0.01D;
            this.accelerationZ *= -0.01D;
            
            double f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
            
            this.posX -= this.motionX / f2 * 0.0500000007450581D;
            this.posY -= this.motionY / f2 * 0.0500000007450581D;
            this.posZ -= this.motionZ / f2 * 0.0500000007450581D;
            
            this.inGround = true;
            
            this.hit = true;
            
            this.rockhit = true;
          }
        }
        else
        {
          this.hit = false;
        }
      }
      else if (material != Material.air)
      {
        this.motionX = ((float)(movingObjectPosition.hitVec.xCoord - this.posX));
        this.motionY = ((float)(movingObjectPosition.hitVec.yCoord - this.posY));
        this.motionZ = ((float)(movingObjectPosition.hitVec.zCoord - this.posZ));
        this.accelerationX *= -0.01D;
        this.accelerationY *= -0.01D;
        this.accelerationZ *= -0.01D;
        float f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
        this.posX -= this.motionX / f2 * 0.0500000007450581D;
        this.posY -= this.motionY / f2 * 0.0500000007450581D;
        this.posZ -= this.motionZ / f2 * 0.0500000007450581D;
        
        this.inGround = true;
        setKnifeCount(3);
        this.hit = true;
      }
    }
    if (this.delay < 0) {
      return;
    }
    if ((!this.worldObj.isRemote) && (getKnifeCount() > 2) && (this.delay >= 0))
    {
      this.posX += this.motionX;
      this.posY += this.motionY;
      this.posZ += this.motionZ;
    }
    if ((getKnifeColor() == 2) && (Math.abs(getEntitySpeed()) > 0.07999999821186066D) && (this.ticksExisted < 60) && (this.ticksExisted > 2) && (this.ticksExisted % 2 == 1))
    {
      Vec3 angle = THShotLib.angle(this.motionX, this.motionY, this.motionZ);
      THShotLib.getVectorNomalize(angle);
      if (!this.worldObj.isRemote)
      {
        EntitySilverKnife knife;
        if ((this.shootingEntity == null) || (this.playerEntity == null))
        {
          knife = new EntitySilverKnife(this.worldObj, THShotLib.pos_Entity(this), angle, getEntitySpeed(), 3);
          this.worldObj.spawnEntityInWorld(knife);
        }
        else
        {
          knife = THKaguyaLib.createSilverKnife(this.shootingEntity, THShotLib.pos_Entity(this), angle, getEntitySpeed(), 3, 0);
        }
        knife.accelerationX = this.accelerationX;
        knife.accelerationY = this.accelerationY;
        knife.accelerationZ = this.accelerationZ;
      }
    }
    float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
    this.rotationYaw = ((float)(Math.atan2(this.motionX, -this.motionZ) * 180.0D / 3.141592741012573D));
    if (!this.worldObj.isRemote) {
      for (this.rotationPitch = ((float)(Math.atan2(this.motionY, f) * 180.0D / 3.141592741012573D)); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {}
    }
    while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
      this.prevRotationPitch += 360.0F;
    }
    while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
      this.prevRotationYaw -= 360.0F;
    }
    while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
      this.prevRotationYaw += 360.0F;
    }
    this.rotationPitch = (this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 1.0F);
    if (this.rockhit == true) {
      this.rotationPitch = 0.0F;
    }
    this.rotationYaw = (this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 1.0F);
    float slowDownRate = 0.95F;
    if (isInWater()) {
      slowDownRate = 0.8F;
    }
    if (this.delay >= 0)
    {
      this.motionX += this.accelerationX;
      this.motionY += this.accelerationY;
      this.motionZ += this.accelerationZ;
      



      this.motionY -= 0.008D * getKnifeCount();
      

      this.motionX *= slowDownRate;
      this.motionY *= slowDownRate;
      this.motionZ *= slowDownRate;
    }
    setPosition(this.posX, this.posY, this.posZ);
    if (this.ticksExisted > this.lastTime)
    {
      this.lastTime = this.ticksExisted;
      this.lastShotMotionX = this.motionX;
      this.lastShotMotionY = this.motionY;
      this.lastShotMotionZ = this.motionZ;
    }
  }
  
  protected void onImpact(MovingObjectPosition movingObjectPosition)
  {
    if ((!this.worldObj.isRemote) && (movingObjectPosition.entityHit != null)) {
      if (movingObjectPosition.entityHit != this.shootingEntity)
      {
        float damage = 4.0F;
        if ((movingObjectPosition.entityHit instanceof EntityLivingBase))
        {
          EntityLivingBase entityLivingBase = (EntityLivingBase)movingObjectPosition.entityHit;
          if (entityLivingBase.hurtTime > 0)
          {
            boolean isLive = false;
            if (entityLivingBase.getHealth() > 0.0F) {
              isLive = true;
            }
            entityLivingBase.heal(-damage);
            if ((entityLivingBase.getHealth() <= 0.0F) && (isLive)) {
              entityLivingBase.onDeath(DamageSource.causeMobDamage(this.shootingEntity));
            }
            this.hit = true;
          }
          else
          {
            movingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.shootingEntity), damage);
          }
        }
      }
    }
  }
  
  public void writeEntityToNBT(NBTTagCompound nbtTagCompound)
  {
    nbtTagCompound.setShort("xTile", (short)this.xTile);
    nbtTagCompound.setShort("yTile", (short)this.yTile);
    nbtTagCompound.setShort("zTile", (short)this.zTile);
    nbtTagCompound.setByte("inTile", (byte)this.inTile);
    nbtTagCompound.setByte("inData", (byte)this.inData);
    

    nbtTagCompound.setByte("inGround", (byte)(this.inGround ? 1 : 0));
    
    nbtTagCompound.setShort("color", (short)getKnifeColor());
    nbtTagCompound.setShort("zAngle", (short)(int)(getKnifeZAngle() * 10000.0F));
  }
  
  public void readEntityFromNBT(NBTTagCompound nbtTagCompound)
  {
    this.xTile = nbtTagCompound.getShort("xTile");
    this.yTile = nbtTagCompound.getShort("yTile");
    this.zTile = nbtTagCompound.getShort("zTile");
    this.inTile = (nbtTagCompound.getByte("inTile") & 0xFF);
    this.inData = (nbtTagCompound.getByte("inData") & 0xFF);
    this.inGround = (nbtTagCompound.getByte("inGround") == 1);
    
    setKnifeColor(nbtTagCompound.getByte("color"));
    setKnifeZAngle(nbtTagCompound.getShort("zAngle") / 10000.0F);
  }
  
  public void onCollideWithPlayer(EntityPlayer player)
  {
    if ((!this.worldObj.isRemote) && (this.inGround) && (player.inventory.addItemStackToInventory(new ItemStack(THKaguyaItems.silver_knife, 1, getKnifeColor()))))
    {
      this.worldObj.playSoundAtEntity(this, "random.pop", 4.0F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
      player.onItemPickup(this, 1);
      setDead();
    }
  }
  
  public boolean canBeCollidedWith()
  {
    return true;
  }
  
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
        this.motionX = vec3d.xCoord;
        this.motionY = vec3d.yCoord;
        this.motionZ = vec3d.zCoord;
        this.accelerationX = (this.motionX * 0.1D);
        this.accelerationY = (this.motionY * 0.1D);
        this.accelerationZ = (this.motionZ * 0.1D);
      }
      if ((damagesource.getEntity() instanceof EntityLivingBase)) {
        this.shootingEntity = ((EntityLivingBase)damagesource.getEntity());
      }
      return true;
    }
    return false;
  }
  
  public void setKnifeCount(int count)
  {
    this.dataWatcher.updateObject(18, Integer.valueOf(count));
  }
  
  public int getKnifeCount()
  {
    return this.dataWatcher.getWatchableObjectInt(18);
  }
  
  public void setKnifeColor(int color)
  {
    this.dataWatcher.updateObject(19, Byte.valueOf((byte)color));
  }
  
  public byte getKnifeColor()
  {
    return this.dataWatcher.getWatchableObjectByte(19);
  }
  
  public void setKnifeZAngle(float angle)
  {
    this.dataWatcher.updateObject(20, Integer.valueOf((int)(angle * 10000.0F)));
  }
  
  public float getKnifeZAngle()
  {
    return this.dataWatcher.getWatchableObjectInt(20) / 10000.0F;
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
    return 15728880;
  }
}
