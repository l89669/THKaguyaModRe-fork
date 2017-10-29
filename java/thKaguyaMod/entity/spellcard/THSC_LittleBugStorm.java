package thKaguyaMod.entity.spellcard;

import static thKaguyaMod.DanmakuConstants.*;
import net.minecraft.entity.Entity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.shot.EntityTHShot;
import thKaguyaMod.entity.shot.ISpecialShot;
import thKaguyaMod.registry.SpecialShotRegistry;

/** 蠢符「リトルバグストーム」 */
public class THSC_LittleBugStorm extends THSpellCard implements ISpecialShot
{
	
	public static final int SP_LITTLEBUGSTORM01 = 3400;
	public static final int SP_LITTLEBUGSTORM02 = 3401;
	public static final int SP_LITTLEBUGSTORM03 = 3402;
	public static final int SP_LITTLEBUGSTORM11 = 3410;
	public static final int SP_LITTLEBUGSTORM12 = 3411;
	public static final int SP_LITTLEBUGSTORM13 = 3412;
	
	public static final int SP_ATTACK01_01 = 3450;
	public static final int SP_ATTACK01_02 = 3451;
	
	private float yaw;
	private float pitch;
	
	public THSC_LittleBugStorm()
	{
		this.setNeedLevel(2);
		this.setRemoveTime(25);
		this.setEndTime(150);
		this.setOriginalUserName(WRIGGLE);
		
		SpecialShotRegistry.registerSpecialShot(THSC_LittleBugStorm.class, SP_LITTLEBUGSTORM01);
		SpecialShotRegistry.registerSpecialShot(THSC_LittleBugStorm.class, SP_LITTLEBUGSTORM02);
		SpecialShotRegistry.registerSpecialShot(THSC_LittleBugStorm.class, SP_LITTLEBUGSTORM03);
		SpecialShotRegistry.registerSpecialShot(THSC_LittleBugStorm.class, SP_LITTLEBUGSTORM11);
		SpecialShotRegistry.registerSpecialShot(THSC_LittleBugStorm.class, SP_LITTLEBUGSTORM12);
		SpecialShotRegistry.registerSpecialShot(THSC_LittleBugStorm.class, SP_LITTLEBUGSTORM13);
		
		SpecialShotRegistry.registerSpecialShot(THSC_LittleBugStorm.class, SP_ATTACK01_01);
		SpecialShotRegistry.registerSpecialShot(THSC_LittleBugStorm.class, SP_ATTACK01_02);
		

	}
	
