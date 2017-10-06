/*  1:   */ package thKaguyaMod.entity.spellcard;
/*  2:   */ 
/*  3:   */ import net.minecraft.entity.Entity;
/*  4:   */ import net.minecraft.util.MovingObjectPosition;
/*  5:   */ import net.minecraft.world.World;
/*  6:   */ import thKaguyaMod.ShotData;
/*  7:   */ import thKaguyaMod.THShotLib;
/*  8:   */ import thKaguyaMod.entity.shot.EntityTHShot;
/*  9:   */ import thKaguyaMod.entity.shot.ISpecialShot;
/* 10:   */ import thKaguyaMod.registry.SpecialShotRegistry;
/* 11:   */ 
/* 12:   */ public class THSC_Zenzinrui_no_Hisouten
/* 13:   */   extends THSpellCard
/* 14:   */   implements ISpecialShot
/* 15:   */ {
/* 16:   */   public static final int SPECIAL_HISOUTEN01 = 700;
/* 17:   */   
/* 18:   */   public THSC_Zenzinrui_no_Hisouten()
/* 19:   */   {
/* 20:22 */     setNeedLevel(5);
/* 21:23 */     setRemoveTime(30);
/* 22:24 */     setEndTime(150);
/* 23:25 */     setOriginalUserName(45);
/* 24:   */     
/* 25:27 */     SpecialShotRegistry.registerSpecialShot(THSC_Zenzinrui_no_Hisouten.class, 700);
/* 26:   */   }
/* 27:   */   
/* 28:   */   public void specialShot_move(World world, int id, EntityTHShot shot)
/* 29:   */   {
/* 30:32 */     switch (id)
/* 31:   */     {
/* 32:   */     case 700: 
/* 33:35 */       if (shot.ticksExisted < 20) {
/* 34:37 */         shot.setShotSize(shot.getShotSize() + 0.1F);
/* 35:   */       }
/* 36:   */       break;
/* 37:   */     }
/* 38:   */   }
/* 39:   */   
/* 40:   */   public boolean specialShot_hitBlock(World world, int id, EntityTHShot shot, MovingObjectPosition movingObjectPosition)
/* 41:   */   {
/* 42:48 */     switch (id)
/* 43:   */     {
/* 44:   */     case 700: 
/* 45:51 */       ShotData shotData = ShotData.shot(31, 0, 0.3F, 4.0F, 0, 90);
/* 46:52 */       THShotLib.createShot(shot.user, shot, THShotLib.pos_Entity(shot), THShotLib.angle_Random(), 0.0F, 0.01D, 0.15D, 0.01D, gravity_Zero(), shotData);
/* 47:53 */       return false;
/* 48:   */     }
/* 49:55 */     return false;
/* 50:   */   }
/* 51:   */   
/* 52:   */   public boolean specialShot_hitEntity(World world, int id, EntityTHShot shot, Entity entity_Hit)
/* 53:   */   {
/* 54:63 */     return false;
/* 55:   */   }
/* 56:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.THSC_Zenzinrui_no_Hisouten
 * JD-Core Version:    0.7.0.1
 */