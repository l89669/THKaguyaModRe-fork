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

/** 魔符「スターダストレヴァリエ」 */
public class THSC_StardustReverie extends THSpellCard implements ISpecialShot
{
	
	public static final int SPECIAL_STARDUST01 = 1200;
	
	public THSC_StardustReverie()
	{
		this.setNeedLevel(4);
		this.setRemoveTime(60);
		this.setEndTime(120);
		this.setOriginalUserName(MARISA);
		
		SpecialShotRegistry.registerSpecialShot(THSC_StardustReverie.class, SPECIAL_STARDUST01);
	}
	
	@Override
	public void spellcard_main()
	{
		if(time < 5)return;
		
		if(time == 5)
		{
			int way[] = { 4, 4, 7, 9, 10 };
			
			THShotLib.createCircleShot(user, user, pos_User(),
					user.getLookVec(), 0.3D, 0.3D, 0.0D, gravity_Zero(),
					shot(FORM_STAR, RAINBOW, 1.2F, 7.0F, 2, 120, SPECIAL_STARDUST01), way[level], 0.2D, 0F);
		}
	}

	@Override
	public void specialShot_move(World world, int id, EntityTHShot shot) {
		switch(id)
		{
			case SPECIAL_STARDUST01:
				ShotData shotData = ShotData.shot(shot.getShotForm(), shot.getShotColor(), 0.3F, 5.0F, 10, 50);
				shot.setShotRotationYaw(2.0F);
				Vec3 vec = THShotLib.getVectorFromRotation(shot.rotate, shot.angle, shot.ticksExisted * 13F);
				THShotLib.createShot(shot.user, shot, pos_Entity(shot), angle(vec.xCoord, vec.yCoord, vec.zCoord), shot.getAngleZ(), shot.rotate, 0F, 9999,  0.1D, 0.3D, 0.03D, THShotLib.gravity_Zero(), shotData);
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
		switch(id)
		{
			case SPECIAL_STARDUST01:
				return true;
			default:
				return false;
		}
		
	}
}
