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

public class ItemHouraiPearl extends Item
{
	//蓬莱の玉　　なにもできないアイテム
	
	public static final String houraiPearlNames[] =
    {
        "red", "blue", "green", "yellow", "purple", "aqua", "orange", "white"
    };
	public static final String houraiPearlIconName[] =
	{
		"RedPearl", "BluePearl", "GreenPearl", "YellowPearl", "PurplePearl", "AquaPearl", "OrangePearl", "WhitePearl"
	};
	
	@SideOnly(Side.CLIENT)
    private IIcon[] icon;
	
	public ItemHouraiPearl()
	{
		super();
		setHasSubtypes(true);
		setMaxDamage(0);
		setCreativeTab(CreativeTabs.tabMaterials);//クリエイティブの素材タブに登録
	}
	
	//langファイルで表示されるアイテム名を決める
	@Override
	public String getUnlocalizedName(ItemStack itemStack)
    {
        int num = MathHelper.clamp_int(itemStack.getItemDamage(), 0, 7);
        return super.getUnlocalizedName() + "." + houraiPearlNames[num];
    }
	
	//ダメージ値によってアイテムアイコンを変える
	@Override
	@SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage)
    {
        int num = MathHelper.clamp_int(damage, 0, 7);
        return this.icon[num];
    }
	
	//アイテムアイコンを登録する
	@Override
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.icon = new IIcon[houraiPearlIconName.length];

        for (int i = 0; i < houraiPearlIconName.length; ++i)
        {
            this.icon[i] = par1IconRegister.registerIcon("thkaguyamod:material/" + houraiPearlIconName[i]);
        }
    }

	//クリエイトモードのアイテム欄に、ダメージ値の違うアイテムも表示できるようにする
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < 7; i++)
        {
            list.add(new ItemStack(THKaguyaItems.hourai_pearl, 1, i));
        }
    }
	
}