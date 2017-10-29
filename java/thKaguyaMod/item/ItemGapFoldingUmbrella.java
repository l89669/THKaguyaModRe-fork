package thKaguyaMod.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thKaguyaMod.entity.item.EntitySukima;
import thKaguyaMod.init.THKaguyaConfig;
import thKaguyaMod.init.THKaguyaItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/** 隙間の折りたたみ傘 */
public class ItemGapFoldingUmbrella extends Item
{
	
	private String colorName[] = { "black", "red", "green", "brown", "blue", "purple", "cyan", "lightGray", 
									"gray","pink", "lime", "yellow", "lightBlue", "magenta", "orange", "white"};

	@SideOnly(Side.CLIENT)
    private IIcon icon_base;
	@SideOnly(Side.CLIENT)
    private IIcon icon_ribbon;
	
	/** アイテム 隙間の折りたたみ傘のコンスタラクタ */
	public ItemGapFoldingUmbrella()
	{
		super();
		this.setTextureName( "thkaguyamod:gapFoldingUmbrella_base" );//テクスチャの指定
		setCreativeTab( CreativeTabs.tabMisc );//クリエイティブのその他タブに登録
		this.setMaxDamage(30);
		this.maxStackSize = 1;
	}
	
	/**
	 * 重ねアイコンを設定する
	 * @return [true : 重ねアイコンを有効にする]　[false : 重ねアイコンを無効にする]
	 */
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses()
    {
        return true;//重ねアイコンに設定
    }
	
