package thKaguyaMod.entity.spellcard;

import static thKaguyaMod.DanmakuConstants.*;
import static thKaguyaMod.THShotLib.*;
import net.minecraft.entity.Entity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thKaguyaMod.ShotData;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.shot.EntityTHShot;
import thKaguyaMod.entity.shot.ISpecialShot;
import thKaguyaMod.registry.SpecialShotRegistry;

/** 蓬莱「凱風快晴  -フジヤマヴォルケイノ-」 */
public class THSC_FujiyamaVolcano extends THSpellCard implements ISpecialShot
{

	public static final int SPECIAL_FUJUYAMA01 = 3300;
	public static final int SPECIAL_FUJUYAMA02 = 3301;
	public static final int SPECIAL_FUJUYAMA03 = 3302;
	public static final int SPECIAL_FUJUYAMA04 = 3303;
	
	public THSC_FujiyamaVolcano()
	{
		this.setNeedLevel(5);
		this.setRemoveTime(20);
		this.setEndTime(120);
		this.setOriginalUserName(MOKOU);
		
		SpecialShotRegistry.registerSpecialShot(THSC_FujiyamaVolcano.class, SPECIAL_FUJUYAMA01);
		SpecialShotRegistry.registerSpecialShot(THSC_FujiyamaVolcano.class, SPECIAL_FUJUYAMA02);
		SpecialShotRegistry.registerSpecialShot(THSC_FujiyamaVolcano.class, SPECIAL_FUJUYAMA03);
		SpecialShotRegistry.registerSpecialShot(THSC_FujiyamaVolcano.class, SPECIAL_FUJUYAMA04);
	}
	
	@Override
	public void spellcard_main()
	{
		if(time < 45 && time % 15 == 0)
		{
			ShotData shot = ShotData.shot(FORM_FAMILIAR, BLUE, 0, 40, SPECIAL_FUJUYAMA01);
			THShotLib.createShot(user, user, pos_User(), this.angle_UserToTarget(), 0F, 2.4D, 0.0D, -(2.4D / user.getDistanceToEntity(target)), gravity_Zero(), shot);
			THShotLib.playShotSound(user);
		}
		if(time >= 60 && time <= 90 && time % 7 == 0)
		{
			ShotData shot = ShotData.shot(FORM_FAMILIAR, BLUE, 0, 40, SPECIAL_FUJUYAMA01);
			THShotLib.createShot(user, user, pos_User(), this.angle_UserToTarget(), 0F, 2.4D, 0.0D, -(2.4D / user.getDistanceToEntity(target)), gravity_Zero(), shot);
			THShotLib.playShotSound(user);
		}
	}

	@Override
	public void specialShot_move(World world, int id, EntityTHShot shot) {
		switch(id)
		{
		case SPECIAL_FUJUYAMA01:
			if(shot.getSpeed() <= 0.0D)
			{
				/*for(int i = 0; i < 44; i++)
				{
					THShotLib.createShot(shot.user, shot, shot.pos_Shot(), angle_Random(), 0.0F, 0.5D, 0.5D, 1.0D, gravity_Zero(), ShotData.shot(FORM_BIG, RED, SIZE[FORM_BIG], 10.0F, 0, 9, SPECIAL_FUJUYAMA02));
				}*/
				THShotLib.createSphereShot(shot.user, shot, shot.pos_Shot(), angle_Random(), 0F, 0.5D, 0.5D, 0.0D, gravity_Zero(), ShotData.shot(FORM_BIG, RED, SIZE[FORM_BIG], 10.0F, 0, 9, SPECIAL_FUJUYAMA02), 44, 0.0D, rand.nextFloat() * 360F);
				if(!world.isRemote)
				{
					world.createExplosion(shot, shot.posX, shot.posY, shot.posZ, 1.0F, false);
					shot.setDead();
				}
			}
			break;
		case SPECIAL_FUJUYAMA02:
			if(shot.isShotEndTime())
			{
				for(int i = 0; i < 2; i++)
				{
					THShotLib.createShot(shot.user, shot, shot.pos_Shot(), angle_LimitRandom(shot.angle, 50F), 0.0F, 0.5D, 0.5D, 1.0D, gravity_Zero(), ShotData.shot(FORM_MEDIUM, RED, SIZE[FORM_MEDIUM], 7.0F, 0, 5, SPECIAL_FUJUYAMA03));
				}
				if(!world.isRemote)
				{
					shot.setDead();
				}
			}
			break;
		case SPECIAL_FUJUYAMA03:
			if(shot.isShotEndTime())
			{
				for(int i = 0; i < 2; i++)
				{
					THShotLib.createShot(shot.user, shot, shot.pos_Shot(),angle_LimitRandom(shot.angle, 50F), 0.0F, 0.5D, 0.5D, 1.0D, gravity_Zero(), ShotData.shot(FORM_SMALL, RED, SIZE[FORM_SMALL], 5.0F, 0, 4, SPECIAL_FUJUYAMA04));
				}
				if(!world.isRemote)
				{
					shot.setDead();
				}
			}
			break;
		case SPECIAL_FUJUYAMA04:
			if(shot.isShotEndTime())
			{
				for(int i = 0; i < 2; i++)
				{
					THShotLib.createShot(shot.user, shot, shot.pos_Shot(), angle_LimitRandom(shot.angle, 50F), 0.0F, 0.5D, 0.5D, 1.0D, gravity_Zero(), ShotData.shot(FORM_TINY, RED, SIZE[FORM_TINY], 3.0F, 0, 2, 0));
				}
				if(!world.isRemote)
				{
					shot.setDead();
				}
			}
			break;
		default:
			break;
		}
	}
	
	/**
	 * ある初速度で、離れた地点に、特定の時間に丁度停止する加速度を返す。
	 * @param time
	 * @param distance
	 * @param firstSpeed
	 * @return
	 */
	public double getJustTimeAcceleration(int time, double distance, double firstSpeed)
	{
		return (distance - firstSpeed) / (double)time;
	}

	@Override
	public boolean specialShot_hitBlock(World world, int id, EntityTHShot shot,
			MovingObjectPosition movingObjectPosition) {
		switch(id)
		{
		case SPECIAL_FUJUYAMA01:
			//if(shot.getSpeed() <= 0.0D)
			{
				for(int i = 0; i < 40; i++)
				{
					THShotLib.createShot(shot.user, shot, shot.pos_Shot(), angle_Random(), 0.0F, 0.5D, 0.5D, 1.0D, gravity_Zero(), ShotData.shot(FORM_BIG, RED, SIZE[FORM_BIG], 10.0F, 0, 6, SPECIAL_FUJUYAMA02));
				}
				if(!world.isRemote)
				{
					shot.setDead();
				}
			}
			return false;
		default:
			break;
		}
		return false;
	}

	@Override
	public boolean specialShot_hitEntity(World world, int id,
			EntityTHShot shot, Entity entity_Hit) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

}
