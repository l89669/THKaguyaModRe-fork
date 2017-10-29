package thKaguyaMod.entity.spellcard;

import static thKaguyaMod.DanmakuConstants.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.shot.EntityTHShot;
import thKaguyaMod.entity.shot.ISpecialShot;
import thKaguyaMod.registry.SpecialShotRegistry;

public class THSC_YouryokuSpoiler extends THSpellCard implements ISpecialShot
{
	//妖怪退治「妖力スポイラー」
	
	public static final int SPECIAL_SPOILER01 = 1600;
	public static final int SPECIAL_SPOILER02 = 1601;
	
	public THSC_YouryokuSpoiler()
	{
		this.setNeedLevel(2);
		this.setRemoveTime(10);
		this.setEndTime(80);
		this.setOriginalUserName(SANAE);
		
		SpecialShotRegistry.registerSpecialShot(THSC_YouryokuSpoiler.class, SPECIAL_SPOILER01);
		SpecialShotRegistry.registerSpecialShot(THSC_YouryokuSpoiler.class, SPECIAL_SPOILER02);
	}
	
	@Override
	public void spellcard_main()
	{
		if(time < 20)
		{
			if(target != null)
			{
				world.playSoundAtEntity(user, "portal.portal", 2.0F, 1.9F);//音を出す
				THShotLib.createCircleShot(user, user, pos(targetPosX, targetPosY, targetPosZ), getVecFromAngle(card.rotationYaw, card.rotationPitch), 2.0D, 0.2D, -0.1D, gravity_Zero(), shot(FORM_SCALE, rand.nextInt(7), 0.3F, 3.0F, 6, 70, SPECIAL_SPOILER01), 7, 1.5D, time * 3F);
		
			}
		}
		if(time > 40 && time < 60)
		{
			if(target != null)
			{
				world.playSoundAtEntity(user, "portal.portal", 2.0F, 1.9F);//音を出す
				THShotLib.createCircleShot(user, user, pos(targetPosX, targetPosY, targetPosZ), getVecFromAngle(card.rotationYaw, card.rotationPitch), 2.0D, 0.2D, -0.1D, gravity_Zero(), shot(FORM_SCALE, rand.nextInt(7), 0.3F, 3.0F, 6, 70, SPECIAL_SPOILER01), 7, 1.5D,  -time * 3F);
		
			}
		}
	}

	@Override
	public void specialShot_move(World world, int id, EntityTHShot shot) {
		switch(id)
		{
			case SPECIAL_SPOILER01:
				if(shot.ticksExisted >= 20)
				{
					if(shot.user != null)
					{
						shot.shotLimitSpeed = 1.0D;
						shot.shotAcceleration = 0.05D;
					
						double lengthToUserX = shot.user.posX - shot.posX;
						double lengthToUserZ = shot.user.posZ - shot.posZ;
						shot.rotationYaw = (float)Math.atan2(lengthToUserX, lengthToUserZ) / 3.141593F * 180F;
						shot.rotationPitch = (float)Math.atan2(shot.user.posY + 1.0D - shot.posY, Math.sqrt(lengthToUserX * lengthToUserX + lengthToUserZ * lengthToUserZ)) / 3.141593F * 180F;
						
						shot.setVector();
						
						if(shot.getDistanceToEntity(shot.user) < 1.5F)
						{
							if(!world.isRemote)
							{
								shot.setDead();
							}
						}
					}
				}
				break;
			case SPECIAL_SPOILER02:
				if(shot.ticksExisted >= 20)
				{
					if(shot.user != null)
					{
						shot.shotLimitSpeed = 1.0D;
						shot.shotAcceleration = 0.05D;
					
						double lengthToUserX = shot.user.posX - shot.posX;
						double lengthToUserZ = shot.user.posZ - shot.posZ;
						shot.rotationYaw = (float)Math.atan2(lengthToUserX, lengthToUserZ) / 3.141593F * 180F;
						shot.rotationPitch = (float)Math.atan2(shot.user.posY + 1.0D - shot.posY, Math.sqrt(lengthToUserX * lengthToUserX + lengthToUserZ * lengthToUserZ)) / 3.141593F * 180F;
						
						shot.setVector();
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
			case SPECIAL_SPOILER01:
				if(entity_Hit instanceof EntityLivingBase)
				{
					EntityLivingBase living = (EntityLivingBase)entity_Hit;
					if(!living.isEntityEqual(shot.user))
					{
						living.addPotionEffect(new PotionEffect(20, 20 * 20, 1));//ウィザー
						living.addPotionEffect(new PotionEffect(18, 20 * 20, 1));//攻撃低下
						shot.shotSpecial = SPECIAL_SPOILER02;
						return true;
					}
					else
					{

						return false;
					}
				}
			case SPECIAL_SPOILER02:
				if(shot.user != null && entity_Hit.isEntityEqual(shot.user))
				{
					EntityLivingBase living = (EntityLivingBase)entity_Hit;
					living.addPotionEffect(new PotionEffect(10, 20 * 20, 1));//回復
					living.addPotionEffect(new PotionEffect(5, 20 * 20, 1));//攻撃上昇
					return false;
				}
				else
				{
					return true;
				}
			default:
				return false;
		}
	}
}