	@Override
	public void spellcard_main()
	{
		int way = 1 + level * 2;
		
		if(time < 15)
		{
			yaw += 2.7F;
			pitch += 2.7F;
			//yaw += MathHelper.cos((float)(time + 75) / 50F) * 17F;
			//pitch += MathHelper.sin(MathHelper.sin((time + 75) / 50F) * 3.14F) * 17F;
			Vec3 angle = THShotLib.angle(yaw, pitch);
			//THShotLib.createCircleShot(user, user, pos_User(), angle, 1.0F, 0.0F, -0.05F - time * 0.001, gravity_Zero(), ShotData.shot(FORM_TINY, WHITE, THShotLib.SIZE[FORM_TINY], 2.0F, 0, 50, SP_LITTLEBUGSTORM01), way, 0.3D, time * way);
			THShotLib.createSphereShot(user, user, pos_User(), angle, 1.0F, 1.0F, 0.0F, -0.05F - time * 0.001, gravity_Zero(), shot(FORM_TINY, WHITE, THShotLib.SIZE[FORM_TINY], 2.0F, 0, 50, SP_LITTLEBUGSTORM01), way, 0.3D, 1.4F  );
			THShotLib.playShotSound(user);
		}
		else if(time < 30)
		{
			yaw += 2.7F;
			pitch += 2.7F;
			//yaw += MathHelper.cos((float)(time + 75) / 50F) * 17F;
			//pitch += MathHelper.sin(MathHelper.sin((time + 75) / 50F) * 3.14F) * 17F;
			Vec3 angle = THShotLib.angle(yaw, pitch);
			//THShotLib.createCircleShot(user, user, pos_User(), angle, 1.0F, 0.0F, -0.05F - time * 0.001, gravity_Zero(), ShotData.shot(FORM_TINY, WHITE, THShotLib.SIZE[FORM_TINY], 2.0F, 0, 50, SP_LITTLEBUGSTORM11), way, 0.3D, time * way);
			THShotLib.createSphereShot(user, user, pos_User(), angle, 1.0F, 1.0F, 0.0F, -0.05F - time * 0.001, gravity_Zero(), shot(FORM_TINY, WHITE, THShotLib.SIZE[FORM_TINY], 2.0F, 0, 50, SP_LITTLEBUGSTORM11), way, 0.3D, 1.4F  );
			THShotLib.playShotSound(user);
		}
		else if(time < 45)
		{
			yaw += 2.7F;
			pitch += 2.7F;
			//yaw += MathHelper.cos((float)(time + 75) / 50F) * 17F;
			//pitch += MathHelper.sin(MathHelper.sin((time + 75) / 50F) * 3.14F) * 17F;
			Vec3 angle = THShotLib.angle(yaw, pitch);
			//THShotLib.createCircleShot(user, user, pos_User(), angle, 1.0F, 0.0F, -0.05F - time * 0.001, gravity_Zero(), ShotData.shot(FORM_TINY, WHITE, THShotLib.SIZE[FORM_TINY], 2.0F, 0, 50, SP_LITTLEBUGSTORM01), way, 0.3D, -time * way);
			THShotLib.createSphereShot(user, user, pos_User(), angle, 1.0F, 1.0F, 0.0F, -0.05F - time * 0.001, gravity_Zero(), shot(FORM_TINY, WHITE, THShotLib.SIZE[FORM_TINY], 2.0F, 0, 50, SP_LITTLEBUGSTORM01), way, 0.3D, 1.4F  );
			THShotLib.playShotSound(user);
		}
		else if(time < 60)
		{
			yaw += 2.7F;
			pitch += 2.7F;
			//yaw += MathHelper.cos((float)(time + 75) / 50F) * 17F;
			//pitch += MathHelper.sin(MathHelper.sin((time + 75) / 50F) * 3.14F) * 17F;
			Vec3 angle = THShotLib.angle(yaw, pitch);
			//THShotLib.createCircleShot(user, user, pos_User(), angle, 1.0F, 0.0F, -0.05F - time * 0.001, gravity_Zero(), ShotData.shot(FORM_TINY, WHITE, THShotLib.SIZE[FORM_TINY], 2.0F, 0, 50, SP_LITTLEBUGSTORM11), way, 0.3D, -time * way);
			THShotLib.createSphereShot(user, user, pos_User(), angle, 1.0F, 1.0F, 0.0F, -0.05F - time * 0.001, gravity_Zero(), shot(FORM_TINY, WHITE, THShotLib.SIZE[FORM_TINY], 2.0F, 0, 50, SP_LITTLEBUGSTORM11), way, 0.3D, 1.4F );
			THShotLib.playShotSound(user);
		}
	}

