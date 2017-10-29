package thKaguyaMod.entity.living;

import static thKaguyaMod.DanmakuConstants.*;
import static thKaguyaMod.THShotLib.*;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import thKaguyaMod.ShotData;
import thKaguyaMod.THShotLib;
import thKaguyaMod.item.ItemTHShot;

/** ルーミア */
public class EntityRumia extends EntityDanmakuMob
{
    public EntityRumia(World world)
    {
        super(world);
        
        this.setSize(1.0F, 1.8F);//MOBの当たり判定の大きさ 横奥行き、高さ、大きさ
        
    	experienceValue = 160;//経験値の量
    	ignoreFrustumCheck = true;//中心が画面から外れても描画される
    	
    	this.setDanmakuPattern(NORMAL_ATTACK01);
    	this.setMaxHP(36.0F);
    	setHealth(36.0F);
    	this.setDanmakuMobName("Rumia");
    	this.setSpecies(this.SPECIES_YOUKAI);
    	
    	this.setAttackDistance(14.0D);
    	this.setDetectionDistance(12.0D);
    	this.setFlyingHeight(2);
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
    		return 28;
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
    		moveDanmakuAttack(SPELLCARD_ATTACK01, 40, 44.0F, 60);
        	this.setAttackDistance(14.0D);
        	this.setDetectionDistance(30.0D);
        	this.setFlyingHeight(2);
        	break;
    	case SPELLCARD_ATTACK01:
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
	    	if(attackCounter == 1)
	    	{
	    		THShotLib.playShotSound(this);
	    		for(int i = 0; i < 1 + level; i++)
	    		{
	    			THShotLib.createCircleShot(this, pos(), angle(rotationYaw, rotationPitch), 2.0D, 0.4D, -0.3D, ShotData.shot(FORM_CIRCLE, BLUE, 10 + i), 12 + 3 * level );
	    		}
	    		
	    	}
	    	else if(attackCounter == 14)
	    	{
	    		THShotLib.playShotSound(this);
	    		for(int i = 0; i < 1 + level; i++)
	    		{
	    			THShotLib.createCircleShot(this, pos(), angle(rotationYaw, rotationPitch), 2.0D, 0.4D, -0.3D, ShotData.shot(FORM_CIRCLE, THShotLib.RED, 10 + i), 12 + 3 * level);
	    		}
	    		
	    	}
	    	else if(attackCounter == 24)
	    	{
	    		moveRight(0.3D, 20);
	    	}
	    	else if(attackCounter >= 62 && attackCounter < 67)
	    	{
	    		THShotLib.playShotSound(this);
	    		int i = attackCounter - 62;
	    		THShotLib.createCircleShot(this, pos(), angle(rotationYaw + i * 5F, rotationPitch), 0.2D + level * 0.1D, ShotData.shot(FORM_TINY, attackCounter % 7), 5 + level * 2);
	    	}
	    	else if(attackCounter == 67)
	    	{
	    		moveLeft(0.3D, 20);
	    	}
	    	if(attackCounter >= 127)
	    	{
	    		
	    		attackCounter = 0;
	    	}
	    	break;
    	case SPELLCARD_ATTACK01:
			if(attackCounter == 1)
			{
				useSpellCard(getUsingSpellCardNo());
			}
			else if(attackCounter < 60 && attackCounter % 9 == 0)
			{
				THShotLib.createCircleShot(this, pos(), angle(rotationYaw, rotationPitch), 0.1D + level * 0.1D, ShotData.shot(FORM_TINY, WHITE), 15 + level * 3);
				THShotLib.playShotSound(this);
			}
			else if(attackCounter >= 120)
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
			
			this.dropShotItem(ItemTHShot.SMALL, 8 + rand.nextInt(2) + lootingLevel, 5, 32, BLUE, 0, 0, 2);
	        
		    this.dropPowerUpItem(8 + this.rand.nextInt(5) + this.rand.nextInt(1 + lootingLevel));
	
	        this.dropPointItem(8 + this.rand.nextInt(5) + this.rand.nextInt(1 + lootingLevel));
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
    
    //自然スポーンするときに呼ばれ、trueならスポーンする
    @Override
    public boolean getCanSpawnHere()
    {
    	//ゾンビなどと同じ湧き条件
        return this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL && this.isValidLightLevel();
    }

}
