/*  1:   */ package thKaguyaMod.entity.spellcard;
/*  2:   */ 
/*  3:   */ import net.minecraft.entity.EntityLivingBase;
/*  4:   */ import net.minecraft.util.Vec3;
/*  5:   */ import thKaguyaMod.LaserData;
/*  6:   */ import thKaguyaMod.THShotLib;
/*  7:   */ import thKaguyaMod.entity.shot.EntityTHShot;
/*  8:   */ 
/*  9:   */ public class THSC_Kachoufuugetu
/* 10:   */   extends THSpellCard
/* 11:   */ {
/* 12:   */   public THSC_Kachoufuugetu()
/* 13:   */   {
/* 14:14 */     setNeedLevel(5);
/* 15:15 */     setRemoveTime(40);
/* 16:16 */     setEndTime(90);
/* 17:17 */     setOriginalUserName(33);
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void spellcard_main()
/* 21:   */   {
/* 22:23 */     Vec3 look = THShotLib.getVecFromAngle(this.user.rotationYaw, this.user.rotationPitch, 1.0D);
/* 23:24 */     if (!this.target.isDead) {
/* 24:26 */       look = THShotLib.angle_ToPos(pos_User(), pos_Target());
/* 25:   */     }
/* 26:28 */     Vec3 rotate = THShotLib.getVecFromAngle(this.user.rotationYaw, this.user.rotationPitch + 90.0F);
/* 27:   */     
/* 28:   */ 
/* 29:31 */     float angle = this.time * 3.0F;
/* 30:33 */     if (this.time > 30) {
/* 31:35 */       angle = -this.time * 3.0F;
/* 32:   */     }
/* 33:39 */     if (this.time > 60) {}
/* 34:45 */     rotate = THShotLib.getVecFromAngle(this.user.rotationYaw, this.user.rotationPitch + 5.0F + (float)Math.sin(this.time * 10.0F / 180.0F * 3.141593F) * 50.0F);
/* 35:47 */     for (int i = 0; i < 5; i++)
/* 36:   */     {
/* 37:49 */       Vec3 look2 = THShotLib.getVectorFromRotation(look.xCoord, look.yCoord, look.zCoord, rotate.xCoord, rotate.yCoord, rotate.zCoord, angle);
/* 38:50 */       Vec3 look3 = THShotLib.getVectorFromRotation(look.xCoord, look.yCoord, look.zCoord, rotate.xCoord, rotate.yCoord, rotate.zCoord, -angle);
/* 39:51 */       if (this.time % 10 == 0)
/* 40:   */       {
/* 41:53 */         EntityTHShot mother = THShotLib.createShot(this.user, this.user, pos_User(look2, 2.0D), look2, 0.0F, rotate, 0.0F, 9999, 0.4D, 0.2D, 0.0D, gravity_Zero(), shot(2, 0, THShotLib.SIZE[30], 14.0F, 3, 80));
/* 42:54 */         float angle2 = 0.0F;
/* 43:55 */         Vec3 motherOver = THShotLib.getVecFromAngle(mother.rotationYaw, mother.rotationPitch + 90.0F);
/* 44:56 */         for (int j = 0; j < 5; j++)
/* 45:   */         {
/* 46:58 */           Vec3 set = THShotLib.getVectorFromRotation(mother.angle.xCoord, mother.angle.yCoord, mother.angle.zCoord, motherOver.xCoord, motherOver.yCoord, motherOver.zCoord, angle2);
/* 47:   */           
/* 48:60 */           THShotLib.createLaserB(this.user, mother, pos(0.0D, 0.0D, 0.0D), set, mother.angle, 4.0F, 9999, LaserData.laser(0, 0.5F, 2.0F, 6.0F, 0, 120, 0), mother, 0.0D, 0.0D);
/* 49:61 */           angle2 += 72.0F;
/* 50:   */         }
/* 51:   */       }
/* 52:64 */       else if (this.time % 2 == 0)
/* 53:   */       {
/* 54:66 */         THShotLib.createShot(this.user, this.user, pos_User(look2, 2.0D), look2, 0.0F, rotate, 0.0F, 9999, 0.4D, 0.2D, 0.0D, gravity_Zero(), shot(2, 3, THShotLib.SIZE[2], 14.0F, 3, 80));
/* 55:67 */         THShotLib.createShot(this.user, this.user, pos_User(look3, 2.0D), look3, 0.0F, rotate, 0.0F, 9999, 0.4D, 0.2D, 0.0D, gravity_Zero(), shot(2, 3, THShotLib.SIZE[2], 14.0F, 3, 80));
/* 56:   */       }
/* 57:69 */       angle += 72.0F;
/* 58:   */     }
/* 59:   */   }
/* 60:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.THSC_Kachoufuugetu
 * JD-Core Version:    0.7.0.1
 */