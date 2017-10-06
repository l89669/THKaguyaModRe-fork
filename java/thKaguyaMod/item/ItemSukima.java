package thKaguyaMod.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.item.EntitySukima;
import thKaguyaMod.init.THKaguyaConfig;

public class ItemSukima extends Item
{
	
	//スキマを設置する
	//同じ色のスキマ同士を行き来することができるようになる
	//シフトで使うと瞬間的に見た場所に移動できるスキマを作る
	//Entityを見て使うとそのEntityを取り寄せる
	
	public ItemSukima()
	{
		super();
		this.setTextureName("thkaguyamod:sukima");//テクスチャの指定
		setCreativeTab(CreativeTabs.tabMisc);//クリエイティブのその他タブに登録
	}
	
	protected float getSetAngle(EntityPlayer player, double setX, double setZ)
	{
    	float rotationYaw = player.rotationYaw;
    	rotationYaw = (float)Math.atan2(setX - player.posX, setZ - player.posZ) / 3.141593F * 180F;
    	//rotationYaw = rotationYaw % 180F;
    	if(rotationYaw < 0F)
    	{
    		rotationYaw += 360F;
    	}
    	if(rotationYaw % 90F < 45F)
    	{
    		rotationYaw = rotationYaw - (rotationYaw % 45F);
    	}
    	else
    	{
    		rotationYaw = rotationYaw - (rotationYaw % 45F) + 45F;
    	}
    	return -rotationYaw;
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
    	//通常世界なら使用可能  ここで-1にするとネザーということになる　　また、プレイヤーが何かに乗っているときは使用できない
    	//旧バージョンの効果*****************************************************************//
    	/*if(par3EntityPlayer.dimension == 0 && par3EntityPlayer.ridingEntity == null)
    	{
    		ChunkCoordinates chunkcoordinates = par2World.getSpawnPoint();
    		ChunkCoordinates chunkcoordinates2 = chunkcoordinates;
    		chunkcoordinates = par3EntityPlayer.verifyRespawnCoordinates(par2World, chunkcoordinates);
    		if( chunkcoordinates == null)
    		{
    			chunkcoordinates = chunkcoordinates2;
    		}
	    	par3EntityPlayer.posX = chunkcoordinates.posX+0.5F;
	    	par3EntityPlayer.posY = chunkcoordinates.posY-0.5F;
	    	par3EntityPlayer.posZ = chunkcoordinates.posZ+0.5F;
	    	par3EntityPlayer.fallDistance = 0.0F;
	    	//par3EntityPlayer.setPosition(par3EntityPlayer.posX,par3EntityPlayer.posY,par3EntityPlayer.posZ);

	    	par3EntityPlayer.preparePlayerToSpawn();
	    	
	    	//par3EntityPlayer.respawnPlayer();
	    	par2World.playSoundAtEntity(par3EntityPlayer, "mob.endermen.portal", 0.5F, 0.4F / (itemRand.nextFloat() * 4F + 0.8F));
	    	par1ItemStack.stackSize--;
    	}*/
    	//************************************************************************************//
    	
    	double setX = player.posX - Math.sin(player.rotationYaw   / 180F * (float)Math.PI) * Math.cos(player.rotationPitch / 180F * (float)Math.PI) * 2.0D;
    	double setY = player.posY - Math.sin(player.rotationPitch / 180F * (float)Math.PI) * 2.0D + player.getEyeHeight() / 2.0D;
    	double setZ = player.posZ + Math.cos(player.rotationYaw   / 180F * (float)Math.PI) * Math.cos(player.rotationPitch / 180F * (float)Math.PI)* 2.0D;
    	boolean lost = true;
    	
    	//if(!world.isBlockNormalCube((int)setX, (int)setY, (int)setZ))
    	if(!world.isBlockNormalCubeDefault((int)setX, (int)setY, (int)setZ, true))
    	{
    		EntitySukima entitySukima;
    		EntitySukima entitySukima2;
    		double hitX = player.posX;
    		double hitY = player.posY;
    		double hitZ = player.posZ;
    		float rotationYaw = 0F;
    		
    		if(!player.isSneaking())
    		{
    			double i = (int)setX;
    			double j = (int)setY;
    			double k = (int)setZ;
    			//double ix = 1.0D;
    			//double iy = 1.0D;
    			//double iz = 1.0D;
    			
    	        MovingObjectPosition movingobjectposition = getMovingObjectPositionFromPlayer(world, player, true);
    	        
    	        if (movingobjectposition == null)
    	        {
    	        	Vec3 look = player.getLookVec();
    	           	i = (player.posX + look.xCoord * 2.0D - 0.5D);
    	           	j = (player.posY + look.yCoord * 2.0D + 1.0D);
    	           	k = (player.posZ + look.zCoord * 2.0D - 0.5D);
    	           	
    	        	rotationYaw = player.rotationYaw;//getSetAngle(player, i, k);
    	        }
    	        else
    	        {
    	          	i = movingobjectposition.blockX;
    				j = movingobjectposition.blockY;
    	        	k = movingobjectposition.blockZ;
    		        
    	           	if (movingobjectposition.sideHit == 0)
    		        {
    		            j--;
    		            rotationYaw = getSetAngle(player, i, k);
    		        }
   		            if (movingobjectposition.sideHit == 1)
   		            {
   		                j++;
   		                rotationYaw = getSetAngle(player, i, k);
   		            }
   		            if (movingobjectposition.sideHit == 2)
   		            {
   		                k--;
   		                rotationYaw = 0F;
   		            }
   		            if (movingobjectposition.sideHit == 3)
   		            {
   		                k++;
   		                rotationYaw = 180F;
   		            }
   		            if (movingobjectposition.sideHit == 4)
   		            {
   		            	i--;
   		            	rotationYaw = 270F;
    		        }
   		            if (movingobjectposition.sideHit == 5)
   		            {
   		                i++;
   		                rotationYaw = 90F;
   		            }
   	            }
    		
    			entitySukima = new EntitySukima(world, player, (double)i + 0.5D, (double)j, (double)k + 0.5D, rotationYaw);
    		}
    		else
    		{
    			double xVecMax = -Math.sin(player.rotationYaw   / 180F * (float)Math.PI) * Math.cos(player.rotationPitch / 180F * (float)Math.PI) * 128.0D;
    			double yVecMax = -Math.sin(player.rotationPitch / 180F * (float)Math.PI) * 128.0D;
    			double zVecMax =  Math.cos(player.rotationYaw   / 180F * (float)Math.PI) * Math.cos(player.rotationPitch / 180F * (float)Math.PI) * 128.0D;
    			//始点（現在地）
    			Vec3 vec3d = THShotLib.pos(player.posX, player.posY + player.getEyeHeight(), player.posZ);
    			//終点（現在地に移動量を足した点）
    			Vec3 vec3d1 = THShotLib.pos(player.posX + xVecMax, player.posY + yVecMax, player.posZ + zVecMax);
        		//始点と終点からブロックとの当たりを取得
    			MovingObjectPosition movingObjectPosition = world.func_147447_a(vec3d, vec3d1, false, true, false);
    			vec3d = THShotLib.pos(player.posX, player.posY + player.getEyeHeight(), player.posZ);
    			vec3d1 = THShotLib.pos(player.posX + xVecMax, player.posY + yVecMax, player.posZ + zVecMax);
    			//何らかのブロックに当たっているなら
        		if (movingObjectPosition != null)
        		{
        			//終点を当たった点に変更
        			vec3d1 = Vec3.createVectorHelper(movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
        			hitX = movingObjectPosition.hitVec.xCoord;
        			hitY = movingObjectPosition.hitVec.yCoord;
        			hitZ = movingObjectPosition.hitVec.zCoord;
        			double xz_sq = (hitX - setX) * (hitX - setX) + (hitZ - setZ) * (hitZ - setZ);//平面方向の平方
        			//５m以下の距離なら無効
        			if(Math.sqrt( xz_sq + (hitY - setY) * (hitY - setY)) <= 5.0D)
        			{
        				return itemStack;
        			}
        		}
        		else
        		{
        			return itemStack;
        		}

    			Entity entity = null;

    			//このEntityから移動後までの線分に、指定分の範囲を追加した直方体と衝突するEntityのリストを取得
        		List<?> list = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.addCoord(xVecMax, yVecMax, zVecMax).expand(1.0D, 1.0D, 1.0D));
        		double d = 0.0D;
		        for (int j = 0; j < list.size(); j++)
		        {
		        	// 衝突リストから、i番目のEntityを取得
		            Entity entity1 = (Entity)list.get(j);

		    		if(/*entity1.canBeCollidedWith() ||*/ entity1 instanceof EntityLivingBase)
		    		{
		        		float f2 = 0.3F;
		            	AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(f2, f2, f2);
		            	MovingObjectPosition movingObjectPosition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);
		        		//Entityとの衝突がないなら、このEntityはパスする
		            	if (movingObjectPosition1 != null)
		            	{
		        			//始点からEntityに衝突した点までの距離を取得
		            		double d1 = vec3d.distanceTo(movingObjectPosition1.hitVec);
		        			//今までで一番近いEntity、かつ、５m以上離れているなら、Entityと距離を記憶する
		            		if((d1 < d || d == 0.0D) && d1 > 5.0D)
		            		{		
		                		entity = entity1;
		                		d = d1;
		            		}
		            	}
		            }
		        }

		    	//Entityに当たっていたなら
		        if (entity != null)
		        {
		            movingObjectPosition = new MovingObjectPosition(entity);
		        	movingObjectPosition.entityHit = entity;
		        }
		    	
		    	/*if (movingObjectPosition != null && movingObjectPosition.entityHit != null && movingObjectPosition.entityHit instanceof EntityPlayer)
		        {
		        	EntityPlayer player2 = (EntityPlayer)movingObjectPosition.entityHit;

		            if (player2.capabilities.disableDamage)
		            {
		            	movingObjectPosition = null;
		            }
		        }*/
		    	
		    	if (movingObjectPosition != null && movingObjectPosition.entityHit != null)
		        {
		        	if(movingObjectPosition.entityHit instanceof EntityLivingBase)
		        	{
		        		EntityLivingBase hitEntityLivingBase = (EntityLivingBase)movingObjectPosition.entityHit;
		        		hitX = hitEntityLivingBase.posX;
		        		hitY = hitEntityLivingBase.posY + hitEntityLivingBase.yOffset / 2.0D;
		        		hitZ = hitEntityLivingBase.posZ;
		        		setY += 1.0D;
		        	}
		        	else
		        	{
		        		hitX = movingObjectPosition.entityHit.posX;
		        		hitY = movingObjectPosition.entityHit.posY + 0.75D;
		        		hitZ = movingObjectPosition.entityHit.posZ;
		        	}
		        }
    			/*else
    			{
    				return itemStack;
    			}*/
    			entitySukima = new EntitySukima(world, player, setX, setY, setZ, 16);
    			entitySukima2 = new EntitySukima(world, player, hitX, hitY, hitZ, 16);
    			lost = itemRand.nextInt(100) < 15;//ランダムで消失
    			
    			if(!world.isRemote)
    			{
    				world.spawnEntityInWorld(entitySukima2);//スキマを生み出す
    			}
    		}
    		if(THKaguyaConfig.useDefaultGapSE)
    		{
    			world.playSoundAtEntity(player, "mob.endermen.portal", 0.5F, 0.4F / (itemRand.nextFloat() * 4F + 0.8F));
    		}
    		else
    		{
    			world.playSoundAtEntity(player,  THKaguyaConfig.sukimaWarpSE, 1.0F, 1.0F);
    		}
    		//world.playSoundAtEntity(player, "THItems.yukariPortal_spawn", 0.5F, 0.4F / (itemRand.nextFloat() * 4F + 0.8F));
    		if(!world.isRemote)
    		{
    			world.spawnEntityInWorld(entitySukima);//スキマを生み出す
    			if(lost)
    			{
    				itemStack.stackSize--;//スタックを一つ減らす
    			}
    		}
    	}
        return itemStack;
    }
}