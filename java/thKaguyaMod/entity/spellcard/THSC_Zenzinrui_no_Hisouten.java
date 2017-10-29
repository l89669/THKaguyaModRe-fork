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

public class THSC_Zenzinrui_no_Hisouten extends THSpellCard implements ISpecialShot
{
	//「全人類の緋想天」
	
	public static final int SPECIAL_HISOUTEN01 = 700;
	
	public THSC_Zenzinrui_no_Hisouten()
	{
		this.setNeedLevel(5);
		this.setRemoveTime(30);
		this.setEndTime(150);
		this.setOriginalUserName(TENSHI);
		
		SpecialShotRegistry.registerSpecialShot(THSC_Zenzinrui_no_Hisouten.class, SPECIAL_HISOUTEN01);
	}

	@Override
	public void specialShot_move(World world, int id, EntityTHShot shot) {
		switch(id)
		{
			case SPECIAL_HISOUTEN01://気質弾
				if(shot.ticksExisted < 20)
				{
					shot.setShotSize(shot.getShotSize() + 0.1F);
				}
				break;
			default:
				break;
		}
	}

	@Override
	public boolean specialShot_hitBlock(World world, int id, EntityTHShot shot,
			MovingObjectPosition movingObjectPosition) {
		switch(id)
		{
			case SPECIAL_HISOUTEN01:
				ShotData shotData = ShotData.shot(FORM_KISHITU, RED, 0.3F, 4.0F, 0, 90);
				THShotLib.createShot(shot.user, shot, pos_Entity(shot), angle_Random(), 0F, 0.01D, 0.15D, 0.01D, gravity_Zero(), shotData);
				return false;
			default:
				return false;
		}
	}

	@Override
	public boolean specialShot_hitEntity(World world, int id,
			EntityTHShot shot, Entity entity_Hit) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
}
