package thKaguyaMod.entity.spellcard;

import static thKaguyaMod.DanmakuConstants.*;
import net.minecraft.util.MathHelper;
import thKaguyaMod.THShotLib;

public class THSC_Kerochan_Fuuu_ni_Makezu extends THSpellCard
{
	//土着神「ケロちゃん風雨に負けず」
	
	public THSC_Kerochan_Fuuu_ni_Makezu()
	{
		this.setNeedLevel(2);
		this.setRemoveTime(40);
		this.setEndTime(200);
		this.setOriginalUserName(SUWAKO);
	}
	
	@Override
	public void spellcard_main()
	{
		if(time < 2)return;
		
		int i;
		
		if(time < 140)
		{
			for(i = 0; i < 4; i++)
			{
				double speed = 0.5D;// - j * 0.1D;
					
				THShotLib.createShot(user, user, pos(cardPosX, cardPosY - 0.4D, cardPosZ), angle(card.rotationYaw + i * 90F +  MathHelper.cos((float)time * 4F) * 45F, MathHelper.cos((float)(time + i * 45F) * 2F / 180F * 3.141593F)  * 19F - 70F) , 0F, speed, speed, 0.0F, THShotLib.gravity(0.0F, -0.01F, 0.0F), shot(THShotLib.FORM_LIGHT, THShotLib.BLUE, THShotLib.SIZE[THShotLib.FORM_LIGHT], 8.0F, 10, 120));
					
			}
		}
		for(i = 0; i < 2; i++)
		{
			double speed2 = rand.nextDouble() * 0.2D + 0.45D;
			THShotLib.createShot(user, user, pos(cardPosX, cardPosY - 0.4D, cardPosZ), angle(rand.nextFloat() * 360F, rand.nextFloat() * 14F - 75F) , 0F, speed2, speed2, 0.0F, THShotLib.gravity(0.0F, -0.018F, 0.0F), shot(FORM_RICE, AQUA, 0.15F, 9.0F, 4, 120));
		}
	}
}
