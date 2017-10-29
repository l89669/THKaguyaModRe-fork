package thKaguyaMod.entity.spellcard;

import net.minecraft.util.Vec3;
import thKaguyaMod.THShotLib;

/** 奇術「エターナルミーク」 */
public class THSC_EternalMeek extends THSpellCard
{
	
	public THSC_EternalMeek()
	{
		this.setNeedLevel(2);
		this.setRemoveTime(20);
		this.setEndTime(50);
		this.setOriginalUserName(SAKUYA);
	}
	
	@Override
	public void spellcard_main()
	{
		if(time < 40)
		{
			Vec3 angle = user.getLookVec();//使用者の見ている方向を角度の基準にする
			if(!target.isDead)
			{
				//始点と終点を指定して（位置ベクトル）、始点から終点への方向ベクトルを取得します
				angle = THShotLib.angle_ToPos(this.pos_User(), this.pos_Target());//ターゲットが生きているならターゲットの方向を基準にする
			}
			THShotLib.createRandomRingShot(user, user, pos_User(angle, 0.7D), angle, 0F, 9999, 1.5D, 1.5D, 0.0D, gravity_Zero(), shot(THShotLib.FORM_KNIFE, THShotLib.BLUE, THShotLib.SIZE[THShotLib.FORM_KNIFE], 3F, 3, 60, 0), 3, 0.1D, 50F);
			//弾の発射音を出す
			THShotLib.playShotSound(user);
		}
	}
}
