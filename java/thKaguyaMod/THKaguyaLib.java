package thKaguyaMod;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.entity.item.EntitySilverKnife;
import thKaguyaMod.entity.spellcard.EntitySpellCard;
import thKaguyaMod.entity.spellcard.THSpellCard;
import thKaguyaMod.init.THKaguyaConfig;
import thKaguyaMod.init.THKaguyaItems;
import thKaguyaMod.registry.SpellCardRegistry;

/**
 * 五つの難題MOD+用、汎用関数群
 *
 */
public class THKaguyaLib
{
	
	
	/**
	 *  持ち主の指定のポジションにぬるぬるついていく処理
	 * @param itemEntity    : 追尾するアイテムのEntity。通常はthis
	 * @param userEntity    : アイテムの持ち主。ついていく対象
	 * @param lengthToUser  : アイテムと持ち主の距離
	 * @param positionAngle : 持ち主から見てアイテムの来る角度。0Fで正面。
	 * @param yForrow       : 垂直方向の角度変化にも反応するか
	 * @param yOffset       : 垂直方向に反応しない場合の高さ
	 */
	public static void itemEffectFollowUser(Entity itemEntity, Entity userEntity, double lengthToUser, float positionAngle, boolean yForrow, double yOffset)
	{
		if(userEntity != null)
		{
			double posX, posY, posZ;

			if(yForrow)
			{
				posX = userEntity.posX - Math.sin((userEntity.rotationYaw + positionAngle) / 180F * 3.141593F) * Math.cos(userEntity.rotationPitch / 180F * 3.141593F) * lengthToUser;
    			posZ = userEntity.posZ + Math.cos((userEntity.rotationYaw + positionAngle) / 180F * 3.141593F) * Math.cos(userEntity.rotationPitch / 180F * 3.141593F) * lengthToUser;
				posY = userEntity.posY - Math.sin(userEntity.rotationPitch / 180F * 3.141593F) * lengthToUser +  (double)userEntity.getEyeHeight() - 0.5D;//若干目の高さより下に設置
			}
			else
			{
				posX = userEntity.posX - Math.sin((userEntity.rotationYaw + positionAngle) / 180F * 3.141593F) * lengthToUser;
    			posZ = userEntity.posZ + Math.cos((userEntity.rotationYaw + positionAngle) / 180F * 3.141593F) * lengthToUser;
				posY = userEntity.posY + yOffset;//若干目の高さより下に設置
			}
    		itemEntity.motionX = userEntity.motionX + (posX - itemEntity.posX);
    		itemEntity.motionY = userEntity.motionY + (posY - itemEntity.posY);
    		itemEntity.motionZ = userEntity.motionZ + (posZ - itemEntity.posZ);
    		itemEntity.setPosition(posX, posY, posZ);
		}
	}
	
	//↑と同じだが、垂直方向の角度の影響を受ける場合の簡易
	public static void itemEffectFollowUser(Entity itemEntity, Entity userEntity, double lengthToUser, float positionAngle)
	{
		itemEffectFollowUser(itemEntity, userEntity, lengthToUser, positionAngle, true, 0.0D);
	}
	
