package thKaguyaMod.item;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import thKaguyaMod.entity.effect.EntityCirnoIceBox;

public class ItemIcicleSword extends ItemSword
{

	//アイシクルソード
	//攻撃した相手を凍りづけにする
    
    public ItemIcicleSword(ToolMaterial toolMaterial)
    {
        super(toolMaterial);
        this.setTextureName("thkaguyamod:IcicleSword");//テクスチャの指定
    }

	//Entityに当たった時に呼び出される
	@Override
    public boolean hitEntity(ItemStack itemStack, EntityLivingBase living_hit, EntityLivingBase living_used)
    {
		/*int x = (int)(living_hit.posX - 0.5D) - 1;
		int y = (int)(living_hit.posY + 0.5D) - 1;
		int z = (int)(living_hit.posZ - 0.5D) - 1;
		int yLoop = 3 + (int)living_hit.height;
		
		living_hit.setPosition(x + 1 + 0.5D, y + 1 + 0.5D, z + 1 + 0.5D);
		living_hit.motionX = 0.0D;
		living_hit.motionY = 0.0D;
		living_hit.motionZ = 0.0D;
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < yLoop; j ++)
			{
				for(int k = 0; k < 3; k++)
				{
					if(living_hit.worldObj.isAirBlock(x, y, z) || living_hit.worldObj.getBlock(x, y, z) == Blocks.water
							|| living_hit.worldObj.getBlock(x, y, z) == Blocks.sapling
							|| living_hit.worldObj.getBlock(x, y, z) == Blocks.deadbush
							|| living_hit.worldObj.getBlock(x, y, z) == Blocks.fire
							|| living_hit.worldObj.getBlock(x, y, z) == Blocks.snow_layer)
						{
						living_hit.worldObj.setBlock(x, y, z, Blocks.ice);
					}
					z++;
				}
				z -= 3;
				y++;
			}
			y -= yLoop;
			x++;
		}*/
		
		
		World world = living_hit.worldObj;
		
		//周囲のEntityを取得
		List<?> list = world.getEntitiesWithinAABBExcludingEntity(living_hit, living_hit.boundingBox.addCoord(0.0D, 0.0D, 0.0D).expand(0.0D, 0.0D, 0.0D));
		for(int k = 0; k < list.size(); k++)
		{
			Entity entity = (Entity)list.get(k);
			if(entity instanceof EntityCirnoIceBox)//懐中時計があるなら
			{
				EntityCirnoIceBox iceBox1 = (EntityCirnoIceBox)entity;
				if(iceBox1.frozen.equals(living_hit))
				{
					return super.hitEntity(itemStack, living_hit, living_used);
				}
			}
		}
		
		if(living_hit.width > 3.0F || living_hit.height > 3.0F)
		{
			return super.hitEntity(itemStack, living_hit, living_used);
		}
		
		EntityCirnoIceBox iceBox = new EntityCirnoIceBox(world, living_hit);
		if(!world.isRemote)
		{
			world.spawnEntityInWorld(iceBox);//
		}
		
        return super.hitEntity(itemStack, living_hit, living_used);
    }
		
	/** 右クリックを押した瞬間の処理
	 *  @param itemStack : 右クリックを押したItemStack
	 *  @param world     : ワールド
	 *  @param player    : 右クリックを押したプレイヤー
	 *  @return 右クリックを押したItemStackを返す
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
    	entityPlayer.setItemInUse(itemStack, getMaxItemUseDuration(itemStack));//アイテムの使用継続時間を記憶させる
        return itemStack;
    }

	//エンチャント可能か？
	@Override
    public int getItemEnchantability()
    {
        return 0;//エンチャント不可
    }
	
	/**
     * Return whether this item is repairable in an anvil.
     */
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return Item.getItemFromBlock(Blocks.ice) == par2ItemStack.getItem() ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
    }
	
	//Forgeの追加メソッド　エンチャントブックの使用を許可するか
	@Override
	public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2)
    {
        return false;
    }
	
}
