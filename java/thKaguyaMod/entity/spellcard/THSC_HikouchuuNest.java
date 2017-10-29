package thKaguyaMod.entity.spellcard;

import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import thKaguyaMod.entity.item.EntitySukima;

public class THSC_HikouchuuNest extends THSpellCard
{
	//幻巣「飛光虫ネスト」
	
	public THSC_HikouchuuNest()
	{
		this.setNeedLevel(4);
		this.setRemoveTime(50);
		this.setEndTime(160);
		this.setOriginalUserName(YUKARI);
	}
	
	@Override
	public void spellcard_main()
	{
		boolean flag = false;
		int count = 0;
		while(!flag && count < 4)
		{
			double length = rand.nextDouble() * 5.0D + 1.0D;
			float angleXZ = (card.rotationYaw + 90F) / 180F * (float)Math.PI + rand.nextFloat() * (float)Math.PI;
			float angleY  = rand.nextFloat() * (float)Math.PI - (float)Math.PI / 2.0F;
			double xVector = Math.sin(angleXZ) * Math.cos(angleY) * length;
			double yVector = Math.sin(angleY) * length;
			double zVector = Math.cos(angleXZ) * Math.cos(angleY) * length;
			double xPos, yPos, zPos;
		    //始点（現在地）
	    	Vec3 vec3d = Vec3.createVectorHelper(user.posX, user.posY, user.posZ);
	    	//終点（現在地に移動量を足した点）
	    	Vec3 vec3d1 = Vec3.createVectorHelper(user.posX + xVector, user.posY + yVector, user.posZ + zVector);
	        //始点と終点からブロックとの当たりを取得
	    	MovingObjectPosition movingObjectPosition = world.func_147447_a(vec3d, vec3d1, false, true, true);
	    	vec3d = Vec3.createVectorHelper(user.posX, user.posY, user.posZ);
	    	vec3d1 = Vec3.createVectorHelper(user.posX + xVector, user.posY + yVector, user.posZ + zVector);
	    	//何らかのブロックに当たっているなら
	        if (movingObjectPosition != null)
	        {
	        	count ++;
	        }
			else
			{
				xPos = user.posX + xVector;
				yPos = user.posY + yVector;
				zPos = user.posZ + zVector;
				EntitySukima entitySukima = new EntitySukima(world, user, xPos, yPos, zPos, user.rotationYaw, 17);
				if(!world.isRemote)
				{
					world.spawnEntityInWorld(entitySukima);
				}
				flag = true;
			}
		}
	}
}
