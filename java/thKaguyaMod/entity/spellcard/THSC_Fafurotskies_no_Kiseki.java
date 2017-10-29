package thKaguyaMod.entity.spellcard;

import static thKaguyaMod.DanmakuConstants.*;
import static thKaguyaMod.THShotLib.*;

import java.util.Calendar;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.ShotData;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.living.EntityDanmakuCreeper;
import thKaguyaMod.entity.shot.EntityNuclearShot;
import thKaguyaMod.entity.shot.EntityTHShot;
import thKaguyaMod.entity.shot.ISpecialShot;
import thKaguyaMod.registry.SpecialShotRegistry;

/** 奇跡「ファフロッキーズの奇跡」 */
public class THSC_Fafurotskies_no_Kiseki extends THSpellCard implements ISpecialShot
{	

	public static final int SPECIAL_FAFUROTSKIES01 = 1500;
	
	public THSC_Fafurotskies_no_Kiseki()
	{
		this.setNeedLevel(1);
		this.setRemoveTime(20);
		this.setEndTime(40);
		this.setOriginalUserName(SANAE);
		
		SpecialShotRegistry.registerSpecialShot(THSC_Fafurotskies_no_Kiseki.class, SPECIAL_FAFUROTSKIES01);
	}
	
	@Override
	public void spellcard_main()
	{
		if(time == 1)
		{
			int pattern = rand.nextInt(100);
			for(int i = 0; i < 10; i++)
			{
				Vec3 angle = getVecFromAngle(user.rotationYaw + rand.nextFloat() * 50F - 25F, rand.nextFloat() * 20F - 69F);
				Vec3 angle2 = angle(user.rotationYaw, 0.0F);
				
				THShotLib.createShot(user, user, pos_Distance(pos_User(), angle2, 0.5D), angle, 0F, 1.2D, pattern, 0.0D, gravity_Default(), shot(THShotLib.FORM_CIRCLE, THShotLib.AQUA, 0.4F, 6.0F, i, 20, SPECIAL_FAFUROTSKIES01));
			}
			THShotLib.playShotSound(user);
		}
	}

	@Override
	public void specialShot_move(World world, int id, EntityTHShot shot) {
		switch(id)
		{
		case SPECIAL_FAFUROTSKIES01:
			if(shot.ticksExisted == shot.getDeadTime())
			{
				int pattern = (int)shot.shotLimitSpeed;
				
				Calendar calendar = world.getCurrentDate();
				
				if(calendar.get(calendar.MONTH) + 1 == 12 && calendar.get(calendar.DATE) == 25)
				{
					EntityDanmakuCreeper entityCreeper = new EntityDanmakuCreeper(world);
					entityCreeper.setLocationAndAngles((int)shot.posX, (int)shot.posY, (int)shot.posZ, rand.nextFloat() * 360F, 0.0F);
					if(!world.isRemote)
					{
						
						world.spawnEntityInWorld(entityCreeper);
						shot.setDead();
					}
					return;
				}
				
				if(calendar.get(calendar.MONTH) + 1 == 1 && (calendar.get(calendar.DATE) >= 1 && calendar.get(calendar.DATE) <= 3))
				{
					if(!world.isRemote)
					{
						EntityNuclearShot nuclearShot = new EntityNuclearShot(world, shot.user, shot.user, pos(shot.posX, shot.posY, shot.posZ), angle_Random(), 0.0F, rotate_Default(), 0.0F, 9999,
								0.2D, 0.8D, 0.00D, gravity_Default(), ShotData.shot(0, rand.nextInt(7), 3.2F, 10.0F, 0, 70, 0));
						if(!world.isRemote)
						{
							world.spawnEntityInWorld(nuclearShot);//核弾を出現させる
						}
						nuclearShot.shootingFlag = true;
						shot.setDead();
					}
					return;
				}
	
				if(pattern <= 70)
				{
					for(int i = 0; i < 8; i++)
					{
						ShotData shotData = ShotData.shot(FORM_KNIFE, RANDOM, 0.6F, 10.0F, 3, 120);
						THShotLib.createShot(shot.user, shot, pos_Entity(shot), angle(rand.nextFloat() * 360F, rand.nextFloat() * 180 - 90F), 0F, 0.6D, 0.6D, 0.0D, gravity_Default(), shotData);
					}
					if(!world.isRemote)
					{
						shot.setDead();
					}
				}
				else if(pattern <= 80)
				{
					if(!world.isRemote)
					{
						world.spawnEntityInWorld(new EntityItem(world, shot.posX, shot.posY, shot.posZ, new ItemStack(Items.fish)));
						shot.setDead();
					}
				}
				else if(pattern <= 90)
				{
					EntityCreeper entityCreeper = new EntityCreeper(world);
					entityCreeper.setLocationAndAngles((int)shot.posX, (int)shot.posY, (int)shot.posZ, rand.nextFloat() * 360F, 0.0F);
					if(!world.isRemote)
					{
						world.spawnEntityInWorld(entityCreeper);
						shot.setDead();
					}
				}
				else
				{
					EntityChicken entitychicken = new EntityChicken(world);
					entitychicken.setLocationAndAngles((int)shot.posX, (int)shot.posY, (int)shot.posZ, rand.nextFloat() * 360F, 0.0F);
					if(!world.isRemote)
					{	
						world.spawnEntityInWorld(entitychicken);
						shot.setDead();
					}
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
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
}
