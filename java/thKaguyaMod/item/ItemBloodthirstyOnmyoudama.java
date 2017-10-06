package thKaguyaMod.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
//import net.minecraft.util.Icon;
import thKaguyaMod.THShotLib;
import thKaguyaMod.init.THKaguyaItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/** 血に餓えた陰陽玉 */
public class ItemBloodthirstyOnmyoudama extends Item
{	
	
	/** アイテム 血に餓えた陰陽玉のコンストラクタ　*/
	public ItemBloodthirstyOnmyoudama()
	{
		super();
		this.setTextureName("thkaguyamod:material/Onmyoudama");//テクスチャの指定
		maxStackSize = 1;//最大スタック数
		setMaxDamage(150);
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
		player.setItemInUse(itemStack, getMaxItemUseDuration(itemStack));

		
		return itemStack;
	}
	
	//右クリックを押したときの処理
	@Override
	public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int usedTime)
	{
    	String strHP,strType,str, strfl;

    	Vec3 look = player.getLookVec();
    	//始点を登録
    	Vec3 vec3d = Vec3.createVectorHelper(player.posX, player.posY + player.getEyeHeight(), player.posZ);
    	//終点を登録
    	Vec3 vec3d1 = Vec3.createVectorHelper(player.posX + look.xCoord * 64.0D, player.posY + look.yCoord * 64.0D, player.posZ + look.zCoord * 64.0D);
        //始点と終点からブロックとの衝突を取得
    	//MovingObjectPosition movingObjectPosition = world.rayTraceBlocks_do_do(vec3d, vec3d1, false, true);
    	MovingObjectPosition movingObjectPosition = world.func_147447_a(vec3d, vec3d1, false, true, true);
    	//始点を登録
    	vec3d = Vec3.createVectorHelper(player.posX, player.posY + player.getEyeHeight(), player.posZ);
    	//終点を登録
    	vec3d1 = Vec3.createVectorHelper(player.posX + look.xCoord * 64.0D, player.posY + look.yCoord * 64.0D, player.posZ + look.zCoord * 64.0D);
        //ブロックと衝突していたなら
    	if (movingObjectPosition != null)
        {
        	//終点を衝突した点に変更
        	vec3d1 = Vec3.createVectorHelper(movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
        }
    	Entity entity = null;

    	//このEntityから移動後までの線分に、指定分の範囲を追加した直方体と衝突するEntityのリストを取得
        List<?> list = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.addCoord(look.xCoord * 64.0D, look.yCoord * 64.0D, look.zCoord * 64.0D).expand(1.0D, 1.0D, 1.0D));
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
        if (entity != null)
        {
            movingObjectPosition = new MovingObjectPosition(entity);
            
        	if (movingObjectPosition != null)
            {
        		if(player instanceof EntityPlayerMP && !world.isRemote)
        		{
        			EntityPlayerMP playerMP = (EntityPlayerMP)player;
        			Vec3 posPlayer = THShotLib.pos_Living(player);
        			//Vec3 toVec = THShotLib.angle_ToPos(movingObjectPosition.hitVec, THShotLib.pos_Living(player));
        			Vec3 toVec = THShotLib.pos(movingObjectPosition.hitVec.xCoord - posPlayer.xCoord, movingObjectPosition.hitVec.yCoord - posPlayer.yCoord, movingObjectPosition.hitVec.zCoord - posPlayer.zCoord);
        			Vec3 vec = THShotLib.getVectorNomalize(toVec);
    	    		player.posX = player.posX + toVec.xCoord - vec.xCoord * 2.0D;
    	    		if( entity.posY > player.posY )
    	    		{
    	    			player.posY = (int)(player.posY + toVec.yCoord - vec.yCoord * 2.0D + 1.0D) + 0.1D;// + (entity.posY - player.posY);
    	    		}
    	    		else
    	    		{
    	    			player.posY = (int)(player.posY + toVec.yCoord - vec.yCoord * 2.0D + 1.0D) + 0.1D;
    	    		}
    	    		player.posZ = player.posZ + toVec.zCoord - vec.zCoord * 2.0D;
    				playerMP.playerNetServerHandler.setPlayerLocation(player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch);
        		}
            }
        }
    	
    	/*if (movingObjectPosition != null && movingObjectPosition.entityHit != null && movingObjectPosition.entityHit instanceof EntityPlayer)
        {
        	EntityPlayer player2 = (EntityPlayer)movingObjectPosition.entityHit;

            if (player2.capabilities.disableDamage)
            {
            	movingObjectPosition = null;
            }
        }*/
    	

    	
		itemStack.damageItem(1, player);
		
		NBTTagCompound	nbt			= itemStack.getTagCompound();
		
		if( itemStack.getItemDamage() >= this.getMaxDamage() )
		{
			int originalDamage = 0;
			
    		if( nbt != null )
    		{
    			originalDamage = nbt.getInteger("OriginalDamage");
    			nbt.removeTag("OriginalDamage");
    		}
    		
    		itemStack = new ItemStack( THKaguyaItems.yin_yang_orb );
    		itemStack.setItemDamage( originalDamage );
		}
	}
	
	//アイテムを使ったときのアクションを指定
	@Override
	public EnumAction getItemUseAction(ItemStack itemStack)
	{
		return EnumAction.bow;//右クリック時は剣のガードアクション
	}
	
    @Override
    public int getMaxItemUseDuration(ItemStack itemStack)
    {
        return 72000;
    }
    
	//アイテムを発光させる。 trueなら発光
	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack itemStack, int pass)
	{   
		return true;
	}

}