package thKaguyaMod.entity.spellcard;

import static thKaguyaMod.DanmakuConstants.*;
import static thKaguyaMod.THShotLib.*;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.shot.EntityHomingAmulet;
import thKaguyaMod.entity.shot.EntityMusouFuuin;
import thKaguyaMod.entity.shot.EntityTHLaser;
import thKaguyaMod.entity.shot.EntityTHShot;
import thKaguyaMod.entity.shot.ISpecialShot;
import thKaguyaMod.registry.SpecialShotRegistry;

public class THSC_PerfectFreeze extends THSpellCard implements ISpecialShot
{
	//凍符「パーフェクトフリーズ」
	
	public static final int SPECIAL_FREEZE = 900;
	
	public THSC_PerfectFreeze()
	{
		this.setNeedLevel(1);
		this.setRemoveTime(20);
		this.setEndTime(110);
		this.setOriginalUserName(CIRNO);
		
		//特殊弾の登録はコンストラクタ内で行う
		SpecialShotRegistry.registerSpecialShot(THSC_PerfectFreeze.class, SPECIAL_FREEZE);
	}
	
	@Override
	public void spellcard_main()
	{
		if(time < 5)
		{
			return;
		}
		
		//時間が58になるまでランダムな方向に弾を出し続ける
		if(time < 58)
		{
			int num = 4 + level;
			if(num > 10)num = 10;
			Vec3 angle;
			for(int i = 0; i < num; i++)
			{
				angle = angle_Random();//全方向からランダムな方向を取得
				THShotLib.createShot(user, user, pos_User(angle, 1.0D), angle, 0F, level * 0.1D, 0.5D, 0.03D, gravity_Zero(), shot(FORM_CIRCLE, RAINBOW, SIZE[FORM_CIRCLE], 4.0F, 5, 120));
			}
			THShotLib.playShotSound(user);
		}

		//時間が64なら弾幕を凍らせる
		if(time == 64)
		{
			List list = world.getEntitiesWithinAABBExcludingEntity(user, user.boundingBox.addCoord(0.0D, 0.0D, 0.0D).expand(20.0D, 20.0D, 20.0D));
    		for(int k = 0; k < list.size(); k++)
    		{
    			Entity entity = (Entity)list.get(k);
    			if(entity instanceof EntityTHShot && entity instanceof EntityTHLaser == false)
    			{
    				if(entity instanceof EntityHomingAmulet == false && entity instanceof EntityMusouFuuin == false)
    				{
    					EntityTHShot shot = (EntityTHShot)entity;
    					THShotLib.createShot(	user, user, THShotLib.pos_Entity(shot), getVecFromAngle(rand.nextFloat() * 360F, rand.nextFloat() * 180F - 90F), 0F, 
    						0.0001D, 0.0001D, 0.0D, gravity_Zero(), shot(shot.getShotForm(), WHITE, shot.getShotSize(), shot.shotDamage, 0, shot.getShotEndTime() - shot.ticksExisted + 50, SPECIAL_FREEZE));
    					shot.setDead();
    				}
    			}
    		}
		}
	}
	
	@Override
	public void specialShot_move(World world, int id, EntityTHShot shot)
	{
		switch(id)
		{
			case SPECIAL_FREEZE:
			if(shot.ticksExisted >= 20)
			{
				shot.shotSpeed = 0.01D;
				shot.shotLimitSpeed = 0.3D;
				shot.shotAcceleration = 0.03D;
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
