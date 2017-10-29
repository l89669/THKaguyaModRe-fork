package thKaguyaMod.entity.living;

import static thKaguyaMod.DanmakuConstants.*;
import static thKaguyaMod.THShotLib.*;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import thKaguyaMod.ShotData;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.spellcard.THSC_GagoujiCyclone;
import thKaguyaMod.item.ItemTHShot;

/** 屠自古 */
public class EntityToziko extends EntityDanmakuMob
{
	
    public EntityToziko(World world)
    {
        super(world);
        
        this.setSize(1.0F, 1.8F);//MOBの当たり判定の大きさ 横奥行き、高さ、大きさ
        
    	experienceValue = 160;//経験値の量
    	
    	this.setDanmakuPattern(NORMAL_ATTACK01);
    	this.setMaxHP(40.0F);
    	setHealth(40.0F);
    	this.setDanmakuMobName("Soga no Toziko");
    	this.setSpecies(this.SPECIES_PHANTOM_GHOST);
    	
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
    		return 40;
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
	    	if(attackCounter % 80 < 20)
	    	{
	    		THShotLib.playShotSound(this);
	    		for(int i = 0; i < 1 + level; i++)
	    		{
	    		
	    			THShotLib.createShot(this, this, pos_Random(pos(), attackCounter * 0.2D), angle_Random(), 0F, 0.3D, 0.0D, -0.03D, gravity_Zero(), ShotData.shot(FORM_ARROW, BLUE, 1, 20, THSC_GagoujiCyclone.SPECIAL_TOZIKO01));
	    		}
	    	}
	    	else if(attackCounter == 24)
	    	{
	    		if(rand.nextInt(2) == 0)
	    		{
	    			this.moveRight(rand.nextDouble() * 0.3D + 0.2D, 16);
	    		}
	    		else
	    		{
	    			this.moveLeft(rand.nextDouble() * 0.3D + 0.2D, 16);
	    		}
	    	}
	    	if(attackCounter >= 80)
	    	{
	    		
	    		attackCounter = 0;
	    	}
	    	break;
    	case SPELLCARD_ATTACK01:
			if(attackCounter == 1)
			{
				useSpellCard(getUsingSpellCardNo());
			}
			else if(attackCounter >= 200)
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
			
			this.dropShotItem(ItemTHShot.ARROW, 8 + rand.nextInt(2) + lootingLevel, 5, 32, YELLOW, 0, THSC_GagoujiCyclone.SPECIAL_GAGOUJI01, 2);
	        
		    this.dropPowerUpItem(8 + this.rand.nextInt(5) + this.rand.nextInt(1 + lootingLevel));
	
	        this.dropPointItem(8 + this.rand.nextInt(5) + this.rand.nextInt(1 + lootingLevel));
		}
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
        return this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL && this.isValidLightLevel() && worldObj.isThundering();
    }

}