package thKaguyaMod.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thKaguyaMod.THKaguyaLib;
import thKaguyaMod.entity.item.EntityHisou;
import thKaguyaMod.init.THKaguyaItems;

public class ItemHisou extends ItemSword
{

	//緋想の剣
	//5レベル以上なら「全人類の緋想天」を放てる
	//相手の気質を取得し弱点をつく
    
    public ItemHisou(ToolMaterial toolMaterial)
    {
        super(toolMaterial);
        this.setTextureName("thkaguyamod:hisousword");//テクスチャの指定
    }
    
	//Entityに当たったときの処理
    @Override
    public boolean hitEntity(ItemStack itemStack, EntityLivingBase living_hit, EntityLivingBase living_user)
    {
    	//NBTを取得
    	NBTTagCompound nbt = itemStack.getTagCompound();
    	
    	//NBTがnullでないなら
    	if(nbt != null)
    	{
    		//当たったEntityの名前を取得。ここではどの言語にも対応できる名前で取得している
    		String living_hit_name = "entity." + EntityList.getEntityString(living_hit) + ".name";
    		
    		//当たったEntityがすでに取得しているEntityと同じ名前なら
    		if( living_hit_name.equals( nbt.getString("EntityName")))
    		{
    			//通常の２倍のダメージであるハート５つ分を与える
    			living_hit.attackEntityFrom(DamageSource.causeMobDamage(living_user), 10F);
    			//使用者がプレイヤーなら
    			if(living_user instanceof EntityPlayer)
    			{
    				EntityPlayer player = (EntityPlayer)living_user;
    				//クリティカルエフェクトを出す
    				player.onEnchantmentCritical(living_hit);
    			}
    		}
	    }
    	//NBTがnullなら
    	else
	    {
    		//新たにNBTを設定
	    	nbt = new NBTTagCompound();
	    	itemStack.setTagCompound(nbt);
	    }
    	
    	//NBTに攻撃したEntityの名前を登録
    	nbt.setString("EntityName", "entity." + EntityList.getEntityString(living_hit) + ".name");
	    
    	//耐久を１減らす
        itemStack.damageItem(1, living_user);
        
        return true;
    }
	
    //ブロックを破壊したときに呼び出される
	@Override
	public boolean onBlockDestroyed(ItemStack itemStack, World world, Block block, 
			int blockX, int blockY, int blockZ, EntityLivingBase living)
	{
		//壊したブロックが葉ブロックなら
        if(block == Blocks.leaves || block == Blocks.leaves2)
        {
        	if( itemRand.nextInt(25) == 1 && blockY > 128)//４％の確立で、高さが128m以上の場所なら
        	{
        		//天界の桃を出現させる
        		EntityItem entityItem = new EntityItem(living.worldObj, blockX, blockY, blockZ, new ItemStack(THKaguyaItems.heavenly_peach));
        		if(!world.isRemote)
        		{
        			living.worldObj.spawnEntityInWorld(entityItem);
        		}
        	}
            return true;
        }
        //葉ブロックでないなら通常の処理
        else
        {
            return super.onBlockDestroyed(itemStack, world, block, blockX, blockY, blockZ, living);
        }
	}
	
	//ブロックの破壊速度を取得
	@Override
	public float getDigSpeed(ItemStack itemStack, Block block, int metadata)
	{
		//葉ブロックならかなり速い
    	if (block == Blocks.leaves || block == Blocks.leaves2)
        {
            return 15F;
        }
    	//羊毛ブロックならそこそこ速い
    	else if (block == Blocks.wool)
        {
            return 10F;
        }
    	//その他のブロックなら通常の処理
        else
        {
            return super.getDigSpeed(itemStack, block, metadata);
        }
	}
	
	//アイテムを発光させる。 trueなら発光
	@Override
	public boolean hasEffect(ItemStack itemstack, int pass)
	{   
		return true;
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
    	//「全人類の緋想天」を使用する
    	if(!world.isRemote)//レベルが５より上なら使用可能
    	{
    		//スペルカード「全人類の緋想天」を宣言
    		if(THKaguyaLib.checkSpellCardDeclaration(world, itemStack, player, 7, 2, true) == false)
    		{
    			//宣言に失敗したなら終了する
    			return itemStack;
    		}
    		EntityHisou hisou_0, hisou_1;
    		
    		//緋想の剣本体を出現させる
    		hisou_0 = new EntityHisou(world, player,null, 7 + 1, itemStack.getItemDamageForDisplay());
	        world.spawnEntityInWorld(hisou_0);//バールのようなものの本体
    		itemStack.stackSize--;//スタックから消滅させる
    		
    		//残像を７つ出現させる
    		for(int i = 0; i < 7; i++)
    		{
	    		hisou_1 = new EntityHisou(world, player, hisou_0, i + 1, 0);
	    		world.spawnEntityInWorld(hisou_1);//バールのようなものの残像
    		}
    		

    	}
       	return itemStack;
    }

	//エンチャント
	@Override
    public int getItemEnchantability()
    {
        return 0;//エンチャントできない
    }
	
	//アイテム名の下に情報を付加する
    @Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean bool)
	{
		super.addInformation(itemStack, player, list, bool);
		
		//NBTを取得
		NBTTagCompound nbt = itemStack.getTagCompound();
		
		//NBTがnullなら何もしない
		if(nbt == null)
		{
			return;
		}
		//NBTの"EntityName"で取得しているEntityの名前を取得する
		String entityName = nbt.getString("EntityName");
		//気質：Entity名　を追加する
		list.add(StatCollector.translateToLocal("thKaguya.item.kishitu") + " : " + StatCollector.translateToLocal(entityName));
	}
    
	//Forgeの追加メソッド　エンチャントブックの使用を許可するか
	@Override
	public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2)
    {
        return false;
    }
	
}
