/*  1:   */ package thKaguyaMod.entity.spellcard;
/*  2:   */ 
/*  3:   */ import java.util.Random;
/*  4:   */ import net.minecraft.entity.EntityLivingBase;
/*  5:   */ import net.minecraft.util.Vec3;
/*  6:   */ import thKaguyaMod.THShotLib;
/*  7:   */ 
/*  8:   */ public class THSC_Mishagujisama
/*  9:   */   extends THSpellCard
/* 10:   */ {
/* 11:   */   public THSC_Mishagujisama()
/* 12:   */   {
/* 13:13 */     setNeedLevel(3);
/* 14:14 */     setRemoveTime(20);
/* 15:15 */     setEndTime(90);
/* 16:16 */     setOriginalUserName(43);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void spellcard_main()
/* 20:   */   {
/* 21:22 */     if (this.time % 9 == 3)
/* 22:   */     {
/* 23:24 */       float angleBase = this.rand.nextFloat() * 360.0F;
/* 24:25 */       Vec3 angle = THShotLib.getVecFromAngle(this.user.rotationYaw, 0.0F, 1.0D);
/* 25:26 */       Vec3 rotate = THShotLib.getVecFromAngle(this.user.rotationYaw, this.user.rotationPitch + 90.0F, 1.0D);
/* 26:27 */       Vec3 rotate2 = THShotLib.getVectorFromRotation(rotate.xCoord, rotate.yCoord, rotate.zCoord, angle.xCoord, angle.yCoord, angle.zCoord, 90.0F);
/* 27:28 */       THShotLib.createCircleShot(this.user, this.user, pos(this.user.posX, this.user.posY + 0.2D, this.user.posZ), angle, 1.2F, 30, 1.9D, 0.2D, -0.15D, gravity_Zero(), shot(10, 2, THShotLib.SIZE[10], 7.0F, 3, 120), 32, 0.7D, angleBase);
/* 28:29 */       THShotLib.createCircleShot(this.user, this.user, pos(this.user.posX, this.user.posY + 0.2D, this.user.posZ), angle, -1.2F, 30, 1.9D, 0.2D, -0.15D, gravity_Zero(), shot(10, 2, THShotLib.SIZE[10], 7.0F, 3, 120), 32, 0.7D, angleBase);
/* 29:   */     }
/* 30:   */   }
/* 31:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.THSC_Mishagujisama
 * JD-Core Version:    0.7.0.1
 */