package thKaguyaMod.entity.spellcard;

import static thKaguyaMod.DanmakuConstants.*;
import net.minecraft.util.Vec3;
import thKaguyaMod.THShotLib;

public class THSC_Kasho_no_Eimin extends THSpellCard
{
	//死蝶「華胥の永眠」
	
	public THSC_Kasho_no_Eimin()
	{
		this.setNeedLevel(3);
		this.setRemoveTime(40);
		this.setEndTime(150);
		this.setOriginalUserName(YUYUKO);
	}
	
	@Override
	public void spellcard_main()
	{
		if(time % 6 == 3 && time < 90)
		{		
			Vec3 angle = THShotLib.getVecFromAngle(user.rotationYaw, user.rotationPitch, 1.0D);
			if(!target.isDead)
			{
				angle = THShotLib.angle_ToPos(this.pos_User(), this.pos_Target());
			}
			int color = PURPLE;
			if(time % 9 == 0)
			{
				color = YELLOW;
			}
			float angleBase = rand.nextFloat() * 360F;
			THShotLib.createCircleShot(user, user, pos_User(), angle,  1.6F, 30, 0.2D, 0.7D, 0.01D, gravity_Zero(), shot(THShotLib.FORM_BUTTERFLY, color, THShotLib.SIZE[THShotLib.FORM_BUTTERFLY], 6.0F, 3, 70), 15, 0.8D, angleBase);
			THShotLib.createCircleShot(user, user, pos_User(), angle, -1.6F, 30, 0.2D, 0.7D, 0.01D, gravity_Zero(), shot(THShotLib.FORM_BUTTERFLY, color, THShotLib.SIZE[THShotLib.FORM_BUTTERFLY], 6.0F, 3, 70), 15, 0.8D, angleBase);
		}
	}
}
