/*  1:   */ package thKaguyaMod.event;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*  4:   */ import net.minecraft.entity.Entity;
/*  5:   */ import net.minecraft.world.World;
/*  6:   */ import net.minecraftforge.event.entity.EntityEvent;
/*  7:   */ 
/*  8:   */ public class THKaguyaTimeStopEventHandler
/*  9:   */ {
/* 10:   */   @SubscribeEvent
/* 11:   */   public void CanUpdate(EntityEvent event)
/* 12:   */   {
/* 13:13 */     Entity entity = event.entity;
/* 14:14 */     if (entity.worldObj.isRemote) {}
/* 15:   */   }
/* 16:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.event.THKaguyaTimeStopEventHandler
 * JD-Core Version:    0.7.0.1
 */