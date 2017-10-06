/*  1:   */ package thKaguyaMod.entity.spellcard;
/*  2:   */ 
/*  3:   */ import java.util.Random;
/*  4:   */ import net.minecraft.entity.EntityLivingBase;
/*  5:   */ import net.minecraft.util.Vec3;
/*  6:   */ import thKaguyaMod.THShotLib;
/*  7:   */ 
/*  8:   */ public class THSC_ScarletShoot
/*  9:   */   extends THSpellCard
/* 10:   */ {
/* 11:   */   public THSC_ScarletShoot()
/* 12:   */   {
/* 13:13 */     setNeedLevel(4);
/* 14:14 */     setRemoveTime(30);
/* 15:15 */     setEndTime(120);
/* 16:16 */     setOriginalUserName(9);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void spellcard_main()
/* 20:   */   {
/* 21:22 */     if ((this.time % 25 == 20) && (this.time < 60))
/* 22:   */     {
/* 23:24 */       Vec3 lookAt = THShotLib.getVecFromAngle(this.user.rotationYaw, this.user.rotationPitch, 1.0D);
/* 24:25 */       if (!this.target.isDead) {
/* 25:27 */         lookAt = THShotLib.angle_ToPos(pos_User(), pos_Target());
/* 26:   */       }
/* 27:29 */       Vec3 rotate = THShotLib.getOverVector(lookAt);
/* 28:30 */       float angle = -90.0F;
/* 29:31 */       float angleSpan = 45.0F;
/* 30:33 */       for (int i = 0; i < 5; i++)
/* 31:   */       {
/* 32:35 */         scarletShot(THShotLib.getVectorFromRotation(rotate, lookAt, angle), 1.0D, false);
/* 33:36 */         angle += angleSpan;
/* 34:   */       }
/* 35:38 */       THShotLib.playShotSound(this.user);
/* 36:   */     }
/* 37:40 */     else if (this.time == 80)
/* 38:   */     {
/* 39:42 */       Vec3 lookAt = THShotLib.getVecFromAngle(this.user.rotationYaw, this.user.rotationPitch, 1.0D);
/* 40:43 */       if (!this.target.isDead) {
/* 41:45 */         lookAt = THShotLib.angle_ToPos(pos_User(), pos_Target());
/* 42:   */       }
/* 43:47 */       Vec3 rotate = THShotLib.getOverVector(lookAt);
/* 44:48 */       float angle = -3.0F;
/* 45:49 */       float angleSpan = 3.0F;
/* 46:51 */       for (int i = 0; i < 3; i++)
/* 47:   */       {
/* 48:53 */         scarletShot(THShotLib.getVectorFromRotation(rotate, lookAt, angle), 1.0D, false);
/* 49:54 */         angle += angleSpan;
/* 50:   */       }
/* 51:56 */       THShotLib.playShotSound(this.user);
/* 52:   */     }
/* 53:   */   }
/* 54:   */   
/* 55:   */   public void scarletShot(Vec3 angle, double speed, boolean isCatadi)
/* 56:   */   {
/* 57:63 */     int special = 0;
/* 58:64 */     int color = 0;
/* 59:67 */     if (isCatadi)
/* 60:   */     {
/* 61:69 */       special = 43;
/* 62:70 */       color = 1;
/* 63:   */     }
/* 64:72 */     THShotLib.createShot(this.user, this.user, pos_User(), angle, 0.0F, speed, speed, 0.0D, gravity_Zero(), shot(30, color, THShotLib.SIZE[30], 6.0F, 0, 120, special));
/* 65:73 */     for (int i = 0; i < 11; i++)
/* 66:   */     {
/* 67:75 */       double speed2 = (0.5D + this.rand.nextDouble() * 0.3D) * speed;
/* 68:   */       
/* 69:77 */       THShotLib.createRandomRingShot(this.user, pos_User(), angle, speed2, speed2, 0.0D, shot(2, color, THShotLib.SIZE[2], 4.0F, 0, 120, special), 1, 0.0D, 30.0F);
/* 70:   */     }
/* 71:79 */     for (int i = 0; i < 22; i++)
/* 72:   */     {
/* 73:81 */       double speed2 = (0.2D + this.rand.nextDouble() * 0.3D) * speed;
/* 74:   */       
/* 75:83 */       THShotLib.createRandomRingShot(this.user, pos_User(), angle, speed2, speed2, 0.0D, shot(0, color, THShotLib.SIZE[0], 2.0F, 0, 120, special), 1, 0.0D, 60.0F);
/* 76:   */     }
/* 77:   */   }
/* 78:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.THSC_ScarletShoot
 * JD-Core Version:    0.7.0.1
 */