package thKaguyaMod.entity.spellcard;

import static thKaguyaMod.DanmakuConstants.*;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.item.EntitySakuyaWatch;
import thKaguyaMod.entity.shot.EntityTHShot;

public class THSC_SatuzinDoll extends THSpellCard
{
	//メイド秘技「殺人ドール」
	
	
	public THSC_SatuzinDoll()
	{
		this.setNeedLevel(4);
		this.setRemoveTime(30);
		this.setEndTime(110);
		this.setOriginalUserName(SAKUYA);
	}
	
	@Override
	public void spellcard_main()
	{
		if(time < 5)
		{
			return;
		}
		
		if(time < 15 /*&& time % 2 == 0*/)
		{
			THShotLib.createSphereShot(user, user, pos_User(), user.getLookVec(), 0F, rotate_Default(), 0F, 9999,
					0.4D, 0.4D, 0.0D, gravity_Zero(), shot(FORM_KNIFE,BLUE, THShotLib.SIZE[FORM_KNIFE], 12.0F, 3, 80), 32, 1.0D, time * 6F);
		}
		if(time > 14 && time < 24 && time % 4 == 0)
		{
			int way = 9;
			float angleXZ = user.rotationYaw;
			float angleY = user.rotationPitch;
			//thShotLib.createCircleShot01(user, angleXZ,      angleY, 0.4D, thShotLib.KNIFE[thShotLib.RED], way);
			//thShotLib.createCircleShot01(user, angleXZ + 5F, angleY, 0.4D, thShotLib.KNIFE[thShotLib.RED], way);
			THShotLib.createCircleShot(user, user, pos_User(), getVecFromAngle(angleXZ, angleY), 
					0.4D, 0.4D, 0.0D, gravity_Zero(), shot(FORM_KNIFE, RED, THShotLib.SIZE[FORM_KNIFE], 12.0F, 3, 80), way, 1.0D, 0F);
			THShotLib.createCircleShot(user, user, pos_User(), getVecFromAngle(angleXZ + 5F, angleY), 
					0.4D, 0.4D, 0.0D, gravity_Zero(), shot(FORM_KNIFE, RED, THShotLib.SIZE[FORM_KNIFE], 12.0F, 3, 80), way, 1.0D, 0F);
		}
		if(time == 36)
		{
			boolean flag = true;
		    List list = world.getEntitiesWithinAABBExcludingEntity(card, card.boundingBox.addCoord(0.0D, 0.0D, 0.0D).expand(20.0D, 20.0D, 20.0D));
    		for(int k = 0; k < list.size(); k++)
    		{
    			Entity entity = (Entity)list.get(k);
    			if(entity instanceof EntitySakuyaWatch)
    			{
    				EntitySakuyaWatch entityPrivateSquare = (EntitySakuyaWatch)entity;
    				if(entityPrivateSquare.userEntity == user)
    				{
						flag = false;
    				}
    			}
    		}
			if(flag)
			{
    			EntitySakuyaWatch entityPrivateSquare = new EntitySakuyaWatch(world, user, 1);
				if(!world.isRemote)
       			{
         			world.spawnEntityInWorld(entityPrivateSquare);//時間停止空間を生み出す
       			}
			}
		}

	}
	
	/**
	 * 時間停止中にのみ実行される
	 */
	@Override
	public void specialProcessInTimeStop()
	{
		super.specialProcessInTimeStop();
		
		if(time > 44 && time < 68 && time % 8 == 3)
		{
			List list = world.getEntitiesWithinAABBExcludingEntity(card, card.boundingBox.addCoord(0.0D, 0.0D, 0.0D).expand(20.0D, 20.0D, 20.0D));
    		for(int k = 0; k < list.size(); k++)
    		{
    			Entity entity = (Entity)list.get(k);
    			if(entity instanceof EntityTHShot)
    			{
    				EntityTHShot entityTHShot = (EntityTHShot)entity;
    				if(entityTHShot.getShotForm() == FORM_KNIFE && (entityTHShot.getShotColor() == BLUE || entityTHShot.getShotColor() == RED ) && entityTHShot.user.equals(this.user) &&  rand.nextInt(100) < 25)
    				{
    					//thShotLib.createShot03(user, entityTHShot.posX, entityTHShot.posY - ((double)user.getEyeHeight() - 0.25D), entityTHShot.posZ, 
    						//	rand.nextFloat() * 360F, rand.nextFloat() * 360F, 0.01D, 0.28D, 0.05D, thShotLib.KNIFE[thShotLib.GREEN]);
    					Vec3 angle;
    					if(target != null)
    					{
    						angle = THShotLib.angle_ToPos(THShotLib.pos_Entity(entityTHShot), pos_Target());
    					}
    					else
    					{
    						angle = THShotLib.angle_Random();
    					}
    					THShotLib.createShot(user, user, pos(entityTHShot.posX, entityTHShot.posY - ((double)user.getEyeHeight() - 0.25D), entityTHShot.posZ), 
    							angle, 0F, 0.2D + rand.nextDouble() * 0.5D, 0.7D, 0.02D, gravity_Zero(),
    							shot(FORM_KNIFE, GREEN, THShotLib.SIZE[FORM_KNIFE], 12.0F, 0, 80));
    					if(!world.isRemote)
    					{
    						entityTHShot.setDead();
    					}
    					
    				}
    			}
    		}
    		
		}
	}
	
	/**
	 * 時間の停止した状態でも処理を続行できるか（使用者自信が時間停止していないと意味は無い）
	 * @return 処理を続けられるならtrue
	 */
	@Override
	public boolean canMoveInTimeStop()
	{
		return true;
	}
}
