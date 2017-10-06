package thKaguyaMod.item;

import static thKaguyaMod.DanmakuConstants.*;
import static thKaguyaMod.THShotLib.*;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.ShotData;
import thKaguyaMod.THShotLib;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
// 変更１

/** 蓬莱の玉の枝 */
public class ItemHouraiJeweledBranch extends ItemBow
{
	
	public static final String houraiJeweledBranchIconName[] =
	{
		"houraiEda", "houraiEda_4","houraiEda_3", "houraiEda_2", "houraiEda_1", "houraiEda_0"
	};
	
	@SideOnly(Side.CLIENT)
    private IIcon[] icon;
	
	public ItemHouraiJeweledBranch()
	{
		super();
		this.setTextureName("thkaguyamod:houraiEda");//テクスチャの指定
		setMaxDamage(301);//耐久値だが、ここではチャージの具合を表すものとして使用している。
		setHasSubtypes(true);
		setNoRepair();//修理不可
	}
	
    /**
     * Returns the icon index of the stack given as argument.
     */
	@Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconIndex(ItemStack itemStack)
    {
    	return this.getIconFromDamage(getCharge(itemStack));
        //return this.getIconFromDamage(par1ItemStack.getItemDamage());
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	//ダメージ値によってアイテムアイコンを変える
    public IIcon getIconFromDamage(int damage)
    {
    	int i = 5;
    	if(damage <= 0)
    	{
    		i = 0;
    	}
    	else if(damage <= 50)
    	{
    		i = 1;
    	}
    	else if(damage <= 100)
    	{
    		i = 2;
    	}
    	else if(damage <= 150)
    	{
    		i = 3;
    	}
    	else if(damage <= 200)
    	{
    		i = 4;
    	}
        return this.icon[i];
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        this.icon = new IIcon[houraiJeweledBranchIconName.length];

        for (int i = 0; i < houraiJeweledBranchIconName.length; ++i)
        {
            this.icon[i] = iconRegister.registerIcon("thkaguyamod:" + houraiJeweledBranchIconName[i]);
        }
    }
	
	@Override
    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
    {
		return this.getIconFromDamage(getCharge(stack));
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
		player.setItemInUse(itemStack, getMaxItemUseDuration(itemStack));
		
		return itemStack;
	}
       
	//右クリックを終了したときに呼び出されるメソッド
	@Override
	public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int usedTime)
	{
		int shotNum;
		shotNum = 32 + getMaxItemUseDuration(itemStack)-usedTime;//発射される弾数を代入。　最低32発。　getMaxItemUseDuration(itemstack)-usedTimeは右クリック継続時間で変化うする部分

		if(shotNum > 72)
		{
			shotNum = 72;//最大でも72発まで
		}
		shotNum /= 3;
		
		//if(itemStack.getItemDamageForDisplay() <= 250)//受けているダメージが249より小さいなら使用可能　つまり耐久値が50/300より大きいなら使用できる
    	if(getCharge(itemStack) <= 200)
		{
			ShotData shot = ShotData.shot(FORM_PEARL, RAINBOW, 0.3F, 5.0F, 1, 80);
			if(player.isSneaking())//スニーク状態なら
			{
				//弾幕を発射。　単純な全方位ではなく、弾速の違う３つをセットに全方位に放つ
				for(int i = 0; i < shotNum; i+=3)
				{
					//shot = ShotData.shot(FORM_PEARL, RAINBOW, 0.3F, 5.0F, 1, 80);
					//Vec3 angle = THShotLib.getVectorFromRotation(over, player.getLookVec(), (float)i * 3F);
					THShotLib.createCircleShot(player, player, THShotLib.pos_Living(player), 
							angle(player.rotationYaw, player.rotationPitch), 0.3D + (double)i * 0.2D, 2.0D, 0.03D, gravity_Zero(), 
							shot, shotNum, 0.2D, i * 3F);
				}

			}
			else//スニーク状態でないなら
			{	
				/*THShotLib.createWideShot(player, player, THShotLib.pos_Living(player), 
						THShotLib.angle(player.rotationYaw, player.rotationPitch), 0.3D, 2.0D, 0.03D, gravity_Zero(), 
						shot, shotNum, 30F, 0.2D);*/
				Vec3 angle = angle(player.rotationYaw, player.rotationPitch);
				Vec3 pos = pos(player.posX + angle.xCoord * 0.5D, THShotLib.getPosYFromEye(player) + angle.yCoord * 0.5D, player.posZ + angle.zCoord * 0.5D);
				THShotLib.createRingShot(player, player, pos, angle, 0F, 9999, 0.6D, 2.0D, 0.03D, gravity_Zero(), shot, shotNum, 0.0D/*MathHelper.cos(90F / 180F * (float)Math.PI) * 0.7D*/, 15F, itemRand.nextFloat() * 360F);
				shot = ShotData.shot(FORM_PEARL, RAINBOW, 0.3F, 5.0F, 1, 80);
				THShotLib.createRingShot(player, player, pos, angle, 0F, 9999, 0.6D, 2.0D, 0.03D, gravity_Zero(), shot, shotNum / 2, 0.0D/*MathHelper.cos(60F / 180F * (float)Math.PI) * 0.7D*/, 10F, itemRand.nextFloat() * 360F);
				shot = ShotData.shot(FORM_PEARL, RAINBOW, 0.3F, 5.0F, 1, 80);
				THShotLib.createRingShot(player, player, pos, angle, 0F, 9999, 0.6D, 2.0D, 0.03D, gravity_Zero(), shot, shotNum / 3, 0.0D/*MathHelper.cos(30F / 180F * (float)Math.PI) * 0.7D*/, 5F, itemRand.nextFloat() * 360F);
			}
			
			world.playSoundAtEntity(player, "random.orb", 0.5F, 0.4F / (itemRand.nextFloat() * 4F + 0.8F));//音を出す
			
			addCharge(itemStack, -50);
			//itemStack.damageItem(50, player);//耐久を50/300減らす　連射は6回までということになる
		}
	}
	
