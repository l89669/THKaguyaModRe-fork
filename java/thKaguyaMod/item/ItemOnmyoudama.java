package thKaguyaMod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.THShotLib;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/** 陰陽玉 */
public class ItemOnmyoudama extends Item
{
	
	//public ItemOnmyoudama(ArmorMaterial armorMaterial, int renderIndex, int armorType)
	public ItemOnmyoudama()
	{
		super();
		this.setTextureName("thkaguyamod:material/Onmyoudama");//テクスチャの指定
		setCreativeTab(CreativeTabs.tabMaterials);//クリエイティブの素材タブに登録
		this.maxStackSize = 1;//最大スタック数
	}
	
	//アーマーのテクスチャを指定
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		return "thkaguyamod:textures/armor/MarisaHatTexture.png";
	}
	
	//エンチャントの可否
	@Override
	public int getItemEnchantability()
    {
        return 0;//エンチャント不可
    }
	
	@SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses()
    {
        return false;
    }
	
	//Forgeの追加メソッド　エンチャントブックの使用を許可するか
	@Override
	public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2)
    {
        return false;
    }
	
	//Forgeの追加メソッド　アーマースロットに入っているときに呼び出される
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
    {	
		Vec3 look = THShotLib.angle(player.rotationYaw, player.rotationPitch);
		double speed = 0.4D;
		
		if( !player.isSneaking() )
		{
			if( player.motionY < 0.0D )
			{
				//player.motionY = 0.0D;
			}
		}
		
		player.motionX = player.motionX * 1.2D;
		player.motionZ = player.motionZ * 1.2D;
		
		
			
   		//周囲のEntityを取得
		/*List<?> list = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.addCoord(0.0D, 0.0D, 0.0D).expand(5.0D, 5.0D, 5.0D));
		for(int k = 0; k < list.size(); k++)
		{
			Entity entity = (Entity)list.get(k);
			if(entity instanceof EntityItem)//アイテムがあるなら
			{
				EntityItem item = (EntityItem)entity;
				item.onCollideWithPlayer(player);
			}
		}*/
    }
}
