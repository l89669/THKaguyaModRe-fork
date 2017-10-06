package thKaguyaMod.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemIbukihyou extends ItemFood
{
	
	//飲むと一時的に攻撃力が上昇するが、
	
	public ItemIbukihyou(int foodLevel, boolean flag)
	{
		super(foodLevel, flag);
		this.setTextureName("thkaguyamod:ibukihyou");//テクスチャの指定
		setMaxDamage(11);//耐久値
		maxStackSize = 1;//スタック不可
		setAlwaysEdible();//いつでも飲める
		setNoRepair();//修理不可
	}
	
	//食べたときの処理
	@Override
	protected void onFoodEaten(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (!world.isRemote)
    	{
    		//	 				PotionEffect(ポーションのタイプ,持続時間（秒）*20（20は定数？）,レベル（0がレベル１、1がレベル２）)
        	player.addPotionEffect(new PotionEffect( 5, 15 * 20, 0));//攻撃力アップ
    		player.addPotionEffect(new PotionEffect( 9, 20 * 20, 2));//ゆらゆら
    	}
    }
	
	//食べ物を食べたときに呼び出されるメソッド
	@Override
	public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player)
    {
    	if( player.isSneaking() )
    	{
    		return itemStack;
    	}
		
    	if(itemStack.getItemDamageForDisplay() < 6)
    	{
    		itemStack.damageItem(5, player);
        	player.getFoodStats().addStats(0, 0.0F);//満腹度回復、隠し満腹度みたい
        	world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
        	this.onFoodEaten(itemStack, world, player);
    	}
        return itemStack;
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


    	int blockX = 0, blockY = 0, blockZ = 0;
    	
        MovingObjectPosition movingobjectposition = getMovingObjectPositionFromPlayer(world, player, true);
        
        if (movingobjectposition != null)
        {
        	blockX = movingobjectposition.blockX;
			blockY = movingobjectposition.blockY;
        	blockZ = movingobjectposition.blockZ;
        	//if (world.getBlockMaterial(blockX, blockY, blockZ) == Material.water && world.getBlockMetadata(blockX, blockY, blockZ) == 0)
        	//if (world.getBlockgetBlockMaterial(blockX, blockY, blockZ) == Material.water && world.getBlockMetadata(blockX, blockY, blockZ) == 0)
        	if (world.getBlock(blockX, blockY, blockZ) == Blocks.water && world.getBlockMetadata(blockX, blockY, blockZ) == 0)
	        {
            	//if( player.isSneaking() )
            	{
                	//super.onItemRightClick(itemStack, world, player);
    	        	//world.setBlockToAir(blockX, blockY, blockZ);
            		if( itemStack.isItemDamaged() )
            		{
            			player.swingItem();
            			itemStack.setItemDamage( itemStack.getItemDamage() - 1 );
            		}
            	}
	        }
            else
            {
            	if(itemStack.getItemDamageForDisplay() < 6)
            	{
            		super.onItemRightClick(itemStack, world, player);
            	}
            }
		}
        else
        {
        	if(itemStack.getItemDamageForDisplay() < 6)
        	{
        		super.onItemRightClick(itemStack, world, player);
        	}
        }
		return itemStack;
    }
	
	//飲み干すまでにかかる時間
	@Override
    public int getMaxItemUseDuration(ItemStack itemStack)
    {
        return 16;
    }
	
	//右クリックしている間の動作
	@Override
    public EnumAction getItemUseAction(ItemStack itemStack)
    {
    	if(itemStack.getItemDamageForDisplay() >= 6)
    	{
    		return EnumAction.none;
    	}
        return EnumAction.drink;
    }
	
}