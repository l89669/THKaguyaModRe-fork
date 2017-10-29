package thKaguyaMod.entity.living;

import static thKaguyaMod.DanmakuConstants.*;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.ShotData;
import thKaguyaMod.THShotLib;
import thKaguyaMod.init.THKaguyaConfig;

/** ハナビーパー　爆発時に弾幕をまき散らすクリーパー */
public class EntityDanmakuCreeper extends EntityCreeper
{
    /**
     * Time when this creeper was last in an active state (Messed up code here, probably causes creeper animation to go
     * weird)
     */
    private int lastActiveTime;

    /**
     * The amount of time since the creeper was close enough to the player to ignite
     */
    private int timeSinceIgnited2;
    private int fuseTime2 = 30;

    /** Explosion radius for this creeper. */
    private int explosionRadius = 3;

    public EntityDanmakuCreeper(World par1World)
    {
        super(par1World);
    }

    /** 死亡時に毎tick呼ばれる処理 */
    @Override
    protected void onDeathUpdate()
    {
        ++this.deathTime;

        if (this.deathTime == 40)
        {
            int i;

            if (!this.worldObj.isRemote && (this.recentlyHit > 0 || this.isPlayer()) && !this.isChild() && this.worldObj.getGameRules().getGameRuleBooleanValue("doMobLoot"))
            {
                i = this.getExperiencePoints(this.attackingPlayer);

                while (i > 0)
                {
                    int j = EntityXPOrb.getXPSplit(i);
                    i -= j;
                    this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, j));
                }
            }

            this.setDead();

            for (i = 0; i < 20; ++i)
            {
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                double d2 = this.rand.nextGaussian() * 0.02D;
                this.worldObj.spawnParticle("explode", this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
            }
        }
    }
    
    /** 毎tick呼ばれる処理 */
    @Override
    public void onUpdate()
    {
    	boolean danmakuFlag = false;
    	
    	
        if (this.isEntityAlive())
        {
            /*this.lastActiveTime = this.timeSinceIgnited;
            int i = this.getCreeperState();

            if (i > 0 && this.timeSinceIgnited == 0)
            {
                this.playSound("random.fuse", 1.0F, 0.5F);
            }

            //this.timeSinceIgnited += i;

            if (this.timeSinceIgnited < 0)
            {
                this.timeSinceIgnited = 0;
            }*/
        	timeSinceIgnited2 += this.getCreeperState();

            if (this.timeSinceIgnited2 >= this.fuseTime2)
            {
                this.timeSinceIgnited2 = -300;//this.fuseTime;
                //setHealth(0.0F);
                //onDeathUpdate();
                if (!this.worldObj.isRemote)
                {
                    boolean flag = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
                    float damage = 5.0F;//3 + this.worldObj.difficultySetting * 2;
                    int level = THKaguyaConfig.danmakuLevel;
                    float size = 0.1F + level * 0.1F;
                    int ways[] = {8, 12, 20, 32, 44};
                    int way = ways[level];

                    if (this.getPowered())
                    {
                    	Vec3 angle = THShotLib.angle_Random();
                    	ShotData shot = ShotData.shot(FORM_LIGHT, GREEN, size, damage * 2F);
                        THShotLib.createSphereShot(this, this, THShotLib.pos_Living(this), angle, 0F, 1.2D, 0.01D, 0.00D, THShotLib.gravity(0.0D, -0.02D, 0.0D), shot, way, 0.0D, 0F);
                        shot.color = RED;
                        THShotLib.createSphereShot(this, this, THShotLib.pos_Living(this), angle, 0F, 0.5D, 0.01D, 0.00D, THShotLib.gravity(0.0D, -0.02D, 0.0D), shot, way, 0.0D, 0F);
                        shot.color = YELLOW;
                        THShotLib.createSphereShot(this, this, THShotLib.pos_Living(this), angle, 0F, 0.4D, 0.01D, 0.00D, THShotLib.gravity(0.0D, -0.02D, 0.0D), shot, way, 0.0D, 0F);
                        //this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (float)this.explosionRadius, flag);
                    }
                    else
                    {
                    	Vec3 angle = THShotLib.angle_Random();
                    	ShotData shot = ShotData.shot(FORM_LIGHT, GREEN, size, damage);
                        THShotLib.createSphereShot(this, this, THShotLib.pos_Living(this), angle, 0F, 0.6D, 0.01D, 0.00D, THShotLib.gravity(0.0D, -0.02D, 0.0D), shot, way, 0.0D, 0F);
                        shot.color = RED;
                        THShotLib.createSphereShot(this, this, THShotLib.pos_Living(this), angle, 0F, 0.5D, 0.01D, 0.00D, THShotLib.gravity(0.0D, -0.02D, 0.0D), shot, way, 0.0D, 0F);
                        shot.color = YELLOW;
                        THShotLib.createSphereShot(this, this, THShotLib.pos_Living(this), angle, 0F, 0.4D, 0.01D, 0.00D, THShotLib.gravity(0.0D, -0.02D, 0.0D), shot, way, 0.0D, 0F);
                        //this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (float)this.explosionRadius, flag);
                    }
                    this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "random.explode", 4.0F, (1.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);


                    //this.setDead();
                }
                //timeSinceIgnited = 0;
                //setCreeperState(0);
                danmakuFlag = true;
                //this.setCreeperState(-1);
                
                //setEntityHealth(0.0F);
                //this.setHealth(0.0F);
            }
        }
        
        if(timeSinceIgnited2 >= -100 && timeSinceIgnited2 < 0)
        {
        	timeSinceIgnited2 = 0;
        }
        
        if(timeSinceIgnited2 < -100 && timeSinceIgnited2 > -280)
        {
        	if(this.getHealth() > 0F)
        	{
        		
        		this.onDeathUpdate();
        		//this.deathTime++;
        	}
        }
        
    	if(timeSinceIgnited2 < 20 && timeSinceIgnited2 >= 0)
    	{
    		super.onUpdate();
    	}
        
        /*if(!danmakuFlag this.fuseTime == 0)
        {
        	super.onUpdate();
        }*/
    }

    /**
     * Called when the mob's health reaches 0.
     */
    @Override
    public void onDeath(DamageSource damageSource)
    {
        super.onDeath(damageSource);

        /*if (damageSource.getEntity() instanceof EntitySkeleton)
        {
            int i = Item.record13.itemID + this.rand.nextInt(Item.recordWait.itemID - Item.record13.itemID + 1);
            this.dropItem(i, 1);
            this.dropItem(Item.getItemById(i), 1);
        }*/
    }
    
    /**
	 * 保存するデータの書き込み
	 * @param nbtTagCompound : NTBタグ
	 */
    public void writeEntityToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeEntityToNBT(nbtTagCompound);

        nbtTagCompound.setShort("Fuse", (short)200);
    }
    

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    /*protected int getDropItemId()
    {
        return Item.gunpowder.itemID;
    }*/
    
    /**
     * Returns the item ID for the item the mob drops on death.
     */
    /*protected int getDropItemId()
    {
        //return Item.gunpowder;
    }*/
}
