/*  1:   */ package thKaguyaMod.entity.spellcard;
/*  2:   */ 
/*  3:   */ import net.minecraft.entity.EntityLivingBase;
/*  4:   */ import net.minecraft.util.Vec3;
/*  5:   */ import thKaguyaMod.LaserData;
/*  6:   */ import thKaguyaMod.THShotLib;
/*  7:   */ 
/*  8:   */ public class THSC_MoonlightRay
/*  9:   */   extends THSpellCard
/* 10:   */ {
/* 11:   */   public THSC_MoonlightRay()
/* 12:   */   {
/* 13:13 */     setNeedLevel(1);
/* 14:14 */     setRemoveTime(30);
/* 15:15 */     setEndTime(60);
/* 16:16 */     setOriginalUserName(2);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void spellcard_main()
/* 20:   */   {
/* 21:22 */     if (this.time == 3)
/* 22:   */     {
/* 23:24 */       Vec3 look = this.user.getLookVec();
/* 24:   */       
/* 25:   */ 
/* 26:27 */       Vec3 over = THShotLib.getVecFromAngle(this.user.rotationYaw, this.user.rotationPitch + 90.0F);
/* 27:28 */       int way = 7;
/* 28:29 */       float angle = 0.0F;
/* 29:30 */       float angleSpan = 360.0F / way;
/* 30:31 */       for (int i = 0; i < way; i++)
/* 31:   */       {
/* 32:33 */         Vec3 laserVec = THShotLib.getVectorFromRotation(look.xCoord, look.yCoord, look.zCoord, over.xCoord, over.yCoord, over.zCoord, angle);
/* 33:34 */         Vec3 rotate = THShotLib.getOuterProduct(look, laserVec);
/* 34:35 */         THShotLib.createLaserB(this.user, this.user, pos_User(), laserVec, rotate, -2.0F, 9999, 
/* 35:36 */           LaserData.laser(1, 0.8F + this.level * 0.2F, 40.0F, 6.0F, 10, 29 + this.level * 5, 0), this.user, 1.0D, this.user.height / 2.0D);
/* 36:37 */         angle += angleSpan;
/* 37:   */       }
/* 38:   */     }
/* 39:   */   }
/* 40:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.THSC_MoonlightRay
 * JD-Core Version:    0.7.0.1
 */