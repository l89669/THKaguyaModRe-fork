package thKaguyaMod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thKaguyaMod.entity.effect.EntityAjaRedStoneEffect;
import thKaguyaMod.entity.shot.EntityTHShot;
import thKaguyaMod.entity.shot.ISpecialShot;
import thKaguyaMod.registry.SpecialShotRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/** エイジャの赤石 
 *  光を溜めてアンデッド特攻のレーザーを発射する
 */
public class ItemAjaRedStone extends Item implements ISpecialShot
{
	/** アンデッドだけを燃やすレーザーの特殊弾ID */
	public static final int SPECIAL_AJA_REDSTONE = 351;
	
	/** エイジャの赤石 */
	public ItemAjaRedStone()
	{
		super();
		this.setTextureName("thkaguyamod:AjaRedStone");//テクスチャの指定
		this.maxStackSize = 1;//最大スタック数
		this.setCreativeTab(CreativeTabs.tabCombat);//クリエイティブタブの武器に設定
		
		SpecialShotRegistry.registerSpecialShot(ItemAjaRedStone.class, SPECIAL_AJA_REDSTONE);
	}

	/** 右クリックを押した瞬間の処理
	 *  @param itemStack : 右クリックを押したItemStack
	 *  @param world     : ワールド
	 *  @param player    : 右クリックを押したプレイヤー
	 *  @return 右クリックを押したItemStackを返す
	 */
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
		//アイテムの使用継続時間を記憶させる
    	player.setItemInUse(itemStack, getMaxItemUseDuration(itemStack));
    	
    	//エイジャの赤石の光吸収エフェクトを出現させる
    	EntityAjaRedStoneEffect ajaRedStoneEffect = new EntityAjaRedStoneEffect( world, player);
    	
    	if(!world.isRemote)
    	{
    		world.spawnEntityInWorld(ajaRedStoneEffect);
    	}

    	
    	return itemStack;
    }
    
	@Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 72000;
    }
	
	/** アイテムを使ったときのアクションを指定 */
	@Override
	public EnumAction getItemUseAction(ItemStack itemStack)
    {
        return EnumAction.block;//右クリック時は剣のガードアクション
    }
	
	/** アイテムを発光させるか */
	@Override
    @SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack itemStack)
	{   
		return true;//発光させる
    }
	
	/** Forgeの追加メソッド　エンチャントブックの使用を許可するか */
	@Override
	public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2)
    {
        return false;//許可しない
    }

//===================================================================================
//	特殊弾定義
//===================================================================================
	@Override
	public void specialShot_move(World world, int id, EntityTHShot shot) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public boolean specialShot_hitBlock(World world, int id, EntityTHShot shot,
			MovingObjectPosition movingObjectPosition) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean specialShot_hitEntity(World world, int id,
			EntityTHShot shot, Entity entity_Hit) {
		//指定したダメージ分の魔法ダメージを与える
		if(entity_Hit instanceof EntityLivingBase)
		{
			EntityLivingBase living = (EntityLivingBase)entity_Hit;
			if(living.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD)//アンデッドなら
			{
				//通常の2倍のダメージを与える
				living.setFire((int)shot.shotDamage);
				shot.damageRate *= 2.0F;
				world.playSoundEffect(shot.posX, shot.posY, shot.posZ, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
				for (int l = 0; l < 8; l++)
            	{
               		world.spawnParticle("largesmoke", shot.posX + Math.random(), shot.posY + Math.random(), shot.posZ + Math.random(), 0.0D, 0.0D, 0.0D);
            	}
			}
		}
		return false;
	}
}