package thKaguyaMod.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
//import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
//import net.minecraft.util.Icon;


public class ItemThirdEye extends Item
{	
	//生物の情報を読み取る目
	//名前と体力を読み取る　飼うことのできる動物の飼い主がわかる
	//シフトで動物に使うとラブモードになる
	
	private EntityLivingBase hitEntity = null;
	private boolean check = false;
	
	public static final String iconName[] =
	{
		"thirdEye_0", "thirdEye_1"
	};
	
	public ItemThirdEye()
	{
		super();
		this.setTextureName("thkaguyamod:thirdEye_0");//テクスチャの指定
		maxStackSize = 1;//最大スタック数
		setMaxDamage(150);
		setCreativeTab(CreativeTabs.tabMisc);//クリエイティブのその他タブに登録
		
	}
	
	//ダメージ値によってアイテムアイコンを変える
	/*@Override
	public int getIconFromDamage(int damage)
    {
    	if (damage == 0)//ダメージ値0の場合
        {
            return 84;//getTextureFileで取得した画像ファイルを16x16に分けたときの、84番目の画像を使用。左上を0、右下を255とする
        }
    	else
    	{
    		return 85;
    	}
    }*/
	
	/*@SideOnly(Side.CLIENT)
	//ダメージ値によってアイテムアイコンを変える
    public Icon getIconFromDamage(int damage)
    {
        int i = MathHelper.clamp_int(damage, 0, 1);
        return this.icon[i];
    }*/
	
	/*@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.icon = new Icon[iconName.length];

        for (int i = 0; i < iconName.length; ++i)
        {
            this.icon[i] = par1IconRegister.registerIcon("thkaguyamod:" + iconName[i]);
        }
    }*/
	
	/*@Override
	public boolean itemInteractionForEntity(ItemStack itemStack, EntityLivingBase entityLivingBase)
    {
    	hitEntity = entityLivingBase;
    	check = true;

        return true;
    }*/
	
