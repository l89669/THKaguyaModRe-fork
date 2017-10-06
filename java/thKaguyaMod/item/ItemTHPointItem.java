package thKaguyaMod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemTHPointItem extends Item
{
	//点アイテム
	
	public ItemTHPointItem()
	{
		super();
		this.setTextureName("thkaguyamod:material/THPointItem");//テクスチャの指定
		setMaxDamage(0);
		setCreativeTab(CreativeTabs.tabMaterials);//クリエイティブの素材タブに登録
	}
	
}