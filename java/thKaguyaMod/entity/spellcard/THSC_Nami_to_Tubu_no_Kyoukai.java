package thKaguyaMod.entity.spellcard;

import static thKaguyaMod.DanmakuConstants.*;
import net.minecraft.util.MathHelper;
import thKaguyaMod.ShotData;
import thKaguyaMod.THShotLib;

public class THSC_Nami_to_Tubu_no_Kyoukai extends THSpellCard
{
	//境符「波と粒の境界」
	private float yaw;
	private float pitch;
	
	public THSC_Nami_to_Tubu_no_Kyoukai()
	{
		this.setNeedLevel(5);
		this.setRemoveTime(40);
		this.setEndTime(320);
		this.setOriginalUserName(YUKARI);
	}
	
	@Override
	public void spellcard_main()
	{
		if(time == 0)
		{
			yaw = 0.0F;
			pitch = 0.0F;
		}
		if(time >= 5 && time < 280)
		{
			yaw += MathHelper.cos((float)(time + 75) / 50F) * 17F;
			pitch += MathHelper.sin(MathHelper.sin((time + 75) / 50F) * 3.14F) * 17F;
			float size = 0.3F;
			float damage = 9.0F;//5.0F;
			ShotData shot = ShotData.shot(FORM_LIGHT, PURPLE, size, damage, 0, 40);
			
			THShotLib.createSphereShot(user, user, pos_User(), getVecFromAngle(yaw, pitch), 0F, rotate_Default(), 0F, 9999, 0.2D, 0.6D, 0.05D, gravity_Zero(), shot, 12, 2.0D, 0F);
		}
	}
}
