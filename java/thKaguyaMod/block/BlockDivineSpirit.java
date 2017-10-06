/*   1:    */ package thKaguyaMod.block;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.relauncher.Side;
/*   4:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   5:    */ import java.util.List;
/*   6:    */ import net.minecraft.block.Block;
/*   7:    */ import net.minecraft.block.BlockContainer;
/*   8:    */ import net.minecraft.block.material.Material;
/*   9:    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*  10:    */ import net.minecraft.creativetab.CreativeTabs;
/*  11:    */ import net.minecraft.item.Item;
/*  12:    */ import net.minecraft.item.ItemStack;
/*  13:    */ import net.minecraft.tileentity.TileEntity;
/*  14:    */ import net.minecraft.util.AxisAlignedBB;
/*  15:    */ import net.minecraft.util.IIcon;
/*  16:    */ import net.minecraft.util.MathHelper;
/*  17:    */ import net.minecraft.world.World;
/*  18:    */ import thKaguyaMod.THKaguyaCore;
/*  19:    */ import thKaguyaMod.init.THKaguyaBlocks;
/*  20:    */ import thKaguyaMod.tileentity.TileEntityDivineSpirit;
/*  21:    */ 
/*  22:    */ public class BlockDivineSpirit
/*  23:    */   extends BlockContainer
/*  24:    */ {
/*  25: 26 */   public static final String[] texture = { "thkaguyamod:divineSpirit_Red", "thkaguyamod:divinespirit_Blue", "thkaguyamod:divineSpirit_Green", "thkaguyamod:divinespirit_yellow", "thkaguyamod:divinespirit_purple", "thkaguyamod:divinespirit_aqua", "thkaguyamod:divinespirit_orange", "thkaguyamod:divinespirit_white" };
/*  26: 35 */   private IIcon[] iconArray = new IIcon[8];
/*  27:    */   
/*  28:    */   public BlockDivineSpirit(Material material)
/*  29:    */   {
/*  30: 39 */     super(material);
/*  31:    */     
/*  32: 41 */     setCreativeTab(CreativeTabs.tabDecorations);
/*  33: 42 */     setBlockTextureName("thkaguyamod:divineSpirit_Blue");
/*  34: 43 */     setHardness(0.1F);
/*  35: 44 */     setResistance(2000.0F);
/*  36: 45 */     setStepSound(Block.soundTypeSnow);
/*  37: 46 */     setLightLevel(0.5333334F);
/*  38:    */   }
/*  39:    */   
/*  40:    */   public TileEntity createNewTileEntity(World world)
/*  41:    */   {
/*  42: 52 */     return new TileEntityDivineSpirit();
/*  43:    */   }
/*  44:    */   
/*  45:    */   public void getSubBlocks(Item item, CreativeTabs par2CreativeTabs, List par3List)
/*  46:    */   {
/*  47: 59 */     for (int i = 0; i < 8; i++) {
/*  48: 61 */       par3List.add(new ItemStack(THKaguyaBlocks.divine_spirit, 1, i));
/*  49:    */     }
/*  50:    */   }
/*  51:    */   
/*  52:    */   @SideOnly(Side.CLIENT)
/*  53:    */   public IIcon getIcon(int side, int metadata)
/*  54:    */   {
/*  55: 69 */     return this.iconArray[metadata];
/*  56:    */   }
/*  57:    */   
/*  58:    */   @SideOnly(Side.CLIENT)
/*  59:    */   public void registerBlockIcons(IIconRegister par1IconRegister)
/*  60:    */   {
/*  61: 77 */     for (int i = 0; i < 8; i++)
/*  62:    */     {
/*  63: 79 */       String iconName = texture[i];
/*  64: 80 */       this.iconArray[i] = par1IconRegister.registerIcon(iconName);
/*  65:    */     }
/*  66:    */   }
/*  67:    */   
/*  68:    */   @SideOnly(Side.CLIENT)
/*  69:    */   public IIcon getIconFromDamage(int damage)
/*  70:    */   {
/*  71: 88 */     int i = MathHelper.clamp_int(damage, 0, 6);
/*  72: 89 */     return this.iconArray[i];
/*  73:    */   }
/*  74:    */   
/*  75:    */   public int damageDropped(int damage)
/*  76:    */   {
/*  77: 95 */     return damage;
/*  78:    */   }
/*  79:    */   
/*  80:    */   public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
/*  81:    */   {
/*  82:100 */     return null;
/*  83:    */   }
/*  84:    */   
/*  85:    */   public boolean isOpaqueCube()
/*  86:    */   {
/*  87:105 */     return false;
/*  88:    */   }
/*  89:    */   
/*  90:    */   public boolean renderAsNormalBlock()
/*  91:    */   {
/*  92:115 */     return false;
/*  93:    */   }
/*  94:    */   
/*  95:    */   public int getRenderType()
/*  96:    */   {
/*  97:122 */     return THKaguyaCore.blockRenderId;
/*  98:    */   }
/*  99:    */   
/* 100:    */   public boolean isBlockReplaceable(World world, int x, int y, int z)
/* 101:    */   {
/* 102:127 */     return false;
/* 103:    */   }
/* 104:    */   
/* 105:    */   public boolean hasTileEntity(int metadata)
/* 106:    */   {
/* 107:132 */     return true;
/* 108:    */   }
/* 109:    */   
/* 110:    */   public TileEntity createNewTileEntity(World var1, int var2)
/* 111:    */   {
/* 112:137 */     TileEntityDivineSpirit tileEntityDivineSpirit = new TileEntityDivineSpirit();
/* 113:138 */     return tileEntityDivineSpirit;
/* 114:    */   }
/* 115:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.block.BlockDivineSpirit
 * JD-Core Version:    0.7.0.1
 */