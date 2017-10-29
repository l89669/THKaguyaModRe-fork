 package thKaguyaMod.entity.living;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import thKaguyaMod.THShotLib;
import thKaguyaMod.init.THKaguyaItems;
import thKaguyaMod.item.ItemTHShot;

/** ひまわり妖精 */
public class EntitySunFlowerFairy extends EntityTHFairy
{

	/** ひまわり妖精のコンストラクタ（引数Worldのみは必須） */
    public EntitySunFlowerFairy(World world)
    {
        super(world);
        
        this.setSize(0.9F, 1.7F);//MOBの当たり判定の大きさ 横奥行き、高さ、大きさ
    	
    	experienceValue = 30;//経験値の量

    	setForm((byte)10);
        this.setMaxHP(20.0F);
        this.setHealth(20.0F);
    }
    
    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData entityLivingData)
    {
        Object p_110161_1_1 = super.onSpawnWithEgg(entityLivingData);

        // 使い魔の設定
        EntityFamiliar familiar = new EntityFamiliar(this.worldObj);
        familiar.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
        // 使い魔を出現させる
        this.worldObj.spawnEntityInWorld(familiar);
        // 使い魔にこのひまわり妖精を乗せる
        familiar.mountEntity(this);

        return (IEntityLivingData)p_110161_1_1;
    }

    
  

	/** 毎tick呼ばれる処理 */
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

			this.dropShotItem(ItemTHShot.MEDIUM, rand.nextInt(12) + lootingLevel * 2, 7, 8, THShotLib.GREEN, 0, 0, 2);
		       
			this.dropPowerUpItem(this.rand.nextInt(20) + this.rand.nextInt(3 + lootingLevel * 2));
		
			this.dropPointItem(this.rand.nextInt(20) + this.rand.nextInt(3 + lootingLevel * 2));
			
			// ５％の確立でエクステンドアイテムをドロップ
			if(rand.nextInt(100) < 5)
			{
				this.dropExtendItem(this.pos(), this.angle(this.rotationYaw, -90F));
			}
			// ２０％の確立でスペルカードアイテムをドロップ（エクステンドと重複はしない）
			else if(rand.nextInt(100) < 25)
			{
				this.dropSpellCardItem(this.pos(), this.angle(this.rotationYaw, -90F));
			}


		}
    }
    
    //一つのチャンクに湧く最大数
    @Override
    public int getMaxSpawnedInChunk()
    {
        return 3;
    }

}
