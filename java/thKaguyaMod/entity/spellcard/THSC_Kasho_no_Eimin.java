/*  1:   */ package thKaguyaMod.entity.spellcard;
/*  2:   */ 
/*  3:   */ import java.util.Random;
/*  4:   */ import net.minecraft.entity.EntityLivingBase;
/*  5:   */ import net.minecraft.util.Vec3;
/*  6:   */ import thKaguyaMod.THShotLib;
/*  7:   */ 
/*  8:   */ public class THSC_Kasho_no_Eimin
/*  9:   */   extends THSpellCard
/* 10:   */ {
/* 11:   */   public THSC_Kasho_no_Eimin()
/* 12:   */   {
/* 13:13 */     setNeedLevel(3);
/* 14:14 */     setRemoveTime(40);
/* 15:15 */     setEndTime(150);
/* 16:16 */     setOriginalUserName(19);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void spellcard_main()
/* 20:   */   {
/* 21:22 */     if ((this.time % 6 == 3) && (this.time < 90))
/* 22:   */     {
/* 23:24 */       Vec3 angle = THShotLib.getVecFromAngle(this.user.rotationYaw, this.user.rotationPitch, 1.0D);
/* 24:25 */       if (!this.target.isDead) {
/* 25:27 */         angle = THShotLib.angle_ToPos(pos_User(), pos_Target());
/* 26:   */       }
/* 27:29 */       int color = 4;
/* 28:30 */       if (this.time % 9 == 0) {
/* 29:32 */         color = 3;
/* 30:   */       }
/* 31:34 */       float angleBase = this.rand.nextFloat() * 360.0F;
/* 32:35 */       THShotLib.createCircleShot(this.user, this.user, pos_User(), angle, 1.6F, 30, 0.2D, 0.7D, 0.01D, gravity_Zero(), shot(7, color, THShotLib.SIZE[7], 6.0F, 3, 70), 15, 0.8D, angleBase);
/* 33:36 */       THShotLib.createCircleShot(this.user, this.user, pos_User(), angle, -1.6F, 30, 0.2D, 0.7D, 0.01D, gravity_Zero(), shot(7, color, THShotLib.SIZE[7], 6.0F, 3, 70), 15, 0.8D, angleBase);
/* 34:   */     }
/* 35:   */   }
/* 36:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.THSC_Kasho_no_Eimin
 * JD-Core Version:    0.7.0.1
 */