	/** 右クリックを押した瞬間の処理
	 *  @param itemStack : 右クリックを押したItemStack
	 *  @param world     : ワールド
	 *  @param player    : 右クリックを押したプレイヤー
	 *  @return 右クリックを押したItemStackを返す
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
    	String strHP,strType,str, strfl;
    	if(hitEntity != null)
    	{
    		
    		//strType = hitentity.getClass().getCanonicalName();//Entity名を取得
    		//strHP = "  Life = " + (float)hitEntity.getHealth()/2F + "/" + (float)hitEntity.getMaxHealth()/2F;//体力表示の文字列
    		//str = strType + strHP;
    		//str = strHP;
    		
    		//par3EntityPlayer.addChatMessage( str.substring(24) );//classファイル名のEntity以降を抽出。MODには対応しないかもしれない手抜き仕様
    		//par3EntityPlayer.addChatMessage("  " + hitentity.getEntityString());//名前が読み取れなくなった・・・orz
    		//par3EntityPlayer.addChatMessage( strHP );//メッセージを出力
    		//par3EntityPlayer.addChatMessage( strfl );
    		
    		//動物に好かれる処理がしたい
 
    	}
    	/*if(hitEntity instanceof EntityAnimal && par3EntityPlayer.isSneaking() && par1ItemStack.getItemDamageForDisplay() < 1)
    	{
    		EntityAnimal entityAnimal = (EntityAnimal)hitEntity;
    		boolean ahs;
    		if(entityAnimal instanceof EntityCow)
    		{
	    		par1ItemStack.itemID = Item.wheat.shiftedIndex;//小麦に擬態
    		}
    		else if(entityAnimal instanceof EntityPig)
    		{
    			par1ItemStack.itemID = Item.field_82797_bK.shiftedIndex;//にんじんに擬態　アイテム名がへんな名前
    		}
    		else if(entityAnimal instanceof EntityChicken)
    		{
    			par1ItemStack.itemID = Item.seeds.shiftedIndex;//種に擬態
    		}
	    	par1ItemStack.stackSize = 2;//アイテムを強制消費するため、一時的に２つにしておく
    		if(ahs = !hitEntity.interact(par3EntityPlayer) || par3EntityPlayer.capabilities.isCreativeMode)//ラブモードにする　小麦であることが必須
    		{
    			par1ItemStack.stackSize = 1;//条件を満たしていない場合は、消費しないため、1に戻す
    		}
	    	par1ItemStack.itemID = mod_thKaguya.thirdeyeItem.shiftedIndex;//小麦から第３の眼に戻す
	    	
    		if(!ahs)//条件を満たしていれば第三の眼に戻した後に、ダメージを限界まで与える
    		{
    			par1ItemStack.damageItem(149, par3EntityPlayer);
    		}

    	}*/
    	//hitEntity = null;
    	//check = true;
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
        }
    	
    	/*if (movingObjectPosition != null && movingObjectPosition.entityHit != null && movingObjectPosition.entityHit instanceof EntityPlayer)
        {
        	EntityPlayer player2 = (EntityPlayer)movingObjectPosition.entityHit;

            if (player2.capabilities.disableDamage)
            {
            	movingObjectPosition = null;
            }
        }*/
    	
    	if (movingObjectPosition != null)
        {
        	check = true;
        	hitEntity = (EntityLivingBase)movingObjectPosition.entityHit;
        }
        return itemStack;
    }
	
	//インベントリにある限り常時呼び出されるメソッド
	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean flag)
    {
    	//耐久が減っていたら徐々に回復
    	if( itemstack.isItemDamaged() == true)
    	{
    		itemstack.damageItem(-1, (EntityLivingBase)entity);//毎フレーム耐久を1回復
    	}
    	if(check && hitEntity != null/* && hitEntity instanceof EntityPlayer == false*/)//プレイヤーの体力は読み取れない
    	{
    		if(entity instanceof EntityPlayer)
    		{
    			EntityPlayer player = (EntityPlayer)entity;
    			String strHP;
    			String strEntityName;
    			//strHP = " HP ： " + (float)hitEntity.func_110143_aJ()/*getHealth()*//2F + "/" + (float)hitEntity.func_110138_aP()/*getMaxHealth()*//2F;//体力表示の文字列
    			float hp = (float)hitEntity.getHealth()/2F;
    			hp = (float)Math.round(hp * 100F) / 100F;
    			strHP = " HP ： " + hp + "/" + (float)hitEntity.getMaxHealth()/2F;//体力表示の文字列
    			
    			if(hitEntity instanceof EntityPlayer)//当たったEntityがプレイヤーなら
    			{
    				EntityPlayer player2 = (EntityPlayer)hitEntity;
    				//strEntityName = player2.username;
    				strEntityName = player2.getDisplayName();
    			}
    			else//プレイヤーでないなら
    			{
    				//strEntityName = EntityList.getEntityString( hitEntity );
    				//strEntityName = hitEntity.getEntityName();
    				strEntityName = hitEntity.getCommandSenderName();
    			}
    			if(!world.isRemote)
    			{
    				
    				player.addChatMessage(new ChatComponentText(strEntityName));//名前を表示
    				player.addChatComponentMessage(new ChatComponentText(strHP));//体力を表示
    			}
    			if(hitEntity instanceof EntityTameable)//テイムできる生物の場合
    			{
    				EntityTameable entityTameable = (EntityTameable)hitEntity;
    				if(entityTameable.isTamed())//テイムされているなら
    				{
    					String strOW;
    					strOW = "  Owner : " + entityTameable.getOwner().getCommandSenderName();
    					if(!world.isRemote)
    					{
    						player.addChatComponentMessage(new ChatComponentText(strOW));//オーナー名を表示
    						
    					}
    				}
    			}
    		}

    		check = false;
    		hitEntity = null;
    	}
    }
	
	//アイテムをEntityに対して使ったときに呼び出されるメソッド
	/*public void useItemOnEntity(ItemStack itemstack, EntityLivingBase EntityLivingBase)
    {
    	hitentity = EntityLivingBase;//Entityの情報を取得
    }*/
	
}