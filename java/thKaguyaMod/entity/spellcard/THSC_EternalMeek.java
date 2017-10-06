/*  1:   */ package thKaguyaMod.entity.spellcard;
/*  2:   */ 
/*  3:   */ import net.minecraft.entity.EntityLivingBase;
/*  4:   */ import net.minecraft.util.Vec3;
/*  5:   */ import thKaguyaMod.THShotLib;
/*  6:   */ 
/*  7:   */ public class THSC_EternalMeek
/*  8:   */   extends THSpellCard
/*  9:   */ {
/* 10:   */   public THSC_EternalMeek()
/* 11:   */   {
/* 12:12 */     setNeedLevel(2);
/* 13:13 */     setRemoveTime(20);
/* 14:14 */     setEndTime(50);
/* 15:15 */     setOriginalUserName(8);
/* 16:   */   }
/* 17:   */   
/* 18:   */   public void spellcard_main()
/* 19:   */   {
/* 20:21 */     if (this.time < 40)
/* 21:   */     {
/* 22:23 */       Vec3 angle = this.user.getLookVec();
/* 23:24 */       if (!this.target.isDead) {
/* 24:27 */         angle = THShotLib.angle_ToPos(pos_User(), pos_Target());
/* 25:   */       }
/* 26:29 */       THShotLib.createRandomRingShot(this.user, this.user, pos_User(angle, 0.7D), angle, 0.0F, 9999, 1.5D, 1.5D, 0.0D, gravity_Zero(), shot(28, 1, THShotLib.SIZE[28], 3.0F, 3, 60, 0), 3, 0.1D, 50.0F);
/* 27:   */       
/* 28:31 */       THShotLib.playShotSound(this.user);
/* 29:   */     }
/* 30:   */   }
/* 31:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.THSC_EternalMeek
 * JD-Core Version:    0.7.0.1
 */