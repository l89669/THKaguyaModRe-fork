/*  1:   */ package thKaguyaMod.entity.spellcard;
/*  2:   */ 
/*  3:   */ import net.minecraft.entity.EntityLivingBase;
/*  4:   */ import net.minecraft.util.MathHelper;
/*  5:   */ import net.minecraft.util.Vec3;
/*  6:   */ import thKaguyaMod.THShotLib;
/*  7:   */ 
/*  8:   */ public class THSC_HoukaKenran
/*  9:   */   extends THSpellCard
/* 10:   */ {
/* 11:   */   public THSC_HoukaKenran()
/* 12:   */   {
/* 13:15 */     setNeedLevel(2);
/* 14:16 */     setRemoveTime(60);
/* 15:17 */     setEndTime(170);
/* 16:18 */     setOriginalUserName(5);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void spellcard_main()
/* 20:   */   {
/* 21:24 */     if ((this.time < 150) && (this.time % 3 == 0))
/* 22:   */     {
/* 23:26 */       Vec3 angle = this.user.getLookVec();
/* 24:27 */       if (this.target.getHealth() > 0.0F) {
/* 25:29 */         angle = angle_UserToTarget();
/* 26:   */       }
/* 27:32 */       THShotLib.createRingShot(this.user, pos_User(), angle, 0.4D, shot(11, 3, THShotLib.SIZE[11], 5.0F), 6, 0.6D, 45.0F + MathHelper.sin(this.time / 20.0F) * 40.0F, this.time * 2.0F);
/* 28:33 */       THShotLib.createRingShot(this.user, pos_User(), angle, 0.4D, shot(11, 3, THShotLib.SIZE[11], 5.0F), 6, 0.6D, 45.0F - MathHelper.sin(this.time / 20.0F) * 40.0F, this.time * -2.0F);
/* 29:   */     }
/* 30:35 */     if ((this.time < 150) && (this.time % 20 == 19))
/* 31:   */     {
/* 32:37 */       Vec3 angle = this.user.getLookVec();
/* 33:38 */       if (this.target.getHealth() > 0.0F) {
/* 34:40 */         angle = angle_UserToTarget();
/* 35:   */       }
/* 36:43 */       THShotLib.createRingShot(this.user, pos_User(), angle, 0.4D, shot(11, 0, THShotLib.SIZE[11], 5.0F), 32, 0.6D, 45.0F, this.time * -2.0F);
/* 37:   */     }
/* 38:   */   }
/* 39:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.THSC_HoukaKenran
 * JD-Core Version:    0.7.0.1
 */