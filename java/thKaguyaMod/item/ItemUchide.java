package thKaguyaMod.item;

import java.util.List;
import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;
import thKaguyaMod.entity.shot.EntityTHShot;
import thKaguyaMod.init.THKaguyaItems;

/** 打ち出の小槌 */
public class ItemUchide extends Item
{
	
	public ItemUchide()
	{
		super();
		this.setTextureName("thkaguyamod:UchidenoKoduchi");//テクスチャの指定
		maxStackSize = 1;//最大スタック数
		this.setMaxDamage(100);//耐久値
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
		//player.setItemInUse(itemStack, getMaxItemUseDuration(itemStack));
		/*if( player.isSneaking() )
		{
			player.openGui(THKaguyaCore.instance, THKaguyaCore.instance.guiMiracleMalletID, world, (int)player.posX, (int)player.posY, (int)player.posZ);
			return itemStack;
		}
		
    	Vec3 look = player.getLookVec();
    	//始点を登録
    	Vec3 vec3d = Vec3.createVectorHelper(player.posX, player.posY + player.getEyeHeight(), player.posZ);
    	//終点を登録
    	Vec3 vec3d1 = Vec3.createVectorHelper(player.posX + look.xCoord * 3.0D, player.posY + look.yCoord * 3.0D, player.posZ + look.zCoord * 3.0D);
        //始点と終点からブロックとの衝突を取得
    	//MovingObjectPosition movingObjectPosition = world.rayTraceBlocks_do_do(vec3d, vec3d1, false, true);
    	MovingObjectPosition movingObjectPosition = world.func_147447_a(vec3d, vec3d1, false, true, true);
    	//始点を登録
    	vec3d = Vec3.createVectorHelper(player.posX, player.posY + player.getEyeHeight(), player.posZ);
    	//終点を登録
    	vec3d1 = Vec3.createVectorHelper(player.posX + look.xCoord * 3.0D, player.posY + look.yCoord * 3.0D, player.posZ + look.zCoord * 3.0D);
        //ブロックと衝突していたなら
    	if (movingObjectPosition != null)
        {
        	//終点を衝突した点に変更
        	vec3d1 = Vec3.createVectorHelper(movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
        }
    	EntityItem entityItem = null;

    	//このEntityから移動後までの線分に、指定分の範囲を追加した直方体と衝突するEntityのリストを取得
        List<?> list = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.addCoord(look.xCoord * 3.0D, look.yCoord * 3.0D, look.zCoord * 3.0D).expand(1.0D, 1.0D, 1.0D));
        double d = 0.0D;
        for (int j = 0; j < list.size(); j++)
        {
        	// 衝突リストから、i番目のEntityを取得
            Entity entity1 = (Entity)list.get(j);

    		if(entity1 instanceof EntityItem)
    		{
    			EntityItem entity1Item = (EntityItem)entity1;
        		float f2 = 0.3F;
            	AxisAlignedBB axisalignedbb = entity1Item.boundingBox.expand(f2, f2, f2);
            	MovingObjectPosition movingObjectPosition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);
        		//Entityとの衝突がないなら、このEntityはパスする
            	if (movingObjectPosition1 != null)
            	{
        			//始点からEntityに衝突した点までの距離を取得
            		double d1 = vec3d.distanceTo(movingObjectPosition1.hitVec);
        			//今までで一番近いEntityなら、Entityと距離を記憶する
            		if (d1 < d || d == 0.0D)
            		{		
                		entityItem = entity1Item;
                		d = d1;
            		}
            	}
            }
        }

    	//Entityに当たっていたなら
        if (entityItem != null)
        {
        	
            movingObjectPosition = new MovingObjectPosition(entityItem);
            if(entityItem.getEntityItem().getUnlocalizedName().equals("item.yinYangOrb"))
            {
            	if(!world.isRemote)
            		entityItem.setDead();
            	//entityItem.setEntityItemStack(new ItemStack(THKaguyaItems.bloodthirsty_yin_yang_orb));
            }
            else if(entityItem.getEntityItem().getUnlocalizedName().equals("item.alicedoll.bare"))
            {
            	entityItem.setEntityItemStack(new ItemStack(THKaguyaItems.cursedDecoyDoll));
            }
            else if(entityItem.getEntityItem().getUnlocalizedName().equals("item.oritatamigasa.bare"))
            {
            	entityItem.setEntityItemStack(new ItemStack(THKaguyaItems.gapFoldingUmbrella));
            }
            player.swingItem();
        }*/
		
    	if(player.getFoodStats().getFoodLevel() > 0)//空腹ゲージ２以上で使用可能
    	{

    		
    		boolean flag = true;
    		//周囲のEntityを取得
    		List list = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.addCoord(0.0D, 0.0D, 0.0D).expand(20.0D, 20.0D, 20.0D));
    		for(int k = 0; k < list.size(); k++)
    		{
    			Entity entity = (Entity)list.get(k);
    			if(entity instanceof EntityTHShot)//懐中時計があるなら
    			{
    				EntityTHShot shot = (EntityTHShot)entity;
    				if(shot.user == player)//その懐中時計の持ち主がこの時計の持ち主と同じなら
    				{
						shot.setShotSize(shot.getShotSize() + 0.5F);
    				}
    			}
    		}

    		world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 4F + 0.8F));
    		player.swingItem();//投げる動作をさせる
    		itemStack.setItemDamage(itemStack.getItemDamage() + 30);
       		
    	}
       	return itemStack;
    }
	
	//インベントリにある限り常時呼び出されるメソッド
	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int i, boolean flag)
	{
		itemStack.setItemDamage(itemStack.getItemDamage() - 1);
	}
	
	//Entityに当たったときの処理
    /*public boolean hitEntity(ItemStack itemStack, EntityLivingBase hitEntityLivingBase, EntityLivingBase useEntityLivingBase)
    {
    	hitEntityLivingBase.
    	return true;
    }*/
	
	//右クリックを終了したときに呼び出されるメソッド
	/*@Override
	public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int usedTime)
	{

	}*/
	
    /*public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player)
    {
        return itemStack;
    }
    
    public int getMaxItemUseDuration(ItemStack itemStack)
    {
        return 72000;
    }
	
	//アイテムを使ったときのアクションを指定
	@Override
	public EnumAction getItemUseAction(ItemStack itemStack)
	{
		return EnumAction.bow;
	}*/
	
    /**
     * Generates the base Random item for a specific instance of the chest gen,
     * Enchanted books use this to pick a random enchantment.
     *
     * @param chest The chest category to generate for
     * @param rnd World RNG
     * @param original Original result registered with the chest gen hooks.
     * @return New values to use as the random item, typically this will be original
     */
    public WeightedRandomChestContent getChestGenBase(ChestGenHooks chest, Random rnd, WeightedRandomChestContent original)
    {
    	original.theItemId = new ItemStack( THKaguyaItems.miracle_mallet);
    	original.theMinimumChanceToGenerateItem = 100;
    	original.theMaximumChanceToGenerateItem = 100;
    	//original.
        return original;
    }
}
