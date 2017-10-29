package thKaguyaMod.entity.spellcard;

import static thKaguyaMod.DanmakuConstants.*;
import thKaguyaMod.THShotLib;

public class THSC_MosesMiracle extends THSpellCard
{
	//開海「モーゼの奇跡」
	
	public THSC_MosesMiracle()
	{
		this.setNeedLevel(2);
		this.setRemoveTime(80);
		this.setEndTime(180);
		this.setOriginalUserName(SANAE);
	}
	
	@Override
	public void spellcard_main()
	{
		if(time % 2 == 0)
		{
			return;
		}
		double angle = (double)time * 6.0D;
		float baseAngle = user.rotationYaw;
		if(target != null)
		{
			baseAngle = (float)Math.atan2(card.posX - target.posX, -(card.posZ - target.posZ) ) / 3.141593F * 180F;
		}
		
		for(int i = -5; i < 6; i++)
		{
			double baseX = cardPosX - Math.sin(angle / 180F * 3.141593F) * Math.cos(baseAngle / 180F * 3.141593F) * 3.0D;
			double baseZ = cardPosZ + Math.cos(angle / 180F * 3.141593F) * Math.sin(baseAngle / 180F * 3.141593F) * 3.0D;
			double sideX = -Math.sin((baseAngle + 90F) / 180F * 3.141593F);
			double sideZ = Math.cos((baseAngle + 90F) / 180F * 3.141593F);
			THShotLib.createShot(user, user,  pos(baseX + sideX * 2.5D, cardPosY + i, baseZ + sideZ * 2.5D), THShotLib.angle_ToPos(this.pos_Card(), THShotLib.pos_Living(target)), 0F, 0.9D, 0.9D, 0.0D, gravity_Zero(), shot(FORM_LIGHT, BLUE, 0.8F, 7.0F, 0, 50));
			THShotLib.createShot(user, user,  pos(baseX - sideX * 2.5D, cardPosY + i, baseZ - sideZ * 2.5D), THShotLib.angle_ToPos(this.pos_Card(), THShotLib.pos_Living(target)), 0F, 0.9D, 0.9D, 0.0D, gravity_Zero(), shot(FORM_LIGHT, BLUE, 0.8F, 7.0F, 0, 50));
		}
	}
}
