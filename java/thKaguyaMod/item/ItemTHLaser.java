package thKaguyaMod.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.LaserData;
import thKaguyaMod.THShotLib;
import thKaguyaMod.init.THKaguyaConfig;
import thKaguyaMod.init.THKaguyaItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTHLaser extends Item
{
	//単発レーザー
	
	//登録した英語名のアイテム名（？）
	public static final String laserNames[] =
    {
        "middle", "short", "long"
    };
	//アイコンの名前。smallShot.pngを反映させるなら、"smallShot"で対応する。
	public static final String laserIconName[] =
	{
		"MiddleLaser", "ShortLaser", "LongLaser"
	};

	//各弾の弾速。
	public static final float speed[] =
	{
		0.6F, 0.6F, 0.5F, 0.3F, 0.6F, 0.6F, 0.6F, 0.65F, 0.3F, 0.5F,
	};

	//各弾IDの形状
	public static final int form[] =
	{
		THShotLib.SMALL[0]    , THShotLib.TINY[0]     , THShotLib.MEDIUM[0], THShotLib.BIG[0]  , 
		THShotLib.STAR[0]     , THShotLib.SMALLSTAR[0], THShotLib.CIRCLE[0], THShotLib.SCALE[0],
		THShotLib.BUTTERFLY[0], THShotLib.LIGHT[0]
	};
	
	public static final String danmakuForm[] =
	{
		"Point", "Random", "Sector", "Around", "Sphere", "未定義"
	};
	
	@SideOnly(Side.CLIENT)
    private IIcon[] icon;
	
	public ItemTHLaser()
	{
		super();
		setHasSubtypes(true);
		setMaxDamage(0);
		setCreativeTab(CreativeTabs.tabMaterials);//クリエイティブの素材タブに登録
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack)
    {
        int i = MathHelper.clamp_int(itemStack.getItemDamage(), 0, 15);
        return super.getUnlocalizedName() + "." + laserNames[i];
    }
	
	//ダメージ値によってアイテムアイコンを変える
	@Override
	@SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage)
    {
        int i = MathHelper.clamp_int(damage, 0, laserNames.length);
        return this.icon[i];
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        this.icon = new IIcon[laserIconName.length];

        for (int i = 0; i < laserIconName.length; ++i)
        {
            this.icon[i] = iconRegister.registerIcon("thkaguyamod:shot/" + laserIconName[i]);
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
		int type = itemStack.getItemDamage();
		int type2 = form[type] / 8;//IDの互換用
		int color = /*form[type] + */itemRand.nextInt(7);
		int sellectbleColor[] = {7, 0, 2, 0, 1, 4, 5, 7, 7, 4, 2, 3, 5, 4, 6, 7};//１６色の染料を８色に対応させるための配列
		ItemStack colorItem = player.inventory.mainInventory[player.inventory.currentItem + 1];//右横に持っている相手っ無を取得
		if(player.inventory.currentItem < 8 && colorItem != null)//右横にアイテムがあるなら
    	{
    		//if(colorItem.itemID == Item.dyePowder.itemID)//そのアイテムが染料なら
    		if(colorItem.getItem() == Item.getItemById(351))//そのアイテムが染料なら
    		{
    			//染料のダメージ値を取得
    			color = /*form[type] +*/ sellectbleColor[player.inventory.mainInventory[player.inventory.currentItem + 1].getItemDamage()];
    		}
    	}
		float length = 5.0F;
		if(type == 1)
		{
			length = 2.0F;
		}
		else if(type == 2)
		{
			length = 8.0F;
			//length = 20D;
		}
		
		int way = 1;
		double shotSpeed = speed[type];
		int danmakuForm = 0;
		int deadTime = 60;
		float damage = 5.0F;
		//double gravity = 0.0D;
		int i;
		
		NBTTagCompound nbt = itemStack.getTagCompound();
		if(nbt != null)
		{
			way = nbt.getShort("Number");
			danmakuForm = nbt.getByte("DanmakuForm");
			shotSpeed = speed[type] * (1.0D + (double)nbt.getByte("Speed") * 0.03D);
			//special = nbt.getByte("special");
			//gravity = (double)nbt.getByte("gravity") * -0.003D;
			
			int setColor = (int)nbt.getByte("Color");
			if(setColor != 9)
			{
				color = /*form[type] +*/ (int)nbt.getByte("Color");
			}
			//special = (int)nbt.getByte("Special");
    	}
		
		//configで設定した最高弾数より多いなら、最高弾数にする
		if(way > THKaguyaConfig.laserMaxNumber)
		{
			way = THKaguyaConfig.laserMaxNumber;
		}
		
		float angleXZ, angleY;
		float wide;
		Vec3 look = THShotLib.angle(player.rotationYaw, player.rotationPitch);
		
		LaserData laser = LaserData.laser(color, 0.1F, length, damage);
		
		switch(danmakuForm)
		{
			case 0:
			double shotSpeed2 = shotSpeed;
			
				for(i = 1; i <= way; i++)
				{
					THShotLib.createLaserA(player, THShotLib.pos_Living(player), look, shotSpeed2, laser);
					shotSpeed2 = shotSpeed / (double)way * (double)i;
				}
				break;
			case 1:
				wide = 30F;
				if(player.isSneaking())
				{
					wide *= 0.5F;
				}
				THShotLib.createRandomRingLaser(player, player, THShotLib.pos_Living(player),
						look, shotSpeed, shotSpeed, 0.0D, THShotLib.gravity_Zero(),
						laser, way, 0.2D, wide);
				break;
			case 2:
				wide = way * 5F;
				if(player.isSneaking())
				{
					wide = wide * 0.5F;
				}
				THShotLib.createWideLaserA(player, player, THShotLib.pos_Living(player), player.getLookVec(), shotSpeed, shotSpeed, 0.0D, THShotLib.gravity_Zero(),
						laser, way, wide, 0.1D);
				break;
			case 3:
				THShotLib.createCircleLaserA(player, player, THShotLib.pos_Living(player), player.getLookVec(), shotSpeed, shotSpeed, 0.0D, THShotLib.gravity_Zero(),
						laser, way, 0.1D);
				break;
			case 4:
				THShotLib.createSphereLaserA(player, player, THShotLib.pos_Living(player), look, shotSpeed, shotSpeed, 0.0D, THShotLib.gravity_Zero(), laser, way, 0.2D, 0F);
				
				break;
			case 5:
				wide = 15F;
				if(player.isSneaking())
				{
					wide *= 0.5F;
				}
				THShotLib.createRingLaserA(player, player, THShotLib.pos_Living(player), look, shotSpeed, shotSpeed, 0.0D, THShotLib.gravity_Zero(),
						laser, way, 0.1D, wide, itemRand.nextFloat() * 360F);

				break;
			default:
				break;
		}

		if(!world.isRemote)
		{
			itemStack.stackSize --;
		}
		
		return itemStack;
	}

	@Override
	@SideOnly(Side.CLIENT)
	//クリエイトモードのアイテム欄に、ダメージ値の違うアイテムも表示できるようにする
	public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < laserNames.length; i++)
        {
            list.add(new ItemStack(THKaguyaItems.laser_item, 1, i));
        }
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
		//double gravity = 0.0D;
		//byte bound = 0;
		
		
		NBTTagCompound nbt = itemStack.getTagCompound();
		if(nbt != null)
		{
			shotNum = nbt.getShort("Number");
			form = nbt.getByte("DanmakuForm");
			shotSpeed = (float)speed[type] * (1.0F + (float)nbt.getByte("Speed") * 0.03F);
			color = nbt.getByte("Color");
			//gravity = (double)nbt.getByte("gravity") * 0.003D;
			//bound = (byte)(nbt.getByte("special") - thShotLib.BOUND01 + 1);
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
		
		list.add(StatCollector.translateToLocal("danmakuCrafting.damage") + " : 2.5");
		list.add(StatCollector.translateToLocal("danmakuCrafting.number") + " : " + number);
		list.add(StatCollector.translateToLocal("danmakuCrafting.form") + " : " + StatCollector.translateToLocal("thKaguya.danmakuForm." + form));
		list.add(StatCollector.translateToLocal("danmakuCrafting.speed") + " : " + shotSpeed);
		/*if(gravity != 0.0D)
		{
			list.add(StatCollector.translateToLocal("danmakuCrafting.gravity") + " : " + gravity);
		}
		else
		{
			list.add(StatCollector.translateToLocal("danmakuCrafting.gravity") + " : GFree");
		}*/
		list.add(StatCollector.translateToLocal("danmakuCrafting.color") + " : " + StatCollector.translateToLocal("thKaguya.color." + color));
		/*if(bound >= 1 && bound <= 3)
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
		//list.add("Special: " + special);*/
	}
	
}