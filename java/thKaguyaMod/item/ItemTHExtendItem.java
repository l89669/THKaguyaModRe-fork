package thKaguyaMod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/** エクステンドアイテム*/
public class ItemTHExtendItem extends Item
{	
	public ItemTHExtendItem()
	{
		super();
		this.setTextureName("thkaguyamod:material/ExtendItem");//テクスチャの指定
		setHasSubtypes(true);
		setMaxDamage(0);
		maxStackSize = 8;
		setCreativeTab(CreativeTabs.tabMaterials);//クリエイティブの素材タブに登録
	}
}