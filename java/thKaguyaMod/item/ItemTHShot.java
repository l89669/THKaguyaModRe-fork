package thKaguyaMod.item;

import static thKaguyaMod.DanmakuConstants.*;

import java.util.List;
import java.util.Random;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.ShotData;
import thKaguyaMod.THKaguyaLib;
import thKaguyaMod.THShotLib;
import thKaguyaMod.init.THKaguyaConfig;
import thKaguyaMod.init.THKaguyaItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/** 弾アイテム */
public class ItemTHShot extends Item
{
	//単発ショット　弾幕作成の素でもある
	
	//登録した英語名のアイテム名（？）
	public static final String shotNames[] =
    {
        "smallShot"	, "tinyShot"		, "mediumShot"		, "bigShot"	,
        "starShot"		, "smallStarShot"	, "circleShot"		, "scaleShot"	,
        "butterflyShot", "lightShot"		, "knifeShot"		, "heartShot"	,
        "kunaiShot"	, "talismanShot"	, "bigLightShot"	, "riceShot"	,
        "ovalShot", "crystalShot", "arrowShot"
    };
	//アイコンの名前。smallShot.pngを反映させるなら、"smallShot"で対応する。
	public static final String shotIconName[] =
	{
		"SmallShot", "TinyShot", "MediumShot", "BigShot",
		"StarShot", "SmallStarShot", "CircleShot", "ScaleShot",
		"ButterflyShot", "LightShot", "KnifeShot", "HeartShot",
		"KunaiShot", "TalismanShot", "BigLightShot", "RiceShot",
		"OvalShot", "CrystalShot", "ArrowShot"
	};
	
	//弾アイテムとEntityで弾の番号が異なるため、その変換用
	public static final int shotTypeTrans[] =
	{
		 0,  1,  2, 30,
		 9,  8,  4,  6,
		 7,  5, 28, 12,
		13, 14, 15, 10,
		16, 11, 18
	};
	
	//各弾の弾速。
	public static final float speed[] =
	{
		0.50F, 0.50F, 0.40F, 0.25F,
		0.50F, 0.50F, 0.50F, 0.55F,
		0.30F, 0.50F, 0.65F, 0.40F,
		0.60F, 0.50F, 0.40F, 0.50F,
		0.40F, 0.45F, 0.50F
	};

	//各弾IDの形状
	public static final int form[] =
	{
		THShotLib.FORM_SMALL    	, THShotLib.FORM_TINY     , THShotLib.FORM_MEDIUM	, THShotLib.FORM_BIG  , 
		THShotLib.FORM_STAR     	, THShotLib.FORM_SMALLSTAR, THShotLib.FORM_CIRCLE	, THShotLib.FORM_SCALE,
		THShotLib.FORM_BUTTERFLY	, THShotLib.FORM_LIGHT	, THShotLib.FORM_KNIFE	, THShotLib.FORM_HEART,
		THShotLib.FORM_KUNAI		, THShotLib.FORM_TALISMAN	, THShotLib.FORM_BIGLIGHT , THShotLib.FORM_RICE,
		THShotLib.FORM_OVAL		, THShotLib.FORM_CRYSTAL, THShotLib.FORM_ARROW
	};
	
	public static final int SMALL		=  0;
	public static final int TINY		=  1;
	public static final int MEDIUM		=  2;
	public static final int BIG			=  3;
	public static final int STAR		=  4;
	public static final int SMALLSTAR	=  5;
	public static final int CIRCLE		=  6;
	public static final int SCALE		=  7;
	public static final int BUTTERFLY	=  8;
	public static final int LIGHT		=  9;
	public static final int KNIFE		= 10;
	public static final int HEART		= 11;
	public static final int KUNAI		= 12;
	public static final int TALISMAN	= 13;
	public static final int BIGLIGHT	= 14;
	public static final int RICE		= 15;
	public static final int OVAL		= 16;
	public static final int CRYSTAL		= 17;
	public static final int ARROW		= 18;
	
	public static final String danmakuForm[] =
	{
		"Point", "Random", "Sector", "Around", "Sphere", "Ring", "未定義"
	};
	
	public static final int sellectbleColor[] = {7, 0, 2, 0, 1, 4, 5, 7, 7, 4, 2, 3, 5, 4, 6, 7};//１６色の染料を８色に対応させるための配列
	
