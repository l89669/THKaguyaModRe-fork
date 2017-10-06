package thKaguyaMod.item;

import java.util.List;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thKaguyaMod.client.model.ModelMarisaHat;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class ItemMarisaHat extends ItemArmor
{	
	
	/*
	 * 魔理沙の帽子
	 * かぶると通常よりアイテムとの当たり判定が大きくなる
	 */
	
    
    public ItemMarisaHat(ArmorMaterial armorMaterial, int renderIndex, int armorType)
    {
    	super(armorMaterial,renderIndex,armorType);
    	this.setTextureName("thkaguyamod:MarisaHat");//テクスチャの指定
    	setMaxDamage(40);//耐久値
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
	
	/*
	 * IArmorTextureProviderで防具のテクスチャファイルを適当させているだけです。
	 * 頭、胴、足は"/armor/hogearmor_1.png"から
	 * 脚は"/armor/hogearmor_2.png"を参照させています。
	 */
	/*@SideOnly(Side.CLIENT)
	public String getArmorTextureFile(ItemStack itemstack)
	{
		return "/armor/hinezumi_1.png";
	}*/

	
	//アイテムを大きく表示する
	@Override
	public boolean isFull3D()
    {
        return true;
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
   		//周囲のEntityを取得
		List<?> list = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.addCoord(0.0D, 0.0D, 0.0D).expand(5.0D, 5.0D, 5.0D));
		for(int k = 0; k < list.size(); k++)
		{
			Entity entity = (Entity)list.get(k);
			if(entity instanceof EntityItem)//アイテムがあるなら
			{
				EntityItem item = (EntityItem)entity;
				item.onCollideWithPlayer(player);
			}
		}
    }
	
    /**
     * Override this method to have an item handle its own armor rendering.
     * 
     * @param  entityLiving  The entity wearing the armor 
     * @param  itemStack  The itemStack to render the model of 
     * @param  armorSlot  0=head, 1=torso, 2=legs, 3=feet
     * 
     * @return  A ModelBiped to render instead of the default
     */
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
    {
    	if(armorSlot == 0)
    	{
    		return new ModelMarisaHat(0.4F, entityLiving.yOffset, 64, 32);
    	}
    	else
    	{
    		return null;
    	}
    }
}