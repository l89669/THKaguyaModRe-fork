package thKaguyaMod.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.item.EntitySilverKnife;
import thKaguyaMod.init.THKaguyaItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/** 咲夜の銀のナイフ　　投げてよし、切ってよし */
public class ItemSilverKnife extends Item
{
	
	
	private static final String knifeColorNames[] =
    {
        "blue", "red", "green", "white"
    };
	private static final String knifeIconName[] =
	{
		"SilverKnife_Blue", "SilverKnife_Red", "SilverKnife_Green", "SilverKnife_White"
	};
	
    @SideOnly(Side.CLIENT)
    private IIcon[] icon;
	
	public ItemSilverKnife()
	{
		super();
		setHasSubtypes(true);
		setMaxDamage(0);
		setCreativeTab(CreativeTabs.tabCombat);//クリエイティブの武器タブに登録
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack)
    {
        int i = MathHelper.clamp_int(itemStack.getItemDamage(), 0, 3);
        return super.getUnlocalizedName() + "." + knifeColorNames[i];
    }
	
	/**
	 * ダメージ値によってアイテムアイコンを変える
	 * @param damage : ダメージ値
	 * @return アイコン？
	 */
	@Override
	@SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage)
    {
        int i = MathHelper.clamp_int(damage, 0, 3);
        return this.icon[i];
    }
	
	/**
	 * アイコンを登録する
	 * @param iconRegister : アイコンのレジスタ？
	 */
	@Override
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        this.icon = new IIcon[knifeIconName.length];

        for (int i = 0; i < knifeIconName.length; ++i)
        {
            this.icon[i] = iconRegister.registerIcon("thkaguyamod:shot/" + knifeIconName[i]);
        }
    }
	
	/**
	 * Entityに当たったときの処理
	 * @param itemStack   : 攻撃したアイテム
	 * @param living_hit  : 攻撃を当てられたEntityLivingBase
	 * @param living_used : 攻撃を当てたEntityLivingBase
	 * @return 当たったならtrueを返す
	 */
	@Override
    public boolean hitEntity(ItemStack itemStack, EntityLivingBase living_hit, EntityLivingBase living_used)
    {
    	living_hit.attackEntityFrom(DamageSource.causeMobDamage(living_used), 3.0F);
    	//白ナイフなら消える
    	if(itemStack.getItemDamage() == 3)
    	{
    		itemStack.stackSize--;
    	}
    	return true;
    }
	
	/**
	 * 右クリックを終了したときに呼び出されるメソッド
	 * @param itemStack : 右クリックをしたアイテム
	 * @param world     : ワールド
	 * @param player    : 右クリックをしたプレイヤー
	 * @return 右クリックをしたアイテムを返す
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
    	double xVec,yVec,zVec;
    	double speed = 0.28D;
    	EntitySilverKnife silverKnife;

    	xVec = -Math.sin(player.rotationYaw / 180F * 3.141593F) * Math.cos(player.rotationPitch / 180F * 3.141593F) * speed;//X方向　水平方向
    	yVec = -Math.sin(player.rotationPitch / 180F * 3.141593F) * speed;//Y方向　上下
    	zVec =  Math.cos(player.rotationYaw / 180F * 3.141593F) * Math.cos(player.rotationPitch / 180F * 3.141593F) * speed;//Z方向　水平方向
    	Vec3 angle = THShotLib.angle(player.rotationYaw, player.rotationPitch);
    	Vec3 pos = THShotLib.pos_Distance(THShotLib.pos_Living(player), angle, speed);
    	
    	silverKnife = new EntitySilverKnife(world, player, pos, angle, speed, itemStack.getItemDamage());
       	
    	world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 4F + 0.8F));//音を出す
       	
    	if(!world.isRemote)
       	{
			world.spawnEntityInWorld(silverKnife);//銀のナイフを出現させる
			itemStack.stackSize--;//一つ消費
       	}
    	
    	player.swingItem();//投げる動作をさせる
        
    	return itemStack;
    }
	
	/**
	 * クリエイトモードのアイテム欄に、ダメージ値の違うアイテムも表示できるようにする
	 * @param item        : アイテム
	 * @param creativeTab : 収めるクリエイティブ用のタブ
	 * @param list        : 収めるクリエイティブ用のタブ内のアイテムリスト
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < 3; i++)
        {
            list.add(new ItemStack(THKaguyaItems.silver_knife, 1, i));
        }
    }
	
	
}