	//protected int colorR[] = { 224,   0,   0, 224, 224,   0, 255, 255};
	//protected int colorG[] = {   0,   0, 224, 224,   0, 224, 128, 255};
	//protected int colorB[] = {   0, 224,   0,   0, 224, 224,   0, 255};
	
	protected int colorR[] = { 255,   0,   0, 255, 255,   0, 255, 255};
	protected int colorG[] = {   0,   0, 255, 255,   0, 255, 128, 255};
	protected int colorB[] = {   0, 255,   0,   0, 255, 255,   0, 255};
	
	@SideOnly(Side.CLIENT)
    private IIcon[] icon;
	@SideOnly(Side.CLIENT)
    private IIcon[] icon2;
	
	public ItemTHShot()
	{
		super();
		setHasSubtypes(true);							// ダメージ値で別のアイテムを表す
		setMaxDamage(0);								// ダメージ値を持たない
		setCreativeTab(CreativeTabs.tabMaterials);	// クリエイティブの素材タブに登録
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack)
    {
        int i = MathHelper.clamp_int(itemStack.getItemDamage(), 0, shotNames.length);
        return super.getUnlocalizedName() + "." + shotNames[i];
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
        int i = MathHelper.clamp_int(damage, 0, shotNames.length);
        return this.icon[i];
    }
	
	@SideOnly(Side.CLIENT)
	/**
	 * ダメージ値によってアイテムアイコン（レイヤー２）を変える
	 * @param damage	: ダメージ値
	 * @return アイコン（レイヤー２）を返す
	 */
    public IIcon getIconFromDamage2(int damage)
    {
        int i = MathHelper.clamp_int(damage, 0, shotNames.length);
        return this.icon2[i];
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
    		return getIconFromDamage( damage );
    	}
    	else if( layer == 1 )
    	{
    		return getIconFromDamage2( damage );
    	}
    	return icon[0];
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
		if( pass == 1 )
		{
			return 0xFFFFFF;
		}
		
		int				color	= 0;
		NBTTagCompound	nbt			= itemStack.getTagCompound();
		
		if( nbt != null )
		{
			color = (int)nbt.getByte("Color");
			
			if( color >= 8 )
			{
				color = 7;
				//color = itemRand.nextInt( 8 );
			}
			else if( color >= 9 )
			{
				//color = itemRand.nextInt( 8 );
			}
		}
		else
		{
			color = 7;
		}
		
