/*  1:   */ package thKaguyaMod.entity.spellcard;
/*  2:   */ 
/*  3:   */ import net.minecraft.entity.EntityLivingBase;
/*  4:   */ import net.minecraft.util.Vec3;
/*  5:   */ import thKaguyaMod.THShotLib;
/*  6:   */ 
/*  7:   */ public class THSC_Catadioptric
/*  8:   */   extends THSC_ScarletShoot
/*  9:   */ {
/* 10:   */   public THSC_Catadioptric()
/* 11:   */   {
/* 12:12 */     setNeedLevel(3);
/* 13:13 */     setRemoveTime(40);
/* 14:14 */     setEndTime(110);
/* 15:15 */     setOriginalUserName(10);
/* 16:   */   }
/* 17:   */   
/* 18:   */   public void spellcard_main()
/* 19:   */   {
/* 20:21 */     if ((this.time % 25 == 1) && (this.time < 75))
/* 21:   */     {
/* 22:24 */       float angleSpan = 22.5F;
/* 23:25 */       float angleBase = 180.0F;
/* 24:26 */       if (this.time == 26) {
/* 25:28 */         angleBase = 90.0F;
/* 26:30 */       } else if (this.time == 51) {
/* 27:32 */         angleBase = -90.0F;
/* 28:   */       }
/* 29:34 */       float angle = angleBase - angleSpan * 2.0F;
/* 30:36 */       for (int i = 0; i < 5; i++)
/* 31:   */       {
/* 32:38 */         Vec3 look = THShotLib.getRotationVectorFromAngle(this.user.rotationYaw, this.user.rotationPitch, angle, 1.0D);
/* 33:39 */         scarletShot(look, 0.6D, true);
/* 34:40 */         angle += angleSpan;
/* 35:   */       }
/* 36:42 */       THShotLib.playShotSound(this.user);
/* 37:   */     }
/* 38:   */   }
/* 39:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.THSC_Catadioptric
 * JD-Core Version:    0.7.0.1
 */