 package thKaguyaMod.entity.living;

import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import thKaguyaMod.THShotLib;
import thKaguyaMod.init.THKaguyaItems;
import thKaguyaMod.item.ItemTHShot;
import thKaguyaMod.registry.DanmakuPatternRegistry;

/** 幽霊 */
public class EntityTHPhantom extends EntityTHFairy
{

    public EntityTHPhantom(World world)
    {
        super(world);
        
        this.setSize(1.0F, 1.0F);//MOBの当たり判定の大きさ 横奥行き、高さ、大きさ
    	lastTime = 0;
    	
    	experienceValue = 5;//経験値の量

    	setForm((byte)rand.nextInt(8));
    	setPattern(rand.nextInt(DanmakuPatternRegistry.pattern.size()));
    	
    	lostTarget = 0;
    	this.setSpeed(0.18D);
    	this.setSpecies(this.SPECIES_PHANTOM);
    	isFlyingMode = true;
    	
    	this.setAttackDistance(6.0D);
    	this.setDetectionDistance(30.0D);
    	this.setFlyingHeight(3);
    	
    	height = 1.0F;
    }
    
    //死んでいるときに呼ばれる
    protected void onDeathUpdate()
    {
    	if(!worldObj.isRemote)
    	{
    		setDead();
    	}
    	
    }

	//常時呼ばれる
    public void onUpdate()
    {	
    	//体力がないなら動かない
    	if(this.getHealth() <= 0)
    	{
    		motionX = 0.0D;
    		motionY = 0.00D;
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
    	//強い光で消滅
        float f = this.getBrightness(1.0F);

        if (this.getBrightness(1.0F) > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)))
        {
        	setHealth(0.0F);
        }
    }
    
    //周りの妖精を呼び出すことができるか
    @Override
    protected boolean canFairyCall()
    {
    	return false;
    }
    
    //妖精を呼び出せる最大距離
    @Override
    protected double getFairyCallDistance()
    {
    	return 0.0D;
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
			
			this.dropShotItem(ItemTHShot.SMALL, rand.nextInt(2) + lootingLevel, 3, 0, THShotLib.RED, 0, 0, 2);
	        
		    this.dropPowerUpItem(this.rand.nextInt(5) + this.rand.nextInt(1 + lootingLevel));
	
	        this.dropPointItem(this.rand.nextInt(5) + this.rand.nextInt(1 + lootingLevel));
	        
	        this.dropItem(THKaguyaItems.soulTorch, (rand.nextInt(10) / 9) + this.rand.nextInt(1 + lootingLevel));
		}
    }
    
    //一つのチャンクに湧く最大数
    @Override
    public int getMaxSpawnedInChunk()
    {
        return 10;
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
    
    //Entityの発する音の大きさ
    @Override
    protected float getSoundVolume()
    {
        return 0.0F;
    }

    //生きてるときに出す音
    /*@Override
    protected String getLivingSound()
    {
        return "";
    }

    //攻撃を受けたときの音
    @Override
    protected String getHurtSound()
    {
        return "";
    }

	//倒れたときの音
    @Override
    protected String getDeathSound()
    {
        return "";
    }*/

}