	@Override
	public void specialShot_move(World world, int id, EntityTHShot shot) {
		switch(id)
		{
		case SP_LITTLEBUGSTORM01:
			if(shot.isShotEndTime())
			{
				shot.setShotColor(WHITE);
				shot.setShotSize(THShotLib.SIZE[FORM_SMALL]);
				shot.shotSpecial = SP_LITTLEBUGSTORM02;
				shot.setShotEndTime(20);
				shot.angle = THShotLib.getVectorFromRotation(shot.rotate, shot.angle, 90);
				shot.shotSpeed = 3.0;
				shot.shotLimitSpeed = 0.0;
				shot.shotAcceleration = -1.0;
				shot.reCreate();
				shot.delete();
				
				
			}
			break;
		case SP_LITTLEBUGSTORM02:
			if(shot.isShotEndTime())
			{
				shot.setShotForm(FORM_LIGHT);
				shot.setShotColor(YELLOW);
				shot.shotDamage = 4.0F;
				shot.setShotSize(THShotLib.SIZE[FORM_TINY]);
				shot.shotSpecial = SP_LITTLEBUGSTORM03;
				shot.reCreate();
				shot.delete();
			}
			break;
		case SP_LITTLEBUGSTORM03:
			if(shot.ticksExisted % 3 == 0)
			{
				if(shot.getShotColor() == YELLOW)
				{
					shot.setShotColor(WHITE);
					shot.setShotSize(THShotLib.SIZE[FORM_TINY]);
				}
				else
				{
					shot.setShotColor(YELLOW);
					shot.setShotSize(THShotLib.SIZE[FORM_TINY] * 2F);
				}
			}
			if(shot.isShotEndTime())
			{
				shot.setShotForm(FORM_RICE);
				shot.setShotColor(YELLOW);
				shot.shotDamage = 6.0F;
				shot.setShotSize(THShotLib.SIZE[FORM_RICE] * 2F);
				shot.specialEnd();
				shot.angle = THShotLib.getVectorFromRotation(shot.rotate, shot.angle, -45);
				shot.angle.yCoord = Math.sin(THShotLib.getYawFromVector(shot.angle.xCoord, shot.angle.zCoord) * 23F / 180F * 3.141593F) * 30F * 0.01;
				shot.updateAngle();
				shot.shotSpeed = 0.01;
				shot.shotLimitSpeed = 0.5;
				shot.shotAcceleration = 0.02;
				shot.setShotEndTime(80);
				shot.reCreate();
				shot.delete();
			}
			break;
		case SP_LITTLEBUGSTORM11:
			if(shot.isShotEndTime())
			{
				shot.setShotColor(WHITE);
				shot.setShotSize(THShotLib.SIZE[FORM_SMALL]);
				shot.shotSpecial = SP_LITTLEBUGSTORM12;
				shot.setShotEndTime(20);
				shot.angle = THShotLib.getVectorFromRotation(shot.rotate, shot.angle, 90);
				shot.shotSpeed = 3.0;
				shot.shotLimitSpeed = 0.0;
				shot.shotAcceleration = -1.0;
				shot.reCreate();
				shot.delete();
			}
			break;
		case SP_LITTLEBUGSTORM12:
			if(shot.isShotEndTime())
			{
				shot.setShotForm(FORM_LIGHT);
				shot.setShotColor(YELLOW);
				shot.shotDamage = 4.0F;
				shot.setShotSize(THShotLib.SIZE[FORM_TINY]);
				shot.shotSpecial = SP_LITTLEBUGSTORM13;
				shot.reCreate();
				shot.delete();
			}
			break;
		case SP_LITTLEBUGSTORM13:
			if(shot.ticksExisted % 3 == 0)
			{
				if(shot.getShotColor() == YELLOW)
				{
					shot.setShotColor(WHITE);
					shot.setShotSize(THShotLib.SIZE[FORM_TINY]);
				}
				else
				{
					shot.setShotColor(YELLOW);
					shot.setShotSize(THShotLib.SIZE[FORM_TINY] * 2F);
				}
			}
			if(shot.isShotEndTime())
			{
				shot.setShotForm(FORM_RICE);
				shot.setShotColor(GREEN);
				shot.setShotSize(THShotLib.SIZE[FORM_RICE] * 2F);
				shot.shotDamage = 6.0F;
				shot.specialEnd();
				shot.angle = THShotLib.getVectorFromRotation(shot.rotate, shot.angle, 135);
				shot.angle.yCoord = Math.sin(THShotLib.getYawFromVector(shot.angle.xCoord, shot.angle.zCoord) * 23F / 180F * 3.141593F) * 30F * 0.01;
				shot.updateAngle();
				shot.shotSpeed = 0.01;
				shot.shotLimitSpeed = 0.5;
				shot.shotAcceleration = 0.02;
				shot.setShotEndTime(80);
				shot.reCreate();
				shot.delete();
			}
			break;
		case SP_ATTACK01_01:
			if(shot.isShotEndTime())
			{
				shot.specialEnd();
				shot.setShotEndTime(80);
				shot.angle = THShotLib.getVectorFromRotation(shot.rotate, shot.angle, 70);
				shot.shotLimitSpeed = 0.5;
				shot.shotAcceleration = 0.03;
				shot.reCreate();
				shot.delete();
			}
			break;
		case SP_ATTACK01_02:
			if(shot.isShotEndTime())
			{
				shot.specialEnd();
				shot.setShotEndTime(80);
				shot.angle = THShotLib.getVectorFromRotation(shot.rotate, shot.angle, -70);
				shot.shotLimitSpeed = 0.5;
				shot.shotAcceleration = 0.03;
				shot.reCreate();
				shot.delete();
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
