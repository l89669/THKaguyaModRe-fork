package thKaguyaMod.entity.spellcard;

import static thKaguyaMod.DanmakuConstants.*;
import static thKaguyaMod.THShotLib.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.ShotData;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.shot.EntityTHShot;
import thKaguyaMod.entity.shot.ISpecialShot;
import thKaguyaMod.registry.SpecialShotRegistry;

public class THSC_MiracleFruit extends THSpellCard implements ISpecialShot
{
	//奇跡「ミラクルフルーツ」
	
	public static final int SPECIAL_MIRACLE_FRUIT01 = 1400;
	
	public THSC_MiracleFruit()
	{
		this.setNeedLevel(1);
		this.setRemoveTime(30);
		this.setEndTime(60);
		this.setOriginalUserName(SANAE);
		
		SpecialShotRegistry.registerSpecialShot(THSC_MiracleFruit.class, SPECIAL_MIRACLE_FRUIT01);
	}
	
	@Override
	public void spellcard_main()
	{
		if(time == 1)
		{
			user.clearActivePotions();
		}
		if(time < 5)
		{
			return;
		}
		
		if(time % 30 == 6)
		{
			Vec3 angle = THShotLib.getRotationVectorFromAngle(user.rotationYaw, user.rotationPitch, 0F, 1.0D);
			THShotLib.playShotSound(user);
			THShotLib.createCircleShot(user, user, pos_User(), angle, 2.0D, 0.1D, -0.2D, gravity_Zero(), shot(FORM_OVAL, RED, THShotLib.SIZE[FORM_OVAL], 7.0F, 3, 20, SPECIAL_MIRACLE_FRUIT01), 8, 0.2D, rand.nextFloat() * 360F);
		}
	}

	@Override
	public void specialShot_move(World world, int id, EntityTHShot shot) {
		switch(id)
		{
			case SPECIAL_MIRACLE_FRUIT01:
				if(shot.ticksExisted == shot.getDeadTime())
				{
					float angle2 = rand.nextFloat() * 360F;
					Vec3 look;
					for(int j = 0; j < 8; j++)
					{
						look = THShotLib.getVectorFromRotation(shot.rotate, shot.angle, angle2);
						for(int i = 0; i < 6; i++)
						{
							THShotLib.createShot(shot.user, shot, pos_Entity(shot), look, 0.0F, shot.rotate, 0.0F, 9999, 0.5D, 0.2D, 0.8D, gravity_Zero(), ShotData.shot(FORM_SMALL, RED, 0.3F, 6.0F, 3 + i * 2, 90));
		
						}
						angle2 += 45F;
					}
					
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
		switch(id)
		{
			case SPECIAL_MIRACLE_FRUIT01:
				if(entity_Hit instanceof EntityLivingBase)
				{
					EntityLivingBase living = (EntityLivingBase)entity_Hit;
					living.clearActivePotions();
				}
				return false;
			default:
				return false;
		}
	}
}
