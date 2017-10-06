package thKaguyaMod.item;

import static thKaguyaMod.DanmakuConstants.*;
import static thKaguyaMod.THShotLib.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.effect.EntityMiracleCircle;
import thKaguyaMod.entity.shot.EntitySanaeWind;
import thKaguyaMod.init.THKaguyaItems;

public class ItemOharaibouS extends Item
{
	
	//早苗の持つお祓い棒
	//信仰（レベル）に応じて使える技と使えない技がある。
	//また、強さも変わる
	
	public ItemOharaibouS()
	{
		super();
		this.setTextureName("thkaguyamod:mikoStick_S");//テクスチャの指定
		setMaxStackSize(1);//最大スタック数
		setMaxDamage(50);//耐久値
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
    	
    	player.setItemInUse(itemStack, getMaxItemUseDuration(itemStack));//右クリックを押し始めた
    	EntityMiracleCircle entityMiracleCircle = new EntityMiracleCircle(world, player, 0);
    	if(!world.isRemote)
    	{
    		world.spawnEntityInWorld(entityMiracleCircle);//１つ目の五芒星を出現させる
    	}
        return itemStack;
    }
	
	//右クリックを終了したときに呼び出されるメソッド
	@Override
	public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int usedTime)
    {
    	int time = this.getMaxItemUseDuration(itemStack) - usedTime;//アイテムの右クリックしてた時間
    	
    	
    	if(time >= 30)//30tick経過しているなら（五芒星を一つ以上描き終えているなら）
    	{
        	for(int i = 0; i < player.inventory.mainInventory.length; i++)//使用者のインベントリのアイテムを１つずつ見ていく
        	{
        		//そこにアイテムがあり、それがスペルカードなら
        		if (player.inventory.mainInventory[i] != null && 
        			player.inventory.mainInventory[i].getItem() == THKaguyaItems.spell_card)
                {
        			
        			//五芒星を５つ以上、使用者のレベルが４０以上、大奇跡「八坂の神風」なら
        			if(time >= 150 && player.experienceLevel >= 40 && player.inventory.mainInventory[i].getItemDamage() == 18)
        			{
        				setMiraclePower(player.inventory.mainInventory[i]);//スペルカードを使用可能状態にする
        				itemStack.damageItem(-30, player);//お祓い棒の耐久を３０回復
        			}
        			//五芒星を４つ以上、使用者のレベルが３５以上、開海「モーゼの奇跡」なら
        			else if(time >= 120 && player.experienceLevel >= 35 && player.inventory.mainInventory[i].getItemDamage() == 17)
        			{
        				setMiraclePower(player.inventory.mainInventory[i]);
        				itemStack.damageItem(-20, player);
        			}
        			//五芒星を３つ以上、使用者のレベルが３０以上、妖怪退治「妖力スポイラー」なら
           			else if(time >= 90 && player.experienceLevel >= 30 && player.inventory.mainInventory[i].getItemDamage() == 16)
        			{
           				setMiraclePower(player.inventory.mainInventory[i]);
        				itemStack.damageItem(-15, player);
        			}
        			//五芒星を２つ以上、使用者のレベルが２５以上、奇跡「ファフロッキーズの奇跡」なら
           			else if(time >= 60 && player.experienceLevel >= 25 && player.inventory.mainInventory[i].getItemDamage() == 15)
        			{
           				setMiraclePower(player.inventory.mainInventory[i]);
        				itemStack.damageItem(-10, player);
        			}
        			//五芒星を１つ以上、使用者のレベルが２０以上、奇跡「ミラクルフルーツ」なら
           			else if(player.experienceLevel >= 20 && player.inventory.mainInventory[i].getItemDamage() == 14)
        			{
           				setMiraclePower(player.inventory.mainInventory[i]);
        				itemStack.setItemDamage(itemStack.getItemDamage() - 5);
        			}
                }
        	}
    	}
    	
    	//五芒星を描き終えていないなら
    	if(time < 30)//通常攻撃の風起し
	    {
    		Vec3 vec3 = THShotLib.getVecFromAngle(player.rotationYaw, player.rotationPitch, 1.0D);
    		float damage = player.experienceLevel / 5F;
    		int endurance = player.experienceLevel * 3;
    		if(endurance > 120)
    		{
    			endurance = 120;
    		}
    		else if(endurance < 15)
    		{
    			endurance = 15;
    		}
    		if(damage == 0)
    		{
    			damage = 0.1F;
    		}
    		
    		EntitySanaeWind entitySanaeWind = new EntitySanaeWind(world, player, player, THShotLib.pos_Living(player), vec3, 0F, 
    				rotate_Default(), 0F, 9999, 0.4D, 0.01D, 0.99D, gravity_Zero(),  0, 1.0F, damage, 0, endurance, WIND01);
	    	if(!world.isRemote)
	    	{
	    		world.spawnEntityInWorld(entitySanaeWind);
	    	}
	    	itemStack.damageItem(1, player);
	    }
   	}
	
	//スペルカードに奇跡の力を付与
	private void setMiraclePower(ItemStack itemStack)
	{
		NBTTagCompound nbt = itemStack.getTagCompound();
		if(nbt == null)
		{
			nbt = new NBTTagCompound();
			itemStack.setTagCompound(nbt);
		}
		nbt.setInteger("Charge", 1000);
	}
	
    /**
     * How long it takes to use or consume an item
     */
	@Override
    public int getMaxItemUseDuration(ItemStack itemStack)
    {
        return 72000;
    }
	
	//アイテムを使ったときのアクションを指定
	@Override
	public EnumAction getItemUseAction(ItemStack itemStack)
    {
        return EnumAction.block;//右クリック時は剣のガードアクション
    }
	
	//アイテムを大きく表示するか
	@Override
	public boolean isFull3D()
    {
        return true;
    }
}