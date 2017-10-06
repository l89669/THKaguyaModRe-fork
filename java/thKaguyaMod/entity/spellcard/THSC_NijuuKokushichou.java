/*  1:   */ package thKaguyaMod.entity.spellcard;
/*  2:   */ 
/*  3:   */ import java.util.Random;
/*  4:   */ import net.minecraft.entity.Entity;
/*  5:   */ import net.minecraft.util.MovingObjectPosition;
/*  6:   */ import net.minecraft.world.World;
/*  7:   */ import thKaguyaMod.THShotLib;
/*  8:   */ import thKaguyaMod.entity.shot.EntityTHShot;
/*  9:   */ import thKaguyaMod.entity.shot.ISpecialShot;
/* 10:   */ import thKaguyaMod.registry.SpecialShotRegistry;
/* 11:   */ 
/* 12:   */ public class THSC_NijuuKokushichou
/* 13:   */   extends THSpellCard
/* 14:   */   implements ISpecialShot
/* 15:   */ {
/* 16:   */   public static final int SPECIAL_KOKUSHICHOU01 = 500;
/* 17:   */   
/* 18:   */   public THSC_NijuuKokushichou()
/* 19:   */   {
/* 20:20 */     setNeedLevel(3);
/* 21:21 */     setRemoveTime(20);
/* 22:22 */     setEndTime(200);
/* 23:23 */     setOriginalUserName(21);
/* 24:   */     
/* 25:25 */     SpecialShotRegistry.registerSpecialShot(THSC_NijuuKokushichou.class, 500);
/* 26:   */   }
/* 27:   */   
/* 28:   */   public void spellcard_main()
/* 29:   */   {
/* 30:31 */     if ((this.time % 70 == 10) && (this.time <= 100))
/* 31:   */     {
/* 32:33 */       for (int i = 0; i < 30; i++)
/* 33:   */       {
/* 34:38 */         double speed = this.rand.nextDouble() * 2.0D + 0.5D;
/* 35:39 */         float angleXZ = this.rand.nextFloat() * 360.0F;
/* 36:40 */         float angleY = this.rand.nextFloat() * 180.0F - 90.0F;
/* 37:41 */         THShotLib.createCircleShot(this.user, this.user, pos_User(), getVecFromAngle(angleXZ, angleY), speed, 0.0D, 1.0D, gravity_Zero(), shot(7, i % 2, THShotLib.SIZE[7], 8.0F, 1, 120, 500), 8, 0.2D, 0.0F);
/* 38:   */       }
/* 39:43 */       THShotLib.playShotSound(this.user);
/* 40:   */     }
/* 41:   */   }
/* 42:   */   
/* 43:   */   public void specialShot_move(World world, int id, EntityTHShot shot)
/* 44:   */   {
/* 45:49 */     switch (id)
/* 46:   */     {
/* 47:   */     case 500: 
/* 48:52 */       if (shot.ticksExisted < 25)
/* 49:   */       {
/* 50:54 */         shot.motionX *= 0.9D;
/* 51:55 */         shot.motionY *= 0.9D;
/* 52:56 */         shot.motionZ *= 0.9D;
/* 53:   */       }
/* 54:58 */       else if (shot.ticksExisted == 25)
/* 55:   */       {
/* 56:60 */         shot.shotLimitSpeed = 2.0D;
/* 57:61 */         shot.shotAcceleration = 0.03D;
/* 58:   */       }
/* 59:63 */       else if (shot.ticksExisted < 50)
/* 60:   */       {
/* 61:65 */         if (shot.getShotColor() % 2 == 0) {
/* 62:67 */           shot.setShotRotationYaw(10.0F);
/* 63:   */         } else {
/* 64:71 */           shot.setShotRotationYaw(-10.0F);
/* 65:   */         }
/* 66:   */       }
/* 67:   */       break;
/* 68:   */     }
/* 69:   */   }
/* 70:   */   
/* 71:   */   public boolean specialShot_hitBlock(World world, int id, EntityTHShot shot, MovingObjectPosition movingObjectPosition)
/* 72:   */   {
/* 73:87 */     return false;
/* 74:   */   }
/* 75:   */   
/* 76:   */   public boolean specialShot_hitEntity(World world, int id, EntityTHShot shot, Entity entity_Hit)
/* 77:   */   {
/* 78:94 */     return false;
/* 79:   */   }
/* 80:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.THSC_NijuuKokushichou
 * JD-Core Version:    0.7.0.1
 */