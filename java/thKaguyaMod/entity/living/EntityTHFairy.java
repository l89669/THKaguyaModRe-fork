 package thKaguyaMod.entity.living;

import java.util.Calendar;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import thKaguyaMod.LaserData;
import thKaguyaMod.ShotData;
import thKaguyaMod.THShotLib;
import thKaguyaMod.init.THKaguyaConfig;
import thKaguyaMod.init.THKaguyaItems;
import thKaguyaMod.item.ItemTHShot;
import thKaguyaMod.registry.DanmakuPatternRegistry;

/** 妖精 */
public class EntityTHFairy extends EntityDanmakuMob
{

    public EntityTHFairy(World world)
    {
        super(world);
        
        this.setSize(0.6F, 1.5F);//MOBの当たり判定の大きさ 横奥行き、高さ、大きさ
    	lastTime = 0;
    	
    	experienceValue = 5;//経験値の量

    	setForm((byte)rand.nextInt(3));
    	setPattern(rand.nextInt(DanmakuPatternRegistry.pattern.size()));
    	
    	lostTarget = 0;
    	this.setSpeed(0.3D);
    	this.setSpecies(this.SPECIES_FAIRY);
    	isFlyingMode = true;
    	
    	this.setAttackDistance(8.0D);
    	this.setDetectionDistance(8.0D);
    	this.setFlyingHeight(2);
    	
    }
    
    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData entityLivingData)
    {
        Object object = super.onSpawnWithEgg(entityLivingData);

        if (this.worldObj.rand.nextInt(100) < 2 && getForm() != 10)
        {
            /*EntityFamiliar familiar = new EntityFamiliar(this.worldObj);
            familiar.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            //familiar.onSpawnWithEgg((IEntityLivingData)null);
            this.setMaxHP(20.0F);
            this.setHealth(20.0F);
            this.worldObj.spawnEntityInWorld(familiar);
            familiar.mountEntity(this);
            setForm((byte)10);*/
        }
        
    	/*this.setAttackDistance(8.0D);
    	this.setDetectionDistance(8.0D);
        setFlyingHeight(10);
        danmakuSpan = 20;
        speedRate = 0.5F;*/

        /*if (p_110161_1_1 == null)
        {
            p_110161_1_1 = new EntitySpider.GroupData();

            if (this.worldObj.difficultySetting == EnumDifficulty.HARD && this.worldObj.rand.nextFloat() < 0.1F * this.worldObj.func_147462_b(this.posX, this.posY, this.posZ))
            {
                ((EntitySpider.GroupData)p_110161_1_1).func_111104_a(this.worldObj.rand);
            }
        }

        if (p_110161_1_1 instanceof EntitySpider.GroupData)
        {
            int i = ((EntitySpider.GroupData)p_110161_1_1).field_111105_a;

            if (i > 0 && Potion.potionTypes[i] != null)
            {
                this.addPotionEffect(new PotionEffect(i, Integer.MAX_VALUE));
            }
        }*/

        return (IEntityLivingData)object;
    }

    
    protected void setPattern(int patternId)
    {
    	String patternKey = DanmakuPatternRegistry.pattern.get(patternId);
    	int shot = DanmakuPatternRegistry.form.get(patternKey);
    	
    	setDanmakuPattern(DanmakuPatternRegistry.danmaku.get(patternKey));
    	danmakuSpan = DanmakuPatternRegistry.span.get(patternKey);
    	speedRate = DanmakuPatternRegistry.speed.get(patternKey);
    	shotForm = (byte)((shot - (shot % 8)) / 8);
    	shotColor = (byte)(shot % 8);
    }
    
    //死んでいるときに呼ばれる
    protected void onDeathUpdate()
    {
    	Calendar calendar = worldObj.getCurrentDate();
    	
    	if(worldObj.provider.isHellWorld ||
    		(calendar.get(calendar.MONTH) + 1 == 10 && calendar.get(calendar.DATE) == 31 )	
    			&& this instanceof EntitySunFlowerFairy == false)
    	{
    		if(this.getDetectionDistance() > 2.0D)
    		{
    			this.setFlyingHeight(0);
    			this.setDetectionDistance(this.getDetectionDistance() - 0.03D);
    			entityToAttack = null;
    		}
    		else
    		{
    			setHealth(2.0F);
    			setDetectionDistance(8.0D);
    			setAttackDistance(2.0D);
    			setFlyingHeight(2);
    			setSpeed(0.03D);
    			//setDanmakuPattern((byte)-1);
    			setDanmakuPattern(0);
    			danmakuSpan = 50 - (THKaguyaConfig.danmakuLevel * 10);
    			speedRate = 0.2F;
    			shotForm = (byte)(THShotLib.LIGHT[0] / 8);
    			shotColor = (byte)(THShotLib.RED);
    			this.setForm((byte)-1);
    		}
    	}
    	else
    	{
    		
    		super.onDeathUpdate();
    	}
    	
    }

	//常時呼ばれる
    public void onUpdate()
    {	
    	/*if(ridingEntity == null)
    	{
    		EntityFamiliar familiar = new EntityFamiliar(worldObj);
    		familiar.mountEntity(this);
 			if(!worldObj.isRemote)
 			{
 				worldObj.spawnEntityInWorld(familiar);
 			}
    		
    	}*/
    	//体力がないなら動かない
    	if(this.getHealth() <= 0)
    	{
    		motionX = 0.0D;
    		motionY = 0.0D;
    		motionZ = 0.0D;
    	}
    	
    	if(ticksExisted <= lastTime)
    	{
    		return;
    	}
    	else
    	{
    		super.onUpdate();
    		if(this.attackCounter > danmakuSpan)
    		{
    			attackCounter = 0;
    		}
    	}
    }
    
    /**
     * 弾を出したEntityLivingBaseを返す（要は発射主）
     * @return
     */
    public EntityLivingBase getShooter()
    {
    	return this;
    }
    
    //弾幕の処理
    @Override
    public void danmakuPattern(int level)
    {
    	//攻撃カウンターが０でないなら弾幕を出さない
    	if(attackCounter > 0)
    	{
    		return;
    	}
    	
    	//見ている方向ベクトルを取得
    	Vec3 look = THShotLib.angle(getShooter().rotationYaw, getShooter().rotationPitch);
    	//方向ベクトルにコンフィグの設定でランダム性を与える
    	look = THShotLib.angle_LimitRandom(look, THKaguyaConfig.danmakuAccuracy);
    	
    	int d = getDanmakuPattern();//弾幕のパターン値を記憶
    	
    	boolean isShot = shotForm < 32;//弾ならtrue
    	boolean isLaser = shotForm == 64;//レーザーならtrue
    	int shot = shotForm * 8 + shotColor;//
    	ShotData shotData = ShotData.shot(shotForm, shotColor, 0, 80, special);
    	LaserData laserData = LaserData.laser(THShotLib.LASER_NORMAL, shotColor, 0.1F, 2.0F, 3.0F, 0, 80, special);
    	
    	//発射音を出す
    	THShotLib.playShotSound(this);
    	
		//速度差のある弾を一点に飛ばす
		if(d >= 0 && d <= 999)
		{
			int way = d % 100 + 1;
			int num = d / 100 + 1;
			double speed = speedRate;
			double slowDownRate = speed / num;
			if(isShot)
			{
				for(int i = 0; i < num; i++)
				{
					THShotLib.createSphereShot(getShooter(), pos(), look, speed, shotData, way, 0F);
					speed -= slowDownRate;
				}
			}
			else if(isLaser)
			{
				for(int i = 0; i < num; i++)
				{
					THShotLib.createSphereLaserA(getShooter(), getShooter(), pos(), look, speed, speed, 0.0D, gravity_Zero(), laserData, way, 0F, 0F);
					speed -= slowDownRate;
				}
			}
		}
		//5,10,15,20,25...165,170,175,180度に扇状の弾幕。弾数は角度÷5発
		else if(d >= 1000 && d <= 1999)
		{
			int num = d % 100 + 1;
			float span = (d - 1000) / 50F + 1F;
			if(isShot)
			{
				THShotLib.createWideShot(getShooter(), pos(), look, (double)speedRate, (double)speedRate, 0.0D, shotData, num, num * span);
			}
			else if(isLaser)
			{
				THShotLib.createWideLaserA(getShooter(), pos(), look, (double)speedRate, (double)speedRate, 0.0D, laserData, num, num * span);
			}
		}
		//2～36way全方位弾
		else if(d >= 2000 && d <= 2999)
		{
			int way = d % 100 + 1;
			int num = (d - 2000) / 100 + 1;
			double speed = speedRate;
			double slowDownRate = speed / num;
			if(isShot)
			{
				for(int i = 0; i < num; i++)
				{
					THShotLib.createCircleShot(getShooter(), pos(), look, speed, shotData, way);
					speed -= slowDownRate;
				}
			}
			else if(isLaser)
			{
				for(int i = 0; i < num; i++)
				{
					THShotLib.createCircleLaserA(getShooter(), pos(), look, speed, laserData, way);
					speed -= slowDownRate;
				}
			}
		}
		//１発の弾を０～２０度の角度にランダムに発射する
		else if(d >= 3000 && d <= 3999)
		{
			float shotSpan = d % 100 + 1F;
			int num = (d - 3000) / 100 + 1;
			if(isShot)
			{
				THShotLib.createRandomRingShot(getShooter(), pos(), look, speedRate, shotData, num, shotSpan);
			}
			else if(isLaser)
			{
				//THShotLib.createRandomRingLaserA(getShooter(), pos(posX, shootPosY, posZ), look, speedRate, laserData, num, shotSpan);
			}
		}
		else if(d >= 4000 && d <= 4999)
		{
			float shotSpan = ((d - 4000) % 100 + 1F) * 1.8F;
			int way = (d - 4000) / 100 * ((d % 100) / 10) + 3;
			THShotLib.createRingShot(getShooter(), pos(), look, speedRate, shotData, way, 0, shotSpan, rand.nextFloat() * 360F);
		}
		else if(d >= 5000 && d <= 5999)
		{
			int num = d % 100 + 1;
			THShotLib.createRandomRingShot(getShooter(), pos(), look, speedRate, shotData, num, 360F);
		}
    }
    
    //周りの妖精を呼び出すことができるか
    @Override
    protected boolean canFairyCall()
    {
    	return true;
    }
    
    //妖精を呼び出せる最大距離
    @Override
    protected double getFairyCallDistance()
    {
    	return 30.0D;
    }
 
	@Override
    protected Item getDropItem()
    {
    	if(getForm() < 0)
    	{
    		return null;
    	}
    	else
    	{
    		return THKaguyaItems.power_item;
    	}
    }
	
	//倒れたときに落とすアイテム
	@Override
	protected void dropFewItems(boolean hasBeenAttackedByPlayer, int lootingLevel)
    {
		super.dropFewItems(hasBeenAttackedByPlayer, lootingLevel);
		
		if(getForm() < 0)
		{
			return;
		}
		
		if(hasBeenAttackedByPlayer)
		{	
			if(this.getMaxHealth() == 10.0F)
			{
				this.dropShotItem(ItemTHShot.SMALL, rand.nextInt(12) + lootingLevel * 2, 7, 8, THShotLib.RED, 0, 0, 2);
		        
			    this.dropPowerUpItem(this.rand.nextInt(20) + this.rand.nextInt(3 + lootingLevel * 2));
		
		        this.dropPointItem(this.rand.nextInt(20) + this.rand.nextInt(3 + lootingLevel * 2));
			}
			else
			{
				this.dropShotItem(ItemTHShot.SMALL, rand.nextInt(2) + lootingLevel, 3, 0, THShotLib.RED, 0, 0, 2);
		        
			    this.dropPowerUpItem(this.rand.nextInt(5) + this.rand.nextInt(1 + lootingLevel));
		
		        this.dropPointItem(this.rand.nextInt(5) + this.rand.nextInt(1 + lootingLevel));
			}
		}
    }
    
    //一つのチャンクに湧く最大数
    @Override
    public int getMaxSpawnedInChunk()
    {
        return 10;
    }

	//自然スポーンするときに呼ばれる。tureならスポーンする
    @Override
    public boolean getCanSpawnHere()
    {
    	//妖精のスポーンレートよりランダム値が低いならスポーンしない
    	if(rand.nextInt(100) < THKaguyaConfig.fairySpawnRate)
    	{
    		return false;
    	}
    	
    	
    	
        int yPosition = MathHelper.floor_double(this.boundingBox.minY);
    	int xPosition = MathHelper.floor_double(this.posX);
        int zPosition = MathHelper.floor_double(this.posZ);
        
        if(yPosition < 64)
        {
        	if(yPosition < rand.nextInt(64))
        	{
        		return false;
        	}
        }
        
        if(worldObj.getSavedLightValue(EnumSkyBlock.Sky, xPosition, yPosition, zPosition) - worldObj.skylightSubtracted <= 7)
        {
        	return false;
        }
        
    	//int pointBlock = worldObj.getBlockId(xPosition, yPosition - 1, zPosition);
    	Block pointBlock = worldObj.getBlock(xPosition, yPosition - 1, zPosition);
    	//地面が草ブロックか土ブロックか砂ブロックならスポーンする
    	//if(pointBlock == Block.grass.blockID || pointBlock == Block.dirt.blockID || pointBlock == Block.sand.blockID)
    	if(pointBlock == Blocks.grass || pointBlock == Blocks.dirt || pointBlock == Blocks.sand)
    	{
    		return true;
    	}
    	return false;
    }
    
    
    //妖精の攻撃モーションを返す
    public byte getFairyAttackMotion()
    {
    	return dataWatcher.getWatchableObjectByte(17);
    }
    
    //妖精の攻撃モーションを設定
    public void setFairyAttackMotion(byte motion)
    {
    	dataWatcher.updateObject(17, Byte.valueOf(motion));
    }
    
    /**
     * 内部名を返す。言語ファイルで使われるもの
     */
    @Override
    public String getCommandSenderName()
    {
        String s = EntityList.getEntityString(this);

        if (s == null)
        {
            s = "generic";
        }
        else
        {
        	if(getForm() >= 32)
        	{
        		s = "THGhost";
        	}
        }

        return StatCollector.translateToLocal("entity." + s + ".name");
    }

    /**
     * Initialize this creature.
     */
    public void initCreature() {}

}



