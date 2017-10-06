package thKaguyaMod.item;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

/** 死神の大鎌
 * 目の前の動物やモンスターを引き寄せたり、離したりできる
 */
public class ItemDeathScythe extends ItemSword
{

    /** 死神の大鎌 */
    public ItemDeathScythe(ToolMaterial toolMaterial)
    {
        super(toolMaterial);
        this.setTextureName("thkaguyamod:deathScythe");//テクスチャの指定
        setMaxDamage(444);//耐久値
    }
	
	/** 右クリックを押した瞬間の処理
	 *  @param itemStack : 右クリックを押したItemStack
	 *  @param world     : ワールド
	 *  @param player    : 右クリックを押したプレイヤー
	 *  @return 右クリックを押したItemStackを返す
	 */
	@Override
   	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
    {
		//有効距離
		double range = 16.0D;
    	Vec3 look = player.getLookVec();
    	//始点を登録
    	Vec3 vec3d = Vec3.createVectorHelper(player.posX, player.posY + player.getEyeHeight(), player.posZ);
    	//終点を登録
    	Vec3 vec3d1 = Vec3.createVectorHelper(player.posX + look.xCoord * range, player.posY + look.yCoord * range, player.posZ + look.zCoord * range);
        //始点と終点からブロックとの衝突を取得
    	//MovingObjectPosition movingObjectPosition = world.rayTraceBlocks_do_do(vec3d, vec3d1, false, true);
    	MovingObjectPosition movingObjectPosition = world.func_147447_a(vec3d, vec3d1, false, true, true);
    	//始点を登録
    	vec3d = Vec3.createVectorHelper(player.posX, player.posY + player.getEyeHeight(), player.posZ);
    	//終点を登録
    	vec3d1 = Vec3.createVectorHelper(player.posX + look.xCoord * range, player.posY + look.yCoord * range, player.posZ + look.zCoord * range);
        //ブロックと衝突していたなら
    	if (movingObjectPosition != null)
        {
        	//終点を衝突した点に変更
        	vec3d1 = Vec3.createVectorHelper(movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
        }
    	Entity entity = null;

    	//このEntityから移動後までの線分に、指定分の範囲を追加した直方体と衝突するEntityのリストを取得
        List list = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.addCoord(look.xCoord * range, look.yCoord * range, look.zCoord * range).expand(1.0D, 1.0D, 1.0D));
        double d = 0.0D;
        for (int j = 0; j < list.size(); j++)
        {
        	// 衝突リストから、i番目のEntityを取得
            Entity entity1 = (Entity)list.get(j);

    		if(entity1 instanceof EntityLivingBase)
    		{
        		float f2 = 0.3F;
            	AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(f2, f2, f2);
            	MovingObjectPosition movingObjectPosition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);
        		//Entityとの衝突がないなら、このEntityはパスする
            	if (movingObjectPosition1 != null)
            	{
        			//始点からEntityに衝突した点までの距離を取得
            		double d1 = vec3d.distanceTo(movingObjectPosition1.hitVec);
        			//今までで一番近いEntityなら、Entityと距離を記憶する
            		if (d1 < d || d == 0.0D)
            		{		
                		entity = entity1;
                		d = d1;
            		}
            	}
            }
        }

    	//Entityに当たっていたなら
        if (entity != null && !world.isRemote)
        {
        	double power = 0.6D;
        	if(player.isSneaking())//スニークなら斥力
	        {
	        	entity.motionX += look.xCoord * power;//powerの分だけ斥力を働かせる
	        	entity.motionY += look.yCoord * power;
	            entity.motionZ += look.zCoord * power;
		    }
		    else//スニークじゃないなら引力
		    {
	        	entity.motionX -= look.xCoord * power;//powerの分だけ引力を働かせる
	        	entity.motionY -= look.yCoord * power;
	            entity.motionZ -= look.zCoord * power;
		    }
	        itemstack.damageItem(1, player);//距離を操った生物の数だけ耐久が減る
        }
       	return itemstack;
    }
	
	//Forgeの追加メソッド
	//武器のダメージを返す
	//@Override
    /*@Deprecated
    public float getDamageVsEntity(Entity hitEntity, ItemStack itemStack)
    {
        return 6.0F; //getDamageVsEntity(par1Entity);
    }*/

	//エンチャン適性値
	@Override
    public int getItemEnchantability()
    {
        return 7;
    }	
}
