package thKaguyaMod.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thKaguyaMod.THKaguyaCore;
import thKaguyaMod.THKaguyaLib;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.spellcard.THSpellCard;
import thKaguyaMod.init.THKaguyaItems;
import thKaguyaMod.registry.SpellCardRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/** スペルカード */
public class ItemTHSpellCard extends Item
{
	
	@SideOnly(Side.CLIENT)
    private IIcon[] icon;
	@SideOnly(Side.CLIENT)
    private IIcon[] icon_lv;
	
    /** スペルカードのコンストラクタ */
	public ItemTHSpellCard()
	{
		super();
		this.setTextureName("thkaguyamod:spellCard/MusouFuuin");	// テクスチャの指定
		this.maxStackSize = 1;										// 最大スタック数
		this.setCreativeTab(THKaguyaCore.tabSpellCard);			// クリエイティブのスペルカードタブに登録
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack)
    {
		//int i = MathHelper.clamp_int(itemStack.getItemDamage(), 0, SpellCardRegistry.getNumberOfSpellCard());
    	return super.getUnlocalizedName() + "." + itemStack.getItemDamage();
    }
	
	/**
	 * 重ねアイコンを設定する
	 * @return [true : 重ねアイコンを有効にする]　[false : 重ねアイコンを無効にする]
	 */
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses()
    {
        return true;	//重ねアイコンに設定
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * ダメージ値によってアイテムアイコンを変える
	 * @param damage	: ダメージ値
	 * @return アイコンを返す
	 */
    public IIcon getIconFromDamage(int damage)
    {

		return icon[damage];
    }
	
    /**
     * ダメージ値とレイヤー値で描画を変える
     * @param damage	: ダメージ値
     * @param layer	: レイヤー値
     * @return アイコンを返す
     */
	@Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamageForRenderPass(int damage, int layer)
    {
		int level = this.getLevel(damage);
		
		if( (level < 1) || (level > 5) )
		{
			level = 1;
		}
		
    	if( layer == 0 )
    	{
    		return getIconFromDamage( damage );
    	}
    	else if( layer == 1 )
    	{
    		return icon_lv[level - 1];
    	}
    	return icon[0];
    }
	
	//アイテムアイコンを登録する
	@Override
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
		icon =new IIcon[1024];icon_lv =new IIcon[5];
		THSpellCard spell;
		int spellcardID;
		int i = 0;
		for(String name : SpellCardRegistry.spellcardClass.keySet())
		{
			spellcardID = i;
			i++;
			//icon[(int)id] = iconRegister.registerIcon("thkaguyamod:spellCard/" + (int)id);
			try {
				spell = (THSpellCard)SpellCardRegistry.spellcardClass.get(name).newInstance();
				
				icon[SpellCardRegistry.getSpellCardNumber(name)] = iconRegister.registerIcon(SpellCardRegistry.getSpellCardModDomain(name) + ":spellCard/" + name);// + spell.getIconName());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			//icon[] = iconRegister.registerIcon("thkaguyamod:spellCard/" + "0");// + spell.getIconName());
		}
		
		for( i = 0; i < 5; i++)
		{
			icon_lv[i] = iconRegister.registerIcon("thkaguyamod:spellCard/spellcard_lv" + (i + 1));
		}

    }
	
	//右クリックを終了したときに呼び出されるメソッド
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {	
		//スペルカードを宣言する
    	THKaguyaLib.checkSpellCardDeclaration(world, itemStack, player, itemStack.getItemDamage(), THShotLib.NORMAL, true);
    	
    	return itemStack;
   	}
	
	//インベントリにある限り常時呼び出されるメソッド
	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int i, boolean flag)
	{
		int miracleTime = 0;
		NBTTagCompound nbt = itemStack.getTagCompound();
		if(nbt != null)
		{
			miracleTime = nbt.getInteger("Charge");
			if(miracleTime > 1)
			{
				nbt.setInteger("Charge", miracleTime - 1);
			}
			else
			{
				nbt.removeTag("Charge");
			}
		}
	}
	
	 //アイテムの表示情報付加
    @Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean bool)
	{
		super.addInformation(itemStack, player, list, bool);
		
		list.add("Lv." + getLevel( itemStack.getItemDamage() ) + " Spell Card");
		
		int miracleTime = 0;
		NBTTagCompound nbt = itemStack.getTagCompound();
		if(nbt != null)
		{
			miracleTime = nbt.getInteger("Charge");
		}
		if(itemStack.getItemDamage() >= 14 && itemStack.getItemDamage() <= 18)
		{
			int level = 20 - ((14 - itemStack.getItemDamage()) * 5) ;
			list.add(StatCollector.translateToLocal("thKaguya.msg.sneSc1") + level + StatCollector.translateToLocal("thKaguya.msg.sneSc2")+ (itemStack.getItemDamage() - 13) + StatCollector.translateToLocal("thKaguya.msg.sneSc3"));
			list.add(StatCollector.translateToLocal("thKaguya.msg.sneSc4") + miracleTime / 10 + "%");
		}

	}
    
    /**
     * アイテムスタックからスペルカードIDを取得する
     * @param itemStack	: アイテムスタック
     * @return スペルカードID（ダメージ値）を返す
     */
    public int getSpellCardID( ItemStack itemStack )
    {
    	return itemStack.getItemDamage();
    }
    
    /**
     * スペルカードIDからスペルカードの宣言コストのレベルを取得する
     * @param itemStack : アイテムスタック
     * @return 宣言コストのレベル（1～5)
     */
    public int getLevel( int spellCardID )
    {
		int needLevel = 1;
		Class<?> spellcard;// = THSpellCard_0.class;
		THSpellCard useSpellCard;// = spellcard.newInstance();
		
		if((spellcard = SpellCardRegistry.getSpellCardClass( spellCardID )) != null)
		{
			try {
				useSpellCard = (THSpellCard)spellcard.newInstance();

				needLevel = useSpellCard.getNeedLevel();
				
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		return needLevel;
    }
    
	//アイテムを発光させる。 trueなら発光
	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack itemStack, int pass)
	{   
		//早苗のスペルカードがアクティブ状態なら光らせる
		if(itemStack.getItemDamage() >= 14 && itemStack.getItemDamage() <= 18)
		{
			NBTTagCompound nbt = itemStack.getTagCompound();
			if(nbt != null)
			{
				if(nbt.getInteger("Charge") > 0)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	//クリエイトモードのアイテム欄に、ダメージ値の違うアイテムも表示できるようにする
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
    {
		List<String> sortList = SpellCardRegistry.sortSpellCard();
		
		for(int i = 0; i < sortList.size(); i++)
		{
			list.add(new ItemStack(THKaguyaItems.spell_card, 1, SpellCardRegistry.spellcardID.get(sortList.get(i))));
		}
    }
	
}