	/**
	 * Entityとして出現しているアイテムの効果が切れたときの処理
	 * @param itemEntity : 効果を終えるEntity。通常はthis
	 * @param userEntity : アイテムの持ち主。プレイヤー
	 * @param item       : アイテム化するアイテム
	 * @param damage     : アイテム化したときのダメージ値
	 * @return 
	 */
	public static boolean itemEffectFinish(Entity itemEntity, EntityLivingBase userEntity, Item item, int damage)
	{	
		//使用者がクリエイティブではなく、存在するなら
		if(userEntity != null )
		{
			if(userEntity instanceof EntityPlayer)
			{
				EntityPlayer userEntity_p = (EntityPlayer)userEntity;
				if(userEntity_p.capabilities.isCreativeMode)//使用者がクリエイティブモードなら
				{
					if( !itemEntity.worldObj.isRemote )
					{
						itemEntity.setDead();//そのまま消滅させる
					}
					return true;
				}
				//アイテムをインベントリに加える
				if(userEntity_p.inventory.addItemStackToInventory(new ItemStack(item, 1, damage)) == false)
				{
					//インベントリが一杯なら使用者の目の前でアイテム化
					//（クリエイティブでは常にインベントリには物が吸収される仕様があるらしく、クリエイティブでは落とさない（false判定がでない)）
					if(!itemEntity.worldObj.isRemote)
					{
						itemEntity.worldObj.spawnEntityInWorld(new EntityItem(itemEntity.worldObj, itemEntity.posX, itemEntity.posY, itemEntity.posZ, new ItemStack(item, 1, damage)));
						itemEntity.setDead();
					}
					return true;
				}
			}
			if(userEntity.isDead)//使用者が死んでいるなら
			{
				if(!itemEntity.worldObj.isRemote)
				{
					//アイテム化し、その場にドロップ
					itemEntity.worldObj.spawnEntityInWorld(new EntityItem(itemEntity.worldObj, itemEntity.posX, itemEntity.posY, itemEntity.posZ, new ItemStack(item, 1, damage)));
				}
			}
		}
		else
		{
			//使用者がいない場合（再起動した際など）にはその場にアイテム化
			if(!itemEntity.worldObj.isRemote)
			{
				itemEntity.worldObj.spawnEntityInWorld(new EntityItem(itemEntity.worldObj, itemEntity.posX, itemEntity.posY, itemEntity.posZ, new ItemStack(item, 1, damage)));
			}
    	}
		if(!itemEntity.worldObj.isRemote)
		{
			itemEntity.setDead();
		}
		return true;
	}
	
	//↑のダメージ値のないアイテム版
	public static boolean itemEffectFinish(Entity itemEntity, EntityLivingBase userEntity, Item item)
	{
		return itemEffectFinish(itemEntity, userEntity, item, 0);
	}
	
