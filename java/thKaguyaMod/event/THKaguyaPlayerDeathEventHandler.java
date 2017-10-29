package thKaguyaMod.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.effect.EntitySpellCardCircle;
import thKaguyaMod.init.THKaguyaConfig;
import thKaguyaMod.init.THKaguyaItems;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/** EntityLivingが倒れたときの割り込み処理 */
public class THKaguyaPlayerDeathEventHandler
{
	
	@SubscribeEvent
	public void onLivingDeathEvent(LivingDeathEvent event) {
		if(event.entityLiving.worldObj.isRemote) {
			return;
		}
		
		//プレイヤーが倒れたときに、エクステンドアイテムを所持していたらその場に復活させる
		if(event.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)event.entityLiving;
			
			//worldObj.playSoundAtEntity(this, "thkaguyamod:masterspark", THKaguyaConfig.MasterSparkVol, 1.0F);
			player.worldObj.playSoundAtEntity(player, "thkaguyamod:down", THKaguyaConfig.DownVol, 1.0F);
			
	    	for(int i = 0; i < player.inventory.mainInventory.length; i++)
	    	{
	    		if (player.inventory.mainInventory[i] != null && 
	    			player.inventory.mainInventory[i].getItem() == THKaguyaItems.extend_item)//エクステンドアイテムなら
	            {
	    			player.inventory.mainInventory[i].stackSize--;
	    			player.hurtResistantTime = 100;
	    	    	player.isDead = false;
	    	    	player.setHealth(player.getMaxHealth());
	    	    	EntitySpellCardCircle circle = new EntitySpellCardCircle(player.worldObj, player, THShotLib.PURPLE, player.hurtResistantTime);
	    	    	if(!player.worldObj.isRemote)
	    	    	{
	    	    		player.worldObj.spawnEntityInWorld(circle);
	    	    		
	    	    	}
	            	break;
	            }
	    	}
		}
	}
}
