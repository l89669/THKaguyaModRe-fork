package thKaguyaMod.entity.living;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.spellcard.EntitySpellCard;
import thKaguyaMod.init.THKaguyaItems;

/** 楽園の素敵な巫女　博麗 霊夢 */
public class EntityReimu extends EntityDanmakuMob
{
	
	public EntityReimu(World world)
    {
        super(world);
        
        this.setSize(1.0F, 1.8F);//MOBの当たり判定の大きさ 横奥行き、高さ、大きさ
        
        this.experienceValue = 250;//経験値の量
        
        this.setDanmakuMobName("Reimu Hakurei");
        this.setSpecies(this.SPECIES_HUMAN);
    	
    	//this.setDanmakuPattern(NOT_ATTACK);
    	this.setDanmakuPattern(SPELLCARD_ATTACK01);
    	this.setMaxHP(74.0F);
        this.setHealth(74.0F);
        this.setSpeed(0.4F);
        this.setAttackDistance(14.0D);
    	this.setDetectionDistance(0.0D);
    	this.setFlyingHeight(4);
    	this.isFlyingMode = false;
    	
    	this.isSpellCardMode = false;
    	this.attackInterval = 0;
    }
	
    /**
     * 使用しているスペルカードNoを返す
     * @return 使用しているスペルカードNo
     */
    public int getUsingSpellCardNo()
    {
    	switch(this.getDanmakuPattern())
    	{
    		case SPELLCARD_ATTACK01:
    			return EntitySpellCard.SC_REIMU_MusouFuuin;
    		default:
    			return -1;
    	}
    }
    
    //死んでいるときに呼ばれる
    @Override
    protected void onDeathUpdate()
    {
    	switch(getDanmakuPattern())
    	{
    		case NORMAL_ATTACK01:
    			setFlyingHeight(2);
    			moveDanmakuAttack(SPELLCARD_ATTACK01, 40, 60.0F, 160);
    			break;
    		case SPELLCARD_ATTACK01:
    			moveDanmakuAttack(ATTACK_END, 90, 40.0F, 160);
    			break;
    		default:
    			if(deathTime % 6 == 0)
    			{
    				THShotLib.explosionEffect(worldObj, posX, posY, posZ, 1.0F + deathTime * 0.1F);
    			}
    			super.onDeathUpdate();
    			break;
    	}
    }

    
	//常時呼ばれる
    @Override
    public void onUpdate()
    {
    	super.onUpdate();
    }

    
    /**
     * 弾幕のパターンを記述
     * @param level : EASY～LUNATICの難易度
     */
    @Override
    public void danmakuPattern(int level)
    {
    	Vec3 look = this.getLookVec();
    	switch(getDanmakuPattern())
    	{
    		case NORMAL_ATTACK01:
    			//danmaku01(look, level);
    			break;
    		case SPELLCARD_ATTACK01:
    			if(attackCounter == 1)
    			{
    				this.useSpellCard(EntitySpellCard.SC_REIMU_MusouFuuin);
    			}
    			else if(attackCounter >= 140)
    			{
    				attackCounter = 0;
    			}
    			break;
    		/*case SPELLCARD_ATTACK02:
    			if(attackCounter == 1)
    			{
    				this.useSpellCard(EntitySpellCard.SC_SANAE_Moses_no_Kiseki);
    			}
    			else if(attackCounter >= 140)
    			{
    				attackCounter = 0;
    			}
    			break;
    		case SPELLCARD_ATTACK03:
    			if(attackCounter == 1)
    			{
    				this.useSpellCard(EntitySpellCard.SC_SANAE_Yasaka_no_Kamikaze);
    			}
    			else if(attackCounter >= 140)
    			{
    				attackCounter = 0;
    			}
    			break;
    		case SPELLCARD_ATTACK04:
    			if(attackCounter == 1)
    			{
    				this.useSpellCard(EntitySpellCard.SC_SANAE_YouryokuSpoiler);
    			}
    			else if(attackCounter >= 140)
    			{
    				attackCounter = 0;
    			}
    			break;*/
    		default:
    			break;
    	}
    }
    
    //周りの妖精を呼び出すことができるか
    @Override
    protected boolean canFairyCall()
    {
    	return false;
    }
    
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

    
    //無敵かどうかを返す
    @Override
    public boolean isEntityInvulnerable()
    {
    	if(getDanmakuPattern() == NOT_ATTACK)
    	{
    		return true;
    	}
    	return false;
    }

    @Override
    protected void updateAITasks()
    {
        super.updateAITasks();
    }
	
	//倒れたときに落とすアイテム
	@Override
	protected void dropFewItems(boolean par1, int par2)
    {
		super.dropFewItems(par1, par2);
		
		if(this.isSpellCardAttack())
		{
	        int j = 12;//this.rand.nextInt(15) + this.rand.nextInt(1 + par2);
	        int k;
	        EntityItem item;
	        Vec3 vec3;
	
	        for (k = 0; k < j; ++k)
	        {
	            item = this.dropItem(THKaguyaItems.power_item, 1);
	            item.rotationYaw = k * 30F;
	            item.rotationPitch = -60F;
	            vec3 = THShotLib.getVecFromAngle(item.rotationYaw, item.rotationPitch, 0.5F);
	            item.setPosition(item.posX + vec3.xCoord * 2.0D, item.posY, item.posZ + vec3.zCoord * 2.0D);
	            
	            item.motionX = vec3.xCoord;
	            item.motionY = vec3.yCoord;
	            item.motionZ = vec3.zCoord;
	        }
	
	        for (k = 0; k < j; ++k)
	        {
	            item = this.dropItem(THKaguyaItems.point_item, 1);
	            item.rotationYaw = k * 30F + 15F;
	            item.rotationPitch = -60F;
	            vec3 = THShotLib.getVecFromAngle(item.rotationYaw, item.rotationPitch, 0.3F);
	            item.setPosition(item.posX + vec3.xCoord * 2.0D, item.posY, item.posZ + vec3.zCoord * 2.0D);
	            
	            item.motionX = vec3.xCoord;
	            item.motionY = vec3.yCoord;
	            item.motionZ = vec3.zCoord;
	        }
		}
        /*if(getDanmakuPattern() == SPELLCARD_ATTACK02)
        {
        	this.dropItem(THKaguyaItems.icicle_sword, 1);
        	
        }*/
    }
    
	@Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

	//自然スポーンするときに呼ばれる。tureならスポーンする
	@Override
    public boolean getCanSpawnHere()
    {
    	return false;
    }
}