package thKaguyaMod.item;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import thKaguyaMod.init.THKaguyaItems;

public class ItemOnbashira extends ItemSword
{

	/*
	* 御柱
	* とてつもなく強力な武器
	* しかし重くて、所持しているだけで動作に支障をきたす
	* 投げつけることもできる
	*/
    
    public ItemOnbashira(ToolMaterial toolMaterial)
    {
        super(toolMaterial);
        this.setTextureName("thkaguyamod:onbashira");//テクスチャの指定
    }
	
	//ブロックに対する強さ
	@Override
    public float getDigSpeed(ItemStack itemStack, Block block, int metadata)
    {
    	return 3F;
    }
	
	//Entityに当たったときの処理
	@Override
    public boolean hitEntity(ItemStack itemStack, EntityLivingBase living_hit, EntityLivingBase living_used)
    {
    	living_hit.attackEntityFrom(DamageSource.causeMobDamage(living_used), 5 + itemRand.nextInt(18));
    	itemStack.damageItem(1, living_used);
    	return true;
    }
	
	//所持している限り常時呼び出されるメソッド
	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int i, boolean flag)
	{
		//移動速度が0.9倍になる。持てば持つほど移動速度が落ちる
		entity.motionX *= 0.9;
		entity.motionZ *= 0.9;
		if(entity.motionY > 0.0)
		{
			entity.motionY *= 0.9;
		}
		
		//entityがプレイヤーなら
		if(entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)entity;
			//手に何か持っているなら
			if(player.inventory.getCurrentItem() != null)
			{
				//プレイヤーが手にしているものが御柱なら
				if(player.inventory.getCurrentItem().getItem() == THKaguyaItems.onbashira)
				{
					if (!world.isRemote)
    				{
    					//	 				PotionEffect(ポーションのタイプ,持続時間（秒）*20（20は定数？）,レベル（0がレベル１、1がレベル２）)
        				//player.addPotionEffect(new PotionEffect( 4, 1 * 10, 0));//攻撃力アップ
    				}
				}
			}
    	}
	}
}
