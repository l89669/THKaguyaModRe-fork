package thKaguyaMod.entity.living;

import static thKaguyaMod.DanmakuConstants.*;
import static thKaguyaMod.THShotLib.*;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import thKaguyaMod.ShotData;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.spellcard.THSC_LittleBugStorm;
import thKaguyaMod.item.ItemTHShot;

/** リグル */
public class EntityWriggle extends EntityDanmakuMob
{

	
    public EntityWriggle(World world)
    {
        super(world);
        
        this.setSize(1.0F, 1.8F);//MOBの当たり判定の大きさ 横奥行き、高さ、大きさ
        
    	experienceValue = 140;//経験値の量
    	
    	this.setDanmakuPattern(NORMAL_ATTACK01);
    	this.setMaxHP(30.0F);
    	setHealth(30.0F);
    	this.setDanmakuMobName("Wriggle");
    	this.setSpecies(this.SPECIES_YOUKAI_BUG);
    	
    	this.setAttackDistance(14.0D);
    	this.setDetectionDistance(12.0D);
    	this.setFlyingHeight(3);
    	this.isFlyingMode = true;
    	

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
    		return 35;
    	case SPELLCARD_ATTACK02:
    		return 34;
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
    		moveDanmakuAttack(SPELLCARD_ATTACK01, 20, 36.0F, 40);
        	this.setAttackDistance(16.0D);
        	this.setDetectionDistance(30.0D);
        	this.setFlyingHeight(2);
        	break;
    	case SPELLCARD_ATTACK01:
    		moveDanmakuAttack(SPELLCARD_ATTACK02, 60, 42.0F, 80);
        	this.setAttackDistance(15.0D);
        	this.setDetectionDistance(30.0D);
        	this.setFlyingHeight(4);
        	break;
    	case SPELLCARD_ATTACK02:
    		moveDanmakuAttack(ATTACK_END, 30, 0F, 60);
    		break;
    	default:
    		if(deathTime % 9 == 0)
    		{
    			explosionEffect(worldObj, posX, posY, posZ, 1.0F + deathTime * 0.1F);
    		}
    		super.onDeathUpdate();
    		
    	}
    }
    
    @Override
    public void danmakuPattern(int level)
    {
    	
    	switch(getDanmakuPattern())
    	{
    	case NORMAL_ATTACK01:
	    	Vec3 look = this.getLookVec();
	    	Vec3 rotate = THShotLib.angle(this.rotationYaw, this.rotationPitch - 90F);
	    	Vec3 angle;
	    	Vec3 angle2;
	    	if(attackCounter < 30)
	    	{
		    	if(attackCounter % 15 >= 1  && attackCounter % 15 < 8)
		    	{
		    		angle = THShotLib.getRotationVectorFromAngle(this.rotationYaw, this.rotationPitch, (attackCounter % 8) * 10F, 1.0);
		    		THShotLib.playShotSound(this);
		    		THShotLib.createShot(this, pos(), angle, 1.0, 0.0, -0.2, ShotData.shot(FORM_RICE, YELLOW, 3, 15, THSC_LittleBugStorm.SP_ATTACK01_01));
		    		if(level >= HARD)
		    		{
		    			angle2 = THShotLib.getRotationVectorFromAngle(this.rotationYaw, this.rotationPitch, 5F + (attackCounter % 8) * 10F, 1.0);
		    			THShotLib.createShot(this, pos(), angle, 1.0, 0.0, -0.4, ShotData.shot(FORM_RICE, YELLOW, 3, 15, THSC_LittleBugStorm.SP_ATTACK01_01));
		    		}
		    		
		    	}
		    	else if(attackCounter % 15 >= 8 && attackCounter % 15 < 15)
		    	{
		    		angle = THShotLib.getRotationVectorFromAngle(this.rotationYaw, this.rotationPitch,  - (attackCounter % 8) * 10F, 1.0);
		    		THShotLib.playShotSound(this);
		    		THShotLib.createShot(this, pos(), angle, 1.0, 0.0, -0.2, ShotData.shot(FORM_RICE, AQUA, 3, 15, THSC_LittleBugStorm.SP_ATTACK01_02));
		    		if(level >= HARD)
		    		{
		    			angle2 = THShotLib.getRotationVectorFromAngle(this.rotationYaw, this.rotationPitch, -5F - (attackCounter % 8) * 10F, 1.0);
		    			THShotLib.createShot(this, pos(), angle, 1.0, 0.0, -0.4, ShotData.shot(FORM_RICE, AQUA, 3, 15, THSC_LittleBugStorm.SP_ATTACK01_02));
		    		}
		    	}
	    	}
	    	else if(attackCounter == 24)
	    	{
	    		moveRight(0.3D, 20);
	    	}
	    	else if(attackCounter >= 62 && attackCounter < 72)
	    	{
	    		THShotLib.playShotSound(this);
	    		int i = attackCounter - 62;
	    		double firstSpeed = 0.3 + 0.1 * level;
	    		double limit = 0.3;//(72 - attackCounter) * 0.02;
	    		double acceleration = -(attackCounter - 61) * 0.005;
	    		THShotLib.createWideShot(this, pos(), look, firstSpeed, limit, acceleration, ShotData.shot(FORM_SMALL, YELLOW, 10), 5, 90F);
	    	}
	    	else if(attackCounter == 67)
	    	{
	    		moveLeft(0.3D, 20);
	    	}
	    	if(attackCounter >= 147)
	    	{
	    		
	    		attackCounter = 0;
	    	}
	    	break;
    	case SPELLCARD_ATTACK01:
    		if(attackCounter % 15 == 1 && attackCounter < 120)
    		{
    			if(level == NORMAL)
    			{
    				THShotLib.createSphereShot(this, pos(), look(), 0.2D, ShotData.shot(FORM_MEDIUM, BLUE), 16, ticksExisted * 13F);
    			}
    			else if(level == HARD)
    			{
    				THShotLib.createSphereShot(this, pos(), look(), 0.3D, ShotData.shot(FORM_MEDIUM, BLUE), 24, ticksExisted * 13F);
    			}
    			else if(level == LUNATIC)
    			{
    				THShotLib.createSphereShot(this, pos(), look(), 0.4D, ShotData.shot(FORM_MEDIUM, BLUE), 32, ticksExisted * 13F);
    			}
    		}
    		
			if(attackCounter == 1)
			{
				useSpellCard(getUsingSpellCardNo());
			}
			else if(attackCounter >= 180)
			{
				attackCounter = 0;
			}
    		break;
    	case SPELLCARD_ATTACK02:
			if(attackCounter == 1)
			{
				useSpellCard(getUsingSpellCardNo());
			}
			else if(attackCounter >= 260)
			{
				attackCounter = 0;
			}
    		break;
    	default:
    		break;
    	}
    }
	
	//倒れたときに落とすアイテム
	@Override
	protected void dropFewItems(boolean hasBeenAttackedByPlayer, int lootingLevel)
    {
		super.dropFewItems(hasBeenAttackedByPlayer, lootingLevel);
		
		if(hasBeenAttackedByPlayer)
		{	
			if(this.isSpellCardAttack())
			{
				
				this.dropShotItem(ItemTHShot.RICE, 8 + rand.nextInt(2) + lootingLevel, 5, 32, YELLOW, 0, 0, 2);
		        
			    this.dropPowerUpItem(8 + this.rand.nextInt(5) + this.rand.nextInt(1 + lootingLevel));
		
		        this.dropPointItem(8 + this.rand.nextInt(5) + this.rand.nextInt(1 + lootingLevel));
			}
		}
    }
	
    //ダメージを受けたときの処理
    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float damage)
    {

        if (super.attackEntityFrom(damageSource, applyPotionDamageCalculations(damageSource, damage)) && damageSource.getEntity() instanceof EntityLivingBase)
        {
            EntityLivingBase entity = (EntityLivingBase)damageSource.getEntity();

        	//ノックバック耐性が高い
        	motionX *= 0.01D;
        	motionY *= 0.01D;
        	motionZ *= 0.01D;
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
    }
    
	@Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }
    
    /**
     * Checks to make sure the light is not too bright where the mob is spawning
     */
    protected boolean isValidLightLevel()
    {
        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.boundingBox.minY);
        int k = MathHelper.floor_double(this.posZ);

        if (this.worldObj.getSavedLightValue(EnumSkyBlock.Sky, i, j, k) > this.rand.nextInt(32))
        {
            return false;
        }
        else
        {
            int l = this.worldObj.getBlockLightValue(i, j, k);

            if (this.worldObj.isThundering())
            {
                int i1 = this.worldObj.skylightSubtracted;
                this.worldObj.skylightSubtracted = 10;
                l = this.worldObj.getBlockLightValue(i, j, k);
                this.worldObj.skylightSubtracted = i1;
            }

            return l <= this.rand.nextInt(8);
        }
    }
    
	/**
	 * 自然スポーンするときに呼ばれる
	 * @return trueならスポーン成功
	 */
	@Override
    public boolean getCanSpawnHere()
    {
		/*	暗闇でのみ湧く	*/
		if( 	( this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL	)
			||	( !this.isValidLightLevel()										) )
		{
			return false;
		}
    	
        int yPosition = MathHelper.floor_double(this.posY + 0.1D);
    	int xPosition = MathHelper.floor_double(this.posX);
        int zPosition = MathHelper.floor_double(this.posZ);

        Block pointBlock, pointBlock2;

        pointBlock = pointBlock2 = worldObj.getBlock(xPosition, yPosition, zPosition);

        for(int i = -2; i <= 2; i++)
        {
        	for(int j = -2; j<= 2; j++)
        	{
            	pointBlock2 = worldObj.getBlock(xPosition + i, yPosition - 1, zPosition + j);
            	if(pointBlock2 == Blocks.water)
            	{
            		return true;
            	}
        	}
        }
    	return false;
    }
    

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness(float par1)
    {
        return 1.0F;
    }
    

}
