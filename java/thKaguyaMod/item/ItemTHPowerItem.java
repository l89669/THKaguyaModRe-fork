package thKaguyaMod.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import thKaguyaMod.init.THKaguyaItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTHPowerItem extends Item
{
	//パワーアイテム

	public static final String itemNames[] =
	{
		"small", "big"
	};
	
	public static final String itemIconName[] =
	{
		"SmallPowerUpItem", "BigPowerUpItem"
	};
		
	@SideOnly(Side.CLIENT)
	private IIcon[] icon;
	
	public ItemTHPowerItem()
	{
		super();
		setHasSubtypes(true);
		setMaxDamage(0);
		setCreativeTab(CreativeTabs.tabMaterials);//クリエイティブの素材タブに登録
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack)
    {
        int i = MathHelper.clamp_int(itemStack.getItemDamage(), 0, 15);
        return super.getUnlocalizedName() + "." + itemNames[i];
    }
	
	//ダメージ値によってアイテムアイコンを変える
	@Override
	@SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage)
    {
        int i = MathHelper.clamp_int(damage, 0, 6);
        return this.icon[i];
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.icon = new IIcon[itemIconName.length];

        for (int i = 0; i < itemIconName.length; ++i)
        {
            this.icon[i] = par1IconRegister.registerIcon("thkaguyamod:material/" + itemIconName[i]);
        }
    }

	@Override
	@SideOnly(Side.CLIENT)
	//クリエイトモードのアイテム欄に、ダメージ値の違うアイテムも表示できるようにする
	public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < 2; i++)
        {
            list.add(new ItemStack(THKaguyaItems.power_item, 1, i));
        }
    }
	
}