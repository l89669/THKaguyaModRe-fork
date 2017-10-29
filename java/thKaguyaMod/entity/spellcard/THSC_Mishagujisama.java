package thKaguyaMod.entity.spellcard;

import static thKaguyaMod.DanmakuConstants.*;
import net.minecraft.util.Vec3;
import thKaguyaMod.THShotLib;

public class THSC_Mishagujisama extends THSpellCard
{
	//祟符「ミシャグジさま」

	public THSC_Mishagujisama()
	{
		this.setNeedLevel(3);
		this.setRemoveTime(20);
		this.setEndTime(90);
		this.setOriginalUserName(SUWAKO);
	}
	
	@Override
	public void spellcard_main()
	{
		if(time% 9 == 3)
		{		
			float angleBase = rand.nextFloat() * 360F;
			Vec3 angle = THShotLib.getVecFromAngle(user.rotationYaw, 0F, 1.0D);
			Vec3 rotate = THShotLib.getVecFromAngle(user.rotationYaw, user.rotationPitch + 90F, 1.0D);
			Vec3 rotate2 = THShotLib.getVectorFromRotation(rotate.xCoord, rotate.yCoord, rotate.zCoord, angle.xCoord, angle.yCoord, angle.zCoord, 90F);
			THShotLib.createCircleShot(user, user, pos(user.posX, user.posY + 0.2D, user.posZ), angle,  1.2F, 30, 1.9D, 0.2D, -0.15D, gravity_Zero(), shot(FORM_RICE, GREEN, THShotLib.SIZE[FORM_RICE], 7.0F, 3, 120), 32, 0.7D, angleBase);
			THShotLib.createCircleShot(user, user, pos(user.posX, user.posY + 0.2D, user.posZ), angle, -1.2F, 30, 1.9D, 0.2D, -0.15D, gravity_Zero(), shot(FORM_RICE, GREEN, THShotLib.SIZE[FORM_RICE], 7.0F, 3, 120), 32, 0.7D, angleBase);
		}
	}
}
