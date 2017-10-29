package thKaguyaMod.entity.spellcard;

import net.minecraft.util.Vec3;
import thKaguyaMod.THShotLib;

/** 禁弾「カタディオプトリック」 */
public class THSC_Catadioptric extends THSC_ScarletShoot
{
	
	public THSC_Catadioptric()
	{
		this.setNeedLevel(3);
		this.setRemoveTime(40);
		this.setEndTime(110);
		this.setOriginalUserName(FLANDORE);
	}
	
	@Override
	public void spellcard_main()
	{
		if(time % 25 == 1 && time < 75)
		{
			float angle;
			float angleSpan = 90F / 4F;
			float angleBase = 180F;
			if(time == 26)
			{
				angleBase = 90F;
			}
			else if(time == 51)
			{
				angleBase = -90F;
			}
			angle = angleBase - angleSpan * 2;
			
	    	for(int i = 0; i < 5; i++)
	    	{
				Vec3 look = THShotLib.getRotationVectorFromAngle(user.rotationYaw, user.rotationPitch, angle, 1.0D);
				scarletShot(look, 0.6D, true);
	    		angle += angleSpan;
	    	}
	    	THShotLib.playShotSound(user);
		}
	}
}