        return colorR[color] * 65536 + colorG[color] * 256 + colorB[color];
    }
	
	
	@Override
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        this.icon = new IIcon[shotIconName.length];
        this.icon2 = new IIcon[shotIconName.length];

        for (int i = 0; i < shotIconName.length; ++i)
        {
            this.icon[i]	= iconRegister.registerIcon( "thkaguyamod:shot/" + shotIconName[i]		);
            this.icon2[i]	= iconRegister.registerIcon( "thkaguyamod:shot/" + shotIconName[i] + "_2"	);
        }
    }
	
	public static void shootDanmaku(ItemStack itemStack, EntityLivingBase living, boolean isSlowMode, Vec3 pos, Vec3 angle, double distance)
	{
		Random rand = new Random();
		int type = itemStack.getItemDamage();
		int type2 = form[type];//IDの互換用
		int color = /*form[type] +*/ rand.nextInt(7);
		int special = 0;
		boolean isInfinity = false;
		
		/*ItemStack colorItem = player.inventory.mainInventory[player.inventory.currentItem + 1];//右横に持っているアイテムを取得
		
		if(player.inventory.currentItem < 8)//常時選択可能な１～９のアイテムなら
		{
			if(colorItem != null)//右横にアイテムがあるなら
	    	{
	    		//if(colorItem.itemID == Item.dyePowder.itemID)//そのアイテムが染料なら
	    		if(colorItem.getItem() == Items.dye)//そのアイテムが染料なら
	    		{
	    			//染料のダメージ値を取得
	    			color = sellectbleColor[player.inventory.mainInventory[player.inventory.currentItem + 1].getItemDamage()];
	    		}
	    	}
		}*/
		
		int way = 1;
		double shotSpeed = speed[type];
		int danmakuForm = 0;
		double gravity = 0.0D;
		int i;
		
		NBTTagCompound nbt = itemStack.getTagCompound();
		if(nbt != null)
		{
			way = nbt.getShort("Number");
			danmakuForm = nbt.getByte("DanmakuForm");
			shotSpeed = speed[type] * (1.0D + (double)nbt.getByte("Speed") * 0.03D);
			special = nbt.getInteger("Special");
			gravity = (double)nbt.getByte("Gravity") * -0.003D;
			
			color = (int)nbt.getByte("Color");
			isInfinity = nbt.getBoolean("Infinity");
			/*if(setColor != 9)
			{
				color = form[type] + (int)nbt.getByte("Color");
			}*/
			//special = (int)nbt.getByte("Special");
    	}
		if(living instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)living;
	        if(player.capabilities.isCreativeMode)
	        {
		        if(nbt == null)
		        {
		        	nbt = new NBTTagCompound();
		        	itemStack.setTagCompound(nbt);
		        	nbt.setShort("Number", (short)1);
		        }
		        nbt.setBoolean("Infinity", true);
		        //itemStack.setTagCompound(nbt);
	        }
		}
		
		//configで設定した最高弾数より多いなら、最高弾数にする
		if(way > THKaguyaConfig.shotMaxNumber)
		{
			way = THKaguyaConfig.shotMaxNumber;
		}
		
		float wide;
		float damagePlus = 0.0F;
				
		if( living instanceof EntityPlayer )
		{
			damagePlus += THKaguyaLib.getPlayerPower( (EntityPlayer)living ) / 400.0F;
		}
		
		ShotData shot = ShotData.shot(type2, color, THShotLib.SIZE[type2], THShotLib.DAMAGE[type2] + damagePlus, 0, 80, special);
		
		switch(danmakuForm)
		{
			case 0://一点
				double shotSpeed2 = shotSpeed;
				pos.xCoord = pos.xCoord + angle.xCoord * distance;
				pos.yCoord = pos.yCoord + angle.yCoord * distance;
				pos.zCoord = pos.zCoord + angle.zCoord * distance;
			
				for(i = 1; i <= way; i++)
				{	
					THShotLib.createShot(living, living, pos, angle, 0F, shotSpeed2, shotSpeed2, 0.0D, THShotLib.gravity(gravity),
							shot);
					shotSpeed2 = shotSpeed / (double)way * (double)i;
				}
				break;
			case 1://前方ランダム
				wide = 120F;
				if(isSlowMode)
				{
					wide = 60F;
				}
				THShotLib.createRandomRingShot(living, living, pos, 
						angle, 0F, 9999, shotSpeed, shotSpeed, 0.0D, THShotLib.gravity(0.0D, gravity, 0.0D), shot, way, distance, wide);
				break;
			case 2://前方n-way
				wide = way * 3F;
				if(isSlowMode)
				{
					wide = wide * 0.5F;
				}
				THShotLib.createWideShot(living, living, pos, angle,shotSpeed, shotSpeed, 0.0D, THShotLib.gravity(0.0D, gravity, 0.0D),
						shot, way, wide, distance);
				break;
			case 3://全方位弾
				THShotLib.createCircleShot(living, living, pos, angle, shotSpeed, shotSpeed, 0.0D, THShotLib.gravity(0.0D, gravity, 0.0D),
						shot, way, distance, 0F);
				break;
			case 4://球状
				THShotLib.createSphereShot(living, living, pos, angle,
						0F, THShotLib.rotate_Default(), 0F, 99999, shotSpeed, shotSpeed, 0.0D, THShotLib.gravity_Zero(),
						shot, way, distance, 0F);
				break;
			case 5://リング
				wide = 15F;
				if(isSlowMode)
				{
					wide *= 0.5F;
				}
				THShotLib.createRingShot(living, living, pos, 
						angle, 0F, 9999, shotSpeed, shotSpeed, 0.0D, THShotLib.gravity(0.0D, gravity, 0.0D), 
						shot, way, distance, wide, rand.nextFloat() * 360F);
			default:
				break;
		}
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
		/*int color = rand.nextInt(7);
		
		ItemStack colorItem = player.inventory.mainInventory[player.inventory.currentItem + 1];//右横に持っているアイテムを取得
		
		if(player.inventory.currentItem < 8)//常時選択可能な１～９のアイテムなら
		{
			if(colorItem != null)//右横にアイテムがあるなら
	    	{
	    		//if(colorItem.itemID == Item.dyePowder.itemID)//そのアイテムが染料なら
	    		if(colorItem.getItem() == Items.dye)//そのアイテムが染料なら
	    		{
	    			//染料のダメージ値を取得
	    			color = sellectbleColor[player.inventory.mainInventory[player.inventory.currentItem + 1].getItemDamage()];
	    		}
	    	}
		}*/
		
		shootDanmaku(itemStack, player, player.isSneaking(), THShotLib.pos_Living(player), player.getLookVec(), THShotLib.SIZE[shotTypeTrans[itemStack.getItemDamage()]]);

		
		THShotLib.playShotSound(player);
		
		if(!world.isRemote)
		{
			boolean isInfinity = false;
			
			if(itemStack.getTagCompound() != null)
			{
				isInfinity = itemStack.getTagCompound().getBoolean("Infinity");
			}
			
			if(!isInfinity)
			{
				itemStack.stackSize--;
			}
		}
		
		return itemStack;
	}

	//クリエイトモードのアイテム欄に、ダメージ値の違うアイテムも表示できるようにする
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < shotNames.length; i++)
        {
            list.add(new ItemStack(THKaguyaItems.shot_item, 1, i));
        }
    }
	
	//アイテムを発光させる。 trueなら発光
	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack itemStack, int pass)
	{   
		NBTTagCompound nbt = itemStack.getTagCompound();
		if(nbt != null)
		{
			if(nbt.getBoolean("Infinity") == true)
			{
				return true;
			}
		}
		return false;
	}
	
    @Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean bool)
	{
		super.addInformation(itemStack, player, list, bool);
		int type = itemStack.getItemDamage();
		short shotNum = 1;
		byte form = 0;
		float shotSpeed = (float)speed[type];
		int color = 8;
		double gravity = 0.0D;
		byte bound = 0;
		boolean isInfinity = false;
		
		
		NBTTagCompound nbt = itemStack.getTagCompound();
		if(nbt != null)
		{
			shotNum = nbt.getShort("Number");
			form = nbt.getByte("DanmakuForm");
			shotSpeed = (float)speed[type] * (1.0F + (float)nbt.getByte("Speed") * 0.03F);
			color = nbt.getByte("Color");
			gravity = (double)nbt.getByte("Gravity") * 0.003D;
			bound = (byte)(nbt.getInteger("Special") - BOUND01 + 1);
			isInfinity = nbt.getBoolean("Infinity");
		}
		
		if(bound < 0)
		{
			bound = 0;
		}
		
		if(form >= danmakuForm.length)
		{
			form = (byte)(danmakuForm.length - 1);
		}
		
		String number = "" + shotNum;
		
		/*String special = "G Free";
		if((int)nbt.getByte("special") == thShotLib.FALL01)
		{
			special = "Fall";
		}*/
		
		//shotSpeed
		
		float damageRate = ( THKaguyaLib.getPlayerPower(player) / 400.0F );
		
		list.add(StatCollector.translateToLocal("danmakuCrafting.damage") + " : " + ((float)THShotLib.DAMAGE[shotTypeTrans[type]] / 2F) + " + " + damageRate );
		list.add(StatCollector.translateToLocal("danmakuCrafting.number") + " : " + number);
		list.add(StatCollector.translateToLocal("danmakuCrafting.form") + " : " + StatCollector.translateToLocal("thKaguya.danmakuForm." + form));
		list.add(StatCollector.translateToLocal("danmakuCrafting.speed") + " : " + shotSpeed);
		if(gravity != 0.0D)
		{
			list.add(StatCollector.translateToLocal("danmakuCrafting.gravity") + " : " + gravity);
		}
		else
		{
			list.add(StatCollector.translateToLocal("danmakuCrafting.gravity") + " : GFree");
		}
		list.add(StatCollector.translateToLocal("danmakuCrafting.color") + " : " + StatCollector.translateToLocal("thKaguya.color." + color));
		if(bound >= 1 && bound <= 3)
		{
			list.add(StatCollector.translateToLocal("danmakuCrafting.bound") + " : " + bound);
		}
		else if(bound == 4)
		{
			list.add(StatCollector.translateToLocal("danmakuCrafting.bound") + " : Infinity");
		}
		else
		{
			list.add(StatCollector.translateToLocal("danmakuCrafting.bound") + " : 0");
		}
		if(isInfinity)
		{
			list.add(StatCollector.translateToLocal("Infinity"));
		}
		//list.add("Special: " + special);
	}
	
}