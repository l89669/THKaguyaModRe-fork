package thKaguyaMod.entity.living;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import thKaguyaMod.THKaguyaCore;

/** 霖之助 */
public class EntityRinnosuke extends EntityVillager
{

	public EntityRinnosuke(World world) {
		super(world);
		this.setProfession(THKaguyaCore.villagerRinnosukeId);
	}
	
    public EntityRinnosuke(World world, int profession)
    {
    	super(world, THKaguyaCore.villagerRinnosukeId);
    }

    public int getProfession()
    {
        return THKaguyaCore.villagerRinnosukeId;
    }
    
    //デスポーンするか
    @Override
    protected boolean canDespawn()
    {
        return true;
    }
    
    //一つのチャンクに湧く最大数
    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }
    
    @Override
    public EntityVillager createChild(EntityAgeable p_90011_1_)
    {
    	return null;
    }
    
    @Override
    public boolean isMating()
    {
        return false;
    }
    
	//自然スポーンするときに呼ばれる。tureならスポーンする
	@Override
    public boolean getCanSpawnHere()
    {
		if(super.getCanSpawnHere() == false)
		{
			return false;
		}
		
    	if(rand.nextInt(100) < 98)
    	{
    		return false;
    	}
    	
    	int yPosition = MathHelper.floor_double(this.boundingBox.minY);
    	int xPosition = MathHelper.floor_double(this.posX);
        int zPosition = MathHelper.floor_double(this.posZ);
    	Block pointBlock = worldObj.getBlock(xPosition, yPosition - 1, zPosition);
    	//地面が草ブロックか土ブロックか砂ブロックならスポーンする
    	//if(pointBlock == Block.grass.blockID || pointBlock == Block.dirt.blockID || pointBlock == Block.sand.blockID)
    	if( 	worldObj.getBlock(xPosition - 1, yPosition - 1, zPosition) == pointBlock &&
    			worldObj.getBlock(xPosition + 1, yPosition - 1, zPosition) == pointBlock &&
    			worldObj.getBlock(xPosition, yPosition - 1, zPosition - 1) == pointBlock &&
    			worldObj.getBlock(xPosition, yPosition - 1, zPosition + 1) == pointBlock)
    	{
    		return false;
    	}
    	
    	return true;
    }
}
