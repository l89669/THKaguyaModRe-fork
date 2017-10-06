package thKaguyaMod.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;


public class ItemSwallowCowrieShell extends ItemFood
{
	
	//レベルを消費してそれに応じた、空腹の回復、強力なリジェネ効果を得る（隠し満腹度はかなり破格っぽい）
	//消費レベルは、通常は空腹分（小数点以下切り上げ）のレベル、シフト時は問答無用で１０レベル消費
	//ただし、レベルが足りなければそこまでの効果しか得られない
	
	public ItemSwallowCowrieShell(int foodLevel, boolean flag)
	{
		super(foodLevel, flag);
		this.setTextureName("thkaguyamod:koyasugai");//テクスチャの指定
		maxStackSize = 1;//最大スタック数
		setAlwaysEdible();//いつでも食べられる
	}
	
	//食べたときに呼び出されるメソッド
	@Override
	public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player)
    {
    	int performance = player.experienceLevel;//性能が変動するため、そのための変数
		//スニーク状態なら最大１０レベル問答無用に消費して、そのぶんの効果を得る
    	//スニークでないなら空腹分のみ回復するようにする　当然消費レベルも得られる効果も回復したぶんのみ
    	//基本的にスニークで使用する方が癖が強い効果にする
    	if(!player.isSneaking())
    	{
    		performance = (int)((20 - player.getFoodStats().getFoodLevel() ) / 2.0 + 0.5);
    		if(performance < 0)
    		{
    			performance = 0;
    		}
    		else if(performance > player.experienceLevel)
    		{
    			performance = player.experienceLevel;
    		}
    	}
    	if(performance > 10)
    	{
    		performance = 10;//消費は最大10レベル
    	}

    	//par3EntityPlayer.removeExperience(performance);//経験値消費
    	player.addExperienceLevel(-performance);
    	/***
    	経験値の消費量に合わせて空腹ゲージを回復 5Fの部分は多分隠し満腹度
    	大きいほど満腹でも全然満腹度が減らなくなる
    	隠し満腹度5Fはかなり大きい方　最大のステーキでも0.8Fくらい
    	実際の値は、空腹回復量*隠し空腹度*2で計算される。
    	これによると、ステーキは、 8(空腹回復度) * 0.8（隠し満腹度) * 2 = 12.8となる
		子安貝は　(1～10)*2(空腹回復度 偶数にしかならない）　* 5.0（隠し空腹度) * 2 =　20～200（ただし20の倍数)
		何気なくずっとこの設定だが、割りと破格の性能を誇っている。　バランスは要検証
		***/
		player.getFoodStats().addStats(performance * 2, 5F);
        world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);//ゲップｗ　いらない？

        if (!world.isRemote)
        {
            player.addPotionEffect(new PotionEffect(10, performance * 3 * 20, 1));//消費レベル×3秒間強力なリジェネ効果
        	//									 PotionEffect(ポーションのタイプ,持続時間（秒）*20（20は定数？）,レベル（0がレベル１、1がレベル２）)
        }

		return itemStack;
		
    }
	
	//アイテムを発光させる。 trueなら発光
	@Override
	public boolean hasEffect(ItemStack itemStack, int pass)
    {   
		return true;
    }
}