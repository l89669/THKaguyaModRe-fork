package thKaguyaMod.item;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thKaguyaMod.entity.EntityDivineSpirit;
import thKaguyaMod.entity.living.EntityTHFairy;


public class ItemMikoSword extends ItemSword
{

	/*
	 * 神子の持つ宝剣　１０人の言葉を同時に聞き取れる
	 * 基本鉄の剣と同じ
	 */
    
    public ItemMikoSword(ToolMaterial toolMaterial)
    {
        super(toolMaterial);
        this.setTextureName("thkaguyamod:toyosatomimiSword");//テクスチャの指定
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
    	player.setItemInUse(itemStack, getMaxItemUseDuration(itemStack));//アイテムの使用継続時間を記憶させる
    	
    	if(itemStack.getItemDamage() >=itemStack.getMaxDamage())
    	{
    		itemStack.stackSize--;
    	}
        return itemStack;
    }
	
	//右クリックを押し終わったときに呼び出されるメソッド
	@Override
	public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer player, int usedTime)
    {
    	Entity[] nearEntitys;//11番目の空間はゴミ箱
    	double[] nearDistance;
    	nearEntitys = new Entity[11];
    	nearDistance = new double[11];
    	double recognizeDistance = (double)(72000 - usedTime);
    	if(recognizeDistance > 50D) recognizeDistance = 50D;
    	int i;
    	for(i = 0; i < 11; i++)
    	{
    		nearEntitys[i] = null;
    		nearDistance[i] = 999.9D;
    	}
		List<?> list = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(recognizeDistance, recognizeDistance,recognizeDistance));
        for (int j = 0; j < list.size(); j++)
        {
			Entity entity1 = (Entity)list.get(j);
        	double distance = player.getDistanceToEntity(entity1);
        	if(entity1 instanceof EntityLivingBase)
        	{
        		int n = 0;
        		boolean flag = false;
        		while(n < 10 && !flag)
        		{
        			if(nearDistance[n] > distance)
        			{
        				for(int k = 10; k > n; k--)
        				{
        					nearEntitys[k] = nearEntitys[k-1];
        					nearDistance[k] = nearDistance[k-1];
        				}
        				nearEntitys[n] = entity1;
        				nearDistance[n] = distance;
        				flag = true;
        			}
        			n++;
        		}
        	}
        }
    	String str = "";
    	String strPos;
    	String strUpDown;
    	String strColor;
    	String strNum;
    	int num;
    	double dx;
    	double dz;
    	float angle = (360F-player.rotationYaw) % 360F;
    	float angle2;
    	//if(angle < 0F)epYaw -= 360F;
    	if(angle < -180F) angle += 360F;
    	if(angle >  180F) angle -= 360F;
    	i = 0;
    	while(i < 10 && nearEntitys[i] != null)
    	{
			double eyePosX = nearEntitys[i].posX;
			double eyePosY = nearEntitys[i].posY + nearEntitys[i].getEyeHeight();
			double eyePosZ = nearEntitys[i].posZ;
			/*if(world.isAirBlock(eyePosX, eyePosY, eyePosZ))
			{
				world.setBlock(eyePosX, eyePosY, eyePosZ, mod_thKaguya.divineSpiritBlockId, 0 + itemRand.nextInt(8), 0);
			}*/
			
	    	EntityDivineSpirit entityDivineSpirit;
	    	int color = 0;
	    	if(nearEntitys[i] instanceof EntityTHFairy)
	    	{
	    		int randomnum = itemRand.nextInt(100);
	    		{
	    			
	    			if(randomnum < 85)
	    			{
	    				color = 1;
	    			}
	    			else if(randomnum < 95)
	    			{
	    				color = 2;
	    			}
	    			else
	    			{
	    				color = 4;
	    			}
	    		}
	    	}
	    	else if(nearEntitys[i] instanceof EntityCreeper)
	    	{
	    		color = 2;
	    	}
	    	else if(nearEntitys[i] instanceof EntityVillager)
	    	{
	    		color = 1;
	    	}
	    	else if(nearEntitys[i] instanceof EntityAnimal)
	    	{
	    		color = 3;
	    		if(nearEntitys[i] instanceof EntityTameable)
	    		{
		    		EntityTameable tame = (EntityTameable)nearEntitys[i];
		    		if(tame.isTamed())
		    		{
		    			color = 4;
		    		}
	    		}
	    			
	    	}
	    	else if(nearEntitys[i] instanceof EntityWaterMob)
	    	{
	    		color = 5;
	    	}
	    	else if(nearEntitys[i] instanceof EntityLivingBase)
	    	{
	    		EntityLivingBase living = (EntityLivingBase)nearEntitys[i];
	    		if(living.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD)
	    		{
	    			color = 7;
	    		}
	    		else if(living.getCreatureAttribute() == EnumCreatureAttribute.ARTHROPOD)
	    		{
	    			color = 6;
	    		}
	    		else
	    		{
	    			color = 0;
	    		}
	    	}
	    	entityDivineSpirit = new EntityDivineSpirit(world, eyePosX, eyePosY, eyePosZ, color);
	    	//world.playSoundAtEntity(player, "random.orb", 0.5F, 0.9F);
	       	if(!world.isRemote)
	       	{
	        	world.spawnEntityInWorld(entityDivineSpirit);//ペンデュラムを出現させる
	       		//itemStack.stackSize--;//スタックから消滅させる
	       	}
	       	
	       	//スニークなら探知メッセージを表示しない
	       	if(!player.isSneaking())
	       	{
    		
	    		dx = nearEntitys[i].posX - player.posX;//Entityと使用者の各要素の距離を保存
				dz = nearEntitys[i].posZ - player.posZ;
				
	    		angle2  = ((float)Math.atan2(dx, dz) / 3.141593F * 180F) % 360F;//平面上の角度を求める
	    		if(angle2 < -180F) angle += 360F;
	    		if(angle2 >  180F) angle -= 360F;
	    		angle2 -= angle;
	    		if(angle2 < -135F || angle2 > 135F) strPos = StatCollector.translateToLocal("thKaguya.back");
	    		else if(angle2 < -45F) strPos = StatCollector.translateToLocal("thKaguya.right");
	    		else if(angle2 <  45F) strPos = StatCollector.translateToLocal("thKaguya.front");
	    		else strPos = StatCollector.translateToLocal("thKaguya.left");
	    		if(player.posY > nearEntitys[i].posY + 3.0D) strUpDown = StatCollector.translateToLocal("thKaguya.down");
	    		else if(player.posY < nearEntitys[i].posY - 3.0D) strUpDown = StatCollector.translateToLocal("thKaguya.up");
	    		else strUpDown = "";
	    		if(nearEntitys[i] instanceof EntityMob) strColor = "\u00a74";
	    		else if(nearEntitys[i] instanceof EntityAnimal) strColor = "\u00a7a";
	    		else strColor = "\u00a73";
	    		num = i+1;
	    		strNum = num + ". ";
	    		if(num != 10) strNum = " " + strNum;
	    		if(nearEntitys[i] instanceof EntityPlayer)
	    		{
	    			EntityPlayer nearEntityPlayer = (EntityPlayer)nearEntitys[i];
	    			//str = strColor + strNum + nearEntityPlayer.getEntityName() + " " + strPos + " " + strUpDown + "  "+ (int)nearDistance[i] + "m" + "  ";
	    			str = strColor + strNum + nearEntityPlayer.getDisplayName() + " " + strPos + " " + strUpDown + "  "+ (int)nearDistance[i] + "m" + "  ";
	    		}
	    		else if(nearEntitys[i] instanceof EntityLivingBase)
	    		{
	    			EntityLivingBase nearEntityLiving = (EntityLivingBase)nearEntitys[i];
	    			str = strColor + strNum + /*EntityList.getEntityString( nearEntitys[i] )*/nearEntityLiving.getCommandSenderName() + " " + strPos + " " + strUpDown + "  "+ (int)nearDistance[i] + "m" + "  ";
	    		}
	    		if(!world.isRemote)
	    		{
	    			player.addChatMessage(  new ChatComponentText(str) );
	    		}
	       	}
    		i++;
    	}
    	
    	//探知した数だけ耐久を減らす
    	if(!player.capabilities.isCreativeMode)
    	{
    		itemstack.setItemDamage(itemstack.getItemDamage() + i);
    	}

    	
    	/*
    	int light = world.getSavedLightValue(EnumSkyBlock.Sky, (int)player.posX, (int)player.posY, (int)player.posZ);
    	int blLight = world.getBlockLightValue((int)player.posX, (int)player.posY, (int)player.posZ);
    	int skyLight = world.skylightSubtracted;
    	if(!world.isRemote)
    	{
    		player.addChatMessage( "Light = " + light + "blLight = " + blLight + "__sky = " + skyLight );
    	}*/
   	}

	//エンチャント可能か？
	@Override
    public int getItemEnchantability()
    {
        return 0;//エンチャント不可
    }
	
	//Forgeの追加メソッド　エンチャントブックの使用を許可するか
	@Override
	public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2)
    {
        return false;
    }
}
