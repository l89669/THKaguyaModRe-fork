package thKaguyaMod.entity.living;
	 
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import thKaguyaMod.init.THKaguyaItems;
import thKaguyaMod.registry.DanmakuPatternRegistry;

/** 使い魔 */
public class EntityFamiliar extends EntityTHFairy
{

	/** 使い魔のコンストラクタ(引数がWorldのみは必須） */
     public EntityFamiliar(World world)
     {
         super(world);
         
         this.setSize(0.6F, 0.6F);//MOBの当たり判定の大きさ 横奥行き、高さ、大きさ
     	lastTime = 0;
     	
     	experienceValue = 0;//経験値の量

     	setForm((byte)rand.nextInt(3));
     	setPattern(rand.nextInt(DanmakuPatternRegistry.pattern.size()));
     	
     	lostTarget = 0;
     	this.setSpeed(0.3D);
     	this.setSpecies(this.SPECIES_SHIKIGAMI_FAMILIAR);
     	this.isFlyingMode = true;
     	
     	this.setAttackDistance(8.0D);
     	this.setDetectionDistance(0.0D);
     	this.setFlyingHeight(2);
     }
     
     //死んでいるときに呼ばれる
     protected void onDeathUpdate()
     {
    	 if(!worldObj.isRemote)
    	 {
    		 setDead();
    	 }
     }
     
     /**
      * 弾を出したEntityLivingBaseを返す（要は発射主）
      * @return 弾の発射主。いない場合はnullを返す
      */
     public EntityLivingBase getShooter()
     {
    	 // 何かに乗っている場合
    	 if(this.ridingEntity != null)
    	 {
    		 // 乗っているEntityが生物の場合
    		 if(ridingEntity instanceof EntityLivingBase)
    		 {
    			 EntityLivingBase currentEntity = (EntityLivingBase)ridingEntity;
				 if(currentEntity.getHealth() <= 0.0F)
				 {
					 return null;
				 }
    			 // 乗っているEntityが使い魔の場合
    			 if(ridingEntity instanceof EntityFamiliar)
        		 {
        			 do
        			 {
        				 if(currentEntity == null)
        				 {
        					 return null;
        				 }
        				 if(currentEntity.ridingEntity instanceof EntityLivingBase)
        				 {
        					 currentEntity = (EntityLivingBase)currentEntity.ridingEntity;
        				 }
        				 else
        				 {
        					 return null;
        				 }
        			 } while(currentEntity.ridingEntity instanceof EntityFamiliar);
        			 if(currentEntity instanceof EntityLivingBase)
        			 {
        				 return (EntityLivingBase)currentEntity;
        			 }
        		 }
    			 else
    			 {
    				 return (EntityLivingBase)ridingEntity;
    			 }
    		 }
    	 }
    	 return null;
     }

 	/** 毎tick呼ばれる処理 */
     public void onUpdate()
     {	
     	
     	if(ticksExisted <= lastTime)
     	{
     		return;
     	}
     	else
     	{
     		if(ticksExisted >= 1 && getShooter() == null)
     		{
     			if(!worldObj.isRemote)
     			{
     				setDead();
     			}
     			return;
     		}
     		if(ridingEntity instanceof EntityCreature)
     		{
     			EntityCreature riding = (EntityCreature)ridingEntity;
     			entityToAttack = riding.getEntityToAttack();
     		}
     		super.onUpdate();
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
     		return THKaguyaItems.point_item;
     	}
     }
 	
 	//倒れたときに落とすアイテム
 	@Override
 	protected void dropFewItems(boolean hasBeenAttackedByPlayer, int lootingLevel)
 	{

 	}
 	
    //生きてるときに出す音
    @Override
    protected String getLivingSound()
    {
        return null;
    }

    //攻撃を受けたときの音
    @Override
    protected String getHurtSound()
    {
        return null;
    }

	//倒れたときの音
    @Override
    protected String getDeathSound()
    {
        return null;
    }
    
	//Entityが乗ったときのそのEntityを置く高さ
    public double getMountedYOffset()
    {
        return 0.5D;
    }
    

	//他の物体と衝突したときのその物体の当たり判定？
    @Override
    public AxisAlignedBB getCollisionBox(Entity entity)
    {
        return null;
    }

	//当たり判定を設定
    @Override
    public AxisAlignedBB getBoundingBox()
    {
        return null;
    }
    
    @Override
    public boolean canBeCollidedWith()
    {
    	return false;
    }

 }

