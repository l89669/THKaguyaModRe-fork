package thKaguyaMod.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import thKaguyaMod.THKaguyaCore;
import thKaguyaMod.init.THKaguyaBlocks;
import thKaguyaMod.tileentity.TileEntityDivineSpirit;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDivineSpirit extends BlockContainer
{
	//小神霊
	public static final String[] texture = {
		"thkaguyamod:divineSpirit_Red",
		"thkaguyamod:divinespirit_Blue",
		"thkaguyamod:divineSpirit_Green",
		"thkaguyamod:divinespirit_yellow",
		"thkaguyamod:divinespirit_purple",
		"thkaguyamod:divinespirit_aqua",
		"thkaguyamod:divinespirit_orange",
		"thkaguyamod:divinespirit_white"};
	private IIcon[] iconArray = new IIcon[8];
	
	public BlockDivineSpirit(Material material)
	{
		super(material);
		
		this.setCreativeTab(CreativeTabs.tabDecorations);//クリエイティブタブの選択
		this.setBlockTextureName("thkaguyamod:divineSpirit_Blue");
		this.setHardness(0.1F);//硬さ
		this.setResistance(2000.0F);//爆発耐性
		this.setStepSound(Block.soundTypeSnow);//ブロックの音
		this.setLightLevel(8F / 15F);
		
	}
	
    public TileEntity createNewTileEntity(World world)
    {
        return new TileEntityDivineSpirit();
    }
    
    //クリエイティブダブにメタデータの異なるアイテムも出現させる
    @Override
    public void getSubBlocks(Item item, CreativeTabs par2CreativeTabs, List par3List)
    {
    	for(int i = 0; i < 8; i++)
    	{
    		par3List.add(new ItemStack(THKaguyaBlocks.divine_spirit, 1, i));
    	}
    }
    
    @SideOnly(Side.CLIENT)

    public IIcon getIcon(int side, int metadata)
    {
        return iconArray[metadata];//(par2 & 3) == 1 ? this.iconArray[this.field_94394_cP][1] : ((par2 & 3) == 3 ? this.iconArray[this.field_94394_cP][3] : ((par2 & 3) == 2 ? this.iconArray[this.field_94394_cP][2] : this.iconArray[this.field_94394_cP][0]));
    }
    
    @SideOnly(Side.CLIENT)
    
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
    	String iconName;
    	for(int i = 0; i < 8; i++)
    	{
    		iconName = texture[i];
    		iconArray[i] = par1IconRegister.registerIcon(iconName);
    	}
    }
    
    @SideOnly(Side.CLIENT)
	//ダメージ値によってアイテムアイコンを変える
    public IIcon getIconFromDamage(int damage)
    {
        int i = MathHelper.clamp_int(damage, 0, 6);
        return this.iconArray[i];
    }
    
    //壊したときにドロップするブロックのダメージ値を返す
    public int damageDropped(int damage)
    {
        return damage;
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }
    
    public boolean isOpaqueCube()
    {
        return false;
    }
	
    
    /**
     * 通常と違う形のブロックならfalse
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }
   
    
    //描画時の描画タイプを返す
    public int getRenderType()
    {
        return THKaguyaCore.blockRenderId;
    }
	
    public boolean isBlockReplaceable(World world, int x, int y, int z)
    {
        return false;
    }
    
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
        TileEntityDivineSpirit tileEntityDivineSpirit = new TileEntityDivineSpirit();
        return tileEntityDivineSpirit;
	}
}