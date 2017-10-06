package thKaguyaMod.item;

import static thKaguyaMod.DanmakuConstants.*;
import static thKaguyaMod.THShotLib.*;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import thKaguyaMod.ShotData;
import thKaguyaMod.THShotLib;
import thKaguyaMod.init.THKaguyaItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemHomingAmulet extends Item
{
	//霊夢のホーミングアミュレット
	public static final String amuletTypeNames[] =
	    {
	        "homing", "diffusion"
	    };
	public static final String amuletIconName[] =
		{
			"thkaguyamod:homingAmulet", "thkaguyamod:diffusionAmulet"
		};
	
	@SideOnly(Side.CLIENT)
    private IIcon[] icon;
	
	public ItemHomingAmulet()
	{
		super();
		this.setTextureName("thkaguyamod:homingAmulet");//テクスチャの指定
		setHasSubtypes(true);
		setMaxDamage(0);
		setCreativeTab(CreativeTabs.tabCombat);//クリエイティブの武器タブに登録
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack)
    {
        int i = MathHelper.clamp_int(itemStack.getItemDamage(), 0, 15);
        return super.getUnlocalizedName() + "." + amuletTypeNames[i];
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	//ダメージ値によってアイテムアイコンを変える
    public IIcon getIconFromDamage(int damage)
    {
        int i = MathHelper.clamp_int(damage, 0, 2);
        return this.icon[i];
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        this.icon = new IIcon[amuletIconName.length];

        for (int i = 0; i < amuletIconName.length; ++i)
        {
            this.icon[i] = iconRegister.registerIcon(amuletIconName[i]);
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
    	if(itemStack.getItemDamage() == 0)
    	{
    		if(player.isSneaking())//低速モード
    		{
    			ShotData shot = ShotData.shot(FORM_AMULET, RED, 1.0F, 8.0F, 0, 90, HOMING01);
    			THShotLib.createWideShot(player, player, pos_Living(player), player.getLookVec(), 0.7D, 0.7D, 0.7D, gravity_Zero(), shot, 2, 20F, 0.5D, 0F);
	    	}
	    	else//高速モード
	    	{
	   			ShotData shot = ShotData.shot(FORM_AMULET, RED, 0.4F, 5.0F, 0, 90, HOMING01);
    			THShotLib.createWideShot(player, player, pos_Living(player), player.getLookVec(), 0.7D, 0.7D, 0.7D, gravity_Zero(), shot, 5, 100F, 0.5D, 0F);
	    	}
    	}
    	else
    	{
    		ShotData shot = ShotData.shot(FORM_AMULET, BLUE, 1.0F, 8.0F, 0, 16, DIFFUSION01);
			THShotLib.createWideShot(player, player, pos_Living(player), player.getLookVec(), 0.5D, 0.0D, -0.03D, gravity_Zero(), shot, 2, 60F, 0.5D, 0F);
    	}
    	world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 4F + 0.8F));//音を出す
    	if(!world.isRemote)
    	{
    		itemStack.stackSize--;//一つ消費
    	}
    	
        return itemStack;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	//クリエイトモードのアイテム欄に、ダメージ値の違うアイテムも表示できるようにする
	public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < 2; i++)
        {
            list.add(new ItemStack(THKaguyaItems.amulet, 1, i));
        }
    }
}