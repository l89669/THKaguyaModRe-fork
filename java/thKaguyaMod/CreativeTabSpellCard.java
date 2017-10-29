package thKaguyaMod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import thKaguyaMod.init.THKaguyaItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/** スペルカードのクリエイティブタブ */
public class CreativeTabSpellCard extends CreativeTabs
{
	//新しいクリエイティブタグ"SpellCard"の設定

    public CreativeTabSpellCard(String type)
    {
        super(type);
    }

	//@Override
    //@SideOnly(Side.CLIENT)
    /**
     * the itemID for the item to be displayed on the tab
     */
    /*public int getTabIconItemIndex()
    {
        return mod_thKaguya.spellCardItem.itemID;
    }*/
	

    /** タブの表示名称 */
	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel()
	{
		return "SpellCard";
	}

	/** タブの画像(既に設定しているアイテムから選択) */
	@Override
	public Item getTabIconItem() {
		return THKaguyaItems.spell_card;
	}
}