package thKaguyaMod.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFireRatRobe extends ItemArmor
{	
	
	//火鼠の皮衣
	//炎に耐性のある衣 着ても持っても炎に対しては強い
    
    public ItemFireRatRobe(ArmorMaterial armorMaterial, int j, int k)
    {
    	super(armorMaterial, j, k);//鎧の素材を、独自のものにしたいなら、EnumArmorMaterialを独自のものに変更すればいいはず
    	this.setTextureName("thkaguyamod:hinezumi");//テクスチャの指定
    	setMaxDamage(0);//アイテムの耐久設定。　0なら無限
    }
    
	//アーマーのテクスチャを指定
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		return "thkaguyamod:textures/armor/hinezumi_1.png";
	}
	
	/*public void takenFromCrafting(EntityPlayer player, ItemStack itemstack, IInventory iinventory)
    {
    	itemstack.addEnchantment(Enchantment.fireProtection, 5);//fireProtection　レベル５を付加
    	//他のエンチャント効果を付けたいのなら、Enchantment.javaを参照
    	//ちなみにShiftでアイテムを回収した場合にエンチャントがつかないのは、このメソッドのバグ？
    }*/
	
	//左クリックでEntityに当たった場合に呼び出されるメソッド
	@Override
	public boolean hitEntity(ItemStack itemStack, EntityLivingBase living_hit, EntityLivingBase living_used)
    {
    	living_hit.setFire(20);//当たったEntityに着火　数値が大きいほど長くなるのかな？
        return true;
    }
	
	//右クリック時にとる行動。
	@Override
	public EnumAction getItemUseAction(ItemStack itemStack)
    {
        return EnumAction.block;//ガードの構えをする
    }
	
	//右クリックを押している時間の最大時間
	@Override
	public int getMaxItemUseDuration(ItemStack itemStack)
    {
        return 72000;
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
    	player.setItemInUse(itemStack, getMaxItemUseDuration(itemStack));
    	player.extinguish();//使用者の炎を消す
        return itemStack;
    }
	
	//インベントリにある限り常時呼び出されるメソッド
	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int i, boolean flag)
	{
    	if(!itemStack.isItemEnchanted())//エンチャントがついていないなら、炎耐性５を付与（救済処置）
    	{
    		itemStack.addEnchantment(Enchantment.fireProtection, 5);
    	}
	}
	
	//アイテムの表示サイズ。　trueなら剣の様に大きく表示される
	@Override
    public boolean isFull3D()
    {
        return true;
    }
    
	//アイテムを発光させるか。 trueなら発光
	@Override
	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack itemStack, int pass)
    {   
		return true;
    }
	
	//エンチャントの可否
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
	
	//アーマースロットに入っているときに呼び出される（Forge追加）
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
    {
		player.extinguish();//着用者の炎を消す
    }
	
}