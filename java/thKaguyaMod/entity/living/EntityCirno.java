package thKaguyaMod.entity.living;

import static thKaguyaMod.DanmakuConstants.*;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.LaserData;
import thKaguyaMod.ShotData;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.spellcard.EntitySpellCard;
import thKaguyaMod.init.THKaguyaConfig;
import thKaguyaMod.init.THKaguyaItems;
import thKaguyaMod.item.ItemTHShot;

/** チルノ */
public class EntityCirno extends EntityDanmakuMob
{
	private int random;
	
	/** チルノのコンストラクタ（Worldのみは必須） */
	public EntityCirno(World world)
    {
        super(world);
        
        this.setSize(1.0F, 1.8F);					// MOBの当たり判定の大きさ 横奥行き、高さ、大きさ
        
        this.experienceValue = 250;					// 経験値の量
        
        this.setDanmakuMobName("Cirno");			// 体力バーに表示される名前を設定
        this.setSpecies(this.SPECIES_FAIRY, this.SPECIES_FAIRY_ICE);
        											// 種族の設定
    	
    	this.setDanmakuPattern(NORMAL_ATTACK01);	// 最初の弾幕パターン
    	this.setMaxHP(38.0F);						// 最初の最大HP
        this.setHealth(38.0F);						// 最初のHP
        this.setSpeed(0.5F);						// 移動速度
        this.setAttackDistance(14.0D);				// 攻撃時取る間合い
    	this.setDetectionDistance(30.0D);			// 索敵範囲
    	this.setFlyingHeight(2);					// 飛行高度
    	this.isFlyingMode = true;					// 飛行モードのONOFF
    	
    	this.isSpellCardMode = false;				// スペルカードモード判定
    	this.attackInterval = 0;					// 次の攻撃パターンへの移行時間
    	this.random = 0;							// ランダム値
    }
	
    /**
     * 使用しているスペルカードNoを返す
     * @return 使用しているスペルカードNo
     */
    public int getUsingSpellCardNo()
    {
    	switch(getDanmakuPattern())
    	{
    	case SPELLCARD_ATTACK01:
    		return EntitySpellCard.SC_CIRNO_IcicleFall;
    	case SPELLCARD_ATTACK02:
    		return EntitySpellCard.SC_CIRNO_PerfectFreeze;
    	default:
    		return -1;
    	}
    }
    
    //死んでいるときに呼ばれる
    @Override
    protected void onDeathUpdate()
    {
    	if(ticksExisted <= lastTime)
    	{
    		return;
    	}
    	
    	switch(getDanmakuPattern())
    	{
	    	case NORMAL_ATTACK01:
	    		moveDanmakuAttack(SPELLCARD_ATTACK01, 40, 46.0F, 60);
	    		break;
	    	case SPELLCARD_ATTACK01:
	    		moveDanmakuAttack(NORMAL_ATTACK02, 60, 32.0F, 20);
	    		break;
	    	case NORMAL_ATTACK02:
	    		moveDanmakuAttack(SPELLCARD_ATTACK02, 40, 58.0F, 60);
	    		break;
	    	case SPELLCARD_ATTACK02:
	    		moveDanmakuAttack(ATTACK_END, 30, 0F, 60);
	    		break;
	    	default:
	    		if(deathTime % 9 == 0)
	    		{
	    			THShotLib.explosionEffect(worldObj, posX, posY, posZ, 1.0F + deathTime * 0.1F);
	    		}
	    		super.onDeathUpdate();
    		
    	}
    }

    
	/** 毎tick呼ばれる処理 */
    @Override
    public void onUpdate()
    {
    	super.onUpdate();
    }

    
    /** 弾幕のパターンを記述
     * @param level : EASY～LUNATICの難易度
     */
    @Override
    public void danmakuPattern(int level)
    {
    	Vec3 angle;
    	angle = THShotLib.angle(rotationYaw,  rotationPitch);
    	
		switch(getDanmakuPattern())
		{
			case NORMAL_ATTACK01:
				danmaku01(angle, level);
				break;
			case SPELLCARD_ATTACK01:
				spellcard01(angle, level);
				break;
			case NORMAL_ATTACK02:
				danmaku02(angle, level);
				break;
			case SPELLCARD_ATTACK02:
				spellcard02(angle, level);
				break;
			default:
				break;
		}
    }
    
