package thKaguyaMod.entity.spellcard;

import static thKaguyaMod.DanmakuConstants.*;
import static thKaguyaMod.THShotLib.*;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import thKaguyaMod.THShotLib;

public class THSC_Yasaka_no_Kamikaze extends THSpellCard
{
	//大奇跡「八坂の神風」
	
	public THSC_Yasaka_no_Kamikaze()
	{
		this.setNeedLevel(3);
		this.setRemoveTime(100);
		this.setEndTime(270);
		this.setOriginalUserName(SANAE);
	}
	
	@Override
	public void spellcard_main()
	{
		if(time < 5 || user == null)
		{
			return;
		}
		
		Vec3 angle = THShotLib.getVecFromAngle(user.rotationYaw, user.rotationPitch, -1.0D);
		if(!target.isDead)
		{
			angle = THShotLib.angle_ToPos(this.pos_Target(), this.pos_User());
		}
		
		if( time % 46 < 23)
		{
			THShotLib.createRingShot(user, user, pos_User(), angle/*getVecFromAngle(0F, -90F)*/,  180F, 1, 0.0D, 1.6D, 0.1D, gravity_Zero(), shot(FORM_RICE, GREEN, SIZE[FORM_RICE], 18F, 10, 40, THShotLib.WIND01), 8, Math.cos((float)(time * 2) / 180F * (float)Math.PI - (float)Math.PI) * 2.0D +  3.0D , MathHelper.sin((float)(time * 16) / 180F * (float)Math.PI) * 50F + 50F, time * 10F);
		}
		else
		{
			THShotLib.createRingShot(user, user, pos_User(), angle/*getVecFromAngle(0F, -90F)*/, -180F, 2, 0.0D, 1.6D, 0.1D, gravity_Zero(), shot(FORM_RICE, AQUA, SIZE[FORM_RICE], 18F, 10, 40, THShotLib.WIND01), 8, Math.cos((float)(time * 2) / 180F * (float)Math.PI - (float)Math.PI) * 2.0D + 3.0D, MathHelper.sin((float)(-time * 16) / 180F * (float)Math.PI) * 50F + 50F, -time * 10F);
		}
	}
}
