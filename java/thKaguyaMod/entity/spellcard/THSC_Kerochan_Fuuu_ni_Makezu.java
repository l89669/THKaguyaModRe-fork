/*  1:   */ package thKaguyaMod.entity.spellcard;
/*  2:   */ 
/*  3:   */ import java.util.Random;
/*  4:   */ import net.minecraft.util.MathHelper;
/*  5:   */ import thKaguyaMod.THShotLib;
/*  6:   */ 
/*  7:   */ public class THSC_Kerochan_Fuuu_ni_Makezu
/*  8:   */   extends THSpellCard
/*  9:   */ {
/* 10:   */   public THSC_Kerochan_Fuuu_ni_Makezu()
/* 11:   */   {
/* 12:13 */     setNeedLevel(2);
/* 13:14 */     setRemoveTime(40);
/* 14:15 */     setEndTime(200);
/* 15:16 */     setOriginalUserName(43);
/* 16:   */   }
/* 17:   */   
/* 18:   */   public void spellcard_main()
/* 19:   */   {
/* 20:22 */     if (this.time < 2) {
/* 21:22 */       return;
/* 22:   */     }
/* 23:26 */     if (this.time < 140) {
/* 24:28 */       for (int i = 0; i < 4; i++)
/* 25:   */       {
/* 26:30 */         double speed = 0.5D;
/* 27:   */         
/* 28:32 */         THShotLib.createShot(this.user, this.user, pos(this.cardPosX, this.cardPosY - 0.4D, this.cardPosZ), angle(this.card.rotationYaw + i * 90.0F + MathHelper.cos(this.time * 4.0F) * 45.0F, MathHelper.cos((this.time + i * 45.0F) * 2.0F / 180.0F * 3.141593F) * 19.0F - 70.0F), 0.0F, speed, speed, 0.0D, THShotLib.gravity(0.0D, -0.009999999776482582D, 0.0D), shot(5, 1, THShotLib.SIZE[5], 8.0F, 10, 120));
/* 29:   */       }
/* 30:   */     }
/* 31:36 */     for (int i = 0; i < 2; i++)
/* 32:   */     {
/* 33:38 */       double speed2 = this.rand.nextDouble() * 0.2D + 0.45D;
/* 34:39 */       THShotLib.createShot(this.user, this.user, pos(this.cardPosX, this.cardPosY - 0.4D, this.cardPosZ), angle(this.rand.nextFloat() * 360.0F, this.rand.nextFloat() * 14.0F - 75.0F), 0.0F, speed2, speed2, 0.0D, THShotLib.gravity(0.0D, -0.01799999922513962D, 0.0D), shot(10, 5, 0.15F, 9.0F, 4, 120));
/* 35:   */     }
/* 36:   */   }
/* 37:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.THSC_Kerochan_Fuuu_ni_Makezu
 * JD-Core Version:    0.7.0.1
 */