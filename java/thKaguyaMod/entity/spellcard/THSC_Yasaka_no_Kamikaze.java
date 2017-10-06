/*  1:   */ package thKaguyaMod.entity.spellcard;
/*  2:   */ 
/*  3:   */ import net.minecraft.entity.EntityLivingBase;
/*  4:   */ import net.minecraft.util.MathHelper;
/*  5:   */ import net.minecraft.util.Vec3;
/*  6:   */ import thKaguyaMod.THShotLib;
/*  7:   */ 
/*  8:   */ public class THSC_Yasaka_no_Kamikaze
/*  9:   */   extends THSpellCard
/* 10:   */ {
/* 11:   */   public THSC_Yasaka_no_Kamikaze()
/* 12:   */   {
/* 13:15 */     setNeedLevel(3);
/* 14:16 */     setRemoveTime(100);
/* 15:17 */     setEndTime(270);
/* 16:18 */     setOriginalUserName(41);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void spellcard_main()
/* 20:   */   {
/* 21:24 */     if ((this.time < 5) || (this.user == null)) {
/* 22:26 */       return;
/* 23:   */     }
/* 24:29 */     Vec3 angle = THShotLib.getVecFromAngle(this.user.rotationYaw, this.user.rotationPitch, -1.0D);
/* 25:30 */     if (!this.target.isDead) {
/* 26:32 */       angle = THShotLib.angle_ToPos(pos_Target(), pos_User());
/* 27:   */     }
/* 28:35 */     if (this.time % 46 < 23) {
/* 29:37 */       THShotLib.createRingShot(this.user, this.user, pos_User(), angle, 180.0F, 1, 0.0D, 1.6D, 0.1D, gravity_Zero(), shot(10, 2, THShotLib.SIZE[10], 18.0F, 10, 40, 180), 8, Math.cos(this.time * 2 / 180.0F * 3.141593F - 3.141593F) * 2.0D + 3.0D, MathHelper.sin(this.time * 16 / 180.0F * 3.141593F) * 50.0F + 50.0F, this.time * 10.0F);
/* 30:   */     } else {
/* 31:41 */       THShotLib.createRingShot(this.user, this.user, pos_User(), angle, -180.0F, 2, 0.0D, 1.6D, 0.1D, gravity_Zero(), shot(10, 5, THShotLib.SIZE[10], 18.0F, 10, 40, 180), 8, Math.cos(this.time * 2 / 180.0F * 3.141593F - 3.141593F) * 2.0D + 3.0D, MathHelper.sin(-this.time * 16 / 180.0F * 3.141593F) * 50.0F + 50.0F, -this.time * 10.0F);
/* 32:   */     }
/* 33:   */   }
/* 34:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.THSC_Yasaka_no_Kamikaze
 * JD-Core Version:    0.7.0.1
 */