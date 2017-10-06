package thKaguyaMod.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import thKaguyaMod.block.BlockDanmakuCraftingTable;
import thKaguyaMod.block.BlockDivineSpirit;

/** ブロックの登録クラス */
public class THKaguyaBlocks
{

	/** 弾幕作業台 */
	public static Block danmaku_crafting_table;
	/** 小神霊 */
	public static Block divine_spirit;

	/** 弾幕作業台 弾 */
	public static ItemStack danmaku_crafting_table_shot;
	/** 弾幕作業台 レーザー */
	public static ItemStack danmaku_crafting_table_laser;
	
	/** 小神霊 赤 */
	public static ItemStack divine_spirit_red;
	/** 小神霊 青 */
	public static ItemStack divine_spirit_blue;
	/** 小神霊 緑 */
	public static ItemStack divine_spirit_green;
	/** 小神霊 黄 */
	public static ItemStack divine_spirit_yellow;
	/** 小神霊 紫 */
	public static ItemStack divine_spirit_purple;
	/** 小神霊 水 */
	public static ItemStack divine_spirit_aqua;
	/** 小神霊 橙 */
	public static ItemStack divine_spirit_orange;
	/** 小神霊 白 */
	public static ItemStack divine_spirit_white;
	
	/** ブロックを登録 */
	public static void setBlocks()
	{
		danmaku_crafting_table = new BlockDanmakuCraftingTable(Material.wood).setBlockName("danmakuCraftingTable");
			danmaku_crafting_table_shot  = new ItemStack(danmaku_crafting_table, 1, 0);
			danmaku_crafting_table_laser = new ItemStack(danmaku_crafting_table, 1, 1);
			
		divine_spirit = new BlockDivineSpirit(Material.glass).setBlockName("divineSpirit");
			divine_spirit_red = new ItemStack(divine_spirit, 1, 0);
			divine_spirit_blue = new ItemStack(divine_spirit, 1, 1);
			divine_spirit_green = new ItemStack(divine_spirit, 1, 2);
			divine_spirit_yellow = new ItemStack(divine_spirit, 1, 3);
			divine_spirit_purple = new ItemStack(divine_spirit, 1, 4);
			divine_spirit_aqua = new ItemStack(divine_spirit, 1, 5);
			divine_spirit_orange = new ItemStack(divine_spirit, 1, 6);
			divine_spirit_white = new ItemStack(divine_spirit, 1, 7);
	}
}
