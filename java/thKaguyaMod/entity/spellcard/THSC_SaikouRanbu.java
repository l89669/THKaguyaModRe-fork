package thKaguyaMod.entity.spellcard;

import static thKaguyaMod.DanmakuConstants.*;
import net.minecraft.util.Vec3;
import thKaguyaMod.THShotLib;

public class THSC_SaikouRanbu extends THSpellCard
{
	//彩符「彩光乱舞」
	
	public THSC_SaikouRanbu()
	{
		this.setNeedLevel(2);
		this.setRemoveTime(60);
		this.setEndTime(80);
		this.setOriginalUserName(MEIRIN);
	}
	
	@Override
	public void spellcard_main()
	{
		if(time > 60)
		{
			return;
		}
		
    	if(time % 30 < 10 && time % 2 == 1)
    	{
    		Vec3 angle = user.getLookVec();
    		int color[] = {THShotLib.YELLOW, THShotLib.AQUA, THShotLib.BLUE, THShotLib.GREEN};
    		angle = THShotLib.getVecFromAngle(card.rotationYaw, -90);
    		THShotLib.createRingShot(user, user, pos(userPosX, THShotLib.getPosYFromEye(user, 0.3D), userPosZ), angle, 0F, 9999, 0.7D, 0.7D, 0.0D, gravity_Default(), shot(FORM_CRYSTAL, color[time % 4], 0.2F, 2.5F, 0, 60), 15, 0.7, 40F + (time % 30) * 5F, 0F);
    		//THShotLib.createRingShot(user, posX, posY, posZ, look.xCoord, look.yCoord, look.zCoord, 0.4D, THShotLib.CRYSTAL[THShotLib.RED], 32, 0.3D, 45.0F, (float)(time * -2F));
    	}
    	if(time % 30 >= 10 && time % 30 < 20)
    	{
    		int color[] = {THShotLib.RED, THShotLib.ORANGE, THShotLib.PURPLE};
    		for(int i = 1; i <= 14; i++)
    		{
	    		float randYaw = rand.nextFloat() * 360F;
	    		float randPitch = rand.nextFloat() * 360F;
	    		Vec3 move = THShotLib.getVecFromAngle(randYaw, randPitch);
	    		Vec3 rotate = THShotLib.getVecFromAngle(randYaw, randPitch + 90F);
	    		THShotLib.createShot(user, user, pos_User(),
	    				move, 0F, 
	    				rotate, 3.0F, 20,
	    				1.5D, 0.4D, -0.4D, gravity_Zero(),
	    				shot(FORM_CRYSTAL, color[rand.nextInt(3)], 0.2F, 3.5F, 0, 50));
    		}
    	}
    	if(time % 30 >= 20 && time % 30 < 30)
    	{
    		int color[] = {THShotLib.RED, THShotLib.ORANGE, THShotLib.PURPLE};
    		for(int i = 1; i <= 14; i++)
    		{
	    		float randYaw = rand.nextFloat() * 360F;
	    		float randPitch = rand.nextFloat() * 360F;
	    		Vec3 move = THShotLib.getVecFromAngle(randYaw, randPitch);
	    		Vec3 rotate = THShotLib.getVecFromAngle(randYaw, randPitch + 90F);
	    		THShotLib.createShot(user, user, pos_User(),
	    				move, 0F, 
	    				rotate, -3.0F, 20,
	    				1.5D, 0.4D, -0.4D, gravity_Zero(),
	    				shot(FORM_CRYSTAL, color[rand.nextInt(3)], 0.2F, 3.5F, 0, 50));
    		}
    	}
	}
}
