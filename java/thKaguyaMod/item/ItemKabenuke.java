package thKaguyaMod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thKaguyaMod.init.THKaguyaConfig;

public class ItemKabenuke extends Item
{
	
	//壁抜けの鑿
	//壁の向こうに空間があれば、そこまで通り抜けできる
	
	public ItemKabenuke()
	{
		super();
		this.setTextureName("thkaguyamod:kabenuke");//テクスチャの指定
		maxStackSize = 1;//最大スタック数
		setMaxDamage(122);//耐久値
		setCreativeTab(CreativeTabs.tabMisc);//クリエイティブのその他タブに登録
	}
	
	//Forgeの追加メソッド　ブロックに対して右クリックをしたときに呼び出される
	@Override
	public boolean onItemUseFirst(ItemStack itemStack, EntityPlayer player, World world, int blockX, int blockY, int blockZ, int surface, float hitX, float hiyY, float hitZ) 
	{
		//コンフィグで使用制限をかけているなら使えない
		if(!THKaguyaConfig.canUseWallPassingChisel)
		{
			return false;
		}
		
		int px = 0;
    	int py = 0;
    	int pz = 0;
    	switch(surface)
    	{
    		case 0:
    			py = 1;
    			break;
    		case 1:
    			py = -1;
    			break;
    		case 2:
    			pz = 1;
    			break;
    		case 3:
    			pz = -1;
    			break;
    		case 4:
    			px = 1;
    			break;
    		default:
    			px = -1;
    			break;
    	}
    	
		if(player instanceof EntityPlayerMP && !world.isRemote)
		{
			EntityPlayerMP playerMP = (EntityPlayerMP)player;
	    	for(int i = 0; i < 16; i++)//最大16回の空間チェックの判定　最大16ブロック抜けられる
	    	{
	    		if( world.isAirBlock(blockX, blockY  ,blockZ))//空気ブロックかどうかの判定
	    		{
	    			if( world.isAirBlock(blockX, blockY + 1, blockZ))//標準より上も空気ブロックかどうか？
	    			{
	    				world.playSoundAtEntity(player, "random.fizz", 0.5F, 1.0F);//音を出す
	    				player.posX = (double)blockX + 0.5D;
	    				player.posY = (double)blockY + player.yOffset;
	    				player.posZ = (double)blockZ + 0.5D;
	    				player.motionX = 0.0D;
	    				player.motionY = 0.0D;
	    				player.motionZ = 0.0D;
	    				playerMP.playerNetServerHandler.setPlayerLocation((double)blockX + 0.5D, (double)blockY + player.yOffset, (double)blockZ + 0.5D, player.rotationYaw, player.rotationPitch);

	    				//player.setPosition( (double)blockX + 0.5D, (double)blockY + player.yOffset, (double)blockZ + 0.5D);
	    				itemStack.damageItem(1, player);
	    				return false;
	    			}
	    			else if( world.isAirBlock(blockX, blockY - 1, blockZ))//標準より下も空気ブロックかどうか？
	    			{
	    				world.playSoundAtEntity(player, "random.fizz", 0.5F, 1.0F);//音を出す
	    				player.posX = (double)blockX + 0.5D;
	    				player.posY = (double)blockY + player.yOffset - 1.0D;
	    				player.posZ = (double)blockZ + 0.5D;
	    				player.motionX = 0.0D;
	    				player.motionY = 0.0D;
	    				player.motionZ = 0.0D;
	    				playerMP.playerNetServerHandler.setPlayerLocation((double)blockX + 0.5D, (double)blockY + player.yOffset - 1.0D, (double)blockZ + 0.5D, player.rotationYaw, player.rotationPitch);

	    				//player.setPosition( (double)blockX + 0.5D, (double)blockY + player.yOffset - 1.0D, (double)blockZ + 0.5D);
	    				itemStack.damageItem(1, player);
	    				return false;
	    			}
	    		}
	    		
	    		//岩盤があったら失敗する 岩盤は壁抜け不可
	    		if(world.getBlock(blockX, blockY, blockZ) == Blocks.bedrock)
	    		{
	    			return false;
	    		}
	    		blockX += px;
	    		blockY += py;
	    		blockZ += pz;
	    	}
		}
    	
    	
    	return false;
	}
	
}