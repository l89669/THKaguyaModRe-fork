package thKaguyaMod.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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

public class BlockDanmakuCraftingTable
  extends Block
{
  public static final String[] texture = { "thkaguyamod:danmaku_crafting_table", "thkaguyamod:danmaku_crafting_table2" };
  private IIcon[] iconArray = new IIcon[2];
  
  public BlockDanmakuCraftingTable(Material material)
  {
    super(material);
    
    setCreativeTab(CreativeTabs.tabDecorations);
    setBlockTextureName("thkaguyamod:danmaku_crafting_table");
    setHardness(1.5F);
    setResistance(1.0F);
    setStepSound(Block.soundTypeWood);
    setLightLevel(1.0F);
  }
  
  @SideOnly(Side.CLIENT)
  public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
  {
    list.add(new ItemStack(THKaguyaBlocks.danmaku_crafting_table, 1, 0));
    list.add(new ItemStack(THKaguyaBlocks.danmaku_crafting_table, 1, 1));
  }
  
  public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int size, float disX, float disY, float disZ)
  {
    if (world.isRemote) {
      return true;
    }
    if (world.getBlockMetadata(x, y, z) == 0) {
      player.openGui(THKaguyaCore.instance, 0, world, x, y, z);
    } else {
      player.openGui(THKaguyaCore.instance, 1, world, x, y, z);
    }
    return true;
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int side, int metadata)
  {
    return this.iconArray[metadata];
  }
  
  @SideOnly(Side.CLIENT)
  public void registerBlockIcons(IIconRegister iconRegister)
  {
    this.iconArray[0] = iconRegister.registerIcon(texture[0]);
    this.iconArray[1] = iconRegister.registerIcon(texture[1]);
  }
  
  public int damageDropped(int damage)
  {
    return damage;
  }
}
