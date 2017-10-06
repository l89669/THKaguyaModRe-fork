package thKaguyaMod.item;

import static thKaguyaMod.DanmakuConstants.*;
import static thKaguyaMod.THShotLib.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.ShotData;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.shot.EntityTHShot;
import thKaguyaMod.entity.shot.ISpecialShot;
import thKaguyaMod.registry.SpecialShotRegistry;

/**
 * 人魂灯
 * 幽霊を呼び出す
 */
public class ItemSoulTorch extends Item implements ISpecialShot
{

	public static final int SPECIAL_SOULTORCH = 352;
	
	public ItemSoulTorch()
	{
		super();
		this.setTextureName("thkaguyamod:soulTorch");//テクスチャの指定
		maxStackSize = 16;//最大スタック
		setCreativeTab(CreativeTabs.tabCombat);//クリエイティブの武器タブに登録
		
		SpecialShotRegistry.registerSpecialShot(ItemSoulTorch.class, SPECIAL_SOULTORCH);
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
		if(player.capabilities.isCreativeMode)
		{
			onEaten(itemStack, world, player);
		}
		
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
		/*int x, y, z;
		x = (int)player.posX - 20;
		y = (int)player.posY - 20;
		z = (int)player.posZ - 20;
		for(x = (int)player.posX - 20; x < (int)player.posX + 20; x++)
		{
			for(y = (int)player.posY - 20; y < (int)player.posY + 20; y++)
			{
				for(z = (int)player.posZ - 20; z < (int)player.posZ + 20; z++)
				{
					if(itemRand.nextInt(3000) == 0)
					{
						double distance = player.getDistance((double)x, (double)y, (double)z);
						if(distance > 15 && distance < 20)
						{
							EntityTHPhantom phantom = new EntityTHPhantom(world);
							phantom.setPosition(x, y, z);
							if(!world.isRemote)
							{
								world.spawnEntityInWorld(phantom);
							}
						}

					}
				}
			}
		}*/
		ShotData shot = ShotData.shot(FORM_PHANTOM, PURPLE, 0.4F, 7.0F, 0, 480, SPECIAL_SOULTORCH);
		Vec3 angle = THShotLib.angle(player.rotationYaw, -90F);
		THShotLib.createRingShot(player, player, pos_Living(player), angle, 0F, 9999, 0.01D, 0.4D, 0.03D, gravity_Zero(), shot, 3, 0.3D, 30F, 0F);
		
		THShotLib.playShotSound(player);
		
   		if(! player.capabilities.isCreativeMode)
   		{
   			itemStack.stackSize--;
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
        return 12;
    }
	
	//アイテムを使ったときのアクションを指定
	@Override
	public EnumAction getItemUseAction(ItemStack itemStack)
	{
		return EnumAction.bow;
	}

	@Override
	public void specialShot_move(World world, int id, EntityTHShot shot) {
		shot.homing(16.0F);
		
	}

	@Override
	public boolean specialShot_hitBlock(World world, int id, EntityTHShot shot,
			MovingObjectPosition movingObjectPosition) {
		return true;//ブロック衝突では消えない
	}

	@Override
	public boolean specialShot_hitEntity(World world, int id,
			EntityTHShot shot, Entity entity_Hit) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
}