package thKaguyaMod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thKaguyaMod.entity.item.EntityPendulum;

public class ItemPendulum extends Item
{
	
	//ナズーリンペンデュラム
	//ナズーリンの持つペンデュラム
	//使用時の横に持っていたアイテム（ブロックのみ）が周囲にあるか探索する
	//ダイヤの探索などに使える。といっても、この仕様ではダイヤ鉱石もっていないと・・・
	//Shiftで使用すると高感度モードになる
	
	public ItemPendulum()
	{
		super();
		this.setTextureName("thkaguyamod:pendulum");//テクスチャの指定
		maxStackSize = 1;
		setCreativeTab(CreativeTabs.tabMisc);//クリエイティブのその他タブに登録
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
    	Item item = null;
    	ItemStack searchItem = player.inventory.mainInventory[player.inventory.currentItem + 1];
    	
    	//右にアイテムがないなら失敗する
    	if(searchItem == null)
    	{
    		return itemStack;
    	}
    	//アイテムがブロックでないならサーチ不能
    	if(searchItem.getItem() instanceof ItemBlock == false)
    	{
    		return itemStack;
    	}
    	
    	if(player.inventory.currentItem < 8 && searchItem != null)
    	{
    		//blockID = searchItem.itemID;
    		//blockID = searchItem.
    		//blockID = searchItem.getItemSpriteNumber();
    		item = searchItem.getItem();
    	}
    	else
    	{
    		return itemStack;
    	}
    	
    	int mode = 0;
    	if(player.isSneaking())//スニークなら高感度ナズーリンペンデュラムになる
    	{
    		mode = 1;
    	}
    	
    	EntityPendulum entityPendulum;
    	entityPendulum = new EntityPendulum(world, player, mode, StatCollector.translateToLocal(item.getUnlocalizedName() + ".name"), searchItem);
    	world.playSoundAtEntity(player, "random.orb", 0.5F, 0.9F);
       	if(!world.isRemote)
       	{
        	world.spawnEntityInWorld(entityPendulum);//ペンデュラムを出現させる
       		itemStack.stackSize--;//スタックから消滅させる
       	}
    	
    	//使用時のメッセージを表示
    	String str;
    	if(mode == 0)
    	{
    		str = StatCollector.translateToLocal("thKaguya.msg.pendulum1") + StatCollector.translateToLocal(item.getUnlocalizedName() + ".name") + StatCollector.translateToLocal("thKaguya.msg.pendulum2");
    	}
    	else
    	{
    		str = StatCollector.translateToLocal("thKaguya.msg.pendulum1") + StatCollector.translateToLocal(item.getUnlocalizedName() + ".name") + StatCollector.translateToLocal("thKaguya.msg.pendulum3");
    	}
    	if(!world.isRemote)
    	{
    		player.addChatMessage( new ChatComponentText(str) );
    	}
    	
    	return itemStack;
   	}
	
	//Forgeの追加メソッド
	/*@Override
	public boolean onItemUseFirst(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int surface, float hitX, float hiyY, float hitZ) 
	{
		int blockID = world.getBlockId(x, y, z);
		String name = getBlock(x, y, z).getUnlocalizedName();
		spawnPendulum(world, itemStack, player, blockID, name);
		return true;
	}
	
	private ItemStack spawnPendulum(World world, ItemStack itemStack, EntityPlayer player, int blockID, String name)
	{
		int mode = 0;
    	if(player.isSneaking())//スニークなら高感度ナズーリンペンデュラムになる
    	{
    		mode = 1;
    	}
		
		EntityPendulum entityPendulum;
    	entityPendulum = new EntityPendulum(world, player, mode, blockID);
    	world.playSoundAtEntity(player, "random.orb", 0.5F, 0.9F);
       	if(!world.isRemote)
       	{
        	world.spawnEntityInWorld(entityPendulum);//ペンデュラムを出現させる
       		itemStack.stackSize--;//スタックから消滅させる
       	}
    	
    	//使用時のメッセージを表示
    	String str;
    	if(mode == 0)
    	{
    		str = "Pendulum started searching for " + name;
    	}
    	else
    	{
    		str = "Pendulum started searching for " + name + " by High sensitivity mode!";
    	}
    	if(!world.isRemote)
    	{
    		player.addChatMessage( str );
    	}
		
		return itemStack;
	}*/

}