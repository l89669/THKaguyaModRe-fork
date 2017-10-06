package thKaguyaMod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thKaguyaMod.entity.shot.EntityTHSetLaser;
import thKaguyaMod.entity.shot.EntityTHShot;

public class ItemHoutou extends Item{
	
	//毘沙門天の宝塔
	
	public ItemHoutou()
	{
		super();
		this.setTextureName("thkaguyamod:houtou");//テクスチャの指定
		setHasSubtypes(true);
		setMaxDamage(50);
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
		//EntityTHHenyoriLaser laser;
		EntityTHSetLaser setLaser;
		
		double xVec, yVec, zVec;
		
		xVec = -Math.sin(((player.rotationYaw) / 180F) * 3.141593F) * Math.cos(((player.rotationPitch) / 180F) * 3.141593F);//X方向　水平方向
		yVec = -Math.sin(((player.rotationPitch) / 180F) * 3.141593F);// * MathHelper.cos((0F/180F) * 3.141593F);//Y方向　上下
		zVec =  Math.cos(((player.rotationYaw) / 180F) * 3.141593F) * Math.cos(((player.rotationPitch) / 180F) * 3.141593F);//Z方向　水平方向
		
		/*laser = new EntityTHHenyoriLaser(world, player, player, player.posX, player.posY, player.posZ,
				xVec, yVec, zVec, 0.3D, 0.3D, 1.0D, 0.0D, 0.0D, 0.0D, 
				4.0F, thShotLib.YELLOW, 0.1F, 120, 0, 4.0D);*/
		
		EntityTHShot shot = null;// THShotLib.createShot(player, THShotLib.pos_User(player), player.getLookVec(), 0.3D, THShotLib.BIG[THShotLib.RED]);
		
		
		if(shot != null)
		{
			for(int i = 0; i < 4; i++)
			{
				/*Vec3 vec = thShotLib.getRotationVectorFromAngle(shot.rotationYaw,  shot.rotationPitch, i * 90F, 1.0D);
				setLaser = 
					new EntityTHSetLaser(world, player, player,
						shot.posX, shot.posY, shot.posZ,
				    	vec.xCoord, vec.yCoord, vec.zCoord,
				    	4.0F, thShotLib.RED, 0.5F, 120, 0, 0, 5.0D,
				    	shot, i * 90F, 0.0D, 0.0D, 9.0F);*/
				
				/*if(!world.isRemote)
				{
					//world.spawnEntityInWorld(laser);
		
					world.spawnEntityInWorld(setLaser);
				}*/
			}
		}
    	/*double ax,ay,az;
    	EntityHomingAmulet entityHomingAmulet;

    	if(entityplayer.isSneaking())//低速モード
    	{
    		float angle = -10F;
    		for(int i = 0; i < 2; i++)
    		{
   				ax = -Math.sin(((entityplayer.rotationYaw + angle) / 180F) * 3.141593F) * Math.cos(((entityplayer.rotationPitch) / 180F) * 3.141593F);//X方向　水平方向
    			ay = -Math.sin(((entityplayer.rotationPitch) / 180F) * 3.141593F);// * MathHelper.cos((0F/180F) * 3.141593F);//Y方向　上下
   				az =  Math.cos(((entityplayer.rotationYaw + angle) / 180F) * 3.141593F) * Math.cos(((entityplayer.rotationPitch) / 180F) * 3.141593F);//Z方向　水平方向
   				entityHomingAmulet = new EntityHomingAmulet(world, (EntityLivingBase)entityplayer, ax*0.5D, ay*0.5D, az*0.5D, 0.5D,4, 1, 2.0F);
       			if(!world.isRemote)
       			{
          			world.spawnEntityInWorld(entityHomingAmulet);//ホーミングアミュレットを出現させる
       			}
    			angle += 20F;
    		}
    	}
    	else//高速モード
    	{
    		float angle = -50F;
    		for(int i = 0; i < 5; i++)
    		{
   				ay = -Math.sin(((entityplayer.rotationPitch) / 180F) * 3.141593F);// * MathHelper.cos((0F/180F) * 3.141593F);//Y方向　上下
   				ax = -Math.sin(((entityplayer.rotationYaw + angle) / 180F) * 3.141593F) * Math.cos(((entityplayer.rotationPitch) / 180F) * 3.141593F);//X方向　水平方向
   				az =  Math.cos(((entityplayer.rotationYaw + angle) / 180F) * 3.141593F) * Math.cos(((entityplayer.rotationPitch) / 180F) * 3.141593F);//Z方向　水平方向
   				entityHomingAmulet = new EntityHomingAmulet(world, (EntityLivingBase)entityplayer, ax*0.5D, ay*0.5D, az*0.5D, 0.5D,2, 0, 1.2F);
       			if(!world.isRemote)
       			{
          			world.spawnEntityInWorld(entityHomingAmulet);//ホーミングアミュレットを出現させる
       			}
    			angle += 25F;
    		}
    	}*/
    	world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 4F + 0.8F));//音を出す
    	if(!world.isRemote)
    	{
    		//itemStack.stackSize--;//一つ消費
    	}
    	
        return itemStack;
    }
}