    //通常１
    private void danmaku01(Vec3 angle, int level)
    {
		if(attackCounter == 40)
		{
			angle = this.getLookVec();
			for(int i = 0; i < 7; i++)
			{
				THShotLib.createRingShot(this, this, pos(), angle, 0F, 9999, 0.6D + level * 0.15D, 0.6D + level * 0.15D, 0.1D, gravity_Zero(), ShotData.shot(FORM_CRYSTAL, AQUA, 0.2F, 4.0F, i, 120, 0), i + 2, 0.0D, i * (level), rand.nextFloat() * 360F);
			}
			worldObj.playSoundAtEntity(this, "random.bow", 2.0F, 0.8F);//音を出す
		}
		if(attackCounter >= 50)
		{
			attackCounter = 0;
		}
    }
    
    //スペルカード１　アイシクルフォール
    private void spellcard01(Vec3 angle, int level)
    {
		if(attackCounter == 1)
		{
			useSpellCard(getUsingSpellCardNo());
		}
		else if(attackCounter > 30 && attackCounter < 170 && attackCounter % 17 == 0 && level >= 2)
		{
			
			float size = THShotLib.SIZE[THShotLib.SMALL[0] / 8];
			if(level >= 3)
			{
				size = THShotLib.SIZE[THShotLib.MEDIUM[0] / 8];
			}
			THShotLib.createWideShot(this, this,pos(), angle, 0.3D + level * 0.1D, 0.5D, 1.0D, gravity_Zero(), ShotData.shot(FORM_SMALL, YELLOW, size, 4.0F, 3, 80), 3, 70F, 0.2D);
			worldObj.playSoundAtEntity(this, "random.bow", 2.0F, 0.8F);//音を出す
			
		}
		else if(attackCounter >= 240)
		{
			attackCounter = 0;
		}
    }
    
    //通常２
    private void danmaku02(Vec3 angle, int level)
    {
		if(attackCounter < 50 && attackCounter % 6 == 0)
		{
			THShotLib.createCircleShot(this, pos(), angle, 0.2D, ShotData.shot(FORM_TINY, WHITE), 5 + level * 2);
			THShotLib.createCircleShot(this, pos(), angle, 0.3D + level * 0.1D, ShotData.shot(FORM_CIRCLE, BLUE), 5 + level * 2);
			worldObj.playSoundAtEntity(this, "random.bow", 2.0F, 0.8F);//音を出す
		}
		else if(attackCounter == 55 || attackCounter == 65 || attackCounter == 75)
		{
			//thShotLib.createWideLaserA01(this, this.posX, thShotLib.getPosYFromEye(this, -0.2D), this.posZ, angleXZ, angleY, 0.5D + (double)level * 0.1D, 6.0D, thShotLib.WHITE, 0.1F, 3.0D, 3, 30F);
			THShotLib.createWideLaserA(this, this.pos(), getLookVec(), 0.3D + level * 0.2D, LaserData.laser(WHITE, 0.1F, 4.0F, 4.0F), 3, 30F);
		}
		if(attackCounter > 128)
		{
			attackCounter = 0;
		}
    }
    
    //スペルカード２　パーフェクトフリーズ
    private void spellcard02(Vec3 angle, int level)
    {
		if(attackCounter == 1)
		{
			random = rand.nextInt(2);
			useSpellCard(getUsingSpellCardNo());
		}
		else if(attackCounter >= 70 && attackCounter <= 90 && attackCounter % 3 == 0)
		{
			int way = 4 + random;
			for(int i = 0; i < 3 + level; i++)
			{
				THShotLib.createWideShot(this, this, pos(), angle, 0.3D + level * 0.1D, 0.5D, 1.0D, gravity_Zero(), ShotData.shot(FORM_CIRCLE, BLUE, 0.3F, 4.0F, i, 80), way, 70F, 0.2D);
			}
			worldObj.playSoundAtEntity(this, "random.bow", 2.0F, 0.8F);//音を出す
		}
		else if(attackCounter >= 170)
		{
			attackCounter = 0;
		}
    }
    
