package thKaguyaMod.entity.spellcard;

import net.minecraft.util.Vec3;
import thKaguyaMod.LaserData;
import thKaguyaMod.THShotLib;
import thKaguyaMod.init.THKaguyaConfig;

public class THSC_NonDirectionalLaser extends THSpellCard
{
	//恋符「ノンディレクショナルレーザー」

	public THSC_NonDirectionalLaser()
	{
		this.setNeedLevel(3);
		this.setRemoveTime(60);
		this.setEndTime(103);
		this.setOriginalUserName(MARISA);
	}
	
	@Override
	public void spellcard_main()
	{
		if(time == 3)
		{
			world.playSoundAtEntity(user, "thKaguyaMod.masterspark", THKaguyaConfig.MasterSparkVol, 1.0F);
			for(int i = 0; i < 5; i++)
			{
				Vec3 look = user.getLookVec();
				Vec3 rotate = THShotLib.getVecFromAngle(user.rotationYaw, user.rotationPitch - 90F);
				Vec3 move = THShotLib.getVectorFromRotation(rotate.xCoord, rotate.yCoord, rotate.zCoord, look.xCoord, look.yCoord, look.zCoord, i * 72F);
				THShotLib.createLaserB(user, user, pos(0.0D, THShotLib.getPosYFromEye(user) - user.posY, 0.0D), move, rotate, -4F, 9999, LaserData.laser(THShotLib.RED + i, 0.7F, 20.8F, 7.0F, 25, 60, 0), user, 1.5D, 1.0F);
			}
		}
		if(time == 63)
		{
			world.playSoundAtEntity(user, "thKaguyaMod.masterspark", THKaguyaConfig.MasterSparkVol, 1.0F);
			for(int i = 0; i < 5; i++)
			{
				Vec3 look = user.getLookVec();
				Vec3 rotate = THShotLib.getVecFromAngle(user.rotationYaw, user.rotationPitch - 90F);
				Vec3 move = THShotLib.getVectorFromRotation(rotate.xCoord, rotate.yCoord, rotate.zCoord, look.xCoord, look.yCoord, look.zCoord, i * 72F);
				THShotLib.createLaserB(user, user, pos(0.0D, THShotLib.getPosYFromEye(user) - user.posY, 0.0D), move, rotate,  4F, 9999, LaserData.laser(THShotLib.RED + i, 0.7F, 20.8F, 7.0F, 25, 60, 0), user, 1.5D, 1.0F);
			}
		}
	}
}
