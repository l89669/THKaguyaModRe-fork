/*  1:   */ package thKaguyaMod.entity.spellcard;
/*  2:   */ 
/*  3:   */ import java.util.Random;
/*  4:   */ import net.minecraft.entity.Entity;
/*  5:   */ import net.minecraft.entity.EntityLivingBase;
/*  6:   */ import net.minecraft.util.MovingObjectPosition;
/*  7:   */ import net.minecraft.world.World;
/*  8:   */ import thKaguyaMod.ShotData;
/*  9:   */ import thKaguyaMod.THShotLib;
/* 10:   */ import thKaguyaMod.entity.shot.EntityTHShot;
/* 11:   */ import thKaguyaMod.entity.shot.ISpecialShot;
/* 12:   */ import thKaguyaMod.registry.SpecialShotRegistry;
/* 13:   */ 
/* 14:   */ public class THSC_KappaPororoca
/* 15:   */   extends THSpellCard
/* 16:   */   implements ISpecialShot
/* 17:   */ {
/* 18:   */   public static final int SPECIAL_KAPPA_POROROCA01 = 1100;
/* 19:   */   
/* 20:   */   public THSC_KappaPororoca()
/* 21:   */   {
/* 22:22 */     setNeedLevel(3);
/* 23:23 */     setRemoveTime(30);
/* 24:24 */     setEndTime(140);
/* 25:25 */     setOriginalUserName(39);
/* 26:   */     
/* 27:27 */     SpecialShotRegistry.registerSpecialShot(THSC_KappaPororoca.class, 1100);
/* 28:   */   }
/* 29:   */   
/* 30:   */   public void spellcard_main()
/* 31:   */   {
/* 32:33 */     if (this.time < 5) {
/* 33:33 */       return;
/* 34:   */     }
/* 35:35 */     if (this.time % 10 == 0)
/* 36:   */     {
/* 37:38 */       THShotLib.createShot(this.user, this.user, pos_User(), getVecFromAngle(this.user.rotationYaw + 90.0F, -60.0F), 0.0F, 0.3D, 0.3D, 0.0D, gravity_Default(), 
/* 38:39 */         shot(5, 1, 0.45F, 8.0F, 2, 60, 1100));
/* 39:40 */       THShotLib.createShot(this.user, this.user, pos_User(), getVecFromAngle(this.user.rotationYaw - 90.0F, -60.0F), 0.0F, 0.3D, 0.3D, 0.0D, gravity_Default(), 
/* 40:41 */         shot(5, 1, 0.45F, 8.0F, 2, 60, 1100));
/* 41:   */     }
/* 42:   */   }
/* 43:   */   
/* 44:   */   public void specialShot_move(World world, int id, EntityTHShot shot)
/* 45:   */   {
/* 46:47 */     switch (id)
/* 47:   */     {
/* 48:   */     case 1100: 
/* 49:50 */       if (shot.ticksExisted % 4 == 3)
/* 50:   */       {
/* 51:52 */         ShotData shotData = ShotData.shot(5, 1, 0.3F, 5.0F, 0, 100, 40);
/* 52:53 */         THShotLib.createShot(shot.user, shot, THShotLib.pos_Entity(shot), angle(shot.user.rotationYaw + this.rand.nextFloat() * 30.0F - 15.0F, -15.0F), 0.0F, 0.7D, 0.7D, 0.0D, gravity_Default(), shotData);
/* 53:   */       }
/* 54:55 */       break;
/* 55:   */     }
/* 56:   */   }
/* 57:   */   
/* 58:   */   public boolean specialShot_hitBlock(World world, int id, EntityTHShot shot, MovingObjectPosition movingObjectPosition)
/* 59:   */   {
/* 60:66 */     switch (id)
/* 61:   */     {
/* 62:   */     case 1100: 
/* 63:69 */       shot.bound(movingObjectPosition, 0.9D, 4);
/* 64:70 */       return true;
/* 65:   */     }
/* 66:72 */     return false;
/* 67:   */   }
/* 68:   */   
/* 69:   */   public boolean specialShot_hitEntity(World world, int id, EntityTHShot shot, Entity entity_Hit)
/* 70:   */   {
/* 71:80 */     return false;
/* 72:   */   }
/* 73:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.THSC_KappaPororoca
 * JD-Core Version:    0.7.0.1
 */