    //周りの妖精を呼び出すことができるか
    @Override
    protected boolean canFairyCall()
    {
    	return false;//チルノは周りの妖精の助けを得ない
    }
    
    //ダメージを受けたときの処理
    /*@Override
    public boolean attackEntityFrom(DamageSource damageSource, float damage)
    {

        if (super.attackEntityFrom(damageSource, applyPotionDamageCalculations(damageSource, damage)) && damageSource.getEntity() instanceof EntityLivingBase)
        {
        	//ノックバック耐性が高い
        	motionX *= 0.01D;
        	motionY *= 0.01D;
        	motionZ *= 0.01D;
        	
            EntityLivingBase entity = (EntityLivingBase)damageSource.getEntity();

            if (this.riddenByEntity != entity && this.ridingEntity != entity)
            {
                if (entity instanceof EntityPlayer)//entity != this)
                {
                    this.entityToAttack = entity;
                }

                return true;
            }
            else
            {
                return true;
            }
        }
        else
        {
            return false;
        }
    }*/
    
    /**
     * Reduces damage, depending on potions
     */
    @Override
    protected float applyPotionDamageCalculations(DamageSource damageSource, float damage)
    {
        damage = super.applyPotionDamageCalculations(damageSource, damage);

        if (isEntityInvulnerable())
        {
            damage = (float)((double)damage * 0.05D);
        }

        return damage;
    }

    @Override
    protected void updateAITasks()
    {
        super.updateAITasks();

        /*if (this.getIsTHFairyHanging())
        {
            if (!this.worldObj.isBlockNormalCube(MathHelper.floor_double(this.posX), (int)this.posY + 1, MathHelper.floor_double(this.posZ)))
            {
                this.setIsTHFairyHanging(false);
                this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1015, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
            }
            else
            {
                if (this.rand.nextInt(200) == 0)
                {
                    this.rotationYawHead = (float)this.rand.nextInt(360);
                }

                if (this.worldObj.getClosestPlayerToEntity(this, 0.5D) != null)
                {
                    this.setIsTHFairyHanging(false);
                    this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1015, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
                }
            }
        }
        else
        {
            if (this.currentFlightTarget != null && (!this.worldObj.isAirBlock(this.currentFlightTarget.posX, this.currentFlightTarget.posY, this.currentFlightTarget.posZ) || this.currentFlightTarget.posY < 1))
            {
                this.currentFlightTarget = null;
            }

            if (this.currentFlightTarget == null || this.rand.nextInt(30) == 0 || this.currentFlightTarget.getDistanceSquared((int)this.posX, (int)this.posY, (int)this.posZ) < 4.0F)
            {
                this.currentFlightTarget = new ChunkCoordinates((int)this.posX + this.rand.nextInt(7) - this.rand.nextInt(7), (int)this.posY + this.rand.nextInt(6) - 2, (int)this.posZ + this.rand.nextInt(7) - this.rand.nextInt(7));
            }

            double var1 = (double)this.currentFlightTarget.posX + 0.5D - this.posX;
            double var3 = (double)this.currentFlightTarget.posY + 0.1D - this.posY;
            double var5 = (double)this.currentFlightTarget.posZ + 0.5D - this.posZ;
            this.motionX += (Math.signum(var1) * 0.5D - this.motionX) * 0.10000000149011612D;
            this.motionY += (Math.signum(var3) * 0.699999988079071D - this.motionY) * 0.10000000149011612D;
            this.motionZ += (Math.signum(var5) * 0.5D - this.motionZ) * 0.10000000149011612D;
            float var7 = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
            float var8 = MathHelper.wrapAngleTo180_float(var7 - this.rotationYaw);
            this.moveForward = 0.5F;
            this.rotationYaw += var8;

            if (this.rand.nextInt(100) == 0 && this.worldObj.isBlockNormalCube(MathHelper.floor_double(this.posX), (int)this.posY + 1, MathHelper.floor_double(this.posZ)))
            {
                this.setIsTHFairyHanging(true);
            }
        }*/
    }
	
