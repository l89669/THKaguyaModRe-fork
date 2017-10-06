package thKaguyaMod.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thKaguyaMod.entity.item.EntitySakuyaWatch;

/** 咲夜の懐中時計 */
public class ItemSakuyaWatch extends Item
{

	/** 咲夜の懐中時計のコンストラクタ */
	public ItemSakuyaWatch()
	{
		super();
		this.setTextureName("thkaguyamod:sakuyaWatch");	//テクスチャの指定
		maxStackSize = 1;								//最大スタック
		setCreativeTab(CreativeTabs.tabMisc);			//クリエイティブのその他タブに登録
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
        /*ArrowNockEvent event = new ArrowNockEvent(player, itemStack);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
        {
            return event.result;
        }*/
		
		// スニークではモード切り替えができる
    	if(player.isSneaking() && player.ticksExisted % 2 == 0)
    	{
    		if(this.getDamage(itemStack) == 0)
    		{
    			this.setDamage(itemStack, 1);
    		}
    		else
    		{
    			this.setDamage(itemStack, 0);
    		}
    		return itemStack;
    	}
    	
    	// クリエイティブの場合
		if(player.capabilities.isCreativeMode)
		{
			onEaten(itemStack, world, player);
		}
		else
		{
			/*if(player.hurtTime > 0)
			{
				THShotLib.createShot(player, THShotLib.pos_Entity(player), player.getLookVec(), 0.5D, ShotData.shot(THShotLib.FORM_AMULET, THShotLib.RED));
				itemStack.stackSize--;
				return itemStack;
			}*/
			
		}
		
		int mode;

		
		player.setItemInUse(itemStack, getMaxItemUseDuration(itemStack));
       	return itemStack;
    }
	
	/**
	 * 右クリックを終了したときの処理
	 * @param itemStack
	 * @param world
	 * @param player
	 * @param usedTime
	 */
	@Override
	//public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int usedTime)
	public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player)
	{
		//int usedTime = 24;
		int usedTime = getMaxItemUseDuration(itemStack) - player.getItemInUseCount();
		
        /*ArrowLooseEvent event = new ArrowLooseEvent(player, itemStack, usedTime);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
        {
            return;
        }
        usedTime = event.charge;*/
		int mode = EntitySakuyaWatch.TIME_HALF_WITH_LIMIT;
        
		//空腹でないか、クリエイティブなら使用可能
    	if(player.getFoodStats().getFoodLevel() > 0 || player.capabilities.isCreativeMode)
    	{
    		// クリエイティブの場合
    		if(player.capabilities.isCreativeMode)
    		{
	    		mode = EntitySakuyaWatch.TIME_STOP;
	    		if(this.getDamage(itemStack) == 0)//スニークなら低速モード　　通常は停止モード
	    		{
	    			mode = EntitySakuyaWatch.TIME_HALF;
	    		}
    		}
    		// クリエイティブではない場合で、1.2秒以上右クリックしている場合
    		else if(usedTime >= getMaxItemUseDuration(itemStack))
    		{
	    		mode = EntitySakuyaWatch.TIME_STOP_WITH_LIMIT;
	    		if(this.getDamage(itemStack) == 0)//スニークなら低速モード　　通常は停止モード
	    		{
	    			mode = EntitySakuyaWatch.TIME_HALF_WITH_LIMIT;
	    		}
    		}
    		// 上記の場合以外無効
    		else
    		{
	    		if(player.isSneaking())//スニークなら低速モード　　通常は停止モード
	    		{
	    			if(this.getDamage(itemStack) == 0)
	    			{
	    				this.setDamage(itemStack, 1);
	    			}
	    			else
	    			{
	    				this.setDamage(itemStack, 0);
	    			}

	    		}
    			return itemStack;
    		}
    		
    		//周囲のEntityを取得
    		List<?> list = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.addCoord(0.0D, 0.0D, 0.0D).expand(20.0D, 20.0D, 20.0D));
    		for(int k = 0; k < list.size(); k++)
    		{
    			Entity entity = (Entity)list.get(k);
    			// すでに周囲に咲夜の懐中時計が出現している場合
    			if(entity instanceof EntitySakuyaWatch)
    			{
    				// 咲夜の懐中時計の使用は失敗する
    				return itemStack;
    			}
    		}

   			world.playSoundAtEntity(player, "random.click", 0.5F, 0.4F / (itemRand.nextFloat() * 4F + 0.8F));

    		EntitySakuyaWatch entityPrivateSquare = new EntitySakuyaWatch(world, player, mode);
       		if(!world.isRemote)
       		{
         		world.spawnEntityInWorld(entityPrivateSquare);//時間停止空間を生み出す
       		}
       		if(! player.capabilities.isCreativeMode)
       		{
       			itemStack.stackSize--;
       		}
    	}
    	return itemStack;
	}
	
	/*@Override
    public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player)
    {
        return itemStack;
    }*/
    
    @Override
    public int getMaxItemUseDuration(ItemStack itemStack)
    {
    	if(this.getDamage(itemStack) == 0)
    	{
    		return 20;
    	}
    	else
    	{
    		return 48;
    	}
    }
	
	//アイテムを使ったときのアクションを指定
	@Override
	public EnumAction getItemUseAction(ItemStack itemStack)
	{
		return EnumAction.bow;
	}
	
	/** アイテムを発光させるか返す
	 * @param itemStack 	: アイテムスタック
	 * @param pass			: ？
	 * @return trueなら発光する
	 */
	@Override
	public boolean hasEffect(ItemStack itemStack, int pass)
    {   
    	if(this.getDamage(itemStack) == 0)
    	{
    		return false;
    	}
    	else
    	{
    		return true;
    	}
    }
}