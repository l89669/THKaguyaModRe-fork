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

public class THSC_RedMagic extends THSpellCard implements ISpecialShot
{
	//「レッドマジック」
	
	public static final int SPECIAL_REDMAGIC01 = 2300;
	public static final int SPECIAL_REDMAGIC02 = 2301;

	public THSC_RedMagic()
	{
		this.setNeedLevel(4);
		this.setRemoveTime(20);
		this.setEndTime(190);
		this.setOriginalUserName(REMILIA);
		
		SpecialShotRegistry.registerSpecialShot(THSC_RedMagic.class, SPECIAL_REDMAGIC01);
		SpecialShotRegistry.registerSpecialShot(THSC_RedMagic.class, SPECIAL_REDMAGIC02);
	}
	
	@Override
	public void spellcard_main()
	{
		if(time == 5)
		{
			float angle2 = 0F;
			ShotData shotData = shot(FORM_BIG, PURPLE, THShotLib.SIZE[FORM_BIG], 14F, 3, 30, SPECIAL_REDMAGIC01);
			for(int i = 0; i < 3; i++)
			{
				Vec3 angle = THShotLib.getVecFromAngle(user.rotationYaw, user.rotationPitch + angle2, 1.0D);
				THShotLib.createCircleShot(user, user, pos_User(), angle, -5F, 20, 0.9D, 0.2D, 0.0D, gravity_Zero(), shotData, 6, 0.7D);

			
				angle = THShotLib.getVecFromAngle(user.rotationYaw + 90F, user.rotationPitch + 90F + angle2, 1.0D);
				THShotLib.createCircleShot(user, user, pos_User(), angle, -5F, 20, 0.9D, 0.2D, 0.0D, gravity_Zero(), shotData, 6, 0.7D);
				angle2 += 60F;
			}
		}
		else if(time == 75)
		{
			float angle2 = 0F;
			ShotData shotData = shot(FORM_BIG, PURPLE, THShotLib.SIZE[FORM_BIG], 14F, 3, 30, SPECIAL_REDMAGIC01);
			for(int i = 0; i < 3; i++)
			{
				Vec3 angle = THShotLib.getVecFromAngle(user.rotationYaw, user.rotationPitch + angle2, 1.0D);
				THShotLib.createCircleShot(user, user, pos_User(), angle,  5F, 20, 0.9D, 0.2D, 0.0D, gravity_Zero(), shotData, 6, 0.7D);
				
				angle = THShotLib.getVecFromAngle(user.rotationYaw + 90F, user.rotationPitch + 90F + angle2, 1.0D);
				THShotLib.createCircleShot(user, user, pos_User(), angle,  5F, 20, 0.9D, 0.2D, 0.0D, gravity_Zero(), shotData, 6, 0.7D);
				angle2 += 60F;
			}
		}
	}

	@Override
	public void specialShot_move(World world, int id, EntityTHShot shot) {
		switch(id)
		{
			case SPECIAL_REDMAGIC01:
				if(shot.ticksExisted % 3 == 0)
				{
					Vec3 rotate2 = THShotLib.getVectorFromRotation(shot.rotate, shot.angle, 90F + shot.ticksExisted * 3F);
		
					ShotData shotData = ShotData.shot(FORM_LIGHT, RED, THShotLib.SIZE[FORM_LIGHT], 9.0F, 20, 30 - shot.ticksExisted + 120, SPECIAL_REDMAGIC02);
					THShotLib.createShot(shot.user, shot, pos_Entity(shot) ,rotate2, shot.getAngleZ(), shot.rotate, 0F, 9999, 0.001D, 0.0D, 0.02D, gravity_Zero(), shotData);
				}
				break;
			case SPECIAL_REDMAGIC02:
				if(shot.ticksExisted >= 30)
				{
					shot.shotSpeed = 0.1D;
					shot.shotLimitSpeed = 0.3D;
					shot.specialEnd();
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
