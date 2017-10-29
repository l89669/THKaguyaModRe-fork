package thKaguyaMod.entity.spellcard;

import static thKaguyaMod.DanmakuConstants.*;
import static thKaguyaMod.THShotLib.*;
import net.minecraft.entity.Entity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.ShotData;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.shot.EntityTHShot;
import thKaguyaMod.entity.shot.ISpecialShot;
import thKaguyaMod.registry.SpecialShotRegistry;

public class THSC_StarbowBreak extends THSpellCard implements ISpecialShot
{
	//禁弾「スターボウブレイク」
	
	public static final int SPECIAL_STARBOW_BRAKE01 = 2000;

	public THSC_StarbowBreak()
	{
		this.setNeedLevel(3);
		this.setRemoveTime(40);
		this.setEndTime(150);
		this.setOriginalUserName(FLANDORE);
		
		SpecialShotRegistry.registerSpecialShot(THSC_StarbowBreak.class, SPECIAL_STARBOW_BRAKE01);
	}
	
	@Override
	public void spellcard_main()
	{
		if(time % 60 >= 3 && time % 60 <= 23 && time % 3 == 0 && time < 119)
		{
			//虹色のパターン
			int rainbowPattern[] = {THShotLib.PURPLE, THShotLib.BLUE, THShotLib.AQUA, THShotLib.GREEN, THShotLib.YELLOW, THShotLib.ORANGE, THShotLib.RED};
			
			Vec3 look = user.getLookVec();//使用者が見ている方向のベクトルを取得
			if(!target.isDead)
			{
				look = THShotLib.angle_ToPos(this.pos_User(), this.pos_Target());
			}
			Vec3 side = THShotLib.getVecFromAngle(user.rotationYaw + 90F, 0F, 0.3F);
			int color;
			int num = time % 60;
			float span = 360F / num;
			
			Vec3 gravity = THShotLib.gravity(look.xCoord * 0.13D, look.yCoord * 0.13D, look.zCoord * 0.13D);
			color = rainbowPattern[num / 3 - 1];
			THShotLib.createRingShot(user, user, pos(user.posX + gravity.xCoord * num, THShotLib.getPosYFromEye(user, -0.2D) + gravity.yCoord * num, user.posZ + gravity.zCoord * num), look,
					0.0F, 9999, 0.1D, 4.0D, 0.01D, gravity, shot(FORM_LIGHT, color, 0.3F, 8.0F, 5, 8, SPECIAL_STARBOW_BRAKE01), num * 2, (float)num / 5F, 180F, rand.nextFloat() * 360F);
		}
	}

	@Override
	public void specialShot_move(World world, int id, EntityTHShot shot) {
		switch(id)
		{
			case SPECIAL_STARBOW_BRAKE01:
				if(shot.isEndTime() )
				{
					shot.rotationYaw = -shot.rotationYaw + rand.nextFloat() * 14F - 7F;
					shot.rotationPitch = -shot.rotationPitch + rand.nextFloat() * 14F - 7F;
					Vec3 vec = THShotLib.getVecFromAngle(shot.rotationYaw, shot.rotationPitch, 1.0F);
					double gravityLevel = rand.nextDouble() * 0.1D + 0.02D;
					ShotData shotData = ShotData.shot(shot.getShotForm(), shot.getShotColor(), shot.getShotSize(), shot.shotDamage, 10, 90);
					THShotLib.createShot(shot.user, shot, pos_Entity(shot), angle(shot.rotationYaw, shot.rotationPitch), 0F, -0.3D, 0.7D, rand.nextDouble() * 0.03D, gravity(shot.gravity.xCoord * gravityLevel, shot.gravity.yCoord * gravityLevel, shot.gravity.zCoord * gravityLevel), shotData);
					
				}
				break;
			default:
				break;
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
