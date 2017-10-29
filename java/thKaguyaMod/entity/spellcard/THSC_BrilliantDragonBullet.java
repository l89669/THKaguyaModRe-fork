package thKaguyaMod.entity.spellcard;

import static thKaguyaMod.DanmakuConstants.*;
import net.minecraft.entity.Entity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.LaserData;
import thKaguyaMod.ShotData;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.shot.EntityTHShot;
import thKaguyaMod.entity.shot.ISpecialShot;
import thKaguyaMod.registry.SpecialShotRegistry;

/** 神宝「ブリリアントドラゴンバレッタ」 */
public class THSC_BrilliantDragonBullet extends THSpellCard implements ISpecialShot
{
	
	public static final int SPECIAL_DRAGONBULLET01 = 4100;
	
	public THSC_BrilliantDragonBullet()
	{
		this.setNeedLevel(2);
		this.setRemoveTime(20);
		this.setEndTime(90);
		this.setOriginalUserName(KAGUYA);
		
		SpecialShotRegistry.registerSpecialShot(THSC_BrilliantDragonBullet.class, SPECIAL_DRAGONBULLET01);
	}
	
	@Override
	public void spellcard_main()
	{
		if(time % 5 == 0)
		{
			//Vec3 angle = user.getLookVec();//使用者の見ている方向を角度の基準にする
			Vec3 angle = THShotLib.angle(user.rotationYaw + time * 4 - 90, user.rotationPitch);
			Vec3 angle2 = THShotLib.angle(user.rotationYaw - time * 4 + 90, user.rotationPitch);
			/*if(!target.isDead)
			{
				//始点と終点を指定して（位置ベクトル）、始点から終点への方向ベクトルを取得します
				angle = THShotLib.angle_ToPos(this.pos_User(), this.pos_Target());//ターゲットが生きているならターゲットの方向を基準にする
			}*/
			//Vec3 angle = THShotLib.angle(user.rotationYaw + time - 90, 0F);
			THShotLib.createShot(user, user, pos_User(), angle, 0F, 0.4F, 0.4F, 0.0F, gravity_Zero(), ShotData.shot(THShotLib.FORM_FAMILIAR, RED, 1.0F, 1.0F, 0, 5, SPECIAL_DRAGONBULLET01));
			THShotLib.createShot(user, user, pos_User(), angle2, 0F, 0.4F, 0.4F, 0.0F, gravity_Zero(), ShotData.shot(THShotLib.FORM_FAMILIAR, RED, 1.0F, 1.0F, 0, 5, SPECIAL_DRAGONBULLET01));
			//THShotLib.createRandomRingShot(user, user, pos_User(angle, 0.7D), angle, 0F, 9999, 1.5D, 1.5D, 0.0D, gravity_Zero(), shot(THShotLib.FORM_KNIFE, THShotLib.BLUE, THShotLib.SIZE[THShotLib.FORM_KNIFE], 3F, 3, 60, 0), 3, 0.1D, 50F);
			//弾の発射音を出す
			//THShotLib.playShotSound(user);
		}
	}

	@Override
	public void specialShot_move(World world, int id, EntityTHShot shot) {
		switch(id)
		{
		case SPECIAL_DRAGONBULLET01:
			int color[] = {PURPLE, AQUA, YELLOW};
			int color2[] = {RED, BLUE, YELLOW, PURPLE, AQUA};
			if(shot.ticksExisted >= shot.getShotEndTime() - 1)
			{
				Vec3 angle = THShotLib.angle_LimitRandom(shot.angle, 180F);
				angle = THShotLib.getVectorMultiply(angle, -1.0D);
				Vec3 angle2 = THShotLib.angle_LimitRandom(shot.angle, 30F);
				angle2 = THShotLib.getVectorMultiply(angle2, 0.1D);
				for(int i = 0; i < 10; i++)
				{
					THShotLib.createRandomRingShot(shot.user, shot.user, shot.pos_Shot(), angle, 0F, 9999, 0.6D, 0.2D, -0.05D, angle2, shot(THShotLib.FORM_LIGHT, color2[rand.nextInt(5)], THShotLib.SIZE[THShotLib.FORM_LIGHT], 3F, 3, 60, 0), 1, 0.1D, 50F);
				}
				for(int i = 0; i < 4; i++)
				{
					THShotLib.createLaserA(shot.user, shot.pos_Shot(), THShotLib.angle_LimitRandom(shot.angle, 30F), 0.6D, 0.1D, -0.1D, LaserData.laser(color[rand.nextInt(3)], 0.2F, 6.0F, 6.0F));
				}
				if(!world.isRemote)
				{
					shot.setDead();
				}
				THShotLib.playShotSound(shot.user);
			}
			break;
		default:
			
		}
		
	}

	@Override
	public boolean specialShot_hitBlock(World world, int id, EntityTHShot shot,
			MovingObjectPosition movingObjectPosition) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean specialShot_hitEntity(World world, int id,
			EntityTHShot shot, Entity entity_Hit) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
}