	/**
	 * スペルカードを宣言する
	 * スペルカード宣言は相手を選択（遠くの相手でも可）することで可能
	 * スペルカードは一人あたり多重には宣言できない
	 * @param world            : 宣言するワールド
	 * @param itemStack        : スペルカード
	 * @param user 			: スペルカードの宣言者
	 * @param spellCardNumber  : スペルカードナンバー。使用するスペルカードを指定
	 * @param level            : スペルカードの難易度。1～4でEasy～Lunaticに相当。プレイヤー使用のスペルカードは2のNormal
	 * @param firstAttack      : スペルカード名とスペルカード宣言音を出すかどうか。trueなら出す
	 * @return スペルカード宣言に成功したらtrue
	 */
	public static boolean checkSpellCardDeclaration(World world, ItemStack itemStack, EntityLivingBase user, int spellCardNumber, int level, boolean firstAttack)
	{
		
		int needLevel = 0;
		
		//宣言者がプレイヤーだった場合の処理
		if(user instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)user;
			
			//プレイヤーがクリエイティブでないなら（クリエイティブは処理不要）
			if(!player.capabilities.isCreativeMode)
			{
				//手に持っているのがスペルカードで、早苗のスペルカードなら
				if(itemStack.getItem() == THKaguyaItems.spell_card && itemStack.getItemDamage() >= 14 && itemStack.getItemDamage() <=18)
				{
					NBTTagCompound nbt = itemStack.getTagCompound();
					
					//NBTがあるなら
					if(nbt != null)
					{
						//早苗のスペルカードがアクティブでないなら
						if(nbt.getInteger("Charge") <= 0)
						{
							//宣言失敗
							return false;
						}
					}
					else
					{
						//早苗のスペルカードにNBTがないなら宣言失敗
						return false;
					}
				}
				
				Class<?> spellcard;
				THSpellCard useSpellCard;
				
				//スペルカードごとの消費レベルをプレイヤーから消費する
				if((spellcard = SpellCardRegistry.getSpellCardClass(spellCardNumber)) != null)
				{
					try {
						useSpellCard = (THSpellCard)spellcard.newInstance();
						needLevel = useSpellCard.getNeedLevel();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
						
					
				}
				
				int bombItemNum = 0;//スペルカードアイテムの総数をカウント
				
	    		//プレイヤーのインベントリを順番に見ていく
	    		for(int i = 0; i < player.inventory.mainInventory.length; i++){
	    			//i番目のアイテムがスペルカードアイテムなら
	    			if(player.inventory.mainInventory[i] != null && 
	    				player.inventory.mainInventory[i].getItem() == THKaguyaItems.bomb_item)
	    			{
	    				//スペルカードアイテムをスタックしているなら、そのスタック分countを追加
	    				bombItemNum += player.inventory.mainInventory[i].stackSize;
	    			}
	    		}
	    		
	    		//プレイヤーのレベルとスペルカードアイテムの総数の合計が、必要レベルを下回るなら
				if((player.experienceLevel + bombItemNum) < needLevel)
				{
					return false;//宣言失敗
				}
			}
			

		}
		
		double angleX = -Math.sin(radTrans(user.rotationYaw)) * Math.cos(radTrans(user.rotationPitch));
    	double angleY = -Math.sin(radTrans(user.rotationPitch));
    	double angleZ =  Math.cos(radTrans(user.rotationYaw)) * Math.cos(radTrans(user.rotationPitch));
    	//始点を登録
    	Vec3 vec3d = Vec3.createVectorHelper(user.posX, user.posY + user.getEyeHeight(), user.posZ);
    	//終点を登録
    	Vec3 vec3d1 = Vec3.createVectorHelper(user.posX + angleX * 32.0D, user.posY + angleY * 32.0D, user.posZ + angleZ * 32.0D);
        //始点と終点からブロックとの衝突を取得
    	//MovingObjectPosition movingObjectPosition = world.rayTraceBlocks_do_do(vec3d, vec3d1, false, true);
    	MovingObjectPosition movingObjectPosition = world.func_147447_a(vec3d, vec3d1, false, true, true);
    	//始点を再登録
    	vec3d = Vec3.createVectorHelper(user.posX, user.posY + user.getEyeHeight(), user.posZ);
    	//終点を再登録
    	vec3d1 = Vec3.createVectorHelper(user.posX + angleX * 32.0D, user.posY + angleY * 32.0D, user.posZ + angleZ * 32.0D);
        //ブロックと衝突していたなら
    	if (movingObjectPosition != null)
        {
        	//終点を衝突した点に変更
        	vec3d1 = Vec3.createVectorHelper(movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
        }
    	Entity entity = null;

    	//このEntityから移動後までの線分に、指定分の範囲を追加した直方体と衝突するEntityのリストを取得
        List list = world.getEntitiesWithinAABBExcludingEntity(user, user.boundingBox.addCoord(angleX * 32.0D, angleY * 32.0D, angleZ * 32.0D).expand(1.0D, 1.0D, 1.0D));
        double nearDistance = 0.0D;
        for (int j = 0; j < list.size(); j++)
        {
        	// 衝突リストから、i番目のEntityを取得
            Entity entity1 = (Entity)list.get(j);

    		if(entity1 instanceof EntityLivingBase && entity1 instanceof EntityAnimal == false && entity1 instanceof EntityVillager == false)
    		{
        		float expand = 0.3F;//当たり判定の加算値
            	AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(expand, expand, expand);
            	MovingObjectPosition movingObjectPosition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);
        		//Entityとの衝突がないなら、このEntityはパスする
            	if (movingObjectPosition1 != null)
            	{
        			//始点からEntityに衝突した点までの距離を取得
            		double distanceToHitEntity = vec3d.distanceTo(movingObjectPosition1.hitVec);
        			//今までで一番近いEntityなら、Entityと距離を記憶する
            		if (distanceToHitEntity < nearDistance || nearDistance == 0.0D)
            		{		
                		entity = entity1;
                		nearDistance = distanceToHitEntity;
            		}
            	}
            }
        }
    	
    	List list2 = world.getEntitiesWithinAABBExcludingEntity(user, user.boundingBox.addCoord(0.0D, 0.0D, 0.0D).expand(64.0D, 64.0D, 64.0D));
    	for(int k = 0; k < list2.size(); k++)
    	{
    		Entity entity2 = (Entity)list2.get(k);
    		if(entity2 instanceof EntitySpellCard)
    		{
    			EntitySpellCard entitySpellCard2 = (EntitySpellCard)entity2;
    			if(entitySpellCard2.user == user)
    			{
    				entity = null;//スペルカード宣言を無効化
    				return false;
    			}
    		}
    	}
    	
    	//Entityに当たっていたなら
        if (entity != null)
        {
            movingObjectPosition = new MovingObjectPosition(entity);
        }
        else
        {
        	return false;
        }
    	
    	if (movingObjectPosition != null && movingObjectPosition.entityHit != null &&itemProssessionCheck(world, user, spellCardNumber))
        {
        	if(movingObjectPosition.entityHit instanceof EntityLivingBase)
        	{
        		if(!world.isRemote)
        		{
        			spellCardDeclaration(world, user, (EntityLivingBase)movingObjectPosition.entityHit, spellCardNumber, needLevel, level, firstAttack);
        			return true;
        		}
        		else
        		{
        			return false;
        		}
        	}
        }
		return false;	
	}
	
