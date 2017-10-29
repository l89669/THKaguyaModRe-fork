package thKaguyaMod.entity.spellcard;

import static thKaguyaMod.DanmakuConstants.*;
import thKaguyaMod.entity.shot.EntityMusouFuuin;

public class THSC_MusouFuuin extends THSpellCard
{
	//霊符「夢想封印」
	
	public THSC_MusouFuuin()
	{
		this.setNeedLevel(5);
		this.setEndTime(90);
		this.setRemoveTime(70);
		this.setOriginalUserName(REIMU);
	}
	
	@Override
	public void spellcard_main()
	{	
		if(time % 2 == 0 && time >= 15 && time < 49)
		{
			if(user != null && target != null)
			{
				EntityMusouFuuin musouFuuin = new EntityMusouFuuin(world, user, user, pos_User(), angle(time * 33F, 0F), 0F, rotate_Default(), 0F, 9999, 2.0D, 0.0D, -0.1D, gravity_Zero(), 2.0F, 9.0F, target);
				if(!world.isRemote)
       			{
        			world.spawnEntityInWorld(musouFuuin);//夢想封印の光弾を出現させる
       			}
			}
		}
	}
	
	//魔法陣の色を返す
	@Override
	public int getSpellCardCircleColor()
	{
		return RAINBOW;
	}
}