	//ダメージ値によってアイテムアイコンを変える
	@Override
	@SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage)
    {
        return this.icon_base;
    }
	
	//アイテムアイコンを登録する
	@Override
	@SideOnly(Side.CLIENT)
    public void registerIcons( IIconRegister iconRegister )
    {
		this.icon_base		= iconRegister.registerIcon( "thkaguyamod:GapFoldingUmbrella_base"	);
		this.icon_ribbon	= iconRegister.registerIcon( "thkaguyamod:GapFoldingUmbrella_ribbon"	);
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
    	if( layer == 0 )
    	{
    		return icon_base;
    	}
    	else
    	{
    		return icon_ribbon;
    	}
    }
	
	/**
	 * アイコンの色合いを変える
	 * @param itemStack	: アイテムスタック
	 * @param pass			: レイヤー（0:ベースレイヤー、1:上部レイヤー)
	 * @return 色合いRGBを１６進数の形で返す（0xFFFFFF）
	 */
	@Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack( ItemStack itemStack, int pass )
    {
		int colorR[] = { 30,179, 59, 81, 37,123, 40,128, 51,216, 65,222,102,211,237,240,179};
		int colorG[] = { 27, 49, 81, 48, 49, 47,118,128, 51,129,203,207,133, 84,136,240, 49};
		int colorB[] = { 27, 44, 26, 26,146,190,151,128, 51,152, 51, 42,219,205, 68,240, 44};
		
		if( pass == 0 )
		{
			return 0xFFFFFF;
		}
		
		int				color	= 0;
		NBTTagCompound	nbt			= itemStack.getTagCompound();
		
		if( nbt != null )
		{
			color = (int)nbt.getByte("GapColor");
			
			if( color >= 16 )
			{
				color = 0;
				nbt.setByte( "GapColor", (byte)0 );
			}
		}
		else
		{
			color = 0;
			nbt = new NBTTagCompound();
			nbt.setByte( "GapColor", (byte)0 );
			itemStack.setTagCompound( nbt );
		}
		
        return colorR[color] * 65536 + colorG[color] * 256 + colorB[color];
    }
	
	protected float getSetAngle(EntityPlayer player, double setX, double setZ)
	{
    	float rotationYaw = player.rotationYaw;
    	rotationYaw = (float)Math.atan2(setX - player.posX, setZ - player.posZ) / 3.141593F * 180F;
    	//rotationYaw = rotationYaw % 180F;
    	if(rotationYaw < 0F)
    	{
    		rotationYaw += 360F;
    	}
    	if(rotationYaw % 90F < 45F)
    	{
    		rotationYaw = rotationYaw - (rotationYaw % 45F);
    	}
    	else
    	{
    		rotationYaw = rotationYaw - (rotationYaw % 45F) + 45F;
    	}
    	return -rotationYaw;
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
    	double setX = player.posX - Math.sin(player.rotationYaw   / 180F * (float)Math.PI) * Math.cos(player.rotationPitch / 180F * (float)Math.PI) * 2.0D;
    	double setY = player.posY - Math.sin(player.rotationPitch / 180F * (float)Math.PI) * 2.0D + player.getEyeHeight() / 2.0D;
    	double setZ = player.posZ + Math.cos(player.rotationYaw   / 180F * (float)Math.PI) * Math.cos(player.rotationPitch / 180F * (float)Math.PI)* 2.0D;
    	//boolean lost = true;
    	int color = 0;
    	
    	if(player.isSneaking())
    	{
    		NBTTagCompound	nbt			= itemStack.getTagCompound();
    		
    		if( nbt != null )
    		{
    			color = (int)nbt.getByte("GapColor");
    			color++;
    			
    			if( color >= 16 )
    			{
    				color = 0;
    			}
    		}
    		else
    		{
    			color = 0;
    			nbt = new NBTTagCompound();
    			itemStack.setTagCompound( nbt );
    		}
    		nbt.setByte( "GapColor", (byte)color );
    		
    		/*if(itemStack.getItemDamage() < 15)
    		{
    			itemStack.setItemDamage(itemStack.getItemDamage() + 1);
    		}
    		else
    		{
    			itemStack.setItemDamage(0);
    		}*/
    		return itemStack;
    	}
    	
    	//if(!world.isBlockNormalCube((int)setX, (int)setY, (int)setZ))
    	if(!world.isBlockNormalCubeDefault((int)setX, (int)setY, (int)setZ, true))
    	{
    		EntitySukima entitySukima;

    		if(THKaguyaConfig.useDefaultGapSE)
    		{
    			world.playSoundAtEntity(player, "mob.endermen.portal", 0.5F, 0.4F / (itemRand.nextFloat() * 4F + 0.8F));
    		}
    		else
    		{
    			world.playSoundAtEntity(player,  THKaguyaConfig.sukimaWarpSE, 1.0F, 1.0F);
    		}
    		//color = itemStack.getItemDamage();
    		
    		NBTTagCompound	nbt			= itemStack.getTagCompound();
    		
    		if( nbt != null )
    		{
    			color = (int)nbt.getByte("GapColor");
    			
    			if( color >= 16 )
    			{
    				color = 0;
    			}
    		}
    		else
    		{
    			
    			color = 0;
    			nbt = new NBTTagCompound();
    			itemStack.setTagCompound( nbt );
        		nbt.setByte( "Color", (byte)color );
    		}
    		
    		entitySukima = new EntitySukima(world, player, setX, setY, setZ, 32 + color);
    		//world.playSoundAtEntity(player, "THItems.yukariPortal_spawn", 0.5F, 0.4F / (itemRand.nextFloat() * 4F + 0.8F));
    		if(!world.isRemote)
    		{
    			world.spawnEntityInWorld(entitySukima);//スキマを生み出す    			
    		}
    		
    		itemStack.damageItem( 1, player );
    		
			if( itemStack.getItemDamage() >= this.getMaxDamage() )
			{
				int originalDamage = 0;
				
	    		if( nbt != null )
	    		{
	    			originalDamage = nbt.getInteger("OriginalDamage");
	    			nbt.removeTag("GapColor");
	    			nbt.removeTag("OriginalDamage");
	    		}
	    		
	    		itemStack = new ItemStack( THKaguyaItems.gap );
	    		itemStack.setItemDamage( originalDamage );
			}
    	}
    	
    	
        return itemStack;
    }
	
	/** アイテムを発光させるか返す
	 * @param itemStack 	: アイテムスタック
	 * @param pass			: ？
	 * @return trueなら発光する
	 */
	@Override
	public boolean hasEffect(ItemStack itemStack, int pass)
    {   
		return true;
    }
}