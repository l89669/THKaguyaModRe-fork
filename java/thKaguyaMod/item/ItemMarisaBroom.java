package thKaguyaMod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import thKaguyaMod.entity.item.EntityMarisaBroom;

public class ItemMarisaBroom extends Item
{
	
	/*
	 * 魔理沙の魔法の箒。
	 * そのまま武器としても使え、空をとぶこともできる
	 */
	
	public ItemMarisaBroom()
	{
		super();
		this.setTextureName("thkaguyamod:magicBroom");//テクスチャの指定
		maxStackSize = 1;//最大スタック数
		setCreativeTab(CreativeTabs.tabCombat);//クリエイティブの武器タブに登録
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
    	EntityMarisaBroom broom;
    	//使用者の上方向に発射
    	broom = new EntityMarisaBroom(world, player.posX, player.posY, player.posZ);
       	world.playSoundAtEntity(player, "random.drr", 0.5F, 0.4F / (itemRand.nextFloat() * 4F + 0.8F));//音を出す
       	if(!world.isRemote)
       	{
         	world.spawnEntityInWorld(broom);//魔理沙の箒を出現させる
       		itemStack.stackSize--;//スタックから消滅させる
       	}
       	if(!player.isRiding() && !player.isSneaking())
       	{
       		player.mountEntity(broom);
       		broom.rotationYaw = player.rotationYaw;
       		broom.rotationPitch = player.rotationPitch;
       	}
    		
       	return itemStack;
    }
	
	//Entityに当たったときの処理
	@Override
    public boolean hitEntity(ItemStack itemStack, EntityLivingBase living_hit, EntityLivingBase living_used)
    {
    	living_hit.attackEntityFrom(DamageSource.causeMobDamage(living_used), 2.0F);
    	return true;
    }
	
	//右クリック時の行動を設定
	@Override
    public EnumAction getItemUseAction(ItemStack itemStack)
    {
        return EnumAction.block;//弓の構えをする
    }
	
	//右クリック時のカウントの最大値を指定
	@Override
	public int getMaxItemUseDuration(ItemStack itemStack)
    {
        return 72000;
    }
	
	@Override
    public boolean isFull3D()
    {
        return true;
    }
	
}