	//アイテムを使ったときのアクションを指定
	@Override
	public EnumAction getItemUseAction(ItemStack itemStack)
	{
		return EnumAction.block;//右クリック時は剣のガードアクション
	}

	//インベントリにある限り常時呼び出されるメソッド
	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int i, boolean flag)
	{
		//耐久が減っていたら徐々に回復
		/*if(itemStack.isItemDamaged() && entity.ticksExisted % 20 == 0)
		{
			if(entity instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer)entity;
				itemStack.damageItem(-20, player);
			}
		}*/
		
		if(getCharge(itemStack) > 0 && entity.ticksExisted % 50 == 0)
		{
			addCharge(itemStack, 50);
			if(getCharge(itemStack) < 0)
			{
				setCharge(itemStack, 0);
			}
		}
	}
	
	private void addCharge(ItemStack itemStack, int charge)
	{
		//NBTを取得
		NBTTagCompound nbt = itemStack.getTagCompound();
		
		//NBTがnullなら何もしない
		if(nbt == null)
		{
			nbt = new NBTTagCompound();
	    	itemStack.setTagCompound(nbt);
			nbt.setShort("Charge", (short)-charge);
		}
		else
		{
			if(nbt.getShort("Charge") >= 0)
			{
				nbt.setShort("Charge", (short)(nbt.getShort("Charge") - charge));
			}
		}
	}
	
	private void setCharge(ItemStack itemStack, int charge)
	{
		//NBTを取得
		NBTTagCompound nbt = itemStack.getTagCompound();
		
		//NBTがnullなら何もしない
		if(nbt == null)
		{
			nbt = new NBTTagCompound();
	    	itemStack.setTagCompound(nbt);
			nbt.setShort("Charge", (short)charge);
		}
		else
		{
			if(nbt.getShort("Charge") >= 0)
			{
				nbt.setShort("Charge", (short)(charge));
			}
		}
	}
	
	private int getCharge(ItemStack itemStack)
	{
		//NBTを取得
		NBTTagCompound nbt = itemStack.getTagCompound();
		if(nbt == null)
		{
			return 0;
		}
		return nbt.getShort("Charge");
	}
	
	//アイテム名の下に情報を付加する
    /*@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean bool)
	{
		super.addInformation(itemStack, player, list, bool);
		list.add("charge : " + getCharge(itemStack));
	}*/

	//アイテムを発光させる。 trueなら発光
	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack itemStack, int pass)
	{   
		return true;
	}
	
	//エンチャント不可
	@Override
	public int getItemEnchantability()
	{
		return 0;
	}
	
	//Forgeの追加メソッド　エンチャントブックの使用を許可するか
	@Override
	public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2)
    {
        return false;
    }
	
}