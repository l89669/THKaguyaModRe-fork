package thKaguyaMod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/** スペルカードアイテム*/
public class ItemTHBombItem extends Item
{	
	public ItemTHBombItem()
	{
		super();
		this.setTextureName("thkaguyamod:material/BombItem");//テクスチャの指定
		setHasSubtypes(true);
		setMaxDamage(0);
		maxStackSize = 64;
		setCreativeTab(CreativeTabs.tabMaterials);//クリエイティブの素材タブに登録
	}
}