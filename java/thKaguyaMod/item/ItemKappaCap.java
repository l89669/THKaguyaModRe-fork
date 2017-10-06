package thKaguyaMod.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
//import net.minecraftforge.common.IArmorTextureProvider;

public class ItemKappaCap extends ItemArmor// implements IArmorTextureProvider//ItemArmorを継承。これを継承しないと簡単に鎧を着ることができない。
{	
	
	//河童の帽子
	//着用すると水中での移動が速くなる
	//水中にいないとどんどん壊れる
    
    public ItemKappaCap(ArmorMaterial armorMaterial, int j, int k)
    {
    	super(armorMaterial, j, k);//鎧の素材を、独自のものにしたいなら、ArmorMaterialを独自のものに変更すればいいはず
    	this.setTextureName("thkaguyamod:kappaCap");//テクスチャの指定
    	setMaxDamage(500);//アイテムの耐久設定
    }
    
	//アーマーのテクスチャを指定
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		return "thkaguyamod:textures/armor/hinezumi_1.png";
	}
	
	//エンチャントの可否
	@Override
	public int getItemEnchantability()
    {
        return 0;//エンチャント不可
    }
	
	@Override
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
    	if(player.isInWater())//水中にいるなら
    	{
    		player.motionX *= 1.2D;//速度を1.2倍する
    		if(player.motionY > 0.0D)
    		{
    			player.motionY *= 1.2D;
    		}
    		player.motionZ *= 1.2D;
    		double maxSpeed = 0.5D;
    		if(player.motionX > maxSpeed)
    		{
    			player.motionX = maxSpeed;
    		}
    		else if(player.motionX < -maxSpeed)
    		{
    			player.motionX = -maxSpeed;
    		}
    		if(player.motionY > maxSpeed)
    		{
    			player.motionY = maxSpeed;
    		}
    		if(player.motionZ > maxSpeed)
    		{
    			player.motionZ = maxSpeed;
    		}
    		else if(player.motionZ < -maxSpeed)
    		{
    			player.motionZ = -maxSpeed;
    		}
    		if(itemStack.isItemDamaged() == true)
    		{
    			itemStack.damageItem(-3, player);//耐久を3増やす
    		}
    	}
    	else//地上にいるなら
    	{
    		if(player.isWet())//濡れているなら
    		{
    			itemStack.damageItem(-1, player);//耐久を1増やす
    		}
    		else
    		{
    			
    			if(itemStack.getItemDamage() < 498)
    			{
    				itemStack.damageItem(1, player);//耐久を1減らす
    			}
    		}
    	}
    }

	
}