package thKaguyaMod.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemHeavenlyPeach extends ItemFood
{
	
	//天界の桃　食べると一時的に硬くなる
	
	public ItemHeavenlyPeach(int foodLevel, boolean flag)
	{
		super(foodLevel, flag);
		this.setTextureName("thkaguyamod:heavenlyPeach");//テクスチャの指定
		maxStackSize = 10;//10個まで持てる
		setAlwaysEdible();//いつでも食べられる
	}
	
	//食べたときの処理
	@Override
	protected void onFoodEaten(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (!world.isRemote)
    	{
        	int potionType = 11;//耐性効果付与
        	int effectTime = 20 * 20;//効果時間。tick単位なので、１５秒なら、15 * 20になる。
        	int potionLevel = 2;//ポーション効果のレベル
    		//ポーション効果を付与
        	player.addPotionEffect(new PotionEffect(potionType, effectTime, potionLevel));//防御力アップ
    	}
    }
	
}