	//倒れたときに落とすアイテム
	/*@Override
    protected Item getDropItem()
    {
        return THKaguyaItems.icicle_sword;
    }*/
	
	//倒れたときに落とすアイテム
	@Override
	protected void dropFewItems(boolean hasBeenAttackedByPlayer, int lootingLevel)
    {
		super.dropFewItems(hasBeenAttackedByPlayer, lootingLevel);
		
		if( hasBeenAttackedByPlayer && this.isSpellCardAttack() )
		{
	        int j = 40;//this.rand.nextInt(15) + this.rand.nextInt(1 + par2);
	        int k;
	        Vec3 vec3;
	        float yaw, pitch;
	
	        for (k = 0; k < j; k+=2)
	        {
	        	yaw = 360F / (float)j * (float)k;
	        	pitch = MathHelper.sin( yaw / 180F * 3.141593F * 4F) * 20F - 60F;
	        	vec3 = THShotLib.getVecFromAngle(yaw, pitch, 1.0F);
	        	this.dropPointItem(this.pos(), vec3);
	        	yaw = 360F / (float)j * (float)(k + 1);
	        	pitch = MathHelper.cos( yaw / 180F * 3.141593F * 4F) * 20F - 60F;
	        	vec3 = THShotLib.getVecFromAngle(yaw, pitch, 1.0F);
	        	this.dropPowerUpItem(this.pos(), vec3);
	        }
	        
	        this.dropShotItem(ItemTHShot.CRYSTAL, 17 + rand.nextInt(2) + lootingLevel, 5, 32, AQUA, 0, 0, 2);
		}
        if( hasBeenAttackedByPlayer && getDanmakuPattern() == SPELLCARD_ATTACK02)
        {
        	this.dropItem(THKaguyaItems.icicle_sword, 1);
        	this.dropExtendItem(this.pos(), this.angle(this.rotationYaw, -90F));
        	
        }
    }

    /**
     * Return whether this entity should NOT trigger a pressure plate or a tripwire.
     */
	@Override
    public boolean doesEntityNotTriggerPressurePlate()
    {
        return true;
    }
    
	@Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

	/**
	 * 自然スポーンするときに呼ばれる
	 * @return trueならスポーン成功
	 */
	@Override
    public boolean getCanSpawnHere()
    {
    	if(rand.nextInt(100) < THKaguyaConfig.fairySpawnRate)
    	{
    		return false;
    	}
    	//return true;
    	
        int yPosition = MathHelper.floor_double(this.posY + 0.1D);
    	int xPosition = MathHelper.floor_double(this.posX);
        int zPosition = MathHelper.floor_double(this.posZ);
        //int pointBlock, pointBlock2;
        Block pointBlock, pointBlock2;
        //pointBlock = pointBlock2 = worldObj.getBlockId(xPosition, yPosition, zPosition);
        pointBlock = pointBlock2 = worldObj.getBlock(xPosition, yPosition, zPosition);
        //if(pointBlock != Block.snow.blockID)
        if(pointBlock != Blocks.snow_layer)
        {
        	return false;
        }
        for(int i = -2; i <= 2; i++)
        {
        	for(int j = -2; j<= 2; j++)
        	{
            	//pointBlock2 = worldObj.getBlockId(xPosition + i, yPosition - 1, zPosition + j);
            	pointBlock2 = worldObj.getBlock(xPosition + i, yPosition - 1, zPosition + j);
            	//if(pointBlock2 == Block.ice.blockID)
            	if(pointBlock2 == Blocks.ice)
            	{
            		return true;
            	}
        	}
        }
    	return false;
    }
}
