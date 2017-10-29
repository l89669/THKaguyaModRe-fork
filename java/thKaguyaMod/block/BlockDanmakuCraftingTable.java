package thKaguyaMod.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thKaguyaMod.THKaguyaCore;
import thKaguyaMod.init.THKaguyaBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/** 弾幕作業台ブロック */
public class BlockDanmakuCraftingTable extends Block
{
	/** テクスチャ */
	public static final String[] texture = {"thkaguyamod:danmaku_crafting_table", "thkaguyamod:danmaku_crafting_table2"};
	private IIcon[] iconArray = new IIcon[2];
	
	/**
	 * コンストラクタ 
	 * @param material : ブロックの素材。木材などの設定
	 */
	public BlockDanmakuCraftingTable(Material material)
	{
		super(material);
		
		this.setCreativeTab(CreativeTabs.tabDecorations);				//クリエイティブタブの選択
		this.setBlockTextureName("thkaguyamod:danmaku_crafting_table");	//テクスチャの設定
		this.setHardness(1.5F);											//硬さ
		this.setResistance(1.0F);										//爆発耐性
		this.setStepSound(Block.soundTypeWood);							//歩いたり叩いたときの音
		this.setLightLevel(15.0F / 15.0F);								//明るさ。0.0F～1.0Fで15段階で設定
	}
	
	/**
	 * クリエイティブタブにメタデータの異なるアイテムも出現させる
	 * @param item : アイテム
	 * @param creativeTabs : 出現させるクリエイティブタブ
	 * @param list : クリエイティブタブの出現アイテムリスト
	 */
	@Override
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
    {
        list.add(new ItemStack(THKaguyaBlocks.danmaku_crafting_table, 1, 0));
        list.add(new ItemStack(THKaguyaBlocks.danmaku_crafting_table, 1, 1));
    }
	

	/**
	 * ブロックが右クリックされたときに呼ばれる
	 * 
	 */
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, 
			EntityPlayer player, int size, float disX, float disY, float disZ)
	{
		if(world.isRemote)
		{
			return true;
		}
		else
		{
			if(world.getBlockMetadata(x, y, z) == 0)
			{
				player.openGui(THKaguyaCore.instance, THKaguyaCore.instance.guiDanmakuCraftingID, world, x, y, z);
			}
			else
			{
				player.openGui(THKaguyaCore.instance, THKaguyaCore.instance.guiDanmakuCraftingLaserID, world, x, y, z);
			}
			return true;
		}
	}
	

    
	@Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata)
    {
        return iconArray[metadata];
    }
    
	@Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
    	iconArray[0] = iconRegister.registerIcon(texture[0]);
    	iconArray[1] = iconRegister.registerIcon(texture[1]);
    }
    
    /**
     * 壊したときにドロップするブロックのダメージ値を返す
     */
    public int damageDropped(int damage)
    {
        return damage;
    }
}
