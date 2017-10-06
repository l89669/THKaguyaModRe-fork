/*  1:   */ package thKaguyaMod.event;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*  4:   */ import net.minecraft.entity.EntityLivingBase;
/*  5:   */ import net.minecraft.entity.player.EntityPlayer;
/*  6:   */ import net.minecraft.entity.player.InventoryPlayer;
/*  7:   */ import net.minecraft.item.ItemStack;
/*  8:   */ import net.minecraft.world.World;
/*  9:   */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/* 10:   */ import thKaguyaMod.entity.effect.EntitySpellCardCircle;
/* 11:   */ import thKaguyaMod.init.THKaguyaConfig;
/* 12:   */ import thKaguyaMod.init.THKaguyaItems;
/* 13:   */ 
/* 14:   */ public class THKaguyaPlayerDeathEventHandler
/* 15:   */ {
/* 16:   */   @SubscribeEvent
/* 17:   */   public void onLivingDeathEvent(LivingDeathEvent event)
/* 18:   */   {
/* 19:17 */     if (event.entityLiving.worldObj.isRemote) {
/* 20:18 */       return;
/* 21:   */     }
/* 22:22 */     if ((event.entityLiving instanceof EntityPlayer))
/* 23:   */     {
/* 24:23 */       EntityPlayer player = (EntityPlayer)event.entityLiving;
/* 25:   */       
/* 26:   */ 
/* 27:26 */       player.worldObj.playSoundAtEntity(player, "thkaguyamod:down", THKaguyaConfig.DownVol, 1.0F);
/* 28:28 */       for (int i = 0; i < player.inventory.mainInventory.length; i++) {
/* 29:30 */         if ((player.inventory.mainInventory[i] != null) && 
/* 30:31 */           (player.inventory.mainInventory[i].getItem() == THKaguyaItems.extend_item))
/* 31:   */         {
/* 32:33 */           player.inventory.mainInventory[i].stackSize -= 1;
/* 33:34 */           player.hurtResistantTime = 100;
/* 34:35 */           player.isDead = false;
/* 35:36 */           player.setHealth(player.getMaxHealth());
/* 36:37 */           EntitySpellCardCircle circle = new EntitySpellCardCircle(player.worldObj, player, 4, player.hurtResistantTime);
/* 37:38 */           if (player.worldObj.isRemote) {
/* 38:   */             break;
/* 39:   */           }
/* 40:40 */           player.worldObj.spawnEntityInWorld(circle); break;
/* 41:   */         }
/* 42:   */       }
/* 43:   */     }
/* 44:   */   }
/* 45:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.event.THKaguyaPlayerDeathEventHandler
 * JD-Core Version:    0.7.0.1
 */