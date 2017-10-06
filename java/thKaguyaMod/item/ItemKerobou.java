package thKaguyaMod.item;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thKaguyaMod.client.model.ModelSuwakoHat;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class ItemKerobou extends ItemArmor
{	
	
	//諏訪子の帽子
	//物理的なダメージを受けたとき、確定で相手にもダメージを与える
    
    public ItemKerobou(ArmorMaterial armorMaterial, int j, int k)
    {
    	super(armorMaterial,j,k);
    	this.setTextureName("thkaguyamod:Kerobou");//テクスチャの指定
    	setMaxDamage(620);//アイテムの耐久設定
    }
    
	//アーマーのテクスチャを指定
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		return "thkaguyamod:textures/armor/SuwakoHatTexture.png";
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
	
	//インベントリにある限り常時呼び出されるメソッド
	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int i, boolean flag)
	{
	   	if(!itemStack.isItemEnchanted())//エンチャントがついていないなら、刺の鎧１０を付与（救済処置）
	   	{
	   		itemStack.addEnchantment(Enchantment.thorns, 10);
	   	}
	}
	
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
	   	if(!itemStack.isItemEnchanted())//エンチャントがついていないなら、刺の鎧１０を付与（救済処置）
	   	{
	   		itemStack.addEnchantment(Enchantment.thorns, 10);
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
    		return new ModelSuwakoHat(0.4F, entityLiving.yOffset, 64, 32);
    	}
    	else
    	{
    		return null;
    	}
    }
}