	/**
	 * スペルカードの宣言で宣言が可能だった場合の処理
	 * @param world
	 * @param user スペルカード使用者
	 * @param target スペルカードを宣言された相手
	 * @param spellCardNumber スペルカードNo
	 * @param level スペルカードのレベル。1:Easy  2:Normal  3:Hard  4:Lunatic  プレイヤー宣言は基本2
	 * @param firstAttack trueならスペルカード名とスペルカード音が出る
	 */
	public static void spellCardDeclaration(World world, EntityLivingBase user, EntityLivingBase target, int spellCardNumber, int needLevel, int level, boolean firstAttack)
	{
		//使用するスペルカード
		EntitySpellCard entitySpellCard = new EntitySpellCard(world, user, target, spellCardNumber, level);
		//スペルカード名
	    String spellCardName = StatCollector.translateToLocal("item.thSpellCard." + spellCardNumber + ".name");
		
		if(!world.isRemote)
		{
			world.spawnEntityInWorld(entitySpellCard);//スペルカードを出現させる
		}

		//宣言者がプレイヤーの場合
	    if(user instanceof EntityPlayer)
	    {
	       	EntityPlayer entityPlayer_user = (EntityPlayer)user;
	       	int bomb = 0;
	       	//entityPlayer_user.openGui(THKaguyaCore.instance, THKaguyaCore.instance.guiSpellCardID, world, (int)entityPlayer_user.posX, (int)entityPlayer_user.posY, (int)entityPlayer_user.posZ);
	       		
	       	if(!world.isRemote)
	       	{
	       		//使用者がスペルカードを使ったというメッセージを出す
	       		entityPlayer_user.addChatMessage( new ChatComponentText(spellCardName));
	       		//スペルカード宣言音を出す
	       		world.playSoundAtEntity(entityPlayer_user, "thkaguyamod:spellcard", THKaguyaConfig.SpellCardVol, 1.0F);
	       	}

	       	//プレイヤーのインベントリを順番に見ていく
	       	//消費ボムカウントが必要レベルより低く、ボムアイテムを持っている間続ける
	       	while(bomb < needLevel &&  entityPlayer_user.inventory.hasItem(THKaguyaItems.bomb_item))
	       	{
	       		//インベントリからボムアイテムを１つ消費する
	       		entityPlayer_user.inventory.consumeInventoryItem(THKaguyaItems.bomb_item);
	       		bomb++;
	       	}

	       	//ボムアイテムで足りなかった分は、レベルを消費
	       	entityPlayer_user.addExperienceLevel(bomb - needLevel);
	    }
	    
	    //宣言対象がプレイヤーの場合
	    if(target instanceof EntityPlayer)
	    {
	    	//継続スペルカードでなければ
	    	if(firstAttack)
	    	{
		       	EntityPlayer entityPlayer_target = (EntityPlayer)target;
		       	
		       	if(!world.isRemote)
		       	{
		       		//宣言対象にスペルカード宣言メッセージを出す
		       		entityPlayer_target.addChatMessage( new ChatComponentText(spellCardName));
		       		//スペルカード宣言音を出す
		       		world.playSoundAtEntity(entityPlayer_target, "thkaguyamod:spellcard", THKaguyaConfig.SpellCardVol, 1.0F);
		       	}
	    	}  		
	    }

		//継続的に使っているスペルカードでなければ、周囲の弾幕を回収する
       	if(firstAttack)
       	{
       		THShotLib.danmakuRemove(user, 40.0F, "Enemy", true);
       	}
	}
	
