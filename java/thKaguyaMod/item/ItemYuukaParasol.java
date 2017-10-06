package thKaguyaMod.item;

import static thKaguyaMod.DanmakuConstants.*;
import static thKaguyaMod.THShotLib.*;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thKaguyaMod.ShotData;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.item.EntityYuukaParasol;
import thKaguyaMod.entity.shot.EntityTHShot;
import thKaguyaMod.entity.shot.ISpecialShot;
import thKaguyaMod.registry.SpecialShotRegistry;


public class ItemYuukaParasol extends Item implements ISpecialShot
{
	//幽香の傘
	
	public static final int PARASOL_MAX_DAMAGE = 60;//耐久値
	public static final int SPECIAL_FLOWER_LAND = 350;
	
	public ItemYuukaParasol()
	{
		super();
		this.setTextureName("thkaguyamod:yuukaParasol");//テクスチャの指定
		setMaxDamage(PARASOL_MAX_DAMAGE);//耐久値
		this.maxStackSize = 1;//最大スタック数
		this.setCreativeTab(CreativeTabs.tabCombat);//クリエイティブタブの設定
		//特殊弾の登録はコンストラクタ内で行う
		SpecialShotRegistry.registerSpecialShot(ItemYuukaParasol.class, SPECIAL_FLOWER_LAND);
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
    	if(player.isSneaking())
    	{
    		boolean flag = true;
    		//周囲のEntityを取得
    		List list = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.addCoord(0.0D, 0.0D, 0.0D).expand(20.0D, 20.0D, 20.0D));
    		for(int k = 0; k < list.size(); k++)
    		{
    			Entity entity = (Entity)list.get(k);
    			if(entity instanceof EntityYuukaParasol)//幽香の日傘があるなら
    			{
    				EntityYuukaParasol entityYuukaParasol = (EntityYuukaParasol)entity;
    				if(entityYuukaParasol.user == player)//その日傘の持ち主がこの日傘の持ち主と同じなら
    				{
						return itemStack;//日傘は出せない
    				}
    			}
    		}
    		EntityYuukaParasol entityYuukaParasol = new EntityYuukaParasol(world, player, itemStack.getItemDamageForDisplay());
    		if(!world.isRemote)
    		{
    			world.spawnEntityInWorld(entityYuukaParasol);
    			itemStack.stackSize--;
    		}
    	}
    	return itemStack;
    }
	
	//右クリックを離したときに呼び出される
	@Override
	public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int usedTime)
	{
		int shotPower = 10 + getMaxItemUseDuration(itemStack)-usedTime;
		double shotPower2 = (float)shotPower / 10.0D;
		if(shotPower2 > 4.0D)
		{
			shotPower2 = 5.0D;
		}
    	float transDegree = 3.141593F / 180F;
    	double ax,ay,az;
		Random random = new Random();
    	float angle = (float)random.nextInt(36000)/100F;
    	float angle2 = 0.0F;
    	int shotNum = 45;
    	float angleSpan = (360F / (float)shotNum) * transDegree;
    	float angle2Span = angleSpan * 20F;
    	ShotData shot = ShotData.shot(FORM_SCALE, RED, 0.3F, 7.0F, 3, 80, SPECIAL_FLOWER_LAND);
		int color = THShotLib.SCALE[RED] + random.nextInt(2);
		if(itemRand.nextInt(2) == 0)
		{
			shot.color = YELLOW;
		}
		
		//THShotLib.createRingShot(player, player, pos_Entity(player), angle(0.0D, 1.0D, 0.0D), 0F, 9999, speed, speed, 0.0D, gravity(-0.02D), shot, shotNum, 0.5D, 45F, itemRand.nextFloat() * 360F);
    	for(int i = 0; i < shotNum; i++)
    	{
    		ax = -(double)MathHelper.sin(angle);//X方向　水平方向
    		az =  (double)MathHelper.cos(angle);//Z方向　水平方向
    		double speed = 0.23D - (double)MathHelper.sin(angle2) * 0.02D;
    		
    		createShot(player, player, 
    			pos(player.posX, THShotLib.getPosYFromEye(player, 0.2D), player.posZ), 
    			angle(ax * shotPower2, 1.4F, az * shotPower2), 0F, 
    			speed, speed, 0.0D, gravity(-0.02), shot);
    		angle += angleSpan;
    		angle2 += angle2Span;
    	}
    	
    	shotNum = (int)((float)shotNum * 0.25F);
    	angleSpan  = (360F / (float)shotNum) * transDegree;
		shotPower2 *= 0.66D;
    	for(int i = 0 ;i < shotNum; i++)
    	{
    		ax = -(double)MathHelper.sin(angle);//X方向　水平方向
    		az =  (double)MathHelper.cos(angle);//Z方向　水平方向
    		createShot(player, player, 
        			pos(player.posX, THShotLib.getPosYFromEye(player, 0.2D), player.posZ), 
        			angle(ax * shotPower2, 1.1F, az * shotPower2), 0F, 
        			0.23D, 0.23D, 0.0D, gravity(-0.02), shot);
    		angle += angleSpan;
    	}
    	world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 4F + 0.8F));//音を出す
		itemStack.damageItem(1, player);
    }
	
	//Entityに当たった時に呼び出される
	@Override
    public boolean hitEntity(ItemStack itemStack, EntityLivingBase living_hit, EntityLivingBase living_used)
    {
    	//Entityを吹っ飛ばす
    	double disX = living_hit.posX - living_used.posX;
    	double disY = living_hit.posY - living_used.posY;
    	double disZ = living_hit.posZ - living_used.posZ;
    	float toEntityYaw = (float)Math.atan2(disX, disZ);
    	float toEntityPitch = (float)Math.atan2(disY, Math.sqrt(disX * disX + disZ * disZ));
    	double power = 3.0D;

    	living_hit.motionX = MathHelper.sin(toEntityYaw) * MathHelper.cos(toEntityPitch) * power;
    	living_hit.motionY = MathHelper.sin(toEntityPitch) * power + 1.0D;
    	living_hit.motionZ = MathHelper.cos(toEntityYaw) * MathHelper.cos(toEntityPitch) * power;

    	living_hit.attackEntityFrom(DamageSource.causeMobDamage(living_used), 7);
    	
    	itemStack.damageItem(1, living_used);
    	
        return true;
    }
	
	//アイテムを使ったときのアクションを指定
	@Override
	public EnumAction getItemUseAction(ItemStack itemStack)
    {
        return EnumAction.block;//右クリック時は剣のガードアクション
    }
	
	//Entityへのダメージ
	/*@Override
    public int getDamageVsEntity(Entity par1Entity)
    {
        return 7;
    }*/
	
	//Forgeの追加メソッド
	//武器のダメージを返す
	//@Override
    /*@Deprecated
    public float getDamageVsEntity(Entity hitEntity, ItemStack itemStack)
    {
        return 7.0F;
    }*/
	
	@Override
    public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player)
    {
        return itemStack;
    }
    
	@Override
    public int getMaxItemUseDuration(ItemStack itemStack)
    {
        return 72000;
    }
	
	//エンチャント
	@Override
    public int getItemEnchantability()
    {
        return 0;//エンチャントできない
    }
	
	//Forgeの追加メソッド　エンチャントブックの使用を許可するか
	@Override
	public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2)
    {
        return false;
    }

	@Override
	public void specialShot_move(World world, int id, EntityTHShot shot) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public boolean specialShot_hitBlock(World world, int id, EntityTHShot shot,
			MovingObjectPosition movingObjectPosition) {

    	int hitPointX = (int)shot.posX;
    	int hitPointY = (int)(shot.posY - 0.3D);
    	int hitPointZ = (int)shot.posZ;
    	//int hitPointBlock = worldObj.getBlockId(hitPointX, hitPointY, hitPointZ);
    	Block hitPointBlock = world.getBlock(hitPointX, hitPointY, hitPointZ);
    	//if(hitPointBlock == Block.grass.blockID)
    	if(hitPointBlock == Block.getBlockById(2))
    	{
    		int setX = hitPointX;
    		int setY = hitPointY + 1;
    		int setZ = hitPointZ;
    		if(world.isAirBlock(setX, setY, setZ))
    		{
    			if(shot.getShotColor() == YELLOW)//黄色の弾幕なら
    			{
    				if(!world.isRemote)
    				{
    					//黄色い花を咲かす
    					//worldObj.setBlock(setX, setY, setZ, BlockFlower.plantYellow.blockID, 1, 3);
    					world.setBlock(setX, setY, setZ, BlockFlower.getBlockById(37));
    				}
    			}
    			else if(shot.getShotColor() == RED)//赤色の弾幕なら
    			{
    				if(!world.isRemote)
    				{
    					//赤い花を咲かす
    					//worldObj.setBlock(setX, setY, setZ, BlockFlower.plantRed.blockID, 1, 3);
    					world.setBlock(setX, setY, setZ, BlockFlower.getBlockById(38));
    				}
    			}
    		}
    	}
		return false;
	}

	@Override
	public boolean specialShot_hitEntity(World world, int id, EntityTHShot shot,
			Entity entity_Hit) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

}