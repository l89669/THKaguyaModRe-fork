package thKaguyaMod.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import thKaguyaMod.entity.effect.EntityHakurouReflecter;

public class ItemHakurouken extends ItemSword
{
	//白楼剣　弾幕を跳ね返す
	//基本鉄の剣と同じ
    
    public ItemHakurouken(ToolMaterial toolMaterial)
    {
        super(toolMaterial);
        this.setTextureName("thkaguyamod:hakurouken");//テクスチャの指定
    }
	
		
	/** 右クリックを押した瞬間の処理
	 *  @param itemStack : 右クリックを押したItemStack
	 *  @param world     : ワールド
	 *  @param player    : 右クリックを押したプレイヤー
	 *  @return 右クリックを押したItemStackを返す
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
    	player.setItemInUse(itemStack, getMaxItemUseDuration(itemStack));//アイテムの使用継続時間を記憶させる
        return itemStack;
    }
	
	//右クリックを押し終わったときに呼び出されるメソッド
	@Override
	public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int usedTime)
    {	
		float size = (72000 - usedTime) / 3;
		boolean used = false;
		if(size > 8.0F)
		{
			size = 8.0F;
		}
		
		if(player.isSneaking())
		{

	    	EntityHakurouReflecter entityHakurouReflecter = new EntityHakurouReflecter(world, player);
	    	
	    	if(!world.isRemote)
	    	{
	    		world.spawnEntityInWorld(entityHakurouReflecter);
	    	}
	    	used = true;
		}
		else
		{
			
			if(player.onGround && (player.motionX != 0.0D || player.motionZ != 0.0D))
			{
				double angle = Math.atan2(player.motionX, player.motionZ);
				player.motionX -= Math.sin(angle) * 1.5D;
				player.motionZ -= Math.cos(angle) * 1.5D;
				used = true;
			}
		}
		
		if(used)
		{
			itemStack.damageItem(1, player);
		}
		
   	}

	//エンチャント可能か？
	@Override
    public int getItemEnchantability()
    {
        return 0;//エンチャント不可
    }
	
	//Forgeの追加メソッド　エンチャントブックの使用を許可するか
	@Override
	public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2)
    {
        return false;
    }
}