	private static boolean itemProssessionCheck(World world, EntityLivingBase EntityLivingBase, int nomber)
	{
		return true;		
	}
	
	/**
	 * プレイヤーのパワーを返す。最大400
	 * @param player
	 * @return
	 */
	public static float getPlayerPower( EntityPlayer player )
	{
		float power = 0.0F;
		
		/* プレイヤーのインベントリを順番に見ていく */
		for( int i = 0 ; i < player.inventory.mainInventory.length ; i++ )
		{
			/* i番目のアイテムがパワーアップアイテムの場合 */
			if(		( player.inventory.mainInventory[i] != null									)
				&&	( player.inventory.mainInventory[i].getItem() == THKaguyaItems.power_item	) )
			{
				/* 小の場合パワー+1 */
				if( player.inventory.mainInventory[i].getItemDamage() == 0 )
				{
					power += 1.0F * player.inventory.mainInventory[i].stackSize;
				}
				/* 大の場合パワー+10 */
				else if( player.inventory.mainInventory[i].getItemDamage() == 1 )
				{
					power += 10.0F * player.inventory.mainInventory[i].stackSize;
				}
			}
		}
		
		if( power > 400.0F )
		{
			power = 400.0F;
		}
		
		return power;
	}
	
	
	/**
	 * 銀のナイフを出現させる
	 * @param user 銀のナイフの使用者
	 * @param pos 銀のナイフの発射される座標
	 * @param angle 銀のナイフの角度
	 * @param speed 銀のナイフの速度
	 * @param color 銀のナイフの色。0で青、1で赤、2で緑、3で白
	 * @param startCount
	 * @return 出現したEntitySilverKnifeを返す
	 */
	public static EntitySilverKnife createSilverKnife(EntityLivingBase user, Vec3 pos, Vec3 angle, double speed, int color, int startCount )
	{
		EntitySilverKnife silverKnife;
		
		if(user != null)
		{
			silverKnife = new EntitySilverKnife(user.worldObj, user, pos, angle, speed, 3);
			silverKnife.setKnifeCount(startCount);

		    if(!user.worldObj.isRemote)
		    {
				user.worldObj.spawnEntityInWorld(silverKnife);//銀のナイフを出現させる
				return silverKnife;
		    }
		   
		}
		return null;
	}
	
	/**
	 * ホーミングアミュレットを生成する
	 * ほとんど弾の生成処理と同じ
	 * colorは 0で小さいホーミングアミュレット、1で大きいホーミングアミュレット、2で小さい拡散しないアミュレット、3で大きい拡散アミュレット
	 * @param user
	 * @param source
	 * @param posX
	 * @param posY
	 * @param posZ
	 * @param vectorX
	 * @param vectorY
	 * @param vectorZ
	 * @param firstSpeed
	 * @param limitSpeed
	 * @param acceleration
	 * @param xVectorG
	 * @param yVectorG
	 * @param zVectorG
	 * @param damage
	 * @param color 0で小さいホーミングアミュレット、1で大きいホーミングアミュレット、2で小さい拡散しないアミュレット、3で大きい拡散アミュレット
	 * @param size
	 * @param end
	 * @param delay
	 * @return 生成したEntityHomingAmulet
	 */
	/*public static EntityHomingAmulet createHomingAmulet(EntityLivingBase user, Entity source, double posX, double posY, double posZ, double vectorX, double vectorY, double vectorZ, 
			double firstSpeed, double limitSpeed, double acceleration, double xVectorG, double yVectorG, double zVectorG, 
			float damage, int color, float size, int end, int delay)
	{
		EntityHomingAmulet amulet = null;
		if(user != null)
		{
			amulet = new EntityHomingAmulet(user.worldObj, user, source, posX, posY, posZ, vectorX, vectorY, vectorZ, firstSpeed, limitSpeed, acceleration, xVectorG, yVectorG, zVectorG, damage, color, size, end, delay, 0);
			if(!user.worldObj.isRemote)
			{
				user.worldObj.spawnEntityInWorld(amulet);//ホーミングアミュレットを出現させる
			}
		}
		return amulet;
	}*/
		
	public static float radTrans(float deg)
	{
		return deg / 180F * (float)Math.PI;
	}
		
	public static float degTrans(float rad)
	{
		return rad / (float)Math.PI * 180F;
	}
}