package thKaguyaMod.entity.spellcard;

import net.minecraft.util.Vec3;
import thKaguyaMod.LaserData;
import thKaguyaMod.THShotLib;

public class THSC_MoonlightRay extends THSpellCard
{
	//月符「ムーンライトレイ」
	
	public THSC_MoonlightRay()
	{
		this.setNeedLevel(1);
		this.setRemoveTime(30);
		this.setEndTime(60);
		this.setOriginalUserName(RUMIA);
	}
	
	@Override
	public void spellcard_main()
	{
		if(time == 3)
		{
			Vec3 look = user.getLookVec();
			Vec3 laserVec;
			Vec3 rotate;
			Vec3 over = THShotLib.getVecFromAngle(user.rotationYaw, user.rotationPitch + 90F);
			int way = 7;
			float angle = 0F;
			float angleSpan = 360F / way;
			for(int i = 0; i < way; i++)
			{
				laserVec = THShotLib.getVectorFromRotation(look.xCoord, look.yCoord, look.zCoord, over.xCoord, over.yCoord, over.zCoord, angle);
				rotate = THShotLib.getOuterProduct(look, laserVec);
				THShotLib.createLaserB(user, user, pos_User(), laserVec, 
						rotate, -2F, 9999, LaserData.laser(THShotLib.BLUE, 0.8F + level * 0.2F, 40.0F, 6.0F, 10, 29 + level * 5, 0), user, 1.0D, user.height / 2.0D);
				angle += angleSpan;
			}
		}
	}
}
