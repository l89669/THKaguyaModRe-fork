package thKaguyaMod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thKaguyaMod.init.THKaguyaItems;

public class ItemHisyaku extends Item 
{
    //舟幽霊の柄杓
	//Shift+右クリックでモード変更ができる
	//空のアイコンのときは無限に水源を除去できる
	//水のアイコンのときは水源を有限回設置可能
	//何もブロックがないところで使うと足元が水源になる
	//クリエイティブではモード変更ができない
	//0が設置モード
	//1が除去モード
	protected final int FILL = 0;
	protected final int EMPTY = 1;

    public ItemHisyaku(int subID)
    {
    	super();
        this.setTextureName("thkaguyamod:hisyaku_0");//テクスチャの指定
        if(subID == EMPTY)
        {
        	this.setTextureName("thkaguyamod:hisyaku_1");//テクスチャの指定
        }
        maxStackSize = 1;//最大スタック数
    	setMaxDamage(60);//耐久値
    	setCreativeTab(CreativeTabs.tabMisc);//クリエイティブのその他タブに登録
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
    	int mode = FILL;//itemStack.itemID % 2;//itemStack.getItemDamage() % 2;
    	if(itemStack.getItem() == THKaguyaItems.ship_ghost_dipper_empty)
    	{
    		mode = EMPTY;
    	}
    	if(player.isSneaking())
    	{
    		//モード切り替えでアイテムIDを切り替える処理（現状できない）
    		if(mode == FILL)
    		{
    			return new ItemStack(THKaguyaItems.ship_ghost_dipper_empty, 1, itemStack.getItemDamage());
    		}
    		else
    		{
    			return new ItemStack(THKaguyaItems.ship_ghost_dipper_fill, 1, itemStack.getItemDamage());
    		}
    	}
    	
    	float f = 1.0F;
        double d = player.prevPosX + (player.posX - player.prevPosX) * (double)f;
        double d1 = (player.prevPosY + (player.posY - player.prevPosY) * (double)f + 1.6200000000000001D) - (double)player.yOffset;
        double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double)f;
    	int i = 0, j = 0,k = 0;
        boolean flag = mode == 1;
    	
        MovingObjectPosition movingobjectposition = getMovingObjectPositionFromPlayer(world, player, flag);
        
        if(mode == EMPTY)
        {
        	if (movingobjectposition != null)
        	{
        		i = movingobjectposition.blockX;
				j = movingobjectposition.blockY;
        		k = movingobjectposition.blockZ;
	            //if (world.getBlockMaterial(i, j, k) == Material.water && world.getBlockMetadata(i, j, k) == 0)
        		if (world.getBlock(i, j, k) == Blocks.water && world.getBlockMetadata(i, j, k) == 0)
	        	{
	                //world.setBlockWithNotify(i, j, k, 0);
	        		//world.setBlockAndMetadataWithNotify(i, j, k, 0, 0, 0);
	        		//world.func_94571_i(i, j, k);
	        		world.setBlockToAir(i, j, k);
	            }
	        }
		}
        
        else
        {
        	int hit = 1;
            if (movingobjectposition == null)
            {
            	i = (int)(player.posX - 0.5D);
            	j = (int)(player.posY - player.yOffset + 0.5D);
            	k = (int)(player.posZ - 0.5D);
            }
            else
            {
            	i = movingobjectposition.blockX;
				j = movingobjectposition.blockY;
        		k = movingobjectposition.blockZ;
	            
        		hit = movingobjectposition.sideHit;
        		 
            	if (movingobjectposition.sideHit == 0)
	            {
	                j--;
	            }

	            if (movingobjectposition.sideHit == 1)
	            {
	                j++;
	            }

	            if (movingobjectposition.sideHit == 2)
	            {
	                k--;
	            }

	            if (movingobjectposition.sideHit == 3)
	            {
	                k++;
	            }

	            if (movingobjectposition.sideHit == 4)
	            {
	                i--;
	            }

	            if (movingobjectposition.sideHit == 5)
	            {
	                i++;
	            }
            }
            	

            //if (world.isAirBlock(i, j, k) || !world.getBlockMaterial(i, j, k).isSolid())
            if (world.isAirBlock(i, j, k) || /*!world.getBlock(i,  j,  k) ==*/ Blocks.water.isBlockSolid(world, i, j, k, hit) || world.getBlock(i, j, k) == Blocks.water)
            {
                if (world.provider.isHellWorld)
                {
                    world.playSoundEffect(d + 0.5D, d1 + 0.5D, d2 + 0.5D, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);

                    for (int l = 0; l < 8; l++)
                    {
                        world.spawnParticle("largesmoke", (double)i + Math.random(), (double)j + Math.random(), (double)k + Math.random(), 0.0D, 0.0D, 0.0D);
                    }
                }
                else
                {                    
                	/*if (!world.isRemote)
                	{
                		world.func_147480_a(i, j, k, true);
                		
                		
                	}*/
                    //world.setBlockAndMetadataWithNotify(i, j, k, 8, 0, 0);//水を設置
                	//world.setBlock(i, j, k, 8, 0, 3);
                	//world.setBlock(p_147449_1_, p_147449_2_, p_147449_3_, p_147449_4_)
                	world.setBlock(i, j, k, Blocks.water);
                	//world.setBlockMetadataWithNotify(i, j, k, 0, 0);//水を設置
                	itemStack.damageItem(2, player);
                }

            }
        }
		return itemStack;
    }
}
