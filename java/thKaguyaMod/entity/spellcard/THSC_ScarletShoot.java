package thKaguyaMod.entity.spellcard;

import static thKaguyaMod.DanmakuConstants.*;
import net.minecraft.util.Vec3;
import thKaguyaMod.THShotLib;

public class THSC_ScarletShoot extends THSpellCard
{
	//紅符「スカーレットシュート」
	
	public THSC_ScarletShoot()
	{
		this.setNeedLevel(4);
		this.setRemoveTime(30);
		this.setEndTime(120);
		this.setOriginalUserName(REMILIA);
	}
	
	@Override
	public void spellcard_main()
	{
		if(time % 25 == 20 && time < 60)
		{
			Vec3 lookAt = THShotLib.getVecFromAngle(user.rotationYaw, user.rotationPitch, 1.0D);
			if(!target.isDead)
			{
				lookAt = THShotLib.angle_ToPos(this.pos_User(), this.pos_Target());
			}
			Vec3 rotate = THShotLib.getOverVector(lookAt);
			float angle = -90F ;
			float angleSpan = 180F / 4F;
			
	    	for(int i = 0; i < 5; i++)
	    	{
	    		scarletShot(THShotLib.getVectorFromRotation(rotate, lookAt, angle), 1.0D, false);
	    		angle += angleSpan;
	    	}
	    	THShotLib.playShotSound(user);
		}
		else if(time == 80)
		{
			Vec3 lookAt = THShotLib.getVecFromAngle(user.rotationYaw, user.rotationPitch, 1.0D);
			if(!target.isDead)
			{
				lookAt = THShotLib.angle_ToPos(this.pos_User(), this.pos_Target());
			}
			Vec3 rotate = THShotLib.getOverVector(lookAt);
			float angle = -3F ;
			float angleSpan = 6F / 2F;
			
	    	for(int i = 0; i < 3; i++)
	    	{
	    		scarletShot(THShotLib.getVectorFromRotation(rotate, lookAt, angle), 1.0D, false);
	    		angle += angleSpan;
	    	}
	    	THShotLib.playShotSound(user);
		}
	}
	
	//スカーレットシュートのまとまった弾（カタディオプトリックの弾に変更も可）
	public void scarletShot(Vec3 angle, double speed, boolean isCatadi)
	{
		int special = 0;
		int color = THShotLib.RED;
		double speed2;
		
		if(isCatadi)
		{
			special = THShotLib.BOUND04;
			color = THShotLib.BLUE;
		}
		THShotLib.createShot(user, user, pos_User(), angle, 0F, speed, speed, 0.0D, gravity_Zero(), shot(FORM_BIG, color, THShotLib.SIZE[FORM_BIG], 6F, 0, 120, special));
		for(int i = 0; i < 11; i++)
		{
			speed2 = (0.5D + rand.nextDouble() * 0.3D) * speed;
			//thShotLib.createShot(user, user, user.posX + (rand.nextDouble() * 2.0D - 1.0D) * 0.2D, thShotLib.getPosYFromEye(user, -0.2D) + (rand.nextDouble() * 2.0D - 1.0D) * 0.2D, user.posZ + (rand.nextDouble() - 0.5D) * 0.2D, vectorX, vectorY, vectorZ, 0F, 0.0D, 1.0D, 0.0D, speed2, speed2, 0.0D, 0.0D, 0.0D, 0.0D, 4.0F, thShotLib.MEDIUM[color], thShotLib.SIZE[thShotLib.MEDIUM[0] / 8], 120, 0, special);
			THShotLib.createRandomRingShot(user, pos_User(), angle, speed2, speed2, 0.0D, shot(FORM_MEDIUM, color, THShotLib.SIZE[FORM_MEDIUM], 4.0F, 0, 120, special), 1, 0.0D, 30F);
		}
		for(int i = 0; i < 22; i++)
		{
			speed2 = (0.2D + rand.nextDouble() * 0.3D) * speed;
			//thShotLib.createShot(user, user, user.posX + (rand.nextDouble() * 2.0D - 1.0D) * 0.3D, thShotLib.getPosYFromEye(user, -0.2D) + (rand.nextDouble() * 2.0D - 1.0D) * 0.3D, user.posZ + (rand.nextDouble() - 0.5D) * 0.3D, vectorX, vectorY, vectorZ, 0F, 0.0D, 1.0D, 0.0D, speed2, speed2, 0.0D, 0.0D, 0.0D, 0.0D, 2.0F, thShotLib.SMALL[color], thShotLib.SIZE[thShotLib.SMALL[0] / 8], 120, 0, special);
			THShotLib.createRandomRingShot(user, pos_User(), angle, speed2, speed2, 0.0D, shot(FORM_SMALL, color, THShotLib.SIZE[FORM_SMALL], 2.0F, 0, 120, special), 1, 0.0D, 60F);
		}
		
	}
}
