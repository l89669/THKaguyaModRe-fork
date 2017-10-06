package thKaguyaMod.item;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/** 仏の御石の鉢
    石の複合ツール 剣、ツルハシ、スコップ、斧の役割を持つ
	ただし、剣のガード、スコップの雪回収等の細かな機能はない
	石だから、金やダイヤの採取も不可
 */
public class ItemBuddhaStoneBowl extends ItemTool
{
	
	/** 仏の御石の鉢 */
	public ItemBuddhaStoneBowl(ToolMaterial toolMaterial)
	{
		super(4.0F, toolMaterial,null);//ダメージ、素材、？
		this.setTextureName("thkaguyamod:hotokeHachi");
		setMaxDamage(toolMaterial.getMaxUses());
	}

	/** アイテムを発光させる。
	 * 
	 *  @return trueなら発光する
	 */
	@SideOnly(Side.CLIENT)
	@Override
   	public boolean hasEffect(ItemStack itemStack, int pass)
   {   
		return true;
    }
	
	/** ブロックの壊し安さを設定。　大きいほど壊れやすい。*/
	@Override
	public float getDigSpeed(ItemStack itemStack, Block block, int metadata)
	{
		return 4F;
	}

	/** Entityに当たったときの処理 */
	@Override
	public boolean hitEntity(ItemStack itemStack, EntityLivingBase living_hit, EntityLivingBase living_used)
	{
		return true;
	}

	/** ブロックを破壊したときの処理 */
	@Override
	public boolean onBlockDestroyed(ItemStack itemstack, World world, Block block, int blockX, int blockY, int blockZ, EntityLivingBase living)
	{
		return true;
	}
	
	/** アイテムを剣のような描画にするか */
	@Override
	public boolean isFull3D()
	{
		return false;
	}
	
	/** 採取可能なブロックかどうかを返す */
	@Override
    public boolean func_150897_b(Block block)
    {
        return block == Blocks.obsidian ? this.toolMaterial.getHarvestLevel() == 3 : (block != Blocks.diamond_block && block != Blocks.diamond_ore ? (block != Blocks.emerald_ore && block != Blocks.emerald_block ? (block != Blocks.gold_block && block != Blocks.gold_ore ? (block != Blocks.iron_block && block != Blocks.iron_ore ? (block != Blocks.lapis_block && block != Blocks.lapis_ore ? (block != Blocks.redstone_ore && block != Blocks.lit_redstone_ore ? (block.getMaterial() == Material.rock ? true : (block.getMaterial() == Material.iron ? true : block.getMaterial() == Material.anvil)) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2);
    }
	
	/** エンチャントできない。　できるとかなりのチートアイテム化する。*/
	@Override
	public int getItemEnchantability()
    {
        return 0;
    }
	
	/** Forgeの追加メソッド　エンチャントブックの使用を許可するか */
	@Override
	public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2)
    {
        return false;
    }

}